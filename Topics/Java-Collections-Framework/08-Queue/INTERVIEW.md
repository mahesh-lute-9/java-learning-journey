# Queue — Interview Questions

> Interview-focused questions and answers for the Java `Queue` interface.

---

## 📚 Table of Contents

1. [Basic Questions](#1-basic-questions)
2. [Core Queue Operations](#2-core-queue-operations)
3. [Queue Method Pairs](#3-queue-method-pairs)
4. [Queue Hierarchy](#4-queue-hierarchy)
5. [Queue Implementations](#5-queue-implementations)
6. [ArrayDeque vs LinkedList](#6-arraydeque-vs-linkedlist)
7. [PriorityQueue](#7-priorityqueue)
8. [Null Values and Duplicates](#8-null-values-and-duplicates)
9. [Time Complexity](#9-time-complexity)
10. [Code-Based Questions](#10-code-based-questions)
11. [Scenario-Based Questions](#11-scenario-based-questions)
12. [Common Interview Traps](#12-common-interview-traps)
13. [Rapid-Fire Questions](#13-rapid-fire-questions)
14. [Must-Know Questions](#14-must-know-questions)
15. [Final Interview Answer](#15-final-interview-answer)
16. [Interview Checklist](#16-interview-checklist)
17. [Progress](#17-progress)

---

# 1. Basic Questions

## Q1. What is a Queue?

A **Queue** is a collection designed for holding elements before they are processed.

The most common queue behavior is:

> **FIFO — First In, First Out**

The element that enters first is normally processed first.

Example:

```text
Front
  ↓
[A] [B] [C] [D]
                 ↑
                Rear
```

`A` will normally be removed before `B`, `C`, and `D`.

---

## Q2. What does FIFO mean?

FIFO means:

> **First In, First Out**

Example:

```java
queue.offer(10);
queue.offer(20);
queue.offer(30);
```

Removal order:

```text
10
20
30
```

The oldest element is removed first.

---

## Q3. Is Queue a class or an interface?

`Queue` is an **interface**.

```java
import java.util.Queue;
```

You need a concrete implementation such as:

```java
Queue<Integer> queue = new ArrayDeque<>();
```

or:

```java
Queue<Integer> queue = new LinkedList<>();
```

---

## Q4. Which package contains Queue?

```java
java.util.Queue
```

It is part of the Java Collections Framework.

---

## Q5. What are the main Queue methods?

The main methods are:

```text
add()
offer()

remove()
poll()

element()
peek()
```

The methods are intentionally provided in pairs with different failure/empty behavior.

---

# 2. Core Queue Operations

## Q6. What does `offer()` do?

`offer()` attempts to insert an element into the queue.

```java
queue.offer(10);
```

For an unbounded queue such as `ArrayDeque`, successful insertion normally returns `true`.

---

## Q7. What does `poll()` do?

`poll()`:

1. retrieves the head
2. removes it
3. returns it

Example:

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);

System.out.println(queue.poll());
```

Output:

```text
10
```

Remaining:

```text
[20]
```

---

## Q8. What does `peek()` do?

`peek()` retrieves the head without removing it.

```java
System.out.println(queue.peek());
```

If:

```text
10 → 20 → 30
```

then `peek()` returns:

```text
10
```

The queue remains unchanged.

---

## Q9. Difference between `poll()` and `peek()`?

| `poll()` | `peek()` |
|---|---|
| Returns head | Returns head |
| Removes head | Does not remove head |
| Changes queue | Does not change queue |
| Empty queue → `null` | Empty queue → `null` |

---

## Q10. What does `add()` do?

`add()` inserts an element.

```java
queue.add(10);
```

It is similar to `offer()` for many unbounded queues.

The important difference appears when insertion cannot be accepted.

---

# 3. Queue Method Pairs

## Q11. Difference between `add()` and `offer()`?

Both attempt to insert an element.

| `add()` | `offer()` |
|---|---|
| Inserts element | Inserts element |
| Returns `boolean` | Returns `boolean` |
| Throws `IllegalStateException` if insertion cannot be performed because of capacity | Returns `false` in that situation |

For an unbounded queue such as `ArrayDeque`, capacity failure normally isn't encountered during ordinary use.

### Interview Answer

> `add()` and `offer()` both insert elements, but `add()` may throw an exception if insertion cannot be performed, whereas `offer()` reports insertion failure by returning `false`.

---

## Q12. Difference between `remove()` and `poll()`?

Both remove and return the head.

The difference is what happens when the queue is empty.

| Method | Empty Queue |
|---|---|
| `remove()` | `NoSuchElementException` |
| `poll()` | `null` |

Example:

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.poll();
```

returns:

```text
null
```

But:

```java
queue.remove();
```

throws:

```text
NoSuchElementException
```

---

## Q13. Difference between `element()` and `peek()`?

Both inspect the head without removing it.

| Method | Empty Queue |
|---|---|
| `element()` | `NoSuchElementException` |
| `peek()` | `null` |

---

## Q14. Which Queue methods are generally preferred when you want graceful empty handling?

Usually:

```text
offer()
poll()
peek()
```

These methods use return values to communicate certain failure/empty states rather than throwing exceptions.

---

## Q15. Explain all six methods together.

### Insert

```text
add(e)
offer(e)
```

### Remove

```text
remove()
poll()
```

### Inspect

```text
element()
peek()
```

Memory table:

| Purpose | Exception-based method | Return-value-based method |
|---|---|---|
| Insert | `add()` | `offer()` |
| Remove | `remove()` | `poll()` |
| Inspect | `element()` | `peek()` |

---

# 4. Queue Hierarchy

## Q16. What is the Queue hierarchy?

A simplified hierarchy:

```text
Iterable
   ↓
Collection
   ↓
Queue
   ├── PriorityQueue
   │
   └── Deque
        ├── ArrayDeque
        └── LinkedList
```

`LinkedList` also implements `List`, so its complete relationship is broader than this simplified diagram.

---

## Q17. Is Deque a Queue?

Yes.

`Deque` extends `Queue`.

```text
Queue
  ↓
Deque
```

A deque supports operations at both ends.

---

## Q18. Can LinkedList be used as a Queue?

Yes.

`LinkedList` implements `Deque`, which extends `Queue`.

Therefore:

```java
Queue<Integer> queue = new LinkedList<>();
```

is valid.

---

## Q19. Can ArrayDeque be used as a Queue?

Yes.

```java
Queue<Integer> queue = new ArrayDeque<>();
```

It provides efficient operations at both ends and can be used as either a queue or a stack.

---

# 5. Queue Implementations

## Q20. What are common Queue implementations in Java?

Common examples include:

```text
LinkedList
ArrayDeque
PriorityQueue
```

Each has different behavior and characteristics.

---

## Q21. Which implementation is generally preferred for a normal FIFO queue?

For typical queue usage in new Java code:

```java
Queue<Integer> queue = new ArrayDeque<>();
```

is generally preferred.

---

## Q22. Why is ArrayDeque preferred for typical queue usage?

Because it:

- is designed for deque/queue operations
- provides efficient operations at both ends
- avoids the node-based structure of `LinkedList`
- does not have the legacy design associated with some older collection classes
- is generally a good choice for typical single-threaded queue usage

---

## Q23. Why can LinkedList be used as a Queue?

Because it implements `Deque`, and `Deque` extends `Queue`.

```text
LinkedList
   ↓
Deque
   ↓
Queue
```

Therefore:

```java
Queue<Integer> queue = new LinkedList<>();
```

is valid.

---

# 6. ArrayDeque vs LinkedList

## Q24. What is the difference between ArrayDeque and LinkedList as a Queue?

| Feature | ArrayDeque | LinkedList |
|---|---|---|
| Data structure | Resizable array/deque structure | Doubly linked list |
| Queue operations | Efficient | Efficient |
| Deque operations | Yes | Yes |
| Allows `null` | No | Yes |
| Synchronized | No | No |
| Typical queue choice | Generally preferred | Valid alternative |

---

## Q25. Does ArrayDeque allow null?

No.

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.offer(null);
```

This results in:

```text
NullPointerException
```

---

## Q26. Does LinkedList allow null?

Yes.

```java
Queue<Integer> queue = new LinkedList<>();

queue.offer(null);
```

However, using `null` as a queue element can make return values such as `poll()` or `peek()` ambiguous, because those methods use `null` to indicate an empty queue.

---

## Q27. Is ArrayDeque synchronized?

No.

`ArrayDeque` is not synchronized.

For concurrent access, use an appropriate concurrent collection or external synchronization depending on the requirements.

---

# 7. PriorityQueue

## Q28. Is PriorityQueue a Queue?

Yes.

```java
PriorityQueue<Integer> queue = new PriorityQueue<>();
```

It implements `Queue`.

---

## Q29. Does PriorityQueue follow FIFO?

Not in the ordinary insertion-order sense.

`PriorityQueue` orders elements according to their natural ordering or a supplied `Comparator`.

For integers:

```java
Queue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);
```

The head is:

```text
10
```

---

## Q30. What is the difference between Queue and PriorityQueue?

A normal FIFO queue:

```text
Insert:
30 → 10 → 20

Remove:
30 → 10 → 20
```

A `PriorityQueue` with natural integer ordering:

```text
Insert:
30 → 10 → 20

Remove:
10 → 20 → 30
```

### Important

The `Queue` interface does not force every implementation to use simple insertion-order FIFO.

The implementation determines its ordering policy.

---

## Q31. Is the internal array of PriorityQueue sorted?

No.

A `PriorityQueue` is typically implemented using a heap structure.

The internal representation is not a fully sorted array.

What matters is that the head is the highest-priority element according to the queue's ordering.

---

## Q32. What is the time complexity of PriorityQueue operations?

Typical complexities:

| Operation | Complexity |
|---|---:|
| `offer()` | O(log n) |
| `poll()` | O(log n) |
| `peek()` | O(1) |

---

# 8. Null Values and Duplicates

## Q33. Can Queue contain duplicates?

Yes, depending on the implementation.

For example:

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(10);
queue.offer(20);
```

The queue can contain:

```text
10 → 10 → 20
```

---

## Q34. Can all Queue implementations contain null?

No.

Null support depends on the implementation.

Examples:

```text
LinkedList  → allows null
ArrayDeque  → does not allow null
PriorityQueue → does not allow null
```

---

## Q35. Why does ArrayDeque reject null?

Because methods such as:

```java
peek()
poll()
```

can use `null` to indicate that no element is available.

Allowing `null` as a legitimate element would make that result ambiguous.

---

# 9. Time Complexity

## Q36. What is the complexity of ArrayDeque queue operations?

Typical:

| Operation | Complexity |
|---|---:|
| `offer()` | O(1) amortized |
| `poll()` | O(1) |
| `peek()` | O(1) |
| `isEmpty()` | O(1) |
| `size()` | O(1) |

---

## Q37. What is the complexity of LinkedList queue operations?

For operations at the ends:

| Operation | Complexity |
|---|---:|
| `offer()` | O(1) |
| `poll()` | O(1) |
| `peek()` | O(1) |
| `isEmpty()` | O(1) |
| `size()` | O(1) |

---

## Q38. Why is PriorityQueue different?

Because it maintains a heap structure to efficiently manage priorities.

Typical:

```text
offer() → O(log n)
poll()  → O(log n)
peek()  → O(1)
```

---

# 10. Code-Based Questions

## Q39. What is the output?

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.poll());
```

### Answer

```text
10
```

---

## Q40. What is the output?

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.peek());
System.out.println(queue.size());
```

### Answer

```text
10
3
```

`peek()` does not remove anything.

---

## Q41. What is the output?

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);

System.out.println(queue.poll());
System.out.println(queue.peek());
```

### Answer

```text
10
20
```

---

## Q42. What happens?

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.poll();
```

### Answer

```text
null
```

---

## Q43. What happens?

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.remove();
```

### Answer

```text
NoSuchElementException
```

---

## Q44. What happens?

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.peek();
```

### Answer

```text
null
```

---

## Q45. What happens?

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.element();
```

### Answer

```text
NoSuchElementException
```

---

## Q46. What is the output?

```java
Queue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);

System.out.println(queue.poll());
```

### Answer

```text
10
```

---

## Q47. What is the output?

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

while (!queue.isEmpty()) {
    System.out.println(queue.poll());
}
```

### Answer

```text
10
20
30
```

---

## Q48. What happens?

```java
Queue<String> queue = new ArrayDeque<>();

queue.offer(null);
```

### Answer

```text
NullPointerException
```

---

## Q49. What is the output?

```java
Queue<Integer> queue = new LinkedList<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

for (Integer value : queue) {
    System.out.println(value);
}

System.out.println(queue.size());
```

### Answer

```text
10
20
30
3
```

Iteration does not remove the elements.

---

# 11. Scenario-Based Questions

## Q50. You are building a printer system. Which data structure would you use?

A queue is a natural choice when documents should normally be processed in arrival order.

```text
Document A
Document B
Document C
```

Processing:

```text
A → B → C
```

---

## Q51. Which data structure is commonly used for BFS?

**Queue.**

BFS explores nodes level by level.

A typical implementation is:

```java
Queue<Node> queue = new ArrayDeque<>();
```

---

## Q52. You need to implement an Undo feature. Queue or Stack?

**Stack.**

Undo usually follows LIFO:

```text
Action A
Action B
Action C

Undo C
Undo B
Undo A
```

---

## Q53. A hospital wants to process patients based on priority. Which structure is useful?

A `PriorityQueue` can be useful when processing should be based on priority rather than simple arrival order.

---

## Q54. You need a normal FIFO queue in new Java code. What would you choose?

A strong answer:

```java
Queue<Integer> queue = new ArrayDeque<>();
```

---

## Q55. Why not always use LinkedList as Queue?

`LinkedList` works correctly as a queue, but `ArrayDeque` is generally a better fit for typical queue/deque operations.

The choice should depend on requirements, but for ordinary FIFO behavior, `ArrayDeque` is usually the preferred modern option.

---

## Q56. You need a queue that supports null values. Can you use ArrayDeque?

No.

`ArrayDeque` rejects `null`.

You need an implementation that supports the requirement, such as `LinkedList`, while also considering whether `null` creates ambiguity in your application.

---

## Q57. Multiple threads need to share a queue. Is ArrayDeque enough?

No.

`ArrayDeque` is not thread-safe.

For concurrent applications, choose an appropriate concurrent queue or synchronization strategy based on the use case.

---

# 12. Common Interview Traps

## Trap 1 — Saying Queue is a class

Incorrect:

```text
Queue is a class.
```

Correct:

```text
Queue is an interface.
```

---

## Trap 2 — Saying Queue always means FIFO

The common queue model is FIFO, but specific implementations can define different ordering policies.

For example:

```text
PriorityQueue → priority-based ordering
```

---

## Trap 3 — Confusing `poll()` and `peek()`

`poll()`:

```text
remove + return
```

`peek()`:

```text
return without removing
```

---

## Trap 4 — Confusing `remove()` and `poll()`

On an empty queue:

```text
remove() → NoSuchElementException
poll()   → null
```

---

## Trap 5 — Confusing `element()` and `peek()`

On an empty queue:

```text
element() → NoSuchElementException
peek()    → null
```

---

## Trap 6 — Saying ArrayDeque allows null

Incorrect.

```text
ArrayDeque → null not allowed
```

---

## Trap 7 — Saying PriorityQueue preserves insertion order

It does not.

The head is determined by priority ordering.

---

## Trap 8 — Saying PriorityQueue is completely sorted internally

Incorrect.

It is heap-based rather than maintaining a fully sorted internal representation.

---

## Trap 9 — Saying LinkedList is the best queue in all cases

LinkedList is a valid Queue implementation, but `ArrayDeque` is generally preferred for typical queue/deque usage.

---

## Trap 10 — Saying iteration removes elements

It does not.

```java
for (Integer value : queue) {
    System.out.println(value);
}
```

does not remove the elements.

---

## Trap 11 — Ignoring implementation-specific complexity

Do not memorize:

```text
Queue → O(1)
```

as a universal rule.

The actual complexity depends on the implementation.

---

# 13. Rapid-Fire Questions

### Q1. What is Queue?

An interface representing a collection designed for processing elements in a particular order.

### Q2. What is the common Queue principle?

FIFO.

### Q3. What does FIFO mean?

First In, First Out.

### Q4. Is Queue a class?

No. It is an interface.

### Q5. Which package contains Queue?

```java
java.util
```

### Q6. What is used to insert into a Queue?

```java
offer()
```

or:

```java
add()
```

### Q7. What is used to remove the head?

```java
poll()
```

or:

```java
remove()
```

### Q8. What is used to view the head?

```java
peek()
```

or:

```java
element()
```

### Q9. What does `poll()` return on an empty queue?

`null`.

### Q10. What does `remove()` do on an empty queue?

Throws `NoSuchElementException`.

### Q11. What does `peek()` return on an empty queue?

`null`.

### Q12. What does `element()` do on an empty queue?

Throws `NoSuchElementException`.

### Q13. What is a common modern Queue implementation?

`ArrayDeque`.

### Q14. Can LinkedList be used as Queue?

Yes.

### Q15. Can ArrayDeque be used as Queue?

Yes.

### Q16. Can ArrayDeque be used as Stack?

Yes, through `Deque` operations such as `push()` and `pop()`.

### Q17. Does ArrayDeque allow null?

No.

### Q18. Does LinkedList allow null?

Yes.

### Q19. Does Queue allow duplicates?

Generally yes, depending on the implementation.

### Q20. Does PriorityQueue use normal FIFO?

No.

### Q21. What determines PriorityQueue ordering?

Natural ordering or a supplied `Comparator`.

### Q22. Is ArrayDeque synchronized?

No.

### Q23. What is the typical complexity of ArrayDeque `offer()`?

O(1) amortized.

### Q24. What is the typical complexity of ArrayDeque `poll()`?

O(1).

### Q25. What is the typical complexity of ArrayDeque `peek()`?

O(1).

### Q26. What is the typical complexity of PriorityQueue `offer()`?

O(log n).

### Q27. What is the typical complexity of PriorityQueue `poll()`?

O(log n).

### Q28. What is the typical complexity of PriorityQueue `peek()`?

O(1).

---

# 14. Must-Know Questions

Before an interview, make sure you can answer these without hesitation:

- [ ] What is a Queue?
- [ ] What is FIFO?
- [ ] Is Queue a class or interface?
- [ ] What are the main Queue operations?
- [ ] `add()` vs `offer()`
- [ ] `remove()` vs `poll()`
- [ ] `element()` vs `peek()`
- [ ] What happens when a Queue is empty?
- [ ] What is `NoSuchElementException`?
- [ ] What is a common implementation of Queue?
- [ ] Why is ArrayDeque generally preferred?
- [ ] How can LinkedList be used as Queue?
- [ ] What is Deque?
- [ ] Is Deque a Queue?
- [ ] What is PriorityQueue?
- [ ] Does PriorityQueue follow FIFO?
- [ ] How does PriorityQueue determine priority?
- [ ] Does ArrayDeque allow null?
- [ ] Does LinkedList allow null?
- [ ] Can Queue contain duplicates?
- [ ] What are the typical complexities of queue operations?
- [ ] How is Queue used in BFS?
- [ ] Difference between Queue and Stack?

---

# 15. Final Interview Answer

## ⭐ Best Short Answer

> **Queue is an interface in the Java Collections Framework used for processing elements in a particular order, commonly FIFO — First In, First Out. Its main operations are `offer()`, `poll()`, and `peek()`. Java provides implementations such as `ArrayDeque`, `LinkedList`, and `PriorityQueue`. For typical FIFO queue usage, `ArrayDeque` is generally preferred in new code. `PriorityQueue` is different because it processes elements according to priority rather than simple insertion order.**

---

## ⭐ If Asked About Queue Methods

> **Queue provides paired methods for insertion, removal, and inspection. `add()` and `offer()` insert elements; `remove()` and `poll()` remove the head; and `element()` and `peek()` inspect the head. The exception-based methods throw exceptions when the operation cannot be performed, while `offer()`, `poll()`, and `peek()` use return values such as `false` or `null` for certain failure or empty cases.**

---

## ⭐ If Asked Why ArrayDeque?

> **For typical queue usage, I would use `Queue<E> queue = new ArrayDeque<>();`. `ArrayDeque` is a modern, efficient deque implementation that supports queue operations at both ends and is generally preferred over `LinkedList` for ordinary queue/deque workloads. It does not allow `null` and is not synchronized.**

---

# 16. Interview Checklist

## Fundamentals

- [ ] Queue definition
- [ ] FIFO
- [ ] Front
- [ ] Rear
- [ ] Queue interface
- [ ] Java Collections Framework

## Methods

- [ ] `add()`
- [ ] `offer()`
- [ ] `remove()`
- [ ] `poll()`
- [ ] `element()`
- [ ] `peek()`
- [ ] `size()`
- [ ] `isEmpty()`

## Method Differences

- [ ] `add()` vs `offer()`
- [ ] `remove()` vs `poll()`
- [ ] `element()` vs `peek()`

## Implementations

- [ ] LinkedList
- [ ] ArrayDeque
- [ ] PriorityQueue
- [ ] Deque

## Edge Cases

- [ ] Empty queue
- [ ] `poll()` → `null`
- [ ] `remove()` → `NoSuchElementException`
- [ ] `peek()` → `null`
- [ ] `element()` → `NoSuchElementException`
- [ ] Duplicate elements
- [ ] Null handling

## PriorityQueue

- [ ] Priority-based ordering
- [ ] Natural ordering
- [ ] Comparator
- [ ] Heap concept
- [ ] O(log n) insertion/removal

## Complexity

- [ ] ArrayDeque `offer()` → O(1) amortized
- [ ] ArrayDeque `poll()` → O(1)
- [ ] ArrayDeque `peek()` → O(1)
- [ ] PriorityQueue `offer()` → O(log n)
- [ ] PriorityQueue `poll()` → O(log n)
- [ ] PriorityQueue `peek()` → O(1)

## Problem Solving

- [ ] BFS
- [ ] Level-order traversal
- [ ] Queue using stacks
- [ ] First non-repeating character
- [ ] Circular queue
- [ ] Sliding window problems

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

**Queue is now complete. Next: `09-PriorityQueue/NOTES.md`**
