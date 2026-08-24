# List Interface - Practice

> Practice problems to build a strong understanding of the `List` interface before moving to `ArrayList`.

---

## Table of Contents

- [1. Basic Practice](#1-basic-practice)
- [2. Index-Based Operations](#2-index-based-operations)
- [3. Adding and Removing Elements](#3-adding-and-removing-elements)
- [4. Searching](#4-searching)
- [5. Iteration](#5-iteration)
- [6. List Manipulation](#6-list-manipulation)
- [7. Sorting and Collections](#7-sorting-and-collections)
- [8. Code Prediction](#8-code-prediction)
- [9. Intermediate Problems](#9-intermediate-problems)
- [10. Challenge Problems](#10-challenge-problems)
- [11. Interview-Oriented Practice](#11-interview-oriented-practice)
- [12. Practice Checklist](#12-practice-checklist)

---

# 1. Basic Practice

## Problem 1: Create a List

Create a `List<Integer>` and add:

```text
10
20
30
40
50
```

Print the list.

### Expected Output

```text
[10, 20, 30, 40, 50]
```

### Concepts

- `List`
- `ArrayList`
- `add()`

---

## Problem 2: Create a List of Strings

Create a list containing:

```text
Java
Spring
Hibernate
SQL
Docker
```

Print every element.

---

## Problem 3: Find the Size

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40, 50)
);
```

Find the number of elements.

### Expected Output

```text
5
```

---

## Problem 4: Check Whether a List Is Empty

Create:

```java
List<String> names = new ArrayList<>();
```

Check whether it is empty.

### Expected Output

```text
true
```

Then add one element and check again.

### Expected Output

```text
false
```

---

## Problem 5: Print the First and Last Element

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40, 50)
);
```

Print the first and last elements.

### Expected Output

```text
First: 10
Last: 50
```

---

# 2. Index-Based Operations

## Problem 6: Access an Element

Given:

```java
List<String> languages = new ArrayList<>(
        List.of(
                "Java",
                "Python",
                "C++",
                "Dart"
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

```java
List<String> languages = new ArrayList<>(
        List.of(
                "Java",
                "Python",
                "C++"
        )
);
```

Replace `Python` with `Spring`.

### Expected Output

```text
[Java, Spring, C++]
```

### Hint

Use:

```java
set()
```

---

## Problem 8: Insert at a Specific Index

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 40, 50)
);
```

Insert `30` at index `2`.

### Expected Output

```text
[10, 20, 30, 40, 50]
```

---

## Problem 9: Insert at the Beginning

Given:

```java
List<String> names = new ArrayList<>(
        List.of("Rahul", "Amit", "Sneha")
);
```

Insert `"Mahesh"` at index `0`.

### Expected Output

```text
[Mahesh, Rahul, Amit, Sneha]
```

---

# 3. Adding and Removing Elements

## Problem 10: Remove by Index

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40, 50)
);
```

Remove the element at index `2`.

### Expected Output

```text
[10, 20, 40, 50]
```

---

## Problem 11: Remove by Value

Given:

```java
List<String> languages = new ArrayList<>(
        List.of(
                "Java",
                "Python",
                "C++",
                "Dart"
        )
);
```

Remove `"Python"`.

### Expected Output

```text
[Java, C++, Dart]
```

---

## Problem 12: Remove an Integer by Value

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40)
);
```

Remove the value `20`.

### Requirement

Do not accidentally remove by index.

Use:

```java
numbers.remove(Integer.valueOf(20));
```

---

## Problem 13: Remove the First Element

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40)
);
```

Remove the first element.

### Expected Output

```text
[20, 30, 40]
```

---

## Problem 14: Remove the Last Element

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40, 50)
);
```

Remove the last element.

### Expected Output

```text
[10, 20, 30, 40]
```

### Hint

```java
numbers.remove(numbers.size() - 1);
```

---

# 4. Searching

## Problem 15: Check Whether an Element Exists

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40, 50)
);
```

Check whether `30` exists.

### Expected Output

```text
true
```

---

## Problem 16: Find the First Occurrence

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 10, 30, 10)
);
```

Find the first index of `10`.

### Expected Output

```text
0
```

---

## Problem 17: Find the Last Occurrence

Using the same list, find the last index of `10`.

### Expected Output

```text
4
```

---

## Problem 18: Find All Occurrences

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(
                10, 20, 10,
                30, 10, 40, 10
        )
);
```

Print every index where `10` occurs.

### Expected Output

```text
0
2
4
6
```

---

# 5. Iteration

## Problem 19: Enhanced For Loop

Print every element using an enhanced `for` loop.

Given:

```java
List<String> languages = List.of(
        "Java",
        "Python",
        "C++",
        "Dart"
);
```

---

## Problem 20: Traditional For Loop

Print every element using:

```java
for
```

and:

```java
get(index)
```

---

## Problem 21: Print Index and Value

Given:

```java
List<String> languages = List.of(
        "Java",
        "Python",
        "C++"
);
```

Expected output:

```text
0 -> Java
1 -> Python
2 -> C++
```

---

## Problem 22: Calculate Sum

Given:

```java
List<Integer> numbers = List.of(
        10,
        20,
        30,
        40,
        50
);
```

Calculate the sum.

### Expected Output

```text
150
```

---

## Problem 23: Find Maximum

Given:

```java
List<Integer> numbers = List.of(
        10,
        50,
        20,
        90,
        30
);
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

# 6. List Manipulation

## Problem 24: Reverse a List

Given:

```text
[10, 20, 30, 40, 50]
```

Reverse the list.

### Expected Output

```text
[50, 40, 30, 20, 10]
```

### Challenge

Try solving it without:

```java
Collections.reverse()
```

---

## Problem 25: Swap Two Elements

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40)
);
```

Swap the elements at indexes `1` and `3`.

### Expected Output

```text
[10, 40, 30, 20]
```

---

## Problem 26: Create a Sublist

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(10, 20, 30, 40, 50)
);
```

Create a sublist containing:

```text
20
30
40
```

### Expected Output

```text
[20, 30, 40]
```

### Hint

```java
subList()
```

---

## Problem 27: Replace Every Element

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(1, 2, 3, 4, 5)
);
```

Multiply every element by `10`.

### Expected Output

```text
[10, 20, 30, 40, 50]
```

### Hint

```java
replaceAll()
```

---

## Problem 28: Remove All Odd Numbers

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(
                10, 15, 20,
                25, 30, 35
        )
);
```

Remove all odd numbers.

### Expected Output

```text
[10, 20, 30]
```

### Hint

```java
removeIf()
```

---

# 7. Sorting and Collections

## Problem 29: Sort in Ascending Order

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(50, 10, 40, 20, 30)
);
```

Sort in ascending order.

### Expected Output

```text
[10, 20, 30, 40, 50]
```

---

## Problem 30: Sort in Descending Order

Sort:

```text
[50, 10, 40, 20, 30]
```

in descending order.

### Expected Output

```text
[50, 40, 30, 20, 10]
```

---

## Problem 31: Find Minimum and Maximum

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(30, 10, 50, 20, 40)
);
```

Find:

- Minimum
- Maximum

Try both:

1. Manually
2. Using `Collections`

---

# 8. Code Prediction

## Problem 32

What is the output?

```java
List<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);

System.out.println(numbers);
```

---

## Problem 33

What is the output?

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

numbers.set(1, 100);

System.out.println(numbers);
```

---

## Problem 34

What is the output?

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

numbers.add(1, 100);

System.out.println(numbers);
```

---

## Problem 35

What is the output?

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

numbers.remove(1);

System.out.println(numbers);
```

---

## Problem 36

What is the output?

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

numbers.remove(Integer.valueOf(20));

System.out.println(numbers);
```

---

## Problem 37

What is the output?

```java
List<String> names = List.of(
        "Java",
        "Spring",
        "Java",
        "Python"
);

System.out.println(
        names.indexOf("Java")
);

System.out.println(
        names.lastIndexOf("Java")
);
```

### Expected Output

```text
0
2
```

---

# 9. Intermediate Problems

## Problem 38: Remove Duplicates

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(
                10, 20, 10,
                30, 20, 40,
                30
        )
);
```

Create a new list containing only unique values.

### Expected Output

```text
[10, 20, 30, 40]
```

### Challenge

Solve it:

1. Using a `Set`
2. Without using a `Set`

---

## Problem 39: Find Common Elements

Given:

```java
List<Integer> first =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

List<Integer> second =
        List.of(20, 40, 60, 80);
```

Find the common elements.

### Expected Output

```text
[20, 40]
```

---

## Problem 40: Find Difference

Given:

```java
List<Integer> first =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

List<Integer> second =
        List.of(20, 40);
```

Find elements that exist in `first` but not in `second`.

### Expected Output

```text
[10, 30]
```

---

## Problem 41: Find Second Largest

Given:

```java
List<Integer> numbers = new ArrayList<>(
        List.of(
                10, 50, 20,
                90, 30, 80
        )
);
```

Find the second-largest element.

### Expected Output

```text
80
```

### Challenge

Try solving without sorting.

---

## Problem 42: Find Frequency

Given:

```java
List<Integer> numbers = List.of(
        10, 20, 10, 30,
        20, 10, 40
);
```

Find the frequency of `10`.

### Expected Output

```text
3
```

---

## Problem 43: Find the Most Frequent Element

Given:

```text
[10, 20, 10, 30, 20, 10]
```

Find the element that occurs most frequently.

### Expected Output

```text
10
```

---

# 10. Challenge Problems

## Challenge 1: Rotate a List

Given:

```text
[1, 2, 3, 4, 5]
```

Rotate the list to the right by `2`.

### Expected Output

```text
[4, 5, 1, 2, 3]
```

### Challenge

Try solving without:

```java
Collections.rotate()
```

---

## Challenge 2: Check Whether a List Is Sorted

For:

```text
[10, 20, 30, 40, 50]
```

return:

```text
true
```

For:

```text
[10, 30, 20, 40]
```

return:

```text
false
```

---

## Challenge 3: Two Sum

Given:

```java
List<Integer> numbers = List.of(
        10, 20, 30, 40, 50
);
```

Determine whether there is a pair whose sum equals:

```text
70
```

### Expected Output

```text
true
```

---

## Challenge 4: Move Zeros to the End

Given:

```text
[0, 10, 0, 20, 30, 0, 40]
```

Move all zeros to the end while maintaining the order of non-zero elements.

### Expected Output

```text
[10, 20, 30, 40, 0, 0, 0]
```

---

## Challenge 5: Remove Consecutive Duplicates

Given:

```text
[10, 10, 20, 20, 20, 30, 10, 10]
```

Remove only consecutive duplicates.

### Expected Output

```text
[10, 20, 30, 10]
```

---

## Challenge 6: Merge Two Sorted Lists

Given:

```text
First:  [1, 3, 5, 7]
Second: [2, 4, 6, 8]
```

Merge them into:

```text
[1, 2, 3, 4, 5, 6, 7, 8]
```

Try solving without simply combining both lists and sorting.

---

# 11. Interview-Oriented Practice

## Interview Problem 1: The `remove()` Trap

Predict the output:

```java
List<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

numbers.remove(1);

System.out.println(numbers);
```

### Questions

1. What is the output?
2. Which overloaded `remove()` method is called?
3. How would you remove the value `1` instead?

---

## Interview Problem 2: `add()` vs `set()`

Start with:

```text
[10, 20, 30]
```

What happens after:

```java
list.add(1, 100);
```

and:

```java
list.set(1, 100);
```

### Expected Understanding

```text
add():
[10, 100, 20, 30]

set():
[10, 100, 30]
```

---

## Interview Problem 3: `ArrayList` vs `LinkedList`

Explain why:

```java
list.get(500);
```

is generally faster for an `ArrayList` than a `LinkedList`.

### Expected Concepts

```text
ArrayList
    -> Array-based
    -> Direct index access
    -> O(1)

LinkedList
    -> Node-based
    -> Traversal required
    -> O(n)
```

---

## Interview Problem 4: `subList()`

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

### Expected Output

```text
[10, 100, 30, 40]
```

### Question

Why did changing `sub` affect `numbers`?

---

## Interview Problem 5: `List.of()`

What happens here?

```java
List<Integer> numbers =
        List.of(10, 20, 30);

numbers.add(40);
```

### Expected Exception

```text
UnsupportedOperationException
```

---

# 12. Practice Checklist

## Basic Operations

- [ ] Create a `List`
- [ ] Add elements
- [ ] Add at an index
- [ ] Access using `get()`
- [ ] Update using `set()`
- [ ] Remove by index
- [ ] Remove by value
- [ ] Clear a list
- [ ] Check size
- [ ] Check whether empty

## Searching

- [ ] `contains()`
- [ ] `indexOf()`
- [ ] `lastIndexOf()`
- [ ] Find all occurrences
- [ ] Find frequency
- [ ] Find most frequent element

## Iteration

- [ ] Enhanced `for` loop
- [ ] Traditional `for` loop
- [ ] `Iterator`
- [ ] `ListIterator`
- [ ] Forward traversal
- [ ] Backward traversal

## List Manipulation

- [ ] Reverse a list
- [ ] Swap elements
- [ ] Copy a list
- [ ] Create a sublist
- [ ] `replaceAll()`
- [ ] `removeIf()`
- [ ] Sort
- [ ] Shuffle
- [ ] Rotate

## Important Concepts

- [ ] `List` vs `Collection`
- [ ] `add()` vs `set()`
- [ ] `remove(int)` vs `remove(Object)`
- [ ] `ArrayList` vs `LinkedList`
- [ ] Random access
- [ ] `List.of()`
- [ ] `Arrays.asList()`
- [ ] Mutable vs unmodifiable lists
- [ ] `subList()` view
- [ ] Duplicate elements
- [ ] Ordering
- [ ] `null` handling

## Challenge Problems

- [ ] Remove duplicates
- [ ] Find common elements
- [ ] Find difference
- [ ] Find second largest
- [ ] Find frequency
- [ ] Find most frequent element
- [ ] Move zeros
- [ ] Check sorted
- [ ] Two Sum
- [ ] Rotate list
- [ ] Merge sorted lists
- [ ] Remove consecutive duplicates

---

# Final Practice Goals

Before moving to `ArrayList`, you should be comfortable with:

```text
List
 |
 +-- Ordered
 |
 +-- Allows duplicates
 |
 +-- Index-based access
 |
 +-- add()
 +-- get()
 +-- set()
 +-- remove()
 +-- contains()
 +-- indexOf()
 +-- lastIndexOf()
 +-- subList()
 +-- listIterator()
 +-- replaceAll()
 +-- removeIf()
 +-- sort()
```

You should especially understand these three interview concepts:

```text
remove(1)
    vs
remove(Integer.valueOf(1))
```

```text
add(index, value)
    vs
set(index, value)
```

```text
ArrayList
    vs
LinkedList
```

---

# Completion Status

```text
03-List/
├── NOTES.md       [x]
├── PRACTICE.md    [x]
└── INTERVIEW.md   [ ]
```

> Complete `INTERVIEW.md` before moving to `04-ArrayList`.
