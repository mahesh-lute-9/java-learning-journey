# ArrayList - Practice

> Hands-on problems to master `ArrayList`, including basic operations, internal behavior, complexity, resizing, iteration, tricky cases, and interview-level problems.

---

## Table of Contents

- [1. Basic ArrayList Operations](#1-basic-arraylist-operations)
- [2. Index-Based Operations](#2-index-based-operations)
- [3. Removing Elements](#3-removing-elements)
- [4. Searching](#4-searching)
- [5. Iteration](#5-iteration)
- [6. ArrayList Manipulation](#6-arraylist-manipulation)
- [7. Sorting](#7-sorting)
- [8. Bulk Operations](#8-bulk-operations)
- [9. ArrayList and Arrays](#9-arraylist-and-arrays)
- [10. Size vs Capacity](#10-size-vs-capacity)
- [11. ensureCapacity and trimToSize](#11-ensurecapacity-and-trimtosize)
- [12. Iterator Practice](#12-iterator-practice)
- [13. Code Prediction](#13-code-prediction)
- [14. Intermediate Problems](#14-intermediate-problems)
- [15. Challenge Problems](#15-challenge-problems)
- [16. Interview Practice](#16-interview-practice)
- [17. Practice Checklist](#17-practice-checklist)

---

# 1. Basic ArrayList Operations

## Problem 1: Create an ArrayList

Create an `ArrayList<Integer>` and add:

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

---

## Problem 2: Create an ArrayList of Strings

Create:

```java
ArrayList<String> languages =
        new ArrayList<>();
```

Add:

```text
Java
Python
C++
JavaScript
```

Print the list.

### Expected Output

```text
[Java, Python, C++, JavaScript]
```

---

## Problem 3: Find the Size

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40, 50)
        );
```

Find the number of elements.

### Expected Output

```text
5
```

---

## Problem 4: Check Whether the List Is Empty

Create an empty `ArrayList`.

Check:

```java
isEmpty()
```

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

## Problem 5: Add Elements Dynamically

Start with:

```java
ArrayList<Integer> numbers =
        new ArrayList<>();
```

Add the numbers from `1` to `10` using a loop.

### Expected Output

```text
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
```

---

# 2. Index-Based Operations

## Problem 6: Access an Element

Given:

```java
ArrayList<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Python",
                        "C++",
                        "Go"
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
ArrayList<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Python",
                        "C++"
                )
        );
```

Replace `"Python"` with `"Spring"`.

### Expected Output

```text
[Java, Spring, C++]
```

---

## Problem 8: Insert at an Index

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
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
ArrayList<String> names =
        new ArrayList<>(
                List.of(
                        "Rahul",
                        "Amit",
                        "Sneha"
                )
        );
```

Insert `"Mahesh"` at index `0`.

### Expected Output

```text
[Mahesh, Rahul, Amit, Sneha]
```

---

## Problem 10: Insert at the End

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30)
        );
```

Add `40` at the end.

### Expected Output

```text
[10, 20, 30, 40]
```

---

# 3. Removing Elements

## Problem 11: Remove by Index

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40, 50)
        );
```

Remove the element at index `2`.

### Expected Output

```text
[10, 20, 40, 50]
```

---

## Problem 12: Remove by Value

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );
```

Remove the value `30`.

### Expected Output

```text
[10, 20, 40]
```

---

## Problem 13: The Integer Remove Trap

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );
```

What happens after:

```java
numbers.remove(1);
```

### Questions

1. Which element is removed?
2. Why?
3. How would you remove the value `1`?

---

## Problem 14: Remove the Last Element

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );
```

Remove the last element.

### Expected Output

```text
[10, 20, 30]
```

### Hint

```java
numbers.remove(numbers.size() - 1);
```

---

## Problem 15: Remove All Elements

Given:

```java
ArrayList<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );
```

Remove all elements.

### Expected Output

```text
[]
```

---

# 4. Searching

## Problem 16: Check Whether an Element Exists

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 30,
                        40, 50
                )
        );
```

Check whether `30` exists.

### Expected Output

```text
true
```

---

## Problem 17: Search for a Missing Element

Check whether `100` exists.

### Expected Output

```text
false
```

---

## Problem 18: Find First Occurrence

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 10,
                        30, 10
                )
        );
```

Find the first index of `10`.

### Expected Output

```text
0
```

---

## Problem 19: Find Last Occurrence

Using the same list, find the last index of `10`.

### Expected Output

```text
4
```

---

## Problem 20: Count Occurrences

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 10,
                        30, 10, 40
                )
        );
```

Count how many times `10` appears.

### Expected Output

```text
3
```

---

# 5. Iteration

## Problem 21: Enhanced For Loop

Print every element using:

```java
for-each
```

Given:

```java
ArrayList<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Python",
                        "C++"
                )
        );
```

---

## Problem 22: Traditional For Loop

Print every element using:

```java
for
```

and:

```java
get(index)
```

---

## Problem 23: Print Index and Value

Given:

```java
ArrayList<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );
```

Expected output:

```text
0 -> Java
1 -> Spring
2 -> SQL
```

---

## Problem 24: Calculate Sum

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 30,
                        40, 50
                )
        );
```

Calculate the sum.

### Expected Output

```text
150
```

---

## Problem 25: Find Maximum

Find the maximum without using:

```java
Collections.max()
```

### Expected Output

```text
50
```

---

## Problem 26: Find Minimum

Find the minimum without using:

```java
Collections.min()
```

### Expected Output

```text
10
```

---

# 6. ArrayList Manipulation

## Problem 27: Reverse an ArrayList

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

First solve without:

```java
Collections.reverse()
```

---

## Problem 28: Swap Two Elements

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );
```

Swap index `1` and index `3`.

### Expected Output

```text
[10, 40, 30, 20]
```

---

## Problem 29: Copy an ArrayList

Create:

```java
ArrayList<Integer> original =
        new ArrayList<>(
                List.of(10, 20, 30)
        );
```

Create an independent copy.

### Requirement

Changing the copy should not change the original.

---

## Problem 30: Create a Sublist

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 30,
                        40, 50
                )
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

---

## Problem 31: Replace Every Element

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
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

## Problem 32: Remove All Odd Numbers

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
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

# 7. Sorting

## Problem 33: Ascending Order

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        50, 10, 40,
                        20, 30
                )
        );
```

Sort in ascending order.

### Expected Output

```text
[10, 20, 30, 40, 50]
```

---

## Problem 34: Descending Order

Sort the same list in descending order.

### Expected Output

```text
[50, 40, 30, 20, 10]
```

### Hint

```java
Comparator.reverseOrder()
```

---

## Problem 35: Sort Strings

Given:

```java
ArrayList<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Python",
                        "C++",
                        "Go"
                )
        );
```

Sort alphabetically.

### Expected Output

```text
[C++, Go, Java, Python]
```

---

# 8. Bulk Operations

## Problem 36: addAll

Given:

```java
ArrayList<Integer> first =
        new ArrayList<>(
                List.of(10, 20)
        );

ArrayList<Integer> second =
        new ArrayList<>(
                List.of(30, 40, 50)
        );
```

Add all elements of `second` to `first`.

### Expected Output

```text
[10, 20, 30, 40, 50]
```

---

## Problem 37: Add Collection at an Index

Given:

```text
[10, 20, 50]
```

Insert:

```text
30, 40
```

at index `2`.

### Expected Output

```text
[10, 20, 30, 40, 50]
```

---

## Problem 38: Remove All Common Elements

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 30,
                        40, 50
                )
        );

List<Integer> remove =
        List.of(20, 40);
```

Remove all values present in `remove`.

### Expected Output

```text
[10, 30, 50]
```

### Hint

```java
removeAll()
```

---

## Problem 39: Retain Common Elements

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 30,
                        40, 50
                )
        );

List<Integer> keep =
        List.of(20, 40, 60);
```

Keep only elements that are also present in `keep`.

### Expected Output

```text
[20, 40]
```

### Hint

```java
retainAll()
```

---

# 9. ArrayList and Arrays

## Problem 40: Convert Array to ArrayList

Given:

```java
String[] languages = {
        "Java",
        "Python",
        "C++"
};
```

Convert it into an `ArrayList<String>`.

---

## Problem 41: Convert ArrayList to Array

Given:

```java
ArrayList<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Python",
                        "C++"
                )
        );
```

Convert it into:

```java
String[]
```

### Hint

```java
toArray()
```

---

## Problem 42: Understand Arrays.asList

What happens?

```java
List<String> languages =
        Arrays.asList(
                "Java",
                "Spring",
                "SQL"
        );

languages.set(1, "Hibernate");
```

What is the resulting list?

---

## Problem 43: Arrays.asList Add Trap

What happens?

```java
List<String> languages =
        Arrays.asList(
                "Java",
                "Spring"
        );

languages.add("SQL");
```

Which exception is thrown?

---

# 10. Size vs Capacity

## Problem 44: Understand Initial Capacity

What is the size of this list?

```java
ArrayList<Integer> numbers =
        new ArrayList<>(100);

System.out.println(
        numbers.size()
);
```

### Expected Output

```text
0
```

### Question

Why is the result not `100`?

---

## Problem 45: Add One Element

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(100);

numbers.add(10);

System.out.println(
        numbers.size()
);
```

### Expected Output

```text
1
```

### Question

What happened to the remaining capacity?

---

## Problem 46: Size vs Capacity

Explain the difference between:

```text
size
```

and:

```text
capacity
```

Use an example.

### Expected Understanding

```text
size
    -> Number of actual elements

capacity
    -> Amount of internal storage currently available
```

---

# 11. ensureCapacity and trimToSize

## Problem 47: ensureCapacity

Create an empty `ArrayList`.

Call:

```java
ensureCapacity(1000);
```

Then print:

```java
size()
```

### Expected Output

```text
0
```

### Question

Why didn't `size()` become `1000`?

---

## Problem 48: trimToSize

Create an `ArrayList` with a large initial capacity.

Add only a few elements.

Then call:

```java
trimToSize();
```

Explain what this operation is intended to do.

---

## Problem 49: Choosing Initial Capacity

Suppose you know that your program will add approximately:

```text
1,000,000
```

elements to an `ArrayList`.

What would be better?

### Option A

```java
ArrayList<Integer> list =
        new ArrayList<>();
```

### Option B

```java
ArrayList<Integer> list =
        new ArrayList<>(1_000_000);
```

Which one is preferable when the estimate is reliable, and why?

---

# 12. Iterator Practice

## Problem 50: Iterate Using Iterator

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 30,
                        40, 50
                )
        );
```

Print all elements using `Iterator`.

---

## Problem 51: Remove Using Iterator

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 15, 20,
                        25, 30
                )
        );
```

Remove all odd numbers using an `Iterator`.

### Expected Output

```text
[10, 20, 30]
```

---

## Problem 52: ListIterator Forward Traversal

Print all elements using:

```java
ListIterator
```

---

## Problem 53: ListIterator Backward Traversal

Given:

```java
ArrayList<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Spring",
                        "SQL"
                )
        );
```

Traverse from the end to the beginning using `ListIterator`.

### Expected Output

```text
SQL
Spring
Java
```

---

# 13. Code Prediction

## Problem 54

What is the output?

```java
ArrayList<Integer> list =
        new ArrayList<>();

list.add(10);
list.add(20);
list.add(10);

System.out.println(list);
```

---

## Problem 55

What is the output?

```java
ArrayList<Integer> list =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

list.set(1, 100);

System.out.println(list);
```

---

## Problem 56

What is the output?

```java
ArrayList<Integer> list =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

list.add(1, 100);

System.out.println(list);
```

---

## Problem 57

What is the output?

```java
ArrayList<Integer> list =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

list.remove(1);

System.out.println(list);
```

---

## Problem 58

What is the output?

```java
ArrayList<Integer> list =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

list.remove(Integer.valueOf(20));

System.out.println(list);
```

---

## Problem 59

What is the output?

```java
ArrayList<Integer> list =
        new ArrayList<>(100);

list.add(10);

System.out.println(list.size());
```

### Expected Output

```text
1
```

---

## Problem 60

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

### Question

Why is this dangerous?

---

# 14. Intermediate Problems

## Problem 61: Remove Duplicates

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 10,
                        30, 20, 40,
                        30
                )
        );
```

Create a new list containing unique elements.

### Expected Output

```text
[10, 20, 30, 40]
```

### Challenge

Solve:

1. Using `Set`
2. Without using `Set`

---

## Problem 62: Find Second Largest

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 50, 20,
                        90, 30, 80
                )
        );
```

Find the second-largest value.

### Expected Output

```text
80
```

### Challenge

Try without sorting.

---

## Problem 63: Find Second Smallest

Given:

```java
ArrayList<Integer> numbers =
        new ArrayList<>(
                List.of(
                        40, 10, 50,
                        20, 30
                )
        );
```

Find the second-smallest value.

### Expected Output

```text
20
```

---

## Problem 64: Move Zeros to the End

Given:

```text
[0, 10, 0, 20, 30, 0, 40]
```

Move all zeros to the end.

### Expected Output

```text
[10, 20, 30, 40, 0, 0, 0]
```

---

## Problem 65: Find Common Elements

Given:

```text
First:
[10, 20, 30, 40]

Second:
[20, 40, 60, 80]
```

Find the common elements.

### Expected Output

```text
[20, 40]
```

---

## Problem 66: Find Difference

Given:

```text
First:
[10, 20, 30, 40]

Second:
[20, 40]
```

Find elements present in the first list but not the second.

### Expected Output

```text
[10, 30]
```

---

## Problem 67: Find Frequency

Given:

```text
[10, 20, 10, 30, 20, 10]
```

Find the frequency of `10`.

### Expected Output

```text
3
```

---

## Problem 68: Most Frequent Element

Given:

```text
[10, 20, 10, 30, 20, 10]
```

Find the most frequent element.

### Expected Output

```text
10
```

---

# 15. Challenge Problems

## Challenge 1: Rotate ArrayList

Given:

```text
[1, 2, 3, 4, 5]
```

Rotate right by `2`.

### Expected Output

```text
[4, 5, 1, 2, 3]
```

### Challenge

Do not use:

```java
Collections.rotate()
```

---

## Challenge 2: Check Sorted

Write a method:

```java
static boolean isSorted(
        ArrayList<Integer> list
)
```

Return `true` if the list is sorted in ascending order.

Example:

```text
[10, 20, 30, 40]
```

returns:

```text
true
```

Example:

```text
[10, 30, 20, 40]
```

returns:

```text
false
```

---

## Challenge 3: Two Sum

Given:

```text
[10, 20, 30, 40, 50]
```

Determine whether any pair sums to:

```text
70
```

### Expected Out
