# ArrayList in Java

> `ArrayList` is a resizable-array implementation of the `List` interface and is one of the most commonly used collections in Java.

---

## Table of Contents

- [1. Introduction](#1-introduction)
- [2. What is ArrayList?](#2-what-is-arraylist)
- [3. ArrayList Hierarchy](#3-arraylist-hierarchy)
- [4. Why is ArrayList So Important?](#4-why-is-arraylist-so-important)
- [5. Creating an ArrayList](#5-creating-an-arraylist)
- [6. Generic Type](#6-generic-type)
- [7. Internal Structure](#7-internal-structure)
- [8. Dynamic Array Concept](#8-dynamic-array-concept)
- [9. Size vs Capacity](#9-size-vs-capacity)
- [10. How ArrayList Grows](#10-how-arraylist-grows)
- [11. ArrayList Constructors](#11-arraylist-constructors)
- [12. Adding Elements](#12-adding-elements)
- [13. Adding at a Specific Index](#13-adding-at-a-specific-index)
- [14. Accessing Elements](#14-accessing-elements)
- [15. Updating Elements](#15-updating-elements)
- [16. Removing Elements](#16-removing-elements)
- [17. Removing by Index vs Value](#17-removing-by-index-vs-value)
- [18. Adding a Collection](#18-adding-a-collection)
- [19. Accessing the Internal Capacity Concept](#19-accessing-the-internal-capacity-concept)
- [20. ensureCapacity](#20-ensurecapacity)
- [21. trimToSize](#21-trimtosize)
- [22. contains](#22-contains)
- [23. indexOf and lastIndexOf](#23-indexof-and-lastindexof)
- [24. isEmpty and size](#24-isempty-and-size)
- [25. clear](#25-clear)
- [26. Iterating ArrayList](#26-iterating-arraylist)
- [27. Iterator](#27-iterator)
- [28. ListIterator](#28-listiterator)
- [29. forEach](#29-foreach)
- [30. Sorting](#30-sorting)
- [31. replaceAll](#31-replaceall)
- [32. removeIf](#32-removeif)
- [33. ArrayList and null](#33-arraylist-and-null)
- [34. ArrayList and duplicates](#34-arraylist-and-duplicates)
- [35. ArrayList and ordering](#35-arraylist-and-ordering)
- [36. Time Complexity](#36-time-complexity)
- [37. Why get is O(1)](#37-why-get-is-o1)
- [38. Why add is Amortized O(1)](#38-why-add-is-amortized-o1)
- [39. Why Insertion Can Be O(n)](#39-why-insertion-can-be-on)
- [40. Why Removal Can Be O(n)](#40-why-removal-can-be-on)
- [41. ArrayList vs Array](#41-arraylist-vs-array)
- [42. ArrayList vs LinkedList](#42-arraylist-vs-linkedlist)
- [43. ArrayList vs Vector](#43-arraylist-vs-vector)
- [44. ArrayList vs CopyOnWriteArrayList](#44-arraylist-vs-copyonwritearraylist)
- [45. Thread Safety](#45-thread-safety)
- [46. Synchronizing an ArrayList](#46-synchronizing-an-arraylist)
- [47. Fail-Fast Behavior](#47-fail-fast-behavior)
- [48. ConcurrentModificationException](#48-concurrentmodificationexception)
- [49. ArrayList and Memory](#49-arraylist-and-memory)
- [50. Common Mistakes](#50-common-mistakes)
- [51. Best Practices](#51-best-practices)
- [52. Important Methods](#52-important-methods)
- [53. Interview Focus](#53-interview-focus)
- [54. Quick Revision](#54-quick-revision)
- [55. Final Mental Model](#55-final-mental-model)

---

# 1. Introduction

`ArrayList` is one of the most important classes in the Java Collections Framework.

It is a resizable-array implementation of the:

```java
List
```

interface.

Unlike a normal Java array, an `ArrayList` can automatically grow when more elements are added.

Example:

```java
List<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
```

The list can continue growing without manually creating a larger array.

---

# 2. What is ArrayList?

`ArrayList` is a class from:

```java
java.util
```

It implements:

```text
List
RandomAccess
Cloneable
Serializable
```

The important point is that `ArrayList` uses an internal array to store its elements.

Conceptually:

```text
ArrayList
    |
    +-- Internal array
            |
            +-- [10]
            +-- [20]
            +-- [30]
            +-- [40]
```

When the internal array becomes full, `ArrayList` creates a larger array and copies the existing elements into it.

This is what makes it a **dynamic array**.

---

# 3. ArrayList Hierarchy

The important inheritance hierarchy is:

```text
Iterable
    |
Collection
    |
List
    |
ArrayList
```

More specifically:

```text
Object
   |
AbstractCollection
   |
AbstractList
   |
ArrayList
```

And through interfaces:

```text
Iterable
    |
Collection
    |
List
    |
RandomAccess
    |
Cloneable
    |
Serializable
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
                     |
                     v
                 ArrayList
```

---

# 4. Why is ArrayList So Important?

`ArrayList` is important because it provides:

- Dynamic resizing
- Fast random access
- Index-based operations
- Duplicate elements
- Insertion-order preservation
- `null` support
- Good general-purpose performance

Its most important performance characteristic is:

```text
get(index) -> O(1)
```

This makes it very useful when elements are frequently accessed by index.

---

# 5. Creating an ArrayList

## 5.1 Empty ArrayList

```java
ArrayList<Integer> numbers =
        new ArrayList<>();
```

---

## 5.2 Using List Reference

Usually, prefer programming to the interface:

```java
List<Integer> numbers =
        new ArrayList<>();
```

This is generally better than:

```java
ArrayList<Integer> numbers =
        new ArrayList<>();
```

when you only need `List` behavior.

---

## 5.3 With Initial Capacity

```java
ArrayList<Integer> numbers =
        new ArrayList<>(100);
```

This creates an `ArrayList` with an initial capacity of `100`.

Important:

> Capacity is not the same as size.

We will discuss this in detail.

---

## 5.4 From Another Collection

```java
List<Integer> source =
        List.of(10, 20, 30);

ArrayList<Integer> numbers =
        new ArrayList<>(source);
```

Result:

```text
[10, 20, 30]
```

---

# 6. Generic Type

`ArrayList` supports generics.

Example:

```java
ArrayList<String> names =
        new ArrayList<>();
```

This means the list is intended to contain `String` values.

You can add:

```java
names.add("Mahesh");
names.add("Rahul");
```

But this is not allowed:

```java
names.add(100);
```

because `100` is an `Integer`.

---

## Why Use Generics?

Without generics:

```java
ArrayList list =
        new ArrayList();
```

the list can hold different types:

```java
list.add("Java");
list.add(10);
list.add(20.5);
```

This reduces type safety.

With generics:

```java
ArrayList<String> list =
        new ArrayList<>();
```

the compiler catches invalid types.

### Remember

```text
ArrayList<String>
        |
        +-- Type safety
        +-- No explicit casting when retrieving
```

---

# 7. Internal Structure

This is one of the most important ArrayList concepts.

Internally, `ArrayList` uses an array-like structure to store elements.

Conceptually:

```text
ArrayList

size = 4
capacity = 6

Internal array:

Index:    0    1    2    3    4    5
          |    |    |    |    |    |
          v    v    v    v    v    v
        [10] [20] [30] [40] [ ]  [ ]
```

Here:

```text
size = 4
capacity = 6
```

There are four actual elements.

The internal storage has room for six elements.

---

# 8. Dynamic Array Concept

A normal Java array has a fixed size.

Example:

```java
int[] numbers = new int[3];
```

The array can hold exactly:

```text
3 elements
```

It cannot automatically become:

```text
4 elements
```

when you add another element.

`ArrayList` solves this problem.

Conceptually:

```text
Initial array

[10][20][30]
```

When full:

```text
Create larger array

[10][20][30][ ][ ]
```

Copy the old elements:

```text
[10][20][30][ ][ ]
```

Then add the new element:

```text
[10][20][30][40][ ]
```

This process is called **resizing** or **growing**.

---

# 9. Size vs Capacity

This is one of the most important ArrayList interview topics.

## Size

`size()` tells you how many elements are currently stored.

Example:

```java
ArrayList<Integer> numbers =
        new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
```

Then:

```java
numbers.size();
```

returns:

```text
3
```

---

## Capacity

Capacity represents how many elements the current internal storage can hold before another resize is required.

For example:

```text
size     = 3
capacity = 10
```

means:

```text
3 actual elements
7 additional positions available
```

---

## Important Difference

```text
size
    -> Number of actual elements

capacity
    -> Storage available internally
```

### Example

```text
Capacity = 10

[10][20][30][ ][ ][ ][ ][ ][ ][ ]
```

Here:

```text
size = 3
capacity = 10
```

---

# 10. How ArrayList Grows

When an `ArrayList` reaches its capacity and another element must be added, it needs more storage.

Conceptually:

```text
Before:

Capacity = 10

[0][1][2][3][4][5][6][7][8][9]
```

Suppose all positions are occupied.

Adding another element requires resizing.

The implementation creates a larger internal array and copies the old elements.

Modern OpenJDK implementations grow the backing array by roughly 50% when growth is needed.

Conceptually:

```text
Old capacity
    10

New capacity
    ~15
```

The exact implementation details are not part of the `List` contract and should not be relied upon as a language guarantee.

---

## Important Interview Point

Do not say:

> ArrayList always doubles its size.

That is an oversimplification.

A better answer is:

> `ArrayList` grows its internal array when necessary. In modern OpenJDK implementations, the growth is approximately 1.5 times the previous capacity.

---

# 11. ArrayList Constructors

There are three important constructor forms.

---

## 11.1 Default Constructor

```java
ArrayList<Integer> list =
        new ArrayList<>();
```

Creates an empty `ArrayList`.

---

## 11.2 Initial Capacity Constructor

```java
ArrayList<Integer> list =
        new ArrayList<>(100);
```

The initial capacity is `100`.

Important:

```text
size != 100
```

Initially:

```text
size = 0
```

The list simply has room to grow without immediate resizing up to that capacity.

---

## 11.3 Collection Constructor

```java
List<Integer> source =
        List.of(10, 20, 30);

ArrayList<Integer> list =
        new ArrayList<>(source);
```

The new list contains the elements from the supplied collection.

---

# 12. Adding Elements

The basic operation is:

```java
add(E element)
```

Example:

```java
ArrayList<String> names =
        new ArrayList<>();

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

## Adding at the End

```java
list.add("Java");
```

The element is added at the end.

For an `ArrayList`, this is:

```text
O(1) amortized
```

---

# 13. Adding at a Specific Index

Use:

```java
add(int index, E element)
```

Example:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 40)
        );

numbers.add(2, 30);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30, 40]
```

---

## What Happens Internally?

Before:

```text
Index:  0    1    2
        |    |    |
        v    v    v
       10   20   40
```

Insert `30` at index `2`.

The existing element `40` needs to move one position:

```text
Index:  0    1    2    3
        |    |    |    |
        v    v    v    v
       10   20   30   40
```

Therefore insertion at an arbitrary position is generally:

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
ArrayList<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Python",
                        "C++"
                )
        );

System.out.println(
        languages.get(1)
);
```

Output:

```text
Python
```

---

## Why Is `get()` Fast?

Because `ArrayList` uses an array internally.

If the starting memory location is known, the location of an element can be calculated using its index.

Conceptually:

```text
address =
    base address + index × element reference size
```

Therefore, accessing an element does not require traversing previous elements.

```text
get(index)
    -> O(1)
```

---

# 15. Updating Elements

Use:

```java
set(index, element)
```

Example:

```java
ArrayList<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Python",
                        "C++"
                )
        );

languages.set(1, "Spring");

System.out.println(languages);
```

Output:

```text
[Java, Spring, C++]
```

Since the index is directly accessible:

```text
set(index)
    -> O(1)
```

---

# 16. Removing Elements

There are two important `remove()` overloads:

```java
remove(int index)
```

and:

```java
remove(Object object)
```

---

## Remove by Index

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

numbers.remove(1);

System.out.println(numbers);
```

Output:

```text
[10, 30, 40]
```

---

## Remove by Value

```java
numbers.remove(Integer.valueOf(30));
```

This removes the value `30`.

---

# 17. Removing by Index vs Value

This is a classic interview trap.

Consider:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );
```

Then:

```java
numbers.remove(1);
```

means:

```text
Remove element at index 1
```

Result:

```text
[10, 30]
```

To remove the value `1`:

```java
numbers.remove(Integer.valueOf(1));
```

---

## Why?

Because of method overloading:

```java
remove(int)
remove(Object)
```

The literal:

```java
1
```

is an `int`.

Therefore Java chooses:

```java
remove(int)
```

---

# 18. Adding a Collection

`addAll()` adds all elements from another collection.

Example:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20)
        );

List<Integer> extra =
        List.of(30, 40, 50);

numbers.addAll(extra);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30, 40, 50]
```

---

## addAll at an Index

```java
numbers.addAll(
        1,
        List.of(15, 17)
);
```

This inserts all elements starting at the specified index.

---

# 19. Accessing the Internal Capacity Concept

`ArrayList` does not provide a public:

```java
capacity()
```

method.

You can directly access:

```java
size()
```

but not the internal capacity through the normal public API.

For example:

```java
ArrayList<Integer> list =
        new ArrayList<>(100);

System.out.println(list.size());
```

Output:

```text
0
```

Even though the initial capacity was `100`.

---

## Why Is There No Public capacity()?

Capacity is an implementation detail of `ArrayList`.

The public API focuses on the logical contents of the collection.

If application code needs to inspect internal implementation details, that can involve non-public implementation mechanisms and is generally not recommended.

---

# 20. ensureCapacity

`ArrayList` provides:

```java
ensureCapacity(int minCapacity)
```

It can be used to request that the list have enough internal capacity for at least the specified number of elements.

Example:

```java
ArrayList<Integer> numbers =
        new ArrayList<>();

numbers.ensureCapacity(1000);
```

This is useful when you know approximately how many elements will be added.

---

## Why Use ensureCapacity?

Suppose you know you will add:

```text
1,000,000 elements
```

If the list repeatedly grows, multiple reallocations and copies may occur.

You can provide an appropriate capacity ahead of time:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(1_000_000);
```

or:

```java
numbers.ensureCapacity(1_000_000);
```

This can reduce unnecessary resizing.

---

## Important

`ensureCapacity()` does not change:

```java
size()
```

Example:

```java
ArrayList<Integer> list =
        new ArrayList<>();

list.ensureCapacity(1000);

System.out.println(list.size());
```

Output:

```text
0
```

Capacity and size are different concepts.

---

# 21. trimToSize

`trimToSize()` reduces the internal capacity to the current size.

Example:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(100);

numbers.add(10);
numbers.add(20);
numbers.add(30);

numbers.trimToSize();
```

Conceptually:

```text
Before:

size     = 3
capacity = 100

After:

size     = 3
capacity ≈ 3
```

The exact internal representation remains an implementation detail.

---

## Why Use trimToSize?

It can reduce unused internal storage when the list is no longer expected to grow significantly.

However, it should not be used unnecessarily.

---

# 22. contains

`contains()` checks whether an element exists.

Example:

```java
ArrayList<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Spring",
                        "Python"
                )
        );

System.out.println(
        languages.contains("Java")
);
```

Output:

```text
true
```

---

## Complexity

For `ArrayList`:

```text
contains()
    -> O(n)
```

because it may need to scan the elements.

---

# 23. indexOf and lastIndexOf

## indexOf

Returns the first occurrence.

```java
ArrayList<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Spring",
                        "Java"
                )
        );

System.out.println(
        languages.indexOf("Java")
);
```

Output:

```text
0
```

---

## lastIndexOf

Returns the last occurrence.

```java
System.out.println(
        languages.lastIndexOf("Java")
);
```

Output:

```text
2
```

Both operations are generally:

```text
O(n)
```

---

# 24. isEmpty and size

## size

Returns the number of actual elements.

```java
ArrayList<Integer> numbers =
        new ArrayList<>();

numbers.add(10);
numbers.add(20);

System.out.println(numbers.size());
```

Output:

```text
2
```

---

## isEmpty

Checks whether the list contains no elements.

```java
System.out.println(
        numbers.isEmpty()
);
```

Output:

```text
false
```

---

# 25. clear

`clear()` removes all elements.

Example:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

numbers.clear();

System.out.println(numbers);
```

Output:

```text
[]
```

After:

```java
clear()
```

the logical size becomes:

```text
0
```

The exact backing-array capacity is an implementation detail.

---

# 26. Iterating ArrayList

There are several ways to iterate over an `ArrayList`.

---

## Enhanced For Loop

```java
for (Integer number : numbers) {
    System.out.println(number);
}
```

---

## Traditional For Loop

```java
for (int i = 0; i < numbers.size(); i++) {
    System.out.println(numbers.get(i));
}
```

Because `ArrayList.get()` is O(1), index-based iteration is efficient.

---

## Iterator

```java
Iterator<Integer> iterator =
        numbers.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

---

## ListIterator

```java
ListIterator<Integer> iterator =
        numbers.listIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

---

## forEach

```java
numbers.forEach(
        System.out::println
);
```

---

# 27. Iterator

`ArrayList` provides an `Iterator`.

Example:

```java
ArrayList<String> names =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );

Iterator<String> iterator =
        names.iterator();

while (iterator.hasNext()) {
    String name = iterator.next();

    System.out.println(name);
}
```

---

## Removing During Iteration

If you need to safely remove elements while using an `Iterator`, use:

```java
iterator.remove();
```

Example:

```java
It
