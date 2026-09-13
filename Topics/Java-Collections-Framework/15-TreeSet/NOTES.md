# TreeSet — Notes

> `TreeSet` is a `Set` implementation that stores unique elements in sorted order.

---

## Table of Contents

- [1. What is TreeSet?](#1-what-is-treeset)
- [2. Package](#2-package)
- [3. Declaration](#3-declaration)
- [4. Basic Example](#4-basic-example)
- [5. Key Characteristics](#5-key-characteristics)
- [6. Why Use TreeSet?](#6-why-use-treeset)
- [7. Unique Elements](#7-unique-elements)
- [8. Sorted Order](#8-sorted-order)
- [9. Natural Ordering](#9-natural-ordering)
- [10. Comparator Ordering](#10-comparator-ordering)
- [11. Adding Elements](#11-adding-elements)
- [12. Removing Elements](#12-removing-elements)
- [13. contains()](#13-contains)
- [14. first() and last()](#14-first-and-last)
- [15. Higher, Lower, Ceiling, and Floor](#15-higher-lower-ceiling-and-floor)
- [16. Iteration](#16-iteration)
- [17. Null Elements](#17-null-elements)
- [18. Internal Working](#18-internal-working)
- [19. TreeSet and TreeMap](#19-treeset-and-treemap)
- [20. Time Complexity](#20-time-complexity)
- [21. Constructors](#21-constructors)
- [22. Custom Objects](#22-custom-objects)
- [23. Comparable and TreeSet](#23-comparable-and-treeset)
- [24. Comparator and TreeSet](#24-comparator-and-treeset)
- [25. Important NavigableSet Methods](#25-important-navigableset-methods)
- [26. TreeSet vs HashSet](#26-treeset-vs-hashset)
- [27. TreeSet vs LinkedHashSet](#27-treeset-vs-linkedhashset)
- [28. TreeSet vs ArrayList](#28-treeset-vs-arraylist)
- [29. Advantages](#29-advantages)
- [30. Disadvantages](#30-disadvantages)
- [31. When to Use TreeSet](#31-when-to-use-treeset)
- [32. When Not to Use TreeSet](#32-when-not-to-use-treeset)
- [33. Quick Revision](#33-quick-revision)
- [34. Progress](#34-progress)

---

# 1. What is TreeSet?

`TreeSet` is a class in the `java.util` package that implements the `NavigableSet` interface.

It stores:

- Unique elements.
- Elements in sorted order.

Example:

```java
Set<Integer> numbers = new TreeSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);
```

Output:

```text
[10, 20, 30]
```

The elements are automatically maintained in sorted order.

---

# 2. Package

`TreeSet` belongs to:

```java
java.util
```

Import:

```java
import java.util.TreeSet;
```

Recommended:

```java
import java.util.Set;
import java.util.TreeSet;
```

---

# 3. Declaration

Basic declaration:

```java
Set<Integer> numbers = new TreeSet<>();
```

Or:

```java
TreeSet<Integer> numbers = new TreeSet<>();
```

Recommended:

```java
Set<Integer> numbers = new TreeSet<>();
```

If you need `TreeSet`-specific methods such as `floor()`, `ceiling()`, `higher()`, or `lower()`, you can use:

```java
NavigableSet<Integer> numbers = new TreeSet<>();
```

---

# 4. Basic Example

```java
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {

        Set<Integer> numbers = new TreeSet<>();

        numbers.add(50);
        numbers.add(20);
        numbers.add(40);
        numbers.add(10);
        numbers.add(30);

        System.out.println(numbers);
    }
}
```

Output:

```text
[10, 20, 30, 40, 50]
```

Notice that the insertion order is different from the output order.

---

# 5. Key Characteristics

| Feature | TreeSet |
|---|---|
| Implements | `NavigableSet` |
| Duplicates | Not allowed |
| Ordering | Sorted |
| Sorting basis | Natural ordering or `Comparator` |
| Index access | No |
| `null` | Generally not supported with natural ordering |
| Thread-safe | No |
| Typical `add()` | O(log n) |
| Typical `remove()` | O(log n) |
| Typical `contains()` | O(log n) |
| Underlying structure | Tree-based |

---

# 6. Why Use TreeSet?

Use `TreeSet` when you need:

```text
Unique elements
+
Sorted order
```

For example:

```java
Set<Integer> marks = new TreeSet<>();

marks.add(85);
marks.add(70);
marks.add(95);
marks.add(70);
```

Result:

```text
[70, 85, 95]
```

Duplicates are removed and the values remain sorted.

---

# 7. Unique Elements

Like every `Set`, `TreeSet` does not allow duplicate elements.

```java
Set<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

---

# 8. Sorted Order

`TreeSet` automatically maintains sorted order.

```java
Set<Integer> numbers = new TreeSet<>();

numbers.add(40);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30, 40]
```

This is different from:

```text
HashSet
```

and:

```text
LinkedHashSet
```

which have different ordering behavior.

---

# 9. Natural Ordering

When no `Comparator` is supplied, `TreeSet` uses the natural ordering of its elements.

For integers:

```java
Set<Integer> numbers = new TreeSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);
```

Result:

```text
[10, 20, 30]
```

For strings:

```java
Set<String> names = new TreeSet<>();

names.add("Charlie");
names.add("Alice");
names.add("Bob");

System.out.println(names);
```

Result:

```text
[Alice, Bob, Charlie]
```

The element type must support the required comparison.

---

# 10. Comparator Ordering

You can provide a custom `Comparator`.

Example: descending order.

```java
Set<Integer> numbers =
    new TreeSet<>(Comparator.reverseOrder());

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

## Custom Comparator

```java
Comparator<String> byLength =
    Comparator.comparingInt(String::length);

Set<String> words = new TreeSet<>(byLength);

words.add("Java");
words.add("Spring");
words.add("API");

System.out.println(words);
```

The comparator determines how elements are ordered and can also affect which elements are considered duplicates by the set.

---

# 11. Adding Elements

Use:

```java
add()
```

Example:

```java
Set<Integer> numbers = new TreeSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);
```

The set automatically maintains:

```text
[10, 20, 30]
```

---

## add() Return Value

`add()` returns a boolean.

```java
Set<Integer> numbers = new TreeSet<>();

System.out.println(numbers.add(10));
System.out.println(numbers.add(10));
```

Output:

```text
true
false
```

The second `10` is not added.

---

# 12. Removing Elements

Use:

```java
remove()
```

Example:

```java
Set<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

numbers.remove(20);

System.out.println(numbers);
```

Output:

```text
[10, 30]
```

---

# 13. contains()

Use `contains()` to check membership.

```java
Set<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers.contains(20));
System.out.println(numbers.contains(50));
```

Output:

```text
true
false
```

Typical complexity:

```text
O(log n)
```

---

# 14. first() and last()

`TreeSet` provides methods for accessing the smallest and largest elements.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);
numbers.add(40);

System.out.println(numbers.first());
System.out.println(numbers.last());
```

Output:

```text
10
40
```

---

## first()

Returns the smallest element according to the set's ordering.

## last()

Returns the largest element according to the set's ordering.

---

# 15. Higher, Lower, Ceiling, and Floor

These are important `NavigableSet` methods.

Suppose:

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);
```

---

## lower()

Returns the greatest element strictly less than the given value.

```java
System.out.println(numbers.lower(30));
```

Output:

```text
20
```

---

## floor()

Returns the greatest element less than or equal to the given value.

```java
System.out.println(numbers.floor(30));
```

Output:

```text
30
```

---

## higher()

Returns the smallest element strictly greater than the given value.

```java
System.out.println(numbers.higher(30));
```

Output:

```text
40
```

---

## ceiling()

Returns the smallest element greater than or equal to the given value.

```java
System.out.println(numbers.ceiling(30));
```

Output:

```text
30
```

---

## Summary

For:

```text
10, 20, 30, 40
```

and target:

```text
30
```

| Method | Result |
|---|---:|
| `lower(30)` | 20 |
| `floor(30)` | 30 |
| `higher(30)` | 40 |
| `ceiling(30)` | 30 |

---

# 16. Iteration

## Enhanced for Loop

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);

for (Integer number : numbers) {
    System.out.println(number);
}
```

Output:

```text
10
20
30
```

---

## Descending Iteration

Use:

```java
descendingSet()
```

Example:

```java
for (Integer number : numbers.descendingSet()) {
    System.out.println(number);
}
```

Output:

```text
30
20
10
```

---

# 17. Null Elements

`TreeSet` with natural ordering generally does **not** permit `null`.

Example:

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(null);
```

This results in a `NullPointerException` with natural ordering.

### Why?

The tree needs to compare elements to maintain sorted order, and `null` has no natural ordering.

### Important

Do not treat `TreeSet` like `HashSet` or `LinkedHashSet` when it comes to `null`.

---

# 18. Internal Working

`TreeSet` is based on a tree structure.

The standard Java implementation is backed by a `TreeMap`.

Conceptually:

```text
TreeSet
   ↓
TreeMap
   ↓
Balanced search tree
```

The tree structure maintains elements according to their ordering.

Modern Java implementations use a red-black tree for the underlying `TreeMap`.

---

## Why a Tree?

A balanced search tree allows operations such as:

```text
add()
remove()
contains()
```

to be performed in:

```text
O(log n)
```

typical/worst-case for the tree operations.

---

# 19. TreeSet and TreeMap

`TreeSet` is a set.

`TreeMap` is a map.

### TreeSet

Stores:

```text
Elements
```

Example:

```java
TreeSet<Integer> numbers = new TreeSet<>();
```

### TreeMap

Stores:

```text
Key → Value
```

Example:

```java
TreeMap<Integer, String> students =
    new TreeMap<>();
```

The standard implementation of `TreeSet` is backed by a `TreeMap`.

Conceptually:

```text
TreeSet
   ↓
TreeMap
   ↓
element → PRESENT
```

---

# 20. Time Complexity

Typical complexity for a `TreeSet`:

| Operation | Complexity |
|---|---:|
| `add()` | O(log n) |
| `remove()` | O(log n) |
| `contains()` | O(log n) |
| `first()` | O(log n) |
| `last()` | O(log n) |
| `lower()` | O(log n) |
| `floor()` | O(log n) |
| `higher()` | O(log n) |
| `ceiling()` | O(log n) |
| `size()` | O(1) |

The main trade-off is:

```text
Sorted order
+
O(log n) operations
```

instead of the typical average O(1) basic operations of hash-based sets.

---

# 21. Constructors

## Default Constructor

```java
TreeSet<Integer> numbers = new TreeSet<>();
```

Uses natural ordering.

---

## Comparator Constructor

```java
TreeSet<Integer> numbers =
    new TreeSet<>(Comparator.reverseOrder());
```

Uses the supplied comparator.

---

## Collection Constructor

You can construct a `TreeSet` from another collection:

```java
List<Integer> numbers =
    Arrays.asList(40, 10, 30, 20);

TreeSet<Integer> sorted =
    new TreeSet<>(numbers);

System.out.println(sorted);
```

Output:

```text
[10, 20, 30, 40]
```

This is useful when you want to convert data into a sorted unique set.

---

# 22. Custom Objects

You can store custom objects in a `TreeSet`, but the set needs a way to compare them.

For example:

```java
class Student {

    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

This alone is not enough for natural ordering.

You can make the class implement `Comparable<Student>`:

```java
class Student implements Comparable<Student> {

    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }
}
```

Now:

```java
TreeSet<Student> students = new TreeSet<>();

students.add(new Student(3, "C"));
students.add(new Student(1, "A"));
students.add(new Student(2, "B"));
```

The students are ordered by `id`.

---

# 23. Comparable and TreeSet

`Comparable` defines a class's natural ordering.

Example:

```java
class Student implements Comparable<Student> {

    int id;

    Student(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }
}
```

Then:

```java
TreeSet<Student> students = new TreeSet<>();
```

uses:

```java
compareTo()
```

to determine ordering.

---

## Important

For a `TreeSet`, comparison is also used to determine whether elements are considered equivalent for set purposes.

If:

```java
compareTo() == 0
```

the `TreeSet` treats the elements as duplicates according to its ordering.

Therefore, the ordering should generally be consistent with `equals()` when possible.

---

# 24. Comparator and TreeSet

Instead of implementing `Comparable`, you can provide a `Comparator`.

Example:

```java
class Student {

    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

Sort by name:

```java
TreeSet<Student> students =
    new TreeSet<>(
        Comparator.comparing(student -> student.name)
    );
```

Now the set uses the comparator for ordering.

---

## Multiple Sorting Rules

You can build more complex comparators:

```java
Comparator<Student> comparator =
    Comparator.comparing((Student s) -> s.name)
              .thenComparingInt(s -> s.id);

TreeSet<Student> students =
    new TreeSet<>(comparator);
```

This sorts by:

1. Name.
2. Then ID if names are equal.

---

# 25. Important NavigableSet Methods

`TreeSet` implements `NavigableSet`.

Important methods include:

| Method | Meaning |
|---|---|
| `first()` | Smallest element |
| `last()` | Largest element |
| `lower(e)` | Greatest element `< e` |
| `floor(e)` | Greatest element `<= e` |
| `higher(e)` | Smallest element `> e` |
| `ceiling(e)` | Smallest element `>= e` |
| `pollFirst()` | Remove and return smallest |
| `pollLast()` | Remove and return largest |
| `descendingSet()` | Reverse-order view |
| `headSet()` | Elements below a boundary |
| `tailSet()` | Elements from a boundary |
| `subSet()` | Elements within a range |

---

## pollFirst()

```java
TreeSet<Integer> numbers =
    new TreeSet<>(
        Arrays.asList(10, 20, 30)
    );

System.out.println(numbers.pollFirst());
System.out.println(numbers);
```

Output:

```text
10
[20, 30]
```

---

## pollLast()

```java
System.out.println(numbers.pollLast());
```

Removes and returns the largest element.

---

# 26. TreeSet vs HashSet

| Feature | HashSet | TreeSet |
|---|---|---|
| Unique elements | Yes | Yes |
| Ordering | No guarantee | Sorted |
| Typical basic operations | O(1) average | O(log n) |
| Hashing | Yes | No |
| Comparison | No | Yes |
| `null` | One allowed | Generally not with natural ordering |
| Best for | Fast membership | Sorted unique data |

### Rule

```text
HashSet
→ Unique + fast average lookup

TreeSet
→ Unique + sorted order
```

---

# 27. TreeSet vs LinkedHashSet

| Feature | LinkedHashSet | TreeSet |
|---|---|---|
| Unique | Yes | Yes |
| Ordering | Insertion order | Sorted order |
| Typical operations | O(1) average | O(log n) |
| Hash-based | Yes | No |
| Comparison-based | No | Yes |
| `null` | One allowed | Generally not with natural ordering |

### Example

Insert:

```text
30
10
20
```

`LinkedHashSet`:

```text
[30, 10, 20]
```

`TreeSet`:

```text
[10, 20, 30]
```

---

# 28. TreeSet vs ArrayList

| Feature | TreeSet | ArrayList |
|---|---|---|
| Duplicates | No | Yes |
| Sorted automatically | Yes | No |
| Index access | No | Yes |
| `contains()` | O(log n) | O(n) |
| Typical insertion | O(log n) | O(1) amortized at end |
| Best for | Sorted unique elements | Ordered indexed sequence |

---

# 29. Advantages

## 1. Automatically sorted

No separate sorting step is required to maintain the set's order.

## 2. Unique elements

Duplicates are not stored.

## 3. Navigational operations

Provides:

```text
lower()
floor()
higher()
ceiling()
```

and more.

## 4. Range operations

You can efficiently work with portions of the sorted set.

## 5. Predictable ordering

Iteration follows the set's ordering.

---

# 30. Disadvantages

## 1. Slower basic operations than HashSet

Typical:

```text
HashSet    → O(1) average
TreeSet    → O(log n)
```

## 2. Requires comparison

Elements must be comparable through natural ordering or a supplied comparator.

## 3. No index access

You cannot use:

```java
set.get(0);
```

## 4. Additional tree overhead

Tree-based structures require additional node/link information.

## 5. Null limitations

Natural-ordering `TreeSet` generally cannot handle `null`.

---

# 31. When to Use TreeSet

Use `TreeSet` when you need:

### Unique + sorted values

```java
Set<Integer> numbers = new TreeSet<>();
```

### Range queries

For example:

```text
All scores between 50 and 80
```

### Nearest-value operations

For example:

```text
floor()
ceiling()
lower()
higher()
```

### Automatically maintained sorted data

You do not want to repeatedly sort a collection after modifications.

---

# 32. When Not to Use TreeSet

Do not use `TreeSet` when:

### You only need uniqueness

Use:

```text
HashSet
```

if ordering is unnecessary.

### You need insertion order

Use:

```text
LinkedHashSet
```

### You need duplicates

Use:

```text
List
```

### You need index access

Use:

```text
List
```

### Elements do not have a meaningful ordering

A `TreeSet` requires natural ordering or a suitable comparator.

---

# 33. Quick Revision

```text
TreeSet
│
├── java.util
├── Implements NavigableSet
├── Unique elements
├── Sorted order
├── No index access
├── Not thread-safe
│
├── Natural ordering
│      └── Comparable
│
├── Custom ordering
│      └── Comparator
│
├── Typical operations
│      ├── add()      → O(log n)
│      ├── remove()   → O(log n)
│      └── contains() → O(log n)
│
├── Navigation
│      ├── lower()
│      ├── floor()
│      ├── higher()
│      └── ceiling()
│
├── first()
├── last()
├── pollFirst()
├── pollLast()
│
├── Backed by TreeMap
│
└── Best for:
       Unique elements
       +
       Sorted order
       +
       Navigation / range operations
```

---

# 34. Progress

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
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
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

**`15-TreeSet/NOTES.md` is complete. Next: `15-TreeSet/PRACTICE.md`.**
