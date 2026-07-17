# Chapter 29 — Object Cloning

**Part XI: Advanced OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Distinguish shallow copy from deep copy precisely, and explain which one `Object.clone()` performs by default.
- Explain, with specific concrete reasons, why `Cloneable`/`clone()` is now widely considered a flawed design.
- Explain why `clone()` bypasses a class's constructor entirely — a genuinely unusual object-creation path compared to everything else in this handbook.
- Justify preferring a copy constructor (Chapter 5 §7) over `clone()` for new code.

---

## 1. Introduction

Chapter 28 §4 used `new ArrayList<>(skills)` as a "defensive copy" without naming precisely what kind of copy it was. This chapter formalizes that distinction — shallow vs. deep copy — and covers Java's built-in cloning mechanism, `Cloneable` and `Object.clone()`, including why this handbook, like most modern Java guidance, recommends against it.

---

## 2. Shallow Copy vs. Deep Copy

> **A shallow copy duplicates an object's own fields — for reference-type fields, this copies the reference itself, so the original and the copy end up sharing the same referenced object. A deep copy recursively duplicates every mutable object reachable from the original, so nothing mutable is shared.**

```java
Employee original = new Employee("Asha", new ArrayList<>(List.of("Java")));
Employee shallowCopy = /* shallow-copy original somehow */;

shallowCopy.getSkills().add("Spring");
// If the copy was shallow, original.getSkills() now shows "Spring" too —
// both objects' `skills` fields hold a reference to the SAME List (Chapter 3 §7's aliasing)
```

Chapter 28 §4's `new ArrayList<>(skills)` was already a **deep enough** copy for that specific case — because `String` elements are themselves immutable (Chapter 26 §2's `java.lang.String` is `final`, with no mutating methods), copying the list container was sufficient. A field holding genuinely mutable objects would need those objects copied too, not just the containing list.

---

## 3. `Object.clone()` and `Cloneable`

```java
public class Employee implements Cloneable {
    private String name;
    private List<String> skills;

    @Override
    public Employee clone() {
        try {
            return (Employee) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);   // can't actually happen, since Cloneable is implemented
        }
    }
}
```

### 3.1 `Cloneable` Is an Unusual Interface

`Cloneable` declares **no methods at all** — unlike every interface Chapter 18 covered, which always declares at least one method as its contract. `Cloneable`'s only actual effect is a runtime flag: calling `Object.clone()` on an object whose class doesn't implement `Cloneable` throws `CloneNotSupportedException` — a checked exception, forcing a `try`/`catch` even in code where, once `Cloneable` is correctly implemented, that exception can never actually occur. This is a well-known, awkward piece of API design, and a large part of why `clone()` has a poor reputation.

### 3.2 `clone()` Bypasses the Constructor Entirely

This is the single most important, most commonly missed fact about `Object.clone()`: it does **not** go through Chapter 3 §4's five-step object-creation pipeline at all. It performs a direct, field-by-field copy of the object's memory — fields are copied as raw values, with no constructor call, no field initializers (Chapter 6), and no validation logic (Chapter 12) running at all. This is genuinely different from every other object-creation mechanism this handbook has covered — `new` (Chapter 3), copy constructors (Chapter 5 §7), and static factory methods (Chapter 9 §8) all go through a real constructor; `clone()` deliberately does not.

### 3.3 `Object.clone()`'s Default Behavior Is Shallow

The default `clone()` inherited from `Object` performs exactly a **shallow** copy (§2) — every field is copied as-is, so any reference-type field ends up shared between the original and the clone:

```java
Employee original = new Employee("Asha", new ArrayList<>(List.of("Java")));
Employee copy = original.clone();

copy.getSkills().add("Spring");
// original.getSkills() now ALSO shows "Spring" — the default clone() shallow-copy leak
```

To achieve a genuine deep copy, `clone()` must be overridden to explicitly clone every mutable field itself:

```java
@Override
public Employee clone() {
    try {
        Employee copy = (Employee) super.clone();
        copy.skills = new ArrayList<>(this.skills);   // manually deep-copy the mutable field
        return copy;
    } catch (CloneNotSupportedException e) {
        throw new AssertionError(e);
    }
}
```

This has to be repeated, correctly, for every mutable field, at every level of nesting — easy to get wrong, and easy to silently forget when a class gains a new mutable field later.

---

## 4. Why `Cloneable`/`clone()` Is Considered Flawed

Putting §3's pieces together, several concrete, specific problems compound:

- `Cloneable` is a marker interface with no method contract at all (§3.1) — unusual and easy to misuse.
- `clone()` bypasses the constructor entirely (§3.2) — any validation logic a constructor performs (Chapter 12) is silently skipped.
- The default behavior is shallow (§3.3), and achieving a correct deep copy requires manually re-implementing it per mutable field, with no compiler help catching an omission.
- `CloneNotSupportedException` (§3.1) is a checked exception that a correctly-implemented class can never actually throw, forcing awkward, pointless exception handling.

---

## 5. The Modern Alternative: Copy Constructors

This handbook already has a cleaner tool for exactly this purpose — Chapter 5 §7's copy constructor:

```java
public Employee(Employee other) {
    this.name = other.name;
    this.skills = new ArrayList<>(other.skills);   // deep-copy exactly where it's needed, explicitly
}
```

```java
Employee copy = new Employee(original);   // goes through the normal constructor pipeline (Ch. 3 §4)
```

This achieves the same deep-copy safety as a correctly-written `clone()` override, but goes through the **real** constructor — meaning any validation logic (Chapter 12) still runs, there's no checked exception to awkwardly handle, and no unusual marker interface to implement. This is why modern Java guidance, and this handbook, recommend copy constructors or static factory copy methods (Chapter 9 §8) over `Cloneable`/`clone()` for new code.

---

## 6. Best Practices

- Prefer a copy constructor (Chapter 5 §7) or a static factory copy method (Chapter 9 §8) over `Cloneable`/`clone()` in new code — both go through the real constructor pipeline and avoid every problem in §4.
- If `clone()` must be used — typically only when extending an existing `Cloneable` class in a legacy codebase — deep-copy every mutable field explicitly; never rely on the default shallow behavior for a class with any mutable-typed field.
- Never assume `clone()` is automatically a deep copy — `Object.clone()`'s baseline is shallow (§3.3), always.

## 7. Common Mistakes

- ⚠️ Assuming `clone()` produces a deep copy by default — it's shallow unless explicitly overridden further (§3.3).
- ⚠️ Forgetting that `clone()` bypasses the constructor, and being surprised that validation logic in the constructor never ran on a cloned object (§3.2).
- ⚠️ Deep-cloning one mutable field but forgetting another, especially after a class gains a new mutable field later — nothing catches this at compile time.
- ⚠️ Reaching for `Cloneable`/`clone()` in new code instead of a copy constructor, inheriting all of §4's problems for no real benefit.

## 8. Interview Perspective

**Frequently Asked**

- *"What's the difference between shallow and deep copy?"* — Shallow copies reference fields as-is, sharing the referenced objects; deep copy recursively duplicates every mutable object reachable from the original, sharing nothing mutable (§2).
- *"Does `clone()` call the class's constructor?"* — No — it performs a direct field-by-field memory copy, entirely bypassing Chapter 3 §4's object-creation pipeline (§3.2).
- *"Why is `Cloneable` considered a flawed design?"* — It's a no-method marker interface, its default behavior is shallow, achieving a correct deep copy requires manual, error-prone per-field work, and it forces handling a checked exception that a correct implementation can never actually throw (§4).

**Tricky Question**

- *"If a class has validation logic in its constructor, does that validation run when the class is cloned via `Object.clone()`?"* — No — because `clone()` bypasses the constructor entirely (§3.2), any validation written there never executes on a cloned object, which is one of the concrete reasons a copy constructor (§5) is the safer choice.

**Common Misconception**

- Believing `clone()` is simply "a built-in copy constructor." It's mechanically very different — a copy constructor is an ordinary constructor, going through the full object-creation pipeline and any validation logic; `clone()` is a direct memory copy that skips all of that entirely (§3.2, §5).

---

## 9. Summary

- Shallow copy duplicates fields as-is, sharing referenced mutable objects; deep copy recursively duplicates everything mutable, sharing nothing.
- `Object.clone()`'s default behavior is shallow, and it bypasses the constructor entirely — no field initializers, no validation logic, none of Chapter 3 §4's pipeline runs.
- `Cloneable` is an unusual marker interface with no method contract, and `clone()`'s checked `CloneNotSupportedException` is awkward for a correctly-implemented class that can never actually throw it.
- Copy constructors (Chapter 5 §7) achieve the same deep-copy safety through the real constructor pipeline, without any of `clone()`'s baggage — the recommended modern approach.

## 10. Quick Revision

- Shallow copy = fields copied as-is, mutable references shared. Deep copy = everything mutable duplicated recursively.
- `Object.clone()` = shallow by default, bypasses the constructor entirely.
- `Cloneable` = no-method marker interface; `CloneNotSupportedException` = checked, awkward, usually unreachable once implemented correctly.
- Prefer copy constructors/static factory copy methods over `Cloneable`/`clone()` for new code.

## 11. Self Assessment

1. Explain precisely why a shallow copy of an object with a `List` field can let a mutation through the copy affect the original.
2. Why doesn't validation logic written in a constructor run when an object is duplicated via `Object.clone()`?
3. List the four concrete problems this chapter identifies with `Cloneable`/`clone()`, without looking back at §4.
4. Write a copy constructor for a class with one primitive field and one `List` field that achieves a correct deep copy.
5. Why is `CloneNotSupportedException` considered an awkward piece of API design, given that a class correctly implementing `Cloneable` can never actually trigger it?

---

## What's Next

**Chapter 30 — Reflection** covers `getClass()` (Chapter 19 §6) in full depth — inspecting and even invoking a class's members at runtime, dynamically, without knowing its exact type at compile time — the mechanism many frameworks (including Spring Boot) rely on internally, and the last major piece of Java's `java.lang`/`java.lang.reflect` toolkit this handbook covers.
