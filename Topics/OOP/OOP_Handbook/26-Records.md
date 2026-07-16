# Chapter 26 — Records

**Part XI: Advanced OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain what a `record` automatically generates, and why that eliminates an entire category of hand-written boilerplate.
- Explain precisely why a record can never extend another class, and can never itself be subclassed — both direct consequences of chapters already covered.
- Add validation to a record using a compact constructor, without breaking its automatic generation.
- Decide correctly whether a given class — including `Employee` itself — is actually a good candidate to become a record.

---

## 1. Introduction

Chapter 25 closed by naming `record` as a complementary construct to `enum`: a compact way to declare an immutable, data-carrying class. This chapter delivers it — and along the way, revisits `ContactInfo` (Chapters 22–23) with a genuinely satisfying realization: it was always exactly the shape a record is built for.

> This chapter assumes Chapter 19's `equals()`/`hashCode()`/`toString()` contract, Chapter 15 §5's single-inheritance rule, and Chapter 10's `final` completely — a record is best understood as all three colliding into one compact, automatic construct.

---

## 2. Theory — What a Record Actually Generates

```java
public record ContactInfo(String phone, String email) { }
```

This single line generates, automatically: a `private final` field for each component; a **canonical constructor** accepting all components in order; public accessor methods named exactly after each component (`phone()`, `email()` — not `getPhone()`); and correct `equals()`, `hashCode()`, and `toString()` implementations, based on every component, following Chapter 19's full contract precisely.

### 2.1 What This Actually Saves — Concretely

Compare directly against Chapters 22–23's hand-written `ContactInfo`:

```java
// Hand-written (Chapters 22–23):
private static class ContactInfo {
    private final String phone;
    private final String email;
    ContactInfo(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }
    // equals(), hashCode(), toString() were never actually added —
    // this class has been silently incomplete by Chapter 19's standard since Chapter 22.
}

// Record equivalent:
private record ContactInfo(String phone, String email) { }
```

This is worth sitting with: the hand-written `ContactInfo` this handbook built back in Chapter 22 never actually got proper `equals()`/`hashCode()`/`toString()` overrides — by Chapter 19's own standard, it's been quietly incomplete ever since. A record would have supplied all three correctly, automatically, from day one.

---

## 3. Why a Record Can Never Extend Another Class

A record implicitly extends `java.lang.Record` — the exact same structural situation Chapter 25 §3 described for enums implicitly extending `java.lang.Enum`. Per Chapter 15 §5's single-inheritance rule, that uses up a record's one allowed parent slot entirely: **a record can never `extends` anything else**, though — again, exactly like enums — it **can** `implements` interfaces (Chapter 18).

---

## 4. Why a Record Can Never Be Subclassed

A record is also implicitly `final` (Chapter 10 §5) — no class may ever `extends` a record. This isn't incidental: a record's entire contract is *transparency* — its `equals()`, `hashCode()`, and `toString()` are defined completely by its declared components, and nothing else. Allowing subclasses would let a subclass add hidden state that record's contract explicitly promises not to have, silently breaking the very transparency the construct exists to guarantee.

---

## 5. Accessor Naming — A Deliberate Departure

Record accessors are named exactly after their component — `phone()`, not `getPhone()`. This is a deliberate break from the JavaBeans-style getter convention Chapter 12 established for ordinary encapsulated classes: a record isn't meant to *look* like a class hiding implementation behind accessors — it's meant to look like exactly what it is, a transparent, fixed shape of data.

---

## 6. Validation via Compact Constructors

Records aren't exempt from Chapter 12's principle that an object should never be constructible into an invalid state — they just validate differently, using a **compact constructor**, which omits the parameter list entirely since it's already declared in the record header:

```java
public record ContactInfo(String phone, String email) {
    public ContactInfo {   // compact constructor — no parameter list repeated
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
    }
}
```

The compact constructor's body runs before the fields are actually assigned — it can validate or even normalize the parameters (e.g., trimming whitespace), and whatever it leaves the parameters holding at the end is what gets assigned to the generated `final` fields.

---

## 7. Records Can Still Have Their Own Behavior

A record isn't *only* auto-generated boilerplate — its body can still declare additional methods, static fields, and static methods, exactly like an ordinary class:

```java
public record ContactInfo(String phone, String email) {
    public String maskedEmail() {
        return email.replaceAll("(?<=.).(?=[^@]*?@)", "*");
    }
}
```

What a record body **cannot** do is declare additional *instance fields* beyond its component list — a record's state is exactly, and only, its declared components. This is what preserves the transparency guarantee §4 described.

---

## 8. Could `Employee` Itself Be a Record?

This is worth asking directly, since it synthesizes several earlier chapters at once: **no** — and precisely, disqualifyingly so, for three separate reasons this handbook has already established:

- `Employee` has genuinely **mutable** state (`setSalary()`, Chapter 12) — a record's fields are always `final` (§2), incompatible with any setter at all.
- `Employee` participates in **inheritance** (`Manager`/`Intern extends Employee`, Chapter 15) — a record can never be extended (§4), which would make `Manager` and `Intern` impossible.
- `Employee` is `abstract` (Chapter 17) — a record is a concrete, final data shape by design; it cannot be `abstract` either.

`ContactInfo`, by contrast, passes every test cleanly: it's immutable, has no subclasses, and its entire purpose is holding a fixed shape of data — which is exactly why §2.1's comparison feels so natural, and exactly why `Employee` itself was never a candidate.

---

## 9. Real-World Example

```java
public abstract class Employee implements Payable {

    private record ContactInfo(String phone, String email) {
        public ContactInfo {
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Invalid email: " + email);
            }
        }
    }

    private final ContactInfo contactInfo;

    protected Employee(String employeeId, String name, double salary, String phone, String email) {
        this.employeeId = employeeId;
        this.name = name;
        setSalary(salary);
        this.contactInfo = new ContactInfo(phone, email);   // validated automatically at construction
    }

    // ... existing fields, abstract getSalary(), equals()/hashCode()/toString(), etc. ...
}
```

`ContactInfo` is now genuinely complete — correct `equals()`, `hashCode()`, and `toString()` included — for less code than the original Chapter 22 version had, which never got those overrides at all.

---

## 10. Best Practices

- Use records for genuine, simple, immutable data carriers — DTOs, value objects, API response shapes — where equality should be based purely on component values.
- Reach for a compact constructor (§6) whenever a record's components need validation, rather than assuming records can't enforce Chapter 12's "no invalid state" principle.
- Don't force an existing class with mutable state, inheritance participation, or abstraction into record form — §8's `Employee` analysis is the general test to apply.

## 11. Common Mistakes

- ⚠️ Trying to make a record `abstract`, or trying to have it `extends` another class — never legal, for the reasons in §3–§4.
- ⚠️ Expecting `getPhone()`/`getEmail()` naming — records use `phone()`/`email()` instead (§5).
- ⚠️ Assuming a record can't validate its inputs — the compact constructor (§6) handles this cleanly.
- ⚠️ Trying to add extra instance fields to a record beyond its declared components — a record's state is exactly its component list, by design (§7).

## 12. Interview Perspective

**Frequently Asked**

- *"What does declaring a record actually generate?"* — Private final fields per component, a canonical constructor, named accessors, and correct `equals()`/`hashCode()`/`toString()` based on every component (§2).
- *"Can a record extend a class?"* — No — it implicitly extends `java.lang.Record`, using up its one allowed parent slot (Chapter 15 §5), exactly like enums implicitly extending `Enum` (Chapter 25 §3). It can implement interfaces.
- *"Can a record be subclassed?"* — No — it's implicitly `final`, to preserve its transparency guarantee (§4).

**Tricky Question**

- *"Given everything covered about `Employee` across this handbook, could it ever be converted into a record?"* — No, for three independent, disqualifying reasons: mutable state, participation in inheritance, and being `abstract` (§8) — any one of the three would be enough on its own.

**Common Misconception**

- Believing records are "immune" to Chapter 19's equals/hashCode pitfalls by luck. They're immune by construction — the compiler generates a correctly-paired `equals()`/`hashCode()` together, every time, which is precisely the discipline Chapter 19 §5.1 had to teach as a manual rule for ordinary classes.

---

## 13. Summary

- A record is a restricted, compact class declaration that automatically generates private final fields, a canonical constructor, named accessors, and a correct `equals()`/`hashCode()`/`toString()` triplet.
- It implicitly extends `java.lang.Record` (so it can never extend anything else) and is implicitly `final` (so it can never be subclassed) — both to preserve a strict transparency guarantee.
- Validation belongs in a compact constructor, which omits the repeated parameter list.
- A record's state is exactly its declared components — no additional instance fields are allowed, though additional methods are.
- Not every class is a record candidate — mutable state, inheritance participation, or abstraction (as with `Employee`) each independently disqualify it.

## 14. Quick Revision

- Record = auto-generated fields + constructor + accessors + correct equals/hashCode/toString, in one line.
- Implicitly extends `Record` (no other superclass) — can implement interfaces.
- Implicitly `final` — can never be subclassed.
- Accessors named `x()`, not `getX()`.
- Validate via a compact constructor; no extra instance fields allowed.

## 15. Self Assessment

1. List everything `public record ContactInfo(String phone, String email) { }` generates automatically, without looking back at §2.
2. Why can a record never `extends` another class — connect your answer to a specific rule from an earlier chapter.
3. Write a compact constructor for a `Money(String currency, double amount)` record that rejects a negative `amount`.
4. Give the three independent, specific reasons `Employee` itself could never become a record.
5. Why are records structurally immune to the "overrode `equals()` but forgot `hashCode()`" bug that Chapter 19 §5.1 covered as a manual discipline for ordinary classes?

---

## What's Next

**Chapter 27 — Sealed Classes** covers a construct that works naturally alongside both records and Chapter 15's inheritance: a way to declare a class or interface whose set of permitted subclasses is fixed and known at compile time — closing a gap between the fully-open hierarchies Chapter 15 built and the fully-closed, fixed-set nature of an enum (Chapter 25).
