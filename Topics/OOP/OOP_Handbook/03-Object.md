# Chapter 3 — Object

**Part II: Classes & Objects**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Give a precise definition of an object and explain exactly how it differs from a class.
- Trace, step by step, what happens in memory when `new Employee(...)` executes.
- Explain where an object's reference lives versus where the object itself lives.
- Explain default field initialization and why it matters before a constructor body runs.
- Distinguish object identity (`==`) from object equality (`.equals()`) at a conceptual level.
- Avoid the most common beginner mistakes around references, aliasing, and `null`.

---

## 1. Introduction

Chapter 2 defined a class as a compile-time blueprint that occupies no memory for object data by itself. This chapter answers the natural follow-up question: **what actually happens when you turn that blueprint into a real, memory-occupying thing?**

> This chapter does not re-explain what a class is, the Metaspace/Heap distinction, or why classes load lazily — that's Chapter 2, §5–§6. It also doesn't re-explain Identity/State/Behavior as OOP concepts — that's Chapter 1, §1.5. Both are assumed here and only referenced.

---

## 2. Why This Concept Exists

A class alone can't do anything — it's a description, not a thing. Java needs a mechanism to:

1. Take a class's description and produce an actual, independent unit of state from it.
2. Give that unit its own storage for field values, separate from every other unit made from the same class.
3. Hand the program a way to refer to that unit so it can be used.

That mechanism is **object instantiation**, triggered by the `new` keyword. Every object you've ever used in Java — a `String`, an `ArrayList`, an `Employee` — came into existence exactly this way.

---

## 3. Theory — What Exactly Is an Object?

> **An object is a runtime instance of a class — a block of memory on the Heap holding actual values for that class's fields, created and destroyed during program execution.**

Where a class is compile-time and shared, an object is **runtime and individual**: every object created from `Employee` gets its own independent copy of `name`, `department`, and `salary`, even though all of them share the exact same `Employee` method bytecode sitting once in Metaspace (Chapter 2, §6.3).

### 3.1 Class vs. Object — Precisely

| | Class | Object |
|---|---|---|
| What it is | A blueprint / type definition | A concrete instance of that type |
| When it's created | Compile time (`.class` file); loaded once at first use | Runtime, every time `new` executes |
| Where it lives | Metaspace (metadata) | Heap (instance data) |
| How many exist | One loaded copy per class | As many as your program creates |
| Occupies memory for field *values*? | No | Yes |

### 3.2 Reference vs. Object — the Distinction Beginners Miss Most

```java
Employee emp = new Employee("Asha", "Engineering", 95000);
```

This single line actually creates **two** distinct things:

```
Stack                          Heap
┌────────────┐                ┌──────────────────────────┐
│ emp  ●─────┼───────────────►│ Employee object           │
└────────────┘                │  name = "Asha"            │
  (reference                  │  department = "Engineering"│
   variable)                  │  salary = 95000.0          │
                               └──────────────────────────┘
```

`emp` is a **reference variable** — it does not hold the object; it holds the object's location. The object itself lives on the Heap. This is why Java is often described as "pass references by value": copying `emp` to another variable copies the *arrow*, not the object it points to (we'll see the consequences of that directly in §7).

---

## 4. Internal Working — What `new` Actually Does, Step by Step

```
new Employee("Asha", "Engineering", 95000)

        │
        ▼
1. Memory Allocation
   The JVM allocates a block of memory on the Heap
   large enough for an Employee's object header + fields.

        │
        ▼
2. Default Initialization
   Every field is first set to its type's default —
   0 for numeric types, false for boolean, null for
   references — regardless of what the constructor will
   later assign. This happens before any of your code runs.

        │
        ▼
3. Field Initialization
   Any inline field initializers or instance initializer
   blocks (Chapter 6) run, in the order they appear in the class.

        │
        ▼
4. Constructor Execution
   The matching constructor body runs, typically assigning
   the parameters you passed in — name, department, salary.

        │
        ▼
5. Reference Returned
   `new` evaluates to a reference to the fully constructed
   object, which is then stored in `emp`.
```

**Why does default initialization (step 2) matter?** Because it's what guarantees a Java object can never contain garbage/uninitialized memory — a real class of bugs in languages without this guarantee. It also explains a subtlety we'll return to in Chapter 5: if a constructor doesn't explicitly set a field, that field isn't "empty" or undefined — it's already sitting at its type's default value.

---

## 5. Compiler Behaviour

At the bytecode level, `new Employee(...)` compiles to roughly three bytecode instructions: `new` (allocate raw, uninitialized space for an `Employee` and push a reference to it), `dup` (duplicate that reference, since the constructor call will consume one copy), and `invokespecial` (call the `Employee` constructor — the special `<init>` method — on the freshly allocated object). You don't need to memorize bytecode mnemonics for this handbook's purposes, but recognizing that "allocate" and "construct" are two genuinely separate bytecode steps reinforces §4's memory-allocation-then-constructor-execution ordering — it isn't just a conceptual simplification, it's literally how the JVM does it.

---

## 6. JVM Internals — Object Memory Layout

Every object on the Heap carries more than just your declared fields. A typical HotSpot JVM object layout looks like this:

```
Employee object on the Heap
┌─────────────────────────────┐
│ Object Header                │
│  ├─ Mark Word                │  ← identity hash, GC/locking info
│  └─ Class Pointer             │  ← points back to Employee's
│                               │     metadata in Metaspace
├─────────────────────────────┤
│ Instance Fields               │
│  name       → "Asha"         │
│  department → "Engineering"  │
│  salary     → 95000.0        │
├─────────────────────────────┤
│ Padding (if needed, for      │
│ memory alignment)             │
└─────────────────────────────┘
```

The **Class Pointer** in the header is what ties an object back to its class's shared metadata in Metaspace (Chapter 2, §6.1) — it's how `emp.calculateAnnualSalary()` knows which method bytecode to run, without that bytecode being duplicated inside every single object. This connection is also what makes `emp.getClass()` and reflection (Chapter 30) possible later.

We'll go deeper into Heap regions (Young Generation, Old Generation) and how objects get promoted between them when we reach Garbage Collection in Chapter 38 — for now, the important model is simply "objects live on the Heap; their header links them back to shared class metadata in Metaspace."

---

## 7. Aliasing — Two References, One Object

```java
Employee emp1 = new Employee("Asha", "Engineering", 95000);
Employee emp2 = emp1;

emp2.raiseSalary(5000);

System.out.println(emp1.getSalary()); // 100000.0 — emp1 sees the change too
```

```
Stack                          Heap
┌────────────┐
│ emp1  ●─────┼──────┐         ┌──────────────────────────┐
├────────────┤       ├────────►│ Employee object            │
│ emp2  ●─────┼──────┘         │  salary = 100000.0 (after)│
└────────────┘                 └──────────────────────────┘
```

`emp1 = emp2` did not create a second object — it copied the *reference*, so both variables now point at the same Heap object. Modifying it through `emp2` is visible through `emp1` too, because there is only ever one object here. This is called **aliasing**, and it's the single most common source of "why did my object change when I didn't touch that variable?" bugs in Java.

## 8. Object Identity vs. Equality

Two different questions, two different operators:

```java
Employee a = new Employee("Asha", "Engineering", 95000);
Employee b = new Employee("Asha", "Engineering", 95000);

a == b        // false — two distinct objects on the Heap, different addresses
a.equals(b)   // depends on Employee's equals() implementation (default: same as ==)
```

`==` on objects compares **references** (are these two variables pointing at the exact same Heap object?), not field values. By default, `.equals()` inherited from `Object` behaves identically to `==`, unless a class deliberately overrides it to compare field values instead. We'll implement that override properly — and see why it must be paired with `hashCode()` — in Chapter 19 (Object Class).

---

## 9. Real-World Example

Continuing the running `Employee` class from Chapter 2:

```java
Employee emp1 = new Employee("Asha", "Engineering", 95000);
Employee emp2 = new Employee("Rohan", "Sales", 62000);
Employee emp3 = emp1;                       // alias, not a new object

System.out.println(emp1 == emp3);           // true  — same object
System.out.println(emp1 == emp2);           // false — different objects
```

Three reference variables, but only **two** actual `Employee` objects on the Heap — `emp1` and `emp3` point at the same one. This is exactly the distinction §7 walks through, now applied to the class this handbook has been building since Chapter 2.

---

## 10. Advantages of Java's Object Model

- **Automatic default initialization** removes an entire category of "uninitialized memory" bugs common in lower-level languages.
- **Reference semantics** make passing large objects around cheap — you're copying a small reference, not duplicating the whole object.
- **Garbage Collection** (previewed here, full detail in Chapter 38) means you never manually free an object's memory — once nothing references it, the JVM reclaims it automatically.

## 11. Limitations

- Reference semantics are also the *source* of aliasing bugs (§7) — an object can be modified through a reference you didn't realize was pointing at it.
- Every object carries header overhead (§6) beyond its declared fields — a real cost when your program creates very large numbers of small objects.
- Because default `.equals()` is identity-based (§8), forgetting to override it for value-comparison classes is a frequent, hard-to-spot bug.

## 12. Best Practices

- Assume any object reference you didn't just create yourself might be an alias — don't mutate an object through one reference without considering who else might be holding a reference to it.
- Don't rely on a field's default value (`0`, `null`, `false`) as intentional program logic — assign it explicitly in the constructor, even if the value happens to match the default, so the intent is clear to the next reader.
- Use `==` only when you genuinely mean "is this the same object in memory," not "do these have the same data" — reach for `.equals()` (Chapter 19) for the latter.

## 13. Common Mistakes

- ⚠️ Comparing objects with `==` expecting it to compare field values — it compares references.
- ⚠️ Assuming `emp2 = emp1` creates a new object — it creates a second reference to the same one.
- ⚠️ Forgetting that fields have default values *before* a constructor runs, and being surprised by a `NullPointerException` when a reference field a constructor forgot to set is used later.
- ⚠️ Treating "object" and "class" as interchangeable — after this chapter, that distinction should be automatic; watch for it slipping back in under interview pressure.

## 14. Interview Perspective

**Frequently Asked**

- *"What is an object?"* — Use the precise definition in §3, and be ready to explain where it lives (Heap) versus where its class's metadata lives (Metaspace, Chapter 2).
- *"What happens when you write `new Employee()`?"* — Walk through the five steps in §4 in order: allocation → default initialization → field initialization → constructor execution → reference returned.
- *"Difference between `==` and `.equals()`?"* — `==` compares references (identity); `.equals()` compares whatever a class defines as "equal" — by default, also identity, unless overridden (§8, full detail Chapter 19).

**Tricky Question**

- *"If `Employee emp2 = emp1;` and then `emp2 = new Employee(...)`, does `emp1` change?"* — No. That line doesn't touch the original object at all — it just points `emp2` at a *new*, separate object, leaving `emp1` pointing at the original unchanged. Aliasing (§7) means two variables can share an object, but reassigning one variable never affects the other unless you're modifying the shared object's *fields* through it.

**Common Misconception**

- Believing Java passes objects "by reference" in the C++ sense. Java always passes the **reference's value** — you can use that reference to mutate the object it points to, but reassigning the parameter inside a method never affects the caller's original variable. This distinction matters enough that it gets its own treatment when we cover Methods in Chapter 7.

---

## 15. Summary

- An object is a runtime instance of a class, holding actual field values on the Heap.
- `new` triggers five ordered steps: memory allocation, default initialization, field initialization, constructor execution, reference return.
- A reference variable holds an object's location, not the object itself — assigning one reference to another creates an alias, not a copy of the object.
- Every object's header links back to its class's shared metadata in Metaspace, which is how method calls resolve without duplicating method code per object.
- `==` compares object identity (same Heap address); `.equals()` compares whatever a class defines as equality, defaulting to identity unless overridden.

## 16. Quick Revision

- Class = blueprint (Metaspace). Object = instance (Heap). Reference = the arrow connecting a variable to an object.
- `new` order: allocate → default-init → field-init → constructor → return reference.
- Two references can point at one object (aliasing) — mutating through either one is visible through both.
- `==` = identity. `.equals()` = defined equality (identity by default).

## 17. Self Assessment

1. Walk through, in order, exactly what happens in memory when `Product p = new Product();` executes.
2. If a class has a field `private int quantity;` and its constructor never assigns it, what value does `quantity` hold immediately after construction, and why?
3. Write two lines of code that create **one** `Employee` object with **two** references to it, then explain how you'd prove — using `==` — that they refer to the same object.
4. Explain why `a.equals(b)` and `a == b` can return different results for two objects, even though by default they behave identically.
5. A colleague says "Java passes objects by reference, so if I reassign a parameter inside a method, it changes the caller's variable too." Is this correct? Justify your answer.

---

## What's Next

**Chapter 4 — Variables** builds on §4's "Field Initialization" and "Default Initialization" steps by formally covering the different kinds of variables in Java (instance, local, static, parameters), their default values, and their scope and lifetime — filling in the mechanics this chapter deliberately treated as a black box inside the object-creation pipeline.
