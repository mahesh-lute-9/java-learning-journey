# PriorityQueue — Notes

> A practical guide to understanding Java's `PriorityQueue`, how priority-based ordering works, its internal structure, operations, complexity, and common interview concepts.

---

## 📚 Table of Contents

1. [Introduction](#1-introduction)
2. [What is PriorityQueue?](#2-what-is-priorityqueue)
3. [Queue vs PriorityQueue](#3-queue-vs-priorityqueue)
4. [PriorityQueue in Java](#4-priorityqueue-in-java)
5. [Hierarchy](#5-hierarchy)
6. [Creating a PriorityQueue](#6-creating-a-priorityqueue)
7. [How PriorityQueue Works](#7-how-priorityqueue-works)
8. [Min-Heap Concept](#8-min-heap-concept)
9. [Basic Operations](#9-basic-operations)
10. [`offer()` and `add()`](#10-offer-and-add)
11. [`poll()`](#11-poll)
12. [`peek()`](#12-peek)
13. [`remove()`](#13-remove)
14. [`element()`](#14-element)
15. [Removing a Specific Element](#15-removing-a-specific-element)
16. [Checking for Elements](#16-checking-for-elements)
17. [PriorityQueue Ordering](#17-priorityqueue-ordering)
18. [Natural Ordering](#18-natural-ordering)
19. [Custom Comparator](#19-custom-comparator)
20. [Max-Heap Using Comparator](#20-max-heap-using-comparator)
21. [PriorityQueue with Strings](#21-priorityqueue-with-strings)
22. [PriorityQueue with Custom Objects](#22-priorityqueue-with-custom-objects)
23. [Duplicates](#23-duplicates)
24. [Null Values](#24-null-values)
25. [Iteration](#25-iteration)
26. [Why Printing is Not Sorted](#26-why-printing-is-not-sorted)
27. [PriorityQueue Internal Structure](#27-priorityqueue-internal-structure)
28. [Heap Representation](#28-heap-representation)
29. [Time Complexity](#29-time-complexity)
30. [PriorityQueue vs ArrayDeque](#30-priorityqueue-vs-arraydeque)
31. [PriorityQueue vs TreeSet](#31-priorityqueue-vs-treeset)
32. [PriorityQueue vs Sorting](#32-priorityqueue-vs-sorting)
33. [Real-World Applications](#33-real-world-applications)
34. [Common Mistakes](#34-common-mistakes)
35. [Quick Revision](#35-quick-revision)
36. [Final Mental Model](#36-final-mental-model)
37. [Key Interview Statement](#37-key-interview-statement)
38. [Progress](#38-progress)

---

# 1. Introduction

A normal queue generally follows:

> **FIFO — First In, First Out**

But sometimes we don't want to process elements based on when they arrived.

Instead, we want to process the element with the **highest priority** first.

That's where `PriorityQueue` is useful.

Example:

```text
Normal Queue:

Task A → Task B → Task C
   ↓
Process A first
```

Priority Queue:

```text
Task A → Priority 2
Task B → Priority 5
Task C → Priority 1

Process:
Task B → 5
Task A → 2
Task C → 1
```

The processing order is determined by priority rather than simple insertion order.

---

# 2. What is PriorityQueue?

`PriorityQueue` is a class in Java that implements the `Queue` interface.

It stores elements according to an ordering determined by:

- natural ordering, or
- a supplied `Comparator`.

For example:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);
```

The smallest element has the highest priority under natural ordering.

Therefore:

```java
queue.peek();
```

returns:

```text
10
```

---

# 3. Queue vs PriorityQueue

A normal FIFO queue:

```text
Insert:
30 → 10 → 20

Remove:
30 → 10 → 20
```

A `PriorityQueue`:

```text
Insert:
30 → 10 → 20

Remove:
10 → 20 → 30
```

assuming natural ordering for integers.

### Important

> `PriorityQueue` is a `Queue`, but it does not use ordinary insertion-order FIFO behavior.

It uses priority-based ordering.

---

# 4. PriorityQueue in Java

`PriorityQueue` belongs to:

```java
java.util.PriorityQueue
```

Example:

```java
import java.util.PriorityQueue;

PriorityQueue<Integer> queue = new PriorityQueue<>();
```

It implements:

```text
Queue
```

and therefore provides queue operations such as:

```text
offer()
poll()
peek()
```

---

# 5. Hierarchy

A simplified hierarchy:

```text
Iterable
   ↓
Collection
   ↓
Queue
   ↓
PriorityQueue
```

Therefore:

```java
Queue<Integer> queue = new PriorityQueue<>();
```

is valid.

You can also use the concrete type:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();
```

---

# 6. Creating a PriorityQueue

## 6.1 Integer PriorityQueue

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();
```

---

## 6.2 String PriorityQueue

```java
PriorityQueue<String> queue = new PriorityQueue<>();
```

Strings use their natural ordering.

---

## 6.3 With Initial Capacity

You can specify an initial capacity:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>(20);
```

This sets the initial capacity of the internal structure.

It does not limit the queue to 20 elements.

---

## 6.4 With Comparator

You can provide custom ordering:

```java
PriorityQueue<Integer> queue =
        new PriorityQueue<>(Comparator.reverseOrder());
```

Now larger integers have higher priority.

Example:

```java
queue.offer(10);
queue.offer(30);
queue.offer(20);
```

The next element is:

```text
30
```

---

# 7. How PriorityQueue Works

The most important concept:

> `PriorityQueue` is implemented using a **heap**.

For the default ordering, it behaves as a **min-heap**.

Example:

```text
        10
       /  \
     20    30
    /  \
   40   50
```

The smallest element is at the root.

Therefore:

```java
queue.peek();
```

returns:

```text
10
```

---

# 8. Min-Heap Concept

A min-heap follows:

> Parent is less than or equal to its children according to the queue's ordering.

Example:

```text
        10
       /  \
     20    30
    /  \
   40   50
```

Notice:

```text
10 < 20
10 < 30
20 < 40
20 < 50
```

The smallest element is always at the root.

### Important

A heap is **not a fully sorted structure**.

For example:

```text
        10
       /  \
     30    20
```

can satisfy the min-heap property even though `30` appears before `20` in the tree representation.

---

# 9. Basic Operations

The most important operations are:

| Method | Purpose |
|---|---|
| `offer()` | Insert element |
| `add()` | Insert element |
| `peek()` | View highest-priority element |
| `poll()` | Remove highest-priority element |
| `remove()` | Remove element |
| `element()` | View head |
| `size()` | Number of elements |
| `isEmpty()` | Check whether empty |
| `contains()` | Check whether element exists |

---

# 10. `offer()` and `add()`

Both can insert elements.

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);
```

The queue's priority determines which element becomes the head.

You can also use:

```java
queue.add(40);
```

### Difference

The Queue API defines:

```text
add()
offer()
```

as two insertion styles.

For a capacity-limited queue, `add()` can throw `IllegalStateException` if insertion cannot be performed, while `offer()` returns `false`.

`PriorityQueue` itself is unbounded, so normal insertion does not fail because of a fixed queue capacity.

---

# 11. `poll()`

`poll()` removes and returns the highest-priority element.

Example:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);

System.out.println(queue.poll());
```

Output:

```text
10
```

Next:

```java
System.out.println(queue.poll());
```

Output:

```text
20
```

Then:

```text
30
```

---

# 12. `peek()`

`peek()` returns the highest-priority element without removing it.

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);

System.out.println(queue.peek());
System.out.println(queue.size());
```

Output:

```text
10
3
```

The size remains unchanged.

---

# 13. `remove()`

`remove()` can remove the head element.

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);

System.out.println(queue.remove());
```

Output:

```text
10
```

If the queue is empty:

```java
queue.remove();
```

throws:

```text
NoSuchElementException
```

---

# 14. `element()`

`element()` returns the head without removing it.

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);

System.out.println(queue.element());
```

Output:

```text
10
```

If empty:

```java
queue.element();
```

throws:

```text
NoSuchElementException
```

For non-exceptional empty behavior, use:

```java
peek()
```

---

# 15. Removing a Specific Element

You can remove a specific value:

```java
queue.remove(20);
```

This is different from:

```java
queue.poll();
```

### `poll()`

Removes the current highest-priority element.

### `remove(Object)`

Removes a matching element.

Example:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

queue.remove(20);

System.out.println(queue);
```

The `20` element is removed.

### Complexity

Removing an arbitrary element is generally:

```text
O(n)
```

because the queue may need to search for the element first.

---

# 16. Checking for Elements

Use:

```java
queue.contains(20);
```

Example:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();

queue.offer(10);
queue.offer(20);

System.out.println(queue.contains(20));
```

Output:

```text
true
```

Searching for an arbitrary element is generally:

```text
O(n)
```

---

# 17. PriorityQueue Ordering

The ordering comes from:

1. Natural ordering
2. Custom `Comparator`

---

## Natural Ordering

For integers:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();
```

Natural ordering means smaller numbers have higher priority.

```text
1
2
3
4
5
```

---

## Custom Ordering

You can change the priority:

```java
PriorityQueue<Integer> queue =
        new PriorityQueue<>(Comparator.reverseOrder());
```

Now larger numbers have higher priority:

```text
5
4
3
2
1
```

---

# 18. Natural Ordering

If no comparator is provided:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();
```

elements are ordered according to their natural ordering.

For integers:

```text
10 < 20 < 30
```

So:

```java
queue.poll();
```

returns:

```text
10
```

For strings:

```text
Apple
Banana
Mango
```

the natural ordering is lexicographical according to `String`'s ordering.

---

# 19. Custom Comparator

Suppose we want larger numbers first.

```java
PriorityQueue<Integer> queue =
        new PriorityQueue<>(Comparator.reverseOrder());
```

Now:

```java
queue.offer(10);
queue.offer(30);
queue.offer(20);
```

Removal order:

```text
30
20
10
```

### Using Lambda

You can also write:

```java
PriorityQueue<Integer> queue =
        new PriorityQueue<>((a, b) -> b - a);
```

However, for integer comparators, prefer:

```java
Comparator.reverseOrder()
```

or:

```java
Integer.compare(b, a)
```

because subtraction-based comparators can overflow for extreme integer values.

---

# 20. Max-Heap Using Comparator

Java's `PriorityQueue` is commonly used as a min-heap by default.

To create max-heap behavior:

```java
PriorityQueue<Integer> maxHeap =
        new PriorityQueue<>(Comparator.reverseOrder());
```

Example:

```java
maxHeap.offer(10);
maxHeap.offer(50);
maxHeap.offer(30);

System.out.println(maxHeap.poll());
```

Output:

```text
50
```

---

# 21. PriorityQueue with Strings

```java
PriorityQueue<String> queue = new PriorityQueue<>();

queue.offer("Mango");
queue.offer("Apple");
queue.offer("Banana");

while (!queue.isEmpty()) {
    System.out.println(queue.poll());
}
```

The strings are removed according to their natural ordering.

Expected:

```text
Apple
Banana
Mango
```

---

# 22. PriorityQueue with Custom Objects

Suppose we have:

```java
class Student {

    int marks;
    String name;

    Student(int marks, String name) {
        this.marks = marks;
        this.name = name;
    }
}
```

We can define priority using a comparator.

For highest marks first:

```java
PriorityQueue<Student> queue =
        new PriorityQueue<>(
                (a, b) -> Integer.compare(b.marks, a.marks)
        );
```

Then:

```java
queue.offer(new Student(75, "A"));
queue.offer(new Student(90, "B"));
queue.offer(new Student(60, "C"));
```

Removal order:

```text
B → 90
A → 75
C → 60
```

### Important

Custom objects generally need an ordering strategy if you want them prioritized by a particular field.

You can provide that ordering using a `Comparator`.

---

# 23. Duplicates

`PriorityQueue` allows duplicate elements.

Example:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();

queue.offer(10);
queue.offer(10);
queue.offer(20);
queue.offer(10);
```

The duplicates are stored.

Polling produces:

```text
10
10
10
20
```

under natural ordering.

---

# 24. Null Values

`PriorityQueue` does **not** permit `null` elements.

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();

queue.offer(null);
```

This results in:

```text
NullPointerException
```

### Why?

PriorityQueue needs to compare elements to maintain its ordering.

`null` does not provide a natural ordering for this purpose.

---

# 25. Iteration

You can iterate over a `PriorityQueue`:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);

for (Integer value : queue) {
    System.out.println(value);
}
```

### Important

Do **not** assume this iteration prints elements in sorted priority order.

The iterator does not guarantee sorted traversal.

If you want to process elements according to priority, use:

```java
while (!queue.isEmpty()) {
    System.out.println(queue.poll());
}
```

---

# 26. Why Printing is Not Sorted

Consider:

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);

System.out.println(queue);
```

You should **not** expect:

```text
[10, 20, 30]
```

The internal representation is a heap, not a sorted list.

The important guarantee is:

```java
queue.peek()
```

returns the element with the highest priority according to the queue's ordering.

And repeated:

```java
poll()
```

operations retrieve elements in priority order.

---

# 27. PriorityQueue Internal Structure

`PriorityQueue` is based on a heap.

A heap can be represented using an array.

For example:

```text
        10
       /  \
     20    30
    /  \
   40   50
```

Array representation:

```text
[10, 20, 30, 40, 50]
```

For a zero-based array, relationships are commonly represented as:

```text
parent(i) = (i - 1) / 2

left(i) = 2 * i + 1

right(i) = 2 * i + 2
```

These formulas help implement heap operations efficiently.

---

# 28. Heap Representation

Suppose:

```text
[10, 20, 30, 40, 50]
```

The tree is conceptually:

```text
             10
           /    \
         20      30
        /  \
      40    50
```

The root:

```text
10
```

is the minimum element.

Therefore:

```java
peek()
```

returns:

```text
10
```

---

## What Happens During `offer()`?

Suppose:

```text
10
20
30
```

and we insert:

```text
5
```

Conceptually:

1. Add the new element.
2. Compare it with its parent.
3. Move it upward if necessary.
4. Restore the heap property.

This process is called:

> **Heapify-up / sift-up**

Typical complexity:

```text
O(log n)
```

---

## What Happens During `poll()`?

When removing the root:

1. Remove the root.
2. Move the last element into the root position.
3. Compare it with its children.
4. Move it downward if necessary.
5. Restore the heap property.

This is commonly called:

> **Heapify-down / sift-down**

Typical complexity:

```text
O(log n)
```

---

# 29. Time Complexity

Important complexities:

| Operation | Complexity |
|---|---:|
| `offer()` | O(log n) |
| `add()` | O(log n) |
| `peek()` | O(1) |
| `poll()` | O(log n) |
| `remove()` head | O(log n) |
| `remove(Object)` | O(n) |
| `contains()` | O(n) |
| `size()` | O(1) |
| `isEmpty()` | O(1) |

### Why is `peek()` O(1)?

The highest-priority element is stored at the root of the heap.

---

# 30. PriorityQueue vs ArrayDeque

| Feature | PriorityQueue | ArrayDeque |
|---|---|---|
| Main purpose | Priority-based processing | Queue/Deque |
| Default ordering | Natural ordering | Insertion order |
| FIFO | No | Yes, when used as Queue |
| Heap-based | Yes | No |
| `peek()` | Highest priority | Front |
| `poll()` | Highest priority | Front |
| `offer()` | O(log n) | O(1) amortized |
| Allows null | No | No |
| Duplicates | Yes | Yes |

### Example

`ArrayDeque`:

```text
Insert:
30 → 10 → 20

Poll:
30 → 10 → 20
```

`PriorityQueue`:

```text
Insert:
30 → 10 → 20

Poll:
10 → 20 → 30
```

---

# 31. PriorityQueue vs TreeSet

Both can provide sorted access, but they solve different problems.

| Feature | PriorityQueue | TreeSet |
|---|---|---|
| Main purpose | Priority processing | Sorted unique elements |
| Duplicates | Allowed | Not allowed |
| Data structure | Heap | Balanced search tree |
| `peek()` minimum | O(1) | O(log n) via first/last navigation |
| Insert | O(log n) | O(log n) |
| Remove head/min | O(log n) | O(log n) |
| Search arbitrary element | O(n) | O(log n) |
| Fully sorted iteration | No | Yes |

### Key Difference

Use `PriorityQueue` when:

> You repeatedly need the highest/lowest-priority element.

Use `TreeSet` when:

> You need a sorted set of unique elements.

---

# 32. PriorityQueue vs Sorting

Suppose you have:

```text
1000 elements
```

and repeatedly need the smallest element.

One approach is:

```text
sort
take smallest
remove
sort again
...
```

This can be inefficient.

A priority queue maintains the heap property dynamically.

Typical:

```text
offer() → O(log n)
poll()  → O(log n)
peek()  → O(1)
```

This makes it useful when elements are continuously added and the next highest-priority element is repeatedly needed.

---

# 33. Real-World Applications

## 33.1 Task Scheduling

Tasks can have different priorities:

```text
Task A → Priority 2
Task B → Priority 5
Task C → Priority 1
```

Processing:

```text
Task B
Task A
Task C
```

---

## 33.2 Hospital Emergency Systems

Patients can be assigned priorities based on urgency.

Higher-priority cases can be processed first.

---

## 33.3 CPU Scheduling

Some scheduling strategies process jobs according to priority.

---

## 33.4 Dijkstra's Algorithm

Dijkstra's shortest-path algorithm commonly uses a priority queue to repeatedly select the node with the smallest known distance.

---

## 33.5 A* Search

A priority queue can store nodes ordered by their estimated total cost.

---

## 33.6 Top K Problems

Priority queues are commonly useful for problems such as:

```text
Top K largest elements
Top K smallest elements
Kth largest element
Kth smallest element
```

---

## 33.7 Merge K Sorted Lists

A priority queue can track the smallest current element from each sorted list.

This allows efficient merging.

---

## 33.8 Event Simulation

Events can be ordered by timestamp:

```text
Event A → 10:00
Event B → 09:30
Event C → 11:00
```

The earliest event can be processed first.

---

# 34. Common Mistakes

## Mistake 1 — Saying PriorityQueue is FIFO

Incorrect.

`PriorityQueue` is priority-based.

---

## Mistake 2 — Assuming insertion order is preserved

It isn't.

```java
queue.offer(30);
queue.offer(10);
queue.offer(20);
```

does not mean the removal order is:

```text
30
10
20
```

---

## Mistake 3 — Assuming printing the queue gives sorted order

Incorrect.

```java
System.out.println(queue);
```

does not guarantee sorted order.

Use repeated:

```java
poll()
```

to process elements according to priority.

---

## Mistake 4 — Assuming the whole heap is sorted

A heap only guarantees the heap property.

The root has the highest priority, but the remaining elements are not necessarily globally sorted.

---

## Mistake 5 — Assuming PriorityQueue allows null

It doesn't.

```text
null → NullPointerException
```

---

## Mistake 6 — Confusing PriorityQueue with TreeSet

`PriorityQueue`:

- allows duplicates
- provides priority access

`TreeSet`:

- removes duplicates
- maintains sorted-set semantics

---

## Mistake 7 — Using subtraction for all comparators

Avoid:

```java
(a, b) -> a - b
```

or:

```java
(a, b) -> b - a
```

for general integer comparisons because overflow can produce incorrect results.

Prefer:

```java
Integer.compare(a, b)
```

or:

```java
Comparator.naturalOrder()
```

and:

```java
Comparator.reverseOrder()
```

---

## Mistake 8 — Thinking `contains()` is O(log n)

For `PriorityQueue`, arbitrary search is generally:

```text
O(n)
```

The heap gives efficient access to the root, not arbitrary element lookup.

---

# 35. Quick Revision

```text
PriorityQueue
      ↓
Queue implementation
      ↓
Priority-based ordering
      ↓
Heap
      ↓
Default → Min-Heap behavior
      ↓
Smallest element at head
```

### Core Operations

```text
offer() → O(log n)
poll()  → O(log n)
peek()  → O(1)
```

### Ordering

```text
No Comparator
     ↓
Natural Ordering
     ↓
Min-Heap behavior
```

Custom:

```text
Comparator
     ↓
Custom Priority
```

Max-heap example:

```java
PriorityQueue<Integer> queue =
        new PriorityQueue<>(Comparator.reverseOrder());
```

---

# 36. Final Mental Model

```text
                    PriorityQueue
                         │
                         ▼
                      Queue
                         │
                         ▼
                  Priority Based
                     Ordering
                         │
                         ▼
                       Heap
                         │
              ┌──────────┴──────────┐
              ▼                     ▼
          Min-Heap              Custom Order
              │                     │
         Default Order          Comparator
              │                     │
              ▼                     ▼
       Smallest First           Your Priority
```

### Think of it like this:

```text
Insert:
30
10
20
40
5

          5
        /   \
      10     20
     /  \
   40   30

peek() → 5

poll() → 5
poll() → 10
poll() → 20
poll() → 30
poll() → 40
```

---

# 37. Key Interview Statement

> **`PriorityQueue` is a Java class that implements the `Queue` interface and processes elements according to priority rather than normal FIFO insertion order. It is heap-based and provides min-heap behavior by default, so the smallest element is at the head when natural ordering is used. `offer()` and `poll()` are typically O(log n), while `peek()` is O(1). A custom `Comparator` can be supplied to change the priority order, such as creating max-heap behavior.**

---

## ⭐ One-Line Interview Answer

> **PriorityQueue is a heap-based Queue implementation that efficiently gives access to the highest-priority element.**

---

# 38. Progress

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
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
│
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

**Next:** `09-PriorityQueue/PRACTICE.md`
