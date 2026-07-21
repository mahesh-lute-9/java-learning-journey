# Chapter 38 — Garbage Collection

**Part XIII: JVM Internals**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain the mark-sweep-compact algorithm, phase by phase, and why compaction specifically matters beyond tidiness.
- Explain why Young Generation collection typically uses a copying approach instead, and why that's cheaper given Chapter 37's generational hypothesis.
- Explain precisely why a "stop-the-world" pause is conceptually necessary during marking.
- Explain what `System.gc()` actually does — and doesn't — guarantee.

---

## 1. Introduction

This is the payoff for the oldest, most-repeated forward pointer in this entire handbook — first made in Chapter 3 §10, echoed in Chapter 9 §6, Chapter 28 §6, and formalized as "Stage 6" of Chapter 36's lifecycle. Chapter 36 established *when* an object becomes eligible (reachability); Chapter 37 established *where* objects live (Heap generations). This chapter covers *how* reclamation actually happens.

---

## 2. Mark-Sweep-Compact — The Foundational Algorithm

### 2.1 Mark

Starting from every GC root (Chapter 36 §4 — stack variables, live static fields, thread references), the collector traverses every reachable reference, marking each object it reaches as **live**. This is literally a graph traversal outward from the roots — anything left unmarked once traversal completes is, by Chapter 36 §4's definition, unreachable.

### 2.2 Sweep

The memory occupied by every **unmarked** object is reclaimed, made available again for future allocation.

### 2.3 Compact

Sweeping alone can leave memory **fragmented** — surviving objects scattered throughout the Heap, with small, discontinuous gaps between them rather than one large contiguous free block. Compaction moves the surviving objects together, eliminating those gaps. This matters concretely, not just cosmetically: a large object might fail to allocate even when the *total* free memory is technically sufficient, if no single contiguous gap is large enough to hold it. Compaction is what prevents that.

---

## 3. Why Minor GC Uses Copying, Not Mark-Sweep-Compact Directly

Chapter 37 §3 asserted minor GCs are fast without fully explaining the mechanism — here it is. Because the generational hypothesis (Chapter 37 §2) means the *overwhelming majority* of Eden's objects are already dead by the time a minor GC runs, it's cheaper to do the opposite of sweeping: **copy** the small number of surviving objects directly into a Survivor space, and treat the rest of Eden as reclaimed in bulk, with nothing individually swept at all. Copying also compacts as a natural side effect — the copied survivors land contiguously in the Survivor space, with no fragmentation to clean up afterward. This copying approach is efficient specifically *because* most objects die — it would be wasteful for the Old Generation, where the proportion of live objects is much higher (§4).

---

## 4. Why Major/Full GC Typically Uses Mark-Sweep-Compact

The Old Generation holds objects that have already survived repeated minor GCs (Chapter 37 §5's Mark Word age counter) — by construction, a much *higher* proportion of Old Generation objects are genuinely still live at any given collection. Copying would mean copying most of the region's contents around for little benefit; mark-sweep-compact, which only has to move survivors together once rather than copy everything wholesale, fits this proportion better. This asymmetry — copying for a mostly-dead region, mark-sweep-compact for a mostly-live one — is a direct, deliberate consequence of the generational split Chapter 37 built.

---

## 5. "Stop-the-World" Pauses, Precisely

Chapter 37 §4 used this term without explaining why it's necessary. During the **mark** phase specifically, if application threads kept mutating references while the collector was traversing the reachability graph, the collector could end up with an inconsistent picture — an object could become unreachable, or newly reachable, mid-scan, corrupting the mark phase's result. To guarantee correctness, application threads must pause during at least the critical parts of marking. Modern collectors use sophisticated techniques to minimize how much work happens during this pause — but the underlying correctness requirement, a consistent view of the reference graph while it's being traversed, is why *some* pause is conceptually unavoidable, regardless of how well-optimized a specific collector is.

---

## 6. Modern JVM Collectors — Brief Recognition, Not Deep Dive

Worth knowing by name, without implementation depth (specific collector tuning is a specialized topic beyond this handbook's OOP-fundamentals scope): **Serial GC** (simple, single-threaded, suited to small applications), **Parallel GC** (multi-threaded, throughput-focused), **G1 — Garbage First** (the default since Java 9, dividing the Heap into many small regions rather than one contiguous Young/Old split, balancing throughput against pause time), and low-pause collectors like **ZGC** and **Shenandoah**, designed for latency-sensitive applications where even brief stop-the-world pauses (§5) are unacceptable.

---

## 7. `System.gc()` — A Request, Not a Command

```java
System.gc();   // only a HINT to the JVM — never a guarantee
```

This directly reinforces Chapter 36 §9's warning against assuming deterministic collection timing, with a concrete, specific, and commonly misunderstood API fact: `System.gc()` merely *suggests* that the JVM consider running a garbage collection — it does not guarantee one happens immediately, or even at all. The JVM is always free to ignore the request entirely if it determines a collection isn't currently worthwhile.

---

## 8. Real-World Example — Tracing Reclamation

Continuing Chapter 36 §7's `temp` scenario:

```java
public void processTemporaryHire() {
    Manager temp = new Manager("M099", "Rohan", 80000, "", "");
    // ... temp used briefly ...
}   // temp's stack frame pops — temp becomes unreachable (Ch. 36 §4)
```

Conceptually, at some later point the JVM decides to run:

```
Minor GC triggered (Eden nearing capacity)
        │
        ▼
Mark: traverse from GC roots — temp's stack frame is already gone,
      so nothing reaches the Manager object it referenced. Unmarked.
        │
        ▼
Copy: only the still-marked (live) objects in Eden are copied to a
      Survivor space — the Manager object is NOT among them
        │
        ▼
Eden reclaimed in bulk — the Manager object's memory is now free
```

Note precisely when this happens: `temp` became unreachable the moment its method returned (Chapter 36 §4) — but the actual reclamation only occurs whenever the next minor GC happens to run, which could be immediately or considerably later, entirely at the JVM's discretion (§7).

---

## 9. Best Practices

- Never call `System.gc()` expecting an immediate, guaranteed collection — it's a hint (§7), and calling it unnecessarily can actually hurt performance by forcing avoidable work.
- Treat specific collector selection and tuning as a specialized topic outside ordinary application development — recognizing the concepts in this chapter (mark-sweep-compact, copying, generational asymmetry) is valuable baseline knowledge; picking and tuning a collector for a production workload is its own deep discipline.
- Understand that reachability (Chapter 36 §4) determines *eligibility*; this chapter's algorithms determine *when and how* eligible memory actually gets reclaimed — keep the two questions separate.

## 10. Common Mistakes

- ⚠️ Calling `System.gc()` and assuming it triggers an immediate collection — it's only a request the JVM may ignore (§7).
- ⚠️ Assuming every collection always compacts — some collectors or specific GC phases skip compaction when the cost doesn't justify it for that particular collection.
- ⚠️ Treating "stop-the-world" as always meaning a long, noticeable pause — modern collectors (§6) have dramatically minimized pause duration, even though the underlying correctness requirement during marking (§5) still conceptually holds.

## 11. Interview Perspective

**Frequently Asked**

- *"Explain the mark-sweep-compact algorithm."* — Mark (traverse from GC roots, flag reachable objects live), sweep (reclaim unmarked objects' memory), compact (move survivors together to eliminate fragmentation) (§2).
- *"Why does Young Generation collection typically use copying instead of mark-sweep-compact?"* — Because most Eden objects are already dead by the time a minor GC runs (Chapter 37 §2's generational hypothesis), so copying the few survivors is cheaper than marking and sweeping the whole region (§3).
- *"Does `System.gc()` guarantee a collection happens?"* — No — it's only a hint the JVM may ignore entirely (§7).

**Tricky Question**

- *"Why is a stop-the-world pause conceptually necessary during marking, even in principle?"* — Because application threads mutating references mid-traversal could give the collector an inconsistent view of the reachability graph — an object appearing reachable or unreachable depending on exactly when it's checked. Pausing during the critical marking work guarantees a consistent, correct result (§5).

**Common Misconception**

- Believing garbage collection is a single, uniform algorithm applied identically everywhere in the Heap. It isn't — Chapter 37's generational split leads directly to two different strategies here: copying for the mostly-dead Young Generation, mark-sweep-compact for the mostly-live Old Generation (§3, §4) — each chosen specifically because it fits that region's actual survival ratio.

---

## 12. Summary

- Mark-sweep-compact: mark reachable objects from GC roots, sweep unmarked memory, compact survivors to eliminate fragmentation.
- Minor GC uses copying instead, since most Young Generation objects are already dead — cheaper to copy the few survivors than sweep the whole region; copying compacts as a natural side effect.
- Major/full GC typically uses mark-sweep-compact, since the Old Generation has a much higher live-object ratio, making copying wasteful there.
- Stop-the-world pauses during marking exist to guarantee a consistent view of the reachability graph while it's being traversed — modern collectors minimize, but don't eliminate, this requirement.
- `System.gc()` is only a request the JVM may ignore — never a guarantee.

## 13. Quick Revision

- Mark-sweep-compact: mark (from GC roots) → sweep (reclaim unmarked) → compact (eliminate fragmentation).
- Minor GC = copying (few survivors, cheap). Major/full GC = mark-sweep-compact (many survivors, copying wasteful).
- Stop-the-world = necessary during marking for a consistent reachability view; modern collectors minimize duration.
- `System.gc()` = hint only, never guaranteed.
- Collectors to recognize: Serial, Parallel, G1 (default since Java 9), ZGC/Shenandoah (low-pause).

## 14. Self Assessment

1. Explain mark-sweep-compact's three phases, and specifically why compaction matters beyond just tidiness.
2. Why is a copying approach cheaper than mark-sweep-compact specifically for the Young Generation, tying your answer to Chapter 37's generational hypothesis?
3. Explain, precisely, why a stop-the-world pause is conceptually necessary during the mark phase.
4. What does `System.gc()` actually guarantee, and what's the risk of relying on it in application code?
5. Trace Chapter 36 §7's `temp` object through an actual minor GC, from "unreachable" to "memory reclaimed," using this chapter's mark/copy mechanics.

---

## What's Next

**Chapter 39 — Class Loading** covers the last major "we'll cover this fully later" thread this handbook has carried since Chapter 2 §6.2: the complete Loading → Linking (Verification, Preparation, Resolution) → Initialization pipeline, and the classloader delegation model that determines exactly which `.class` file gets loaded when a fully-qualified name (Chapter 14 §2.3) is resolved.
