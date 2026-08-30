# Vector - Interview Questions

> Interview-focused revision of `Vector`: internal structure, capacity, synchronization, legacy methods, complexity, and comparisons with modern collection classes.

---

## Table of Contents

- [1. What is Vector?](#1-what-is-vector)
- [2. Why is Vector Called a Dynamic Array?](#2-why-is-vector-called-a-dynamic-array)
- [3. Vector Hierarchy](#3-vector-hierarchy)
- [4. Internal Structure](#4-internal-structure)
- [5. Size vs Capacity](#5-size-vs-capacity)
- [6. Initial Capacity](#6-initial-capacity)
- [7. Capacity Growth](#7-capacity-growth)
- [8. Capacity Increment](#8-capacity-increment)
- [9. ensureCapacity](#9-ensurecapacity)
- [10. trimToSize](#10-trimtosize)
- [11. Time Complexity](#11-time-complexity)
- [12. Is Vector Thread-Safe?](#12-is-vector-thread-safe)
- [13. What Does Synchronized Mean?](#13-what-does-synchronized-mean)
- [14. Is Vector Completely Thread-Safe?](#14-is-vector-completely-thread-safe)
- [15. Vector vs ArrayList](#15-vector-vs-arraylist)
- [16. Vector vs LinkedList](#16-vector-vs-linkedlist)
- [17. Vector vs CopyOnWriteArrayList](#17-vector-vs-copyonwritearraylist)
- [18. Legacy Methods](#18-legacy-methods)
- [19. Enumeration](#19-enumeration)
- [20. Iterator](#20-iterator)
- [21. Null and Duplicates](#21-null-and-duplicates)
- [22. Common Traps](#22-common-traps)
- [23. Tricky Output Questions](#23-tricky-output-questions)
- [24. Scenario-Based Questions](#24-scenario-based-questions)
- [25. Rapid-Fire Questions](#25-rapid-fire-questions)
- [26. Must-Know Questions](#26-must-know-questions)
- [27. Final Interview Answer](#27-final-interview-answer)
- [28. Final Checklist](#28-final-checklist)

---

# 1. What is Vector?

## Question

What is `Vector` in Java?

## Answer

`Vector` is a legacy, synchronized, dynamically growing array implementation of the `List` interface.

It stores elements in an array-backed structure and provides efficient index-based access.

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

# 2. Why is Vector Called a Dynamic Array?

A normal Java array has fixed size:

```java
int[] numbers = new int[5];
```

Its length cannot change after creation.

Vector can automatically grow when its current capacity is insufficient.

Conceptually:

```text
Initial:

[10][20][30][ ][ ]
```

After growth:

```text
[10][20][30][40][50][ ][ ][ ]
```

Therefore:

```text
Vector
    =
Resizable / Dynamic Array
```

---

# 3. Vector Hierarchy

A simplified hierarchy is:

```text
Object
   |
AbstractCollection
   |
AbstractList
   |
Vector
```

Important interfaces include:

```text
List
RandomAccess
Cloneable
Serializable
```

So:

```text
Iterable
    |
Collection
    |
List
    |
Vector
```

---

# 4. Internal Structure

Vector is array-backed.

Conceptually:

```text
Vector
   |
   v
Backing Array
   |
   +---- [10]
   +---- [20]
   +---- [30]
   +---- [40]
```

Because the elements are stored using array indexing:

```java
vector.get(index)
```

can directly access the required position.

Therefore:

```text
get(index) -> O(1)
```

---

# 5. Size vs Capacity

This is one of the most important Vector interview questions.

Consider:

```java
Vector<Integer> vector =
        new Vector<>(10);
```

Initially:

```text
size     = 0
capacity = 10
```

After:

```java
vector.add(10);
vector.add(20);
```

we have:

```text
size     = 2
capacity = 10
```

### Size

Number of actual elements:

```java
vector.size()
```

### Capacity

Amount of internal storage currently available:

```java
vector.capacity()
```

---

## Key Rule

```text
size != capacity
```

They represent different things.

---

# 6. Initial Capacity

You can specify an initial capacity:

```java
Vector<Integer> vector =
        new Vector<>(100);
```

This means:

```text
Initial capacity = 100
```

It does **not** mean:

```text
100 elements
```

Initially:

```text
size = 0
```

---

## Interview Trap

### Question

What is the size of:

```java
new Vector<>(100)
```

### Answer

```text
0
```

The capacity is:

```text
100
```

---

# 7. Capacity Growth

When a Vector becomes full, it needs additional storage.

Conceptually:

```text
Before:

Capacity = 3

[10][20][30]
```

Add another element:

```text
40
```

The Vector must grow:

```text
New larger array
        |
        v
[10][20][30][40][ ][ ]
```

Existing elements need to be copied into the new storage.

Therefore, although append is generally:

```text
O(1) amortized
```

an individual resize can require:

```text
O(n)
```

work.

---

## Important

Do not rely on a simplistic statement such as:

> Vector always doubles its capacity.

The exact growth behavior depends on the Vector's configuration and implementation.

Vector can use a configured:

```text
capacityIncrement
```

or its default growth behavior.

---

# 8. Capacity Increment

Vector provides a constructor:

```java
Vector<>(
    initialCapacity,
    capacityIncrement
)
```

Example:

```java
Vector<Integer> vector =
        new Vector<>(10, 5);
```

Conceptually:

```text
Initial capacity = 10
Capacity increment = 5
```

When additional capacity is required, this increment can influence growth.

---

## Why Is This Important?

It is a legacy feature that distinguishes Vector from the more commonly used `ArrayList`.

---

# 9. ensureCapacity

Example:

```java
Vector<Integer> vector =
        new Vector<>();

vector.ensureCapacity(1000);
```

This ensures sufficient internal capacity for at least the requested number of elements.

It does not add elements.

Therefore:

```java
vector.size()
```

can still be:

```text
0
```

---

## Interview Question

### Does `ensureCapacity()` change size?

### Answer

No.

It changes internal capacity if necessary.

---

# 10. trimToSize

Example:

```java
Vector<Integer> vector =
        new Vector<>(100);

vector.add(10);
vector.add(20);
vector.add(30);

vector.trimToSize();
```

Before:

```text
size     = 3
capacity = 100
```

After:

```text
size     = 3
capacity ≈ 3
```

The purpose is to remove unused capacity.

---

# 11. Time Complexity

| Operation | Complexity |
|---|---:|
| `get(index)` | O(1) |
| `set(index, value)` | O(1) |
| `add(value)` | O(1) amortized |
| `add(index, value)` | O(n) |
| `remove(index)` | O(n) |
| `remove(Object)` | O(n) |
| `contains()` | O(n) |
| `indexOf()` | O(n) |
| `lastIndexOf()` | O(n) |
| `size()` | O(1) |
| `capacity()` | O(1) |
| `ensureCapacity()` | O(n) in the case of resizing |
| `trimToSize()` | O(n) in the case of copying |

---

# 12. Is Vector Thread-Safe?

## Question

Is Vector thread-safe?

### Answer

Vector synchronizes many of its methods by default.

Therefore, individual operations have synchronization protection.

Example:

```java
Vector<Integer> vector =
        new Vector<>();
```

is different from:

```java
ArrayList<Integer> list =
        new ArrayList<>();
```

in this respect.

---

## Important Nuance

Do not simply say:

> Vector is completely thread-safe.

A more accurate answer is:

> Vector synchronizes individual operations, but compound operations involving multiple method calls are not automatically atomic.

---

# 13. What Does Synchronized Mean?

Consider:

```java
vector.add(10);
```

The operation is synchronized on the Vector object.

Conceptually:

```text
Thread A
   |
   v
 synchronized method
   |
 Vector
   ^
   |
Thread B
```

Only one thread can hold the object's intrinsic monitor for a synchronized method invocation at a time.

This provides mutual exclusion for the synchronized operation.

---

# 14. Is Vector Completely Thread-Safe?

No.

Consider:

```java
if (!vector.contains(10)) {
    vector.add(10);
}
```

Even though:

```java
contains()
```

and:

```java
add()
```

are individually synchronized, the entire sequence is not automatically atomic.

Another thread could execute between those two operations.

Conceptually:

```text
Thread A:
contains(10)
       |
       |   <-- Thread B modifies Vector
       |
add(10)
```

Therefore:

```text
synchronized individual methods
        !=
atomic multi-step operation
```

---

# 15. Vector vs ArrayList

This is one of the most common interview questions.

| Feature | ArrayList | Vector |
|---|---|---|
| Internal structure | Dynamic array | Dynamic array |
| `get(index)` | O(1) | O(1) |
| Add end | O(1) amortized | O(1) amortized |
| Indexed insertion | O(n) | O(n) |
| Indexed removal | O(n) | O(n) |
| Search | O(n) | O(n) |
| Synchronized by default | No | Yes |
| Legacy | No | Yes |
| General modern choice | Yes | Usually no |

---

## Why ArrayList Is Usually Preferred

For ordinary list usage:

```java
ArrayList
```

is generally preferred because it does not impose Vector's legacy synchronization overhead on every operation.

---

# 16. Vector vs LinkedList

| Feature | Vector | LinkedList |
|---|---|---|
| Internal structure | Dynamic array | Doubly linked nodes |
| `get(index)` | O(1) | O(n) |
| Add end | O(1) amortized | O(1) |
| Add first | O(n) | O(1) |
| Remove first | O(n) | O(1) |
| Indexed insertion | O(n) | O(n) |
| Search | O(n) | O(n) |
| Synchronized by default | Yes | No |
| Memory overhead | Lower | Higher |
| Implements `Deque` | No | Yes |

---

# 17. Vector vs CopyOnWriteArrayList

These are very different concurrency strategies.

| Feature | Vector | CopyOnWriteArrayList |
|---|---|---|
| Main approach | Synchronization | Copy-on-write |
| Reads | Synchronized method access | Very efficient |
| Writes | Synchronized | Expensive due to copying |
| Best for | Legacy synchronized list | Read-heavy concurrent list |
| Iterator behavior | Fail-fast style | Snapshot-style |
| Allows null | Yes | Yes |

---

## When Is CopyOnWriteArrayList Useful?

It is useful when:

```text
Reads >> Writes
```

For example:

```text
Many threads read
Very few threads modify
```

Every structural write creates a new underlying array.

---

# 18. Legacy Methods

Vector contains methods from before the modern Collections Framework.

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

---

## Modern Equivalents

| Vector Legacy Method | Modern Method |
|---|---|
| `addElement(e)` | `add(e)` |
| `elementAt(i)` | `get(i)` |
| `setElementAt(e, i)` | `set(i, e)` |
| `removeElement(e)` | `remove(e)` |
| `removeElementAt(i)` | `remove(i)` |
| `insertElementAt(e, i)` | `add(i, e)` |
| `elements()` | `iterator()` |

---

# 19. Enumeration

Vector supports the old:

```java
Enumeration
```

API.

Example:

```java
Vector<String> vector =
        new Vector<>(
                List.of(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );

Enumeration<String> enumeration =
        vector.elements();

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

### Enumeration

Older API:

```java
hasMoreElements()
nextElement()
```

### Iterator

Modern collection API:

```java
hasNext()
next()
remove()
```

Generally prefer `Iterator` in modern code.

---

# 20. Iterator

Vector also supports:

```java
Iterator
```

Example:

```java
Iterator<Integer> iterator =
        vector.iterator();

while (iterator.hasNext()) {

    Integer value =
            iterator.next();

    System.out.println(value);
}
```

---

## Removing During Iteration

Use:

```java
iterator.remove();
```

rather than directly modifying the Vector during traversal.

---

# 21. Null and Duplicates

## Does Vector Allow null?

Yes.

```java
Vector<String> vector =
        new Vector<>();

vector.add(null);
```

Valid.

---

## Does Vector Allow Duplicates?

Yes.

```java
vector.add("Java");
vector.add("Java");
```

Result:

```text
[Java, Java]
```

---

## Does Vector Preserve Insertion Order?

Yes.

Example:

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

# 22. Common Traps

## Trap 1: Vector Is a Linked List

Wrong.

```text
Vector
    -> Dynamic array

LinkedList
    -> Doubly linked list
```

---

## Trap 2: Vector Has No Capacity

Wrong.

Vector provides:

```java
capacity()
```

---

## Trap 3: `new Vector<>(100)` Creates 100 Elements

Wrong.

It means:

```text
size = 0
capacity = 100
```

---

## Trap 4: Vector Is Always Better Because It Is Thread-Safe

Wrong.

For ordinary list usage, `ArrayList` is generally preferred.

For concurrent use cases, choose the collection based on the actual access pattern and concurrency requirements.

---

## Trap 5: Vector Makes Compound Operations Atomic

Wrong.

This:

```java
if (!vector.contains(x)) {
    vector.add(x);
}
```

is not automatically atomic.

---

## Trap 6: Vector Always Doubles Capacity

Do not rely on that statement.

Vector supports configurable capacity increment behavior, and exact growth behavior should not be oversimplified.

---

## Trap 7: `ensureCapacity()` Adds Elements

Wrong.

It only manages internal capacity.

---

# 23. Tricky Output Questions

## Question 1

What is the output?

```java
Vector<Integer> vector =
        new Vector<>();

vector.add(10);
vector.add(20);
vector.add(30);

System.out.println(vector);
```

### Answer

```text
[10, 20, 30]
```

---

## Question 2

What is the output?

```java
Vector<Integer> vector =
        new Vector<>(100);

System.out.println(
        vector.size()
);
```

### Answer

```text
0
```

---

## Question 3

What is the output?

```java
Vector<Integer> vector =
        new Vector<>(100);

System.out.println(
        vector.capacity()
);
```

### Answer

```text
100
```

assuming the Vector has not grown or otherwise changed capacity.

---

## Question 4

What is the output?

```java
Vector<Integer> vector =
        new Vector<>(10);

vector.add(100);

System.out.println(
        vector.size()
);

System.out.println(
        vector.capacity()
);
```

### Answer

```text
1
10
```

---

## Question 5

What is the output?

```java
Vector<Integer> vector =
        new Vector<>(
                List.of(
                        10, 20, 30
                )
        );

vector.set(1, 200);

System.out.println(vector);
```

### Answer

```text
[10, 200, 30]
```

---

## Question 6

What is the output?

```java
Vector<Integer> vector =
        new Vector<>(
                List.of(
                        10, 20, 30
                )
        );

vector.add(1, 100);

System.out.println(vector);
```

### Answer

```text
[10, 100, 20, 30]
```

---

## Question 7

What is the output?

```java
Vector<Integer> vector =
        new Vector<>(
                List.of(
                        10, 20, 30
                )
        );

System.out.println(
        vector.elementAt(1)
);
```

### Answer

```text
20
```

---

## Question 8

What is the output?

```java
Vector<Integer> vector =
        new Vector<>(
                List.of(
                        10, 20, 30
                )
        );

vector.removeElement(20);

System.out.println(vector);
```

### Answer

```text
[10, 30]
```

---

## Question 9

What is the output?

```java
Vector<Integer> vector =
        new Vector<>(
                List.of(
                        10, 20, 30
                )
        );

vector.ensureCapacity(100);

System.out.println(
        vector.size()
);
```

### Answer

```text
3
```

`ensureCapacity()` does not modify the number of elements.

---

## Question 10

What is the output?

```java
Vector<Integer> vector =
        new Vector<>(
                List.of(
                        10, 20, 30
                )
        );

vector.trimToSize();

System.out.println(
        vector.size()
);
```

### Answer

```text
3
```

`trimToSize()` changes capacity, not the number of elements.

---

# 24. Scenario-Based Questions

## Scenario 1

You need a normal list in a modern Java application.

Which should you generally choose?

### Answer

```java
ArrayList
```

---

## Scenario 2

You are maintaining an old application that heavily uses Vector.

Should you automatically replace it?

### Answer

No.

First understand:

- Existing API dependencies
- Threading requirements
- Performance requirements
- Compatibility concerns

A migration should be deliberate.

---

## Scenario 3

You need efficient random access.

Choose:

```text
Vector
LinkedList
```

### Answer

```text
Vector
```

because:

```text
get(index) -> O(1)
```

while:

```text
LinkedList.get(index) -> O(n)
```

---

## Scenario 4

You need frequent insertion/removal at both ends.

Which is more natural?

```text
Vector
LinkedList
```

### Answer

```text
LinkedList
```

because it implements `Deque`.

However, for pure deque behavior, also consider:

```text
ArrayDeque
```

---

## Scenario 5

You need a read-heavy concurrent list.

Would Vector automatically be your first choice?

### Answer

No.

Depending on the workload, a collection such as:

```java
CopyOnWriteArrayList
```

may be more appropriate.

---

## Scenario 6

You need a stack.

Should you choose Vector?

### Answer

No.

For modern stack behavior, prefer:

```java
ArrayDeque
```

---

## Scenario 7

You need to know how much unused internal storage a Vector has.

What should you inspect?

### Answer

```java
vector.capacity()
```

---

## Scenario 8

You know in advance that a Vector will hold approximately 10,000 elements.

What can you do to reduce unnecessary resizing?

### Answer

Specify an appropriate initial capacity:

```java
Vector<Integer> vector =
        new Vector<>(10_000);
```

or use:

```java
ensureCapacity(10_000);
```

---

# 25. Rapid-Fire Questions

## 1. What is Vector?

A legacy synchronized dynamic-array implementation of `List`.

## 2. Which package?

```java
java.util
```

## 3. Is Vector array-backed?

Yes.

## 4. Is Vector dynamically resizable?

Yes.

## 5. Is Vector synchronized?

Yes, many of its methods are synchronized by default.

## 6. Is Vector the preferred modern List implementation?

Usually no.

## 7. What is usually preferred?

```java
ArrayList
```

for normal list usage.

## 8. What is `get(index)` complexity?

```text
O(1)
```

## 9. What is indexed insertion?

```text
O(n)
```

## 10. Why is indexed insertion O(n)?

Elements may need to be shifted.

## 11. What is indexed removal?

```text
O(n)
```

## 12. Why?

Elements may need to be shifted.

## 13. What is search complexity?

```text
O(n)
```

## 14. Does Vector allow duplicates?

Yes.

## 15. Does Vector allow null?

Yes.

## 16. Does Vector maintain insertion order?

Yes.

## 17. Does Vector support `Enumeration`?

Yes.

## 18. Does Vector support `Iterator`?

Yes.

## 19. What is `size()`?

Number of actual elements.

## 20. What is `capacity()`?

Current internal storage capacity.

## 21. Does `ensureCapacity()` add elements?

No.

## 22. Does `trimToSize()` remove elements?

No.

## 23. What is `capacityIncrement`?

A configurable growth increment used when additional capacity is needed.

## 24. Does synchronization make every operation atomic?

No.

## 25. Does Vector implement Deque?

No.

---

# 26. Must-Know Questions

## ⭐⭐⭐⭐⭐ Critical

- [ ] What is Vector?
- [ ] Why is Vector called a dynamic array?
- [ ] Explain its internal structure.
- [ ] Is Vector synchronized?
- [ ] Vector vs ArrayList
- [ ] Size vs capacity
- [ ] Initial capacity
- [ ] Capacity growth
- [ ] Why is `get()` O(1)?
- [ ] Why is indexed insertion O(n)?
- [ ] Why is indexed removal O(n)?
- [ ] Why is Vector considered legacy?

---

## ⭐⭐⭐⭐ Important

- [ ] `ensureCapacity()`
- [ ] `trimToSize()`
- [ ] `capacityIncrement`
- [ ] Vector vs LinkedList
- [ ] Vector vs CopyOnWriteArrayList
- [ ] Enumeration
- [ ] Iterator
- [ ] Thread safety
- [ ] Atomicity vs synchronization

---

## ⭐⭐⭐ Good to Know

- [ ] Legacy methods
- [ ] `addElement()`
- [ ] `elementAt()`
- [ ] `setElementAt()`
- [ ] `removeElement()`
- [ ] `removeElementAt()`
- [ ] `insertElementAt()`
- [ ] `elements()`

---

# 27. Final Interview Answer

## Question

> Explain Vector in Java.

### Strong Answer

> `Vector` is a legacy, synchronized, dynamically growing array implementation of the `List` interface. Internally, it uses an array-backed structure, so indexed access such as `get(index)` is O(1). Adding at the end is O(1) amortized, while insertion or removal at an arbitrary index is generally O(n) because elements may need to be shifted. Vector maintains a size and a separate capacity, supports configurable capacity growth through its capacity-increment constructor, and provides legacy APIs such as `Enumeration` and `addElement()`. It synchronizes many individual operations by default, but this does not make arbitrary multi-operation sequences atomic. For new code, `ArrayList` is generally preferred for ordinary list usage, while specialized concurrent collections should be chosen when concurrency is required.

---

# 28. Final Checklist

Before moving to `Stack`, make sure you understand:

## Core

- [ ] Vector is a dynamic array
- [ ] Vector is array-backed
- [ ] Vector implements `List`
- [ ] Vector supports random access
- [ ] Vector maintains insertion order
- [ ] Vector allows duplicates
- [ ] Vector allows `null`

---

## Complexity

- [ ] `get()` -> O(1)
- [ ] `set()` -> O(1)
- [ ] Add at end -> O(1) amortized
- [ ] Indexed insertion -> O(n)
- [ ] Indexed removal -> O(n)
- [ ] Search -> O(n)

---

## Capacity

- [ ] Understand size
- [ ] Understand capacity
- [ ] Initial capacity
- [ ] Capacity growth
- [ ] Capacity increment
- [ ] `ensureCapacity()`
- [ ] `trimToSize()`

---

## Thread Safety

- [ ] Vector synchronizes many methods
- [ ] Synchronization has overhead
- [ ] Individual synchronization does not guarantee compound-operation atomicity
- [ ] Vector is not automatically the best concurrent collection

---

## Comparisons

- [ ] Vector vs ArrayList
- [ ] Vector vs LinkedList
- [ ] Vector vs CopyOnWriteArrayList
- [ ] Understand why ArrayList is generally preferred

---

## Legacy API

- [ ] `addElement()`
- [ ] `elementAt()`
- [ ] `setElementAt()`
- [ ] `removeElement()`
- [ ] `removeElementAt()`
- [ ] `insertElementAt()`
- [ ] `elements()`
- [ ] `Enumeration`

---

# Vector Complete

```text
06-Vector/
├── NOTES.md       [x]
├── PRACTICE.md    [x]
└── INTERVIEW.md   [x]
```

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
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 07-Stack
│   ├── NOTES.md       [ ]
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
│
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

> **Next topic: `07-Stack/NOTES.md`**
