# HashSet — Notes

## Table of Contents

1. [What is HashSet?](#1-what-is-hashset)
2. [HashSet in the Collections Framework](#2-hashset-in-the-collections-framework)
3. [Key Characteristics](#3-key-characteristics)
4. [Creating a HashSet](#4-creating-a-hashset)
5. [Adding Elements](#5-adding-elements)
6. [Duplicate Elements](#6-duplicate-elements)
7. [How HashSet Prevents Duplicates](#7-how-hashset-prevents-duplicates)
8. [hashCode() and equals()](#8-hashcode-and-equals)
9. [Removing Elements](#9-removing-elements)
10. [Checking Elements](#10-checking-elements)
11. [Size and Empty Check](#11-size-and-empty-check)
12. [Iteration](#12-iteration)
13. [Ordering](#13-ordering)
14. [Null Elements](#14-null-elements)
15. [Internal Working](#15-internal-working)
16. [Hashing](#16-hashing)
17. [Hash Collisions](#17-hash-collisions)
18. [Mutable Objects as HashSet Elements](#18-mutable-objects-as-hashset-elements)
19. [Initial Capacity](#19-initial-capacity)
20. [Load Factor](#20-load-factor)
21. [Resizing](#21-resizing)
22. [Time Complexity](#22-time-complexity)
23. [HashSet vs Other Collections](#23-hashset-vs-other-collections)
24. [HashSet Use Cases](#24-hashset-use-cases)
25. [Common Mistakes](#25-common-mistakes)
26. [Quick Revision](#26-quick-revision)
27. [Progress](#27-progress)

---

# 1. What is HashSet?

`HashSet` is a class in the Java Collections Framework that implements the `Set` interface.

Its main purpose is to store:

> **Unique elements using hash-based organization.**

Example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
numbers.add(30);

System.out.println(numbers);
```

The duplicate `10` is ignored.

Logical contents:

```text
10
20
30
```

---

# 2. HashSet in the Collections Framework

`HashSet` is part of the following hierarchy:

```text
Iterable
   │
   └── Collection
          │
          └── Set
               │
               └── HashSet
```

Important:

```text
Set
 ↓
interface

HashSet
 ↓
class
```

Therefore, we commonly write:

```java
Set<Integer> numbers = new HashSet<>();
```

---

# 3. Key Characteristics

| Property | HashSet |
|---|---|
| Type | Class |
| Package | `java.util` |
| Implements | `Set` |
| Duplicates | Not allowed |
| Ordering | No guaranteed iteration order |
| Indexed access | No |
| `null` | One `null` allowed |
| Thread-safe | No |
| Hash-based | Yes |
| Average `add()` | O(1) |
| Average `remove()` | O(1) |
| Average `contains()` | O(1) |

The most important points are:

```text
HashSet
   ↓
Unique elements
   ↓
No guaranteed order
   ↓
Hash-based
   ↓
Fast average-case lookup
```

---

# 4. Creating a HashSet

## Basic Creation

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<Integer> numbers = new HashSet<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println(numbers);
    }
}
```

---

## Using HashSet Directly

You can also write:

```java
HashSet<Integer> numbers = new HashSet<>();
```

But generally prefer:

```java
Set<Integer> numbers = new HashSet<>();
```

because the variable depends on the interface rather than the implementation.

---

## Initial Capacity

You can specify an initial capacity:

```java
HashSet<Integer> numbers = new HashSet<>(20);
```

This is an initial capacity, not a maximum number of elements.

The Set can grow beyond this.

---

# 5. Adding Elements

Use:

```java
add()
```

Example:

```java
Set<String> names = new HashSet<>();

names.add("Amit");
names.add("Rahul");
names.add("Priya");
```

---

## Return Value of add()

`add()` returns:

```text
true
```

if the Set changed.

It returns:

```text
false
```

if the element already exists.

Example:

```java
Set<Integer> numbers = new HashSet<>();

System.out.println(numbers.add(10));
System.out.println(numbers.add(20));
System.out.println(numbers.add(10));
```

Output:

```text
true
true
false
```

---

# 6. Duplicate Elements

HashSet does not store duplicate elements.

Example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
numbers.add(30);
numbers.add(20);
```

Logical contents:

```text
10
20
30
```

---

## Important Concept

The Set does not reject the method call itself.

The call:

```java
numbers.add(10);
```

still executes.

But if `10` is already present:

```java
numbers.add(10);
```

returns:

```text
false
```

and the Set remains unchanged.

---

# 7. How HashSet Prevents Duplicates

For typical objects, HashSet uses:

```text
hashCode()
   ↓
hash-based location
   ↓
equals()
   ↓
duplicate check
```

When adding an element:

```java
set.add(element);
```

conceptually:

1. Calculate the element's hash code.
2. Use the hash to identify a candidate location.
3. Compare with existing candidate elements using equality.
4. If an equal element already exists, don't add the new one.
5. Otherwise, store the new element.

---

# 8. hashCode() and equals()

This is one of the most important HashSet interview concepts.

HashSet depends on the contract between:

```java
hashCode()
```

and:

```java
equals()
```

---

## The Contract

If:

```java
a.equals(b)
```

is `true`, then:

```java
a.hashCode() == b.hashCode()
```

must also be true.

However, the reverse is not required.

Two different objects can have the same hash code.

---

## Example

Suppose:

```java
class Student {
    int id;
    String name;
}
```

If `Student` objects are intended to be equal based on `id`, then `equals()` and `hashCode()` should both use the same logical identity.

Example:

```java
class Student {

    int id;
    String name;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Student)) return false;

        Student other = (Student) obj;

        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
```

Now:

```java
Set<Student> students = new HashSet<>();

students.add(new Student(101, "A"));
students.add(new Student(101, "B"));
```

If equality is based only on `id`, the two objects are considered equal and only one is stored.

---

# 9. Removing Elements

Use:

```java
remove()
```

Example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

numbers.remove(20);

System.out.println(numbers);
```

`20` is removed.

---

## Return Value

```java
boolean removed = numbers.remove(20);
```

Returns:

```text
true
```

if the element existed and was removed.

Otherwise:

```text
false
```

---

# 10. Checking Elements

Use:

```java
contains()
```

Example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers.contains(20));
```

Output:

```text
true
```

---

## Why HashSet Is Useful for Membership Checks

A common use case is:

```text
"Have I already seen this value?"
```

For example:

```java
Set<Integer> seen = new HashSet<>();

if (seen.contains(value)) {
    System.out.println("Duplicate");
}
```

This is typically much faster than repeatedly searching through an `ArrayList` for large collections.

---

# 11. Size and Empty Check

## `size()`

Returns the number of unique elements.

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);

System.out.println(numbers.size());
```

Output:

```text
2
```

---

## `isEmpty()`

```java
System.out.println(numbers.isEmpty());
```

Returns:

```text
true
```

or:

```text
false
```

---

## `clear()`

Removes all elements:

```java
numbers.clear();
```

---

# 12. Iteration

## Enhanced For Loop

```java
Set<String> names = new HashSet<>();

names.add("Amit");
names.add("Rahul");
names.add("Priya");

for (String name : names) {
    System.out.println(name);
}
```

Important:

> Do not rely on the iteration order of a HashSet.

---

## Iterator

```java
Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

Import:

```java
import java.util.Iterator;
```

---

## Important

HashSet does not guarantee:

```text
insertion order
```

or:

```text
sorted order
```

Therefore, code should not depend on the exact iteration sequence.

---

# 13. Ordering

HashSet provides:

> **No guaranteed iteration order.**

Example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);
```

You must not assume that printing will always produce:

```text
[30, 10, 20]
```

or:

```text
[10, 20, 30]
```

The iteration order is unspecified.

---

## Need Insertion Order?

Use:

```text
LinkedHashSet
```

---

## Need Sorted Order?

Use:

```text
TreeSet
```

---

## Quick Comparison

```text
HashSet
   ↓
Unique
No guaranteed order

LinkedHashSet
   ↓
Unique
Insertion order

TreeSet
   ↓
Unique
Sorted order
```

---

# 14. Null Elements

HashSet permits one `null` element.

Example:

```java
Set<String> names = new HashSet<>();

names.add(null);
names.add("Amit");
names.add(null);

System.out.println(names);
```

Only one `null` can exist.

Why?

Because duplicates are not allowed.

---

## Important

This is valid:

```java
HashSet
→ one null allowed
```

But do not generalize this rule to every Set implementation.

For example:

```text
TreeSet
→ generally does not allow null with natural ordering
```

---

# 15. Internal Working

HashSet is backed by a hash table.

In modern Java implementations, its internal storage is based on a `HashMap`.

Conceptually:

```text
HashSet
   ↓
backed by HashMap
   ↓
hash table
   ↓
buckets
   ↓
entries
```

The Set uses the HashMap's keys to store its elements.

Conceptually:

```text
HashSet element
      ↓
HashMap key
      ↓
Hash-based storage
```

---

## Simplified Internal Model

Suppose:

```java
set.add(25);
```

Conceptually:

```text
25
 ↓
hashCode()
 ↓
hash calculation
 ↓
bucket
 ↓
store element
```

When searching:

```java
set.contains(25);
```

the hash is used to quickly locate the relevant area.

---

# 16. Hashing

Hashing converts an object's state into an integer hash code.

Java objects can provide:

```java
hashCode()
```

Example:

```java
String name = "Java";

System.out.println(name.hashCode());
```

The exact integer is not important here.

The important concept is:

```text
Object
  ↓
hashCode()
  ↓
integer hash value
  ↓
hash table location
```

---

## Why Hashing?

Without hashing, finding an element might require checking every element.

Hashing allows the collection to narrow down where an element should be.

This is why HashSet can provide average-case:

```text
O(1)
```

for operations such as:

```text
add()
remove()
contains()
```

---

# 17. Hash Collisions

A collision occurs when different objects produce the same hash value or map to the same bucket.

Example concept:

```text
Object A
   ↓
hash
   ↓
bucket 5

Object B
   ↓
hash
   ↓
bucket 5
```

This is allowed.

A hash collision does **not** mean the objects are equal.

---

## How Is Collision Handled?

HashSet uses the underlying HashMap's bucket structure.

When multiple entries end up in the same bucket, equality checks are used to determine whether an existing element is actually equal to the new one.

In modern Java implementations, heavily populated buckets can use a tree-based structure under certain conditions to improve worst-case behavior.

The key interview idea is:

```text
Same bucket ≠ same object
```

---

# 18. Mutable Objects as HashSet Elements

This is an important advanced concept.

Suppose an object uses a field in:

```java
hashCode()
```

and:

```java
equals()
```

Then you add it to a HashSet.

Later, you change that field.

Example concept:

```text
Object
 ↓
id = 10
 ↓
hashCode() based on id
 ↓
added to HashSet

id changes to 20
 ↓
hashCode() changes
```

The object may now be logically associated with a different bucket than the one where it was originally stored.

As a result:

```java
set.contains(object);
```

or:

```java
set.remove(object);
```

may not behave as expected.

---

## Rule

Avoid changing fields that participate in equality and hashing while the object is stored in a HashSet.

Prefer immutable or effectively immutable key state.

---

# 19. Initial Capacity

HashSet provides constructors such as:

```java
HashSet<>()
```

and:

```java
HashSet<>(20)
```

The second form specifies an initial capacity.

Example:

```java
HashSet<Integer> numbers = new HashSet<>(20);
```

This does not mean:

```text
Maximum capacity = 20
```

The Set can grow.

---

# 20. Load Factor

HashSet also uses a load factor.

A common default load factor is:

```text
0.75
```

Conceptually:

```text
load factor = how full the hash table is allowed to become before resizing
```

For example, if the table has capacity `16`:

```text
16 × 0.75 = 12
```

When the relevant threshold is reached, the table can resize.

---

## Why Load Factor Matters

A lower load factor generally means:

```text
more empty space
+
fewer collisions
```

but:

```text
more memory usage
```

A higher load factor generally means:

```text
less memory usage
```

but potentially:

```text
more collisions
```

The default of `0.75` provides a practical balance for many general-purpose workloads.

---

# 21. Resizing

As more elements are added, the hash table may become too full.

Then HashSet can resize its backing table.

Conceptually:

```text
Small table
     ↓
Elements added
     ↓
Threshold reached
     ↓
Resize
     ↓
Larger table
```

Resizing is more expensive than a normal individual insertion.

This is one reason why HashSet insertion is described as:

```text
O(1) average
```

rather than saying every insertion is always exactly O(1).

---

# 22. Time Complexity

Typical average-case complexity:

| Operation | HashSet |
|---|---:|
| `add()` | O(1) average |
| `remove()` | O(1) average |
| `contains()` | O(1) average |
| `size()` | O(1) |
| `isEmpty()` | O(1) |
| `clear()` | O(n) |

---

## Important

The `O(1)` values are average-case expectations.

They are not a mathematical guarantee for every possible input and implementation state.

Hash collisions can affect performance.

---

# 23. HashSet vs Other Collections

## HashSet vs ArrayList

| HashSet | ArrayList |
|---|---|
| No duplicates | Duplicates allowed |
| No index access | Index access |
| Average `contains()` O(1) | `contains()` O(n) |
| No guaranteed order | Maintains list sequence |
| Hash-based | Array-based |

Use HashSet when:

```text
uniqueness + membership checking
```

are important.

Use ArrayList when:

```text
ordered sequence + index access
```

are important.

---

## HashSet vs LinkedHashSet

| HashSet | LinkedHashSet |
|---|---|
| Unique | Unique |
| No guaranteed order | Insertion order |
| Usually slightly less overhead | Extra links for ordering |
| Hash-based | Hash-based + linked ordering |

---

## HashSet vs TreeSet

| HashSet | TreeSet |
|---|---|
| Unique | Unique |
| No guaranteed order | Sorted order |
| O(1) average basic operations | O(log n) basic operations |
| Hash-based | Tree-based |
| Usually faster for simple membership | Useful when sorted navigation is required |

---

## HashSet vs ArrayDeque

These solve different problems.

```text
HashSet
→ uniqueness and membership

ArrayDeque
→ Queue / Stack / double-ended operations
```

---

## HashSet vs HashMap

This is an important relationship.

```text
HashSet
→ stores unique elements

HashMap
→ stores key-value pairs
```

Conceptually, HashSet is backed by a HashMap.

You can think of:

```text
HashSet element
=
HashMap key
```

with a common dummy value internally.

---

# 24. HashSet Use Cases

## 1. Remove Duplicates

Given:

```text
[10, 20, 10, 30, 20]
```

Use:

```java
Set<Integer> unique = new HashSet<>();
```

Result contains:

```text
10
20
30
```

---

## 2. Detect Duplicates

```java
Set<Integer> seen = new HashSet<>();

for (int value : arr) {

    if (seen.contains(value)) {
        System.out.println("Duplicate found");
        break;
    }

    seen.add(value);
}
```

---

## 3. Visited Nodes

Graph traversal:

```java
Set<Integer> visited = new HashSet<>();
```

Before processing a node:

```java
if (!visited.contains(node)) {
    visited.add(node);

    // process node
}
```

---

## 4. Unique Usernames

```java
Set<String> usernames = new HashSet<>();
```

Check:

```java
if (usernames.contains(username)) {
    System.out.println("Username already exists");
}
```

---

## 5. Unique Tags

Input:

```text
java
backend
java
spring
backend
```

HashSet stores unique tags:

```text
java
backend
spring
```

---

## 6. Membership Testing

Example:

```text
Does this product ID already exist?
```

A HashSet can provide efficient average-case membership testing.

---

# 25. Common Mistakes

## Mistake 1 — Assuming HashSet Preserves Insertion Order

Wrong:

```text
HashSet → insertion order
```

Correct:

```text
HashSet → no guaranteed iteration order
```

---

## Mistake 2 — Assuming HashSet Sorts Elements

Wrong:

```text
HashSet automatically sorts values.
```

It does not.

Use:

```text
TreeSet
```

when sorted order is required.

---

## Mistake 3 — Thinking HashSet Allows Duplicates

Wrong:

```java
set.add(10);
set.add(10);
```

and expecting two `10`s.

A HashSet stores only one logical occurrence.

---

## Mistake 4 — Forgetting equals() and hashCode()

For custom objects, inconsistent `equals()` and `hashCode()` implementations can cause incorrect Set behavior.

Remember:

```text
equals() true
      ↓
hashCode() must be same
```

---

## Mistake 5 — Changing Hash-Related Fields

Avoid changing fields used by:

```text
equals()
hashCode()
```

while the object is stored in the HashSet.

---

## Mistake 6 — Saying HashSet Is Always O(1)

Better:

```text
Average case → O(1)
```

---

## Mistake 7 — Assuming Same Hash Means Same Object

Wrong:

```text
same hashCode
    ↓
same object
```

Correct:

```text
same hashCode
    ↓
possible collision
    ↓
equals() still matters
```

---

## Mistake 8 — Assuming HashSet Is Thread-Safe

It is not.

For concurrent applications, use an appropriate concurrent collection or synchronization strategy.

---

# 26. Quick Revision

## One-Line Definition

> `HashSet` is a hash-based Set implementation that stores unique elements without guaranteeing iteration order.

---

## Key Properties

```text
HashSet
   ↓
Unique elements
   ↓
No guaranteed iteration order
   ↓
Hash-based
   ↓
Allows one null
   ↓
Not thread-safe
```

---

## Core Methods

```java
add()
remove()
contains()
size()
isEmpty()
clear()
iterator()
```

---

## Duplicate Detection

Conceptually:

```text
add(element)
     ↓
hashCode()
     ↓
candidate bucket
     ↓
equals()
     ↓
duplicate?
```

---

## Complexity

```text
add()       → O(1) average
remove()    → O(1) average
contains()  → O(1) average
size()      → O(1)
```

---

## Ordering Comparison

```text
HashSet
   ↓
No guaranteed order

LinkedHashSet
   ↓
Insertion order

TreeSet
   ↓
Sorted order
```

---

## When to Use HashSet

Use it when you primarily need:

```text
Unique elements
+
Fast average-case membership checking
+
No ordering requirement
```

---

## Most Important Interview Concept

Remember this flow:

```text
HashSet
   ↓
Hash-based storage
   ↓
hashCode()
   ↓
candidate bucket
   ↓
equals()
   ↓
duplicate check
```

And remember:

```text
HashSet ≠ Sorted
HashSet ≠ Indexed
HashSet ≠ Thread-safe
```

---

# 27. Progress

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
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
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
