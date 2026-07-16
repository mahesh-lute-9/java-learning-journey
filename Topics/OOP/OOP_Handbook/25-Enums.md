# Chapter 25 — Enums

**Part XI: Advanced OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain why `enum` replaces the int-constant/String-constant pattern, and what type safety it actually buys.
- Explain precisely what an enum constant *is* — not a label, a genuine singleton object — and how the compiler generates it.
- Give enum constants their own fields, constructors, and even per-constant method bodies.
- Explain why `==` is the correct, idiomatic way to compare enum constants, unlike ordinary objects (Chapter 19).
- Recognize why relying on `ordinal()` for anything persisted is fragile.

---

## 1. Introduction

Before `enum` existed, a fixed set of related constants was typically represented as a handful of `public static final int` values — `MANAGER = 0`, `INTERN = 1`. This "worked," but bought none of Java's type system: any `int` could be passed where one of these was expected, with no compiler check that it was even one of the valid values at all. Java's `enum` construct is a proper class-based fix for this, and — genuinely more than most learners realize — a real class, not just labeled integers.

---

## 2. Theory — What an Enum Constant Actually Is

> **An enum is a special class whose instances are restricted to a fixed, named set of constants declared up front — and each constant is a genuine singleton instance of that class, not merely a label.**

```java
public enum EmployeeType {
    MANAGER, INTERN, FULL_TIME;
}
```

`MANAGER` here isn't a number or a string — it's an actual object, the one and only instance of `EmployeeType` representing that constant, created once and reused everywhere it's referenced.

---

## 3. Compiler Behaviour — What `enum` Actually Generates

Every `enum` implicitly extends `java.lang.Enum` — which means, per Chapter 15 §5's single-inheritance rule, **an enum can never `extends` any other class** (it's already using its one allowed parent), though it **can** `implements` interfaces (Chapter 18 §3), exactly like any other class.

For each constant, the compiler generates a `public static final EmployeeType` field, initialized exactly once — inside a compiler-generated static initializer block, the very mechanism Chapter 6 §3.3 covered — the moment the enum class is first loaded (Chapter 2 §6.2):

```
Source you write:            What effectively exists after compilation:

enum EmployeeType {           class EmployeeType extends Enum<EmployeeType> {
    MANAGER, INTERN;              public static final EmployeeType MANAGER = new EmployeeType("MANAGER", 0);
}                                  public static final EmployeeType INTERN = new EmployeeType("INTERN", 1);
                                   // ... plus values(), valueOf(), toString(), etc.
                               }
```

The compiler also generates several methods automatically, for free, on every enum: `toString()` (returns the constant's declared name, e.g. `"MANAGER"`), `name()` (the same, explicitly), `ordinal()` (the constant's position in declaration order, zero-indexed), a `static values()` method returning all constants as an array in declaration order, and a `static valueOf(String)` for looking one up by its exact name.

---

## 4. Enum Constructors Are Always Private

```java
public enum EmployeeType {
    MANAGER(50000), INTERN(20000), FULL_TIME(35000);

    private final double baseSalary;

    EmployeeType(double baseSalary) {   // implicitly private — cannot be public or protected, ever
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
}
```

An enum's constructor is always `private` (or package-private, indistinguishably, since it can never be called from anywhere except the constant declarations themselves) — the compiler enforces this without exception. This is an extreme, automatic version of the private-constructor pattern Chapter 5 §8 introduced for controlling instantiation: not only is `new EmployeeType(...)` illegal from outside the enum (as with any private constructor), it's illegal *everywhere*, including inside the enum's own methods — the only place an enum constructor is ever actually invoked is in the constant list itself, once per constant, at class-loading time (§3).

---

## 5. Enum Constants With Per-Constant Method Bodies

An enum implementing an interface can give **each constant its own distinct implementation** of an abstract method:

```java
public enum EmployeeType implements Payable {
    MANAGER(50000) {
        @Override
        public double calculatePay() { return getBaseSalary() + 5000; }
    },
    INTERN(20000) {
        @Override
        public double calculatePay() { return getBaseSalary() * 0.5; }
    };

    private final double baseSalary;
    EmployeeType(double baseSalary) { this.baseSalary = baseSalary; }
    public double getBaseSalary() { return baseSalary; }
}
```

Each constant with its own `{ ... }` body is compiled as its own anonymous subclass of `EmployeeType` — genuinely the same mechanism Chapter 24 covered for ordinary anonymous classes, including the sequential compiled naming (`EmployeeType$1`, `EmployeeType$2`, ...) from Chapter 24 §4. `MANAGER.calculatePay()` and `INTERN.calculatePay()` each run their own distinct implementation, resolved through the same dynamic dispatch (Chapter 16 §4.2) that governs any other overridden method call.

---

## 6. Why `==` Is Correct — and Preferred — for Enum Comparison

Chapter 19 §4 emphasized overriding `.equals()` for value-based equality on ordinary classes, since `==` alone only checks identity. For enums, this concern doesn't apply the same way: **because each constant is a genuine singleton (§2, §3), `==` and `.equals()` behave identically for enums, and `==` is the idiomatic, preferred choice.**

```java
EmployeeType type = EmployeeType.MANAGER;
if (type == EmployeeType.MANAGER) { ... }   // ✔ idiomatic, and completely safe
```

There is no risk of two different `MANAGER` objects existing to confuse `==`, the way two separately-constructed `Employee` objects could confuse it (Chapter 3 §8) — every reference to `EmployeeType.MANAGER` anywhere in the program refers to the exact same object, guaranteed by the compiler-generated singleton initialization (§3).

---

## 7. The `ordinal()` Trap

`ordinal()` returns a constant's position in declaration order — but relying on it for anything **persisted** (saved to a database, serialized to a file) is fragile:

```java
public enum EmployeeType { MANAGER, INTERN, FULL_TIME }
// MANAGER.ordinal() == 0, INTERN.ordinal() == 1, FULL_TIME.ordinal() == 2
```

If a new constant is later inserted between existing ones, or the declaration order is simply rearranged, every previously-stored `ordinal()` value now points at a *different* constant than originally intended — silently, with no compile error to catch it. Persisted data should reference a constant by its `name()` (or an explicitly assigned field, like a stable code), never by `ordinal()`.

---

## 8. Enums vs. Subclasses — When to Use Which

This handbook already modeled `Manager` and `Intern` as full subclasses of `Employee` (Chapter 15), each with genuinely different state and overridden behavior. `EmployeeType` in this chapter models something narrower: a small, closed, compile-time-known set of variants that mainly differ in a few associated values and a small calculation. The practical guidance: reach for **subclasses** (Chapter 15) when a variant genuinely needs its own distinct fields, its own overridden behavior across many methods, or further specialization later; reach for an **enum** when the set of variants is fixed, small, known completely up front, and the differences between them are narrow enough to express as constant-specific data or a single overridden method (§5).

---

## 9. Real-World Example

```java
public enum EmployeeType implements Payable {
    MANAGER(50000) {
        @Override
        public double calculatePay() { return getBaseSalary() + 5000; }
    },
    INTERN(20000) {
        @Override
        public double calculatePay() { return getBaseSalary() * 0.5; }
    },
    FULL_TIME(35000) {
        @Override
        public double calculatePay() { return getBaseSalary(); }
    };

    private final double baseSalary;

    EmployeeType(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
}
```

```java
for (EmployeeType type : EmployeeType.values()) {
    System.out.println(type.name() + ": " + type.calculatePay());
}
```

---

## 10. Best Practices

- Use `enum` for any fixed, closed set of related constants — never fall back to bare `int` or `String` constants for this purpose.
- Add fields and methods to enrich enum constants with real data and behavior, rather than scattering `switch`/`if` chains over a plain constant elsewhere in the codebase.
- Compare enum constants with `==` (§6) — it's both safe and idiomatic.
- Never persist an `ordinal()` value — persist `name()`, or an explicit, stable field, instead (§7).

## 11. Common Mistakes

- ⚠️ Trying to call `new EmployeeType(...)` anywhere, expecting it to work like an ordinary class — enum constructors are always private, with no exception (§4).
- ⚠️ Storing an enum's `ordinal()` in a database or file, then having a later reordering or insertion silently corrupt the stored meaning (§7).
- ⚠️ Overriding `.equals()`/`hashCode()` on an enum, assuming it needs the same treatment as an ordinary class (Chapter 19) — it doesn't; the compiler-guaranteed singleton nature (§3) makes the default identity-based behavior already correct.
- ⚠️ Reaching for a full subclass hierarchy (Chapter 15) for a small, fixed, compile-time-known set of variants that would be more simply and safely expressed as an enum (§8).

## 12. Interview Perspective

**Frequently Asked**

- *"What is an enum, precisely?"* — A special class whose constants are compiler-generated singleton instances of it, not merely labeled integers (§2, §3).
- *"Can an enum extend a class?"* — No — it already implicitly extends `java.lang.Enum`, using up its one allowed parent (Chapter 15 §5) — but it can implement interfaces (§3).
- *"Is `==` safe for comparing enum constants?"* — Yes, and it's the idiomatic choice — every reference to a given constant is guaranteed to be the exact same singleton object (§6).

**Tricky Question**

- *"Why is persisting an enum's `ordinal()` considered risky?"* — Because `ordinal()` reflects declaration order, and inserting or reordering constants later silently shifts every subsequent ordinal value, corrupting previously-persisted meaning without any compile-time warning (§7).

**Common Misconception**

- Believing enum constants are just named integers under the hood, the way they are in some other languages. In Java, each constant is a fully-fledged singleton object of the enum's class — capable of carrying its own fields, its own constructor-assigned data, and even its own per-constant method implementation (§5) — genuinely richer than a plain named integer in every respect.

---

## 13. Summary

- `enum` replaces the int-constant/String-constant pattern with a proper, type-safe class whose constants are each a genuine singleton instance.
- An enum implicitly extends `java.lang.Enum` (using its one allowed parent, Chapter 15 §5) but can implement interfaces (Chapter 18).
- Enum constructors are always private, enforced by the compiler with no exception, since the constant set is meant to be exhaustive and fixed.
- Constants can carry their own fields and even their own per-constant method bodies, compiled as anonymous subclasses (Chapter 24).
- `==` is the correct, idiomatic way to compare enum constants; `ordinal()` should never be relied on for anything persisted.

## 14. Quick Revision

- Enum constant = genuine singleton object, not a label.
- Implicitly extends `Enum` (no other superclass allowed); can implement interfaces.
- Constructor always private — `new` is never legal, anywhere.
- Compiler auto-generates `toString()`, `name()`, `ordinal()`, `values()`, `valueOf()`.
- `==` is safe and preferred for enums; never persist `ordinal()`.

## 15. Self Assessment

1. Explain precisely why `EmployeeType.MANAGER == EmployeeType.MANAGER` is always `true`, tying your answer to how the compiler actually creates enum constants.
2. Why can an enum never `extends` another class, and what can it do instead to gain additional behavior?
3. Why is an enum constructor always private, and what happens if you try to call `new EmployeeType(...)` from inside the enum's own methods?
4. Give a concrete scenario where relying on `ordinal()` for persisted data causes a silent bug after the enum is modified.
5. Using this chapter's guidance, decide whether a `PaymentMethod` (Cash, Card, UPI, Wallet) should be modeled as an enum or as a set of subclasses, and justify your choice.

---

## What's Next

**Chapter 26 — Records** covers a newer, complementary Java construct: a compact way to declare an immutable, data-carrying class — automatically generating constructors, accessors, `equals()`, `hashCode()`, and `toString()` (Chapter 19) in one line, for the common case of a class that's essentially just a fixed set of fields.
