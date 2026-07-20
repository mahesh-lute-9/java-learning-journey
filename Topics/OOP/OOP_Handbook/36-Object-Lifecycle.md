# Chapter 36 — Object Lifecycle

**Part XIII: JVM Internals**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Trace an object's complete lifecycle, end to end, as one coherent picture assembled from mechanics built across many earlier chapters.
- Define reachability precisely — the actual basis for garbage collection eligibility, not reference counting.
- Explain "islands of isolation" — why two objects that only reference each other can still both be eligible for collection.
- Explain why `finalize()` is deprecated and discouraged in modern Java.

---

## 1. Introduction

This handbook has built an object's lifecycle in pieces, across many chapters, each deliberately deferring the full picture: Chapter 2 §6.2's lazy class loading, Chapter 3 §4's five-step creation pipeline, Chapter 6 §4's complete initialization order, Chapter 11 §3's parent-first mechanics, and a running promise — repeated in Chapter 3 §10, Chapter 9 §6, and Chapter 28 §6 — that garbage collection would be covered "fully in Chapter 38." This chapter assembles everything *except* the GC algorithm itself into one coherent birth-to-death picture; Chapter 38 covers reclamation mechanics in full.

---

## 2. The Complete Lifecycle, Assembled

```
1. CLASS LOADING (once per class — Chapter 2 §6.2)
        │
        ▼
2. OBJECT CREATION (per instance — Chapters 3, 6, 11 combined, §3 below)
        │
        ▼
3. IN USE (method calls, state changes, references passed around)
        │
        ▼
4. UNREACHABLE (no path from any GC root reaches it anymore — §4 below)
        │
        ▼
5. ELIGIBLE FOR GARBAGE COLLECTION (§5 below)
        │
        ▼
6. ACTUALLY RECLAIMED (timing and algorithm: Chapter 38, in full)
```

---

## 3. Stage 1–2: Class Loading, Then Object Creation

Before a single `Manager` object can exist, the `Manager` class itself must be loaded — once, lazily, the first time it's actively referenced (Chapter 2 §6.2), including linking (Chapter 12 §6's bytecode verification) and static initialization (Chapter 6 §4.1's `<clinit>`).

Once the class is loaded, each `new Manager(...)` follows the complete order this handbook built across three separate chapters, now shown as one synthesized diagram:

```
new Manager("M001", "Asha", 95000, "", "")
        │
        ▼
Memory allocated for the WHOLE object            (Ch. 3 §4, step 1; Ch. 11 §3)
        │
        ▼
All fields (Employee's AND Manager's) defaulted   (Ch. 3 §4, step 2)
        │
        ▼
Employee's full initialization runs via super()   (Ch. 11 §3 — parent completes entirely first)
        │
        ▼
Manager's own field initializers / instance blocks run   (Ch. 6 §4.2, source order)
        │
        ▼
Manager's own constructor body runs                (Ch. 5)
        │
        ▼
Reference returned                                  (Ch. 3 §4, step 5)
```

---

## 4. Stage 3–4: In Use, Then Unreachable — Reachability, Precisely

While in use, an object is read, mutated, passed by reference (Chapter 7 §4), and its methods dispatch dynamically based on its actual class (Chapter 16 §4.2). Eventually, it stops being reachable — and this is the concept this chapter formalizes for the first time, after being used loosely (e.g., "becomes unreachable," Chapter 16 §7, Chapter 22 §4, Chapter 28 §6) throughout the handbook.

> **An object is reachable if there exists a chain of references leading to it, starting from a GC root — a local variable in an active stack frame (Chapter 4 §5), a live static field (Chapter 4 §4), or a reference held by a running thread. An object with no such chain is unreachable.**

```java
void demo() {
    Manager mgr = new Manager("M001", "Asha", 95000, "", "");   // mgr is a GC root's reference
    // ... mgr is reachable the entire time this method is executing ...
}   // method returns — mgr's stack frame is popped (Ch. 4 §5, Ch. 7 §6) — the Manager object is now unreachable
```

This is a direct consequence of Chapter 4 §5's Stack model: once `demo()`'s frame is popped, `mgr` — the only reference to that `Manager` object — ceases to exist, and nothing else in the program can reach it anymore.

---

## 5. Stage 5: Eligibility, and the "Islands of Isolation" Case

An object becomes eligible for garbage collection the moment it becomes unreachable — which can happen because its last referencing variable goes out of scope (§4), is reassigned to something else or to `null` (the reverse of Chapter 3 §7's aliasing), or because the object *holding* the only reference to it becomes unreachable itself, transitively.

**A genuinely important, frequently-tested nuance:** Java's garbage collection is based on **reachability**, not reference counting — and this distinction matters concretely:

```java
class Node {
    Node partner;
}

Node a = new Node();
Node b = new Node();
a.partner = b;
b.partner = a;   // a and b now reference EACH OTHER

a = null;
b = null;        // no external variable references either object anymore
```

`a` and `b` still hold references to *each other* — but neither is reachable from any GC root anymore. This is called an **island of isolation**, and Java correctly identifies both objects as eligible for collection despite their mutual references, because reachability is traced from GC roots outward, not measured by counting how many references point at an object. A naive reference-counting scheme would incorrectly conclude neither object could ever be collected, since each still has one incoming reference — Java's actual reachability-based approach doesn't have this blind spot.

---

## 6. `finalize()` — A Deprecated Piece of History

Older Java code sometimes overrides `Object.finalize()` as a "last chance to clean up" before an object is reclaimed. This is now **deprecated (since Java 9) and strongly discouraged**: its timing is entirely unpredictable (there's no guarantee *when*, or even *whether*, it runs before JVM shutdown), it carries real performance costs, and it can even accidentally "resurrect" an object by creating a new reachable reference to it from inside `finalize()` itself, defeating collection entirely. Modern Java code that genuinely needs deterministic cleanup uses `try`-with-resources and `AutoCloseable` instead — a different, more reliable mechanism this handbook doesn't cover in depth, but worth knowing as the correct modern replacement.

---

## 7. Real-World Example — Tracing a Full Lifecycle

```java
public void processTemporaryHire() {
    Manager temp = new Manager("M099", "Rohan", 80000, "", "");   // creation (§3)

    System.out.println(temp.getSalary());   // in use — dynamic dispatch (Ch. 16 §4.2)

    temp.raiseSalary(5000);                  // in use — mutation (Ch. 8's `this`, Ch. 12)

}   // temp's stack frame pops — the Manager object becomes unreachable (§4),
    // eligible for collection (§5) — actual reclamation timing: Chapter 38
```

Every stage this chapter named is present in this one small method, end to end — class loading happened earlier, invisibly, the first time `Manager` was ever referenced anywhere in the program.

---

## 8. Best Practices

- Don't rely on `finalize()`, or any assumption about deterministic collection timing, for anything critical — use `try`-with-resources/`AutoCloseable` for guaranteed, timely cleanup instead (§6).
- Let references to large, no-longer-needed objects go out of scope naturally rather than obsessively nulling local variables — the JVM's reachability analysis (§4) already handles ordinary cases correctly; manual nulling is only meaningfully useful for long-lived reference holders, like static fields or caches, that would otherwise keep an object artificially reachable far longer than intended.
- Understand reachability (§4), not reference counting, as the real basis for what keeps an object alive — this resolves a surprising number of "why hasn't this been collected" or "why was this collected" questions correctly.

## 9. Common Mistakes

- ⚠️ Assuming garbage collection happens immediately, deterministically, the instant an object becomes unreachable — eligibility (§5) and actual reclamation timing (Chapter 38) are two separate things.
- ⚠️ Believing two objects that reference only each other can never be collected — the island of isolation case (§5) shows this is false; Java's reachability model handles it correctly.
- ⚠️ Relying on `finalize()` for meaningful cleanup logic, given its unpredictable timing and deprecated status (§6).

## 10. Interview Perspective

**Frequently Asked**

- *"What determines whether an object is eligible for garbage collection?"* — Reachability from a GC root (a stack variable, a live static field, or a thread reference) — not a reference count (§4, §5).
- *"Can two objects that only reference each other still be garbage collected?"* — Yes — an island of isolation (§5); Java traces reachability from roots outward, so mutual references with no external path in don't protect either object.
- *"Walk through an object's complete lifecycle."* — Class loading (once, Chapter 2) → creation (Chapters 3, 6, 11 combined, §3) → in use → unreachable (§4) → eligible for collection (§5) → actually reclaimed (Chapter 38).

**Tricky Question**

- *"If `a.partner = b; b.partner = a;` and no other variable references either, are `a` and `b` eligible for collection?"* — Yes (§5) — despite referencing each other, neither is reachable from any GC root, which is the entire basis Java actually uses, unlike a naive reference-counting scheme that would incorrectly treat them as still alive.

**Common Misconception**

- Believing Java's memory management works by reference *counting* (decrementing a count to zero, as in some other languages). It doesn't — Java uses reachability analysis from GC roots (§4), which is precisely why the island-of-isolation case (§5) resolves correctly instead of leaking memory forever.

---

## 11. Summary

- An object's complete lifecycle: class loading (once) → creation (the combined Chapter 3/6/11 pipeline) → in use → unreachable → eligible for collection → actually reclaimed (Chapter 38).
- Reachability — a chain of references from a GC root (stack variables, live static fields, thread references) — is the real basis for garbage collection eligibility, not reference counting.
- Islands of isolation — mutually-referencing objects with no path from any GC root — are still correctly identified as eligible, because Java traces reachability outward from roots rather than counting incoming references.
- `finalize()` is deprecated and unreliable; `try`-with-resources/`AutoCloseable` is the modern replacement for deterministic cleanup.

## 12. Quick Revision

- Lifecycle: load class (once) → create object (Ch3+6+11) → in use → unreachable → GC-eligible → reclaimed (Ch38).
- Reachable = chain of references from a GC root. GC roots: stack variables, live static fields, thread references.
- Islands of isolation: mutual references alone don't prevent collection — reachability from a root is what matters.
- `finalize()`: deprecated, unreliable timing — use `try`-with-resources/`AutoCloseable` instead.

## 13. Self Assessment

1. Trace the complete lifecycle of an object from `new` to eligibility for collection, naming which earlier chapter covers each stage.
2. Define reachability precisely, and name the three kinds of GC roots.
3. Explain why two objects referencing only each other can still be garbage collected, and why a naive reference-counting scheme would get this wrong.
4. Why is `finalize()` considered a design mistake in retrospect, and what should be used instead for deterministic cleanup?
5. A local variable holding the only reference to an object goes out of scope when its method returns. Explain, using Chapter 4's Stack model, exactly why the object becomes unreachable at that moment.

---

## What's Next

**Chapter 37 — Memory Management** goes one level deeper into where reachable and unreachable objects actually live — the Heap's internal generational structure (Young Generation, Old Generation), and why most objects die young — filling in the memory-region detail this chapter's lifecycle diagram treated as a single undifferentiated "Heap."
