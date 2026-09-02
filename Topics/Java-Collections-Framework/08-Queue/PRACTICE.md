# Queue — Practice

> Practice problems to build a strong understanding of the Java `Queue` interface, its operations, implementations, and real-world use cases.

---

## 📚 Table of Contents

1. [Basic Queue Operations](#1-basic-queue-operations)
2. [FIFO Practice](#2-fifo-practice)
3. [Queue Method Pairs](#3-queue-method-pairs)
4. [Empty Queue Practice](#4-empty-queue-practice)
5. [Queue with Strings and Objects](#5-queue-with-strings-and-objects)
6. [Duplicates and Null Values](#6-duplicates-and-null-values)
7. [Iteration and Traversal](#7-iteration-and-traversal)
8. [LinkedList as Queue](#8-linkedlist-as-queue)
9. [ArrayDeque as Queue](#9-arraydeque-as-queue)
10. [PriorityQueue Practice](#10-priorityqueue-practice)
11. [Queue vs Stack](#11-queue-vs-stack)
12. [Intermediate Problems](#12-intermediate-problems)
13. [Code Prediction Questions](#13-code-prediction-questions)
14. [Scenario-Based Questions](#14-scenario-based-questions)
15. [Challenge Problems](#15-challenge-problems)
16. [Practice Checklist](#16-practice-checklist)
17. [Final Goal](#17-final-goal)

---

# 1. Basic Queue Operations

## 1.1 Create a Queue

Create an integer queue using `LinkedList`.

```java
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();

        System.out.println(queue);
    }
}
```

### Practice

- [ ] Create a `Queue<Integer>`
- [ ] Create a `Queue<String>`
- [ ] Print an empty queue
- [ ] Check whether it is empty
- [ ] Print its size

---

## 1.2 Add Elements Using `offer()`

```java
Queue<Integer> queue = new LinkedList<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue);
```

Expected:

```text
[10, 20, 30]
```

### Questions

1. Which element is at the front?
2. Which element is at the rear?
3. What will `peek()` return?
4. What will `poll()` return?

---

## 1.3 Add Elements Using `add()`

```java
Queue<Integer> queue = new LinkedList<>();

queue.add(10);
queue.add(20);
queue.add(30);

System.out.println(queue);
```

### Practice

Compare:

```java
queue.add(40);
```

with:

```java
queue.offer(40);
```

Think about why both methods exist.

---

## 1.4 Remove Using `poll()`

```java
Queue<Integer> queue = new LinkedList<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

int value = queue.poll();

System.out.println(value);
System.out.println(queue);
```

Expected:

```text
10
[20, 30]
```

### Practice

- [ ] Add five elements
- [ ] Remove the first element
- [ ] Remove the next element
- [ ] Print the remaining queue

---

## 1.5 View Using `peek()`

```java
Queue<Integer> queue = new LinkedList<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.peek());
System.out.println(queue);
```

Expected:

```text
10
[10, 20, 30]
```

### Important

`peek()` does **not** remove the element.

---

## 1.6 Check Size

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.size());
```

Expected:

```text
3
```

Now remove one:

```java
queue.poll();

System.out.println(queue.size());
```

Expected:

```text
2
```

---

## 1.7 Check Empty

```java
Queue<Integer> queue = new ArrayDeque<>();

System.out.println(queue.isEmpty());

queue.offer(100);

System.out.println(queue.isEmpty());

queue.poll();

System.out.println(queue.isEmpty());
```

Expected:

```text
true
false
true
```

---

# 2. FIFO Practice

The fundamental queue principle is:

> **FIFO — First In, First Out**

---

## 2.1 Predict the Removal Order

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);
queue.offer(40);
```

Predict:

```java
System.out.println(queue.poll());
System.out.println(queue.poll());
System.out.println(queue.poll());
System.out.println(queue.poll());
```

### Expected

```text
10
20
30
40
```

---

## 2.2 Push-like Confusion

A queue is not a stack.

Given:

```text
10
20
30
```

The next element removed from a queue is:

```text
?
```

The next element removed from a stack would be:

```text
?
```

---

## 2.3 Operation Sequence

Perform these operations mentally:

```text
offer(10)
offer(20)
offer(30)
poll()
offer(40)
poll()
peek()
```

### Questions

1. Which values are removed?
2. What does `peek()` return?
3. What is the final queue?

---

## 2.4 Queue Simulation

Start with an empty queue.

Perform:

```text
offer(A)
offer(B)
poll()
offer(C)
offer(D)
poll()
offer(E)
peek()
```

### Draw the queue after every operation.

Use:

```text
Front → [ ] ← Rear
```

---

## 2.5 Reverse the Thinking

Given the removal order:

```text
A → B → C → D
```

What must have been the insertion order for a normal FIFO queue?

---

# 3. Queue Method Pairs

One of the most important interview topics is understanding these pairs.

---

## 3.1 `add()` vs `offer()`

### Question

What is the difference between:

```java
queue.add(10);
```

and:

```java
queue.offer(10);
```

### Practice

Create a queue and use both methods.

Then research/observe their behavior when insertion cannot be accepted.

Remember:

```text
add()   → may throw an exception
offer() → returns false
```

---

## 3.2 `remove()` vs `poll()`

```java
Queue<Integer> queue = new LinkedList<>();

System.out.println(queue.poll());
```

What happens?

Now try:

```java
System.out.println(queue.remove());
```

### Expected behavior

```text
poll()   → null
remove() → NoSuchElementException
```

---

## 3.3 `element()` vs `peek()`

Try:

```java
Queue<Integer> queue = new LinkedList<>();

System.out.println(queue.peek());
```

Then:

```java
System.out.println(queue.element());
```

### Expected behavior

```text
peek()    → null
element() → NoSuchElementException
```

---

## 3.4 Complete Method Table

Fill in the table before checking your notes.

| Operation | Purpose | Empty/Failure Behavior |
|---|---|---|
| `add()` | Insert | ? |
| `offer()` | Insert | ? |
| `remove()` | Remove head | ? |
| `poll()` | Remove head | ? |
| `element()` | View head | ? |
| `peek()` | View head | ? |

---

# 4. Empty Queue Practice

## 4.1 `poll()` on Empty Queue

```java
Queue<Integer> queue = new ArrayDeque<>();

Integer value = queue.poll();

System.out.println(value);
```

Expected:

```text
null
```

---

## 4.2 `remove()` on Empty Queue

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.remove();
```

### Question

What exception is thrown?

Answer:

```text
NoSuchElementException
```

---

## 4.3 `peek()` on Empty Queue

```java
Queue<Integer> queue = new ArrayDeque<>();

System.out.println(queue.peek());
```

Expected:

```text
null
```

---

## 4.4 `element()` on Empty Queue

```java
Queue<Integer> queue = new ArrayDeque<>();

System.out.println(queue.element());
```

### Question

What happens?

Expected:

```text
NoSuchElementException
```

---

## 4.5 Safe Queue Processing

Write:

```java
while (!queue.isEmpty()) {
    // process element
}
```

Then implement the loop using:

```java
poll()
```

instead of:

```java
remove()
```

---

# 5. Queue with Strings and Objects

## 5.1 Queue of Strings

```java
Queue<String> queue = new ArrayDeque<>();

queue.offer("Alice");
queue.offer("Bob");
queue.offer("Charlie");

System.out.println(queue.peek());
```

Expected:

```text
Alice
```

### Practice

Process every name in FIFO order.

---

## 5.2 Customer Queue

Create:

```text
Customer 1
Customer 2
Customer 3
Customer 4
```

Store them in a queue.

Then process them one by one.

Expected:

```text
Serving Customer 1
Serving Customer 2
Serving Customer 3
Serving Customer 4
```

---

## 5.3 Queue of Custom Objects

Create:

```java
class Student {

    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

Then:

```java
Queue<Student> queue = new ArrayDeque<>();
```

Add three students.

### Practice

- [ ] Add objects
- [ ] View the first object
- [ ] Process students in FIFO order
- [ ] Print the remaining size

---

# 6. Duplicates and Null Values

## 6.1 Duplicate Values

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(10);
queue.offer(30);

System.out.println(queue);
```

Expected:

```text
[10, 20, 10, 30]
```

### Practice

Poll every value and observe the order.

---

## 6.2 LinkedList and Null

```java
Queue<String> queue = new LinkedList<>();

queue.offer(null);

System.out.println(queue);
```

This is permitted by `LinkedList`.

### Question

Why can this create ambiguity when using methods such as `poll()`?

---

## 6.3 ArrayDeque and Null

```java
Queue<String> queue = new ArrayDeque<>();

queue.offer(null);
```

### Question

What happens?

Expected:

```text
NullPointerException
```

---

## 6.4 Compare Null Handling

| Queue Implementation | Allows `null`? |
|---|---|
| `LinkedList` | Yes |
| `ArrayDeque` | No |
| `PriorityQueue` | No |

### Interview Practice

Explain why `ArrayDeque` rejects `null`.

---

# 7. Iteration and Traversal

## 7.1 For-Each Loop

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

for (Integer value : queue) {
    System.out.println(value);
}
```

Expected:

```text
10
20
30
```

---

## 7.2 Does Iteration Remove Elements?

Check:

```java
for (Integer value : queue) {
    System.out.println(value);
}

System.out.println(queue.size());
```

### Question

Does the size change?

Expected:

```text
No
```

---

## 7.3 Destructive Traversal

```java
while (!queue.isEmpty()) {
    System.out.println(queue.poll());
}
```

This removes elements as they are processed.

---

## 7.4 Iterator Practice

Use:

```java
Iterator<Integer> iterator = queue.iterator();
```

Then:

```java
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

### Compare

| Traversal | Removes elements? |
|---|---|
| For-each | No |
| Iterator | No |
| `poll()` loop | Yes |

---

# 8. LinkedList as Queue

## 8.1 Basic Queue

```java
Queue<Integer> queue = new LinkedList<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.poll());
```

Expected:

```text
10
```

---

## 8.2 LinkedList Queue Practice

Write a program that:

1. Creates a queue of five integers.
2. Adds all five using `offer()`.
3. Prints the first element.
4. Removes two elements.
5. Prints the remaining queue.
6. Prints the size.

---

## 8.3 Why Can LinkedList Be Used as Queue?

Because `LinkedList` implements:

```text
Deque
```

and therefore supports queue operations.

Conceptually:

```text
Collection
    ↓
   Queue
    ↓
   Deque
    ↓
LinkedList
```

---

# 9. ArrayDeque as Queue

## 9.1 Basic Queue

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.poll());
```

Expected:

```text
10
```

---

## 9.2 Queue Using `Deque`

```java
Deque<Integer> queue = new ArrayDeque<>();

queue.offerLast(10);
queue.offerLast(20);
queue.offerLast(30);

System.out.println(queue.pollFirst());
```

Expected:

```text
10
```

This explicitly shows the two ends:

```text
Front → [10] [20] [30] ← Rear
          ↑          ↑
      pollFirst   offerLast
```

---

## 9.3 ArrayDeque Practice

Create a queue using:

```java
Queue<String>
```

with:

```java
ArrayDeque<String>
```

Add:

```text
Java
Spring
Hibernate
Docker
Git
```

Then remove all elements in FIFO order.

---

## 9.4 Compare Implementations

Compare:

```java
Queue<Integer> q1 = new LinkedList<>();
```

and:

```java
Queue<Integer> q2 = new ArrayDeque<>();
```

### Questions

1. Which one uses nodes?
2. Which one is array-backed?
3. Which one rejects `null`?
4. Which one is generally preferred for typical queue usage?

---

# 10. PriorityQueue Practice

Remember:

> `PriorityQueue` is a `Queue`, but it does not use ordinary FIFO ordering.

---

## 10.1 Basic PriorityQueue

```java
Queue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);

System.out.println(queue.peek());
```

Expected:

```text
10
```

---

## 10.2 Poll from PriorityQueue

```java
while (!queue.isEmpty()) {
    System.out.println(queue.poll());
}
```

For the values:

```text
30
10
20
```

the output will be:

```text
10
20
30
```

---

## 10.3 FIFO vs Priority

Insert:

```text
30
10
20
```

### Normal Queue

Expected removal:

```text
30
10
20
```

### PriorityQueue

Expected removal for natural ordering:

```text
10
20
30
```

---

## 10.4 Reverse Priority

Create a `PriorityQueue<Integer>` using a comparator so that larger values have higher priority.

Expected removal:

```text
30
20
10
```

### Practice

Write the comparator yourself.

---

# 11. Queue vs Stack

## 11.1 Basic Comparison

| Feature | Queue | Stack |
|---|---|---|
| Principle | FIFO | LIFO |
| First removal | Oldest element | Newest element |
| Insert | Rear | Top |
| Remove | Front | Top |
| Insert method | `offer()` | `push()` |
| Remove method | `poll()` | `pop()` |
| View method | `peek()` | `peek()` |

---

## 11.2 Same Input, Different Output

Input:

```text
10
20
30
```

### Queue

```text
10
20
30
```

### Stack

```text
30
20
10
```

### Practice

Explain why the outputs are different.

---

# 12. Intermediate Problems

## 12.1 First Element

Write:

```java
static Integer getFirst(Queue<Integer> queue)
```

that returns the first element without removing it.

### Requirement

Use:

```java
peek()
```

---

## 12.2 Process All Elements

Write:

```java
static void processQueue(Queue<Integer> queue)
```

that prints and removes every element.

### Expected approach

```java
while (!queue.isEmpty()) {
    System.out.println(queue.poll());
}
```

---

## 12.3 Reverse a Queue

Given:

```text
10 → 20 → 30 → 40
```

produce:

```text
40 → 30 → 20 → 10
```

### Hint

Use a stack.

---

## 12.4 Generate Binary Numbers

Generate the first `N` binary numbers using a queue.

For example:

```text
1
10
11
100
101
110
```

### Challenge

Use queue operations to generate the sequence.

---

## 12.5 Generate Numbers in Order

Given:

```text
1
2
3
4
5
```

Process them in FIFO order and print:

```text
Processing 1
Processing 2
Processing 3
Processing 4
Processing 5
```

---

## 12.6 First Non-Repeating Character

Given:

```text
aabbcd
```

Find the first non-repeating character.

Expected:

```text
c
```

### Hint

Use:

- a queue
- a frequency map

This is a common interview problem.

---

## 12.7 First Non-Repeating Character in a Stream

Input stream:

```text
a
a
b
c
```

After each character, determine the first non-repeating character.

Expected progression:

```text
a → a
a → #
b → b
c → b
```

Use:

```text
Queue + HashMap
```

---

## 12.8 Implement Queue Using Two Stacks

Design:

```java
class MyQueue {

    void offer(int value) {
    }

    int poll() {
    }

    int peek() {
    }
}
```

### Requirement

Use two stacks internally.

This is a classic interview problem.

---

## 12.9 Implement Stack Using Two Queues

Design:

```java
class MyStack {

    void push(int value) {
    }

    int pop() {
    }

    int peek() {
    }
}
```

### Requirement

Use two queues.

---

# 13. Code Prediction Questions

Try solving these without running the code.

---

## Question 1

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.poll());
```

Output?

---

## Question 2

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.peek());
System.out.println(queue.size());
```

Output?

---

## Question 3

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);

queue.poll();

System.out.println(queue.peek());
```

Output?

---

## Question 4

```java
Queue<Integer> queue = new ArrayDeque<>();

System.out.println(queue.poll());
```

What happens?

---

## Question 5

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.remove();
```

What happens?

---

## Question 6

```java
Queue<Integer> queue = new ArrayDeque<>();

System.out.println(queue.peek());
```

What happens?

---

## Question 7

```java
Queue<Integer> queue = new ArrayDeque<>();

System.out.println(queue.element());
```

What happens?

---

## Question 8

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);

System.out.println(queue.poll());
```

What is the output?

---

## Question 9

```java
Queue<Integer> queue = new PriorityQueue<>();

queue.offer(30);
queue.offer(10);
queue.offer(20);

System.out.println(queue.poll());
```

What is the output?

---

## Question 10

```java
Queue<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

for (Integer value : queue) {
    System.out.println(value);
}

System.out.println(queue.size());
```

### Questions

1. What is printed?
2. Does iteration remove elements?
3. What is the final size?

---

## Question 11

```java
Queue<String> queue = new LinkedList<>();

queue.offer(null);

System.out.println(queue.peek());
```

What happens?

---

## Question 12

```java
Queue<String> queue = new ArrayDeque<>();

queue.offer(null);
```

What happens?

---

# 14. Scenario-Based Questions

## Scenario 1 — Printer Queue

A printer receives:

```text
Document A
Document B
Document C
```

The printer should normally process documents in arrival order.

### Question

Which data structure is appropriate?

---

## Scenario 2 — BFS

You need to implement Breadth-First Search.

### Question

Which data structure is commonly used?

### Expected Answer

Queue.

---

## Scenario 3 — Browser Back Button

You need to implement a browser's Back operation.

### Question

Would a normal FIFO queue be the natural choice?

Why or why not?

### Expected Concept

A stack is more natural because Back navigation is generally LIFO.

---

## Scenario 4 — Task Processing

A server receives tasks:

```text
Task A
Task B
Task C
Task D
```

The system wants to process them in arrival order.

### Question

Which data structure would you choose?

---

## Scenario 5 — Highest Priority First

A hospital emergency system wants to process patients according to priority rather than arrival order.

### Question

Would a normal FIFO queue be enough?

What Java implementation might be useful?

### Expected Concept

`PriorityQueue` or another priority-based design.

---

## Scenario 6 — Modern Java Queue

You need a normal FIFO queue in new Java code.

Which is generally preferable?

```java
Queue<Integer> queue = new LinkedList<>();
```

or:

```java
Queue<Integer> queue = new ArrayDeque<>();
```

Explain your choice.

---

# 15. Challenge Problems

## Challenge 1 — Circular Queue

Implement a circular queue using an array.

Support:

```java
enqueue()
dequeue()
peek()
isEmpty()
isFull()
```

---

## Challenge 2 — Queue Using Array

Implement:

```java
class MyQueue {

    private int[] data;

    void offer(int value) {
    }

    int poll() {
    }

    int peek() {
    }
}
```

Do not use Java's built-in Queue.

---

## Challenge 3 — Queue Using Linked Nodes

Create a custom queue using nodes.

Maintain:

```text
front
rear
```

Operations:

```text
offer()
poll()
peek()
```

---

## Challenge 4 — Maximum of Every Window

Given:

```text
[1, 3, -1, -3, 5, 3, 6, 7]
```

and:

```text
k = 3
```

find the maximum value in every sliding window.

Expected:

```text
[3, 3, 5, 5, 6, 7]
```

### Hint

Use a deque.

This is an important problem for understanding why `Deque` is powerful.

---

## Challenge 5 — Rotten Oranges

Given a grid where:

```text
0 → empty
1 → fresh orange
2 → rotten orange
```

Calculate the minimum time required for all fresh oranges to become rotten.

### Hint

Use BFS.

BFS requires a queue.

---

## Challenge 6 — Level Order Traversal

Given a binary tree, print its nodes level by level.

Example:

```text
        1
       / \
      2   3
     / \
    4   5
```

Expected:

```text
1
2 3
4 5
```

### Hint

Use a queue.

---

# 16. Practice Checklist

## Fundamentals

- [ ] Understand Queue
- [ ] Understand FIFO
- [ ] Know front and rear
- [ ] Know `Queue` is an interface
- [ ] Know common implementations

## Core Methods

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

- [ ] `LinkedList`
- [ ] `ArrayDeque`
- [ ] `PriorityQueue`
- [ ] Understand `Deque`

## Edge Cases

- [ ] Empty queue with `poll()`
- [ ] Empty queue with `remove()`
- [ ] Empty queue with `peek()`
- [ ] Empty queue with `element()`
- [ ] Duplicate values
- [ ] Null handling

## Traversal

- [ ] For-each
- [ ] Iterator
- [ ] Destructive `poll()` loop

## Problem Solving

- [ ] Reverse a queue
- [ ] Queue using two stacks
- [ ] Stack using two queues
- [ ] First non-repeating character
- [ ] Generate binary numbers
- [ ] Circular queue
- [ ] Sliding window maximum
- [ ] BFS
- [ ] Level-order traversal

---

# 17. Final Goal

After completing this practice file, you should confidently be able to answer:

> **What is a Queue?**

> **What does FIFO mean?**

> **What is the difference between `add()` and `offer()`?**

> **What is the difference between `remove()` and `poll()`?**

> **What is the difference between `element()` and `peek()`?**

> **What happens when these methods are used on an empty queue?**

> **Why is `Queue` an interface?**

> **How can `LinkedList` be used as a Queue?**

> **Why is `ArrayDeque` generally preferred for typical queue usage?**

> **How is `PriorityQueue` different from a normal FIFO queue?**

> **Why doesn't `ArrayDeque` allow `null`?**

> **How is a Queue different from a Stack?**

> **How is a Queue used in BFS?**

---

# 📌 Quick Revision

```text
Queue
  ↓
Interface
  ↓
Normally FIFO
  ↓
First In → First Out
  ↓
Main Operations
  ├── offer()   → insert
  ├── poll()    → remove
  └── peek()    → view
  ↓
Common Implementations
  ├── LinkedList
  ├── ArrayDeque
  └── PriorityQueue
```

### Method Memory Trick

```text
INSERT
add()   → exception on failure
offer() → false on failure

REMOVE
remove() → exception if empty
poll()   → null if empty

VIEW
element() → exception if empty
peek()    → null if empty
```

### Queue Mental Model

```text
                 FIFO

       First In → First Out

Front                              Rear
  ↓                                  ↓
[A] → [B] → [C] → [D]
 ↑                    ↑
poll()              offer()
```

---

# 🚀 Progress

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
│   └── INTERVIEW.md   [ ]
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

**Next:** `08-Queue/INTERVIEW.md`
