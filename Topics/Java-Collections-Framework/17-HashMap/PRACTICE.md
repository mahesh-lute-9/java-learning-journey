# HashMap — Practice

> Hands-on practice for `HashMap`, covering basic operations, frequency counting, custom objects, grouping, and DSA-style problems.

---

## Table of Contents

- [1. Basic HashMap Creation](#1-basic-hashmap-creation)
- [2. Insert Key-Value Pairs](#2-insert-key-value-pairs)
- [3. Retrieve Values](#3-retrieve-values)
- [4. Update a Value](#4-update-a-value)
- [5. Remove a Mapping](#5-remove-a-mapping)
- [6. Check for a Key](#6-check-for-a-key)
- [7. Check for a Value](#7-check-for-a-value)
- [8. Find Map Size](#8-find-map-size)
- [9. Iterate Using keySet()](#9-iterate-using-keyset)
- [10. Iterate Using entrySet()](#10-iterate-using-entryset)
- [11. Use getOrDefault()](#11-use-getordefault)
- [12. Use putIfAbsent()](#12-use-putifabsent)
- [13. Use replace()](#13-use-replace)
- [14. Use computeIfAbsent()](#14-use-computeifabsent)
- [15. Use merge()](#15-use-merge)
- [16. Frequency Counting](#16-frequency-counting)
- [17. Find Duplicate Elements](#17-find-duplicate-elements)
- [18. Find the First Non-Repeating Character](#18-find-the-first-non-repeating-character)
- [19. Find the Most Frequent Element](#19-find-the-most-frequent-element)
- [20. Two Sum](#20-two-sum)
- [21. Count Pairs With a Given Sum](#21-count-pairs-with-a-given-sum)
- [22. Find Common Elements](#22-find-common-elements)
- [23. Group Anagrams](#23-group-anagrams)
- [24. Group Students by Branch](#24-group-students-by-branch)
- [25. Character Frequency](#25-character-frequency)
- [26. Word Frequency](#26-word-frequency)
- [27. Prefix Sum with HashMap](#27-prefix-sum-with-hashmap)
- [28. Longest Subarray With Sum K](#28-longest-subarray-with-sum-k)
- [29. Subarray Sum Equals K](#29-subarray-sum-equals-k)
- [30. Custom Object as a Key](#30-custom-object-as-a-key)
- [31. HashMap with Null](#31-hashmap-with-null)
- [32. Mutable Key Experiment](#32-mutable-key-experiment)
- [33. Mini Project](#33-mini-project)
- [34. Practice Checklist](#34-practice-checklist)
- [35. Progress](#35-progress)

---

# 1. Basic HashMap Creation

### Task

Create a `HashMap<Integer, String>` to store student roll numbers and names.

Expected data:

```text
101 -> Mahesh
102 -> Rahul
103 -> Amit
```

### Starter Code

```java
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        HashMap<Integer, String> students = new HashMap<>();

        // Add data here
    }
}
```

### Goal

Practice:

- Creating a HashMap
- Generics
- `put()`

---

# 2. Insert Key-Value Pairs

### Task

Create a HashMap containing:

```text
Java       -> 90
DBMS       -> 85
DSA        -> 95
Operating System -> 88
```

Print the map.

```java
HashMap<String, Integer> marks = new HashMap<>();

// Add subjects and marks
```

### Think About

- Can two subjects have the same marks?
- Can two subjects have the same name?

---

# 3. Retrieve Values

Given:

```java
HashMap<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Amit");
```

### Task

Print:

1. Student with roll number `101`
2. Student with roll number `103`
3. Student with roll number `999`

### Expected

```text
Mahesh
Amit
null
```

---

# 4. Update a Value

Given:

```java
HashMap<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
```

### Task

Change:

```text
102 -> Rahul
```

to:

```text
102 -> Rohan
```

Do not create another key.

---

# 5. Remove a Mapping

Given:

```java
HashMap<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Amit");
```

### Task

Remove student `102`.

Then print the map.

### Bonus

Store the return value of `remove()` and print the removed student's name.

---

# 6. Check for a Key

Given:

```java
HashMap<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
```

### Task

Check whether:

```text
101
105
```

exist in the map.

Use:

```java
containsKey()
```

---

# 7. Check for a Value

Given:

```java
HashMap<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Amit");
```

### Task

Check whether:

```text
Mahesh
Rohan
```

exist as values.

Use:

```java
containsValue()
```

---

# 8. Find Map Size

Create:

```java
HashMap<String, Integer> marks = new HashMap<>();
```

Add five subjects.

### Task

Print:

```text
Total subjects = 5
```

Then remove one subject and print the new size.

---

# 9. Iterate Using keySet()

Given:

```java
HashMap<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Amit");
```

### Task

Print only the keys.

Use:

```java
for (Integer key : students.keySet()) {
    // ...
}
```

---

# 10. Iterate Using entrySet()

Using the same map, print:

```text
101 -> Mahesh
102 -> Rahul
103 -> Amit
```

Use:

```java
for (Map.Entry<Integer, String> entry : students.entrySet()) {
    // ...
}
```

### Interview Practice

Explain why `entrySet()` is preferable when you need both the key and value.

---

# 11. Use getOrDefault()

Given:

```java
HashMap<String, Integer> marks = new HashMap<>();

marks.put("DSA", 95);
marks.put("DBMS", 90);
```

### Task

Print:

```text
DSA -> 95
Java -> 0
```

using:

```java
getOrDefault()
```

---

# 12. Use putIfAbsent()

Given:

```java
HashMap<Integer, String> map = new HashMap<>();

map.put(1, "Java");
```

### Task

Try:

```java
map.putIfAbsent(1, "Python");
map.putIfAbsent(2, "C++");
```

Print the final map.

### Question

Why does the value for key `1` remain `"Java"`?

---

# 13. Use replace()

Given:

```java
HashMap<String, Integer> marks = new HashMap<>();

marks.put("Java", 80);
marks.put("DBMS", 75);
```

### Task

Replace:

```text
Java -> 90
```

using `replace()`.

Then practice conditional replacement:

```java
replace("Java", 90, 95)
```

---

# 14. Use computeIfAbsent()

Create:

```java
HashMap<String, List<String>> groups = new HashMap<>();
```

Group technologies by category.

Expected concept:

```text
Backend -> [Java, Spring Boot]
Frontend -> [HTML, CSS]
Database -> [PostgreSQL, MySQL]
```

### Requirement

Use:

```java
computeIfAbsent()
```

instead of manually checking:

```java
containsKey()
```

---

# 15. Use merge()

Create a frequency map.

Given:

```text
Java Java Python Java Python
```

Use:

```java
merge()
```

to produce:

```text
Java   -> 3
Python -> 2
```

Hint:

```java
map.merge(word, 1, Integer::sum);
```

---

# 16. Frequency Counting

### Problem

Given:

```java
int[] arr = {1, 2, 2, 3, 1, 2, 4, 3, 3};
```

Count the frequency of every element.

Expected:

```text
1 -> 2
2 -> 3
3 -> 3
4 -> 1
```

### Expected Complexity

```text
Time: O(n) expected
Space: O(n)
```

---

# 17. Find Duplicate Elements

### Problem

Given:

```java
int[] arr = {1, 2, 3, 2, 4, 5, 1, 6};
```

Find all duplicate elements.

Expected:

```text
1
2
```

### Requirement

Use a HashMap or frequency map.

### Bonus

Solve the same problem using `HashSet`.

---

# 18. Find the First Non-Repeating Character

### Problem

Given:

```text
"swiss"
```

Find the first character that appears exactly once.

Expected:

```text
w
```

### Approach

1. Count character frequencies.
2. Traverse the string again.
3. Return the first character with frequency `1`.

### Complexity

```text
Time: O(n) expected
Space: O(k)
```

where `k` is the number of distinct characters.

---

# 19. Find the Most Frequent Element

### Problem

Given:

```java
int[] arr = {4, 1, 2, 4, 2, 4, 3, 2};
```

Find the element with the highest frequency.

Expected:

```text
4
```

### Follow-up

What should your program do if multiple elements have the same maximum frequency?

Define the tie-breaking rule explicitly.

---

# 20. Two Sum

### Problem

Given:

```java
int[] nums = {2, 7, 11, 15};
int target = 9;
```

Find the indices of two numbers whose sum equals the target.

Expected:

```text
[0, 1]
```

### Requirement

Use a HashMap.

### Hint

For every:

```text
nums[i]
```

calculate:

```text
complement = target - nums[i]
```

Check whether the complement already exists.

### Complexity

```text
Time: O(n) expected
Space: O(n)
```

---

# 21. Count Pairs With a Given Sum

### Problem

Given:

```java
int[] arr = {1, 5, 7, -1, 5};
int target = 6;
```

Count the number of pairs whose sum is `6`.

Expected count:

```text
3
```

Possible pairs:

```text
1 + 5
1 + 5
7 + (-1)
```

### Requirement

Use a frequency HashMap.

---

# 22. Find Common Elements

### Problem

Given:

```java
int[] a = {1, 2, 3, 4, 5};
int[] b = {3, 4, 5, 6, 7};
```

Find common elements.

Expected:

```text
3
4
5
```

### Follow-up

Solve:

1. With `HashMap`
2. With `HashSet`

Compare the two approaches.

---

# 23. Group Anagrams

### Problem

Given:

```java
String[] words = {
    "eat",
    "tea",
    "tan",
    "ate",
    "nat",
    "bat"
};
```

Group anagrams together.

Expected grouping concept:

```text
[eat, tea, ate]
[tan, nat]
[bat]
```

### Hint

Sort every word:

```text
eat -> aet
tea -> aet
ate -> aet
```

Use the sorted string as the HashMap key.

Possible structure:

```java
HashMap<String, List<String>> groups;
```

---

# 24. Group Students by Branch

Create a `Student` class:

```java
class Student {

    String name;
    String branch;

    Student(String name, String branch) {
        this.name = name;
        this.branch = branch;
    }
}
```

Given:

```text
Mahesh -> Computer Science
Rahul  -> Mechanical
Amit   -> Computer Science
Sneha  -> Electronics
```

Group students by branch.

Expected:

```text
Computer Science -> [Mahesh, Amit]
Mechanical       -> [Rahul]
Electronics      -> [Sneha]
```

### Requirement

Use:

```java
HashMap<String, List<Student>>
```

and:

```java
computeIfAbsent()
```

---

# 25. Character Frequency

### Problem

Given:

```text
"programming"
```

Count every character.

Example format:

```text
p -> 1
r -> 2
o -> 1
g -> 2
...
```

### Bonus

Print the characters in the order in which they first appeared.

Which Map implementation would help with that requirement?

---

# 26. Word Frequency

### Problem

Given:

```text
"java is powerful and java is popular"
```

Count each word.

Expected:

```text
java    -> 2
is      -> 2
powerful -> 1
and     -> 1
popular -> 1
```

### Requirement

Use:

```java
HashMap<String, Integer>
```

### Bonus

Use:

```java
merge()
```

instead of:

```java
getOrDefault()
```

---

# 27. Prefix Sum with HashMap

### Problem

Given:

```java
int[] arr = {1, -1, 2, 3, -2};
```

Determine whether a subarray with sum `0` exists.

### Key Idea

Maintain:

```text
prefixSum
```

and store previously seen prefix sums.

If the same prefix sum appears again:

```text
prefixSum[i] == prefixSum[j]
```

then:

```text
sum(i + 1 ... j) = 0
```

### Goal

Implement the solution using `HashMap<Integer, Integer>` or an appropriate set-based approach.

---

# 28. Longest Subarray With Sum K

### Problem

Given:

```java
int[] arr = {10, 5, 2, 7, 1, 9};
int k = 15;
```

Find the length of the longest subarray whose sum is `15`.

Expected:

```text
4
```

One such subarray:

```text
5, 2, 7, 1
```

### Key Idea

Store the first index at which each prefix sum appears.

For current prefix sum:

```text
prefixSum
```

look for:

```text
prefixSum - k
```

in the map.

### Expected Complexity

```text
Time: O(n)
Space: O(n)
```

---

# 29. Subarray Sum Equals K

### Problem

Given:

```java
int[] nums = {1, 1, 1};
int k = 2;
```

Count the number of subarrays whose sum equals `2`.

Expected:

```text
2
```

Subarrays:

```text
[1, 1]
[1, 1]
```

### Key Idea

Store prefix-sum frequencies.

If:

```text
currentPrefix - k
```

has appeared `x` times, then there are `x` subarrays ending at the current index with sum `k`.

---

# 30. Custom Object as a Key

Create:

```java
class Employee {

    int id;
    String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

Create:

```java
HashMap<Employee, String> employees = new HashMap<>();
```

### Task

Use an `Employee` object as a key.

Then create another Employee object containing the same logical data.

Test:

```java
employees.get(secondEmployee);
```

### Observe

Without properly overriding:

```java
equals()
hashCode()
```

the result may not behave as expected.

---

# 31. HashMap with Null

Create a HashMap and test:

```java
map.put(null, "Unknown");
map.put("Java", null);
map.put("Python", null);
```

### Tasks

Check:

```java
map.containsKey(null);
map.get(null);
map.containsKey("Java");
map.get("Java");
```

Then explain the difference between:

```text
key does not exist
```

and:

```text
key exists with null value
```

---

# 32. Mutable Key Experiment

Create a mutable class:

```java
class Student {

    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Student))
            return false;

        Student other = (Student) obj;

        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
```

### Task

1. Create a Student.
2. Insert it as a key.
3. Retrieve the value successfully.
4. Change `id`.
5. Try retrieving the same object again.

Example:

```java
Student s = new Student(101, "Mahesh");

map.put(s, "Computer Science");

System.out.println(map.get(s));

s.id = 999;

System.out.println(map.get(s));
```

### Goal

Understand why mutable keys are dangerous.

---

# 33. Mini Project

## Student Marks Management System

Build a small console-based application using:

```java
HashMap<Integer, Student>
```

Create:

```java
class Student {

    int rollNo;
    String name;
    double marks;
}
```

### Required Features

```text
1. Add Student
2. Find Student by Roll Number
3. Update Marks
4. Remove Student
5. Display All Students
6. Check Whether Student Exists
7. Find Highest Marks
8. Find Average Marks
9. Count Students
10. Exit
```

### Suggested Structure

```text
HashMap<Integer, Student>
          |
          +-- Add
          +-- Search
          +-- Update
          +-- Delete
          +-- Display
          +-- Statistics
```

### Bonus Features

Add:

```text
11. Find Students Above 80
12. Find Students Below 40
13. Group Students by Grade
14. Find Top 3 Students
15. Export Results
```

---

# 34. Practice Checklist

## Basic Operations

- [ ] Create a HashMap
- [ ] Add mappings
- [ ] Retrieve values
- [ ] Update values
- [ ] Remove mappings
- [ ] Check keys
- [ ] Check values
- [ ] Find size
- [ ] Clear the map

---

## Modern HashMap Methods

- [ ] `getOrDefault()`
- [ ] `putIfAbsent()`
- [ ] `replace()`
- [ ] `replaceAll()`
- [ ] `computeIfAbsent()`
- [ ] `computeIfPresent()`
- [ ] `compute()`
- [ ] `merge()`
- [ ] `forEach()`

---

## Iteration

- [ ] `keySet()`
- [ ] `values()`
- [ ] `entrySet()`
- [ ] `Map.Entry`
- [ ] `forEach()`

---

## DSA

- [ ] Frequency counting
- [ ] Duplicate detection
- [ ] First non-repeating character
- [ ] Most frequent element
- [ ] Two Sum
- [ ] Pair sum
- [ ] Common elements
- [ ] Group anagrams
- [ ] Character frequency
- [ ] Word frequency
- [ ] Prefix sum
- [ ] Longest subarray with sum K
- [ ] Subarray sum equals K

---

## Advanced

- [ ] Custom object as key
- [ ] `equals()` implementation
- [ ] `hashCode()` implementation
- [ ] Hash collisions
- [ ] Null keys and values
- [ ] Mutable key problem
- [ ] Load factor
- [ ] Resizing
- [ ] Tree bins

---

# 35. Progress

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
│   └── INTERVIEW.md   [x]
│
├── 13-HashSet
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 14-LinkedHashSet
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 15-TreeSet
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 16-Map
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 17-HashMap
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [ ]
│
├── 18-LinkedHashMap
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

> **`17-HashMap/PRACTICE.md` completed.**
>
> **Next: `17-HashMap/INTERVIEW.md`**.
