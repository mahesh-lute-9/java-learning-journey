# Chapter 12 — Encapsulation

**Part VII: Object Design**

---

## Learning Objectives

After completing this chapter, you will be able to:

- State Encapsulation's actual purpose precisely — not "hiding fields," but protecting an object's invariants.
- Build a properly encapsulated class with validated setters and, where appropriate, read-only fields.
- Explain why a class full of mechanical, validation-free getters and setters is not meaningfully more encapsulated than public fields would be.
- Explain how Java enforces access control at two separate points — compile time and again at class-loading time — and why the second check exists at all.

---

## 1. Introduction

Chapter 1 §1.8 introduced Encapsulation as the first of the Four Pillars, at a glance: "bind data and the methods that operate on it into a single unit, restricting direct access to internal state." Every `Employee` example since Chapter 2 has quietly *practiced* this — private fields, public constructors and methods — without ever making the principle itself the subject. This chapter does that: it turns a pattern you've been following into a design principle you can apply, and defend, deliberately.

> This chapter does not re-explain the Four Pillars overview or why OOP protects data in general — that's Chapter 1 §1.6 and §1.8. It also doesn't yet cover `protected` or package-private access — the full access modifier system is Chapter 13's job. This chapter uses only `private` and `public`, informally, exactly as every earlier `Employee` example already has.

---

## 2. Theory — What Encapsulation Actually Protects

> **Encapsulation is the practice of keeping an object's fields private and exposing controlled access to them through methods, so that the object itself — not its callers — is responsible for enforcing its own valid state.**

The word "hiding" undersells this. The point isn't secrecy — it's **control**. An object that encapsulates its data can guarantee something about itself that a bag of public fields never can: that it is never observed in an invalid state, because the only doors into it are ones it built and locked itself.

### 2.1 What Goes Wrong Without It

Chapter 1 §1.3 already named this exact failure mode for Procedural Programming — shared, unprotected data anyone can corrupt:

```java
class Employee {
    public double salary;   // no protection at all
}

emp.salary = -50000;   // compiles, runs, and silently corrupts the object
```

Nothing here is technically broken — the compiler is satisfied, the program runs — but the object's data no longer means anything sensible. Encapsulation exists specifically to make this kind of corruption impossible to express, not just impolite to write.

---

## 3. The Getter/Setter Pattern

```java
public class Employee {
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }
}
```

`private` blocks direct access entirely (a compile error, not a runtime one — enforced by the compiler as covered in §6). `getSalary()` and `setSalary(double)` become the *only* doors in and out — and because `setSalary` is a method with a body, it can reject invalid input before the field is ever touched:

```java
emp.setSalary(-50000);   // ✘ throws IllegalArgumentException — the object refuses to corrupt itself
```

This is the entire point §2 described made concrete: the object enforces its own valid state, rather than trusting every caller everywhere to do it correctly.

### 3.1 Why Not Just Leave the Field Public?

Beyond the corruption risk in §2.1, a public field forecloses three things a well-encapsulated one keeps open:

- **Validation** — a public field can never reject a bad value; a setter can.
- **Changing the internal representation later** — if `salary` later needs to become `salaryInCents` internally for precision reasons, `getSalary()`/`setSalary()` can adapt without breaking any code that calls them; a public field can't be changed without breaking every caller that touched it directly.
- **Computed or derived values** — a getter can return something computed on the fly (`getAnnualSalary()` returning `salary * 12`) without needing a redundant, easily-out-of-sync stored field.

---

## 4. Read-Only Fields — Getter Without a Setter

Not every field should be changeable after construction. Chapter 10 §3.2 already introduced the mechanism — a blank `final` field — and encapsulation is where it becomes a deliberate design choice: expose a getter, and simply don't write a setter at all.

```java
public class Employee {
    private final String employeeId;   // Chapter 10 §3.2 — set once, in the constructor

    public Employee(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeId() {
        return employeeId;   // read access only — no setEmployeeId() exists
    }
}
```

There is no way, from outside this class, to change `employeeId` after construction — not because of a runtime check like `setSalary`'s, but because the door simply doesn't exist. This is a stronger guarantee than validation, and it's the direction Chapter 28 (Immutability) takes to its logical conclusion for an entire object, not just one field.

---

## 5. When Encapsulation Isn't Actually Happening

This is the nuance that separates genuine understanding of Encapsulation from mechanically following a rule: **a private field with an auto-generated getter and setter that does nothing but pass the value straight through provides no real protection at all.**

```java
private double salary;

public double getSalary() { return salary; }
public void setSalary(double salary) { this.salary = salary; }   // no validation — anyone can still set -50000
```

This compiles, looks encapsulated, and is routinely produced by IDE "generate getters and setters" tooling — but it offers exactly the same lack of protection as a public field, just with more code and an illusion of safety. Real encapsulation lives in what a setter's body actually *does* — the validation in §3's example — not in the mere presence of `private` plus a pass-through method pair. A getter/setter pair is a mechanism Encapsulation *can* use; it is not Encapsulation itself.

---

## 6. How Java Enforces Access Control — Two Separate Checks

This is a genuinely underappreciated JVM fact: **`private` and `public` are checked twice, not once.**

1. **At compile time**, `javac` refuses to compile code that accesses a `private` field from outside its class — the error you're used to seeing immediately.
2. **Again, separately, when a class is loaded** — during the Linking/Verification phase Chapter 2 §6.2 named but didn't detail (full treatment: Chapter 39) — the JVM's bytecode verifier independently re-checks that every field and method access respects the access modifiers declared in the target class's `.class` file.

The second check exists because `.class` files aren't necessarily produced by `javac` from trustworthy source — they can be handwritten or generated by other tools, potentially bypassing the compiler's checks entirely. The JVM doesn't trust that a `.class` file it's asked to load has already been validated; it re-verifies access rules itself, independently, every time. This is why access control in Java is a genuine security boundary, not merely a compile-time convenience — it holds even against bytecode the compiler never saw.

---

## 7. Real-World Example

```java
public class Employee {

    private final String employeeId;   // read-only after construction (§4)
    private String name;
    private double salary;             // validated on write (§3)

    public Employee(String employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        setSalary(salary);              // even the constructor routes through validation
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }
}
```

Notice the constructor itself calls `setSalary(salary)` rather than assigning `this.salary = salary;` directly — this guarantees the *same* validation rule applies whether `salary` is set at construction or changed later, with the logic written in exactly one place.

---

## 8. Best Practices

- Keep fields `private` by default — this handbook has modeled this since Chapter 2; this chapter is where the habit becomes a stated rule.
- Put real validation logic in setters wherever a field has genuine constraints (§3) — a setter without validation is providing the appearance of encapsulation, not the substance (§5).
- Route constructor assignment through the same setter used elsewhere, where validation should apply universally (§7) — don't duplicate the rule.
- Prefer no setter at all (§4) for fields that conceptually shouldn't change after construction, rather than a setter that's simply never expected to be called.

## 9. Common Mistakes

- ⚠️ Generating a getter and setter for every field mechanically, without asking whether a setter should exist at all, or what it should validate (§5) — this is the single most common way "encapsulation" ends up doing nothing.
- ⚠️ Validating in a setter but bypassing it in the constructor by assigning the field directly — leaving one path into the object unprotected (§7 shows the fix).
- ⚠️ Believing `private` is only a compile-time convenience that determined tooling could bypass — the JVM re-verifies access independently at class-loading time (§6), making it a real enforcement boundary.
- ⚠️ Exposing a public field "just for now" with the intention of encapsulating it later — every caller that touches it directly has to be found and rewritten once you do; there's no way to add validation to a public field retroactively without breaking source compatibility.

## 10. Interview Perspective

**Frequently Asked**

- *"What is Encapsulation, really — beyond 'hiding fields'?"* — Restricting direct access so the object itself can guarantee its own valid state, typically via private fields and validated public accessors (§2). "Hiding" is a means, not the point.
- *"Is generating a getter and setter for every private field genuinely encapsulation?"* — Often no. Without real validation logic, a pass-through getter/setter pair provides no more protection than a public field would (§5) — this is a strong interview answer precisely because most candidates assume the mechanical pattern *is* the principle.
- *"How does Java enforce `private`?"* — Twice: at compile time by `javac`, and again independently at class-loading time by the JVM's bytecode verifier (§6) — most candidates only know the first half.

**Tricky Question**

- *"If `private` is enforced by the compiler, why does the JVM check it again at runtime?"* — Because not every `.class` file the JVM loads was necessarily produced by `javac` from checked source — bytecode can be handcrafted or generated by other tools. The JVM's independent re-verification (§6) is what makes Java's access control a genuine security boundary rather than a compile-time-only convenience.

**Common Misconception**

- Treating Encapsulation as a mechanical rule ("all fields private, all fields get a getter and setter") rather than a design decision made per field: does this field need validation? Should it be changeable at all after construction? A field that's simply `private` with an unvalidated pass-through pair (§5) has followed the letter of the rule while missing its entire purpose.

---

## 11. Summary

- Encapsulation's purpose is protecting an object's valid state — keeping fields private and exposing controlled, validated access is the mechanism, not the goal itself.
- A getter/setter pair with no validation logic offers no more real protection than a public field — genuine encapsulation lives in what a setter's body actually enforces.
- Read-only fields (getter, no setter, often paired with `final`) are a stronger guarantee than validation: the door to change the value simply doesn't exist.
- Java enforces access control twice — once at compile time, and again, independently, by the JVM's bytecode verifier at class-loading time — which is what makes it a genuine security boundary.

## 12. Quick Revision

- Encapsulation = private fields + controlled access, so the object enforces its own valid state.
- Unvalidated getter/setter pairs ≈ public fields in disguise — real protection lives in the validation logic.
- Read-only field = getter only, often paired with `final` (Ch. 10) — a stronger guarantee than a validated setter.
- Access control checked twice: compiler (compile time) + JVM bytecode verifier (class-loading time).

## 13. Self Assessment

1. Explain why a class with `private double salary;` plus an unvalidated `getSalary()`/`setSalary()` pair offers essentially the same risk profile as `public double salary;`.
2. Write a `setAge(int age)` method that rejects negative values, and explain why routing the constructor through this same method matters.
3. When would you expose only a getter for a field, with no setter at all? Connect your answer to Chapter 10's blank finals.
4. Why does the JVM re-check access modifiers at class-loading time, given that the compiler already enforced them?
5. A colleague says "I've made all my fields private and added getters and setters for each — my class is now properly encapsulated." Is this necessarily true? Explain.

---

## What's Next

**Chapter 13 — Access Modifiers** formalizes what this chapter used only informally: the full `private` / package-private / `protected` / `public` system, including exactly what each one exposes across packages and subclasses — the missing piece needed before Inheritance (Chapter 15) can meaningfully discuss what a subclass can and cannot access from its parent.
