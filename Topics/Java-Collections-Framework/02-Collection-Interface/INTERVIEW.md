# Collection Interface - Interview Questions

> Interview-focused questions covering the `Collection` interface, its methods, hierarchy, contracts, generics, polymorphism, performance, and common misconceptions.

---

## Table of Contents

- [Basic Questions](#basic-questions)
- [Method-Based Questions](#method-based-questions)
- [Hierarchy Questions](#hierarchy-questions)
- [Intermediate Questions](#intermediate-questions)
- [Advanced Questions](#advanced-questions)
- [Code-Based Questions](#code-based-questions)
- [Tricky Questions](#tricky-questions)
- [Rapid Fire Revision](#rapid-fire-revision)
- [Most Important Interview Questions](#most-important-interview-questions)
- [Interview Checklist](#interview-checklist)

---

# Basic Questions

## 1. What is the `Collection` interface?

`Collection` is an interface in the Java Collections Framework that represents a group of individual elements.

It provides common operations such as:

- Adding elements
- Removing elements
- Searching
- Checking size
- Clearing
- Iterating

It is located in:

```java
java.util
```

---

## 2. Is `Collection` a class or an interface?

`Collection` is an interface.

Therefore, this is invalid:

```java
Collection<Integer> numbers =
        new Collection<>();
```

Instead, we create an object of a concrete implementation:

```java
Collection<Integer> numbers =
        new ArrayList<>();
```

---

## 3. Which package contains `Collection`?

```java
java.util
```

Fully qualified name:

```java
java.util.Collection
```

---

## 4. What interface does `Collection` extend?

`Collection` extends:

```java
Iterable
```

Relationship:

```text
Iterable
    ↑
Collection
```

Therefore, every `Collection` is an `Iterable`.

---

## 5. Which interfaces extend `Collection`?

The major interfaces are:

```text
Collection
    |
    ├── List
    ├── Set
    └── Queue
```

These interfaces provide more specific behavior.

---

## 6. What is the difference between `Collection` and `Collections`?

This is a common interview question.

### `Collection`

An interface:

```java
java.util.Collection
```

It represents a group of elements.

### `Collections`

A utility class:

```java
java.util.Collections
```

It provides utility algorithms and methods.

Examples:

```java
Collections.sort()
Collections.reverse()
Collections.shuffle()
Collections.max()
Collections.min()
```

### Easy way to remember

```text
Collection
    ↓
Interface

Collections
    ↓
Utility class
```

---

## 7. Can we instantiate `Collection` directly?

No.

`Collection` is an interface.

We need a concrete implementation:

```java
Collection<Integer> numbers =
        new ArrayList<>();
```

or:

```java
Collection<Integer> numbers =
        new HashSet<>();
```

---

## 8. Why is `Collection` useful?

It provides a common contract for different collection implementations.

For example:

```java
Collection<Integer> numbers;
```

can reference:

```java
new ArrayList<>()
new LinkedList<>()
new HashSet<>()
new TreeSet<>()
```

This supports abstraction and polymorphism.

---

# Method-Based Questions

## 9. What does `add()` do?

`add()` adds an element to the collection.

```java
boolean add(E e);
```

Example:

```java
Collection<Integer> numbers =
        new ArrayList<>();

numbers.add(10);
numbers.add(20);
```

The return value indicates whether the collection changed.

---

## 10. Why does `add()` return `boolean`?

The return value tells us whether the collection was modified.

For example, with a `Set`:

```java
Set<Integer> numbers =
        new HashSet<>();

System.out.println(numbers.add(10));
System.out.println(numbers.add(10));
```

Output:

```text
true
false
```

The second `10` does not change the set.

---

## 11. What does `remove()` do?

It removes an element from the collection.

```java
boolean remove(Object o);
```

Example:

```java
Collection<String> names =
        new ArrayList<>(
                List.of("Java", "Python", "C++")
        );

names.remove("Python");
```

---

## 12. What does `contains()` do?

It checks whether an element exists.

```java
boolean contains(Object o);
```

Example:

```java
Collection<Integer> numbers =
        List.of(10, 20, 30);

System.out.println(
        numbers.contains(20)
);
```

Output:

```text
true
```

---

## 13. What does `size()` return?

It returns the number of elements currently contained in the collection.

```java
int size();
```

Example:

```java
Collection<Integer> numbers =
        List.of(10, 20, 30);

System.out.println(numbers.size());
```

Output:

```text
3
```

---

## 14. What does `isEmpty()` do?

It checks whether the collection contains zero elements.

```java
boolean isEmpty();
```

Example:

```java
Collection<Integer> numbers =
        new ArrayList<>();

System.out.println(numbers.isEmpty());
```

Output:

```text
true
```

---

## 15. What does `clear()` do?

It removes all elements from the collection.

```java
void clear();
```

Example:

```java
Collection<Integer> numbers =
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

---

## 16. What does `addAll()` do?

It adds all elements from another collection.

```java
boolean addAll(
        Collection<? extends E> c
);
```

Example:

```java
Collection<Integer> first =
        new ArrayList<>(
                List.of(10, 20)
        );

Collection<Integer> second =
        List.of(30, 40);

first.addAll(second);
```

Result:

```text
[10, 20, 30, 40]
```

---

## 17. What does `removeAll()` do?

It removes elements from the current collection that are also contained in the specified collection.

Example:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

numbers.removeAll(
        List.of(20, 40)
);
```

Result:

```text
[10, 30]
```

---

## 18. What does `retainAll()` do?

It keeps only elements that are also present in the specified collection.

Example:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

numbers.retainAll(
        List.of(20, 40)
);
```

Result:

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

## 19. What does `containsAll()` do?

It checks whether all elements of another collection are present.

Example:

```java
Collection<Integer> numbers =
        List.of(10, 20, 30, 40);

boolean result =
        numbers.containsAll(
                List.of(20, 30)
        );
```

Result:

```text
true
```

---

## 20. What does `removeIf()` do?

`removeIf()` removes every element that satisfies a given predicate.

Example:

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 15, 20, 25, 30)
        );

numbers.removeIf(
        number -> number % 2 != 0
);
```

Result:

```text
[10, 20, 30]
```

---

## 21. What is the difference between `removeAll()` and `removeIf()`?

### `removeAll()`

Removes elements based on membership in another collection.

```java
numbers.removeAll(
        List.of(10, 20)
);
```

### `removeIf()`

Removes elements based on a condition.

```java
numbers.removeIf(
        number -> number % 2 == 0
);
```

### Comparison

| `removeAll()` | `removeIf()` |
|---|---|
| Uses another collection | Uses a predicate |
| Removes matching elements | Removes elements satisfying condition |
| Membership-based | Condition-based |

---

# Hierarchy Questions

## 22. What is the hierarchy of `Collection`?

```text
Iterable
    |
Collection
    |
    ├── List
    ├── Set
    └── Queue
```

---

## 23. Is `Map` a subtype of `Collection`?

No.

`Map` is a separate hierarchy.

```text
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

---

## 24. Why is `Map` not part of the `Collection` hierarchy?

Because the abstractions are different.

A `Collection` represents individual elements:

```text
A
B
C
```

A `Map` represents mappings:

```text
Key → Value
```

For example:

```text
101 → Mahesh
102 → Rahul
103 → Amit
```

Therefore, `Map` requires a different interface design.

---

## 25. Is every `List` a `Collection`?

Yes.

```text
Collection
    ↑
   List
```

Therefore:

```java
List<Integer> numbers =
        new ArrayList<>();
```

can also be treated as:

```java
Collection<Integer>
```

---

## 26. Is every `Set` a `Collection`?

Yes.

```text
Collection
    ↑
   Set
```

For example:

```java
Collection<Integer> numbers =
        new HashSet<>();
```

is valid.

---

## 27. Is every `Queue` a `Collection`?

Yes.

```text
Collection
    ↑
   Queue
```

For example:

```java
Collection<Integer> queue =
        new LinkedList<>();
```

is valid.

---

# Intermediate Questions

## 28. Does `Collection` guarantee ordering?

No.

`Collection` does not guarantee a specific iteration order.

The concrete implementation determines the ordering behavior.

Examples:

```text
ArrayList
    → insertion order

LinkedHashSet
    → insertion order

TreeSet
    → sorted order

HashSet
    → no guaranteed iteration order
```

---

## 29. Does `Collection` allow duplicate elements?

The `Collection` interface itself does not impose one universal duplicate policy.

Different implementations behave differently.

```text
ArrayList
    → duplicates allowed

HashSet
    → duplicates not allowed
```

More specific interfaces such as `List` and `Set` define stronger contracts.

---

## 30. Does `Collection` allow `null`?

The interface does not require every implementation to accept or reject `null`.

Examples:

```text
ArrayList
    → allows null

HashSet
    → allows one null

TreeSet
    → generally does not support null with natural ordering

PriorityQueue
    → does not allow null
```

Always check the specific implementation.

---

## 31. Why is `Collection` generic?

Generics provide compile-time type safety.

Example:

```java
Collection<String> names =
        new ArrayList<>();
```

Now:

```java
names.add("Java");
```

is valid.

But:

```java
names.add(100);
```

is rejected by the compiler.

Without generics, we could accidentally store incompatible types and require casts when retrieving them.

---

## 32. What is the meaning of `Collection<E>`?

`E` represents the element type.

For example:

```java
Collection<String>
```

means:

```text
E = String
```

and:

```java
Collection<Integer>
```

means:

```text
E = Integer
```

---

## 33. Why can we assign `ArrayList` to a `Collection` reference?

Because `ArrayList` implements `List`, `List` extends `Collection`, and therefore `ArrayList` is a subtype of `Collection`.

```text
Collection
    ↑
   List
    ↑
ArrayList
```

Therefore:

```java
Collection<Integer> numbers =
        new ArrayList<>();
```

is valid.

This is polymorphism.

---

## 34. What is programming to an interface?

Instead of depending on a concrete implementation:

```java
ArrayList<Integer> numbers =
        new ArrayList<>();
```

we can depend on the abstraction:

```java
Collection<Integer> numbers =
        new ArrayList<>();
```

This reduces coupling and makes code easier to change.

For example:

```java
Collection<Integer> numbers =
        new HashSet<>();
```

can replace the implementation without changing code that only depends on the `Collection` contract.

---

## 35. Can a method accept a `Collection` parameter?

Yes.

Example:

```java
static void printElements(
        Collection<Integer> collection
) {
    for (Integer value : collection) {
        System.out.println(value);
    }
}
```

It can accept multiple implementations:

```java
printElements(
        new ArrayList<>(List.of(1, 2, 3))
);

printElements(
        new HashSet<>(Set.of(1, 2, 3))
);
```

---

## 36. What is the difference between `Iterable` and `Collection`?

### `Iterable`

Primarily provides traversal:

```java
iterator()
```

### `Collection`

Provides a broader API for managing a group of elements:

```text
add()
remove()
contains()
size()
clear()
...
```

Relationship:

```text
Iterable
    ↑
Collection
```

### Interview answer

> `Iterable` defines the ability to traverse elements, while `Collection` extends that abstraction with operations for managing a group of elements.

---

# Advanced Questions

## 37. Does `Collection` define performance guarantees?

Generally, no.

The interface defines operations, but the concrete implementation determines their performance characteristics.

For example:

```text
ArrayList.contains()
    → O(n)

HashSet.contains()
    → O(1) average

TreeSet.contains()
    → O(log n)
```

Therefore, choosing the right implementation matters.

---

## 38. Why doesn't `Collection` provide `get(index)`?

Because not every collection is index-based.

For example:

```text
ArrayList
    → index-based

HashSet
    → no index

TreeSet
    → no index
```

If `Collection` defined:

```java
get(int index)
```

it would not make sense for many collection types.

That operation belongs to the more specific `List` abstraction.

---

## 39. Why does `Collection` not guarantee duplicates?

Because some collection types need uniqueness.

For example:

```text
List
    → duplicates allowed

Set
    → duplicates prohibited
```

Therefore, the general `Collection` abstraction is flexible enough to support both.

---

## 40. Why does `Collection` not guarantee ordering?

Because different collection types have different ordering requirements.

For example:

```text
ArrayList
    → insertion order

HashSet
    → no guaranteed order

TreeSet
    → sorted order
```

The general interface does not impose one ordering rule.

---

## 41. Why is `Collection` an interface?

It provides a common contract without dictating a particular internal data structure.

Different implementations can use:

- Arrays
- Linked nodes
- Hash tables
- Trees
- Heaps

while exposing the common `Collection` API.

---

## 42. Can a class implement `Collection` directly?

Yes.

A custom class can implement:

```java
Collection<T>
```

However, it must implement the required methods.

In practice, it is often easier to extend an appropriate existing abstract collection class or use composition, depending on the design.

---

## 43. What is the difference between `Collection` and `Map` from an API perspective?

`Collection` works with individual elements:

```java
Collection<String>
```

`Map` works with key-value pairs:

```java
Map<Integer, String>
```

Collection operations:

```text
add()
remove()
contains()
```

Map operations:

```text
put()
get()
remove()
containsKey()
containsValue()
```

---

## 44. Can a `Collection` contain another collection?

Yes.

A collection can store objects of any reference type, including other collections.

Example:

```java
Collection<List<Integer>> groups =
        new ArrayList<>();
```

Then:

```java
groups.add(
        List.of(1, 2, 3)
);

groups.add(
        List.of(4, 5, 6)
);
```

This creates a collection of collections.

---

## 45. What is a collection view?

Some APIs provide a view of data rather than an independent copy.

For example, a `Map` provides:

```java
map.keySet()
map.values()
map.entrySet()
```

These are views backed by the map.

This is important when studying the `Map` interface.

---

## 46. Does `Collection` support streams?

Yes.

Collections can create streams using:

```java
stream()
```

and:

```java
parallelStream()
```

Example:

```java
Collection<Integer> numbers =
        List.of(10, 20, 30, 40);

numbers.stream()
       .filter(n -> n > 20)
       .forEach(System.out::println);
```

Output:

```text
30
40
```

Streams will be studied separately as part of Java's Stream API.

---

# Code-Based Questions

## 47. What is the output?

```java
Collection<Integer> numbers =
        new ArrayList<>();

System.out.println(
        numbers.add(10)
);

System.out.println(
        numbers.add(10)
);

System.out.println(
        numbers.size()
);
```

### Answer

```text
true
true
2
```

`ArrayList` allows duplicates.

---

## 48. What is the output?

```java
Collection<Integer> numbers =
        new HashSet<>();

System.out.println(
        numbers.add(10)
);

System.out.println(
        numbers.add(10)
);

System.out.println(
        numbers.size()
);
```

### Answer

```text
true
false
1
```

`HashSet` does not allow duplicate elements.

---

## 49. What is the output?

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

numbers.removeAll(
        List.of(20, 40)
);

System.out.println(numbers);
```

### Answer

```text
[10, 30]
```

---

## 50. What is the output?

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

numbers.retainAll(
        List.of(20, 40)
);

System.out.println(numbers);
```

### Answer

```text
[20, 40]
```

---

## 51. What is the output?

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(1, 2, 3, 4, 5)
        );

numbers.removeIf(
        n -> n % 2 == 0
);

System.out.println(numbers);
```

### Answer

```text
[1, 3, 5]
```

---

## 52. What is the output?

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

System.out.println(
        numbers.contains(20)
);

numbers.clear();

System.out.println(
        numbers.isEmpty()
);
```

### Answer

```text
true
true
```

---

## 53. Is this valid?

```java
Collection<Integer> numbers =
        new ArrayList<>();
```

### Answer

Yes.

`ArrayList` implements `List`, and `List` extends `Collection`.

---

## 54. Is this valid?

```java
Collection<Integer> numbers =
        new HashSet<>();
```

### Answer

Yes.

`HashSet` implements `Set`, and `Set` extends `Collection`.

---

## 55. Is this valid?

```java
Collection<Integer> numbers =
        new HashMap<>();
```

### Answer

No.

`HashMap` implements `Map`, not `Collection`.

---

# Tricky Questions

## 56. Is every `Collection` ordered?

No.

Ordering depends on the implementation.

---

## 57. Is every `Collection` unique?

No.

`List` allows duplicates.

`Set` does not.

---

## 58. Is every `Collection` index-based?

No.

Only certain collection types, such as `List`, support index-based operations.

---

## 59. Is `Map` a collection?

In Java terminology, `Map` is not a subtype of `Collection`.

It is part of the Java Collections Framework, but it represents a separate key-value abstraction.

---

## 60. Does `Collection` extend `Map`?

No.

The two interfaces are unrelated in the type hierarchy.

---

## 61. Does `Collection` guarantee thread safety?

No.

Most common collection implementations such as `ArrayList` and `HashMap` are not inherently thread-safe.

Thread safety depends on the implementation and usage.

---

## 62. Does `Collection` guarantee `null` support?

No.

`null` behavior depends on the specific collection implementation.

---

## 63. Can `Collection` contain duplicate objects that are equal?

Yes, depending on the implementation.

For example, a `List` can contain multiple equal elements:

```java
List<String> names =
        new ArrayList<>();

names.add("Java");
names.add("Java");
```

Result:

```text
[Java, Java]
```

A `Set` generally prevents duplicate elements according to its equality semantics.

---

## 64. What determines whether a `Set` considers two objects duplicates?

For hash-based sets, `equals()` and `hashCode()` are important.

For sorted sets such as `TreeSet`, ordering/comparison semantics are used.

This is why `equals()`, `hashCode()`, `Comparable`, and `Comparator` become important later in the Collections Framework.

---

# Rapid Fire Revision

| Question | Answer |
|---|---|
| What is `Collection`? | Interface representing a group of elements |
| Package? | `java.util` |
| Parent interface? | `Iterable` |
| Major 
