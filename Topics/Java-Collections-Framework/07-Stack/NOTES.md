# Stack in Java

> `Stack` is a legacy class in the Java Collections Framework that represents a **LIFO (Last-In, First-Out)** data structure. It extends `Vector`, which means it inherits Vector's synchronized behavior and dynamic-array implementation.

---

## Table of Contents

- [1. Introduction](#1-introduction)
- [2. What is Stack?](#2-what-is-stack)
- [3. LIFO Principle](#3-lifo-principle)
- [4. Stack Hierarchy](#4-stack-hierarchy)
- [5. Internal Structure](#5-internal-structure)
- [6. Creating a Stack](#6-creating-a-stack)
- [7. push()](#7-push)
- [8. pop()](#8-pop)
- [9. peek()](#9-peek)
- [10. empty()](#10-empty)
- [11. search()](#11-search)
- [12. Stack Example](#12-stack-example)
- [13. Stack Operations](#13-stack-operations)
- [14. Stack as LIFO](#14-stack-as-lifo)
- [15. Stack and Vector](#15-stack-and-vector)
- [16. Stack and ArrayDeque](#16-stack-and-arraydeque)
- [17. Why Stack is Legacy](#17-why-stack-is-legacy)
- [18. Recommended Modern Alternative](#18-recommended-modern-alternative)
- [19. Stack Using Deque](#19-stack-using-deque)
- [20. Stack Operations with ArrayDeque](#20-stack-operations-with-arraydeque)
- [21. Stack with Objects](#21-stack-with-objects)
- [22. Stack and Null](#22-stack-and-null)
- [23. Stack and Duplicates](#23-stack-and-duplicates)
- [24. Thread Safety](#24-thread-safety)
- [25. Time Complexity](#25-time-complexity)
- [26. Real-World Applications](#26-real-world-applications)
- [27. Common Mistakes](#27-common-mistakes)
- [28. Quick Revision](#28-quick-revision)
- [29. Final Mental Model](#29-final-mental-model)

---

# 1. Introduction

A **Stack** is a linear data structure where the most recently inserted element is removed first.

This behavior is called:

```text
LIFO
```

which means:

```text
Last In, First Out
```

Think about a stack of plates:

```text
       +-------+
       | Plate |  <- First removed
       +-------+
       | Plate |
       +-------+
       | Plate |  <- First inserted
       +-------+
```

You normally remove the top plate first.

The same principle applies to a stack.

---

# 2. What is Stack?

Java provides a class called:

```java
Stack
```

from:

```java
java.util
```

Example:

```java
Stack<Integer> stack =
        new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);
```

The stack is conceptually:

```text
Top
 |
 v
30
20
10
```

Calling:

```java
stack.pop();
```

returns:

```text
30
```

The remaining stack is:

```text
20
10
```

---

# 3. LIFO Principle

The fundamental rule of a stack is:

```text
Last In
   ↓
First Out
```

Suppose we perform:

```java
stack.push(10);
stack.push(20);
stack.push(30);
```

Insertion order:

```text
10 -> 20 -> 30
```

Removal order:

```text
30 -> 20 -> 10
```

Therefore:

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

# 4. Stack Hierarchy

`Stack` is a legacy class that extends `Vector`.

The hierarchy is:

```text
Object
   |
AbstractCollection
   |
AbstractList
   |
Vector
   |
Stack
```

So:

```text
Vector
   |
   v
Stack
```

This is important.

`Stack` inherits many methods from `Vector`.

---

## Important Interfaces

Through its inheritance, Stack participates in:

```text
Collection
List
RandomAccess
Cloneable
Serializable
```

---

# 5. Internal Structure

Because:

```java
Stack extends Vector
```

Stack uses Vector's underlying dynamic-array approach.

Conceptually:

```text
Stack
  |
  v
Vector
  |
  v
Dynamic Array
```

For example:

```text
Index:

  0    1    2
+----+----+----+
| 10 | 20 | 30 |
+----+----+----+
             ^
             |
            top
```

The top of the stack is represented by the last element.

---

# 6. Creating a Stack

Import:

```java
import java.util.Stack;
```

Create:

```java
Stack<Integer> stack =
        new Stack<>();
```

Add elements:

```java
stack.push(10);
stack.push(20);
stack.push(30);
```

Print:

```java
System.out.println(stack);
```

Output:

```text
[10, 20, 30]
```

The printed representation follows the underlying list order.

The top is:

```text
30
```

---

# 7. push()

The:

```java
push()
```

method adds an element to the top of the stack.

Example:

```java
Stack<Integer> stack =
        new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);
```

Conceptually:

```text
Top
 |
 v
30
20
10
```

---

## Syntax

```java
stack.push(element);
```

Example:

```java
stack.push(40);
```

Now:

```text
Top
 |
 v
40
30
20
10
```

---

# 8. pop()

The:

```java
pop()
```

method removes and returns the top element.

Example:

```java
Stack<Integer> stack =
        new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

int value =
        stack.pop();

System.out.println(value);
```

Output:

```text
30
```

Remaining stack:

```text
[10, 20]
```

---

## Important

`pop()` does two things:

```text
1. Removes the top element
2. Returns the removed element
```

---

# 9. peek()

The:

```java
peek()
```

method returns the top element **without removing it**.

Example:

```java
Stack<Integer> stack =
        new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(
        stack.peek()
);
```

Output:

```text
30
```

The stack remains:

```text
[10, 20, 30]
```

---

## pop() vs peek()

| Method | Removes element? | Returns element? |
|---|---|---|
| `pop()` | Yes | Yes |
| `peek()` | No | Yes |

---

# 10. empty()

The:

```java
empty()
```

method checks whether the stack contains no elements.

Example:

```java
Stack<Integer> stack =
        new Stack<>();

System.out.println(
        stack.empty()
);
```

Output:

```text
true
```

After:

```java
stack.push(10);
```

Output becomes:

```text
false
```

---

## Modern Alternative

You can also use:

```java
isEmpty()
```

because Stack inherits it from the collection hierarchy.

For example:

```java
stack.isEmpty();
```

---

# 11. search()

Stack provides:

```java
search(Object o)
```

It returns the **1-based position from the top**.

Example:

```java
Stack<Integer> stack =
        new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);
stack.push(40);
```

Conceptually:

```text
Top
 |
 v
40  <- position 1
30  <- position 2
20  <- position 3
10  <- position 4
```

Therefore:

```java
stack.search(40);
```

returns:

```text
1
```

And:

```java
stack.search(20);
```

returns:

```text
3
```

---

## If Element Does Not Exist

```java
stack.search(100);
```

returns:

```text
-1
```

---

# 12. Stack Example

Complete example:

```java
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Stack<Integer> stack =
                new Stack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println(stack);

        System.out.println(
                stack.peek()
        );

        System.out.println(
                stack.pop()
        );

        System.out.println(stack);
    }
}
```

Output:

```text
[10, 20, 30]
30
30
[10, 20]
```

---

# 13. Stack Operations

The primary Stack operations are:

| Operation | Purpose |
|---|---|
| `push()` | Add to top |
| `pop()` | Remove top |
| `peek()` | View top |
| `empty()` | Check empty |
| `search()` | Find position from top |

---

## Example

Starting:

```text
[]
```

### Push 10

```text
[10]
```

### Push 20

```text
[10, 20]
```

### Push 30

```text
[10, 20, 30]
```

### Peek

```text
30
```

Stack remains:

```text
[10, 20, 30]
```

### Pop

```text
30
```

Stack becomes:

```text
[10, 20]
```

---

# 14. Stack as LIFO

Consider:

```java
stack.push("A");
stack.push("B");
stack.push("C");
```

Stack:

```text
Top
 |
 v
C
B
A
```

Now:

```java
stack.pop();
```

returns:

```text
C
```

Next:

```java
stack.pop();
```

returns:

```text
B
```

Finally:

```java
stack.pop();
```

returns:

```text
A
```

Therefore:

```text
Push order:

A -> B -> C

Pop order:

C -> B -> A
```

---

# 15. Stack and Vector

The most important structural fact:

```java
Stack extends Vector
```

Therefore Stack inherits Vector's characteristics.

Conceptually:

```text
Stack
   |
   +-- extends Vector
           |
           +-- Dynamic array
           |
           +-- Synchronized methods
```

---

## Consequence

Stack is synchronized because it inherits the synchronized behavior of Vector and its own operations are built on that model.

This is one reason Stack is considered a legacy class.

---

# 16. Stack and ArrayDeque

For new code, Java documentation generally recommends considering `Deque` implementations instead of the legacy `Stack` class.

A common modern choice is:

```java
ArrayDeque
```

Instead of:

```java
Stack<Integer> stack =
        new Stack<>();
```

use:

```java
Deque<Integer> stack =
        new ArrayDeque<>();
```

with:

```java
stack.push(10);
stack.push(20);
stack.push(30);
```

---

# 17. Why Stack is Legacy

`Stack` was designed as an older collection class.

It extends:

```java
Vector
```

which means it inherits list-oriented operations in addition to stack operations.

For example:

```java
stack.get(0);
stack.add(10);
stack.remove(0);
```

are possible.

But a pure stack should ideally expose only operations such as:

```text
push
pop
peek
```

This is one reason:

```java
Deque
```

is preferred for modern stack implementations.

---

# 18. Recommended Modern Alternative

Use:

```java
Deque<E>
```

with:

```java
ArrayDeque<E>
```

Example:

```java
Deque<Integer> stack =
        new ArrayDeque<>();
```

Then:

```java
stack.push(10);
stack.push(20);
stack.push(30);
```

Pop:

```java
stack.pop();
```

Peek:

```java
stack.peek();
```

Check empty:

```java
stack.isEmpty();
```

---

## Why Use the Interface?

Prefer:

```java
Deque<Integer> stack =
        new ArrayDeque<>();
```

over:

```java
ArrayDeque<Integer> stack =
        new ArrayDeque<>();
```

when you only need deque/stack behavior.

This follows the general programming-to-an-interface principle.

---

# 19. Stack Using Deque

Modern stack:

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) {

        Deque<Integer> stack =
                new ArrayDeque<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println(
                stack.pop()
        );
    }
}
```

Output:

```text
30
```

---

# 20. Stack Operations with ArrayDeque

The stack methods map naturally to:

```text
push()
pop()
peek()
```

Example:

```java
Deque<String> stack =
        new ArrayDeque<>();

stack.push("Java");
stack.push("Spring");
stack.push("Hibernate");
```

Stack behavior:

```text
Top
 |
 v
Hibernate
Spring
Java
```

Then:

```java
stack.pop();
```

returns:

```text
Hibernate
```

---

# 21. Stack with Objects

Stack is not limited to integers.

You can store objects:

```java
Stack<String> stack =
        new Stack<>();
```

or:

```java
Stack<Student> stack =
        new Stack<>();
```

Example:

```java
Stack<String> stack =
        new Stack<>();

stack.push("Java");
stack.push("Spring");
stack.push("Docker");
```

Pop:

```java
String value =
        stack.pop();
```

---

# 22. Stack and Null

`Stack` inherits Vector's ability to store `null`.

Example:

```java
Stack<String> stack =
        new Stack<>();

stack.push(null);
```

This is allowed.

---

## Important Comparison

`ArrayDeque` does **not** permit `null`.

Therefore:

```text
Stack
    -> null allowed

ArrayDeque
    -> null not allowed
```

This is one behavioral difference between the legacy Stack class and the modern ArrayDeque approach.

---

# 23. Stack and Duplicates

Stack allows duplicate values.

Example:

```java
Stack<Integer> stack =
        new Stack<>();

stack.push(10);
stack.push(10);
stack.push(20);
```

Result:

```text
[10, 10, 20]
```

There is no uniqueness requirement.

---

# 24. Thread Safety

Because Stack extends Vector, it has synchronized behavior.

Example:

```java
Stack<Integer> stack =
        new Stack<>();
```

This does not mean every possible multi-step operation is automatically atomic.

For example:

```java
if (!stack.contains(10)) {
    stack.push(10);
}
```

The individual operations have synchronization, but the entire sequence is not automatically one atomic operation.

---

## Modern Concurrency

If multiple threads need stack behavior, choose the data structure according to the concurrency requirements rather than automatically choosing Stack.

---

# 25. Time Complexity

For typical Stack operations:

| Operation | Complexity |
|---|---:|
| `push()` | O(1) amortized |
| `pop()` | O(1) |
| `peek()` | O(1) |
| `empty()` | O(1) |
| `isEmpty()` | O(1) |
| `search()` | O(n) |
| `get(index)` | O(1) |
| `contains()` | O(n) |
| `remove(index)` | O(n) |

---

## Why is push O(1) Amortized?

Stack is backed by Vector's dynamic array.

Normally, adding at the end is:

```text
O(1)
```

But if the backing array needs to grow, resizing requires copying elements.

That resize can take:

```text
O(n)
```

Therefore:

```text
push()
    -> O(1) amortized
```

---

# 26. Real-World Applications

Stacks are used extensively in computer science.

---

## 26.1 Function Call Stack

When methods call other methods, the runtime maintains call information using a stack-like structure.

Example:

```text
main()
  |
  v
methodA()
  |
  v
methodB()
```

Conceptually:

```text
Top
 |
 v
methodB()
methodA()
main()
```

When `methodB()` finishes, it is removed first.

---

## 26.2 Undo Operations

Text editors can use stacks for:

```text
Undo
```

Example:

```text
Type A
Type B
Type C
```

Undo order:

```text
C
B
A
```

---

## 26.3 Browser History

A stack-like model can be used for:

```text
Back
```

operations.

---

## 26.4 Expression Evaluation

Stacks are commonly used in:

```text
Arithmetic expression evaluation
```

and:

```text
Postfix evaluation
```

---

## 26.5 Parentheses Matching

Example:

```text
{ [ ( ) ] }
```

A stack can track opening brackets.

---

## 26.6 Depth-First Search

DFS can be implemented using:

```text
Recursion
```

or an explicit:

```text
Stack
```

---

## 26.7 Backtracking

Many backtracking algorithms naturally follow stack-like behavior.

Examples:

```text
Maze solving
Permutations
Combinations
N-Queens
```

---

# 27. Common Mistakes

## Mistake 1: Stack Is a Modern Recommended Class

Not generally.

`Stack` is a legacy class.

Prefer:

```java
Deque<E> stack =
        new ArrayDeque<>();
```

for typical modern stack behavior.

---

## Mistake 2: Stack Does Not Extend Vector

Wrong.

```text
Stack extends Vector
```

---

## Mistake 3: Stack Is a Linked List

Wrong.

Stack itself extends Vector, so its implementation is array-backed.

---

## Mistake 4: peek() Removes the Element

Wrong.

```text
peek()
    -> returns top
    -> does not remove
```

---

## Mistake 5: pop() Only Reads the Element

Wrong.

```text
pop()
    -> returns top
    -> removes top
```

---

## Mistake 6: search() Uses Zero-Based Indexing

Wrong.

Stack's:

```java
search()
```

uses:

```text
1-based position from the top
```

---

## Mistake 7: Stack Is Always the Best Choice for Thread Safety

Wrong.

It is synchronized, but that does not make it the best concurrency abstraction for every use case.

---

# 28. Quick Revision

| Concept | Key Point |
|---|---|
| Stack | LIFO data structure |
| Package | `java.util` |
| Class | `Stack<E>` |
| Parent | `Vector<E>` |
| Internal approach | Dynamic array |
| Main operations | `push`, `pop`, `peek` |
| `push()` | Add to top |
| `pop()` | Remove and return top |
| `peek()` | View top |
| `empty()` | Check empty |
| `search()` | 1-based position from top |
| Duplicates | Allowed |
| `null` | Allowed |
| Synchronized | Yes |
| Legacy | Yes |
| Modern alternative | `Deque` + `ArrayDeque` |

---

# 29. Final Mental Model

Think about Java Stack like this:

```text
                 Stack
                    |
                    v
                 Vector
                    |
                    v
              Dynamic Array
                    |
          +---------+---------+
          |         |         |
          v         v         v
         10        20        30
                              ^
                              |
                             TOP
```

Operations:

```text
push(40)

          TOP
           |
           v
          40
          30
          20
          10
```

Then:

```text
pop()
```

returns:

```text
40
```

and:

```text
30
20
10
```

remain.

---

# Stack vs ArrayDeque

```text
              Stack
                |
                +-- Legacy
                |
                +-- extends Vector
                |
                +-- Synchronized
                |
                +-- Dynamic array
                |
                +-- LIFO
                |
                +-- null allowed


            ArrayDeque
                |
                +-- Modern choice
                |
                +-- Implements Deque
                |
                +-- Efficient deque operations
                |
                +-- LIFO supported
                |
                +-- null not allowed
```

---

# Most Important Interview Statement

> **Stack is a legacy Java class that implements LIFO behavior and extends Vector. It provides operations such as `push()`, `pop()`, `peek()`, `empty()`, and `search()`. Because it extends Vector, it uses a dynamically growing array and has synchronized behavior. For modern Java applications, `Deque` with `ArrayDeque` is generally preferred for stack operations because it provides a cleaner stack/deque abstraction without the legacy design of Stack.**

---

# Collections Framework Progress

```text
Java Collections Framework
│
├── 01-Iterable
│   ├── NOTES.md
│   ├── PRACTICE.md
│   └── INTERVIEW.md
│
├── 02-Collection-Interface
│   ├── NOTES.md
│   ├── PRACTICE.md
│   └── INTERVIEW.md
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
│   ├── PRACTICE.md    [ ]
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

---

# Stack Completion

```text
07-Stack/
├── NOTES.md       [x]
├── PRACTICE.md    [ ]
└── INTERVIEW.md   [ ]
```

> **Next: `07-Stack/PRACTICE.md`**
