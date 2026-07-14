# Chapter 15 — Inheritance

**Part VIII: Core OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Apply the "is-a" test correctly to decide when inheritance is the right tool — and recognize when it isn't.
- State precisely what a subclass inherits, what it doesn't, and why constructors are never among the former.
- Distinguish overloading, overriding, and method hiding — three genuinely different mechanisms this handbook has built toward across three separate chapters.
- Override a method correctly, following every rule the compiler enforces, and explain what `@Override` actually checks.
- Explain why Java allows a class to extend only one other class, and what problem that restriction avoids.

---

## 1. Introduction

Chapter 1 §1.8 introduced Inheritance as the second Pillar, with a single sentence: "a Manager *is an* Employee." Chapter 5, Chapter 9, Chapter 10, and Chapter 11 all quietly built the machinery this chapter finally assembles — constructor delegation via `super(...)`, method hiding vs. overriding as a distinction, `final` methods blocking overriding, and the complete parent-then-child initialization order. This chapter is where all of that becomes a deliberate design tool: `Employee`'s first real subclass, `Manager`.

> This chapter does not re-explain `super()` mechanics, the initialization order with a parent class, or `super.method()`'s static resolution — Chapter 11 is definitive for all three. It also doesn't cover dynamic dispatch (how the JVM picks *which* overridden method runs at runtime) — that's Chapter 16's job. This chapter is about what inheritance *is*, what a subclass gets, and the rules for overriding correctly.

---

## 2. Theory — What Inheritance Actually Is

> **Inheritance lets one class (a subclass) acquire the accessible fields and methods of another (its superclass), expressing an "is-a" relationship between them.**

```java
public class Manager extends Employee {
    // Manager automatically has everything Employee exposed to it
}
```

### 2.1 The "Is-A" Test

Before reaching for `extends`, ask directly: is a `Manager` genuinely a kind of `Employee`? Yes — every `Manager` has a salary, an employee ID, a department; a `Manager` *is* an `Employee`, just a more specialized one. Contrast this with a relationship like "an `Employee` has a `Department`" — that's a **has-a** relationship, and forcing inheritance onto it (`Employee extends Department`) would be a design mistake. Has-a relationships belong to Composition, which gets its own full treatment in Part X (Object Relationships, Chapters 20–22); this chapter is scoped specifically to is-a relationships and the `extends` mechanism.

---

## 3. What a Subclass Actually Inherits

| Member Kind | Inherited? |
|---|---|
| `public` / `protected` fields and methods | Yes |
| Package-private fields and methods (same package only) | Yes, if `Manager` is in the same package as `Employee` |
| `private` fields and methods | Present in memory (Chapter 11 §3 — one object, whole layout) but **not accessible by name** from the subclass |
| Constructors | **Never** — a subclass can only *invoke* a parent constructor via `super(...)` (Chapter 5 §6, Chapter 11 §2), never inherit one directly |
| Static members | Yes, inherited the same way instance members are, subject to the same access rules (Chapter 9) |

The `private` row is worth being precise about: a `private` field declared in `Employee` genuinely exists inside every `Manager` object — Chapter 11 §3 already established there's exactly one object, not two — but `Manager`'s own code cannot refer to it by name. It can only be reached through whatever `public`/`protected` methods `Employee` chose to expose, which is exactly Chapter 12's Encapsulation principle continuing to hold across the inheritance boundary, not being bypassed by it.

---

## 4. Method Overriding

**Overriding** means a subclass provides its own implementation of a method it inherited, using the **exact same signature** (Chapter 7 §2 — name plus parameter types) as the parent's version:

```java
public class Employee {
    public double getSalary() {
        return salary;
    }
}

public class Manager extends Employee {
    @Override
    public double getSalary() {
        return super.getSalary() + calculateManagementBonus();   // Chapter 11 §4
    }

    private double calculateManagementBonus() {
        return directReports.size() * 500;
    }
}
```

`super.getSalary()` reaches `Employee`'s exact version directly (Chapter 11 §4's `invokespecial` rule) — this is the standard, deliberate pattern for *extending*, not replacing, a parent's behavior.

### 4.1 Overloading vs. Overriding vs. Hiding — Three Different Things, One Point of Confusion

This handbook has now built all three pieces separately; this is the first place to hold them side by side:

| | Overloading (Ch. 7 §3) | Overriding (this chapter) | Method Hiding (Ch. 9 §5) |
|---|---|---|---|
| Applies to | Any methods, same class | Instance methods, subclass vs. parent | `static` methods, subclass vs. parent |
| Signature | Different | **Identical** | Identical |
| Resolved | Compile time, by argument types | Runtime, by actual object's class (Ch. 16) | Compile time, by declared reference type |
| Bytecode | Separate methods entirely | `invokevirtual`, dynamically dispatched | `invokestatic`, statically resolved |

### 4.2 Rules the Compiler Enforces on a Valid Override

- The signature must match **exactly** — same name, same parameter types, same order. A parameter-list mismatch doesn't produce a compile error; it silently creates an unrelated *overload* instead (§4.3 shows exactly this trap).
- The return type must be the same, or a subtype of the parent's return type (a **covariant return type**) — never a broader or unrelated type.
- The overriding method cannot reduce visibility — a `public` parent method cannot be overridden as `protected` or package-private (this is a direct consequence of Chapter 13: any code that could call the method through a parent-typed reference must still be able to call it through a subclass-typed one).
- A `final` method (Chapter 10 §4) cannot be overridden at all — the compiler rejects any attempt.
- A `static` method cannot be overridden — only hidden (Chapter 9 §5) — attempting to override one with an instance method, or vice versa, is a compile error.

### 4.3 `@Override` — What It Actually Does

```java
public class Manager extends Employee {
    @Override
    public double getSalery() {   // typo — this is NOT an override
        return 0;
    }
}
```

Without `@Override`, this compiles — Java sees a brand-new method, `getSalery`, unrelated to `Employee`'s `getSalary`, and creates it without complaint. **With** `@Override`, the compiler is instructed to verify that this method genuinely matches an inherited signature — and since `getSalery` doesn't, it becomes a compile error instead of a silent bug. `@Override` has **no effect at runtime at all** — it's purely a compile-time safety net, and this handbook treats it as mandatory on every override for exactly that reason.

---

## 5. Single Inheritance, and Why Java Restricts It

A Java class may `extends` **at most one** other class:

```java
class Manager extends Employee, Auditor { }   // ✘ not legal Java
```

This is a deliberate restriction, not an oversight. If a class could inherit from two parents that each defined a method with the same signature but different implementations, the compiler would have no principled way to decide which version the subclass actually means — this ambiguity is known as the **diamond problem**. Java sidesteps it entirely by allowing single inheritance for classes, while permitting a class to implement **multiple interfaces** (Chapter 18) — a different, deliberately more restricted mechanism where this specific ambiguity can't arise in the same way.

---

## 6. Types of Class Inheritance Java Supports

```
Single:                  Multilevel:                 Hierarchical:

  Employee                 Employee                    Employee
     ▲                        ▲                        ▲      ▲
     │                        │                       Manager  Intern
   Manager                 Manager
                              ▲
                          SeniorManager
```

- **Single** — one subclass, one superclass (`Manager extends Employee`).
- **Multilevel** — a chain (`SeniorManager extends Manager extends Employee`); Chapter 11 §3's "parent's entire initialization finishes before the child's begins" rule applies transitively down the whole chain.
- **Hierarchical** — multiple subclasses sharing one superclass (`Manager` and `Intern` both `extends Employee`), each free to override `Employee`'s methods independently.

Java does not support **multiple inheritance of classes** (§5) — only these three shapes, built from single-parent links.

---

## 7. If You Don't Write `extends` at All

Every class that doesn't explicitly extend another still, implicitly, extends `java.lang.Object` — Java's universal root class. This is why every object this handbook has created since Chapter 3 already has methods like `.equals()` and `.toString()` available, without ever having written them: they're inherited from `Object`, whether a class's declaration says `extends Object` or nothing at all. `Object`'s own methods get full treatment in Chapter 19; the only fact needed here is that "no parent specified" doesn't mean "no parent."

---

## 8. JVM Internals — What Changes in the Class File

Every compiled class file — not just explicit subclasses — carries a `super_class` reference in its constant pool, pointing to its parent (which, per §7, is `Object` if nothing else was specified). This is how the JVM resolves inherited members: when `Manager`'s bytecode references a method it didn't itself define, the JVM follows this `super_class` chain upward until it finds a class that does. This chapter deliberately stops here — the mechanism that decides, at runtime, *which* version of an overridden method actually executes (a per-class virtual method table, resolved via `invokevirtual`) is Chapter 16's subject in full, once Polymorphism gives that mechanism its purpose.

---

## 9. Real-World Example

```java
// File: com/acme/hr/Manager.java
package com.acme.hr;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {

    private List<Employee> directReports;

    public Manager(String employeeId, double salary) {
        super(employeeId, salary);            // Chapter 11 §2 — parent constructor first
        this.directReports = new ArrayList<>();
    }

    @Override
    public double getSalary() {
        return super.getSalary() + calculateManagementBonus();   // Chapter 11 §4
    }

    private double calculateManagementBonus() {
        return directReports.size() * 500;
    }

    public void addDirectReport(Employee emp) {
        emp.department = this.department;   // ✔ protected, same subclass-own-type access (Ch. 13 §5)
        directReports.add(emp);
    }
}
```

`Manager` never redeclares `employeeId`, `salary`, or the validation logic in `setSalary` — it inherits all of it, and overrides only the one behavior (`getSalary`) that genuinely needs to differ for a manager. This is inheritance doing exactly what §2's is-a test predicted it should.

---

## 10. Best Practices

- Apply the is-a test rigorously (§2.1) before writing `extends` — a relationship that's really has-a belongs to Composition (Part X), not inheritance.
- Keep hierarchies shallow — Chapter 1 §1.9 already flagged deep inheritance chains as a design risk; multilevel inheritance (§6) is legal but should be used sparingly.
- Mark every genuine override with `@Override` (§4.3) — it costs nothing and catches an entire class of silent bugs.
- Don't reduce an overridden method's visibility, and be deliberate about covariant return types — both are compiler-enforced (§4.2), but understanding *why* they're enforced makes the rule easier to apply correctly under pressure.

## 11. Common Mistakes

- ⚠️ Getting a parameter type slightly wrong in what was meant to be an override, silently creating an unrelated overload instead (§4.3) — this is exactly what `@Override` exists to catch.
- ⚠️ Assuming a `private` parent field is directly accessible from a subclass — it's part of the object (Chapter 11 §3) but not reachable by name (§3); only inherited accessors reach it.
- ⚠️ Trying to `extends` two classes at once, expecting Java to support multiple class inheritance the way some other languages do (§5).
- ⚠️ Assuming constructors are inherited — they're never inherited; only invoked via `super(...)` (Chapter 5 §6, Chapter 11 §2).
- ⚠️ Reaching for inheritance out of habit for any relationship between two classes, rather than applying the is-a test deliberately (§2.1).

## 12. Interview Perspective

**Frequently Asked**

- *"What is Inheritance?"* — A subclass acquiring the accessible members of a superclass, expressing an is-a relationship (§2).
- *"Why doesn't Java support multiple inheritance of classes?"* — To avoid the diamond problem — ambiguity over which of two parents' conflicting implementations a subclass should use (§5). Java allows multiple interface implementation instead, a more restricted mechanism where this specific ambiguity doesn't arise the same way.
- *"Are constructors inherited?"* — No, never — only invoked via `super(...)` (§3, Chapter 5 §6).

**Tricky Question**

- *"If `Employee` has a `private` field `salary`, does `Manager` inherit it?"* — It exists inside every `Manager` object (one object, whole layout, Chapter 11 §3) but is not directly accessible by name from `Manager`'s own code — only through whatever `public`/`protected` accessors `Employee` provides. This is a subtle but precise distinction between "part of the object" and "accessible by name."

**Common Misconception**

- Conflating overloading, overriding, and method hiding as roughly "the same idea, three different keywords." They're resolved completely differently — overloading and hiding at compile time, overriding dynamically at runtime (§4.1) — and this distinction is exactly what Chapter 16 depends on to explain polymorphism properly.

---

## 13. Summary

- Inheritance lets a subclass acquire a superclass's accessible members, and should be applied only where a genuine is-a relationship exists — has-a relationships belong to Composition instead.
- A subclass inherits public/protected/package-private members and static members, but never constructors, and cannot directly access a parent's `private` fields by name even though they exist in every subclass object.
- Overriding requires an identical signature, a same-or-covariant return type, and no reduction in visibility; `@Override` is a compile-time-only safety net that catches accidental overloads masquerading as overrides.
- Overloading, overriding, and method hiding are three genuinely distinct mechanisms — resolved at different times, via different bytecode instructions.
- Java restricts a class to single inheritance specifically to avoid the diamond problem; every class implicitly extends `Object` if nothing else is specified.

## 14. Quick Revision

- Is-a → inheritance. Has-a → composition (Part X).
- Inherited: public/protected/package-private members, static members. Never inherited: constructors. Present but inaccessible by name: `private` fields.
- Override = identical signature, runtime-resolved (`invokevirtual`, full mechanics Ch. 16). Overload = different signature, compile-time-resolved. Hide = `static`, compile-time-resolved by declared type.
- `@Override` = compile-time check only, zero runtime effect.
- Single inheritance only, for classes — avoids the diamond problem. Multiple interfaces are a separate mechanism (Ch. 18).

## 15. Self Assessment

1. Apply the is-a test to decide: should `Order` extend `Customer`, or should `Order` simply hold a reference to a `Customer`? Justify your answer.
2. `Employee` has a `private double salary` and a `public double getSalary()`. Explain precisely what `Manager` can and cannot do with `salary` directly.
3. Write an override of `getSalary()` in a `Manager` class that calls the parent's version and adds a bonus, and explain why this is preferable to duplicating `Employee`'s calculation logic.
4. A method meant to override a parent's `calculateBonus(double rating)` is instead declared `calculateBonus(int rating)`. What actually happens, and how would `@Override` have caught it?
5. Why does Java's restriction to single class inheritance not apply to interfaces, and what problem does that restriction avoid in the first place?

---

## What's Next

**Chapter 16 — Polymorphism** answers the question this chapter deliberately left open: when `Employee emp = new Manager(...); emp.getSalary();` is called, *how* does the JVM know to run `Manager`'s overridden version rather than `Employee`'s, given that `emp` is declared as an `Employee`? That mechanism — dynamic dispatch via each class's virtual method table — is exactly what makes overriding (this chapter) meaningfully different from overloading and hiding, and it's the payoff this whole chapter has been building toward.
