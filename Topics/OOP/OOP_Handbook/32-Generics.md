# Chapter 32 — Generics

**Part XI: Advanced OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain why generics exist as a direct extension of this handbook's running preference for compile-time errors over runtime ones.
- Write generic classes and methods, including bounded type parameters.
- Apply wildcards correctly using the PECS rule, and explain precisely why `List<Manager>` is not a subtype of `List<Employee>`.
- Explain type erasure — what the compiler actually does with a type parameter by the time bytecode exists — and its concrete consequences.

---

## 1. Introduction

Chapter 16 §7 used `List<Employee>` without ever explaining the `<Employee>` part. This chapter delivers generics from first principles — the mechanism that lets a single class or method work safely with many different types, entirely checked at compile time.

---

## 2. Theory — Why Generics Exist

Before generics (pre-Java 5), a general-purpose container held plain `Object` references. Retrieving an element required an explicit downcast (Chapter 16 §6) — and nothing stopped the wrong type being put in to begin with:

```java
List rawList = new ArrayList();        // pre-generics style
rawList.add(new Manager(...));
rawList.add("not an employee at all"); // compiles fine — no protection whatsoever

Employee emp = (Employee) rawList.get(1);   // ✘ ClassCastException — at RUNTIME, far from the actual mistake
```

> **Generics let a class, interface, or method be parameterized by one or more types, checked entirely at compile time — moving this exact class of bug from a runtime `ClassCastException` to a compile error, at the line where the actual mistake was made.**

This is the same principle this handbook has valued since Chapter 4 §5.1's definite-assignment rule and Chapter 15 §4.3's `@Override`: catching a mistake as early as possible, at compile time, rather than discovering it later at runtime.

---

## 3. Generic Classes

```java
public class Box<T> {
    private T contents;
    public void set(T contents) { this.contents = contents; }
    public T get() { return contents; }
}
```

```java
Box<Employee> empBox = new Box<>();
empBox.set(new Manager("M001", "Asha", 95000, "", ""));   // only Employee-typed values compile
Employee e = empBox.get();                                  // no downcast needed — the compiler already knows
```

`T` is a **type parameter** — a placeholder the compiler resolves to a concrete type (`Employee`, here) at every use site, checked fully at compile time.

---

## 4. Generic Methods

A method can be generic independent of whether its enclosing class is:

```java
public static <T> T firstElement(List<T> list) {
    return list.get(0);
}
```

```java
Employee first = firstElement(employeeList);   // T inferred as Employee from the argument
```

---

## 5. Bounded Type Parameters

```java
public class Repository<T extends Employee> {
    private List<T> items = new ArrayList<>();

    public void save(T item) { items.add(item); }

    public double totalPayroll() {
        double total = 0;
        for (T item : items) {
            total += item.getSalary();   // ✔ legal — T is guaranteed to be an Employee or subtype
        }
        return total;
    }
}
```

`<T extends Employee>` restricts `T` to `Employee` or one of its subtypes (Chapter 15) — and, crucially, this is what makes `item.getSalary()` legal inside the generic class at all. An unbounded `<T>` only guarantees `Object`'s methods (Chapter 19) are available; a bound widens that guarantee to whatever the bound's own type provides.

---

## 6. Wildcards — and the PECS Rule

```java
void printAll(List<?> list) { ... }                      // unknown type — read-only-ish
void payAll(List<? extends Employee> employees) { ... }   // "producer" — safe to READ Employee values out
void addDefaults(List<? super Employee> sink) { ... }      // "consumer" — safe to WRITE Employee values in
```

- **`? extends Employee`** — the list holds *some* subtype of `Employee`, unknown exactly which. Reading out is safe (whatever it is, it's guaranteed assignable to `Employee`), but adding is not (the compiler can't confirm an arbitrary `Employee` you're adding matches the list's *actual*, more specific element type).
- **`? super Employee`** — the list holds `Employee` or some supertype of it. Adding an `Employee` (or subtype) is safe, but reading only guarantees you get back something as general as `Object`.

The standard mnemonic: **PECS — Producer `extends`, Consumer `super`.** If a parameter is only *producing* values you read out, use `extends`; if it's only *consuming* values you pass in, use `super`.

---

## 7. Why `List<Manager>` Is Not a Subtype of `List<Employee>`

This is a genuinely precise, frequently misunderstood rule, worth walking through directly:

```java
List<Manager> managers = new ArrayList<>();
List<Employee> employees = managers;   // ✘ compile error — this is NOT legal
```

If this *were* allowed, the next line could compile too:

```java
employees.add(new Intern(...));   // would silently corrupt managers — an Intern in a List<Manager>!
```

Even though `Manager IS-A Employee` (Chapter 15), `List<Manager>` is deliberately **not** treated as a subtype of `List<Employee>` — allowing it would let code add any `Employee` (including an `Intern`) through the `List<Employee>`-typed reference, silently violating the real list's actual, more specific element type. This is exactly the gap §6's wildcards exist to bridge safely: `List<? extends Employee> employees = managers;` *is* legal, because `? extends Employee` explicitly forfeits the ability to add anything, closing off precisely the unsafe operation above.

---

## 8. JVM Internals — Type Erasure

This is the single most important fact about how generics actually work: **generics exist only at compile time. By the time bytecode exists, the compiler has erased every type parameter, replacing it with its bound (`Object`, if unbounded) and inserting the necessary casts automatically.**

```
Source you write:                 What effectively exists after compilation:

Box<Employee> box = new Box<>();   Box box = new Box();
box.set(manager);                  box.set(manager);   // manager treated as Object
Employee e = box.get();            Employee e = (Employee) box.get();   // cast inserted by the compiler
```

**Concrete consequences of erasure:**

- `Box<Employee>` and `Box<Intern>` are, at runtime, the **exact same class**, `Box` — one single `.class` file, unlike `Manager` and `Intern` (Chapter 2 §5.1), which genuinely are distinct compiled classes.
- `new T()` is illegal inside a generic class — there's no real `T` left at runtime to instantiate; the compiler has already erased it.
- `list instanceof List<Employee>` is illegal — only `list instanceof List<?>` compiles, since the specific type argument simply doesn't exist at runtime to check against.

Erasure exists primarily for backward compatibility — it lets generic code interoperate with pre-generics bytecode that predates Java 5, at the cost of these specific, well-known limitations.

---

## 9. Real-World Example

```java
public class Repository<T extends Employee> {
    private List<T> items = new ArrayList<>();

    public void save(T item) {
        items.add(item);
    }

    public double totalPayroll() {
        double total = 0;
        for (T item : items) {
            total += item.getSalary();
        }
        return total;
    }

    public List<T> findAll() {
        return List.copyOf(items);   // defensive copy (Chapter 28 §4)
    }
}
```

```java
Repository<Manager> managerRepo = new Repository<>();
managerRepo.save(new Manager("M001", "Asha", 95000, "", ""));
System.out.println(managerRepo.totalPayroll());
```

This shape — a generic, bounded repository managing a list of a specific `Employee` subtype — is directly, deliberately reminiscent of the Repository pattern Spring Data uses throughout Spring Boot applications.

---

## 10. Best Practices

- Prefer generic containers and utilities over raw `Object` plus manual casting — push type errors to compile time (§2) wherever possible.
- Use a bounded type parameter (`<T extends X>`, §5) whenever generic code needs to call specific methods on the type parameter, not just `Object`'s.
- Apply PECS (§6) when designing a method parameter that accepts a wildcard-typed collection — it resolves most wildcard-direction confusion mechanically.

## 11. Common Mistakes

- ⚠️ Trying `new T()` inside a generic class — illegal, because of type erasure (§8); there's no real `T` left at runtime.
- ⚠️ Trying `obj instanceof SomeGeneric<Employee>` — illegal for the same reason; only the unbounded wildcard form compiles.
- ⚠️ Assuming `List<Manager>` is a subtype of `List<Employee>` because `Manager IS-A Employee` — it isn't, and treating it as one would break type safety (§7).
- ⚠️ Reaching for `? super` when a producer relationship was actually needed, or vice versa — apply PECS deliberately rather than guessing (§6).

## 12. Interview Perspective

**Frequently Asked**

- *"What is type erasure, and why does it exist?"* — The compiler removes all generic type information by the time bytecode exists, replacing type parameters with their bound and inserting casts automatically — primarily to preserve backward compatibility with pre-generics Java bytecode (§8).
- *"Why isn't `List<Manager>` a subtype of `List<Employee>`?"* — Because allowing it would let code add an arbitrary `Employee` (like an `Intern`) through a more general reference, silently corrupting the actual list's real, more specific element type (§7).
- *"What's the PECS rule?"* — Producer `extends`, Consumer `super` — use `? extends X` when only reading values out, `? super X` when only writing values in (§6).

**Tricky Question**

- *"Can you write `new T[10]` inside a generic class?"* — No, for the same underlying reason as `new T()` (§8) — type erasure means there's no real `T` at runtime to create an array of; this is one of several well-known generics limitations that trace directly back to erasure.

**Common Misconception**

- Believing `Box<Employee>` and `Box<Intern>` are genuinely different classes at runtime, the way `Manager` and `Intern` are (Chapter 2 §5.1). They're not — type erasure (§8) means there's exactly one `Box` class in the compiled program; the type parameter only ever existed at compile time.

---

## 13. Summary

- Generics let a class, interface, or method be parameterized by types, checked entirely at compile time — moving a whole class of `ClassCastException` bugs earlier, into compile errors.
- Bounded type parameters (`<T extends X>`) widen what a generic class can safely do with its type parameter, beyond `Object`'s methods alone.
- `List<Manager>` is deliberately not a subtype of `List<Employee>`, even though `Manager IS-A Employee` — wildcards (`? extends`/`? super`, following PECS) express the safe subset of that relationship without breaking type safety.
- Type erasure means generics exist only at compile time — `Box<Employee>` and `Box<Intern>` share one runtime class, `new T()` and generic `instanceof` checks are both illegal, and casts are inserted automatically by the compiler.

## 14. Quick Revision

- Generics = compile-time type parameterization; moves runtime `ClassCastException`s to compile errors.
- `<T extends X>` = bounded; unlocks `X`'s methods on `T` inside the generic class.
- `List<Manager>` ≠ subtype of `List<Employee>` — use wildcards instead.
- PECS: `? extends` for producers (read), `? super` for consumers (write).
- Type erasure: no `T` at runtime — `new T()`, generic `instanceof`, and per-type-argument classes are all impossible.

## 15. Self Assessment

1. Explain, using a concrete `ClassCastException` example, what specific problem generics solve compared to raw `Object`-based containers.
2. Write a bounded generic method that accepts any `List<T extends Employee>` and returns the highest-paid element.
3. Why does allowing `List<Employee> employees = someListOfManagers;` directly (without a wildcard) break type safety? Walk through the exact unsafe operation it would permit.
4. Why is `new T()` illegal inside a generic class, tying your answer directly to type erasure?
5. Given a method that only ever reads `Employee`-typed values out of a list parameter, which wildcard form should it use, and why, per PECS?

---

## What's Next

**Chapter 33 — Comparable vs. Comparator** closes Part XI (Advanced OOP). It covers two generic interfaces — `Comparable<T>` (Chapter 18's interface mechanism, now genuinely parameterized) and `Comparator<T>` — for defining how objects should be ordered, directly building on this chapter's generics and Chapter 19's `equals()` to explain how sorting actually works for custom classes like `Employee`.
