# Chapter 22 — Composition

**Part X: Object Relationships**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain Composition as the strongest whole-part relationship, and identify its mechanical signal precisely, in contrast to Chapter 21's Aggregation.
- Apply the "independent existence" test to decide whether a relationship is Composition, Aggregation, or plain Association.
- Explain — with concrete, specific reasons, not just as a repeated slogan — why Composition is often preferred over Inheritance.
- Recognize the subtle mistake that quietly turns intended Composition into Aggregation.

---

## 1. Introduction

Chapter 21 covered Aggregation — a whole-part relationship where the part survives the whole. This chapter covers the opposite end of that same spectrum: **Composition**, where the part has no meaningful existence apart from its specific whole, and is created and destroyed alongside it. This closes Part X's relationships trilogy, and — finally, with both Inheritance (Chapter 15) and Composition fully in view — lets this handbook properly explain a principle it has quoted since Chapter 1 without justifying: "prefer Composition over Inheritance."

> This chapter assumes Chapter 20's Association vocabulary and Chapter 21's Aggregation completely, including its "mechanical signal" framing (§3 there). This chapter's own signal is the direct mirror image of that one.

---

## 2. Theory — What Composition Actually Is

> **Composition is a whole-part relationship in which the part has no independent existence apart from its specific whole — it is created by the whole and destroyed along with it.**

### 2.1 The Test

Ask: *does this "part" make sense as a standalone concept, potentially reused elsewhere or outliving this specific whole?* If the honest answer is no — if it was created exclusively to serve this one object and has no meaning detached from it — that's Composition. (Chapter 21 §2.1 asked the mirror question for Aggregation: "does the part survive the whole?")

---

## 3. The Mechanical Signal — Created Internally, Not Received

Chapter 21 §3 identified aggregation's signal: the whole *receives* pre-built parts from outside. Composition's signal is the exact opposite: **the whole constructs its own parts internally, typically inside its own constructor, and never accepts a pre-built one from external code.**

```java
public abstract class Employee implements Payable {
    private final String employeeId;
    private String name;
    private double salary;
    private final ContactInfo contactInfo;   // composition — created by Employee itself

    protected Employee(String employeeId, String name, double salary, String phone, String email) {
        this.employeeId = employeeId;
        this.name = name;
        setSalary(salary);
        this.contactInfo = new ContactInfo(phone, email);   // built HERE, by Employee, not passed in
    }
}

class ContactInfo {
    private String phone;
    private String email;

    ContactInfo(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }
}
```

Notice: there is no constructor parameter or setter anywhere that accepts a pre-built `ContactInfo` from outside `Employee`. `Employee` is the *only* code in the entire program that can ever create one, and it does so as an unavoidable part of creating itself.

---

## 4. Lifecycle Coupling

Once an `Employee` object becomes unreachable and eligible for garbage collection (full mechanics: Chapter 38), its `ContactInfo` object becomes unreachable too — assuming, as is true here, that nothing else in the program ever held a separate reference to it. There is no scenario, as there was with `Department`/`Employee` in Chapter 21 §4, where the `ContactInfo` "survives" and gets reassigned to a different `Employee`. Its entire existence is bound to the one `Employee` that created it.

---

## 5. UML Notation

Composition is conventionally drawn with a **filled (solid) diamond** at the whole's end — the direct visual counterpart to Chapter 21 §5's hollow diamond for Aggregation:

```
Employee ◆──────── ContactInfo
 (whole)   filled      (part)
           diamond
```

---

## 6. Favor Composition Over Inheritance

Chapter 1 §1.8 and Chapter 15 §10 both mentioned this principle in passing, without justifying it — this chapter is where both Inheritance and Composition are finally in view together, so the reasoning can be given properly.

### 6.1 What Inheritance Costs

Chapter 15 established that inheritance creates **tight, compile-time coupling**: a subclass is permanently bound to exactly one parent (Chapter 15 §5's single-inheritance restriction), automatically exposes *every* inherited member whether the subclass wants it or not, and — Chapter 1 §1.9 already flagged this — a deep inheritance chain (`Vehicle → Car → ElectricCar → LuxuryElectricCar`) becomes fragile: a change to a class anywhere in the chain can silently break every subclass beneath it, since they're all structurally bound to it at compile time. This is sometimes called the **fragile base class problem**.

### 6.2 What Composition Buys Instead

```java
interface Engine {
    void start();
}

class CombustionEngine implements Engine {
    public void start() { System.out.println("Vroom"); }
}

class ElectricEngine implements Engine {
    public void start() { System.out.println("Silent hum"); }
}

class Car {
    private Engine engine;   // composed — a Car HAS an Engine, isn't tightly bound to one kind

    Car(Engine engine) { this.engine = engine; }

    void start() { engine.start(); }
}
```

```java
Car combustionCar = new Car(new CombustionEngine());
Car electricCar = new Car(new ElectricEngine());
```

Instead of `ElectricCar extends Car extends Vehicle` — the deep, rigid hierarchy Chapter 1 §1.9 flagged — `Car` simply *has* an `Engine`, and which kind of `Engine` it has can be decided (and even changed) independently of `Car`'s own class definition. This avoids the fragile base class problem entirely: changing `ElectricEngine`'s internals can never silently break `Car`, because `Car` only depends on `Engine`'s interface (Chapter 18), not its implementation details. It also sidesteps Chapter 15 §5's single-inheritance limit — a class can compose as many different "parts" as it needs, where it could only ever `extends` one parent.

**The practical rule, stated precisely (not just as a slogan):** reach for inheritance only where a genuine, stable is-a relationship holds (Chapter 15 §2.1) and the shared behavior is unlikely to need swapping at runtime. Reach for composition whenever a class needs a capability or a piece of behavior that might vary, might need replacing, or doesn't represent "a specialized kind of" the containing class.

---

## 7. The Subtle Mistake: Accidentally Turning Composition Into Aggregation

```java
public class Employee {
    private ContactInfo contactInfo;

    public Employee(String employeeId, ContactInfo contactInfo) {   // ✘ accepted from outside!
        this.employeeId = employeeId;
        this.contactInfo = contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {   // ✘ also breaks exclusivity
        this.contactInfo = contactInfo;
    }
}
```

The moment `Employee` accepts a pre-built `ContactInfo` from outside — whether through a constructor parameter or a setter — the relationship quietly stops being Composition and becomes something closer to Aggregation (Chapter 21), whether or not that was the intent. If `ContactInfo` objects are meant to be exclusively owned and created by their `Employee`, §3's pattern — construct internally, expose no way to inject one from outside — must be enforced deliberately; nothing about the word "composition" itself prevents this drift if the code doesn't actually maintain it.

---

## 8. Best Practices

- Use Composition when a "part" is really just decomposed internal structure of the whole — breaking a large class into smaller, focused pieces (`ContactInfo` isolating contact-related fields) rather than representing an independently meaningful domain entity.
- Keep a composed part's class scoped tightly — often package-private, or even a private nested class (a natural fit once Chapter 23 covers nested classes formally) — since external code generally shouldn't need to reference it directly at all.
- Apply "favor composition over inheritance" (§6) as a genuine design decision per relationship, not a blanket rule that inheritance is never appropriate — Chapter 15's is-a relationships (`Manager IS-A Employee`) remain the correct tool when the relationship genuinely is one.

## 9. Common Mistakes

- ⚠️ Adding a constructor parameter or setter that accepts a pre-built "part" object, unintentionally turning Composition into Aggregation (§7) without noticing the guarantee has changed.
- ⚠️ Over-decomposing simple value fields into needless wrapper "part" classes — Composition should reflect genuine conceptual structure, not be applied mechanically to every field.
- ⚠️ Treating "favor composition over inheritance" as "never use inheritance" — Chapter 15's is-a relationships are still the right tool when they genuinely apply; the principle is about not reaching for inheritance *by default* for relationships that aren't truly is-a.

## 10. Interview Perspective

**Frequently Asked**

- *"What is Composition, and how does it differ from Aggregation?"* — The part has no independent existence and is created/destroyed with the whole (this chapter), versus Aggregation's part surviving and being reassignable (Chapter 21) — same has-a shape, opposite lifecycle answer.
- *"Why is Composition often preferred over Inheritance?"* — Give §6's concrete reasons: avoids the fragile base class problem, avoids the single-inheritance limit, and allows swapping an implementation (like `Engine`) without touching the containing class at all.
- *"What's the mechanical signal that tells Composition from Aggregation in code?"* — Whether the "whole" constructs its parts internally (Composition, §3) or receives pre-built ones from outside (Aggregation, Chapter 21 §3).

**Tricky Question**

- *"If a `Car` class accepts an `Engine` through its constructor, is that Composition or Aggregation?"* — Aggregation, by §3/§7's test — the `Engine` is received from outside, not constructed internally, so it could in principle be reused or reassigned elsewhere, even if in practice each `Engine` instance is only ever used once.

**Common Misconception**

- Believing "favor composition over inheritance" means inheritance is a design smell to avoid entirely. It's a preference for genuinely has-a-shaped, replaceable relationships over is-a-shaped ones — Chapter 15's `Manager extends Employee` remains exactly the right tool for a real is-a relationship; the principle only cautions against reaching for `extends` where composition would actually fit the relationship better.

---

## 11. Summary

- Composition is the strongest whole-part relationship: the part has no independent existence and is created and destroyed alongside its specific whole.
- Its mechanical signal is the mirror of Aggregation's: the whole constructs its parts internally, rather than receiving pre-built ones from outside.
- "Favor composition over inheritance" has concrete justification once both are fully understood: composition avoids the fragile base class problem, sidesteps single-inheritance's limit, and allows swapping an implementation without touching the containing class.
- A composed relationship can silently become Aggregation if the whole ever accepts a pre-built part from outside — the guarantee must be actively maintained, not assumed from the label alone.

## 12. Quick Revision

- Composition = whole creates and destroys the part; no independent existence.
- Signal: whole constructs its parts internally (vs. Aggregation's whole receiving them from outside).
- UML: filled diamond (vs. Aggregation's hollow one).
- Favor composition over inheritance: avoids fragile base class problem, avoids single-inheritance limit, allows runtime-swappable parts — but only where the relationship is genuinely has-a, not is-a.

## 13. Self Assessment

1. Apply §2.1's test to `Employee`/`ContactInfo` — explain why it's Composition rather than Aggregation.
2. What specific change to `Employee`'s code would silently turn its relationship with `ContactInfo` into Aggregation instead of Composition?
3. Using the `Car`/`Engine` example, explain concretely how composition avoids the fragile base class problem that a `Car extends CombustionEngine`-style hierarchy would risk.
4. Why does composition sidestep Java's single-inheritance restriction (Chapter 15 §5) in a way that inheritance itself cannot?
5. Give one example each, from any domain, of a relationship that should be Composition and one that should remain Inheritance — justify both using this chapter's and Chapter 15's tests.

---

## What's Next

Part X (Object Relationships) is now complete — Association, Aggregation, and Composition. **Chapter 23 — Nested Classes** opens Part XI (Advanced OOP), and picks up directly on this chapter's best-practice note: `ContactInfo`, a class that exists purely to serve `Employee` and is never meant to be referenced independently, is exactly the kind of class Java lets you nest *inside* another — formalizing the scoping this chapter only informally recommended.
