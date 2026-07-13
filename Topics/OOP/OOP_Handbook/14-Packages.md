# Chapter 14 — Packages

**Part VII: Object Design**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain precisely what a package is, and how it maps to a directory structure on disk.
- Write correct package declarations and imports, and explain what a fully-qualified class name actually is.
- Debunk the common "wildcard imports are slower" myth with the actual compile-time mechanism.
- Explain how the JVM locates a class file at runtime using its package path.
- Tie Chapter 13's "same package" access boundary to a concrete, physical mechanism for the first time.

---

## 1. Introduction

Chapter 13 used "same package" as a load-bearing boundary throughout its entire access-level table, without ever defining what a package mechanically *is*. This chapter closes that gap — and with it, closes Part VII (Object Design).

> This chapter doesn't re-explain any access level's semantics — Chapter 13 is definitive for that. It only explains the physical/organizational mechanism "package" that those semantics are built on top of.

---

## 2. Theory — What a Package Actually Is

> **A package is a namespace that groups related classes and interfaces together, and maps directly onto a physical directory structure that both the compiler and the JVM rely on to locate class files.**

Chapter 1 §1.4 already named packages, in passing, as Java's concrete implementation of the Modular Programming idea (`com.company.payroll`, `com.company.order`) — this chapter is where that becomes precise and mechanical rather than illustrative.

### 2.1 Declaring a Package

```java
package com.acme.hr;

public class Employee {
    // ...
}
```

The `package` statement, if present, must be the **first non-comment line** in the file — before even the imports.

### 2.2 The Directory Mapping Is Not Optional

A class declared `package com.acme.hr;` must physically live at `com/acme/hr/Employee.class` (and `Employee.java`, before compilation) relative to the project's source/class root. This isn't a style convention — the compiler and the JVM's classloader both depend on the package name matching the directory path exactly; a mismatch produces a compile or class-loading error, not a warning.

```
src/
└── com/
    └── acme/
        ├── hr/
        │   └── Employee.java        (package com.acme.hr;)
        └── payroll/
            └── SalaryUtils.java     (package com.acme.payroll;)
```

### 2.3 The Fully-Qualified Name

A class's **fully-qualified name** is its package plus its simple name: `com.acme.hr.Employee`. This is the name the JVM actually uses internally to identify the class uniquely — it's also *why* two classes can share a simple name (`Employee`) without conflict, as long as they live in different packages: `com.acme.hr.Employee` and `com.otherlib.hr.Employee` are, to the JVM, two entirely distinct classes.

---

## 3. Importing

Writing a fully-qualified name everywhere is workable but tedious — `import` lets you refer to a class by its simple name within a file:

```java
package com.acme.payroll;

import com.acme.hr.Employee;

public class SalaryUtils {
    static double applyTax(Employee emp) {   // Employee, not com.acme.hr.Employee
        // ...
    }
}
```

### 3.1 Wildcard Imports — and the Myth Worth Retiring

```java
import com.acme.hr.*;   // "on-demand" import — any type from com.acme.hr, as needed
```

A wildcard import does **not** load every class in that package into memory, and it has **zero runtime performance cost** compared to explicit imports — this is a genuinely common but entirely false belief. All an `import` statement — wildcard or explicit — does is tell the *compiler* which package to search when it encounters an unqualified type name in the file; it's resolved once, at compile time, and produces identical bytecode either way. The real, legitimate downside of wildcard imports is readability and collision risk (§5), not performance.

### 3.2 `java.lang` Is Special-Cased

Classes in `java.lang` — `String`, `Object`, `Integer`, `Math`, and others this handbook has used since Chapter 1 — never need an explicit import. Every Java file behaves as if `import java.lang.*;` were already present, silently, at the top.

### 3.3 Static Imports

```java
import static com.acme.payroll.SalaryUtils.TAX_RATE;
// now usable as TAX_RATE, instead of SalaryUtils.TAX_RATE
```

A static import lets a `static` member (Chapter 9) be referenced without qualifying it by class name. It's used sparingly in practice — mostly for things like test-assertion libraries or mathematical constants — since overusing it can make code harder to trace back to its source class.

---

## 4. JVM Internals — How a Class Is Actually Located

When the JVM needs to load `com.acme.hr.Employee` for the first time (Chapter 2 §6.2's "lazy, on first use"), its classloader translates the fully-qualified name directly into a file path — `com/acme/hr/Employee.class` — and searches for it across the **classpath**: the set of directories and JAR files the JVM was told to look in. If no matching `.class` file is found anywhere on the classpath, the result is a `ClassNotFoundException` at the point the class was needed — not at program startup, consistent with the lazy-loading model Chapter 2 established. The full classloader mechanism — including how the search actually proceeds through multiple classloaders — is Chapter 39's subject; this chapter only needs the core fact that a package name *is*, quite literally, a search path.

---

## 5. Real-World Example

```java
// File: com/acme/hr/Employee.java
package com.acme.hr;

public class Employee {
    private final String employeeId;
    protected String department;      // Chapter 13 §5 — meant for a subclass, even cross-package
    String team;                       // Chapter 13 §4 — package-private, visible only within com.acme.hr

    private double salary;

    public Employee(String employeeId, double salary) {
        this.employeeId = employeeId;
        setSalary(salary);
    }

    public double getSalary() { return salary; }
    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("Salary cannot be negative");
        this.salary = salary;
    }
}
```

```java
// File: com/acme/payroll/SalaryUtils.java
package com.acme.payroll;

import com.acme.hr.Employee;

public class SalaryUtils {
    static double applyTax(Employee emp) {
        return emp.getSalary() * 0.8;   // ✔ public accessor — fine from any package
        // emp.team would NOT compile here — team is package-private to com.acme.hr (Ch. 13 §4)
    }
}
```

This is the first time this handbook has shown `Employee` living in an actual named package — every access-level distinction Chapter 13 drew now corresponds to a real, physical boundary: `com.acme.hr` versus `com.acme.payroll`.

---

## 6. Best Practices

- Follow the reverse-domain naming convention (`com.company.module`) — it guarantees global uniqueness across organizations, which is the entire reason it's the standard.
- Group classes by cohesive feature or responsibility, echoing Chapter 1 §1.4's Modular Programming principle — not by technical layer alone (avoid a single miscellaneous `util` package that accumulates everything unrelated).
- Prefer explicit imports over wildcards in most cases — not for any performance reason (§3.1 debunked that), but because they make a file's actual dependencies easier to scan at a glance.
- Use package-private access (Chapter 13 §4) deliberately, by keeping genuinely related classes in the same package — its usefulness depends entirely on the package boundary meaning something.

## 7. Common Mistakes

- ⚠️ Placing the `package` statement anywhere other than the very first non-comment line — a compile error.
- ⚠️ Letting a file's package declaration drift out of sync with its actual directory location — produces confusing compile or classloading errors, not an obvious "you moved a file" message.
- ⚠️ Believing wildcard imports slow down a program — they don't; the resolution happens once, at compile time (§3.1).
- ⚠️ Using wildcard imports across many packages and then hitting an ambiguous-reference compile error when two imported packages happen to define a class with the same simple name — a real, if infrequent, cost of over-relying on `*`.

## 8. Interview Perspective

**Frequently Asked**

- *"What is a package, mechanically?"* — A namespace grouping related classes, mapped directly onto a directory structure the compiler and JVM both rely on to locate class files (§2).
- *"Do wildcard imports hurt performance?"* — No — this is a persistent myth. Import resolution happens entirely at compile time and produces identical bytecode either way (§3.1).
- *"Why don't we need to import `String` or `Object`?"* — `java.lang` is implicitly imported into every Java file (§3.2).

**Tricky Question**

- *"Two libraries both define a class named `Employee`. Can a program use both in the same file?"* — Yes, as long as they're in different packages — reference each by its fully-qualified name (§2.3), or import one and fully-qualify the other at each use site.

**Common Misconception**

- Treating `import` as somehow "loading" classes into a running program, the way it might work in some scripting languages. In Java, `import` is a purely compile-time convenience for the compiler's name resolution (§3.1) — actual class loading (§4) happens later, lazily, at runtime, and has nothing to do with which `import` statements a file happened to contain.

---

## 9. Summary

- A package is a namespace mapped directly onto a physical directory structure — the mapping is enforced by both the compiler and the JVM's classloader, not just a convention.
- A fully-qualified name (package + simple name) is what actually identifies a class uniquely to the JVM, which is why two classes can share a simple name across different packages.
- Wildcard imports have no runtime performance cost — import resolution is entirely compile-time; the real tradeoff is readability and collision risk, not speed.
- The JVM locates a class by translating its fully-qualified name directly into a file path and searching the classpath — consistent with Chapter 2's lazy class-loading model.
- Package boundaries are what give Chapter 13's package-private and cross-package `protected` access rules physical meaning.

## 10. Quick Revision

- Package = namespace + mandatory directory mapping.
- Fully-qualified name = package + simple name; this is what the JVM actually tracks.
- Wildcard imports: compile-time only, zero runtime cost — the myth is false.
- `java.lang` is auto-imported everywhere.
- Classpath = where the JVM searches for a package's directory-mapped `.class` files.

## 11. Self Assessment

1. Why must `com.acme.hr.Employee` physically live at `com/acme/hr/Employee.class`, rather than this being just a style convention?
2. Explain why `import com.acme.hr.*;` does not make a program run any slower than importing each class individually.
3. Two different libraries each define a class named `Logger`. How can a single file use both without a naming conflict?
4. What happens, and at what point in execution, if the JVM can't find a `.class` file matching a fully-qualified name it needs?
5. Connect this chapter back to Chapter 13: give a concrete example of a package-private field that is only meaningful because of the package boundary this chapter defines.

---

## What's Next

Part VII (Object Design) is now complete — Encapsulation, Access Modifiers, and Packages. **Chapter 15 — Inheritance** begins Part VIII (Core OOP) and finally delivers what Chapter 5, Chapter 6, and Chapter 11 have all been building toward in pieces: `Employee`'s first real subclass, `Manager` — using the `protected department` field this chapter's example placed there specifically for that purpose.
