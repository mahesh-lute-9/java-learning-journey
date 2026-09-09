# Set — Notes

## Table of Contents

1. [What is Set?](#1-what-is-set)
2. [Set in the Collections Framework](#2-set-in-the-collections-framework)
3. [Key Characteristics](#3-key-characteristics)
4. [Creating a Set](#4-creating-a-set)
5. [Basic Set Operations](#5-basic-set-operations)
6. [Adding Elements](#6-adding-elements)
7. [Removing Elements](#7-removing-elements)
8. [Checking Elements](#8-checking-elements)
9. [Size and Empty Check](#9-size-and-empty-check)
10. [Iteration](#10-iteration)
11. [Duplicates](#11-duplicates)
12. [Null Elements](#12-null-elements)
13. [Ordering](#13-ordering)
14. [Common Set Implementations](#14-common-set-implementations)
15. [HashSet](#15-hashset)
16. [LinkedHashSet](#16-linkedhashset)
17. [TreeSet](#17-treeset)
18. [Set vs List](#18-set-vs-list)
19. [Set vs Queue](#19-set-vs-queue)
20. [Set Hierarchy](#20-set-hierarchy)
21. [Common Methods](#21-common-methods)
22. [Set Operations](#22-set-operations)
23. [Union](#23-union)
24. [Intersection](#24-intersection)
25. [Difference](#25-difference)
26. [Subset and Superset](#26-subset-and-superset)
27. [Important Points](#27-important-points)
28. [Real-World Use Cases](#28-real-world-use-cases)
29. [Common Mistakes](#29-common-mistakes)
30. [Quick Revision](#30-quick-revision)
31. [Progress](#31-progress)

---

# 1. What is Set?

`Set` is an interface in the Java Collections Framework that represents a collection of **unique elements**.

The most important property of a Set is:

> **A Set does not allow duplicate elements.**

Example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
numbers.add(30);

System.out.println(numbers);
```

The second `10` is not added.

Conceptually:

```text
Input:
10, 20, 10, 30

Set:
10, 20, 30
```

---

# 2. Set in the Collections Framework

`Set` is part of the `Collection` hierarchy.

```text
Iterable
   │
   └── Collection
          │
          ├── List
          │
          ├── Queue
          │
          └── Set
```

`Set` extends:

```java
Collection<E>
```

Therefore, a Set inherits many common Collection operations such as:

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

# 3. Key Characteristics

A Set generally provides:

| Characteristic | Set |
|---|---|
| Interface/Class | Interface |
| Extends | `Collection` |
| Duplicate elements | Not allowed |
| Indexed access | No |
| Ordering | Depends on implementation |
| `add()` return value | `boolean` |
| `remove()` return value | `boolean` |
| `contains()` | Supported |
| Iteration | Supported |
| Common implementations | `HashSet`, `LinkedHashSet`, `TreeSet` |

Important:

> The `Set` interface itself does **not** define one universal ordering behavior.

The actual ordering depends on the implementation.

---

# 4. Creating a Set

Because `Set` is an interface, we cannot directly write:

```java
Set<Integer> set = new Set<>();
```

This is invalid.

Instead, use an implementation:

```java
Set<Integer> set = new HashSet<>();
```

or:

```java
Set<Integer> set = new LinkedHashSet<>();
```

or:

```java
Set<Integer> set = new TreeSet<>();
```

Imports:

```java
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
```

---

# 5. Basic Set Operations

Consider:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
```

Common operations:

```java
numbers.add(40);
numbers.remove(20);

numbers.contains(30);

numbers.size();

numbers.isEmpty();

numbers.clear();
```

---

# 6. Adding Elements

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

`add()` returns:

```text
true
```

if the element was actually added.

If the element already exists:

```text
false
```

is returned.

Example:

```java
Set<Integer> numbers = new HashSet<>();

System.out.println(numbers.add(10));
System.out.println(numbers.add(10));
```

Output:

```text
true
false
```

Why?

```text
First add:
10 does not exist → added → true

Second add:
10 already exists → not added → false
```

---

# 7. Removing Elements

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

The result contains:

```text
10
30
```

`remove()` returns:

```text
true
```

if an element was removed.

Otherwise:

```text
false
```

---

# 8. Checking Elements

Use:

```java
contains()
```

Example:

```java
Set<String> names = new HashSet<>();

names.add("Amit");
names.add("Priya");

System.out.println(names.contains("Amit"));
System.out.println(names.contains("Rahul"));
```

Output:

```text
true
false
```

This is one of the most common reasons to use a Set:

> Quickly checking whether an element already exists.

---

# 9. Size and Empty Check

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

Checks whether the Set contains no elements.

```java
System.out.println(numbers.isEmpty());
```

Returns:

```text
false
```

---

## `clear()`

Removes all elements.

```java
numbers.clear();
```

After this:

```java
numbers.isEmpty()
```

returns:

```text
true
```

---

# 10. Iteration

A Set can be traversed using an enhanced `for` loop.

```java
Set<String> names = new HashSet<>();

names.add("A");
names.add("B");
names.add("C");

for (String name : names) {
    System.out.println(name);
}
```

The exact iteration order depends on the Set implementation.

For example:

```text
HashSet
→ no guaranteed insertion order

LinkedHashSet
→ insertion order

TreeSet
→ sorted order
```

---

## Using Iterator

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

# 11. Duplicates

The defining feature of a Set is uniqueness.

Example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
numbers.add(20);
numbers.add(30);
```

Logical contents:

```text
10
20
30
```

Not:

```text
10
20
10
20
30
```

---

## How Does Set Prevent Duplicates?

The exact mechanism depends on the implementation.

For `HashSet`:

```text
hashCode()
    ↓
bucket/location
    ↓
equals()
    ↓
duplicate check
```

For `TreeSet`, uniqueness is determined through ordering/comparison rather than hashing.

Therefore:

> Different Set implementations can use different mechanisms to enforce uniqueness.

---

# 12. Null Elements

The `Set` interface itself does not universally prohibit `null`.

Support depends on the implementation.

| Implementation | Null |
|---|---|
| `HashSet` | Allows one `null` |
| `LinkedHashSet` | Allows one `null` |
| `TreeSet` | Generally does not allow `null` with natural ordering |

Example with `HashSet`:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(null);
numbers.add(10);
numbers.add(null);

System.out.println(numbers);
```

Only one `null` can exist because duplicates are not allowed.

Important:

> `Set` does not mean "null is forbidden." Null behavior depends on the implementation.

---

# 13. Ordering

One of the most important concepts is that `Set` does not necessarily preserve insertion order.

Consider:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);
```

You should **not** rely on the output being:

```text
30 10 20
```

because `HashSet` does not guarantee insertion order.

---

## Different Implementations

### HashSet

```text
No guaranteed iteration order
```

### LinkedHashSet

```text
Insertion order
```

### TreeSet

```text
Sorted order
```

Remember:

```text
HashSet       → hashing
LinkedHashSet → hashing + linked order
TreeSet       → sorted tree structure
```

---

# 14. Common Set Implementations

The three most important implementations are:

```text
Set
 │
 ├── HashSet
 │
 ├── LinkedHashSet
 │
 └── TreeSet
```

---

## HashSet

Use when:

- You need unique elements
- Ordering is not important
- Fast average-case membership operations are desired

Example:

```java
Set<Integer> set = new HashSet<>();
```

---

## LinkedHashSet

Use when:

- You need unique elements
- You want insertion order preserved

Example:

```java
Set<Integer> set = new LinkedHashSet<>();
```

---

## TreeSet

Use when:

- You need unique elements
- You need sorted order

Example:

```java
Set<Integer> set = new TreeSet<>();
```

---

# 15. HashSet

`HashSet` stores unique elements using hash-based organization.

Example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(50);
numbers.add(10);
numbers.add(30);
numbers.add(10);
```

The duplicate `10` is ignored.

Typical average-case complexity:

```text
add()       → O(1)
remove()    → O(1)
contains()  → O(1)
```

These are average-case expectations, not absolute guarantees.

---

# 16. LinkedHashSet

`LinkedHashSet` combines:

```text
HashSet behavior
+
insertion-order iteration
```

Example:

```java
Set<Integer> numbers = new LinkedHashSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);
```

Iteration:

```text
30
10
20
```

The insertion order is preserved.

---

# 17. TreeSet

`TreeSet` stores unique elements in sorted order.

Example:

```java
Set<Integer> numbers = new TreeSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);
```

Iteration:

```text
10
20
30
```

Typical complexity:

```text
add()       → O(log n)
remove()    → O(log n)
contains()  → O(log n)
```

`TreeSet` is useful when you need both:

```text
uniqueness
+
sorted order
```

---

# 18. Set vs List

This is a very common interview comparison.

| Set | List |
|---|---|
| Unique elements | Duplicates allowed |
| No index-based API | Index-based API |
| `get(index)` not available | `get(index)` available |
| Good for membership/uniqueness | Good for ordered sequence |
| Ordering depends on implementation | List maintains sequence order |

Example:

```java
List<Integer> list = new ArrayList<>();

list.add(10);
list.add(10);
```

Result:

```text
[10, 10]
```

Set:

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(10);
```

Logical result:

```text
[10]
```

---

# 19. Set vs Queue

A Queue focuses on processing order.

```text
FIFO
```

A Set focuses on uniqueness.

```text
No duplicates
```

Example:

```text
Queue → process tasks
Set   → track unique tasks
```

They solve different problems.

---

# 20. Set Hierarchy

```text
Iterable
   │
   └── Collection
          │
          └── Set
               │
               ├── HashSet
               │
               ├── LinkedHashSet
               │
               └── SortedSet
                    │
                    └── NavigableSet
                         │
                         └── TreeSet
```

More precisely:

```text
Set
 │
 ├── HashSet
 │     └── LinkedHashSet
 │
 └── SortedSet
       │
       └── NavigableSet
             │
             └── TreeSet
```

`LinkedHashSet` extends `HashSet`.

`TreeSet` implements `NavigableSet`.

---

# 21. Common Methods

Since `Set` extends `Collection`, it provides common Collection methods.

| Method | Purpose |
|---|---|
| `add(E)` | Adds an element |
| `addAll(Collection)` | Adds elements from another collection |
| `remove(Object)` | Removes an element |
| `removeAll(Collection)` | Removes matching elements |
| `retainAll(Collection)` | Keeps only matching elements |
| `contains(Object)` | Checks membership |
| `containsAll(Collection)` | Checks whether all elements exist |
| `size()` | Number of elements |
| `isEmpty()` | Checks if empty |
| `clear()` | Removes everything |
| `iterator()` | Creates iterator |
| `toArray()` | Converts to array |

---

## `add()`

```java
Set<Integer> set = new HashSet<>();

boolean result = set.add(10);
```

Returns `true` if the Set changed.

---

## `remove()`

```java
set.remove(10);
```

Returns `true` if the element existed and was removed.

---

## `contains()`

```java
set.contains(10);
```

Returns:

```text
true / false
```

---

# 22. Set Operations

Set theory gives us useful operations:

```text
Union
Intersection
Difference
```

Java's Collection methods make these easy to implement.

---

# 23. Union

Union means:

> Elements present in either Set.

Example:

```text
A = {1, 2, 3}
B = {3, 4, 5}

Union = {1, 2, 3, 4, 5}
```

Java:

```java
Set<Integer> a = new HashSet<>();
a.add(1);
a.add(2);
a.add(3);

Set<Integer> b = new HashSet<>();
b.add(3);
b.add(4);
b.add(5);

Set<Integer> union = new HashSet<>(a);
union.addAll(b);

System.out.println(union);
```

Result:

```text
{1, 2, 3, 4, 5}
```

---

# 24. Intersection

Intersection means:

> Elements common to both Sets.

Example:

```text
A = {1, 2, 3}
B = {3, 4, 5}

Intersection = {3}
```

Java:

```java
Set<Integer> intersection = new HashSet<>(a);

intersection.retainAll(b);

System.out.println(intersection);
```

Result:

```text
{3}
```

---

# 25. Difference

Difference means:

> Elements present in one Set but not the other.

Example:

```text
A = {1, 2, 3}
B = {3, 4, 5}

A - B = {1, 2}
```

Java:

```java
Set<Integer> difference = new HashSet<>(a);

difference.removeAll(b);

System.out.println(difference);
```

Result:

```text
{1, 2}
```

---

# 26. Subset and Superset

Suppose:

```text
A = {1, 2, 3, 4}
B = {2, 3}
```

`B` is a subset of `A`.

In Java:

```java
A.containsAll(B)
```

returns:

```text
true
```

Example:

```java
Set<Integer> a = Set.of(1, 2, 3, 4);
Set<Integer> b = Set.of(2, 3);

System.out.println(a.containsAll(b));
```

Output:

```text
true
```

---

# 27. Important Points

## Point 1

`Set` is an interface.

```java
Set<Integer> set;
```

---

## Point 2

You need an implementation.

```java
Set<Integer> set = new HashSet<>();
```

---

## Point 3

Duplicates are not allowed.

```java
set.add(10);
set.add(10);
```

The second insertion does not change the Set.

---

## Point 4

`add()` returns whether the Set changed.

```java
set.add(10);
```

Possible result:

```text
true
```

or:

```text
false
```

---

## Point 5

Ordering depends on the implementation.

```text
HashSet       → no guaranteed order
LinkedHashSet → insertion order
TreeSet       → sorted order
```

---

## Point 6

There is no index-based access.

You cannot do:

```java
set.get(2);
```

---

## Point 7

Set implementations have different performance characteristics.

```text
HashSet       → average O(1) basic operations
LinkedHashSet → average O(1) basic operations
TreeSet       → O(log n) basic operations
```

---

# 28. Real-World Use Cases

## 1. Removing Duplicates

Given:

```text
[10, 20, 10, 30, 20]
```

Use a Set to obtain unique values:

```text
[10, 20, 30]
```

---

## 2. Checking Membership

Example:

```text
Is this username already registered?
```

A Set can be used to track unique usernames.

---

## 3. Tracking Visited Nodes

Graph algorithms commonly use a Set to track visited nodes.

```java
Set<Integer> visited = new HashSet<>();
```

---

## 4. Unique Tags

For a post:

```text
java
backend
java
spring
backend
```

A Set can store:

```text
java
backend
spring
```

---

## 5. Unique IDs

If IDs must not repeat:

```java
Set<Long> ids = new HashSet<>();
```

---

## 6. Duplicate Detection

Example:

```text
Input:
10 20 30 20
```

A Set can help detect that `20` appeared more than once.

---

# 29. Common Mistakes

## Mistake 1 — Assuming Every Set Is Sorted

Wrong:

```text
Set automatically sorts elements.
```

Correct:

```text
TreeSet → sorted
HashSet → no guaranteed ordering
LinkedHashSet → insertion order
```

---

## Mistake 2 — Assuming Set Allows Duplicates

Wrong:

```java
set.add(10);
set.add(10);
```

expecting two elements.

A Set stores only one logical occurrence.

---

## Mistake 3 — Using Index Access

Wrong:

```java
set.get(0);
```

Set does not provide indexed access.

---

## Mistake 4 — Assuming HashSet Preserves Insertion Order

Do not rely on:

```text
HashSet
→ insertion order
```

Use:

```text
LinkedHashSet
```

when insertion order matters.

---

## Mistake 5 — Assuming Set Always Rejects Null

The interface does not impose one universal null policy.

For example:

```text
HashSet       → allows one null
LinkedHashSet → allows one null
TreeSet       → generally does not allow null with natural ordering
```

---

## Mistake 6 — Modifying a Set During Enhanced For Loop

This can cause:

```text
ConcurrentModificationException
```

Example:

```java
for (Integer value : set) {
    if (value == 10) {
        set.remove(value);
    }
}
```

If you need to remove while iterating, use the Iterator's `remove()` method where appropriate:

```java
Iterator<Integer> iterator = set.iterator();

while (iterator.hasNext()) {
    Integer value = iterator.next();

    if (value == 10) {
        iterator.remove();
    }
}
```

---

# 30. Quick Revision

## Definition

> `Set` is a Collection that represents unique elements and does not permit duplicate elements.

---

## Main Implementations

```text
HashSet
LinkedHashSet
TreeSet
```

---

## Choosing the Implementation

```text
Need unique elements
        │
        ├── Order doesn't matter
        │       ↓
        │    HashSet
        │
        ├── Need insertion order
        │       ↓
        │    LinkedHashSet
        │
        └── Need sorted order
                ↓
             TreeSet
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

## Key Differences

| Requirement | Choose |
|---|---|
| Unique elements | `HashSet` |
| Unique + insertion order | `LinkedHashSet` |
| Unique + sorted order | `TreeSet` |
| Duplicates + index access | `ArrayList` |
| FIFO processing | `Queue` / `ArrayDeque` |

---

## Complexity Summary

| Implementation | `add()` | `remove()` | `contains()` | Ordering |
|---|---:|---:|---:|---|
| `HashSet` | O(1) average | O(1) average | O(1) average | No guaranteed order |
| `LinkedHashSet` | O(1) average | O(1) average | O(1) average | Insertion order |
| `TreeSet` | O(log n) | O(log n) | O(log n) | Sorted order |

---

## Most Important Interview Line

Remember:

```text
Set = uniqueness
```

Then decide the implementation based on ordering:

```text
HashSet       → uniqueness
LinkedHashSet → uniqueness + insertion order
TreeSet       → uniqueness + sorted order
```

---

# 31. Progress

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
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
│
├── 13-HashSet
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

**`12-Set/NOTES.md` is complete. Next: `12-Set/PRACTICE.md`.**
