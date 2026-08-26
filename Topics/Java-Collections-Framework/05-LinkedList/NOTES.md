# LinkedList in Java

> `LinkedList` is a doubly linked list implementation of the `List` and `Deque` interfaces. It is important for understanding linked-list internals, node traversal, insertion/removal, and the difference between positional access and node-based operations.

---

## Table of Contents

- [1. Introduction](#1-introduction)
- [2. What is LinkedList?](#2-what-is-linkedlist)
- [3. LinkedList Hierarchy](#3-linkedlist-hierarchy)
- [4. Why LinkedList is Important](#4-why-linkedlist-is-important)
- [5. Creating a LinkedList](#5-creating-a-linkedlist)
- [6. Internal Structure](#6-internal-structure)
- [7. Node Structure](#7-node-structure)
- [8. Why Doubly Linked List](#8-why-doubly-linked-list)
- [9. Head and Tail](#9-head-and-tail)
- [10. Adding Elements](#10-adding-elements)
- [11. Adding at Beginning](#11-adding-at-beginning)
- [12. Adding at End](#12-adding-at-end)
- [13. Adding at an Index](#13-adding-at-an-index)
- [14. Accessing Elements](#14-accessing-elements)
- [15. Why get() is O(n)](#15-why-get-is-on)
- [16. Updating Elements](#16-updating-elements)
- [17. Removing Elements](#17-removing-elements)
- [18. Removing First and Last](#18-removing-first-and-last)
- [19. Searching](#19-searching)
- [20. Duplicates and null](#20-duplicates-and-null)
- [21. Ordering](#21-ordering)
- [22. Iteration](#22-iteration)
- [23. Iterator](#23-iterator)
- [24. ListIterator](#24-listiterator)
- [25. Deque Operations](#25-deque-operations)
- [26. Queue Operations](#26-queue-operations)
- [27. Stack-like Operations](#27-stack-like-operations)
- [28. Time Complexity](#28-time-complexity)
- [29. ArrayList vs LinkedList](#29-arraylist-vs-linkedlist)
- [30. LinkedList vs ArrayDeque](#30-linkedlist-vs-arraydeque)
- [31. Memory Overhead](#31-memory-overhead)
- [32. Thread Safety](#32-thread-safety)
- [33. Fail-Fast Behavior](#33-fail-fast-behavior)
- [34. Common Mistakes](#34-common-mistakes)
- [35. When Should You Use LinkedList?](#35-when-should-you-use-linkedlist)
- [36. Important Methods](#36-important-methods)
- [37. Interview Focus](#37-interview-focus)
- [38. Quick Revision](#38-quick-revision)
- [39. Final Mental Model](#39-final-mental-model)

---

# 1. Introduction

`LinkedList` is a class in:

```java
java.util
```

It implements both:

```java
List
```

and:

```java
Deque
```

This makes it different from `ArrayList`.

A simplified view:

```text
LinkedList
    |
    +-- List
    |
    +-- Deque
```

Because it implements `Deque`, a `LinkedList` can be used as:

- A list
- A queue
- A deque
- A stack-like structure

However, being capable of these operations does not mean it is always the best implementation for them.

---

# 2. What is LinkedList?

`LinkedList` is a **doubly linked list** implementation.

Instead of storing elements in one contiguous backing array like `ArrayList`, it stores elements in separate nodes connected to one another.

Conceptually:

```text
[10] <-> [20] <-> [30] <-> [40]
```

Each node contains:

```text
previous reference
element
next reference
```

Conceptually:

```text
+---------+---------+---------+
| prev    | value   | next    |
+---------+---------+---------+
```

---

# 3. LinkedList Hierarchy

The inheritance hierarchy is approximately:

```text
Object
   |
AbstractCollection
   |
AbstractSequentialList
   |
LinkedList
```

Through interfaces:

```text
Iterable
    |
Collection
    |
List
    |
LinkedList
```

And:

```text
Deque
    |
Queue
```

`LinkedList` also implements:

```text
Cloneable
Serializable
```

A simplified view:

```text
                   Iterable
                      |
                  Collection
                  /        \
                List      Queue
                  |          |
                  +----------+
                       |
                     Deque
                       |
                       v
                  LinkedList
```

---

# 4. Why LinkedList is Important

Understanding `LinkedList` helps explain:

- Linked-list data structures
- Nodes
- References
- Doubly linked lists
- Traversal
- Insertion
- Removal
- Positional access
- Queue operations
- Deque operations
- Why `ArrayList` and `LinkedList` have different performance characteristics

It is also a common interview topic.

---

# 5. Creating a LinkedList

## 5.1 Empty LinkedList

```java
LinkedList<Integer> numbers =
        new LinkedList<>();
```

---

## 5.2 Using the List Interface

Prefer:

```java
List<Integer> numbers =
        new LinkedList<>();
```

when you only need `List` operations.

---

## 5.3 Using the Deque Interface

If you specifically need deque behavior:

```java
Deque<Integer> numbers =
        new LinkedList<>();
```

This makes the intended abstraction clearer.

---

## 5.4 Creating from Another Collection

```java
List<Integer> source =
        List.of(10, 20, 30);

LinkedList<Integer> numbers =
        new LinkedList<>(source);
```

Result:

```text
[10, 20, 30]
```

---

# 6. Internal Structure

Unlike `ArrayList`, `LinkedList` does not use a dynamic array for storing the elements.

Conceptually:

```text
LinkedList

head
 |
 v
[10] <-> [20] <-> [30] <-> [40]
                                  ^
                                  |
                                 tail
```

Each element is stored inside a node.

The nodes are connected using references.

---

# 7. Node Structure

A conceptual doubly linked-list node looks like:

```java
class Node<E> {

    E item;

    Node<E> next;

    Node<E> prev;
}
```

So a node contains:

```text
+-----------------------------+
| previous | value | next     |
+-----------------------------+
```

Example:

```text
       prev       next
        |          |
        v          v
    +-------+-------+-------+
    | prev  |  20   | next  |
    +-------+-------+-------+
```

The actual JDK implementation uses its own private internal node representation.

The above is a conceptual model.

---

# 8. Why Doubly Linked List?

A doubly linked list stores references in both directions.

Example:

```text
[10] <-> [20] <-> [30]
```

For node `20`:

```text
prev -> 10
next -> 30
```

This allows traversal in both directions.

```text
Forward:

10 -> 20 -> 30

Backward:

30 -> 20 -> 10
```

This is useful for:

- `ListIterator`
- Removing a known node
- Traversing from either end

---

# 9. Head and Tail

A linked list conceptually maintains references to:

```text
first node
last node
```

For example:

```text
head
 |
 v
[10] <-> [20] <-> [30]
                    ^
                    |
                   tail
```

Because the list knows both ends, operations at the beginning and end can be very efficient.

---

# 10. Adding Elements

The basic operation is:

```java
add(E element)
```

Example:

```java
LinkedList<Integer> numbers =
        new LinkedList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

Adding at the end is efficient.

---

# 11. Adding at Beginning

Use:

```java
addFirst()
```

Example:

```java
LinkedList<Integer> numbers =
        new LinkedList<>(
                List.of(20, 30)
        );

numbers.addFirst(10);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

Conceptually:

```text
Before:

[20] <-> [30]

After:

[10] <-> [20] <-> [30]
```

The new node becomes the first node.

---

# 12. Adding at End

Use:

```java
addLast()
```

Example:

```java
LinkedList<Integer> numbers =
        new LinkedList<>(
                List.of(10, 20)
        );

numbers.addLast(30);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

`add(E)` also adds to the end.

Therefore:

```java
numbers.add(30);
```

is equivalent in intent to:

```java
numbers.addLast(30);
```

---

# 13. Adding at an Index

You can use:

```java
add(index, element)
```

Example:

```java
LinkedList<Integer> numbers =
        new LinkedList<>(
                List.of(
                        10, 20, 40
                )
        );

numbers.add(2, 30);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30, 40]
```

---

## Important Complexity Point

Do not simply say:

> `LinkedList.add(index, value)` is O(1).

That is incomplete.

The list may first need to locate the position.

Finding the node can take:

```text
O(n)
```

After the node is located, linking the new node itself is:

```text
O(1)
```

Therefore indexed insertion is generally:

```text
O(n)
```

---

# 14. Accessing Elements

Use:

```java
get(index)
```

Example:

```java
LinkedList<String> languages =
        new LinkedList<>(
                List.of(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );

System.out.println(
        languages.get(1)
);
```

Output:

```text
Spring
```

However, unlike `ArrayList`, this is not O(1).

---

# 15. Why get() is O(n)

This is one of the most important LinkedList interview questions.

Consider:

```text
[10] <-> [20] <-> [30] <-> [40] <-> [50]
```

Suppose you request:

```java
list.get(4);
```

The implementation needs to locate the corresponding node.

It cannot directly calculate the memory location using the index like an array-backed list.

It must traverse the linked structure.

Therefore:

```text
get(index) -> O(n)
```

---

## But There Is an Optimization

Because `LinkedList` is doubly linked, it can choose the closer end.

For example:

```text
[10] <-> [20] <-> [30] <-> [40] <-> [50]
 ^                               ^
 first                           last
```

If you request an element near the beginning, traversal can start from the first node.

If you request an element near the end, traversal can start from the last node.

So the implementation can reduce traversal to approximately:

```text
O(min(index, n - index))
```

in terms of node traversal.

But the standard Big-O classification for indexed access remains:

```text
O(n)
```

---

# 16. Updating Elements

Use:

```java
set(index, value)
```

Example:

```java
LinkedList<String> languages =
        new LinkedList<>(
                List.of(
                        "Java",
                        "Python",
                        "SQL"
                )
        );

languages.set(1, "Spring");

System.out.println(languages);
```

Output:

```text
[Java, Spring, SQL]
```

Because the implementation must locate the node first:

```text
set(index, value) -> O(n)
```

---

# 17. Removing Elements

You can remove elements by:

```java
remove(index)
```

or:

```java
remove(Object)
```

Example:

```java
LinkedList<Integer> numbers =
        new LinkedList<>(
                List.of(
                        10, 20, 30, 40
                )
        );

numbers.remove(1);

System.out.println(numbers);
```

Output:

```text
[10, 30, 40]
```

---

# 18. Removing First and Last

`LinkedList` provides specialized methods:

```java
removeFirst()
removeLast()
```

Example:

```java
LinkedList<Integer> numbers =
        new LinkedList<>(
                List.of(
                        10, 20, 30, 40
                )
        );

numbers.removeFirst();
numbers.removeLast();

System.out.println(numbers);
```

Output:

```text
[20, 30]
```

Because the first and last nodes are directly tracked, these operations are:

```text
O(1)
```

---

## Poll Methods

`LinkedList` also supports:

```java
pollFirst()
pollLast()
```

These return and remove an element.

Unlike `removeFirst()` and `removeLast()`, polling an empty deque returns:

```text
null
```

instead of throwing an exception.

---

# 19. Searching

`LinkedList` supports:

```java
contains()
```

Example:

```java
LinkedList<Integer> numbers =
        new LinkedList<>(
                List.of(
                        10, 20, 30
                )
        );

System.out.println(
        numbers.contains(20)
);
```

Output:

```text
true
```

Searching requires traversal in the general case:

```text
contains() -> O(n)
```

Similarly:

```java
indexOf()
lastIndexOf()
```

are generally:

```text
O(n)
```

---

# 20. Duplicates and null

## Duplicates

`LinkedList` allows duplicate values.

```java
LinkedList<Integer> numbers =
        new LinkedList<>();

numbers.add(10);
numbers.add(10);
numbers.add(20);
```

Result:

```text
[10, 10, 20]
```

---

## Null

`LinkedList` permits `null`.

```java
LinkedList<String> names =
        new LinkedList<>();

names.add("Java");
names.add(null);
names.add("Spring");
```

Result:

```text
[Java, null, Spring]
```

---

# 21. Ordering

`LinkedList` maintains insertion order.

Example:

```java
LinkedList<String> languages =
        new LinkedList<>();

languages.add("Python");
languages.add("Java");
languages.add("C++");
```

Result:

```text
[Python, Java, C++]
```

It does not automatically sort elements.

---

# 22. Iteration

You can iterate through a `LinkedList` in several ways.

## Enhanced For Loop

```java
for (Integer number : numbers) {
    System.out.println(number);
}
```

---

## Iterator

```java
Iterator<Integer> iterator =
        numbers.iterator();

while (iterator.hasNext()) {
    System.out.println(
            iterator.next()
    );
}
```

---

## ListIterator

```java
ListIterator<Integer> iterator =
        numbers.listIterator();
```

This supports forward and backward traversal.

---

## Descending Iterator

Because `LinkedList` implements `Deque`, it also supports:

```java
Iterator<Integer> iterator =
        numbers.descendingIterator();
```

This traverses from the end toward the beginning.

---

# 23. Iterator

Example:

```java
LinkedList<Integer> numbers =
        new LinkedList<>(
                List.of(
                        10, 20, 30
                )
        );

Iterator<Integer> iterator =
        numbers.iterator();

while (iterator.hasNext()) {

    Integer number =
            iterator.next();

    System.out.println(number);
}
```

Output:

```text
10
20
30
```

---

## Removing with Iterator

```java
Iterator<Integer> iterator =
        numbers.iterator();

while (iterator.hasNext()) {

    Integer number =
            iterator.next();

    if (number % 2 == 0) {
        iterator.remove();
    }
}
```

This is the safe iterator-based removal pattern.

---

# 24. ListIterator

`LinkedList` supports `ListIterator`.

Example:

```java
ListIterator<Integer> iterator =
        numbers.listIterator();
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

---

## ListIterator Advantages

It supports:

```text
next()
previous()
add()
set()
remove()
nextIndex()
previousIndex()
```

This is useful for bidirectional list traversal.

---

# 25. Deque Operations

Because `LinkedList` implements `Deque`, you can use:

```java
addFirst()
addLast()
removeFirst()
removeLast()
peekFirst()
peekLast()
```

Example:

```java
Deque<Integer> deque =
        new LinkedList<>();

deque.addFirst(20);
deque.addFirst(10);
deque.addLast(30);

System.out.println(deque);
```

Output:

```text
[10, 20, 30]
```

---

# 26. Queue Operations

`LinkedList` can also be used as a queue.

Example:

```java
Queue<String> queue =
        new LinkedList<>();

queue.offer("A");
queue.offer("B");
queue.offer("C");
```

Queue:

```text
[A, B, C]
```

Remove from the front:

```java
String value =
        queue.poll();
```

Result:

```text
A
```

Remaining queue:

```text
[B, C]
```

---

## Queue Methods

Common methods:

| Method | Purpose |
|---|---|
| `offer()` | Add element |
| `poll()` | Remove and return front |
| `peek()` | View front |
| `remove()` | Remove front |
| `element()` | View front |

The `offer/poll/peek` family is often preferred when you want non-exception behavior for empty queues.

---

# 27. Stack-like Operations

`LinkedList` can also be used for stack-style behavior.

Example:

```java
Deque<Integer> stack =
        new LinkedList<>();

stack.push(10);
stack.push(20);
stack.push(30);
```

Stack:

```text
[30, 20, 10]
```

Then:

```java
stack.pop();
```

returns:

```text
30
```

---

## Important

Although `LinkedList` can act as a stack, modern Java code should generally prefer:

```java
ArrayDeque
```

for stack/deque use cases.

---

# 28. Time Complexity

The key complexities are:

| Operation | LinkedList |
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
| `remove(object)` | O(n) |
| `contains()` | O(n) |
| `indexOf()` | O(n) |
| `peekFirst()` | O(1) |
| `peekLast()` | O(1) |
| `pollFirst()` | O(1) |
| `pollLast()` | O(1) |
| `size()` | O(1) |

---

# 29. ArrayList vs LinkedList

This is one of the most important comparisons.

| Feature | ArrayList | LinkedList |
|---|---|---|
| Internal structure | Dynamic array | Doubly linked list |
| `get(index)` | O(1) | O(n) |
| `set(index)` | O(1) | O(n) |
| Add at end | O(1) amortized | O(1) |
| Add first | O(n) | O(1) |
| Remove first | O(n) | O(1) |
| Add at arbitrary index | O(n) | O(n) |
| Remove at arbitrary index | O(n) | O(n) |
| Search | O(n) | O(n) |
| Memory overhead | Lower | Higher |
| Cache locality | Better | Worse |
| Implements Deque | No | Yes |

---

## Important Interview Point

Do not say:

> LinkedList is faster for insertion and deletion.

That statement is incomplete.

A better statement is:

> `LinkedList` can perform insertion or removal in O(1) once the relevant node/position is already known, but finding an arbitrary position can require O(n) traversal. Therefore, indexed insertion/removal is generally O(n).

---

# 30. LinkedList vs ArrayDeque

This is a very important practical comparison.

Both can implement queue/deque behavior.

| Feature | LinkedList | ArrayDeque |
|---|---|---|
| Structure | Doubly linked nodes | Resizable circular array |
| Implements `Deque` | Yes | Yes |
| Random indexed access | O(n) | Not supported |
| Add/remove at ends | O(1) | O(1) amortized |
| Memory overhead | Higher | Lower |
| Cache locality | Worse | Better |
| Allows null | Yes | No |
| Typical deque choice | Less common | Usually preferred |

---

## General Rule

If you need:

```text
Queue
Deque
Stack
```

prefer:

```java
ArrayDeque
```

in many normal single-threaded use cases.

Use `LinkedList` when you specifically need its `List` behavior or other characteristics.

---

# 31. Memory Overhead

This is one of the major disadvantages of `LinkedList`.

Consider:

```text
[10] <-> [20] <-> [30]
```

Each node needs to store:

```text
element reference
next reference
previous reference
```

plus object overhead.

Therefore, compared with an `ArrayList` storing references in a backing array, `LinkedList` generally consumes substantially more memory per element.

---

## Cache Locality

`ArrayList` stores references contiguously in an array.

Conceptually:

```text
[ref][ref][ref][ref][ref]
```

This tends to provide better CPU cache locality.

`LinkedList` nodes can be scattered across memory:

```text
Node A       Node C
   \           /
    \         /
     Node B
```

Traversal can therefore have worse cache behavior.

This is one reason why `LinkedList` is often slower in real applications than its theoretical O(1) endpoint operations might suggest.

---

# 32. Thread Safety

`LinkedList` is not thread-safe by default.

Example:

```java
LinkedList<Integer> numbers =
        new LinkedList<>();
```

does not automatically synchronize access.

For concurrent applications, choose an appropriate concurrent collection or synchronization strategy based on the required behavior.

Do not assume:

```text
LinkedList = thread-safe
```

It is not.

---

# 33. Fail-Fast Behavior

Like `ArrayList`, iterators of `LinkedList` are generally fail-fast.

Example:

```java
LinkedList<Integer> numbers =
        new Link
