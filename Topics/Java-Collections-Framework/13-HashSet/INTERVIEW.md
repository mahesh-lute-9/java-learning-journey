# HashSet — Interview Questions

> Interview-focused questions covering `HashSet`, hashing, `equals()`, `hashCode()`, performance, internal working, collisions, and practical use cases.

---

## Table of Contents

- [1. What is HashSet?](#1-what-is-hashset)
- [2. What are the main characteristics of HashSet?](#2-what-are-the-main-characteristics-of-hashset)
- [3. Does HashSet allow duplicates?](#3-does-hashset-allow-duplicates)
- [4. How does HashSet detect duplicates?](#4-how-does-hashset-detect-duplicates)
- [5. Why are equals() and hashCode() important?](#5-why-are-equals-and-hashcode-important)
- [6. What is the equals() and hashCode() contract?](#6-what-is-the-equals-and-hashcode-contract)
- [7. What happens if equals() is overridden but hashCode() is not?](#7-what-happens-if-equals-is-overridden-but-hashcode-is-not)
- [8. Can HashSet store null?](#8-can-hashset-store-null)
- [9. Does HashSet maintain insertion order?](#9-does-hashset-maintain-insertion-order)
- [10. Is HashSet thread-safe?](#10-is-hashset-thread-safe)
- [11. What is the time complexity of HashSet?](#11-what-is-the-time-complexity-of-hashset)
- [12. How does HashSet work internally?](#12-how-does-hashset-work-internally)
- [13. What is the relationship between HashSet and HashMap?](#13-what-is-the-relationship-between-hashset-and-hashmap)
- [14. What is a hash collision?](#14-what-is-a-hash-collision)
- [15. Can two different objects have the same hashCode?](#15-can-two-different-objects-have-the-same-hashcode)
- [16. Does the same hashCode mean objects are equal?](#16-does-the-same-hashcode-mean-objects-are-equal)
- [17. What happens when two objects have the same hashCode?](#17-what-happens-when-two-objects-have-the-same-hashcode)
- [18. What happens if an object is modified after insertion?](#18-what-happens-if-an-object-is-modified-after-insertion)
- [19. What is load factor?](#19-what-is-load-factor)
- [20. What is initial capacity?](#20-what-is-initial-capacity)
- [21. Does initial capacity represent maximum size?](#21-does-initial-capacity-represent-maximum-size)
- [22. Can HashSet contain mutable objects?](#22-can-hashset-contain-mutable-objects)
- [23. HashSet vs ArrayList](#23-hashset-vs-arraylist)
- [24. HashSet vs LinkedHashSet](#24-hashset-vs-linkedhashset)
- [25. HashSet vs TreeSet](#25-hashset-vs-treeset)
- [26. HashSet vs Hashtable](#26-hashset-vs-hashtable)
- [27. HashSet vs ConcurrentHashMap](#27-hashset-vs-concurrenthashmap)
- [28. Can HashSet store different data types?](#28-can-hashset-store-different-data-types)
- [29. Can HashSet be used for sorting?](#29-can-hashset-be-used-for-sorting)
- [30. Why is HashSet useful for duplicate removal?](#30-why-is-hashset-useful-for-duplicate-removal)
- [31. How can you make a HashSet thread-safe?](#31-how-can-you-make-a-hashset-thread-safe)
- [32. Can you access HashSet elements by index?](#32-can-you-access-hashset-elements-by-index)
- [33. Why doesn't HashSet have get(index)?](#33-why-doesnt-hashset-have-getindex)
- [34. What does add() return?](#34-what-does-add-return)
- [35. What does remove() return?](#35-what-does-remove-return)
- [36. What is the difference between contains() and iteration?](#36-what-is-the-difference-between-contains-and-iteration)
- [37. Can HashSet contain custom objects?](#37-can-hashset-contain-custom-objects)
- [38. Why should immutable objects be preferred as HashSet elements?](#38-why-should-immutable-objects-be-preferred-as-hashset-elements)
- [39. Practical Interview Problems](#39-practical-interview-problems)
- [40. Rapid-Fire Questions](#40-rapid-fire-questions)
- [41. Interview Checklist](#41-interview-checklist)
- [42. Final Interview Summary](#42-final-interview-summary)

---

# 1. What is HashSet?

`HashSet` is a class in the `java.util` package that implements the `Set` interface.

It stores **unique elements** and uses hashing to provide fast average-case operations such as:

```text
add()
remove()
contains()
```

Example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
```

The set contains only:

```text
10
20
```

---

# 2. What are the main characteristics of HashSet?

| Characteristic | HashSet |
|---|---|
| Duplicate elements | Not allowed |
| `null` | One `null` allowed |
| Insertion order | Not guaranteed |
| Sorting | No |
| Index-based access | No |
| Thread-safe | No |
| Average `add()` | O(1) |
| Average `remove()` | O(1) |
| Average `contains()` | O(1) |
| Package | `java.util` |

---

# 3. Does HashSet allow duplicates?

No.

```java
Set<String> set = new HashSet<>();

set.add("Java");
set.add("Java");
set.add("Python");
```

Only one `"Java"` is stored.

The duplicate insertion does not change the set.

---

# 4. How does HashSet detect duplicates?

`HashSet` uses hashing and equality checks.

Conceptually:

```text
Element
   ↓
hashCode()
   ↓
Find candidate bucket
   ↓
Compare using equals()
   ↓
Already exists?
   ├── Yes → Don't add
   └── No  → Add
```

This is why correct implementations of:

```java
equals()
hashCode()
```

are important for custom objects.

---

# 5. Why are equals() and hashCode() important?

Suppose:

```java
Student s1 = new Student(1, "Amit");
Student s2 = new Student(1, "Amit");
```

If these students should be considered equal, the class must define equality correctly.

For hash-based collections:

```java
s1.equals(s2)
```

must be consistent with:

```java
s1.hashCode() == s2.hashCode()
```

Otherwise, a `HashSet` may fail to recognize logically duplicate objects.

---

# 6. What is the equals() and hashCode() contract?

The most important rule is:

> If two objects are equal according to `equals()`, they must return the same `hashCode()`.

So:

```java
a.equals(b) == true
```

requires:

```java
a.hashCode() == b.hashCode()
```

But the reverse is not required.

Two objects can have the same hash code while still being unequal.

---

# 7. What happens if equals() is overridden but hashCode() is not?

This is a common interview trap.

Consider:

```java
class Student {

    int id;

    @Override
    public boolean equals(Object obj) {
        // equality based on id
        return true;
    }
}
```

If `hashCode()` is not overridden consistently, two logically equal objects may produce different hash codes.

The `HashSet` may place them in different buckets and fail to treat them as duplicates.

### Rule

Whenever you override:

```java
equals()
```

you should normally override:

```java
hashCode()
```

consistently.

---

# 8. Can HashSet store null?

Yes.

A `HashSet` can contain **at most one `null` element**.

```java
Set<String> set = new HashSet<>();

set.add(null);
set.add(null);

System.out.println(set.size());
```

Output:

```text
1
```

---

# 9. Does HashSet maintain insertion order?

No.

```java
Set<Integer> set = new HashSet<>();

set.add(30);
set.add(10);
set.add(20);
```

You must not assume iteration will produce:

```text
30
10
20
```

or any other particular order.

If insertion order is required, consider:

```java
LinkedHashSet
```

---

# 10. Is HashSet thread-safe?

No.

`HashSet` is not designed for concurrent modification by multiple threads without appropriate synchronization.

For concurrent use cases, consider an appropriate concurrent collection or synchronization strategy.

---

# 11. What is the time complexity of HashSet?

Typical average-case complexity:

| Operation | Average |
|---|---:|
| `add()` | O(1) |
| `remove()` | O(1) |
| `contains()` | O(1) |
| `size()` | O(1) |
| `clear()` | O(n) |

However:

> O(1) is an average-case expectation, not an absolute guarantee.

Hash collisions and other conditions can affect performance.

---

# 12. How does HashSet work internally?

Conceptually:

```text
HashSet
   ↓
HashMap
   ↓
Hash table
   ↓
Buckets
```

In the standard Java implementation, `HashSet` is backed by a `HashMap`.

The elements of the set are stored as keys in the underlying map, with a shared dummy value.

Conceptually:

```text
HashSet element
       ↓
HashMap key
       ↓
dummy value
```

You generally do not interact with that backing map directly.

---

# 13. What is the relationship between HashSet and HashMap?

`HashSet` uses a `HashMap` internally in the standard implementation.

For example:

```java
Set<String> set = new HashSet<>();

set.add("Java");
```

Conceptually, the backing map stores something similar to:

```text
"Java" → PRESENT
```

The value is not meaningful to the `HashSet`; the key represents the set element.

---

# 14. What is a hash collision?

A hash collision occurs when two different objects produce the same hash value.

For example:

```text
Object A → hashCode = 100
Object B → hashCode = 100
```

This does not mean the objects are equal.

The collection must handle the collision and use equality checks to determine whether the objects are actually equal.

---

# 15. Can two different objects have the same hashCode?

Yes.

This is called a collision.

For example:

```java
a.hashCode() == b.hashCode()
```

can be `true` even when:

```java
a.equals(b)
```

is `false`.

This is completely valid.

---

# 16. Does the same hashCode mean objects are equal?

No.

This is a very common interview mistake.

Correct relationship:

```text
equals() == true
        ↓
same hashCode
```

But:

```text
same hashCode
        X
equals() == true
```

is **not guaranteed**.

---

# 17. What happens when two objects have the same hashCode?

They are candidates for the same hash location.

The collection then uses equality checking to determine whether they represent the same element.

Conceptually:

```text
Object A ──┐
           ├── same hash location
Object B ──┘
                ↓
            equals()
                ↓
       ┌────────┴────────┐
     equal             unequal
       ↓                   ↓
   duplicate              add
```

A collision does not automatically mean one object is discarded.

---

# 18. What happens if an object is modified after insertion?

This can cause serious problems if the modified fields participate in `equals()` or `hashCode()`.

Example:

```java
Set<Student> students = new HashSet<>();

Student student = new Student(10);

students.add(student);

student.id = 20;
```

The object's hash-related state has changed after it was placed into the set.

Later:

```java
students.contains(student);
```

or:

```java
students.remove(student);
```

may not behave as expected.

### Interview Answer

Avoid modifying fields used by `equals()` and `hashCode()` while the object is stored in a `HashSet`.

---

# 19. What is load factor?

Load factor controls how full a hash table can become before resizing is triggered.

The commonly used default load factor for `HashMap`/`HashSet` is:

```text
0.75
```

Conceptually:

```text
threshold = capacity × load factor
```

When the relevant threshold is reached, the hash table can resize.

---

# 20. What is initial capacity?

Initial capacity represents the initial sizing of the underlying hash table.

Example:

```java
Set<Integer> set = new HashSet<>(32);
```

This does **not** mean the set can contain only 32 elements.

The collection can grow.

---

# 21. Does initial capacity represent maximum size?

No.

For example:

```java
Set<Integer> set = new HashSet<>(10);
```

does not mean:

```text
Maximum elements = 10
```

The set can resize as more elements are added.

---

# 22. Can HashSet contain mutable objects?

Yes, but caution is required.

Example:

```java
Set<Student> students = new HashSet<>();
```

If `Student` uses mutable fields in:

```java
equals()
hashCode()
```

and those fields change after insertion, lookup behavior can become unreliable.

### Better Approach

Prefer immutable or effectively immutable fields for equality and hashing.

---

# 23. HashSet vs ArrayList

| Feature | HashSet | ArrayList |
|---|---|---|
| Duplicates | No | Yes |
| Index access | No | Yes |
| Ordering | Not guaranteed | Insertion order |
| `contains()` | O(1) average | O(n) |
| Primary use | Unique elements | Ordered sequence |

### Interview Question

When would you choose `HashSet` over `ArrayList`?

### Answer

When:

- duplicates should not exist,
- fast average-case membership checks are useful,
- index-based access is not required,
- ordering is not important.

---

# 24. HashSet vs LinkedHashSet

| Feature | HashSet | LinkedHashSet |
|---|---|---|
| Duplicates | No | No |
| Insertion order | Not guaranteed | Preserved |
| Hash-based | Yes | Yes |
| Typical lookup | O(1) average | O(1) average |
| Memory overhead | Lower | Higher |

### Choose HashSet when:

Order does not matter.

### Choose LinkedHashSet when:

You need uniqueness **and** insertion order.

---

# 25. HashSet vs TreeSet

| Feature | HashSet | TreeSet |
|---|---|---|
| Duplicates | No | No |
| Ordering | None guaranteed | Sorted |
| Typical operations | O(1) average | O(log n) |
| Hashing | Yes | No |
| Comparison | Not required for membership | Required for ordering |

### Choose HashSet when:

You mainly need fast membership and uniqueness.

### Choose TreeSet when:

You need unique elements maintained in sorted order.

---

# 26. HashSet vs Hashtable

These collections solve different problems.

| Feature | HashSet | Hashtable |
|---|---|---|
| Type | Set | Map |
| Stores | Values/elements | Key-value pairs |
| Duplicates | Not allowed | Keys unique |
| `null` | One `null` allowed | Does not allow `null` keys/values |
| Thread-safe | No | Legacy synchronized collection |

Do not confuse:

```text
HashSet
```

with:

```text
Hashtable
```

---

# 27. HashSet vs ConcurrentHashMap

`HashSet` is a non-concurrent set implementation.

`ConcurrentHashMap` is a concurrent map implementation.

If you need a concurrent set, Java provides set views backed by concurrent maps, such as:

```java
Set<String> set =
    ConcurrentHashMap.newKeySet();
```

This can be useful when multiple threads need to work with a set concurrently.

---

# 28. Can HashSet store different data types?

Generics normally define one element type:

```java
Set<String> names = new HashSet<>();
```

So adding an integer is not allowed:

```java
names.add(10); // compile-time error
```

You can technically create:

```java
Set<Object> values = new HashSet<>();
```

and store different object types:

```java
values.add("Java");
values.add(10);
values.add(3.14);
```

But this is usually less type-safe and less expressive than using a specific generic type.

---

# 29. Can HashSet be used for sorting?

No.

`HashSet` does not maintain sorted order.

If you need sorted unique elements, use:

```java
TreeSet
```

Example:

```java
Set<Integer> numbers = new TreeSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);
```

The set maintains sorted order.

---

# 30. Why is HashSet useful for duplicate removal?

Because adding the same value multiple times does not create multiple entries.

Example:

```java
int[] arr = {1, 2, 2, 3, 3, 4};

Set<Integer> unique = new HashSet<>();

for (int number : arr) {
    unique.add(number);
}
```

The resulting set contains only unique values.

This makes `HashSet` a common tool for:

- Duplicate detection.
- Duplicate removal.
- Membership checks.
- Set intersection.
- Set union.
- Set difference.

---

# 31. How can you make a HashSet thread-safe?

One option is:

```java
Set<Integer> set =
    Collections.synchronizedSet(new HashSet<>());
```

Example:

```java
Set<Integer> set =
    Collections.synchronizedSet(new HashSet<>());
```

Another option for concurrent workloads is:

```java
Set<Integer> set =
    ConcurrentHashMap.newKeySet();
```

The appropriate choice depends on the concurrency requirements.

---

# 32. Can you access HashSet elements by index?

No.

This does not exist:

```java
set.get(0);
```

`HashSet` does not provide index-based access.

If index-based access is important, a `List` is usually more appropriate.

---

# 33. Why doesn't HashSet have get(index)?

Because a `Set` is not designed around positional indexing.

The core abstraction is:

```text
Is this element present?
```

not:

```text
What is the element at index 5?
```

A `HashSet` organizes elements using hashing rather than list positions.

---

# 34. What does add() return?

`add()` returns:

```java
boolean
```

Example:

```java
Set<Integer> set = new HashSet<>();

System.out.println(set.add(10));
System.out.println(set.add(10));
```

Output:

```text
true
false
```

Meaning:

```text
true  → set changed
false → element was already present
```

---

# 35. What does remove() return?

`remove()` returns:

```java
boolean
```

Example:

```java
Set<Integer> set = new HashSet<>();

set.add(10);

System.out.println(set.remove(10));
System.out.println(set.remove(10));
```

Output:

```text
true
false
```

---

# 36. What is the difference between contains() and iteration?

`contains()` is specifically designed to check membership.

```java
set.contains(value);
```

For a `HashSet`, this is typically O(1) average-case.

With iteration, you generally have to examine elements until you find a match:

```java
for (Integer number : set) {
    if (number.equals(value)) {
        // found
    }
}
```

This is typically O(n).

So for membership checks:

> Use `contains()` rather than manually iterating.

---

# 37. Can HashSet contain custom objects?

Yes.

Example:

```java
Set<Employee> employees = new HashSet<>();
```

But if logical equality is based on fields such as:

```text
employeeId
```

you should implement `equals()` and `hashCode()` consistently.

Example:

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }

    if (!(obj instanceof Employee)) {
        return false;
    }

    Employee other = (Employee) obj;

    return employeeId == other.employeeId;
}

@Override
public int hashCode() {
    return Integer.hashCode(employeeId);
}
```

---

# 38. Why should immutable objects be preferred as HashSet elements?

Because their hash-related state cannot unexpectedly change after insertion.

For example:

```java
Set<String> set = new HashSet<>();
```

`String` is immutable.

That makes it a safe and common key/element type for hash-based collections.

With mutable objects, changing fields involved in equality/hashing can make the object difficult to locate.

---

# 39. Practical Interview Problems

## Problem 1 — Remove Duplicates

Given:

```java
int[] arr = {1, 2, 2, 3, 4, 4, 5};
```

Remove duplicates using a `HashSet`.

### Expected Concept

```text
{1, 2, 3, 4, 5}
```

---

## Problem 2 — Detect Duplicate

Given:

```java
int[] arr = {10, 20, 30, 20, 40};
```

Return `true` if any element appears more than once.

### Expected

```text
true
```

### Approach

```java
Set<Integer> seen = new HashSet<>();

for (int number : arr) {

    if (!seen.add(number)) {
        return true;
    }
}

return false;
```

---

## Problem 3 — Find Common Elements

Given:

```java
int[] a = {1, 2, 3, 4};
int[] b = {3, 4, 5, 6};
```

Find the common elements.

Expected:

```text
3
4
```

---

## Problem 4 — First Repeating Element

Given:

```java
int[] arr = {5, 3, 4, 3, 5};
```

Find the first repeated element.

Expected:

```text
3
```

---

## Problem 5 — Longest Consecutive Sequence

Given:

```java
int[] nums = {100, 4, 200, 1, 3, 2};
```

Find the length of the longest consecutive sequence.

Expected:

```text
4
```

Sequence:

```text
1, 2, 3, 4
```

A `HashSet` can provide efficient membership checks for this problem.

---

# 40. Rapid-Fire Questions

### Q1. Which package contains HashSet?

```text
java.util
```

### Q2. Which interface does HashSet implement?

```text
Set
```

### Q3. Are duplicates allowed?

```text
No
```

### Q4. Can HashSet store null?

```text
Yes, one null
```

### Q5. Is HashSet ordered?

```text
No guaranteed order
```

### Q6. Is HashSet sorted?

```text
No
```

### Q7. Does HashSet support index access?

```text
No
```

### Q8. Is HashSet thread-safe?

```text
No
```

### Q9. Average complexity of contains()?

```text
O(1)
```

### Q10. Is O(1) guaranteed?

```text
No, it is average-case
```

### Q11. What does HashSet use internally?

```text
HashMap
```

### Q12. What is used to determine the hash location?

```text
hashCode()
```

### Q13. What helps establish equality?

```text
equals()
```

### Q14. If equals() is true, what must be true about hashCode()?

```text
The hash codes must be equal
```

### Q15. Does same hashCode mean equals() is true?

```text
No
```

### Q16. What happens with duplicate add()?

```text
add() returns false
```

### Q17. What does remove() return?

```text
true if an element was removed, otherwise false
```

### Q18. Which collection preserves insertion order?

```text
LinkedHashSet
```

### Q19. Which collection maintains sorted order?

```text
TreeSet
```

### Q20. Which collection is generally preferred for fast unique membership checks?

```text
HashSet
```

---

# 41. Interview Checklist

Before considering `HashSet` interview-ready, make sure you can explain:

## Fundamentals

- [ ] What `HashSet` is.
- [ ] Why duplicates are not allowed.
- [ ] Why `null` is allowed once.
- [ ] Why there is no index access.
- [ ] Why insertion order should not be relied upon.
- [ ] Why `HashSet` is not thread-safe.

## Internal Working

- [ ] Hashing.
- [ ] `hashCode()`.
- [ ] `equals()`.
- [ ] Buckets.
- [ ] Hash collisions.
- [ ] HashSet's relationship with HashMap.
- [ ] Resizing.
- [ ] Load factor.
- [ ] Initial capacity.

## Object Equality

- [ ] `equals()`/`hashCode()` contract.
- [ ] Why both should be overridden consistently.
- [ ] Custom objects in a `HashSet`.
- [ ] Mutable object problem.
- [ ] Why immutable objects are safer.

## Performance

- [ ] Average `add()` → O(1).
- [ ] Average `remove()` → O(1).
- [ ] Average `contains()` → O(1).
- [ ] `size()` → O(1).
- [ ] `clear()` → O(n).
- [ ] O(1) is not an absolute guarantee.

## Comparisons

- [ ] HashSet vs ArrayList.
- [ ] HashSet vs LinkedHashSet.
- [ ] HashSet vs TreeSet.
- [ ] HashSet vs Hashtable.
- [ ] HashSet vs concurrent set approaches.

## Coding

- [ ] Remove duplicates.
- [ ] Detect duplicates.
- [ ] Find intersection.
- [ ] Find union.
- [ ] Find difference.
- [ ] Find first repeating element.
- [ ] Find longest consecutive sequence.

---

# 42. Final Interview Summary

If an interviewer asks:

> **What is HashSet?**

A strong concise answer is:

> `HashSet` is a `Set` implementation that stores unique elements using hashing. It provides average O(1) time for `add`, `remove`, and `contains`. It does not guarantee iteration order, allows one `null`, does not support index-based access, and is not thread-safe. In the standard Java implementation, it is backed by a `HashMap`.

If asked:

> **How does HashSet detect duplicates?**

Answer:

> It uses the element's `hashCode()` to locate the relevant hash area and then uses equality checks such as `equals()` to determine whether an equivalent element already exists.

If asked:

> **Why must equals() and hashCode() be consistent?**

Answer:

> Equal objects must have the same hash code so that hash-based collections can locate them consistently. If `equals()` is overridden without a consistent `hashCode()`, logically equal objects may be stored incorrectly.

If asked:

> **When would you use HashSet?**

Answer:

> When I need unique elements and fast average-case membership checks, and I don't need insertion order, sorted order, or index-based access.

---

## One-Minute Revision

```text
HashSet
   │
   ├── Unique elements
   ├── No guaranteed order
   ├── One null allowed
   ├── No index access
   ├── Not thread-safe
   │
   ├── add()      → O(1) average
   ├── remove()   → O(1) average
   ├── contains() → O(1) average
   │
   ├── Hashing
   │    ├── hashCode()
   │    └── equals()
   │
   ├── Backed by HashMap
   │
   ├── Duplicate detection
   │
   ├── Custom objects
   │    └── equals() + hashCode()
   │
   └── Best when:
        uniqueness + fast membership
```

---

# Progress

```text
Java Collections Framework
│
├── 01-Iterable
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 02-Collection-Interface
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 03-List
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 04-ArrayList
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 05-LinkedList
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 06-Vector
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 07-Stack
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 08-Queue
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 09-PriorityQueue
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 10-Deque
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 11-ArrayDeque
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 12-Set
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 13-HashSet
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 14-LinkedHashSet
├── 15-TreeSet
├── 16-Map
├── 17-HashMap
├── 18-LinkedHashMap
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

**`13-HashSet` is now complete. Next topic: `14-LinkedHashSet`.**
