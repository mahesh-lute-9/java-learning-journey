# Chapter 40 — Method Dispatch

**Part XIII: JVM Internals**

*The final chapter of the Java OOP Handbook.*

---

## Learning Objectives

After completing this chapter, you will be able to:

- Name all four Java method-invocation bytecode instructions, and explain precisely when each is used and why.
- Explain what constructors, private methods, and `super.method()` calls genuinely have in common — the reason they share one instruction.
- Explain why interface calls need a fundamentally different lookup mechanism than ordinary instance calls, despite both being dynamically dispatched.
- Assemble, in one place, a synthesis this handbook has built one piece at a time across six separate chapters.

---

## 1. Introduction

Every method call this handbook has written since Chapter 3 has actually compiled to one of exactly four bytecode instructions — introduced separately, one at a time, in Chapter 3 §5, Chapter 7 §6, Chapter 9 §5, Chapter 11 §4, Chapter 16 §4.2, and Chapter 18 §5. This final chapter puts all four side by side, as the single, unified account this handbook has been building toward the entire time.

---

## 2. The Four Instructions

### 2.1 `invokestatic` — Static Methods (Chapter 9 §5)

```java
Employee.createIntern("Rohan");
```

No receiver object is involved at all — a static call resolves **entirely at compile time**, based purely on the class name written at the call site. This is precisely why a `static` method can never be genuinely overridden, only hidden (Chapter 9 §5): there's no vtable lookup here to override in the first place.

### 2.2 `invokespecial` — Constructors, Private Methods, and `super.method()` (Chapters 3, 5, 11)

```java
new Manager(...)          // constructor — Chapter 3 §5
private helper();         // a private method, called from within its own class — Chapter 5's private-method logic
super.getSalary()          // Chapter 11 §4
```

These three, seemingly unrelated cases — constructor calls, `private` method calls, and `super.method()` calls — share one instruction for a single, precise reason: **in every one of them, the exact method to run is already known statically, at compile time, with no possibility of dynamic dispatch being relevant at all.** A constructor is never inherited (Chapter 5 §3.2) — there's nothing to dispatch dynamically. A `private` method is invisible outside its own class (Chapter 13) — no subclass can override what it can't even see, so there's no dispatch decision to make. A `super.method()` call deliberately *bypasses* dynamic dispatch to reach one specific, named ancestor implementation (Chapter 11 §4) — using `invokevirtual` here would defeat the entire purpose of writing `super.` in the first place.

### 2.3 `invokevirtual` — Ordinary Instance Methods (Chapter 7 §6, Chapter 16 §4.2)

```java
emp.getSalary();
```

Resolved **dynamically**, at runtime, via the actual object's class's virtual method table (Chapter 16 §4.2) — this is genuine polymorphism: the same call site can run different code depending on what `emp` actually refers to at that moment.

### 2.4 `invokeinterface` — Calls Through an Interface Reference (Chapter 18 §5)

```java
Payable p = emp;
p.calculatePay();
```

Also dynamically dispatched — but through a fundamentally different lookup than `invokevirtual`'s. Because a class can implement *many* interfaces (Chapter 18 §3), unlike having only one single-inheritance ancestor chain (Chapter 15 §5), interface method offsets aren't uniform across every implementing class the simple, fixed-slot way `invokevirtual`'s vtable is. `invokeinterface` searches for the correct implementation by the interface method's identity instead of a fixed slot number — more general, at a modest additional runtime cost.

---

## 3. The Complete Comparison

| Instruction | Used For | Resolved | Why |
|---|---|---|---|
| `invokestatic` | Static methods | Compile time | No receiver object exists at all |
| `invokespecial` | Constructors, `private` methods, `super.method()` | Compile time | The exact target is already known statically — no dispatch decision to make |
| `invokevirtual` | Ordinary instance methods | Runtime | Genuine polymorphism — dispatched via the object's actual class's vtable |
| `invokeinterface` | Calls through an interface-typed reference | Runtime | Dynamic, but via identity lookup, not a fixed slot — needed because a class can implement many interfaces |

**The single organizing idea:** the first two rows are resolved at compile time because, in every one of those cases, dynamic dispatch is either impossible (no object, Chapter 9) or deliberately unwanted (Chapters 3, 5, 11). The last two rows are both genuinely dynamic, differing only in *how* the runtime lookup is structured — a fixed slot for single-inheritance classes, an identity-based search for interfaces implementable many at a time.

---

## 4. Real-World Example — All Four, in One Place

```java
Employee emp = new Manager("M001", "Asha", 95000, "", "");
//              ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//              invokespecial — Manager's constructor, which itself calls Employee's
//              constructor via super(...), also invokespecial (Chapters 3, 5, 11)

emp.getSalary();
// invokevirtual — dispatched to Manager's override, via Manager's own vtable slot (Chapter 16)

Employee.createIntern("Rohan");
// invokestatic — resolved entirely at compile time (Chapter 9)

Payable p = emp;
p.calculatePay();
// invokeinterface — dispatched via identity lookup, not a fixed vtable slot (Chapter 18)
```

```java
class Manager extends Employee {
    @Override
    public double getSalary() {
        return super.getSalary() + 5000;
        //     ^^^^^^^^^^^^^^^^^
        //     invokespecial — deliberately bypasses the vtable to reach Employee's
        //     exact implementation directly (Chapter 11 §4)
    }
}
```

Every dispatch mechanism this handbook has ever used is present in these two snippets — nothing new, only the complete picture finally assembled in one place.

---

## 5. Bonus: De-virtualization

A genuinely advanced, closing fact worth knowing: modern JIT compilers can sometimes **de-virtualize** an `invokevirtual` call — replacing the general vtable lookup with a direct call, as fast as `invokespecial`'s — when they can prove, at runtime, that only one implementation is actually possible for a given call site (for example, if a method is `final`, Chapter 10 §4, or if the JIT has observed that only one subclass has ever been loaded for that type in practice). This is an optimization, not a change to the rules in §2–§3 — it's simply the JVM recognizing, dynamically, when a "dynamic" dispatch has only one real answer, and skipping the general-purpose lookup accordingly.

---

## 6. Common Mistakes

- ⚠️ Assuming all method calls use the same underlying dispatch mechanism — there are four distinct instructions, each serving a specific, precise purpose (§2).
- ⚠️ Assuming interface calls dispatch identically to ordinary instance calls — `invokeinterface` and `invokevirtual` are both dynamic, but structurally different lookups (§2.4).
- ⚠️ Forgetting that constructors, `private` methods, and `super.method()` calls share `invokespecial` for the same underlying reason — a known, static target — rather than three coincidentally similar rules.

## 7. Interview Perspective

**Frequently Asked**

- *"Name all four Java method-invocation bytecode instructions."* — `invokestatic`, `invokespecial`, `invokevirtual`, `invokeinterface` (§2).
- *"What do constructors, private methods, and `super.method()` calls have in common that puts them all under `invokespecial`?"* — In each case, the target method is already known statically at compile time, with no dynamic dispatch decision to make (§2.2).
- *"Why can't interface calls use the same simple vtable-slot lookup as ordinary instance calls?"* — Because a class can implement many interfaces at once (Chapter 18 §3), so interface method positions aren't uniform across implementers the way single-inheritance vtable slots are (§2.4).

**Tricky Question**

- *"If a method is `final`, does the JVM still perform a full dynamic dispatch lookup every time it's called?"* — Not necessarily — a JIT compiler can de-virtualize the call once it proves only one implementation is possible, running it as directly as `invokespecial` would, even though the bytecode itself still says `invokevirtual` (§5).

**Common Misconception**

- Believing "polymorphism" and "method dispatch" are the same idea applied to every method call uniformly. Only `invokevirtual` and `invokeinterface` are genuinely, dynamically polymorphic — `invokestatic` and `invokespecial` deliberately opt out of dynamic dispatch entirely, for reasons this handbook built individually across six chapters and only now states together.

---

## 8. Chapter Summary

- Every Java method call compiles to exactly one of four bytecode instructions: `invokestatic`, `invokespecial`, `invokevirtual`, `invokeinterface`.
- `invokestatic` and `invokespecial` are resolved at compile time — the former because there's no receiver object, the latter because the target (a constructor, a private method, or an explicit `super` call) is already statically known.
- `invokevirtual` and `invokeinterface` are both resolved dynamically at runtime, differing only in lookup structure — a fixed vtable slot for single-inheritance classes, an identity-based search for interfaces a class can implement many of at once.
- JIT de-virtualization can optimize an `invokevirtual` call down to `invokespecial`-like speed when only one implementation is provably possible.

---

## 9. Closing — The Complete Handbook

This chapter closes a thread every one of the previous thirty-nine opened, one at a time: Chapter 1 asked why Object-Oriented Programming exists at all, and forty chapters later, this final one shows precisely how the JVM actually executes the mechanism — method dispatch — that makes every one of the Four Pillars real at the bytecode level. Encapsulation (Chapter 12) protects what `invokespecial`'s private-method case can even see. Inheritance (Chapter 15) and Polymorphism (Chapter 16) are what `invokevirtual`'s vtable exists to serve. Abstraction (Chapter 17, Chapter 18) is what `invokeinterface` had to be invented to support once a class could implement many contracts at once.

The `Employee` class this handbook began building in Chapter 2 — a bare, three-field class with no constructor of its own — is, forty chapters later, an `abstract`, `Payable`-implementing, properly encapsulated, package-organized, sealed-hierarchy base class with two full subclasses, a composed `ContactInfo` record, correctly-paired `equals()`/`hashCode()`/`toString()`, and a natural ordering by salary. Nothing about it was ever rewritten from scratch — every chapter added exactly one capability, and only ever referenced what came before.

That was deliberate, and it's the whole handbook's method, not just a stylistic choice: real Java code is built the same way — incrementally, on a foundation that holds, each new capability layered onto what already works rather than replacing it.

---

## What's Next

The handbook is complete — all 40 chapters, Foundations through JVM Internals. From here: build something real with these concepts (a small Spring Boot service is the natural next step, given everything Chapters 30, 31, and 35 already built toward it), revisit the Self Assessment questions across chapters as spaced-repetition interview prep, and keep the `HANDBOOK_TRACKER.md` as a record of what this repository actually contains — a complete, from-scratch, interview-ready account of Java Object-Oriented Programming, built one deliberate chapter at a time.
