# Deque — Practice

> Practice file for understanding and using the `Deque` interface in Java.

---

## Table of Contents

1. [Practice Goals](#1-practice-goals)
2. [Basic Deque Operations](#2-basic-deque-operations)
3. [addFirst() and addLast()](#3-addfirst-and-addlast)
4. [offerFirst() and offerLast()](#4-offerfirst-and-offerlast)
5. [removeFirst() and removeLast()](#5-removefirst-and-removelast)
6. [pollFirst() and pollLast()](#6-pollfirst-and-polllast)
7. [getFirst() and getLast()](#7-getfirst-and-getlast)
8. [peekFirst() and peekLast()](#8-peekfirst-and-peeklast)
9. [Practice Deque as a Queue](#9-practice-deque-as-a-queue)
10. [Practice Deque as a Stack](#10-practice-deque-as-a-stack)
11. [Mixed Front and Back Operations](#11-mixed-front-and-back-operations)
12. [Duplicates and Null](#12-duplicates-and-null)
13. [Iteration](#13-iteration)
14. [Output Prediction](#14-output-prediction)
15. [Basic Coding Problems](#15-basic-coding-problems)
16. [Intermediate Problems](#16-intermediate-problems)
17. [Challenge Problems](#17-challenge-problems)
18. [Scenario-Based Practice](#18-scenario-based-practice)
19. [Practice Checklist](#19-practice-checklist)
20. [Final Goal](#20-final-goal)
21. [Progress](#21-progress)

---

# 1. Practice Goals

By the end of this practice, you should be able to:

- [ ] Create a `Deque`
- [ ] Use `ArrayDeque`
- [ ] Add elements at the front
- [ ] Add elements at the back
- [ ] Remove elements from the front
- [ ] Remove elements from the back
- [ ] Inspect both ends
- [ ] Understand `add` vs `offer`
- [ ] Understand `remove` vs `poll`
- [ ] Understand `get` vs `peek`
- [ ] Use `Deque` as a Queue
- [ ] Use `Deque` as a Stack
- [ ] Traverse a Deque
- [ ] Traverse in reverse
- [ ] Understand why `null` is not allowed by `ArrayDeque`
- [ ] Solve palindrome problems
- [ ] Solve sliding-window problems
- [ ] Recognize when `Deque` is better than `PriorityQueue`

---

# 2. Basic Deque Operations

## 2.1 Create a Deque

Start with:

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {

        Deque<Integer> deque = new ArrayDeque<>();

        System.out.println(deque);
    }
}
```

Expected:

```text
[]
```

---

## 2.2 Add Elements

Try:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addFirst(20);
deque.addLast(30);
deque.addFirst(10);
deque.addLast(40);

System.out.println(deque);
```

Expected:

```text
[10, 20, 30, 40]
```

### Questions

1. Which element is at the front?
2. Which element is at the back?
3. Which method added `10`?
4. Which method added `40`?

---

# 3. addFirst() and addLast()

## Exercise 1

Start with:

```text
20 30
```

Perform:

```java
deque.addFirst(10);
deque.addLast(40);
```

Expected:

```text
10 20 30 40
```

---

## Exercise 2

Perform:

```java
deque.addFirst(5);
deque.addFirst(1);
deque.addLast(50);
deque.addLast(60);
```

If the Deque initially contains:

```text
10 20 30 40
```

what will it contain?

### Answer

```text
1 5 10 20 30 40 50 60
```

---

## Exercise 3

Create a Deque and insert:

```text
10, 20, 30, 40, 50
```

using only `addLast()`.

Then remove everything using `removeFirst()`.

### Expected removal order

```text
10
20
30
40
50
```

This demonstrates FIFO behavior.

---

# 4. offerFirst() and offerLast()

These methods also insert elements at either end.

```java
deque.offerFirst(10);
deque.offerLast(20);
```

### Exercise

Create:

```text
[]
```

Perform:

```java
deque.offerLast(20);
deque.offerFirst(10);
deque.offerLast(30);
deque.offerFirst(5);
```

Predict:

```java
System.out.println(deque);
```

### Answer

```text
[5, 10, 20, 30]
```

---

# 5. removeFirst() and removeLast()

## Exercise 1

Given:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);
deque.addLast(40);
```

Execute:

```java
System.out.println(deque.removeFirst());
```

### Answer

```text
10
```

Remaining:

```text
20 30 40
```

---

## Exercise 2

Reset the Deque:

```text
10 20 30 40
```

Execute:

```java
System.out.println(deque.removeLast());
```

### Answer

```text
40
```

Remaining:

```text
10 20 30
```

---

## Exercise 3

What happens if:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.removeFirst();
```

### Answer

```text
NoSuchElementException
```

---

# 6. pollFirst() and pollLast()

These methods remove from either end but return `null` when the Deque is empty.

## Exercise 1

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);

System.out.println(deque.pollFirst());
System.out.println(deque.pollLast());
```

### Expected

```text
10
20
```

---

## Exercise 2

```java
Deque<Integer> deque = new ArrayDeque<>();

System.out.println(deque.pollFirst());
System.out.println(deque.pollLast());
```

### Expected

```text
null
null
```

---

## Exercise 3

Compare:

```java
deque.removeFirst();
```

with:

```java
deque.pollFirst();
```

### Remember

```text
removeFirst() → exception if empty
pollFirst()   → null if empty
```

---

# 7. getFirst() and getLast()

These inspect elements without removing them.

## Exercise

Given:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);
```

Predict:

```java
System.out.println(deque.getFirst());
System.out.println(deque.getLast());
System.out.println(deque);
```

### Answer

```text
10
30
[10, 20, 30]
```

The Deque is unchanged.

---

## Empty Deque

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.getFirst();
```

Result:

```text
NoSuchElementException
```

---

# 8. peekFirst() and peekLast()

These also inspect without removing.

## Exercise

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);

System.out.println(deque.peekFirst());
System.out.println(deque.peekLast());
```

### Expected

```text
10
30
```

---

## Empty Deque

```java
Deque<Integer> deque = new ArrayDeque<>();

System.out.println(deque.peekFirst());
System.out.println(deque.peekLast());
```

### Expected

```text
null
null
```

---

# 9. Practice Deque as a Queue

A Deque can behave like a FIFO Queue.

Use:

```java
offerLast()
pollFirst()
```

---

## Exercise 1

Insert:

```text
A
B
C
D
```

using:

```java
offerLast()
```

Then remove all using:

```java
pollFirst()
```

Expected:

```text
A
B
C
D
```

---

## Exercise 2

Implement:

```java
static void processQueue(Deque<String> queue)
```

The method should process elements in FIFO order.

### Example

```text
Input:
Task A
Task B
Task C

Output:
Task A
Task B
Task C
```

---

## Queue Pattern

Remember:

```java
queue.offerLast(value);
queue.pollFirst();
```

means:

```text
Back In → Front Out
```

which produces FIFO behavior.

---

# 10. Practice Deque as a Stack

A Deque can also behave as a LIFO stack.

Use:

```java
push()
pop()
peek()
```

---

## Exercise 1

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.pop());
```

### Expected

```text
30
```

---

## Exercise 2

Push:

```text
10
20
30
40
50
```

Then pop everything.

### Expected

```text
50
40
30
20
10
```

---

## Exercise 3

What do these methods correspond to?

| Stack Method | Deque Equivalent |
|---|---|
| `push()` | `addFirst()` |
| `pop()` | `removeFirst()` |
| `peek()` | `peekFirst()` |

---

## Stack Pattern

```java
stack.push(value);
stack.pop();
```

This gives:

```text
Last In → First Out
```

---

# 11. Mixed Front and Back Operations

This is where Deque becomes especially useful.

Start:

```text
[]
```

Perform:

```java
deque.addLast(20);
deque.addLast(30);
deque.addFirst(10);
deque.addFirst(5);
deque.addLast(40);
```

Final Deque:

```text
5 10 20 30 40
```

Now execute:

```java
System.out.println(deque.removeFirst());
System.out.println(deque.removeLast());
```

Output:

```text
5
40
```

Remaining:

```text
10 20 30
```

---

## Exercise

Predict the final Deque:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addFirst(20);
deque.addLast(30);
deque.addFirst(40);
deque.removeLast();
deque.addLast(50);
deque.removeFirst();
```

### Answer

```text
20 10 50
```

---

# 12. Duplicates and Null

## 12.1 Duplicates

Try:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(10);
deque.addLast(20);
deque.addLast(20);
```

The Deque contains:

```text
10 10 20 20
```

### Conclusion

Duplicates are allowed.

---

## 12.2 Null

Try:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(null);
```

### Result

```text
NullPointerException
```

`ArrayDeque` does not allow `null`.

---

## Exercise

Explain why allowing `null` could be problematic when methods such as:

```java
peekFirst()
pollFirst()
```

use `null` to represent an empty Deque.

---

# 13. Iteration

## 13.1 Normal Iteration

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);

for (Integer value : deque) {
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

## 13.2 Descending Iterator

Use:

```java
Iterator<Integer> iterator =
        deque.descendingIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

Expected:

```text
30
20
10
```

---

## 13.3 Practice

Create:

```text
10 20 30 40 50
```

Print:

1. Normal order
2. Reverse order

Expected:

```text
Normal:
10 20 30 40 50

Reverse:
50 40 30 20 10
```

---

# 14. Output Prediction

## Question 1

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addFirst(10);
deque.addLast(20);
deque.addFirst(5);

System.out.println(deque);
```

### Answer

```text
[5, 10, 20]
```

---

## Question 2

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);

System.out.println(deque.pollFirst());
```

### Answer

```text
10
```

---

## Question 3

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);

System.out.println(deque.pollLast());
```

### Answer

```text
30
```

---

## Question 4

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);

System.out.println(deque.peekFirst());
System.out.println(deque.peekLast());
```

### Answer

```text
10
30
```

---

## Question 5

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);
deque.addLast(30);

System.out.println(deque.removeFirst());
System.out.println(deque.removeLast());
System.out.println(deque);
```

### Answer

```text
10
30
[20]
```

---

## Question 6

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

## Question 7

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(20);

System.out.println(deque.pollFirst());
System.out.println(deque.pollFirst());
System.out.println(deque.pollFirst());
```

### Answer

```text
10
20
null
```

---

# 15. Basic Coding Problems

## 15.1 Reverse a String Using Deque

### Problem

Write:

```java
static String reverse(String str)
```

Input:

```text
"hello"
```

Output:

```text
"olleh"
```

### Hint

1. Add every character to a Deque.
2. Remove from the appropriate end.

---

## 15.2 Check Palindrome

### Problem

Write:

```java
static boolean isPalindrome(String str)
```

Example:

```text
Input:
madam

Output:
true
```

Example:

```text
Input:
hello

Output:
false
```

### Hint

Use:

```java
Deque<Character>
```

Compare:

```text
first ↔ last
```

Continue until the Deque is empty or only one character remains.

---

## 15.3 Implement Queue Using Deque

Create:

```java
class MyQueue {
    Deque<Integer> deque = new ArrayDeque<>();

    void enqueue(int value) {
        // implement
    }

    int dequeue() {
        // implement
    }
}
```

### Requirement

Implement FIFO behavior.

### Hint

Use:

```java
offerLast()
pollFirst()
```

---

## 15.4 Implement Stack Using Deque

Create:

```java
class MyStack {
    Deque<Integer> deque = new ArrayDeque<>();

    void push(int value) {
        // implement
    }

    int pop() {
        // implement
    }
}
```

### Requirement

Implement LIFO behavior.

### Hint

Use:

```java
push()
pop()
```

---

# 16. Intermediate Problems

## 16.1 First Negative Number in Every Window

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
```

### Hint

Maintain a Deque of indices of negative numbers.

---

## 16.2 Sliding Window Maximum

Given:

```text
arr = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3
```

Output:

```text
3 3 5 5 6 7
```

### Goal

Solve using a monotonic Deque in:

```text
O(n)
```

### Key Idea

Store indices rather than values.

Maintain the Deque so that values are in decreasing order.

The front always represents the maximum for the current window.

---

## 16.3 Sliding Window Minimum

Given:

```text
arr = [1, 3, -1, -3, 5, 3, 6, 7]
k = 3
```

Find the minimum in each window.

Expected:

```text
-1 -3 -3 -3 3 3
```

### Hint

Maintain a monotonic increasing Deque.

---

## 16.4 Maximum Difference

Given an array, use a Deque-based approach to explore how sliding-window structures can help calculate local maximum/minimum differences.

Example:

```text
arr = [4, 2, 7, 1, 5]
```

Practice identifying the maximum and minimum values within windows.

---

# 17. Challenge Problems

## Challenge 1 — Sliding Window Maximum

Implement:

```java
static int[] maxSlidingWindow(int[] nums, int k)
```

Example:

```text
Input:
[1,3,-1,-3,5,3,6,7]
k = 3

Output:
[3,3,5,5,6,7]
```

### Target Complexity

```text
O(n)
```

---

## Challenge 2 — Palindrome Ignoring Case

Implement:

```java
static boolean isPalindrome(String str)
```

The method should ignore:

- uppercase/lowercase differences
- spaces
- punctuation

Example:

```text
"A man, a plan, a canal: Panama"
```

Expected:

```text
true
```

---

## Challenge 3 — First Negative in Every Window

Implement:

```java
static int[] firstNegative(int[] nums, int k)
```

Example:

```text
Input:
[12,-1,-7,8,-15,30,16,28]
k = 3

Output:
[-1,-1,-7,-15,-15]
```

---

## Challenge 4 — Custom Deque-Based Cache

Design a simple cache with:

```text
capacity
```

and operations:

```java
get(key)
put(key, value)
```

Use a Deque to explore how recently used elements could be moved toward one end.

### Extension

Think about why a production-quality LRU cache generally combines:

```text
HashMap + Doubly Linked List
```

rather than relying on a plain `ArrayDeque` alone.

---

## Challenge 5 — Sliding Window Minimum and Maximum

For every window of size `k`, return both:

```text
minimum
maximum
```

Example:

```text
Input:
[1,3,-1,-3,5,3,6,7]
k = 3
```

Expected:

```text
Window     Min    Max
[1,3,-1]   -1      3
[3,-1,-3]  -3      3
[-1,-3,5]  -3      5
[-3,5,3]   -3      5
[5,3,6]     3      6
[3,6,7]     3      7
```

Try solving it with two Deques.

---

# 18. Scenario-Based Practice

## Scenario 1 — Normal Queue

Tasks must be processed in arrival order.

### Best approach

```java
Deque<Task> queue = new ArrayDeque<>();
```

Use:

```text
offerLast()
pollFirst()
```

---

## Scenario 2 — Stack

You need:

```text
Last In → First Out
```

### Best approach

```java
Deque<Integer> stack = new ArrayDeque<>();
```

Use:

```text
push()
pop()
```

---

## Scenario 3 — Need Both Ends

You need to add urgent items to the front and normal items to the back.

### Best choice

```text
Deque
```

---

## Scenario 4 — Need Highest Priority

You need the smallest-priority value regardless of insertion position.

### Best choice

```text
PriorityQueue
```

Not a Deque.

---

## Scenario 5 — Sliding Window Maximum

You need the maximum value of every window in `O(n)`.

### Best choice

```text
Monotonic Deque
```

---

## Scenario 6 — Sorted Unique Data

You need:

- sorted elements
- no duplicates
- ordered traversal

### Best choice

```text
TreeSet
```

Not a Deque.

---

# 19. Practice Checklist

## Basic Operations

- [ ] Create `Deque`
- [ ] Create `ArrayDeque`
- [ ] Use `addFirst()`
- [ ] Use `addLast()`
- [ ] Use `offerFirst()`
- [ ] Use `offerLast()`
- [ ] Use `removeFirst()`
- [ ] Use `removeLast()`
- [ ] Use `pollFirst()`
- [ ] Use `pollLast()`
- [ ] Use `getFirst()`
- [ ] Use `getLast()`
- [ ] Use `peekFirst()`
- [ ] Use `peekLast()`

## Queue Behavior

- [ ] Understand FIFO
- [ ] Use `offerLast()` + `pollFirst()`
- [ ] Implement a Queue using Deque

## Stack Behavior

- [ ] Understand LIFO
- [ ] Use `push()` + `pop()`
- [ ] Implement a Stack using Deque

## Important Rules

- [ ] Duplicates are allowed
- [ ] `ArrayDeque` does not allow `null`
- [ ] Understand empty behavior
- [ ] Understand front vs back
- [ ] Understand normal iteration
- [ ] Understand `descendingIterator()`

## Problem Solving

- [ ] Reverse a string
- [ ] Check palindrome
- [ ] First negative in window
- [ ] Sliding window maximum
- [ ] Sliding window minimum
- [ ] Understand monotonic Deque
- [ ] Understand LRU-cache concept

---

# 20. Final Goal

Before moving to `INTERVIEW.md`, make sure you can explain this:

> `Deque` is a double-ended queue that allows insertion, removal, and inspection from both the front and the back. It can be used as a FIFO Queue or a LIFO Stack. `ArrayDeque` is a common implementation and provides efficient operations at both ends.

You should also be able to write these patterns from memory:

### Queue

```java
Deque<Integer> queue = new ArrayDeque<>();

queue.offerLast(10);
queue.pollFirst();
```

### Stack

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.pop();
```

### Double-Ended

```java
deque.addFirst(10);
deque.addLast(20);

deque.removeFirst();
deque.removeLast();
```

---

# 21. Progress

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
│   └── INTERVIEW.md   [ ]
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
```
