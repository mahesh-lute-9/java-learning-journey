# Chapter 17 — Abstraction

**Part VIII: Core OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain precisely what an `abstract` class and an `abstract` method guarantee, and how that guarantee differs from ordinary overriding.
- Explain why `Employee` itself should become `abstract` — and what changes, concretely, when it does.
- Explain why an abstract method can never be `private`, `static`, or `final` — by connecting three earlier chapters' rules into one coherent answer.
- Explain why an abstract class can still have a constructor, even though it can never be instantiated directly.

---

## 1. Introduction

Chapter 16 ended by noting that "no plain, undifferentiated `Employee` may ever need to exist" once `Manager` and `Intern` are the classes actually used in practice. This chapter makes that observation concrete: it's the last of the Four Pillars, and the one that finally lets `Employee` declare *what every subclass must provide* without pretending to provide a generic answer itself.

> This chapter covers `abstract` classes and methods only. Interfaces — Java's other abstraction mechanism — are a distinct topic with their own rules and history, covered fully in Chapter 18.

---

## 2. Theory — What Abstraction Actually Adds

> **An `abstract` class cannot be instantiated directly, and may declare `abstract` methods — methods with no body at all — which every concrete subclass is *required* to implement.**

This is a meaningfully stronger guarantee than ordinary overriding (Chapter 15 §4). An ordinary overridable method already has a default implementation in the parent; a subclass *may* override it, but doesn't have to. An `abstract` method has no implementation to fall back on at all — providing one is not optional.

---

## 3. Making `Employee` Abstract

Does a "generic Employee, belonging to no specific role" genuinely need to exist as an object? In this handbook's domain, no — every real employee is a `Manager`, an `Intern`, or some other specific role, each calculating salary differently. `Employee` exists purely to describe what they all share:

```java
public abstract class Employee {

    protected String department;
    private final String employeeId;
    private double salary;

    protected Employee(String employeeId, double salary) {
        this.employeeId = employeeId;
        setSalary(salary);
    }

    public abstract double getSalary();   // no body — every subclass MUST supply one

    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative");
        this.salary = salary;
    }

    protected double getBaseSalary() {
        return salary;
    }
}
```

```java
Employee emp = new Employee("E001", 50000);   // ✘ compile error:
                                                //   Employee is abstract; cannot be instantiated
```

`new Manager(...)` and `new Intern(...)` remain completely unaffected — only direct instantiation of `Employee` itself is now forbidden.

---

## 4. Subclasses Must Implement Every Abstract Method — Or Become Abstract Themselves

```java
public class Manager extends Employee {
    protected Manager(String employeeId, double salary) {
        super(employeeId, salary);
    }

    @Override
    public double getSalary() {
        return getBaseSalary() + 5000;   // management bonus
    }
}
```

`Manager` is a **concrete** class — it can be instantiated — precisely because it implements every `abstract` method it inherited. If it left `getSalary()` unimplemented, the compiler would require `Manager` itself to be declared `abstract` too, propagating the same unfulfilled obligation one level further down the hierarchy, until some subclass finally provides it.

---

## 5. A Genuinely Common Point of Confusion: Constructors in Abstract Classes

If `Employee` can never be instantiated directly, why does it still have a constructor?

Because Chapter 11 §2–§3 already established the mechanism this answer depends on: **every subclass constructor calls `super(...)` before running its own body**, and `Employee`'s constructor is exactly what runs when `Manager`'s does. An abstract class's constructor is never invoked by `new` directly against *it* — but it's invoked constantly, indirectly, by every concrete subclass's `super(...)` call. This is also why abstract classes routinely have concrete fields and concrete methods alongside their abstract ones (`department`, `employeeId`, `setSalary`, `getBaseSalary` above) — an abstract class is not "pure contract," it's an ordinary class that also happens to declare some methods it deliberately leaves unfinished.

---

## 6. Why an Abstract Method Can Never Be `private`, `static`, or `final`

This rule looks arbitrary in isolation, but it's actually the direct, necessary consequence of three earlier chapters:

- **Not `private`** — Chapter 13 established `private` members aren't accessible outside their declaring class at all; a subclass couldn't even see, let alone override, a `private` abstract method, which would make "every subclass must implement this" impossible to enforce.
- **Not `static`** — Chapter 9 §5 and Chapter 16 §4.2 established that `static` methods have no vtable entry and don't participate in dynamic dispatch at all; `abstract` exists specifically to mandate an *overridden* implementation, and overriding is a purely instance-method concept.
- **Not `final`** — Chapter 10 §4 established that `final` explicitly blocks a method from ever being overridden; `abstract` explicitly requires it. The two are direct opposites of the same mechanism, applied to the same thing.

Put together: `abstract` requires exactly the machinery (visibility to subclasses, dynamic dispatch, overridability) that `private`, `static`, and `final` each individually remove. The compiler rejecting all three combinations isn't three unrelated rules — it's one rule, seen from three angles this handbook has already built separately.

---

## 7. JVM Internals

An abstract class's compiled file carries an `ACC_ABSTRACT` flag — the same flag family Chapter 13 §7 introduced for `ACC_PRIVATE`/`ACC_PROTECTED`/`ACC_PUBLIC` — which is what the compiler checks to reject a bare `new` against it. In the class's virtual method table (Chapter 16 §4.2), an abstract method's slot simply has no implementation to point to at all — the compiler's job is making sure no concrete class's vtable is ever left with such an empty slot, which is exactly what §4's "must implement or become abstract" rule enforces before the program can even compile.

---

## 8. Abstract Classes vs. Interfaces — A Preview

Java has a second abstraction mechanism, `interface`, historically closer to "100% contract, no implementation at all," though modern Java has blurred that line somewhat since Java 8's default methods. The core distinction worth holding onto until Chapter 18: an abstract class supports only single inheritance (Chapter 15 §5) like any class, while a class can implement *multiple* interfaces — making interfaces the tool of choice when a type needs to promise several unrelated contracts at once. Chapter 18 covers this fully; this chapter is only the abstract-class half of Java's abstraction story.

---

## 9. Real-World Example

```java
public abstract class Employee {
    protected String department;
    private final String employeeId;
    private double salary;

    protected Employee(String employeeId, double salary) {
        this.employeeId = employeeId;
        setSalary(salary);
    }

    public abstract double getSalary();          // contract — every subclass must define this

    public void setSalary(double salary) {         // concrete, shared, inherited as-is
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative");
        this.salary = salary;
    }

    protected double getBaseSalary() { return salary; }
}

public class Manager extends Employee {
    protected Manager(String employeeId, double salary) { super(employeeId, salary); }
    @Override
    public double getSalary() { return getBaseSalary() + 5000; }
}

public class Intern extends Employee {
    protected Intern(String employeeId, double salary) { super(employeeId, salary); }
    @Override
    public double getSalary() { return getBaseSalary() * 0.5; }
}
```

```java
Employee e1 = new Employee("E001", 50000);   // ✘ compile error — abstract
Employee e2 = new Manager("M001", 95000);     // ✔ concrete subclass
```

---

## 10. Best Practices

- Mark a class `abstract` when it represents a shared category that should never exist on its own — the compiler-enforced version of "this concept only makes sense specialized."
- Use `abstract` methods to force subclasses to supply behavior the base class genuinely has no sensible generic answer for — `getSalary()` here, since a generic "employee salary formula" doesn't exist independent of role.
- Keep genuinely shared logic (`setSalary`'s validation, `employeeId` assignment) concrete and inherited as-is — don't make a method `abstract` just because a subclass *could* theoretically want to override it; reserve `abstract` for behavior that must differ.

## 11. Common Mistakes

- ⚠️ Trying to instantiate an abstract class directly and being surprised by the compile error (§3) — it's deliberate, not a bug.
- ⚠️ Forgetting to implement an inherited abstract method in a concrete subclass, and not realizing the fix is either implementing it or marking that subclass `abstract` too (§4).
- ⚠️ Assuming an abstract class can't have a constructor or concrete methods — it can, and routinely does (§5); only *some* of its methods are left unimplemented, not all of it.
- ⚠️ Trying to combine `abstract` with `private`, `static`, or `final` and not understanding why the compiler rejects it — §6 gives the precise, connected reason for all three, not three separate arbitrary rules to memorize.

## 12. Interview Perspective

**Frequently Asked**

- *"What is an abstract class?"* — A class that cannot be instantiated directly and may declare abstract methods with no body, which every concrete subclass must implement (§2).
- *"Can an abstract class have a constructor?"* — Yes — it's invoked by every subclass's `super(...)` call (Chapter 11), even though `new` can never be used against the abstract class itself (§5).
- *"Why can't an abstract method be `private`, `static`, or `final`?"* — Because `abstract` fundamentally requires subclass visibility, dynamic dispatch, and mandatory overriding — the exact three things `private`, `static`, and `final` each individually remove (§6).

**Tricky Question**

- *"If `Manager extends Employee` and `Employee` has an abstract `getSalary()`, what happens if `Manager` doesn't implement it?"* — `Manager` fails to compile as a concrete class; it would itself need to be declared `abstract`, passing the same unfulfilled obligation to whatever eventually extends `Manager` (§4).

**Common Misconception**

- Believing an abstract class is "just an interface with extra steps," or that it can't do anything a normal class does. In reality it's an ordinary class in every respect — fields, constructors, concrete methods, all fully functional — that additionally reserves the right to leave specific methods undefined, forcing concrete subclasses to finish the job (§5).

---

## 13. Summary

- An `abstract` class cannot be instantiated directly; an `abstract` method has no body and must be implemented by every concrete subclass, or that subclass must itself be `abstract`.
- `Employee` becoming `abstract` reflects a real design fact: no generic, role-less employee should ever exist as an object in this domain.
- Abstract classes still have constructors (invoked via subclass `super(...)` calls) and can freely mix concrete and abstract members.
- An abstract method can never be `private`, `static`, or `final` — each of those would remove exactly the property (subclass visibility, dynamic dispatch, overridability) that `abstract` depends on.
- Abstract classes support only single inheritance, like any class; interfaces (Chapter 18) are Java's other, more flexible abstraction mechanism.

## 14. Quick Revision

- `abstract` class: no direct instantiation; may mix concrete and abstract members; still has constructors, invoked via subclass `super(...)`.
- `abstract` method: no body, mandatory override in every concrete subclass, or that subclass must also be `abstract`.
- Can't combine `abstract` with `private` (no subclass visibility), `static` (no dynamic dispatch), or `final` (blocks overriding entirely) — one reason, three angles.
- `ACC_ABSTRACT` class-file flag blocks `new` from ever validly targeting the class.

## 15. Self Assessment

1. Explain why `Employee` becoming `abstract` is a meaningful design decision, not just a syntax change — what real-world fact does it encode?
2. If `Manager extends Employee` doesn't implement `getSalary()`, what are the *two* ways to make the code compile again?
3. Why does it make sense for an abstract class to have a constructor, given that `new AbstractClass()` is never legal?
4. Give the precise, connected reason an abstract method cannot be `static`, tying it back to how dynamic dispatch works (Chapter 16).
5. What's the key structural difference between an abstract class and an interface, at the level covered in this chapter (full detail: Chapter 18)?

---

## What's Next

**Chapter 18 — Interface** completes Java's abstraction toolkit. It covers Java's second, more flexible abstraction mechanism — multiple interface implementation, default methods (added in Java 8), and precisely when to reach for an interface instead of an abstract class, closing out both Part VIII (Core OOP) and the Four Pillars this handbook began describing all the way back in Chapter 1 §1.8.
