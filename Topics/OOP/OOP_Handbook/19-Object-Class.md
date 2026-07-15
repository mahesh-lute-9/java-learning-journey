# Chapter 19 — The Object Class

**Part IX: Java Root Object**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain what `java.lang.Object` actually provides, and why every class has it whether or not `extends` is written.
- Override `equals()` and `hashCode()` correctly, together, and explain precisely why they must always change as a pair.
- State the equals/hashCode contract precisely, and explain what breaks, concretely, when it's violated.
- Override `toString()` usefully, and explain what the default implementation actually prints and why.

---

## 1. Introduction

Chapter 15 §7 named `java.lang.Object` as the implicit parent of every class that doesn't explicitly extend another, and Chapter 3 §8 flagged its `equals()`/`hashCode()` override mechanics forward to this exact chapter. This is where both promises are delivered — the last piece of groundwork before Part X can discuss how objects relate to each other.

> This chapter assumes Chapter 15's inheritance mechanics and Chapter 16's dynamic dispatch completely. It also assumes Chapter 3 §8's default `==`-equivalent `.equals()` behavior — this chapter's job is showing how, and why, to override it.

---

## 2. Theory — What `Object` Actually Provides

Every class, explicitly or implicitly (Chapter 15 §7), inherits a small set of methods from `Object` — the genuine "floor" every Java object stands on. This chapter covers the three that matter most for everyday class design: `toString()`, `equals(Object)`, and `hashCode()`, plus `getClass()`.

---

## 3. `toString()`

### 3.1 The Default Behavior

```java
Employee emp = new Manager("M001", 95000);
System.out.println(emp);
// com.acme.hr.Manager@1b6d3586
```

`Object`'s default `toString()` returns the class's fully-qualified name (Chapter 14 §2.3), an `@`, and the object's hash code in hexadecimal — genuinely useless for debugging, and the exact output every learner eventually sees and wonders about.

### 3.2 Overriding It

```java
@Override
public String toString() {
    return "Employee{id=" + employeeId + ", name=" + name + "}";
}
```

`System.out.println(obj)` and string concatenation (`"Employee: " + emp`) both call `toString()` implicitly — overriding it is one of the highest-value, lowest-effort things a class can do for its own debuggability, and this handbook treats it as close to mandatory for any class meant to be logged or printed.

---

## 4. `equals(Object)` — Delivering Chapter 3's Deferred Promise

Chapter 3 §8 already established the default: `Object`'s `equals()` behaves identically to `==` — pure reference identity. Overriding it lets a class define its own notion of "equal," typically based on meaningful field values rather than memory address.

### 4.1 A Correct Override

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;                    // fast path — Chapter 3 §7's aliasing case
    if (obj == null || getClass() != obj.getClass()) return false;
    Employee other = (Employee) obj;
    return employeeId.equals(other.employeeId);
}
```

Each line matters: the identity check is a cheap early exit; `getClass() != obj.getClass()` (§7 has more on this) ensures we're not comparing genuinely different types; the cast is safe only because that check already passed; and the actual comparison is delegated to whatever field(s) genuinely define this class's notion of equality — here, `employeeId`, the blank-final identity field Chapter 10 §3.2 established.

### 4.2 The `equals()` Contract

A correct `equals()` override must satisfy four properties, and violating any of them causes real, often silent, bugs in code that trusts the contract:

- **Reflexive** — `x.equals(x)` must always be `true`.
- **Symmetric** — `x.equals(y)` must equal `y.equals(x)`.
- **Transitive** — if `x.equals(y)` and `y.equals(z)`, then `x.equals(z)` must also hold.
- **Consistent** — repeated calls, with no state changes in between, must return the same result.
- `x.equals(null)` must always return `false`, never throw.

---

## 5. `hashCode()` — Why It Must Always Change With `equals()`

> **The equals/hashCode contract: if `x.equals(y)` is `true`, then `x.hashCode()` must equal `y.hashCode()`. The reverse is not required — unequal objects may share a hash code — but a good `hashCode()` makes that rare.**

```java
@Override
public int hashCode() {
    return employeeId.hashCode();   // same field(s) equals() uses — this is not a coincidence
}
```

### 5.1 What Actually Breaks If You Skip This

This is worth walking through concretely, because "you must override both together" sounds like an arbitrary rule until you see the actual failure:

```java
Employee e1 = new Employee("E001", ...);
Employee e2 = new Employee("E001", ...);   // e1.equals(e2) is true, by our override

Set<Employee> ids = new HashSet<>();
ids.add(e1);
System.out.println(ids.contains(e2));   // if hashCode() wasn't overridden: FALSE, unexpectedly
```

`HashSet` (and `HashMap`) use `hashCode()` first, to decide which internal bucket an object belongs in, and only then use `equals()` to check for a match *within* that bucket. If `e1` and `e2` are `equals()` but produce *different* default (identity-based) hash codes, they land in different buckets entirely — `equals()` is never even consulted, because the set never looks in the right bucket. The result: a `HashSet` can silently contain what looks like a duplicate, and `.contains()` can silently return `false` for an object that, by your own `equals()` definition, is already present. This is one of the most common real Java bugs, and it's entirely explained by this one asymmetry in how hash-based collections work.

### 5.2 A Modern Shortcut

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Employee other = (Employee) obj;
    return Objects.equals(employeeId, other.employeeId);
}

@Override
public int hashCode() {
    return Objects.hash(employeeId);
}
```

`Objects.equals(a, b)` (from `java.util.Objects`) handles `null` safely on either side without extra checks, and `Objects.hash(...)` combines any number of fields into one well-distributed hash code — this is the pattern most modern Java code, and most IDE-generated overrides, actually produce.

---

## 6. `getClass()`

Returns the object's actual runtime `Class` object — the same concept behind `instanceof` (Chapter 16 §6) and the `getClass() != obj.getClass()` check in §4.1. `getClass()` is what makes reflection possible; Chapter 30 covers `Class` objects in their own right. For this chapter's purposes, the only fact needed is the subtle distinction in §7.

---

## 7. `getClass()` vs. `instanceof` in an `equals()` Check

This is a genuinely precise distinction worth stating explicitly: `obj.getClass() != this.getClass()` requires the **exact same class**, while `obj instanceof Employee` would also accept any **subclass** of `Employee`. Using `instanceof` in `equals()` risks breaking the symmetry rule (§4.2) across an inheritance boundary — an `Employee` might consider a `Manager` equal to it (if it only checks fields `Employee` has), while the `Manager`'s own, more specific `equals()` might not consider that same `Employee` equal back. Using `getClass()` (§4.1's version) avoids this asymmetry entirely, at the cost of never considering a subclass instance equal to a superclass one, even if it "shares" the same relevant field values — a deliberate, common, and generally safer tradeoff.

---

## 8. JVM Internals

Because every class implicitly extends `Object` (Chapter 15 §7), `Object`'s methods occupy the very first slots in the virtual method table (Chapter 16 §4.2) every class ultimately builds on — `equals`, `hashCode`, and `toString` each have a vtable slot present from the very root of every class hierarchy, which subclasses then override exactly the way Chapter 15 §4 already described for any other method. `Object`'s own default `hashCode()` implementation is not specified precisely by the Java Language Specification — in practice, HotSpot commonly derives it from information stored in the object's header (Chapter 3 §6's Mark Word), though this is a JVM implementation detail, not a guarantee — which is precisely why relying on the *default* hash code for anything beyond debugging output is unsafe, and why a class with real equality semantics must supply its own.

---

## 9. Real-World Example

```java
public abstract class Employee implements Payable {
    private final String employeeId;
    private String name;
    private double salary;

    protected Employee(String employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        setSalary(salary);
    }

    // ... setSalary, abstract getSalary, calculatePay from earlier chapters ...

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        return Objects.equals(employeeId, other.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    @Override
    public String toString() {
        return "Employee{id=" + employeeId + ", name=" + name + "}";
    }
}
```

`employeeId` — the blank `final` field Chapter 10 §3.2 introduced specifically as a stable identity — is exactly the right field to base both `equals()` and `hashCode()` on: it never changes after construction (§9.1 below explains why that matters), unlike `salary` or `name`.

### 9.1 Why the Identity Field Should Be Immutable

If `hashCode()` were based on a mutable field like `salary`, and an `Employee` object were placed into a `HashSet` and then had its salary changed afterward, the object's hash code would change too — but the `HashSet` would still be looking for it in the *original* bucket, based on the *old* hash code. The object becomes effectively unfindable in its own collection, without ever being removed. This is exactly why Chapter 10's blank-final `employeeId` — assigned once, never reassigned — is a safer foundation for `equals()`/`hashCode()` than any field that can change over an object's lifetime.

---

## 10. Best Practices

- Override `toString()` on any class you expect to log, print, or debug — the default output (§3.1) is essentially useless.
- Always override `equals()` and `hashCode()` together — never one without the other (§5.1's concrete failure case is why).
- Base `equals()`/`hashCode()` on immutable, identity-defining fields where possible (§9.1) — never on fields that change after construction.
- Prefer `Objects.equals()`/`Objects.hash()` (§5.2) over hand-rolled null-checking and hash-combining logic.

## 11. Common Mistakes

- ⚠️ Overriding `equals()` without overriding `hashCode()` — this is the single most common Java correctness bug involving hash-based collections (§5.1).
- ⚠️ Basing `hashCode()` on a mutable field, then mutating an object after it's already been placed in a `HashSet`/`HashMap` (§9.1) — the object effectively disappears from its own collection.
- ⚠️ Using `instanceof` instead of `getClass()` in `equals()` without considering the symmetry risk across subclasses (§7).
- ⚠️ Forgetting `equals(null)` must return `false`, not throw a `NullPointerException` — a direct violation of the contract (§4.2).

## 12. Interview Perspective

**Frequently Asked**

- *"What does `Object`'s default `equals()` do?"* — Reference identity, equivalent to `==` (Chapter 3 §8) — confirmed and now fully overridden in this chapter.
- *"Why must you override `hashCode()` whenever you override `equals()`?"* — Because hash-based collections use `hashCode()` to locate a bucket before ever calling `equals()` — mismatched implementations mean equal objects can land in different buckets and never be recognized as duplicates (§5.1).
- *"What's the equals/hashCode contract, precisely?"* — Equal objects (by `equals()`) must produce equal hash codes; unequal objects may share one, but shouldn't routinely (§5).

**Tricky Question**

- *"You override `equals()` and `hashCode()` based on a mutable field, then mutate that field on an object already stored in a `HashSet`. What happens when you call `.contains()` on it afterward?"* — It very likely returns `false`, even though the object is still physically in the set — because the set is now searching the wrong bucket, based on the object's changed hash code (§9.1).

**Common Misconception**

- Believing `getClass() != obj.getClass()` and `!(obj instanceof Employee)` are interchangeable in an `equals()` check. They differ specifically at subclass boundaries (§7), and the choice between them is a deliberate design decision about whether a subclass instance can ever be considered equal to a superclass one — not an arbitrary stylistic choice.

---

## 13. Summary

- Every class implicitly inherits from `Object`, which provides default `toString()`, `equals()`, `hashCode()`, and `getClass()` — the default `equals()`/`hashCode()` pair are both identity-based, confirmed from Chapter 3 §8.
- `toString()` should almost always be overridden for meaningful debug output; the default prints little more than a class name and a hash code.
- `equals()` and `hashCode()` must always be overridden together — violating the equals/hashCode contract causes hash-based collections to silently misbehave, not throw an obvious error.
- Basing identity on an immutable field (like a blank-final ID) avoids a specific, dangerous failure mode where a mutated object becomes unfindable in a `HashSet`/`HashMap` it's already stored in.
- `getClass()` vs. `instanceof` in `equals()` is a deliberate choice about whether subclass instances can be considered equal to superclass ones.

## 14. Quick Revision

- Default `toString()`: class name + hash code, in hex — override it.
- Default `equals()`: identity (`==`) — override it for value equality.
- Equal objects (by `equals()`) MUST share a `hashCode()` — always override both together.
- Base identity on immutable fields — mutable-field-based hash codes break `HashSet`/`HashMap` lookups after mutation.
- `getClass()` = exact type match; `instanceof` = allows subclasses — pick deliberately in `equals()`.

## 15. Self Assessment

1. What does `System.out.println(new Employee(...))` print if `toString()` is never overridden, and why?
2. Write a correct `equals()` override for a `Product` class that should be equal based on `productId`, following every step in §4.1's pattern.
3. Explain, concretely, what goes wrong if you override `equals()` for a class but leave `hashCode()` at its default — walk through a `HashSet` scenario like §5.1's.
4. Why is a blank `final` field a safer basis for `hashCode()` than a mutable one? Describe the specific failure a mutable-field-based hash code causes.
5. What's the practical difference between using `getClass() != obj.getClass()` and `!(obj instanceof Employee)` inside an `equals()` override?

---

## What's Next

Part IX (Java Root Object) is complete. **Chapter 20 — Association** opens Part X (Object Relationships), returning to the has-a relationships Chapter 15 §2.1 explicitly set aside in favor of is-a inheritance — the first of three chapters (Association, Aggregation, Composition) covering how objects reference and depend on one another, rather than how they inherit from one another.
