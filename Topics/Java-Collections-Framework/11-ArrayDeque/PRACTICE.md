# ArrayDeque — Practice

## Table of Contents

1. [Practice Goals](#practice-goals)
2. [Creating an ArrayDeque](#creating-an-arraydeque)
3. [Adding Elements](#adding-elements)
4. [Removing Elements](#removing-elements)
5. [Peeking and Inspecting](#peeking-and-inspecting)
6. [Using ArrayDeque as a Queue](#using-arraydeque-as-a-queue)
7. [Using ArrayDeque as a Stack](#using-arraydeque-as-a-stack)
8. [Front and Back Operations](#front-and-back-operations)
9. [Method Behavior Practice](#method-behavior-practice)
10. [Duplicates and Null](#duplicates-and-null)
11. [Iteration Practice](#iteration-practice)
12. [Output Prediction](#output-prediction)
13. [Basic Coding Problems](#basic-coding-problems)
14. [Intermediate Coding Problems](#intermediate-coding-problems)
15. [Challenge Problems](#challenge-problems)
16. [Scenario-Based Practice](#scenario-based-practice)
17. [Comparison Practice](#comparison-practice)
18. [Practice Checklist](#practice-checklist)
19. [Final Goal](#final-goal)
20. [Progress](#progress)

---

# 1. Practice Goals

By completing this practice file, you should be able to:

- [ ] Create an `ArrayDeque`
- [ ] Add elements at the front
- [ ] Add elements at the back
- [ ] Remove elements from the front
- [ ] Remove elements from the back
- [ ] Peek at both ends
- [ ] Use `ArrayDeque` as a Queue
- [ ] Use `ArrayDeque` as a Stack
- [ ] Understand `add()` vs `offer()`
- [ ] Understand `remove()` vs `poll()`
- [ ] Understand `element()` vs `peek()`
- [ ] Understand why `null` is not allowed
- [ ] Traverse in normal and reverse order
- [ ] Solve Queue and Stack problems using `ArrayDeque`
- [ ] Solve BFS and DFS problems
- [ ] Solve sliding-window problems using a Deque
- [ ] Choose between `ArrayDeque`, `ArrayList`, `LinkedList`, `Stack`, and `PriorityQueue`

---

# 2. Creating an ArrayDeque

## Basic Creation

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {

        Deque<Integer> deque = new ArrayDeque<>();

        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);

        System.out.println(deque);
    }
}
```

### Expected Output

```text
[10, 20, 30]
```

---

## Using the `ArrayDeque` Type Directly

```java
ArrayDeque<Integer> deque = new ArrayDeque<>();
```

However, prefer programming to the interface:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

This makes the code easier to change later.

---

## Initial Capacity

```java
ArrayDeque<Integer> deque = new ArrayDeque<>(10);
```

The value is an initial capacity hint.

It does **not** mean the deque can contain only 10 elements.

---

# 3. Adding Elements

## `addFirst()`

Adds an element to the front.

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addFirst(20);
deque.addFirst(10);
deque.addFirst(5);

System.out.println(deque);
```

### Output

```text
[5, 10, 20]
```

---

## `addLast()`

Adds an element to the back.

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);

System.out.println(deque);
```

### Output

```text
[10, 20, 30]
```

---

## `offerFirst()`

```java
deque.offerFirst(10);
```

Adds an element at the front.

---

## `offerLast()`

```java
deque.offerLast(20);
```

Adds an element at the back.

---

## Practice

What will be the output?

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addFirst(5);
deque.addFirst(1);

System.out.println(deque);
```

### Answer

```text
[1, 5, 10, 20]
```

---

# 4. Removing Elements

## `removeFirst()`

Removes and returns the first element.

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);

int value = deque.removeFirst();

System.out.println(value);
System.out.println(deque);
```

### Output

```text
10
[20, 30]
```

---

## `removeLast()`

Removes and returns the last element.

```java
int value = deque.removeLast();
```

---

## `pollFirst()`

Removes and returns the first element.

```java
int value = deque.pollFirst();
```

If the deque is empty:

```java
null
```

is returned.

---

## `pollLast()`

Removes and returns the last element.

```java
int value = deque.pollLast();
```

---

## Practice

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);
deque.addLast(40);

deque.removeFirst();
deque.removeLast();

System.out.println(deque);
```

### Answer

```text
[20, 30]
```

---

# 5. Peeking and Inspecting

## `getFirst()`

Returns the first element without removing it.

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);

System.out.println(deque.getFirst());
System.out.println(deque);
```

### Output

```text
10
[10, 20]
```

---

## `getLast()`

Returns the last element without removing it.

```java
System.out.println(deque.getLast());
```

---

## `peekFirst()`

Returns the first element without removing it.

```java
System.out.println(deque.peekFirst());
```

Returns `null` if empty.

---

## `peekLast()`

Returns the last element without removing it.

```java
System.out.println(deque.peekLast());
```

---

# 6. Using ArrayDeque as a Queue

A Queue follows:

```text
FIFO
First In → First Out
```

Use:

```java
offerLast()
pollFirst()
peekFirst()
```

### Example

```java
Deque<Integer> queue = new ArrayDeque<>();

queue.offerLast(10);
queue.offerLast(20);
queue.offerLast(30);

System.out.println(queue);

System.out.println(queue.pollFirst());
System.out.println(queue.pollFirst());

System.out.println(queue);
```

### Output

```text
[10, 20, 30]

10
20

[30]
```

---

## Queue Pattern

```text
offerLast() → add at back
pollFirst()  → remove from front
peekFirst()  → inspect front
```

Visual:

```text
Front                       Back
  ↓                           ↓
[10] [20] [30] [40] [50]
  ↑
remove here

                     add here
```

---

## Practice

Implement a simple task queue:

```java
Deque<String> tasks = new ArrayDeque<>();
```

Perform:

1. Add `"Task 1"`
2. Add `"Task 2"`
3. Add `"Task 3"`
4. Process the first task
5. Process the second task
6. Print remaining tasks

---

# 7. Using ArrayDeque as a Stack

A Stack follows:

```text
LIFO
Last In → First Out
```

Use:

```java
push()
pop()
peek()
```

### Example

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack);

System.out.println(stack.pop());
System.out.println(stack.pop());

System.out.println(stack);
```

### Output

```text
[30, 20, 10]

30
20

[10]
```

---

## Stack Pattern

```text
push() → add to front
pop()  → remove from front
peek() → inspect front
```

Visual:

```text
      TOP
       ↓
     [30]
     [20]
     [10]
```

---

## Practice

Use an `ArrayDeque<Integer>` to:

1. Push `10`
2. Push `20`
3. Push `30`
4. Pop one element
5. Push `40`
6. Peek at the top
7. Print the stack

---

# 8. Front and Back Operations

Practice the following methods:

| Operation | Front | Back |
|---|---|---|
| Add | `addFirst()` | `addLast()` |
| Offer | `offerFirst()` | `offerLast()` |
| Remove | `removeFirst()` | `removeLast()` |
| Poll | `pollFirst()` | `pollLast()` |
| Inspect | `getFirst()` | `getLast()` |
| Peek | `peekFirst()` | `peekLast()` |

---

## Practice Program

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addFirst(20);
deque.addLast(30);
deque.addFirst(10);
deque.addLast(40);

System.out.println(deque);

System.out.println("First: " + deque.peekFirst());
System.out.println("Last: " + deque.peekLast());

deque.removeFirst();
deque.removeLast();

System.out.println(deque);
```

### Expected Output

```text
[10, 20, 30, 40]

First: 10
Last: 40

[20, 30]
```

---

# 9. Method Behavior Practice

A very important interview concept is understanding which methods throw exceptions and which return special values.

## Add Operations

| Method | Behavior |
|---|---|
| `addFirst()` | Adds at front |
| `addLast()` | Adds at back |
| `offerFirst()` | Adds at front |
| `offerLast()` | Adds at back |

For `ArrayDeque`, these methods normally succeed unless an invalid element such as `null` is supplied.

---

## Remove Operations

| Method | Empty Deque |
|---|---|
| `removeFirst()` | Throws `NoSuchElementException` |
| `removeLast()` | Throws `NoSuchElementException` |
| `pollFirst()` | Returns `null` |
| `pollLast()` | Returns `null` |

---

## Inspect Operations

| Method | Empty Deque |
|---|---|
| `getFirst()` | Throws `NoSuchElementException` |
| `getLast()` | Throws `NoSuchElementException` |
| `peekFirst()` | Returns `null` |
| `peekLast()` | Returns `null` |

---

## Queue-Style Methods

| Method | Empty Deque |
|---|---|
| `remove()` | Throws `NoSuchElementException` |
| `poll()` | Returns `null` |
| `element()` | Throws `NoSuchElementException` |
| `peek()` | Returns `null` |

---

## Practice Question

What happens here?

```java
Deque<Integer> deque = new ArrayDeque<>();

System.out.println(deque.pollFirst());
```

### Answer

```text
null
```

What happens here?

```java
Deque<Integer> deque = new ArrayDeque<>();

System.out.println(deque.removeFirst());
```

### Answer

```text
NoSuchElementException
```

---

# 10. Duplicates and Null

## Duplicates Are Allowed

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(10);

System.out.println(deque);
```

### Output

```text
[10, 20, 10]
```

---

## `null` Is Not Allowed

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.add(null);
```

This throws:

```text
NullPointerException
```

The same restriction applies to operations such as:

```java
deque.offer(null);
deque.addFirst(null);
deque.addLast(null);
```

---

## Why Is `null` Not Allowed?

Because `ArrayDeque` uses `null` as a possible return value from methods such as:

```java
poll()
peek()
pollFirst()
peekFirst()
```

Allowing `null` elements would make it difficult to distinguish:

```text
"the deque contains null"
```

from:

```text
"the deque is empty"
```

---

# 11. Iteration Practice

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

### Output

```text
10
20
30
```

---

## Using an Iterator

```java
Iterator<Integer> iterator = deque.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

Remember to import:

```java
import java.util.Iterator;
```

---

## Reverse Iteration

Use:

```java
descendingIterator()
```

Example:

```java
Iterator<Integer> iterator = deque.descendingIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

### Output

```text
30
20
10
```

---

## Practice

Given:

```java
Deque<String> deque = new ArrayDeque<>();

deque.addLast("A");
deque.addLast("B");
deque.addLast("C");
deque.addLast("D");
```

Print:

```text
D
C
B
A
```

using `descendingIterator()`.

---

# 12. Output Prediction

## Question 1

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

deque.offerLast(10);
deque.offerLast(20);
deque.offerLast(30);

System.out.println(deque.pollFirst());
System.out.println(deque);
```

### Answer

```text
10
[20, 30]
```

---

## Question 3

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.push(10);
deque.push(20);
deque.push(30);

System.out.println(deque.pop());
System.out.println(deque);
```

### Answer

```text
30
[20, 10]
```

---

## Question 4

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addFirst(20);
deque.addLast(30);
deque.removeFirst();

System.out.println(deque);
```

### Answer

```text
[10, 30]
```

---

## Question 5

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);

System.out.println(deque.peekFirst());
System.out.println(deque.peekLast());
System.out.println(deque);
```

### Answer

```text
10
20
[10, 20]
```

---

# 13. Basic Coding Problems

## Problem 1 — Create and Display

Create an `ArrayDeque<Integer>` and add:

```text
10, 20, 30, 40, 50
```

Print the deque.

### Expected

```text
[10, 20, 30, 40, 50]
```

---

## Problem 2 — Add at Both Ends

Given:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

Perform:

```text
Add 20 at back
Add 10 at front
Add 30 at back
Add 5 at front
```

Expected:

```text
[5, 10, 20, 30]
```

---

## Problem 3 — Remove from Both Ends

Given:

```text
[10, 20, 30, 40, 50]
```

Remove:

- first element
- last element

Expected:

```text
[20, 30, 40]
```

---

## Problem 4 — Implement a Queue

Use `ArrayDeque` to implement:

```text
enqueue()
dequeue()
peek()
```

Expected behavior:

```text
enqueue(10)
enqueue(20)
enqueue(30)

dequeue() → 10
peek()    → 20
```

---

## Problem 5 — Implement a Stack

Use `ArrayDeque` to implement:

```text
push()
pop()
peek()
```

Expected behavior:

```text
push(10)
push(20)
push(30)

pop()  → 30
peek() → 20
```

---

## Problem 6 — Reverse a String

Use `ArrayDeque<Character>` to reverse:

```text
"JAVA"
```

Expected:

```text
AVAJ
```

### Hint

Push every character into the deque and then pop characters.

---

## Problem 7 — Palindrome Check

Check whether a string is a palindrome using a Deque.

Examples:

```text
"madam" → true
"level" → true
"java"  → false
```

---

## Problem 8 — Reverse a Queue

Given:

```text
[10, 20, 30, 40, 50]
```

Reverse it using an `ArrayDeque`.

Expected:

```text
[50, 40, 30, 20, 10]
```

---

# 14. Intermediate Coding Problems

## Problem 9 — BFS Traversal

Use `ArrayDeque` as a queue to implement Breadth-First Search.

For example:

```text
        1
       / \
      2   3
     / \
    4   5
```

Expected BFS:

```text
1 2 3 4 5
```

### Core idea

```java
Deque<Integer> queue = new ArrayDeque<>();

queue.offerLast(start);

while (!queue.isEmpty()) {
    int node = queue.pollFirst();

    // process node
}
```

---

## Problem 10 — DFS Traversal

Use `ArrayDeque` as a stack to implement iterative Depth-First Search.

### Core idea

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(start);

while (!stack.isEmpty()) {
    int node = stack.pop();

    // process node
}
```

---

## Problem 11 — First Negative Number in Every Window

Given:

```text
arr = [12, -1, -7, 8, -15, 30, 16, 28]
k = 3
```

Find the first negative number in every window of size `3`.

Expected:

```text
-1
-1
-7
-15
-15
-15
```

### Hint

Store useful indices in a Deque.

---

## Problem 12 — Sliding Window Maximum

Given:

```text
arr = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3
```

Expected:

```text
[3, 3, 5, 5, 6, 7]
```

### Important

Use a monotonic Deque.

The target complexity should be:

```text
O(n)
```

---

## Problem 13 — Sliding Window Minimum

Given:

```text
arr = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3
```

Find the minimum element in every window.

Expected:

```text
[1, -1, -3, -3, 3, 3]
```

---

## Problem 14 — Undo Operations

Use an `ArrayDeque<String>` to simulate an undo stack.

Example:

```text
Type: A
Type: B
Type: C
Undo
Undo
```

Expected current state:

```text
A
```

---

## Problem 15 — Two-Ended Task Processing

A system receives urgent tasks at the front and normal tasks at the back.

Implement:

```text
addUrgentTask()
addNormalTask()
processUrgentTask()
processNormalTask()
```

Use an `ArrayDeque<String>`.

---

# 15. Challenge Problems

## Challenge 1 — Monotonic Deque

Implement a data structure that maintains elements in decreasing order.

Example:

```text
Input:
[10, 5, 12, 3, 8]

Deque behavior:
10
5
12 → remove smaller elements first
...
```

Understand why this technique is useful for:

- Sliding window maximum
- Maintaining maximum candidates
- Optimizing range queries

---

## Challenge 2 — Sliding Window Maximum in O(n)

Solve:

```text
arr = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3
```

Expected:

```text
[3, 3, 5, 5, 6, 7]
```

### Requirements

- Use `ArrayDeque<Integer>`
- Store indices
- Remove indices outside the current window
- Remove smaller values from the back
- Keep the largest candidate at the front
- Achieve `O(n)` time

---

## Challenge 3 — Palindrome Using Two Ends

Use a Deque to compare:

```text
front character
```

with:

```text
back character
```

For:

```text
"racecar"
```

Expected:

```text
true
```

---

## Challenge 4 — Queue Using Two Stacks

Implement a Queue using two `ArrayDeque<Integer>` stacks.

Required operations:

```text
enqueue()
dequeue()
peek()
```

Think about:

```text
input stack
output stack
```

---

## Challenge 5 — Stack Using Two Queues

Implement a Stack using two `ArrayDeque<Integer>` queues.

Required operations:

```text
push()
pop()
peek()
```

---

# 16. Scenario-Based Practice

## Scenario 1 — BFS

You need to traverse a graph level by level.

Which structure should you use?

```text
ArrayDeque as Queue
```

Why?

Because BFS requires:

```text
FIFO
```

---

## Scenario 2 — DFS

You need iterative depth-first traversal.

Which structure?

```text
ArrayDeque as Stack
```

Why?

Because DFS naturally follows:

```text
LIFO
```

---

## Scenario 3 — Undo Operations

You need to store recent operations and undo the most recent one first.

Use:

```text
ArrayDeque
```

with:

```java
push()
pop()
```

---

## Scenario 4 — Priority-Based Processing

Tasks must be processed according to priority:

```text
High
Medium
Low
```

Should you use `ArrayDeque`?

```text
No
```

A `PriorityQueue` is more appropriate when processing order depends on priority rather than insertion order.

---

## Scenario 5 — Random Index Access

You frequently need:

```java
list.get(500);
```

Should you use `ArrayDeque`?

```text
No
```

A `List`, such as `ArrayList`, is more appropriate for indexed access.

---

## Scenario 6 — Add and Remove from Both Ends

You frequently need:

```text
add at front
add at back
remove from front
remove from back
```

Use:

```text
ArrayDeque
```

---

# 17. Comparison Practice

## ArrayDeque vs ArrayList

### Question

You need:

- indexed access
- `get(index)`
- list-style operations

Which should you choose?

```text
ArrayList
```

If you need:

- add/remove at both ends
- queue behavior
- stack behavior

Prefer:

```text
ArrayDeque
```

---

## ArrayDeque vs LinkedList

Both can implement `Deque`.

### Question

You need a general-purpose deque and do not need `List` operations.

Which is usually the better default?

```text
ArrayDeque
```

Why?

- Array-based storage
- Lower per-element node overhead
- Efficient end operations
- Good cache locality

---

## ArrayDeque vs Stack

### Question

You need LIFO behavior.

Which is preferred in modern Java?

```text
ArrayDeque
```

Example:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

rather than:

```java
Stack<Integer> stack = new Stack<>();
```

---

## ArrayDeque vs PriorityQueue

### Question

You need the smallest element first.

Which should you use?

```text
PriorityQueue
```

### Question

You need insertion/removal from both ends.

Which should you use?

```text
ArrayDeque
```

---

# 18. Practice Checklist

## Basic

- [ ] Create an `ArrayDeque`
- [ ] Add elements using `addFirst()`
- [ ] Add elements using `addLast()`
- [ ] Add elements using `offerFirst()`
- [ ] Add elements using `offerLast()`
- [ ] Remove using `removeFirst()`
- [ ] Remove using `removeLast()`
- [ ] Remove using `pollFirst()`
- [ ] Remove using `pollLast()`
- [ ] Inspect using `peekFirst()`
- [ ] Inspect using `peekLast()`

## Queue

- [ ] Use `ArrayDeque` as FIFO Queue
- [ ] Understand `offerLast()`
- [ ] Understand `pollFirst()`
- [ ] Understand `peekFirst()`

## Stack

- [ ] Use `ArrayDeque` as LIFO Stack
- [ ] Understand `push()`
- [ ] Understand `pop()`
- [ ] Understand `peek()`

## Edge Cases

- [ ] Understand empty deque behavior
- [ ] Understand `NoSuchElementException`
- [ ] Understand `null` restriction
- [ ] Understand duplicate elements
- [ ] Understand that the deque is not sorted

## Iteration

- [ ] Use enhanced `for` loop
- [ ] Use `iterator()`
- [ ] Use `descendingIterator()`

## Problem Solving

- [ ] Reverse a string
- [ ] Check palindrome
- [ ] Implement Queue
- [ ] Implement Stack
- [ ] BFS
- [ ] DFS
- [ ] First negative in every window
- [ ] Sliding window maximum
- [ ] Sliding window minimum
- [ ] Monotonic Deque

---

# 19. Final Goal

Before moving to the interview questions, you should be comfortable writing:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

and immediately understanding:

```java
deque.addFirst(x);
deque.addLast(x);

deque.removeFirst();
deque.removeLast();

deque.pollFirst();
deque.pollLast();

deque.peekFirst();
deque.peekLast();
```

You should also be comfortable using:

```java
deque.push(x);
deque.pop();
deque.peek();
```

for Stack behavior.

And:

```java
deque.offerLast(x);
deque.pollFirst();
deque.peekFirst();
```

for Queue behavior.

The main idea to remember:

```text
ArrayDeque
    │
    ├── Queue → FIFO
    │
    ├── Stack → LIFO
    │
    └── Deque  → both ends
```

---

# 20. Progress

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
```

**ArrayDeque `PRACTICE.md` is now complete. Next: `11-ArrayDeque/INTERVIEW.md`.**
