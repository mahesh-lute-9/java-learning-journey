# Chapter 39 — Class Loading

**Part XIII: JVM Internals**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Walk through the complete Loading → Linking → Initialization pipeline, phase by phase.
- Explain precisely which events trigger class loading — and, just as precisely, which ones don't.
- Locate Chapter 12's bytecode verifier and Chapter 27's `PermittedSubclasses` check within the exact pipeline phase they actually belong to.
- Explain classloader delegation, and why it guarantees core Java classes can't be silently shadowed by application code.

---

## 1. Introduction

This is the last major "we'll cover this fully later" thread this handbook has carried — Chapter 2 §6.2 introduced lazy class loading informally, Chapter 12 §6 introduced the bytecode verifier without placing it in a larger pipeline, and Chapter 14 §4 previewed classpath-based lookup. This chapter assembles all of it into the complete, precise pipeline.

---

## 2. The Complete Pipeline

```
LOADING
   │  Find the .class file (via the classpath, Chapter 14 §4) and read its
   │  bytecode into the JVM, creating a runtime Class object (Chapter 19 §6,
   │  Chapter 30 §2) in Metaspace (Chapter 2 §6.1)
   ▼
LINKING
   │  ┌─ Verification  (§3.1 below)
   │  ├─ Preparation    (§3.2 below)
   │  └─ Resolution     (§3.3 below)
   ▼
INITIALIZATION
      Static field initializers and static blocks actually run — the
      <clinit> method (Chapter 6 §3.3, §4.1, §6 — already fully covered)
```

---

## 3. Linking's Three Sub-Phases

### 3.1 Verification

Before any bytecode is trusted to run, the JVM checks its structural correctness and safety. **This is precisely Chapter 12 §6's bytecode verifier**, now given its exact place in the full pipeline: this is where access modifiers (Chapter 12, Chapter 13) are re-checked independently of the compiler, and where Chapter 27 §6's `PermittedSubclasses` attribute is re-validated against whatever class claims to extend a sealed one. Both of those chapters described *what* gets checked; this is precisely *when* — during Linking, before Initialization ever runs.

### 3.2 Preparation

Memory is allocated for the class's `static` fields (Chapter 4 §4, Chapter 9), and set to their **default values** — `0`, `null`, `false` — exactly the same default-initialization concept Chapter 3 §4's step 2 established for object fields, applied here at the *class* level instead of the *object* level. No static initializer or static block has run yet — this phase only allocates and zeroes.

### 3.3 Resolution

Symbolic references inside the bytecode — a reference to another class, method, or field, initially stored just as a name — are resolved into direct, concrete references the JVM can actually use. This can happen eagerly, right here during Linking, or lazily, deferred until the reference is actually used for the first time, depending on the specific JVM implementation.

---

## 4. What Actually Triggers Class Loading — Precisely

Chapter 2 §6.2 described loading as "lazy, on first use," informally. The specific triggering events are:

- Creating an instance with `new` (Chapter 3 §4).
- Invoking a `static` method (Chapter 9).
- Accessing or assigning a `static` field — **except** a compile-time constant.
- Reflectively loading a class via `Class.forName(...)` with initialization requested (Chapter 30 §2).
- Initializing a subclass — which, per Chapter 11 §3's parent-first rule, first triggers the **parent's** initialization, if it hasn't already happened.

**The exception is worth pausing on:** Chapter 10 §6 already established that a `static final` compile-time constant is **constant-folded** — its literal value is baked directly into every use site at compile time. Referencing such a constant from another class therefore does **not** trigger that class's loading at all — there's nothing to load *from* at runtime, since the value was already copied in during compilation. This is the same fact Chapter 10 §6 used to explain the "stale constant after a library update" gotcha, now shown to have a second, equally real consequence: it changes whether a reference to a class even causes that class to load.

---

## 5. Classloader Delegation

This is genuinely new content this handbook hasn't touched: Java loads classes through a **hierarchy of classloaders** — at minimum, the **Bootstrap** classloader (loading Java's own core classes, like `java.lang.Object` and `String`), a **Platform** classloader, and the **Application (System)** classloader (loading your own compiled classes). When any classloader is asked to load a class, the standard **delegation model** has it first ask its *parent* classloader to attempt the load before trying itself.

**Why this matters:** it guarantees `java.lang.Object`, `java.lang.String`, and the rest of Java's core classes are always loaded by the trusted Bootstrap classloader, at the top of the hierarchy — application code can never silently substitute its own version of `String` and have it used instead, because delegation always checks upward first. This is a real, deliberate security and consistency guarantee, not an incidental detail.

A subtler consequence worth knowing: a class is identified at runtime by **both** its fully-qualified name (Chapter 14 §2.3) **and** its defining classloader — the "same" class loaded by two different classloaders is treated as two genuinely distinct types by the JVM, a fact that occasionally surfaces in plugin systems or applications with unusual classpath setups.

---

## 6. Real-World Example — Tracing `Manager`'s Full Pipeline

```
new Manager("M001", "Asha", 95000, "", "")   — first time Manager is referenced anywhere

LOADING
   Manager.class located via the classpath (Ch. 14 §4), bytecode read,
   runtime Class object created in Metaspace (Ch. 2 §6.1)

LINKING
   Verification — bytecode checked; since Employee is sealed (Ch. 27),
                   Manager's presence in Employee's PermittedSubclasses
                   is re-validated here
   Preparation  — Manager's own static fields (if any) defaulted
   Resolution   — symbolic references to Employee, Payable, etc. resolved

INITIALIZATION
   Per Ch. 11 §3's parent-first rule: Employee's own initialization
   (its <clinit>, Ch. 6 §6) completes FIRST — then Manager's own
   static initializers/blocks run

── only now can `new Manager(...)` actually proceed with
   Chapter 3 §4's object-creation steps ──
```

---

## 7. Best Practices

- Recognize the constant-folding exception (§4) when reasoning about whether referencing another class actually causes it to load — it explains real, sometimes-surprising behavior around compile-time constants.
- Treat classloader delegation as mostly invisible, automatic infrastructure in ordinary application code — but recognize it by name, since it's directly relevant to understanding classpath conflicts and plugin/framework class-loading setups later.
- Keep Loading, Linking, and Initialization mentally distinct — conflating "the class is loaded" with "the class's static setup has run" is a common, imprecise habit this chapter's pipeline corrects.

## 8. Common Mistakes

- ⚠️ Assuming any reference to a `static` field triggers class initialization — a compile-time constant specifically doesn't, due to constant folding (§4, Chapter 10 §6).
- ⚠️ Treating "loaded" and "initialized" as synonyms — they're genuinely distinct phases; a class can be loaded and linked without its static initializers having run yet.
- ⚠️ Assuming the same class name always refers to the same runtime type — not if it was loaded by two different classloaders (§5).

## 9. Interview Perspective

**Frequently Asked**

- *"Walk through the complete class loading pipeline."* — Loading (find and read bytecode) → Linking (Verification, Preparation, Resolution) → Initialization (run static setup) (§2, §3).
- *"What specifically triggers class loading?"* — `new`, static method/field access (except compile-time constants), reflective loading, or a subclass's own initialization triggering its parent's first (§4).
- *"What is classloader delegation, and why does it matter?"* — Classloaders check their parent before attempting a load themselves, guaranteeing core Java classes are always loaded by the trusted Bootstrap classloader and can't be silently shadowed by application code (§5).

**Tricky Question**

- *"Does referencing `Employee.SOME_CONSTANT` from another class cause `Employee` to load, if `SOME_CONSTANT` is a `static final` compile-time constant?"* — No — constant folding (Chapter 10 §6) means the literal value was already copied into the referencing class's own bytecode at compile time; there's no runtime reference to `Employee` left to trigger its loading (§4).

**Common Misconception**

- Believing Chapter 12's bytecode verifier and Chapter 27's `PermittedSubclasses` check happen "at some point during loading," vaguely. They happen specifically during **Verification**, the first sub-phase of Linking (§3.1) — a precise pipeline location, not a fuzzy general step.

---

## 10. Summary

- The complete pipeline: Loading (find and read bytecode) → Linking (Verification, Preparation, Resolution) → Initialization (run static setup, Chapter 6's `<clinit>`).
- Verification is precisely where Chapter 12's bytecode access checks and Chapter 27's `PermittedSubclasses` check both actually happen.
- Preparation defaults static fields to zero values; Resolution converts symbolic references into direct ones.
- Loading triggers on `new`, static access (except compile-time constants, Chapter 10 §6), reflective loading, or a subclass's initialization cascading to its parent first.
- Classloader delegation (checking the parent classloader first) guarantees core Java classes can never be silently shadowed by application code, and a class's runtime identity includes its defining classloader, not just its name.

## 11. Quick Revision

- Pipeline: Loading → Linking (Verify, Prepare, Resolve) → Initialization.
- Verification = Chapter 12/27's checks, precisely located here.
- Preparation = static fields defaulted. Resolution = symbolic → direct references.
- Triggers: `new`, static access (not compile-time constants), reflection, parent-first subclass init.
- Classloader delegation: parent checked first — protects core classes; class identity = name + classloader.

## 12. Self Assessment

1. Name the three sub-phases of Linking, and state precisely what each one does.
2. Where, precisely, in this pipeline does Chapter 12's bytecode verifier actually run?
3. Why doesn't referencing a `static final` compile-time constant from another class trigger that class's loading?
4. Explain classloader delegation, and the specific guarantee it provides for classes like `java.lang.String`.
5. If `SeniorManager extends Manager extends Employee`, and `new SeniorManager(...)` is the first reference to any of these three classes, what order do their respective initializations run in, and why?

---

## What's Next

**Chapter 40 — Method Dispatch** is this handbook's final chapter. It delivers the complete, formal picture of `invokevirtual`, `invokestatic`, `invokespecial`, and `invokeinterface` side by side — each introduced separately across Chapters 3, 7, 9, 11, 16, and 18 — as one unified, precise account of how the JVM decides which method implementation actually runs, closing out both Part XIII and this entire 40-chapter handbook.
