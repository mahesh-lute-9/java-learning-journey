# Vector - Practice

> Hands-on practice for understanding `Vector`, dynamic array behavior, capacity, synchronization, legacy methods, and the differences between `Vector` and modern collection classes.

---

## Table of Contents

- [1. Basic Operations](#1-basic-operations)
- [2. Index-Based Operations](#2-index-based-operations)
- [3. Size and Capacity](#3-size-and-capacity)
- [4. Capacity Management](#4-capacity-management)
- [5. Searching](#5-searching)
- [6. Iteration](#6-iteration)
- [7. Legacy Vector Methods](#7-legacy-vector-methods)
- [8. ArrayList vs Vector](#8-arraylist-vs-vector)
- [9. Intermediate Problems](#9-intermediate-problems)
- [10. Code Prediction](#10-code-prediction)
- [11. Interview Practice](#11-interview-practice)
- [12. Challenge Problems](#12-challenge-problems)
- [13. Practice Checklist](#13-practice-checklist)

---

# 1. Basic Operations

## Problem 1: Create a Vector

Create:

```java
Vector<Integer> numbers =
        new Vector<>();
```

Add:

```text
10
20
30
40
50
```

### Expected Output

```text
[10, 20, 30, 40, 50]
```

---

## Problem 2: Create a String Vector

Create a:

```java
Vector<String>
```

containing:

```text
Java
Spring
Hibernate
SQL
```

Print the Vector.

---

## Problem 3: Find Size

Given:

```java
Vector<Integer> numbers =
        new Vector<>(
                List.of(
                        10, 20, 30,
                        40, 50
                )
        );
```

Find the number of elements.

### Expected Output

```text
5
```

---

## Problem 4: Check Empty

Create an empty Vector.

Check:

```java
isEmpty()
```

Then add an element and check again.

### Expected Output

```text
true
false
```

---

## Problem 5: Add Elements Using a Loop

Create an empty Vector and add numbers from:

```text
1 to 10
```

### Expected Output

```text
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
```

---

# 2. Index-Based Operations

## Problem 6: Access an Element

Given:

```java
Vector<String> languages =
        new Vector<>(
                List.of(
                        "Java",
                        "Python",
                        "C++",
                        "Go"
                )
        );
```

Print the element at index `2`.

### Expected Output

```text
C++
```

---

## Problem 7: Update an Element

Given:

```text
[Java, Python, C++]
```

Replace:

```text
Python
```

with:

```text
Spring
```

### Expected Output

```text
[Java, Spring, C++]
```

---

## Problem 8: Insert at Index

Given:

```text
[10, 20, 40, 50]
```

Insert `30` at index `2`.

### Expected Output

```text
[10, 20, 30, 40, 50]
```

---

## Problem 9: Remove by Index

Given:

```text
[10, 20, 30, 40, 50]
```

Remove the element at index `2`.

### Expected Output

```text
[10, 20, 40, 50]
```

---

## Problem 10: Remove by Value

Given:

```text
[10, 20, 30, 40]
```

Remove:

```text
30
```

### Expected Output

```text
[10, 20, 40]
```

---

# 3. Size and Capacity

> This section is especially important for Vector interviews.

---

## Problem 11: Size vs Capacity

Create:

```java
Vector<Integer> vector =
        new Vector<>(10);
```

Print:

```java
vector.size();
vector.capacity();
```

### Expected Result

Conceptually:

```text
Size: 0
Capacity: 10
```

---

## Problem 12: Add One Element

Create:

```java
Vector<Integer> vector =
        new Vector<>(10);
```

Add:

```text
100
```

Print:

```text
size
capacity
```

### Expected Result

```text
Size: 1
Capacity: 10
```

---

## Problem 13: Fill the Vector

Create:

```java
Vector<Integer> vector =
        new Vector<>(3);
```

Add:

```text
10
20
30
```

Print:

```text
size
capacity
```

### Expected

```text
Size: 3
Capacity: 3
```

Then add one more element and observe the capacity change.

> Do not assume the exact new capacity unless you know the JDK implementation behavior being used.

---

## Problem 14: Observe Capacity Growth

Write a program that adds 20 elements one by one.

After every insertion print:

```text
Size
Capacity
```

Example format:

```text
Size: 1   Capacity: ...
Size: 2   Capacity: ...
Size: 3   Capacity: ...
...
```

Observe when Vector grows its internal storage.

---

# 4. Capacity Management

## Problem 15: ensureCapacity()

Create:

```java
Vector<Integer> vector =
        new Vector<>();
```

Call:

```java
vector.ensureCapacity(100);
```

Then print:

```java
vector.size();
vector.capacity();
```

### Important

`ensureCapacity()` does not add elements.

Therefore:

```text
size = 0
```

while capacity should be sufficient for the requested minimum capacity.

---

## Problem 16: trimToSize()

Create:

```java
Vector<Integer> vector =
        new Vector<>(100);
```

Add:

```text
10
20
30
```

Print:

```java
size
capacity
```

Then call:

```java
trimToSize();
```

Print them again.

### Expected Concept

Before:

```text
Size: 3
Capacity: 100
```

After trimming:

```text
Size: 3
Capacity: approximately 3
```

---

## Problem 17: Capacity Increment

Create:

```java
Vector<Integer> vector =
        new Vector<>(5, 3);
```

Add elements one by one.

Observe how the capacity changes when the original capacity is exceeded.

### Goal

Understand the difference between:

```text
initial capacity
```

and:

```text
capacity increment
```

---

## Problem 18: Constructor Comparison

Compare:

```java
new Vector<>();
```

```java
new Vector<>(20);
```

```java
new Vector<>(20, 5);
```

For each one, determine:

```text
Initial size
Initial capacity
Growth configuration
```

---

# 5. Searching

## Problem 19: contains()

Given:

```java
Vector<Integer> numbers =
        new Vector<>(
                List.of(
                        10, 20, 30, 40
                )
        );
```

Check whether `30` exists.

### Expected Output

```text
true
```

---

## Problem 20: indexOf()

Given:

```text
[10, 20, 30, 20, 40]
```

Find the first index of:

```text
20
```

### Expected Output

```text
1
```

---

## Problem 21: lastIndexOf()

Using:

```text
[10, 20, 30, 20, 40]
```

find the last index of:

```text
20
```

### Expected Output

```text
3
```

---

## Problem 22: Count Occurrences

Given:

```text
[10, 20, 10, 30, 10, 40]
```

Count how many times `10` occurs.

### Expected Output

```text
3
```

---

# 6. Iteration

## Problem 23: Enhanced For Loop

Given:

```java
Vector<Integer> numbers =
        new Vector<>(
                List.of(
                        10, 20, 30
                )
        );
```

Print every element using:

```java
for-each
```

---

## Problem 24: Traditional For Loop

Traverse the Vector using:

```java
for
```

and:

```java
get(index)
```

---

## Problem 25: Iterator

Traverse the Vector using:

```java
Iterator<Integer>
```

---

## Problem 26: ListIterator

Create a:

```java
ListIterator<Integer>
```

and traverse the Vector forward.

Then traverse it backward.

---

## Problem 27: Enumeration

Create:

```java
Enumeration<Integer>
```

using:

```java
vector.elements()
```

Print every element.

### Expected Behavior

The elements should be printed in insertion order.

---

# 7. Legacy Vector Methods

> Vector contains older method names because it predates the modern Collections Framework.

---

## Problem 28: addElement()

Given:

```java
Vector<Integer> numbers =
        new Vector<>();
```

Use:

```java
addElement()
```

to add:

```text
10
20
30
```

### Expected Output

```text
[10, 20, 30]
```

---

## Problem 29: elementAt()

Given:

```text
[10, 20, 30]
```

Use:

```java
elementAt(1)
```

### Expected Output

```text
20
```

---

## Problem 30: setElementAt()

Given:

```text
[10, 20, 30]
```

Replace `20` with `200` using:

```java
setElementAt()
```

### Expected Output

```text
[10, 200, 30]
```

---

## Problem 31: removeElement()

Given:

```text
[10, 20, 30]
```

Remove `20` using:

```java
removeElement()
```

### Expected Output

```text
[10, 30]
```

---

## Problem 32: removeElementAt()

Given:

```text
[10, 20, 30]
```

Remove index `1` using:

```java
removeElementAt()
```

### Expected Output

```text
[10, 30]
```

---

## Problem 33: insertElementAt()

Given:

```text
[10, 20, 40]
```

Insert `30` at index `2` using:

```java
insertElementAt()
```

### Expected Output

```text
[10, 20, 30, 40]
```

---

# 8. ArrayList vs Vector

## Problem 34: Choose the Collection

You need a normal list for a single-threaded application.

Choose:

```text
ArrayList
```

or:

```text
Vector
```

### Answer

Usually:

```text
ArrayList
```

---

## Problem 35: Need Synchronization

You encounter legacy code that expects a synchronized `List` implementation.

Which class might you encounter?

### Answer

```text
Vector
```

---

## Problem 36: Random Access

Which provides efficient index-based access?

```text
ArrayList
Vector
LinkedList
```

### Answer

```text
ArrayList
Vector
```

because both are array-backed.

---

## Problem 37: Dynamic Array

Which of these are dynamically growing array-backed lists?

```text
ArrayList
Vector
LinkedList
```

### Answer

```text
ArrayList
Vector
```

---

## Problem 38: Deque

Which supports:

```text
addFirst()
addLast()
removeFirst()
removeLast()
```

naturally?

### Answer

```text
LinkedList
```

and:

```text
ArrayDeque
```

Vector does not implement `Deque`.

---

# 9. Intermediate Problems

## Problem 39: Remove Even Numbers

Given:

```text
[10, 15, 20, 25, 30, 35]
```

Remove all even numbers.

### Expected Output

```text
[15, 25, 35]
```

Try solving it with an `Iterator`.

---

## Problem 40: Find Maximum

Given:

```text
[10, 90, 30, 70, 50]
```

Find the maximum without using:

```java
Collections.max()
```

### Expected Output

```text
90
```

---

## Problem 41: Find Minimum

Given:

```text
[10, 90, 30, 70, 50]
```

Find the minimum.

### Expected Output

```text
10
```

---

## Problem 42: Reverse the Vector

Given:

```text
[10, 20, 30, 40, 50]
```

Reverse it.

### Expected Output

```text
[50, 40, 30, 20, 10]
```

Try:

1. `Collections.reverse()`
2. Without `Collections.reverse()`

---

## Problem 43: Remove Duplicates

Given:

```text
[10, 20, 10, 30, 20, 40]
```

Create:

```text
[10, 20, 30, 40]
```

### Challenge

Use a `Set`.

---

## Problem 44: Find Second Largest

Given:

```text
[10, 50, 20, 90, 30, 80]
```

Find the second-largest value.

### Expected Output

```text
80
```

Try solving without sorting.

---

## Problem 45: Count Even and Odd

Given:

```text
[10, 15, 20, 25, 30, 35]
```

Find:

```text
Even = 3
Odd  = 3
```

---

# 10. Code Prediction

## Problem 46

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

## Problem 47

What is the output?

```java
Vector<Integer> vector =
        new Vector<>(10);

vector.add(100);

System.out.println(
        vector.size()
);
```

### Answer

```text
1
```

---

## Problem 48

What is the output?

```java
Vector<Integer> vector =
        new Vector<>(10);

vector.add(100);

System.out.println(
        vector.capacity()
);
```

### Answer

```text
10
```

assuming no capacity expansion has been triggered.

---

## Problem 49

What is the output?

```java
Vector<Integer> vector =
        new Vector<>(10);

System.out.println(
        vector.size()
);

System.out.println(
        vector.capacity()
);
```

### Answer

```text
0
10
```

---

## Problem 50

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

## Problem 51

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

## Problem 52

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

## Problem 53

What is the output?

```java
Vector<Integer> vector =
        new Vector<>();

vector.addElement(10);
vector.addElement(20);

System.out.println(vector);
```

### Answer

```text
[10, 20]
```

---

## Problem 54

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

`ensureCapacity()` does not add elements.

---

# 11. Interview Practice

## Problem 55: Explain Size vs Capacity

Explain the difference between:

```java
vector.size()
```

and:

```java
vector.capacity()
```

### Expected Answer

`size()` is the number of elements currently stored.

`capacity()` is the amount of internal storage currently available before the Vector needs to grow.

Example:

```text
size     = 3
capacity = 10
```

means:

```text
3 actual elements
10 storage slots available
```

---

## Problem 56: Explain ensureCapacity()

What does:

```java
ensureCapacity(100)
```

do?

### Expected Answer

It ensures that the Vector has enough internal capacity to accommodate at least the requested number of elements without immediate resizing.

It does not add elements.

---

## Problem 57: Explain trimToSize()

What does:

```java
trimToSize()
```

do?

### Expected Answer

It reduces excess capacity so that the internal capacity is approximately equal to the current number of elements.

---

## Problem 58: Why Is Vector Legacy?

### Expected Answer

Vector existed before the modern Java Collections Framework.

Although it was later integrated into that framework, its synchronization and legacy API design make it less commonly preferred for new applications.

---

## Problem 59: Why Is ArrayList Usually Preferred?

### Expected Answer

`ArrayList` is generally preferred for normal list usage because it is not synchronized by default and therefore avoids the synchronization overhead built into Vector's legacy design.

---

## Problem 60: Is Vector Thread-Safe?

### Expected Answer

Vector synchronizes many of its individual methods, so individual operations have synchronization protection.

However, this does not mean arbitrary multi-operation sequences are automatically atomic or that Vector is the best concurrent collection for every situation.

---

# 12. Challenge Problems

## Challenge 1: Capacity Monitoring

Write a program that:

1. Creates a Vector.
2. Adds 100 elements.
3. Prints the size and capacity after every insertion.
4. Identifies when capacity increases.

### Goal

Understand dynamic resizing.

---

## Challenge 2: Compare Growth

Create two Vectors:

```java
Vector<Integer> first =
        new Vector<>(5);

Vector<Integer> second =
        new Vector<>(5, 2);
```

Add 20 elements to both.

Track their capacities after every insertion.

### Goal

Understand how capacity configuration affects growth.

---

## Challenge 3: Implement a Dynamic Array

Create your own class:

```java
class MyVector<E>
```

Internally use:

```java
Object[] data;
```

Implement:

```java
add(E element)
get(int index)
set(int index, E element)
remove(int index)
size()
capacity()
```

### Goal

Understand how a dynamically growing array works internally.

---

## Challenge 4: Implement Capacity Growth

Extend your `MyVector` implementation to automatically resize when:

```text
size == capacity
```

Implement:

```java
ensureCapacity()
```

---

## Challenge 5: Vector vs ArrayList Benchmark

Create both:

```java
ArrayList<Integer>
```

and:

```java
Vector<Integer>
```

Add a large number of elements.

Measure the execution time.

### Goal

Understand the practical cost of synchronization.

> Do not treat a single benchmark as a universal performance conclusion. JVM warm-up, hardware, JDK version, workload, and benchmark design all affect results.

---

## Challenge 6: Thread Experiment

Create multiple threads that add elements to the same Vector.

Observe the behavior.

Then repeat with:

```java
ArrayList
```

and compare the behavior.

### Goal

Understand the difference between synchronized and non-synchronized collection methods.

---

## Challenge 7: Compound Operation

Consider:

```java
if (!vector.contains(10)) {
    vector.add(10);
}
```

Explain why synchronizing individual methods does not automatically make the entire operation atomic.

### Goal

Understand:

```text
thread safety
vs
atomicity
```

---

# 13. Practice Checklist

## Basic Operations

- [ ] Create Vector
- [ ] Add elements
- [ ] Get elements
- [ ] Set elements
- [ ] Remove elements
- [ ] Search
- [ ] Clear
- [ ] Check size
- [ ] Check empty

---

## Capacity

- [ ] Understand size
- [ ] Understand capacity
- [ ] Use `capacity()`
- [ ] Use `ensureCapacity()`
- [ ] Use `trimToSize()`
- [ ] Understand initial capacity
- [ ] Understand capacity increment
- [ ] Observe dynamic growth

---

## Iteration

- [ ] Enhanced `for`
- [ ] Traditional `for`
- [ ] Iterator
- [ ] ListIterator
- [ ] Enumeration

---

## Legacy API

- [ ] `addElement()`
- [ ] `elementAt()`
- [ ] `setElementAt()`
- [ ] `removeElement()`
- [ ] `removeElementAt()`
- [ ] `insertElementAt()`
- [ ] `elements()`

---

## Comparisons

- [ ] Vector vs ArrayList
- [ ] Vector vs LinkedList
- [ ] Vector vs CopyOnWriteArrayList
- [ ] Understand synchronization
- [ ] Understand memory layout
- [ ] Understand random access

---

# Final Goal

Before moving to `INTERVIEW.md`, you should be able to explain:

```text
Vector
   |
   v
Dynamic Array
   |
   +-- index access -> O(1)
   |
   +-- append -> O(1) amortized
   |
   +-- indexed insertion -> O(n)
   |
   +-- indexed removal -> O(n)
   |
   +-- search -> O(n)
   |
   +-- duplicates -> allowed
   |
   +-- null -> allowed
   |
   +-- synchronized methods -> legacy characteristic
   |
   +-- size != capacity
   |
   +-- legacy methods
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
├── PRACTICE.md    [x]
└── INTERVIEW.md   [ ]
```

> **Next: `06-Vector/INTERVIEW.md`**
