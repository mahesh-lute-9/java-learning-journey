# Deque — Interview Questions

> Interview-focused revision for the `Deque` interface in Java.

---

## Table of Contents

1. [What is Deque?](#1-what-is-deque)
2. [Deque Hierarchy](#2-deque-hierarchy)
3. [Why is Deque Called Double-Ended Queue?](#3-why-is-deque-called-double-ended-queue)
4. [Important Methods](#4-important-methods)
5. [addFirst() vs offerFirst()](#5-addfirst-vs-offerfirst)
6. [addLast() vs offerLast()](#6-addlast-vs-offerlast)
7. [removeFirst() vs pollFirst()](#7-removefirst-vs-pollfirst)
8. [removeLast() vs pollLast()](#8-removelast-vs-polllast)
9. [getFirst() vs peekFirst()](#9-getfirst-vs-peekfirst)
10. [getLast() vs peekLast()](#10-getlast-vs-peeklast)
11. [Deque as Queue](#11-deque-as-queue)
12. [Deque as Stack](#12-deque-as-stack)
13. [Deque vs Queue](#13-deque-vs-queue)
14. [Deque vs Stack](#14-deque-vs-stack)
15. [Deque vs PriorityQueue](#15-deque-vs-priorityqueue)
16. [Deque Implementations](#16-deque-implementations)
17. [ArrayDeque](#17-arraydeque)
18. [LinkedList as Deque](#18-linkedlist-as-deque)
19. [Null Elements](#19-null-elements)
20. [Duplicates](#20-duplicates)
21. [Iteration](#21-iteration)
22. [Time Complexity](#22-time-complexity)
23. [Thread Safety](#23-thread-safety)
24. [Real-World Applications](#24-real-world-applications)
25. [Monotonic Deque](#25-monotonic-deque)
26. [Scenario-Based Questions](#26-scenario-based-questions)
27. [Common Interview Traps](#27-common-interview-traps)
28. [Rapid-Fire Questions](#28-rapid-fire-questions)
29. [Must-Know Questions](#29-must-know-questions)
30. [Short Interview Answer](#30-short-interview-answer)
31. [Final Checklist](#31-final-checklist)
32. [Progress](#32-progress)

---

# 1. What is Deque?

### Q1. What is a Deque?

`Deque` stands for **Double-Ended Queue**.

It is an interface in the Java Collections Framework that allows elements to be:

- inserted at the front
- inserted at the back
- removed from the front
- removed from the back
- inspected from either end

Example:

```text
Front                         Back
  ↓                             ↓
10    20    30    40    50
```

You can operate on either end.

---

### Q2. Is Deque a class or interface?

`Deque` is an **interface**.

```java
java.util.Deque
```

---

### Q3. Which interface does Deque extend?

`Deque` extends:

```text
Queue
```

The hierarchy is:

```text
Iterable
   ↓
Collection
   ↓
Queue
   ↓
Deque
```

---

# 2. Deque Hierarchy

Important hierarchy:

```text
Iterable
   ↓
Collection
   ↓
Queue
   ↓
Deque
```

Common implementations include:

```text
Deque
├── ArrayDeque
└── LinkedList
```

Typical declaration:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

This follows the programming-to-an-interface principle.

---

# 3. Why is Deque Called Double-Ended Queue?

### Q4. Why is it called a double-ended queue?

Because both ends can be used for insertion, removal, and inspection.

Example:

```text
          FRONT                         BACK
            ↓                            ↓
       ┌────┬────┬────┬────┬────┐
       │ 10 │ 20 │ 30 │ 40 │ 50 │
       └────┴────┴────┴────┴────┘
            ↑                            ↑
          remove                       remove
          / add                        / add
```

A normal Queue primarily follows:

```text
Insert → Back
Remove → Front
```

A Deque supports both directions.

---

# 4. Important Methods

## Insertion

```text
addFirst()
addLast()

offerFirst()
offerLast()
```

## Removal

```text
removeFirst()
removeLast()

pollFirst()
pollLast()
```

## Inspection

```text
getFirst()
getLast()

peekFirst()
peekLast()
```

---

## Method Table

| Operation | Front | Back |
|---|---|---|
| Insert | `addFirst()` | `addLast()` |
| Insert safely | `offerFirst()` | `offerLast()` |
| Remove | `removeFirst()` | `removeLast()` |
| Remove safely | `pollFirst()` | `pollLast()` |
| Inspect | `getFirst()` | `getLast()` |
| Inspect safely | `peekFirst()` | `peekLast()` |

---

# 5. addFirst() vs offerFirst()

### Q5. What does `addFirst()` do?

Adds an element at the front.

```java
deque.addFirst(10);
```

---

### Q6. What does `offerFirst()` do?

Attempts to add an element at the front and returns a boolean indicating whether insertion succeeded.

```java
deque.offerFirst(10);
```

---

### Difference

| `addFirst()` | `offerFirst()` |
|---|---|
| Inserts at front | Inserts at front |
| Exception-based failure contract | Boolean failure contract |

For an unbounded implementation such as `ArrayDeque`, normal insertion does not ordinarily fail due to capacity.

---

# 6. addLast() vs offerLast()

### Q7. What does `addLast()` do?

Adds an element at the back.

```java
deque.addLast(20);
```

---

### Q8. What does `offerLast()` do?

Attempts to add an element at the back.

```java
deque.offerLast(20);
```

Returns:

```text
true → inserted
false → insertion failed
```

Again, `ArrayDeque` is unbounded from the API perspective, so capacity-based insertion failure is not normally encountered.

---

# 7. removeFirst() vs pollFirst()

### Q9. Difference between `removeFirst()` and `pollFirst()`?

Both remove and return the first element.

The difference is their behavior when the Deque is empty.

| Method | Empty Deque |
|---|---|
| `removeFirst()` | `NoSuchElementException` |
| `pollFirst()` | `null` |

Example:

```java
deque.pollFirst();
```

is useful when an empty Deque is a valid possibility.

---

# 8. removeLast() vs pollLast()

### Q10. Difference between `removeLast()` and `pollLast()`?

| Method | Empty Deque |
|---|---|
| `removeLast()` | `NoSuchElementException` |
| `pollLast()` | `null` |

Both remove from the back.

---

# 9. getFirst() vs peekFirst()

### Q11. Difference between `getFirst()` and `peekFirst()`?

Both inspect the first element without removing it.

| Method | Empty Deque |
|---|---|
| `getFirst()` | `NoSuchElementException` |
| `peekFirst()` | `null` |

---

# 10. getLast() vs peekLast()

### Q12. Difference between `getLast()` and `peekLast()`?

Both inspect the last element without removing it.

| Method | Empty Deque |
|---|---|
| `getLast()` | `NoSuchElementException` |
| `peekLast()` | `null` |

---

# 11. Deque as Queue

### Q13. Can Deque be used as a Queue?

Yes.

Use:

```java
Deque<Integer> queue = new ArrayDeque<>();

queue.offerLast(10);
queue.offerLast(20);
queue.offerLast(30);

System.out.println(queue.pollFirst());
```

Output:

```text
10
```

This produces FIFO behavior:

```text
First In → First Out
```

---

### Q14. Which Deque methods provide FIFO behavior?

Use:

```text
offerLast() → insert
pollFirst() → remove
```

Conceptually:

```text
Back → Insert
Front → Remove
```

---

# 12. Deque as Stack

### Q15. Can Deque be used as a Stack?

Yes.

Use:

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.pop());
```

Output:

```text
30
```

This gives LIFO behavior:

```text
Last In → First Out
```

---

### Q16. Which Deque methods correspond to Stack methods?

| Stack | Deque |
|---|---|
| `push()` | `addFirst()` |
| `pop()` | `removeFirst()` |
| `peek()` | `peekFirst()` |

The `Deque` interface also directly provides `push()`, `pop()`, and `peek()` methods for stack-style use.

---

### Q17. Why is Deque preferred over Stack for typical stack usage?

`Stack` is a legacy class.

For typical LIFO operations, the modern approach is:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

It provides efficient stack operations without requiring the legacy `Stack` class.

---

# 13. Deque vs Queue

### Q18. What is the difference between Queue and Deque?

| Queue | Deque |
|---|---|
| Primarily one-direction queue behavior | Both-end operations |
| Insert at tail | Insert at either end |
| Remove from head | Remove from either end |
| FIFO is common | FIFO or LIFO possible |
| More restricted API | More flexible API |

Important:

```text
Deque extends Queue
```

---

### Q19. Can every Queue be used as a Deque?

No.

`Deque` provides additional operations that a general `Queue` reference does not expose.

---

# 14. Deque vs Stack

### Q20. Difference between Deque and Stack?

| Deque | Stack |
|---|---|
| Interface | Class |
| Supports both ends | Stack-oriented |
| Can implement FIFO | LIFO |
| Can implement LIFO | LIFO |
| Modern stack choice | Legacy collection |
| Common implementation: `ArrayDeque` | `Stack` |

For a normal stack:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

is generally preferred.

---

# 15. Deque vs PriorityQueue

### Q21. Difference between Deque and PriorityQueue?

They solve different problems.

| Deque | PriorityQueue |
|---|---|
| Double-ended | Priority-based |
| Operates on front/back | Operates according to priority |
| No heap ordering | Heap-based |
| FIFO/LIFO possible | Not FIFO |
| `O(1)` amortized end operations with ArrayDeque | `O(log n)` insertion/removal |

---

### Q22. When would you choose Deque?

Choose Deque when you need:

```text
front/back access
```

Examples:

- FIFO queue
- Stack
- Sliding window
- Palindrome checking

---

### Q23. When would you choose PriorityQueue?

Choose PriorityQueue when you need:

```text
highest-priority element
```

Examples:

- Task scheduling
- Dijkstra's algorithm
- Top-K problems

---

# 16. Deque Implementations

Common implementations:

```text
ArrayDeque
LinkedList
```

---

### Q24. Which implementation is commonly preferred for general Deque usage?

Usually:

```java
ArrayDeque
```

when you simply need a general-purpose Deque.

---

# 17. ArrayDeque

### Q25. What is ArrayDeque?

`ArrayDeque` is a resizable-array implementation of the `Deque` interface.

Example:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

---

### Q26. Does ArrayDeque allow null?

No.

```java
deque.add(null);
```

throws:

```text
NullPointerException
```

---

### Q27. Why is ArrayDeque efficient?

It is designed for efficient insertion and removal at both ends.

For typical operations:

```text
addFirst() → O(1) amortized
addLast()  → O(1) amortized
pollFirst() → O(1)
pollLast()  → O(1)
```

---

# 18. LinkedList as Deque

### Q28. Can LinkedList implement Deque?

Yes.

```java
Deque<Integer> deque = new LinkedList<>();
```

`LinkedList` implements `Deque`.

---

### Q29. Why might you choose ArrayDeque instead of LinkedList?

If you specifically need Deque behavior, `ArrayDeque` is often a better default because it avoids the per-node overhead of a linked structure and is designed specifically around deque operations.

---

# 19. Null Elements

### Q30. Does the Deque interface itself universally forbid null?

No.

Null-handling depends on the implementation.

For example:

```text
ArrayDeque → does not allow null
```

`LinkedList` has historically permitted `null` elements.

However, when using `ArrayDeque`, assume:

```text
null → not allowed
```

---

### Q31. Why does ArrayDeque not allow null?

Methods such as:

```text
peekFirst()
peekLast()
pollFirst()
pollLast()
```

can return `null` to indicate that no element is available.

Allowing `null` as a stored element would make that distinction ambiguous.

---

# 20. Duplicates

### Q32. Does Deque allow duplicate elements?

Yes.

Example:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(10);
deque.addLast(20);
```

Duplicates remain in the Deque.

---

### Q33. Does Deque automatically remove duplicates?

No.

A Deque is not a Set.

---

# 21. Iteration

### Q34. How do you iterate over a Deque?

Using a for-each loop:

```java
for (Integer value : deque) {
    System.out.println(value);
}
```

---

### Q35. How do you iterate from the back toward the front?

Use:

```java
Iterator<Integer> iterator =
        deque.descendingIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

---

### Q36. Does descendingIterator() modify the Deque?

No.

It only traverses the elements in reverse order.

---

# 22. Time Complexity

For `ArrayDeque`:

| Operation | Typical Complexity |
|---|---:|
| `addFirst()` | `O(1)` amortized |
| `addLast()` | `O(1)` amortized |
| `offerFirst()` | `O(1)` amortized |
| `offerLast()` | `O(1)` amortized |
| `removeFirst()` | `O(1)` |
| `removeLast()` | `O(1)` |
| `pollFirst()` | `O(1)` |
| `pollLast()` | `O(1)` |
| `peekFirst()` | `O(1)` |
| `peekLast()` | `O(1)` |
| `getFirst()` | `O(1)` |
| `getLast()` | `O(1)` |
| `contains()` | `O(n)` |
| `size()` | `O(1)` |

---

### Q37. Why is insertion described as amortized `O(1)`?

Because `ArrayDeque` uses a resizable array.

Most insertions take constant time, but occasionally the internal storage needs to grow and elements may need to be reorganized.

Over many operations, the average cost remains `O(1)` amortized.

---

# 23. Thread Safety

### Q38. Is ArrayDeque thread-safe?

No.

`ArrayDeque` is not synchronized.

If multiple threads modify the same Deque concurrently, appropriate synchronization or a concurrent collection should be considered.

---

### Q39. Is Deque itself thread-safe?

`Deque` is an interface, so thread-safety depends on the implementation.

For example:

```text
ArrayDeque → not thread-safe
```

---

# 24. Real-World Applications

## Q40. Where is Deque used?

Common applications include:

### 1. Queue

```text
FIFO processing
```

### 2. Stack

```text
LIFO processing
```

### 3. Palindrome Checking

Compare:

```text
front ↔ back
```

### 4. Sliding Window

Maintain useful candidates inside a window.

### 5. BFS

Use:

```text
offerLast()
pollFirst()
```

### 6. Monotonic Queue

Efficiently solve:

```text
sliding window maximum/minimum
```

### 7. Undo/Redo-style structures

Can support operations from one or both ends depending on design.

---

# 25. Monotonic Deque

### Q41. What is a monotonic Deque?

A monotonic Deque is a Deque maintained in a specific increasing or decreasing order to efficiently solve problems involving a sliding window.

For example, for sliding-window maximum:

```text
Deque values:
largest → smallest
```

The front represents the maximum candidate.

---

### Q42. Why is a Deque useful for sliding-window maximum?

Consider:

```text
[1, 3, -1, -3, 5, 3, 6, 7]
```

with:

```text
k = 3
```

Expected result:

```text
3 3 5 5 6 7
```

A monotonic Deque lets us:

- remove indices that leave the window
- remove smaller values from the back
- keep the largest candidate at the front

This gives:

```text
O(n)
```

overall time.

---

### Q43. Why store indices instead of values?

Indices tell us whether an element has moved outside the current sliding window.

Therefore, for many sliding-window problems, the Deque stores **indices** rather than just values.

---

# 26. Scenario-Based Questions

## Q44. You need FIFO processing. Which Deque operations?

Use:

```text
offerLast()
pollFirst()
```

---

## Q45. You need LIFO processing. Which Deque operations?

Use:

```text
push()
pop()
```

or:

```text
addFirst()
removeFirst()
```

---

## Q46. You need insertion and removal from both ends.

Which collection?

```text
Deque
```

---

## Q47. You need to repeatedly retrieve the smallest element.

Which collection?

```text
PriorityQueue
```

---

## Q48. You need unique sorted elements.

Which collection?

```text
TreeSet
```

---

## Q49. You need sliding-window maximum in `O(n)`.

Which technique?

```text
Monotonic Deque
```

---

## Q50. You need a general-purpose stack in modern Java.

Which approach?

```java
Deque<Integer> stack = new ArrayDeque<>();
```

---

# 27. Common Interview Traps

## Trap 1 — "Deque is just a Queue"

Not exactly.

A Deque extends Queue but adds operations at both ends.

---

## Trap 2 — "Deque is FIFO only"

Wrong.

A Deque can support both:

```text
FIFO
LIFO
```

depending on the operations used.

---

## Trap 3 — "Deque is the same as PriorityQueue"

Wrong.

```text
Deque → front/back based
PriorityQueue → priority based
```

---

## Trap 4 — "ArrayDeque allows null"

Wrong.

```text
ArrayDeque → null not allowed
```

---

## Trap 5 — "removeFirst() returns null when empty"

Wrong.

It throws:

```text
NoSuchElementException
```

Use:

```text
pollFirst()
```

if you want `null` for an empty Deque.

---

## Trap 6 — "getFirst() returns null when empty"

Wrong.

It throws:

```text
NoSuchElementException
```

Use:

```text
peekFirst()
```

if you want `null`.

---

## Trap 7 — "Deque automatically removes duplicates"

Wrong.

Duplicates are allowed.

---

## Trap 8 — "ArrayDeque is thread-safe"

Wrong.

It is not thread-safe.

---

## Trap 9 — "Stack is always the best implementation for a stack"

Not generally.

For typical stack behavior, prefer:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

---

# 28. Rapid-Fire Questions

### Q51. What does Deque stand for?

```text
Double-Ended Queue
```

### Q52. Class or interface?

```text
Interface
```

### Q53. What does Deque extend?

```text
Queue
```

### Q54. Can you insert at both ends?

```text
Yes
```

### Q55. Can you remove from both ends?

```text
Yes
```

### Q56. Can Deque behave as FIFO?

```text
Yes
```

### Q57. Can Deque behave as LIFO?

```text
Yes
```

### Q58. Common implementation?

```text
ArrayDeque
```

### Q59. Does ArrayDeque allow null?

```text
No
```

### Q60. Does ArrayDeque allow duplicates?

```text
Yes
```

### Q61. Is ArrayDeque thread-safe?

```text
No
```

### Q62. Front insertion?

```text
addFirst()
offerFirst()
```

### Q63. Back insertion?

```text
addLast()
offerLast()
```

### Q64. Front removal?

```text
removeFirst()
pollFirst()
```

### Q65. Back removal?

```text
removeLast()
pollLast()
```

### Q66. Inspect front?

```text
getFirst()
peekFirst()
```

### Q67. Inspect back?

```text
getLast()
peekLast()
```

### Q68. Empty `pollFirst()`?

```text
null
```

### Q69. Empty `removeFirst()`?

```text
NoSuchElementException
```

### Q70. Empty `peekFirst()`?

```text
null
```

### Q71. Empty `getFirst()`?

```text
NoSuchElementException
```

### Q72. ArrayDeque end operation complexity?

```text
O(1) amortized
```

### Q73. `contains()` complexity?

```text
O(n)
```

### Q74. FIFO pattern?

```text
offerLast()
pollFirst()
```

### Q75. LIFO pattern?

```text
push()
pop()
```

### Q76. Sliding-window maximum technique?

```text
Monotonic Deque
```

---

# 29. Must-Know Questions

Before an interview, make sure you can answer these without hesitation:

### ⭐⭐⭐⭐⭐

1. What is `Deque`?
2. What does Deque stand for?
3. Is Deque a class or interface?
4. Which interface does Deque extend?
5. Why is it called double-ended?
6. How do you add at the front?
7. How do you add at the back?
8. How do you remove from the front?
9. How do you remove from the back?
10. Difference between `removeFirst()` and `pollFirst()`.
11. Difference between `getFirst()` and `peekFirst()`.
12. How can Deque be used as a Queue?
13. How can Deque be used as a Stack?
14. Why is `ArrayDeque` preferred over `Stack` for typical stack usage?
15. Does `ArrayDeque` allow `null`?
16. Does Deque allow duplicates?
17. Is `ArrayDeque` thread-safe?
18. What is the complexity of end operations?
19. `Deque` vs `PriorityQueue`.
20. `Deque` vs `Stack`.
21. `Deque` vs `Queue`.
22. What is a monotonic Deque?
23. How does a Deque solve sliding-window maximum?
24. Why are indices stored in a monotonic Deque?
25. What are common implementations of Deque?

---

# 30. Short Interview Answer

If an interviewer asks:

> "What is Deque?"

A strong answer is:

> `Deque` stands for Double-Ended Queue and is an interface that extends `Queue`. It allows insertion, removal, and inspection from both the front and the back. It can be used as a FIFO Queue or a LIFO Stack. `ArrayDeque` is a common implementation and provides efficient operations at both ends, typically `O(1)` amortized. `ArrayDeque` allows duplicates but does not allow `null`.

---

# 31. Final Checklist

## Core Concepts

- [ ] I know what Deque means.
- [ ] I know Deque is an interface.
- [ ] I know Deque extends Queue.
- [ ] I understand front and back operations.
- [ ] I understand FIFO behavior.
- [ ] I understand LIFO behavior.

## Methods

- [ ] `addFirst()`
- [ ] `addLast()`
- [ ] `offerFirst()`
- [ ] `offerLast()`
- [ ] `removeFirst()`
- [ ] `removeLast()`
- [ ] `pollFirst()`
- [ ] `pollLast()`
- [ ] `getFirst()`
- [ ] `getLast()`
- [ ] `peekFirst()`
- [ ] `peekLast()`

## Implementation

- [ ] I know `ArrayDeque`.
- [ ] I know `LinkedList` can implement Deque.
- [ ] I know `ArrayDeque` does not allow `null`.
- [ ] I know duplicates are allowed.
- [ ] I know `ArrayDeque` is not thread-safe.

## Comparisons

- [ ] Deque vs Queue
- [ ] Deque vs Stack
- [ ] Deque vs PriorityQueue
- [ ] ArrayDeque vs LinkedList

## Problem Solving

- [ ] Reverse a string
- [ ] Check palindrome
- [ ] Implement Queue using Deque
- [ ] Implement Stack using Deque
- [ ] First negative in sliding window
- [ ] Sliding-window maximum
- [ ] Sliding-window minimum
- [ ] Understand monotonic Deque

---

# 32. Progress

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
