# ArrayList - Interview Questions

> Interview-focused revision of `ArrayList`, covering internal working, capacity, resizing, complexity, tricky questions, code-output problems, and real-world scenarios.

---

## Table of Contents

- [1. What is ArrayList?](#1-what-is-arraylist)
- [2. ArrayList Hierarchy](#2-arraylist-hierarchy)
- [3. Why is ArrayList Called a Dynamic Array?](#3-why-is-arraylist-called-a-dynamic-array)
- [4. Internal Working](#4-internal-working)
- [5. Size vs Capacity](#5-size-vs-capacity)
- [6. ArrayList Growth](#6-arraylist-growth)
- [7. Constructors](#7-constructors)
- [8. Important Methods](#8-important-methods)
- [9. Time Complexity](#9-time-complexity)
- [10. Why is get O(1)?](#10-why-is-get-o1)
- [11. Why is add Amortized O(1)?](#11-why-is-add-amortized-o1)
- [12. Why is Insertion O(n)?](#12-why-is-insertion-on)
- [13. Why is Removal O(n)?](#13-why-is-removal-on)
- [14. remove(int) vs remove(Object)](#14-removeint-vs-removeobject)
- [15. ArrayList vs Array](#15-arraylist-vs-array)
- [16. ArrayList vs LinkedList](#16-arraylist-vs-linkedlist)
- [17. ArrayList vs Vector](#17-arraylist-vs-vector)
- [18. Thread Safety](#18-thread-safety)
- [19. Fail-Fast Behavior](#19-fail-fast-behavior)
- [20. ConcurrentModificationException](#20-concurrentmodificationexception)
- [21. Iterator](#21-iterator)
- [22. ListIterator](#22-listiterator)
- [23. ensureCapacity](#23-ensurecapacity)
- [24. trimToSize](#24-trimtosize)
- [25. List.of vs ArrayList](#25-listof-vs-arraylist)
- [26. Arrays.asList vs ArrayList](#26-arraysaslist-vs-arraylist)
- [27. Null and Duplicate Handling](#27-null-and-duplicate-handling)
- [28. Tricky Output Questions](#28-tricky-output-questions)
- [29. Scenario-Based Questions](#29-scenario-based-questions)
- [30. Rapid-Fire Questions](#30-rapid-fire-questions)
- [31. Must-Know Interview Questions](#31-must-know-interview-questions)
- [32. Final Interview Checklist](#32-final-interview-checklist)

---

# 1. What is ArrayList?

## Question

What is `ArrayList` in Java?

## Answer

`ArrayList` is a resizable-array implementation of the `List` interface in the Java Collections Framework.

It provides:

- Ordered elements
- Duplicate elements
- Index-based access
- Dynamic resizing
- Fast random access

Example:

```java
List<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
```

Result:

```text
[10, 20, 30]
```

---

# 2. ArrayList Hierarchy

## Question

What is the hierarchy of `ArrayList`?

## Answer

A simplified hierarchy is:

```text
Object
   |
AbstractCollection
   |
AbstractList
   |
ArrayList
```

Through interfaces:

```text
Iterable
    |
Collection
    |
List
    |
ArrayList
```

`ArrayList` also implements:

```text
RandomAccess
Cloneable
Serializable
```

---

# 3. Why is ArrayList Called a Dynamic Array?

## Question

Why is `ArrayList` called a dynamic array?

## Answer

A normal Java array has a fixed size:

```java
int[] numbers = new int[5];
```

Its length cannot automatically increase.

`ArrayList` uses an internal array but can create a larger array when the current storage becomes insufficient.

Conceptually:

```text
Initial:

[10][20][30]
```

After growth:

```text
[10][20][30][ ][ ]
```

Then:

```text
[10][20][30][40][ ]
```

Therefore, it behaves like a dynamically growing array.

---

# 4. Internal Working

## Question

How does `ArrayList` work internally?

## Answer

Conceptually:

```text
ArrayList
    |
    v
Backing Array
    |
    +----> element 0
    +----> element 1
    +----> element 2
    +----> element 3
```

When there is enough capacity:

```java
list.add(value);
```

places the new element into the next available position.

When the backing array becomes full:

```text
1. Create a larger array
2. Copy existing elements
3. Replace the old backing array
4. Add the new element
```

This resizing operation costs O(n).

---

# 5. Size vs Capacity

## Question

What is the difference between size and capacity?

## Answer

### Size

The number of elements currently stored.

```java
ArrayList<Integer> list =
        new ArrayList<>();

list.add(10);
list.add(20);

System.out.println(list.size());
```

Output:

```text
2
```

### Capacity

The amount of internal storage currently available before another growth operation is required.

---

## Example

```java
ArrayList<Integer> list =
        new ArrayList<>(100);
```

Initially:

```text
size     = 0
capacity = approximately 100
```

The exact internal behavior is implementation-specific.

### Important

This:

```java
new ArrayList<>(100)
```

does **not** create a list containing 100 elements.

It creates an empty list with requested initial capacity.

---

# 6. ArrayList Growth

## Question

What happens when an ArrayList becomes full?

## Answer

When the backing array cannot accommodate another element, `ArrayList` grows its internal storage.

Conceptually:

```text
Before:

[10][20][30][40]
```

Backing storage is full.

A larger array is created:

```text
[10][20][30][40][ ][ ]
```

Existing elements are copied.

Then the new element is added.

---

## Interview Trap

Do not say:

> ArrayList always doubles its capacity.

A better answer is:

> ArrayList grows its backing array when necessary. Modern OpenJDK implementations generally increase the capacity by roughly 50%, but the exact growth policy is an implementation detail.

---

# 7. Constructors

## Question

What constructors does ArrayList provide?

## Answer

The commonly used forms are:

### Default Constructor

```java
ArrayList<Integer> list =
        new ArrayList<>();
```

### Initial Capacity

```java
ArrayList<Integer> list =
        new ArrayList<>(100);
```

### Collection Constructor

```java
List<Integer> source =
        List.of(10, 20, 30);

ArrayList<Integer> list =
        new ArrayList<>(source);
```

---

# 8. Important Methods

| Method | Purpose |
|---|---|
| `add()` | Adds an element |
| `add(index, value)` | Inserts at index |
| `addAll()` | Adds another collection |
| `get()` | Retrieves an element |
| `set()` | Replaces an element |
| `remove(index)` | Removes by index |
| `remove(object)` | Removes by value |
| `contains()` | Searches for an element |
| `indexOf()` | Finds first occurrence |
| `lastIndexOf()` | Finds last occurrence |
| `size()` | Returns number of elements |
| `isEmpty()` | Checks whether empty |
| `clear()` | Removes all elements |
| `removeIf()` | Removes matching elements |
| `replaceAll()` | Replaces all elements |
| `sort()` | Sorts elements |
| `ensureCapacity()` | Requests additional capacity |
| `trimToSize()` | Reduces unused capacity |

---

# 9. Time Complexity

## Question

What are the time complexities of common ArrayList operations?

| Operation | Complexity |
|---|---:|
| `get(index)` | O(1) |
| `set(index, value)` | O(1) |
| `add(value)` | O(1) amortized |
| `add(index, value)` | O(n) |
| `remove(index)` | O(n) |
| `remove(value)` | O(n) |
| `contains()` | O(n) |
| `indexOf()` | O(n) |
| `lastIndexOf()` | O(n) |
| `size()` | O(1) |
| `isEmpty()` | O(1) |
| `clear()` | O(n) |

---

# 10. Why is get O(1)?

## Question

Why is `ArrayList.get(index)` O(1)?

## Answer

Because `ArrayList` stores elements in an array-backed structure.

Consider:

```text
Index:

0     1     2     3     4
|     |     |     |     |
v     v     v     v     v

10    20    30    40    50
```

To access index `3`, the implementation can directly access the corresponding array position.

There is no need to traverse previous elements.

Therefore:

```text
get(index) -> O(1)
```

---

# 11. Why is add Amortized O(1)?

## Question

Why is `ArrayList.add(element)` O(1) amortized instead of always O(1)?

## Answer

Most of the time, adding an element at the end only requires placing it into an available position.

That is:

```text
O(1)
```

But occasionally the backing array becomes full.

Then:

```text
1. Allocate a larger array
2. Copy all existing elements
3. Add the new element
```

That resize operation is:

```text
O(n)
```

However, resizing happens only occasionally.

Across a large sequence of append operations, the average cost per append is:

```text
O(1) amortized
```

---

# 12. Why is Insertion O(n)?

## Question

Why is:

```java
list.add(index, value);
```

generally O(n)?

## Answer

Because elements after the insertion point may need to be shifted.

Example:

```text
Before:

[10][20][30][40][50]
```

Insert `99` at index `2`.

Elements `30`, `40`, and `50` need to move right:

```text
[10][20][99][30][40][50]
```

Therefore:

```text
add(index, value) -> O(n)
```

---

# 13. Why is Removal O(n)?

## Question

Why is removing an element by index generally O(n)?

Consider:

```text
[10][20][30][40][50]
```

Remove `20`.

The remaining elements shift left:

```text
[10][30][40][50]
```

Therefore:

```text
remove(index) -> O(n)
```

The cost depends on how many elements need to be shifted.

---

# 14. remove(int) vs remove(Object)

This is one of the most important ArrayList interview questions.

Consider:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );
```

Now:

```java
numbers.remove(1);
```

Which method is called?

```java
remove(int index)
```

Therefore:

```text
[10, 30]
```

---

## Removing by Value

Use:

```java
numbers.remove(Integer.valueOf(20));
```

Now Java calls:

```java
remove(Object)
```

Result:

```text
[10, 30]
```

---

## Interview Shortcut

Remember:

```text
remove(1)
    -> index

remove(Integer.valueOf(1))
    -> value
```

---

# 15. ArrayList vs Array

| Feature | Array | ArrayList |
|---|---|---|
| Size | Fixed | Dynamic |
| `get()` | Yes | Yes |
| `add()` | No | Yes |
| `remove()` | No | Yes |
| Generics | No | Yes |
| Collection API | No | Yes |
| Primitive types | Yes | No |
| Automatic resizing | No | Yes |

Example array:

```java
int[] numbers = new int[10];
```

Example ArrayList:

```java
ArrayList<Integer> numbers =
        new ArrayList<>();
```

---

## Important

This is invalid:

```java
ArrayList<int> numbers;
```

Use:

```java
ArrayList<Integer> numbers;
```

because Java generics work with reference types.

---

# 16. ArrayList vs LinkedList

## Question

Which is better: ArrayList or LinkedList?

## Answer

There is no universal winner.

It depends on the workload.

| Operation | ArrayList | LinkedList |
|---|---:|---:|
| `get(index)` | O(1) | O(n) |
| `set(index)` | O(1) | O(n) |
| Add at end | O(1) amortized | O(1) |
| Insert by index | O(n) | O(n) |
| Remove by index | O(n) | O(n) |
| Memory overhead | Lower | Higher |
| Cache locality | Better | Worse |
| Random access | Excellent | Poor |

### General Recommendation

For most general-purpose list usage:

```java
ArrayList
```

is usually the better default.

---

## Important Interview Nuance

Do not say:

> LinkedList insertion is always O(1).

A more accurate answer is:

> Inserting or removing a node is O(1) once the node or position is already known, but locating an arbitrary position can take O(n).

---

# 17. ArrayList vs Vector

Both use a dynamically growing array.

The main difference is synchronization and legacy status.

| Feature | ArrayList | Vector |
|---|---|---|
| Dynamic array | Yes | Yes |
| Synchronized by default | No | Yes |
| Legacy | No | Yes |
| Random access | O(1) | O(1) |
| Modern default | Yes | No |

`Vector` is generally considered a legacy collection.

---

# 18. Thread Safety

## Question

Is ArrayList thread-safe?

## Answer

No.

`ArrayList` is not synchronized by default.

This means multiple threads should not concurrently modify the same `ArrayList` without appropriate synchronization.

---

## Possible Solutions

### Synchronized Wrapper

```java
List<Integer> list =
        Collections.synchronizedList(
                new ArrayList<>()
        );
```

### CopyOnWriteArrayList

For appropriate read-heavy concurrent scenarios:

```java
CopyOnWriteArrayList<Integer> list =
        new CopyOnWriteArrayList<>();
```

The correct choice depends on the workload.

---

# 19. Fail-Fast Behavior

## Question

What does fail-fast mean for ArrayList?

## Answer

`ArrayList` iterators are generally fail-fast.

If the list is structurally modified while an iterator is active, the iterator may detect the modification and throw:

```text
ConcurrentModificationException
```

Example:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

for (Integer number : numbers) {

    if (number == 20) {
        numbers.add(40);
    }
}
```

This can result in:

```text
ConcurrentModificationException
```

---

# 20. ConcurrentModificationException

## Question

How can you safely remove elements while iterating?

### Option 1: Iterator

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

### Option 2: removeIf

```java
numbers.removeIf(
        number -> number % 2 == 0
);
```

---

## Important

`ConcurrentModificationException` does not mean:

> Multiple threads are definitely involved.

It can occur in a single-threaded program when a collection is structurally modified while it is being iterated in an unsupported way.

---

# 21. Iterator

## Question

What is an Iterator?

`Iterator` provides a standard way to traverse a collection.

Example:

```java
Iterator<Integer> iterator =
        numbers.iterator();

while (iterator.hasNext()) {
    System.out.println(
            iterator.next()
    );
}
```

It also provides:

```java
remove()
```

which can safely remove the last element returned by the iterator.

---

# 22. ListIterator

`ListIterator` is more powerful than `Iterator`.

It supports:

- Forward traversal
- Backward traversal
- `add()`
- `set()`
- `remove()`
- Index information

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

# 23. ensureCapacity

## Question

What does `ensureCapacity()` do?

It requests that the `ArrayList` have enough internal capacity to hold at least the specified number of elements.

Example:

```java
ArrayList<Integer> numbers =
        new ArrayList<>();

numbers.ensureCapacity(1000);
```

This does **not** mean:

```java
numbers.size() == 1000
```

The size is still:

```text
0
```

---

## When Is It Useful?

When you know the approximate number of elements beforehand.

Example:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(1_000_000);
```

This can reduce repeated growth operations.

---

# 24. trimToSize

## Question

What does `trimToSize()` do?

It requests that the internal capacity be reduced toward the current size.

Example:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(1000);

numbers.add(10);
numbers.add(20);

numbers.trimToSize();
```

Conceptually:

```text
Before:

size     = 2
capacity = 1000

After:

size     = 2
capacity ≈ 2
```

The exact internal representation is implementation-specific.

---

# 25. List.of vs ArrayList

| Feature | `List.of()` | `ArrayList` |
|---|---|---|
| Mutable | No | Yes |
| Add | No | Yes |
| Remove | No | Yes |
| Set | No | Yes |
| Allows null | No | Yes |
| Duplicate values | Yes | Yes |
| Ordering | Yes | Yes |

Example:

```java
List<Integer> list =
        List.of(10, 20, 30);
```

This is unmodifiable.

---

# 26. Arrays.asList vs ArrayList

Consider:

```java
List<String> list =
        Arrays.asList(
                "Java",
                "Spring",
                "SQL"
        );
```

The list is fixed-size.

This is allowed:

```java
list.set(0, "Java 21");
```

But this is not:

```java
list.add("Python");
```

It throws:

```text
UnsupportedOperationException
```

---

## Creating a Mutable ArrayList

Use:

```java
ArrayList<String> list =
        new ArrayList<>(
                Arrays.asList(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );
```

Now:

```java
list.add("Python");
```

works.

---

# 27. Null and Duplicate Handling

## Does ArrayList allow null?

Yes.

```java
ArrayList<String> list =
        new ArrayList<>();

list.add(null);
```

Valid.

Multiple `null` values are also allowed.

---

## Does ArrayList allow duplicates?

Yes.

```java
list.add("Java");
list.add("Java");
```

Result:

```text
[Java, Java]
```

---

## Does ArrayList preserve order?

Yes.

```java
list.add("C");
list.add("A");
list.add("B");
```

Result:

```text
[C, A, B]
```

---

# 28. Tricky Output Questions

## Question 1

What is the output?

```java
ArrayList<Integer> list =
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

## Question 2

What is the output?

```java
ArrayList<Integer> list =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

list.remove(Integer.valueOf(1));

System.out.println(list);
```

### Answer

```text
[10, 20, 30]
```

There is no value `1`, so nothing is removed.

---

## Question 3

What is the output?

```java
ArrayList<Integer> list =
        new ArrayList<>(100);

list.add(10);

System.out.println(list.size());
```

### Answer

```text
1
```

---

## Question 4

What is the output?

```java
ArrayList<String> list =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );

list.set(1, "Hibernate");

System.out.println(list);
```

### Answer

```text
[Java, Hibernate, SQL]
```

---

## Question 5

What is the output?

```java
ArrayList<String> list =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );

list.add(1, "Hibernate");

System.out.println(list);
```

### Answer

```text
[Java, Hibernate, Spring, SQL]
```

---

## Question 6

What is the output?

```java
ArrayList<Integer> list =
        new ArrayList<>();

list.add(10);
list.add(20);
list.add(10);

System.out.println(list.size());
```

### Answer

```text
3
```

Duplicates count as separate elements.

---

## Question 7

What happens?

```java
ArrayList<String> list =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );

for (String value : list) {

    if (value.equals("Spring")) {
        list.remove(value);
    }
}
```

### Answer

This can result in:

```text
ConcurrentModificationException
```

because the list is structurally modified during enhanced `for` iteration.

---

## Question 8

What happens?

```java
ArrayList<String> list =
        new ArrayList<>();

list.add(null);

System.out.println(list);
```

### Answer

```text
[null]
```

---

# 29. Scenario-Based Questions

## Scenario 1

You need fast access using indexes.

Which collection should you choose?

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

You know beforehand that you will add approximately 500,000 elements.

What can you do?

### Answer

Provide an initial capacity:

```java
ArrayList<Integer> list =
        new ArrayList<>(500_000);
```

This can reduce unnecessary resizing.

---

## Scenario 3

You need a mutable list created from another collection.

Use:

```java
ArrayList<Integer> list =
        new ArrayList<>(source);
```

---

## Scenario 4

You need a fixed-size list backed by an array.

One option is:

```java
Arrays.asList(...)
```

---

## Scenario 5

You need an unmodifiab
