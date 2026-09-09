# Set — Interview Questions

## Table of Contents

1. [Basic Questions](#1-basic-questions)
2. [Set Characteristics](#2-set-characteristics)
3. [Methods and Behavior](#3-methods-and-behavior)
4. [Duplicates and Equality](#4-duplicates-and-equality)
5. [Ordering](#5-ordering)
6. [Set Implementations](#6-set-implementations)
7. [Complexity](#7-complexity)
8. [Set Operations](#8-set-operations)
9. [Set vs Other Collections](#9-set-vs-other-collections)
10. [Scenario-Based Questions](#10-scenario-based-questions)
11. [Output-Based Questions](#11-output-based-questions)
12. [Coding Questions](#12-coding-questions)
13. [Common Interview Traps](#13-common-interview-traps)
14. [Quick Revision](#14-quick-revision)
15. [Interview Checklist](#15-interview-checklist)
16. [Progress](#16-progress)

---

# 1. Basic Questions

## 1. What is a Set in Java?

`Set` is an interface in the Java Collections Framework that represents a collection of **unique elements**.

The defining property of a Set is:

> A Set does not allow duplicate elements.

Example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
```

The logical contents are:

```text
10
20
```

The second `10` does not create another element.

---

## 2. Which interface does Set extend?

`Set` extends:

```java
Collection<E>
```

Hierarchy:

```text
Iterable
   ↓
Collection
   ↓
Set
```

Therefore, Set inherits common Collection operations such as:

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

## 3. Can we create an object of Set directly?

No.

This is invalid:

```java
Set<Integer> set = new Set<>();
```

`Set` is an interface.

Instead:

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

---

## 4. Why do we use `Set` instead of `List`?

Use a Set when **uniqueness** is important.

Example:

```text
Student IDs:
101
102
101
103
```

If duplicate IDs should not be stored:

```java
Set<Integer> ids = new HashSet<>();
```

A List would allow the duplicate.

---

## 5. Does Set allow duplicate elements?

No.

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(10);
set.add(20);
```

The Set contains only:

```text
10
20
```

---

# 2. Set Characteristics

## 6. Does Set maintain insertion order?

Not necessarily.

Ordering depends on the implementation.

```text
HashSet       → no guaranteed iteration order
LinkedHashSet → insertion order
TreeSet       → sorted order
```

---

## 7. Does Set support index-based access?

No.

You cannot do:

```java
set.get(2);
```

Set is not an indexed collection.

---

## 8. Does Set allow null?

The `Set` interface does not impose one universal rule.

It depends on the implementation.

| Implementation | Null Support |
|---|---|
| `HashSet` | Allows one `null` |
| `LinkedHashSet` | Allows one `null` |
| `TreeSet` | Generally does not allow `null` with natural ordering |

---

## 9. Does Set allow mutable objects?

Yes, but caution is required.

If an object's fields used by `equals()` and `hashCode()` are changed after inserting it into a hash-based Set, the Set may no longer behave correctly for lookup/removal.

Example concept:

```text
Object inserted
     ↓
hashCode determines location
     ↓
Object state changes
     ↓
hashCode may change
     ↓
contains/remove may fail unexpectedly
```

Therefore:

> Objects used as elements in hash-based Sets should ideally have stable equality and hash-code state while stored in the Set.

---

## 10. Is Set thread-safe?

The `Set` interface itself does not guarantee thread safety.

The behavior depends on the implementation.

For example:

```text
HashSet → not synchronized
LinkedHashSet → not synchronized
TreeSet → not synchronized
```

For concurrent requirements, use an appropriate concurrent Set implementation or synchronization strategy.

---

# 3. Methods and Behavior

## 11. What does `add()` return?

`add()` returns a boolean.

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
true  → Set changed
false → element already existed
```

---

## 12. What does `remove()` return?

`remove()` returns:

```text
true
```

if the element existed and was removed.

Otherwise:

```text
false
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

## 13. What does `contains()` do?

It checks whether an element exists.

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);

System.out.println(set.contains(20));
```

Output:

```text
true
```

---

## 14. What does `size()` return?

The number of unique elements.

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);
set.add(10);

System.out.println(set.size());
```

Output:

```text
2
```

---

## 15. What does `clear()` do?

It removes all elements.

```java
set.clear();
```

After:

```java
set.isEmpty()
```

returns:

```text
true
```

---

## 16. Can we use `Iterator` with Set?

Yes.

```java
Iterator<Integer> iterator = set.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

---

# 4. Duplicates and Equality

## 17. How does a Set determine whether an element is a duplicate?

The answer depends on the implementation.

For hash-based Sets such as `HashSet`, duplicate detection relies on:

```text
hashCode()
+
equals()
```

Conceptually:

```text
add(element)
     ↓
hashCode()
     ↓
candidate bucket/location
     ↓
equals()
     ↓
duplicate?
```

For `TreeSet`, uniqueness is based on its ordering/comparison mechanism.

---

## 18. What is the role of `equals()` and `hashCode()` in HashSet?

For objects stored in a `HashSet`:

- `hashCode()` helps determine where the object should be looked up.
- `equals()` is used to determine equality among candidate objects.

If two objects are equal according to `equals()`:

```java
a.equals(b) == true
```

they must have the same hash code:

```java
a.hashCode() == b.hashCode()
```

This is part of Java's `equals()`/`hashCode()` contract.

---

## 19. What happens if `equals()` is overridden but `hashCode()` is not?

This can cause incorrect behavior in hash-based collections.

For example, logically equal objects may produce different hash codes.

Then a `HashSet` may place them in different hash locations and fail to recognize them as duplicates.

Therefore:

> If you override `equals()`, you should normally override `hashCode()` consistently.

---

## 20. Why is `hashCode()` not enough to detect duplicates?

Different objects can have the same hash code.

This is called a collision.

Therefore:

```text
same hashCode
```

does not necessarily mean:

```text
objects are equal
```

Conceptually:

```text
hashCode()
    ↓
candidate location
    ↓
equals()
    ↓
actual equality check
```

---

## 21. What happens if `hashCode()` changes after insertion?

Suppose an object is inserted into a `HashSet` and later a field used by `hashCode()` is modified.

Its new hash code may point to a different location.

Then operations such as:

```java
set.contains(object);
set.remove(object);
```

may behave unexpectedly.

This is why mutable fields that participate in equality should generally not be changed while the object is being used as a hash-based Set element.

---

# 5. Ordering

## 22. What is the difference between HashSet, LinkedHashSet, and TreeSet?

This is one of the most important interview questions.

| Set | Ordering | Typical Basic Operation |
|---|---|---|
| `HashSet` | No guaranteed iteration order | O(1) average |
| `LinkedHashSet` | Insertion order | O(1) average |
| `TreeSet` | Sorted order | O(log n) |

---

## 23. Why doesn't HashSet preserve insertion order?

`HashSet` is hash-based.

Its iteration order depends on its internal organization rather than the order in which elements were inserted.

Therefore:

> Never write code that depends on a particular `HashSet` iteration order.

---

## 24. How does LinkedHashSet preserve insertion order?

`LinkedHashSet` maintains additional linked ordering information along with hash-based storage.

Therefore, iteration follows insertion order.

Example:

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(30);
set.add(10);
set.add(20);
```

Iteration:

```text
30
10
20
```

---

## 25. How does TreeSet maintain sorted order?

`TreeSet` is based on a tree structure and maintains elements according to their ordering.

With natural ordering:

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

---

# 6. Set Implementations

## 26. What are the main implementations of Set?

The three most important are:

```text
HashSet
LinkedHashSet
TreeSet
```

---

## 27. When should you use HashSet?

Use `HashSet` when:

- Duplicate elements should be prevented.
- Ordering is not required.
- Fast average-case membership operations are useful.

Example:

```java
Set<String> usernames = new HashSet<>();
```

---

## 28. When should you use LinkedHashSet?

Use `LinkedHashSet` when:

- Duplicate elements should be prevented.
- Insertion order matters.

Example:

```java
Set<String> names = new LinkedHashSet<>();
```

---

## 29. When should you use TreeSet?

Use `TreeSet` when:

- Duplicate elements should be prevented.
- Sorted order is required.

Example:

```java
Set<Integer> numbers = new TreeSet<>();
```

---

## 30. Can TreeSet store custom objects?

Yes, but the objects must have a defined ordering.

This can be provided through:

```text
Comparable
```

or:

```text
Comparator
```

Example:

```java
TreeSet<Student> students = new TreeSet<>();
```

If `Student` does not provide a usable ordering, operations may fail with a `ClassCastException` or require a suitable Comparator.

---

## 31. Does TreeSet use `equals()` to determine duplicates?

This is a common interview trap.

`TreeSet` uses its comparison mechanism.

With natural ordering, this means `compareTo()`.

With a supplied Comparator, it means `compare()`.

If comparison returns:

```text
0
```

the elements are considered equivalent for TreeSet ordering purposes.

Therefore:

> TreeSet uniqueness is based on comparison, not directly on `equals()`.

---

# 7. Complexity

## 32. What is the time complexity of HashSet?

Typical average-case:

```text
add()       → O(1)
remove()    → O(1)
contains()  → O(1)
```

Worst-case behavior can differ depending on collisions and implementation details.

---

## 33. What is the time complexity of LinkedHashSet?

Typical average-case:

```text
add()       → O(1)
remove()    → O(1)
contains()  → O(1)
```

It additionally maintains insertion-order links.

---

## 34. What is the time complexity of TreeSet?

Typically:

```text
add()       → O(log n)
remove()    → O(log n)
contains()  → O(log n)
```

because it maintains elements in a balanced tree structure.

---

## 35. Which Set is fastest?

There is no universal "fastest" Set.

For typical membership operations:

```text
HashSet
```

usually provides the best average-case complexity.

But the correct choice depends on requirements.

```text
Need uniqueness only
        ↓
HashSet

Need insertion order
        ↓
LinkedHashSet

Need sorted order
        ↓
TreeSet
```

---

# 8. Set Operations

## 36. How do you find the union of two Sets?

Use:

```java
addAll()
```

Example:

```java
Set<Integer> union = new HashSet<>(a);
union.addAll(b);
```

If:

```text
A = {1, 2, 3}
B = {3, 4, 5}
```

then:

```text
Union = {1, 2, 3, 4, 5}
```

---

## 37. How do you find the intersection?

Use:

```java
retainAll()
```

Example:

```java
Set<Integer> intersection = new HashSet<>(a);
intersection.retainAll(b);
```

Result:

```text
{3}
```

---

## 38. How do you find the difference?

Use:

```java
removeAll()
```

Example:

```java
Set<Integer> difference = new HashSet<>(a);
difference.removeAll(b);
```

If:

```text
A = {1, 2, 3}
B = {3, 4, 5}
```

then:

```text
A - B = {1, 2}
```

---

## 39. How do you check whether one Set is a subset of another?

Use:

```java
containsAll()
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

# 9. Set vs Other Collections

## 40. Set vs List

| Set | List |
|---|---|
| No duplicates | Duplicates allowed |
| No index-based access | Index-based access |
| Good for uniqueness | Good for ordered sequence |
| Ordering depends on implementation | Sequence order is maintained |

---

## 41. Set vs Queue

Set:

```text
Uniqueness
```

Queue:

```text
Processing order
```

Example:

```text
Set   → track visited nodes
Queue → process BFS nodes
```

---

## 42. HashSet vs ArrayList

Suppose you frequently ask:

```text
"Does this ID already exist?"
```

For a large collection, a `HashSet` is usually a better fit for average-case membership checking.

```java
Set<Integer> ids = new HashSet<>();

ids.contains(id);
```

An `ArrayList` requires a linear search for `contains()`.

```text
HashSet contains() → O(1) average
ArrayList contains() → O(n)
```

---

## 43. HashSet vs LinkedHashSet

Use:

```text
HashSet
```

when order does not matter.

Use:

```text
LinkedHashSet
```

when insertion order matters.

---

## 44. HashSet vs TreeSet

Use:

```text
HashSet
```

for:

```text
uniqueness + fast average-case lookup
```

Use:

```text
TreeSet
```

for:

```text
uniqueness + sorted order
```

---

# 10. Scenario-Based Questions

## 45. You receive thousands of IDs and need to detect duplicates.

Which Set would you choose?

```text
HashSet
```

Reason:

- Uniqueness is the main requirement.
- Ordering is not required.
- Average-case membership operations are efficient.

---

## 46. You need to remove duplicates from an array but preserve original order.

Which Set?

```text
LinkedHashSet
```

Example:

```text
Input:
[4, 2, 4, 1, 2]

Output:
[4, 2, 1]
```

---

## 47. You need unique values in ascending order.

Which Set?

```text
TreeSet
```

Example:

```text
Input:
[50, 10, 30, 20]

Output:
[10, 20, 30, 50]
```

---

## 48. You need to check whether a username already exists.

Which collection is appropriate?

```text
HashSet<String>
```

Example:

```java
Set<String> usernames = new HashSet<>();

if (usernames.contains(username)) {
    System.out.println("Username already exists");
}
```

---

## 49. You need to track visited graph nodes.

Which collection is commonly used?

```text
HashSet
```

Example:

```java
Set<Integer> visited = new HashSet<>();
```

---

## 50. You need unique values and sorted traversal.

Which implementation?

```text
TreeSet
```

---

## 51. You need duplicates and index access.

Should you use a Set?

No.

Use an appropriate `List`, such as:

```text
ArrayList
```

---

# 11. Output-Based Questions

## Question 1

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);
set.add(10);

System.out.println(set.size());
```

### Answer

```text
2
```

---

## Question 2

```java
Set<Integer> set = new HashSet<>();

System.out.println(set.add(10));
System.out.println(set.add(10));
```

### Answer

```text
true
false
```

---

## Question 3

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(30);
set.add(10);
set.add(20);

System.out.println(set);
```

### Answer

```text
[30, 10, 20]
```

Because `LinkedHashSet` preserves insertion order.

---

## Question 4

```java
Set<Integer> set = new TreeSet<>();

set.add(30);
set.add(10);
set.add(20);

System.out.println(set);
```

### Answer

```text
[10, 20, 30]
```

---

## Question 5

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);

System.out.println(set.contains(10));

set.remove(10);

System.out.println(set.contains(10));
```

### Answer

```text
true
false
```

---

## Question 6

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);
set.add(10);
set.add(30);

set.clear();

System.out.println(set.isEmpty());
```

### Answer

```text
true
```

---

## Question 7

What is guaranteed?

```java
Set<Integer> set = new HashSet<>();

set.add(30);
set.add(10);
set.add(20);

System.out.println(set);
```

### Answer

The exact iteration order is **not guaranteed**.

Do not predict a specific order for a `HashSet`.

---

## Question 8

```java
Set<Integer> a = new HashSet<>();
Set<Integer> b = new HashSet<>();

a.add(1);
a.add(2);
a.add(3);

b.add(2);
b.add(3);
b.add(4);

Set<Integer> result = new HashSet<>(a);
result.retainAll(b);

System.out.println(result);
```

### Answer

```text
[2, 3]
```

The exact display order is not guaranteed.

---

# 12. Coding Questions

## 52. Remove Duplicates from an Array

Write:

```java
int[] removeDuplicates(int[] arr)
```

Example:

```text
Input:
[1, 2, 2, 3, 1, 4]

Output:
[1, 2, 3, 4]
```

### Follow-up

How would you preserve insertion order?

Answer:

```text
LinkedHashSet
```

---

## 53. Check Whether an Array Contains Duplicates

Write:

```java
boolean containsDuplicate(int[] arr)
```

Example:

```text
[1, 2, 3, 4] → false
[1, 2, 3, 2] → true
```

### Expected Approach

Use:

```java
Set<Integer> set = new HashSet<>();
```

For every element:

```text
If already present → duplicate found
Otherwise → add it
```

---

## 54. Find Common Elements

Given:

```text
A = [1, 2, 3, 4]
B = [3, 4, 5, 6]
```

Return:

```text
[3, 4]
```

Use Set intersection.

---

## 55. Find Union

Given:

```text
A = [1, 2, 3]
B = [3, 4, 5]
```

Return:

```text
[1, 2, 3, 4, 5]
```

Use:

```java
addAll()
```

---

## 56. Find Missing Elements

Given:

```text
Expected:
1, 2, 3, 4, 5

Actual:
1, 2, 4, 5
```

Find:

```text
3
```

Try solving using a Set.

---

## 57. Find First Repeating Element

Given:

```text
[10, 20, 30, 20, 40]
```

Return:

```text
20
```

### Hint

Maintain a Set of already-seen elements.

---

## 58. Find Unique Characters

Given:

```text
"programming"
```

Find the characters that occur at least once.

Use:

```java
Set<Character>
```

---

## 59. Longest Consecutive Sequence

Given:

```text
[100, 4, 200, 1, 3, 2]
```

Find the length of the longest consecutive sequence.

Expected:

```text
4
```

Because:

```text
1, 2, 3, 4
```

form the longest sequence.

A Set can help achieve an efficient solution.

---

## 60. Check if Two Arrays Contain the Same Unique Values

Given:

```text
A = [1, 2, 2, 3]
B = [3, 2, 1]
```

Return:

```text
true
```

because:

```text
unique(A) = {1, 2, 3}
unique(B) = {1, 2, 3}
```

---

# 13. Common Interview Traps

## Trap 1 — "Set Means Sorted"

Wrong.

```text
Set ≠ Sorted
```

Instead:

```text
HashSet       → no guaranteed order
LinkedHashSet → insertion order
TreeSet       → sorted order
```

---

## Trap 2 — "HashSet Preserves Insertion Order"

Wrong.

Do not rely on HashSet iteration order.

If insertion order matters:

```text
LinkedHashSet
```

---

## Trap 3 — "Set Always Rejects Null"

Wrong.

Null support depends on the implementation.

For example:

```text
HashSet       → one null allowed
LinkedHashSet → one null allowed
TreeSet       → generally no null with natural ordering
```

---

## Trap 4 — "TreeSet Uses hashCode()"

Wrong.

TreeSet is based on ordering.

It uses:

```text
Comparable
```

or:

```text
Comparator
```

for comparison.

---

## Trap 5 — "TreeSet Duplicate Means equals() Is True"

Not necessarily.

For TreeSet, if comparison returns:

```text
0
```

the elements are considered equivalent for Set purposes.

This can happen even when `equals()` returns `false`.

---

## Trap 6 — "HashSet Contains Is Always O(1)"

Better answer:

```text
Average case → O(1)
```

Do not describe it as an unconditional guarantee.

---

## Trap 7 — Forgetting the equals/hashCode Contract

If:

```java
a.equals(b) == true
```

then:

```java
a.hashCode() == b.hashCode()
```

must also be true.

---

## Trap 8 — Modifying Hash-Based Set Elements

If fields involved in `equals()`/`hashCode()` change while the object is inside a `HashSet`, lookups can behave unexpectedly.

Prefer stable key/equality state while the object is stored.

---

## Trap 9 — Modifying a Set During Enhanced For Loop

This can result in:

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

Prefer the Iterator's `remove()` when removing during iteration:

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

# 14. Quick Revision

## Definition

> `Set` is a Collection that stores unique elements and does not allow duplicate elements.

---

## Main Implementations

```text
HashSet
LinkedHashSet
TreeSet
```

---

## Remember Them Like This

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

## Important Methods

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

## Set Operations

```java
addAll()       // Union
retainAll()    // Intersection
removeAll()    // Difference
containsAll()  // Subset check
```

---

## Complexity

```text
HashSet
add       → O(1) average
remove    → O(1) average
contains  → O(1) average

LinkedHashSet
add       → O(1) average
remove    → O(1) average
contains  → O(1) average

TreeSet
add       → O(log n)
remove    → O(log n)
contains  → O(log n)
```

---

## Most Important Interview Question

### "Which Set should I use?"

Answer:

```text
Need uniqueness
        │
        ├── Order doesn't matter
        │       ↓
        │    HashSet
        │
        ├── Need insertion order
        │       ↓
        │  LinkedHashSet
        │
        └── Need sorted order
                ↓
             TreeSet
```

---

# 15. Interview Checklist

Before moving to `HashSet`, make sure you can answer:

- [ ] What is Set?
- [ ] Why does Set not allow duplicates?
- [ ] Is Set an interface or class?
- [ ] Which interface does Set extend?
- [ ] Can Set be instantiated directly?
- [ ] Does Set support index-based access?
- [ ] Does Set maintain insertion order?
- [ ] Does Set allow null?
- [ ] Is Set thread-safe?
- [ ] What does `add()` return?
- [ ] What does `remove()` return?
- [ ] What does `contains()` do?
- [ ] How does HashSet detect duplicates?
- [ ] Why are `equals()` and `hashCode()` important?
- [ ] What happens if `hashCode()` changes after insertion?
- [ ] Difference between HashSet and LinkedHashSet
- [ ] Difference between LinkedHashSet and TreeSet
- [ ] Difference between HashSet and TreeSet
- [ ] How does TreeSet determine uniqueness?
- [ ] Complexity of HashSet operations
- [ ] Complexity of LinkedHashSet operations
- [ ] Complexity of TreeSet operations
- [ ] How to perform union?
- [ ] How to perform intersection?
- [ ] How to perform difference?
- [ ] How to check subset?
- [ ] How to remove duplicates from an array?
- [ ] How to detect duplicates?
- [ ] How to find common elements?
- [ ] When should you choose each Set implementation?

---

# 16. Progress

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

**`12-Set` is now complete — NOTES, PRACTICE, and INTERVIEW. Next: `13-HashSet/NOTES.md`.**
