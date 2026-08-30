# Vector in Java

> `Vector` is a legacy, synchronized, dynamically growing array implementation of the `List` interface. It is important mainly for understanding Java's older collection classes, synchronization, capacity growth, and how it differs from `ArrayList`.

---

## Table of Contents

- [1. Introduction](#1-introduction)
- [2. What is Vector?](#2-what-is-vector)
- [3. Vector Hierarchy](#3-vector-hierarchy)
- [4. Why is Vector Called a Dynamic Array?](#4-why-is-vector-called-a-dynamic-array)
- [5. Internal Structure](#5-internal-structure)
- [6. Creating a Vector](#6-creating-a-vector)
- [7. Vector Constructors](#7-vector-constructors)
- [8. Adding Elements](#8-adding-elements)
- [9. Accessing Elements](#9-accessing-elements)
- [10. Updating Elements](#10-updating-elements)
- [11. Removing Elements](#11-removing-elements)
- [12. Searching](#12-searching)
- [13. Size and Capacity](#13-size-and-capacity)
- [14. Capacity Increment](#14-capacity-increment)
- [15. ensureCapacity](#15-ensurecapacity)
- [16. trimToSize](#16-trimtosize)
- [17. Enumeration](#17-enumeration)
- [18. Iterator](#18-iterator)
- [19. Vector and Thread Safety](#19-vector-and-thread-safety)
- [20. Synchronization](#20-synchronization)
- [21. Vector vs ArrayList](#21-vector-vs-arraylist)
- [22. Vector vs CopyOnWriteArrayList](#22-vector-vs-copyonwritearraylist)
- [23. Vector vs LinkedList](#23-vector-vs-linkedlist)
- [24. Performance](#24-performance)
- [25. Null and Duplicate Handling](#25-null-and-duplicate-handling)
- [26. Legacy Methods](#26-legacy-methods)
- [27. When Should You Use Vector?](#27-when-should-you-use-vector)
- [28. Common Mistakes](#28-common-mistakes)
- [29. Quick Revision](#29-quick-revision)
- [30. Final Mental Model](#30-final-mental-model)

---

# 1. Introduction

`Vector` is one of Java's older collection classes.

It was introduced before the modern Java Collections Framework.

Later, `Vector` was integrated into the Collections Framework by making it implement the `List` interface.

So:

```text
Vector
    |
    +-- List
    |
    +-- Collection
    |
    +-- Iterable
```

The most important thing to remember is:

> **Vector is a synchronized, dynamically growing array.**

---

# 2. What is Vector?

`Vector` is a resizable-array implementation of the `List` interface.

It provides:

- Index-based access
- Dynamic resizing
- Insertion-order preservation
- Duplicate elements
- `null` values
- Synchronized methods

Example:

```java
Vector<Integer> numbers =
        new Vector<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

---

# 3. Vector Hierarchy

The inheritance hierarchy is approximately:

```text
Object
   |
AbstractCollection
   |
Vector
```

Vector directly extends:

```java
AbstractList
```

A simplified view:

```text
Object
   |
AbstractCollection
   |
AbstractList
   |
Vector
```

It implements important interfaces such as:

```text
List
RandomAccess
Cloneable
Serializable
```

---

# 4. Why is Vector Called a Dynamic Array?

A normal array has fixed length:

```java
int[] numbers = new int[5];
```

Once created, its length cannot increase.

`Vector` internally uses an array-like structure that can grow when required.

Conceptually:

```text
Initial:

[10][20][30][ ][ ]
```

When more elements are added:

```text
[10][20][30][40][50][ ]
```

If the storage becomes insufficient, Vector creates larger internal storage and copies the existing elements.

Therefore:

```text
Vector
    -> dynamically growing array
```

---

# 5. Internal Structure

Conceptually, Vector maintains:

```text
Object[] elementData
```

The exact JDK implementation details can vary, but the important idea is:

```text
Vector
   |
   v
Backing Array
   |
   +---- 10
   +---- 20
   +---- 30
   +---- 40
```

Because the elements are stored in an array-backed structure, Vector provides efficient index-based access.

Therefore:

```java
vector.get(index)
```

is generally:

```text
O(1)
```

---

# 6. Creating a Vector

## 6.1 Empty Vector

```java
Vector<Integer> numbers =
        new Vector<>();
```

---

## 6.2 Vector with Initial Capacity

```java
Vector<Integer> numbers =
        new Vector<>(100);
```

This means:

```text
initial capacity = 100
```

It does **not** mean:

```text
size = 100
```

Initially:

```text
size = 0
```

---

## 6.3 Vector with Capacity Increment

You can specify:

```java
new Vector<>(initialCapacity, capacityIncrement)
```

Example:

```java
Vector<Integer> numbers =
        new Vector<>(10, 5);
```

This specifies:

```text
initial capacity = 10
capacity increment = 5
```

When growth is required, the increment can influence how capacity grows.

---

## 6.4 Create from Collection

```java
List<Integer> source =
        List.of(10, 20, 30);

Vector<Integer> numbers =
        new Vector<>(source);
```

Result:

```text
[10, 20, 30]
```

---

# 7. Vector Constructors

The commonly used constructors are:

```java
Vector()
```

```java
Vector(int initialCapacity)
```

```java
Vector(int initialCapacity,
       int capacityIncrement)
```

```java
Vector(Collection<? extends E> c)
```

---

# 8. Adding Elements

The standard method is:

```java
add()
```

Example:

```java
Vector<Integer> numbers =
        new Vector<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
```

Result:

```text
[10, 20, 30]
```

---

## addElement()

Vector also provides the older method:

```java
addElement()
```

Example:

```java
numbers.addElement(40);
```

Result:

```text
[10, 20, 30, 40]
```

Modern code generally uses:

```java
add()
```

because it comes from the `List` API.

---

# 9. Accessing Elements

Use:

```java
get(index)
```

Example:

```java
Vector<String> languages =
        new Vector<>(
                List.of(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );

System.out.println(
        languages.get(1)
);
```

Output:

```text
Spring
```

Because Vector is array-backed:

```text
get(index) -> O(1)
```

---

## elementAt()

Vector also has:

```java
elementAt(index)
```

Example:

```java
System.out.println(
        languages.elementAt(1)
);
```

Output:

```text
Spring
```

This is a legacy-style Vector method.

Prefer:

```java
get(index)
```

in modern collection-oriented code.

---

# 10. Updating Elements

Use:

```java
set(index, value)
```

Example:

```java
Vector<String> languages =
        new Vector<>(
                List.of(
                        "Java",
                        "Python",
                        "SQL"
                )
        );

languages.set(1, "Spring");

System.out.println(languages);
```

Output:

```text
[Java, Spring, SQL]
```

---

## setElementAt()

Vector also provides:

```java
setElementAt(value, index)
```

Example:

```java
languages.setElementAt(
        "Hibernate",
        1
);
```

Modern code generally prefers:

```java
set(index, value)
```

---

# 11. Removing Elements

Vector supports:

```java
remove(index)
```

and:

```java
remove(Object)
```

Example:

```java
Vector<Integer> numbers =
        new Vector<>(
                List.of(
                        10, 20, 30, 40
                )
        );

numbers.remove(1);

System.out.println(numbers);
```

Output:

```text
[10, 30, 40]
```

---

## removeElement()

Vector also provides:

```java
removeElement(value)
```

Example:

```java
numbers.removeElement(30);
```

---

## removeElementAt()

To remove by index using the legacy API:

```java
numbers.removeElementAt(1);
```

Modern code generally uses:

```java
remove(index)
```

---

# 12. Searching

Vector provides normal `List` search methods:

```java
contains()
indexOf()
lastIndexOf()
```

Example:

```java
Vector<Integer> numbers =
        new Vector<>(
                List.of(
                        10, 20, 30,
                        20, 40
                )
        );

System.out.println(
        numbers.contains(20)
);
```

Output:

```text
true
```

---

## indexOf()

```java
numbers.indexOf(20);
```

Output:

```text
1
```

---

## lastIndexOf()

```java
numbers.lastIndexOf(20);
```

Output:

```text
3
```

Searching is generally:

```text
O(n)
```

---

# 13. Size and Capacity

This is an important interview concept.

Consider:

```java
Vector<Integer> vector =
        new Vector<>(100);
```

Initially:

```text
size     = 0
capacity = 100
```

After:

```java
vector.add(10);
vector.add(20);
```

we have:

```text
size     = 2
capacity = approximately 100
```

The exact capacity after growth depends on the implementation and constructor settings.

---

## size()

Returns the number of actual elements:

```java
vector.size();
```

---

## capacity()

Returns the current internal capacity:

```java
vector.capacity();
```

Example:

```java
Vector<Integer> vector =
        new Vector<>(100);

System.out.println(
        vector.size()
);

System.out.println(
        vector.capacity()
);
```

Conceptually:

```text
0
100
```

---

# 14. Capacity Increment

Vector has a feature that is historically important:

```java
capacityIncrement
```

Example:

```java
Vector<Integer> vector =
        new Vector<>(5, 3);
```

Conceptually:

```text
initial capacity = 5
increment        = 3
```

When the current capacity is insufficient, the Vector can increase its capacity based on this configured increment.

---

## Why Is This Important?

`ArrayList` and `Vector` both dynamically resize, but Vector provides an explicit capacity-increment constructor.

Example:

```java
new Vector<>(10, 5);
```

This is different from simply specifying:

```java
new Vector<>(10);
```

---

# 15. ensureCapacity

Vector provides:

```java
ensureCapacity()
```

Example:

```java
Vector<Integer> numbers =
        new Vector<>();

numbers.ensureCapacity(1000);
```

This requests enough internal capacity for approximately 1000 elements.

It does not add elements.

Therefore:

```java
numbers.size()
```

is still:

```text
0
```

---

# 16. trimToSize

Vector also provides:

```java
trimToSize()
```

Example:

```java
Vector<Integer> numbers =
        new Vector<>(1000);

numbers.add(10);
numbers.add(20);

numbers.trimToSize();
```

The method reduces excess capacity toward the current size.

Conceptually:

```text
Before:

size     = 2
capacity = 1000

After:

size     = 2
capacity ≈ 2
```

---

# 17. Enumeration

One reason Vector is considered a legacy class is that it provides:

```java
Enumeration
```

Example:

```java
Vector<String> languages =
        new Vector<>(
                List.of(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );

Enumeration<String> enumeration =
        languages.elements();

while (enumeration.hasMoreElements()) {

    System.out.println(
            enumeration.nextElement()
    );
}
```

Output:

```text
Java
Spring
SQL
```

---

## Enumeration vs Iterator

`Enumeration` is older.

Modern collections generally use:

```java
Iterator
```

instead.

---

# 18. Iterator

Vector also supports modern collection traversal.

```java
Iterator<Integer> iterator =
        vector.iterator();

while (iterator.hasNext()) {

    Integer value =
            iterator.next();

    System.out.println(value);
}
```

Because Vector implements `List`, it participates in the normal Collections Framework APIs.

---

# 19. Vector and Thread Safety

This is the most important characteristic of Vector.

`Vector` is synchronized by default.

Many of its methods are synchronized.

For example, conceptually:

```java
public synchronized boolean add(E e)
```

The synchronization helps protect individual method calls when multiple threads access the same Vector.

---

## Important Nuance

Do not conclude:

> Vector makes every multi-step operation automatically thread-safe.

That is too broad.

Consider:

```java
if (!vector.contains(10)) {
    vector.add(10);
}
```

Even if individual methods are synchronized, the whole sequence is not automatically atomic.

Another thread could modify the Vector between:

```java
contains()
```

and:

```java
add()
```

So synchronization of individual methods does not automatically make arbitrary compound operations atomic.

---

# 20. Synchronization

Vector's synchronization was one of its original design goals.

Conceptually:

```text
Thread A
   |
   v
Vector
   |
 synchronized methods
   |
Thread B
```

Only one synchronized method invocation can hold the Vector's intrinsic monitor at a time for that object.

This can provide thread-safety for individual operations.

---

## But There Is a Cost

Synchronization introduces overhead.

For applications that do not need synchronization:

```text
ArrayList
```

is generally preferred.

For concurrent applications:

```text
Vector
```

is not automatically the best choice.

Modern concurrent collections often provide better semantics for specific workloads.

---

# 21. Vector vs ArrayList

This is one of the most important Vector interview questions.

| Feature | ArrayList | Vector |
|---|---|---|
| Internal structure | Dynamic array | Dynamic array |
| Implements `List` | Yes | Yes |
| Random access | O(1) | O(1) |
| Dynamic resizing | Yes | Yes |
| Synchronized by default | No | Yes |
| Legacy | No | Yes |
| Performance | Usually better for single-threaded use | Synchronization overhead |
| Modern default | Yes | No |

---

## Which Should You Choose?

For normal list usage:

```java
ArrayList
```

is generally preferred.

Why?

Because it does not synchronize every operation by default.

---

# 22. Vector vs CopyOnWriteArrayList

Both can be used in concurrent environments, but their designs are very different.

| Feature | Vector | CopyOnWriteArrayList |
|---|---|---|
| Synchronization approach | Synchronized methods | Copy-on-write |
| Reads | Synchronized methods | Generally no locking for reads |
| Writes | Synchronized | Copies underlying array |
| Best for | Legacy synchronized list use | Read-heavy concurrent workloads |
| Write cost | Lower than copy-on-write | Potentially expensive |
| Iterators | Fail-fast style | Snapshot-style |
| Null | Allowed | Allowed |

---

## Important

Do not use:

```text
CopyOnWriteArrayList
```

simply because it is "thread-safe."

It is especially useful when:

```text
Reads >> Writes
```

because every mutation may require copying the underlying array.

---

# 23. Vector vs LinkedList

| Feature | Vector | LinkedList |
|---|---|---|
| Structure | Dynamic array | Doubly linked list |
| `get(index)` | O(1) | O(n) |
| Add end | O(1) amortized | O(1) |
| Add first | O(n) | O(1) |
| Remove first | O(n) | O(1) |
| Search | O(n) | O(n) |
| Thread-safe by default | Yes | No |
| Memory overhead | Lower | Higher |
| Implements `Deque` | No | Yes |

---

# 24. Performance

## Random Access

Because Vector is array-backed:

```java
vector.get(index)
```

is:

```text
O(1)
```

---

## Append

Adding to the end is generally:

```text
O(1) amortized
```

but resizing can temporarily cost:

```text
O(n)
```

---

## Insertion

Insertion at an arbitrary index requires shifting elements:

```text
O(n)
```

---

## Removal

Removal from the middle also requires shifting elements:

```text
O(n)
```

---

## Search

Searching requires scanning elements:

```text
O(n)
```

---

# 25. Null and Duplicate Handling

## Null

Vector allows:

```java
null
```

Example:

```java
Vector<String> names =
        new Vector<>();

names.add(null);
```

Valid.

---

## Duplicates

Vector allows duplicate elements.

```java
vector.add("Java");
vector.add("Java");
```

Result:

```text
[Java, Java]
```

---

## Ordering

Vector maintains insertion order.

```java
vector.add("C");
vector.add("A");
vector.add("B");
```

Result:

```text
[C, A, B]
```

---

# 26. Legacy Methods

Vector contains many older method names.

Examples:

```java
addElement()
elementAt()
setElementAt()
removeElement()
removeElementAt()
insertElementAt()
elements()
```

Modern Collection APIs provide equivalents.

| Legacy Vector Method | Modern Preferred Method |
|---|---|
| `addElement(e)` | `add(e)` |
| `elementAt(i)` | `get(i)` |
| `setElementAt(e, i)` | `set(i, e)` |
| `removeElement(e)` | `remove(e)` |
| `removeElementAt(i)` | `remove(i)` |
| `insertElementAt(e, i)` | `add(i, e)` |
| `elements()` | `iterator()` |

---

# 27. When Should You Use Vector?

In modern Java development:

> **Usually, you should not choose Vector for new code unless there is a specific reason.**

For a normal list:

```java
ArrayList
```

is usually preferred.

For a concurrent read-heavy list:

```java
CopyOnWriteArrayList
```

may be appropriate.

For queue/deque behavior:

```java
ArrayDeque
```

is often preferred.

---

## When Might You Encounter Vector?

You may encounter it when:

- Maintaining legacy Java applications
- Working with old APIs
- Reading older interview questions
- Maintaining existing code
- Working with APIs specifically expecting Vector

Therefore, knowing Vector is still important for Java interviews.

---

# 28. Common Mistakes

## Mistake 1: Saying Vector Is Not a List

Wrong.

Vector implements:

```java
List
```

---

## Mistake 2: Saying Vector Is Faster Than ArrayList

Generally wrong.

Vector synchronizes many methods, which can add overhead.

For normal single-threaded list usage:

```text
ArrayList
```

is generally preferred.

---

## Mistake 3: Saying Vector Is Completely Thread-Safe

Too broad.

Individual synchronized methods do not automatically make multi-step operations atomic.

---

## Mistake 4: Saying Vector Uses Linked Nodes

Wrong.

Vector is array-backed.

```text
Vector
    -> dynamic array
```

---

## Mistake 5: Saying Vector Has No Capacity

Wrong.

Vector has:

```java
capacity()
```

and:

```java
ensureCapacity()
trimToSize()
```

---

## Mistake 6: Confusing Size and Capacity

This:

```java
new Vector<>(100)
```

does not create 100 elements.

It creates:

```text
size = 0
capacity = 100
```

---

## Mistake 7: Using Vector for Every Concurrent List

Wrong.

Modern concurrent collections are usually better suited to specific concurrency requirements.

---

# 29. Quick Revision

| Concept | Key Point |
|---|---|
| Vector | Legacy dynamic array |
| Package | `java.util` |
| Implements | `List` |
| Internal structure | Array-backed |
| Random access | Fast |
| `get(index)` | O(1) |
| Append | O(1) amortized |
| Indexed insertion | O(n) |
| Indexed removal | O(n) |
| Search | O(n) |
| Duplicates | Allowed |
| `null` | Allowed |
| Insertion order | Preserved |
| Synchronized | Yes, by default |
| Legacy | Yes |
| Modern default list | No |
| Capacity available | Yes |
| `Enumeration` | Supported |
| `Iterator` | Supported |

---

# 30. Final Mental Model

Think of Vector like this:

```text
                 Vector
                    |
                    v
             Dynamic Array
                    |
          +---------+---------+
          |         |         |
          v         v         v
        [10]      [20]      [30]
```

It behaves similarly to:

```text
ArrayList
```

but with an important historical difference:

```text
Vector
    -> synchronized by default

ArrayList
    -> not synchronized by default
```

---

## Vector vs ArrayList

```text
                List
                 |
          +------+------+
          |             |
          v             v
      ArrayList       Vector
          |             |
          v             v
   Dynamic Array    Dynamic Array
          |             |
      Not sync       Synchronized
          |             |
       Modern          Legacy
       default
```

---

# Most Important Interview Statement

> **Vector is a legacy, synchronized, dynamically growing array implementation of the List interface. It provides O(1) index-based access and O(1) amortized append, while insertion and removal at arbitrary positions are generally O(n) because elements need to be shifted. Unlike ArrayList, Vector synchronizes its methods by default, which can introduce synchronization overhead. For new code, ArrayList is generally preferred for ordinary list usage, while modern concurrent collections should be considered when concurrency is actually required.**

---

# Collections Framework Progress

```text
Java Collections Framework
│
├── 01-Iterable
│   ├── NOTES.md
│   ├── PRACTICE.md
│   └── INTERVIEW.md
│
├── 02-Collection-Interface
│   ├── NOTES.md
│   ├── PRACTICE.md
│   └── INTERVIEW.md
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
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
│
├── 07-Stack
├── 08-Queue
├── 09-PriorityQueue
├── 10-Deque
├── 11-ArrayDeque
├── 12-Set
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

---

# Vector Completion

```text
06-Vector/
├── NOTES.md       [x]
├── PRACTICE.md    [ ]
└── INTERVIEW.md   [ ]
```

> **Next: `06-Vector/PRACTICE.md`**
