# Chapter 13 — Access Modifiers

**Part VII: Object Design**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Name all four Java access levels precisely, including the one with no keyword at all.
- State exactly what each level exposes — same class, same package, subclass, or everywhere.
- Explain `protected`'s genuinely subtle cross-package subclass rule — one of the most commonly misunderstood access rules in Java.
- Apply the principle of least privilege when choosing access levels, rather than defaulting to `public` out of convenience.

---

## 1. Introduction

Chapter 12 used `private` and `public` informally, exactly enough to demonstrate Encapsulation. This chapter formalizes the complete system: Java actually has **four** access levels, not two — and the one most learners forget even exists is, unhelpfully, the one you get by writing nothing at all.

> This chapter covers member-level access (fields, methods, constructors). Chapter 2 §4.2 already established that a *top-level class* may only be `public` or package-private — that rule isn't repeated here, only referenced.

---

## 2. The Four Access Levels

| Modifier | Same Class | Same Package | Subclass (different package) | Everywhere |
|---|:---:|:---:|:---:|:---:|
| `private` | ✔ | ✘ | ✘ | ✘ |
| *(no modifier — package-private)* | ✔ | ✔ | ✘ | ✘ |
| `protected` | ✔ | ✔ | ✔ (with a caveat, §5) | ✘ |
| `public` | ✔ | ✔ | ✔ | ✔ |

Each level strictly widens the one before it — `private` is the most restrictive, `public` the least.

---

## 3. `private` — Same Class Only

```java
public class Employee {
    private double salary;   // only Employee's own code can touch this
}
```

Exactly what Chapter 12 already used: accessible only within the declaring class itself — not even a subclass can reach it directly (a subclass reaches inherited state through inherited methods, not by touching a `private` field by name). This is the right default for genuine implementation details, per Chapter 12's central point: fields should be `private` unless there's a specific reason to widen them.

---

## 4. Package-Private — the Modifier You Get by Writing Nothing

```java
class Department {   // no modifier at all → package-private
    String name;      // also package-private
}
```

Writing *no* access modifier doesn't mean "private" — it produces Java's actual default, **package-private** (sometimes called *default access*): accessible from anywhere in the same package, but invisible outside it. This is genuinely one of the most common things to get wrong in an interview — "no modifier" and "`private`" sound related but mean very different things; package-private is strictly *more* open than `private`, not the same as it.

Chapter 4 §4's `static int employeeCount = 0;` on the running `Employee` class, and Chapter 2's `class Department` example, were both, technically, package-private the entire time — this chapter is simply the first to name that level explicitly.

---

## 5. `protected` — Same Package, Plus a Genuinely Subtle Subclass Rule

`protected` starts as package-private access, plus one addition: a subclass in a **different** package can also reach it. So far this sounds simple — but the actual rule is narrower than most learners assume, and this is real interview-differentiating territory.

```java
// package pkgA
public class Vehicle {
    protected String make;
}

// package pkgB
public class Car extends Vehicle {
    void inspect(Vehicle v, Car c) {
        System.out.println(v.make);      // ✘ compile error
        System.out.println(c.make);      // ✔ compiles
        System.out.println(this.make);   // ✔ compiles
    }
}
```

**The rule:** a subclass in a different package can access an inherited `protected` member only through a reference typed as the subclass itself (or one of its own subtypes) — `c.make` and `this.make` both work because `c` and `this` are `Car`-typed. It **cannot** access that same member through a reference typed as the parent class, even if that reference happens to point at a `Car` object at runtime — `v.make` fails to compile, regardless of what `v` actually refers to, because the compiler only looks at `v`'s *declared* type, `Vehicle`.

**Why does this restriction exist at all?** Without it, `Car` could reach into a *sibling* subclass's inherited `make` field through a `Vehicle`-typed reference — for example, some other class `Truck extends Vehicle` in yet another package — which would let `protected` leak access between unrelated subclasses that merely happen to share a parent, defeating the entire point of restricting it to "your own inheritance line." The rule confines `protected` cross-package access to exactly this: your own subclass reaching its own inherited state, nothing broader.

---

## 6. `public` — Everywhere

```java
public double getSalary() { return salary; }
```

No restriction at all — accessible from any class, in any package, with no relationship to the declaring class required. Chapter 12 §8 already established the guiding principle here: reach for `public` only when a member is genuinely meant to be part of a class's external contract, not as a default chosen to avoid deciding.

---

## 7. Compiler and Bytecode Behaviour

Each access level is stored as a literal flag on the corresponding entry in a compiled class's `field_info` or `method_info` structure — `ACC_PRIVATE`, `ACC_PROTECTED`, `ACC_PUBLIC`, or, for package-private, the simple absence of any of those flags. This is the concrete form Chapter 12 §6's "two-layer enforcement" checks against: both the compiler and the JVM's bytecode verifier are reading these exact flags — the compiler while producing and consuming `.class` files during compilation, the verifier while independently re-checking access at class-loading time.

---

## 8. Real-World Example

```java
public class Employee {

    private final String employeeId;    // Ch. 12 — true implementation detail, no one else needs it
    protected String department;         // visible to a future subclass like Manager (Ch. 15), across packages
    String team;                          // package-private — visible within this codebase's package, nowhere else
    private double salary;               // Ch. 12 — validated via public accessors only

    public Employee(String employeeId, double salary) {
        this.employeeId = employeeId;
        setSalary(salary);
    }

    public double getSalary() { return salary; }   // public — part of Employee's external contract

    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative");
        this.salary = salary;
    }
}
```

`department` being `protected` here is deliberate groundwork: Chapter 15 will introduce `Manager extends Employee`, and `protected` is exactly the access level that lets `Manager` — even from a different package — reach `department` directly on its own inherited state, following §5's rule precisely.

---

## 9. Best Practices

- Default to the most restrictive access that still works: try `private` first, widen only when there's a concrete reason — this is the *principle of least privilege*, and it's a real, name-worthy design principle, not just a Java convention.
- Reserve `protected` specifically for members a subclass genuinely needs to inherit and use directly — not as a slightly-more-open alternative to package-private chosen out of uncertainty.
- Don't default to `public` for convenience — every `public` member becomes part of a class's permanent external contract, and Chapter 12 §3.1 already covered why that's costly to walk back later.

## 10. Common Mistakes

- ⚠️ Assuming no modifier means `private` — it means package-private, which is strictly more open (§4).
- ⚠️ Assuming a subclass in another package can access an inherited `protected` member through *any* reference of the parent's type — it can't; only through the subclass's own type or subtype (§5).
- ⚠️ Reaching for `protected` as a default "just in case a subclass needs it" — it's a deliberate design commitment, not a safety net.
- ⚠️ Marking everything `public` to avoid thinking about access levels — this directly undermines the encapsulation discipline Chapter 12 built.

## 11. Interview Perspective

**Frequently Asked**

- *"What's the default access level if no modifier is written?"* — Package-private: accessible anywhere in the same package, nowhere outside it (§4). Frequently misstated as "private."
- *"What's the difference between `protected` and package-private?"* — `protected` adds one thing package-private doesn't have: access from a subclass in a *different* package — but only through a reference of the subclass's own type (§5), not the parent's.
- *"Can a top-level class be `protected` or `private`?"* — No — only `public` or package-private, established already in Chapter 2 §4.2.

**Tricky Question**

- *"Given `Car extends Vehicle` in a different package, why does `car.make` compile inside `Car` but `vehicle.make` doesn't, even if `vehicle` refers to the exact same `Car` object at runtime?"* — Because `protected` cross-package access is checked against the reference's **declared** type at compile time, not the object's actual runtime type (§5) — this is precisely the rule that prevents `protected` from leaking access between unrelated sibling subclasses.

**Common Misconception**

- Believing `protected` roughly means "package-private, plus subclasses can use it freely, wherever they are." The actual rule is narrower and more deliberate (§5) — cross-package subclass access is real, but scoped tightly enough that it can't be used as a backdoor into a sibling subclass's inherited state.

---

## 12. Summary

- Java has four access levels — `private`, package-private (no modifier), `protected`, `public` — each strictly widening the one before it.
- Package-private is the actual default when no modifier is written, and is more open than `private`, not equivalent to it.
- `protected` grants same-package access plus subclass access across packages, but only through a reference typed as the subclass itself, not the parent — a deliberate restriction that prevents access leaking between sibling subclasses.
- Access levels are stored as literal flags (`ACC_PRIVATE`, `ACC_PROTECTED`, `ACC_PUBLIC`, or none) on each class member in the compiled `.class` file, which is exactly what Chapter 12's two-layer enforcement checks against.
- Choosing access levels should follow the principle of least privilege: start restrictive, widen only with a concrete reason.

## 13. Quick Revision

- Four levels, strictly nested: `private` ⊂ package-private ⊂ `protected` ⊂ `public`.
- No modifier = package-private, not private.
- `protected` cross-package subclass access only works through the subclass's own type — never through a parent-typed reference.
- Stored as `ACC_*` flags in the compiled class file.
- Default to the most restrictive access that works.

## 14. Self Assessment

1. List all four access levels from most to least restrictive, and state exactly what each one exposes.
2. Why is "no modifier" not the same as `private`? What's the practical difference?
3. Given `Car extends Vehicle` in different packages, write one line of code that compiles when accessing an inherited `protected` field, and one that doesn't — and explain the difference.
4. Why is `protected`'s cross-package subclass access restricted to the subclass's own type, rather than allowing access through any `Vehicle`-typed reference?
5. Apply the principle of least privilege to a `Product` class with fields `id`, `internalCacheKey`, and `price` — what access level would you choose for each, and why?

---

## What's Next

**Chapter 14 — Packages** completes Part VII (Object Design). It formalizes what this chapter's "same package" boundary actually means mechanically — package declarations, imports, and how packages map to directory structure — filling in the mechanism every access-level comparison in this chapter has quietly assumed.
