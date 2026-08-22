# Iterable in Java

> `Iterable` is the root interface that provides a standard way to traverse elements one by one using an `Iterator`.

---

## Table of Contents

- [1. What is Iterable?](#1-what-is-iterable)
- [2. Why Does Iterable Exist?](#2-why-does-iterable-exist)
- [3. Iterable Interface](#3-iterable-interface)
- [4. Generic Type `<T>`](#4-generic-type-t)
- [5. `iterator()` Method](#5-iterator-method)
- [6. What is an Iterator?](#6-what-is-an-iterator)
- [7. Iterable vs Iterator](#7-iterable-vs-iterator)
- [8. Enhanced For Loop](#8-enhanced-for-loop)
- [9. How the Enhanced For Loop Works](#9-how-the-enhanced-for-loop-works)
- [10. `forEach()`](#10-foreach)
- [11. `spliterator()`](#11-spliterator)
- [12. Custom Iterable](#12-custom-iterable)
- [13. Iterable vs Collection](#13-iterable-vs-collection)
- [14. Important Collection Hierarchy](#14-important-collection-hierarchy)
- [15. Why Map is Different](#15-why-map-is-different)
- [16. Iterator Removal](#16-iterator-removal)
- [17. Multiple Iterators](#17-multiple-iterators)
- [18. Programming to an Interface](#18-programming-to-an-interface)
- [19. Common Mistakes](#19-common-mistakes)
- [20. Interview Questions](#20-interview-questions)
- [21. Quick Revision](#21-quick-revision)
- [22. Final Mental Model](#22-final-mental-model)

---

# 1. What is Iterable?

`Iterable` is a generic interface in the `java.lang` package.

Its fully qualified name is:

```java
java.lang.Iterable
```

The primary purpose of `Iterable` is to provide a standard mechanism for traversing the elements of an object.

The most important method is:

```java
iterator()
```

which returns an `Iterator`.

### Simple definition

> **`Iterable` represents an object whose elements can be traversed one by one.**

---

# 2. Why Does Iterable Exist?

Different data structures store elements differently.

For example:

| Data Structure | Internal Representation |
|---|---|
| `ArrayList` | Dynamic array |
| `LinkedList` | Linked nodes |
| `HashSet` | Hash table |
| `TreeSet` | Tree-based structure |

Even though their internal implementations are different, we want a common way to traverse their elements.

Java solves this problem using `Iterable`.

```text
Iterable
    |
    | iterator()
    ↓
Iterator
    |
    ↓
Traverse elements
```

This gives different data structures a common traversal contract.

---

# 3. Iterable Interface

A simplified version of the `Iterable` interface looks like this:

```java
public interface Iterable<T> {

    Iterator<T> iterator();

    default void forEach(Consumer<? super T> action) {
        // ...
    }

    default Spliterator<T> spliterator() {
        // ...
    }
}
```

The important methods are:

| Method | Purpose |
|---|---|
| `iterator()` | Returns an `Iterator` |
| `forEach()` | Performs an action for each element |
| `spliterator()` | Provides traversal and partitioning support |

The most fundamental method is:

```java
iterator()
```

---

# 4. Generic Type `<T>`

`Iterable` is a generic interface:

```java
Iterable<T>
```

Here, `T` represents the type of elements being iterated.

For example:

```java
Iterable<String>
```

means the iterable contains `String` elements.

Similarly:

```java
Iterable<Integer>
```

means the iterable contains `Integer` elements.

Example:

```java
List<String> names = new ArrayList<>();
```

Here, the list is iterable over `String` objects.

---

# 5. `iterator()` Method

The most important method of `Iterable` is:

```java
Iterator<T> iterator();
```

It returns an object of type:

```java
Iterator<T>
```

Example:

```java
List<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

Iterator<Integer> iterator = numbers.iterator();
```

Now the `iterator` can be used to traverse the elements.

---

# 6. What is an Iterator?

`Iterator` is an interface used to traverse elements.

It belongs to:

```java
java.util
```

Some important methods of `Iterator` are:

```java
hasNext()
next()
remove()
```

The relationship between `Iterable` and `Iterator` is:

```text
Iterable
    |
    | iterator()
    ↓
Iterator
    |
    ├── hasNext()
    ├── next()
    └── remove()
```

### Easy way to remember

> **Iterable gives you an Iterator.**

> **Iterator performs the traversal.**

---

# 7. Iterable vs Iterator

This is one of the most important distinctions in the Collections Framework.

| Feature | `Iterable` | `Iterator` |
|---|---|---|
| Purpose | Provides an iterator | Performs traversal |
| Important method | `iterator()` | `hasNext()`, `next()`, `remove()` |
| Represents | Object that can be traversed | Current traversal state |
| Package | `java.lang` | `java.util` |
| Used by enhanced `for` | Yes | Internally |

### Mental model

```text
Iterable
    ↓
provides
    ↓
Iterator
    ↓
traverses
    ↓
Elements
```

---

# 8. Enhanced For Loop

One of the biggest reasons `Iterable` is important is the enhanced `for` loop.

Example:

```java
List<String> names = List.of(
    "Mahesh",
    "Rahul",
    "Amit"
);

for (String name : names) {
    System.out.println(name);
}
```

The enhanced `for` loop provides a simple syntax for traversing elements.

For iterable objects, Java uses an iterator-based mechanism behind the scenes.

---

# 9. How the Enhanced For Loop Works

Consider:

```java
for (String name : names) {
    System.out.println(name);
}
```

Conceptually, this is similar to:

```java
Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {

    String name = iterator.next();

    System.out.println(name);
}
```

So the mental model is:

```text
Enhanced for-loop
        ↓
    iterator()
        ↓
    Iterator
        ↓
   hasNext()
        ↓
     next()
        ↓
    Element
```

This is an extremely important interview concept.

---

# 10. `forEach()`

`Iterable` also provides a default `forEach()` method.

Example:

```java
List<String> names = List.of(
    "Mahesh",
    "Rahul",
    "Amit"
);

names.forEach(name -> {
    System.out.println(name);
});
```

Since the operation is simple, we can use a method reference:

```java
names.forEach(System.out::println);
```

### Lambda version

```java
names.forEach(name -> System.out.println(name));
```

### Method reference version

```java
names.forEach(System.out::println);
```

Both perform the same basic operation.

---

# 11. `spliterator()`

`Iterable` also provides:

```java
Spliterator<T> spliterator()
```

`Spliterator` is designed for:

- Traversing elements
- Splitting elements
- Stream processing
- Parallel processing

Example:

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5);

Spliterator<Integer> spliterator = numbers.spliterator();
```

`Spliterator` becomes particularly important when learning:

- Java Stream API
- Parallel Streams
- Parallel processing

We will study it separately later.

---

# 12. Custom Iterable

We can create our own class that implements `Iterable`.

Example:

```java
import java.util.Iterator;

class NumberCollection implements Iterable<Integer> {

    private final int[] numbers = {10, 20, 30, 40};

    @Override
    public Iterator<Integer> iterator() {

        return new Iterator<Integer>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < numbers.length;
            }

            @Override
            public Integer next() {
                return numbers[index++];
            }
        };
    }
}
```

Now we can use the class with the enhanced `for` loop:

```java
public class Main {

    public static void main(String[] args) {

        NumberCollection numbers = new NumberCollection();

        for (Integer number : numbers) {
            System.out.println(number);
        }
    }
}
```

### Output

```text
10
20
30
40
```

The important part is:

```java
implements Iterable<Integer>
```

and:

```java
@Override
public Iterator<Integer> iterator()
```

By implementing this contract, our custom class becomes usable with the enhanced `for` loop.

---

# 13. Iterable vs Collection

A common misconception is that `Iterable` and `Collection` are the same thing.

They are not.

`Collection` extends `Iterable`.

```text
Iterable
    ↑
Collection
```

### Comparison

| Feature | `Iterable` | `Collection` |
|---|---|---|
| Primary purpose | Traversal | Manage a group of elements |
| Package | `java.lang` | `java.util` |
| Parent | None | `Iterable` |
| Main method | `iterator()` | `add()`, `remove()`, `contains()`, etc. |
| Enhanced `for` | Yes | Yes |
| Must represent a collection | No | Yes |

### Mental model

```text
Iterable
    ↓
"Can I iterate over it?"

Collection
    ↓
"Can I manage a group of elements?"
```

---

# 14. Important Collection Hierarchy

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

A more detailed view:

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

> **`Collection` extends `Iterable`.**

---

# 15. Why `Map` Is Different

`Map` does **not** extend `Collection`.

The collection hierarchy is:

```text
Iterable
    |
Collection
    |
    ├── List
    ├── Set
    └── Queue
```

The map hierarchy is separate:

```text
Map
 |
 ├── HashMap
 ├── LinkedHashMap
 ├── TreeMap
 ├── Hashtable
 └── ConcurrentHashMap
```

However, maps provide collection views:

```java
map.keySet()
map.values()
map.entrySet()
```

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Java");
map.put(2, "Spring");

for (Map.Entry<Integer, String> entry : map.entrySet()) {

    System.out.println(
        entry.getKey() + " = " + entry.getValue()
    );
}
```

`entrySet()` provides a collection view that can be iterated.

This becomes very important when we study `Map` and `HashMap`.

---

# 16. Iterator Removal

`Iterator` provides a `remove()` method.

Example:

```java
List<Integer> numbers =
        new ArrayList<>(List.of(10, 20, 30, 40));

Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {

    Integer number = iterator.next();

    if (number == 20) {
        iterator.remove();
    }
}
```

After execution:

```text
[10, 30, 40]
```

The important point is that the removal is performed through the iterator.

---

# 17. Why `Iterator.remove()` Matters

Consider:

```java
for (Integer number : numbers) {

    if (number == 20) {
        numbers.remove(number);
    }
}
```

Structural modification of many collections while iterating through them can cause:

```text
ConcurrentModificationException
```

A standard iterator-based approach is:

```java
Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {

    Integer number = iterator.next();

    if (number == 20) {
        iterator.remove();
    }
}
```

We will study fail-fast behavior and `ConcurrentModificationException` in more detail when we cover concrete collection implementations.

---

# 18. Multiple Iterators

A collection can create multiple iterators.

Example:

```java
List<Integer> numbers = List.of(10, 20, 30);

Iterator<Integer> first = numbers.iterator();
Iterator<Integer> second = numbers.iterator();
```

Conceptually:

```text
Collection
   |
   ├── Iterator 1 → 10 → 20 → 30
   |
   └── Iterator 2 → 10 → 20 → 30
```

Each iterator maintains its own traversal state.

Advancing one iterator does not automatically advance the other.

---

# 19. Programming to an Interface

`Iterable` demonstrates an important Java design principle:

> **Program to an interface, not an implementation.**

Instead of requiring:

```java
ArrayList<Integer>
```

when all we need is traversal, we can accept:

```java
Iterable<Integer>
```

Example:

```java
void printAll(Iterable<Integer> data) {

    for (Integer value : data) {
        System.out.println(value);
    }
}
```

This method can work with:

- `ArrayList`
- `LinkedList`
- `HashSet`
- `TreeSet`
- Custom `Iterable` implementations

as long as they implement:

```java
Iterable<Integer>
```

---

# 20. Common Mistakes

## Mistake 1: Thinking `Iterable` stores elements

`Iterable` does not define a general-purpose storage API.

Its primary purpose is traversal.

---

## Mistake 2: Confusing `Iterable` and `Iterator`

Remember:

```text
Iterable → provides an Iterator

Iterator → performs traversal
```

---

## Mistake 3: Thinking `Map` extends `Collection`

It does not.

```text
Collection
├── List
├── Set
└── Queue

Map
├── HashMap
├── LinkedHashMap
└── TreeMap
```

They are separate hierarchies.

---

## Mistake 4: Thinking every `Iterable` must be a `Collection`

False.

A class can directly implement:

```java
Iterable<Integer>
```

without implementing `Collection`.

---

## Mistake 5: Thinking enhanced `for` works only with collections

False.

The enhanced `for` statement also works with arrays.

For objects, the iterable mechanism is based on `Iterable`.

> **Note:** Arrays do not implement `Iterable`.

---

# 21. Interview Questions

### Basic

1. What is `Iterable` in Java?
2. Which package contains `Iterable`?
3. What is the main method of `Iterable`?
4. What does `iterator()` return?
5. What is the relationship between `Iterable` and `Iterator`?
6. Does `Collection` extend `Iterable`?
7. Does `Map` extend `Collection`?
8. Can a custom class implement `Iterable`?

### Intermediate

9. How does the enhanced `for` loop work?
10. What is the difference between `Iterable` and `Iterator`?
11. Why does `ArrayList` support the enhanced `for` loop?
12. Why does `HashSet` support the enhanced `for` loop?
13. What is the purpose of `forEach()`?
14. What is `Spliterator`?
15. Can an object implement `Iterable` without implementing `Collection`?
16. Can multiple iterators exist for the same collection?

### Advanced

17. How does iterator-based removal differ from direct collection removal?
18. What is fail-fast iteration?
19. Why can modifying a collection during iteration cause `ConcurrentModificationException`?
20. How would you make a custom data structure usable with the enhanced `for` loop?
21. Why is `Iterable` considered a useful abstraction?
22. Why is `Map` separate from the `Collection` hierarchy?

---

# 22. Quick Revision

| Question | Answer |
|---|---|
| What is `Iterable`? | Interface for traversable objects |
| Package | `java.lang` |
| Main method | `iterator()` |
| Return type | `Iterator<T>` |
| `Collection` extends `Iterable`? | Yes |
| `Map` extends `Collection`? | No |
| Can custom classes implement it? | Yes |
| Used by enhanced `for`? | Yes |
| Important related interface | `Iterator` |
| Other methods | `forEach()`, `spliterator()` |

---

# 23. Final Mental Model

```text
                         Iterable
                            |
                            | iterator()
                            ↓
                         Iterator
                            |
                 ┌──────────┼──────────┐
                 ↓          ↓          ↓
             hasNext()    next()     remove()
                            |
                            ↓
                         Elements
```

Collection hierarchy:

```text
                         Iterable
                            ↑
                            |
                        Collection
                            |
              ┌─────────────┼─────────────┐
              ↓             ↓             ↓
             List           Set          Queue
              ↓             ↓             ↓
          ArrayList       HashSet     PriorityQueue
          LinkedList      TreeSet         Deque
          Vector          LinkedHashSet      ↓
          Stack                           ArrayDeque
```

Map is separate:

```text
                           Map
                            |
              ┌─────────────┼─────────────┐
              ↓             ↓             ↓
           HashMap      LinkedHashMap   TreeMap
              |
         ConcurrentHashMap
```

---

# 24. Interview One-Liner

> **`Iterable` is the interface that provides the `iterator()` method, allowing an object to be traversed using an `Iterator` and enabling the enhanced `for` loop for iterable objects.**

---

# 25. Status

```text
Java Collections Framework
│
├── 01-Iterable              ✅
├── 02-Collection Interface  ⏳
├── 03-List                  ⏳
├── 04-ArrayList             ⏳ ⭐⭐⭐⭐⭐
├── 05-LinkedList            ⏳
├── 06-Vector                ⏳
├── 07-Stack                 ⏳
├── 08-Queue                 ⏳
├── 09-PriorityQueue         ⏳
├── 10-Deque                 ⏳
├── 11-ArrayDeque            ⏳
├── 12-Set                   ⏳
├── 13-HashSet               ⏳ ⭐⭐⭐⭐⭐
├── 14-LinkedHashSet         ⏳
├── 15-TreeSet               ⏳
├── 16-Map                   ⏳
├── 17-HashMap               ⏳ ⭐⭐⭐⭐⭐
├── 18-LinkedHashMap         ⏳
├── 19-TreeMap               ⏳
├── 20-Hashtable             ⏳
├── 21-ConcurrentHashMap     ⏳ ⭐⭐⭐⭐⭐
├── 22-Comparable            ⏳
└── 23-Comparator             ⏳
```

---

## Key Takeaway

```text
Iterable
    ↓
iterator()
    ↓
Iterator
    ↓
hasNext() + next()
    ↓
Traverse elements
```

**Remember:**

> `Iterable` tells Java **"I can be iterated."**

> `Iterator` tells Java **"Here is how you traverse me."**
