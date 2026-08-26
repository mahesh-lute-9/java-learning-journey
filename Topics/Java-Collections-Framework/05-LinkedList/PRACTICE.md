# LinkedList - Practice

> Hands-on practice for mastering `LinkedList`, including node-based thinking, list operations, `Deque` operations, queue/stack behavior, complexity, and interview-level problems.

---

## Table of Contents

- [1. Basic Operations](#1-basic-operations)
- [2. Index-Based Operations](#2-index-based-operations)
- [3. First and Last Operations](#3-first-and-last-operations)
- [4. Searching](#4-searching)
- [5. Iteration](#5-iteration)
- [6. Iterator and ListIterator](#6-iterator-and-listiterator)
- [7. Queue Practice](#7-queue-practice)
- [8. Deque Practice](#8-deque-practice)
- [9. Stack Practice](#9-stack-practice)
- [10. ArrayList vs LinkedList](#10-arraylist-vs-linkedlist)
- [11. Intermediate Problems](#11-intermediate-problems)
- [12. LinkedList Algorithm Practice](#12-linkedlist-algorithm-practice)
- [13. Challenge Problems](#13-challenge-problems)
- [14. Code Prediction](#14-code-prediction)
- [15. Interview Practice](#15-interview-practice)
- [16. Practice Checklist](#16-practice-checklist)

---

# 1. Basic Operations

## Problem 1: Create a LinkedList

Create:

```java
LinkedList<Integer> numbers =
        new LinkedList<>();
```

Add:

```text
10
20
30
40
50
```

### Expected Output

```text
[10, 20, 30, 40, 50]
```

---

## Problem 2: Create a String LinkedList

Create a `LinkedList<String>` containing:

```text
Java
Spring
Hibernate
SQL
```

Print the list.

---

## Problem 3: Find Size

Given:

```java
LinkedList<Integer> numbers =
        new LinkedList<>(
                List.of(
                        10, 20, 30,
                        40, 50
                )
        );
```

Find its size.

### Expected Output

```text
5
```

---

## Problem 4: Check Empty

Create an empty `LinkedList`.

Check:

```java
isEmpty()
```

Then add an element and check again.

### Expected Output

```text
true
false
```

---

## Problem 5: Add Elements Using a Loop

Add numbers from `1` to `10`.

### Expected Output

```text
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
```

---

# 2. Index-Based Operations

## Problem 6: Access an Element

Given:

```java
LinkedList<String> languages =
        new LinkedList<>(
                List.of(
                        "Java",
                        "Python",
                        "C++",
                        "Go"
                )
        );
```

Print the element at index `2`.

### Expected Output

```text
C++
```

---

## Problem 7: Update an Element

Given:

```text
[Java, Python, C++]
```

Replace:

```text
Python
```

with:

```text
Spring
```

### Expected Output

```text
[Java, Spring, C++]
```

---

## Problem 8: Insert at Index

Given:

```text
[10, 20, 40, 50]
```

Insert `30` at index `2`.

### Expected Output

```text
[10, 20, 30, 40, 50]
```

---

## Problem 9: Remove by Index

Given:

```text
[10, 20, 30, 40, 50]
```

Remove index `2`.

### Expected Output

```text
[10, 20, 40, 50]
```

---

## Problem 10: Remove by Value

Given:

```text
[10, 20, 30, 40]
```

Remove the value `30`.

### Expected Output

```text
[10, 20, 40]
```

---

# 3. First and Last Operations

## Problem 11: Add First

Given:

```text
[20, 30, 40]
```

Add `10` at the beginning.

### Expected Output

```text
[10, 20, 30, 40]
```

### Hint

```java
addFirst()
```

---

## Problem 12: Add Last

Given:

```text
[10, 20, 30]
```

Add `40` at the end.

### Expected Output

```text
[10, 20, 30, 40]
```

### Hint

```java
addLast()
```

---

## Problem 13: Remove First

Given:

```text
[10, 20, 30, 40]
```

Remove the first element.

### Expected Output

```text
[20, 30, 40]
```

---

## Problem 14: Remove Last

Given:

```text
[10, 20, 30, 40]
```

Remove the last element.

### Expected Output

```text
[10, 20, 30]
```

---

## Problem 15: Peek First

Given:

```text
[10, 20, 30]
```

Find the first element without removing it.

### Expected Output

```text
10
```

### Hint

```java
peekFirst()
```

---

## Problem 16: Peek Last

Find the last element without removing it.

### Expected Output

```text
30
```

---

## Problem 17: Poll First

Given:

```text
[10, 20, 30]
```

Use:

```java
pollFirst()
```

Print:

1. The removed element.
2. The remaining list.

### Expected Output

```text
Removed: 10
Remaining: [20, 30]
```

---

## Problem 18: Poll Last

Given:

```text
[10, 20, 30]
```

Use:

```java
pollLast()
```

### Expected Output

```text
Removed: 30
Remaining: [10, 20]
```

---

# 4. Searching

## Problem 19: Contains

Check whether `30` exists.

Given:

```text
[10, 20, 30, 40]
```

### Expected Output

```text
true
```

---

## Problem 20: Find Index

Find the index of `30`.

### Expected Output

```text
2
```

### Hint

```java
indexOf()
```

---

## Problem 21: Find Last Index

Given:

```text
[10, 20, 10, 30, 10]
```

Find the last index of `10`.

### Expected Output

```text
4
```

---

## Problem 22: Count Occurrences

Given:

```text
[10, 20, 10, 30, 10, 40]
```

Count how many times `10` occurs.

### Expected Output

```text
3
```

---

# 5. Iteration

## Problem 23: Enhanced For Loop

Print every element using:

```java
for-each
```

Given:

```text
[10, 20, 30, 40]
```

---

## Problem 24: Traditional For Loop

Print every element using:

```java
for
```

and:

```java
get(index)
```

---

## Problem 25: Iterator

Traverse the list using:

```java
Iterator<Integer>
```

---

## Problem 26: Reverse Traversal

Use:

```java
ListIterator
```

to print:

```text
40
30
20
10
```

for:

```text
[10, 20, 30, 40]
```

---

## Problem 27: Descending Iterator

Given:

```java
LinkedList<Integer> numbers =
        new LinkedList<>(
                List.of(
                        10, 20, 30, 40
                )
        );
```

Use:

```java
descendingIterator()
```

to traverse the list.

### Expected Output

```text
40
30
20
10
```

---

# 6. Iterator and ListIterator

## Problem 28: Remove Even Numbers Using Iterator

Given:

```text
[10, 15, 20, 25, 30]
```

Remove all even numbers using an `Iterator`.

### Expected Output

```text
[15, 25]
```

---

## Problem 29: Add Using ListIterator

Given:

```text
[10, 20, 40]
```

Use `ListIterator` to insert `30` between `20` and `40`.

### Expected Output

```text
[10, 20, 30, 40]
```

---

## Problem 30: Replace Using ListIterator

Given:

```text
[10, 20, 30]
```

Use `ListIterator` to replace `20` with `200`.

### Expected Output

```text
[10, 200, 30]
```

---

## Problem 31: Forward and Backward Traversal

Given:

```text
[10, 20, 30, 40]
```

Traverse forward and then backward using `ListIterator`.

---

# 7. Queue Practice

> Use the interface type when the goal is queue behavior.

```java
Queue<Integer> queue =
        new LinkedList<>();
```

---

## Problem 32: Implement a Queue

Add:

```text
10
20
30
40
```

using:

```java
offer()
```

Then remove them using:

```java
poll()
```

### Expected Order

```text
10
20
30
40
```

---

## Problem 33: Peek Queue Front

Given:

```text
[10, 20, 30]
```

Use:

```java
peek()
```

### Expected Output

```text
10
```

The queue should remain unchanged.

---

## Problem 34: Poll Queue

Given:

```text
[10, 20, 30]
```

Call:

```java
poll()
```

### Expected Output

```text
10
```

Remaining:

```text
[20, 30]
```

---

## Problem 35: Queue Using LinkedList

Implement:

```java
Queue<String> queue =
        new LinkedList<>();
```

Perform:

```text
offer("A")
offer("B")
offer("C")
poll()
offer("D")
poll()
```

Determine the final queue.

### Expected Output

```text
[C, D]
```

---

# 8. Deque Practice

> `LinkedList` implements `Deque`, allowing insertion and removal from both ends.

---

## Problem 36: Add at Both Ends

Start with:

```text
[20, 30]
```

Perform:

```java
addFirst(10);
addLast(40);
```

### Expected Output

```text
[10, 20, 30, 40]
```

---

## Problem 37: Remove from Both Ends

Given:

```text
[10, 20, 30, 40]
```

Perform:

```java
removeFirst();
removeLast();
```

### Expected Output

```text
[20, 30]
```

---

## Problem 38: Peek Both Ends

Given:

```text
[10, 20, 30, 40]
```

Find:

```text
first = 10
last = 40
```

Use:

```java
peekFirst()
peekLast()
```

---

## Problem 39: Offer at Both Ends

Use:

```java
offerFirst()
offerLast()
```

to create:

```text
[10, 20, 30, 40]
```

starting from an empty deque.

---

## Problem 40: Poll at Both Ends

Given:

```text
[10, 20, 30, 40]
```

Use:

```java
pollFirst()
pollLast()
```

Print both removed elements.

### Expected Output

```text
First: 10
Last: 40
```

---

# 9. Stack Practice

> `LinkedList` can implement stack behavior, although `ArrayDeque` is generally preferred for modern stack/deque use cases.

---

## Problem 41: Implement a Stack

Create:

```java
Deque<Integer> stack =
        new LinkedList<>();
```

Push:

```text
10
20
30
```

Then pop all elements.

### Expected Order

```text
30
20
10
```

---

## Problem 42: Peek Stack

Push:

```text
10
20
30
```

Then call:

```java
peek()
```

### Expected Output

```text
30
```

The stack should remain unchanged.

---

## Problem 43: Push and Pop

Perform:

```text
push(10)
push(20)
pop()
push(30)
push(40)
pop()
```

Determine the final stack.

### Expected Output

```text
[30, 10]
```

---

# 10. ArrayList vs LinkedList

## Problem 44: Choose the Collection

You need frequent:

```java
get(index)
```

Which should you choose?

### Answer

```text
ArrayList
```

because:

```text
get(index) -> O(1)
```

---

## Problem 45: Frequent First-Element Operations

You frequently need:

```text
addFirst()
removeFirst()
```

Which list naturally supports these efficiently?

### Answer

```text
LinkedList
```

with:

```text
O(1)
```

endpoint operations.

---

## Problem 46: Queue or Deque

You need:

```text
Queue
```

or:

```text
Deque
```

behavior.

Should you automatically choose `LinkedList`?

### Answer

No.

Consider:

```java
ArrayDeque
```

which is usually a better default for queue/deque behavior in non-concurrent scenarios.

---

## Problem 47: Memory-Constrained Application

You need a large list and memory usage matters.

Which is generally more memory-efficient?

```text
ArrayList
```

or:

```text
LinkedList
```

### Answer

Usually:

```text
ArrayList
```

because `LinkedList` has per-node overhead for element, previous, and next references.

---

# 11. Intermediate Problems

## Problem 48: Reverse a LinkedList

Given:

```text
[10, 20, 30, 40, 50]
```

Reverse it.

### Expected Output

```text
[50, 40, 30, 20, 10]
```

### Challenge

Try:

1. Using `Collections.reverse()`
2. Without `Collections.reverse()`
3. Using a deque-like approach

---

## Problem 49: Find Maximum

Given:

```text
[10, 50, 20, 90, 30]
```

Find the maximum without using:

```java
Collections.max()
```

### Expected Output

```text
90
```

---

## Problem 50: Find Minimum

Find the minimum.

### Expected Output

```text
10
```

---

## Problem 51: Remove Duplicates

Given:

```text
[10, 20, 10, 30, 20, 40]
```

Create a list containing unique elements.

### Expected Output

```text
[10, 20, 30, 40]
```

### Challenge

Solve using:

1. `Set`
2. Only list operations

---

## Problem 52: Count Even and Odd Numbers

Given:

```text
[10, 15, 20, 25, 30, 35]
```

Count:

```text
Even = 3
Odd  = 3
```

---

## Problem 53: Remove All Odd Numbers

Given:

```text
[10, 15, 20, 25, 30, 35]
```

Remove all odd numbers.

### Expected Output

```text
[10, 20, 30]
```

---

## Problem 54: Find Second Largest

Given:

```text
[10, 50, 20, 90, 30, 80]
```

Find the second-largest value.

### Expected Output

```text
80
```

### Challenge

Solve without sorting.

---

## Problem 55: Find Common Elements

Given:

```text
First:
[10, 20, 30, 40]

Second:
[20, 40, 60, 80]
```

Find the common elements.

### Expected Output

```text
[20, 40]
```

---

# 12. LinkedList Algorithm Practice

> These problems are designed to connect Java's `LinkedList` API with the underlying linked-list data structure.

---

## Problem 56: Find Middle Element

Given:

```text
[10, 20, 30, 40, 50]
```

Find the middle element.

### Expected Output

```text
30
```

### Challenge

Use the:

```text
slow and fast pointer
```

technique.

---

## Problem 57: Find Middle for Even Length

Given:

```text
[10, 20, 30, 40, 50, 60]
```

Find the middle element according to the convention you choose.

For example, the second middle:

```text
40
```

### Challenge

Use slow/fast pointers.

---

## Problem 58: Detect Cycle

Given a conceptual linked list:

```text
10 -> 20 -> 30 -> 40
          ^         |
          |_________|
```

Determine whether a cycle exists.

### Challenge

Use:

```text
Floyd's Cycle Detection Algorithm
```

---

## Problem 59: Find Cycle Start

Given a linked list containing a cycle, find the node where the cycle begins.

### Challenge

Use:

```text
Floyd's algorithm
```

---

## Problem 60: Reverse Linked List

Reverse:

```text
10 -> 20 -> 30 -> 40
```

into:

```text
40 -> 30 -> 20 -> 10
```

### Challenge

Implement the classic iterative algorithm using:

```text
prev
current
next
```

---

## Problem 61: Merge Two Sorted Lists

Given:

```text
First:
1 -> 3 -> 5 -> 7

Second:
2 -> 4 -> 6 -> 8
```

Merge them into:

```text
1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
```

---

## Problem 62: Remove Nth Node from End

Given:

```text
10 -> 20 -> 30 -> 40 -> 50
```

Remove the:

```text
2nd node from the end
```

### Expected Result

```text
10 -> 20 -> 30 -> 50
```

### Challenge

Use:

```text
two-pointer technique
```

---

## Problem 63: Find Nth Node from End

Given:

```text
10 -> 20 -> 30 -> 40 -> 50
```

Find the:

```text
2nd node from the end
```

### Expected Output

```text
40
```

---

## Problem 64: Check Palindrome

Given:

```text
10 -> 20 -> 30 -> 20 -> 10
```

Determine whether the linked list is a palindrome.

### Expected Output

```text
true
```

---

## Problem 65: Find Intersection

Given two linked lists that eventually merge:

```text
List A:

10 -> 20
          \
           30 -> 40 -> 50
          /
List B:

5 -> 15
```

Find the intersection node.

### Expected Output

```text
30
```

---

# 13. Challenge Problems

## Challenge 1: Implement Your Own Node

Create:

```java
class Node {

    int data;

    Node next;

    Node prev;
}
```

Create:

```text
10 <-> 20 <-> 30
```

manually.

---

## Challenge 2: Implement Singly Linked List

Create your own:

```java
class MyLinkedList
```

with:

```text
Node head
```

Implement:

```java
add(int value)
addFirst(int value)
removeFirst()
display()
```

---

## Challenge 3: Implement Doubly Linked List

Create:

```java
class MyDoublyLinkedList
```

with:

```text
head
tail
```

Implement:

```java
addFirst()
addLast()
removeFirst()
removeLast()
displayForward()
displayBackward()
```

---

## Challenge 4: Implement Queue Using LinkedList

Create a queue supporting:

```java
enqueue()
dequeue()
peek()
isEmpty()
```

Expected behavior:

```text
enqueue(10)
enqueue(20)
enqueue(30)

dequeue()
    -> 10

peek()
    -> 20
```

---

## Challenge 5: Implement Stack Using LinkedList

Create a stack supporting:

```java
push()
pop()
peek()
isEmpty()
```

Example:

```text
push(10)
push(20)
push(30)

pop()
    -> 30
```

---

## Challenge 6: Reverse in Groups of K

Given:

```text
1 -> 2 -> 3 -> 4 -> 5 -> 6
```

and:

```text
k = 2
```

Expected:

```text
2 -> 1 -> 4 -> 3 -> 6 -> 5
```

---

## Challenge 7: Rotate Linked List

Given:

```text
1 -> 2 -> 3 -> 4 -> 5
```

Rotate right by:

```text
2
```

### Expected

```text
4 -> 5 -> 1 -> 2 -> 3
```

---

## Challenge 8: Sort a Linked List

Given:

```text
4 -> 2 -> 1 -> 3
```

Sort it.

### Expected

```text
1 -> 2 -> 3 -> 4
```

### Challenge

Use:

```text
Merge Sort
```

because it works well with linked lists.

---

# 13. Code Prediction

## Problem 66

What is the output?

```java
LinkedList<Integer> list =
        new LinkedList<>();

list.add(10);
list.add(20);
list.add(30);

System.out.println(list);
```

### Expected Output

```text
[10, 20, 30]
```

---

## Problem 67

What is the output?

```java
LinkedList<Integer> list =
        new LinkedList<>(
                List.of(10, 20, 30)
        );

list.addFirst(5);

System.out.println(list);
```

### Expected Output

```text
[5, 10, 20, 30]
```

---

## Problem 68

What is the output?

```java
LinkedList<Integer> list =
        new LinkedList<>(
                List.of(10, 20, 30)
        );

list.addLast(40);

System.out.println(list);
```

### Expected Output

```text
[10, 20, 30, 40]
```

---

## Problem 69

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

### Expected Output

```text
10
[20, 30]
```

---

## Problem 70

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

### Expected Output

```text
10
[10, 20, 30]
```

`peekFirst()` does not remove the element.

---

## Problem 71

What is the output?

```java
Deque<Integer> deque =
        new LinkedList<>();

deque.addFirst(20);
deque.addFirst(10);
deque.addLast(30);

System.out.println(deque);
```

### Expected Output

```text
[10, 20, 30]
```

---

## Problem 72

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

### Expected Output

```text
10
[20, 30]
```

---

## Problem 73

What is the output?

```java
Deque<Integer> stack =
        new LinkedList<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.pop());
```

### Expected Output

```text
30
```

---

## Problem 74

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

### Expected Result

It can throw:

```text
ConcurrentModificationException
```

because the list is structurally modified during iteration.

---

# 14. Interview Practice

## Interview Problem 1: What is LinkedList?

Explain `LinkedList` in Java.

### Expected Answer

`LinkedList` is a doubly linked list implementation of both the `List` and `Deque` interfaces. Each element is stored in a node containing references to the previous and next nodes.

---

## Interview Problem 2: What Is the Internal Structure?

Explain:

```text
10 <-> 20 <-> 30
```

### Expected Answer

Each node contains:

```text
previous reference
element
next reference
```

The list maintains references to its first and last nodes.

---

## Interview Problem 3: Why Is get() O(n)?

### Expected Answer

Because `LinkedList` does not provide array-style direct index access. It must locate the required node through traversal.

---

## Interview Problem 4: Is LinkedList Insertion O(1)?

### Expected Answer

Only after the target node/position has already been located.

The actual relinking can be O(1), but locating an arbitrary index can take O(n). Therefore indexed insertion is generally O(n).

---

## Interview Problem 5: Why Are addFirst() and removeFirst() O(1)?

### Expected Answer

The list maintains a reference to its first node. Updating the first node and its neighboring references does not require traversing the list.

---

## Interview Problem 6: Why Are addLast() and removeLast() O(1)?

### Expected Answer

The list maintains a reference to its last node, so endpoint operations can be performed directly.

---

## Interview Problem 7: Why Does LinkedList Consume More Memory?

### Expected Answer

Each node stores the element reference plus references to the previous and next nodes, along with object overhead.

---

## Interview Problem 8: ArrayList or LinkedList for Random Access?

### Answer

```text
ArrayList
```

because:

```text
get(index) -> O(1)
```

while:

```text
LinkedList.get(index) -> O(n)
```

---

## Inter
