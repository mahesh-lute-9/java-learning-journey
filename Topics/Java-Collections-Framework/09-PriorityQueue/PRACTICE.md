# PriorityQueue — Practice

> Practice file for `PriorityQueue` in the Java Collections Framework.

---

## Table of Contents

1. [Practice Goals](#1-practice-goals)
2. [Basic PriorityQueue Operations](#2-basic-priorityqueue-operations)
3. [Queue Method Pairs](#3-queue-method-pairs)
4. [Natural Ordering](#4-natural-ordering)
5. [Understand Priority Order](#5-understand-priority-order)
6. [Heap vs Sorted Order](#6-heap-vs-sorted-order)
7. [Custom Comparator](#7-custom-comparator)
8. [PriorityQueue with Custom Objects](#8-priorityqueue-with-custom-objects)
9. [Null Values](#9-null-values)
10. [Iteration and Traversal](#10-iteration-and-traversal)
11. [Intermediate Problems](#11-intermediate-problems)
12. [Code Prediction Questions](#12-code-prediction-questions)
13. [Scenario-Based Practice](#13-scenario-based-practice)
14. [Challenge Problems](#14-challenge-problems)
15. [Practice Checklist](#15-practice-checklist)
16. [Final Goal](#16-final-goal)
17. [Progress](#17-progress)

---

# 1. Practice Goals

By the end of this practice, you should be able to:

- [ ] Create a `PriorityQueue`
- [ ] Add elements using `offer()` and `add()`
- [ ] Read the head using `peek()`
- [ ] Remove the head using `poll()`
- [ ] Understand why `PriorityQueue` is not FIFO
- [ ] Understand natural ordering
- [ ] Create a max-heap using `Comparator.reverseOrder()`
- [ ] Use custom `Comparator`
- [ ] Work with custom objects
- [ ] Understand duplicate elements
- [ ] Understand why `null` is not allowed
- [ ] Understand why iteration is not sorted
- [ ] Solve Top-K problems
- [ ] Solve Kth smallest/largest problems
- [ ] Understand two-heap problems
- [ ] Choose `PriorityQueue` for real-world scenarios

---

# 2. Basic PriorityQueue Operations

## 2.1 Create a PriorityQueue

### Task

Create an integer `PriorityQueue` and add:

```text
40, 10, 30, 20
```

Then print the head.

### Expected

```text
10
```

### Try It

```java
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(40);
        pq.offer(10);
        pq.offer(30);
        pq.offer(20);

        System.out.println(pq.peek());
    }
}
```

---

## 2.2 Add Elements

Practice both:

```java
pq.add(50);
pq.offer(60);
```

### Questions

1. What is the difference between `add()` and `offer()`?
2. Which element becomes the head?
3. Does `add()` sort the entire queue?

---

## 2.3 Peek

Given:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(30);
pq.offer(10);
pq.offer(20);
```

Predict:

```java
System.out.println(pq.peek());
```

### Answer

```text
10
```

---

## 2.4 Poll

Given:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(30);
pq.offer(10);
pq.offer(20);

System.out.println(pq.poll());
System.out.println(pq.poll());
System.out.println(pq.poll());
```

### Expected Output

```text
10
20
30
```

---

## 2.5 Size and Empty Check

Practice:

```java
pq.size();
pq.isEmpty();
```

### Task

Create a queue, add five elements, remove two elements, and print:

- size before removal
- removed elements
- size after removal
- whether queue is empty

---

# 3. Queue Method Pairs

PriorityQueue inherits the standard `Queue` operations.

Practice these pairs carefully:

| Operation | Method 1 | Method 2 |
|---|---|---|
| Insert | `add()` | `offer()` |
| Remove head | `remove()` | `poll()` |
| View head | `element()` | `peek()` |

---

## 3.1 `add()` vs `offer()`

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.add(10);
pq.offer(20);
```

### Practice Questions

- Which elements are inserted?
- Which method is generally preferred when using the `Queue` API?
- What happens when insertion cannot be performed?

---

## 3.2 `remove()` vs `poll()`

### Task

Test both methods on an empty queue.

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

System.out.println(pq.poll());
```

Then test:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

System.out.println(pq.remove());
```

### Observe

- `poll()` returns `null` when empty.
- `remove()` throws `NoSuchElementException`.

---

## 3.3 `peek()` vs `element()`

Test:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

System.out.println(pq.peek());
```

Then:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

System.out.println(pq.element());
```

### Observe

- `peek()` returns `null` when empty.
- `element()` throws `NoSuchElementException`.

---

# 4. Natural Ordering

By default, `PriorityQueue` uses the natural ordering of its elements.

---

## 4.1 Integer PriorityQueue

### Task

Insert:

```text
50, 5, 30, 10, 20
```

Then repeatedly call `poll()`.

### Expected

```text
5
10
20
30
50
```

---

## 4.2 String PriorityQueue

Create:

```java
PriorityQueue<String> pq = new PriorityQueue<>();
```

Add:

```text
Mango
Apple
Orange
Banana
```

Then repeatedly call `poll()`.

### Expected

```text
Apple
Banana
Mango
Orange
```

---

## 4.3 Duplicate Elements

Try:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(10);
pq.offer(5);
pq.offer(10);
pq.offer(5);
pq.offer(20);
```

Repeatedly remove elements.

### Question

Does `PriorityQueue` allow duplicates?

### Expected

Yes.

Possible removal order:

```text
5
5
10
10
20
```

---

# 5. Understand Priority Order

## Exercise 1

Given:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(40);
pq.offer(15);
pq.offer(30);
pq.offer(5);
pq.offer(20);
```

What does:

```java
pq.peek();
```

return?

### Answer

```text
5
```

---

## Exercise 2

What will this print?

```java
while (!pq.isEmpty()) {
    System.out.println(pq.poll());
}
```

### Answer

```text
5
15
20
30
40
```

---

## Exercise 3

Insert:

```text
100, 50, 75, 25, 10
```

Predict the sequence returned by repeated `poll()`.

### Answer

```text
10
25
50
75
100
```

---

## Exercise 4 — Duplicates

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(20);
pq.offer(10);
pq.offer(20);
pq.offer(5);
pq.offer(10);
```

Predict the `poll()` sequence.

### Answer

```text
5
10
10
20
20
```

---

# 6. Heap vs Sorted Order

This is one of the most important PriorityQueue concepts.

Consider:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(40);
pq.offer(10);
pq.offer(30);
pq.offer(20);
```

Do **not** assume:

```java
System.out.println(pq);
```

will print:

```text
[10, 20, 30, 40]
```

The internal representation is a **heap**, not a fully sorted array.

### Important Rule

```text
peek()  → gives the highest-priority element
poll()  → repeatedly gives elements in priority order
iterator() → does NOT guarantee sorted order
```

---

## Practice Task

Compare:

```java
System.out.println(pq);
```

with:

```java
while (!pq.isEmpty()) {
    System.out.println(pq.poll());
}
```

Observe the difference.

---

# 7. Custom Comparator

A custom `Comparator` allows you to define a different priority.

---

## 7.1 Create a Max-Heap

By default:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
```

behaves like a min-heap.

Create a max-heap:

```java
PriorityQueue<Integer> pq =
        new PriorityQueue<>(Comparator.reverseOrder());
```

### Task

Insert:

```text
10, 50, 20, 40, 30
```

Repeatedly call `poll()`.

### Expected

```text
50
40
30
20
10
```

---

## 7.2 Descending Order

Practice:

```java
PriorityQueue<Integer> pq =
        new PriorityQueue<>((a, b) -> Integer.compare(b, a));
```

Verify that the largest element is always removed first.

---

## 7.3 Avoid Comparator Subtraction

Avoid:

```java
(a, b) -> b - a
```

because subtraction can overflow for extreme integer values.

Prefer:

```java
(a, b) -> Integer.compare(b, a)
```

or:

```java
Comparator.reverseOrder()
```

---

## 7.4 Custom String Priority

Create a queue where strings are processed according to descending alphabetical order.

Example:

```text
Orange
Mango
Banana
Apple
```

Expected removal:

```text
Orange
Mango
Banana
Apple
```

---

# 8. PriorityQueue with Custom Objects

## 8.1 Student Priority

Create:

```java
class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}
```

Create a PriorityQueue that gives priority to the student with the **highest marks**.

### Goal

Given:

```text
Rahul  → 75
Amit   → 92
Neha   → 85
Priya  → 95
```

Expected removal order:

```text
Priya
Amit
Neha
Rahul
```

### Hint

```java
PriorityQueue<Student> pq =
        new PriorityQueue<>(
            (a, b) -> Integer.compare(b.marks, a.marks)
        );
```

---

## 8.2 Task Priority

Create:

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

Assume:

```text
1 = highest priority
5 = lowest priority
```

Insert:

```text
Email    → 3
Bug Fix  → 1
Meeting  → 2
Testing  → 4
```

Expected order:

```text
Bug Fix
Meeting
Email
Testing
```

---

## 8.3 Multiple Priority Fields

Create a `Task` with:

```text
name
priority
executionTime
```

Rules:

1. Smaller priority number comes first.
2. If priority is equal, smaller execution time comes first.

Example:

```text
Task A → priority 2, time 30
Task B → priority 1, time 50
Task C → priority 2, time 10
Task D → priority 1, time 20
```

Expected order:

```text
Task D
Task B
Task C
Task A
```

### Hint

Use:

```java
Comparator.comparingInt((Task t) -> t.priority)
          .thenComparingInt(t -> t.executionTime);
```

---

# 9. Null Values

Try:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(null);
```

### Expected

```text
NullPointerException
```

### Important

`PriorityQueue` does **not** permit `null` elements.

---

# 10. Iteration and Traversal

## 10.1 For-Each Loop

Try:

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(40);
pq.offer(10);
pq.offer(30);
pq.offer(20);

for (Integer value : pq) {
    System.out.println(value);
}
```

### Question

Is the output guaranteed to be:

```text
10
20
30
40
```

### Answer

No.

`PriorityQueue` iteration does not guarantee priority order.

---

## 10.2 Correct Way to Get Priority Order

Use repeated `poll()`:

```java
while (!pq.isEmpty()) {
    System.out.println(pq.poll());
}
```

This processes elements according to queue priority.

---

## 10.3 Important Practice

Remember:

```text
PriorityQueue ≠ sorted collection
```

It guarantees access to the highest-priority head, not sorted iteration.

---

# 11. Intermediate Problems

## 11.1 Find Kth Smallest Element

### Problem

Given:

```text
10, 30, 20, 5, 15, 25
```

Find the 3rd smallest element using `PriorityQueue`.

### Expected

```text
15
```

### Approach

1. Insert all elements.
2. Call `poll()` three times.
3. The third removed element is the answer.

---

## 11.2 Find Kth Largest Element

### Problem

Given:

```text
10, 30, 20, 5, 15, 25
```

Find the 2nd largest element.

### Expected

```text
25
```

### Hint

Use a max-heap:

```java
PriorityQueue<Integer> pq =
        new PriorityQueue<>(Comparator.reverseOrder());
```

---

## 11.3 Top K Largest Elements

### Problem

Given:

```text
10, 50, 20, 40, 30, 60, 70
```

Find the top 3 largest elements.

### Expected

```text
70
60
50
```

### Challenge

Try solving it using a **min-heap of size K** instead of storing all elements.

---

## 11.4 Top K Smallest Elements

Given:

```text
50, 10, 40, 20, 30, 5, 60
```

Find the 3 smallest elements.

### Expected

```text
5
10
20
```

---

## 11.5 Top K Frequent Elements

Given:

```text
1, 1, 1, 2, 2, 3
```

Find the top 2 most frequent elements.

### Expected

```text
1
2
```

### Hint

Use:

```text
HashMap + PriorityQueue
```

---

## 11.6 Merge K Sorted Arrays

Given:

```text
Array 1: 1, 4, 7
Array 2: 2, 5, 8
Array 3: 3, 6, 9
```

Merge them into:

```text
1, 2, 3, 4, 5, 6, 7, 8, 9
```

### Hint

Use a `PriorityQueue` containing the current smallest element from each array.

---

## 11.7 Running Median

Given the stream:

```text
5
15
1
3
```

Find the median after each insertion.

Expected:

```text
5     → 5
15    → 10
1     → 5
3     → 4
```

### Hint

Use two heaps:

```text
Max-Heap → smaller half
Min-Heap → larger half
```

---

## 11.8 Task Scheduler

Given tasks with priorities:

```text
Backup    → 3
Database  → 1
Email     → 4
Security  → 2
```

Process tasks from highest priority to lowest.

Expected:

```text
Database
Security
Backup
Email
```

---

# 12. Code Prediction Questions

## Question 1

What is the output?

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.add(30);
pq.add(10);
pq.add(20);

System.out.println(pq.peek());
```

<details>
<summary>Answer</summary>

```text
10
```

</details>

---

## Question 2

What is the output?

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(30);
pq.offer(10);
pq.offer(20);

System.out.println(pq.poll());
System.out.println(pq.peek());
```

<details>
<summary>Answer</summary>

```text
10
20
```

</details>

---

## Question 3

What is the output?

```java
PriorityQueue<Integer> pq =
        new PriorityQueue<>(Comparator.reverseOrder());

pq.offer(10);
pq.offer(50);
pq.offer(20);

System.out.println(pq.poll());
```

<details>
<summary>Answer</summary>

```text
50
```

</details>

---

## Question 4

What happens?

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(null);
```

<details>
<summary>Answer</summary>

```text
NullPointerException
```

</details>

---

## Question 5

Does this guarantee sorted output?

```java
for (Integer value : pq) {
    System.out.println(value);
}
```

<details>
<summary>Answer</summary>

No.

PriorityQueue iteration does not guarantee sorted order.

</details>

---

## Question 6

What is the output?

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(40);
pq.offer(10);
pq.offer(30);
pq.offer(20);

while (!pq.isEmpty()) {
    System.out.print(pq.poll() + " ");
}
```

### Answer

```text
10 20 30 40
```

---

# 13. Scenario-Based Practice

## Scenario 1 — Hospital Emergency Queue

Patients have different emergency levels.

```text
Patient A → Priority 3
Patient B → Priority 1
Patient C → Priority 2
```

### Question

Which data structure would you use?

### Answer

`PriorityQueue`

Because patients should be processed based on priority rather than arrival order.

---

## Scenario 2 — Normal Print Queue

Documents should be printed in the exact order they were submitted.

### Question

Should you use `PriorityQueue`?

### Answer

No.

A FIFO queue such as `ArrayDeque` is more appropriate.

---

## Scenario 3 — CPU Task Scheduling

Tasks have different priorities.

```text
Task A → 4
Task B → 1
Task C → 2
Task D → 3
```

Process the highest-priority task first.

### Best Choice

```text
PriorityQueue
```

---

## Scenario 4 — Keep the 10 Largest Numbers

A stream contains millions of integers.

You only need the 10 largest values.

### Question

Should you store everything?

### Better Approach

Use a **min-heap of size 10**.

When a new number arrives:

1. Add it to the heap.
2. If size exceeds 10, remove the smallest.
3. The heap contains the current top 10 values.

---

## Scenario 5 — Need Sorted Unique Elements

You need:

- sorted order
- no duplicates
- efficient ordered operations

### Better Choice

```text
TreeSet
```

Not `PriorityQueue`.

---

# 14. Challenge Problems

## Challenge 1 — Kth Largest Element

Write:

```java
static int kthLargest(int[] nums, int k)
```

Example:

```text
Input:
nums = [3, 2, 1, 5, 6, 4]
k = 2

Output:
5
```

---

## Challenge 2 — Kth Smallest Element

Write:

```java
static int kthSmallest(int[] nums, int k)
```

Example:

```text
Input:
[7, 10, 4, 3, 20, 15]
k = 3

Output:
7
```

---

## Challenge 3 — Top K Frequent

Write:

```java
static int[] topKFrequent(int[] nums, int k)
```

Example:

```text
Input:
[1, 1, 1, 2, 2, 3]
k = 2

Output:
[1, 2]
```

---

## Challenge 4 — Merge K Sorted Lists

Given:

```text
[1,4,5]
[1,3,4]
[2,6]
```

Return:

```text
[1,1,2,3,4,4,5,6]
```

### Goal

Solve it using `PriorityQueue`.

---

## Challenge 5 — K Closest Numbers

Given:

```text
nums = [1, 2, 3, 4, 5]
target = 3
k = 2
```

Find the two numbers closest to the target.

Possible result:

```text
2, 3
```

### Goal

Use a custom comparator with `PriorityQueue`.

---

## Challenge 6 — Custom Task Scheduler

Create a `Task` class:

```java
class Task {
    String name;
    int priority;
    int duration;
}
```

Rules:

1. Lower priority number comes first.
2. If priority is equal, shorter duration comes first.
3. If both are equal, process alphabetically by task name.

Implement the `PriorityQueue`.

---

## Challenge 7 — Running Median

Implement:

```java
static void addNumber(int number)
```

and:

```java
static double findMedian()
```

Use:

```text
Max-Heap
+
Min-Heap
```

---

# 15. Practice Checklist

## Basics

- [ ] Create `PriorityQueue`
- [ ] Add elements
- [ ] Remove elements
- [ ] Peek at head
- [ ] Check size
- [ ] Check empty state

## Queue API

- [ ] Understand `add()` vs `offer()`
- [ ] Understand `remove()` vs `poll()`
- [ ] Understand `element()` vs `peek()`

## Ordering

- [ ] Understand min-heap behavior
- [ ] Understand natural ordering
- [ ] Understand custom ordering
- [ ] Create a max-heap
- [ ] Use `Comparator.reverseOrder()`

## Objects

- [ ] Create PriorityQueue of custom objects
- [ ] Sort by one field
- [ ] Sort by multiple fields
- [ ] Add tie-breaking rules

## Important Concepts

- [ ] Duplicates are allowed
- [ ] `null` is not allowed
- [ ] PriorityQueue is not FIFO
- [ ] PriorityQueue is not fully sorted
- [ ] Iteration is not guaranteed to be sorted
- [ ] `poll()` gives elements according to priority

## Problem Solving

- [ ] Kth smallest
- [ ] Kth largest
- [ ] Top K largest
- [ ] Top K smallest
- [ ] Top K frequent
- [ ] Merge K sorted arrays/lists
- [ ] Running median
- [ ] Task scheduling

---

# 16. Final Goal

Before moving to `INTERVIEW.md`, make sure you can explain this without looking at your notes:

> `PriorityQueue` is a heap-based queue where elements are processed according to priority rather than FIFO order.

You should also be comfortable answering:

```text
What is the default priority?
What does peek() return?
What does poll() do?
Does it allow duplicates?
Does it allow null?
Is it thread-safe?
Is it fully sorted?
How do you create a max-heap?
What is the complexity of offer()?
What is the complexity of poll()?
How do you use PriorityQueue with custom objects?
```

---

# 17. Progress

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

**Next:** `09-PriorityQueue/INTERVIEW.md` — interview questions, internals, complexities, comparisons, traps, and rapid-fire revision.
