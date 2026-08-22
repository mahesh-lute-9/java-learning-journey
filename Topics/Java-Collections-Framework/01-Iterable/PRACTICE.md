# Iterable - Practice

> Practice problems to understand `Iterable`, `Iterator`, enhanced `for` loops, and custom iterable objects.

---

## Table of Contents

- [Level 1 - Basics](#level-1---basics)
- [Level 2 - Iterator Practice](#level-2---iterator-practice)
- [Level 3 - Enhanced For Loop](#level-3---enhanced-for-loop)
- [Level 4 - Custom Iterable](#level-4---custom-iterable)
- [Level 5 - Iterator Modification](#level-5---iterator-modification)
- [Level 6 - Interview-Style Problems](#level-6---interview-style-problems)
- [Challenge Problems](#challenge-problems)

---

# Level 1 - Basics

## 1. Print Elements Using Enhanced For Loop

Create a list of integers:

```java
List<Integer> numbers =
        List.of(10, 20, 30, 40, 50);
```

Print every element using an enhanced `for` loop.

### Expected Output

```text
10
20
30
40
50
```

### Concepts

- `Iterable`
- Enhanced `for` loop
- Collection traversal

---

## 2. Print Elements Using Iterator

Given:

```java
List<String> languages =
        List.of("Java", "Python", "C++", "Dart");
```

Use an `Iterator` to print every element.

Do not use an enhanced `for` loop.

### Expected Output

```text
Java
Python
C++
Dart
```

### Concepts

- `iterator()`
- `Iterator`
- `hasNext()`
- `next()`

---

## 3. Count Elements Using Iterator

Given:

```java
List<Integer> numbers =
        List.of(10, 20, 30, 40, 50);
```

Use an `Iterator` to count the number of elements.

### Expected Output

```text
5
```

---

## 4. Calculate Sum Using Iterator

Given:

```java
List<Integer> numbers =
        List.of(10, 20, 30, 40, 50);
```

Use an `Iterator` to calculate the sum.

### Expected Output

```text
150
```

---

# Level 2 - Iterator Practice

## 5. Find Maximum Element

Given:

```java
List<Integer> numbers =
        List.of(10, 45, 20, 90, 30);
```

Use an `Iterator` to find the maximum element.

### Expected Output

```text
90
```

---

## 6. Find Minimum Element

Given:

```java
List<Integer> numbers =
        List.of(10, 45, 20, 90, 30);
```

Use an `Iterator` to find the minimum element.

### Expected Output

```text
10
```

---

## 7. Count Even Numbers

Given:

```java
List<Integer> numbers =
        List.of(10, 15, 22, 31, 40, 51);
```

Use an `Iterator` to count even numbers.

### Expected Output

```text
3
```

---

## 8. Search for an Element

Given:

```java
List<Integer> numbers =
        List.of(10, 20, 30, 40, 50);
```

Use an `Iterator` to determine whether `30` exists.

### Expected Output

```text
true
```

---

## 9. Search for a Missing Element

Using the same list, search for:

```text
100
```

### Expected Output

```text
false
```

---

# Level 3 - Enhanced For Loop

## 10. Print Only Even Numbers

Given:

```java
List<Integer> numbers =
        List.of(10, 15, 20, 25, 30, 35);
```

Print only the even numbers using an enhanced `for` loop.

### Expected Output

```text
10
20
30
```

---

## 11. Print Strings With Length Greater Than 5

Given:

```java
List<String> names =
        List.of(
            "Mahesh",
            "Java",
            "Spring",
            "Developer",
            "Code"
        );
```

Print strings whose length is greater than `5`.

### Expected Output

```text
Mahesh
Spring
Developer
```

---

## 12. Calculate Average

Given:

```java
List<Integer> numbers =
        List.of(10, 20, 30, 40, 50);
```

Use an enhanced `for` loop to calculate the average.

### Expected Output

```text
30.0
```

---

# Level 4 - Custom Iterable

## 13. Create a Custom Iterable

Create:

```java
class NumberCollection implements Iterable<Integer>
```

Store the following numbers internally:

```text
10, 20, 30, 40, 50
```

Implement:

```java
iterator()
```

so that the following works:

```java
NumberCollection numbers = new NumberCollection();

for (Integer number : numbers) {
    System.out.println(number);
}
```

### Expected Output

```text
10
20
30
40
50
```

---

## 14. Custom String Iterable

Create:

```java
class NameCollection implements Iterable<String>
```

Store:

```text
Mahesh
Rahul
Amit
Sneha
```

Implement `iterator()`.

The following should work:

```java
NameCollection names = new NameCollection();

for (String name : names) {
    System.out.println(name);
}
```

---

## 15. Custom Range Iterable

Create a class:

```java
class NumberRange implements Iterable<Integer>
```

The constructor should accept:

```java
NumberRange(int start, int end)
```

Example:

```java
NumberRange range = new NumberRange(1, 5);

for (int number : range) {
    System.out.println(number);
}
```

### Expected Output

```text
1
2
3
4
5
```

---

## 16. Reverse Iterable

Create:

```java
class ReverseRange implements Iterable<Integer>
```

Example:

```java
ReverseRange range = new ReverseRange(5, 1);

for (int number : range) {
    System.out.println(number);
}
```

### Expected Output

```text
5
4
3
2
1
```

---

# Level 5 - Iterator Modification

## 17. Remove Even Numbers

Given:

```java
List<Integer> numbers =
        new ArrayList<>(
            List.of(10, 15, 20, 25, 30, 35)
        );
```

Use an `Iterator` to remove all even numbers.

### Expected Result

```text
[15, 25, 35]
```

### Restriction

Do not use:

```java
removeIf()
```

Use:

```java
Iterator.remove()
```

---

## 18. Remove Strings With Length Less Than 5

Given:

```java
List<String> names =
        new ArrayList<>(
            List.of(
                "Java",
                "Spring",
                "C",
                "Python",
                "Go"
            )
        );
```

Use an `Iterator` to remove strings whose length is less than `5`.

### Expected Result

```text
[Spring, Python]
```

---

## 19. Why Does Direct Removal Fail?

Study the following code:

```java
List<Integer> numbers =
        new ArrayList<>(
            List.of(10, 20, 30, 40)
        );

for (Integer number : numbers) {

    if (number == 20) {
        numbers.remove(number);
    }
}
```

Answer:

1. What happens when this code runs?
2. Why can the exception occur?
3. How can `Iterator.remove()` solve the problem?

---

# Level 6 - Interview-Style Problems

## 20. Explain This Code

What will the following code do?

```java
List<Integer> numbers =
        List.of(10, 20, 30);

Iterator<Integer> iterator =
        numbers.iterator();

System.out.println(iterator.next());
System.out.println(iterator.next());
```

### Questions

- What is printed?
- Where is the iterator positioned after the second `next()`?
- What happens if `next()` is called again?

---

## 21. Multiple Iterators

Consider:

```java
List<Integer> numbers =
        List.of(10, 20, 30);

Iterator<Integer> first =
        numbers.iterator();

Iterator<Integer> second =
        numbers.iterator();

System.out.println(first.next());
System.out.println(second.next());
System.out.println(first.next());
```

Determine the output.

Then explain why the second iterator is not affected by the first iterator's movement.

---

## 22. Iterable Without Collection

Create a class that implements:

```java
Iterable<Integer>
```

but does **not** implement:

```java
Collection<Integer>
```

Make it possible to use:

```java
for (Integer value : object) {
    System.out.println(value);
}
```

Explain why this works.

---

## 23. Generic Iterable

Create:

```java
class Box<T> implements Iterable<T>
```

The class should store multiple values and allow:

```java
Box<String> box = ...;

for (String value : box) {
    System.out.println(value);
}
```

Then test it with:

```java
Box<Integer>
```

---

# Challenge Problems

## Challenge 1 - Custom Collection Traversal

Create a class:

```java
StudentCollection
```

that internally stores:

```java
Student[]
```

Implement:

```java
Iterable<Student>
```

Requirements:

- Implement `iterator()`
- Support enhanced `for`
- Do not expose the internal array
- Allow users to traverse students

---

## Challenge 2 - Filtered Iterable

Create:

```java
FilteredIterable
```

It should accept:

```java
Iterable<Integer>
```

and a condition.

Example:

```java
List<Integer> numbers =
        List.of(1, 2, 3, 4, 5, 6);

FilteredIterable evenNumbers =
        new FilteredIterable(
            numbers,
            number -> number % 2 == 0
        );
```

Then:

```java
for (Integer number : evenNumbers) {
    System.out.println(number);
}
```

### Expected Output

```text
2
4
6
```

---

## Challenge 3 - Custom Range With Step

Create:

```java
Range implements Iterable<Integer>
```

Constructor:

```java
Range(int start, int end, int step)
```

Example:

```java
Range range =
        new Range(0, 10, 2);
```

Expected iteration:

```text
0
2
4
6
8
10
```

---

# Practice Checklist

## Level 1

- [ ] Print elements using enhanced `for`
- [ ] Print elements using `Iterator`
- [ ] Count elements
- [ ] Calculate sum

## Level 2

- [ ] Find maximum
- [ ] Find minimum
- [ ] Count even numbers
- [ ] Search for an element
- [ ] Search for a missing element

## Level 3

- [ ] Filter using enhanced `for`
- [ ] Process strings
- [ ] Calculate average

## Level 4

- [ ] Create custom `Iterable`
- [ ] Create custom String iterable
- [ ] Create range iterable
- [ ] Create reverse iterable

## Level 5

- [ ] Remove elements using `Iterator`
- [ ] Understand `ConcurrentModificationException`
- [ ] Compare direct removal vs iterator removal

## Level 6

- [ ] Explain iterator state
- [ ] Understand multiple iterators
- [ ] Implement `Iterable` without `Collection`
- [ ] Implement generic `Iterable`

## Challenges

- [ ] Student collection
- [ ] Filtered iterable
- [ ] Custom range with step

---

# Key Practice Goal

By the end of this practice set, you should be able to explain and implement:

```text
Iterable
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

And you should be comfortable implementing:

```java
class MyCollection implements Iterable<T> {
    
    @Override
    public Iterator<T> iterator() {
        // implementation
    }
}
```
