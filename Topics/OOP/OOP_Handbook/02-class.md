# Chapter 2 — Class

**Part II: Classes & Objects**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Give a precise, technical definition of a class — not just "a blueprint."
- Write a correctly structured Java class declaration and explain every part of its anatomy.
- Explain what `javac` produces from a class declaration, and where that output lives at runtime.
- Explain where a class's metadata lives in memory, and why that is *not* the same place its objects live.
- Apply Java's file-naming and one-public-class-per-file rules, and explain why the compiler enforces them.
- Avoid the most common beginner confusion: treating a class as if it were an object.

---

## 1. Introduction

Chapter 1 introduced the idea that Object-Oriented Programming organizes software around **objects** — units combining state and behavior. But an object doesn't come from nowhere. Every object in Java is stamped out from a **class**.

This chapter formally defines what a class is, what the Java compiler does with one, and where a class actually "lives" while your program runs — questions most learners can't answer confidently even after writing hundreds of classes.

> This chapter does not re-explain *why* OOP bundles data and behavior together, or what Identity/State/Behavior mean — that was covered fully in Chapter 1, §1.5 and §1.7. If any of that feels unfamiliar, revisit it there rather than expecting it repeated here.

---

## 2. Why This Concept Exists

Java needed a construct that could:

1. Describe the **shape** of a category of objects (what fields and methods they'll have) exactly once.
2. Let that description be reused to produce as many objects as needed, without re-describing the shape each time.
3. Let the compiler check, ahead of time, that code using an object is using it correctly (type safety).

A `class` is Java's answer to all three. It is a **compile-time construct** — a template — not a running thing itself.

---

## 3. Theory — What Exactly Is a Class?

> **A class is a user-defined blueprint (template) that defines the fields and methods common to all objects of a particular kind, without itself occupying memory for those fields.**

The key phrase is "without itself occupying memory for those fields." A class declaration tells the compiler *what shape* an `Employee` object will have. It does not create any employee. No memory for `name`, `salary`, or `department` is allocated when the class is merely declared — only when an object is *instantiated* from it (Chapter 3).

### 3.1 Class as a Blueprint — and Where the Analogy Breaks

The "blueprint for a house" analogy is common and useful up to a point:

```
Blueprint (Class)                 House (Object)
──────────────────                ───────────────
Describes: 3 bedrooms,     ──►    An actual house built
2 bathrooms, garage               from that blueprint,
                                   standing at a real address
```

Where the analogy breaks: you can build many houses from one blueprint and they don't share bricks — but in Java, many *objects* of the same class don't share instance data either (each gets its own copy of instance fields), while they **do** share the class's method code and metadata. We'll pin down exactly what's shared and what isn't in the Memory Representation section below.

### 3.2 A Class as a New Data Type

Every class you write becomes a new **reference type** you can use exactly like `int` or `boolean` are used as primitive types — as a variable's type, a method's parameter type, or a method's return type:

```java
Employee emp;              // emp's type is Employee
void promote(Employee e) { }      // parameter type
Employee findById(int id) { }     // return type
```

This is a subtle but important idea: `class` doesn't just group code — it extends the Java type system with a type you invented.

---

## 4. Syntax — Anatomy of a Class Declaration

```java
[access modifier] class ClassName {

    // 1. Fields (instance variables) — the state
    private String name;
    private double salary;

    // 2. Constructors — covered fully in Chapter 5
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // 3. Methods — the behavior
    public double calculateAnnualSalary() {
        return salary * 12;
    }

    // 4. Static members — covered fully in Chapter 9
    static int employeeCount = 0;

    // 5. Initializer blocks — covered fully in Chapter 6
    { employeeCount++; }

    // 6. Nested classes — covered fully in Chapter 23
}
```

A class body can contain: fields, constructors, methods, static members, instance/static initializer blocks, and nested classes. This chapter focuses on the class construct itself; each of those member kinds gets its own dedicated chapter later in this handbook (see the "What's Next" section) — they are only outlined here so you can recognize the full anatomy of a class at a glance.

### 4.1 Minimal Valid Class

The smallest legal Java class is just:

```java
class Employee {
}
```

This compiles. It defines a valid type with no fields and no methods — proof that a class is fundamentally a *declaration of a type*, and everything inside the braces is optional.

### 4.2 Access Modifiers on a Class

A top-level class may only be declared `public` or left **package-private** (no modifier) — it cannot be `private` or `protected`. (Access modifiers are covered in full in Chapter 13; this rule is mentioned here only because it directly affects the file-naming rule below.)

---

## 5. Compiler Behaviour

### 5.1 One `.java` File, One or More `.class` Files

When you compile `Employee.java`, `javac` produces one `.class` file **per class**, not per file. If `Employee.java` contains a top-level `Employee` class plus a nested `Address` class, compiling it produces:

```
Employee.java
        │
        ▼ javac
Employee.class
Employee$Address.class
```

We'll see exactly why nested classes get this `Outer$Inner.class` naming when we reach Chapter 23.

### 5.2 The One-Public-Class-Per-File Rule

A `.java` file may contain **at most one `public` class**, and if it has one, the file name **must** match that class's name exactly (including case).

```java
// File: Employee.java
public class Employee { }   // ✔ Compiles

// File: Employee.java
public class Staff { }      // ✘ Compile error:
                             // class Staff is public, should be declared in a file named Staff.java
```

**Why does the compiler enforce this?** So that any tool — or another developer — can locate a public class's source without reading the file's contents first, purely from its name. Package-private (non-`public`) classes have no such restriction; a single file may contain several of them alongside one public class.

### 5.3 Compiling Multiple Classes in One File

```java
// File: Employee.java
public class Employee {
    private String name;
}

class Department {          // package-private, no restriction
    private String deptName;
}
```

This compiles to two separate files: `Employee.class` and `Department.class`. The `.java` file is purely a source-code organizational convenience for the compiler — the JVM only ever deals in `.class` files.

---

## 6. JVM Internals — Where Does a Class Actually Live?

This is the question most learners never get a satisfying answer to, so let's be precise.

### 6.1 Class Metadata Lives in the Method Area (Metaspace)

When a class is first used, the JVM's **ClassLoader** reads its `.class` file and stores the class's metadata — its field definitions, method bytecode, constant pool, and a runtime representation of the class itself — in a JVM memory region historically called the **Method Area**. Since Java 8, this region is implemented as **Metaspace**, allocated from native (off-heap) memory rather than a fixed-size part of the heap.

```
                     JVM Runtime Data Areas
        ┌───────────────────────────────────────────┐
        │                                            │
        │   Metaspace (Method Area)                  │
        │   ┌──────────────────────────────────┐     │
        │   │ Employee — class metadata         │     │
        │   │  • field definitions (name,       │     │
        │   │    salary — no values, just shape)│     │
        │   │  • method bytecode                │     │
        │   │  • constant pool                  │     │
        │   │  • static variable storage        │     │
        │   └──────────────────────────────────┘     │
        │                                            │
        │   Heap                                     │
        │   (empty — no Employee objects yet,        │
        │    because none has been created with      │
        │    `new` — see Chapter 3)                  │
        │                                            │
        └───────────────────────────────────────────┘
```

Notice: **loading a class allocates memory for the class's metadata — not for any object.** This is the precise, technical version of "a class doesn't occupy memory the way an object does," and it's why the file above deliberately shows the Heap as empty.

### 6.2 Class Loading Happens Lazily, On First Use

The JVM does not load every class in your program at startup. A class is loaded the first time it is actively used — for example, the first time `new Employee()` is executed, or the first time one of its static members is accessed. We'll walk through the full Loading → Linking → Initialization pipeline in depth in **Chapter 39 (Class Loading)**; for now, the important takeaway is simply that class metadata and object instances are allocated at different times, in different memory regions, for different reasons.

### 6.3 One Copy of Metadata, Many Objects

Because a class's method bytecode and structural definition are stored once in Metaspace, every object of that class — no matter how many you create — shares that single copy. Only each object's own field *values* are duplicated, and those live separately in the Heap (Chapter 3 covers this in full).

---

## 7. Real-World Example

Continuing this handbook's running domain model, here is a professionally structured `Employee` class as it might actually appear in a small HR system — deliberately minimal for now, since constructors, encapsulation, and access modifiers each get their own deep-dive chapter later:

```java
public class Employee {

    private String name;
    private String department;
    private double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public double calculateAnnualSalary() {
        return salary * 12;
    }

    public String getDepartment() {
        return department;
    }
}
```

This class will be reused — not redefined — in later chapters (Object, Constructors, Encapsulation, Inheritance) as it grows more complete. Per this handbook's No Repetition Rule, each new capability will simply be *added* to this same running example rather than the class being re-explained from scratch.

---

## 8. Advantages of Organizing Code as Classes

- **Single source of truth for shape** — an `Employee`'s structure is defined in exactly one place, so every object built from it is guaranteed consistent.
- **Compile-time type safety** — the compiler rejects code that misuses an `Employee` (e.g., passing one where a `Product` is expected) before the program ever runs.
- **Reuse without duplication** — one class definition can be instantiated into an unlimited number of objects.
- **A natural unit of change** — modifying `Employee`'s behavior means editing one file, not hunting through unrelated code.

## 9. Limitations

- A class only describes structure and behavior — it says nothing about how many objects should exist or how they relate to each other at runtime; that's a design decision made elsewhere (see Part X, Object Relationships).
- Overly large classes that try to model too much responsibility become difficult to maintain — a concern we'll return to directly when covering the Single Responsibility Principle in Chapter 34 (SOLID Principles).

## 10. Best Practices

- Name classes with **PascalCase** nouns that name the real-world entity they model (`Employee`, not `employee` or `EmployeeClass`).
- Keep one class's responsibility focused — resist the urge to make `Employee` also handle payroll report generation or database connections.
- Match the file name to the public class name exactly — this isn't optional style, the compiler requires it (§5.2).
- Prefer declaring only one class (public or otherwise) per file once a class grows beyond a handful of members, even though Java permits multiple package-private classes per file — it keeps navigation predictable for other developers (and for GitHub search).

## 11. Common Mistakes

- ⚠️ Believing that **declaring** a class allocates memory for an object — it doesn't; only `new` does (Chapter 3).
- ⚠️ Naming the file differently from the public class it contains, then being confused by the resulting compile error.
- ⚠️ Putting two `public` classes in one file — Java allows only one.
- ⚠️ Treating "class" and "object" as interchangeable terms in conversation or in interviews — precision here is one of the fastest ways to signal (or fail to signal) real understanding.

## 12. Interview Perspective

**Frequently Asked**

- *"What is a class?"* — Give the precise definition from §3, not just "a blueprint." Be ready to explain *why* declaring a class doesn't allocate object memory.
- *"Can a class exist without ever creating an object from it?"* — Yes. A class with only `static` members (a "utility class," e.g. `java.lang.Math`) is commonly used without ever being instantiated. We'll examine this pattern fully in Chapter 9 (`static`).
- *"Where does a class live in memory?"* — Its metadata lives in Metaspace (§6.1), not the Heap. Objects created from it live in the Heap (Chapter 3).
- *"Why must a public class's file name match the class name?"* — So the class can be located from its name alone; the compiler enforces it (§5.2).

**Tricky Question**

- *"If I declare `class Employee { }` and never write `new Employee()` anywhere in my program, does the class get loaded into memory at all?"* — Not necessarily. Class loading is lazy (§6.2); a class that's never referenced may never be loaded by the JVM at all, even though it compiled successfully. We'll verify this precisely with the class-loading pipeline in Chapter 39.

**Common Misconception**

- Believing a `.java` file and a `.class` file are the same thing. A `.java` file is source code you write; `javac` compiles it into one `.class` file *per class* it contains (§5.1), and those `.class` files — not the `.java` file — are what the JVM actually loads and runs.

---

## 13. Summary

- A class is a compile-time blueprint describing the fields and methods a category of objects will have — it does not itself hold object data.
- A class declaration also introduces a new reference type into Java's type system.
- `javac` compiles each class into its own `.class` file; a `.java` file may hold several classes, but only one may be `public`, and that one's name must match the file name.
- At runtime, a class's metadata (field definitions, method bytecode, constant pool) is loaded — once, lazily, on first use — into Metaspace, not the Heap; object instances built from that class are what eventually occupy the Heap.
- Every object of a class shares the same one copy of the class's method bytecode and structural metadata; only instance field *values* are duplicated per object.

## 14. Quick Revision

- Class = blueprint (compile-time) · Object = instance (runtime) — never conflate the two.
- One `.class` file per class, not per `.java` file.
- Only one `public` class per `.java` file, and the file name must match it.
- Class metadata → Metaspace. Object data → Heap. Different regions, different lifetimes.
- Classes load lazily, on first active use — not all at program startup.

## 15. Self Assessment

1. In your own words, explain why declaring `class Employee { }` does not consume memory the way `new Employee()` does.
2. What compile error would you get from a file named `Staff.java` containing `public class Employee { }`? Why does the compiler care?
3. If a `.java` file contains one `public` class and two package-private classes, how many `.class` files does `javac` produce, and what determines each one's name?
4. Where does a loaded class's method bytecode live — the Heap or Metaspace — and why does that distinction matter for memory efficiency when many objects of the same class exist?
5. Give an example (other than `java.lang.Math`) of a class you'd expect to be used without ever instantiating it, and explain why.
6. True or false, with justification: "A class is loaded into the JVM the moment the program that references it starts running."

---

## What's Next

**Chapter 3 — Object** picks up exactly where §3 and §6 of this chapter left off: what actually happens, step by step, when `new Employee(...)` executes — memory allocation on the Heap, default field initialization, constructor execution, and the reference that gets returned. This chapter's Metaspace/Heap distinction (§6.1) is the foundation Chapter 3's memory diagrams are built on, so it will be referenced, not re-explained.
