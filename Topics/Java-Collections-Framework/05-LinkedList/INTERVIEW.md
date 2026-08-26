# LinkedList - Interview Questions

> Interview-focused revision of `LinkedList`, covering internal working, complexity, `ArrayList` comparison, `Deque` behavior, tricky questions, code-output problems, and real-world scenarios.

---

## Table of Contents

- [1. What is LinkedList?](#1-what-is-linkedlist)
- [2. LinkedList Hierarchy](#2-linkedlist-hierarchy)
- [3. Why is LinkedList Doubly Linked?](#3-why-is-linkedlist-doubly-linked)
- [4. Internal Working](#4-internal-working)
- [5. Node Structure](#5-node-structure)
- [6. Head and Tail](#6-head-and-tail)
- [7. Why is get O(n)?](#7-why-is-get-on)
- [8. Why are First and Last Operations O(1)?](#8-why-are-first-and-last-operations-o1)
- [9. Is Insertion O(1)?](#9-is-insertion-o1)
- [10. Time Complexity](#10-time-complexity)
- [11. ArrayList vs LinkedList](#11-arraylist-vs-linkedlist)
- [12. LinkedList vs ArrayDeque](#12-linkedlist-vs-arraydeque)
- [13. Memory Overhead](#13-memory-overhead)
- [14. Cache Locality](#14-cache-locality)
- [15. Queue Operations](#15-queue-operations)
- [16. Deque Operations](#16-deque-operations)
- [17. Stack Operations](#17-stack-operations)
- [18. Iterator](#18-iterator)
- [19. ListIterator](#19-listiterator)
- [20. Fail-Fast Behavior](#20-fail-fast-behavior)
- [21. Null and Duplicates](#21-null-and-duplicates)
- [22. Thread Safety](#22-thread-safety)
- [23. Common Traps](#23-common-traps)
- [24. Tricky Output Questions](#24-tricky-output-questions)
- [25. Scenario-Based Questions](#25-scenario-based-questions)
- [26. Rapid-Fire Questions](#26-rapid-fire-questions)
- [27. Must-Know Interview Questions](#27-must-know-interview-questions)
- [28. Final Interview Answer](#28-final-interview-answer)
- [29. Final Checklist](#29-final-checklist)

---

# 1. What is LinkedList?

## Question

What is `LinkedList` in Java?

## Answer

`LinkedList` is a doubly linked list implementation of the `List` and `Deque` interfaces in the Java Collections Framework.

It stores elements in linked nodes rather than in a contiguous array.

Conceptually:

```text
[10] <-> [20] <-> [30] <-> [40]
```

Each node maintains references to neighboring nodes.

---

# 2. LinkedList Hierarchy

A simplified inheritance hierarchy is:

```text
Object
   |
AbstractCollection
   |
AbstractList
   |
AbstractSequentialList
   |
LinkedList
```

It implements:

```text
List
Deque
Queue
Cloneable
Serializable
```

Conceptually:

```text
Iterable
    |
Collection
    |
   +------------------+
   |                  |
  List              Queue
   |                  |
   |                 Deque
   |                  |
   +--------+---------+
            |
       LinkedList
```

---

# 3. Why is LinkedList Doubly Linked?

## Question

Why is Java's `LinkedList` called a doubly linked list?

## Answer

Each node conceptually contains:

```text
previous reference
element
next reference
```

For example:

```text
       prev        next
        |           |
        v           v
+-------+-------+-------+
| prev  |  20   | next  |
+-------+-------+-------+
```

The nodes are connected in both directions:

```text
10 <-> 20 <-> 30
```

This allows traversal:

```text
Forward:
10 -> 20 -> 30

Backward:
30 -> 20 -> 10
```

---

# 4. Internal Working

## Question

How does `LinkedList` work internally?

Conceptually, it maintains a chain of nodes:

```text
first
  |
  v
[10] <-> [20] <-> [30] <-> [40]
                                  ^
                                  |
                                 last
```

Each node stores:

```text
prev
item
next
```

When a new node is inserted, the links between neighboring nodes are updated.

For example, inserting `25` between `20` and `30`:

```text
Before:

20 <-> 30
```

After:

```text
20 <-> 25 <-> 30
```

Only the relevant links need to be changed once the correct position/node has been located.

---

# 5. Node Structure

A conceptual implementation is:

```java
class Node<E> {

    E item;

    Node<E> next;

    Node<E> prev;
}
```

For:

```text
10 <-> 20 <-> 30
```

the structure is conceptually:

```text
null <- [10] <-> [20] <-> [30] -> null
```

The first node has no previous node.

The last node has no next node.

---

# 6. Head and Tail

A linked list conceptually maintains references to its first and last nodes.

```text
first
  |
  v
[10] <-> [20] <-> [30]
                    ^
                    |
                   last
```

This is why operations at both ends can be efficient.

For example:

```java
list.addFirst(5);
list.addLast(40);
```

do not require traversing the entire list.

---

# 7. Why is get O(n)?

## Question

Why is:

```java
list.get(index)
```

O(n) for `LinkedList`?

## Answer

Because `LinkedList` does not provide array-style direct access.

Consider:

```text
[10] <-> [20] <-> [30] <-> [40] <-> [50]
```

To get index `3`, the implementation must locate the corresponding node.

It can traverse from either end, but it still has to traverse nodes.

Therefore:

```text
get(index) -> O(n)
```

---

## Optimization

Because the list is doubly linked, traversal can begin from whichever end is closer.

Conceptually:

```text
index near beginning
    -> start from first

index near end
    -> start from last
```

This reduces the number of nodes traversed in practice.

The general Big-O classification remains:

```text
O(n)
```

---

# 8. Why are First and Last Operations O(1)?

## Question

Why are:

```java
addFirst()
removeFirst()
addLast()
removeLast()
```

O(1)?

## Answer

Because the list maintains references to both ends.

Example:

```text
first
 |
 v
[10] <-> [20] <-> [30]
                    ^
                    |
                   last
```

To add `5` at the beginning:

```text
[5] <-> [10] <-> [20] <-> [30]
```

Only a few references need to be updated.

No traversal is required.

Therefore:

```text
addFirst()    -> O(1)
removeFirst() -> O(1)
addLast()     -> O(1)
removeLast()  -> O(1)
```

---

# 9. Is Insertion O(1)?

## Question

Is insertion into a `LinkedList` O(1)?

## Answer

This question needs a precise answer.

### Once the node/position is known

The actual linking operation can be:

```text
O(1)
```

### Finding an arbitrary position

If you have:

```java
list.add(index, value);
```

the implementation generally needs to locate that position first.

That can take:

```text
O(n)
```

Therefore:

```text
add(index, value)
    -> O(n)
```

in general.

---

## Interview-Friendly Answer

> Linked-list insertion is O(1) when the insertion location/node is already known, but locating an arbitrary index can take O(n). Therefore, indexed insertion in Java's `LinkedList` is generally O(n).

---

# 10. Time Complexity

| Operation | Complexity |
|---|---:|
| `get(index)` | O(n) |
| `set(index, value)` | O(n) |
| `add(value)` | O(1) |
| `addFirst()` | O(1) |
| `addLast()` | O(1) |
| `removeFirst()` | O(1) |
| `removeLast()` | O(1) |
| `add(index, value)` | O(n) |
| `remove(index)` | O(n) |
| `remove(Object)` | O(n) |
| `contains()` | O(n) |
| `indexOf()` | O(n) |
| `lastIndexOf()` | O(n) |
| `peekFirst()` | O(1) |
| `peekLast()` | O(1) |
| `pollFirst()` | O(1) |
| `pollLast()` | O(1) |
| `size()` | O(1) |

---

# 11. ArrayList vs LinkedList

This is one of the most frequently asked Java Collections questions.

| Feature | ArrayList | LinkedList |
|---|---|---|
| Internal structure | Dynamic array | Doubly linked list |
| Random access | Fast | Slow |
| `get(index)` | O(1) | O(n) |
| `set(index)` | O(1) | O(n) |
| Add at end | O(1) amortized | O(1) |
| Add first | O(n) | O(1) |
| Remove first | O(n) | O(1) |
| Indexed insertion | O(n) | O(n) |
| Indexed removal | O(n) | O(n) |
| Search | O(n) | O(n) |
| Memory overhead | Lower | Higher |
| Cache locality | Better | Worse |
| Implements `Deque` | No | Yes |

---

## Which One Should You Choose?

### Choose ArrayList when:

- You need frequent indexed access.
- You mostly append elements.
- You frequently iterate over the list.
- Memory efficiency matters.
- You need a general-purpose `List`.

### Choose LinkedList when:

- You specifically need linked-list behavior.
- You need `List` and `Deque` behavior from the same object.
- You frequently operate at both ends.
- The workload benefits from node-based operations.

---

## Important

Do not answer:

> LinkedList is better for insertion.

A better answer is:

> LinkedList can modify links in O(1) once the relevant node is known, but finding an arbitrary position can take O(n). Therefore, indexed insertion is generally O(n).

---

# 12. LinkedList vs ArrayDeque

Both implement `Deque`.

| Feature | LinkedList | ArrayDeque |
|---|---|---|
| Internal structure | Doubly linked nodes | Resizable circular array |
| Implements `Deque` | Yes | Yes |
| Add/remove ends | O(1) | O(1) amortized |
| Memory overhead | Higher | Lower |
| Cache locality | Worse | Better |
| Allows `null` | Yes | No |
| Random access | O(n) | Not supported |
| Typical deque choice | Less common | Usually preferred |

---

## Interview Answer

If asked:

> Which should I use for a stack or deque?

A strong answer is:

> `ArrayDeque` is generally preferred for typical single-threaded stack and deque use cases because it avoids per-node overhead and usually has better cache locality. `LinkedList` is useful when its `List` behavior or ability to store `null` is specifically required.

---

# 13. Memory Overhead

## Question

Why does LinkedList use more memory than ArrayList?

Consider:

```text
ArrayList:

[ref][ref][ref][ref]
```

The backing array mainly stores references to elements.

For `LinkedList`:

```text
[prev | item | next]
[prev | item | next]
[prev | item | next]
```

Each node has:

- Object overhead
- Element reference
- Previous reference
- Next reference

Therefore:

```text
LinkedList
    -> more memory overhead
```

---

# 14. Cache Locality

`ArrayList` stores references in a contiguous array.

Conceptually:

```text
[ref][ref][ref][ref][ref]
```

This generally provides better cache locality.

`LinkedList` nodes may be located at different memory locations:

```text
Node A ----> Node B ----> Node C
```

The CPU may need to follow pointers from one object to another.

Therefore:

```text
ArrayList
    -> generally better cache locality

LinkedList
    -> generally worse cache locality
```

This is an important reason why theoretical linked-list advantages do not always translate into better real-world performance.

---

# 15. Queue Operations

Because `LinkedList` implements `Queue`, it can be used as a FIFO queue.

Example:

```java
Queue<Integer> queue =
        new LinkedList<>();
```

Add:

```java
queue.offer(10);
queue.offer(20);
queue.offer(30);
```

Queue:

```text
10 -> 20 -> 30
```

Remove:

```java
queue.poll();
```

returns:

```text
10
```

Remaining:

```text
20 -> 30
```

---

## Important Queue Methods

| Method | Behavior |
|---|---|
| `offer()` | Adds element |
| `poll()` | Removes front, returns `null` if empty |
| `peek()` | Views front, returns `null` if empty |
| `remove()` | Removes front, throws if empty |
| `element()` | Views front, throws if empty |

---

# 16. Deque Operations

Because `LinkedList` implements `Deque`, it supports operations at both ends.

```java
Deque<Integer> deque =
        new LinkedList<>();
```

### Add

```java
deque.addFirst(10);
deque.addLast(20);
```

Result:

```text
[10, 20]
```

### Remove

```java
deque.removeFirst();
deque.removeLast();
```

### Inspect

```java
deque.peekFirst();
deque.peekLast();
```

---

# 17. Stack Operations

A `Deque` can represent a stack.

```java
Deque<Integer> stack =
        new LinkedList<>();
```

Push:

```java
stack.push(10);
stack.push(20);
stack.push(30);
```

Conceptually:

```text
30 <- top
20
10
```

Pop:

```java
stack.pop();
```

returns:

```text
30
```

---

## Important

Although this works:

```java
Deque<Integer> stack =
        new LinkedList<>();
```

prefer:

```java
Deque<Integer> stack =
        new ArrayDeque<>();
```

for most modern stack use cases.

---

# 18. Iterator

`LinkedList` supports `Iterator`.

Example:

```java
Iterator<Integer> iterator =
        list.iterator();

while (iterator.hasNext()) {

    Integer value =
            iterator.next();

    System.out.println(value);
}
```

---

## Safe Removal

```java
Iterator<Integer> iterator =
        list.iterator();

while (iterator.hasNext()) {

    Integer value =
            iterator.next();

    if (value % 2 == 0) {
        iterator.remove();
    }
}
```

This is the correct iterator-based removal pattern.

---

# 19. ListIterator

`ListIterator` provides bidirectional traversal.

```java
ListIterator<Integer> iterator =
        list.listIterator();
```

Forward:

```java
while (iterator.hasNext()) {
    System.out.println(
            iterator.next()
    );
}
```

Backward:

```java
while (iterator.hasPrevious()) {
    System.out.println(
            iterator.previous()
    );
}
```

It also supports:

```java
add()
set()
remove()
```

---

# 20. Fail-Fast Behavior

`LinkedList` iterators are generally fail-fast.

Example:

```java
LinkedList<Integer> list =
        new LinkedList<>(
                List.of(
                        10, 20, 30
                )
        );

for (Integer value : list) {

    if (value == 20) {
        list.add(40);
    }
}
```

This can result in:

```text
ConcurrentModificationException
```

---

## Important

`ConcurrentModificationException` does **not** necessarily mean multiple threads are involved.

It can occur in a single thread when a collection is structurally modified while being traversed using an iterator/enhanced `for` loop.

---

# 21. Null and Duplicates

## Can LinkedList contain duplicates?

Yes.

```java
list.add(10);
list.add(10);
```

Result:

```text
[10, 10]
```

---

## Can LinkedList contain null?

Yes.

```java
list.add(null);
```

is valid.

---

# 22. Thread Safety

## Question

Is LinkedList thread-safe?

### Answer

No.

`LinkedList` is not synchronized by default.

This:

```java
LinkedList<Integer> list =
        new LinkedList<>();
```

does not automatically make concurrent modifications safe.

For concurrent programs, select an appropriate concurrent data structure or synchronization mechanism based on the use case.

---

# 23. Common Traps

## Trap 1: `get()` is O(1)

Wrong.

```text
ArrayList.get(index)  -> O(1)

LinkedList.get(index) -> O(n)
```

---

## Trap 2: Every LinkedList insertion is O(1)

Wrong.

The actual link update can be O(1), but locating the insertion position may take O(n).

---

## Trap 3: LinkedList is always better for insertion

Wrong.

Performance depends on:

- Where the insertion occurs
- Whether the node is already known
- How the list is accessed
- How often indexed access occurs
- Memory/cache behavior

---

## Trap 4: LinkedList uses an array

Wrong.

Conceptually:

```text
ArrayList  -> backing array
LinkedList -> linked nodes
```

---

## Trap 5: LinkedList is thread-safe

Wrong.

It is not thread-safe by default.

---

## Trap 6: ArrayDeque allows null

Wrong.

`ArrayDeque` does not permit `null` elements.

`LinkedList` does.

---

# 24. Tricky Output Questions

## Question 1

What is the output?

```java
LinkedList<Integer> list =
        new LinkedList<>();

list.add(10);
list.add(20);
list.add(30);

System.out.println(list);
```

### Answer

```text
[10, 20, 30]
```

---

## Question 2

What is the output?

```java
LinkedList<Integer> list =
        new LinkedList<>(
                List.of(10, 20, 30)
        );

list.addFirst(5);

System.out.println(list);
```

### Answer

```text
[5, 10, 20, 30]
```

---

## Question 3

What is the output?

```java
LinkedList<Integer> list =
        new LinkedList<>(
                List.of(10, 20, 30)
        );

list.addLast(40);

System.out.println(list);
```

### Answer

```text
[10, 20, 30, 40]
```

---

## Question 4

What is the output?

```java
LinkedList<Integer> list =
        new LinkedList<>(
                List.of(10, 20, 30)
        );

System.out.println(
        list.removeFirst()
);

System.out.println(list);
```

### Answer

```text
10
[20, 30]
```

---

## Question 5

What is the output?

```java
LinkedList<Integer> list =
        new LinkedList<>(
                List.of(10, 20, 30)
        );

System.out.println(
        list.peekFirst()
);

System.out.println(list);
```

### Answer

```text
10
[10, 20, 30]
```

`peekFirst()` does not remove the element.

---

## Question 6

What is the output?

```java
Deque<Integer> deque =
        new LinkedList<>();

deque.addFirst(20);
deque.addFirst(10);
deque.addLast(30);

System.out.println(deque);
```

### Answer

```text
[10, 20, 30]
```

---

## Question 7

What is the output?

```java
Queue<Integer> queue =
        new LinkedList<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);

System.out.println(queue.poll());
System.out.println(queue);
```

### Answer

```text
10
[20, 30]
```

---

## Question 8

What is the output?

```java
Deque<Integer> stack =
        new LinkedList<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.pop());
```

### Answer

```text
30
```

This demonstrates LIFO behavior.

---

## Question 9

What is the output?

```java
LinkedList<Integer> list =
        new LinkedList<>(
                List.of(10, 20, 30)
        );

list.remove(Integer.valueOf(20));

System.out.println(list);
```

### Answer

```text
[10, 30]
```

This removes by value.

---

## Question 10

What happens?

```java
LinkedList<Integer> list =
        new LinkedList<>(
                List.of(10, 20, 30)
        );

for (Integer value : list) {

    if (value == 20) {
        list.remove(value);
    }
}
```

### Answer

It can throw:

```text
ConcurrentModificationException
```

because the list is structurally modified during iteration.

---

# 25. Scenario-Based Questions

## Scenario 1

You need frequent:

```java
get(index)
```

Which collection?

### Answer

```java
ArrayList
```

---

## Scenario 2

You frequently add and remove elements from the beginning.

Which collection supports that efficiently?

### Answer

`LinkedList` supports:

```java
addFirst()
removeFirst()
```

in O(1).

However, if the structure is primarily a deque, also consider:

```java
ArrayDeque
```

---

## Scenario 3

You need a general-purpose list.

Which should generally be your first choice?

### Answer

```java
ArrayList
```

unless the workload specifically favors another implementation.

---

## Scenario 4

You need a stack.

Which is preferred?

### Answer

```java
ArrayDeque
```

rather than the legacy `Stack` class.

---

## Scenario 5

You need a queue/deque.

Which is generally preferred?

### Answer

```java
ArrayDeque
```

for typical non-concurrent use.

---

## Scenario 6

You need to store `null` in a deque.

Which can do it?

```text
LinkedList
```

`ArrayDeque` does not permit `null`.

---

## Scenario 7

You need to traverse from both directions.

Which Java list supports:

```java
ListIterator
```

for bidirectional traversal?

### Answer

Both `ArrayList` and `LinkedList` support `ListIterator`.

However, the underlying access behavior differs.

---

## Scenario 8

You have a known node reference and need to insert another node next to it in your own linked-list implementation.

What is the complexity?

### Answer

The actual pointer manipulation can be:

```text
O(1)
```

assuming the relevant node is already known.

---

# 26. Rapid-Fire Questions

## 1. What is LinkedList?

A doubly linked list implementation of `List` and `Deque`.

## 2. Which package?

```java
java.util
```

## 3. Does it maintain insertion order?

Yes.

## 4. Does it allow duplicates?

Yes.

## 5. Does it allow null?

Yes.

## 6. Is it thread-safe?

No.

## 7. What is `get(index)`?

```text
O(n)
```

## 8. What is `addFirst()`?

```text
O(1)
```

## 9. What is `addLast()`?

```text
O(1)
```

## 10. What is `removeFirst()`?

```text
O(1)
```

## 11. What is `removeLast()`?

```text
O(1)
```

## 12. What is indexed insertion?

Generally:

