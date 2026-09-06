# ArrayDeque — Notes

> `ArrayDeque` is a resizable-array implementation of the `Deque` interface that supports efficient insertion and removal from both ends.

---

## Table of Contents

1. [What is ArrayDeque?](#1-what-is-arraydeque)
2. [Package and Hierarchy](#2-package-and-hierarchy)
3. [Why ArrayDeque?](#3-why-arraydeque)
4. [Creating an ArrayDeque](#4-creating-an-arraydeque)
5. [Adding Elements](#5-adding-elements)
6. [Removing Elements](#6-removing-elements)
7. [Inspecting Elements](#7-inspecting-elements)
8. [ArrayDeque as a Queue](#8-arraydeque-as-a-queue)
9. [ArrayDeque as a Stack](#9-arraydeque-as-a-stack)
10. [Front and Back Operations](#10-front-and-back-operations)
11. [Important Methods](#11-important-methods)
12. [add() vs offer()](#12-add-vs-offer)
13. [remove() vs poll()](#13-remove-vs-poll)
14. [element() vs peek()](#14-element-vs-peek)
15. [Null Elements](#15-null-elements)
16. [Duplicate Elements](#16-duplicate-elements)
17. [Internal Data Structure](#17-internal-data-structure)
18. [Time Complexity](#18-time-complexity)
19. [ArrayDeque vs LinkedList](#19-arraydeque-vs-linkedlist)
20. [ArrayDeque vs Stack](#20-arraydeque-vs-stack)
21. [ArrayDeque vs PriorityQueue](#21-arraydeque-vs-priorityqueue)
22. [ArrayDeque vs ArrayList](#22-arraydeque-vs-arraylist)
23. [Thread Safety](#23-thread-safety)
24. [Iteration](#24-iteration)
25. [Real-World Applications](#25-real-world-applications)
26. [Advantages](#26-advantages)
27. [Limitations](#27-limitations)
28. [Common Interview Traps](#28-common-interview-traps)
29. [Quick Revision](#29-quick-revision)
30. [Final Mental Model](#30-final-mental-model)
31. [Progress](#31-progress)

---

# 1. What is ArrayDeque?

`ArrayDeque` is a class in the Java Collections Framework.

It implements the `Deque` interface.

```java
ArrayDeque<Integer> deque = new ArrayDeque<>();
```

It is backed by a **resizable array** and is designed for efficient operations at both ends.

The name can be understood as:

```text
Array + Deque
```

It combines:

- array-based storage
- double-ended queue behavior

---

# 2. Package and Hierarchy

## Package

```java
java.util.ArrayDeque
```

Import it using:

```java
import java.util.ArrayDeque;
```

---

## Hierarchy

Conceptually:

```text
Iterable
   ↓
Collection
   ↓
Queue
   ↓
Deque
   ↓
ArrayDeque
```

A common declaration is:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

This is usually preferred when you only need `Deque` operations.

---

# 3. Why ArrayDeque?

`ArrayDeque` is useful when you need:

- FIFO queue behavior
- LIFO stack behavior
- insertion at both ends
- removal from both ends
- efficient end operations

Example:

```text
Front                         Back
  ↓                             ↓
10    20    30    40    50
```

You can:

```text
add/remove → Front
add/remove → Back
```

---

# 4. Creating an ArrayDeque

## Basic Creation

```java
Deque<Integer> deque = new ArrayDeque<>();
```

---

## With Initial Capacity

You can provide an initial capacity:

```java
ArrayDeque<Integer> deque = new ArrayDeque<>(20);
```

This specifies an initial capacity hint.

It does not impose a fixed maximum size.

---

## String Deque

```java
Deque<String> deque = new ArrayDeque<>();

deque.addLast("A");
deque.addLast("B");
deque.addLast("C");
```

---

# 5. Adding Elements

`ArrayDeque` provides multiple insertion methods.

## Add at Front

```java
deque.addFirst(10);
```

Example:

```text
Before:
20 30

After:
10 20 30
```

---

## Add at Back

```java
deque.addLast(40);
```

Example:

```text
Before:
10 20 30

After:
10 20 30 40
```

---

## Offer at Front

```java
deque.offerFirst(5);
```

---

## Offer at Back

```java
deque.offerLast(50);
```

---

## Standard Queue Methods

Because `ArrayDeque` implements `Deque`, it also supports:

```java
deque.add(10);
deque.offer(20);
```

For queue-style usage, these operate at the tail/back.

---

# 6. Removing Elements

## Remove from Front

```java
deque.removeFirst();
```

---

## Remove from Back

```java
deque.removeLast();
```

---

## Poll from Front

```java
deque.pollFirst();
```

---

## Poll from Back

```java
deque.pollLast();
```

---

## Standard Queue Methods

```java
deque.remove();
deque.poll();
```

For `ArrayDeque` used as a Queue:

```text
remove() → remove from front
poll()   → remove from front
```

---

# 7. Inspecting Elements

## Inspect Front

```java
deque.getFirst();
```

or:

```java
deque.peekFirst();
```

---

## Inspect Back

```java
deque.getLast();
```

or:

```java
deque.peekLast();
```

---

## Difference

```text
getFirst()  → exception if empty
peekFirst() → null if empty

getLast()   → exception if empty
peekLast()  → null if empty
```

---

# 8. ArrayDeque as a Queue

A Queue follows:

```text
FIFO
First In → First Out
```

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

---

## Queue Pattern

```text
Insert:
offerLast()

Remove:
pollFirst()
```

Example:

```text
10 → 20 → 30
↑
Remove first
```

This gives FIFO behavior.

---

# 9. ArrayDeque as a Stack

A Stack follows:

```text
LIFO
Last In → First Out
```

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

---

## Stack Pattern

```text
Push:
push()

Remove:
pop()
```

Example:

```text
Push:
10
20
30

Pop:
30
20
10
```

---

## Why use ArrayDeque instead of Stack?

`Stack` is a legacy class.

For typical stack behavior, modern Java code commonly uses:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

---

# 10. Front and Back Operations

The most important feature of `ArrayDeque` is efficient access to both ends.

```text
               FRONT                 BACK
                 ↓                    ↓
              ┌────┬────┬────┬────┐
              │ 10 │ 20 │ 30 │ 40 │
              └────┴────┴────┴────┘
```

## Front

```java
addFirst()
removeFirst()
peekFirst()
```

## Back

```java
addLast()
removeLast()
peekLast()
```

---

# 11. Important Methods

| Operation | Method |
|---|---|
| Add front | `addFirst()` |
| Add back | `addLast()` |
| Offer front | `offerFirst()` |
| Offer back | `offerLast()` |
| Remove front | `removeFirst()` |
| Remove back | `removeLast()` |
| Poll front | `pollFirst()` |
| Poll back | `pollLast()` |
| Inspect front | `getFirst()` |
| Inspect back | `getLast()` |
| Peek front | `peekFirst()` |
| Peek back | `peekLast()` |
| Stack push | `push()` |
| Stack pop | `pop()` |
| Stack peek | `peek()` |
| Queue add | `add()` / `offer()` |
| Queue remove | `remove()` / `poll()` |

---

# 12. add() vs offer()

Because `ArrayDeque` implements `Deque`, it supports:

```java
add()
offer()
```

Both insert at the tail when used through Queue semantics.

Example:

```java
deque.add(10);
deque.offer(20);
```

The general difference is:

| Method | Failure behavior |
|---|---|
| `add()` | May throw exception |
| `offer()` | Returns `false` |

For `ArrayDeque`, which grows as needed, capacity-based insertion failure is not normally encountered.

---

# 13. remove() vs poll()

Both remove the first element when using ArrayDeque as a Queue.

```java
deque.remove();
deque.poll();
```

Difference when empty:

| Method | Empty |
|---|---|
| `remove()` | `NoSuchElementException` |
| `poll()` | `null` |

---

## Example

```java
Deque<Integer> deque = new ArrayDeque<>();

System.out.println(deque.poll());
```

Output:

```text
null
```

But:

```java
deque.remove();
```

throws:

```text
NoSuchElementException
```

---

# 14. element() vs peek()

Both inspect the first element when using Queue semantics.

```java
deque.element();
deque.peek();
```

Difference when empty:

| Method | Empty |
|---|---|
| `element()` | `NoSuchElementException` |
| `peek()` | `null` |

---

# 15. Null Elements

`ArrayDeque` **does not allow `null` elements**.

Example:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.add(null);
```

Result:

```text
NullPointerException
```

The same applies to methods such as:

```java
offer(null);
addFirst(null);
addLast(null);
offerFirst(null);
offerLast(null);
```

---

## Why is null not allowed?

Methods such as:

```java
peek()
poll()
peekFirst()
pollFirst()
```

can return `null` when the Deque is empty.

Disallowing stored `null` values keeps the meaning of `null` unambiguous.

---

# 16. Duplicate Elements

`ArrayDeque` allows duplicate elements.

Example:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(10);
deque.addLast(20);
```

Contents:

```text
10 10 20
```

There is no automatic duplicate removal.

---

# 17. Internal Data Structure

`ArrayDeque` uses a **resizable array** internally.

Conceptually, elements are stored in a circular arrangement so that operations at both ends can be handled efficiently.

A simplified representation:

```text
       ┌───────────────────────────────┐
       │ 10 │ 20 │ 30 │ 40 │ 50 │ ... │
       └───────────────────────────────┘
          ↑                       ↑
        front                    back
```

The actual implementation uses internal indexing to manage the two ends efficiently.

---

## Why circular storage?

If elements were always shifted whenever the first element was removed, removing from the front could become expensive.

Circular indexing allows the implementation to move the logical front/back without shifting all remaining elements after every operation.

---

# 18. Time Complexity

For `ArrayDeque`, end operations are typically constant time.

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

## Why amortized O(1)?

`ArrayDeque` is resizable.

Most insertions are constant-time operations.

Occasionally, the internal array needs to grow, which requires additional work.

Across a sequence of operations, the average insertion cost remains:

```text
O(1) amortized
```

---

# 19. ArrayDeque vs LinkedList

Both can implement `Deque`.

| Feature | ArrayDeque | LinkedList |
|---|---|---|
| Implementation | Resizable array | Doubly linked list |
| Implements Deque | Yes | Yes |
| End operations | Efficient | Efficient |
| Random access | Not supported as List API | `O(n)` |
| Memory overhead | Generally lower | Higher due to node objects/references |
| Null | Not allowed | Allowed |
| Typical Deque choice | Preferred | Useful when linked-list behavior is also needed |

If your requirement is simply:

```text
Deque
```

then `ArrayDeque` is often the better default.

---

# 20. ArrayDeque vs Stack

| Feature | ArrayDeque | Stack |
|---|---|---|
| Type | Deque implementation | Legacy class |
| LIFO | Yes | Yes |
| FIFO | Yes | No |
| Both ends | Yes | No |
| Synchronization | Not synchronized | Synchronized legacy design |
| Modern stack choice | Yes | Usually no |

Preferred:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

---

# 21. ArrayDeque vs PriorityQueue

| Feature | ArrayDeque | PriorityQueue |
|---|---|---|
| Main behavior | Double-ended | Priority-based |
| Internal structure | Resizable array/deque | Heap |
| FIFO | Yes | No |
| LIFO | Yes | No |
| Both-end operations | Yes | No |
| Priority ordering | No | Yes |
| End operation | `O(1)` amortized | `O(log n)` for insertion/removal |
| `peek` | `O(1)` | `O(1)` |

Choose:

```text
ArrayDeque → front/back behavior
PriorityQueue → priority behavior
```

---

# 22. ArrayDeque vs ArrayList

These collections serve different purposes.

| Feature | ArrayDeque | ArrayList |
|---|---|---|
| Main purpose | Queue/Deque/Stack | Dynamic List |
| Indexed access | No | Yes |
| Add at end | Efficient | Efficient amortized |
| Remove at front | Efficient | `O(n)` |
| Add at front | Efficient | `O(n)` |
| Random access | No | `O(1)` |
| Allows null | No | Yes |
| Duplicates | Yes | Yes |

Use:

```text
ArrayList → list + index access
ArrayDeque → queue/stack + end operations
```

---

# 23. Thread Safety

`ArrayDeque` is **not thread-safe**.

It is not synchronized.

If multiple threads need to modify a shared deque safely, consider an appropriate concurrent collection or external synchronization depending on the use case.

Do not assume:

```java
ArrayDeque
```

provides automatic thread safety.

---

# 24. Iteration

## Normal Iteration

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);

for (Integer value : deque) {
    System.out.println(value);
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
Iterator<Integer> iterator =
        deque.descendingIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

Output:

```text
30
20
10
```

---

## Important

Iteration follows the Deque's logical order.

It is not priority-based like `PriorityQueue`.

---

# 25. Real-World Applications

## 25.1 Queue

Use:

```java
Deque<Task> queue = new ArrayDeque<>();

queue.offerLast(task);
Task next = queue.pollFirst();
```

Useful for:

- BFS
- task processing
- request queues
- event processing

---

## 25.2 Stack

Use:

```java
Deque<String> stack = new ArrayDeque<>();

stack.push("A");
stack.push("B");

String value = stack.pop();
```

Useful for:

- DFS
- parentheses matching
- expression processing
- backtracking
- undo-style operations

---

## 25.3 Palindrome

A Deque can compare characters from both ends.

```text
MADAM

M ↔ M
A ↔ A
D
```

---

## 25.4 Sliding Window

`ArrayDeque` is commonly used to implement monotonic queues.

For example:

```text
Sliding Window Maximum
```

can be solved in:

```text
O(n)
```

using a suitable Deque strategy.

---

## 25.5 BFS

A graph traversal can use:

```java
Deque<Integer> queue = new ArrayDeque<>();

queue.offerLast(start);

while (!queue.isEmpty()) {
    int node = queue.pollFirst();

    // process node
}
```

This provides FIFO traversal.

---

# 26. Advantages

## 1. Efficient End Operations

Insertion and removal at both ends are efficient.

---

## 2. Can Replace Stack

Use:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

instead of legacy `Stack` for typical LIFO behavior.

---

## 3. Can Replace Basic Queue

Use:

```java
Deque<Integer> queue = new ArrayDeque<>();
```

when priority ordering is not required.

---

## 4. Flexible

One data structure can provide:

```text
Queue behavior
+
Stack behavior
+
Double-ended behavior
```

---

## 5. No Extra Node Objects

Compared with a linked implementation, array-based storage can have lower per-element memory overhead.

---

# 27. Limitations

## 1. No Indexed Access

You cannot efficiently do:

```java
deque.get(5);
```

as you would with a `List`.

---

## 2. No Null Elements

`ArrayDeque` rejects `null`.

---

## 3. Not Thread-Safe

External synchronization or another collection may be needed for concurrent access.

---

## 4. Not Priority-Based

If you need the smallest/largest/highest-priority element, use:

```text
PriorityQueue
```

rather than `ArrayDeque`.

---

# 28. Common Interview Traps

## Trap 1 — "ArrayDeque is a Queue only"

Wrong.

It implements `Deque`, so it supports both ends.

---

## Trap 2 — "ArrayDeque allows null"

Wrong.

```text
null → NullPointerException
```

---

## Trap 3 — "ArrayDeque is thread-safe"

Wrong.

It is not thread-safe.

---

## Trap 4 — "ArrayDeque is a Stack class"

Wrong.

`ArrayDeque` is a `Deque` implementation that can be used as a Stack.

---

## Trap 5 — "ArrayDeque supports random access"

Wrong.

It is not a `List`.

---

## Trap 6 — "ArrayDeque sorts elements"

Wrong.

It maintains insertion/deque order, not sorted order.

---

## Trap 7 — "ArrayDeque uses a linked list"

Wrong.

It is based on a resizable array.

---

## Trap 8 — "remove() returns null when empty"

Wrong.

```text
remove() → NoSuchElementException
poll()   → null
```

---

## Trap 9 — "peek() removes the element"

Wrong.

`peek()` only inspects.

---

## Trap 10 — "PriorityQueue and ArrayDeque are interchangeable"

Wrong.

```text
ArrayDeque    → end-based operations
PriorityQueue → priority-based operations
```

---

# 29. Quick Revision

## Definition

```text
ArrayDeque = resizable-array implementation of Deque
```

---

## Package

```java
java.util.ArrayDeque
```

---

## Declaration

```java
Deque<Integer> deque = new ArrayDeque<>();
```

---

## Add Front

```java
deque.addFirst(value);
```

---

## Add Back

```java
deque.addLast(value);
```

---

## Remove Front

```java
deque.removeFirst();
```

or:

```java
deque.pollFirst();
```

---

## Remove Back

```java
deque.removeLast();
```

or:

```java
deque.pollLast();
```

---

## Inspect Front

```java
deque.peekFirst();
```

---

## Inspect Back

```java
deque.peekLast();
```

---

## Queue

```java
queue.offerLast(value);
queue.pollFirst();
```

---

## Stack

```java
stack.push(value);
stack.pop();
```

---

## Null

```text
Not allowed
```

---

## Duplicates

```text
Allowed
```

---

## Thread Safety

```text
Not thread-safe
```

---

## Complexity

```text
End operations → O(1) amortized
contains()      → O(n)
```

---

# 30. Final Mental Model

Think of `ArrayDeque` as a flexible double-ended structure:

```text
              FRONT                         BACK
                ↓                            ↓
          ┌────┬────┬────┬────┬────┐
          │ 10 │ 20 │ 30 │ 40 │ 50 │
          └────┴────┴────┴────┴────┘
                ↑                            ↑
             add/remove                   add/remove
```

It can behave as:

### Queue

```text
offerLast()
    ↓
10 → 20 → 30
↑
pollFirst()
```

### Stack

```text
push()
  ↓
10
20
30
  ↓
pop() → 30
```

### Deque

```text
addFirst()  ←  [elements]  →  addLast()
removeFirst() ← [elements] → removeLast()
```

The key idea:

> **ArrayDeque is a resizable-array implementation of Deque that provides efficient operations at both ends and can be used as a Queue, Stack, or general double-ended queue.**

---

# 31. Progress

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
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
│
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
