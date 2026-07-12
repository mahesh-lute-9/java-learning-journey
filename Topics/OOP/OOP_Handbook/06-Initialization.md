# Chapter 6 — Initialization

**Part IV: Object Construction**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain field initializers, instance initializer blocks, and static initializer blocks — what each is for, and how they differ.
- Recite the complete, ordered initialization sequence for both class loading and object creation, from first principles.
- Explain what the compiler actually does with instance and static initializer code at the bytecode level.
- Predict the output of initialization-order trick questions — a genuinely common interview format for this exact topic.

---

## 1. Introduction

Chapter 3 §4 sketched five steps from `new` to a finished object and left step 3 — "Field Initialization" — as a label without detail. Chapter 5 then opened step 4, Constructor Execution, fully. This chapter closes the loop: it defines exactly what step 3 contains, and assembles the complete, final initialization sequence this whole Part has been building toward.

> This chapter assumes Chapter 3's allocation/default-initialization steps, Chapter 4's instance/static variable model, and Chapter 5's constructor mechanics. None of that is re-explained here — only referenced and completed.

---

## 2. Why This Concept Exists

A constructor is not always the cleanest place to express initialization logic. Sometimes you want:

- A simple default value attached directly to a field's declaration, without cluttering every constructor with an assignment.
- Setup logic that must run **no matter which constructor overload is used** — without duplicating that logic into every single one.
- One-time setup for the class itself, done exactly once, before any object of it is ever created.

Java provides a dedicated mechanism for each: **field initializers**, **instance initializer blocks**, and **static initializer blocks**, respectively.

---

## 3. The Three Initialization Mechanisms

### 3.1 Field Initializer

An expression attached directly to a field's declaration:

```java
private double salary = 0.0;
private List<String> skills = new ArrayList<>();
```

This is the simplest and most common mechanism — appropriate whenever a field's starting value can be expressed as a single expression.

### 3.2 Instance Initializer Block

An unnamed `{ }` block written directly in the class body, outside any method or constructor:

```java
public class Employee {
    private String id;

    {
        id = generateId();   // runs during construction, regardless of which constructor is used
    }
}
```

An instance initializer block runs **every time an object is constructed**, no matter which overloaded constructor was called — this is exactly what distinguishes it from putting the same code in one specific constructor, or even from `this(...)` chaining (Chapter 5 §5), which only shares logic between constructors that explicitly opt into the chain.

### 3.3 Static Initializer Block

The same idea, marked `static`, tied to the class rather than to each object:

```java
public class Employee {
    static String companyName;

    static {
        companyName = loadCompanyNameFromConfig();   // runs exactly once
    }
}
```

A static block runs **exactly once** — when the class is first loaded (Chapter 2 §6.2) — regardless of how many `Employee` objects are ever created, or even if none are.

---

## 4. The Complete Initialization Order

This is the master sequence — everything Chapters 3, 4, and 5 built toward, now assembled in full.

### 4.1 Class Loading (happens once, the first time the class is actively used)

```
Class first referenced (Chapter 2 §6.2)
        │
        ▼
1. Static fields set to default values (0, false, null)
        │
        ▼
2. Static field initializers and static blocks run,
   in the exact order they appear in the source file
```

### 4.2 Object Creation (happens every single time `new` runs)

```
new Employee(...)
        │
        ▼
1. Memory allocated on the Heap                  (Chapter 3 §4, step 1)
        │
        ▼
2. Instance fields set to default values          (Chapter 3 §4, step 2)
        │
        ▼
3. Parent class's initialization runs first        (implicit/explicit super() —
   (preview only; full mechanics in Chapter 15)      full detail deferred to Ch. 15)
        │
        ▼
4. Instance field initializers and instance
   initializer blocks run, in the exact order       ← THIS chapter's core addition
   they appear in the source file
        │
        ▼
5. The constructor's own body executes             (Chapter 5)
        │
        ▼
6. Reference to the finished object returned        (Chapter 3 §4, step 5)
```

**The rule worth memorizing precisely:** field initializers and instance initializer blocks always run *before* the constructor body, and they run in **source order**, interleaved with each other exactly as written top to bottom — not "all field initializers, then all blocks."

```java
public class Employee {
    private int a = 1;              // runs 1st
    { System.out.println(a); }      // runs 2nd, prints 1
    private int b = a + 10;         // runs 3rd — a is already 1, so b becomes 11
    { System.out.println(b); }      // runs 4th, prints 11

    public Employee() {
        System.out.println("constructor"); // runs LAST, after all of the above
    }
}
```

---

## 5. The Forward-Reference Trap

Because initializers run strictly top to bottom, referencing a field *before* its own initializer has executed yields that field's **default value** (Chapter 3 §4, step 2) — silently, with no error:

```java
public class Employee {
    private int a = getB();   // b's initializer hasn't run yet — b is still 0 (its default)
    private int b = 5;

    private int getB() {
        return b;
    }
}
// a ends up as 0, not 5 — a surprising but entirely consistent result of source-order execution
```

This isn't a bug — it's the direct, predictable consequence of §4's ordering rule. It's also one of the most common "predict the output" interview questions on this exact topic.

---

## 6. Compiler Behaviour — Where This Code Actually Goes

This is the detail that separates surface familiarity with initializer blocks from real understanding of them: **instance initializer blocks and field initializers don't exist as separate methods at the bytecode level at all.** The compiler copies their code directly into the *beginning* of every constructor — immediately after the `super(...)` call (Chapter 5 §6) and before the constructor's own written body:

```
Source:                              Compiled effect (conceptually):

class Employee {                     class Employee {
  private int a = 1;
  { System.out.println(a); }           Employee() {
                                          super();       // Ch5 §6
  public Employee() {                    a = 1;          // ← field initializer, copied in
    System.out.println("ctor");          System.out.println(a);  // ← instance block, copied in
  }                                       System.out.println("ctor");  // your actual code
}                                       }
                                      }
```

This is *why* §4's ordering rule holds for every constructor, automatically — the compiler mechanically inserts the same initializer code at the top of each one, so there's no way to accidentally skip it, no matter which overload you call.

Static field initializers and static blocks work the same way, but get collected into a single compiler-generated method conventionally called `<clinit>` ("class initialization"), which the JVM runs exactly once, at class initialization time (Chapter 39), before `<init>` is ever invoked for the first object.

---

## 7. Real-World Example

```java
public class Employee {

    static int employeeCount = 0;
    static String companyName;

    static {
        companyName = "Acme Corp";   // runs once, when Employee is first loaded
    }

    private String id;
    private String name;

    {
        id = "EMP-" + (employeeCount + 1);   // runs on every construction, before any constructor body
    }

    public Employee(String name) {
        this.name = name;
        employeeCount++;
    }
}
```

```java
Employee e1 = new Employee("Asha");   // e1.id = "EMP-1"
Employee e2 = new Employee("Rohan");  // e2.id = "EMP-2"
```

`companyName` is set up exactly once, no matter how many `Employee` objects get created. `id` is computed fresh for *every* object, before that object's constructor body runs — guaranteed, regardless of which constructor overload is eventually added to this class.

---

## 8. Best Practices

- Default to field initializers for anything expressible as one simple expression — they're the most readable option and should be your first choice.
- Reach for an instance initializer block only when logic must run for every constructor and doesn't fit cleanly as a single expression — in most real code, this is genuinely rare; constructor chaining (Chapter 5 §5) covers the common cases more clearly.
- Keep static blocks lightweight — expensive work here delays the *first* use of the class, which can be a surprising, hard-to-diagnose source of latency if the block does something like a network call.

## 9. Common Mistakes

- ⚠️ Assuming an instance initializer block runs once, like a static block — it runs on **every** object construction.
- ⚠️ Referencing a field before its own initializer has run (§5) and being surprised the value is the type's default, not what you expected.
- ⚠️ Believing field initializers and instance blocks run "whenever" relative to each other — they run in strict source order, interleaved exactly as written.
- ⚠️ Forgetting a static block runs before *any* object of the class is created, and before any static member is first accessed — not tied to `main()` starting.

## 10. Interview Perspective

**Frequently Asked**

- *"What's the difference between an instance initializer block and a static initializer block?"* — An instance block runs on every object construction, before the constructor body; a static block runs exactly once, when the class is first loaded (§3.3, §4.1).
- *"What is the exact order of execution when an object is created?"* — Walk through §4.2 in order: allocation → default init → parent init → field initializers/instance blocks in source order → constructor body → reference returned.
- *"Where do field initializers actually end up in the compiled class?"* — They're copied into the start of every constructor, right after `super()` (§6) — this is usually the answer that separates a strong candidate from one who's only memorized the ordering as a rule without understanding why it holds.

**Tricky Question**

- *"What does this print?"*
  ```java
  class Demo {
      int x = getX();
      int y = 5;
      int getX() { return y; }
  }
  System.out.println(new Demo().x);
  ```
  **Answer:** `0`. `x`'s initializer runs before `y`'s (source order, §4.2 step 4), so `getX()` reads `y` while it's still at its default value of `0` — exactly the forward-reference trap from §5.

**Common Misconception**

- Believing initializer blocks are rarely-used, purely academic syntax. In practice, understanding *why* the ordering rule in §4 holds — because the compiler literally inlines this code into every constructor (§6) — is what turns "I memorized the order" into genuine understanding, which is precisely why this topic recurs so often in interviews.

---

## 11. Summary

- Field initializers attach a starting value directly to a field's declaration; instance initializer blocks run on every object construction; static initializer blocks run exactly once, at class loading.
- The full object-creation order: allocate → default-init fields → parent initialization → field initializers/instance blocks in source order → constructor body → reference returned.
- Field initializers and instance blocks execute in strict top-to-bottom source order, and referencing a field before its own initializer yields that field's default value, not an error.
- At the bytecode level, field initializers and instance blocks are copied into the start of every constructor (after `super()`); static initializers are collected into a single `<clinit>` method run once at class load.

## 12. Quick Revision

- Field initializer → simplest, one expression. Instance block → shared logic across every constructor, runs every construction. Static block → runs once, at class load.
- Order: allocate → default values → parent init → field initializers/instance blocks (source order) → constructor body.
- Forward reference to a not-yet-initialized field silently yields its default value.
- Compiler inlines instance-level initializers into every constructor; static-level initializers become `<clinit>`, run once.

## 13. Self Assessment

1. State the complete object-creation order from `new` to a finished object, in the correct sequence, without looking back at §4.
2. What is the practical difference between putting shared setup logic in an instance initializer block versus using `this(...)` chaining (Chapter 5 §5) to share it?
3. Predict the output:
   ```java
   class Demo {
       int a = 10;
       { a += 5; }
       int b = a;
       Demo() { System.out.println(a + ", " + b); }
   }
   new Demo();
   ```
4. Why does a static initializer block run only once, no matter how many objects of the class are created?
5. Explain, at the bytecode level, why every constructor of a class — no matter which one is called — still runs that class's instance initializer blocks.

---

## What's Next

**Chapter 7 — Methods** shifts focus from *building* an object (Part IV is now complete) to giving it *behavior*. It covers method syntax, parameter passing (revisiting Chapter 3 §8's "pass reference by value" distinction in full), overloading (the same resolution mechanism §5's constructor overloading relied on, now for ordinary methods), and return values.
