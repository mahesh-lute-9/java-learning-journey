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
| 18 | Interface | ✅ | `18-Interface.md` |

## Part IX — Java Root Object

| # | Chapter | Status | File |
|---|---------|--------|------|
| 19 | Object Class | ✅ | `19-Object-Class.md` |

## Part X — Relationships

| # | Chapter | Status | File |
|---|---------|--------|------|
| 20 | Association | ✅ | `20-Association.md` |
| 21 | Aggregation | ✅ | `21-Aggregation.md` |
| 22 | Composition | ✅ | `22-Composition.md` |

## Part XI — Advanced OOP

| # | Chapter | Status | File |
|---|---------|--------|------|
| 23 | Nested Classes | ✅ | `23-Nested-Classes.md` |
| 24 | Anonymous Classes | ✅ | `24-Anonymous-Classes.md` |
| 25 | Enums | ✅ | `25-Enums.md` |
| 26 | Records | ✅ | `26-Records.md` |
| 27 | Sealed Classes | ✅ | `27-Sealed-Classes.md` |
| 28 | Immutability | ✅ | `28-Immutability.md` |
| 29 | Object Cloning | ✅ | `29-Object-Cloning.md` |
| 30 | Reflection | ✅ | `30-Reflection.md` |
| 31 | Annotations | ✅ | `31-Annotations.md` |
| 32 | Generics | ✅ | `32-Generics.md` |
| 33 | Comparable vs Comparator | ✅ | `33-Comparable-vs-Comparator.md` |

## Part XII — Object-Oriented Design

| # | Chapter | Status | File |
|---|---------|--------|------|
| 34 | SOLID Principles | ✅ | `34-SOLID-Principles.md` |
| 35 | Dependency Injection | ✅ | `35-Dependency-Injection.md` |

## Part XIII — JVM Internals

| # | Chapter | Status | File |
|---|---------|--------|------|
| 36 | Object Lifecycle | ✅ | `36-Object-Lifecycle.md` |
| 37 | Memory Management | ✅ | `37-Memory-Management.md` |
| 38 | Garbage Collection | ✅ | `38-Garbage-Collection.md` |
| 39 | Class Loading | ✅ | `39-Class-Loading.md` |
| 40 | Method Dispatch | ⬜ | `40-Method-Dispatch.md` |

---

## Session Log

| Session | Chapters Touched | Notes |
|------|-------------------|-------|
| 1 | Chapter 1 drafted | Repo scaffolded: `Topics/OOP/OOP_Handbook/ (chapter files) and Topics/OOP/README.md (front matter)`. |
| 2 | Chapter 1 rewritten (full version) | Replaced with the expanded, book-style draft. Front matter (Preface, Philosophy, Roadmap, Icons) moved out of the chapter file into `README.md` so it isn't duplicated per chapter. Three duplicated stub sections (short pre-drafts of 1.10, 1.11, 1.12 immediately followed by their own full versions) were removed. Added a "Self Assessment" section per the required chapter template, which was missing. |
| 3 | Chapter 2 completed | Formal definition of a class, syntax anatomy, compiler behaviour (`.class` generation, one-public-class-per-file rule), and Metaspace vs. Heap distinction. Introduced the running `Employee` example that later chapters (Object, Constructors, Encapsulation, Inheritance) will extend rather than redefine. Deliberately deferred: constructors (full detail → Ch5), static members (→ Ch9), initializer blocks (→ Ch6), nested classes (→ Ch23), full class-loading pipeline (→ Ch39). |
| 4 | Chapter 1 trimmed (2,281 → ~350 lines) | Cut generic computer-history padding (deep Machine/Assembly Language sections merged into one tight section), condensed Modular Programming, removed triple-repeated "advantages of OOP" content (was stated separately in §1.9, §1.11, §1.13 of the old draft — now one clean list), cut the 14-category Applications list down to the handful most relevant to enterprise/Spring Boot work. Section numbers changed as a result — see updated references below. Chapter 2's back-reference to Chapter 1 was updated to match (§1.9/§1.11 → §1.5/§1.7). |
| 5 | Chapter 3 completed | The five-step object-creation pipeline (allocation → default init → field init → constructor → reference returned), reference-vs-object distinction, object header/memory layout, aliasing, and `==` vs `.equals()` at a conceptual level (full `.equals()`/`hashCode()` override mechanics deferred to Ch19). Extended the running `Employee` example with actual instantiation. |
| 6 | Chapter 4 completed | Instance vs. static vs. local variables, with a unified Stack/Heap/Metaspace memory diagram tying together Ch2 (Metaspace) and Ch3 (Heap, object layout). Covered definite-assignment (why locals get no default value, unlike fields), scope, shadowing (setting up Ch8's `this`), and `var` type inference. Depth calibrated topic-driven per Mahi's latest guidance, not to a fixed line-count target. |
| 7 | Chapter 5 completed | Opened the "Constructor Execution" black box from Ch3 §4: no-return-type rule, the default-constructor-disappears-once-you-add-any-constructor gotcha, overloading, `this(...)` chaining rules, a preview of implicit `super()` (full detail deferred to Ch15), the copy-constructor pattern (deep vs. shallow copy flagged forward to Ch29), and private constructors as a Singleton preview (full pattern deferred). |
| 8 | Chapter 6 completed | Closed the loop on Ch3 §4's "Field Initialization" step: field initializers, instance initializer blocks (run every construction, before ctor body), static initializer blocks (run once, at class load). Assembled the full master object-creation order (Ch3 alloc/default-init → parent init preview → field init/instance blocks in source order → Ch5 ctor body → reference returned). Covered the forward-reference-yields-default-value trap and the compiler fact that instance initializers are inlined into every constructor after `super()`, while static initializers become a single `<clinit>`. |
| 9 | Chapter 7 completed | Method signature (name + params, no return type), overloading resolved at compile time, and — the chapter's core — the definitive pass-by-value proof for both mutation-through-reference and reassignment-of-reference cases, resolving the "tricky question" Ch3 §14 deliberately deferred here. Tied method calls back to Ch4's Stack model via stack frames. Introduced `invokevirtual`/dynamic dispatch only as a forward-looking label, deferred fully to Ch16/Ch40. Distinguished overloading (this chapter) from overriding (Ch16) explicitly to prevent the two being conflated later. |
| 10 | Chapter 8 completed | `this` as the implicit per-call object reference; resolved Ch4 §5.3's shadowing bug properly; covered passing/returning `this` (method chaining, Builder-pattern preview); explained why `this` can't exist in a static context both conceptually (Ch4 §4) and at the bytecode level (`this` is an invisible first parameter of every instance method); noted `this` behaves like an implicit `final` reference, previewing Ch10. Kept intentionally leaner than Ch5-7, matching the topic's genuinely narrower scope per Mahi's topic-driven-depth guidance. |
| 11 | Chapter 9 completed | Capstone chapter for `static` — added static methods (new content) and assembled them with static variables (Ch4 §4) and static blocks (Ch6 §3.3) into one unified table (§6). Covered class-name vs. instance-reference calling style, static utility classes (paired with Ch5 §8's private constructor pattern), and the `invokestatic` vs. `invokevirtual` (Ch7 §6) distinction that explains *why* static methods are hidden, not overridden — a direct preview seed for Ch15/16. Included the null-reference-static-call trivia as a JVM-mechanics-grounded gotcha. |
| 12 | Chapter 10 completed | Formalized `final` across its three distinct targets: variables (incl. blank finals and the "final reference ≠ immutable object" trap tied back to Ch3 §3.2), methods (blocks overriding, not inheriting — distinct axis from Ch9 §5's static hiding), and classes (explains Ch9 §4's `final class SalaryUtils` retroactively). Covered constant folding as real JVM/compiler depth, including the stale-constant-after-library-update gotcha. Explicitly flagged that `final` alone isn't sufficient for true immutability, pointing forward to Ch28. |
| 13 | Chapter 11 completed | Closed Part VI (Keywords). Delivered the full `super()` mechanics Ch5 §6 and Ch6 §4.2 both deferred here: constructor-call rules, the no-accessible-no-arg-parent-constructor gotcha, `super.field`/`super.method()`, and the complete concrete initialization order once a parent class is involved. Key JVM fact: `super.method()` compiles to `invokespecial` (static resolution), not `invokevirtual` (Ch7 §6) — explains why it can't recurse into an override. Used a deliberately disposable `Vehicle`/`Car` pair (not the running `Employee` class) to avoid front-running Ch15's real inheritance example; explicitly scoped out "when/why to use inheritance," which stays Ch15/16's job. |
| 14 | Chapter 12 completed | Opened Part VII (Object Design) and delivered the first of the Four Pillars in full, formalizing the private-fields-plus-methods pattern every `Employee` example has quietly used since Ch2. Core contribution: distinguished genuine encapsulation (validated setters, enforced invariants) from the mechanical private-field-plus-pass-through-getter/setter anti-pattern, which offers no more protection than a public field. Covered read-only fields (getter only, paired with Ch10's blank final) and a strong JVM fact — access control is checked twice, once by the compiler and again independently by the JVM bytecode verifier at class-loading time, making it a real security boundary rather than a compile-time-only convention. Used only `private`/`public` informally; full access modifier system deferred to Ch13. |
| 15 | Chapter 13 completed | Formalized all four access levels (`private`, package-private/default, `protected`, `public`) with a single comparison table. Two strongest contributions: (1) package-private ≠ private — a very common misconception, named explicitly; (2) `protected`'s genuinely subtle cross-package subclass rule — access only through the subclass's own declared type, never through a parent-typed reference, even to the same runtime object — real interview-differentiating depth. Gave `Employee` a `protected department` and package-private `team` field specifically as groundwork for Ch15's `Manager extends Employee`. Noted the `ACC_PRIVATE`/`ACC_PROTECTED`/`ACC_PUBLIC` class-file flags as the concrete mechanism behind Ch12 §6's two-layer enforcement, without repeating that explanation. |
| 16 | Chapter 14 completed | Closed Part VII (Object Design). Formalized packages as namespace + mandatory directory mapping, fully-qualified names, and imports — including explicitly debunking the "wildcard imports are slower" myth (compile-time-only resolution, zero runtime cost). Covered `java.lang`'s implicit-import special case, static imports briefly, and how the JVM's classloader translates a fully-qualified name into a classpath search (previewing Ch39). Gave `Employee`/`SalaryUtils` real package names (`com.acme.hr` / `com.acme.payroll`) for the first time, making Ch13's access-level table physically concrete. |
| 17 | Chapter 15 completed | Opened Part VIII (Core OOP). Delivered `Manager extends Employee` — the payoff this handbook has been building toward since Ch5. Covered the is-a test, exactly what's inherited (incl. the "private fields exist in the object but aren't accessible by name" nuance), and — the chapter's strongest contribution — a single comparison table distinguishing overloading (Ch7), overriding (new here), and static method hiding (Ch9), which are commonly conflated as one idea. Covered override rules (signature, covariant return, visibility, `final`), `@Override` as compile-time-only, single inheritance and the diamond problem, the three inheritance shapes (single/multilevel/hierarchical), and `Object` as the implicit universal parent (full treatment: Ch19). Deliberately stopped short of dynamic dispatch mechanics — reserved whole for Ch16. |
| 18 | Chapter 16 completed | Delivered dynamic method dispatch in full — the mechanism Ch15 deliberately deferred. Explained the virtual method table (vtable) concept and finally resolved `invokevirtual`'s preview from Ch7 §6, contrasted against `invokestatic` (Ch9 §5 — no vtable at all) and `invokespecial` (Ch11 §4 `super.method()` — bypasses the vtable deliberately). Strongest original contribution: fields are NEVER polymorphic in Java — always resolved by declared reference type at compile time, unlike methods — demonstrated with a field-hiding example contrasted directly against the method-overriding case. Covered upcasting, `instanceof`/downcasting (incl. Java 16+ pattern matching), and a polymorphic `List<Employee>` payroll loop tying back to Ch1 §1.6's extensibility promise. |
| 19 | Chapter 17 completed | Completed the Four Pillars trilogy (Encapsulation → Inheritance → Polymorphism → Abstraction). Made `Employee` itself `abstract` with an `abstract getSalary()`, following through on Ch16's closing observation that no role-less Employee should exist as an object — `new Employee(...)` now a genuine compile error, `Manager`/`Intern` unaffected. Strongest contribution: explained *why* an abstract method can never be `private`, `static`, or `final` by connecting three earlier chapters (Ch13 visibility, Ch9/Ch16 dynamic dispatch, Ch10 final-blocks-overriding) into one coherent reason rather than three memorized rules. Also resolved the common "why does an abstract class need a constructor" confusion via Ch11's `super()` mechanics. Interfaces explicitly previewed, not covered — reserved whole for Ch18. |
| 20 | Chapter 18 completed | Closed Part VIII (Core OOP) and Java's full abstraction toolkit. Delivered the interface/abstract-class comparison table Ch17 §8 promised, the implicit-`public`-methods/implicit-`public static final`-fields nuance, multiple interface implementation vs. single class inheritance, default methods (Java 8+) with the real `Collection.forEach()` backward-compatibility motivation, and the partial diamond-problem return with default methods (resolved by forcing explicit override, same philosophy as Ch15 §5). Genuinely good JVM depth: `invokeinterface` as a distinct dispatch mechanism from `invokevirtual` (Ch16 §4.2), needed because a class implementing many interfaces doesn't fit one fixed-slot vtable. `Employee` now both `abstract` (is-a hierarchy) AND `implements Payable` (capability), combined on one class exactly as real Java code does. |
| 21 | Chapter 19 completed | Opened and closed Part IX. Delivered the `equals()`/`hashCode()` override mechanics Ch3 §8 flagged forward from the very start of this handbook. Core contribution: walked through, concretely, exactly what breaks in a `HashSet`/`HashMap` when `equals()` is overridden without `hashCode()` (objects land in the wrong bucket, `.contains()` silently returns false) — one of the highest-value, most commonly tested Java topics, now explained mechanically rather than just stated as a rule. Covered the full equals() contract, `Objects.equals()`/`Objects.hash()` helpers, `toString()`'s default output explained, `getClass()` vs. `instanceof` in equals() (subclass symmetry tradeoff), and why an identity field should be immutable (tied back to Ch10's blank final `employeeId`) — a mutable-field-based hash code makes an object unfindable in its own collection after mutation. Noted `Object`'s methods occupy the root vtable slots (Ch16 §4.2) every class ultimately builds on. |
| 22 | Chapter 20 completed | Opened Part X (Object Relationships). Formalized Association as the umbrella has-a/uses-a category — explicitly framed as a design vocabulary for the reference-holding pattern already used since Ch3, not new syntax. Covered unidirectional vs. bidirectional, multiplicity, and the concrete bidirectional-sync bug (nothing in Java keeps both sides consistent automatically). Upgraded `Employee`'s `protected String department` (a plain string since Ch13) into a real `Department` object association with a proper `addEmployee()` that keeps both sides in sync — a deliberate, visible evolution of the running example now that the right design tool is available. Set up Aggregation (Ch21) and Composition (Ch22) explicitly as stronger, lifecycle-distinguished sub-kinds of this chapter's general relationship. |
| 23 | Chapter 21 completed | Delivered Aggregation as a whole-part relationship where the part survives the whole and can be reassigned — refined, rather than replaced, Ch20's `Department`/`Employee` association by applying the "does the part outlive the whole?" test to it directly (yes → this is Aggregation, specifically). Core contribution: the concrete, code-visible mechanical signal distinguishing aggregation from composition — the whole *receives* pre-built parts from outside rather than constructing them itself — demonstrated with `Department` never calling `new Employee(...)` internally. Showed a `Manager` object surviving one `Department`'s dissolution and being reassigned to another without reconstruction. Brief UML hollow-diamond notation note. Deliberately kept shorter than Ch20, since it builds entirely on that chapter's mechanics rather than introducing new ones. |
| 24 | Chapter 22 completed | Closed Part X (Object Relationships). Delivered Composition as the mirror image of Ch21's Aggregation — the whole constructs its parts internally (`Employee` creating its own `ContactInfo`) rather than receiving them from outside, with lifecycle fully bound together. Capstone contribution: finally justified "favor composition over inheritance" — quoted without explanation since Ch1 §1.8 and Ch15 §10 — with concrete reasoning (fragile base class problem from Ch1 §1.9's deep-hierarchy concern, Ch15 §5's single-inheritance limit, and runtime-swappable parts via a `Car`/`Engine` example) now that both Inheritance and Composition are fully in view. Flagged the subtle mistake of a constructor/setter accidentally accepting a pre-built part, silently turning Composition into Aggregation. Set up `ContactInfo` as a natural candidate for a nested class, previewing Ch23. |
| 25 | Chapter 23 completed | Opened Part XI (Advanced OOP). Formalized static nested classes vs. inner classes as Ch9's static-vs-instance distinction applied one level up — `ContactInfo` (Ch22) correctly becomes a static nested class (no enclosing-instance access needed), while a new `SalarySummary` inner class demonstrates genuine instance-dependent nesting. Strong JVM contribution: the synthetic `this$0` hidden field an inner class carries, directly paralleling Ch8 §6's implicit `this` parameter fact. Covered local classes and explained precisely *why* they can only capture effectively-final variables — the compiler copies captured values at creation time because the original Stack-resident local variable (Ch4 §5) won't outlive its method call, so a reassignable capture would go stale. Anonymous classes explicitly reserved whole for Ch24. |
| 26 | Chapter 24 completed | Delivered anonymous classes as the fourth and final nesting form — a nameless local class (Ch23 §6), inheriting its effectively-final capture rule unchanged. Clarified that `new Payable() { ... }` never instantiates the interface itself (Ch18 §2.1), but a compiler-generated implementer of it. Covered the two restrictions unique to anonymous classes: no explicit constructor (instance initializer block instead, Ch6 §3.2) and exactly one supertype total — narrower than a named class's "one class + several interfaces" allowance (Ch15 §5, Ch18 §3). Strongest contribution: the precise, frequently-tested anonymous-class-vs-lambda distinction — `this` inside an anonymous class is its own new instance, while `this` inside a lambda is the *enclosing* instance — kept scoped as a `this`-identity comparison only, not a full lambda/functional-interface tutorial (outside this handbook's OOP focus). Compiler naming (`Employee$1`, `$2`, ...) extends Ch2 §5.1's pattern. |
| 27 | Chapter 25 completed | Delivered enums as genuine singleton-instance classes, not labeled integers — the compiler generates each constant as a `public static final` field inside a static initializer (the exact mechanism from Ch6 §3.3), plus free `toString()`/`name()`/`ordinal()`/`values()`/`valueOf()`. Covered why an enum can never `extends` another class (already uses its one Ch15 §5 parent slot) but can implement interfaces, why enum constructors are always private (an automatic, total version of Ch5 §8's pattern), and per-constant method bodies as anonymous subclasses — a direct, precise callback to Ch24's compiler naming. Strongest contribution: `==` is actually correct and preferred for enum comparison (unlike ordinary objects, Ch19 §4) since every constant is a guaranteed singleton, plus the `ordinal()`-persistence fragility trap. Closed with explicit enum-vs-subclass design guidance contrasting `EmployeeType` against the existing `Manager`/`Intern` hierarchy (Ch15). |
| 28 | Chapter 26 completed | Delivered records as auto-generated immutable data carriers — a satisfying, concrete callback to Ch22/23's hand-written `ContactInfo`, which turns out to have been silently incomplete by Ch19's standard (no `equals()`/`hashCode()`/`toString()` were ever added to it) the whole time. Explained why a record can never extend another class (implicit `Record` superclass, same structural situation as Ch25's `Enum`) and can never be subclassed (implicitly `final`, preserving its transparency contract). Covered compact constructors for validation (preserving Ch12's "never constructible into an invalid state" principle) and the deliberate `x()`-not-`getX()` accessor naming break from Ch12's convention. Capstone synthesis: explicitly tested whether `Employee` itself could be a record and gave three independent disqualifying reasons (mutable state, inheritance participation, abstraction) — a genuine tie-together of Ch12, Ch15, and Ch17. |
| 29 | Chapter 27 completed | Sealed `Payable`/`Employee` — closed set of permitted subclasses (`permits`), each forced to declare `final`/`sealed`/`non-sealed` explicitly, no default. Core payoff: compiler-verified exhaustive handling, contrasted against Ch15's fully-open inheritance and Ch25's enum (structurally-identical-variants) case via a precise three-way test. Genuinely strong JVM callback: `PermittedSubclasses` class-file attribute checked again by the JVM verifier — same two-layer enforcement pattern as Ch12 §6's access modifiers. |
| 30 | Chapter 28 completed | Delivered the full immutability checklist, finally resolving Ch10 §3.4's deferred promise. Core contribution: concretely demonstrated the leak `final` fields alone permit (a `final List` field can still be mutated via the caller's kept reference OR via the getter's returned reference — both independently, both requiring separate defensive copies) and showed records (Ch26) have the identical gap unless a compact constructor defensively copies mutable components. Closed with the thread-safety payoff, tied explicitly back to Ch1 §1.3's original shared-mutable-data problem — a full-circle callback to the handbook's opening argument. |
| 31 | Chapter 29 completed | Formalized shallow vs. deep copy precisely, then delivered the classic critique of `Cloneable`/`clone()`: `Cloneable` is a no-method marker interface (unlike every Ch18 interface), `Object.clone()` bypasses the entire Ch3 §4 constructor pipeline via direct memory copy (skipping Ch12 validation entirely), defaults to shallow, and forces handling a `CloneNotSupportedException` that a correct implementation can never trigger. Closed by recommending Ch5 §7's copy constructor as the modern alternative — same deep-copy safety, real constructor pipeline, none of clone()'s baggage. |
| 32 | Chapter 30 completed | Delivered reflection in full, directly connected to Mahi's stated Spring Boot direction. Core contribution: named plainly that reflection can bypass Ch12/Ch13's encapsulation entirely via `setAccessible(true)` — a deliberate, sanctioned escape hatch, not an accidental hole — and walked through conceptually how dependency injection frameworks actually use this (scan → construct via reflection → inject into private fields) to explain what Spring Boot is doing under the hood. Contrasted `Constructor.newInstance()` (goes through the real Ch3 §4 pipeline) against Ch29's `clone()` (bypasses it entirely) — a precise, useful distinction. Covered the three ways to get a `Class` object and reflection's real performance cost vs. Ch16 §4.2's direct `invokevirtual` dispatch. |
| 33 | Chapter 31 completed | Closed the reflection+annotations story that began in Ch30 §7's imagined `@Autowired` example. Retroactively named `@Override` (used since Ch15 §4.3 without ever being called "an annotation") as a `SOURCE`-retention annotation — a satisfying, precise callback. Covered custom `@interface` declarations (a distinct form from Ch18's ordinary interfaces despite the shared keyword), `@Retention`/`@Target` meta-annotations, and — the chapter's centerpiece — a complete, working mini audit-framework example combining Ch19's `getClass()`, Ch30's reflection, and this chapter's annotations into one end-to-end demonstration of how Spring-style frameworks actually scan/construct/invoke dynamically. |
| 34 | Chapter 32 completed | Delivered generics from first principles, framed explicitly as the same compile-time-over-runtime-error preference this handbook has repeated since Ch4 §5.1 and Ch15 §4.3. Covered generic classes/methods, bounded type parameters (unlocking `Employee`'s methods on `T` via `<T extends Employee>`), wildcards with the PECS rule, and — genuinely precise, frequently-tested content — exactly why `List<Manager>` is not a subtype of `List<Employee>` despite `Manager IS-A Employee` (Ch15), walked through via the specific unsafe operation it would permit. Closed with type erasure as the key JVM fact: `Box<Employee>`/`Box<Intern>` share one runtime class (contrast Ch2 §5.1's genuinely distinct `Manager`/`Intern` classes), explaining why `new T()` and generic `instanceof` are both illegal. Built a bounded `Repository<T extends Employee>` example previewing Spring Data's pattern. |
| 35 | Chapter 33 completed | Closed Part XI (Advanced OOP). Delivered `Comparable<T>` (one natural order, inside the class) vs. `Comparator<T>` (unlimited external orders) as a precise structural distinction. Strongest contribution: a concrete `TreeSet` failure scenario showing exactly what breaks when `compareTo()` disagrees with `equals()` (Ch19) — `TreeSet`/`TreeMap` use `compareTo()` alone for uniqueness, never consulting `equals()`/`hashCode()` at all, so two `!equals()` objects with `compareTo() == 0` silently collapse into one element. Also flagged raw `Comparable` (no type argument) as a direct Ch32 generics/type-erasure mistake, and mentioned `thenComparing` chaining briefly without diving into functional-interface territory. |
| 36 | Chapter 34 completed | Opened Part XII (Object-Oriented Design). Assembled all five SOLID principles, deliberately reusing rather than re-deriving existing running-example content: SRP ↔ Ch22's `ContactInfo` extraction, OCP ↔ Ch16 §7's polymorphic payroll loop (with an explicit, precise note on Ch27's sealed classes as a *deliberate exception* to OCP, not a contradiction), LSP formalized via an `Intern.getSalary()`-throws-instead-of-returning violation (named as the principle that makes Ch16's whole polymorphism chapter safe to rely on), ISP delivering on Ch18 §7's forward promise via a bloated-`Payable`-split example, and DIP ↔ Ch22 §6.2's `Car`/`Engine` example. Closed with a precise Dependency Inversion (principle) vs. Dependency Injection (pattern/mechanism) distinction, setting up Ch35 directly. |
| 37 | Chapter 35 completed | Closed Part XII. Delivered the DI pattern in full — constructor injection (recommended, ties to Ch10 final + Ch12 valid-construction), setter injection (optional deps), and field injection (weakest: no final, hidden deps, harder to test, mechanically Ch30 §4's `setAccessible(true)`+`Field.set()`). Genuine capstone moment: walked through exactly how a Spring-style IoC container performs IoC using ONLY mechanics this handbook already built — Ch30's reflection (scanning, `Constructor.newInstance()`, field injection) plus Ch31's annotations (`isAnnotationPresent` on `@Autowired`) — nothing new introduced, just assembled. Named "Inversion of Control" precisely as the broader idea DI implements. Noted current, correct Spring guidance: constructor injection over field injection despite the latter's historical prevalence in tutorials. |
| 38 | Chapter 36 completed | Opened Part XIII (JVM Internals), the handbook's final part. Assembled the complete object lifecycle — class loading (Ch2 §6.2) → creation (synthesized Ch3 §4 + Ch6 §4 + Ch11 §3 into one unified diagram, a genuine capstone visual) → in use → unreachable → GC-eligible → reclaimed (Ch38 in full). Core new contribution: formalized reachability (GC roots: stack variables, live static fields, thread references) as the actual basis for collection eligibility — not reference counting — demonstrated with the "islands of isolation" case (mutually-referencing objects with no external path in are still correctly eligible). Covered `finalize()`'s deprecated status and `try`-with-resources/`AutoCloseable` as the modern replacement. |
| 39 | Chapter 37 completed | Opened the Heap's internal structure — the third and final memory region, after Stack (Ch4 §5) and Metaspace (Ch2 §6.1) were already fully covered elsewhere. Delivered the generational hypothesis as the reasoning behind Eden → Survivor space(s) → Old Generation, minor vs. major/full GC cost asymmetry, and TLABs as the concrete reason ordinary allocation is cheap despite being automatic. Strongest contribution: connected object promotion directly to Ch3 §6's Mark Word — the age counter that was always part of that object header discussion, now given specific, concrete content instead of staying an abstract mention. Related the whole chapter back to typical Spring Boot request-handling workloads (mostly short-lived objects) as a practical, domain-relevant closing point. |
| 40 | Chapter 38 completed | Delivered the payoff for this handbook's oldest, most-repeated forward pointer (first made Ch3 §10). Covered mark-sweep-compact in full (mark from GC roots, sweep unmarked, compact to eliminate fragmentation — with a concrete reason compaction matters beyond tidiness), then explained precisely why minor GC uses copying instead (Ch37 §2's generational hypothesis makes copying the few survivors cheaper than sweeping a mostly-dead region) while major/full GC uses mark-sweep-compact (Old Gen's much higher live ratio makes copying wasteful there) — a direct, deliberate asymmetry stemming from Ch37's generational split. Explained stop-the-world pauses as a genuine correctness requirement during marking, not an arbitrary inconvenience. Closed with `System.gc()` as hint-not-guarantee, reinforcing Ch36 §9, and brief recognition-level namedrops of Serial/Parallel/G1/ZGC/Shenandoah. |
| 41 | Chapter 39 completed | Delivered the complete Loading → Linking (Verification, Preparation, Resolution) → Initialization pipeline, closing off forward references from Ch2, Ch4, Ch9, Ch10, Ch12, Ch14, Ch19, Ch27, and Ch30 all at once. Strongest contribution: precisely located Ch12's bytecode verifier and Ch27's `PermittedSubclasses` check inside Verification specifically, rather than a vague "somewhere during loading." Enumerated the exact loading-trigger events, including the compile-time-constant exception (Ch10 §6's constant folding — referencing a constant doesn't load its class at all). Introduced classloader delegation as genuinely new content — the parent-first check that guarantees core Java classes can't be shadowed, plus the name+classloader runtime identity fact. Full pipeline traced concretely against `Manager`/`Employee`/Ch27's sealed hierarchy. |

---

## Concepts Already Explained (do not repeat — link back instead)

- **Programming paradigm evolution** (Machine → Assembly → High-Level → Procedural → Modular → OOP) — Chapter 1, §1.1–1.4.
- **Why OOP exists / problems it solves** — Chapter 1, §1.6.
- **Core OOP characteristics** — Chapter 1, §1.7.
- **Four Pillars of OOP** — Chapter 1, §1.8 (preview); all four fully covered: Encapsulation (Ch. 12), Inheritance (Ch. 15), Polymorphism (Ch. 16), Abstraction (Ch. 17, extended by interfaces in Ch. 18). "Favor composition over inheritance" fully justified in Ch. 22 §6.
- **Formal definition of a class, class syntax anatomy, one-public-class-per-file rule, class metadata vs. object data (Metaspace vs. Heap), lazy class loading, `Outer$Inner.class` naming (incl. sequential `$1`/`$2` for anonymous classes)** — Chapter 2, §3–§6, §5.1.
- **The running `Employee` example class** — defined Ch2 §7; instantiated Ch3 §9; instance/static variables Ch4 §8; full constructor set Ch5 §10; static/instance initializer blocks Ch6 §7; behavior methods Ch7 §7; `this` usage Ch8 §7; static factory method Ch9 §8; blank final/constant/final method Ch10 §7; encapsulated Ch12 §7; `protected`/package-private fields Ch13 §8; lives in `com.acme.hr` Ch14 §5; first subclass `Manager` Ch15 §9; sibling `Intern` + polymorphic processing Ch16 §7; made `abstract` Ch17 §3, §9; `implements Payable` Ch18 §6; `equals()`/`hashCode()`/`toString()` overrides Ch19 §9; `department` upgraded to a `Department` association Ch20 §5, reclassified as Aggregation Ch21 §2.2; composes `ContactInfo` internally Ch22 §3; `ContactInfo`/`SalarySummary` nested properly Ch23 §7; demonstrated via one-off anonymous implementations Ch24 §6; companion `EmployeeType` enum introduced Ch25 §9; **`ContactInfo` converted from a hand-written nested class into a validated record in Chapter 26, §9 — `Employee` itself explicitly confirmed NOT to be a record candidate (§8: mutable state, inheritance, abstraction).**
- **Object creation pipeline, reference vs. object, object header layout, aliasing, `==` vs. `.equals()`; full `.equals()`/`hashCode()` mechanics delivered in Ch. 19** — Chapter 3, §3–§8.
- **Instance vs. static vs. local variables — memory location, default-value behaviour, scope, shadowing, `var` type inference** — Chapter 4, §2–§7. (Local-variable Stack lifetime directly explains both Ch. 23 §6.1's and Ch. 24 §3.3's effectively-final capture rule.)
- **Constructors — no return type, default constructor rule, overloading, `this(...)` chaining, copy constructor, private constructors** — Chapter 5, §3–§8.
- **Field/static/instance initializer blocks, the object-creation order (single-class case), forward-reference trap, `<clinit>`/inlined-into-constructors behaviour** — Chapter 6, §3–§6. (Instance initializer blocks now also the standard workaround for anonymous classes' lack of a constructor, Ch. 24 §3.1.)
- **Method signature, overload resolution at compile time, the full pass-by-value proof, stack frames per method call** — Chapter 7, §2–§6.
- **`this` — implicit object reference, shadowing fix, passing/returning `this`, unavailable in static context, invisible-first-parameter bytecode fact** — Chapter 8, §2–§6. (Parallels Ch. 23 §5's `this$0` and contrasts directly with Ch. 24 §5's anonymous-class-vs-lambda `this` binding.)
- **`static` methods, class-name-vs-instance calling style, static utility classes, unified static-variable/static-block/static-method picture, static nested classes** — Chapter 9, §2–§7.
- **`final` variables/methods/classes, constant folding** — Chapter 10, §2–§6. (True immutability design still belongs fully to Ch. 28.)
- **`super()` constructor-call rules, initialization order with a parent class, `super.field`/`super.method()` static resolution** — Chapter 11, §2–§4.
- **Encapsulation — validated accessors vs. pass-through anti-pattern, read-only fields, two-layer access enforcement** — Chapter 12, §2–§6.
- **All four access levels, package-private-vs-private, `protected`'s cross-package subclass-own-type rule, `ACC_*` flags** — Chapter 13, §2–§7.
- **Packages — namespace + directory mapping, fully-qualified names, imports (wildcard myth debunked), classloader mapping** — Chapter 14, §2–§4.
- **Inheritance — is-a test, what's inherited/never-inherited/present-but-inaccessible, override rules, `@Override`, overload/override/hide distinction, single inheritance and the diamond problem, inheritance shapes, `Object` as implicit parent** — Chapter 15, §2–§8.
- **Dynamic dispatch (vtable, `invokevirtual`), compile-time vs. runtime polymorphism, upcasting, `instanceof`/downcasting, fields never polymorphic** — Chapter 16, §2–§6.
- **Abstract classes/methods — no direct instantiation, mandatory implementation or propagate `abstract`, incompatible with `private`/`static`/`final`, constructors still work via `super()`, `ACC_ABSTRACT`** — Chapter 17, §2–§7.
- **Interfaces — implicit `public`/`abstract` methods and `public static final` fields, multiple implementation, default methods and their backward-compatibility motivation, the partial diamond-problem return, `invokeinterface`, abstract-class-vs-interface decision table** — Chapter 18, §2–§5.
- **`Object`'s default `toString()`/`equals()`/`hashCode()`, correct override patterns, the full equals() contract, the equals/hashCode pairing requirement and its concrete `HashSet` failure mode, `Objects.equals()`/`Objects.hash()`, `getClass()` vs. `instanceof` in `equals()`, why identity fields should be immutable** — Chapter 19, §3–§9.
- **Association — the has-a/uses-a umbrella relationship, unidirectional vs. bidirectional, multiplicity, the bidirectional-sync bug, and Association's status as design vocabulary rather than new syntax** — Chapter 20, §2–§6.
- **Aggregation — whole-part relationship where parts outlive and can be reassigned away from the whole, the "does the part survive?" test, the concrete mechanical signal (whole receives pre-built parts), the UML hollow-diamond notation** — Chapter 21, §2–§5.
- **Composition — whole-part relationship where the part has no independent existence and is created/destroyed with the whole, its mechanical signal (whole constructs parts internally), the UML filled-diamond notation, the full "favor composition over inheritance" justification, the subtle Composition→Aggregation drift mistake** — Chapter 22, §2–§7.
- **Nested classes — static nested classes vs. inner classes (`this$0`), the `outer.new Inner()` syntax, local classes and the effectively-final capture rule** — Chapter 23, §2–§6.
- **Anonymous classes — nameless local classes instantiated inline, no explicit constructor (instance initializer block instead), exactly one supertype (never both a class and an interface), identical effectively-final capture rule, sequential `$N` compiler naming, and the precise anonymous-class-vs-lambda `this`-binding distinction** — Chapter 24, §2–§5. (Lambdas and functional interfaces themselves remain outside this handbook's OOP scope, mentioned only for the `this` contrast.)
- **Enums — each constant a genuine compiler-generated singleton (not a labeled integer), the implicit `Enum` superclass and its single-inheritance consequence, always-private constructors, per-constant method bodies as anonymous subclasses, why `==` is correct/preferred for enum comparison, the `ordinal()` persistence trap, and enum-vs-subclass design guidance** — Chapter 25, §2–§8.
- **Records — auto-generated private final fields, canonical constructor, named (not `getX()`) accessors, correctly-paired `equals()`/`hashCode()`/`toString()` by construction; implicit `Record` superclass and implicit `final`; compact constructors; three-part disqualification test** — Chapter 26, §2–§8.
- **Sealed classes — `permits`, mandatory `final`/`sealed`/`non-sealed` choice on every permitted subclass (no default), compiler-verified exhaustive handling, sealed-vs-enum-vs-open-inheritance test, `PermittedSubclasses` class-file attribute checked at the JVM verifier level** — Chapter 27, §2–§6.
- **Immutability — full checklist (final fields + no setters + protected class + defensive copies both directions for mutable-typed fields), the concrete final-field-still-leaks demonstration, records needing compact-constructor copies too, thread-safety payoff tied to Ch1 §1.3** — Chapter 28, §2–§6.
- **Object Cloning — shallow vs. deep copy, `Object.clone()`'s default shallow behavior and total constructor-pipeline bypass, `Cloneable`'s no-method-contract oddity, `CloneNotSupportedException`'s awkwardness, copy constructors as the recommended alternative** — Chapter 29, §2–§5.
- **Reflection — three ways to get a `Class` object, `getDeclaredX()` vs. `getX()`, `setAccessible(true)` as a deliberate bypass of Ch12 §6's two-layer access enforcement, `Constructor.newInstance()` going through the real constructor pipeline (unlike `clone()`), `Method.invoke()` still using real dynamic dispatch underneath, reflection's performance cost vs. `invokevirtual`, the conceptual mechanics of DI frameworks** — Chapter 30, §2–§7.
- **Annotations — metadata with no inherent behavior of its own; `@interface` as a distinct declaration form from Ch18's ordinary interfaces; `@Retention` (`SOURCE`/`CLASS`/`RUNTIME`) and `@Target`; `@Override` retroactively identified as a `SOURCE`-retention annotation; reading annotations reflectively via `isAnnotationPresent()`/`getAnnotation()`; the complete reflection+annotations picture behind DI frameworks** — Chapter 31, §2–§6.
- **Generics — generic classes/methods, bounded type parameters, wildcards and the PECS rule, why `List<Manager>` is not a subtype of `List<Employee>`, and type erasure** — Chapter 32, §2–§8.
- **Comparable vs. Comparator — one natural order (in-class) vs. unlimited external orders, the `compareTo()`/`equals()` consistency recommendation and the concrete `TreeSet`/`TreeMap` uniqueness failure when violated, `thenComparing` chaining, the raw-`Comparable` generics mistake** — Chapter 33, §2–§5.
- **SOLID Principles — SRP, OCP (with sealed classes as a deliberate exception), LSP (underwriting Ch16's polymorphism), ISP, DIP explicitly distinguished from the DI pattern** — Chapter 34, §2–§6.
- **Dependency Injection — constructor/setter/field injection compared precisely, manual DI vs. container-managed DI, the exact Ch30+Ch31 mechanics a Spring-style container uses, Inversion of Control** — Chapter 35, §2–§6.
- **Object Lifecycle — the complete birth-to-death picture, reachability from GC roots, islands of isolation, `finalize()`'s deprecated status** — Chapter 36, §2–§6.
- **Memory Management — the generational hypothesis, Heap internal structure, minor vs. major/full GC cost asymmetry, TLABs, object promotion via the Mark Word age counter** — Chapter 37, §2–§6.
- **Garbage Collection — mark-sweep-compact, copying for minor GC vs. mark-sweep-compact for major/full GC, stop-the-world pauses, `System.gc()` as hint-not-guarantee, modern collector names** — Chapter 38, §2–§7.
- **Class Loading — the complete Loading→Linking(Verification/Preparation/Resolution)→Initialization pipeline, precisely locating Ch12's verifier and Ch27's `PermittedSubclasses` check inside Verification, the exact loading-trigger events including the compile-time-constant exception, and classloader delegation (parent-first, name+classloader runtime identity)** — Chapter 39, §2–§5. Definitive; only reference, don't re-derive.

## Next Up

➡️ Chapter 40 — Method Dispatch (FINAL CHAPTER)

---

## Open Question

- `Topics/OOP/Java_OOP_Handbook.md` — a pre-restructure, single-file handbook draft still sits at the `Topics/OOP/` level, alongside this new chapter-by-chapter `OOP_Handbook/` folder. Decide whether to delete it, archive it, or keep it as a merged single-file export generated later from all 40 chapter files.
