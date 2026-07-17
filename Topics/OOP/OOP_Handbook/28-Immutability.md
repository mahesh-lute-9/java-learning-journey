# Chapter 28 — Immutability

**Part XI: Advanced OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- State the complete checklist for genuine immutability — not just `final` fields, which Chapter 10 §3.4 already warned is insufficient.
- Identify the specific gap that both hand-written `final`-field classes and records (Chapter 26) can silently leave open, and close it with defensive copying.
- Explain why immutable objects are safe to share across threads without synchronization, connecting back to Chapter 1's very first argument for OOP.

---

## 1. Introduction

Chapter 10 §3.4 made a promise this chapter finally keeps: "`final` on a reference does not mean immutable... building a class whose objects genuinely cannot change after construction needs more than `final` fields alone." Chapter 26 §2 showed records generate `final` fields automatically — but even that, on its own, isn't the full guarantee either. This chapter closes both gaps completely.

---

## 2. Theory — What Genuine Immutability Requires

> **An immutable object is one whose observable state can never change after construction — through any method, any code path, or any reference held anywhere else in the program.**

The full checklist:

1. All fields `final` (Chapter 10).
2. No setters, and no method that mutates a field after construction.
3. The class itself `final`, or otherwise structured so a subclass can't undermine the guarantee (Chapter 27 §2's sealing, or simple `final`, Chapter 10 §5).
4. **Defensive copying** for any field whose type is itself mutable (a `List`, an array, a `Date`) — both on the way *in*, at construction, and on the way *out*, through any accessor.

Item 4 is the one both `final` fields alone and a naive record silently miss — and it's this chapter's entire focus.

---

## 3. The Gap `final` Alone Leaves Open

```java
public final class ImmutableEmployee {
    private final String name;
    private final List<String> skills;

    public ImmutableEmployee(String name, List<String> skills) {
        this.name = name;
        this.skills = skills;   // ✘ no defensive copy — still holds the CALLER's list
    }

    public List<String> getSkills() {
        return skills;          // ✘ no defensive copy — hands out the INTERNAL list directly
    }
}
```

Both `name` and `skills` are `final` — neither field can ever be *reassigned*. But this class is not actually immutable:

```java
List<String> mutableList = new ArrayList<>(List.of("Java"));
ImmutableEmployee emp = new ImmutableEmployee("Asha", mutableList);

mutableList.add("Spring Boot");          // mutates the SAME list emp is holding — leak #1
System.out.println(emp.getSkills());     // ["Java", "Spring Boot"] — "immutable" object just changed

emp.getSkills().add("Docker");           // mutates the SAME list through the getter — leak #2
System.out.println(emp.getSkills());     // ["Java", "Spring Boot", "Docker"]
```

Neither line reassigns `skills` — the `final` guarantee (Chapter 10) is technically upheld the entire time. But the *object the field points to* was never protected at all, and it's mutable from two separate directions: the caller's original reference, and the getter's returned reference. This is exactly Chapter 10 §3.4's warning, now demonstrated concretely.

---

## 4. The Fix: Defensive Copying, Both Directions

```java
public final class ImmutableEmployee {
    private final String name;
    private final List<String> skills;

    public ImmutableEmployee(String name, List<String> skills) {
        this.name = name;
        this.skills = new ArrayList<>(skills);   // defensive copy IN — a genuinely separate list
    }

    public List<String> getSkills() {
        return List.copyOf(skills);               // defensive copy OUT — another separate list
    }
}
```

Now, mutating the caller's original `mutableList` has no effect on `emp` at all, and calling `emp.getSkills().add(...)` mutates only a throwaway copy, never `emp`'s own internal state. **Both copies are independently necessary** — fixing only the constructor still leaves the getter leaking the internal list directly, and fixing only the getter still lets the constructor absorb a reference the caller can keep mutating.

---

## 5. Records Aren't Automatically Fully Immutable Either

Chapter 26 §2 established that a record's fields are always `final`. That's item 1 of §2's checklist, automatically — but items 2 and 3 come for free too (no setters exist, and records are implicitly `final`, Chapter 26 §4). **Item 4 does not** — a naive record with a mutable component has exactly §3's leak:

```java
public record NaiveEmployee(String name, List<String> skills) { }
// still leaks through the constructor and the auto-generated skills() accessor,
// for exactly the same reason as §3's hand-written version
```

The fix combines Chapter 26 §6's compact constructor with an explicitly overridden accessor:

```java
public record ImmutableEmployeeRecord(String name, List<String> skills) {
    public ImmutableEmployeeRecord {                    // compact constructor (Ch. 26 §6)
        skills = List.copyOf(skills);                    // defensive copy IN
    }
    // List.copyOf already returns an unmodifiable view, so no further copy is needed OUT —
    // the accessor `skills()` returns exactly what the compact constructor already secured.
}
```

Because `List.copyOf(...)` both copies the data *and* returns a genuinely unmodifiable list, this one line inside the compact constructor closes both directions of §3's leak at once — the auto-generated `skills()` accessor now safely returns that same unmodifiable copy every time.

---

## 6. Why This Matters: Thread Safety Without Synchronization

This closes a thread this handbook opened all the way back in Chapter 1 §1.3: Procedural Programming's core weakness was shared, unprotected, mutable data. A genuinely immutable object is the strongest possible answer to that problem — because its state can never change after construction, **multiple threads can read it simultaneously with no synchronization needed at all.** There's no "changing" for two threads to race over, no lock required to protect a value that's already permanently fixed. A mutable object shared across threads needs careful coordination to avoid corruption; a properly immutable one needs none, by construction — not because anyone was careful, but because there's nothing left to protect.

---

## 7. Best Practices

- Prefer immutable types for anything representing a **value** or a fact at a point in time — a `Money` amount, a date, a completed transaction record — rather than an evolving entity.
- Use records with a compact constructor (§5) as the default modern path to immutability, rather than hand-writing the full checklist from scratch each time.
- Reach for `List.copyOf()`, `Set.copyOf()`, `Map.copyOf()`, or `Collections.unmodifiable*` wrappers for defensive copies rather than writing manual copy loops.
- Audit both directions independently — a defensive copy in the constructor alone, or in the accessor alone, is not sufficient (§4).

## 8. Common Mistakes

- ⚠️ Believing `final` fields alone make a class immutable — Chapter 10 §3.4 warned about this from the start; §3 demonstrates the actual leak.
- ⚠️ Defensively copying only in the constructor, and forgetting the accessor still leaks the internal reference directly (§4).
- ⚠️ Assuming a record is automatically, fully immutable regardless of its component types — only true for components that are themselves immutable (`String`, primitives); mutable components need the same compact-constructor treatment as a hand-written class (§5).
- ⚠️ Forgetting that a mutable field's *class itself* being mutable (not just the containing object) is what creates the leak in the first place — the containing object being immutable doesn't retroactively make a `List` field immutable too.

## 9. Interview Perspective

**Frequently Asked**

- *"What actually makes an object immutable, beyond `final` fields?"* — No setters, a `final` (or otherwise protected) class, and — the commonly-missed piece — defensive copying for any mutable-typed field, both into the constructor and out of any accessor (§2).
- *"Why is defensive copying needed in both the constructor and the getter?"* — Because each is an independent leak: the constructor can absorb a reference the caller keeps mutating; the getter can hand out the internal reference directly for the caller to mutate (§3, §4).
- *"Are records automatically immutable?"* — Only for components that are themselves immutable; a `List` or array component still needs a compact constructor to defensively copy it (§5).

**Tricky Question**

- *"An object has only `final` fields and no setters, yet its observable state changes after construction. How?"* — A `final` field holding a reference to a mutable object (like a `List`) — the field itself never gets reassigned, but the object it points to can still be mutated through any reference to it, including one the caller kept (§3).

**Common Misconception**

- Treating "immutable" and "has `final` fields" as synonyms. `final` (Chapter 10) is a necessary ingredient, not a sufficient one — genuine immutability requires actively protecting every mutable-typed field from external mutation in both directions, which is exactly the piece this chapter adds on top of Chapter 10's foundation.

---

## 10. Summary

- Genuine immutability requires `final` fields, no setters, a protected class, and — the commonly missed piece — defensive copying for any mutable-typed field, both into the constructor and out of any accessor.
- `final` fields alone (Chapter 10) only prevent reassignment; they say nothing about whether the object a reference points to can still be mutated.
- Records (Chapter 26) generate `final` fields automatically but still need a compact constructor's defensive copy for mutable-typed components.
- Immutable objects require no synchronization to share safely across threads, since there is no changing state left to protect — a direct, practical resolution of the shared-mutable-data problem Chapter 1 §1.3 first identified.

## 11. Quick Revision

- Immutability checklist: final fields + no setters + protected class + defensive copies (both directions) for mutable-typed fields.
- `final` field ≠ immutable object — the referenced object can still be mutated through any reference to it.
- Defensive copy IN (constructor) and OUT (accessor) — both required independently.
- Records need a compact constructor's defensive copy for any mutable component — not automatic.
- Immutable objects: thread-safe with zero synchronization, since nothing ever changes.

## 12. Self Assessment

1. Explain, using a `final List<String>` field, exactly how an object's observable state can change even though the field itself is never reassigned.
2. Why are both a constructor-side and a getter-side defensive copy independently necessary — what specific leak does each one close?
3. Is `public record Team(String name, List<String> members) { }`, as written, genuinely immutable? If not, fix it.
4. Why does an immutable object require no synchronization to be safely read by multiple threads at once?
5. Connect this chapter's core lesson back to Chapter 1 §1.3 — what specific Procedural Programming problem does genuine immutability solve, and how?

---

## What's Next

**Chapter 29 — Object Cloning** picks up directly on this chapter's defensive-copying theme, formalizing the deep-vs-shallow copy distinction this chapter used informally (§4's `new ArrayList<>(skills)` is a shallow copy of the list, though a deep enough one for `String` elements) — and covering Java's built-in `clone()` mechanism, including why it's now widely considered a flawed design compared to copy constructors and factory methods.
