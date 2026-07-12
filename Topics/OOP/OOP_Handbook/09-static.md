# Chapter 9 — `static`

**Part VI: Keywords**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Define a static method precisely, and explain why it can't touch instance members directly.
- Explain the difference between calling a static member through the class name versus through an object reference — and why only one of those is good practice.
- Explain, at the bytecode level, why static methods can't be overridden the way instance methods can — as a direct consequence of Chapter 7's dispatch mechanism, not an arbitrary rule.
- Recognize and correctly build a static utility class.
- Assemble the complete picture of `static` — variables, blocks, and methods — as one coherent keyword, not three unrelated facts.

---

## 1. Introduction

`static` has already appeared three times in this handbook, each time deliberately incomplete: Chapter 4 §4 placed static *variables* in Metaspace, Chapter 6 §3.3 covered static *initializer blocks*, and Chapter 8 §5 explained why `this` can't exist in a static context. This chapter adds the missing piece — static **methods** — and then assembles all four into the single, coherent story `static` actually tells.

> This chapter does not re-explain static variables' memory location or static blocks' execution timing — both are referenced, not repeated. It builds only on Chapter 7's method-call model and Chapter 8's `this` explanation.

---

## 2. Theory — What Is a Static Method?

> **A static method belongs to the class itself, not to any object of it — it can be called without ever creating an instance, and it has no access to instance state, because it has no guaranteed object to act on.**

```java
class SalaryUtils {
    static double applyTax(double salary, double taxRate) {
        return salary * (1 - taxRate);
    }
}

SalaryUtils.applyTax(95000, 0.2);   // called directly on the class — no object needed
```

### 2.1 Static vs. Instance Methods

| | Static Method | Instance Method |
|---|---|---|
| Belongs to | The class | Each object |
| Callable without an object? | Yes | No — needs an object to invoke it on |
| Has `this`? | Never (Chapter 8 §5) | Yes, always (Chapter 8 §2) |
| Can access instance fields/methods directly? | No | Yes |
| Can access static fields/methods directly? | Yes | Yes |
| Resolved | At compile time | Dynamically, based on the object's class (previewed in Ch. 7 §6, full detail Ch. 16/40) |

### 2.2 Why a Static Method Can't Touch Instance Members Directly

```java
class Employee {
    private double salary;

    static void printSalary() {
        System.out.println(salary);   // ✘ compile error:
                                        //   non-static field salary cannot be referenced
                                        //   from a static context
    }
}
```

This is the exact same underlying reason `this` is unavailable in a static context (Chapter 8 §5, §6): `salary` lives inside a *specific object*, and a static method call carries no object at all — there's simply no instance for `salary` to belong to at that call site. A static method *can* still work with instance data, but only if an object is handed to it explicitly:

```java
static void printSalary(Employee emp) {   // object passed in explicitly
    System.out.println(emp.salary);        // fine — emp is a concrete object
}
```

---

## 3. Calling a Static Member — Class Name vs. Object Reference

Java technically allows calling a static method through an object reference, but strongly favors calling it through the class name:

```java
SalaryUtils.applyTax(95000, 0.2);          // ✔ recommended — clearly a class-level call

SalaryUtils utils = new SalaryUtils();
utils.applyTax(95000, 0.2);                 // ✔ compiles, but misleading — looks like an instance call
```

Both compile and behave identically, because — as §5 will show precisely — a static call never actually depends on any object, even when one happens to be sitting to the left of the dot. Calling it through an instance only invites a reader to wrongly assume the call does something instance-specific. Most linters and style guides (and this handbook) treat calling a static member through an instance as a code smell, not a real error.

**A genuinely surprising but entirely consistent fact:** because static calls never depend on the object at all, this compiles and runs without throwing anything:

```java
Employee emp = null;
Employee.printCount();     // ✔ works fine — printCount() is static; no object was ever needed
```

We'll see exactly why `null` doesn't matter here once §5 covers `invokestatic`.

---

## 4. Static Utility Classes

A class made up entirely of static members, never meant to be instantiated, is a common and legitimate pattern — `java.lang.Math` is the standard library's own example. Chapter 5 §8 already introduced the mechanism that enforces this: a **private constructor**.

```java
public final class SalaryUtils {

    private SalaryUtils() { }   // Chapter 5 §8 — prevents `new SalaryUtils()` from outside

    static double applyTax(double salary, double taxRate) {
        return salary * (1 - taxRate);
    }

    static double calculateBonus(double salary, double performanceRating) {
        return salary * performanceRating * 0.1;
    }
}
```

Marking the class itself `final` here is a deliberate, related choice: it prevents anyone from subclassing a class that was never meant to have instances at all — inheritance is Chapter 15's topic, but the `final` keyword itself gets its own full chapter next (Chapter 10).

---

## 5. JVM Internals — `invokestatic`, and Why Static Methods Can't Be Overridden

Chapter 7 §6 introduced `invokevirtual` for ordinary instance method calls, and noted the JVM decides *which* implementation to run based on the object's actual class — dynamically, at runtime. A static method call compiles to a completely different bytecode instruction: **`invokestatic`**.

```
Instance call:  emp.raiseSalary(5000);      → invokevirtual  (needs an object; dispatch is dynamic)
Static call:    SalaryUtils.applyTax(...);   → invokestatic   (no object needed; resolved entirely
                                                                 at compile time)
```

`invokestatic` doesn't need a receiver object at all — the method to run is fixed the moment the code compiles, based purely on the class name written at the call site. This is precisely why `Employee emp = null; Employee.printCount();` from §3 works without a `NullPointerException`: there's no object to dereference in the first place.

It's also the deeper reason a subclass can't truly **override** a static method the way it overrides an instance method (Chapter 16 will cover this fully) — a subclass can declare a static method with the identical signature, but because static calls resolve at compile time based on the declared class, not the actual runtime object, this is called **method hiding**, not overriding, and it behaves very differently from the dynamic dispatch instance methods get. This distinction is one of the more commonly tested "gotcha" interview topics once Inheritance enters the picture.

---

## 6. The Complete Picture — `static` as One Coherent Idea

This chapter is the last of three touchpoints for `static` in this handbook — here's the whole story assembled in one place:

| Member Kind | Belongs To | Memory / Mechanism | Covered In |
|---|---|---|---|
| Static variable | The class | Metaspace, one copy shared by all objects | Chapter 4 §4 |
| Static initializer block | The class | Runs once, at class load, folded into `<clinit>` | Chapter 6 §3.3, §6 |
| Static method | The class | No `this`, no implicit object; `invokestatic`, resolved at compile time | This chapter, §2–§5 |

The common thread across all three: **nothing here needs, or gets, an individual object** — everything static is a property of the class itself, loaded once, and shared.

---

## 7. Static Nested Classes — A Brief Preview

A class can itself be declared `static` when nested inside another class — meaning it doesn't hold an implicit reference to an instance of its enclosing class (unlike a regular inner class, which does). This is a distinct topic with its own compiler behavior (recall Chapter 2 §5.1's `Outer$Inner.class` naming), and gets full treatment in Chapter 23 (Nested Classes) — it's mentioned here only so `static` is recognized as applicable to classes too, not only fields, blocks, and methods.

---

## 8. Real-World Example

```java
public class Employee {

    static int employeeCount = 0;     // Chapter 4 §4

    static {                           // Chapter 6 §3.3
        System.out.println("Employee class loaded");
    }

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        employeeCount++;
    }

    static Employee createIntern(String name) {   // static factory method
        return new Employee(name, 30000);
    }
}
```

```java
Employee intern = Employee.createIntern("Rohan");   // no need to know the full constructor signature
System.out.println(Employee.employeeCount);          // 1 — accessed via class name, not an instance
```

`createIntern` is a **static factory method** — a static method that returns a new instance of the class, often used to give a constructor call a more descriptive name than `new Employee(...)` alone would provide. We'll meet this pattern again, formally, when this handbook reaches design patterns.

---

## 9. Best Practices

- Reserve `static` for behavior or state that is genuinely class-wide — not as a shortcut to avoid passing an object between methods.
- Always call static members through the class name, never through an object reference (§3) — it keeps the call site honest about what's actually happening.
- Use the private-constructor pattern (§4, Chapter 5 §8) for any class meant to hold only static utility methods.
- Be deliberate about static methods in a class hierarchy — since they're hidden, not overridden (§5), relying on polymorphic behavior from a static method will not do what an instance method would.

## 10. Common Mistakes

- ⚠️ Trying to access an instance field or call an instance method directly from a static method — the compiler rejects it outright (§2.2), because there is no guaranteed object.
- ⚠️ Calling a static method through an object reference and assuming it depends on that specific object — it never does (§3).
- ⚠️ Assuming a subclass's same-named static method overrides the parent's — it hides it, resolved at compile time by declared type, not the actual object (§5) — a subtly different, easy-to-get-wrong behavior once Chapter 15/16 are in play.
- ⚠️ Overusing `static` for convenience, which quietly makes code harder to test and reason about, since static state is shared and global by nature (echoing the exact "shared, unprotected data" problem Chapter 1 §1.3 identified in Procedural Programming).

## 11. Interview Perspective

**Frequently Asked**

- *"Why can't a static method access instance variables directly?"* — Because a static call has no associated object at all (§2.2) — the same underlying reason `this` doesn't exist in a static context (Chapter 8 §5).
- *"Can a static method be overridden?"* — No — it can be *hidden* by a same-named static method in a subclass, but that's resolved at compile time via `invokestatic` (§5), not dynamically like real overriding.
- *"What's the point of a static factory method over a constructor?"* — It can have a descriptive name a constructor can't (`createIntern` vs. an ambiguous `new Employee(...)` overload), and it isn't required to return a new object every time — useful for patterns like caching or singletons.

**Tricky Question**

- *"Does `Employee emp = null; emp.someStaticMethod();` throw a `NullPointerException`?"* — No. Because static calls resolve via `invokestatic` based on the declared type at compile time, not the actual object at runtime (§5), the fact that `emp` is `null` is irrelevant — the JVM never needs to dereference it.

**Common Misconception**

- Believing `static` is primarily about "shared memory" as an end in itself. It's really about **ownership** — some state and behavior conceptually belongs to the class as a whole, not to any one instance of it — and the shared-memory behavior (Chapter 4 §4) is simply the natural consequence of that ownership, not the reason `static` exists.

---

## 12. Summary

- A static method belongs to the class, is callable without any object, and cannot directly access instance members because it has no guaranteed object to act on.
- Static members should always be called through the class name; calling them through an object reference compiles but is misleading, since the call never actually depends on that object.
- Static utility classes combine static-only members with a private constructor (Chapter 5 §8) to prevent instantiation entirely.
- Static method calls compile to `invokestatic`, resolved entirely at compile time — this is why static methods are *hidden*, not *overridden*, by a subclass's same-named static method.
- `static` variables (Ch. 4), static blocks (Ch. 6), and static methods (this chapter) are all expressions of one idea: some state and behavior belongs to the class itself, not to any individual object.

## 13. Quick Revision

- Static method = class-level, no `this`, no direct instance access, callable without an object.
- Call static members via the class name, not an instance — both compile, only one is good practice.
- `invokestatic` = compile-time resolved, no receiver needed — this is why static methods are hidden, not overridden.
- Static utility class = all-static members + private constructor.
- Static variable (Ch4) + static block (Ch6) + static method (Ch9) = one coherent idea: belongs to the class, not the object.

## 14. Self Assessment

1. Explain, in terms of what a static method call actually needs at runtime, why it cannot access an instance field directly.
2. Why does calling a static method through a `null` object reference not throw a `NullPointerException`? Connect your answer to `invokestatic`.
3. Build a small static utility class with two static methods and a private constructor, and explain what the private constructor prevents.
4. What's the difference between a static method being "hidden" versus "overridden" by a subclass, and why does that distinction exist at the bytecode level?
5. Summarize, in three or four sentences, what static variables (Ch. 4), static blocks (Ch. 6), and static methods (this chapter) all have in common.

---

## What's Next

**Chapter 10 — `final`** covers the keyword that formalizes something this chapter and Chapter 8 both already relied on informally: Chapter 8 §6 noted `this` behaves like an implicit `final` reference, and this chapter's utility classes (§4) were marked `final` to block subclassing. Chapter 10 makes both of those precise, and covers `final` variables, methods, and classes as one unified topic.
