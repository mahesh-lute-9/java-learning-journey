# Chapter 23 — Nested Classes

**Part XI: Advanced OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Distinguish static nested classes from (non-static) inner classes, and choose correctly between them.
- Explain, precisely, what implicit reference an inner class instance carries and how it's created.
- Explain why a local class can only capture "effectively final" local variables — as a direct consequence of Chapter 4's Stack model, not an arbitrary restriction.
- Recognize when a helper class like Chapter 22's `ContactInfo` should be nested at all, and which kind of nesting fits.

---

## 1. Introduction

Chapter 9 §7 previewed static nested classes in passing; Chapter 22 §8 flagged `ContactInfo` as a strong candidate to become one. This chapter delivers nested classes in full — static nested classes, (non-static) inner classes, and local classes. Anonymous classes — a nested class with no name at all — get their own full chapter next (Chapter 24), since they have enough independent depth to earn it.

> This chapter assumes Chapter 2 §5.1's `Outer$Inner.class` compiled naming, Chapter 4's Stack/local-variable model, and Chapter 8's implicit `this` parameter fact completely — each is referenced, not re-derived.

---

## 2. Theory — The Two Core Kinds

> **A nested class is a class declared inside another class. If it's `static`, it behaves like an ordinary top-level class that's simply namespaced inside another for organization. If it isn't, it's an inner class — implicitly bound to one specific instance of its enclosing class, with direct access to that instance's members.**

This single distinction — `static` or not — is the one that matters most, and it mirrors a distinction this handbook has already built in full: Chapter 9's static-vs-instance-member split, now applied one level up, at the class level itself.

---

## 3. Static Nested Classes

```java
public abstract class Employee implements Payable {
    // ...

    private static class ContactInfo {   // Chapter 22's ContactInfo, now properly nested
        private String phone;
        private String email;

        ContactInfo(String phone, String email) {
            this.phone = phone;
            this.email = email;
        }
    }

    private final ContactInfo contactInfo;
}
```

A static nested class carries **no implicit reference** to any specific `Employee` instance — it can be created independent of one, exactly like any static member (Chapter 9 §2):

```java
Employee.ContactInfo info = new Employee.ContactInfo("555-1234", "asha@acme.com");
```

(Here, `ContactInfo` is `private`, so this only compiles from inside `Employee` itself — Chapter 13's access rules apply to nested classes exactly as they do to any other member.) This is precisely `ContactInfo`'s situation from Chapter 22: it never needed access to a specific `Employee`'s fields — it's just a self-contained data holder — so `static` is the correct, minimal choice. Chapter 2 §5.1's `Employee$ContactInfo.class` naming is exactly what the compiler produces for it.

---

## 4. Inner Classes (Non-Static)

An inner class, declared *without* `static`, is different in one crucial way: **every instance of it is implicitly bound to one specific instance of its enclosing class**, and can access that instance's fields and methods directly — even `private` ones.

```java
public abstract class Employee implements Payable {
    private double salary;
    private String name;

    class SalarySummary {   // inner class — no `static`
        String describe() {
            return name + "'s current salary: " + salary;   // direct access, no getter needed
        }
    }
}
```

```java
Employee emp = new Manager("M001", "Asha", 95000);
Employee.SalarySummary summary = emp.new SalarySummary();   // note the unusual `emp.new` syntax
System.out.println(summary.describe());
```

The `emp.new SalarySummary()` syntax is the clearest possible signal of what's happening: creating a `SalarySummary` instance is meaningless without first specifying *which* `Employee` it belongs to — unlike `ContactInfo` in §3, which needed no such anchor.

---

## 5. JVM Internals — The Hidden Reference

This is the concrete mechanism behind §4's behavior, and it directly parallels Chapter 8 §6's fact about `this`: the compiler gives every inner class a **synthetic hidden field** — conventionally named `this$0` — that stores a reference to the specific enclosing instance it was created from:

```
Source you write:                    What the compiler actually generates:

class SalarySummary {                class SalarySummary {
    String describe() { ... }            Employee this$0;   // hidden, synthetic

                                          SalarySummary(Employee this$0) {
                                              this.this$0 = this$0;
                                          }

                                          String describe() {
                                              return this$0.name + "'s salary: "
                                                     + this$0.salary;
                                          }
                                      }
```

This is exactly how `describe()` reaches `name` and `salary` without any getter — it's silently working through `this$0`, exactly as an ordinary instance method silently works through its own invisible `this` parameter (Chapter 8 §6). A static nested class (§3) has no such hidden field at all, which is precisely why it can never touch an `Employee` instance's fields without one being explicitly passed to it — the same rule Chapter 9 §2.2 already established for static methods, now applying at the class level.

---

## 6. Local Classes

A class can also be declared **inside a method body**, scoped entirely to that method:

```java
public double calculateProjectedRaise(double performanceScore) {
    double baseRate = 0.05;   // must be effectively final to be captured below

    class RaiseCalculator {
        double compute() {
            return getSalary() * baseRate * performanceScore;
        }
    }

    return new RaiseCalculator().compute();
}
```

### 6.1 Why Only "Effectively Final" Variables Can Be Captured

`RaiseCalculator` reaches `baseRate` and `performanceScore` — local variables of the enclosing method — even though, per Chapter 4 §5, local variables live on the Stack and vanish the instant their method call returns. This is only possible because the compiler **copies the captured variable's value into a synthetic field of the local class**, at the moment the local class instance is created — much like §5's `this$0`, but holding a copied value rather than a reference.

This copying is exactly why the captured variable must be **effectively final** — assigned once and never reassigned afterward, whether or not it's explicitly marked `final` (Chapter 10). If `baseRate` could change after `RaiseCalculator` captured it, the copy sitting inside `RaiseCalculator` would silently go stale, disagreeing with whatever the enclosing method's own variable now holds — a correctness hazard the compiler avoids entirely by simply forbidding it:

```java
double baseRate = 0.05;
class RaiseCalculator {
    double compute() { return baseRate * 2; }
}
baseRate = 0.10;   // ✘ compile error:
                    //   local variables referenced from an inner class must be final or effectively final
```

---

## 7. Real-World Example

```java
public abstract class Employee implements Payable {

    private final String employeeId;
    private String name;
    private double salary;
    private final ContactInfo contactInfo;

    protected Employee(String employeeId, String name, double salary, String phone, String email) {
        this.employeeId = employeeId;
        this.name = name;
        setSalary(salary);
        this.contactInfo = new ContactInfo(phone, email);
    }

    private static class ContactInfo {          // static nested — no Employee instance needed
        private String phone;
        private String email;
        ContactInfo(String phone, String email) { this.phone = phone; this.email = email; }
    }

    class SalarySummary {                        // inner class — needs a specific Employee
        String describe() {
            return name + "'s current salary: " + salary;
        }
    }
}
```

Both nested classes exist purely to serve `Employee` — neither is meant to be used or referenced by unrelated code elsewhere in the program — but they differ in exactly the way §2 predicts: `ContactInfo` needs no specific `Employee` to make sense; `SalarySummary` is meaningless without one.

---

## 8. Best Practices

- Default to a **static** nested class unless the nested class genuinely needs direct access to a specific enclosing instance — an unnecessary inner class carries a hidden reference (§5) for no real benefit.
- Use nesting deliberately for helper classes that exist purely to serve one enclosing class — it keeps the package namespace (Chapter 14) from accumulating classes no other code should ever need to reference directly.
- Keep local classes small and short-lived — they're best suited to logic genuinely scoped to one method, not as a substitute for a proper top-level or nested class.

## 9. Common Mistakes

- ⚠️ Making a nested class an inner class (non-static) when it never actually touches the enclosing instance — it should be static instead (§3, §8).
- ⚠️ Trying to reassign a local variable after a local class has captured it, not realizing the effectively-final rule exists specifically because of how capturing is implemented (§6.1).
- ⚠️ Forgetting the `outer.new Inner()` syntax and trying to instantiate an inner class the same way as a static nested one.
- ⚠️ Assuming a static nested class can reach an `Employee` instance's fields directly — it can't, for exactly the same reason a static method can't (Chapter 9 §2.2).

## 10. Interview Perspective

**Frequently Asked**

- *"What's the difference between a static nested class and an inner class?"* — A static nested class carries no reference to any enclosing instance and behaves like a namespaced top-level class; an inner class is bound to one specific enclosing instance and can access its members directly (§2, §3, §4).
- *"Why can a local class only capture effectively final local variables?"* — Because the compiler copies the captured value into the local class at creation time, since the original local variable will disappear once its stack frame is popped (Chapter 4 §5) — if the variable could change afterward, the copy would silently go stale (§6.1).
- *"How does an inner class actually reach its enclosing instance's fields?"* — Through a compiler-generated synthetic field, conventionally `this$0`, holding a reference to the specific enclosing instance (§5).

**Tricky Question**

- *"If a local class captures a local variable and outlives the method it was declared in — say, by being returned as part of an object — does it still work correctly?"* — Yes, precisely because the variable's *value* was copied into the local class at creation time (§6.1), not referenced live; the local class instance carries its own independent copy that remains valid long after the original stack frame is gone.

**Common Misconception**

- Believing a static nested class and an inner class are just two syntax options for the same thing. The presence or absence of the hidden enclosing-instance reference (§5) is a real, meaningful structural difference — not a stylistic one — and choosing the wrong one either wastes memory (an unneeded inner class) or makes a genuinely instance-dependent helper impossible to write cleanly (an unnecessarily static one).

---

## 11. Summary

- A static nested class carries no implicit reference to an enclosing instance and behaves like a namespaced top-level class; an inner class does carry one, via a synthetic `this$0` field, and can access the enclosing instance's members directly.
- Choosing between them mirrors Chapter 9's static-vs-instance distinction, now applied at the class level.
- A local class, declared inside a method, can only capture effectively-final local variables — because the compiler copies their values into the local class at creation time, and a variable that could change afterward would leave that copy stale.
- Nested classes are the right tool for helper types that exist purely to serve one enclosing class, keeping unrelated code from ever needing to reference them directly.

## 12. Quick Revision

- Static nested class: no enclosing-instance reference, created independently, like a namespaced top-level class.
- Inner class: implicit `this$0` reference to one specific enclosing instance; created via `outer.new Inner()`.
- Local class: scoped to a method; captures effectively-final locals only, via compiler-copied values.
- Default to static nested unless the enclosing instance is genuinely needed.

## 13. Self Assessment

1. Why is `ContactInfo` correctly a static nested class, while `SalarySummary` correctly is not?
2. What compiler-generated field makes an inner class's access to its enclosing instance's fields possible, and what does it hold?
3. Why must a local variable captured by a local class be effectively final, in terms of what the compiler actually does when the local class is created?
4. Write the correct instantiation syntax for a non-static inner class `Report` nested inside a class `Invoice`, given an existing `Invoice` instance `inv`.
5. What specifically wastes memory or adds unnecessary coupling about making a nested class an inner class when it never touches the enclosing instance's state?

---

## What's Next

**Chapter 24 — Anonymous Classes** covers the fourth and final form of nesting: a local class with no name, declared and instantiated in a single expression — commonly used to provide a one-off implementation of an interface (Chapter 18) or abstract class (Chapter 17) without writing a separate named class at all.
