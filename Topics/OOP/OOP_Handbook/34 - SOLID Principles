# Chapter 34 — SOLID Principles

**Part XII: Object-Oriented Design**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Name and apply all five SOLID principles, recognizing each in code this handbook has already written.
- Explain Liskov Substitution precisely — as a behavioral contract, not just "does it compile."
- Explain the deliberate tension between the Open/Closed Principle and Chapter 27's sealed classes.
- Distinguish the Dependency Inversion *principle* from the Dependency Injection *pattern* that implements it — setting up Chapter 35 directly.

---

## 1. Introduction

Four separate chapters have gestured at SOLID without naming it in full: Chapter 1 §1.8, Chapter 11 §10, and Chapter 17 §6 all pointed forward to this chapter, and Chapter 22 §6 already delivered one piece — "favor composition over inheritance" — in complete depth. This chapter assembles the whole framework: five principles for evaluating object-oriented design decisions, each one addressing a failure mode this handbook has already demonstrated concretely, somewhere.

---

## 2. S — Single Responsibility Principle

> **A class should have only one reason to change.**

**Violation:**
```java
class Employee {
    double calculateSalary() { ... }
    void saveToDatabase() { ... }        // a second, unrelated responsibility
    void sendWelcomeEmail() { ... }       // a third
}
```
Three unrelated reasons to change this one class — a salary formula change, a database schema change, and an email template change all touch the same file.

**This handbook already performed this exact refactor**, before naming it: Chapter 22 §3 extracted `ContactInfo` out of `Employee` specifically because contact data and employee identity were two separate responsibilities living in one class. Chapter 12 §5 made the same point from a different angle — a class full of mechanical getters/setters "looks" encapsulated without actually being well-designed, and SRP is part of *why*: bundling unrelated state and behavior into one class, even with proper access modifiers, is still a design smell.

---

## 3. O — Open/Closed Principle

> **Software entities should be open for extension, but closed for modification.**

Chapter 16 §7's payroll loop is this handbook's clearest demonstration:

```java
for (Employee emp : payroll) {
    System.out.println(emp.getSalary());   // works for any current OR future Employee subtype
}
```

Adding a new `Employee` subtype (a `Contractor`, say) requires writing one new class — this loop, and every other piece of code written against the `Employee` abstraction, needs **zero modification**. This is precisely the extensibility Chapter 1 §1.6 promised back at the very start of this handbook, describing how a new payment method could be "added without changing existing business logic."

**A precise tension worth naming directly:** Chapter 27's sealed classes deliberately restrict extension — the opposite of OCP's usual guidance. This isn't a contradiction; it's a deliberate, principled *exception*. OCP's value is enabling safe, unplanned extension; sealed classes trade that away specifically when **exhaustive, compiler-verified handling** (Chapter 27 §4) matters more than open extensibility for a particular hierarchy. Recognizing when to make that tradeoff — not reciting OCP as an absolute rule — is what real design judgment looks like.

---

## 4. L — Liskov Substitution Principle

> **A subclass must be substitutable for its parent without breaking the correctness of code written against the parent.**

This formalizes something Chapter 15 §2.1's is-a test only gestured at informally: passing the is-a test syntactically isn't enough — a subclass must also honor the *behavioral contract* callers already rely on.

**Violation, using this handbook's own domain:**
```java
class Intern extends Employee {
    @Override
    public double getSalary() {
        throw new UnsupportedOperationException("Interns are unpaid");   // breaks the contract
    }
}
```

This compiles — `Intern` passes Chapter 15's structural is-a test perfectly — but it violates LSP: Chapter 16 §7's payroll loop, and every other piece of code written against `Employee.getSalary()`, reasonably expects a `double` back, not an exception. The moment an `Intern` is substituted into that loop, it breaks — silently, for callers who never had reason to suspect a subtype-specific exception was possible. **LSP is exactly the principle that makes Chapter 16's entire polymorphism chapter safe to rely on** — polymorphism only works as a design tool if every substitutable subtype genuinely honors what callers expect from the supertype.

---

## 5. I — Interface Segregation Principle

> **Prefer many small, focused interfaces over one large interface that forces implementers to support methods they don't need.**

Chapter 18 §7 promised this formalization directly. **Violation:**

```java
interface Payable {
    double calculatePay();
    void generateInvoice();     // Employee doesn't need this
    void fileTaxReport();        // neither does a simple Contract
}
```

Forcing `Employee` to implement `generateInvoice()` and `fileTaxReport()` — methods that make sense for `Invoice` or `Contract` (Chapter 27 §7's `Payable` implementers) but not for `Employee` at all — violates ISP. **Fix:** split into focused, single-purpose interfaces:

```java
interface Payable { double calculatePay(); }
interface Invoiceable { void generateInvoice(); }
interface TaxReportable { void fileTaxReport(); }
```

`Employee` implements only `Payable`; `Invoice` implements `Payable` and `Invoiceable`; only genuinely tax-relevant types implement `TaxReportable`. Each type depends only on what it actually uses.

---

## 6. D — Dependency Inversion Principle

> **Depend on abstractions, not concrete implementations.**

This is exactly what Chapter 22 §6.2's `Car`/`Engine` example already demonstrated, before naming it:

```java
class Car {
    private Engine engine;      // depends on the Engine INTERFACE — Chapter 18 — not a concrete class
    Car(Engine engine) { this.engine = engine; }
}
```

`Car` never depends on `CombustionEngine` or `ElectricEngine` directly — only on the `Engine` abstraction (Chapter 18). This is Dependency Inversion, applied.

### 6.1 Principle vs. Pattern — Setting Up Chapter 35

This is worth being precise about, since the two are easily conflated: **Dependency Inversion is the design principle** ("depend on abstractions"); **Dependency Injection is the pattern/mechanism that supplies a concrete implementation to satisfy that dependency** — typically through a constructor parameter (exactly `Car`'s constructor above), or, at framework scale, through the reflection-based field injection Chapter 30 §7 described conceptually. `Car`'s constructor accepting an `Engine` is dependency injection in its simplest form — something external decides *which* concrete `Engine` to hand over; `Car` itself never decides. Chapter 35 covers this pattern in full.

---

## 7. Real-World Example — Before and After

| Principle | Violation (in this handbook's domain) | Fix Already Demonstrated |
|---|---|---|
| **S**RP | `Employee` handling salary, persistence, and email | `ContactInfo` extracted (Ch. 22 §3) |
| **O**CP | A `switch` on employee type scattered through business logic | Polymorphic `getSalary()` override (Ch. 16 §7) |
| **L**SP | `Intern.getSalary()` throwing instead of returning a value | Every `Employee` subtype honoring the real contract (Ch. 15, 16) |
| **I**SP | One bloated `Payable` forcing unrelated methods on every implementer | Split into `Payable`/`Invoiceable`/`TaxReportable` (§5) |
| **D**IP | `Car` constructing a `CombustionEngine` directly inside itself | `Car(Engine engine)` depending on the interface (Ch. 22 §6.2) |

---

## 8. Best Practices

- Treat SOLID as design judgment, not law — Chapter 1 §1.14 already warned against over-engineering; applying every principle maximally, everywhere, produces exactly the class-explosion and unnecessary abstraction that chapter cautioned against.
- Use LSP as a genuine correctness check when overriding a method — ask whether callers relying on the parent's documented behavior would still get what they expect, not just whether the code compiles.
- Recognize OCP's tension with sealed classes (§3) as a deliberate tradeoff to make consciously, not a contradiction to avoid.

## 9. Common Mistakes

- ⚠️ Applying SRP to the point of absurd fragmentation — a separate class for every field is not "more responsible," it's unnecessary ceremony (Chapter 1 §1.14).
- ⚠️ Treating OCP as "never modify existing code, ever" — genuine bug fixes still require modification; OCP is about not needing to modify code *purely to add new behavior*.
- ⚠️ Confusing an LSP violation with ordinary, correct overriding — LSP is specifically about broken behavioral *contracts* (§4's exception-throwing example), not overriding in general, which Chapter 15 already established as normal and expected.
- ⚠️ Splitting interfaces (ISP) into single-method fragments far beyond what's actually useful, creating more ceremony than the original bloated interface had.

## 10. Interview Perspective

**Frequently Asked**

- *"What does SOLID stand for?"* — Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion (§2–§6).
- *"Give a concrete example of an LSP violation."* — A subclass overriding a method to throw instead of behaving as callers reasonably expect, even though it compiles fine as a subtype (§4).
- *"How does Dependency Inversion relate to Dependency Injection?"* — DIP is the design principle (depend on abstractions); Dependency Injection is the pattern that supplies a concrete implementation to satisfy that dependency, often via constructor parameters or framework-level reflection (§6.1, full treatment Chapter 35).

**Tricky Question**

- *"Do Chapter 27's sealed classes violate the Open/Closed Principle?"* — They deliberately trade away OCP's openness for a specific reason: compiler-verified exhaustive handling (Chapter 27 §4) matters more than unplanned extensibility for that particular hierarchy. It's a conscious, principled exception, not an oversight (§3).

**Common Misconception**

- Believing SOLID principles are independent, unrelated rules to memorize in isolation. In practice they reinforce each other — DIP's abstractions are exactly what make OCP's extensibility possible, and LSP is what makes substituting those abstractions (Chapter 16's polymorphism) actually safe to rely on.

---

## 11. Summary

- SRP: one reason to change per class — already demonstrated by extracting `ContactInfo` from `Employee` (Chapter 22).
- OCP: extensible without modifying existing code — already demonstrated by the polymorphic payroll loop (Chapter 16); deliberately traded away by sealed classes (Chapter 27) when exhaustiveness matters more.
- LSP: a subclass must honor its parent's behavioral contract, not just compile as a subtype — the principle that makes polymorphism (Chapter 16) safe.
- ISP: many focused interfaces beat one bloated one — the formalization Chapter 18 §7 promised.
- DIP: depend on abstractions, not concrete implementations — already demonstrated by `Car`/`Engine` (Chapter 22); realized in practice by the Dependency Injection pattern, Chapter 35's subject next.

## 12. Quick Revision

- S: one responsibility per class.
- O: open to extension, closed to modification (with sealed classes as a deliberate, principled exception).
- L: subclasses must honor the parent's real behavioral contract, not just compile.
- I: many small interfaces over one bloated one.
- D: depend on abstractions; Dependency Injection is the pattern that supplies the concrete implementation.

## 13. Self Assessment

1. Identify which SOLID principle `ContactInfo`'s extraction from `Employee` (Chapter 22) satisfies, and explain why in one sentence.
2. Write a concrete LSP violation involving `Manager` or `Intern`, distinct from this chapter's `Intern.getSalary()` example.
3. Explain, precisely, why Chapter 27's sealed classes represent a deliberate exception to OCP rather than a violation of it.
4. Split a bloated `Reportable` interface (with `generateSalesReport()`, `generateTaxReport()`, `generateAuditReport()`) into properly segregated interfaces.
5. In your own words, distinguish the Dependency Inversion principle from the Dependency Injection pattern — which one is the design idea, and which one is the mechanism?

---

## What's Next

**Chapter 35 — Dependency Injection** delivers the pattern §6.1 set up directly: how concrete implementations actually get supplied to satisfy Dependency Inversion's abstractions — from simple constructor injection (exactly `Car(Engine engine)`) to the reflection-and-annotation-driven container-managed injection Chapters 30 and 31 already built the mechanics for.
