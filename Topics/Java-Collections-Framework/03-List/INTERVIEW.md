# List Interface - Interview Questions

> A complete interview-focused guide to the Java `List` interface, covering fundamentals, implementation details, tricky questions, code-output problems, and practical scenarios.

---

## Table of Contents

- [1. What is List?](#1-what-is-list)
- [2. List Hierarchy](#2-list-hierarchy)
- [3. Basic Interview Questions](#3-basic-interview-questions)
- [4. List vs Collection](#4-list-vs-collection)
- [5. List Characteristics](#5-list-characteristics)
- [6. Index-Based Operations](#6-index-based-operations)
- [7. add vs set](#7-add-vs-set)
- [8. remove int vs remove Object](#8-remove-int-vs-remove-object)
- [9. ArrayList vs LinkedList](#9-arraylist-vs-linkedlist)
- [10. ListIterator](#10-listiterator)
- [11. subList](#11-sublist)
- [12. List.of](#12-listof)
- [13. Arrays.asList](#13-arraysaslist)
- [14. Null Values](#14-null-values)
- [15. Duplicate Elements](#15-duplicate-elements)
- [16. Ordering](#16-ordering)
- [17. Time Complexity](#17-time-complexity)
- [18. Tricky Interview Questions](#18-tricky-interview-questions)
- [19. Output-Based Questions](#19-output-based-questions)
- [20. Scenario-Based Questions](#20-scenario-based-questions)
- [21. Rapid-Fire Questions](#21-rapid-fire-questions)
- [22. Interview Revision Checklist](#22-interview-revision-checklist)

---

# 1. What is List?

## Question

What is the `List` interface in Java?

## Answer

`List` is an interface in the Java Collections Framework that represents an **ordered collection of elements**.

It allows:

- Duplicate elements
- `null` elements depending on the implementation
- Index-based access
- Positional insertion
- Positional removal
- Searching by index

`List` extends the `Collection` interface.

```text
Iterable
   |
Collection
   |
List
```

Example:

```java
List<String> names = new ArrayList<>();

names.add("Java");
names.add("Spring");
names.add("Java");
```

Result:

```text
[Java, Spring, Java]
```

---

# 2. List Hierarchy

## Question

What is the hierarchy of `List`?

## Answer

The important hierarchy is:

```text
Iterable
   |
Collection
   |
List
   |
   +-- ArrayList
   |
   +-- LinkedList
   |
   +-- Vector
         |
         +-- Stack
```

### Important Point

`List` is an interface.

`ArrayList`, `LinkedList`, and `Vector` are implementations.

`Stack` is a class that extends `Vector`.

---

# 3. Basic Interview Questions

## Q1. Is List a class or an interface?

**Answer:**

`List` is an interface.

```java
public interface List<E>
        extends Collection<E>
```

---

## Q2. Which package contains List?

**Answer:**

```java
java.util
```

Import it using:

```java
import java.util.List;
```

---

## Q3. Does List allow duplicates?

**Answer:**

Yes.

Example:

```java
List<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(10);
numbers.add(20);
```

Result:

```text
[10, 10, 20]
```

---

## Q4. Does List maintain insertion order?

**Answer:**

Yes. A `List` maintains a defined sequence of elements.

Example:

```java
list.add(30);
list.add(10);
list.add(20);
```

The list contains:

```text
[30, 10, 20]
```

It does not automatically sort the elements.

---

## Q5. Does List support index-based access?

**Answer:**

Yes.

Example:

```java
list.get(2);
```

The index starts from `0`.

---

## Q6. What is the first index of a List?

**Answer:**

The first index is:

```text
0
```

For:

```text
[A, B, C]
```

the indexes are:

```text
0 -> A
1 -> B
2 -> C
```

---

## Q7. Which classes implement List?

The important implementations are:

```text
ArrayList
LinkedList
Vector
```

`Stack` extends `Vector`.

---

## Q8. Can List contain null?

**Answer:**

It depends on the implementation.

For example:

```java
ArrayList
LinkedList
```

allow `null`.

But:

```java
List.of()
```

does not allow `null`.

---

# 4. List vs Collection

## Question

What is the difference between `Collection` and `List`?

## Answer

`List` extends `Collection`.

`Collection` provides general collection operations.

`List` adds positional and index-based operations.

| Feature | Collection | List |
|---|---|---|
| Interface | Yes | Yes |
| Extends `Iterable` | Yes | Yes |
| Index-based access | No | Yes |
| `get(index)` | No | Yes |
| `set(index, value)` | No | Yes |
| `indexOf()` | No | Yes |
| `lastIndexOf()` | No | Yes |
| `subList()` | No | Yes |
| Duplicates | Depends on implementation | Allowed |
| Ordered | Not guaranteed | Yes |

### Interview Answer

> `Collection` is a general-purpose interface for groups of objects, while `List` is a specialized collection that maintains order, allows duplicates, and supports index-based operations.

---

# 5. List Characteristics

## Question

What are the main characteristics of List?

## Answer

A `List` generally has these characteristics:

```text
1. Ordered
2. Allows duplicates
3. Index-based
4. Supports positional insertion
5. Supports positional removal
6. Supports searching by index
```

Example:

```java
List<String> languages = new ArrayList<>();

languages.add("Java");
languages.add("Python");
languages.add("Java");
```

Result:

```text
[Java, Python, Java]
```

---

# 6. Index-Based Operations

## Question

How do you access an element from a List?

Use:

```java
get(index)
```

Example:

```java
List<String> names = List.of(
        "Mahesh",
        "Rahul",
        "Amit"
);

System.out.println(names.get(1));
```

Output:

```text
Rahul
```

---

## Question

How do you update an element?

Use:

```java
set(index, value)
```

Example:

```java
names.set(1, "Rohan");
```

---

## Question

How do you insert at a specific index?

Use:

```java
add(index, value)
```

Example:

```java
names.add(1, "Rohan");
```

---

## Question

What happens if an invalid index is used?

Example:

```java
List<Integer> numbers =
        List.of(10, 20, 30);

numbers.get(5);
```

This throws:

```text
IndexOutOfBoundsException
```

because valid indexes are:

```text
0
1
2
```

---

# 7. add vs set

This is a very common interview question.

## `add(index, value)`

Inserts a new element.

Starting list:

```text
[A, B, C]
```

Code:

```java
list.add(1, "X");
```

Result:

```text
[A, X, B, C]
```

---

## `set(index, value)`

Replaces an existing element.

Starting list:

```text
[A, B, C]
```

Code:

```java
list.set(1, "X");
```

Result:

```text
[A, X, C]
```

### Easy Way to Remember

```text
add()
    -> Adds

set()
    -> Replaces
```

---

# 8. remove int vs remove Object

This is one of the most important `List` interview traps.

`List` has:

```java
remove(int index)
```

and:

```java
remove(Object object)
```

Consider:

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );
```

Now:

```java
numbers.remove(1);
```

removes the element at index `1`.

Result:

```text
[10, 30]
```

It does not remove the value `1`.

---

## Why?

Because `1` is an `int`.

Therefore Java chooses:

```java
remove(int index)
```

---

## How to Remove by Value

Use:

```java
numbers.remove(Integer.valueOf(20));
```

This calls:

```java
remove(Object)
```

Result:

```text
[10, 30]
```

### Interview Question

What does this print?

```java
List<Integer> list =
        new ArrayList<>(
                List.of(1, 2, 3)
        );

list.remove(1);

System.out.println(list);
```

### Answer

```text
[1, 3]
```

Because index `1` contains the value `2`.

---

# 9. ArrayList vs LinkedList

This is one of the most important comparisons in the Java Collections Framework.

| Feature | ArrayList | LinkedList |
|---|---|---|
| Internal structure | Dynamic array | Doubly linked list |
| Random access | Fast | Slow |
| `get(index)` | O(1) | O(n) |
| Add at end | O(1) amortized | O(1) |
| Insert in middle | O(n) | O(n) to locate position |
| Remove by index | O(n) | O(n) to locate position |
| Memory overhead | Lower | Higher |
| Implements `Deque` | No | Yes |
| Cache locality | Better | Worse |

---

## Why is ArrayList `get()` O(1)?

`ArrayList` uses an array internally.

Suppose:

```text
[10, 20, 30, 40, 50]
```

The elements are stored in contiguous array positions.

To access index `3`, Java can directly calculate where the element is located.

Therefore:

```text
get(index)
    -> O(1)
```

---

## Why is LinkedList `get()` O(n)?

A linked list stores nodes.

Conceptually:

```text
[10] <-> [20] <-> [30] <-> [40] <-> [50]
```

To access an element by index, Java may need to traverse the nodes.

Therefore:

```text
get(index)
    -> O(n)
```

---

## Which One Should You Usually Use?

For general-purpose list operations:

```java
List<T> list = new ArrayList<>();
```

is usually the preferred default.

Use `LinkedList` when its linked/deque behavior provides a specific advantage.

---

# 10. ListIterator

## Question

What is `ListIterator`?

`ListIterator` is a specialized iterator designed for `List`.

It supports:

- Forward traversal
- Backward traversal
- Adding elements
- Removing elements
- Replacing elements
- Index information

Example:

```java
ListIterator<Integer> iterator =
        numbers.listIterator();
```

---

## Iterator vs ListIterator

| Feature | Iterator | ListIterator |
|---|---|---|
| Forward traversal | Yes | Yes |
| Backward traversal | No | Yes |
| `remove()` | Yes | Yes |
| `add()` | No | Yes |
| `set()` | No | Yes |
| Index information | No | Yes |
| Works specifically with List | No | Yes |

---

## Example

```java
List<String> names =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );

ListIterator<String> iterator =
        names.listIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

---

# 11. subList

## Question

What does `subList()` do?

It returns a view of a portion of the list.

Syntax:

```java
subList(fromIndex, toIndex)
```

The rule is:

```text
fromIndex -> inclusive
toIndex   -> exclusive
```

Example:

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 30, 40, 50
                )
        );

List<Integer> sub =
        numbers.subList(1, 4);
```

Result:

```text
[20, 30, 40]
```

---

## Important Interview Point

`subList()` returns a view backed by the original list.

Example:

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

List<Integer> sub =
        numbers.subList(1, 3);

sub.set(0, 100);

System.out.println(numbers);
```

Output:

```text
[10, 100, 30, 40]
```

The original list changed.

---

# 12. List.of

## Question

What is `List.of()`?

`List.of()` is a convenient factory method for creating an unmodifiable list.

Example:

```java
List<Integer> numbers =
        List.of(10, 20, 30);
```

You cannot modify it:

```java
numbers.add(40);
```

This throws:

```text
UnsupportedOperationException
```

---

## Does List.of() Allow null?

No.

This:

```java
List<String> names =
        List.of("Java", null);
```

throws:

```text
NullPointerException
```

---

# 13. Arrays.asList

## Question

What is `Arrays.asList()`?

It creates a fixed-size list backed by an array.

Example:

```java
List<String> names =
        Arrays.asList(
                "Java",
                "Spring",
                "SQL"
        );
```

You can use:

```java
names.set(0, "Java 21");
```

But you cannot change the size:

```java
names.add("Python");
```

or:

```java
names.remove("Java");
```

These throw:

```text
UnsupportedOperationException
```

---

## Arrays.asList vs List.of

| Feature | `Arrays.asList()` | `List.of()` |
|---|---|---|
| Add | No | No |
| Remove | No | No |
| Set | Yes | No |
| Allows `null` | Yes | No |
| Size modification | No | No |
| Unmodifiable | No | Yes |

---

# 14. Null Values

## Question

Does List allow null?

There is no single answer for every implementation.

For example:

### ArrayList

```java
List<String> list =
        new ArrayList<>();

list.add(null);
```

Allowed.

### LinkedList

Also allows `null`.

### List.of()

Does not allow `null`.

```java
List<String> list =
        List.of("Java", null);
```

Throws:

```text
NullPointerException
```

### Interview Answer

> Null support depends on the specific `List` implementation or factory method.

---

# 15. Duplicate Elements

## Question

Can a List contain duplicate elements?

Yes.

Example:

```java
List<String> languages =
        new ArrayList<>();

languages.add("Java");
languages.add("Python");
languages.add("Java");
```

Result:

```text
[Java, Python, Java]
```

This is one of the key differences between `List` and `Set`.

---

## List vs Set

```text
List
    -> Allows duplicates

Set
    -> Does not allow duplicates
```

---

# 16. Ordering

## Question

Does List maintain insertion order?

Yes.

Example:

```java
List<Integer> numbers =
        new ArrayList<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);
```

Result:

```text
[30, 10, 20]
```

The list does not automatically sort elements.

If sorting is required:

```java
numbers.sort(
        Integer::compareTo
);
```

Result:

```text
[10, 20, 30]
```

---

# 17. Time Complexity

The exact complexity can depend on the implementation.

For the common implementations:

## ArrayList

| Operation | Complexity |
|---|---|
| `get(index)` | O(1) |
| `set(index, value)` | O(1) |
| `add(value)` | O(1) amortized |
| `add(index, value)` | O(n) |
| `remove(index)` | O(n) |
| `contains(value)` | O(n) |
| `indexOf(value)` | O(n) |
| `lastIndexOf(value)` | O(n) |

---

## LinkedList

| Operation | Complexity |
|---|---|
| `get(index)` | O(n) |
| `set(index, value)` | O(n) |
| `addFirst()` | O(1) |
| `addLast()` | O(1) |
| `removeFirst()` | O(1) |
| `removeLast()` | O(1) |
| `contains(value)` | O(n) |
| `indexOf(value)` | O(n) |

### Important

For `LinkedList`, insertion/removal at a node can be O(1) **once the node is already located**.

Finding the position by index can take O(n).

---

# 18. Tricky Interview Questions

## Q1. Why is `remove(1)` dangerous with `List<Integer>`?

Because Java has:

```java
remove(int index)
```

and:

```java
remove(Object object)
```

An integer literal such as:

```java
1
```

is an `int`, so Java selects:

```java
remove(int)
```

To remove the value:

```java
remove(Integer.valueOf(1))
```

---

## Q2. Is List synchronized?

The `List` interface itself does not guarantee synchronization.

For example:

```java
ArrayList
```

is not synchronized.

Thread safety depends on the implementation or wrapper being used.

---

## Q3. Is ArrayList thread-safe?

No.

`ArrayList` is not thread-safe by default.

If multiple threads modify a list concurrently, appropriate synchronization or concurrent collections may be required.

---

## Q4. Is LinkedList always better for insertion?

No.

This is a common misconception.

Even though linked lists can insert or remove a node in O(1), finding the correct position can require O(n) traversal.

For many real-world workloads, `ArrayList` performs better due to:

- Better cache locality
- Lower memory overhead
- Faster random access

---

## Q5. Is `subList()` a copy?

No.

It is a view backed by the original list.

---

## Q6. Does List guarantee O(1) `get()`?

No.

The interface only specifies the operation.

The implementation determines its complexity.

For example:

```text
ArrayList -> O(1)
LinkedList -> O(n)
```

---

## Q7. Can List contain duplicates?

Yes.

---

## Q8. Can List contain null?

It depends on the implementation.

---

## Q9. What is the difference between `List.of()` and `new ArrayList<>()`?

```text
List.of()
    -> Unmodifiable

new ArrayList<>()
    -> Mutable
```

---

## Q10. What is the difference between List and ArrayList?

```text
List
    -> Interface

ArrayList
    -> Concrete implementation
```

A common best practice is:

```java
List<Integer> numbers =
        new ArrayList<>();
```

rather than:

```java
ArrayList<Integer> numbers =
        new ArrayList<>();
```

when you only need the `List` behavior.

---

# 19. Output-Based Questions

## Question 1

What is the output?

```java
List<Integer> list =
        new ArrayList<>();

list.add(10);
list.add(20);
list.add(10);

System.out.println(list);
```

### Answer

```text
[10, 20, 10]
```

Duplicates are allowed.

---

## Question 2

What is the output?

```java
List<Integer> list =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

list.set(1, 100);

System.out.println(list);
```

### Answer

```text
[10, 100, 30]
```

---

## Question 3

What is the output?

```java
List<Integer> list =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

list.add(1, 100);

System.out.println(list);
```

### Answer

```text
[10, 100, 20, 30]
```

---

## Question 4

What is the output?

```java
List<Integer> list =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

list.remove(1);

System.out.println(list);
```

### Answer

```text
[10, 30]
```

---

## Question 5

What is the output?

```java
List<Integer> list =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

list.remove(Integer.valueOf(20));

System.out.println(list);
```

### Answer

```text
[10, 30]
```

---

## Question 6

What happens?

```java
List<Integer> list =
        List.of(10, 20, 30);

list.add(40);
```

### Answer

```text
UnsupportedOperationException
```

---

## Question 7

What happens?

```java
List<String> list =
        List.of("Java", null);
```

### Answer

```text
NullPointerException
```

---

## Question 8

What is the output?

```java
List<String> list = Arrays.asList(
        "Java",
        "Spring",
        "SQL"
);

list.set(1, "Hibernate");

System.out.println(list);
```

### Answer

```text
[Java, Hibernate, SQL]
```

---

## Question 9

What happens?

```java
List<String> list = Arrays.asList(
        "Java",
        "Spring"
);

list.add("SQL");
```

### Answer

```text
UnsupportedOperationException
```

---

## Question 10

What is the output?

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

List<Integer> sub =
        numbers.subList(1, 3);

sub.set(0, 100);

System.out.println(numbers);
```

### Answer

```text
[10, 100, 30, 40]
```

Because `subList()` returns a view backed by the original list.

---

# 20. Scenario-Based Questions

## Scenario 1

You need frequent random access using indexes.

Which implementation would you choose?

### Answer

Usually:

```java
ArrayList
```

because:

```text
get(index) -> O(1)
```

---

## Scenario 2

You need a collection that maintains duplicates and order.

Which interface would you choose?

### Answer

```java
List
```

---

## Scenario 3

You need to create a list of fixed values that should not be modified.

Which method can you use?

### Answer

```java
List.of()
```

Example:

```java
List<String> languages =
        List.of(
                "Java",
                "Python",
                "C++"
        );
```

---

## Scenario 4

You need to modify existing elements but do not want to change the size of a list created from an array.

Which method is relevant?

### Answer

```java
Arrays.asList()
```

Example:

```java
List<String> names =
        Arrays.asList(
                "Java",
                "Spring"
        );

names.set(0, "Java 21");
```

---

## Scenario 5

You need stack behavior in modern Java.

What should you prefer?

### Answer

Prefer:

```java
Deque<Integer> stack =
        new ArrayDeque<>();
```

rather than the legacy:

```java
Stack<Integer> stack =
        new Stack<>();
```

---

## Scenario 6

You need to iterate both forward and backward through a list.

What should you use?

### Answer

```java
ListIterator
```

---

## Scenario 7

You need a portion of a list and want changes to the portion to affect the original list.

What should you use?

### Answer

```java
subList()
```

---

#
