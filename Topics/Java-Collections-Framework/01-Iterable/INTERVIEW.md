# Iterable - Interview Questions

> A focused collection of Java interview questions covering `Iterable`, `Iterator`, enhanced `for` loops, custom iteration, and related concepts.

---

## Table of Contents

- [Basic Questions](#basic-questions)
- [Intermediate Questions](#intermediate-questions)
- [Advanced Questions](#advanced-questions)
- [Code-Based Questions](#code-based-questions)
- [Tricky Questions](#tricky-questions)
- [Rapid Fire Revision](#rapid-fire-revision)
- [Most Important Questions](#most-important-questions)

---

# Basic Questions

## 1. What is `Iterable` in Java?

`Iterable` is an interface that represents an object whose elements can be traversed.

It provides the:

```java
iterator()
```

method, which returns an `Iterator`.

---

## 2. Which package contains `Iterable`?

`Iterable` belongs to:

```java
java.lang
```

Its fully qualified name is:

```java
java.lang.Iterable
```

Because it belongs to `java.lang`, it does not need to be explicitly imported.

---

## 3. What is the main purpose of `Iterable`?

The main purpose of `Iterable` is to provide a standard mechanism for traversing elements.

It allows an object to be used with the enhanced `for` loop when the object is iterable.

---

## 4. What is the most important method of `Iterable`?

The most important method is:

```java
iterator()
```

It returns:

```java
Iterator<T>
```

---

## 5. What is an `Iterator`?

`Iterator` is an interface used to traverse elements one by one.

It belongs to:

```java
java.util
```

Important methods include:

```java
hasNext()
next()
remove()
```

---

## 6. What is the relationship between `Iterable` and `Iterator`?

`Iterable` provides an `Iterator`.

The relationship is:

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

### Interview answer

> `Iterable` represents an object that can provide an iterator, while `Iterator` represents the mechanism and state used to traverse the elements.

---

## 7. What is the difference between `Iterable` and `Iterator`?

| `Iterable` | `Iterator` |
|---|---|
| Represents an iterable object | Represents a traversal mechanism |
| Provides `iterator()` | Provides `hasNext()`, `next()`, `remove()` |
| Can create an iterator | Performs traversal |
| `java.lang` | `java.util` |

### Easy way to remember

> `Iterable` gives you an `Iterator`.

> `Iterator` traverses the elements.

---

## 8. Why does `ArrayList` support the enhanced `for` loop?

Because `ArrayList` implements `List`, which extends `Collection`, and `Collection` extends `Iterable`.

The hierarchy is:

```text
Iterable
    ↑
Collection
    ↑
List
    ↑
ArrayList
```

Therefore, `ArrayList` satisfies the `Iterable` contract.

---

## 9. Why does `HashSet` support the enhanced `for` loop?

Because:

```text
Iterable
    ↑
Collection
    ↑
Set
    ↑
HashSet
```

`HashSet` ultimately implements `Iterable`.

---

## 10. Can a class implement `Iterable` directly?

Yes.

Example:

```java
class MyCollection implements Iterable<Integer> {

    @Override
    public Iterator<Integer> iterator() {
        // implementation
    }
}
```

The class does not have to implement `Collection`.

---

# Intermediate Questions

## 11. How does the enhanced `for` loop work?

Consider:

```java
for (String name : names) {
    System.out.println(name);
}
```

For an iterable object, Java uses an iterator-based mechanism conceptually similar to:

```java
Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {

    String name = iterator.next();

    System.out.println(name);
}
```

### Mental model

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
```

---

## 12. Does the enhanced `for` loop always use `Iterator`?

For an object that implements `Iterable`, the enhanced `for` loop uses its `iterator()` mechanism.

However, arrays are handled separately by the language and do not implement `Iterable`.

---

## 13. Do arrays implement `Iterable`?

No.

For example:

```java
int[] numbers = {10, 20, 30};
```

does not implement:

```java
Iterable<Integer>
```

Nevertheless, arrays can be used with the enhanced `for` loop because arrays have special language-level support.

---

## 14. What happens when `hasNext()` returns `false`?

It indicates that there are no more elements available through the iterator.

For example:

```java
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

The loop terminates when:

```java
hasNext()
```

returns:

```java
false
```

---

## 15. What happens if `next()` is called when there are no more elements?

For a standard `Iterator`, calling `next()` when no element remains results in:

```java
NoSuchElementException
```

Therefore, the usual pattern is:

```java
while (iterator.hasNext()) {
    iterator.next();
}
```

---

## 16. What is the purpose of `Iterator.remove()`?

`Iterator.remove()` removes the last element returned by the iterator.

Example:

```java
Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {

    Integer number = iterator.next();

    if (number % 2 == 0) {
        iterator.remove();
    }
}
```

This is the iterator-supported way to remove elements during iteration.

---

## 17. What is `ConcurrentModificationException`?

It is a runtime exception commonly encountered when a collection is structurally modified while it is being iterated in a way that violates the iterator's modification rules.

Example:

```java
for (Integer number : numbers) {

    if (number == 20) {
        numbers.remove(number);
    }
}
```

For many standard collections, this can result in:

```java
ConcurrentModificationException
```

Using:

```java
iterator.remove();
```

is the appropriate iterator-based removal mechanism.

---

## 18. What is fail-fast iteration?

A fail-fast iterator attempts to detect certain structural modifications to the underlying collection that occur outside the iterator's supported modification mechanism.

When such a modification is detected, the iterator may throw:

```java
ConcurrentModificationException
```

Important:

> Fail-fast behavior is a best-effort behavior and should not be relied upon as a thread-safety mechanism.

---

## 19. Can a collection have multiple iterators?

Yes.

Example:

```java
List<Integer> numbers =
        List.of(10, 20, 30);

Iterator<Integer> first =
        numbers.iterator();

Iterator<Integer> second =
        numbers.iterator();
```

Each iterator maintains its own traversal state.

Conceptually:

```text
Collection
   |
   ├── Iterator 1
   |
   └── Iterator 2
```

---

## 20. Does moving one iterator move another iterator?

No.

Each iterator maintains its own traversal position.

Example:

```java
Iterator<Integer> first = numbers.iterator();
Iterator<Integer> second = numbers.iterator();

first.next();
```

This does not automatically advance `second`.

---

# Advanced Questions

## 21. Why is `Iterable` an interface instead of a class?

Because Java wants different types of objects and data structures to provide a common traversal contract without forcing them to share the same implementation.

Different structures can implement:

```java
Iterable<T>
```

in their own way.

This supports abstraction and polymorphism.

---

## 22. Why does `Collection` extend `Iterable`?

A collection represents a group of elements.

Since users commonly need to traverse those elements, `Collection` inherits the traversal contract from `Iterable`.

Therefore:

```text
Iterable
    ↑
Collection
```

Every standard `Collection` is iterable.

---

## 23. Can an `Iterable` contain zero elements?

Yes.

An iterable object can represent an empty sequence.

For example:

```java
List<Integer> numbers =
        Collections.emptyList();
```

Its iterator simply has:

```java
hasNext() == false
```

from the beginning.

---

## 24. Can an `Iterable` contain duplicate elements?

`Iterable` itself does not define whether duplicates are allowed.

That behavior depends on the implementation.

For example:

```text
ArrayList   → duplicates allowed
HashSet     → duplicates not allowed
```

Therefore, `Iterable` does not impose duplicate rules.

---

## 25. Does `Iterable` guarantee ordering?

No.

`Iterable` only defines how an object can be traversed.

It does not define what ordering the elements must have.

The concrete implementation determines the traversal order.

For example:

```text
ArrayList      → insertion order
LinkedHashSet  → insertion order
TreeSet        → sorted order
HashSet        → no guaranteed iteration order
```

---

## 26. Does `Iterable` guarantee thread safety?

No.

`Iterable` is only a traversal abstraction.

Thread-safety depends on the specific implementation and how it is used.

---

## 27. Can `Iterable` be used with generics?

Yes.

`Iterable` is generic:

```java
Iterable<T>
```

Example:

```java
Iterable<String>
Iterable<Integer>
Iterable<Student>
```

This provides compile-time type safety.

---

## 28. Can a method accept `Iterable` instead of `Collection`?

Yes.

If the method only needs to traverse elements, accepting `Iterable` can be more general.

Example:

```java
void printAll(Iterable<Integer> data) {

    for (Integer value : data) {
        System.out.println(value);
    }
}
```

This method does not need to know whether the object is:

- `ArrayList`
- `LinkedList`
- `HashSet`
- `TreeSet`
- A custom iterable

It only requires the traversal contract.

---

## 29. Why is programming to `Iterable` useful?

It reduces coupling.

Instead of writing:

```java
void printAll(ArrayList<Integer> numbers)
```

we can write:

```java
void printAll(Iterable<Integer> numbers)
```

The second version accepts a much broader range of objects.

This is an example of:

> Programming to an interface rather than a concrete implementation.

---

## 30. What are the methods provided by modern `Iterable`?

The main methods are:

```java
iterator()
forEach()
spliterator()
```

The fundamental method is:

```java
iterator()
```

---

# Code-Based Questions

## 31. What is the output?

```java
List<Integer> numbers =
        List.of(10, 20, 30);

Iterator<Integer> iterator =
        numbers.iterator();

System.out.println(iterator.next());
System.out.println(iterator.next());
```

### Answer

```text
10
20
```

The iterator returns elements sequentially.

---

## 32. What is the output?

```java
List<Integer> numbers =
        List.of(10, 20);

Iterator<Integer> iterator =
        numbers.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

### Answer

```text
10
20
```

---

## 33. What happens here?

```java
List<Integer> numbers =
        List.of(10);

Iterator<Integer> iterator =
        numbers.iterator();

System.out.println(iterator.next());
System.out.println(iterator.next());
```

### Answer

The first `next()` returns:

```text
10
```

The second `next()` throws:

```text
NoSuchElementException
```

because no element remains.

---

## 34. What is wrong with this code?

```java
List<Integer> numbers =
        new ArrayList<>(
            List.of(10, 20, 30)
        );

for (Integer number : numbers) {

    if (number == 20) {
        numbers.remove(number);
    }
}
```

### Answer

The collection is structurally modified while the enhanced `for` loop is iterating over it.

For many standard collections, this results in:

```text
ConcurrentModificationException
```

A standard iterator-based solution is:

```java
Iterator<Integer> iterator =
        numbers.iterator();

while (iterator.hasNext()) {

    Integer number = iterator.next();

    if (number == 20) {
        iterator.remove();
    }
}
```

---

## 35. What is the output?

```java
List<Integer> numbers =
        List.of(10, 20, 30);

Iterator<Integer> first =
        numbers.iterator();

Iterator<Integer> second =
        numbers.iterator();

System.out.println(first.next());
System.out.println(second.next());
```

### Answer

```text
10
10
```

Each iterator starts its own traversal.

---

## 36. Can this class be used with a `for` loop?

```java
class Numbers {

    private int[] values = {
        10, 20, 30
    };
}
```

Can we write:

```java
Numbers numbers = new Numbers();

for (int number : numbers) {
    System.out.println(number);
}
```

### Answer

No.

The class does not implement `Iterable`, and it is not an array.

It needs to provide the iterable contract:

```java
class Numbers implements Iterable<Integer> {
    // ...
}
```

---

## 37. What is required to make a custom class iterable?

At minimum, the class should implement:

```java
Iterable<T>
```

and provide:

```java
@Override
public Iterator<T> iterator() {
    // ...
}
```

Then the object can be used with the enhanced `for` loop.

---

# Tricky Questions

## 38. Is `Iterable` a part of the `java.util` package?

No.

`Iterable` belongs to:

```java
java.lang
```

`Iterator`, on the other hand, belongs to:

```java
java.util
```

---

## 39. Does `Iterable` store elements?

No.

`Iterable` defines a traversal contract.

It does not define a storage structure.

---

## 40. Does every `Iterable` implement `Collection`?

No.

The relationship goes in the opposite direction:

```text
Collection
    ↓
extends
    ↓
Iterable
```

A class can directly implement `Iterable` without being a `Collection`.

---

## 41. Is every `Collection` an `Iterable`?

Yes.

Because:

```text
Collection extends Iterable
```

Therefore:

```java
Collection<Integer> numbers = new ArrayList<>();
```

can be used with:

```java
for (Integer number : numbers) {
    // ...
}
```

---

## 42. Is every `Map` an `Iterable`?

No.

`Map` does not extend `Collection` or `Iterable`.

However, its views can be iterated:

```java
map.keySet()
map.values()
map.entrySet()
```

---

## 43. Why can `HashSet` be used with `for-each` even though it has no indexes?

Because enhanced `for` iteration does not require indexes.

`HashSet` provides an iterator.

```text
HashSet
   ↓
Set
   ↓
Collection
   ↓
Iterable
   ↓
Iterator
```

---

## 44. Does `Iterator` know how the collection is internally implemented?

The user of the iterator does not need to know.

The collection implementation provides an iterator that knows how to traverse its own internal structure.

This is an example of abstraction.

---

## 45. Is `Iterator` itself a collection?

No.

`Iterator` is a traversal mechanism.

It does not represent a group of elements.

---

# Rapid Fire Revision

| Question | Answer |
|---|---|
| `Iterable` package? | `java.lang` |
| `Iterator` package? | `java.util` |
| Main `Iterable` method? | `iterator()` |
| Return type? | `Iterator<T>` |
| Main iterator methods? | `hasNext()`, `next()`, `remove()` |
| Does `Collection` extend `Iterable`? | Yes |
| Does `Map` extend `Collection`? | No |
| Can custom classes implement `Iterable`? | Yes |
| Does `Iterable` guarantee order? | No |
| Does `Iterable` guarantee duplicates? | No |
| Does `Iterable` guarantee thread safety? | No |
| Can multiple iterators exist? | Yes |
| Do multiple iterators have independent state? | Yes |
| What happens when `next()` has no element? | `NoSuchElementException` |
| What can happen when modifying during iteration? | `ConcurrentModificationException` |
| Standard removal mechanism during iteration? | `Iterator.remove()` |
| Does an array implement `Iterable`? | No |
| Can arrays use enhanced `for`? | Yes |

---

# Most Important Questions

For interviews, prioritize these questions:

### ⭐⭐⭐⭐⭐

1. What is `Iterable`?
2. What is the difference between `Iterable` and `Iterator`?
3. How does the enhanced `for` loop work?
4. Why does `ArrayList` support enhanced `for`?
5. Why does `HashSet` support enhanced `for`?
6. What is `Iterator.remove()`?
7. What causes `ConcurrentModificationException`?
8. What is fail-fast behavior?
9. Can a class implement `Iterable` without implementing `Collection`?
10. Why is `Map` not part of the `Collection` hierarchy?

### ⭐⭐⭐⭐

11. What happens when `next()` is called with no elements remaining?
12. Can multiple iterators exist for one collection?
13. Does `Iterable` guarantee ordering?
14. Does `Iterable` guarantee duplicate handling?
15. Does `Iterable` guarantee thread safety?

### ⭐⭐⭐

16. What package contains `Iterable`?
17. What package contains `Iterator`?
18. What are the important methods of `Iterator`?
19. What does `spliterator()` do?
20. What does `forEach()` do?

---

# Interview Summary

## One-Line Definition

> `Iterable` is an interface that provides a standard mechanism for traversing elements through an `Iterator`.

## Relationship

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

## Collection Relationship

```text
Iterable
    ↑
Collection
    ↑
List / Set / Queue
```

## Enhanced For Loop

```text
for (Element e : collection)
            ↓
      collection.iterator()
            ↓
          Iterator
            ↓
      hasNext() + next()
```

## Key Principle

> **Iterable defines the ability to traverse. Iterator performs the traversal.**

---

# Final Interview Checklist

- [ ] I can explain `Iterable`.
- [ ] I know its package.
- [ ] I know `iterator()`.
- [ ] I can explain `Iterator`.
- [ ] I know `hasNext()`.
- [ ] I know `next()`.
- [ ] I understand `remove()`.
- [ ] I can explain enhanced `for`.
- [ ] I understand `Iterable` vs `Iterator`.
- [ ] I understand `Iterable` vs `Collection`.
- [ ] I know why `ArrayList` is iterable.
- [ ] I know why `HashSet` is iterable.
- [ ] I understand custom `Iterable`.
- [ ] I understand multiple iterators.
- [ ] I understand fail-fast behavior.
- [ ] I know about `ConcurrentModificationException`.
- [ ] I know why `Map` is separate.
- [ ] I know arrays do not implement `Iterable`.
- [ ] I can answer basic iterator code questions.

---

# Iterable Folder Status

```text
01-Iterable/
├── NOTES.md       ✅
├── PRACTICE.md    ✅
└── INTERVIEW.md   ✅
```

> **`01-Iterable` is now complete.**
