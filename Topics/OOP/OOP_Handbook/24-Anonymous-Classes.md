# Chapter 24 — Anonymous Classes

**Part XI: Advanced OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain precisely what an anonymous class is, and why `new Payable() { ... }` isn't actually instantiating an interface.
- State the restrictions unique to anonymous classes — no explicit constructor, and exactly one supertype, never both a class and an interface.
- Explain why an anonymous class captures local variables under the exact same effectively-final rule as a named local class.
- Explain the precise, commonly-tested difference between an anonymous class and a lambda expression: what `this` refers to in each.

---

## 1. Introduction

Chapter 23 covered three of Java's four nesting forms — static nested classes, inner classes, and local classes. This chapter delivers the fourth and final one: the **anonymous class** — a local class (Chapter 23 §6) with no name at all, declared and instantiated in a single expression, typically to provide a one-off implementation of an interface (Chapter 18) or abstract class (Chapter 17).

> This chapter assumes Chapter 23's local classes and effectively-final capture rule completely — an anonymous class is, mechanically, exactly a local class without a name, and inherits every one of those rules unchanged.

---

## 2. Theory — What's Actually Happening

```java
Payable oneOffPayable = new Payable() {
    @Override
    public double calculatePay() {
        return 500.0;
    }
};
```

This looks like it's instantiating `Payable` directly — but Chapter 18 §2.1 already established that an interface can never be instantiated on its own. What's actually happening: the compiler generates a brand-new, unnamed class that `implements Payable`, supplies the body written between the braces as that class's implementation, and instantiates *that* — all in one expression, at the exact point it's written. `Payable` itself is never instantiated; a hidden, one-off implementer of it is.

The same mechanism works against an abstract class (Chapter 17), fulfilling its abstract methods inline:

```java
Employee tempWorker = new Employee("T001", "Temp", 0, "", "") {
    @Override
    public double getSalary() {
        return 200.0;   // a flat day rate — no need for a formally named subclass anywhere
    }
};
```

This provides `Employee`'s mandatory `getSalary()` (Chapter 17 §3) for a single, one-off use, without ever creating a named `TempWorker` class in the codebase at all.

---

## 3. Restrictions Unique to Anonymous Classes

### 3.1 No Explicit Constructor

An anonymous class has no name — and a constructor must share its class's exact name (Chapter 5 §3) — so an anonymous class can never declare one. Any constructor-like setup logic instead belongs in an **instance initializer block** (Chapter 6 §3.2), which runs during construction regardless of what the anonymous class has no name to declare explicitly:

```java
Payable configured = new Payable() {
    private final double rate;
    {
        rate = 0.15;   // instance initializer block — the closest thing to a constructor here
    }
    @Override
    public double calculatePay() { return 1000 * rate; }
};
```

### 3.2 Exactly One Supertype — Never Both

An anonymous class may `implements` **one** interface, or `extends` **one** class — never both, and never more than one interface:

```java
new Payable(), Auditable() { ... }   // ✘ not legal — an anonymous class gets exactly one supertype
```

This is Chapter 15 §5's single-inheritance rule and Chapter 18 §3's multiple-interface allowance, both narrowed further for the anonymous case specifically: a *named* class can `extends` one class and `implements` several interfaces at once (Chapter 18 §6's `Employee implements Payable` alongside its own `abstract` hierarchy is exactly that shape) — an anonymous class gets to pick only one supertype, total, of either kind.

### 3.3 Capturing Local Variables — Identical to Chapter 23 §6.1

Because an anonymous class is mechanically a local class, it captures enclosing local variables under the exact same rule: only **effectively final** ones, copied into the anonymous class at creation time, for precisely the reason Chapter 23 §6.1 already established — the original Stack-resident local variable (Chapter 4 §5) won't outlive its method call, so a reassignable capture would silently go stale.

```java
double bonusRate = 0.1;   // must be effectively final
Payable bonusPayable = new Payable() {
    @Override
    public double calculatePay() { return 1000 * bonusRate; }
};
bonusRate = 0.2;   // ✘ compile error — same rule as Chapter 23 §6.1, no exception for anonymous classes
```

---

## 4. JVM Internals — Naming

Since an anonymous class has no name to base a `.class` file on, the compiler assigns each one a **sequential number**, appended to the enclosing class — extending Chapter 2 §5.1's `Outer$Inner.class` pattern to this exact case: the first anonymous class defined inside `Employee` compiles to `Employee$1.class`, the second to `Employee$2.class`, and so on, purely in the order they appear in the source file.

---

## 5. Anonymous Classes vs. Lambda Expressions — A Precise Comparison

Since Java 8, an interface with exactly one abstract method (a **functional interface**) can be implemented far more concisely with a **lambda expression** instead of a full anonymous class:

```java
Payable viaAnonymous = new Payable() {
    @Override
    public double calculatePay() { return 500.0; }
};

Payable viaLambda = () -> 500.0;   // equivalent, far more concise
```

Both provide a one-off implementation of `Payable.calculatePay()`. But there's a precise, frequently-tested difference between them: **inside an anonymous class, `this` refers to the anonymous class's own instance — a genuinely new object with its own identity. Inside a lambda, `this` refers to the *enclosing* instance's `this` — a lambda does not introduce a new `this` of its own at all.**

```java
public class Employee {
    void demo() {
        Runnable viaAnon = new Runnable() {
            public void run() {
                System.out.println(this);   // prints the anonymous Runnable instance itself
            }
        };
        Runnable viaLambda = () -> {
            System.out.println(this);       // prints the ENCLOSING Employee instance
        };
    }
}
```

This handbook's scope is OOP fundamentals, not the functional-programming features layered on top of Java since version 8 — lambdas and functional interfaces are genuinely their own topic — but this `this`-binding distinction is squarely about object identity and is worth carrying forward as the sharpest, most precise answer to "what's actually different between these two," beyond just syntax brevity.

---

## 6. Real-World Example

```java
public interface Payable {
    double calculatePay();
    default double calculateAnnualPay() { return calculatePay() * 12; }
}

public abstract class Employee implements Payable {
    // ... existing fields, constructor, abstract getSalary(), etc. from earlier chapters ...
}
```

```java
// One-off Payable implementation, used exactly once, nowhere else in the codebase
Payable bonusPool = new Payable() {
    @Override
    public double calculatePay() {
        return 10000.0;   // a fixed, shared bonus pool amount, not tied to any specific Employee
    }
};

// One-off Employee subclass, for a single temporary worker, without a named TempWorker class
Employee tempWorker = new Employee("T001", "Contract Worker", 0, "", "") {
    @Override
    public double getSalary() {
        return 200.0;   // flat day rate
    }
};
```

---

## 7. Best Practices

- Reserve anonymous classes for genuinely one-off implementations used at exactly one call site — once the same logic is needed elsewhere, a proper named (possibly nested, Chapter 23) class communicates intent far better.
- Keep an anonymous class's body short — readability degrades quickly past a handful of lines, precisely because it has no name to signal what it represents at a glance.
- Prefer a lambda (§5) over an anonymous class whenever the target is a functional interface (a single abstract method) — it's more concise and makes the one-off, stateless intent clearer.
- Use an instance initializer block (§3.1) for anonymous-class setup logic, since an explicit constructor is never an option.

## 8. Common Mistakes

- ⚠️ Trying to declare an explicit constructor inside an anonymous class — impossible, since it has no name to declare one with (§3.1).
- ⚠️ Trying to both `extends` a class and `implements` an interface on the same anonymous class — only one supertype total is ever allowed (§3.2).
- ⚠️ Assuming an anonymous class is exempt from the effectively-final capture rule because it "isn't really a class" — it is, mechanically, exactly a nameless local class, and Chapter 23 §6.1's rule applies without exception (§3.3).
- ⚠️ Using an anonymous class where a lambda would be clearer and more idiomatic for a single-method functional interface (§5).

## 9. Interview Perspective

**Frequently Asked**

- *"What is an anonymous class?"* — A local class with no name, declared and instantiated in one expression, typically providing a one-off implementation of an interface or abstract class (§2).
- *"Can an anonymous class implement an interface and extend a class at the same time?"* — No — exactly one supertype, ever, whether a class or an interface (§3.2).
- *"Can an anonymous class have a constructor?"* — No — it has no name to declare one with; use an instance initializer block instead (§3.1).

**Tricky Question**

- *"Inside a lambda and inside an anonymous class, both implementing the same functional interface, what does `this` refer to in each?"* — In the anonymous class, `this` is the anonymous class's own new instance; in the lambda, `this` refers to the *enclosing* instance — a lambda never introduces a `this` of its own (§5). This is one of the sharpest, most precise ways to demonstrate real understanding of the difference between the two, beyond citing syntax brevity.

**Common Misconception**

- Believing `new Payable() { ... }` instantiates the `Payable` interface directly. It doesn't — Chapter 18 already established interfaces can never be instantiated; this syntax generates and instantiates an entirely new, compiler-named, unnamed-in-source class that implements `Payable` (§2, §4).

---

## 10. Summary

- An anonymous class is a nameless local class, declared and instantiated in a single expression, typically implementing an interface or extending a class (including an abstract one) inline for one-off use.
- It can never declare an explicit constructor (use an instance initializer block instead), and it may have exactly one supertype — never both a class and an interface, and never multiple interfaces.
- It captures effectively-final local variables under the exact same rule as any named local class (Chapter 23 §6.1).
- The compiler names each anonymous class sequentially (`Outer$1`, `Outer$2`, ...), extending Chapter 2's nested-class naming pattern.
- Compared to a lambda expression, the sharpest distinguishing fact is `this`: an anonymous class's `this` refers to its own new instance; a lambda's `this` refers to the enclosing instance.

## 11. Quick Revision

- Anonymous class = nameless local class, one expression, one-off implementation.
- No explicit constructor — use an instance initializer block.
- Exactly one supertype total — never a class and an interface together.
- Captures effectively-final locals, exactly like Chapter 23's local classes.
- `this` inside an anonymous class = its own instance; `this` inside a lambda = the enclosing instance.

## 12. Self Assessment

1. Explain precisely why `new Payable() { ... }` does not actually instantiate the `Payable` interface.
2. Why can't an anonymous class declare an explicit constructor, and what's the standard workaround?
3. Why is `new Payable(), Auditable() { ... }` not legal Java, given what Chapter 18 established about multiple interface implementation for named classes?
4. A local variable is captured by an anonymous class and then reassigned afterward in the enclosing method. What happens, and why, tying your answer back to Chapter 4's Stack model?
5. Inside an anonymous `Runnable`'s `run()` method and inside an equivalent lambda, what does `this` refer to in each — and why does that difference exist?

---

## What's Next

**Chapter 25 — Enums** returns to ordinary named types, covering Java's `enum` construct — a special kind of class (with its own distinct rules) for representing a small, fixed set of related constants, such as `EmployeeType.MANAGER`, `INTERN`, `FULL_TIME`.
