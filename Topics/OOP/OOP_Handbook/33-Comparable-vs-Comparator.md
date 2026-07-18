# Chapter 33 — Comparable vs. Comparator

**Part XI: Advanced OOP**

---

## Learning Objectives

After completing this chapter, you will be able to:

- Explain the structural difference between `Comparable<T>` and `Comparator<T>` — one natural order per class versus any number of external orders.
- Implement `compareTo()` correctly for a class's natural ordering, and write standalone `Comparator` objects for alternative orderings.
- Explain why `compareTo()` should stay consistent with `equals()` (Chapter 19), and what concretely goes wrong in a `TreeSet`/`TreeMap` when it doesn't.
- Recognize the raw-type mistake `implements Comparable` (without a type argument) invites, tying directly back to Chapter 32's generics.

---

## 1. Introduction

Chapter 19 defined equality for `Employee` — `equals()` answers "are these the same, meaningfully?" This chapter answers a different question entirely: "which one comes first?" Java offers two distinct, generic (Chapter 32) interfaces for this: `Comparable<T>` and `Comparator<T>`.

---

## 2. `Comparable<T>` — A Class's Own Natural Ordering

```java
public abstract class Employee implements Payable, Comparable<Employee> {
    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.getSalary(), other.getSalary());
    }
}
```

A class implementing `Comparable<T>` (Chapter 18's interface mechanism, genuinely parameterized this time — Chapter 32) declares its own **natural ordering** via `compareTo(T other)` — returning negative, zero, or positive to mean "comes before," "equal in order," or "comes after." Once `Employee` implements this, sorting just works, automatically, using that natural order:

```java
List<Employee> employees = new ArrayList<>(List.of(mgr, intern, fullTime));
Collections.sort(employees);   // sorts by salary — Employee's own natural order
```

**A class can only implement `Comparable<T>` once** — there is exactly one natural order per class, by construction.

---

## 3. `Comparator<T>` — Orderings Defined Externally

```java
Comparator<Employee> byName = (e1, e2) -> e1.getName().compareTo(e2.getName());
employees.sort(byName);   // sorts by name instead — without touching Employee's own code at all
```

A `Comparator<T>` is a **separate object**, entirely external to the class it compares, defining `compare(T a, T b)` with the same negative/zero/positive convention. Unlike `Comparable`, there's no limit to how many `Comparator`s can exist for the same class — `byName`, `byDepartment`, `byHireDate` can all coexist, each representing a distinct, valid ordering, none of them touching `Employee`'s own source at all.

**The core structural distinction, stated precisely:** `Comparable` = one natural order, defined inside the class. `Comparator` = as many alternative orders as needed, defined outside it.

---

## 4. The `compareTo()`/`equals()` Consistency Recommendation

This deliberately parallels Chapter 19's equals/hashCode contract, though the rule here is a strong recommendation rather than an absolute requirement: **`x.compareTo(y) == 0` should generally align with `x.equals(y) == true`.** Java doesn't enforce this at compile time — but violating it has a concrete, real consequence worth understanding precisely.

### 4.1 What Actually Breaks: `TreeSet`/`TreeMap`

Sorted collections like `TreeSet` and `TreeMap` use **`compareTo()` alone** — not `equals()`/`hashCode()` (Chapter 19) — to determine both ordering *and* uniqueness:

```java
// If compareTo() only compares salary, but equals() compares employeeId:
TreeSet<Employee> set = new TreeSet<>();
set.add(new Manager("M001", "Asha", 95000, "", ""));
set.add(new Manager("M002", "Rohan", 95000, "", ""));   // same salary, DIFFERENT employeeId

System.out.println(set.size());   // 1, not 2 — TreeSet sees compareTo() == 0 and treats them as duplicates,
                                    // even though equals() (Chapter 19) would say they're NOT equal
```

This is precisely the concrete failure the "should be consistent" recommendation warns about: a `TreeSet` never even calls `equals()` — it treats `compareTo() == 0` as its own definition of "the same element," independent of whatever `equals()` says. If the two disagree, a `TreeSet`/`TreeMap` behaves in a way that looks like a bug but is actually working exactly as documented.

---

## 5. Chaining Comparators

Since Java 8, `Comparator` supports fluent chaining for multi-key ordering:

```java
Comparator<Employee> byDeptThenSalary =
    Comparator.comparing(Employee::getDepartment)
              .thenComparing(Employee::getSalary);
```

This is a practical, commonly-used tool worth recognizing; its full method-reference syntax (`Employee::getDepartment`) edges toward the functional-interface territory Chapter 24 §5 already flagged as adjacent to, but outside, this handbook's strict OOP scope — mentioned here only so it's recognizable, not covered in depth.

---

## 6. Real-World Example

```java
public abstract class Employee implements Payable, Comparable<Employee> {
    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.getSalary(), other.getSalary());   // natural order: by salary
    }
}
```

```java
List<Employee> employees = new ArrayList<>(List.of(mgr, intern, fullTime));

Collections.sort(employees);                     // natural order — by salary (Employee's own compareTo)

Comparator<Employee> byName = (e1, e2) -> e1.getName().compareTo(e2.getName());
employees.sort(byName);                            // by name — external Comparator, Employee untouched

Comparator<Employee> byDept = Comparator.comparing(Employee::getDepartment);
employees.sort(byDept);                            // by department — another independent ordering
```

---

## 7. Best Practices

- Give a class a `Comparable` natural order only when there's a genuinely obvious, singular default — an ID for database ordering, or salary for payroll processing — not for every class reflexively.
- Use `Comparator` for anything else, or whenever multiple valid orderings exist and none is clearly "the" default.
- Keep `compareTo()` consistent with `equals()` (§4) whenever a class might end up in a `TreeSet`/`TreeMap` — the inconsistency in §4.1 is subtle enough to be a genuine, hard-to-diagnose bug in real code.

## 8. Common Mistakes

- ⚠️ Writing `implements Comparable` without a type argument — this compiles (a raw type, Chapter 32) but loses all compile-time type safety on `compareTo()`'s parameter, exactly the erasure-adjacent risk Chapter 32 warned about.
- ⚠️ Assuming `compareTo() == 0` always implies `equals() == true` — Java never enforces this, and §4.1 shows exactly what silently goes wrong in a `TreeSet`/`TreeMap` when they disagree.
- ⚠️ Trying to give a class two different "natural" orderings — impossible; a class can implement `Comparable<T>` only once, which is precisely why `Comparator` exists for every additional ordering (§3).

## 9. Interview Perspective

**Frequently Asked**

- *"What's the difference between `Comparable` and `Comparator`?"* — `Comparable` defines one natural ordering inside the class itself; `Comparator` defines any number of orderings externally, without touching the class (§2, §3).
- *"Can a class have multiple `Comparable` implementations?"* — No — exactly one natural order per class, by construction; use separate `Comparator` objects for alternatives (§3).
- *"What happens if `compareTo()` is inconsistent with `equals()`?"* — Sorted collections like `TreeSet`/`TreeMap` use `compareTo()` alone to determine uniqueness, so two objects considered unequal by `equals()` can still be treated as the same element if `compareTo()` returns 0 for them (§4.1).

**Tricky Question**

- *"If `TreeSet.add()` is called with two objects that are `!equals()` but `compareTo() == 0`, how many elements end up in the set?"* — One — `TreeSet` never consults `equals()` at all; `compareTo() == 0` alone is what it treats as "already present" (§4.1).

**Common Misconception**

- Believing `equals()` (Chapter 19) and `compareTo()` are just two names for the same underlying comparison. They answer genuinely different questions — "are these meaningfully the same object" versus "which one comes first" — and Java only *recommends*, never enforces, that they agree, which is exactly why §4.1's TreeSet scenario is a real, documented gotcha rather than a contradiction.

---

## 10. Summary

- `Comparable<T>` defines a class's single natural ordering via `compareTo()`, implemented inside the class itself.
- `Comparator<T>` defines an ordering externally, and any number of them can coexist for the same class.
- `compareTo()` should stay consistent with `equals()` (Chapter 19) — `TreeSet`/`TreeMap` use `compareTo()` alone for uniqueness, so disagreement between the two causes real, documented, surprising behavior.
- `Comparator` supports fluent multi-key chaining (`thenComparing`) since Java 8.
- Writing `implements Comparable` without a type parameter compiles as a raw type, losing the compile-time safety Chapter 32's generics exist to provide.

## 11. Quick Revision

- `Comparable` = one natural order, in the class. `Comparator` = many external orders, outside it.
- `compareTo()` should agree with `equals()` — `TreeSet`/`TreeMap` use `compareTo()` alone for uniqueness, ignoring `equals()`/`hashCode()` entirely.
- A class implements `Comparable<T>` at most once; use `Comparator` for every additional ordering.
- Avoid raw `Comparable` (no type argument) — a Chapter 32 generics mistake.

## 12. Self Assessment

1. Implement `compareTo()` for a `Product` class ordering by `price`, and explain why this only works if `Product` can have exactly one natural order.
2. Write two separate `Comparator<Product>` objects — one by name, one by price — without modifying `Product` at all.
3. A `TreeSet<Employee>` ends up with fewer elements than expected after several `add()` calls. What's the most likely cause, given this chapter?
4. Why can a class implement `Comparable<T>` only once, while it can have unlimited `Comparator<T>` implementations?
5. What specifically is lost by writing `class Employee implements Comparable` instead of `implements Comparable<Employee>`?

---

## What's Next

Part XI (Advanced OOP) is now complete. **Chapter 34 — SOLID Principles** opens Part XII (Object-Oriented Design), and finally delivers on a thread this handbook has pulled on since Chapter 1 §1.8: five design principles — including the "favor composition over inheritance" idea Chapter 22 §6 already justified in depth — assembled into one coherent, named framework for evaluating object-oriented design decisions.
