# Chapter 21 — Aggregation

**Part X: Object Relationships**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain Aggregation precisely as a whole-part relationship where the parts survive the whole — and place it correctly against Chapter 20's plain Association.
- Recognize the concrete, mechanical signal that distinguishes aggregation in actual code, not just in description.
- Reclassify a relationship you've already built (`Department`/`Employee`, Chapter 20) more precisely, now that a sharper vocabulary is available.

---

## 1. Introduction

Chapter 20 §2.1 named Aggregation as one of two stronger, more specific sub-kinds of Association. This chapter delivers it: a whole-part relationship where, despite the "whole" conceptually containing the "parts," those parts have a lifecycle genuinely independent of it.

> This chapter assumes Chapter 20's Association vocabulary and the `Department`/`Employee` bidirectional relationship built there completely. It doesn't re-explain reference mechanics, unidirectional/bidirectional, or multiplicity — only refines what *kind* of association that relationship actually is.

---

## 2. Theory — What Aggregation Actually Is

> **Aggregation is a whole-part (has-a) relationship where the part can exist independently of the whole, and can be reassigned to, or shared with, a different whole without being destroyed.**

### 2.1 The Test

Ask directly: *if the whole is destroyed, does the part still make sense on its own — and could it belong to a different whole instead?* If yes, it's aggregation. (If no — if the part genuinely cannot exist meaningfully outside this specific whole — that's Composition, Chapter 22's stronger relationship.)

### 2.2 Reclassifying `Department`/`Employee`

Chapter 20 built `Department` and `Employee` as a general bidirectional association, without asking exactly what *kind*. Now the vocabulary is sharper: apply §2.1's test. If `Department("Engineering")` is dissolved, does its `Manager` cease to exist? No — that `Manager` object continues to exist, and can simply be reassigned to a different `Department` altogether. This passes the test cleanly: **`Department`/`Employee` is Aggregation, specifically** — not just Association in the general sense Chapter 20 introduced it as.

---

## 3. The Mechanical Signal — Where the Part Gets Created

Aggregation and Composition (Chapter 22) use identical Java syntax — reference fields, exactly like plain Association (Chapter 20 §2). The distinguishing signal isn't syntax at all; it's **where the part object gets created**:

```java
public class Department {
    private String name;
    private List<Employee> employees = new ArrayList<>();   // an empty list — not pre-populated

    public Department(String name) {
        this.name = name;
        // notice: no `new Employee(...)` happens anywhere in here
    }

    public void addEmployee(Employee emp) {   // a pre-existing Employee, created elsewhere, is passed in
        employees.add(emp);
        emp.setDepartment(this);
    }
}
```

`Department`'s constructor never creates an `Employee` itself — every `Employee` it holds was constructed independently, somewhere else, and simply *handed to* the `Department` afterward. This is the concrete, visible marker of aggregation: **the whole receives its parts from outside, rather than manufacturing them itself.** Chapter 22 will show the opposite pattern precisely, for contrast.

---

## 4. Parts Outlive and Outmove the Whole

```java
Department engineering = new Department("Engineering");
Manager mgr = new Manager("M001", "Asha", 95000);

engineering.addEmployee(mgr);
// ... later, Engineering is dissolved as a department ...
engineering.removeEmployee(mgr);
mgr.setDepartment(null);        // mgr still fully exists — just currently unassigned

Department sales = new Department("Sales");
sales.addEmployee(mgr);          // the SAME Manager object, reassigned, not recreated
```

No `Manager` object was ever destroyed or recreated across this sequence — `mgr` is the exact same object throughout, simply reassigned between two different `Department` "wholes." This is Aggregation's defining behavior, made concrete.

---

## 5. UML Notation — A Brief Note

Aggregation is conventionally drawn in UML class diagrams with a **hollow (unfilled) diamond** at the "whole" end of the relationship line:

```
Department ◇──────── Employee
  (whole)   hollow      (part)
            diamond
```

Composition (Chapter 22) uses a **filled** diamond in the identical position — the visual distinction mirrors the lifecycle distinction precisely. This handbook is text-based, but recognizing this notation is worth having for reading design documents and diagrams elsewhere.

---

## 6. Best Practices

- Let the "whole" *receive* its parts through a constructor parameter or a method like `addEmployee()` (§3), rather than constructing them internally — this keeps the aggregation relationship honest, both in behavior and in how the code reads.
- Name methods to reflect the non-owning relationship — `addEmployee`/`removeEmployee` read naturally as "associate/disassociate," not "create/destroy."
- Don't let a "whole" object's cleanup logic accidentally destroy its aggregated parts — dissolving a `Department` should never imply deleting its `Employee` objects.

## 7. Common Mistakes

- ⚠️ Writing aggregation-shaped code (a "whole" holding a collection of "parts") but then adding cleanup logic that destroys the parts along with the whole — that's actually Composition's behavior (Chapter 22), applied where it doesn't belong.
- ⚠️ Having the "whole" construct its own parts internally (`new Employee(...)` inside `Department`'s constructor) while still conceptually calling the relationship aggregation — §3's mechanical signal is what actually determines this, not the label chosen for it.
- ⚠️ Treating Aggregation and Composition as interchangeable synonyms for "has-a with objects" — the lifecycle distinction (§2.1's test) is the entire point of having two separate names.

## 8. Interview Perspective

**Frequently Asked**

- *"What is Aggregation?"* — A whole-part relationship where the part can exist independently of the whole and be reassigned elsewhere (§2).
- *"How is Aggregation different from plain Association?"* — Association (Chapter 20) is the general has-a/uses-a category with no implied whole-part structure; Aggregation adds a specific whole-part shape while still keeping the parts' lifecycle fully independent (§2.1).
- *"Does Java have special syntax for Aggregation?"* — No — identical reference-field mechanics to any Association (Chapter 20 §2); the distinction is a design-level lifecycle question, not a language feature (§3).

**Tricky Question**

- *"How would you tell, just by reading a class's code, whether it represents Aggregation or Composition?"* — Look at where the "part" objects are created (§3): if the "whole" receives already-constructed parts from outside (via a constructor parameter or an `addX()` method), it's aggregation; if the "whole" constructs its parts internally, tying their existence to its own, that's Composition (Chapter 22).

**Common Misconception**

- Believing Aggregation requires some special Java construct to express. It doesn't — Chapters 20, 21, and 22 all use the exact same reference-field mechanics; what changes between them is entirely how the surrounding code manages creation and destruction, which is a design discipline, not a syntax feature.

---

## 9. Summary

- Aggregation is a whole-part relationship where the part survives the whole and can be reassigned elsewhere — a stronger, more specific version of Chapter 20's general Association.
- The `Department`/`Employee` relationship built in Chapter 20 is now precisely classified as Aggregation, using the test: does the part outlive the whole and remain reassignable?
- The concrete, code-visible signal for aggregation is that the "whole" receives its parts from outside rather than constructing them itself.
- UML represents aggregation with a hollow diamond at the whole's end — Composition (next) uses a filled one.

## 10. Quick Revision

- Aggregation = whole-part, but parts are independently lived and reassignable.
- Test: does the part survive the whole's destruction, and could it belong elsewhere? Yes → Aggregation.
- Mechanical signal: the whole receives pre-built parts (constructor param / `addX()`), rather than creating them itself.
- Same Java syntax as plain Association — the difference is entirely in lifecycle-management code, not the language.

## 11. Self Assessment

1. Apply §2.1's test to `Department`/`Employee` explicitly — walk through why it qualifies as Aggregation rather than plain Association or Composition.
2. What specific line of code would you look for to determine whether a "whole" class creates its "parts" itself, versus receiving them from outside?
3. Write a short sequence showing an `Employee` object surviving the dissolution of one `Department` and being reassigned to another, without ever being reconstructed.
4. Why does Aggregation use exactly the same Java syntax as plain Association, and what does that imply about how to actually identify it in a codebase?
5. Describe, in your own words, what would need to change about `Department`'s code for its relationship with `Employee` to become Composition instead of Aggregation.

---

## What's Next

**Chapter 22 — Composition** completes Part X's relationships trilogy: the strongest whole-part relationship, where the parts cannot meaningfully exist without the whole and are destroyed alongside it — built by construction inside the whole itself, the exact opposite of this chapter's §3 signal.
