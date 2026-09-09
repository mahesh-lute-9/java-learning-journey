# Set — Practice

## Table of Contents

1. [Practice Goals](#1-practice-goals)
2. [Creating a Set](#2-creating-a-set)
3. [Adding Elements](#3-adding-elements)
4. [Duplicate Practice](#4-duplicate-practice)
5. [Removing Elements](#5-removing-elements)
6. [Checking Elements](#6-checking-elements)
7. [Size and Empty Check](#7-size-and-empty-check)
8. [Iteration Practice](#8-iteration-practice)
9. [Set Operations](#9-set-operations)
10. [Union Practice](#10-union-practice)
11. [Intersection Practice](#11-intersection-practice)
12. [Difference Practice](#12-difference-practice)
13. [Subset Practice](#13-subset-practice)
14. [HashSet Practice](#14-hashset-practice)
15. [LinkedHashSet Practice](#15-linkedhashset-practice)
16. [TreeSet Practice](#16-treeset-practice)
17. [Output Prediction](#17-output-prediction)
18. [Basic Coding Problems](#18-basic-coding-problems)
19. [Intermediate Coding Problems](#19-intermediate-coding-problems)
20. [Challenge Problems](#20-challenge-problems)
21. [Scenario-Based Practice](#21-scenario-based-practice)
22. [Comparison Practice](#22-comparison-practice)
23. [Practice Checklist](#23-practice-checklist)
24. [Final Goal](#24-final-goal)
25. [Progress](#25-progress)

---

# 1. Practice Goals

By completing this practice file, you should be able to:

- [ ] Create a Set using `HashSet`
- [ ] Create a Set using `LinkedHashSet`
- [ ] Create a Set using `TreeSet`
- [ ] Add elements using `add()`
- [ ] Understand the return value of `add()`
- [ ] Remove elements using `remove()`
- [ ] Check elements using `contains()`
- [ ] Use `size()`
- [ ] Use `isEmpty()`
- [ ] Use `clear()`
- [ ] Iterate through a Set
- [ ] Understand duplicate handling
- [ ] Understand Set ordering
- [ ] Perform union
- [ ] Perform intersection
- [ ] Perform difference
- [ ] Check subsets
- [ ] Understand `HashSet`
- [ ] Understand `LinkedHashSet`
- [ ] Understand `TreeSet`
- [ ] Choose the correct Set implementation
- [ ] Solve duplicate-related problems using Set

---

# 2. Creating a Set

## Practice 1 — Basic HashSet

Create a Set of integers:

```java
Set<Integer> numbers = new HashSet<>();
```

Add:

```text
10
20
30
40
```

Print the Set.

### Expected Result

The Set should contain:

```text
10
20
30
40
```

The exact iteration order should not be assumed for `HashSet`.

---

## Practice 2 — String Set

Create:

```java
Set<String> names = new HashSet<>();
```

Add:

```text
Amit
Rahul
Priya
Sneha
```

Print all elements.

---

## Practice 3 — Interface Reference

Write:

```java
Set<Integer> numbers = new HashSet<>();
```

instead of:

```java
HashSet<Integer> numbers = new HashSet<>();
```

### Question

Why is the first approach generally preferred?

### Answer

It programs to the interface:

```text
Set
```

rather than coupling the variable to one specific implementation.

---

# 3. Adding Elements

## Practice 4 — `add()`

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers);
```

Verify that all three values are present.

---

## Practice 5 — Check Return Value

Predict the output:

```java
Set<Integer> numbers = new HashSet<>();

System.out.println(numbers.add(10));
System.out.println(numbers.add(20));
System.out.println(numbers.add(10));
```

### Expected Output

```text
true
true
false
```

Why?

```text
10 → added
20 → added
10 → already exists
```

---

## Practice 6 — Add Multiple Duplicates

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(10);
numbers.add(10);
numbers.add(20);
numbers.add(20);

System.out.println(numbers);
System.out.println(numbers.size());
```

### Expected Size

```text
2
```

---

# 4. Duplicate Practice

## Practice 7 — Remove Duplicates

Given:

```text
10 20 10 30 20 40 30
```

Store the values in a Set.

Expected unique values:

```text
10 20 30 40
```

---

## Practice 8 — Count Unique Elements

Given:

```text
[10, 20, 10, 30, 20, 40]
```

Use a Set to determine the number of unique elements.

### Expected

```text
4
```

---

## Practice 9 — Detect Duplicate

Given:

```text
[10, 20, 30, 20, 40]
```

Determine whether the array contains a duplicate.

### Hint

Compare:

```text
array length
```

with:

```text
Set size
```

If:

```text
array length != set size
```

then duplicates exist.

---

## Practice 10 — Duplicate Detection

Write a program that returns:

```text
true
```

if an array contains duplicates.

Examples:

```text
[1, 2, 3, 4]       → false
[1, 2, 3, 2]       → true
[5, 5, 5]          → true
[1, 2, 3, 4, 5]    → false
```

---

# 5. Removing Elements

## Practice 11 — Remove One Element

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

numbers.remove(20);

System.out.println(numbers);
```

Verify that `20` is removed.

---

## Practice 12 — Return Value of `remove()`

Predict:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);

System.out.println(numbers.remove(10));
System.out.println(numbers.remove(10));
```

### Expected

```text
true
false
```

The first call removes `10`.

The second call cannot remove it because it no longer exists.

---

## Practice 13 — `clear()`

Create a Set:

```java
Set<String> names = new HashSet<>();
```

Add five names.

Then:

```java
names.clear();
```

Check:

```java
System.out.println(names.isEmpty());
```

### Expected

```text
true
```

---

# 6. Checking Elements

## Practice 14 — `contains()`

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers.contains(20));
System.out.println(numbers.contains(50));
```

### Expected

```text
true
false
```

---

## Practice 15 — Membership Check

Create a Set containing:

```text
Java
Python
C++
JavaScript
```

Ask the user for a language and check whether it exists.

Example:

```text
Input:
Java

Output:
Language exists
```

---

# 7. Size and Empty Check

## Practice 16 — `size()`

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
numbers.add(30);

System.out.println(numbers.size());
```

### Expected

```text
3
```

---

## Practice 17 — `isEmpty()`

Write a program that:

1. Creates an empty Set
2. Checks `isEmpty()`
3. Adds elements
4. Checks `isEmpty()` again
5. Calls `clear()`
6. Checks `isEmpty()` again

Expected:

```text
true
false
true
```

---

# 8. Iteration Practice

## Practice 18 — Enhanced For Loop

```java
Set<String> names = new HashSet<>();

names.add("A");
names.add("B");
names.add("C");

for (String name : names) {
    System.out.println(name);
}
```

Practice iterating through the Set.

Remember:

> Do not assume a particular order when using `HashSet`.

---

## Practice 19 — Iterator

Use:

```java
Iterator<Integer> iterator = numbers.iterator();
```

and print every element.

---

## Practice 20 — Remove During Iteration

Use an Iterator to remove all even numbers.

Example:

```text
Input:
[10, 15, 20, 25, 30]

Output:
[15, 25]
```

### Hint

Use:

```java
iterator.remove();
```

instead of directly modifying the Set inside the loop.

---

# 9. Set Operations

Practice these four concepts:

```text
Union
Intersection
Difference
Subset
```

Example:

```text
A = {1, 2, 3}
B = {3, 4, 5}
```

---

# 10. Union Practice

Union means:

```text
A ∪ B
```

All elements from both Sets.

Given:

```text
A = {1, 2, 3}
B = {3, 4, 5}
```

Expected:

```text
{1, 2, 3, 4, 5}
```

---

## Java Practice

```java
Set<Integer> a = new HashSet<>();
a.add(1);
a.add(2);
a.add(3);

Set<Integer> b = new HashSet<>();
b.add(3);
b.add(4);
b.add(5);

Set<Integer> union = new HashSet<>(a);

union.addAll(b);

System.out.println(union);
```

---

# 11. Intersection Practice

Intersection means:

```text
A ∩ B
```

Only elements common to both Sets.

Given:

```text
A = {1, 2, 3}
B = {3, 4, 5}
```

Expected:

```text
{3}
```

---

## Java Practice

```java
Set<Integer> intersection = new HashSet<>(a);

intersection.retainAll(b);

System.out.println(intersection);
```

---

# 12. Difference Practice

Difference means:

```text
A - B
```

Elements present in `A` but not in `B`.

Given:

```text
A = {1, 2, 3}
B = {3, 4, 5}
```

Expected:

```text
{1, 2}
```

---

## Java Practice

```java
Set<Integer> difference = new HashSet<>(a);

difference.removeAll(b);

System.out.println(difference);
```

---

## Reverse Difference

Now calculate:

```text
B - A
```

Expected:

```text
{4, 5}
```

Practice this by creating a copy of `b` and calling:

```java
removeAll(a);
```

---

# 13. Subset Practice

Given:

```text
A = {1, 2, 3, 4, 5}
B = {2, 3}
```

Check whether `B` is a subset of `A`.

Use:

```java
A.containsAll(B)
```

Expected:

```text
true
```

---

## Practice

Given:

```text
A = {10, 20, 30, 40}
B = {20, 30}
C = {20, 50}
```

Predict:

```java
A.containsAll(B);
A.containsAll(C);
```

### Expected

```text
true
false
```

---

# 14. HashSet Practice

## Practice 21 — Basic HashSet

Create:

```java
Set<Integer> numbers = new HashSet<>();
```

Add:

```text
50
10
40
20
30
```

Print the Set.

### Important

Do not expect insertion order.

---

## Practice 22 — Unique Words

Given:

```text
Java Java Python Java C++ Python
```

Use a `HashSet<String>` to store unique words.

Expected unique words:

```text
Java
Python
C++
```

The display order is not guaranteed.

---

## Practice 23 — Common Elements

Given:

```text
A = [1, 2, 3, 4]
B = [3, 4, 5, 6]
```

Use Sets to find:

```text
[3, 4]
```

---

# 15. LinkedHashSet Practice

## Practice 24 — Preserve Insertion Order

Create:

```java
Set<Integer> numbers = new LinkedHashSet<>();
```

Add:

```text
30
10
20
40
```

Expected iteration:

```text
30
10
20
40
```

---

## Practice 25 — Remove Duplicates While Preserving Order

Given:

```text
[10, 20, 10, 30, 20, 40]
```

Use `LinkedHashSet`.

Expected:

```text
[10, 20, 30, 40]
```

This is a very useful practical pattern.

---

## Practice 26 — Compare HashSet and LinkedHashSet

Run:

```java
Set<Integer> hashSet = new HashSet<>();
Set<Integer> linkedHashSet = new LinkedHashSet<>();

int[] values = {30, 10, 20, 40};

for (int value : values) {
    hashSet.add(value);
    linkedHashSet.add(value);
}

System.out.println("HashSet: " + hashSet);
System.out.println("LinkedHashSet: " + linkedHashSet);
```

Observe:

```text
HashSet
→ no guaranteed insertion order

LinkedHashSet
→ insertion order
```

---

# 16. TreeSet Practice

## Practice 27 — Automatic Sorting

Create:

```java
Set<Integer> numbers = new TreeSet<>();
```

Add:

```text
50
10
40
20
30
```

Expected iteration:

```text
10
20
30
40
50
```

---

## Practice 28 — Remove Duplicates and Sort

Given:

```text
[40, 10, 30, 20, 10, 40]
```

Use `TreeSet`.

Expected:

```text
[10, 20, 30, 40]
```

This demonstrates:

```text
uniqueness
+
sorting
```

---

## Practice 29 — String Sorting

```java
Set<String> names = new TreeSet<>();

names.add("Rahul");
names.add("Amit");
names.add("Priya");
names.add("Kiran");
```

Expected alphabetical order:

```text
Amit
Kiran
Priya
Rahul
```

---

# 17. Output Prediction

## Question 1

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);
set.add(10);

System.out.println(set.size());
```

### Answer

```text
2
```

---

## Question 2

```java
Set<Integer> set = new HashSet<>();

System.out.println(set.add(10));
System.out.println(set.add(10));
```

### Answer

```text
true
false
```

---

## Question 3

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(30);
set.add(10);
set.add(20);

System.out.println(set);
```

### Answer

```text
[30, 10, 20]
```

---

## Question 4

```java
Set<Integer> set = new TreeSet<>();

set.add(30);
set.add(10);
set.add(20);

System.out.println(set);
```

### Answer

```text
[10, 20, 30]
```

---

## Question 5

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);
set.remove(10);

System.out.println(set.contains(10));
```

### Answer

```text
false
```

---

## Question 6

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);
set.add(30);

set.clear();

System.out.println(set.isEmpty());
```

### Answer

```text
true
```

---

## Question 7

```java
Set<Integer> a = new HashSet<>();
Set<Integer> b = new HashSet<>();

a.add(1);
a.add(2);
a.add(3);

b.add(3);
b.add(4);

Set<Integer> result = new HashSet<>(a);

result.retainAll(b);

System.out.println(result);
```

### Answer

```text
[3]
```

The order is not guaranteed because `HashSet` is being used.

---

# 18. Basic Coding Problems

## Problem 1 — Remove Duplicates

Given an integer array:

```text
[10, 20, 10, 30, 20, 40]
```

Remove duplicates using a Set.

Expected unique values:

```text
10 20 30 40
```

---

## Problem 2 — Count Unique Elements

Given:

```text
[1, 2, 2, 3, 4, 4, 5]
```

Return the number of unique elements.

Expected:

```text
5
```

---

## Problem 3 — Check Duplicate Array

Write:

```java
boolean containsDuplicate(int[] arr)
```

Examples:

```text
[1, 2, 3, 4] → false
[1, 2, 3, 2] → true
```

### Hint

Use:

```java
Set<Integer>
```

---

## Problem 4 — Find Common Elements

Given:

```text
A = [1, 2, 3, 4]
B = [3, 4, 5, 6]
```

Find common elements.

Expected:

```text
[3, 4]
```

---

## Problem 5 — Find Union

Given:

```text
A = [1, 2, 3]
B = [3, 4, 5]
```

Find the union.

Expected:

```text
[1, 2, 3, 4, 5]
```

---

## Problem 6 — Find Difference

Given:

```text
A = [1, 2, 3, 4]
B = [3, 4, 5]
```

Find:

```text
A - B
```

Expected:

```text
[1, 2]
```

---

## Problem 7 — Check Subset

Write:

```java
boolean isSubset(Set<Integer> a, Set<Integer> b)
```

Return `true` if `b` is a subset of `a`.

Example:

```text
A = {1, 2, 3, 4}
B = {2, 3}

Result:
true
```

---

## Problem 8 — First Repeating Element

Given:

```text
[10, 20, 30, 20, 40]
```

Find the first element that appears again.

Expected:

```text
20
```

### Hint

Maintain a Set of elements already seen.

---

## Problem 9 — First Non-Repeating Element

Given:

```text
[4, 5, 1, 2, 1, 4, 5]
```

Find the first element that occurs only once.

Expected:

```text
2
```

### Note

A Set alone is not enough for counting frequency. You will need an additional data structure such as a `Map`.

---

## Problem 10 — Unique Characters

Given:

```text
"programming"
```

Find all unique characters.

Expected unique characters:

```text
p
r
o
g
a
m
i
n
```

Use:

```java
Set<Character>
```

---

# 19. Intermediate Coding Problems

## Problem 11 — Intersection of Two Arrays

Given:

```text
A = [1, 2, 2, 3]
B = [2, 2, 4]
```

Find the unique intersection.

Expected:

```text
[2]
```

---

## Problem 12 — Union of Two Arrays

Given:

```text
A = [1, 2, 2, 3]
B = [3, 4, 4, 5]
```

Find unique union.

Expected:

```text
[1, 2, 3, 4, 5]
```

---

## Problem 13 — Longest Consecutive Sequence

Given:

```text
[100, 4, 200, 1, 3, 2]
```

Find the length of the longest consecutive sequence.

Expected:

```text
4
```

Because:

```text
1, 2, 3, 4
```

forms the longest sequence.

### Hint

Use:

```java
Set<Integer>
```

to achieve an efficient solution.

---

## Problem 14 — Missing Number

Given numbers from:

```text
0 to n
```

with one number missing:

```text
[3, 0, 1]
```

Find the missing number.

Expected:

```text
2
```

Try solving this using a Set first.

Then think about whether a mathematical or XOR solution can use less extra space.

---

## Problem 15 — Common Elements in Three Arrays

Given:

```text
A = [1, 5, 10, 20, 40, 80]
B = [6, 7, 20, 80, 100]
C = [3, 4, 15, 20, 30, 70, 80, 120]
```

Find common elements.

Expected:

```text
20, 80
```

---

## Problem 16 — Unique Words

Given a sentence:

```text
"java is easy and java is powerful"
```

Find unique words.

Expected:

```text
java
is
easy
and
powerful
```

Use:

```java
Set<String>
```

---

## Problem 17 — Unique Emails

Given:

```text
a@gmail.com
b@gmail.com
a@gmail.com
c@gmail.com
b@gmail.com
```

Find the number of unique email addresses.

Expected:

```text
3
```

---

# 20. Challenge Problems

## Challenge 1 — Remove Duplicates While Preserving Order

Given:

```text
[4, 2, 4, 1, 2, 3, 1]
```

Return:

```text
[4, 2, 1, 3]
```

### Requirement

Use:

```java
LinkedHashSet<Integer>
```

---

## Challenge 2 — Remove Duplicates and Sort

Given:

```text
[50, 10, 40, 20, 10, 30, 40]
```

Return:

```text
[10, 20, 30, 40, 50]
```

### Requirement

Use:

```java
TreeSet<Integer>
```

---

## Challenge 3 — Find Missing Values

Given:

```text
[1, 2, 4, 6, 7]
```

Assume the expected range is:

```text
1 to 7
```

Find the missing values.

Expected:

```text
3, 5
```

Use a Set.

---

## Challenge 4 — Check if Two Arrays Have the Same Unique Elements

Given:

```text
A = [1, 2, 2, 3, 3]
B = [3, 2, 1]
```

Return:

```text
true
```

Because both contain the same unique values:

```text
{1, 2, 3}
```

---

## Challenge 5 — Find Symmetric Difference

Given:

```text
A = {1, 2, 3, 4}
B = {3, 4, 5, 6}
```

Find elements that appear in exactly one Set.

Expected:

```text
{1, 2, 5, 6}
```

---

## Challenge 6 — Detect Duplicate Username

Given:

```text
["amit", "rahul", "priya", "amit"]
```

Determine whether a username appears more than once.

Expected:

```text
true
```

---

## Challenge 7 — Unique Visitors

A website receives:

```text
"user1"
"user2"
"user1"
"user3"
"user2"
"user4"
```

Find the number of unique visitors.

Expected:

```text
4
```

---

# 21. Scenario-Based Practice

## Scenario 1 — Username Validation

A registration system must ensure usernames are unique.

### Question

Which data structure is suitable?

```text
Set<String>
```

Example:

```java
Set<String> usernames = new HashSet<>();
```

Use:

```java
usernames.contains(username)
```

to check whether a username already exists.

---

## Scenario 2 — Tags

A blog post can have tags:

```text
java
backend
java
spring
backend
```

You want each tag only once.

Use:

```text
HashSet
```

---

## Scenario 3 — Preserve Tag Order

Suppose the tags should remain in the order the user entered them.

Use:

```text
LinkedHashSet
```

---

## Scenario 4 — Sorted Student Names

You need:

- unique names
- alphabetical order

Use:

```text
TreeSet
```

---

## Scenario 5 — Visited Pages

You need to track which pages a crawler has already visited.

Use:

```java
Set<String> visited = new HashSet<>();
```

Before processing:

```java
if (!visited.contains(url)) {
    visited.add(url);

    // process URL
}
```

---

## Scenario 6 — Remove Duplicate IDs

Input:

```text
101
102
101
103
102
104
```

Requirement:

```text
Keep only unique IDs
```

Use:

```text
HashSet
```

If original order must be preserved:

```text
LinkedHashSet
```

If sorted order is required:

```text
TreeSet
```

---

## Scenario 7 — Priority Processing

You have:

```text
High
Medium
Low
```

and processing must happen based on priority.

Should you use a Set?

```text
No.
```

A Set is about uniqueness, not priority-based processing.

---

# 22. Comparison Practice

## Practice 1 — Which Set?

### Requirement

Unique elements only.

Order does not matter.

Answer:

```text
HashSet
```

---

### Requirement

Unique elements + insertion order.

Answer:

```text
LinkedHashSet
```

---

### Requirement

Unique elements + sorted order.

Answer:

```text
TreeSet
```

---

## Practice 2 — Choose the Correct Collection

| Requirement | Choose |
|---|---|
| Unique elements | `HashSet` |
| Unique + insertion order | `LinkedHashSet` |
| Unique + sorted order | `TreeSet` |
| Duplicates + indexed access | `ArrayList` |
| FIFO | `Queue` / `ArrayDeque` |
| LIFO | `ArrayDeque` |
| Priority-based processing | `PriorityQueue` |

---

## Practice 3 — Explain Your Choice

For each scenario, explain why you chose the collection.

### A

```text
Need unique employee IDs.
```

Answer:

```text
HashSet
```

---

### B

```text
Need unique employee IDs in insertion order.
```

Answer:

```text
LinkedHashSet
```

---

### C

```text
Need unique employee IDs in ascending order.
```

Answer:

```text
TreeSet
```

---

### D

```text
Need duplicate employee records and index access.
```

Answer:

```text
ArrayList
```

---

# 23. Practice Checklist

## Basic Set

- [ ] Create a `Set`
- [ ] Understand why `Set` cannot be instantiated directly
- [ ] Create a `HashSet`
- [ ] Create a `LinkedHashSet`
- [ ] Create a `TreeSet`
- [ ] Add elements
- [ ] Remove elements
- [ ] Check membership
- [ ] Check size
- [ ] Check whether empty
- [ ] Clear a Set

## Duplicates

- [ ] Add duplicate values
- [ ] Understand why duplicates are ignored
- [ ] Understand the return value of `add()`
- [ ] Count unique elements
- [ ] Detect duplicates
- [ ] Remove duplicates from an array

## Iteration

- [ ] Use enhanced `for`
- [ ] Use `Iterator`
- [ ] Safely remove elements while iterating

## Set Operations

- [ ] Union
- [ ] Intersection
- [ ] Difference
- [ ] Reverse difference
- [ ] Subset
- [ ] Symmetric difference

## HashSet

- [ ] Understand uniqueness
- [ ] Understand lack of guaranteed ordering
- [ ] Practice duplicate removal
- [ ] Practice membership checking

## LinkedHashSet

- [ ] Understand insertion order
- [ ] Remove duplicates while preserving order

## TreeSet

- [ ] Understand sorted order
- [ ] Remove duplicates and sort
- [ ] Sort strings

## Problem Solving

- [ ] Duplicate detection
- [ ] Unique characters
- [ ] Common elements
- [ ] Union
- [ ] Difference
- [ ] Subset
- [ ] Missing values
- [ ] Longest consecutive sequence
- [ ] Unique words
- [ ] Unique IDs

---

# 24. Final Goal

Before moving to the interview questions, you should immediately understand:

```text
Set
 │
 ├── HashSet
 │      ↓
 │   Unique
 │   No guaranteed order
 │
 ├── LinkedHashSet
 │      ↓
 │   Unique
 │   Insertion order
 │
 └── TreeSet
        ↓
     Unique
     Sorted order
```

You should also know:

```java
set.add(value);
set.remove(value);
set.contains(value);
set.size();
set.isEmpty();
set.clear();
```

And the most important Set operations:

```java
addAll();       // Union
retainAll();    // Intersection
removeAll();    // Difference
containsAll();  // Subset check
```

The core idea:

```text
Set = Unique Elements
```

Then choose the implementation based on ordering:

```text
No ordering requirement
        ↓
    HashSet

Insertion order required
        ↓
  LinkedHashSet

Sorted order required
        ↓
    TreeSet
```

---

# 25. Progress

```text
Java Collections Framework
│
├── 01-Iterable
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 02-Collection-Interface
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 03-List
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 04-ArrayList
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 05-LinkedList
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 06-Vector
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 07-Stack
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 08-Queue
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 09-PriorityQueue
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 10-Deque
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 11-ArrayDeque
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 12-Set
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [ ]
│
├── 13-HashSet
├── 14-LinkedHashSet
├── 15-TreeSet
├── 16-Map
├── 17-HashMap
├── 18-LinkedHashMap
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

**`12-Set/PRACTICE.md` is complete. Next: `12-Set/INTERVIEW.md`.**
