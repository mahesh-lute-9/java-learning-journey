# TreeSet — Interview Questions

> Interview-focused questions and answers for Java `TreeSet`, `NavigableSet`, ordering, `Comparable`, `Comparator`, performance, and common real-world scenarios.

---

## 📚 Table of Contents

1. [What is TreeSet?](#1-what-is-treeset)
2. [Which Interface Does TreeSet Implement?](#2-which-interface-does-treeset-implement)
3. [Main Features of TreeSet](#3-main-features-of-treeset)
4. [Does TreeSet Allow Duplicates?](#4-does-treeset-allow-duplicates)
5. [Does TreeSet Maintain Insertion Order?](#5-does-treeset-maintain-insertion-order)
6. [Does TreeSet Maintain Sorted Order?](#6-does-treeset-maintain-sorted-order)
7. [How Does TreeSet Sort Elements?](#7-how-does-treeset-sort-elements)
8. [What is Natural Ordering?](#8-what-is-natural-ordering)
9. [What is Comparator in TreeSet?](#9-what-is-comparator-in-treeset)
10. [Comparable vs Comparator](#10-comparable-vs-comparator)
11. [How Does TreeSet Determine Duplicates?](#11-how-does-treeset-determine-duplicates)
12. [Can compareTo() Be Inconsistent with equals()?](#12-can-compareto-be-inconsistent-with-equals)
13. [Can TreeSet Store Custom Objects?](#13-can-treeset-store-custom-objects)
14. [What Happens Without Comparable or Comparator?](#14-what-happens-without-comparable-or-comparator)
15. [Does TreeSet Allow null?](#15-does-treeset-allow-null)
16. [Is TreeSet Thread-Safe?](#16-is-treeset-thread-safe)
17. [Time Complexity of TreeSet](#17-time-complexity-of-treeset)
18. [Internal Data Structure](#18-internal-data-structure)
19. [TreeSet vs HashSet](#19-treeset-vs-hashset)
20. [TreeSet vs LinkedHashSet](#20-treeset-vs-linkedhashset)
21. [TreeSet vs ArrayList](#21-treeset-vs-arraylist)
22. [TreeSet vs PriorityQueue](#22-treeset-vs-priorityqueue)
23. [Important TreeSet Methods](#23-important-treeset-methods)
24. [first() vs pollFirst()](#24-first-vs-pollfirst)
25. [last() vs pollLast()](#25-last-vs-polllast)
26. [lower() vs floor()](#26-lower-vs-floor)
27. [higher() vs ceiling()](#27-higher-vs-ceiling)
28. [headSet(), tailSet(), subSet()](#28-headset-tailset-subset)
29. [What is descendingSet()?](#29-what-is-descendingset)
30. [Does TreeSet Support Index-Based Access?](#30-does-treeset-support-index-based-access)
31. [Can TreeSet Have a Custom Comparator?](#31-can-treeset-have-a-custom-comparator)
32. [Can TreeSet Sort in Descending Order?](#32-can-treeset-sort-in-descending-order)
33. [Can TreeSet Store Different Data Types?](#33-can-treeset-store-different-data-types)
34. [Can TreeSet Be Created from Another Collection?](#34-can-treeset-be-created-from-another-collection)
35. [What Happens When Comparator Returns 0?](#35-what-happens-when-comparator-returns-0)
36. [TreeSet Constructor Types](#36-treeset-constructor-types)
37. [Common Output Questions](#37-common-output-questions)
38. [Common Coding Questions](#38-common-coding-questions)
39. [Scenario-Based Questions](#39-scenario-based-questions)
40. [Quick Interview Revision](#40-quick-interview-revision)
41. [Interview Checklist](#41-interview-checklist)
42. [Progress](#42-progress)

---

# 1. What is TreeSet?

### Answer

`TreeSet` is a class in the `java.util` package that implements the `NavigableSet` interface.

It stores unique elements and maintains them according to their ordering.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

### Key Points

- Stores unique elements.
- Maintains sorted order.
- Uses natural ordering by default.
- Supports custom ordering using `Comparator`.
- Provides navigation methods.
- Does not support index-based access.
- Basic operations are typically `O(log n)`.

---

# 2. Which Interface Does TreeSet Implement?

`TreeSet` implements:

```text
NavigableSet
    ↓
SortedSet
    ↓
Set
    ↓
Collection
    ↓
Iterable
```

Declaration:

```java
public class TreeSet<E>
    extends AbstractSet<E>
    implements NavigableSet<E>, Cloneable, Serializable
```

---

# 3. Main Features of TreeSet

| Feature | TreeSet |
|---|---|
| Duplicates | Not allowed |
| Ordering | Sorted |
| Default ordering | Natural ordering |
| Custom ordering | Supported |
| Index access | Not supported |
| `null` | Generally not allowed with natural ordering |
| Thread-safe | No |
| Navigation methods | Yes |
| Range operations | Yes |
| Basic search | `O(log n)` |
| Basic insertion | `O(log n)` |
| Basic removal | `O(log n)` |

---

# 4. Does TreeSet Allow Duplicates?

### Answer

No.

`TreeSet` does not store duplicate elements according to its ordering.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);

System.out.println(numbers);
```

Output:

```text
[10, 20]
```

---

# 5. Does TreeSet Maintain Insertion Order?

### Answer

No.

`TreeSet` maintains sorted order, not insertion order.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

If insertion order is required, consider:

```java
LinkedHashSet
```

---

# 6. Does TreeSet Maintain Sorted Order?

### Answer

Yes.

By default, `TreeSet` maintains elements according to their natural ordering.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(50);
numbers.add(10);
numbers.add(30);

System.out.println(numbers);
```

Output:

```text
[10, 30, 50]
```

With a comparator, the ordering can be customized.

---

# 7. How Does TreeSet Sort Elements?

`TreeSet` uses either:

1. Natural ordering through `Comparable`.
2. A supplied `Comparator`.

Example using natural ordering:

```java
TreeSet<Integer> numbers = new TreeSet<>();
```

Example using a comparator:

```java
TreeSet<Integer> numbers =
        new TreeSet<>(Comparator.reverseOrder());
```

---

# 8. What is Natural Ordering?

Natural ordering is the default ordering defined by a class.

For example:

```java
Integer
String
Double
```

already implement `Comparable`.

Therefore:

```java
TreeSet<Integer> numbers = new TreeSet<>();
```

automatically stores integers in ascending order.

For custom classes, you can define natural ordering by implementing:

```java
Comparable<T>
```

---

# 9. What is Comparator in TreeSet?

A `Comparator` defines custom ordering for elements.

Example:

```java
TreeSet<Integer> numbers =
        new TreeSet<>(Comparator.reverseOrder());
```

Now the elements are maintained in descending order.

```java
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[30, 20, 10]
```

---

# 10. Comparable vs Comparator

| Comparable | Comparator |
|---|---|
| Defines natural ordering | Defines custom ordering |
| Implemented by the class | Usually defined separately |
| Method: `compareTo()` | Method: `compare()` |
| `java.lang` | `java.util` |
| Usually one natural ordering | Multiple custom orderings possible |

### Comparable

```java
class Student implements Comparable<Student> {

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.marks, other.marks);
    }
}
```

### Comparator

```java
Comparator<Student> byName =
        Comparator.comparing(student -> student.name);
```

---

# 11. How Does TreeSet Determine Duplicates?

This is an important interview question.

`TreeSet` determines element equivalence using its ordering.

If:

```java
compareTo() == 0
```

or:

```java
Comparator.compare() == 0
```

the elements are considered equivalent for `TreeSet` purposes.

Therefore, the second element is not added.

---

# 12. Can compareTo() Be Inconsistent with equals()?

### Yes.

`TreeSet` relies on comparison for ordering and uniqueness.

Consider:

```java
class Student implements Comparable<Student> {

    int id;
    String name;

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }
}
```

If two students have the same `id` but different names:

```text
Student(1, "Alice")
Student(1, "Bob")
```

`compareTo()` returns:

```text
0
```

So `TreeSet` considers them equivalent and keeps only one.

### Important

It is generally recommended that natural ordering be consistent with `equals()` when practical.

---

# 13. Can TreeSet Store Custom Objects?

### Yes.

But the objects need an ordering.

You can:

### Option 1

Implement:

```java
Comparable
```

### Option 2

Provide a:

```java
Comparator
```

Example:

```java
TreeSet<Student> students =
        new TreeSet<>(
                Comparator.comparingInt(student -> student.marks)
        );
```

---

# 14. What Happens Without Comparable or Comparator?

Suppose:

```java
class Student {
    int id;
    String name;
}
```

Then:

```java
TreeSet<Student> students = new TreeSet<>();
students.add(new Student());
```

can result in:

```text
ClassCastException
```

because the `TreeSet` needs to compare the elements.

---

# 15. Does TreeSet Allow null?

With natural ordering, `TreeSet` generally does not allow `null`.

Example:

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(null);
```

This can throw:

```text
NullPointerException
```

A comparator can explicitly define how `null` should be handled:

```java
TreeSet<Integer> numbers =
        new TreeSet<>(
                Comparator.nullsFirst(Comparator.naturalOrder())
        );
```

---

# 16. Is TreeSet Thread-Safe?

### No.

`TreeSet` is not synchronized and is not thread-safe by default.

If multiple threads modify a set concurrently, external synchronization or a suitable concurrent design may be required.

For example, a synchronized wrapper can be created:

```java
SortedSet<Integer> set =
        Collections.synchronizedSortedSet(new TreeSet<>());
```

---

# 17. Time Complexity of TreeSet

Because `TreeSet` is based on a balanced tree structure, common operations are typically:

| Operation | Complexity |
|---|---:|
| `add()` | `O(log n)` |
| `remove()` | `O(log n)` |
| `contains()` | `O(log n)` |
| `first()` | `O(log n)` or implementation-dependent constant-ish traversal |
| `last()` | `O(log n)` or implementation-dependent constant-ish traversal |
| `size()` | `O(1)` |
| `clear()` | `O(n)` |
| Iteration | `O(n)` |

For interview purposes, remember:

```text
add     → O(log n)
remove  → O(log n)
search  → O(log n)
```

---

# 18. Internal Data Structure

The standard Java implementation of `TreeSet` is backed by a `TreeMap`.

Conceptually:

```text
TreeSet
   ↓
TreeMap
   ↓
Red-Black Tree
```

A red-black tree is a self-balancing binary search tree.

This allows the tree to maintain efficient operations.

---

# 19. TreeSet vs HashSet

| Feature | TreeSet | HashSet |
|---|---|---|
| Duplicate elements | No | No |
| Ordering | Sorted | No guaranteed ordering |
| Average basic operations | `O(log n)` | `O(1)` |
| Navigation | Yes | No |
| Range operations | Yes | No |
| Custom ordering | Yes | No |
| Hash table | No | Yes |
| Best for sorted data | Yes | No |

### Interview Answer

Use `TreeSet` when sorted order or navigation is required.

Use `HashSet` when ordering is not required and average constant-time basic operations are preferred.

---

# 20. TreeSet vs LinkedHashSet

| Feature | TreeSet | LinkedHashSet |
|---|---|---|
| Unique elements | Yes | Yes |
| Sorted order | Yes | No |
| Insertion order | No | Yes |
| Navigation methods | Yes | No |
| Range operations | Yes | No |
| Basic operations | `O(log n)` | Average `O(1)` |

---

# 21. TreeSet vs ArrayList

| Feature | TreeSet | ArrayList |
|---|---|---|
| Interface | `Set` | `List` |
| Duplicates | No | Yes |
| Sorted automatically | Yes | No |
| Index access | No | Yes |
| `get(index)` | No | Yes |
| Navigation methods | Yes | No |
| Random access | No | Yes |

### Interview Rule

If you need:

```text
unique + sorted
```

consider:

```text
TreeSet
```

If you need:

```text
duplicates + index access
```

consider:

```text
ArrayList
```

---

# 22. TreeSet vs PriorityQueue

This is a common comparison.

| Feature | TreeSet | PriorityQueue |
|---|---|---|
| Duplicates | No | Yes |
| Entire collection sorted when iterated | Yes | No |
| Access minimum/maximum | Yes | Yes, depending on ordering |
| Navigation methods | Yes | No |
| Range operations | Yes | No |
| Set semantics | Yes | No |
| Primary purpose | Sorted unique elements | Priority-based processing |

### Important

Do not assume that iterating a `PriorityQueue` gives sorted order.

---

# 23. Important TreeSet Methods

### Basic Methods

```java
add()
remove()
contains()
size()
isEmpty()
clear()
```

### Ordering Methods

```java
first()
last()
```

### Navigation Methods

```java
lower()
floor()
higher()
ceiling()
```

### Removal of Extremes

```java
pollFirst()
pollLast()
```

### Views

```java
headSet()
tailSet()
subSet()
descendingSet()
```

### Iterators

```java
iterator()
descendingIterator()
```

---

# 24. first() vs pollFirst()

## first()

Returns the first element without removing it.

```java
TreeSet<Integer> set =
        new TreeSet<>(Arrays.asList(10, 20, 30));

System.out.println(set.first());
System.out.println(set);
```

Output:

```text
10
[10, 20, 30]
```

---

## pollFirst()

Returns and removes the first element.

```java
System.out.println(set.pollFirst());
System.out.println(set);
```

Output:

```text
10
[20, 30]
```

### Remember

```text
first()     → read
pollFirst() → read + remove
```

---

# 25. last() vs pollLast()

## last()

Returns the largest element without removing it.

```java
set.last();
```

## pollLast()

Returns and removes the largest element.

```java
set.pollLast();
```

### Remember

```text
last()     → read
pollLast() → read + remove
```

---

# 26. lower() vs floor()

Given:

```text
[10, 20, 30, 40, 50]
```

For:

```text
30
```

### lower(30)

Returns:

```text
20
```

because it means:

```text
strictly less than 30
```

### floor(30)

Returns:

```text
30
```

because it means:

```text
less than or equal to 30
```

### Easy Rule

```text
lower → <
floor  → <=
```

---

# 27. higher() vs ceiling()

Given:

```text
[10, 20, 30, 40, 50]
```

For:

```text
30
```

### higher(30)

Returns:

```text
40
```

because:

```text
> 30
```

### ceiling(30)

Returns:

```text
30
```

because:

```text
>= 30
```

### Easy Rule

```text
higher  → >
ceiling → >=
```

---

# 28. headSet(), tailSet(), subSet()

These methods provide range views.

Given:

```text
[10, 20, 30, 40, 50]
```

### headSet()

```java
set.headSet(30);
```

Output:

```text
[10, 20]
```

### tailSet()

```java
set.tailSet(30);
```

Output:

```text
[30, 40, 50]
```

### subSet()

```java
set.subSet(20, 50);
```

Output:

```text
[20, 30, 40]
```

The range methods return views backed by the original set.

---

# 29. What is descendingSet()?

`descendingSet()` returns a reverse-order view of the set.

```java
TreeSet<Integer> set =
        new TreeSet<>(Arrays.asList(10, 20, 30, 40));

System.out.println(set.descendingSet());
```

Output:

```text
[40, 30, 20, 10]
```

---

# 30. Does TreeSet Support Index-Based Access?

### No.

This is invalid:

```java
set.get(0);
```

`TreeSet` is a `Set`, not a `List`.

If index-based access is required, use:

```java
ArrayList
```

or another `List` implementation.

---

# 31. Can TreeSet Have a Custom Comparator?

### Yes.

Example:

```java
TreeSet<String> names =
        new TreeSet<>(Comparator.reverseOrder());

names.add("Alice");
names.add("Charlie");
names.add("Bob");

System.out.println(names);
```

Output:

```text
[Charlie, Bob, Alice]
```

---

# 32. Can TreeSet Sort in Descending Order?

### Yes.

Use:

```java
Comparator.reverseOrder()
```

Example:

```java
TreeSet<Integer> numbers =
        new TreeSet<>(Comparator.reverseOrder());
```

Or use the reverse-order view:

```java
numbers.descendingSet();
```

### Difference

`Comparator.reverseOrder()` defines the set's ordering.

`descendingSet()` provides a reverse-order view of an existing set.

---

# 33. Can TreeSet Store Different Data Types?

Generally, elements in a naturally ordered `TreeSet` need to be mutually comparable.

For example, mixing unrelated types such as:

```java
Integer
String
```

does not provide a meaningful natural ordering and can result in:

```text
ClassCastException
```

In practice, use a `TreeSet` containing compatible element types and a consistent ordering.

---

# 34. Can TreeSet Be Created from Another Collection?

### Yes.

Example:

```java
List<Integer> numbers =
        Arrays.asList(30, 10, 20, 10);

TreeSet<Integer> set =
        new TreeSet<>(numbers);

System.out.println(set);
```

Output:

```text
[10, 20, 30]
```

This is useful when you want to:

- Remove duplicates.
- Maintain sorted order.

---

# 35. What Happens When Comparator Returns 0?

This is a very important question.

Suppose:

```java
TreeSet<String> set =
        new TreeSet<>(
                Comparator.comparingInt(String::length)
        );
```

Now:

```java
set.add("Java");
set.add("Code");
```

Both strings have length:

```text
4
```

The comparator returns:

```text
0
```

Therefore, the `TreeSet` treats them as equivalent for set purposes.

The result contains only one of them.

### Key Rule

```text
Comparator.compare(a, b) == 0
```

means the elements are considered equivalent by the `TreeSet` ordering.

---

# 36. TreeSet Constructor Types

Common constructors include:

```java
TreeSet()
```

Creates an empty set using natural ordering.

---

```java
TreeSet(Comparator<? super E> comparator)
```

Creates a set using the specified comparator.

---

```java
TreeSet(Collection<? extends E> c)
```

Creates a set containing elements from a collection.

---

```java
TreeSet(SortedSet<E> s)
```

Creates a set containing elements from another sorted set.

### Important

Unlike `HashSet`, `TreeSet` does not have initial-capacity or load-factor constructors.

---

# 37. Common Output Questions

## Question 1

What is the output?

```java
TreeSet<Integer> set = new TreeSet<>();

set.add(30);
set.add(10);
set.add(20);
set.add(10);

System.out.println(set);
```

### Answer

```text
[10, 20, 30]
```

---

## Question 2

```java
TreeSet<Integer> set =
        new TreeSet<>(Comparator.reverseOrder());

set.add(10);
set.add(30);
set.add(20);

System.out.println(set);
```

### Answer

```text
[30, 20, 10]
```

---

## Question 3

```java
TreeSet<Integer> set =
        new TreeSet<>(
                Arrays.asList(10, 20, 30, 40, 50)
        );

System.out.println(set.lower(30));
System.out.println(set.floor(30));
System.out.println(set.higher(30));
System.out.println(set.ceiling(30));
```

### Answer

```text
20
30
40
30
```

---

## Question 4

```java
TreeSet<Integer> set =
        new TreeSet<>(
                Arrays.asList(10, 20, 30, 40, 50)
        );

System.out.println(set.headSet(30));
System.out.println(set.tailSet(30));
System.out.println(set.subSet(20, 50));
```

### Answer

```text
[10, 20]
[30, 40, 50]
[20, 30, 40]
```

---

# 38. Common Coding Questions

## Coding Question 1

Remove duplicates from an integer array and print the values in ascending order.

### Expected Approach

```java
TreeSet<Integer> set = new TreeSet<>();

for (int number : numbers) {
    set.add(number);
}

System.out.println(set);
```

---

## Coding Question 2

Find the smallest and largest values.

### Expected Approach

```java
System.out.println(set.first());
System.out.println(set.last());
```

---

## Coding Question 3

Find the closest value lower than a target.

### Expected Approach

```java
Integer result = set.lower(target);
```

---

## Coding Question 4

Find the closest value greater than a target.

### Expected Approach

```java
Integer result = set.higher(target);
```

---

## Coding Question 5

Find the floor and ceiling of a target.

### Expected Approach

```java
Integer floor = set.floor(target);
Integer ceiling = set.ceiling(target);
```

---

## Coding Question 6

Print all values within a range.

### Expected Approach

```java
System.out.println(set.subSet(
        lowerBound,
        true,
        upperBound,
        true
));
```

---

# 39. Scenario-Based Questions

## Scenario 1

You need to store unique numbers and keep them sorted automatically.

### Answer

```text
TreeSet
```

---

## Scenario 2

You need unique values but do not care about ordering and want fast average lookup.

### Answer

```text
HashSet
```

---

## Scenario 3

You need unique values in insertion order.

### Answer

```text
LinkedHashSet
```

---

## Scenario 4

You need sorted unique values and frequently need:

```text
smallest value
largest value
nearest lower value
nearest higher value
range queries
```

### Answer

```text
TreeSet
```

---

## Scenario 5

You need duplicate values and index-based access.

### Answer

```text
ArrayList
```

---

## Scenario 6

You need to process elements according to priority and duplicates are allowed.

### Answer

```text
PriorityQueue
```

---

## Scenario 7

You need students sorted by marks for one operation and by name for another.

### Answer

Use a `Comparator` and create the required ordering.

Example:

```java
Comparator<Student> byMarks =
        Comparator.comparingInt(student -> student.marks);

Comparator<Student> byName =
        Comparator.comparing(student -> student.name);
```

---

# 40. Quick Interview Revision

## TreeSet in One Minute

```text
TreeSet
│
├── java.util
├── Implements NavigableSet
├── Unique elements
├── Sorted order
├── Natural ordering by default
├── Custom Comparator supported
├── Navigation methods
├── Range views
├── No index-based access
├── Not thread-safe
├── Generally no null with natural ordering
└── Basic operations → O(log n)
```

---

## Most Important Methods

```java
add()
remove()
contains()
first()
last()
lower()
floor()
higher()
ceiling()
pollFirst()
pollLast()
headSet()
tailSet()
subSet()
descendingSet()
```

---

## Most Important Rules

### Rule 1

```text
TreeSet = unique + sorted
```

### Rule 2

```text
No insertion-order guarantee
```

### Rule 3

```text
Natural ordering → Comparable
Custom ordering → Comparator
```

### Rule 4

```text
compareTo() / compare() == 0
→ equivalent for TreeSet purposes
```

### Rule 5

```text
No index-based access
```

### Rule 6

```text
Basic operations → O(log n)
```

### Rule 7

```text
TreeSet is not thread-safe
```

---

# 41. Interview Checklist

## Basics

- [ ] What is `TreeSet`?
- [ ] Which interfaces does it implement?
- [ ] Does it allow duplicates?
- [ ] Does it maintain insertion order?
- [ ] Does it maintain sorted order?
- [ ] Is it thread-safe?
- [ ] Does it support index-based access?

---

## Ordering

- [ ] What is natural ordering?
- [ ] What is `Comparable`?
- [ ] What is `Comparator`?
- [ ] Difference between `Comparable` and `Comparator`.
- [ ] How do you sort a `TreeSet` in descending order?
- [ ] What happens when comparison returns `0`?

---

## Navigation

- [ ] Explain `first()`.
- [ ] Explain `last()`.
- [ ] Explain `lower()`.
- [ ] Explain `floor()`.
- [ ] Explain `higher()`.
- [ ] Explain `ceiling()`.
- [ ] Explain `pollFirst()`.
- [ ] Explain `pollLast()`.

---

## Range Operations

- [ ] Explain `headSet()`.
- [ ] Explain `tailSet()`.
- [ ] Explain `subSet()`.
- [ ] Understand inclusive and exclusive boundaries.
- [ ] Understand range views.
- [ ] Explain `descendingSet()`.

---

## Internal Working

- [ ] Know that `TreeSet` is backed by a `TreeMap` in the standard Java implementation.
- [ ] Know about red-black trees.
- [ ] Know why basic operations are `O(log n)`.
- [ ] Understand comparison-based uniqueness.

---

## Comparisons

- [ ] `TreeSet` vs `HashSet`
- [ ] `TreeSet` vs `LinkedHashSet`
- [ ] `TreeSet` vs `ArrayList`
- [ ] `TreeSet` vs `PriorityQueue`

---

# 42. Progress

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
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 15-TreeSet
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 16-Map
├── 17-HashMap
├── 18-LinkedHashMap
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

**`15-TreeSet` is now complete: `NOTES.md` + `PRACTICE.md` + `INTERVIEW.md`.**

**Next topic: `16-Map`.**
