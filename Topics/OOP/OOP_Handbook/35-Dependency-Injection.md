# Chapter 35 — Dependency Injection

**Part XII: Object-Oriented Design**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain why depending on an abstraction (Chapter 34's DIP) isn't sufficient by itself — and what Dependency Injection adds to make it real.
- Compare constructor, setter, and field injection precisely, and justify why constructor injection is the generally recommended default.
- Explain the term "Inversion of Control" and how Dependency Injection relates to it.
- Explain, concretely, how a framework like Spring performs dependency injection under the hood — using mechanics this handbook already built in Chapters 30 and 31.

---

## 1. Introduction

Chapter 34 §6.1 drew a precise line: Dependency Inversion is the *design principle* ("depend on abstractions"); Dependency Injection is the *pattern* that actually supplies a concrete implementation to satisfy that dependency. This chapter delivers the pattern in full — and, as a capstone for Part XII, ties together nearly every JVM-adjacent chapter this handbook has built since Chapter 30.

---

## 2. Why Depending on an Abstraction Isn't Enough by Itself

```java
class Car {
    private Engine engine = new CombustionEngine();   // still tightly coupled!
}
```

Even though `engine`'s *declared type* is the `Engine` interface (Chapter 18), this class still decides, internally, exactly which concrete `Engine` to use — genuinely no better than depending on `CombustionEngine` directly, since nothing about `Car` can ever use a different engine without editing `Car`'s own source. **Dependency Inversion (Chapter 34 §6) requires that the *choice* of concrete implementation happen externally, and be handed in — that's Dependency Injection.**

---

## 3. Three Forms of Dependency Injection

### 3.1 Constructor Injection

```java
class Car {
    private final Engine engine;   // final — Chapter 10
    Car(Engine engine) { this.engine = engine; }
}
```

This is exactly Chapter 22 §6.2's original `Car`/`Engine` example, already using constructor injection before this chapter named it. Its dependency can be `final` (Chapter 10), guaranteeing it's set exactly once and never reassigned; the object can never exist in a state missing a required dependency, directly extending Chapter 12's "never constructible into an invalid state" principle to a class's *dependencies*, not just its own field values.

### 3.2 Setter Injection

```java
class Car {
    private Engine engine;
    void setEngine(Engine engine) { this.engine = engine; }
}
```

Allows a dependency to be optional or reconfigured after construction — but the tradeoff is real: `engine` can't be `final`, and the object can genuinely exist, temporarily, without it set at all. Reserve this form specifically for dependencies that are legitimately optional or need to change after construction.

### 3.3 Field Injection

```java
class Car {
    @Autowired
    private Engine engine;   // populated by a framework, via reflection — no constructor or setter needed
}
```

The most concise to write, but the weakest of the three, for concrete, specific reasons: the field can't be `final` (a framework typically sets it *after* construction, via `setAccessible(true)` — Chapter 30 §4); a class's actual dependencies are no longer visible just by reading its constructor signature; and testing without a DI framework present becomes harder, since a plain `new Car()` call leaves `engine` unset entirely, with no constructor parameter to supply it directly.

---

## 4. Manual DI vs. Container-Managed DI

**Manual DI** — a developer explicitly writes the wiring code, typically in one place often called the *composition root*:

```java
Engine engine = new ElectricEngine();
Car car = new Car(engine);   // wired by hand, explicitly, right here
```

Fully transparent and easy to trace — but tedious to hand-write as an application's object graph grows large and deeply interconnected.

**Container-managed DI** (Spring-style) — a framework scans classes, builds the dependency graph automatically, and constructs and wires everything itself, handing your code fully-assembled objects it never had to construct by hand. This is where Chapter 30 (reflection) and Chapter 31 (annotations) stop being abstract mechanics and become directly, concretely what's happening: the framework's scan is Chapter 30 §3's `getDeclaredFields()`/`getDeclaredConstructors()`; deciding *what* to inject is Chapter 31 §5's `isAnnotationPresent(...)` check on `@Autowired`; actually constructing objects is Chapter 30 §5's `Constructor.newInstance(...)`; and populating fields directly is Chapter 30 §4's `setAccessible(true)` plus `Field.set(...)`. Chapter 31 §6's tiny audit-framework example used exactly this machinery for a different purpose — a real DI container is the same toolkit, aimed at construction and wiring instead of auditing.

**Worth knowing directly, as current, practical Spring guidance:** modern Spring recommends constructor injection over field injection, for precisely the reasons in §3.1 vs. §3.3 — despite `@Autowired`-on-a-field being extremely common in older tutorials and legacy code.

---

## 5. Inversion of Control

Dependency Injection is one concrete implementation of a broader idea called **Inversion of Control (IoC)**. Traditionally, a class controls its own flow — it calls `new` itself, decides exactly what it needs, and when. With IoC, that control is **inverted**: an external framework — often called an "IoC container" — decides what to construct, in what order, and hands your code fully-wired objects, rather than your code ever calling `new` on its own dependencies. This is worth knowing by name specifically because "IoC container" is standard, constant terminology throughout Spring's own documentation.

---

## 6. Real-World Example

**Manual DI (composition root):**

```java
Engine engine = new ElectricEngine();
Car car = new Car(engine);

PayrollService payrollService = new PayrollService();   // depends on nothing external
Employee mgr = new Manager("M001", "Asha", 95000, "", "");
```

**Conceptually equivalent, container-managed (Spring-style):**

```java
@Component
public class Car {
    private final Engine engine;

    @Autowired   // constructor injection — the recommended form (§4)
    public Car(Engine engine) {
        this.engine = engine;
    }
}

@Component
public class ElectricEngine implements Engine { ... }
```

No code anywhere calls `new Car(...)` explicitly — a Spring container scans for `@Component`-annotated classes (Chapter 31), finds `Car`'s constructor and its `Engine` dependency, locates a class implementing `Engine` (here, `ElectricEngine`), constructs it via reflection (Chapter 30 §5), and passes it into `Car`'s constructor automatically — precisely the mechanism §4 walked through, now shown in the syntax it actually appears as in real Spring Boot code.

---

## 7. Best Practices

- Prefer constructor injection by default — immutable dependencies (Chapter 10), guaranteed valid construction (Chapter 12), an explicit, readable dependency list, and the easiest form to test without a framework present (§3.1).
- Reserve setter injection specifically for genuinely optional or reconfigurable dependencies (§3.2).
- Avoid field injection in new code despite its brevity — the costs in §3.3 are real, and this is current, mainstream Spring guidance, not just this handbook's opinion.
- For small applications, plain manual DI (§4) is often entirely sufficient — pulling in a full DI framework for a handful of objects is exactly the kind of over-engineering Chapter 1 §1.14 and Chapter 34 §9 both already cautioned against.

## 8. Common Mistakes

- ⚠️ Constructing a concrete dependency internally (`new CombustionEngine()` inside `Car`) while believing the class is "using DIP" just because the field's declared type is an interface (§2) — the concrete choice still has to be made externally to genuinely satisfy Dependency Inversion.
- ⚠️ Defaulting to field injection out of habit or brevity, without weighing its testability and immutability costs against constructor injection (§3.3).
- ⚠️ Reaching for a full DI framework for a small application where a simple, manually-wired composition root (§4) would be clearer and entirely sufficient.

## 9. Interview Perspective

**Frequently Asked**

- *"What is Dependency Injection?"* — A pattern where an object's dependencies are supplied from outside — via constructor, setter, or field — rather than the object constructing or looking them up itself (§2, §3).
- *"What are the three forms of DI, and which is recommended?"* — Constructor, setter, and field injection; constructor injection is the generally recommended default, for immutability, guaranteed valid state, and testability (§3, §7).
- *"What is Inversion of Control, and how does it relate to DI?"* — IoC is the broader idea of handing control over object construction and wiring to an external framework rather than a class managing it itself; DI is one specific, concrete way of implementing that idea (§5).

**Tricky Question**

- *"How does Spring actually perform field injection into a `private` field under the hood?"* — Reflection (Chapter 30 §4): the container uses `setAccessible(true)` to bypass normal access checks, then `Field.set(...)` to assign the dependency directly — the exact mechanism Chapter 30 covered in full, now applied specifically to wiring rather than the generic example used there.

**Common Misconception**

- Believing Dependency Injection requires a framework like Spring. It doesn't — manual DI (§4), wiring objects by hand in a composition root, is genuinely Dependency Injection too; a framework only automates the wiring at scale, using the reflection-and-annotation machinery Chapters 30 and 31 already built.

---

## 10. Summary

- Dependency Injection supplies an object's dependencies from outside, rather than letting the object construct or choose them internally — this is what makes Chapter 34's Dependency Inversion principle actually real in code, not just nominal.
- Constructor injection (the recommended default), setter injection (for genuinely optional dependencies), and field injection (concise but weaker — no immutability, hidden dependencies, harder to test) are the three forms.
- Manual DI wires objects by hand in a composition root; container-managed DI (Spring-style) automates this using exactly the reflection (Chapter 30) and annotation (Chapter 31) mechanics this handbook already built.
- Dependency Injection is one specific implementation of the broader Inversion of Control idea — handing construction and wiring control to an external framework.

## 11. Quick Revision

- DI supplies dependencies externally — the concrete realization of Chapter 34's DIP.
- Constructor injection (recommended) > setter injection (optional deps) > field injection (concise but weakest — no `final`, hidden deps, harder to test).
- Manual DI = hand-wired composition root. Container-managed DI = automated via reflection + annotations (Ch. 30, 31).
- IoC = the broader idea; DI = one specific implementation of it.

## 12. Self Assessment

1. Explain why `private Engine engine = new CombustionEngine();` inside `Car` doesn't genuinely satisfy Dependency Inversion, even though `engine`'s declared type is an interface.
2. Compare constructor, setter, and field injection on exactly two dimensions: whether the dependency can be `final`, and how visible the class's full dependency list is.
3. Walk through, step by step, what a Spring-style container actually does — in terms of Chapters 30 and 31's specific mechanics — to inject a dependency into an `@Autowired` field.
4. What is Inversion of Control, and how does Dependency Injection relate to it as a broader idea versus a specific implementation?
5. Give a concrete scenario where manual DI (a hand-wired composition root) is clearly preferable to pulling in a full DI framework.

---

## What's Next

Part XII (Object-Oriented Design) is now complete. **Chapter 36 — Object Lifecycle** opens Part XIII (JVM Internals), the handbook's final part. It assembles the complete lifecycle of an object — creation (Chapter 3), the eligibility conditions for garbage collection, and finalization — closing the loop on every "we'll cover garbage collection fully in Chapter 38" pointer this handbook has made since Part II.
