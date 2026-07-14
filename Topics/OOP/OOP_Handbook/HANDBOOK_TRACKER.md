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
| 8 | this | ✅ | `08-this.md` |
| 9 | static | ✅ | `09-static.md` |
| 10 | final | ✅ | `10-final.md` |
| 11 | super | ✅ | `11-super.md` |

## Part VII — Object Design

| # | Chapter | Status | File |
|---|---------|--------|------|
| 12 | Encapsulation | ✅ | `12-Encapsulation.md` |
| 13 | Access Modifiers | ✅ | `13-Access-Modifiers.md` |
| 14 | Packages | ✅ | `14-Packages.md` |

## Part VIII — Core OOP

| # | Chapter | Status | File |
|---|---------|--------|------|
| 15 | Inheritance | ✅ | `15-Inheritance.md` |
| 16 | Polymorphism | ✅ | `16-Polymorphism.md` |
| 17 | Abstraction | ✅ | `17-Abstraction.md` |
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
| 2026-07-12 | Chapter 8 completed | `this` as the implicit per-call object reference; resolved Ch4 §5.3's shadowing bug properly; covered passing/returning `this` (method chaining, Builder-pattern preview); explained why `this` can't exist in a static context both conceptually (Ch4 §4) and at the bytecode level (`this` is an invisible first parameter of every instance method); noted `this` behaves like an implicit `final` reference, previewing Ch10. Kept intentionally leaner than Ch5-7, matching the topic's genuinely narrower scope per Mahi's topic-driven-depth guidance. |
| 2026-07-12 | Chapter 9 completed | Capstone chapter for `static` — added static methods (new content) and assembled them with static variables (Ch4 §4) and static blocks (Ch6 §3.3) into one unified table (§6). Covered class-name vs. instance-reference calling style, static utility classes (paired with Ch5 §8's private constructor pattern), and the `invokestatic` vs. `invokevirtual` (Ch7 §6) distinction that explains *why* static methods are hidden, not overridden — a direct preview seed for Ch15/16. Included the null-reference-static-call trivia as a JVM-mechanics-grounded gotcha. |
| 2026-07-12 | Chapter 10 completed | Formalized `final` across its three distinct targets: variables (incl. blank finals and the "final reference ≠ immutable object" trap tied back to Ch3 §3.2), methods (blocks overriding, not inheriting — distinct axis from Ch9 §5's static hiding), and classes (explains Ch9 §4's `final class SalaryUtils` retroactively). Covered constant folding as real JVM/compiler depth, including the stale-constant-after-library-update gotcha. Explicitly flagged that `final` alone isn't sufficient for true immutability, pointing forward to Ch28. |
| 2026-07-12 | Chapter 11 completed | Closed Part VI (Keywords). Delivered the full `super()` mechanics Ch5 §6 and Ch6 §4.2 both deferred here: constructor-call rules, the no-accessible-no-arg-parent-constructor gotcha, `super.field`/`super.method()`, and the complete concrete initialization order once a parent class is involved. Key JVM fact: `super.method()` compiles to `invokespecial` (static resolution), not `invokevirtual` (Ch7 §6) — explains why it can't recurse into an override. Used a deliberately disposable `Vehicle`/`Car` pair (not the running `Employee` class) to avoid front-running Ch15's real inheritance example; explicitly scoped out "when/why to use inheritance," which stays Ch15/16's job. |
| 2026-07-13 | Chapter 12 completed | Opened Part VII (Object Design) and delivered the first of the Four Pillars in full, formalizing the private-fields-plus-methods pattern every `Employee` example has quietly used since Ch2. Core contribution: distinguished genuine encapsulation (validated setters, enforced invariants) from the mechanical private-field-plus-pass-through-getter/setter anti-pattern, which offers no more protection than a public field. Covered read-only fields (getter only, paired with Ch10's blank final) and a strong JVM fact — access control is checked twice, once by the compiler and again independently by the JVM bytecode verifier at class-loading time, making it a real security boundary rather than a compile-time-only convention. Used only `private`/`public` informally; full access modifier system deferred to Ch13. |
| 2026-07-13 | Chapter 13 completed | Formalized all four access levels (`private`, package-private/default, `protected`, `public`) with a single comparison table. Two strongest contributions: (1) package-private ≠ private — a very common misconception, named explicitly; (2) `protected`'s genuinely subtle cross-package subclass rule — access only through the subclass's own declared type, never through a parent-typed reference, even to the same runtime object — real interview-differentiating depth. Gave `Employee` a `protected department` and package-private `team` field specifically as groundwork for Ch15's `Manager extends Employee`. Noted the `ACC_PRIVATE`/`ACC_PROTECTED`/`ACC_PUBLIC` class-file flags as the concrete mechanism behind Ch12 §6's two-layer enforcement, without repeating that explanation. |
| 2026-07-13 | Chapter 14 completed | Closed Part VII (Object Design). Formalized packages as namespace + mandatory directory mapping, fully-qualified names, and imports — including explicitly debunking the "wildcard imports are slower" myth (compile-time-only resolution, zero runtime cost). Covered `java.lang`'s implicit-import special case, static imports briefly, and how the JVM's classloader translates a fully-qualified name into a classpath search (previewing Ch39). Gave `Employee`/`SalaryUtils` real package names (`com.acme.hr` / `com.acme.payroll`) for the first time, making Ch13's access-level table physically concrete. |
| 2026-07-13 | Chapter 15 completed | Opened Part VIII (Core OOP). Delivered `Manager extends Employee` — the payoff this handbook has been building toward since Ch5. Covered the is-a test, exactly what's inherited (incl. the "private fields exist in the object but aren't accessible by name" nuance), and — the chapter's strongest contribution — a single comparison table distinguishing overloading (Ch7), overriding (new here), and static method hiding (Ch9), which are commonly conflated as one idea. Covered override rules (signature, covariant return, visibility, `final`), `@Override` as compile-time-only, single inheritance and the diamond problem, the three inheritance shapes (single/multilevel/hierarchical), and `Object` as the implicit universal parent (full treatment: Ch19). Deliberately stopped short of dynamic dispatch mechanics — reserved whole for Ch16. |
| 2026-07-13 | Chapter 16 completed | Delivered dynamic method dispatch in full — the mechanism Ch15 deliberately deferred. Explained the virtual method table (vtable) concept and finally resolved `invokevirtual`'s preview from Ch7 §6, contrasted against `invokestatic` (Ch9 §5 — no vtable at all) and `invokespecial` (Ch11 §4 `super.method()` — bypasses the vtable deliberately). Strongest original contribution: fields are NEVER polymorphic in Java — always resolved by declared reference type at compile time, unlike methods — demonstrated with a field-hiding example contrasted directly against the method-overriding case. Covered upcasting, `instanceof`/downcasting (incl. Java 16+ pattern matching), and a polymorphic `List<Employee>` payroll loop tying back to Ch1 §1.6's extensibility promise. |
| 2026-07-13 | Chapter 17 completed | Completed the Four Pillars trilogy (Encapsulation → Inheritance → Polymorphism → Abstraction). Made `Employee` itself `abstract` with an `abstract getSalary()`, following through on Ch16's closing observation that no role-less Employee should exist as an object — `new Employee(...)` now a genuine compile error, `Manager`/`Intern` unaffected. Strongest contribution: explained *why* an abstract method can never be `private`, `static`, or `final` by connecting three earlier chapters (Ch13 visibility, Ch9/Ch16 dynamic dispatch, Ch10 final-blocks-overriding) into one coherent reason rather than three memorized rules. Also resolved the common "why does an abstract class need a constructor" confusion via Ch11's `super()` mechanics. Interfaces explicitly previewed, not covered — reserved whole for Ch18. |

---

## Concepts Already Explained (do not repeat — link back instead)

- **Programming paradigm evolution** (Machine → Assembly → High-Level → Procedural → Modular → OOP) — Chapter 1, §1.1–1.4.
- **Why OOP exists / problems it solves** — Chapter 1, §1.6.
- **Core OOP characteristics** (Objects, Classes, Encapsulation, Abstraction, Inheritance, Polymorphism, Modularity, Reusability, etc.) — Chapter 1, §1.7.
- **Four Pillars of OOP** — Chapter 1, §1.8 (high-level preview). **All four now have full chapters: Encapsulation (Ch. 12), Inheritance (Ch. 15), Polymorphism (Ch. 16), Abstraction (Ch. 17).** This milestone is complete.
- **Formal definition of a class, class syntax anatomy, one-public-class-per-file rule, class metadata vs. object data (Metaspace vs. Heap), lazy class loading** — Chapter 2, §3–§6.
- **The running `Employee` example class** — first defined in Chapter 2, §7; instantiated in Chapter 3, §9; carries instance/static variables in Chapter 4, §8; full constructor set in Chapter 5, §10; static/instance initializer blocks in Chapter 6, §7; behavior methods in Chapter 7, §7; uses `this` in Chapter 8, §7; static factory method in Chapter 9, §8; blank final/constant/final method in Chapter 10, §7; properly encapsulated in Chapter 12, §7; `protected`/package-private fields in Chapter 13, §8; lives in `com.acme.hr` in Chapter 14, §5; first subclass `Manager` in Chapter 15, §9; sibling subclass `Intern` and polymorphic processing in Chapter 16, §7; **`Employee` itself now declared `abstract` with an `abstract getSalary()` in Chapter 17, §3, §9 — `new Employee(...)` is now a compile error; `Manager`/`Intern` remain fully concrete and instantiable.**
- **Object creation pipeline, reference vs. object, object header layout, aliasing, `==` vs. `.equals()` at a conceptual level** — Chapter 3, §3–§8. (Full `.equals()`/`hashCode()` override mechanics still belong to Ch. 19.)
- **Instance vs. static vs. local variables — memory location, default-value behaviour, scope, shadowing, `var` type inference** — Chapter 4, §2–§7.
- **Constructors — no return type, default constructor rule, overloading, `this(...)` chaining, copy constructor, private constructors** — Chapter 5, §3–§8. (Singleton pattern still belongs to a later design-pattern discussion; deep vs. shallow copy still belongs to Ch. 29.)
- **Field initializers, instance initializer blocks, static initializer blocks, the object-creation order (single-class case), forward-reference-to-uninitialized-field trap, and the `<clinit>`/inlined-into-constructors compiler behaviour** — Chapter 6, §3–§6.
- **Method signature (excludes return type), overload resolution at compile time, the full pass-by-value proof, stack frames per method call** — Chapter 7, §2–§6. (`invokevirtual`/dynamic dispatch fully explained in Ch. 16 §4.)
- **`this` — implicit object reference, shadowing fix, passing/returning `this`, unavailable in static context, invisible-first-parameter bytecode fact, behaves like implicit `final`** — Chapter 8, §2–§6.
- **`static` methods (no `this`, no direct instance access, `invokestatic`, resolved at compile time), class-name-vs-instance calling style, static utility classes, unified static-variable/static-block/static-method picture** — Chapter 9, §2–§7. (Static nested classes still belong fully to Ch. 23.)
- **`final` variables (incl. blank finals, final-reference-≠-immutable-object), `final` methods (blocks overriding, not inheriting), `final` classes, and constant folding** — Chapter 10, §2–§6. (True immutability design still belongs fully to Ch. 28.)
- **`super()` constructor-call rules, the complete initialization order once a parent class exists, `super.field`/`super.method()`, and why `super.method()` uses static (`invokespecial`) resolution rather than dynamic dispatch** — Chapter 11, §2–§4.
- **Encapsulation — validated getters/setters vs. the pass-through-getter/setter anti-pattern, read-only fields via getter-only + `final`, and the two-layer (compiler + JVM bytecode verifier) access-control enforcement model** — Chapter 12, §2–§6.
- **All four access levels, package-private-vs-private, `protected`'s cross-package subclass-own-type rule, `ACC_*` class-file flags** — Chapter 13, §2–§7.
- **Packages — namespace + mandatory directory mapping, fully-qualified names, imports (incl. wildcard-import myth debunked), `java.lang`'s implicit import, static imports, classloader mapping** — Chapter 14, §2–§4.
- **Inheritance — the is-a test, what's inherited vs. never inherited vs. present-but-inaccessible, method overriding rules, `@Override`, overloading/overriding/hiding as three distinct mechanisms, single inheritance and the diamond problem, the three inheritance shapes, `Object` as implicit universal parent, `super_class` constant-pool reference** — Chapter 15, §2–§8.
- **Dynamic method dispatch (vtable mechanism behind `invokevirtual`), compile-time vs. runtime polymorphism, upcasting, `instanceof`/downcasting (incl. Java 16+ pattern matching), and fields never being polymorphic** — Chapter 16, §2–§6.
- **Abstract classes/methods — cannot instantiate directly, abstract methods have no body and mandate implementation (or propagate `abstract` down), why abstract can't combine with `private`/`static`/`final`, why abstract classes still have constructors, `ACC_ABSTRACT` flag** — Chapter 17, §2–§7. Definitive; only reference, don't re-derive. (Interfaces themselves — the other abstraction mechanism, multiple implementation, default methods — still belong entirely to Ch. 18.)

## Next Up

➡️ Chapter 18 — Interface

---

## Open Question

- `Topics/OOP/Java_OOP_Handbook.md` — a pre-restructure, single-file handbook draft still sits at the `Topics/OOP/` level, alongside this new chapter-by-chapter `OOP_Handbook/` folder. Decide whether to delete it, archive it, or keep it as a merged single-file export generated later from all 40 chapter files.
