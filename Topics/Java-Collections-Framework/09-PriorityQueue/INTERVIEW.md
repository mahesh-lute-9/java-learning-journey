# PriorityQueue — Interview Questions

> Interview-focused revision for `PriorityQueue` in the Java Collections Framework.

---

## Table of Contents

1. [What is PriorityQueue?](#1-what-is-priorityqueue)
2. [How Does PriorityQueue Work?](#2-how-does-priorityqueue-work)
3. [Default Ordering](#3-default-ordering)
4. [Important Methods](#4-important-methods)
5. [add() vs offer()](#5-add-vs-offer)
6. [remove() vs poll()](#6-remove-vs-poll)
7. [element() vs peek()](#7-element-vs-peek)
8. [Does PriorityQueue Maintain FIFO?](#8-does-priorityqueue-maintain-fifo)
9. [Is PriorityQueue Sorted?](#9-is-priorityqueue-sorted)
10. [Does PriorityQueue Allow Duplicates?](#10-does-priorityqueue-allow-duplicates)
11. [Does PriorityQueue Allow null?](#11-does-priorityqueue-allow-null)
12. [Internal Data Structure](#12-internal-data-structure)
13. [Time Complexity](#13-time-complexity)
14. [Min-Heap vs Max-Heap](#14-min-heap-vs-max-heap)
15. [Custom Comparator](#15-custom-comparator)
16. [PriorityQueue with Custom Objects](#16-priorityqueue-with-custom-objects)
17. [Comparable vs Comparator](#17-comparable-vs-comparator)
18. [PriorityQueue vs ArrayDeque](#18-priorityqueue-vs-arraydeque)
19. [PriorityQueue vs TreeSet](#19-priorityqueue-vs-treeset)
20. [PriorityQueue vs LinkedList](#20-priorityqueue-vs-linkedlist)
21. [Thread Safety](#21-thread-safety)
22. [Common Interview Traps](#22-common-interview-traps)
23. [Scenario-Based Questions](#23-scenario-based-questions)
24. [Coding Problems](#24-coding-problems)
25. [Rapid-Fire Questions](#25-rapid-fire-questions)
26. [Must-Know Questions](#26-must-know-questions)
27. [Short Interview Answer](#27-short-interview-answer)
28. [Final Checklist](#28-final-checklist)
29. [Progress](#29-progress)

---

# 1. What is PriorityQueue?

### Q1. What is PriorityQueue in Java?

`PriorityQueue` is a class in `java.util` that implements the `Queue` interface.

Unlike a normal FIFO queue, it processes elements according to their **priority**.

By default, it uses the natural ordering of elements.

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

For integers, the smallest element has the highest priority.

Example:

```text
Input:
30, 10, 20

poll():
10
20
30
```

---

### Q2. Is PriorityQueue a class or an interface?

It is a **class**.

```java
java.util.PriorityQueue
```

---

### Q3. Which interface does PriorityQueue implement?

It directly implements:

```text
Queue
```

And therefore participates in the `Collection` hierarchy.

Conceptually:

```text
Iterable
   ↓
Collection
   ↓
Queue
   ↓
PriorityQueue
```

---

# 2. How Does PriorityQueue Work?

### Q4. What data structure does PriorityQueue use internally?

`PriorityQueue` is implemented using a **heap**, commonly represented internally using an array.

By default, it behaves as a **min-heap**.

The highest-priority element is kept at the head.

For natural ordering:

```text
smallest element → head
```

---

### Q5. Is the internal array completely sorted?

No.

This is a very common interview trap.

The internal representation maintains the **heap property**, not complete sorted order.

Therefore:

```java
System.out.println(pq);
```

does not guarantee sorted output.

To process elements in priority order:

```java
while (!pq.isEmpty()) {
    System.out.println(pq.poll());
}
```

---

### Q6. What is the heap property?

For a min-heap:

```text
parent <= children
```

This guarantees that the smallest element is at the root/head.

It does **not** mean every element is globally sorted.

---

# 3. Default Ordering

### Q7. What is the default behavior of PriorityQueue?

By default, `PriorityQueue` uses the **natural ordering** of its elements.

For example:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(50);
pq.offer(10);
pq.offer(30);
```

The head is:

```text
10
```

---

### Q8. Is PriorityQueue a min-heap by default?

Yes, conceptually.

The least element according to natural ordering is at the head.

```text
Min-Heap:

        10
       /  \
     20    30
    /  \
   40   50
```

---

### Q9. How can you create a max-heap?

Use a reverse comparator:

```java
PriorityQueue<Integer> pq =
        new PriorityQueue<>(Comparator.reverseOrder());
```

Now:

```text
50 → highest priority
40
30
20
10
```

---

# 4. Important Methods

| Method | Purpose | Empty Queue |
|---|---|---|
| `add(e)` | Insert element | Throws if insertion fails |
| `offer(e)` | Insert element | Returns `false` if insertion fails |
| `peek()` | View head | `null` |
| `element()` | View head | `NoSuchElementException` |
| `poll()` | Remove head | `null` |
| `remove()` | Remove head | `NoSuchElementException` |
| `size()` | Number of elements | `0` |
| `isEmpty()` | Check empty | `true` |

---

### Q10. What does `peek()` do?

Returns the head without removing it.

```java
Integer value = pq.peek();
```

If the queue is empty:

```text
null
```

---

### Q11. What does `poll()` do?

Removes and returns the head.

```java
Integer value = pq.poll();
```

If the queue is empty:

```text
null
```

---

### Q12. What does `remove()` do?

Removes and returns the head.

If the queue is empty, it throws:

```text
NoSuchElementException
```

---

# 5. add() vs offer()

### Q13. Difference between `add()` and `offer()`?

Both attempt to insert an element.

```java
pq.add(10);
pq.offer(20);
```

The difference comes from the general `Queue` contract:

| Method | Failure behavior |
|---|---|
| `add()` | May throw an exception |
| `offer()` | Returns `false` |

For an unbounded `PriorityQueue`, normal insertion does not ordinarily fail because of capacity.

---

### Interview Answer

> Both `add()` and `offer()` insert elements. `offer()` follows the queue-style contract of returning `false` if insertion cannot be performed, while `add()` may throw an exception.

---

# 6. remove() vs poll()

### Q14. Difference between `remove()` and `poll()`?

Both remove the head.

| Method | Empty Queue |
|---|---|
| `remove()` | Throws `NoSuchElementException` |
| `poll()` | Returns `null` |

Example:

```java
pq.poll();
```

is safer when an empty queue is possible and you want a sentinel value rather than an exception.

---

# 7. element() vs peek()

### Q15. Difference between `element()` and `peek()`?

Both return the head without removing it.

| Method | Empty Queue |
|---|---|
| `element()` | Throws `NoSuchElementException` |
| `peek()` | Returns `null` |

---

# 8. Does PriorityQueue Maintain FIFO?

### Q16. Is PriorityQueue FIFO?

**No.**

A normal FIFO queue processes:

```text
First In → First Out
```

A PriorityQueue processes:

```text
Highest Priority → First
```

Example:

```java
pq.offer(30);
pq.offer(10);
pq.offer(20);
```

Even though `30` was inserted first:

```text
poll() → 10
```

---

### Q17. When should you use PriorityQueue instead of a normal Queue?

Use `PriorityQueue` when the next element to process depends on **priority** rather than insertion order.

Examples:

- CPU scheduling
- Emergency handling
- Task scheduling
- Dijkstra's algorithm
- A* search
- Top-K problems
- Event processing

---

# 9. Is PriorityQueue Sorted?

### Q18. Does PriorityQueue keep all elements sorted?

**No.**

Only the head is guaranteed to be the highest-priority element.

For example:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(40);
pq.offer(10);
pq.offer(30);
pq.offer(20);
```

This:

```java
System.out.println(pq);
```

does not guarantee:

```text
[10, 20, 30, 40]
```

---

### Q19. How do you get elements in priority order?

Repeatedly call `poll()`:

```java
while (!pq.isEmpty()) {
    System.out.println(pq.poll());
}
```

For natural ordering:

```text
10
20
30
40
```

---

### Q20. Does iterator() return elements in sorted order?

No.

The `Iterator` of a `PriorityQueue` does **not** guarantee sorted traversal.

This is an important interview point.

---

# 10. Does PriorityQueue Allow Duplicates?

### Q21. Can PriorityQueue contain duplicate elements?

**Yes.**

Example:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(10);
pq.offer(10);
pq.offer(20);
```

The queue can contain:

```text
10
10
20
```

---

### Q22. Does PriorityQueue remove duplicates automatically?

No.

Unlike a `Set`, `PriorityQueue` allows duplicate elements.

---

# 11. Does PriorityQueue Allow null?

### Q23. Can PriorityQueue contain null?

**No.**

```java
pq.offer(null);
```

results in:

```text
NullPointerException
```

---

### Q24. Why doesn't PriorityQueue allow null?

The queue needs to compare elements to maintain priority.

A `null` value does not provide a natural ordering comparable with ordinary elements.

Therefore `PriorityQueue` does not permit `null` elements.

---

# 12. Internal Data Structure

### Q25. How is a binary heap represented?

A binary heap can be represented efficiently using an array.

For zero-based indexing:

```text
Parent:
(i - 1) / 2

Left Child:
2 * i + 1

Right Child:
2 * i + 2
```

Example:

```text
             10
           /    \
         20      30
        /  \    /  \
      40   50  60   70
```

Array representation:

```text
[10, 20, 30, 40, 50, 60, 70]
```

---

### Q26. Why use an array for a heap?

A complete binary tree can be represented compactly in an array without requiring explicit node objects or pointers.

This makes heap operations efficient.

---

### Q27. What happens when an element is inserted?

Conceptually:

1. Add the element at the end.
2. Compare it with its parent.
3. Move it upward if necessary.
4. Continue until the heap property is restored.

This process is called:

```text
sift-up
```

or

```text
bubble-up
```

---

### Q28. What happens during poll()?

Conceptually:

1. Remove the root/head.
2. Move the last element to the root.
3. Compare it with its children.
4. Move it downward if necessary.
5. Continue until the heap property is restored.

This is commonly called:

```text
sift-down
```

or

```text
heapify-down
```

---

# 13. Time Complexity

### Q29. What is the complexity of `offer()`?

Typically:

```text
O(log n)
```

because the element may move upward through the heap.

---

### Q30. What is the complexity of `poll()`?

Typically:

```text
O(log n)
```

because removing the head requires restoring the heap property.

---

### Q31. What is the complexity of `peek()`?

```text
O(1)
```

The head is directly accessible.

---

### Q32. What is the complexity of `remove(Object)`?

Typically:

```text
O(n)
```

because finding an arbitrary element generally requires scanning the heap.

After locating it, restoring the heap property takes additional logarithmic work, but the search dominates.

---

### Q33. What is the complexity of `contains()`?

Typically:

```text
O(n)
```

A heap is not designed for efficient arbitrary-element search.

---

### Q34. What is the complexity of `size()`?

```text
O(1)
```

---

### Q35. What is the complexity of `isEmpty()`?

```text
O(1)
```

---

### Complexity Table

| Operation | Typical Complexity |
|---|---:|
| `offer()` | `O(log n)` |
| `add()` | `O(log n)` |
| `peek()` | `O(1)` |
| `poll()` | `O(log n)` |
| `remove()` | `O(log n)` |
| `remove(Object)` | `O(n)` |
| `contains()` | `O(n)` |
| `size()` | `O(1)` |
| `isEmpty()` | `O(1)` |

---

# 14. Min-Heap vs Max-Heap

## Min-Heap

Default behavior:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

Smallest element has highest priority.

```text
10
20
30
40
50
```

---

## Max-Heap

Use:

```java
PriorityQueue<Integer> pq =
        new PriorityQueue<>(Comparator.reverseOrder());
```

Largest element has highest priority.

```text
50
40
30
20
10
```

---

### Q36. How do you implement a max-heap using PriorityQueue?

```java
PriorityQueue<Integer> maxHeap =
        new PriorityQueue<>(Comparator.reverseOrder());
```

---

### Q37. Why not use `(a, b) -> b - a`?

Because integer subtraction can overflow.

Avoid:

```java
(a, b) -> b - a
```

Prefer:

```java
(a, b) -> Integer.compare(b, a)
```

or:

```java
Comparator.reverseOrder()
```

---

# 15. Custom Comparator

### Q38. Can PriorityQueue use a custom Comparator?

Yes.

Example:

```java
PriorityQueue<Integer> pq =
        new PriorityQueue<>(
            (a, b) -> Integer.compare(b, a)
        );
```

---

### Q39. Why use a custom Comparator?

When the default natural ordering does not represent the priority you need.

Examples:

```text
Highest marks
Lowest price
Highest salary
Shortest execution time
Highest priority number
Earliest deadline
```

---

### Q40. Can you create a PriorityQueue of custom objects?

Yes.

Example:

```java
class Task {
    String name;
    int priority;

    Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
}
```

Then:

```java
PriorityQueue<Task> pq =
        new PriorityQueue<>(
            Comparator.comparingInt(task -> task.priority)
        );
```

---

### Q41. What happens if custom objects don't provide ordering?

If neither:

- natural ordering via `Comparable`, nor
- an appropriate `Comparator`

is available, the queue may fail when it needs to compare elements, typically with `ClassCastException`.

---

# 16. PriorityQueue with Custom Objects

### Q42. How would you prioritize students by marks?

```java
PriorityQueue<Student> pq =
        new PriorityQueue<>(
            (a, b) -> Integer.compare(b.marks, a.marks)
        );
```

This creates highest-marks-first behavior.

---

### Q43. How do you implement multiple priority rules?

Use comparator chaining.

Example:

```java
Comparator<Task> comparator =
        Comparator.comparingInt((Task t) -> t.priority)
                  .thenComparingInt(t -> t.duration)
                  .thenComparing(t -> t.name);
```

This means:

1. Lower priority number first.
2. If equal, shorter duration first.
3. If still equal, alphabetical name order.

---

# 17. Comparable vs Comparator

### Q44. What is the difference between Comparable and Comparator?

| Comparable | Comparator |
|---|---|
| Defines natural ordering | Defines external/custom ordering |
| Implemented by the class | Usually separate object/lambda |
| Method: `compareTo()` | Method: `compare()` |
| One primary natural order | Multiple possible orderings |

Example:

```java
class Student implements Comparable<Student> {

    int marks;

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.marks, other.marks);
    }
}
```

Or use a Comparator:

```java
Comparator<Student> byMarksDescending =
        (a, b) -> Integer.compare(b.marks, a.marks);
```

---

### Q45. Which should you use with PriorityQueue?

Use:

- `Comparable` when the class has a clear natural ordering.
- `Comparator` when you need a custom ordering or multiple different orderings.

---

# 18. PriorityQueue vs ArrayDeque

### Q46. Difference between PriorityQueue and ArrayDeque?

| Feature | PriorityQueue | ArrayDeque |
|---|---|---|
| Main behavior | Priority-based | FIFO / LIFO |
| Internal structure | Heap | Resizable array/deque |
| Default head | Smallest element | First inserted element when used as Queue |
| `offer()` | `O(log n)` | `O(1)` amortized |
| `poll()` | `O(log n)` | `O(1)` amortized |
| `peek()` | `O(1)` | `O(1)` |
| Duplicates | Yes | Yes |
| `null` | No | No |
| Thread-safe | No | No |

---

### Q47. Which should you choose for FIFO processing?

Use:

```text
ArrayDeque
```

---

### Q48. Which should you choose for priority-based processing?

Use:

```text
PriorityQueue
```

---

# 19. PriorityQueue vs TreeSet

### Q49. Difference between PriorityQueue and TreeSet?

| Feature | PriorityQueue | TreeSet |
|---|---|---|
| Purpose | Priority access | Sorted unique collection |
| Duplicates | Allowed | Not allowed |
| Internal structure | Heap | Balanced search tree |
| Head access | `O(1)` | `O(log n)` typical |
| Insert | `O(log n)` | `O(log n)` |
| Remove head | `O(log n)` | `O(log n)` |
| Sorted iteration | No guarantee | Yes |
| Arbitrary search | `O(n)` | `O(log n)` typical |

---

### Q50. If you need sorted traversal, which should you choose?

Usually:

```text
TreeSet
```

because it maintains elements in sorted order.

---

### Q51. If duplicates are important and you only need repeated access to the smallest element?

Use:

```text
PriorityQueue
```

---

# 20. PriorityQueue vs LinkedList

### Q52. Can LinkedList implement Queue behavior?

Yes.

```java
Queue<Integer> queue = new LinkedList<>();
```

It provides FIFO queue behavior.

---

### Q53. Why use PriorityQueue instead?

When processing depends on priority.

Example:

```text
LinkedList Queue:
A → B → C → D

PriorityQueue:
Priority 1 → Priority 2 → Priority 3
```

---

# 21. Thread Safety

### Q54. Is PriorityQueue thread-safe?

No.

`PriorityQueue` is **not synchronized**.

---

### Q55. Can multiple threads safely modify the same PriorityQueue without synchronization?

Not by relying on `PriorityQueue` itself.

If concurrent access is required, use an appropriate concurrent data structure such as:

```java
PriorityBlockingQueue
```

from `java.util.concurrent`.

---

### Q56. What is PriorityBlockingQueue?

`PriorityBlockingQueue` is a concurrent priority-based queue designed for use by multiple threads.

It is useful when producers and consumers need thread-safe priority-based coordination.

---

# 22. Common Interview Traps

## Trap 1 — "PriorityQueue is FIFO"

Wrong.

```text
PriorityQueue → priority-based
```

---

## Trap 2 — "PriorityQueue is fully sorted"

Wrong.

It maintains a heap, not a fully sorted collection.

---

## Trap 3 — "Printing PriorityQueue gives sorted output"

Wrong.

```java
System.out.println(pq);
```

does not guarantee sorted order.

---

## Trap 4 — "Iterator gives sorted order"

Wrong.

The iterator does not guarantee priority order.

---

## Trap 5 — "PriorityQueue doesn't allow duplicates"

Wrong.

Duplicates are allowed.

---

## Trap 6 — "PriorityQueue allows null"

Wrong.

`null` elements are not permitted.

---

## Trap 7 — "peek() removes the element"

Wrong.

`peek()` only views the head.

---

## Trap 8 — "poll() throws when empty"

Wrong.

`poll()` returns `null`.

`remove()` throws `NoSuchElementException`.

---

## Trap 9 — "PriorityQueue is thread-safe"

Wrong.

It is not thread-safe.

---

## Trap 10 — "The entire internal array is sorted"

Wrong.

The internal representation follows heap ordering.

---

# 23. Scenario-Based Questions

## Q57. You need to process emergency patients based on severity. Which collection?

**Answer:**

```text
PriorityQueue
```

---

## Q58. You need to process tasks in insertion order. Which collection?

**Answer:**

```text
Queue / ArrayDeque
```

---

## Q59. You need the 10 largest values from millions of numbers.

What would you use?

**Answer:**

A **min-heap of size 10**.

---

## Q60. You need the 10 smallest values from millions of numbers.

What would you use?

**Answer:**

A **max-heap of size 10**.

---

## Q61. You need unique sorted elements.

What would you use?

**Answer:**

```text
TreeSet
```

---

## Q62. You need to repeatedly retrieve the smallest element.

What would you use?

**Answer:**

```text
PriorityQueue
```

---

## Q63. You need a priority queue shared safely between producer and consumer threads.

What would you consider?

**Answer:**

```text
PriorityBlockingQueue
```

---

# 24. Coding Problems

## Q64. Find Kth Largest Element

Given:

```text
[3, 2, 1, 5, 6, 4]
```

and:

```text
k = 2
```

Output:

```text
5
```

### Preferred Approach

Maintain a min-heap of size `k`.

Complexity:

```text
O(n log k)
```

---

## Q65. Find Kth Smallest Element

Given:

```text
[7, 10, 4, 3, 20, 15]
```

and:

```text
k = 3
```

Output:

```text
7
```

A min-heap can solve this directly.

---

## Q66. Top K Frequent Elements

Given:

```text
[1, 1, 1, 2, 2, 3]
```

Find the top two frequent values.

Output:

```text
[1, 2]
```

Typical approach:

```text
HashMap
+
PriorityQueue
```

---

## Q67. Merge K Sorted Lists

Given:

```text
[1,4,5]
[1,3,4]
[2,6]
```

Output:

```text
[1,1,2,3,4,4,5,6]
```

Typical approach:

- Put the first element of each list into a min-heap.
- Remove the smallest.
- Add the next element from that list.
- Continue until all lists are processed.

---

## Q68. Find Median from Data Stream

Use two heaps:

```text
Max-Heap → smaller half
Min-Heap → larger half
```

Maintain the heaps so their sizes differ by at most one.

This allows efficient median retrieval.

---

## Q69. Dijkstra's Algorithm

Why is PriorityQueue useful in Dijkstra's algorithm?

Because we repeatedly need the unprocessed vertex with the smallest tentative distance.

A min-heap provides efficient access to that minimum-distance entry.

---

# 25. Rapid-Fire Questions

### Q70. Package?

```text
java.util
```

### Q71. Class or interface?

```text
Class
```

### Q72. Implements?

```text
Queue
```

### Q73. Default ordering?

```text
Natural ordering
```

### Q74. Default heap type?

```text
Min-heap behavior
```

### Q75. Highest-priority default element?

```text
Smallest element
```

### Q76. Allows duplicates?

```text
Yes
```

### Q77. Allows null?

```text
No
```

### Q78. Thread-safe?

```text
No
```

### Q79. `peek()` complexity?

```text
O(1)
```

### Q80. `offer()` complexity?

```text
O(log n)
```

### Q81. `poll()` complexity?

```text
O(log n)
```

### Q82. `contains()` complexity?

```text
O(n)
```

### Q83. `remove(Object)` complexity?

```text
O(n)
```

### Q84. How to create max-heap?

```java
new PriorityQueue<>(Comparator.reverseOrder());
```

### Q85. Does iteration guarantee sorted order?

```text
No
```

### Q86. Does printing guarantee sorted order?

```text
No
```

### Q87. Does `poll()` remove the highest-priority element?

```text
Yes
```

### Q88. Does `peek()` remove the element?

```text
No
```

### Q89. Empty `poll()`?

```text
null
```

### Q90. Empty `remove()`?

```text
NoSuchElementException
```

### Q91. Empty `peek()`?

```text
null
```

### Q92. Empty `element()`?

```text
NoSuchElementException
```

---

# 26. Must-Know Questions

Before an interview, make sure you can answer these without hesitation:

### ⭐⭐⭐⭐⭐

1. What is `PriorityQueue`?
2. How is it different from a normal Queue?
3. What is its default ordering?
4. What data structure does it use?
5. Is it a min-heap or max-heap by default?
6. How do you create a max-heap?
7. Is it fully sorted?
8. Is iteration sorted?
9. Does it allow duplicates?
10. Does it allow `null`?
11. Difference between `peek()` and `poll()`.
12. Difference between `remove()` and `poll()`.
13. Difference between `element()` and `peek()`.
14. What is the complexity of `offer()`?
15. What is the complexity of `poll()`?
16. What is the complexity of `peek()`?
17. What is the complexity of `contains()`?
18. How do you use a custom `Comparator`?
19. How do you use `PriorityQueue` with custom objects?
20. `PriorityQueue` vs `ArrayDeque`.
21. `PriorityQueue` vs `TreeSet`.
22. Is `PriorityQueue` thread-safe?
23. When would you use `PriorityBlockingQueue`?
24. How is PriorityQueue useful in Top-K problems?
25. How is it useful in Dijkstra's algorithm?

---

# 27. Short Interview Answer

If an interviewer asks:

> "What is PriorityQueue?"

A strong short answer is:

> `PriorityQueue` is a Java class that implements the `Queue` interface and processes elements according to priority rather than normal FIFO order. It is heap-based and provides min-heap behavior by default, so the least element according to natural ordering is at the head. `offer()` and `poll()` typically take `O(log n)`, while `peek()` takes `O(1)`. It allows duplicates, does not allow `null`, and a custom `Comparator` can be used to define different priority rules.

---

# 28. Final Checklist

## Basics

- [ ] I know what `PriorityQueue` is.
- [ ] I know its package.
- [ ] I know it implements `Queue`.
- [ ] I understand priority-based processing.
- [ ] I know it is not FIFO.

## Ordering

- [ ] I understand natural ordering.
- [ ] I understand min-heap behavior.
- [ ] I can create a max-heap.
- [ ] I can use a custom `Comparator`.
- [ ] I understand `Comparable` vs `Comparator`.

## Methods

- [ ] `add()`
- [ ] `offer()`
- [ ] `peek()`
- [ ] `element()`
- [ ] `poll()`
- [ ] `remove()`

## Internals

- [ ] I understand the heap structure.
- [ ] I know parent/child index formulas.
- [ ] I understand sift-up.
- [ ] I understand sift-down.
- [ ] I know the internal structure is not fully sorted.

## Complexity

- [ ] `offer()` → `O(log n)`
- [ ] `poll()` → `O(log n)`
- [ ] `peek()` → `O(1)`
- [ ] `contains()` → `O(n)`
- [ ] `remove(Object)` → `O(n)`

## Important Rules

- [ ] Duplicates are allowed.
- [ ] `null` is not allowed.
- [ ] Iteration is not guaranteed to be sorted.
- [ ] Printing is not guaranteed to be sorted.
- [ ] PriorityQueue is not thread-safe.

## Problem Solving

- [ ] Kth smallest
- [ ] Kth largest
- [ ] Top K elements
- [ ] Top K frequent
- [ ] Merge K sorted lists
- [ ] Running median
- [ ] Task scheduling
- [ ] Dijkstra's algorithm

---

# 29. Progress

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

**PriorityQueue is now complete. Next topic: `10-Deque` → `NOTES.md`.**
