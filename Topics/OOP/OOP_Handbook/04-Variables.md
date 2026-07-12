# Chapter 4 — Variables

**Part III: Variables**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Classify any variable in a Java program as instance, static, or local — correctly, on sight.
- Explain exactly where each kind of variable lives in memory, and connect that back to Chapter 2 (Metaspace) and Chapter 3 (Heap, Stack).
- Explain why local variables get no default value while instance and static variables do — and why the compiler treats that difference as an error, not a warning.
- Explain variable scope and lifetime precisely, including what "shadowing" means and why it happens.
- Use `var` (local variable type inference) correctly, and explain why it is not the same thing as dynamic typing.

> **Scope note:** This handbook's prerequisites assume you can already declare a variable and use Java's primitive types and literals (`int x = 5;`, `String s = "hi";`). This chapter does not re-teach that syntax — it covers something prerequisite knowledge doesn't: *where in memory a variable actually lives depends entirely on where in a class it's declared*, and that distinction is central to how Java programs behave and is heavily interview-tested.

---

## 1. Introduction

Not all variables in a Java program are the same kind of thing, even though they're declared with identical-looking syntax. `int count = 0;` means something different depending on whether that line sits inside a class body, marked `static`, or inside a method. This chapter formalizes that difference precisely — it's one of the fastest ways to separate "can write Java" from "understands Java" in an interview.

---

## 2. Theory — The Three Kinds of Variables

| | Instance Variable | Static Variable | Local Variable |
|---|---|---|---|
| Declared | Inside a class, outside any method | Inside a class, outside any method, with `static` | Inside a method, constructor, or block |
| Belongs to | Each individual object | The class itself | The method call currently executing |
| Memory location | Heap (inside the object, Chapter 3 §6) | Metaspace, alongside the class metadata (Chapter 2 §6.1) | Stack (inside the current method's stack frame) |
| Copies that exist | One per object | Exactly one, shared by every object of the class | One per active method call |
| Gets a default value? | Yes, automatically (Chapter 3 §4) | Yes, automatically | **No** — must be explicitly assigned before use |
| Lifetime | As long as the object is reachable | As long as the class is loaded (Chapter 39) | Only while that method call is on the stack |

A **parameter** is simply a special case of a local variable — one that's initialized automatically by the value passed in at the call site, rather than by an assignment statement you write yourself.

```java
public class Employee {

    private String name;          // instance variable — one copy per Employee object

    static int employeeCount = 0; // static variable — exactly one copy, shared by all Employees

    public void raiseSalary(double amount) {   // amount → parameter (a local variable)
        double bonus = amount * 0.1;           // bonus → local variable
        // ...
    }
}
```

---

## 3. Instance Variables

An instance variable is a field declared in a class body without `static`. Its values live **inside each object**, in the Instance Fields section of the object layout Chapter 3 §6 already introduced:

```
Employee object (Heap)
┌─────────────────────────────┐
│ Object Header                │
├─────────────────────────────┤
│ Instance Fields               │
│  name       → "Asha"         │   ← instance variable, this object's own copy
│  department → "Engineering"  │
└─────────────────────────────┘
```

Two `Employee` objects never share instance variable storage — each `new Employee(...)` allocates its own. Per Chapter 3 §4, every instance variable is automatically set to its type's default (`0`, `false`, `null`) the moment the object is allocated, before any constructor code runs — this chapter doesn't repeat that mechanism, only names it as the reason instance variables are never left holding garbage.

**Lifetime:** an instance variable lives exactly as long as its object does. Once an `Employee` object becomes unreachable and is garbage collected (Chapter 38), its instance variables cease to exist along with it.

---

## 4. Static Variables

A static variable is declared with the `static` keyword, and it belongs to the **class**, not to any individual object:

```java
class Employee {
    static int employeeCount = 0;
}
```

Rather than living inside every `Employee` object, a static variable's storage lives once, alongside the class's own metadata in **Metaspace** (Chapter 2 §6.1) — the same region that holds `Employee`'s method bytecode and constant pool. Every object of the class, and the class itself, shares that one copy:

```
Metaspace
┌──────────────────────────────────┐
│ Employee — class metadata         │
│  • method bytecode                │
│  • employeeCount = 3   ← ONE copy, shared by every Employee object
└──────────────────────────────────┘
```

If one `Employee` object increments `employeeCount`, every other reference to `Employee.employeeCount` — through any object, or through the class name directly — sees the updated value, because there's only ever one variable, not one per object.

Static variables also receive automatic default initialization, and their lifetime matches the class's own — they exist from class loading (Chapter 39) until the class is unloaded, which for most applications means the lifetime of the program itself. The full behavior of `static` — static methods, static blocks, when exactly a class initializes — gets its own dedicated treatment in Chapter 9; this chapter introduces static variables only far enough to place them correctly in Java's memory model alongside instance and local variables.

---

## 5. Local Variables

A local variable is declared inside a method, constructor, or any block (`{ }`) — including `for` loop counters and `if` block variables. It lives on the **Stack**, specifically inside the stack frame created for that particular method call, and it disappears the instant that method call returns:

```java
public double calculateBonus(double salary) {   // salary — parameter (local)
    double bonus = salary * 0.1;                 // bonus  — local variable
    return bonus;
}   // both salary and bonus cease to exist here — the stack frame is popped
```

### 5.1 The Rule Every Beginner Hits: No Default Value

This is the single sharpest difference between local variables and the other two kinds:

```java
public void printBonus() {
    double bonus;
    System.out.println(bonus);   // ✘ Compile error:
                                  //   variable bonus might not have been initialized
}
```

Unlike instance and static variables, **local variables are never automatically initialized.** The Java compiler performs *definite assignment analysis* — it tracks, at compile time, whether every possible execution path assigns a local variable before it's read, and refuses to compile the code if it can't prove that. This is a deliberate, compile-time safety net that instance and static variables don't get, precisely because local variables have no "object allocation moment" (Chapter 3 §4) to hook automatic initialization onto.

### 5.2 Scope — Where a Local Variable Is Visible

A local variable's **scope** is the block it's declared in, and nothing outside it:

```java
public void process() {
    int x = 10;
    if (x > 5) {
        int y = 20;      // y's scope: only this if-block
        System.out.println(x + y);   // ✔ fine — x is visible here too
    }
    System.out.println(y);   // ✘ compile error — y is out of scope here
}
```

### 5.3 Shadowing

A local variable (or a parameter) can share a name with an instance variable — the local one temporarily "shadows" the instance one within its own scope:

```java
class Employee {
    private double salary;

    public void setSalary(double salary) {   // parameter shadows the instance variable
        salary = salary;    // ✘ does nothing useful — assigns the parameter to itself
    }
}
```

Inside `setSalary`, the plain name `salary` always refers to the *parameter*, not the field — the field becomes temporarily inaccessible by its own name. Resolving this correctly (`this.salary = salary;`) is exactly what the `this` keyword is for, covered fully in Chapter 8; it's mentioned here only so the *cause* of the bug — shadowing — is clear before we get to the fix.

---

## 6. Putting It All Together — One Unified Memory Picture

```
                         Metaspace
                    ┌─────────────────────────┐
                    │ Employee — class metadata │
                    │  employeeCount = 2   ◄──── static variable, ONE copy
                    └─────────────────────────┘

Stack                                          Heap
┌──────────────────────────┐                  ┌──────────────────────────┐
│ calculateBonus(emp1) frame │                 │ emp1 → Employee object    │
│  salary (param) = 95000.0 │  reads/writes    │  name = "Asha"            │
│  bonus (local)  = 9500.0  │─────────────────►│  salary = 95000.0  ◄────── instance
└──────────────────────────┘                  │                    variable │
                                                └──────────────────────────┘
```

This single diagram is the destination this whole chapter has been building toward: three variable kinds, three distinct memory regions, each tying directly back to where classes live (Chapter 2) and where objects live (Chapter 3).

---

## 7. Local Variable Type Inference — `var`

Since Java 10, a local variable's type can be inferred by the compiler instead of written explicitly:

```java
var name = "Asha";           // compiler infers String
var salary = 95000.0;        // compiler infers double
var emp = new Employee();    // compiler infers Employee
```

`var` is **not** dynamic typing. The compiler determines the concrete type at compile time from the right-hand side and locks it in — `name` is exactly as strongly typed as if you'd written `String name = "Asha";`, it just saves you typing the type name. `var` is also restricted to local variables — it cannot be used for instance variables, static variables, or method parameters, which is a direct consequence of everything in this chapter: type inference needs an initializer expression to infer *from*, and only local variables are required to have one at the point of declaration.

---

## 8. Real-World Example

Extending the running `Employee` class with all three variable kinds in context:

```java
public class Employee {

    private String name;              // instance variable
    private double salary;            // instance variable
    static int employeeCount = 0;     // static variable — shared across all Employees

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        employeeCount++;              // every construction updates the one shared copy
    }

    public double calculateBonus(double performanceRating) {  // parameter (local)
        double bonus = salary * performanceRating * 0.1;      // local variable
        return bonus;
    }
}
```

`name` and `salary` exist once per `Employee` object. `employeeCount` exists exactly once, no matter how many `Employee` objects are created. `performanceRating` and `bonus` exist only for the duration of a single `calculateBonus(...)` call, then vanish.

---

## 9. Best Practices

- Keep instance variables `private` by default — this chapter is purely about memory and lifetime, not access control, but the habit starts here (full treatment in Chapter 12, Encapsulation).
- Give local variables the smallest scope that works — declare them as close as possible to where they're used, not all at the top of a method.
- Reserve `static` variables for genuinely class-wide state (like `employeeCount`) — not as a shortcut to avoid passing a value between methods.
- Use `var` when the right-hand side already makes the type obvious (`var list = new ArrayList<Employee>();`) and avoid it when it would hide the type from a reader (`var result = process();` — what does `process()` return?).

## 10. Common Mistakes

- ⚠️ Expecting a local variable to have a default value the way a field does — it doesn't, and the compiler will refuse to compile code that reads an unassigned one.
- ⚠️ Assigning a parameter to itself (`salary = salary;`) intending to update the field, without realizing the parameter is shadowing it (§5.3) — this compiles fine and silently does nothing.
- ⚠️ Treating a `static` variable as if each object had its own copy — mutating it through one object changes what every other object sees.
- ⚠️ Believing `var` means the variable's type can change later — it can't; the type is fixed at compile time from the initializer.

## 11. Interview Perspective

**Frequently Asked**

- *"Instance variable vs. static variable — what's the actual difference?"* — Not just "one needs `static`." The real answer is memory: instance variables live inside each object on the Heap (one copy per object); static variables live once in Metaspace alongside the class itself (one copy total, shared).
- *"Why don't local variables get default values?"* — Because there's no object-allocation moment (Chapter 3 §4) to hook automatic initialization onto, and Java deliberately makes uninitialized-local-variable-use a compile-time error via definite assignment analysis, rather than risk it becoming a silent runtime bug.
- *"Is `var` the same as JavaScript's `var` or Python's dynamic typing?"* — No — Java's `var` is resolved to a single, fixed, concrete type at compile time (§7); it's syntax sugar for the programmer, not a change to Java's static type system.

**Tricky Question**

- *"If two threads both call a method that increments a `static` counter, what could go wrong?"* — Because there's only one shared copy of a static variable (§4), concurrent updates from multiple threads can race and lose increments unless synchronized. This is a preview only — Java's concurrency model is outside this handbook's OOP scope, but recognizing *why* it's a risk (shared mutable state, §4) is exactly the kind of connection interviewers look for.

**Common Misconception**

- Believing a `static` variable belongs to "the first object created" or "a special object." It doesn't belong to any object at all — it belongs to the class, and exists even if zero objects of that class have ever been created (Chapter 2 §12 covered this exact idea for utility classes with only static members).

---

## 12. Summary

- Java has three kinds of variables — instance, static, and local — and they differ in far more than syntax: where they live in memory, how many copies exist, whether they get a default value, and how long they last.
- Instance variables live on the Heap, one copy per object. Static variables live once in Metaspace, shared by the whole class. Local variables live on the Stack, one copy per active method call, and disappear when that call returns.
- Only instance and static variables get automatic default initialization; local variables must be explicitly assigned before use, enforced by the compiler at compile time.
- A local variable or parameter can shadow an instance variable of the same name — the fix (`this.field`) is covered fully in Chapter 8.
- `var` is compile-time type inference for local variables only, not dynamic typing.

## 13. Quick Revision

- Instance variable → Heap, per-object, default-initialized. Static variable → Metaspace, per-class, default-initialized. Local variable → Stack, per-call, **not** default-initialized.
- Parameters are local variables initialized by the caller.
- Shadowing = a local name temporarily hides a field of the same name; `this.field` is the fix (Chapter 8).
- `var` infers a fixed type at compile time — it is not dynamic typing, and it's local-variable-only.

## 14. Self Assessment

1. For each of `name` (a field), `count` (a `static` field), and `total` (declared inside a method), state where it lives in memory and how many copies of it can exist at once.
2. Why does this code fail to compile, and what specific compiler check causes the failure?
   ```java
   void greet() {
       String message;
       System.out.println(message);
   }
   ```
3. If `Employee` has `static int employeeCount`, and you create five `Employee` objects, how many separate `employeeCount` variables exist in memory?
4. Explain shadowing using a method parameter with the same name as an instance field, and describe (without writing the full `this` syntax, which is Chapter 8's topic) what problem it causes.
5. Is `var total = 0;` the same as declaring `total` with `Object total = 0;`? Why or why not?

---

## What's Next

**Chapter 5 — Constructors** picks up directly from §2 and §3 of this chapter — a constructor's entire job is assigning values to an object's instance variables at the moment it's created, filling in the piece Chapter 3 §4 deliberately left as a black box ("Constructor Execution") and this chapter set up the memory model for. We'll also revisit `this` informally there before it gets its own full chapter (8), specifically to resolve the shadowing problem from §5.3.
