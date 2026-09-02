# Stack — Practice

> Practice file for understanding and applying Java's `Stack` class.

---

## 📚 Table of Contents

1. [Basic Stack Operations](#1-basic-stack-operations)
2. [LIFO Practice](#2-lifo-practice)
3. [`search()` Practice](#3-search-practice)
4. [Stack with Strings and Objects](#4-stack-with-strings-and-objects)
5. [Null Values and Duplicates](#5-null-values-and-duplicates)
6. [Iteration and Traversal](#6-iteration-and-traversal)
7. [Inherited Vector Methods](#7-inherited-vector-methods)
8. [Stack vs ArrayDeque](#8-stack-vs-arraydeque)
9. [Intermediate Problems](#9-intermediate-problems)
10. [Code Prediction Questions](#10-code-prediction-questions)
11. [Scenario-Based Questions](#11-scenario-based-questions)
12. [Challenge Problems](#12-challenge-problems)
13. [Practice Checklist](#13-practice-checklist)
14. [Final Goal](#14-final-goal)

---

# 1. Basic Stack Operations

## 1.1 Create a Stack

Create a stack of integers.

```java
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        System.out.println(stack);
    }
}
```

### Practice

- [ ] Create an empty `Stack<Integer>`
- [ ] Create a `Stack<String>`
- [ ] Print an empty stack
- [ ] Check whether the stack is empty

---

## 1.2 Push Elements

Add elements using `push()`.

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack);
```

### Questions

1. What is the top element?
2. What is the size?
3. What is the order of elements inside the stack?

Expected:

```text
[10, 20, 30]
```

Top:

```text
30
```

---

## 1.3 Pop Elements

Remove the top element using `pop()`.

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

int value = stack.pop();

System.out.println(value);
System.out.println(stack);
```

### Questions

- Which element is removed?
- What does the stack contain afterward?
- What happens if you call `pop()` on an empty stack?

---

## 1.4 Peek

Check the top element without removing it.

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.peek());
System.out.println(stack);
```

### Expected

```text
30
[10, 20, 30]
```

### Practice

- [ ] Use `peek()`
- [ ] Verify that the size does not change
- [ ] Verify that the top element does not get removed

---

## 1.5 Check Empty

Use both `empty()` and `isEmpty()`.

```java
Stack<Integer> stack = new Stack<>();

System.out.println(stack.empty());
System.out.println(stack.isEmpty());

stack.push(100);

System.out.println(stack.empty());
System.out.println(stack.isEmpty());
```

### Practice

Explain the difference between:

```java
stack.empty();
```

and

```java
stack.isEmpty();
```

> `empty()` is the legacy `Stack` method, while `isEmpty()` comes from the collection hierarchy.

---

## 1.6 Size

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.size());
```

Expected:

```text
3
```

### Practice

Try:

```java
stack.pop();
stack.pop();

System.out.println(stack.size());
```

---

# 2. LIFO Practice

The most important rule of a stack:

> **LIFO — Last In, First Out**

---

## 2.1 Push and Pop Sequence

Given:

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);
stack.push(40);
```

Predict the output:

```java
System.out.println(stack.pop());
System.out.println(stack.pop());
System.out.println(stack.pop());
```

### Answer

```text
40
30
20
```

---

## 2.2 Predict the Final Stack

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

stack.pop();

stack.push(40);

stack.pop();

System.out.println(stack);
```

### Task

Predict the output before running the program.

---

## 2.3 Reverse Order

Given:

```text
10 20 30 40 50
```

Use a stack to produce:

```text
50 40 30 20 10
```

### Hint

Push every element and then repeatedly pop.

---

## 2.4 Pop Order

Given:

```java
Stack<String> stack = new Stack<>();

stack.push("A");
stack.push("B");
stack.push("C");
stack.push("D");
```

What is the pop order?

```text
?
?
?
?
```

---

## 2.5 LIFO Challenge

Perform these operations manually:

```java
push(10)
push(20)
push(30)
pop()
push(40)
push(50)
pop()
peek()
```

### Questions

1. Which values were removed?
2. What does `peek()` return?
3. What is the final stack?

---

# 3. `search()` Practice

`Stack` provides:

```java
search(Object item)
```

It searches from the top of the stack.

Important:

> `search()` returns a **1-based position from the top**.

---

## 3.1 Basic Search

```java
Stack<String> stack = new Stack<>();

stack.push("A");
stack.push("B");
stack.push("C");
stack.push("D");

System.out.println(stack.search("D"));
System.out.println(stack.search("B"));
```

### Expected

```text
1
3
```

Because:

```text
Top
 D → 1
 C → 2
 B → 3
 A → 4
```

---

## 3.2 Search for Missing Element

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.search(100));
```

### Expected

```text
-1
```

---

## 3.3 Search with Duplicates

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(10);
stack.push(30);

System.out.println(stack.search(10));
```

### Task

Predict the output.

> Remember: search starts from the top, so the nearest matching element is found first.

---

## 3.4 Search Practice

For:

```text
Bottom
10
20
30
40
50
Top
```

Find the `search()` result for:

| Element | Position from Top |
|---|---:|
| 50 | ? |
| 40 | ? |
| 30 | ? |
| 20 | ? |
| 10 | ? |
| 100 | ? |

---

# 4. Stack with Strings and Objects

## 4.1 Stack of Strings

```java
Stack<String> stack = new Stack<>();

stack.push("Java");
stack.push("Spring");
stack.push("Hibernate");

System.out.println(stack.peek());
```

### Task

Write a program that:

1. Adds five programming technologies.
2. Prints the top element.
3. Removes all elements.
4. Prints them in reverse insertion order.

---

## 4.2 Stack of Characters

Create:

```java
Stack<Character>
```

Push:

```text
J
A
V
A
```

Then pop every character.

### Task

What output will you get?

---

## 4.3 Stack of Custom Objects

Create a `Student` class:

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

Create:

```java
Stack<Student> stack = new Stack<>();
```

Add three students and remove them one by one.

### Practice

- [ ] Create custom object
- [ ] Push objects
- [ ] Peek object
- [ ] Pop objects
- [ ] Print object information

---

# 5. Null Values and Duplicates

## 5.1 Null Value

`Stack` allows `null`.

Practice:

```java
Stack<String> stack = new Stack<>();

stack.push("Java");
stack.push(null);
stack.push("Spring");

System.out.println(stack);
```

### Questions

1. Is `null` allowed?
2. What does `peek()` return?
3. What happens when `null` is popped?

---

## 5.2 Duplicate Values

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(10);
stack.push(30);
stack.push(10);

System.out.println(stack);
```

### Practice

- [ ] Verify duplicates are allowed
- [ ] Use `search(10)`
- [ ] Pop all values
- [ ] Observe the order

---

# 6. Iteration and Traversal

Because `Stack` extends `Vector`, it supports normal collection/list traversal.

---

## 6.1 For-Each Loop

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

for (Integer value : stack) {
    System.out.println(value);
}
```

### Question

Does this traversal behave exactly like repeatedly calling `pop()`?

> No. Normal iteration traverses the underlying collection; it does not remove elements.

---

## 6.2 Iterator

```java
Iterator<Integer> iterator = stack.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

### Practice

- [ ] Iterate using `for-each`
- [ ] Iterate using `Iterator`
- [ ] Check stack size after iteration

---

## 6.3 Destructive Traversal

```java
while (!stack.empty()) {
    System.out.println(stack.pop());
}
```

This prints elements from:

```text
Top → Bottom
```

### Important

After this loop:

```java
stack
```

is empty.

---

## 6.4 Compare Traversals

Try both:

```java
for (Integer value : stack) {
    System.out.println(value);
}
```

and:

```java
while (!stack.empty()) {
    System.out.println(stack.pop());
}
```

### Observe

- Which one removes elements?
- Which one preserves the stack?
- Which one follows LIFO removal behavior?

---

# 7. Inherited Vector Methods

`Stack` extends `Vector`, so it inherits many list operations.

This is one reason the class is considered a legacy design.

---

## 7.1 `get()`

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.get(1));
```

### Expected

```text
20
```

Remember:

> `get(index)` uses zero-based indexing.

---

## 7.2 `set()`

```java
stack.set(1, 200);

System.out.println(stack);
```

Expected:

```text
[10, 200, 30]
```

---

## 7.3 `add()`

Because of inheritance:

```java
stack.add(40);
```

is possible.

### Question

Why can a `Stack` use `add()` even though `add()` is not a traditional stack operation?

---

## 7.4 `remove()`

Try:

```java
stack.remove(0);
```

### Question

Is this a normal stack operation?

No.

A traditional stack should generally remove elements from the top using:

```java
pop();
```

---

## 7.5 Contains

```java
System.out.println(stack.contains(20));
```

Practice checking whether specific values exist.

---

## 7.6 Inherited Methods Practice

Try the following:

```java
stack.add(100);
stack.get(0);
stack.set(0, 500);
stack.contains(500);
stack.remove(0);
```

### Questions

1. Which methods come from `Vector`/the collection hierarchy?
2. Which methods represent actual stack operations?
3. Why can arbitrary indexed access be considered a weakness of the `Stack` API?

---

# 8. Stack vs ArrayDeque

Modern Java code generally prefers:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

instead of:

```java
Stack<Integer> stack = new Stack<>();
```

---

## 8.1 Convert Stack to Deque

Rewrite:

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.pop());
```

using:

```java
Deque<Integer>
```

and:

```java
ArrayDeque<Integer>
```

---

## 8.2 Compare Operations

| Stack | ArrayDeque |
|---|---|
| `push()` | `push()` |
| `pop()` | `pop()` |
| `peek()` | `peek()` |
| `empty()` | `isEmpty()` |
| Legacy class | Modern general-purpose deque |
| Allows `null` | Does not allow `null` |
| Synchronized methods | Not synchronized |

---

## 8.3 Modern Stack Example

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) {

        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println(stack.peek());
        System.out.println(stack.pop());
    }
}
```

### Practice

- [ ] Create stack using `Deque`
- [ ] Use `push()`
- [ ] Use `pop()`
- [ ] Use `peek()`
- [ ] Use `isEmpty()`

---

## 8.4 Null Difference

Compare:

```java
Stack<String> stack = new Stack<>();
stack.push(null);
```

with:

```java
Deque<String> stack = new ArrayDeque<>();
stack.push(null);
```

### Question

What is the important behavioral difference?

---

# 9. Intermediate Problems

## 9.1 Reverse a String

Input:

```text
hello
```

Output:

```text
olleh
```

### Requirement

Use a stack.

### Hint

1. Push every character.
2. Pop characters one by one.

---

## 9.2 Reverse Words

Input:

```text
Java is powerful
```

Output:

```text
powerful is Java
```

### Requirement

Use a stack of strings.

---

## 9.3 Balanced Parentheses

Check whether brackets are balanced.

Examples:

```text
()
```

Valid.

```text
({})
```

Valid.

```text
([)]
```

Invalid.

```text
((()))
```

Valid.

### Hint

Use a stack.

When you encounter:

```text
(
[
{
```

push it.

When you encounter a closing bracket, check the top.

---

## 9.4 Palindrome Check

Determine whether a string is a palindrome.

Examples:

```text
madam → true
level → true
java → false
```

### Requirement

Use a stack.

---

## 9.5 Decimal to Binary

Convert a decimal number into binary using a stack.

Example:

```text
10 → 1010
```

### Hint

Repeatedly:

```text
number % 2
number / 2
```

Push the remainders into a stack.

Then pop them.

---

## 9.6 Evaluate Postfix Expression

Evaluate:

```text
2 3 + 4 *
```

Expected:

```text
20
```

### Explanation

```text
2 + 3 = 5
5 * 4 = 20
```

### Requirement

Use a stack.

---

## 9.7 Another Postfix Problem

Evaluate:

```text
5 6 2 + * 12 4 / -
```

### Task

Calculate the final result using a stack.

---

## 9.8 Infix to Postfix

Convert:

```text
A + B * C
```

to:

```text
A B C * +
```

### Challenge

Use a stack to handle operators and precedence.

---

## 9.9 Undo Simulation

Imagine a text editor where every action is stored in a stack.

Actions:

```text
Type A
Type B
Type C
```

Undo operations should happen in reverse:

```text
Undo C
Undo B
Undo A
```

### Task

Design a simple Java program using a stack.

---

# 10. Code Prediction Questions

Try to answer these **before running the code**.

---

## Question 1

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.peek());
```

What is the output?

---

## Question 2

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);

System.out.println(stack.pop());
System.out.println(stack.pop());
```

What is the output?

---

## Question 3

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.search(20));
```

What is the output?

---

## Question 4

```java
Stack<Integer> stack = new Stack<>();

System.out.println(stack.pop());
```

What happens?

> Remember `EmptyStackException`.

---

## Question 5

```java
Stack<Integer> stack = new Stack<>();

System.out.println(stack.peek());
```

What happens?

---

## Question 6

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.get(0));
```

What is the output?

---

## Question 7

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(10);

System.out.println(stack.search(10));
```

What is the output?

---

## Question 8

```java
Stack<String> stack = new Stack<>();

stack.push(null);

System.out.println(stack.peek());
```

What happens?

---

## Question 9

```java
Deque<String> stack = new ArrayDeque<>();

stack.push(null);
```

What happens?

---

## Question 10

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);

for (Integer value : stack) {
    System.out.println(value);
}

System.out.println(stack.size());
```

### Questions

1. What is printed?
2. Is the stack empty afterward?
3. What is the final size?

---

# 11. Scenario-Based Questions

## Scenario 1 — Undo Feature

You are building a text editor.

Every user action needs to be undone in reverse order.

### Question

Which data structure would you choose?

### Expected Concept

Stack.

---

## Scenario 2 — Browser Back Button

A browser stores visited pages:

```text
Google
YouTube
GitHub
LinkedIn
```

The user presses Back.

Which page should be returned to first?

### Expected Concept

The most recently visited page.

This is a LIFO use case.

---

## Scenario 3 — DFS

You need to implement Depth-First Search without recursive calls.

### Question

What data structure can you use?

### Expected Concept

Stack.

---

## Scenario 4 — Modern Java Development

You are writing new Java code and need a stack.

Which is generally preferred?

```java
Stack<Integer>
```

or:

```java
Deque<Integer>
```

with:

```java
ArrayDeque<Integer>
```

Explain why.

---

## Scenario 5 — Null Requirement

Your application requires storing `null` values.

Would `ArrayDeque` be suitable for that requirement?

Why or why not?

---

## Scenario 6 — Thread Safety

Two threads need to modify a stack.

### Questions

1. Is `Stack` synchronized?
2. Does that automatically make every multi-step operation atomic?
3. What should you consider when designing concurrent code?

---

# 12. Challenge Problems

## Challenge 1 — Reverse Stack

Write:

```java
static Stack<Integer> reverseStack(Stack<Integer> stack)
```

that reverses the stack.

---

## Challenge 2 — Sort a Stack

Given:

```text
30
10
50
20
40
```

Sort the stack using another stack.

Expected sorted order:

```text
10
20
30
40
50
```

---

## Challenge 3 — Minimum Element

Design a stack that supports:

```java
push()
pop()
peek()
getMin()
```

with efficient `getMin()`.

### Hint

Use an additional stack.

---

## Challenge 4 — Remove Middle Element

Given a stack:

```text
1
2
3
4
5
```

Remove the middle element:

```text
3
```

### Requirement

Do not use random indexed access as the main solution.

---

## Challenge 5 — Two Stacks in One Structure

Design a structure that can represent two stacks inside one array.

Think about:

```text
Stack 1 → grows →
← grows ← Stack 2
```

---

## Challenge 6 — Valid Expression

Check whether this expression contains balanced brackets:

```text
{[(a+b) * (c-d)]}
```

Then test:

```text
{[(a+b) * (c-d)]}
```

and:

```text
{[(a+b) * (c-d)]}
```

with deliberately mismatched closing brackets.

---

# 13. Practice Checklist

## Basic Operations

- [ ] Create a `Stack`
- [ ] `push()`
- [ ] `pop()`
- [ ] `peek()`
- [ ] `empty()`
- [ ] `isEmpty()`
- [ ] `size()`

## Stack Behavior

- [ ] Understand LIFO
- [ ] Predict pop order
- [ ] Reverse elements
- [ ] Understand top vs bottom

## Search

- [ ] Use `search()`
- [ ] Understand 1-based indexing
- [ ] Understand search from top
- [ ] Understand `-1` for missing elements
- [ ] Test duplicate values

## Collection Behavior

- [ ] Use `get()`
- [ ] Use `set()`
- [ ] Use `add()`
- [ ] Use `remove()`
- [ ] Use `contains()`
- [ ] Understand inherited `Vector` methods

## Traversal

- [ ] Use for-each
- [ ] Use `Iterator`
- [ ] Use destructive `pop()` traversal
- [ ] Understand the difference between iteration and popping

## Modern Java

- [ ] Understand why `Stack` is legacy
- [ ] Use `Deque`
- [ ] Use `ArrayDeque`
- [ ] Understand `ArrayDeque` does not allow `null`
- [ ] Know `push()`, `pop()`, and `peek()` work with `Deque`

## Problem Solving

- [ ] Reverse a string
- [ ] Reverse words
- [ ] Check balanced parentheses
- [ ] Check palindrome
- [ ] Convert decimal to binary
- [ ] Evaluate postfix expression
- [ ] Convert infix to postfix
- [ ] Implement undo
- [ ] Sort a stack
- [ ] Find minimum efficiently

---

# 14. Final Goal

After completing this practice file, you should be able to confidently answer:

> **What is a Stack?**

> **What does LIFO mean?**

> **How do `push()`, `pop()`, and `peek()` work?**

> **What happens when `pop()` or `peek()` is called on an empty stack?**

> **How does `search()` work in Stack?**

> **Why is `search()` 1-based?**

> **Can Stack contain duplicates?**

> **Can Stack contain `null`?**

> **Why can Stack call methods like `get()` and `add()`?**

> **Why is Stack considered a legacy class?**

> **What should you use instead of Stack in modern Java?**

> **What is the difference between Stack and ArrayDeque?**

---

# 📌 Quick Revision

```text
Stack
  ↓
LIFO
  ↓
Last In → First Out
  ↓
push() → add to top
pop()  → remove from top
peek() → view top
  ↓
search() → 1-based position from top
  ↓
Legacy class
  ↓
extends Vector
  ↓
Modern alternative
  ↓
Deque + ArrayDeque
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
│   └── INTERVIEW.md   [ ]
│
├── 08-Queue
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

**Next:** `07-Stack/INTERVIEW.md`
