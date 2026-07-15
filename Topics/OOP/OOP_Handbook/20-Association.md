# Chapter 20 — Association

**Part X: Object Relationships**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain Association precisely, and place it correctly against Inheritance's is-a relationship (Chapter 15).
- Distinguish unidirectional from bidirectional association, and explain the specific bug bidirectional associations invite.
- Recognize that Association is a design vocabulary describing a pattern you've already been using since Chapter 3, not a new language feature.
- Understand where Association sits relative to Aggregation and Composition — the two stronger, more specific relationships Chapters 21 and 22 build on top of it.

---

## 1. Introduction

Chapter 15 §2.1 drew a firm line: "Manager IS-A Employee" belongs to inheritance, but "Employee HAS-A Department" is a fundamentally different kind of relationship, deliberately set aside for this Part. This chapter opens Part X with the most general form that has-a relationship takes: **Association**.

> This chapter does not re-explain the is-a test or when to reach for inheritance — Chapter 15 §2.1 is definitive for that distinction. This chapter is scoped to has-a/uses-a relationships exclusively.

---

## 2. Theory — What Association Actually Is

> **Association is a relationship where one class uses or connects to another — typically by holding a reference to it — without either object owning or controlling the other's lifecycle.**

This is worth stating plainly: **Association isn't a new Java language feature.** It's a design vocabulary for something this handbook has been doing since Chapter 3 — one object holding a reference to another. What's new in this chapter isn't mechanics, it's the *design perspective*: naming a pattern and reasoning carefully about it, rather than writing it without ever asking what kind of relationship it represents.

### 2.1 Association Is the Umbrella Term

Association is the general category; **Aggregation** (Chapter 21) and **Composition** (Chapter 22) are both specific, *stronger* kinds of association, distinguished by exactly one thing: what happens to the lifecycle of the associated objects. This chapter covers the general, weakest form — plain association, where the two objects' lifecycles are completely independent of each other. Chapters 21 and 22 progressively tighten that independence.

---

## 3. Unidirectional vs. Bidirectional Association

**Unidirectional** — one class holds a reference to the other, but not the reverse:

```java
public abstract class Employee implements Payable {
    private Department department;   // Employee knows its Department
    // Department has no field referencing back to any specific Employee
}
```

**Bidirectional** — both classes hold references to each other:

```java
public class Department {
    private List<Employee> employees = new ArrayList<>();
    // Department now also knows its Employees
}
```

```
Heap

┌───────────────────┐        department        ┌───────────────────┐
│  Employee object    │ ───────────────────────► │  Department object │
│                     │ ◄─────────────────────── │                     │
└───────────────────┘        employees (list)    └───────────────────┘
```

Both objects exist independently on the Heap (Chapter 3 §6) — each simply holds a reference to the other, exactly the aliasing mechanism Chapter 3 §7 already established, just applied deliberately between two different classes rather than two variables of the same class.

---

## 4. Multiplicity

Associations also carry a **multiplicity** — how many instances of one class relate to how many of the other:

- **One-to-one** — one `Employee` has exactly one `Badge`.
- **One-to-many / many-to-one** — one `Department` has many `Employee`s; from the `Employee` side, many employees relate to one `Department`.
- **Many-to-many** — many `Employee`s can work on many `Project`s, and each `Project` can have many `Employee`s.

Java expresses "many" side of a relationship with a collection field (`List<Employee>`, as in §3), and "one" side with a plain reference field (`Department department`, also as in §3) — multiplicity is a design property described in this vocabulary, not a distinct syntax.

---

## 5. Real-World Example — Upgrading `department`

Since Chapter 13, `Employee` has had `protected String department;` — a plain string. Now that Association is available as a design tool, it's worth upgrading that into a real relationship:

```java
public class Department {
    private String name;
    private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
        emp.setDepartment(this);   // keep both sides in sync — see §6
    }
}
```

```java
public abstract class Employee implements Payable {
    private Department department;   // was `protected String department;` through Chapter 19

    public void setDepartment(Department department) {
        this.department = department;
    }
}
```

```java
Department engineering = new Department("Engineering");
Manager mgr = new Manager("M001", "Asha", 95000);
engineering.addEmployee(mgr);   // establishes the bidirectional link both ways at once
```

A plain string could never express "this Employee's Department also needs to know about them" — this is exactly the kind of relationship a string field can't model, and exactly what Association, properly applied, is for.

---

## 6. The Bidirectional Sync Bug

This is the single most important practical risk this chapter covers: **in a bidirectional association, nothing in the language keeps both sides consistent automatically.**

```java
Department engineering = new Department("Engineering");
Manager mgr = new Manager("M001", "Asha", 95000);

engineering.employees.add(mgr);   // ✘ only one side updated
// mgr.department is still null — the two objects now disagree about their own relationship
```

If `addEmployee()` (§5) only updates `Department`'s list and forgets to also call `emp.setDepartment(this)`, the two objects fall out of sync — `engineering.employees` contains `mgr`, but `mgr.getDepartment()` returns `null`. This isn't a compiler error; it's a silent logical inconsistency, and it's exactly why §5's `addEmployee()` deliberately updates both sides in one method, rather than leaving callers to remember to do it themselves in two separate places.

---

## 7. Best Practices

- Prefer unidirectional association unless bidirectional navigation is genuinely needed in both directions — every bidirectional link is another place synchronization can silently drift (§6).
- When a bidirectional association is necessary, centralize the logic that establishes it (like `addEmployee()` in §5) so both sides are always updated together, in one place, rather than trusted to every caller.
- Don't reach for a plain `String`/primitive field to represent what's actually a relationship to another meaningful object — §5's `department` upgrade is exactly the kind of refactor worth making once the relationship's real shape becomes clear.

## 8. Common Mistakes

- ⚠️ Updating only one side of a bidirectional association and leaving the other stale (§6) — a genuine, common, hard-to-spot bug.
- ⚠️ Conflating Association with Inheritance — a `Department` is not a kind of `Employee` (that would fail Chapter 15 §2.1's is-a test); it's something an `Employee` *has*, entirely different relationships.
- ⚠️ Defaulting to bidirectional associations everywhere "just in case," when most relationships only ever need to be navigated in one direction.

## 9. Interview Perspective

**Frequently Asked**

- *"What is Association in OOP?"* — A has-a/uses-a relationship where one class references another without owning its lifecycle, typically implemented as a plain reference field (§2).
- *"Is Association a language feature?"* — No — it's a design vocabulary for the reference-holding pattern this handbook has used since Chapter 3; nothing about it requires new Java syntax (§2).
- *"What's the risk with bidirectional associations specifically?"* — Both sides must be kept manually in sync; nothing in the language enforces it, and drifting out of sync is a silent, not a compile-time, failure (§6).

**Tricky Question**

- *"If `Department.employees` contains a `Manager`, but that `Manager`'s `department` field is `null`, what does that indicate?"* — A bidirectional association that's only been updated on one side (§6) — exactly the bug a centralized method like `addEmployee()` is meant to prevent.

**Common Misconception**

- Treating Association, Aggregation, and Composition as three unrelated things to memorize separately. Association is the general category; the other two are specific, progressively stronger versions of it, distinguished purely by lifecycle dependency — a distinction Chapters 21 and 22 build directly on top of this chapter's foundation.

---

## 10. Summary

- Association is a has-a/uses-a relationship between independently-lived objects, implemented with ordinary reference fields — a design vocabulary, not new syntax.
- Associations can be unidirectional or bidirectional, and carry a multiplicity (one-to-one, one-to-many, many-to-many).
- Bidirectional associations require manual synchronization on both sides — nothing in Java enforces consistency automatically, and this is a genuine, common bug source.
- Association is the umbrella term; Aggregation (Chapter 21) and Composition (Chapter 22) are stronger, more specific relationships distinguished by lifecycle dependency.

## 11. Quick Revision

- Association = has-a/uses-a, independent lifecycles, plain reference field — not new syntax, a design lens on Chapter 3's reference model.
- Unidirectional: one side references the other. Bidirectional: both do, and must be kept in sync manually.
- Multiplicity: one-to-one, one-to-many, many-to-many — expressed via plain fields vs. collection fields.
- Association is the general category; Aggregation and Composition (next two chapters) are stronger sub-kinds.

## 12. Self Assessment

1. Explain why `Employee`'s original `protected String department;` field couldn't express a bidirectional relationship the way a `Department` object reference can.
2. Write a method that establishes a bidirectional association between an `Employee` and a `Department` correctly, in one place, and explain what bug it prevents.
3. Give an example of a one-to-many association and a many-to-many association from this chapter's domain, and explain how each is expressed structurally in Java.
4. Why is Association described as "not a new language feature" in this chapter — what mechanic from Chapter 3 is it built entirely on top of?
5. What is the one property that will distinguish Aggregation and Composition from the plain Association covered in this chapter?

---

## What's Next

**Chapter 21 — Aggregation** tightens plain Association's independence by exactly one notch: a "has-a" relationship where the whole conceptually owns its parts, but the parts can still meaningfully outlive the whole — building directly on this chapter's `Department`/`Employee` relationship to show where weak ownership begins.
