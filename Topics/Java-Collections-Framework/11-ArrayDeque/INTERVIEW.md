# ArrayDeque — Interview Questions

## Table of Contents

1. [Basic Questions](#basic-questions)
2. [Conceptual Questions](#conceptual-questions)
3. [Methods and Behavior](#methods-and-behavior)
4. [Internal Working](#internal-working)
5. [Time Complexity](#time-complexity)
6. [ArrayDeque vs Other Collections](#arraydeque-vs-other-collections)
7. [Scenario-Based Questions](#scenario-based-questions)
8. [Output-Based Questions](#output-based-questions)
9. [Coding Questions](#coding-questions)
10. [Common Traps](#common-traps)
11. [Quick Revision](#quick-revision)
12. [Interview Checklist](#interview-checklist)
13. [Progress](#progress)

---

# 1. Basic Questions

## 1. What is `ArrayDeque`?

`ArrayDeque` is a resizable-array implementation of the `Deque` interface in Java.

It allows insertion and removal from both ends of the deque.

```java
Deque<Integer> deque = new ArrayDeque<>();
```

It can be used as:

```text
Queue  → FIFO
Stack  → LIFO
Deque  → insertion/removal from both ends
```

---

## 2. Which package contains `ArrayDeque`?

```java
java.util.ArrayDeque
```

Example:

```java
import java.util.ArrayDeque;
```

---

## 3. Which interface does `ArrayDeque` implement?

`ArrayDeque` implements:

```java
Deque<E>
```

Since `Deque` extends `Queue`, an `ArrayDeque` can also be used as a Queue.

Relationship:

```text
Collection
    │
    └── Queue
          │
          └── Deque
                │
                └── ArrayDeque
```

---

## 4. Is `ArrayDeque` a class or an interface?

It is a **class**.

```java
ArrayDeque<Integer> deque = new ArrayDeque<>();
```

---

## 5. Is `ArrayDeque` resizable?

Yes.

Its internal storage can grow as elements are added.

The constructor:

```java
new ArrayDeque<>(10)
```

sets an initial capacity, not a fixed maximum size.

---

# 2. Conceptual Questions

## 6. Does `ArrayDeque` allow duplicate elements?

Yes.

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.add(10);
deque.add(10);
deque.add(20);
```

Result:

```text
[10, 10, 20]
```

Duplicates are allowed.

---

## 7. Does `ArrayDeque` allow `null`?

No.

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.add(null);
```

throws:

```text
NullPointerException
```

---

## 8. Why doesn't `ArrayDeque` allow null?

Methods such as:

```java
poll()
peek()
pollFirst()
peekFirst()
```

can return `null` when the deque is empty.

Allowing `null` elements would make it difficult to distinguish between:

```text
empty deque
```

and:

```text
deque containing null
```

Therefore, `ArrayDeque` does not permit `null`.

---

## 9. Is `ArrayDeque` thread-safe?

No.

`ArrayDeque` is not synchronized and is not designed for concurrent modification by multiple threads without external synchronization.

For concurrent queue/deque requirements, consider appropriate classes from `java.util.concurrent`.

---

## 10. Does `ArrayDeque` maintain insertion order?

Iteration proceeds from the front toward the back of the deque.

For example:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);
```

Iteration gives:

```text
10
20
30
```

However, `ArrayDeque` should not be thought of as a general-purpose ordered `List`.

---

## 11. Does `ArrayDeque` sort elements?

No.

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(30);
deque.addLast(10);
deque.addLast(20);
```

The deque remains:

```text
[30, 10, 20]
```

It does not automatically sort elements.

---

## 12. Does `ArrayDeque` support random access?

No.

There is no:

```java
deque.get(5);
```

operation like there is with a `List`.

`ArrayDeque` is designed around access at the two ends.

---

# 3. Methods and Behavior

## 13. What is the difference between `addFirst()` and `addLast()`?

```java
addFirst()
```

adds at the front.

```java
addLast()
```

adds at the back.

Example:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addFirst(20);
deque.addLast(30);
deque.addFirst(10);
```

Result:

```text
[10, 20, 30]
```

---

## 14. What is the difference between `offerFirst()` and `offerLast()`?

They add elements at the front and back respectively, using the Queue/Deque offer-style API.

```java
deque.offerFirst(10);
deque.offerLast(20);
```

---

## 15. What is the difference between `removeFirst()` and `pollFirst()`?

Both remove the first element.

The difference is their behavior when the deque is empty.

| Method | Empty Deque |
|---|---|
| `removeFirst()` | Throws `NoSuchElementException` |
| `pollFirst()` | Returns `null` |

Example:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.removeFirst();
```

throws:

```text
NoSuchElementException
```

While:

```java
deque.pollFirst();
```

returns:

```text
null
```

---

## 16. What is the difference between `getFirst()` and `peekFirst()`?

Both inspect the first element without removing it.

| Method | Empty Deque |
|---|---|
| `getFirst()` | Throws `NoSuchElementException` |
| `peekFirst()` | Returns `null` |

---

## 17. What does `push()` do?

`push()` adds an element to the front of the deque.

```java
deque.push(10);
```

is equivalent to:

```java
deque.addFirst(10);
```

---

## 18. What does `pop()` do?

`pop()` removes and returns the first element.

```java
deque.pop();
```

is equivalent to:

```java
deque.removeFirst();
```

If the deque is empty, it throws `NoSuchElementException`.

---

## 19. How can you use `ArrayDeque` as a Queue?

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

This follows:

```text
FIFO
First In → First Out
```

---

## 20. How can you use `ArrayDeque` as a Stack?

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

This follows:

```text
LIFO
Last In → First Out
```

---

# 4. Internal Working

## 21. How does `ArrayDeque` work internally?

`ArrayDeque` uses a resizable array with circular/ring-buffer style indexing.

Conceptually:

```text
        ┌─────────────────────┐
        │                     │
        ▼                     │
     [ ][ ][ ][ ][ ][ ][ ]    │
      ↑                 ↑     │
    front              back ──┘
```

The implementation can move the logical front and back through the underlying array without requiring every element to be shifted whenever an element is added or removed at an end.

---

## 22. Why is it called `ArrayDeque`?

Because it combines:

```text
Array
  +
Deque
```

The underlying storage is array-based, while the public behavior is that of a double-ended queue.

---

## 23. Is `ArrayDeque` implemented using a LinkedList?

No.

`ArrayDeque` is array-based.

`LinkedList` is node-based.

This is an important interview distinction.

---

## 24. Does `ArrayDeque` use a circular array?

Conceptually, yes.

The implementation uses circular indexing over its internal array so the logical front and back can move efficiently.

The exact internal implementation details can vary between Java versions, so focus on the general concept rather than relying on a specific field layout.

---

## 25. What happens when the internal array becomes full?

The deque grows its internal storage.

Therefore:

```java
new ArrayDeque<>(10)
```

does not mean:

```text
Maximum size = 10
```

It means the deque starts with an initial capacity appropriate for the supplied value.

---

# 5. Time Complexity

## 26. What is the time complexity of adding/removing at the ends?

Typical end operations are:

```text
addFirst()     → O(1) amortized
addLast()      → O(1) amortized

removeFirst()  → O(1)
removeLast()   → O(1)

peekFirst()    → O(1)
peekLast()     → O(1)
```

Insertion is described as **amortized O(1)** because occasional resizing can require more work.

---

## 27. What is the complexity of `contains()`?

```text
O(n)
```

because the deque may need to inspect many elements.

---

## 28. What is the complexity of `size()`?

```text
O(1)
```

The deque maintains its size.

---

## 29. Why is `ArrayDeque` efficient for Queue and Stack operations?

Because the common operations occur at the ends:

```text
front
  ↓
[10][20][30][40]
              ↑
             back
```

The structure is specifically designed for efficient operations at both ends.

---

# 6. ArrayDeque vs Other Collections

## 30. ArrayDeque vs Stack

| ArrayDeque | Stack |
|---|---|
| Modern Deque-based approach | Legacy class |
| Not synchronized | Synchronized methods |
| Supports Queue and Deque operations | Primarily LIFO |
| Usually preferred for stack use | Generally avoided for new stack code |

Recommended:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

---

## 31. Why is `ArrayDeque` preferred over `Stack`?

`Stack` is a legacy class.

Modern Java code generally prefers the `Deque` interface with an implementation such as `ArrayDeque` for stack behavior.

```java
Deque<Integer> stack = new ArrayDeque<>();
```

This also keeps the implementation behind the `Deque` interface.

---

## 32. ArrayDeque vs LinkedList

Both implement `Deque`.

| ArrayDeque | LinkedList |
|---|---|
| Array-based | Doubly linked nodes |
| No `null` elements | Allows `null` |
| No List operations | Also implements `List` |
| Lower per-element node overhead | Extra node objects/references |
| Often preferred for deque-only use | Useful when List + Deque behavior is needed |

If you only need a general-purpose deque:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

is usually a strong default.

---

## 33. ArrayDeque vs ArrayList

| ArrayDeque | ArrayList |
|---|---|
| Implements `Deque` | Implements `List` |
| Designed for both ends | Designed for indexed/list operations |
| No random access API | Supports `get(index)` |
| No `null` | Allows `null` |
| Queue/Stack/Deque use cases | List use cases |

Use:

```text
ArrayDeque → Queue / Stack / both ends
ArrayList  → indexed List operations
```

---

## 34. ArrayDeque vs PriorityQueue

| ArrayDeque | PriorityQueue |
|---|---|
| End-based structure | Priority-based structure |
| FIFO/LIFO/Deque behavior | Highest/lowest priority first |
| Does not sort | Heap-based priority ordering |
| Efficient operations at ends | Efficient priority removal |

Example:

If you need:

```text
First inserted → first processed
```

use a Queue.

If you need:

```text
Smallest priority value → first processed
```

use a `PriorityQueue`.

---

## 35. ArrayDeque vs HashSet

These solve completely different problems.

```text
ArrayDeque → double-ended access
HashSet    → uniqueness
```

Use `HashSet` when membership/uniqueness is the main requirement.

Use `ArrayDeque` when processing elements from one or both ends is the main requirement.

---

# 7. Scenario-Based Questions

## 36. You need BFS traversal. Which collection would you choose?

Use:

```java
Deque<Integer> queue = new ArrayDeque<>();
```

BFS requires FIFO behavior.

Typical operations:

```java
queue.offerLast(node);
queue.pollFirst();
```

---

## 37. You need iterative DFS. Which collection would you choose?

Use:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

Typical operations:

```java
stack.push(node);
stack.pop();
```

---

## 38. You need to process elements from both ends. Which collection is suitable?

```text
ArrayDeque
```

because it provides operations such as:

```java
addFirst()
addLast()
removeFirst()
removeLast()
peekFirst()
peekLast()
```

---

## 39. You need elements ordered by priority. Should you use ArrayDeque?

No.

Use:

```text
PriorityQueue
```

when processing order depends on priority.

---

## 40. You need frequent `get(index)` operations. Should you use ArrayDeque?

No.

Prefer:

```text
ArrayList
```

or another suitable `List`.

---

## 41. You need a stack in modern Java. What would you choose?

```java
Deque<Integer> stack = new ArrayDeque<>();
```

rather than:

```java
Stack<Integer> stack = new Stack<>();
```

for typical single-threaded stack usage.

---

## 42. You need a deque that permits null elements. Can you use ArrayDeque?

No.

`ArrayDeque` does not permit `null`.

A different `Deque` implementation may be required depending on the exact requirements.

For example, `LinkedList` permits `null`.

---

# 8. Output-Based Questions

## Question 1

What is the output?

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addFirst(10);
deque.addFirst(20);
deque.addLast(30);

System.out.println(deque);
```

### Answer

```text
[20, 10, 30]
```

---

## Question 2

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.push(10);
deque.push(20);
deque.push(30);

System.out.println(deque.pop());
System.out.println(deque.pop());
```

### Answer

```text
30
20
```

---

## Question 3

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.offerLast(10);
deque.offerLast(20);
deque.offerLast(30);

System.out.println(deque.pollFirst());
System.out.println(deque.pollFirst());
```

### Answer

```text
10
20
```

---

## Question 4

What happens?

```java
Deque<Integer> deque = new ArrayDeque<>();

System.out.println(deque.peek());
```

### Answer

```text
null
```

---

## Question 5

What happens?

```java
Deque<Integer> deque = new ArrayDeque<>();

System.out.println(deque.pop());
```

### Answer

```text
NoSuchElementException
```

---

## Question 6

What happens?

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.add(null);
```

### Answer

```text
NullPointerException
```

---

## Question 7

What is the output?

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);

System.out.println(deque.peekFirst());
System.out.println(deque.peekLast());
System.out.println(deque);
```

### Answer

```text
10
30
[10, 20, 30]
```

`peekFirst()` and `peekLast()` do not remove elements.

---

# 9. Coding Questions

## 43. Reverse a String Using ArrayDeque

Write a program to reverse:

```text
JAVA
```

Expected:

```text
AVAJ
```

### Hint

Use:

```java
Deque<Character> deque = new ArrayDeque<>();
```

Push every character and then pop them.

---

## 44. Check Whether a String Is a Palindrome

Write a program that checks:

```text
madam → true
level → true
java  → false
```

Use both ends of a Deque.

---

## 45. Implement a Queue Using ArrayDeque

Implement:

```java
enqueue()
dequeue()
peek()
isEmpty()
```

Use FIFO behavior.

---

## 46. Implement a Stack Using ArrayDeque

Implement:

```java
push()
pop()
peek()
isEmpty()
```

Use LIFO behavior.

---

## 47. Implement BFS Using ArrayDeque

Given a graph, perform Breadth-First Search using:

```java
Deque<Integer> queue = new ArrayDeque<>();
```

Expected behavior:

```text
FIFO
```

---

## 48. Implement DFS Using ArrayDeque

Perform iterative Depth-First Search using:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

Expected behavior:

```text
LIFO
```

---

## 49. Find Sliding Window Maximum

Given:

```text
arr = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3
```

Expected:

```text
[3, 3, 5, 5, 6, 7]
```

Use:

```java
ArrayDeque<Integer>
```

and maintain a monotonic decreasing deque of indices.

Target:

```text
O(n)
```

---

# 10. Common Traps

## Trap 1 — Thinking ArrayDeque Allows Null

Wrong:

```java
deque.add(null);
```

Correct:

```text
ArrayDeque does not allow null.
```

---

## Trap 2 — Thinking ArrayDeque Is a List

Wrong assumption:

```java
deque.get(2);
```

`ArrayDeque` does not provide indexed random access.

---

## Trap 3 — Thinking ArrayDeque Sorts Elements

Wrong assumption:

```text
ArrayDeque automatically sorts numbers.
```

It does not.

---

## Trap 4 — Saying Every Operation Is Exactly O(1)

Better answer:

```text
End insertion → O(1) amortized
End removal   → O(1)
Peek          → O(1)
contains      → O(n)
```

The word **amortized** matters for insertion because resizing may occasionally occur.

---

## Trap 5 — Confusing Queue and Stack Behavior

Queue:

```java
offerLast()
pollFirst()
```

Stack:

```java
push()
pop()
```

Remember:

```text
Queue → FIFO
Stack → LIFO
```

---

## Trap 6 — Assuming ArrayDeque Is Thread-Safe

It is not.

```text
ArrayDeque → not thread-safe
```

---

## Trap 7 — Assuming Initial Capacity Is Maximum Capacity

This:

```java
new ArrayDeque<>(10);
```

does not mean:

```text
maximum elements = 10
```

The deque can grow.

---

## Trap 8 — Confusing ArrayDeque with PriorityQueue

`ArrayDeque`:

```text
front/back based
```

`PriorityQueue`:

```text
priority based
```

---

# 11. Quick Revision

## One-Line Definition

> `ArrayDeque` is a resizable-array implementation of the `Deque` interface that supports efficient operations at both ends.

---

## Key Properties

| Property | ArrayDeque |
|---|---|
| Type | Class |
| Package | `java.util` |
| Implements | `Deque` |
| Resizable | Yes |
| Duplicates | Allowed |
| `null` | Not allowed |
| Thread-safe | No |
| Random access | No |
| Sorting | No |
| Queue usage | Yes |
| Stack usage | Yes |
| Deque usage | Yes |
| Internal structure | Resizable array with circular indexing |

---

## Most Important Methods

### Add

```java
addFirst()
addLast()
offerFirst()
offerLast()
```

### Remove

```java
removeFirst()
removeLast()
pollFirst()
pollLast()
```

### Inspect

```java
getFirst()
getLast()
peekFirst()
peekLast()
```

### Stack

```java
push()
pop()
peek()
```

### Queue

```java
offer()
poll()
peek()
```

---

## Queue Pattern

```java
Deque<Integer> queue = new ArrayDeque<>();

queue.offerLast(10);
queue.offerLast(20);

queue.pollFirst();
```

```text
FIFO
```

---

## Stack Pattern

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);

stack.pop();
```

```text
LIFO
```

---

## Complexity

```text
addFirst()     → O(1) amortized
addLast()      → O(1) amortized

removeFirst()  → O(1)
removeLast()   → O(1)

peekFirst()    → O(1)
peekLast()     → O(1)

contains()     → O(n)
size()         → O(1)
```

---

# 12. Interview Checklist

Before moving to the next topic, make sure you can answer:

- [ ] What is `ArrayDeque`?
- [ ] Which interface does it implement?
- [ ] How does it work internally?
- [ ] Why is it array-based?
- [ ] Does it allow duplicates?
- [ ] Does it allow `null`?
- [ ] Why is `null` prohibited?
- [ ] Is it thread-safe?
- [ ] Is it resizable?
- [ ] Does it support indexed access?
- [ ] Does it sort elements?
- [ ] Difference between `addFirst()` and `addLast()`
- [ ] Difference between `removeFirst()` and `pollFirst()`
- [ ] Difference between `getFirst()` and `peekFirst()`
- [ ] How to use it as a Queue?
- [ ] How to use it as a Stack?
- [ ] Why prefer `ArrayDeque` over `Stack`?
- [ ] Difference between `ArrayDeque` and `LinkedList`
- [ ] Difference between `ArrayDeque` and `ArrayList`
- [ ] Difference between `ArrayDeque` and `PriorityQueue`
- [ ] Time complexity of common operations
- [ ] How to use it for BFS?
- [ ] How to use it for DFS?
- [ ] How to solve sliding-window problems with it?

---

# 13. Progress

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

**ArrayDeque is now complete — NOTES, PRACTICE, and INTERVIEW. Next: `12-Set/NOTES.md`.**
