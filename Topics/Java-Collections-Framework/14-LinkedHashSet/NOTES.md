# LinkedHashSet — Notes

> `LinkedHashSet` is a `Set` implementation that stores unique elements while maintaining insertion order.

---

## Table of Contents

- [1. What is LinkedHashSet?](#1-what-is-linkedhashset)
- [2. Package](#2-package)
- [3. Declaration](#3-declaration)
- [4. Basic Example](#4-basic-example)
- [5. Key Characteristics](#5-key-characteristics)
- [6. Why Use LinkedHashSet?](#6-why-use-linkedhashset)
- [7. Duplicate Elements](#7-duplicate-elements)
- [8. Insertion Order](#8-insertion-order)
- [9. Null Elements](#9-null-elements)
- [10. add() Method](#10-add-method)
- [11. remove() Method](#11-remove-method)
- [12. contains() Method](#12-contains-method)
- [13. Iterating Over LinkedHashSet](#13-iterating-over-linkedhashset)
- [14. Internal Working](#14-internal-working)
- [15. LinkedHashSet and HashSet](#15-linkedhashset-and-hashset)
- [16. LinkedHashSet and LinkedHashMap](#16-linkedhashset-and-linkedhashmap)
- [17. Time Complexity](#17-time-complexity)
- [18. Constructors](#18-constructors)
- [19. LinkedHashSet with Custom Objects](#19-linkedhashset-with-custom-objects)
- [20. Important Rules](#20-important-rules)
- [21. HashSet vs LinkedHashSet vs TreeSet](#21-hashset-vs-linkedhashset-vs-treeset)
- [22. When to Use LinkedHashSet](#22-when-to-use-linkedhashset)
- [23. When Not to Use LinkedHashSet](#23-when-not-to-use-linkedhashset)
- [24. Advantages](#24-advantages)
- [25. Disadvantages](#25-disadvantages)
- [26. Quick Revision](#26-quick-revision)
- [27. Progress](#27-progress)

---

# 1. What is LinkedHashSet?

`LinkedHashSet` is a class in the Java Collections Framework that implements the `Set` interface.

It combines two important properties:

```text
Uniqueness
    +
Insertion Order
```

Example:

```java
Set<String> languages = new LinkedHashSet<>();

languages.add("Java");
languages.add("Python");
languages.add("C++");
```

Iteration produces:

```text
Java
Python
C++
```

The insertion order is maintained.

---

# 2. Package

`LinkedHashSet` belongs to:

```java
java.util
```

Import it using:

```java
import java.util.LinkedHashSet;
```

Usually, prefer:

```java
import java.util.Set;
import java.util.LinkedHashSet;
```

and declare:

```java
Set<String> set = new LinkedHashSet<>();
```

---

# 3. Declaration

Basic declaration:

```java
Set<Integer> numbers = new LinkedHashSet<>();
```

Or:

```java
LinkedHashSet<Integer> numbers = new LinkedHashSet<>();
```

### Recommended

```java
Set<Integer> numbers = new LinkedHashSet<>();
```

Programming to the interface makes it easier to change the implementation later.

---

# 4. Basic Example

```java
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<Integer> numbers = new LinkedHashSet<>();

        numbers.add(30);
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println(numbers);
    }
}
```

Output:

```text
[30, 10, 20]
```

Why?

- `30` is inserted first.
- `10` is inserted second.
- `20` is inserted third.
- The second `30` is a duplicate and is ignored.

---

# 5. Key Characteristics

| Feature | LinkedHashSet |
|---|---|
| Implements | `Set` |
| Duplicate elements | Not allowed |
| Insertion order | Preserved |
| Sorted order | No |
| `null` | One `null` allowed |
| Index access | No |
| Thread-safe | No |
| Hash-based | Yes |
| Typical `add()` | O(1) average |
| Typical `remove()` | O(1) average |
| Typical `contains()` | O(1) average |

---

# 6. Why Use LinkedHashSet?

Suppose you have:

```java
int[] numbers = {
    10, 20, 10, 30, 20, 40
};
```

You want:

1. Duplicate values removed.
2. Original order preserved.

A `LinkedHashSet` is a natural choice.

```java
Set<Integer> uniqueNumbers = new LinkedHashSet<>();

for (int number : numbers) {
    uniqueNumbers.add(number);
}

System.out.println(uniqueNumbers);
```

Output:

```text
[10, 20, 30, 40]
```

This is one of the most common practical uses of `LinkedHashSet`.

---

# 7. Duplicate Elements

`LinkedHashSet` does not allow duplicate elements.

```java
Set<String> names = new LinkedHashSet<>();

names.add("Amit");
names.add("Rahul");
names.add("Amit");
names.add("Priya");
names.add("Rahul");

System.out.println(names);
```

Output:

```text
[Amit, Rahul, Priya]
```

The duplicate values are ignored.

---

## Important

`LinkedHashSet` provides:

```text
Unique elements
+
Insertion order
```

It does **not** provide:

```text
Sorted elements
```

For sorted unique elements, use `TreeSet`.

---

# 8. Insertion Order

The main difference between `HashSet` and `LinkedHashSet` is ordering.

Consider:

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(50);
set.add(10);
set.add(30);
set.add(20);
```

Iteration order is:

```text
50
10
30
20
```

because that is the insertion order.

---

## Duplicate Insertion

Consider:

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(10);
set.add(20);
set.add(30);
set.add(20);
```

The result is:

```text
[10, 20, 30]
```

Adding `20` again does not move it to the end.

The original insertion position is retained.

---

# 9. Null Elements

`LinkedHashSet` permits one `null` element.

```java
Set<String> set = new LinkedHashSet<>();

set.add("Java");
set.add(null);
set.add("Python");
set.add(null);

System.out.println(set);
```

Output:

```text
[Java, null, Python]
```

Only one `null` is stored.

The `null` also has an insertion position.

---

# 10. add() Method

The `add()` method inserts an element if it is not already present.

It returns:

```java
boolean
```

Example:

```java
Set<Integer> numbers = new LinkedHashSet<>();

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

### Meaning

| Return value | Meaning |
|---|---|
| `true` | Set changed |
| `false` | Element already existed |

---

## Practical Duplicate Detection

```java
Set<String> names = new LinkedHashSet<>();

if (!names.add("Amit")) {
    System.out.println("Duplicate");
}
```

---

# 11. remove() Method

Use `remove()` to remove an element.

```java
Set<String> names = new LinkedHashSet<>();

names.add("Amit");
names.add("Rahul");
names.add("Priya");

names.remove("Rahul");

System.out.println(names);
```

Output:

```text
[Amit, Priya]
```

`remove()` returns:

```java
boolean
```

Example:

```java
System.out.println(names.remove("Amit"));
System.out.println(names.remove("Amit"));
```

Output:

```text
true
false
```

---

# 12. contains() Method

Use `contains()` to check whether an element exists.

```java
Set<Integer> numbers = new LinkedHashSet<>();

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

Typical average-case complexity:

```text
O(1)
```

---

# 13. Iterating Over LinkedHashSet

## Enhanced for Loop

```java
Set<String> languages = new LinkedHashSet<>();

languages.add("Java");
languages.add("Python");
languages.add("C++");

for (String language : languages) {
    System.out.println(language);
}
```

Output:

```text
Java
Python
C++
```

The insertion order is maintained during iteration.

---

## Iterator

```java
Set<Integer> numbers = new LinkedHashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

Output:

```text
10
20
30
```

---

# 14. Internal Working

`LinkedHashSet` is a hash-table-based `Set` implementation with a linked structure that maintains insertion order.

Conceptually:

```text
LinkedHashSet
      │
      ├── Hash-based storage
      │
      └── Linked structure
             │
             └── Maintains insertion order
```

The hash-based part provides efficient average-case lookup.

The linked structure maintains the order in which elements were inserted.

---

## Relationship With LinkedHashMap

The standard Java implementation of `LinkedHashSet` is built on top of `LinkedHashMap`.

Conceptually:

```text
LinkedHashSet
      ↓
LinkedHashMap
      ↓
Hash table + linked ordering
```

The set elements act like keys, while a shared dummy value is used internally.

You normally do not need to interact with this implementation detail directly.

---

# 15. LinkedHashSet and HashSet

Both store unique elements.

The major difference is ordering.

## HashSet

```java
Set<Integer> set = new HashSet<>();

set.add(30);
set.add(10);
set.add(20);
```

Iteration order:

```text
Not guaranteed
```

## LinkedHashSet

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(30);
set.add(10);
set.add(20);
```

Iteration order:

```text
30
10
20
```

---

## Comparison

| Feature | HashSet | LinkedHashSet |
|---|---|---|
| Unique elements | Yes | Yes |
| Insertion order | Not guaranteed | Preserved |
| Hash-based | Yes | Yes |
| Typical lookup | O(1) average | O(1) average |
| Memory overhead | Lower | Higher |

### Rule

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

# 16. LinkedHashSet and LinkedHashMap

These are different collection types.

### LinkedHashSet

Stores:

```text
Elements
```

Example:

```java
Set<String> names = new LinkedHashSet<>();
```

### LinkedHashMap

Stores:

```text
Key → Value
```

Example:

```java
Map<Integer, String> students = new LinkedHashMap<>();
```

Both maintain insertion order, but they represent different abstractions.

---

# 17. Time Complexity

Typical average-case complexity:

| Operation | Complexity |
|---|---:|
| `add()` | O(1) |
| `remove()` | O(1) |
| `contains()` | O(1) |
| `size()` | O(1) |
| `isEmpty()` | O(1) |
| `clear()` | O(n) |

These are typical average-case expectations.

They are not absolute worst-case guarantees.

---

# 18. Constructors

## Default Constructor

```java
Set<Integer> numbers = new LinkedHashSet<>();
```

---

## Initial Capacity

```java
Set<Integer> numbers = new LinkedHashSet<>(32);
```

The initial capacity is not a maximum size.

The set can grow beyond this size.

---

## Initial Capacity and Load Factor

You can also specify both:

```java
Set<Integer> numbers =
    new LinkedHashSet<>(32, 0.75f);
```

The commonly used default load factor is:

```text
0.75
```

---

## Collection Constructor

You can create a `LinkedHashSet` from another collection:

```java
List<Integer> numbers =
    Arrays.asList(10, 20, 10, 30, 20);

Set<Integer> uniqueNumbers =
    new LinkedHashSet<>(numbers);
```

Result:

```text
[10, 20, 30]
```

This is particularly useful for removing duplicates while preserving the original iteration order of the source collection.

---

# 19. LinkedHashSet with Custom Objects

`LinkedHashSet` can store custom objects.

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

Then:

```java
Set<Student> students = new LinkedHashSet<>();
```

However, if logical equality is based on fields such as `id`, the class should implement `equals()` and `hashCode()` consistently.

Example:

```java
import java.util.Objects;

class Student {

    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Student)) {
            return false;
        }

        Student other = (Student) obj;

        return id == other.id &&
               Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
```

Now duplicate logical students can be recognized correctly.

---

# 20. Important Rules

Remember these rules:

### Rule 1

`LinkedHashSet` does not allow duplicate elements.

### Rule 2

`LinkedHashSet` maintains insertion order.

### Rule 3

`LinkedHashSet` allows one `null`.

### Rule 4

`LinkedHashSet` does not provide index-based access.

### Rule 5

`LinkedHashSet` is not thread-safe.

### Rule 6

Average-case `add()`, `remove()`, and `contains()` are O(1).

### Rule 7

For custom objects, `equals()` and `hashCode()` must be consistent.

### Rule 8

Do not mutate equality/hash-related fields while an object is stored in the set.

### Rule 9

Insertion order is not the same as sorted order.

---

# 21. HashSet vs LinkedHashSet vs TreeSet

This is one of the most important comparisons.

| Feature | HashSet | LinkedHashSet | TreeSet |
|---|---|---|---|
| Unique elements | Yes | Yes | Yes |
| Insertion order | No guarantee | Yes | No |
| Sorted order | No | No | Yes |
| Typical basic operations | O(1) average | O(1) average | O(log n) |
| Hashing | Yes | Yes | No |
| Comparison-based | No | No | Yes |
| Memory overhead | Lower | Higher | Different structure |
| Best use | Fast uniqueness | Uniqueness + insertion order | Sorted uniqueness |

---

## Easy Rule to Remember

```text
HashSet
→ Unique

LinkedHashSet
→ Unique + Insertion Order

TreeSet
→ Unique + Sorted Order
```

---

# 22. When to Use LinkedHashSet

Use `LinkedHashSet` when you need:

### 1. Unique elements

```text
No duplicates
```

### 2. Insertion order

```text
First inserted → First during iteration
```

### 3. Fast average-case membership

```text
contains() → O(1) average
```

---

## Example

Remove duplicates from a list while preserving order:

```java
List<String> names =
    Arrays.asList(
        "Amit",
        "Rahul",
        "Amit",
        "Priya",
        "Rahul"
    );

Set<String> uniqueNames =
    new LinkedHashSet<>(names);

System.out.println(uniqueNames);
```

Output:

```text
[Amit, Rahul, Priya]
```

This is an excellent use case.

---

# 23. When Not to Use LinkedHashSet

Do not choose `LinkedHashSet` when:

### You need duplicate elements

Use:

```text
List
```

instead.

### You need sorted elements

Use:

```text
TreeSet
```

instead.

### You need index-based access

Use:

```text
List
```

instead.

### You need key-value pairs

Use:

```text
Map
```

instead.

### You need concurrent set operations

Use an appropriate concurrent collection or synchronization strategy.

---

# 24. Advantages

## 1. Prevents duplicates

```text
Unique elements
```

## 2. Maintains insertion order

```text
Predictable iteration order
```

## 3. Fast average-case lookup

```text
contains() → O(1) average
```

## 4. Easy duplicate removal

```java
new LinkedHashSet<>(collection)
```

## 5. Useful for ordered unique data

Especially when output order matters.

---

# 25. Disadvantages

## 1. More memory than HashSet

The linked structure used to maintain order requires additional memory.

## 2. Slightly more overhead

Maintaining insertion order adds overhead compared with a plain `HashSet`.

## 3. No sorting

If you need sorted elements, use `TreeSet`.

## 4. No index access

You cannot do:

```java
set.get(0);
```

---

# 26. Quick Revision

```text
LinkedHashSet
│
├── java.util
│
├── Implements Set
│
├── Unique elements
│
├── Preserves insertion order
│
├── One null allowed
│
├── No index access
│
├── Not thread-safe
│
├── Hash-based
│
├── Built on LinkedHashMap internally
│
├── add()      → O(1) average
├── remove()   → O(1) average
├── contains() → O(1) average
│
├── Best for:
│      Unique elements
│      +
│      Insertion order
│
└── Compare:
       HashSet       → Unique
       LinkedHashSet → Unique + Insertion Order
       TreeSet       → Unique + Sorted
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
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 14-LinkedHashSet
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
│
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

**`14-LinkedHashSet/NOTES.md` is complete. Next: `14-LinkedHashSet/PRACTICE.md`.**
