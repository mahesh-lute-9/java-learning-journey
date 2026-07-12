# Chapter 5 — Constructors

**Part IV: Object Construction**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain precisely what a constructor is, and why it has no return type — not even `void`.
- Explain what the compiler actually does when a class defines no constructor at all, and why that stops the moment you add one yourself.
- Overload constructors correctly, and chain them using `this(...)` to eliminate duplicated initialization logic.
- Explain the full, ordered sequence from `new` to a fully initialized object, connecting this chapter to Chapter 3's memory-allocation steps.
- Recognize and avoid the constructor mistakes that show up most often in interviews and real code review.

---

## 1. Introduction

Chapter 3 §4 walked through five steps the JVM performs for `new Employee(...)`, and deliberately left step 4 — **Constructor Execution** — as a black box. This chapter opens that box.

> This chapter assumes Chapter 3's memory-allocation steps (allocation, default initialization) and Chapter 4's instance-variable model. It does not re-explain either — it picks up exactly where step 4 begins.

---

## 2. Why This Concept Exists

An object should never exist in an invalid, half-set-up state. Java could have required every object to be created empty and then patched together with a series of setter calls — but that leaves a window where the object exists yet isn't properly initialized, and no guarantee that anyone remembers to call every setter. A **constructor** closes that window: it's code that is guaranteed to run, automatically, at the exact moment an object comes into existence, before any other code can get a reference to it.

---

## 3. Theory — What Exactly Is a Constructor?

> **A constructor is a special class member, with the exact same name as the class, that has no return type at all, whose job is to initialize a newly allocated object's instance variables.**

```java
public class Employee {

    private String name;
    private double salary;

    public Employee(String name, double salary) {   // constructor
        this.name = name;
        this.salary = salary;
    }
}
```

### 3.1 Why No Return Type — Not Even `void`

A method's return type describes what value it produces when called. A constructor doesn't produce a value in that sense — by the time it runs, the object already exists. Recall Chapter 3 §4: memory allocation (step 1) and default initialization (step 2) happen *before* the constructor body ever executes. The constructor's entire job is step 4 — filling in that already-allocated, already-defaulted memory with real values. There's nothing for it to "return"; `new` itself is what returns the reference (step 5), not the constructor. Writing a return type — even `void` — turns the member into an ordinary method that merely happens to share the class's name, not a constructor at all (§8 covers this exact mistake).

### 3.2 Constructor vs. Method

| | Constructor | Method |
|---|---|---|
| Name | Must exactly match the class name | Any valid identifier |
| Return type | None — not even `void` | Required (or `void`) |
| Called | Automatically, once, by `new` | Explicitly, any number of times, on an existing object |
| Purpose | Initialize a new object | Perform some behavior on an existing object |
| Inherited? | No (Chapter 15 covers why) | Yes, generally |

---

## 4. The Default Constructor — and the Rule That Trips Everyone Up

If a class defines **no constructor at all**, the compiler automatically inserts a no-argument constructor for you — this is called the **default constructor**:

```java
class Employee {
    private String name;
}
// Compiler behaves as if you'd written:
class Employee {
    private String name;
    public Employee() { }   // inserted automatically
}
```

This is why `new Employee()` compiles even though you never wrote a constructor — Chapter 2 §4.1's "minimal valid class" relies on exactly this.

**The rule that catches almost everyone at least once:** the compiler only provides this free default constructor if you define **zero** constructors yourself. The moment you write *any* constructor — even a parameterized one only — the automatic default constructor disappears entirely:

```java
class Employee {
    private String name;

    public Employee(String name) {   // you defined one constructor...
        this.name = name;
    }
}

new Employee();          // ✘ compile error — no-arg constructor no longer exists
new Employee("Asha");    // ✔ fine
```

If you still want a no-arg constructor *and* a parameterized one, you must write both explicitly (§5 shows the clean way to do this without duplicating logic).

---

## 5. Constructor Overloading and Chaining

A class may define multiple constructors, as long as their parameter lists differ — this is **constructor overloading**, resolved by the compiler at compile time based on the arguments you pass:

```java
public class Employee {
    private String name;
    private String department;
    private double salary;

    public Employee(String name) {
        this(name, "Unassigned", 0.0);   // delegates to the constructor below
    }

    public Employee(String name, String department) {
        this(name, department, 0.0);     // delegates too
    }

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}
```

`this(...)` calls **another constructor in the same class** — this is **constructor chaining**, and it exists specifically so initialization logic lives in exactly one place (the most specific constructor) instead of being copy-pasted across every overload. Two hard rules govern it:

- `this(...)` must be the **first statement** in the constructor — the compiler rejects anything else before it, because delegation has to happen before any of *this* constructor's own logic runs.
- A constructor cannot chain to itself, directly or through a cycle (`A() calls this via B()`, `B() calls this via A()`) — the compiler detects this and rejects it, since it would never terminate.

```
new Employee("Asha")
        │
        ▼
Employee(String name)              ── this(name, "Unassigned", 0.0)
        │
        ▼
Employee(String, String)           ── this(name, department, 0.0)
        │
        ▼
Employee(String, String, double)   ── actually assigns the fields
```

Only the last constructor in the chain does real work; the others exist purely to supply sensible defaults for the arguments you didn't provide.

---

## 6. `this()` and the (Preview of) Implicit `super()`

Every constructor's first statement is either an explicit `this(...)` call, an explicit `super(...)` call, or — if you write neither — an **implicit** `super()` call the compiler inserts silently on your behalf. `super()` invokes the parent class's constructor, and it's part of why constructing an object of a subclass always constructs its entire chain of ancestors first. Inheritance hasn't been covered yet (Chapter 15), so this is deliberately just a preview: for now, the only thing to retain is that **`this(...)` and `super(...)` are mutually exclusive** — a constructor can start with one or the other, never both, since a constructor call is only allowed to delegate in one direction at a time.

---

## 7. The Copy Constructor Pattern

Java has no built-in copy constructor the way C++ does, but the same idea is a common, well-understood pattern: a constructor that takes another object of the same class and copies its state.

```java
public Employee(Employee other) {
    this.name = other.name;
    this.department = other.department;
    this.salary = other.salary;
}
```

```java
Employee original = new Employee("Asha", "Engineering", 95000);
Employee copy = new Employee(original);   // a genuinely separate object

copy.raiseSalary(5000);
// original.getSalary() is unaffected — these are two distinct Heap objects (Chapter 3 §7),
// not two references aliasing one
```

One caveat worth flagging now, in full only in Chapter 29 (Object Cloning): this copies each field's *value*. For a reference-type field, that copies the reference itself, not the object it points to — so two `Employee` copies could still end up aliasing the same inner object if a field were, say, a `List`. That distinction (shallow vs. deep copy) is Chapter 29's topic; this chapter only needs you to recognize a copy constructor when you see one.

---

## 8. Private Constructors

A constructor can be `private`, which prevents the class from being instantiated with `new` from outside the class itself:

```java
class ConfigLoader {
    private ConfigLoader() { }   // no one outside this class can call `new ConfigLoader()`

    static ConfigLoader instance = new ConfigLoader();   // the class can still call it internally
}
```

This is the foundation of the **Singleton pattern** — restricting a class to exactly one instance — which we'll build properly once static members (Chapter 9) and design patterns are in scope. For now, the point is narrower: a private constructor is a legitimate, intentional way to control *how* — or whether — a class can be instantiated at all, not a mistake.

---

## 9. Compiler Behaviour and JVM Internals

Chapter 3 §5 already established that `new Employee(...)` compiles to `new` + `dup` + `invokespecial`, where `invokespecial` calls the constructor's internal name, `<init>`. Each overloaded constructor you write becomes its own `<init>` method in the compiled class, distinguished by its parameter types — the same mechanism that lets the JVM tell `Employee(String)` and `Employee(String, String)` apart at the bytecode level, mirroring how the compiler picks between them at the source level in §5.

This chapter doesn't repeat Chapter 3's step-by-step memory diagram — the only addition here is that a *chain* of `this(...)` calls is really a chain of `<init>` calls, each running against the same already-allocated object, finishing only when the innermost constructor's body completes.

---

## 10. Real-World Example

The running `Employee` class, now with a full constructor set:

```java
public class Employee {

    private String name;
    private String department;
    private double salary;

    public Employee(String name) {
        this(name, "Unassigned", 0.0);
    }

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public Employee(Employee other) {          // copy constructor
        this(other.name, other.department, other.salary);
    }
}
```

```java
Employee intern = new Employee("Rohan");                          // defaults applied via chaining
Employee lead = new Employee("Asha", "Engineering", 95000);       // fully specified
Employee leadCopy = new Employee(lead);                            // copy constructor
```

---

## 11. Best Practices

- Keep constructors focused purely on initialization — no file I/O, network calls, or heavy computation inside one; that work belongs in a method called after construction, not baked into object creation.
- Use constructor chaining (§5) instead of duplicating field-assignment logic across overloads.
- Validate constructor arguments where invalid values would leave the object in a broken state (e.g., rejecting a negative `salary`) — an object should never be *constructible* into an invalid state in the first place.
- Prefer the most specific constructor doing the real work, with all others delegating to it via `this(...)` — exactly the shape in §10.

## 12. Common Mistakes

- ⚠️ Adding a return type (even `void`) to what was meant to be a constructor — it silently becomes a regular method with the same name as the class, and `new Employee()` then fails to find a matching constructor if that was the only one defined.
- ⚠️ Assuming a no-arg constructor is always available — it disappears the moment you define any other constructor (§4).
- ⚠️ Placing any statement before `this(...)` — this is a compile error, not a warning.
- ⚠️ Believing a copy constructor always produces a fully independent object — it copies field values, which for reference-type fields means copying the reference, not necessarily a deep copy (§7, full detail Chapter 29).

## 13. Interview Perspective

**Frequently Asked**

- *"Why doesn't a constructor have a return type?"* — Because it doesn't produce a value; it initializes memory that `new` already allocated (§3.1). `new` itself is what returns the reference.
- *"What happens if I don't write any constructor?"* — The compiler inserts a public no-arg default constructor (§4) — but only if you've defined none at all.
- *"What is constructor chaining, and what are the rules around `this(...)`?"* — Delegating from one constructor to another in the same class to avoid duplicated logic; `this(...)` must be the first statement, and circular chains are rejected at compile time (§5).

**Tricky Question**

- *"If a class has three overloaded constructors, all calling each other via `this(...)`, and none does the actual field assignment — does it compile?"* — No. A chain of `this(...)` calls must eventually terminate in a constructor that doesn't call `this(...)` — the compiler detects a cycle with no terminating constructor and rejects it (§5).

**Common Misconception**

- Believing constructors are inherited like regular methods. They are not — a subclass never inherits its parent's constructors; it can only *invoke* one via `super(...)` (§6). We'll see exactly why once Inheritance (Chapter 15) formalizes the parent/child relationship this chapter only previewed.

---

## 14. Summary

- A constructor initializes a newly allocated object; it shares the class's exact name and has no return type, because it isn't producing a value — it's filling in memory `new` already allocated.
- The compiler provides a free default no-arg constructor only when a class defines no constructor at all; defining any constructor removes that default.
- Constructors can be overloaded by parameter list, and can chain to each other via `this(...)`, which must be the first statement and cannot form a cycle.
- A constructor's first statement is always `this(...)`, `super(...)`, or an implicit `super()` the compiler inserts — never both `this(...)` and `super(...)`.
- A copy constructor is a common (not built-in) pattern for producing a new object initialized from an existing one's field values.

## 15. Quick Revision

- No return type, ever — that's what makes it a constructor and not a same-named method.
- Zero constructors defined → compiler gives you one for free. One or more defined → no free one.
- `this(...)` chains constructors within a class; must be first statement; no cycles allowed.
- `this(...)` and `super(...)` are mutually exclusive as a constructor's first statement.
- Copy constructor = common pattern, not a Java keyword feature; copies field values, which may still alias reference-type fields (deep dive: Chapter 29).

## 16. Self Assessment

1. Explain precisely why a constructor cannot have a return type, tying your answer back to Chapter 3's five-step object-creation pipeline.
2. A class defines only `public Employee(String name, double salary)`. Why does `new Employee()` fail to compile?
3. Write two constructors for a `Product` class where the no-arg constructor delegates to the parameterized one via chaining, and explain why this is preferable to duplicating the assignment logic in both.
4. What compile-time rule prevents two constructors from chaining to each other in an infinite loop via `this(...)`?
5. Explain, in one or two sentences, why a copy constructor that copies a `List` field can still leave two "independent" objects sharing state.

---

## What's Next

**Chapter 6 — Initialization** fills in the piece this chapter deliberately stepped around: where field initializers (`private double salary = 0.0;` written inline) and instance initializer blocks fit into the object-creation order, relative to the constructor body this chapter just opened up. Together with this chapter, it completes the full picture Chapter 3 §4 first sketched as five steps.
