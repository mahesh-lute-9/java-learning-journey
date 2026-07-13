# Chapter 11 — `super`

**Part VI: Keywords**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain exactly what `super()` does, and complete the constructor rules Chapter 5 §6 deliberately left as a preview.
- State the complete, concrete object-initialization order once a parent class is involved — finishing what Chapter 6 §4.2 labeled "preview only."
- Use `super.field` and `super.method()` to reach a parent's members, and explain why `super.method()` is resolved differently from an ordinary method call.
- Recognize the boundary of this chapter's scope: `super` the keyword is fully covered here; Inheritance as a design concept — when to use it, overriding rules, polymorphism — is Chapter 15 and Chapter 16's job, not this one's.

> **Scope note:** Two earlier chapters flagged parent-class behavior as "preview only, full detail in Chapter 15" — Chapter 5 §6 (implicit `super()`) and Chapter 6 §4.2 (parent initialization order). This chapter finally delivers both, precisely. To do that, it necessarily uses a minimal `extends` example — but it stops there. *Why* to use inheritance, the "is-a" relationship, method overriding rules, and polymorphism are deliberately left for Chapter 15 and Chapter 16. This chapter is about the keyword only.

---

## 1. Introduction

A subclass — a class declared with `extends` — automatically has access to its parent's non-private members, but it still needs a way to explicitly invoke the parent's *constructor*, and occasionally to reach a parent member that's been hidden by one of its own. `super` is that mechanism.

```java
class Vehicle {
    protected String make;

    Vehicle(String make) {
        this.make = make;
    }

    void describe() {
        System.out.println("A vehicle made by " + make);
    }
}

class Car extends Vehicle {
    private int doors;

    Car(String make, int doors) {
        super(make);       // calls Vehicle's constructor
        this.doors = doors;
    }
}
```

This `Vehicle`/`Car` pair exists purely to demonstrate `super`'s mechanics in this chapter — it is not this handbook's flagship inheritance example. Chapter 15 will build a proper `Employee` → `Manager` hierarchy on the running `Employee` class this handbook has used since Chapter 2, once Inheritance itself is the actual topic.

---

## 2. `super(...)` — Calling the Parent's Constructor

### 2.1 The Rule, Completed

Chapter 5 §6 established that a constructor's first statement is either `this(...)`, `super(...)`, or nothing — in which case the compiler inserts an implicit `super()` on your behalf. With an actual parent class now in view, that rule can be stated precisely:

```java
Car(String make, int doors) {
    super(make);       // must be the first statement
    this.doors = doors;
}
```

`super(make)` invokes `Vehicle`'s constructor that takes a `String`, running `Vehicle`'s initialization (its field initializers, instance blocks, and constructor body — Chapter 6 §4.2) before a single line of `Car`'s own constructor runs.

### 2.2 The Gotcha: No-Arg Parent Constructor Required for the Implicit Case

If a `Car` constructor doesn't call `super(...)` explicitly, the compiler inserts a **no-argument** `super()` call automatically. This only works if `Vehicle` actually has a no-arg constructor:

```java
class Vehicle {
    protected String make;
    Vehicle(String make) { this.make = make; }   // only a parameterized constructor exists
}

class Car extends Vehicle {
    Car(int doors) {
        // no explicit super(...) here
        this.doors = doors;   // ✘ compile error:
    }                          //   there is no default constructor available in Vehicle
}
```

This is precisely Chapter 5 §4's default-constructor rule, now showing its consequence across a class hierarchy: once `Vehicle` defines any constructor, it loses its free no-arg one — and `Car` can no longer rely on an implicit, parameterless `super()` to reach it.

### 2.3 Mutually Exclusive with `this(...)`

Chapter 5 §6 already stated this rule; it's worth restating now that it's concrete: a constructor can start with `super(...)` **or** `this(...)`, never both — because a constructor call can only delegate in one direction (to a specific parent constructor, or to a specific sibling constructor in the same class) at a time.

---

## 3. The Complete Initialization Order, With Inheritance

This finishes what Chapter 6 §4.2 deliberately left as a labeled placeholder. Here is the full, concrete sequence for `new Car("Toyota", 4)`:

```
new Car("Toyota", 4)
        │
        ▼
1. Memory allocated for the entire object — including Vehicle's fields
   and Car's fields together, as one single object on the Heap
        │
        ▼
2. All fields (Vehicle's and Car's) set to default values
        │
        ▼
3. Car's constructor calls super("Toyota") →
      3a. Vehicle's field initializers / instance blocks run
      3b. Vehicle's constructor body runs (make = "Toyota")
        │
        ▼
4. Car's own field initializers / instance blocks run
        │
        ▼
5. Car's own constructor body runs (doors = 4)
        │
        ▼
6. Reference to the finished Car object returned
```

**The one sentence worth memorizing:** a parent's entire initialization — its field initializers, instance blocks, and constructor body — always finishes completely before the subclass's own field initializers, instance blocks, or constructor body begin. There is exactly one `Car` object here, not two — the `Vehicle` portion isn't a separate object living inside it; it's simply the part of this one object's layout that `Vehicle` is responsible for initializing.

The same "parent first" rule applies at class-loading time, too: when `Car` is loaded for the first time, `Vehicle`'s static initializers (Chapter 9 §6) run before `Car`'s own, exactly once each.

---

## 4. `super.field` and `super.method()`

If a subclass declares a field or method with the same name as its parent's, plain, unqualified access from inside the subclass refers to the subclass's own version — `super.` explicitly reaches the parent's:

```java
class Vehicle {
    protected String make = "Generic";
    void describe() { System.out.println("Vehicle: " + make); }
}

class Car extends Vehicle {
    protected String make = "Toyota";   // hides Vehicle's make

    void describe() {
        System.out.println(super.make);      // reaches Vehicle's make → "Generic"
        System.out.println(this.make);        // reaches Car's own make → "Toyota"
        super.describe();                     // explicitly runs Vehicle's describe() too
    }
}
```

### A Genuinely Important JVM Detail: `super.method()` Is Not Dynamically Dispatched

Chapter 7 §6 established that ordinary instance method calls compile to `invokevirtual` and are resolved dynamically, based on the object's actual class. A call through `super.`, however, compiles to `invokespecial` — the same instruction family used for constructor calls (Chapter 3 §5) — which resolves to **one specific, statically known method: the parent's version, exactly**, never dispatched dynamically. This is precisely what prevents `super.describe()` from looping back into `Car`'s own `describe()` — it deliberately bypasses dynamic dispatch to reach the parent directly. This detail matters most once method overriding (Chapter 16) is in play, where calling `super.someMethod()` from inside an override is a common, deliberate pattern for extending — not replacing — the parent's behavior.

---

## 5. Real-World Example

```java
class Vehicle {
    protected String make;

    Vehicle(String make) {
        this.make = make;
    }

    void describe() {
        System.out.println("Vehicle made by " + make);
    }
}

class Car extends Vehicle {
    private int doors;

    Car(String make, int doors) {
        super(make);              // §2 — parent constructor runs first
        this.doors = doors;
    }

    void describe() {
        super.describe();          // §4 — reuse parent's behavior
        System.out.println("It has " + doors + " doors.");
    }
}
```

```java
Car car = new Car("Toyota", 4);
car.describe();
// Vehicle made by Toyota
// It has 4 doors.
```

---

## 6. Best Practices

- Call `super(...)` explicitly whenever the parent has no no-arg constructor, or whenever a specific parent constructor needs specific arguments — don't rely on the implicit call unless a genuinely parameterless parent constructor is what you want.
- Use `super.method()` deliberately, when a subclass genuinely wants to extend — not silently replace — its parent's behavior (§4's `describe()` example).
- Avoid field hiding (`super.field` vs. `this.field`, §4) where possible — it's legal, but it's also one of the more confusing patterns to read; prefer distinct field names, or proper encapsulation (Chapter 12), over relying on `super.` to disambiguate.

## 7. Common Mistakes

- ⚠️ Assuming a subclass automatically gets a working implicit `super()` regardless of what constructors the parent defines — it only works if the parent has an accessible no-arg constructor (§2.2).
- ⚠️ Placing any statement before `super(...)` — a compile error, identical in spirit to Chapter 5 §6's `this(...)` rule.
- ⚠️ Assuming `super.method()` is dynamically dispatched the way a normal call is — it isn't (§4); it always reaches the parent's exact version via `invokespecial`.
- ⚠️ Treating this chapter's `Vehicle`/`Car` pair as if it were teaching *when* to use inheritance — that judgment call, and the full mechanics of overriding, belong to Chapter 15 and Chapter 16.

## 8. Interview Perspective

**Frequently Asked**

- *"What does `super()` do?"* — Invokes the parent class's constructor, explicitly or implicitly, and must be the first statement in the subclass constructor (§2).
- *"What happens if a subclass constructor doesn't call `super(...)` explicitly?"* — The compiler inserts an implicit no-arg `super()` call — which fails to compile if the parent has no accessible no-arg constructor (§2.2).
- *"What's the full object-initialization order once inheritance is involved?"* — Walk through §3 in order: allocate the whole object → default-init all fields → parent's field-init/instance-blocks/constructor body (via `super`) → subclass's own field-init/instance-blocks/constructor body → reference returned.

**Tricky Question**

- *"If an overridden method calls `super.someMethod()` inside itself, does it risk infinite recursion by calling the overridden version again?"* — No — `super.someMethod()` compiles to `invokespecial` (§4), which reaches the parent's exact implementation directly, bypassing the dynamic dispatch (`invokevirtual`, Chapter 7 §6) that would otherwise resolve back to the subclass's own override.

**Common Misconception**

- Believing `super` refers to "a separate parent object" living inside the subclass object. It doesn't — as §3 makes explicit, there is exactly one object on the Heap; `super` is simply a reference mechanism for reaching the parent-defined *portion* of that one object's members, not a pointer to a second, distinct object.

---

## 9. Summary

- `super(...)` invokes the parent class's constructor, must be a constructor's first statement, and is mutually exclusive with `this(...)`.
- If omitted, the compiler inserts an implicit no-arg `super()` — which fails to compile if the parent has no accessible no-arg constructor.
- With inheritance in the picture, initialization order is: allocate the whole object → default-init all fields → parent's full initialization (via `super`) → subclass's own field initializers/instance blocks → subclass's own constructor body.
- `super.field` and `super.method()` reach a parent's members explicitly; `super.method()` is resolved statically via `invokespecial`, not dynamically dispatched like an ordinary call.
- This chapter covered `super` the keyword completely; Inheritance as a design concept is Chapter 15's subject.

## 10. Quick Revision

- `super(...)` = parent constructor call, must be first statement, mutually exclusive with `this(...)`.
- No explicit `super(...)` → compiler inserts implicit no-arg one → fails if parent has none.
- Init order with inheritance: allocate whole object → default values → parent's full init → subclass's own field-init/blocks → subclass's constructor body.
- `super.method()` → `invokespecial`, statically resolved, never dynamically dispatched.

## 11. Self Assessment

1. Why does `Car(int doors) { this.doors = doors; }` fail to compile if `Vehicle` only defines `Vehicle(String make)`?
2. Walk through the complete initialization order for `new Car("Honda", 2)`, from memory allocation to the returned reference.
3. Explain the difference between `this.make` and `super.make` inside a subclass that hides a parent field of the same name.
4. Why doesn't calling `super.describe()` from inside an overriding `describe()` method risk calling itself again infinitely?
5. This chapter used a `Vehicle`/`Car` pair to demonstrate `super`. What is deliberately *not* covered yet about inheritance, and which chapter will cover it?

---

## What's Next

Part VI (Keywords) is now complete — `this`, `static`, `final`, and `super` have all been covered fully. **Chapter 12 — Encapsulation** begins Part VII (Object Design), returning to the running `Employee` class to formally apply the first of the Four Pillars previewed back in Chapter 1 §1.8: bundling `Employee`'s fields behind controlled access, using `private` fields with `public` getters and setters — the practice this handbook has been quietly modeling in every example since Chapter 2, now made explicit and rigorous.
