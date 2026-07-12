# 📘 Java OOP Handbook — Progress Tracker

> Source of truth across sessions. Update this file every time a chapter is completed.
> Do not repeat explanations across chapters — link back to the chapter where a concept was first introduced.

**Target Java Version:** Java 21 LTS (with notes on Java 8 / 11 / 17 differences where relevant)
**Domain model used throughout:** Company, Employee, Manager, HR, Department, Customer, Order, Bank Account, Vehicle, Student, Product

**Handbook front matter** (Preface, Learning Philosophy, Prerequisites, Roadmap, Icons Legend) lives once in `Topics/OOP/README.md` — it is not repeated inside chapter files here in `Topics/OOP/OOP_Handbook/`.

**Note:** `Topics/OOP/Java_OOP_Handbook.md` (the old single-file draft) predates this chapter-by-chapter restructure — see open question at the bottom of this tracker about whether to retire it.

---

## Status Legend

- ✅ Completed
- 🟡 In Progress
- ⬜ Not Started

---

## Part I — Foundations

| # | Chapter | Status | File |
|---|---------|--------|------|
| 1 | Introduction to Programming Paradigms | ✅ | `01-Introduction-to-OOP.md` |

## Part II — Classes & Objects

| # | Chapter | Status | File |
|---|---------|--------|------|
| 2 | Class | ✅ | `02-Class.md` |
| 3 | Object | ✅ | `03-Object.md` |

## Part III — Variables

| # | Chapter | Status | File |
|---|---------|--------|------|
| 4 | Variables | ✅ | `04-Variables.md` |

## Part IV — Object Construction

| # | Chapter | Status | File |
|---|---------|--------|------|
| 5 | Constructors | ✅ | `05-Constructors.md` |
| 6 | Initialization | ✅ | `06-Initialization.md` |

## Part V — Methods

| # | Chapter | Status | File |
|---|---------|--------|------|
| 7 | Methods | ✅ | `07-Methods.md` |

## Part VI — Keywords

| # | Chapter | Status | File |
|---|---------|--------|------|
| 8 | this | ⬜ | `08-this.md` |
| 9 | static | ⬜ | `09-static.md` |
| 10 | final | ⬜ | `10-final.md` |
| 11 | super | ⬜ | `11-super.md` |

## Part VII — Object Design

| # | Chapter | Status | File |
|---|---------|--------|------|
| 12 | Encapsulation | ⬜ | `12-Encapsulation.md` |
| 13 | Access Modifiers | ⬜ | `13-Access-Modifiers.md` |
| 14 | Packages | ⬜ | `14-Packages.md` |

## Part VIII — Core OOP

| # | Chapter | Status | File |
|---|---------|--------|------|
| 15 | Inheritance | ⬜ | `15-Inheritance.md` |
| 16 | Polymorphism | ⬜ | `16-Polymorphism.md` |
| 17 | Abstraction | ⬜ | `17-Abstraction.md` |
| 18 | Interface | ⬜ | `18-Interface.md` |

## Part IX — Java Root Object

| # | Chapter | Status | File |
|---|---------|--------|------|
| 19 | Object Class | ⬜ | `19-Object-Class.md` |

## Part X — Relationships

| # | Chapter | Status | File |
|---|---------|--------|------|
| 20 | Association | ⬜ | `20-Association.md` |
| 21 | Aggregation | ⬜ | `21-Aggregation.md` |
| 22 | Composition | ⬜ | `22-Composition.md` |

## Part XI — Advanced OOP

| # | Chapter | Status | File |
|---|---------|--------|------|
| 23 | Nested Classes | ⬜ | `23-Nested-Classes.md` |
| 24 | Anonymous Classes | ⬜ | `24-Anonymous-Classes.md` |
| 25 | Enums | ⬜ | `25-Enums.md` |
| 26 | Records | ⬜ | `26-Records.md` |
| 27 | Sealed Classes | ⬜ | `27-Sealed-Classes.md` |
| 28 | Immutability | ⬜ | `28-Immutability.md` |
| 29 | Object Cloning | ⬜ | `29-Object-Cloning.md` |
| 30 | Reflection | ⬜ | `30-Reflection.md` |
| 31 | Annotations | ⬜ | `31-Annotations.md` |
| 32 | Generics | ⬜ | `32-Generics.md` |
| 33 | Comparable vs Comparator | ⬜ | `33-Comparable-vs-Comparator.md` |

## Part XII — Object-Oriented Design

| # | Chapter | Status | File |
|---|---------|--------|------|
| 34 | SOLID Principles | ⬜ | `34-SOLID-Principles.md` |
| 35 | Dependency Injection | ⬜ | `35-Dependency-Injection.md` |

## Part XIII — JVM Internals

| # | Chapter | Status | File |
|---|---------|--------|------|
| 36 | Object Lifecycle | ⬜ | `36-Object-Lifecycle.md` |
| 37 | Memory Management | ⬜ | `37-Memory-Management.md` |
| 38 | Garbage Collection | ⬜ | `38-Garbage-Collection.md` |
| 39 | Class Loading | ⬜ | `39-Class-Loading.md` |
| 40 | Method Dispatch | ⬜ | `40-Method-Dispatch.md` |

---

## Session Log

| Date | Chapters Touched | Notes |
|------|-------------------|-------|
| 2026-07-11 | Chapter 1 drafted | Repo scaffolded: `Topics/OOP/OOP_Handbook/ (chapter files) and Topics/OOP/README.md (front matter)`. |
| 2026-07-11 | Chapter 1 rewritten (full version) | Replaced with the expanded, book-style draft. Front matter (Preface, Philosophy, Roadmap, Icons) moved out of the chapter file into `README.md` so it isn't duplicated per chapter. Three duplicated stub sections (short pre-drafts of 1.10, 1.11, 1.12 immediately followed by their own full versions) were removed. Added a "Self Assessment" section per the required chapter template, which was missing. |
| 2026-07-11 | Chapter 2 completed | Formal definition of a class, syntax anatomy, compiler behaviour (`.class` generation, one-public-class-per-file rule), and Metaspace vs. Heap distinction. Introduced the running `Employee` example that later chapters (Object, Constructors, Encapsulation, Inheritance) will extend rather than redefine. Deliberately deferred: constructors (full detail → Ch5), static members (→ Ch9), initializer blocks (→ Ch6), nested classes (→ Ch23), full class-loading pipeline (→ Ch39). |
| 2026-07-11 | Chapter 1 trimmed (2,281 → ~350 lines) | Cut generic computer-history padding (deep Machine/Assembly Language sections merged into one tight section), condensed Modular Programming, removed triple-repeated "advantages of OOP" content (was stated separately in §1.9, §1.11, §1.13 of the old draft — now one clean list), cut the 14-category Applications list down to the handful most relevant to enterprise/Spring Boot work. Section numbers changed as a result — see updated references below. Chapter 2's back-reference to Chapter 1 was updated to match (§1.9/§1.11 → §1.5/§1.7). |
| 2026-07-11 | Chapter 3 completed | The five-step object-creation pipeline (allocation → default init → field init → constructor → reference returned), reference-vs-object distinction, object header/memory layout, aliasing, and `==` vs `.equals()` at a conceptual level (full `.equals()`/`hashCode()` override mechanics deferred to Ch19). Extended the running `Employee` example with actual instantiation. |
| 2026-07-12 | Chapter 4 completed | Instance vs. static vs. local variables, with a unified Stack/Heap/Metaspace memory diagram tying together Ch2 (Metaspace) and Ch3 (Heap, object layout). Covered definite-assignment (why locals get no default value, unlike fields), scope, shadowing (setting up Ch8's `this`), and `var` type inference. Depth calibrated topic-driven per Mahi's latest guidance, not to a fixed line-count target. |
| 2026-07-12 | Chapter 5 completed | Opened the "Constructor Execution" black box from Ch3 §4: no-return-type rule, the default-constructor-disappears-once-you-add-any-constructor gotcha, overloading, `this(...)` chaining rules, a preview of implicit `super()` (full detail deferred to Ch15), the copy-constructor pattern (deep vs. shallow copy flagged forward to Ch29), and private constructors as a Singleton preview (full pattern deferred). |
| 2026-07-12 | Chapter 6 completed | Closed the loop on Ch3 §4's "Field Initialization" step: field initializers, instance initializer blocks (run every construction, before ctor body), static initializer blocks (run once, at class load). Assembled the full master object-creation order (Ch3 alloc/default-init → parent init preview → field init/instance blocks in source order → Ch5 ctor body → reference returned). Covered the forward-reference-yields-default-value trap and the compiler fact that instance initializers are inlined into every constructor after `super()`, while static initializers become a single `<clinit>`. |
| 2026-07-12 | Chapter 7 completed | Method signature (name + params, no return type), overloading resolved at compile time, and — the chapter's core — the definitive pass-by-value proof for both mutation-through-reference and reassignment-of-reference cases, resolving the "tricky question" Ch3 §14 deliberately deferred here. Tied method calls back to Ch4's Stack model via stack frames. Introduced `invokevirtual`/dynamic dispatch only as a forward-looking label, deferred fully to Ch16/Ch40. Distinguished overloading (this chapter) from overriding (Ch16) explicitly to prevent the two being conflated later. |

---

## Concepts Already Explained (do not repeat — link back instead)

- **Programming paradigm evolution** (Machine → Assembly → High-Level → Procedural → Modular → OOP) — Chapter 1, §1.1–1.4.
- **Why OOP exists / problems it solves** — Chapter 1, §1.6.
- **Core OOP characteristics** (Objects, Classes, Encapsulation, Abstraction, Inheritance, Polymorphism, Modularity, Reusability, etc.) — Chapter 1, §1.7.
- **Four Pillars of OOP at a high level** — Chapter 1, §1.8. (Each pillar gets its own dedicated deep-dive chapter later — Ch. 12 Encapsulation, Ch. 15 Inheritance, Ch. 16 Polymorphism, Ch. 17 Abstraction — so Chapter 1's overview should only be referenced there, not repeated.)
- **Formal definition of a class, class syntax anatomy, one-public-class-per-file rule, class metadata vs. object data (Metaspace vs. Heap), lazy class loading** — Chapter 2, §3–§6.
- **The running `Employee` example class** — first defined in Chapter 2, §7; instantiated with real values in Chapter 3, §9; carries instance/static variables in Chapter 4, §8; full constructor set (overloaded, chained, copy constructor) in Chapter 5, §10; static/instance initializer blocks in Chapter 6, §7; now has behavior methods (`calculateAnnualSalary`, overloaded `raiseSalary`) in Chapter 7, §7. Later chapters extend this same class rather than introducing a new one.
- **Object creation pipeline (`new` → allocation → default init → field init → constructor → reference return), reference vs. object, object header layout, aliasing, `==` vs. `.equals()` at a conceptual level** — Chapter 3, §3–§8. (Full `.equals()`/`hashCode()` override mechanics still belong to Ch. 19.)
- **Instance vs. static vs. local variables — memory location, default-value behaviour, scope, shadowing, `var` type inference** — Chapter 4, §2–§7. (Full `this`-based shadowing fix still belongs to Ch. 8; full `static` method/block semantics still belong to Ch. 9.)
- **Constructors — no return type, default constructor rule, overloading, `this(...)` chaining, implicit `super()` preview, copy constructor, private constructors** — Chapter 5, §3–§8. (Full `super()`/inheritance mechanics still belong to Ch. 15; Singleton pattern still belongs to a later design-pattern discussion; deep vs. shallow copy still belongs to Ch. 29.)
- **Field initializers, instance initializer blocks, static initializer blocks, the complete object-creation order, forward-reference-to-uninitialized-field trap, and the `<clinit>`/inlined-into-constructors compiler behaviour** — Chapter 6, §3–§6. This is the definitive, complete version of the initialization sequence — no future chapter should re-derive it, only reference it (parent-class-initialization step still to be filled in fully by Ch. 15).
- **Method signature (excludes return type), overload resolution at compile time, the full pass-by-value proof (mutate-through-reference vs. reassign-reference), stack frames per method call, overloading vs. overriding distinction** — Chapter 7, §2–§6. This is the definitive, complete treatment of Java's parameter-passing rule — no future chapter should re-derive it, only reference it (dynamic dispatch / `invokevirtual` still belongs fully to Ch. 16 and Ch. 40).

## Next Up

➡️ Chapter 8 — `this`

---

## Open Question

- `Topics/OOP/Java_OOP_Handbook.md` — a pre-restructure, single-file handbook draft still sits at the `Topics/OOP/` level, alongside this new chapter-by-chapter `OOP_Handbook/` folder. Decide whether to delete it, archive it, or keep it as a merged single-file export generated later from all 40 chapter files.
