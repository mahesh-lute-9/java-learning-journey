# Chapter 31 — Annotations

**Part XI: Advanced OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain precisely what an annotation is — and, just as importantly, what it never does by itself.
- Declare a custom annotation with elements, and control its visibility and applicability with `@Retention` and `@Target`.
- Explain why `@Retention(RUNTIME)` is specifically required for anything a reflection-based framework needs to read.
- Read annotations reflectively, completing the dependency-injection picture Chapter 30 §7 only sketched conceptually.

---

## 1. Introduction

Chapter 30 §7 described dependency injection frameworks scanning classes for fields "marked with annotations" like `@Autowired`, without explaining what an annotation actually *is*. This chapter delivers it — and along the way, formally names a mechanism this handbook has used since Chapter 15 without ever pausing to define: `@Override`.

---

## 2. Theory — What an Annotation Actually Is

> **An annotation is metadata attached to a program element — a class, method, field, or parameter — that has no effect on execution by itself. Its only effect comes from something else that reads it: the compiler, a reflection-based framework, or a build tool.**

`@Override` (Chapter 15 §4.3) is the annotation this handbook has used most: it attaches a marker to a method, and the *compiler* reads that marker and verifies the method genuinely overrides an inherited one. `@Override` itself performs no action at runtime — remove every `@Override` from a correct program and its behavior is completely unchanged; only the compiler's *checking* is lost.

---

## 3. Declaring a Custom Annotation

```java
public @interface AuditLog {
    String action();
    boolean sensitive() default false;
}
```

`@interface` is its own distinct declaration form — despite sharing the word "interface" with Chapter 18, an annotation cannot be `implements`-ed the normal way, has no method bodies, and is processed entirely differently by the compiler. What look like abstract method declarations here (`action()`, `sensitive()`) are actually **annotation elements** — configurable parameters supplied when the annotation is applied:

```java
public class Employee {
    @AuditLog(action = "SALARY_CHANGE", sensitive = true)
    public void setSalary(double salary) { ... }
}
```

---

## 4. Meta-Annotations: `@Retention` and `@Target`

These two annotations — which annotate annotation *declarations* themselves — control how a custom annotation behaves.

### 4.1 `@Retention` — How Long the Metadata Survives

```java
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditLog { ... }
```

- **`SOURCE`** — discarded entirely by the compiler; never even makes it into the `.class` file. `@Override` uses this — it's a pure compile-time check with nothing left to inspect afterward.
- **`CLASS`** — kept in the `.class` file, but not loaded into the JVM's runtime metadata by default. Rarely used directly.
- **`RUNTIME`** — kept and made available via reflection (Chapter 30) at runtime. **This is the one a framework needs** — without it, an annotation is invisible to any reflective scan, no matter how carefully a class is marked with it.

### 4.2 `@Target` — Where the Annotation May Be Placed

```java
@Target(ElementType.METHOD)
public @interface AuditLog { ... }
```

Restricts which kinds of program elements the annotation can legally be applied to — `TYPE` (classes/interfaces), `FIELD`, `METHOD`, `CONSTRUCTOR`, `PARAMETER`, and others — enforced by the compiler, exactly like any other compile-time rule this handbook has covered.

---

## 5. Reading Annotations Reflectively — Closing the Loop With Chapter 30

This is the missing piece from Chapter 30 §7's imagined `@Autowired` scanning story, now made concrete:

```java
Method setSalary = Employee.class.getMethod("setSalary", double.class);

if (setSalary.isAnnotationPresent(AuditLog.class)) {
    AuditLog log = setSalary.getAnnotation(AuditLog.class);
    System.out.println("Auditing: " + log.action() + " (sensitive=" + log.sensitive() + ")");
}
```

A framework's reflective scan (Chapter 30 §3's `getDeclaredMethods()`, `getDeclaredFields()`) checks each discovered member for `isAnnotationPresent(...)`, and only acts on the ones actually marked — this is genuinely the complete mechanism: **Chapter 30 provides the ability to inspect and act on code dynamically; this chapter provides the targeted metadata that tells a framework exactly *what* to act on, and *how*.** Together, they're the two halves of how Spring Boot — and most Java frameworks — actually work.

---

## 6. Real-World Example — A Tiny Audit Framework

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuditLog {
    String action();
}

public abstract class Employee implements Payable {
    @AuditLog(action = "SALARY_CHANGE")
    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative");
        this.salary = salary;
    }
}
```

```java
public class AuditProcessor {
    public static void invokeWithAudit(Object target, String methodName, Object... args) throws Exception {
        Class<?>[] argTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) argTypes[i] = double.class;   // simplified for this example

        Method method = target.getClass().getMethod(methodName, argTypes);
        if (method.isAnnotationPresent(AuditLog.class)) {
            AuditLog log = method.getAnnotation(AuditLog.class);
            System.out.println("[AUDIT] " + log.action() + " invoked on " + target.getClass().getSimpleName());
        }
        method.invoke(target, args);   // Chapter 30 §6
    }
}
```

```java
Employee emp = new Manager("M001", "Asha", 95000, "", "");
AuditProcessor.invokeWithAudit(emp, "setSalary", 100000.0);
// [AUDIT] SALARY_CHANGE invoked on Manager
```

This tiny example is, structurally, exactly what a real audit-logging or security-checking layer in a production framework does — annotate the methods that matter, and let a generic, reflection-based processor handle every annotated method uniformly, without that processor ever needing to know about `Employee` or `Manager` specifically at compile time.

---

## 7. Best Practices

- Use `RUNTIME` retention only when something (reflection, Chapter 30) genuinely needs to read the annotation at runtime — `SOURCE` (like `@Override`) avoids unnecessary bytecode and runtime presence otherwise.
- Scope custom annotations narrowly with `@Target` to prevent them being misapplied to the wrong kind of element.
- Treat a custom annotation's elements as a real, documented API contract — other developers will read and rely on them exactly like any method signature.
- Remember an annotation never executes anything on its own — always be able to name the specific code (compiler check, reflective processor) that actually gives it effect.

## 8. Common Mistakes

- ⚠️ Forgetting `@Retention(RUNTIME)` on a custom annotation meant to be read via reflection — it silently becomes invisible to `isAnnotationPresent(...)` at runtime with the default retention (§4.1).
- ⚠️ Assuming an annotation has inherent behavior — it never does; all behavior comes from whatever code explicitly reads it (§2).
- ⚠️ Applying an annotation to an element type `@Target` doesn't permit — a compile error, not a runtime surprise.
- ⚠️ Confusing `@interface` with Chapter 18's ordinary `interface` — they share a keyword but are processed by the compiler in entirely different ways (§3).

## 9. Interview Perspective

**Frequently Asked**

- *"What is an annotation, and does it do anything by itself?"* — Metadata attached to a program element; it never executes anything on its own — its effect always comes from something else reading it (§2).
- *"What's the difference between `@Retention(SOURCE)`, `CLASS`, and `RUNTIME`?"* — `SOURCE` is discarded at compile time (like `@Override`); `CLASS` persists in the `.class` file but isn't loaded at runtime by default; `RUNTIME` is available via reflection — required for anything a framework needs to inspect dynamically (§4.1).
- *"How does `@Autowired`-style dependency injection actually see your annotation?"* — Only because it's declared with `RUNTIME` retention; a framework's reflective scan (Chapter 30) checks `isAnnotationPresent(...)` on each member it inspects (§5).

**Tricky Question**

- *"Is `@interface AuditLog { }` literally declaring an interface, the way Chapter 18 covered?"* — No, despite the shared keyword — it's a distinct declaration form: no `implements`, no method bodies, elements rather than abstract methods, and entirely different compiler processing (§3).

**Common Misconception**

- Believing `@Override` (Chapter 15 §4.3) has always been something separate from "annotations" as a general concept. It's been an ordinary, `SOURCE`-retention annotation this entire handbook — the compiler's own use of exactly the mechanism this chapter formalizes, now finally named.

---

## 10. Summary

- An annotation attaches metadata to a program element and has no effect on execution by itself — all behavior comes from something else reading it, whether the compiler (`@Override`) or a reflection-based framework.
- Custom annotations are declared with `@interface`, a distinct form from Chapter 18's ordinary interfaces, with elements rather than methods.
- `@Retention` controls how long an annotation's metadata survives; `RUNTIME` retention is specifically required for anything read via reflection (Chapter 30).
- `@Target` restricts which kinds of program elements an annotation may be applied to, enforced by the compiler.
- Reflection (Chapter 30) plus annotations (this chapter) together are the complete mechanism behind how frameworks like Spring Boot scan, construct, and wire objects dynamically.

## 11. Quick Revision

- Annotation = metadata only; never executes anything by itself.
- `@interface` ≠ Chapter 18's `interface`, despite the shared keyword.
- `@Retention`: `SOURCE` (compile-time only, like `@Override`) / `CLASS` / `RUNTIME` (needed for reflection).
- `@Target` restricts applicable element kinds, compiler-enforced.
- Reflection (Ch. 30) + annotations (this chapter) = how DI frameworks actually work.

## 12. Self Assessment

1. Explain precisely why removing every `@Override` from a correct program changes nothing about how it runs.
2. Why does a custom annotation need `@Retention(RUNTIME)` specifically to work with a reflection-based framework, and what happens if that's omitted?
3. Is declaring `public @interface AuditLog { }` the same mechanism as declaring `public interface Payable { }` from Chapter 18? Justify your answer precisely.
4. Write a custom annotation restricted to fields only, with one required `String` element, and show it applied correctly.
5. Describe, in your own words, how Chapter 30's reflection and this chapter's annotations combine to let a framework discover and act on exactly the methods a developer has marked, and no others.

---

## What's Next

**Chapter 32 — Generics** covers a different kind of compile-time flexibility from this chapter's runtime-oriented tools: writing classes and methods that work with any type while still preserving full compile-time type safety — the mechanism behind `List<Employee>`, already used informally since Chapter 16 §7, now covered from first principles.
