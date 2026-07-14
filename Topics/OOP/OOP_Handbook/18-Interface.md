# Chapter 18 — Interface

**Part VIII: Core OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain what an interface guarantees, and why its methods and fields carry implicit modifiers most learners never notice.
- Explain why a class can implement multiple interfaces when it can extend only one class — and what changed with Java 8's default methods.
- Choose correctly between an abstract class and an interface for a given design problem.
- Explain, at the bytecode level, how dispatching through an interface-typed reference differs from dispatching through a class-typed one.

---

## 1. Introduction

Chapter 17 §8 previewed Java's second abstraction mechanism without covering it: the `interface`. Where an abstract class (Chapter 17) expresses an is-a hierarchy with shared state and behavior, an interface expresses a **capability** — a contract a class promises to fulfill, entirely independent of what that class's is-a hierarchy looks like. This chapter delivers it in full, closing out both Part VIII and Java's complete abstraction toolkit.

> This chapter assumes Chapter 17's abstract classes completely — the comparison in §5 is built directly on it, not re-derived.

---

## 2. Theory — What an Interface Actually Is

> **An interface is a reference type that declares a contract — a set of method signatures — which any implementing class must fulfill, entirely independent of that class's inheritance hierarchy.**

```java
public interface Payable {
    double calculatePay();
}

public class Manager extends Employee implements Payable {
    @Override
    public double calculatePay() {
        return getSalary();
    }
}
```

`Manager` still `extends Employee` — its is-a hierarchy (Chapter 15 §2.1) — and separately `implements Payable`, a capability that says nothing about what `Manager` *is*, only what it can *do*.

### 2.1 The Implicit Modifiers Almost Nobody States Precisely

```java
public interface Payable {
    double calculatePay();       // implicitly public abstract — even though neither word is written
    double MAX_ALLOWED = 1_000_000;   // implicitly public static final — a constant, not a field
}
```

Every method declared in an interface (without a body) is implicitly `public` and `abstract`, whether or not you write those keywords. Every field declared in an interface is implicitly `public static final` — a true constant (Chapter 10 §3.3), never instance state. This isn't a convention; the compiler inserts these modifiers whether you write them or not, and an interface has no mechanism for declaring genuine instance fields at all.

---

## 3. Multiple Interface Implementation

A class may `implements` as many interfaces as it needs, even though it may `extends` only one class (Chapter 15 §5):

```java
public class Manager extends Employee implements Payable, Auditable {
    // must implement every abstract method from Payable AND Auditable
}
```

### 3.1 Why This Doesn't Reintroduce the Diamond Problem (Mostly)

Chapter 15 §5 explained Java restricts class inheritance to one parent specifically to avoid the diamond problem — ambiguity over which of two conflicting parent implementations to use. Historically, interfaces had *zero* implementations to conflict over — every method was abstract — so implementing several at once was always unambiguous: there was nothing to choose between, only contracts to fulfill.

### 3.2 Default Methods (Java 8) — And Why the Diamond Problem Partially Returns

Since Java 8, an interface may provide a **default method** — a method with a real implementation, which implementing classes inherit unless they choose to override it:

```java
public interface Payable {
    double calculatePay();

    default double calculateAnnualPay() {
        return calculatePay() * 12;   // shared, provided by the interface itself
    }
}
```

**Why were these added?** Backward compatibility. Before Java 8, adding a single new method to an existing interface broke every class that already implemented it — they'd suddenly be missing a required method. Default methods let library authors add new capability to widely-implemented interfaces without breaking existing code — most famously, this is how `java.util.Collection` gained `forEach()` in Java 8 without breaking every pre-existing `Collection` implementation across the entire Java ecosystem.

This does partially reopen the diamond problem: if a class implements two interfaces that both provide a **conflicting** default method with the same signature, Java does not silently pick one — it forces the implementing class to override the method explicitly and resolve the conflict itself, or the code fails to compile. Java's answer to the diamond problem, even here, is the same philosophy as §3.1: never guess — force the ambiguity to be resolved explicitly, in source code, rather than resolved implicitly by some rule a reader wouldn't see.

### 3.3 Static Methods on Interfaces (Java 8+)

An interface may also declare `static` methods — utility-style logic (Chapter 9) that belongs to the interface itself, not to any implementing instance:

```java
public interface Payable {
    static double standardTaxRate() { return 0.2; }
}
// called as: Payable.standardTaxRate();
```

---

## 4. Interface vs. Abstract Class

This is the comparison Chapter 17 §8 promised in full:

| | Abstract Class | Interface |
|---|---|---|
| Inheritance | Single only (Chapter 15 §5) | A class may implement many |
| Instance fields | Any kind | None — only implicit `public static final` constants |
| Constructors | Yes (Chapter 17 §5) | Never — no instance state to initialize, and never directly instantiable either |
| Method bodies | Freely mixed concrete + abstract | Abstract by default; `default`/`static` methods since Java 8 |
| Represents | An is-a relationship, shared state and behavior | A capability/contract, independent of is-a |
| Use when | Related classes share real state and behavior | Unrelated classes need to promise the same capability |

**The practical rule:** if the relationship is genuinely is-a and there's real shared state to manage, reach for an abstract class (Chapter 17). If you're describing a capability that classes across otherwise-unrelated hierarchies might all need to promise — comparable, payable, closeable — reach for an interface instead, since only interfaces let a class pick up several such capabilities at once.

---

## 5. JVM Internals — `invokeinterface`, and a Second Dispatch Table

Chapter 16 §4.2 described dynamic dispatch through a single per-class virtual method table (vtable), reached via `invokevirtual`. That model works cleanly when a class has exactly one chain of ancestors — but a class can implement *many* interfaces (§3), and a compact single vtable slot-numbering scheme doesn't extend naturally to an unbounded number of interface contracts layered on top of one class hierarchy.

Java's actual solution: calls made through an **interface-typed reference** compile to a distinct bytecode instruction, **`invokeinterface`**, which searches for the correct implementation by the interface method's identity rather than a fixed vtable slot number — a small amount of extra runtime lookup work compared to `invokevirtual`'s direct slot indexing, in exchange for supporting implementation of an arbitrary number of interfaces per class. This is precisely why `Payable payable = someManager; payable.calculatePay();` and `Employee emp = someManager; emp.getSalary();` — though both dynamically dispatched, both ultimately reaching the same `Manager` object — go through two different bytecode instructions to get there.

---

## 6. Real-World Example

```java
public interface Payable {
    double calculatePay();

    default double calculateAnnualPay() {
        return calculatePay() * 12;
    }
}

public abstract class Employee implements Payable {
    protected String department;
    private final String employeeId;
    private double salary;

    protected Employee(String employeeId, double salary) {
        this.employeeId = employeeId;
        setSalary(salary);
    }

    public abstract double getSalary();     // Chapter 17 — Employee's own abstraction

    @Override
    public double calculatePay() {           // fulfills the Payable contract
        return getSalary();
    }

    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative");
        this.salary = salary;
    }
}

public class Manager extends Employee {
    protected Manager(String employeeId, double salary) { super(employeeId, salary); }
    @Override
    public double getSalary() { return getBaseSalary() + 5000; }
}
```

```java
Manager mgr = new Manager("M001", 95000);
mgr.calculateAnnualPay();   // inherited straight from Payable's default method — no override needed
```

`Employee` now uses both abstraction mechanisms together, exactly as real Java code does: `abstract` to express its is-a hierarchy with `Manager`/`Intern` (Chapter 17), and `implements Payable` to promise a capability that could just as easily apply to an unrelated class like `Invoice` or `Contract` elsewhere in a larger system.

---

## 7. Best Practices

- Reach for an interface when describing a capability multiple, otherwise-unrelated classes might need to promise — not for expressing an is-a hierarchy, which is an abstract class's job (Chapter 17).
- Keep interfaces focused on one coherent capability rather than bundling many unrelated methods into one — a preview of the Interface Segregation Principle, formalized later in Chapter 34 (SOLID).
- Use `default` methods primarily for backward-compatible API evolution (§3.2), not as a substitute for an abstract class's genuine shared-state logic.
- When implementing multiple interfaces with potentially conflicting default methods, resolve the conflict explicitly and deliberately (§3.2) — never assume Java will pick a "sensible" one for you.

## 8. Common Mistakes

- ⚠️ Writing `protected double calculatePay();` inside an interface, expecting narrower visibility — interface methods are always implicitly `public` (§2.1); this either compiles as `public` anyway (pre-Java 9's abstract methods) or is simply not what most learners expect.
- ⚠️ Trying to declare genuine mutable instance state in an interface — only `public static final` constants are possible (§2.1); real per-instance state belongs in the implementing class.
- ⚠️ Assuming Java automatically resolves a conflict between two interfaces' default methods — it doesn't; the implementing class must override and resolve it explicitly (§3.2), or the code fails to compile.
- ⚠️ Treating "implements many interfaces" as free — each interface is still a real contract obligation the class must fully satisfy, exactly as if it were a single method count.

## 9. Interview Perspective

**Frequently Asked**

- *"Can an interface have a constructor?"* — No — there's no instance state to initialize (§2.1, §4), and an interface can never be instantiated on its own, directly or indirectly.
- *"Why were default methods added in Java 8?"* — Backward compatibility: to let existing interfaces gain new methods without breaking every class that already implemented them — `Collection.forEach()` is the canonical real-world example (§3.2).
- *"What's the actual difference between an abstract class and an interface?"* — Single vs. multiple inheritance, no instance state vs. any state, is-a vs. capability (§4) — be ready to give the practical "when to use which" rule, not just the syntax differences.

**Tricky Question**

- *"If a class implements two interfaces that each provide a conflicting `default` method with the same signature, what happens?"* — It fails to compile unless the implementing class explicitly overrides the method itself to resolve the conflict (§3.2) — Java's answer to the diamond problem here is the same as for class inheritance: force an explicit decision rather than silently choosing one.

**Common Misconception**

- Believing interfaces became "basically abstract classes" once Java 8 added default and static methods. The core structural differences remain — no instance fields, no constructors, and multiple implementation — default methods only added the ability to share *some* implementation, not to close the gap entirely (§4).

---

## 10. Summary

- An interface declares a contract of method signatures a class must fulfill; its methods are implicitly `public` (and `abstract`, unless `default`/`static`), and its fields are implicitly `public static final` constants.
- A class may implement multiple interfaces, unlike the single-inheritance restriction on classes — historically unambiguous, since interface methods had no implementation to conflict over.
- Default methods (Java 8+) let interfaces evolve without breaking existing implementers, but can reintroduce a limited diamond problem, which Java resolves by forcing an explicit override rather than an implicit choice.
- Interface-typed calls dispatch via `invokeinterface`, a distinct bytecode instruction from `invokevirtual` — necessary because a class implementing many interfaces doesn't fit a single fixed-slot vtable model.
- Use an abstract class for is-a hierarchies with shared state; use an interface for a capability independent of that hierarchy — and combine both on the same class, exactly as real Java code does.

## 11. Quick Revision

- Interface method: implicitly `public abstract` (unless `default`/`static`). Interface field: implicitly `public static final`.
- Multiple interfaces per class; single class inheritance only.
- No constructors, no instance state in an interface — ever.
- Default methods: backward-compatible API evolution; conflicting defaults must be resolved explicitly by the implementing class.
- `invokeinterface` ≠ `invokevirtual` — a distinct dispatch mechanism for interface-typed calls.

## 12. Self Assessment

1. Why is `public interface Payable { double calculatePay(); }` functionally identical to writing `public abstract double calculatePay();` explicitly?
2. Why doesn't implementing multiple interfaces cause the same diamond-problem ambiguity that multiple class inheritance would — at least before Java 8?
3. What specific problem did default methods solve when they were introduced in Java 8? Give the canonical real-world example.
4. If `Manager implements Payable, Auditable` and both declare a conflicting `default validate()` method, what must `Manager` do to compile successfully?
5. Give one example of a class that should be an abstract class, one that should be an interface, and explain your reasoning for each using this chapter's practical rule.

---

## What's Next

Part VIII (Core OOP) is now complete — Inheritance, Polymorphism, and Abstraction (both abstract classes and interfaces) have all been covered in full. **Chapter 19 — The Object Class** opens Part IX. It formalizes `java.lang.Object` — the implicit universal parent Chapter 15 §7 named but didn't detail — and finally delivers the proper `.equals()`/`hashCode()` override mechanics this handbook has flagged forward since Chapter 3 §8.
