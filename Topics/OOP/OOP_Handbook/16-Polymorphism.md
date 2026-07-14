# Chapter 16 — Polymorphism

**Part VIII: Core OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain dynamic method dispatch precisely — the mechanism Chapter 15 deliberately left unexplained.
- Distinguish compile-time polymorphism (overloading) from runtime polymorphism (overriding) with full confidence, using the exact resolution mechanism for each.
- Explain, correctly, that fields in Java are never polymorphic — only methods are — and why that distinction trips up even experienced developers.
- Use upcasting, `instanceof`, and downcasting appropriately, and recognize excessive downcasting as a design smell.

---

## 1. Introduction

Chapter 15 built `Manager extends Employee` with an overridden `getSalary()`, and asked, without answering: when a variable is *declared* as `Employee` but *refers to* a `Manager` object, which version of `getSalary()` actually runs? This chapter answers that question in full — and in doing so, resolves several threads this handbook has deliberately left open since Chapter 7.

> This chapter assumes Chapter 15's overriding rules and vocabulary (signature, `@Override`, the overloading/overriding/hiding table) completely. It does not re-explain any of them — only the missing mechanism: *how* the JVM picks which override runs.

---

## 2. Theory — What Polymorphism Actually Means

> **Polymorphism lets a reference of a supertype refer to an object of any of its subtypes, and lets a call to an overridden method through that reference invoke the *actual object's* version — not the version belonging to the reference's declared type.**

```java
Employee emp = new Manager("M001", 95000);   // declared as Employee, refers to a Manager
emp.getSalary();                              // runs Manager's override, not Employee's
```

This single example is the payoff of everything Chapter 15 built. The rest of this chapter is about exactly *why* this happens.

### 2.1 Two Kinds of Polymorphism — Now Fully Distinguished

This handbook has actually covered both kinds already, in isolation, without naming them together:

| | Compile-Time Polymorphism | Runtime Polymorphism |
|---|---|---|
| Mechanism | Overloading (Chapter 7 §3) | Overriding (Chapter 15 §4) |
| Resolved | At compile time, by argument types | At runtime, by the object's actual class |
| Also called | Static binding | Dynamic binding / dynamic dispatch |

Chapter 7 §3 already called overloading "static binding" in passing; this chapter is where that label's counterpart — dynamic binding — finally gets explained.

---

## 3. Upcasting

Assigning a subclass object to a supertype-typed reference — as in §2's example — is called **upcasting**, and it's always implicit and safe:

```java
Employee emp = new Manager("M001", 95000);   // upcasting — no cast needed
```

Recall Chapter 3 §3.2: a variable's declared type governs what the compiler will *let you call* through it, but it never changes what object actually exists on the Heap. `emp` here is `Employee`-typed, so the compiler only permits calling methods `Employee` declares — but the object it refers to is, and remains, a full `Manager`, with all of `Manager`'s overridden behavior intact.

---

## 4. Dynamic Method Dispatch — The Mechanism, In Full

This is the answer Chapter 15 §8 deliberately deferred.

### 4.1 What the Compiler Checks vs. What the JVM Decides

The compiler, working only from `emp`'s **declared** type (`Employee`), verifies that `getSalary()` exists somewhere in `Employee`'s method set — that's the full extent of its job. It has no idea, and doesn't need to know, that `emp` will actually refer to a `Manager` at runtime. The decision of *which implementation* to run is deferred entirely to execution time, based on the object's **actual class** — this deferral is what "dynamic" in dynamic dispatch means.

### 4.2 The Virtual Method Table

Chapter 7 §6 introduced `invokevirtual` as the bytecode instruction for ordinary instance method calls, with a promise that its dynamic-dispatch mechanism would be explained "once Polymorphism and Method Dispatch are in scope." Here is that mechanism: at class-loading time (Chapter 2 §6.2), the JVM builds each class a **virtual method table** (informally, a "vtable") — an array mapping every method the class can respond to onto the specific implementation that should run for it.

```
Employee's vtable:              Manager's vtable:

getSalary  → Employee.getSalary  getSalary  → Manager.getSalary   ← overridden slot replaced
getName    → Employee.getName    getName    → Employee.getName    ← inherited, unchanged
```

When `emp.getSalary()` executes, `invokevirtual` doesn't consult `emp`'s declared type at all — it looks up the *actual object's* class (found via the object header's class pointer, Chapter 3 §6), fetches **that** class's vtable, and jumps to whatever implementation sits in the `getSalary` slot. For a `Manager` object, that slot holds `Manager.getSalary`, regardless of what type the reference calling it was declared as. This is the concrete resolution of everything Chapters 7, 9, 11, and 15 have been pointing toward:

- Chapter 7 §6's `invokevirtual` preview — now fully explained.
- Chapter 9 §5's static methods being *hidden, not overridden* — because `static` methods have no object, they have no vtable slot at all; `invokestatic` never consults one.
- Chapter 11 §4's `super.method()` using `invokespecial` — because it deliberately bypasses the vtable lookup to reach one specific, statically-known implementation directly.
- Chapter 15's entire overriding discussion — now shown to be, mechanically, "replacing an entry in the subclass's vtable."

---

## 5. The Rule Almost Everyone Gets Wrong: Fields Are Never Polymorphic

This is the sharpest, most commonly missed distinction in this entire topic: **dynamic dispatch applies only to instance methods. Fields are always resolved by the reference's declared type, at compile time — never dynamically.**

```java
class Employee {
    String role = "Employee";
}

class Manager extends Employee {
    String role = "Manager";   // hides Employee's role — Chapter 4 §5.3's shadowing, at the class level
}

Employee emp = new Manager();
System.out.println(emp.role);          // "Employee" — resolved by emp's DECLARED type
System.out.println(((Manager) emp).role); // "Manager" — only visible after an explicit downcast
```

Unlike `getSalary()` in §2, which dispatches to `Manager`'s version through an `Employee`-typed reference, `emp.role` reads `Employee`'s field, not `Manager`'s — because field access compiles directly, with no vtable lookup at all, purely against the reference's declared type. This asymmetry — methods are dynamic, fields are static — is precisely why relying on field hiding across an inheritance boundary is dangerous: the same-looking code (`emp.role` vs. `emp.getSalary()`) resolves through two entirely different mechanisms depending on whether `role` or `getSalary()` is what's being accessed.

---

## 6. `instanceof` and Downcasting

Sometimes code genuinely needs to act on a subtype-specific capability that the supertype reference can't see. `instanceof` checks an object's actual runtime class before a **downcast** — the reverse of upcasting (§3), and one that must be explicit because it isn't always safe:

```java
void processPayroll(Employee emp) {
    if (emp instanceof Manager) {
        Manager mgr = (Manager) emp;      // explicit downcast, safe because of the check above
        mgr.approveTeamExpenses();        // a Manager-specific method Employee doesn't have
    }
}
```

Since Java 16, pattern matching for `instanceof` combines the check and the cast into one step:

```java
if (emp instanceof Manager mgr) {
    mgr.approveTeamExpenses();   // mgr is already cast, in scope, ready to use
}
```

An unchecked downcast that turns out to be wrong throws a `ClassCastException` at runtime — the entire reason `instanceof` (or the pattern-matching form) is checked *before* casting, rather than casting first and hoping.

---

## 7. Real-World Example

```java
public class Employee {
    protected double salary;
    public double getSalary() { return salary; }
}

public class Manager extends Employee {
    @Override
    public double getSalary() { return salary + 5000; }   // management bonus
}

public class Intern extends Employee {
    @Override
    public double getSalary() { return salary * 0.5; }    // stipend rate
}
```

```java
List<Employee> payroll = List.of(
    new Employee(/* ... */),
    new Manager(/* ... */),
    new Intern(/* ... */)
);

for (Employee emp : payroll) {
    System.out.println(emp.getSalary());   // dispatches to each object's OWN override
}
```

The loop's code doesn't know or care which concrete type each `emp` actually is — `getSalary()` dispatches correctly every time, via each object's own vtable entry (§4.2). This is precisely the practical value Chapter 1 §1.6 promised when it described adding a new payment method "without changing existing business logic": a new `Contractor extends Employee` with its own `getSalary()` override could be added to this list tomorrow, and this loop would handle it correctly without a single line of it changing.

---

## 8. Best Practices

- Write code against the supertype whenever possible (`Employee`, not `Manager`) — this is what makes the polymorphic loop in §7 extensible without modification, and it's a preview of the "program to an interface, not an implementation" principle Chapter 34 (SOLID) formalizes.
- Treat repeated `instanceof` checks and downcasting as a design smell, not a normal pattern — if code keeps asking "is this actually a Manager?", an overridden method on the supertype is very often the cleaner fix.
- Never rely on field hiding across an inheritance boundary (§5) — if two classes need visibly different data under the same name, that's a signal to use an overridden accessor method instead, precisely because methods (unlike fields) dispatch correctly.

## 9. Common Mistakes

- ⚠️ Assuming a `static` method call participates in dynamic dispatch the way an overridden instance method does — it doesn't; static calls never consult a vtable at all (§4.2, Chapter 9 §5).
- ⚠️ Expecting field access to behave like method overriding — fields are always resolved by the declared reference type, never dynamically (§5) — this is the single most commonly wrong assumption in this entire chapter's territory.
- ⚠️ Downcasting without an `instanceof` check first, risking a `ClassCastException` at runtime instead of a controlled, checked branch (§6).
- ⚠️ Writing code full of `instanceof` chains against every possible subtype instead of letting overriding do the work polymorphically (§8) — functionally correct, but it forfeits the entire benefit inheritance and overriding were meant to provide.

## 10. Interview Perspective

**Frequently Asked**

- *"What is polymorphism, and how is it implemented?"* — A supertype reference can refer to any subtype object, and calls to overridden methods dispatch to the actual object's implementation via a per-class virtual method table, looked up through `invokevirtual` at runtime (§2, §4.2).
- *"Difference between compile-time and runtime polymorphism?"* — Compile-time = overloading, resolved by argument types before the program runs (Chapter 7 §3). Runtime = overriding, resolved by the object's actual class during execution (§2.1, §4).
- *"Are fields polymorphic in Java?"* — No — a frequently mis-answered question. Fields are always resolved by the reference's declared type at compile time; only methods dispatch dynamically (§5).

**Tricky Question**

- *"Given `Employee emp = new Manager(); System.out.println(emp.role);` where both classes declare a `role` field, what prints?"* — `Employee`'s value, not `Manager`'s (§5) — because field access never goes through the vtable lookup that method calls do; it's resolved entirely by `emp`'s declared type at compile time.

**Common Misconception**

- Assuming "polymorphism" is a single, uniform behavior across everything in Java. It applies specifically and only to instance method invocation via `invokevirtual` (§4.2) — static methods (Chapter 9 §5), `final` methods (Chapter 10 §4, which can't be overridden at all), constructors (Chapter 5 §3.2, never inherited), and fields (§5) all behave differently, for reasons this handbook has now covered individually across five separate chapters.

---

## 11. Summary

- Polymorphism lets a supertype reference refer to any subtype object; calling an overridden method through it dispatches to the actual object's implementation, not the reference's declared type's.
- Compile-time polymorphism (overloading) resolves by argument types before runtime; runtime polymorphism (overriding) resolves by the object's actual class, via each class's virtual method table, at the moment the call executes.
- `invokevirtual` performs this vtable lookup; `invokestatic` (static methods) and `invokespecial` (`super.method()`, constructors, private methods) both deliberately bypass it.
- Fields are never polymorphic — they're always resolved by a reference's declared type at compile time, unlike methods.
- `instanceof` (optionally with pattern matching) should precede any downcast, to avoid a runtime `ClassCastException`.

## 12. Quick Revision

- Upcasting: implicit, always safe, doesn't change the actual object.
- Dynamic dispatch: `invokevirtual` looks up the object's actual class's vtable — not the reference's declared type.
- Overloading = compile-time. Overriding = runtime, via vtable. Static/`final`/private calls = bypass the vtable entirely.
- Fields: always resolved statically by declared type — never dynamically dispatched, unlike methods.
- `instanceof` before downcasting, always.

## 13. Self Assessment

1. Explain precisely what determines which `getSalary()` implementation runs when `Employee emp = new Manager();` — name the exact mechanism, not just "it picks the right one."
2. Why doesn't a `static` method call ever go through the vtable lookup that an overridden instance method does?
3. `Employee` and `Manager` both declare a field `String role`. What does `emp.role` print if `emp` is declared `Employee` but refers to a `Manager` object — and why does this differ from how `emp.getSalary()` would resolve?
4. Write a loop over a `List<Employee>` containing a mix of `Employee`, `Manager`, and `Intern` objects that correctly calls each one's own `getSalary()` — and explain why no `instanceof` check is needed to do this correctly.
5. Why is a long chain of `instanceof` checks against every subtype often considered a design smell, given what this chapter just explained about how overriding already solves that problem?

---

## What's Next

**Chapter 17 — Abstraction** completes the trio Chapters 15 and 16 built toward: it formalizes *what* a class should expose as an abstract contract — via `abstract` classes and methods — versus what it should hide, tying directly into `Employee` potentially becoming an `abstract` base type itself, since no plain, undifferentiated `Employee` may ever need to exist on its own once `Manager` and `Intern` are the classes actually instantiated in practice.
