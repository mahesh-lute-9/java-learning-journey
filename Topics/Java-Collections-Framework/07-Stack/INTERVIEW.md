# Stack — Interview Questions

> Interview-focused questions and answers for Java `Stack`.

---

## 📚 Table of Contents

1. [Basic Questions](#1-basic-questions)
2. [Core Stack Operations](#2-core-stack-operations)
3. [Internal Working](#3-internal-working)
4. [Stack and Vector](#4-stack-and-vector)
5. [Stack vs ArrayDeque](#5-stack-vs-arraydeque)
6. [Time Complexity](#6-time-complexity)
7. [Exceptions and Edge Cases](#7-exceptions-and-edge-cases)
8. [Code-Based Questions](#8-code-based-questions)
9. [Scenario-Based Questions](#9-scenario-based-questions)
10. [Common Interview Traps](#10-common-interview-traps)
11. [Rapid-Fire Questions](#11-rapid-fire-questions)
12. [Must-Know Questions](#12-must-know-questions)
13. [Final Interview Answer](#13-final-interview-answer)
14. [Interview Checklist](#14-interview-checklist)

---

# 1. Basic Questions

## Q1. What is a Stack?

A **Stack** is a linear data structure that follows the:

> **LIFO — Last In, First Out**

The element added most recently is the first element removed.

In Java:

```java
Stack<Integer> stack = new Stack<>();
```

`Stack` belongs to the `java.util` package.

---

## Q2. What is LIFO?

LIFO means:

> **Last In, First Out**

Example:

```text
push(10)
push(20)
push(30)
```

The removal order is:

```text
30
20
10
```

The last element inserted is removed first.

---

## Q3. How do you create a Stack in Java?

```java
import java.util.Stack;

Stack<Integer> stack = new Stack<>();
```

You can also create a stack for other types:

```java
Stack<String> stack = new Stack<>();
```

---

## Q4. What are the main Stack methods?

The five traditional Stack-specific methods are:

| Method | Purpose |
|---|---|
| `push()` | Adds an element to the top |
| `pop()` | Removes and returns the top element |
| `peek()` | Returns the top element without removing it |
| `empty()` | Checks whether the stack is empty |
| `search()` | Finds an element's position from the top |

---

# 2. Core Stack Operations

## Q5. What does `push()` do?

`push()` adds an element to the top of the stack.

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);
```

Stack:

```text
Bottom
10
20
30 ← Top
```

---

## Q6. What does `pop()` do?

`pop()` removes and returns the top element.

```java
int value = stack.pop();
```

If:

```text
10
20
30 ← Top
```

then:

```java
pop()
```

returns:

```text
30
```

and removes it.

---

## Q7. What does `peek()` do?

`peek()` returns the top element without removing it.

```java
System.out.println(stack.peek());
```

If the stack is:

```text
10
20
30
```

then:

```java
peek()
```

returns:

```text
30
```

The stack remains unchanged.

---

## Q8. Difference between `pop()` and `peek()`?

| `pop()` | `peek()` |
|---|---|
| Returns top element | Returns top element |
| Removes it | Does not remove it |
| Changes stack size | Does not change size |

Example:

```java
stack.push(10);
stack.push(20);

System.out.println(stack.peek());
System.out.println(stack.size());
```

Output:

```text
20
2
```

But:

```java
System.out.println(stack.pop());
System.out.println(stack.size());
```

Output:

```text
20
1
```

---

## Q9. How do you check if a Stack is empty?

You can use:

```java
stack.empty();
```

or:

```java
stack.isEmpty();
```

Example:

```java
if (stack.isEmpty()) {
    System.out.println("Stack is empty");
}
```

`empty()` is the legacy method provided by `Stack`.

`isEmpty()` comes from the collection hierarchy.

---

## Q10. What happens when you pop an empty Stack?

```java
Stack<Integer> stack = new Stack<>();

stack.pop();
```

It throws:

```text
EmptyStackException
```

The same applies to:

```java
stack.peek();
```

when the stack is empty.

---

# 3. Internal Working

## Q11. What class does Stack extend?

This is one of the most important interview questions.

```text
Stack
  ↓
Vector
```

In Java:

```java
public class Stack<E> extends Vector<E>
```

Therefore, `Stack` inherits functionality from `Vector`.

---

## Q12. Is Stack an interface?

No.

`Stack` is a **class**.

```java
Stack<Integer> stack = new Stack<>();
```

---

## Q13. Is Stack part of the Java Collections Framework?

Yes.

It belongs to:

```text
java.util
```

and participates in the collections hierarchy through `Vector`.

Conceptually:

```text
Iterable
   ↓
Collection
   ↓
List
   ↓
Vector
   ↓
Stack
```

---

## Q14. Is Stack array-based?

`Stack` extends `Vector`, and `Vector` is an array-backed dynamic list.

Therefore, Stack's underlying storage is based on a dynamically growing array.

---

## Q15. What happens internally during `push()`?

Conceptually:

```text
Stack
  ↓
Vector
  ↓
Dynamic array
  ↓
Add element at the end
```

For example:

```java
stack.push(10);
stack.push(20);
stack.push(30);
```

The top corresponds to the most recently added element.

---

# 4. Stack and Vector

## Q16. Why is Stack considered a legacy class?

`Stack` is an older class that extends `Vector`.

This means it inherits general-purpose list operations such as:

```java
get()
set()
add()
remove()
```

These operations don't naturally fit the abstraction of a stack.

Modern Java code generally prefers:

```java
Deque<E>
```

for stack behavior.

---

## Q17. Can Stack use `get()`?

Yes.

Because Stack extends Vector:

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.get(1));
```

Output:

```text
20
```

But arbitrary indexed access is not part of normal stack semantics.

---

## Q18. Can Stack use `add()`?

Yes.

Because it inherits methods from `Vector`.

```java
stack.add(40);
```

However, when thinking in terms of stack behavior, prefer:

```java
stack.push(40);
```

---

## Q19. Why is inheritance from Vector considered a design weakness?

A stack should primarily expose operations such as:

```text
push
pop
peek
```

But because `Stack` extends `Vector`, it also exposes operations like:

```text
add
remove
get
set
```

This allows users to manipulate elements in ways that don't follow normal LIFO behavior.

---

## Q20. Is Stack synchronized?

Yes.

`Stack` inherits synchronization behavior from `Vector`.

Therefore, its individual methods are synchronized.

However:

> Synchronization of individual methods does not automatically make a sequence of multiple operations atomic.

For example:

```java
if (!stack.empty()) {
    stack.pop();
}
```

The check and removal are separate operations.

For concurrent programs, synchronization strategy should be considered at the operation/critical-section level.

---

# 5. Stack vs ArrayDeque

## Q21. What should you use instead of Stack in modern Java?

Generally:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

Example:

```java
import java.util.ArrayDeque;
import java.util.Deque;

Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.pop());
```

---

## Q22. Why is ArrayDeque preferred over Stack?

For typical stack usage, `ArrayDeque` is generally preferred because:

- It is designed as a modern deque implementation.
- It supports stack operations directly.
- It avoids the legacy `Stack` class design.
- It does not carry the synchronization behavior of `Vector`.
- It is generally a better fit for typical single-threaded stack use.

---

## Q23. Does ArrayDeque support the same stack methods?

The core operations are the same:

```java
push()
pop()
peek()
```

For example:

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);

System.out.println(stack.peek());
System.out.println(stack.pop());
```

---

## Q24. What is an important difference regarding `null`?

`Stack` allows `null`:

```java
Stack<String> stack = new Stack<>();

stack.push(null);
```

`ArrayDeque` does not allow `null` elements:

```java
Deque<String> stack = new ArrayDeque<>();

stack.push(null);
```

This results in a `NullPointerException`.

---

## Q25. Is ArrayDeque thread-safe?

No.

`ArrayDeque` is not synchronized.

If multiple threads need to access a stack concurrently, choose an appropriate concurrent design rather than assuming `ArrayDeque` provides thread safety.

---

## Q26. Should you always replace Stack with ArrayDeque?

For normal stack use in new code, `Deque` with `ArrayDeque` is usually the preferred choice.

However, the appropriate implementation depends on requirements such as:

- concurrency
- API compatibility
- null handling
- existing legacy code

---

# 6. Time Complexity

## Q27. What is the time complexity of `push()`?

Typically:

```text
O(1) amortized
```

A resize may occasionally require more work.

---

## Q28. What is the time complexity of `pop()`?

```text
O(1)
```

The top element is removed.

---

## Q29. What is the time complexity of `peek()`?

```text
O(1)
```

It only accesses the top element.

---

## Q30. What is the time complexity of `search()`?

```text
O(n)
```

Because it may need to inspect many elements.

---

## Q31. What is the time complexity of `get(index)`?

Because the underlying structure is array-backed:

```text
O(1)
```

This is an inherited list operation, not a normal stack operation.

---

## Q32. What is the time complexity of indexed insertion/removal?

Operations such as inserting or removing at an arbitrary index may require shifting elements.

Therefore:

```text
O(n)
```

in the general case.

---

## Complexity Table

| Operation | Complexity |
|---|---:|
| `push()` | O(1) amortized |
| `pop()` | O(1) |
| `peek()` | O(1) |
| `empty()` | O(1) |
| `isEmpty()` | O(1) |
| `search()` | O(n) |
| `get(index)` | O(1) |
| Indexed insertion/removal | O(n) |

---

# 7. Exceptions and Edge Cases

## Q33. What exception does `pop()` throw on an empty Stack?

```text
EmptyStackException
```

Example:

```java
Stack<Integer> stack = new Stack<>();

stack.pop();
```

---

## Q34. What exception does `peek()` throw on an empty Stack?

```text
EmptyStackException
```

---

## Q35. Does Stack allow duplicate elements?

Yes.

```java
stack.push(10);
stack.push(10);
stack.push(10);
```

All three values can exist.

---

## Q36. Does Stack allow `null`?

Yes.

```java
stack.push(null);
```

This is allowed by `Stack`.

---

## Q37. What does `search()` return if an element doesn't exist?

It returns:

```text
-1
```

Example:

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);

System.out.println(stack.search(100));
```

Output:

```text
-1
```

---

## Q38. Is `search()` zero-based?

No.

`search()` uses a **1-based position from the top**.

Example:

```text
Bottom
10
20
30
40
Top
```

Then:

```text
search(40) → 1
search(30) → 2
search(20) → 3
search(10) → 4
```

---

# 8. Code-Based Questions

## Q39. What is the output?

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.pop());
System.out.println(stack.peek());
```

### Answer

```text
30
20
```

---

## Q40. What is the output?

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.search(20));
```

### Answer

```text
2
```

From the top:

```text
30 → 1
20 → 2
10 → 3
```

---

## Q41. What is the output?

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);

stack.pop();

System.out.println(stack);
```

### Answer

```text
[10]
```

---

## Q42. What is the output?

```java
Stack<String> stack = new Stack<>();

stack.push("A");
stack.push("B");
stack.push("C");

while (!stack.empty()) {
    System.out.println(stack.pop());
}
```

### Answer

```text
C
B
A
```

---

## Q43. What happens?

```java
Stack<Integer> stack = new Stack<>();

stack.peek();
```

### Answer

```text
EmptyStackException
```

---

## Q44. What is the output?

```java
Stack<Integer> stack = new Stack<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.get(0));
```

### Answer

```text
10
```

Remember:

```text
get() → zero-based index
search() → 1-based position from top
```

---

## Q45. What happens?

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(null);
```

### Answer

```text
NullPointerException
```

`ArrayDeque` does not permit `null` elements.

---

# 9. Scenario-Based Questions

## Q46. You need to implement an Undo feature. Which data structure would you use?

A stack is a natural choice.

Every new action is pushed:

```text
Action A
Action B
Action C
```

Undo happens in reverse:

```text
Undo C
Undo B
Undo A
```

This follows LIFO.

---

## Q47. How can a stack be used in browser history?

Recently visited pages can be stored so that the most recently visited page can be processed first when going backward.

This follows LIFO behavior.

---

## Q48. How is a stack used in recursion?

Every recursive method call creates a stack frame.

Conceptually:

```text
main()
  ↓
methodA()
  ↓
methodB()
  ↓
methodC()
```

When a method returns, the most recent stack frame is removed first.

This follows LIFO behavior.

---

## Q49. How can Stack be used for DFS?

Depth-First Search can be implemented iteratively using a stack.

Conceptually:

```text
push starting node

while stack is not empty:
    node = pop()
    process node
    push unvisited neighbors
```

---

## Q50. You are writing new Java code and need a stack. What would you choose?

A strong answer is:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

rather than:

```java
Stack<Integer> stack = new Stack<>();
```

because `Deque` with `ArrayDeque` is generally preferred for modern stack usage.

---

# 10. Common Interview Traps

## Trap 1 — Saying Stack is an interface

Incorrect:

```text
Stack is an interface.
```

Correct:

```text
Stack is a class.
```

---

## Trap 2 — Saying Stack implements List directly

`Stack` extends:

```text
Vector
```

and `Vector` implements the relevant collection/list interfaces.

Hierarchy:

```text
Stack → Vector → List
```

---

## Trap 3 — Confusing `search()` with `indexOf()`

`search()`:

- searches from the top
- returns 1-based position
- returns `-1` if absent

`indexOf()`:

- uses normal list indexing
- is zero-based

---

## Trap 4 — Saying `search()` returns zero-based index

Incorrect:

```text
search() → zero-based
```

Correct:

```text
search() → 1-based position from top
```

---

## Trap 5 — Saying `peek()` removes an element

Incorrect.

`peek()` only views the top.

```java
stack.peek();
```

does not reduce the size.

---

## Trap 6 — Saying `pop()` only returns an element

`pop()`:

1. returns the top element
2. removes it

---

## Trap 7 — Saying Stack is the recommended modern stack

For new code, generally prefer:

```java
Deque<E> stack = new ArrayDeque<>();
```

---

## Trap 8 — Saying ArrayDeque allows null

Incorrect.

```text
ArrayDeque → does not allow null
```

---

## Trap 9 — Saying Stack's synchronization makes every operation atomic

Not necessarily.

Individual synchronized methods do not automatically make a multi-operation sequence atomic.

---

## Trap 10 — Thinking normal iteration is the same as popping

This:

```java
for (Integer value : stack) {
    System.out.println(value);
}
```

does not remove elements.

This:

```java
while (!stack.empty()) {
    System.out.println(stack.pop());
}
```

does remove elements.

---

# 11. Rapid-Fire Questions

Use these for quick interview revision.

### Q1. What principle does Stack follow?

**LIFO.**

### Q2. What does LIFO mean?

**Last In, First Out.**

### Q3. Which package contains Stack?

```java
java.util
```

### Q4. Is Stack a class or interface?

**Class.**

### Q5. Which class does Stack extend?

**Vector.**

### Q6. Main Stack operation for insertion?

**`push()`**

### Q7. Main Stack operation for removal?

**`pop()`**

### Q8. Main Stack operation for viewing the top?

**`peek()`**

### Q9. What does `pop()` return?

**The top element and removes it.**

### Q10. What does `peek()` do?

**Returns the top element without removing it.**

### Q11. What does `empty()` return?

**A boolean indicating whether the stack is empty.**

### Q12. What happens when `pop()` is called on an empty Stack?

**`EmptyStackException`.**

### Q13. What happens when `peek()` is called on an empty Stack?

**`EmptyStackException`.**

### Q14. Does Stack allow duplicates?

**Yes.**

### Q15. Does Stack allow null?

**Yes.**

### Q16. What does `search()` return?

**1-based position from the top.**

### Q17. What does `search()` return if not found?

**`-1`.**

### Q18. Is Stack synchronized?

**Yes, through its Vector inheritance.**

### Q19. Is Stack considered legacy?

**Yes.**

### Q20. What is generally preferred instead?

```java
Deque<E> stack = new ArrayDeque<>();
```

### Q21. Does ArrayDeque allow null?

**No.**

### Q22. Is ArrayDeque synchronized?

**No.**

### Q23. Complexity of `push()`?

**O(1) amortized.**

### Q24. Complexity of `pop()`?

**O(1).**

### Q25. Complexity of `peek()`?

**O(1).**

### Q26. Complexity of `search()`?

**O(n).**

---

# 12. Must-Know Questions

If you are preparing for a Java interview, make sure you can answer these without hesitation:

- [ ] What is Stack?
- [ ] What is LIFO?
- [ ] How does `push()` work?
- [ ] How does `pop()` work?
- [ ] How does `peek()` work?
- [ ] What happens when Stack is empty?
- [ ] What is `EmptyStackException`?
- [ ] What does `search()` return?
- [ ] Why is `search()` 1-based?
- [ ] Does Stack allow duplicates?
- [ ] Does Stack allow null?
- [ ] Which class does Stack extend?
- [ ] Why is Stack considered legacy?
- [ ] What methods does Stack inherit from Vector?
- [ ] Is Stack synchronized?
- [ ] Does synchronization make compound operations atomic?
- [ ] Why prefer `Deque` and `ArrayDeque`?
- [ ] Does ArrayDeque allow null?
- [ ] What are the time complexities of Stack operations?
- [ ] Where are stacks used in real applications?

---

# 13. Final Interview Answer

## ⭐ Best Short Answer

> **Stack is a legacy Java class in `java.util` that follows the LIFO principle — Last In, First Out. It extends `Vector` and provides stack-specific operations such as `push()`, `pop()`, `peek()`, `empty()`, and `search()`. `push`, `pop`, and `peek` are typically O(1), while `search` is O(n). Stack is synchronized through its Vector inheritance, but it is generally not the preferred choice for new code. For modern stack implementations, `Deque` with `ArrayDeque` is usually preferred.**

---

## ⭐ If the Interviewer Asks "Why Not Stack?"

You can answer:

> **`Stack` is a legacy class because it extends `Vector`, which exposes general list operations that don't naturally belong to a stack. For new code, `Deque` with `ArrayDeque` provides a cleaner and more modern stack abstraction and avoids the synchronization overhead of `Vector` for typical single-threaded use.**

---

# 14. Interview Checklist

## Fundamentals

- [ ] Stack definition
- [ ] LIFO
- [ ] Top and bottom
- [ ] Push
- [ ] Pop
- [ ] Peek

## Java Stack

- [ ] `java.util.Stack`
- [ ] Stack extends Vector
- [ ] Stack is a class
- [ ] Legacy design
- [ ] Synchronized methods

## Methods

- [ ] `push()`
- [ ] `pop()`
- [ ] `peek()`
- [ ] `empty()`
- [ ] `isEmpty()`
- [ ] `search()`

## Edge Cases

- [ ] Empty `pop()` → `EmptyStackException`
- [ ] Empty `peek()` → `EmptyStackException`
- [ ] Duplicates allowed
- [ ] `null` allowed
- [ ] `search()` returns `-1` when absent
- [ ] `search()` is 1-based from top

## Modern Java

- [ ] Know `Deque`
- [ ] Know `ArrayDeque`
- [ ] Know why Stack is legacy
- [ ] Know ArrayDeque does not allow null
- [ ] Know ArrayDeque is not synchronized

## Complexity

- [ ] `push()` → O(1) amortized
- [ ] `pop()` → O(1)
- [ ] `peek()` → O(1)
- [ ] `search()` → O(n)
- [ ] `get(index)` → O(1)

---

# 🚀 Final Mental Model

```text
                 STACK
                   │
                   ▼
                  LIFO
                   │
          Last In → First Out
                   │
        ┌──────────┼──────────┐
        ▼          ▼          ▼
      push()      pop()      peek()
        │          │          │
        ▼          ▼          ▼
       Add       Remove      View
        │          │          │
        └──────────┴──────────┘
                   │
                   ▼
             Legacy Java Class
                   │
                   ▼
              extends Vector
                   │
                   ▼
        Modern Alternative
                   │
                   ▼
        Deque + ArrayDeque
```

---

# 📌 Progress

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

**Stack is now complete. Next topic: `08-Queue/NOTES.md`.**
