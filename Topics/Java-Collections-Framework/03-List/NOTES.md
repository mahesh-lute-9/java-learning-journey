# List Interface in Java

> `List` is an ordered collection that allows duplicate elements and provides index-based access to its elements.

---

## Table of Contents

1. [Introduction](#1-introduction)
2. [What is List?](#2-what-is-list)
3. [List Hierarchy](#3-list-hierarchy)
4. [List Interface Declaration](#4-list-interface-declaration)
5. [Characteristics of List](#5-characteristics-of-list)
6. [List vs Collection](#6-list-vs-collection)
7. [Creating a List](#7-creating-a-list)
8. [Adding Elements](#8-adding-elements)
9. [Adding Elements at a Specific Index](#9-adding-elements-at-a-specific-index)
10. [Accessing Elements](#10-accessing-elements)
11. [Updating Elements](#11-updating-elements)
12. [Removing Elements](#12-removing-elements)
13. [Removing by Index vs Removing by Value](#13-removing-by-index-vs-removing-by-value)
14. [Searching in a List](#14-searching-in-a-list)
15. [indexOf](#15-indexof)
16. [lastIndexOf](#16-lastindexof)
17. [size](#17-size)
18. [isEmpty](#18-isempty)
19. [Iterating a List](#19-iterating-a-list)
20. [ListIterator](#20-listiterator)
21. [Forward and Backward Traversal](#21-forward-and-backward-traversal)
22. [subList](#22-sublist)
23. [replaceAll](#23-replaceall)
24. [sort](#24-sort)
25. [removeIf](#25-removeif)
26. [addAll](#26-addall)
27. [List.of](#27-listof)
28. [Mutable vs Unmodifiable Lists](#28-mutable-vs-unmodifiable-lists)
29. [Arrays.asList](#29-arraysaslist)
30. [Null Values](#30-null-values)
31. [Duplicate Elements](#31-duplicate-elements)
32. [Ordering](#32-ordering)
33. [Random Access](#33-random-access)
34. [ArrayList vs LinkedList](#34-arraylist-vs-linkedlist)
35. [List Implementations](#35-list-implementations)
36. [Important List Methods](#36-important-list-methods)
37. [Common Mistakes](#37-common-mistakes)
38. [Interview Focus](#38-interview-focus)
39. [Quick Revision](#39-quick-revision)
40. [Final Mental Model](#40-final-mental-model)

---

# 1. Introduction

`List` is one of the most important interfaces in the Java Collections Framework.

It represents an **ordered collection of elements**.

Unlike a `Set`, a `List` allows duplicate elements.

Unlike the general `Collection` interface, `List` provides **positional access**, meaning elements can be accessed using an index.

The major implementations of `List` are:

```text
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

Among these:

- `ArrayList` is the most commonly used implementation.
- `LinkedList` is a doubly linked list and also implements `Deque`.
- `Vector` is a legacy synchronized implementation.
- `Stack` is a legacy class that extends `Vector`.

---

# 2. What is List?

`List` is an interface in the:

```java
java.util
```

package.

It extends:

```java
Collection<E>
```

A `List` provides:

- Ordered elements
- Duplicate elements
- Index-based access
- Positional insertion
- Positional removal
- Searching by index
- List-specific iteration

### Example

```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        names.add("Mahesh");
        names.add("Rahul");
        names.add("Mahesh");

        System.out.println(names);
    }
}
```

### Output

```text
[Mahesh, Rahul, Mahesh]
```

The duplicate `"Mahesh"` is allowed.

---

# 3. List Hierarchy

The basic hierarchy is:

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

A simplified view:

```text
                 Iterable
                     |
                     v
                 Collection
                     |
                     v
                    List
          +----------+----------+
          |          |          |
          v          v          v
      ArrayList  LinkedList   Vector
                                |
                                v
                              Stack
```

---

# 4. List Interface Declaration

A simplified version of the `List` interface looks like:

```java
public interface List<E> extends Collection<E> {

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    List<E> subList(int fromIndex, int toIndex);

    ListIterator<E> listIterator();

    ListIterator<E> listIterator(int index);

    // Other methods...
}
```

The methods that make `List` different from the general `Collection` interface include:

```text
get()
set()
add(index, element)
remove(index)
indexOf()
lastIndexOf()
subList()
listIterator()
```

---

# 5. Characteristics of List

A `List` has several important characteristics.

## 5.1 Ordered

A `List` maintains a defined sequence of elements.

```java
List<Integer> numbers = new ArrayList<>();

numbers.add(30);
numbers.add(10);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[30, 10, 20]
```

The order in which elements are stored matters.

---

## 5.2 Allows Duplicates

A `List` allows duplicate elements.

```java
List<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(10);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[10, 10, 20]
```

Each duplicate has its own position.

---

## 5.3 Index-Based Access

Every element has a position called an index.

```text
Index:    0    1    2
          |    |    |
          v    v    v
List:    10   20   30
```

Therefore:

```java
numbers.get(1);
```

returns:

```text
20
```

Java uses **zero-based indexing**.

---

## 5.4 Positional Operations

A `List` allows operations based on indexes.

### Insert

```java
list.add(index, element);
```

### Access

```java
list.get(index);
```

### Update

```java
list.set(index, element);
```

### Remove

```java
list.remove(index);
```

---

# 6. List vs Collection

`List` extends `Collection`.

```text
Collection
    ^
    |
   List
```

The `Collection` interface provides general operations such as:

```text
add()
remove()
contains()
size()
clear()
```

`List` adds positional operations.

| Feature | Collection | List |
|---|---|---|
| Group of elements | Yes | Yes |
| Extends `Iterable` | Yes | Yes |
| Ordered | Not guaranteed | Yes |
| Duplicates | Depends on implementation | Allowed |
| Index-based access | No | Yes |
| `get(index)` | No | Yes |
| `set(index, value)` | No | Yes |
| `indexOf()` | No | Yes |
| `subList()` | No | Yes |
| `ListIterator` | No | Yes |

### Simple Mental Model

```text
Collection
    |
    +-- Common collection operations

List
    |
    +-- Everything from Collection
    |
    +-- Ordering
    +-- Duplicates
    +-- Index-based access
```

---

# 7. Creating a List

There are several ways to create a `List`.

## 7.1 Using ArrayList

```java
List<Integer> numbers = new ArrayList<>();
```

---

## 7.2 Using LinkedList

```java
List<Integer> numbers = new LinkedList<>();
```

---

## 7.3 Using Vector

```java
List<Integer> numbers = new Vector<>();
```

---

## 7.4 Using List.of()

```java
List<Integer> numbers = List.of(10, 20, 30);
```

Remember:

> `List.of()` creates an unmodifiable list.

---

# 8. Adding Elements

The inherited `add()` method adds an element to the end of the list.

```java
List<String> names = new ArrayList<>();

names.add("Mahesh");
names.add("Rahul");
names.add("Amit");

System.out.println(names);
```

Output:

```text
[Mahesh, Rahul, Amit]
```

---

## How `add()` Works

Suppose we have:

```text
[A, B, C]
```

After:

```java
list.add("D");
```

we get:

```text
[A, B, C, D]
```

The new element is added to the end.

---

# 9. Adding Elements at a Specific Index

`List` provides:

```java
add(int index, E element)
```

Example:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 40)
);

numbers.add(2, 30);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30, 40]
```

The element `30` is inserted at index `2`.

---

## Before Insertion

```text
Index:  0   1   2
        |   |   |
        v   v   v
       10  20  40
```

## After Insertion

```text
Index:  0   1   2   3
        |   |   |   |
        v   v   v   v
       10  20  30  40
```

The elements after the insertion point may need to be shifted depending on the implementation.

---

# 10. Accessing Elements

The `get()` method retrieves an element at a specific index.

```java
List<String> names = new ArrayList<>(
        List.of(
                "Mahesh",
                "Rahul",
                "Amit"
        )
);

String name = names.get(1);

System.out.println(name);
```

Output:

```text
Rahul
```

---

## Indexing

Java uses zero-based indexing.

```text
Index:   0        1        2
         |        |        |
         v        v        v
       Mahesh   Rahul     Amit
```

Therefore:

```java
names.get(0);
```

returns:

```text
Mahesh
```

and:

```java
names.get(2);
```

returns:

```text
Amit
```

---

## Invalid Index

Consider:

```java
List<Integer> numbers = List.of(10, 20, 30);

numbers.get(5);
```

This results in:

```text
IndexOutOfBoundsException
```

because valid indexes are:

```text
0, 1, 2
```

---

# 11. Updating Elements

The `set()` method replaces an existing element.

Syntax:

```java
set(int index, E element)
```

Example:

```java
List<String> names = new ArrayList<>(
        List.of(
                "Mahesh",
                "Rahul",
                "Amit"
        )
);

names.set(1, "Rohan");

System.out.println(names);
```

Output:

```text
[Mahesh, Rohan, Amit]
```

---

## `add()` vs `set()`

This is an important distinction.

### `add()`

Inserts a new element.

```java
list.add(1, "X");
```

Starting with:

```text
[A, B, C]
```

Result:

```text
[A, X, B, C]
```

---

### `set()`

Replaces an existing element.

```java
list.set(1, "X");
```

Starting with:

```text
[A, B, C]
```

Result:

```text
[A, X, C]
```

### Remember

```text
add()
    -> Insert

set()
    -> Replace
```

---

# 12. Removing Elements

A `List` has two important `remove()` methods:

```java
remove(int index)
```

and:

```java
remove(Object o)
```

Example:

```java
List<String> names = new ArrayList<>(
        List.of(
                "Java",
                "Spring",
                "Python"
        )
);

names.remove(1);

System.out.println(names);
```

Output:

```text
[Java, Python]
```

The element at index `1` was removed.

---

# 13. Removing by Index vs Removing by Value

This is one of the most important `List` interview topics.

Consider:

```java
List<Integer> numbers = new ArrayList<>(
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

It does **not** mean "remove the value 1".

---

## Removing an Integer by Value

To remove the value `20`:

```java
numbers.remove(Integer.valueOf(20));
```

or:

```java
numbers.remove((Integer) 20);
```

---

## Why Does This Happen?

`List` has overloaded methods:

```java
remove(int index)
```

and:

```java
remove(Object o)
```

When we write:

```java
numbers.remove(1);
```

the argument is an `int`, so Java selects:

```java
remove(int index)
```

To force removal by value:

```java
numbers.remove(Integer.valueOf(1));
```

---

## Interview Example

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30)
);

numbers.remove(1);

System.out.println(numbers);
```

Output:

```text
[10, 30]
```

To remove the value `1`:

```java
numbers.remove(Integer.valueOf(1));
```

---

# 14. Searching in a List

The inherited `contains()` method checks whether an element exists.

```java
List<String> languages = List.of(
        "Java",
        "Spring",
        "Python"
);

System.out.println(
        languages.contains("Java")
);
```

Output:

```text
true
```

For a missing element:

```java
System.out.println(
        languages.contains("C++")
);
```

Output:

```text
false
```

---

# 15. indexOf

`indexOf()` returns the index of the **first occurrence** of an element.

Example:

```java
List<String> languages = List.of(
        "Java",
        "Spring",
        "Java",
        "Python"
);

System.out.println(
        languages.indexOf("Java")
);
```

Output:

```text
0
```

If the element does not exist:

```java
System.out.println(
        languages.indexOf("C++")
);
```

Output:

```text
-1
```

---

## Visual Example

```text
Index:   0       1        2       3
         |       |        |       |
         v       v        v       v
       Java   Spring    Java    Python
         ^
         |
     indexOf("Java")
```

Result:

```text
0
```

---

# 16. lastIndexOf

`lastIndexOf()` returns the index of the **last occurrence**.

Example:

```java
List<String> languages = List.of(
        "Java",
        "Spring",
        "Java",
        "Python"
);

System.out.println(
        languages.lastIndexOf("Java")
);
```

Output:

```text
2
```

### Difference

```text
indexOf()
    -> First occurrence

lastIndexOf()
    -> Last occurrence
```

---

# 17. size

`size()` returns the number of elements in the list.

```java
List<Integer> numbers = List.of(
        10,
        20,
        30,
        40
);

System.out.println(numbers.size());
```

Output:

```text
4
```

---

# 18. isEmpty

`isEmpty()` checks whether the list contains zero elements.

```java
List<Integer> numbers = new ArrayList<>();

System.out.println(numbers.isEmpty());
```

Output:

```text
true
```

After adding an element:

```java
numbers.add(10);

System.out.println(numbers.isEmpty());
```

Output:

```text
false
```

---

# 19. Iterating a List

There are several ways to iterate over a list.

---

## 19.1 Enhanced For Loop

```java
List<Integer> numbers = List.of(
        10,
        20,
        30
);

for (Integer number : numbers) {
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

## 19.2 Traditional For Loop

Because a `List` supports index-based access:

```java
for (int i = 0; i < numbers.size(); i++) {
    System.out.println(numbers.get(i));
}
```

---

## 19.3 Iterator

```java
Iterator<Integer> iterator =
        numbers.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

---

## 19.4 forEach

```java
numbers.forEach(
        System.out::println
);
```

---

# 20. ListIterator

`ListIterator` is a specialized iterator designed specifically for lists.

It extends:

```text
Iterator
```

and provides additional functionality.

Important methods include:

```java
hasNext()
next()

hasPrevious()
previous()

add()
set()
remove()

nextIndex()
previousIndex()
```

---

## Creating a ListIterator

```java
List<String> names = new ArrayList<>(
        List.of(
                "Java",
                "Spring",
                "Python"
        )
);

ListIterator<String> iterator =
        names.listIterator();
```

---

# 21. Forward and Backward Traversal

Unlike a normal `Iterator`, `ListIterator` can traverse a list in both directions.

```text
                Forward
                   -->
[A] ---> [B] ---> [C] ---> [D]
                   <--
                Backward
```

Example:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30)
);

ListIterator<Integer> iterator =
        numbers.listIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

while (iterator.hasPrevious()) {
    System.out.println(iterator.previous());
}
```

Output:

```text
10
20
30
30
20
10
```

The second loop starts from the end because the iterator has already traversed forward.

---

# 22. subList

`subList()` returns a view of a portion of the list.

Syntax:

```java
subList(fromIndex, toIndex)
```

Important rule:

```text
fromIndex -> inclusive
toIndex   -> exclusive
```

Example:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40, 50)
);

List<Integer> sub =
        numbers.subList(1, 4);

System.out.println(sub);
```

Output:

```text
[20, 30, 40]
```

---

## Index Visualization

```text
Index:  0   1   2   3   4
        |   |   |   |   |
        v   v   v   v   v
       10  20  30  40  50
           ^       ^
           |       |
        from=1   to=4
```

The elements included are:

```text
20, 30, 40
```

---

## Important: `subList()` Is a View

`subList()` does not necessarily create an independent copy.

It returns a view backed by the original list.

Example:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40)
);

List<Integer> sub =
        numbers.subList(1, 3);

sub.set(0, 99);

System.out.println(numbers);
```

Output:

```text
[10, 99, 30, 40]
```

The original list was affected.

---

# 23. replaceAll

`replaceAll()` applies an operation to every element in the list.

Example:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(1, 2, 3, 4)
);

numbers.replaceAll(
        number -> number * 2
);

System.out.println(numbers);
```

Output:

```text
[2, 4, 6, 8]
```

---

## Another Example

Convert strings to uppercase:

```java
List<String> languages =
        new ArrayList<>(
                List.of(
                        "java",
                        "spring",
                        "sql"
                )
        );

languages.replaceAll(
        String::toUpperCase
);

System.out.println(languages);
```

Output:

```text
[JAVA, SPRING, SQL]
```

---

# 24. sort

`List` provides:

```java
sort(Comparator<? super E> c)
```

Example:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(30, 10, 20)
);

numbers.sort(
        Integer::compareTo
);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

---

## Descending Order

```java
numbers.sort(
        Comparator.reverseOrder()
);
```

Result:

```text
[30, 20, 10]
```

We will study `Comparator` in detail later.

---

# 25. removeIf

`removeIf()` is inherited from `Collection`.

It removes elements that satisfy a condition.

Example:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(
                10,
                15,
                20,
                25,
                30
        )
);

numbers.removeIf(
        number -> number % 2 != 0
);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

All odd numbers were removed.

---

# 26. addAll

`addAll()` adds all elements from another collection.

```java
List<Integer> first = new ArrayList<>(
        List.of(10, 20)
);

List<Integer> second = List.of(
        30,
        40
);

first.addAll(second);

System.out.println(first);
```

Output:

```text
[10, 20, 30, 40]
```

---

## addAll at a Specific Index

`List` also provides:

```java
addAll(int index, Collection<? extends E> c)
```

Example:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 40)
);

numbers.addAll(
        1,
        List.of(20, 30)
);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30, 40]
```

---

# 27. List.of

Modern Java provides the convenient factory method:

```java
List.of()
```

Example:

```java
List<String> languages = List.of(
        "Java",
        "Spring",
        "SQL"
);
```

This is useful when creating a list with predefined values.

However, the returned list is **unmodifiable**.

This will throw:

```java
languages.add("Python");
```

Exception:

```text
UnsupportedOperationException
```

---

## `List.of()` Does Not Allow `null`

This is invalid:

```java
List<String> names = List.of(
        "Java",
        null
);
```

It throws:

```text
NullPointerException
```

---

# 28. Mutable vs Unmodifiable Lists

Understanding mutability is important.

## Mutable List

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

numbers.add(40);
numbers.set(0, 100);
numbers.remove(Integer.valueOf(20));
```

These operations are allowed.

---

## Unmodifiable List

```java
List<Integer> numbers =
        List.of(10, 20, 30);
```

Operations such as:

```java
nu
