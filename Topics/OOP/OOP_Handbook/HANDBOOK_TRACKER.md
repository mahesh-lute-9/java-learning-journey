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
| 2 | Class | ⬜ | `02-Class.md` |
| 3 | Object | ⬜ | `03-Object.md` |

## Part III — Variables

| # | Chapter | Status | File |
|---|---------|--------|------|
| 4 | Variables | ⬜ | `04-Variables.md` |

## Part IV — Object Construction

| # | Chapter | Status | File |
|---|---------|--------|------|
| 5 | Constructors | ⬜ | `05-Constructors.md` |
| 6 | Initialization | ⬜ | `06-Initialization.md` |

## Part V — Methods

| # | Chapter | Status | File |
|---|---------|--------|------|
| 7 | Methods | ⬜ | `07-Methods.md` |

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

---

## Concepts Already Explained (do not repeat — link back instead)

- **Programming paradigm evolution** (Machine → Assembly → High-Level → Procedural → Modular → OOP) — Chapter 1, §1.1–1.10.
- **Why OOP exists / problems it solves** — Chapter 1, §1.10.
- **Core OOP characteristics** (Objects, Classes, Encapsulation, Abstraction, Inheritance, Polymorphism, Modularity, Reusability, etc.) — Chapter 1, §1.11.
- **Four Pillars of OOP at a high level** — Chapter 1, §1.12. (Each pillar gets its own dedicated deep-dive chapter later — Ch. 12 Encapsulation, Ch. 15 Inheritance, Ch. 16 Polymorphism, Ch. 17 Abstraction — so Chapter 1's overview should only be referenced there, not repeated.)
- **Class vs. Object at a high level** — introduced informally in Chapter 1, §1.9 and §1.11; the *formal* definition belongs to Chapter 2.

## Next Up

➡️ Chapter 2 — Class

---

## Open Question

- `Topics/OOP/Java_OOP_Handbook.md` — a pre-restructure, single-file handbook draft still sits at the `Topics/OOP/` level, alongside this new chapter-by-chapter `OOP_Handbook/` folder. Decide whether to delete it, archive it, or keep it as a merged single-file export generated later from all 40 chapter files.
