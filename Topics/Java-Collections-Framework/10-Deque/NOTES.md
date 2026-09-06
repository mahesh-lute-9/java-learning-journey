# Deque — Notes

> `Deque` stands for **Double-Ended Queue**. It allows insertion and removal of elements from **both ends** of the collection.

---

## Table of Contents

1. [What is Deque?](#1-what-is-deque)
2. [Deque Hierarchy](#2-deque-hierarchy)
3. [Why Deque?](#3-why-deque)
4. [Basic Deque Operations](#4-basic-deque-operations)
5. [Head and Tail Operations](#5-head-and-tail-operations)
6. [Deque Method Groups](#6-deque-method-groups)
7. [addFirst() and addLast()](#7-addfirst-and-addlast)
8. [offerFirst() and offerLast()](#8-offerfirst-and-offerlast)
9. [removeFirst() and removeLast()](#9-removefirst-and-removelast)
10. [pollFirst() and pollLast()](#10-pollfirst-and-polllast)
11. [getFirst() and getLast()](#11-getfirst-and-getlast)
12. [peekFirst() and peekLast()](#12-peekfirst-and-peeklast)
13. [Deque as a Queue](#13-deque-as-a-queue)
14. [Deque as a Stack](#14-deque-as-a-stack)
15. [FIFO vs LIFO](#15-fifo-vs-lifo)
16. [Implementations of Deque](#16-implementations-of-deque)
17. [Deque vs Queue](#17-deque-vs-queue)
18. [Deque vs Stack](#18-deque-vs-stack)
19. [Deque vs PriorityQueue](#19-deque-vs-priorityqueue)
20. [Null Elements](#20-null-elements)
21. [Duplicates](#21-duplicates)
22. [Iteration](#22-iteration)
23. [Time Complexity](#23-time-complexity)
24. [Real-World Applications](#24-real-world-applications)
25. [Important Interview Points](#25-important-interview-points)
26. [Common Mistakes](#26-common-mistakes)
27. [Quick Revision](#27-quick-revision)
28. [Final Mental Model](#28-final-mental-model)
29. [Progress](#29-progress)

---

# 1. What is Deque?

`Deque` is an interface in the Java Collections Framework.

The name comes from:

```text
Double-Ended Queue
```

It supports insertion, removal, and inspection from **both the front and the back**.

Conceptually:

```text
        FRONT                         BACK
          ↓                             ↓
       ┌────┬────┬────┬────┬────┐
       │ 10 │ 20 │ 30 │ 40 │ 50 │
       └────┴────┴────┴────┴────┘
          ↑                             ↑
       remove                         remove
       / add                          / add
```

Unlike a normal `Queue`, which is primarily designed around one direction of insertion and removal, a `Deque` provides operations at both ends.

---

# 2. Deque Hierarchy

`Deque` is an **interface**.

The important hierarchy is:

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

Example:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

Using the interface as the reference type is generally preferred:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

rather than:

```java
ArrayDeque<Integer> deque = new ArrayDeque<>();
```

when you only need `Deque` behavior.

---

# 3. Why Deque?

A `Deque` is useful when you need to work with **both ends** of a collection.

For example:

```text
Front → 10 20 30 40 ← Back
```

You can:

```text
add 5 to the front
add 50 to the back

remove from the front
remove from the back
```

This makes a `Deque` useful for:

- Queue operations
- Stack operations
- Sliding window problems
- Palindrome checking
- Undo/redo-style structures
- BFS-related algorithms
- Monotonic queue problems
- Work-stealing style designs

---

# 4. Basic Deque Operations

Create a Deque:

```java
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {

        Deque<Integer> deque = new ArrayDeque<>();

        deque.addFirst(20);
        deque.addLast(30);
        deque.addFirst(10);
        deque.addLast(40);

        System.out.println(deque);
    }
}
```

Conceptually:

```text
10 20 30 40
```

---

# 5. Head and Tail Operations

A Deque has two important ends:

```text
Head / Front
Tail / Back
```

Example:

```text
HEAD                         TAIL
 ↓                            ↓
10   20   30   40   50
```

You can perform operations independently on either end.

| End | Add | Remove | Inspect |
|---|---|---|---|
| Front | `addFirst()` | `removeFirst()` | `getFirst()` |
| Back | `addLast()` | `removeLast()` | `getLast()` |

There are also non-throwing variants:

| End | Add | Remove | Inspect |
|---|---|---|---|
| Front | `offerFirst()` | `pollFirst()` | `peekFirst()` |
| Back | `offerLast()` | `pollLast()` | `peekLast()` |

---

# 6. Deque Method Groups

The methods can be understood as three groups.

## 6.1 Insert

```text
addFirst()
addLast()

offerFirst()
offerLast()
```

---

## 6.2 Remove

```text
removeFirst()
removeLast()

pollFirst()
pollLast()
```

---

## 6.3 Inspect

```text
getFirst()
getLast()

peekFirst()
peekLast()
```

A useful pattern:

```text
add/remove/get
      vs
offer/poll/peek
```

The first group generally uses exception-based behavior for failure/empty cases.

The second group uses special return values such as `false` or `null`.

---

# 7. addFirst() and addLast()

## `addFirst()`

Adds an element at the front.

```java
deque.addFirst(10);
```

Example:

```text
Before:
20 30 40

addFirst(10)

After:
10 20 30 40
```

---

## `addLast()`

Adds an element at the back.

```java
deque.addLast(50);
```

Example:

```text
Before:
10 20 30 40

addLast(50)

After:
10 20 30 40 50
```

---

# 8. offerFirst() and offerLast()

These methods also insert elements at either end.

```java
deque.offerFirst(10);
deque.offerLast(50);
```

They return a boolean indicating whether insertion succeeded.

For an `ArrayDeque`, which is unbounded in its API, normal insertion does not ordinarily fail due to capacity.

Still, the methods follow the general `Deque` contract.

---

# 9. removeFirst() and removeLast()

## `removeFirst()`

Removes and returns the first element.

```java
int value = deque.removeFirst();
```

Example:

```text
Before:
10 20 30 40

removeFirst()

After:
20 30 40

Returned:
10
```

If the Deque is empty:

```text
NoSuchElementException
```

---

## `removeLast()`

Removes and returns the last element.

```java
int value = deque.removeLast();
```

Example:

```text
Before:
10 20 30 40

removeLast()

After:
10 20 30

Returned:
40
```

If the Deque is empty:

```text
NoSuchElementException
```

---

# 10. pollFirst() and pollLast()

These are safer removal methods when the Deque might be empty.

## `pollFirst()`

```java
deque.pollFirst();
```

Removes and returns the first element.

If empty:

```text
null
```

---

## `pollLast()`

```java
deque.pollLast();
```

Removes and returns the last element.

If empty:

```text
null
```

---

## Comparison

| Method | Empty Deque |
|---|---|
| `removeFirst()` | `NoSuchElementException` |
| `pollFirst()` | `null` |
| `removeLast()` | `NoSuchElementException` |
| `pollLast()` | `null` |

---

# 11. getFirst() and getLast()

These methods inspect the ends without removing elements.

## `getFirst()`

```java
deque.getFirst();
```

Returns the first element.

If empty:

```text
NoSuchElementException
```

---

## `getLast()`

```java
deque.getLast();
```

Returns the last element.

If empty:

```text
NoSuchElementException
```

---

# 12. peekFirst() and peekLast()

These methods inspect the ends without removing elements.

## `peekFirst()`

```java
deque.peekFirst();
```

Returns the first element.

If empty:

```text
null
```

---

## `peekLast()`

```java
deque.peekLast();
```

Returns the last element.

If empty:

```text
null
```

---

## Comparison

| Method | Removes? | Empty Deque |
|---|---|---|
| `getFirst()` | No | Exception |
| `peekFirst()` | No | `null` |
| `getLast()` | No | Exception |
| `peekLast()` | No | `null` |

---

# 13. Deque as a Queue

A `Deque` can be used as a normal FIFO queue.

FIFO means:

```text
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

Conceptually:

```text
offerLast()
     ↓
10 → 20 → 30
↑
pollFirst()
```

Therefore:

```text
Insert at back
Remove from front
```

gives FIFO behavior.

---

# 14. Deque as a Stack

A `Deque` can also be used as a stack.

Stack behavior is:

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

`Deque` provides:

```text
push() → addFirst()
pop()  → removeFirst()
peek() → peekFirst()
```

This makes `Deque` a modern alternative to the legacy `Stack` class for stack behavior.

---

# 15. FIFO vs LIFO

The same `Deque` can behave differently depending on which methods you use.

## FIFO Queue

```java
deque.offerLast(value);
deque.pollFirst();
```

Behavior:

```text
First In → First Out
```

---

## LIFO Stack

```java
deque.push(value);
deque.pop();
```

Behavior:

```text
Last In → First Out
```

---

## Visual Comparison

### FIFO

```text
Insert:

10 → 20 → 30

Remove:

10 → 20 → 30
```

### LIFO

```text
Insert:

10 → 20 → 30

Remove:

30 → 20 → 10
```

---

# 16. Implementations of Deque

Two common implementations are:

```text
ArrayDeque
LinkedList
```

---

## 16.1 ArrayDeque

Example:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

`ArrayDeque` is generally the preferred implementation when you need a general-purpose `Deque`.

It is backed by a resizable array.

Advantages:

- Efficient operations at both ends
- Good general-purpose performance
- Can be used as Queue
- Can be used as Stack
- Usually preferred over `Stack` for stack behavior

It does not allow `null`.

---

## 16.2 LinkedList

`LinkedList` also implements `Deque`.

```java
Deque<Integer> deque = new LinkedList<>();
```

It can perform operations at both ends.

However, if you specifically need a Deque and do not need `LinkedList`-specific behavior, `ArrayDeque` is often the better default choice.

---

# 17. Deque vs Queue

| Feature | Queue | Deque |
|---|---|---|
| Main purpose | Queue processing | Double-ended processing |
| Insert front | No | Yes |
| Insert back | Yes | Yes |
| Remove front | Yes | Yes |
| Remove back | No | Yes |
| FIFO | Yes | Can support |
| LIFO | Not the primary use | Can support |
| Example | `PriorityQueue` | `ArrayDeque` |

Important:

```text
Deque extends Queue
```

So every Deque is a Queue, but a Queue reference does not expose the complete double-ended API.

---

# 18. Deque vs Stack

| Feature | Deque | Stack |
|---|---|---|
| Type | Interface | Legacy class |
| LIFO | Yes | Yes |
| FIFO | Yes | No |
| Both-end operations | Yes | No |
| Modern choice for stack | Yes | Usually no |
| Common implementation | `ArrayDeque` | `Stack` |

For normal stack behavior, prefer:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

instead of:

```java
Stack<Integer> stack = new Stack<>();
```

---

# 19. Deque vs PriorityQueue

These collections solve different problems.

| Feature | Deque | PriorityQueue |
|---|---|---|
| Main behavior | Double-ended | Priority-based |
| FIFO | Yes | No |
| LIFO | Yes | No |
| Heap-based | No | Yes |
| Access both ends | Yes | No |
| Priority ordering | No | Yes |
| Allows duplicates | Yes | Yes |
| Allows null | Depends on implementation; `ArrayDeque` does not | No |

Example:

Use `Deque` when:

```text
Need first/last operations
```

Use `PriorityQueue` when:

```text
Need highest-priority element
```

---

# 20. Null Elements

The `Deque` interface itself does not universally prohibit `null` for every possible implementation, but `null` handling depends on the implementation.

For `ArrayDeque`:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.add(null);
```

results in:

```text
NullPointerException
```

Therefore:

> Do not use `null` elements with `ArrayDeque`.

This is important because `null` is also used by methods such as `poll()` and `peek()` to indicate an empty Deque.

---

# 21. Duplicates

Deques generally allow duplicate elements.

Example:

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.addLast(10);
deque.addLast(10);
deque.addLast(20);
```

Contents can be:

```text
10 10 20
```

There is no automatic duplicate removal.

---

# 22. Iteration

A Deque can be traversed using a for-each loop.

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

## Reverse Iteration

A Deque also provides:

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

This traverses from the tail toward the head.

---

# 23. Time Complexity

For `ArrayDeque`, operations at either end are designed to be efficient, typically `O(1)` amortized.

| Operation | ArrayDeque |
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
| `size()` | `O(1)` |
| `contains()` | `O(n)` |

### Important

`ArrayDeque` uses a resizable array, so insertion may occasionally require resizing.

Therefore, insertion at either end is generally described as:

```text
O(1) amortized
```

---

# 24. Real-World Applications

## 24.1 Queue Processing

Use:

```java
Deque<Task> queue = new ArrayDeque<>();
```

Process:

```text
First task → First processed
```

---

## 24.2 Stack Operations

Use:

```java
Deque<String> stack = new ArrayDeque<>();
```

Useful for:

- Parentheses matching
- Expression processing
- DFS
- Undo operations
- Backtracking

---

## 24.3 Palindrome Checking

A Deque allows comparison from both ends.

Example:

```text
MADAM
```

Compare:

```text
M ↔ M
A ↔ A
D
```

If all matching pairs are equal, the string is a palindrome.

---

## 24.4 Sliding Window

Deque is extremely useful for sliding-window problems.

Example:

```text
Find maximum element in every window of size K
```

A monotonic Deque can solve this efficiently in:

```text
O(n)
```

---

## 24.5 BFS

A Deque can be used as a queue:

```java
Deque<Integer> queue = new ArrayDeque<>();

queue.offerLast(start);
int node = queue.pollFirst();
```

This supports FIFO traversal.

---

## 24.6 Work Scheduling

A Deque can support processing from either end.

For example:

```text
Front → urgent tasks
Back  → normal tasks
```

The exact scheduling policy depends on the application.

---

# 25. Important Interview Points

### Point 1

`Deque` means:

```text
Double-Ended Queue
```

---

### Point 2

`Deque` is an interface.

---

### Point 3

It extends:

```text
Queue
```

---

### Point 4

It supports operations at both ends.

---

### Point 5

It can act as a:

```text
Queue → FIFO
Stack → LIFO
```

---

### Point 6

`ArrayDeque` is a common and efficient implementation.

---

### Point 7

`ArrayDeque` does not allow `null`.

---

### Point 8

Duplicates are allowed.

---

### Point 9

`Deque` is not the same as `PriorityQueue`.

```text
Deque        → end-based access
PriorityQueue → priority-based access
```

---

### Point 10

For stack behavior, `ArrayDeque` is generally preferred over legacy `Stack`.

---

# 26. Common Mistakes

## Mistake 1 — Thinking Deque means only Queue

Wrong.

Deque supports both ends.

---

## Mistake 2 — Confusing Deque with PriorityQueue

They are different.

```text
Deque:
front/back

PriorityQueue:
priority
```

---

## Mistake 3 — Using `removeFirst()` without considering empty state

```java
deque.removeFirst();
```

can throw:

```text
NoSuchElementException
```

If empty is expected, consider:

```java
deque.pollFirst();
```

---

## Mistake 4 — Using `getFirst()` when empty is possible

```java
deque.getFirst();
```

throws an exception on an empty Deque.

Use:

```java
deque.peekFirst();
```

if you want `null` instead.

---

## Mistake 5 — Assuming `ArrayDeque` allows null

It does not.

```java
deque.add(null);
```

throws `NullPointerException`.

---

## Mistake 6 — Using Stack when Deque is enough

Instead of:

```java
Stack<Integer> stack = new Stack<>();
```

prefer:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

for typical LIFO stack behavior.

---

# 27. Quick Revision

## Definition

```text
Deque = Double-Ended Queue
```

---

## Hierarchy

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

## Add

```text
addFirst()
addLast()

offerFirst()
offerLast()
```

---

## Remove

```text
removeFirst()
removeLast()

pollFirst()
pollLast()
```

---

## Inspect

```text
getFirst()
getLast()

peekFirst()
peekLast()
```

---

## Stack Operations

```text
push()
pop()
peek()
```

---

## FIFO Queue

```text
offerLast()
pollFirst()
```

---

## LIFO Stack

```text
push()
pop()
```

---

## Common Implementation

```java
Deque<Integer> deque = new ArrayDeque<>();
```

---

## Null

```text
ArrayDeque → No null
```

---

## Duplicates

```text
Allowed
```

---

## Complexity

```text
End operations → O(1) amortized
contains()      → O(n)
```

---

# 28. Final Mental Model

Think of a Deque as a line where **both ends are open**.

```text
                  FRONT
                    ↓
              ┌───────────┐
              │ 10 20 30  │
              └───────────┘
                    ↑
                   BACK
```

You can:

```text
add/remove → FRONT
add/remove → BACK
```

So:

```text
Deque
 │
 ├── Queue behavior
 │      offerLast()
 │      pollFirst()
 │
 └── Stack behavior
        push()
        pop()
```

The key idea is:

> **Deque gives you efficient access to both ends, and can be used as either a Queue or a Stack.**

---

# 29. Progress

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
│   ├── PRACTICE.md    [ ]
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
