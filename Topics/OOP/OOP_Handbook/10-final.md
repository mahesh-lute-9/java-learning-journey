# Chapter 10 — `final`

**Part VI: Keywords**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Apply `final` correctly to variables, methods, and classes — three genuinely different meanings sharing one keyword.
- Explain precisely why a `final` reference variable does not make the object it points to immutable.
- Explain "blank final" fields, and the exact compiler rule that governs them.
- Explain what constant folding is, and why it can cause a subtle real-world bug across separately compiled code.
- Recognize when `final` is the right tool versus when it's being reached for out of habit.

---

## 1. Introduction

Two earlier chapters used `final` informally without naming it: Chapter 8 §6 noted that `this` behaves like an implicit `final` reference — usable, but never reassignable — and Chapter 9 §4 marked a static utility class `final` to block subclassing, without explaining why that worked. This chapter formalizes both, and covers the third, most common use: `final` fields.

> This chapter assumes Chapter 3's reference-vs-object distinction and Chapter 4's definite-assignment rule for local variables. It builds directly on both rather than re-explaining them.

---

## 2. Theory — `final` Means Something Different for Each Target

> **`final` prevents change — but *what* it prevents depends entirely on whether it's applied to a variable, a method, or a class.**

| Applied To | Prevents |
|---|---|
| Variable | Reassignment after its one initial assignment |
| Method | Being overridden by a subclass |
| Class | Being subclassed at all |

These are three distinct guarantees, not one idea in three places — worth holding separately, since conflating them is a common source of confusion (§9 covers the most frequent version of this mistake).

---

## 3. Final Variables

### 3.1 Local Variables

```java
final double taxRate = 0.2;
taxRate = 0.25;   // ✘ compile error — cannot assign a value to final variable taxRate
```

A `final` local variable can be assigned exactly once. This is a stricter version of Chapter 4 §5.1's definite-assignment rule: an ordinary local variable must be assigned *at least* once before use; a `final` one must be assigned *exactly* once, ever.

### 3.2 Blank Finals — Instance Variables Assigned in the Constructor

A `final` instance variable doesn't have to be assigned at its declaration — it can be left as a **blank final**, as long as the compiler can prove every constructor assigns it exactly once:

```java
public class Employee {
    private final String employeeId;   // blank final — no value here yet

    public Employee(String employeeId) {
        this.employeeId = employeeId;   // ✔ assigned exactly once, here
    }
}
```

If a class has multiple constructors (Chapter 5 §5), the compiler requires **every** constructor path to assign the blank final exactly once — including every branch of a `this(...)` chain:

```java
public class Employee {
    private final String employeeId;

    public Employee() {
        // ✘ compile error: variable employeeId might not have been initialized
    }

    public Employee(String employeeId) {
        this.employeeId = employeeId;   // this constructor is fine on its own...
    }
    // ...but the no-arg constructor above still doesn't satisfy it
}
```

### 3.3 Final Static Variables — Constants

Combining `static` and `final` is Java's way of declaring a true constant — one shared value, fixed forever, by convention named in `UPPER_SNAKE_CASE`:

```java
static final double TAX_RATE = 0.2;
```

### 3.4 The Trap: `final` on a Reference Does Not Mean Immutable

This is the single most commonly misunderstood fact about `final`, and it follows directly from Chapter 3 §3.2's reference-vs-object distinction:

```java
final List<String> skills = new ArrayList<>();
skills.add("Java");        // ✔ perfectly legal — the object is being mutated
skills.add("Spring");      // ✔ also legal

skills = new ArrayList<>(); // ✘ compile error — the reference itself cannot be reassigned
```

`final` only locks the **reference variable** — it guarantees `skills` will always point at the same object. It says nothing at all about whether that object's own internal state can change. Building a class whose objects genuinely cannot change after construction — true immutability — needs more than `final` fields alone; that's Chapter 28's dedicated subject, and this distinction is exactly why it needs its own chapter rather than being "just `final` fields."

---

## 4. Final Methods

A `final` method cannot be overridden by any subclass (full override mechanics: Chapter 16), though — and this is worth stating precisely, since it's a common wording trap — it **can** still be inherited and called normally; `final` blocks *overriding* it, not *inheriting* it:

```java
class SalaryUtils {
    final double applyStandardTax(double salary) {
        return salary * 0.8;
    }
}

class ContractorSalaryUtils extends SalaryUtils {
    // ✘ compile error if you try to redefine applyStandardTax here
}
```

This is used to lock down behavior a subclass must never be allowed to change — often because overriding it would break an invariant the class depends on internally. It's a different axis entirely from Chapter 9 §5's static method *hiding*: a `final` method is an ordinary, dynamically-dispatched instance method that simply cannot be replaced further down the hierarchy; a `static` method was never dynamically dispatched to begin with.

---

## 5. Final Classes

A `final` class cannot be subclassed at all — Chapter 9 §4 already used this, on a utility class, without explaining the mechanism:

```java
final class SalaryUtils {
    // no class can ever `extends SalaryUtils`
}
```

Java's own standard library uses this deliberately — `String` itself is a `final` class, specifically so its behavior (including the immutability guarantees Chapter 28 will cover in depth) can never be altered or subverted by a subclass overriding its methods.

---

## 6. Compiler Behaviour — Constant Folding

This is where `final` variables get genuinely interesting at the compiled-code level. When a `final` variable's value is a **compile-time constant** — a `static final` primitive or `String` initialized with a literal or a constant expression — the compiler doesn't just remember it exists; it **substitutes the literal value directly at every place the constant is used**, a technique called **constant folding**:

```java
static final double TAX_RATE = 0.2;

double net = salary * (1 - TAX_RATE);
```

compiles roughly as if you had written:

```java
double net = salary * (1 - 0.2);
```

**The real-world consequence:** if `TAX_RATE` lives in one compiled library and another already-compiled piece of code uses it, that other code has the literal `0.2` baked directly into its own bytecode — not a reference back to `TAX_RATE`. If the library later changes `TAX_RATE` to `0.22` and is redeployed **without recompiling the code that uses it**, that dependent code keeps using the stale `0.2` until it's recompiled itself. This is a genuine, documented Java gotcha, and understanding *why* it happens — constant folding, not a caching bug — is a strong signal of real compiler-level understanding in an interview.

---

## 7. Real-World Example

```java
public class Employee {

    static final double TAX_RATE = 0.2;      // constant — constant-folded at every use site

    private final String employeeId;          // blank final — set once, in the constructor
    private String name;
    private double salary;

    public Employee(String employeeId, String name, double salary) {
        this.employeeId = employeeId;          // satisfies the blank final requirement
        this.name = name;
        this.salary = salary;
    }

    final double calculateNetSalary() {        // final — subclasses cannot override this rule
        return salary * (1 - TAX_RATE);
    }
}
```

`employeeId` is guaranteed to be set exactly once and never change for the lifetime of the object — a natural fit for something that should function as a stable identity. `calculateNetSalary` being `final` guarantees that however this class is later extended, the tax calculation itself can never be silently overridden.

---

## 8. Best Practices

- Default to `final` for fields that genuinely shouldn't change after construction — it documents intent and lets the compiler catch accidental reassignment for you.
- Use `static final` with `UPPER_SNAKE_CASE` for true constants — this is a strong, widely recognized Java convention.
- Mark a class `final` when it isn't designed to be extended — it's a deliberate design decision, not a default.
- Don't rely on `final` alone to communicate "this object is immutable" — that requires the fuller design discussed in Chapter 28.

## 9. Common Mistakes

- ⚠️ Believing `final List<String> skills` means `skills`'s contents can't change — only the reference is locked (§3.4); the object itself is exactly as mutable as it would be without `final`.
- ⚠️ Missing a blank final assignment in one constructor overload out of several — the compiler catches this, but only per-constructor, so it's easy to fix one and forget a sibling (§3.2).
- ⚠️ Assuming a `final` method can't be called by a subclass — it can be inherited and called freely; only *overriding* it is blocked (§4).
- ⚠️ Not recompiling dependent code after changing a `public static final` constant in a shared library, and being confused why the old value persists (§6) — this is constant folding, not a bug in your build process.

## 10. Interview Perspective

**Frequently Asked**

- *"Does `final` make an object immutable?"* — No, not by itself. `final` on a reference variable only prevents reassigning that reference; the object it points to can still be mutated through its own methods, unless the object's *own design* prevents that (§3.4, full treatment Chapter 28).
- *"What is a blank final?"* — A `final` instance variable left unassigned at declaration, which the compiler requires to be assigned exactly once in every constructor (§3.2).
- *"Can a `final` method be inherited?"* — Yes — `final` only blocks overriding it, not inheriting and calling it normally (§4).

**Tricky Question**

- *"If a library's `public static final int VERSION = 1;` changes to `2` and the library is redeployed, but a dependent JAR isn't recompiled, what value does the dependent code see?"* — Still `1` — because constant folding (§6) baked the literal directly into the dependent code's own bytecode at compile time; it never actually reads `VERSION` at runtime.

**Common Misconception**

- Treating `final`, immutability, and constants as the same idea. They overlap but aren't identical: a `final` variable is just non-reassignable; a constant is a `static final` primitive/String, specifically subject to constant folding (§6); true object immutability (Chapter 28) requires `final` fields *plus* deliberate class design — `final` is a necessary ingredient, not a sufficient one.

---

## 11. Summary

- `final` means three different things depending on its target: a variable can't be reassigned, a method can't be overridden, a class can't be subclassed.
- A blank final instance variable must be assigned exactly once, across every constructor the compiler can trace.
- `final` on a reference variable locks the reference, not the object it points to — mutability of the object itself is a separate concern.
- `static final` constants with constant-expression values are constant-folded directly into every use site at compile time — which is why changing one requires recompiling dependent code, not just redeploying it.
- A `final` method can still be inherited and called; only overriding it is blocked.

## 12. Quick Revision

- `final` variable → assign once, never reassign. `final` method → can't be overridden (can still be inherited). `final` class → can't be subclassed.
- Blank final = unassigned at declaration, must be set exactly once in every constructor.
- `final` reference ≠ immutable object — only the reference is locked.
- Constant folding = `static final` constant values get baked into use sites at compile time; recompile dependents after changing one.

## 13. Self Assessment

1. Explain, precisely, why `final List<String> names = new ArrayList<>();` still allows `names.add("Asha");`.
2. A class has two constructors and a blank final field `id`. One constructor assigns it; the other doesn't. Does this compile? Why or why not?
3. What's the difference between a `final` method being "inherited" and being "overridden" — and which one does `final` actually block?
4. Explain constant folding, and describe a concrete scenario where it causes surprising behavior after a library update.
5. Is `final` sufficient, by itself, to make a class's objects immutable? What else does true immutability require?

---

## What's Next

**Chapter 11 — `super`** completes Part VI (Keywords). It formalizes the implicit parent-constructor call Chapter 5 §6 previewed, and the parent-class initialization step Chapter 6 §4.2 marked as "preview only, full detail deferred to Ch. 15" — laying the last piece of groundwork before Chapter 15 (Inheritance) can finally be written.
