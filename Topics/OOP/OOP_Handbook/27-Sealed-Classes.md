# Chapter 27 — Sealed Classes

**Part XI: Advanced OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain the specific gap sealed classes close between Chapter 15's fully-open inheritance and Chapter 25's fully-closed enums.
- Apply the mandatory `final`/`sealed`/`non-sealed` choice to every permitted subclass, and explain why Java forces an explicit choice rather than defaulting.
- Choose correctly between an enum and a sealed hierarchy for a given set of variants.
- Explain how `permits` is enforced — not just at compile time, but at the bytecode level too.

---

## 1. Introduction

Chapter 26 closed by describing sealed classes as sitting between two things this handbook has already built in full: Chapter 15's completely open inheritance, where any class anywhere can extend a non-`final` class at any time, and Chapter 25's enums, where the set of variants is fixed but every variant is a singleton instance of the *same* class. Sealed classes (Java 17+) offer a third option: **a fixed, compile-time-known set of permitted subclasses, where each one can still be a genuinely distinct, full class.**

> This chapter assumes Chapter 15's inheritance mechanics, Chapter 17's abstract classes, Chapter 18's interfaces, and Chapter 25's enum comparison completely.

---

## 2. Theory — What `sealed` Actually Restricts

> **A `sealed` class or interface explicitly lists every class permitted to extend or implement it, via a `permits` clause — no other class, anywhere, may ever do so.**

```java
public sealed interface Payable permits Employee, Invoice, Contract { }

public abstract sealed class Employee implements Payable permits Manager, Intern { }

public final class Manager extends Employee { }
public final class Intern extends Employee { }
```

`Employee` — already `abstract` (Chapter 17) and already `implements Payable` (Chapter 18) — now also declares exactly which classes are allowed to extend it: `Manager` and `Intern`, and nothing else, ever, anywhere in the codebase.

---

## 3. Every Permitted Subclass Must Choose: `final`, `sealed`, or `non-sealed`

This is the rule most worth internalizing precisely: **Java requires every class named in a `permits` clause to declare exactly one of three modifiers — there is no silent default.**

- **`final`** (Chapter 10 §5) — this permitted subclass is a leaf; nothing may extend it further. `Manager` and `Intern` above are both `final`, since they're the concrete end of this hierarchy.
- **`sealed`** — this permitted subclass continues restricting further, with its own `permits` clause, for a multi-level closed hierarchy.
- **`non-sealed`** — this permitted subclass deliberately *reopens* the hierarchy from this point onward, reverting to Chapter 15's ordinary, fully-open extension for anything extending it.

**Why does Java force this explicit choice, rather than defaulting to one?** So a sealed hierarchy's closure can never be silently undermined. If `non-sealed` could happen by omission rather than deliberate declaration, a hierarchy that looks closed at a glance could actually be wide open a level down, without any visible signal in the code. Requiring an explicit word at every permitted subclass makes the hierarchy's actual shape fully visible from its declarations alone.

---

## 4. The Real Payoff: Exhaustiveness

The practical reason to seal a hierarchy — beyond simply restricting who can extend it — is that the compiler can then verify **exhaustive handling** of every permitted case. When code branches on a sealed type's actual subtype (using pattern-matching `switch`, a Java 21+ feature this handbook's OOP scope doesn't cover in depth), the compiler can confirm every permitted subtype is handled, with no fallback `default` branch required at all. If a new permitted subclass is added to `Payable` later, **every place in the codebase that branches exhaustively over `Payable`'s subtypes fails to compile** until it's updated to handle the new case. This is the direct opposite of Chapter 15's ordinary open inheritance, where a new subclass can appear anywhere, anytime, with no guarantee that code elsewhere was updated to account for it.

---

## 5. Sealed Hierarchies vs. Enums — The Precise Test

Chapter 25 §8 already asked whether `Employee` could be an enum, and answered no. Sealed classes raise the same question from the other direction: **when should a fixed set of variants be an enum, and when should it be a sealed hierarchy?**

The precise test: do the variants need genuinely **different structure** — different fields, different shapes of state — or only different **values and behavior** attached to the same shared structure? `EmployeeType`'s constants (Chapter 25) all share the exact same field set (`baseSalary`) and differ only in that value and one overridden method — a clean enum fit. `Manager` and `Intern` as full classes, by contrast, can each have entirely different fields (`Manager.directReports` doesn't exist on `Intern` at all) — a structural difference an enum's shared-field model cannot express, which is exactly why `Manager`/`Intern` were built as full subclasses back in Chapter 15, not enum constants.

| | Enum (Ch. 25) | Sealed Hierarchy (this chapter) | Open Inheritance (Ch. 15) |
|---|---|---|---|
| Set of variants | Fixed, same class | Fixed, `permits`-declared | Open, unbounded |
| Each variant's structure | Identical fields, differing values/behavior | Can differ completely | Can differ completely |
| Compiler-verified exhaustive handling | Yes (all constants) | Yes (all permitted subtypes) | No |
| Extensible by other code later | Never | Never (unless `non-sealed`, §3) | Always |

---

## 6. JVM Internals — `permits` Is Enforced at the Bytecode Level Too

This mirrors a theme from Chapter 12 §6: access control there was checked twice — once by the compiler, once independently by the JVM's bytecode verifier. Sealed classes work the same way. A compiled sealed class's `.class` file carries a **`PermittedSubclasses`** attribute, literally listing the classes allowed to extend it. When the JVM loads a class that claims to extend a sealed class, the verifier independently checks that the extending class actually appears in that attribute — exactly the same "don't just trust the compiler; verify again at load time" principle Chapter 12 §6 established for `private`/`protected`/`public`. This is why sealed restrictions hold even against hand-crafted or independently-compiled bytecode, not just against code that happened to pass through `javac` with the full source in view.

---

## 7. Real-World Example

```java
public sealed interface Payable permits Employee, Invoice, Contract { }

public abstract sealed class Employee implements Payable permits Manager, Intern {
    // ... existing fields, constructor, abstract getSalary(), equals()/hashCode()/toString() ...
}

public final class Manager extends Employee {
    private List<Employee> directReports;
    // ...
}

public final class Intern extends Employee {
    // ...
}

public final class Invoice implements Payable {
    // an entirely different structure — no relation to Employee's fields at all
}

public final class Contract implements Payable {
    // likewise structurally distinct
}
```

`Payable` now has a closed, known set of exactly three implementers — `Employee` (itself further sealed to `Manager`/`Intern`), `Invoice`, and `Contract` — and any code that needs to handle every kind of `Payable` exhaustively can now lean on the compiler to catch a forgotten case, rather than discovering the gap at runtime.

---

## 8. Best Practices

- Seal a hierarchy specifically when exhaustive handling matters — when it's genuinely important that new variants can't silently slip past code written to handle "every case."
- Don't seal a hierarchy "just in case" — sealing is a deliberate commitment to closure, the opposite default from Chapter 15's ordinarily-open inheritance, and should be a considered choice, not a habit.
- Apply Chapter 25's enum-vs-sealed test (§5) before choosing either — structurally identical variants belong in an enum; structurally distinct ones belong in a sealed hierarchy.

## 9. Common Mistakes

- ⚠️ Omitting `final`/`sealed`/`non-sealed` on a permitted subclass — there is no default; the compiler requires one explicitly (§3).
- ⚠️ Confusing `sealed` with `final` — a `final` class permits no extension at all; a `sealed` class permits extension, but only by its named `permits` list (§2 vs. Chapter 10 §5).
- ⚠️ Assuming a sealed hierarchy is closed forever, even through a `non-sealed` permitted subclass — `non-sealed` deliberately reopens extension from that point onward (§3).
- ⚠️ Reaching for a sealed hierarchy where an enum would be simpler, for variants that are actually structurally identical (§5).

## 10. Interview Perspective

**Frequently Asked**

- *"What problem do sealed classes solve that ordinary inheritance doesn't?"* — They let a compiler-verified, closed, known set of subclasses coexist with subclasses that can still have genuinely different structure — enabling exhaustive handling that open inheritance (Chapter 15) can never guarantee (§4).
- *"What are the three choices for a permitted subclass, and why no default?"* — `final`, `sealed`, or `non-sealed` — no default, specifically so a hierarchy's actual openness is always visible directly in its declarations, never silently inherited (§3).
- *"Difference between `sealed` and `final`?"* — `final` forbids all extension; `sealed` permits extension, but only by an explicitly named, closed list (§2, §9).

**Tricky Question**

- *"Is `permits` only a compile-time restriction, or does the JVM enforce it too?"* — Both — the compiled `PermittedSubclasses` attribute is independently re-checked by the JVM's bytecode verifier when a claimed subclass is loaded, mirroring the same two-layer enforcement Chapter 12 §6 established for access modifiers (§6).

**Common Misconception**

- Believing sealed classes are just a stricter form of `final`. They're a different tool entirely — `final` closes off extension completely; `sealed` deliberately keeps a *specific*, known, structurally-flexible set of extension points open, which is precisely what makes compiler-verified exhaustive handling (§4) possible in the first place.

---

## 11. Summary

- `sealed` restricts which classes may extend or implement a type, via an explicit `permits` list — no other class may ever do so.
- Every permitted subclass must declare exactly one of `final`, `sealed`, or `non-sealed` — there's no default, so a hierarchy's actual shape is always visible in its own declarations.
- The real payoff is compiler-verified exhaustive handling of every permitted case — catching a forgotten variant at compile time rather than at runtime.
- Choose between an enum and a sealed hierarchy based on whether variants share identical structure (enum) or genuinely differ in structure (sealed hierarchy).
- `permits` is enforced at both the compiler and the JVM bytecode-verifier level, mirroring Chapter 12's two-layer access-control enforcement.

## 12. Quick Revision

- Sealed = closed, known, `permits`-declared subclass set — not fully open (Ch15), not one shared class (Ch25's enum).
- Every permitted subclass: exactly one of `final`/`sealed`/`non-sealed`, always explicit.
- Payoff: compiler-verified exhaustive handling of every permitted variant.
- Enum vs. sealed test: identical structure → enum; genuinely different structure → sealed hierarchy.
- `PermittedSubclasses` class-file attribute → checked again by the JVM verifier, not just the compiler.

## 13. Self Assessment

1. What specific guarantee does sealing `Payable` provide that Chapter 15's ordinary `implements Payable` never could?
2. Why must every permitted subclass explicitly choose `final`, `sealed`, or `non-sealed`, rather than Java picking a sensible default?
3. Apply §5's test to decide: should a `PaymentMethod` (Cash, Card, UPI) be an enum or a sealed hierarchy — assume each has genuinely different structure (e.g., Card needs an expiry date, UPI needs a handle). Justify your answer.
4. Explain precisely how `permits` is enforced beyond the compiler, and what specific class-file attribute is involved.
5. Give an example of a `non-sealed` permitted subclass, and explain what it actually reopens for anything that extends it.

---

## What's Next

**Chapter 28 — Immutability** completes the thread multiple earlier chapters have pointed toward without fully building: Chapter 10 §3.4 established that `final` alone doesn't make an object immutable, and Chapter 26 §2 showed records generate `final` fields automatically. This chapter finally defines what genuine object immutability requires in full, and builds a properly, completely immutable class from first principles.
