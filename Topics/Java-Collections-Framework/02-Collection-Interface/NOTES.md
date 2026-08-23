# Collection Interface in Java

> `Collection` is the root interface of the Java Collections Framework for representing a group of objects, except for key-value mappings represented by `Map`.

---

## Table of Contents

- [1. Introduction](#1-introduction)
- [2. What is Collection?](#2-what-is-collection)
- [3. Collection Hierarchy](#3-collection-hierarchy)
- [4. Collection Interface Declaration](#4-collection-interface-declaration)
- [5. Collection vs Collections vs Collection Framework](#5-collection-vs-collections-vs-collection-framework)
- [6. Why Does Collection Exist?](#6-why-does-collection-exist)
- [7. Important Methods](#7-important-methods)
- [8. Adding Elements](#8-adding-elements)
- [9. Removing Elements](#9-removing-elements)
- [10. Searching Elements](#10-searching-elements)
- [11. Size and Empty Check](#11-size-and-empty-check)
- [12. Converting Collection to Array](#12-converting-collection-to-array)
- [13. Iterating a Collection](#13-iterating-a-collection)
- [14. `removeIf()`](#14-removeif)
- [15. `containsAll()`](#15-containsall)
- [16. `addAll()`](#16-addall)
- [17. `removeAll()`](#17-removeall)
- [18. `retainAll()`](#18-retainall)
- [19. `clear()`](#19-clear)
- [20. Collection Characteristics](#20-collection-characteristics)
- [21. Collection and Ordering](#21-collection-and-ordering)
- [22. Collection and Duplicates](#22-collection-and-duplicates)
- [23. Collection and `null`](#23-collection-and-null)
- [24. Collection and Generics](#24-collection-and-generics)
- [25. Collection vs Iterable](#25-collection-vs-iterable)
- [26. Collection vs List](#26-collection-vs-list)
- [27. Collection vs Set](#27-collection-vs-set)
- [28. Collection vs Queue](#28-collection-vs-queue)
- [29. Why is Map Separate?](#29-why-is-map-separate)
- [30. Programming to the Collection Interface](#30-programming-to-the-collection-interface)
- [31. Common Mistakes](#31-common-mistakes)
- [32. Interview Takeaways](#32-interview-takeaways)
- [33. Quick Revision](#33-quick-revision)
- [34. Final Mental Model](#34-final-mental-model)

---

# 1. Introduction

The Java Collections Framework provides a standard set of interfaces and implementations for working with groups of objects.

The central interface for groups of individual elements is:

```java
java.util.Collection
```

`Collection` provides common operations such as:

- Adding elements
- Removing elements
- Searching
- Checking size
- Checking emptiness
- Iterating
- Clearing elements

The major interfaces that extend `Collection` are:

```text
Collection
    |
    ├── List
    ├── Set
    └── Queue
```

---

# 2. What is Collection?

`Collection` is an interface that represents a group of objects.

The objects stored inside a collection are called **elements**.

For example:

```java
Collection<String> names = new ArrayList<>();
```

The elements are:

```text
Mahesh
Rahul
Amit
```

We can also use:

```java
Collection<String> names = new HashSet<>();
```

or:

```java
Collection<String> names = new LinkedList<>();
```

The reference type remains:

```java
Collection<String>
```

while the actual implementation can be different.

This is an example of:

> **Programming to an interface rather than a concrete implementation.**

---

# 3. Collection Hierarchy

The basic hierarchy is:

```text
Iterable
    |
    └── Collection
          |
          ├── List
          |
          ├── Set
          |
          └── Queue
```

A more detailed hierarchy:

```text
Iterable
    |
    └── Collection
          |
          ├── List
          │    ├── ArrayList
          │    ├── LinkedList
          │    ├── Vector
          │    └── Stack
          │
          ├── Set
          │    ├── HashSet
          │    ├── LinkedHashSet
          │    └── TreeSet
          │
          └── Queue
               ├── PriorityQueue
               └── Deque
                    └── ArrayDeque
```

Remember:

```text
Collection extends Iterable
```

Therefore every standard `Collection` is iterable.

---

# 4. Collection Interface Declaration

The interface is generic.

A simplified declaration looks like:

```java
public interface Collection<E> extends Iterable<E> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    Object[] toArray();

    <T> T[] toArray(T[] a);

    boolean add(E e);

    boolean remove(Object o);

    boolean containsAll(Collection<?> c);

    boolean addAll(Collection<? extends E> c);

    boolean removeAll(Collection<?> c);

    boolean removeIf(Predicate<? super E> filter);

    boolean retainAll(Collection<?> c);

    void clear();

    // ...
}
```

Here:

```java
E
```

represents the element type.

For example:

```java
Collection<String>
```

means the collection contains `String` elements.

---

# 5. Collection vs Collections vs Collection Framework

These three terms are frequently confused.

## `Collection`

`Collection` is an interface.

```java
Collection<String> names;
```

Fully qualified name:

```java
java.util.Collection
```

---

## `Collections`

`Collections` is a utility class.

```java
java.util.Collections
```

It provides utility methods such as:

```java
Collections.sort()
Collections.reverse()
Collections.shuffle()
Collections.max()
Collections.min()
```

Example:

```java
List<Integer> numbers =
        new ArrayList<>(List.of(30, 10, 20));

Collections.sort(numbers);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

---

## Collection Framework

The Java Collections Framework is the complete system containing:

- Interfaces
- Implementations
- Algorithms
- Iterators
- Utility classes
- Supporting abstractions

Simplified:

```text
Java Collections Framework
        |
        ├── Interfaces
        │    ├── Collection
        │    ├── List
        │    ├── Set
        │    ├── Queue
        │    └── Map
        │
        ├── Implementations
        │    ├── ArrayList
        │    ├── LinkedList
        │    ├── HashSet
        │    ├── HashMap
        │    └── ...
        │
        └── Utilities
             └── Collections
```

---

# 6. Why Does Collection Exist?

Without a common interface, every collection implementation could have a different API.

For example:

```text
ArrayList
    add()
    remove()
    contains()

HashSet
    addElement()
    deleteElement()
    search()

LinkedList
    insert()
    delete()
    find()
```

This would make the API difficult to learn and use.

Instead, Java defines common operations in `Collection`:

```text
Collection
    |
    ├── add()
    ├── remove()
    ├── contains()
    ├── size()
    ├── clear()
    └── ...
```

Different implementations can then provide their own internal mechanisms while following the same basic contract.

---

# 7. Important Methods

| Method | Purpose |
|---|---|
| `add()` | Adds an element |
| `addAll()` | Adds elements from another collection |
| `remove()` | Removes an element |
| `removeAll()` | Removes matching elements |
| `retainAll()` | Keeps matching elements |
| `removeIf()` | Removes elements matching a condition |
| `contains()` | Checks whether an element exists |
| `containsAll()` | Checks whether all specified elements exist |
| `size()` | Returns number of elements |
| `isEmpty()` | Checks whether collection is empty |
| `clear()` | Removes all elements |
| `iterator()` | Returns an iterator |
| `toArray()` | Converts collection to an array |

---

# 8. Adding Elements

The `add()` method adds an element.

```java
Collection<String> names = new ArrayList<>();

names.add("Mahesh");
names.add("Rahul");
names.add("Amit");

System.out.println(names);
```

Output:

```text
[Mahesh, Rahul, Amit]
```

Method:

```java
boolean add(E e);
```

The return value indicates whether the collection changed as a result of the operation.

For a `List`:

```java
List<Integer> numbers = new ArrayList<>();

System.out.println(numbers.add(10));
```

Output:

```text
true
```

For a `Set`, attempting to add a duplicate may not change the collection:

```java
Set<Integer> numbers = new HashSet<>();

System.out.println(numbers.add(10));
System.out.println(numbers.add(10));
```

Output:

```text
true
false
```

The second insertion does not change the set.

---

# 9. Removing Elements

The `remove()` method removes an element.

```java
Collection<String> names =
        new ArrayList<>(
                List.of("Mahesh", "Rahul", "Amit")
        );

names.remove("Rahul");

System.out.println(names);
```

Output:

```text
[Mahesh, Amit]
```

Method:

```java
boolean remove(Object o);
```

It returns `true` if the collection was modified.

---

# 10. Searching Elements

The `contains()` method checks whether an element exists.

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

System.out.println(numbers.contains(20));
```

Output:

```text
true
```

For a missing element:

```java
System.out.println(numbers.contains(50));
```

Output:

```text
false
```

Method:

```java
boolean contains(Object o);
```

---

# 11. Size and Empty Check

## `size()`

Returns the number of elements.

```java
Collection<String> names =
        new ArrayList<>(
                List.of("A", "B", "C")
        );

System.out.println(names.size());
```

Output:

```text
3
```

---

## `isEmpty()`

Checks whether the collection contains no elements.

```java
Collection<String> names = new ArrayList<>();

System.out.println(names.isEmpty());
```

Output:

```text
true
```

For a non-empty collection:

```java
Collection<String> names =
        new ArrayList<>(List.of("Java"));

System.out.println(names.isEmpty());
```

Output:

```text
false
```

---

# 12. Converting Collection to Array

A collection can be converted to an array.

## `toArray()`

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

Object[] array = numbers.toArray();
```

The return type is:

```java
Object[]
```

---

## Typed `toArray()`

We can specify the desired array type:

```java
Integer[] array =
        numbers.toArray(new Integer[0]);
```

Then:

```java
System.out.println(Arrays.toString(array));
```

Output:

```text
[10, 20, 30]
```

---

# 13. Iterating a Collection

Because `Collection` extends `Iterable`, we can use the enhanced `for` loop.

```java
Collection<String> names =
        new ArrayList<>(
                List.of("Mahesh", "Rahul", "Amit")
        );

for (String name : names) {
    System.out.println(name);
}
```

We can also use an `Iterator`:

```java
Iterator<String> iterator =
        names.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

Or use `forEach()`:

```java
names.forEach(System.out::println);
```

---

# 14. `removeIf()`

`removeIf()` removes elements that satisfy a condition.

Example:

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 15, 20, 25, 30)
        );

numbers.removeIf(number -> number % 2 != 0);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

The condition:

```java
number -> number % 2 != 0
```

matches odd numbers.

Therefore, odd numbers are removed.

Method:

```java
boolean removeIf(Predicate<? super E> filter);
```

---

# 15. `containsAll()`

`containsAll()` checks whether all elements of another collection are present.

Example:

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

List<Integer> required =
        List.of(20, 30);

System.out.println(numbers.containsAll(required));
```

Output:

```text
true
```

If one required element is missing:

```java
List<Integer> required =
        List.of(20, 50);

System.out.println(numbers.containsAll(required));
```

Output:

```text
false
```

---

# 16. `addAll()`

`addAll()` adds all elements from another collection.

```java
List<Integer> first =
        new ArrayList<>(
                List.of(10, 20)
        );

List<Integer> second =
        List.of(30, 40);

first.addAll(second);

System.out.println(first);
```

Output:

```text
[10, 20, 30, 40]
```

Method:

```java
boolean addAll(Collection<? extends E> c);
```

---

# 17. `removeAll()`

`removeAll()` removes elements that are also present in another collection.

Example:

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

List<Integer> remove =
        List.of(20, 40);

numbers.removeAll(remove);

System.out.println(numbers);
```

Output:

```text
[10, 30]
```

Think of it as:

```text
Original:
10 20 30 40

Remove:
   20    40

Result:
10    30
```

---

# 18. `retainAll()`

`retainAll()` does the opposite of `removeAll()`.

It keeps only elements that are also present in another collection.

Example:

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

List<Integer> keep =
        List.of(20, 40);

numbers.retainAll(keep);

System.out.println(numbers);
```

Output:

```text
[20, 40]
```

### Remember

```text
removeAll()
    ↓
Remove matching elements

retainAll()
    ↓
Keep matching elements
```

---

# 19. `clear()`

`clear()` removes all elements.

```java
List<Integer> numbers =
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

After calling:

```java
clear()
```

the collection contains no elements.

---

# 20. Collection Characteristics

The `Collection` interface defines a common contract, but it does not define every characteristic of every implementation.

Different collections can have different:

- Ordering rules
- Duplicate rules
- `null` rules
- Performance characteristics
- Thread-safety characteristics

For example:

```text
ArrayList
    Ordered
    Duplicates allowed
    Null allowed

HashSet
    No guaranteed iteration order
    Duplicates not allowed
    One null generally allowed

TreeSet
    Sorted
    Duplicates not allowed
    Null generally not supported with natural ordering
```

Therefore:

> Always distinguish the `Collection` contract from the behavior of a specific implementation.

---

# 21. Collection and Ordering

`Collection` itself does not guarantee a particular iteration order.

Different implementations behave differently.

| Implementation | Ordering |
|---|---|
| `ArrayList` | Insertion order |
| `LinkedList` | Insertion order |
| `HashSet` | No guaranteed order |
| `LinkedHashSet` | Insertion order |
| `TreeSet` | Sorted order |
| `PriorityQueue` | Priority-based queue semantics |

Do not assume ordering based only on the `Collection` interface.

---

# 22. Collection and Duplicates

`Collection` itself does not require all implementations to allow duplicates.

Different implementations behave differently.

| Collection | Duplicates |
|---|---|
| `ArrayList` | Allowed |
| `LinkedList` | Allowed |
| `HashSet` | Not allowed |
| `LinkedHashSet` | Not allowed |
| `TreeSet` | Not allowed |
| `PriorityQueue` | Allowed |

This is an important reason why `List` and `Set` exist as more specific abstractions.

---

# 23. Collection and `null`

The `Collection` interface does not universally specify that `null` must be accepted or rejected.

Different implementations have different rules.

Examples:

| Collection | `null` behavior |
|---|---|
| `ArrayList` | Allows `null` |
| `LinkedList` | Allows `null` |
| `HashSet` | Allows one `null` |
| `TreeSet` | Generally does not support `null` with natural ordering |
| `PriorityQueue` | Does not allow `null` |

Therefore:

> Do not assume that every collection handles `null` in the same way.

---

# 24. Collection and Generics

`Collection` is generic:

```java
Collection<E>
```

For example:

```java
Collection<String> names =
        new ArrayList<>();
```

This means the collection is intended to contain `String` values.

Similarly:

```java
Collection<Integer> numbers =
        new ArrayList<>();
```

means the collection contains `Integer` values.

Generics provide compile-time type safety.

For example:

```java
Collection<String> names =
        new ArrayList<>();

names.add("Java");
names.add("Spring");
```

This is invalid:

```java
names.add(100);
```

because `100` is an `Integer`, not a `String`.

---

# 25. Collection vs Iterable

| Feature | `Iterable` | `Collection` |
|---|---|---|
| Primary purpose | Traversal | Manage a group of elements |
| Package | `java.lang` | `java.util` |
| Provides `iterator()` | Yes | Yes |
| Provides `add()` | No | Yes |
| Provides `remove()` | No | Yes |
| Provides `contains()` | No | Yes |
| Provides `size()` | No | Yes |
| Relationship | Parent abstraction | Extends `Iterable` |

### Mental model

```text
Iterable
    ↓
"Can I traverse it?"

Collection
    ↓
"Can I manage a group of elements?"
```

---

# 26. Collection vs List

`List` extends `Collection`.

```text
Collection
    ↑
   List
```

`List` adds concepts such as:

- Ordering
- Positional access
- Indexes
- Duplicate elements

Example:

```java
List<String> names =
        new ArrayList<>();

names.add("Java");
names.add("Spring");
names.add("Java");
```

Result:

```text
[Java, Spring, Java]
```

We will study `List` in detail next.

---

# 27. Collection vs Set

`Set` also extends `Collection`.

```text
Collection
    ↑
   Set
```

A `Set` represents a collection that does not allow duplicate elements.

Example:

```java
Set<Integer> numbers =
        new HashSet<>();

numbers.add(10);
numbers.add(10);
numbers.add(20);

System.out.println(numbers);
```

The duplicate `10` is not stored twice.

We will study `Set` later in detail.

---

# 28. Collection vs Queue

`Queue` extends `Collection`.

```text
Collection
    ↑
   Queue
```

A queue is designed around elements waiting to be processed.

Common operations include:

```text
Insert
Inspect
Remove
```

Example:

```java
Queue<Integer> queue =
        new LinkedList<>();

queue.offer(10);
queue.offer(20);
queue.offer(30);
```

Queues are useful in:

- BFS
- Scheduling
- Task processing
- Producer-consumer systems
- Message processing

---

# 29. Why is Map Separate?

This is one of the most common Java interview questions.

A `Collection` represents individual elements:

```text
A
B
C
D
```

A `Map` represents key-value associations:

```text
Key → Value

1 → Java
2 → Spring
3 → Python
```

Therefore, `Map` represents a different abstraction.

```text
Collection
    ↓
Individual elements

Map
    ↓
Key-value mappings
```

The hierarchy is:

```text
Iterable
    |
Collection
    |
    ├── List
    ├── Set
    └── Queue
```

while:

```text
Map
 |
 ├── HashMap
 ├── LinkedHashMap
 ├── TreeMap
 ├── Hashtable
 └── ConcurrentHashMap
```

However, a map provides collection views:

```java
map.keySet()
map.values()
map.entrySet()
```

These views can be iterated.

---

# 30. Programming to the Collection Interface

Consider:

```java
ArrayList<Integer> numbers =
        new ArrayList<>();
```

The code is tied to `ArrayList`.

Instead, we can write:

```java
Collection<Integer> numbers =
        new ArrayList<>();
```

Now the variable can reference other `Collection` implementations:

```java
Collection<Integer> numbers =
        new HashSet<>();
```

or:

```java
Collection<Integer> numbers =
        new LinkedList<>();
```

This provides abstraction and reduces coupling.

### Example

```java
void printElements(Collection<Integer> numbers) {

    for (Integer number : numbers) {
        System.out.println(number);
    }
}
```

This method can accept:

```text
ArrayList
LinkedList
HashSet
LinkedHashSet
TreeSet
```

and other `Collection` implementations.

---

# 31. Common Mistakes

## Mistake 1: Thinking `Collection` is a class

`Collection` is an interface.

This is invalid:

```java
Collection<Integer> numbers =
        new Collection<>();
```

Instead:

```java
Collection<Integer> numbers =
        new ArrayList<>();
```

---

## Mistake 2: Confusing `Collection` and `Collections`

Remember:

```t
