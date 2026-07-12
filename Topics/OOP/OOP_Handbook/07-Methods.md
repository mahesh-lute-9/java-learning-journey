# Chapter 7 — Methods

**Part V: Methods**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Define a method's signature precisely, and explain why return type is excluded from it.
- Resolve, with certainty, Java's actual parameter-passing rule — including why "pass by reference" is the wrong way to describe what happens with objects.
- Overload methods correctly, and explain why overload resolution happens at compile time.
- Connect method calls back to Chapter 4's Stack model — what a "stack frame" actually is.
- Avoid the single most common parameter-passing mistake in Java interviews.

---

## 1. Introduction

Part IV was entirely about *building* an object correctly. This chapter starts Part V, which is about giving that object something to *do*. A method is how an object exposes behavior — the "verbs" half of the state-plus-behavior definition Chapter 1 §1.5 introduced, and the mechanism behind the "message passing" characteristic Chapter 1 §1.7 named but didn't detail.

> This chapter assumes you can already write a basic method (per this handbook's prerequisites). It focuses on what's specific to methods as class members: signatures, overload resolution, and — the topic this chapter spends the most time on — exactly how Java passes arguments, because getting this wrong is one of the most common sources of real bugs and failed interview questions alike.

---

## 2. Theory — Method Signature, Precisely

> **A method's signature is its name plus the number, types, and order of its parameters — and nothing else.**

```java
public double calculateBonus(double rating) { ... }
//     ^^^^^^   ^^^^^^^^^^^^^ ^^^^^^^^^^^^^
//   return    method name    parameter list
//    type                    (this + the name make up the signature)
```

The **return type is deliberately not part of the signature.** This single fact explains a rule every Java developer eventually trips on:

```java
public int getValue() { return 1; }
public double getValue() { return 1.0; }   // ✘ compile error — duplicate method
```

Both methods have the identical signature `getValue()` — the compiler has no way to choose between them based on return type alone at a call site like `getValue();`, so Java forbids it outright.

---

## 3. Method Overloading

**Overloading** means multiple methods in the same class share a name but differ in their parameter list — Chapter 5 §5 already used this exact mechanism for constructors; ordinary methods follow the identical rule:

```java
public void raiseSalary(double amount) {
    this.salary += amount;
}

public void raiseSalary(double amount, String reason) {
    this.salary += amount;
    logReason(reason);
}
```

At a call site, the compiler picks the matching overload by comparing the arguments you pass against each candidate's parameter list — this happens entirely **at compile time**, based on the declared types of the arguments, before the program ever runs. (This is sometimes called *static binding* or *compile-time polymorphism* — a term worth recognizing, though the full contrast with *runtime* polymorphism, where the decision happens during execution instead, is Chapter 16's subject, not this one's.)

Overload resolution can occasionally surprise you when multiple overloads could technically match — Java prefers an exact type match first, then a widening conversion (`int` → `double`), then autoboxing (`int` → `Integer`), and only then a varargs match, in that preference order. In practice, keeping overloads clearly distinct in intent avoids ever needing to reason through that precedence.

---

## 4. Parameter Passing — Java's One Actual Rule

This is the single most consequential idea in this chapter, and the most commonly misstated in interviews.

> **Java is always pass-by-value. There is no exception, ever — not even for objects.**

The confusion comes from what "the value" means for a reference type. Recall Chapter 3 §3.2: a variable of a class type doesn't hold the object itself — it holds a *reference* to it. When you pass that variable as an argument, Java copies **the reference's value** into the parameter. Both the caller's variable and the method's parameter now point at the *same* object — but they are two independent variables, each holding its own copy of that reference.

### 4.1 Mutating Through a Reference Parameter — Visible to the Caller

```java
public void raiseSalary(Employee e) {
    e.salary += 5000;   // modifies the object emp and e both point at
}

Employee emp = new Employee("Asha", 95000);
raiseSalary(emp);
System.out.println(emp.salary);   // 100000.0 — the caller sees the change
```

```
Caller's Stack Frame              Heap
┌────────────┐
│ emp   ●─────┼──────┐            ┌──────────────────────┐
└────────────┘       │            │ Employee object        │
                      ├───────────►│  salary = 100000.0    │
raiseSalary's Frame   │            └──────────────────────┘
┌────────────┐        │
│ e     ●─────┼───────┘
└────────────┘
```

Both `emp` and `e` are separate reference variables, but they point at the same object — exactly the aliasing relationship Chapter 3 §7 described. Mutating fields through `e` is visible through `emp`, because there's only one object.

### 4.2 Reassigning a Reference Parameter — Never Visible to the Caller

```java
public void replace(Employee e) {
    e = new Employee("Rohan", 50000);   // e now points somewhere new — emp does not
}

Employee emp = new Employee("Asha", 95000);
replace(emp);
System.out.println(emp.getName());   // "Asha" — completely unaffected
```

```
Caller's Stack Frame              Heap
┌────────────┐                    ┌──────────────────────┐
│ emp   ●─────┼───────────────────►│ Employee("Asha")      │
└────────────┘                    └──────────────────────┘

replace's Frame (after e = new Employee(...))
┌────────────┐                    ┌──────────────────────┐
│ e     ●─────┼───────────────────►│ Employee("Rohan")      │
└────────────┘                    └──────────────────────┘
```

`e = new Employee(...)` only changes what the local variable `e` points to — it has no way to reach back into the caller's `emp` and redirect it, because `e` was only ever a *copy* of the reference `emp` held, not `emp` itself. This is the exact scenario Chapter 3 §14 flagged as a "tricky question" and deferred here — Java copies the reference's value into the parameter; it never hands the method a way to alter the caller's variable itself.

**The rule that resolves both examples at once:** you can always mutate the object a reference parameter points to; you can never make the caller's own variable point somewhere else.

---

## 5. Return Values

A method with a non-`void` return type must return exactly one value of that type via `return`; a `void` method performs an action without producing one. Java has no way to `return` two values directly — the common pattern when a method genuinely needs to hand back multiple pieces of data is to return a single object that holds them (an array, a small dedicated class, or — once we reach Chapter 26 — a `record`).

```java
public double calculateAnnualSalary() {
    return salary * 12;    // single double value returned
}
```

---

## 6. JVM Internals — Methods and the Stack

Every method call gets its own **stack frame**, pushed onto the calling thread's Stack the moment the call begins and popped the moment it returns — this is precisely the mechanism Chapter 4 §5 described for where local variables and parameters live, now connected to the method call that creates it:

```
Stack (grows downward with each call)
┌───────────────────────────┐
│ raiseSalary(emp) frame     │  ← parameter e, any locals inside raiseSalary
├───────────────────────────┤
│ main() frame                │  ← local variable emp
└───────────────────────────┘
```

When `raiseSalary` returns, its entire frame — parameters and locals alike — is popped and discarded; only side effects made through references to Heap objects (§4.1) outlive the call. This is also why deeply recursive methods can exhaust the Stack (`StackOverflowError`) — each recursive call adds another frame, and frames aren't reclaimed until their call returns.

At the bytecode level, a call to an instance method like `emp.raiseSalary(5000)` compiles to `invokevirtual` — "virtual" because, as we'll see fully once Polymorphism (Chapter 16) and Method Dispatch (Chapter 40) are in scope, the JVM doesn't finally decide *which* version of the method to run until runtime, based on the object's actual class. For now, that's a preview label only — every method in this handbook so far has exactly one implementation, so there's nothing to dispatch between yet.

---

## 7. Real-World Example

```java
public class Employee {

    private String name;
    private double salary;

    public double calculateAnnualSalary() {
        return salary * 12;
    }

    public void raiseSalary(double amount) {
        this.salary += amount;
    }

    public void raiseSalary(double amount, String reason) {   // overload
        this.salary += amount;
        System.out.println(name + "'s raise reason: " + reason);
    }
}
```

```java
Employee emp = new Employee("Asha", 95000);
emp.raiseSalary(5000);                          // resolves to the first overload
emp.raiseSalary(5000, "Annual review");         // resolves to the second — chosen at compile time
```

---

## 8. Best Practices

- Give methods a single, clear responsibility, and name them as verbs describing exactly that (`calculateAnnualSalary`, not `salaryStuff`).
- Keep overloads genuinely related in purpose — if two same-named methods do conceptually different things, distinct names are clearer than an overload.
- Don't mutate an object passed as a parameter unless that's the documented, expected behavior — §4.1 showed that callers will see it, so silent mutation is a common source of hard-to-trace bugs.
- Prefer returning a value over mutating a parameter to communicate a result, where either is reasonably possible — it's easier for a caller to reason about.

## 9. Common Mistakes

- ⚠️ Believing Java passes objects "by reference," and expecting `e = new Employee(...)` inside a method to change the caller's variable — it never does (§4.2).
- ⚠️ Trying to overload two methods that differ only by return type — the signature (§2) doesn't include return type, so this is a compile error.
- ⚠️ Mutating a parameter's object unexpectedly and being surprised the caller sees the change — this is always intentional-looking to the compiler, even when it wasn't intended by the developer (§4.1).
- ⚠️ Confusing **overloading** (same name, different parameter list, resolved at compile time — this chapter) with **overriding** (same signature, subclass provides its own implementation, resolved at runtime — Chapter 16). They solve different problems and are frequently mixed up under interview pressure.

## 10. Interview Perspective

**Frequently Asked**

- *"Is Java pass-by-value or pass-by-reference?"* — Always pass-by-value. For objects, the value being passed is a reference — so you can mutate the object through it, but reassigning the parameter never affects the caller's variable. Walk through §4.1 and §4.2 as the definitive proof, not just the rule.
- *"What is a method signature, exactly?"* — Name plus parameter types/count/order. Return type is explicitly excluded (§2) — this single fact answers most "why won't this overload compile" questions.
- *"How does Java decide which overload to call?"* — Entirely at compile time, based on the declared types of the arguments at the call site (§3) — not at runtime, and not based on what type the object "actually is" (that's Chapter 16's concern, for overriding, not overloading).

**Tricky Question**

- *"If a method reassigns its object parameter to `null`, does the caller's variable become `null` too?"* — No, for the exact reason in §4.2: the parameter is a separate copy of the reference. Setting your own copy to `null` only means *your copy* no longer points anywhere; the caller's variable is untouched.

**Common Misconception**

- Treating "pass by value" and "pass by reference" as if Java supports both depending on the type. It doesn't — Java has exactly one parameter-passing rule, always. What changes is *what* the value is: a copied primitive, or a copied reference. Internalizing this as one rule, not two, is what makes §4's two examples make sense together instead of looking contradictory.

---

## 11. Summary

- A method's signature is its name plus parameter types — return type is not part of it, which is why you cannot overload by return type alone.
- Java is always pass-by-value; for object parameters, the value passed is the reference itself, which is why mutating through a parameter is visible to the caller, but reassigning the parameter is not.
- Overload resolution happens entirely at compile time, based on the declared argument types at the call site.
- Every method call gets its own stack frame, holding its parameters and local variables, popped when the call returns.

## 12. Quick Revision

- Signature = name + parameter types. No return type involved.
- Pass-by-value, always. Object parameter = a copied reference: mutate the object → caller sees it; reassign the parameter → caller doesn't.
- Overloading = compile-time, resolved by parameter list. Overriding (Chapter 16) = runtime, resolved by actual object type.
- Every call = a new stack frame; it's popped on return.

## 13. Self Assessment

1. Explain, precisely, why `int getValue()` and `double getValue()` cannot coexist in the same class.
2. Write a method that mutates a `List` passed as a parameter (e.g., adds an element) and explain why the caller sees that change, using the reference model from §4.
3. Write a method that reassigns its object parameter and explain, using a diagram like §4.2's, why the caller's original variable is untouched.
4. A colleague insists "primitives are pass-by-value, but objects are pass-by-reference." Correct this statement precisely, in terms this chapter actually established.
5. What's the practical difference between method overloading and method overriding, even though this chapter only fully covers the former?

---

## What's Next

**Chapter 8 — `this`** picks up directly from §4's reference model and Chapter 4 §5.3's shadowing problem: `this` is the reference every instance method and constructor implicitly receives, pointing at the specific object the call was made on — and it's exactly what resolves the `salary = salary;` shadowing bug Chapter 4 left unfixed.
