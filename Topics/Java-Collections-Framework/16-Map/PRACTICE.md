# Map — Practice

> Hands-on practice for understanding Java `Map`, key-value pairs, map operations, iteration, views, and common real-world problems.

---

## 📚 Table of Contents

1. [Practice Goals](#1-practice-goals)
2. [Create a Map](#2-create-a-map)
3. [Add Key-Value Pairs](#3-add-key-value-pairs)
4. [Retrieve Values](#4-retrieve-values)
5. [Update Values](#5-update-values)
6. [Duplicate Keys](#6-duplicate-keys)
7. [Duplicate Values](#7-duplicate-values)
8. [remove()](#8-remove)
9. [containsKey()](#9-containskey)
10. [containsValue()](#10-containsvalue)
11. [size() and isEmpty()](#11-size-and-isempty)
12. [clear()](#12-clear)
13. [getOrDefault()](#13-getordefault)
14. [putIfAbsent()](#14-putifabsent)
15. [replace()](#15-replace)
16. [replaceAll()](#16-replaceall)
17. [keySet()](#17-keyset)
18. [values()](#18-values)
19. [entrySet()](#19-entryset)
20. [Map.Entry](#20-mapentry)
21. [Iterating Over a Map](#21-iterating-over-a-map)
22. [forEach()](#22-foreach)
23. [Null Practice](#23-null-practice)
24. [Frequency Counting](#24-frequency-counting)
25. [Character Frequency](#25-character-frequency)
26. [Word Frequency](#26-word-frequency)
27. [Find Maximum Frequency](#27-find-maximum-frequency)
28. [Find Duplicate Elements](#28-find-duplicate-elements)
29. [First Non-Repeating Character](#29-first-non-repeating-character)
30. [Grouping Data](#30-grouping-data)
31. [Map Conversion Practice](#31-map-conversion-practice)
32. [Nested Maps](#32-nested-maps)
33. [Basic Coding Problems](#33-basic-coding-problems)
34. [Intermediate Problems](#34-intermediate-problems)
35. [Challenge Problems](#35-challenge-problems)
36. [Scenario-Based Practice](#36-scenario-based-practice)
37. [Implementation Comparison](#37-implementation-comparison)
38. [Practice Checklist](#38-practice-checklist)
39. [Final Goal](#39-final-goal)
40. [Progress](#40-progress)

---

# 1. Practice Goals

By completing this practice file, you should be able to:

- Create a `Map`.
- Add key-value pairs.
- Retrieve values using keys.
- Update existing mappings.
- Remove mappings.
- Check keys and values.
- Iterate over maps.
- Use `keySet()`.
- Use `values()`.
- Use `entrySet()`.
- Work with `Map.Entry`.
- Use `getOrDefault()`.
- Use `putIfAbsent()`.
- Use `replace()`.
- Solve frequency-counting problems.
- Solve duplicate-detection problems.
- Use maps for grouping and lookup.
- Understand when to use different `Map` implementations.

---

# 2. Create a Map

`Map` is an interface, so create it using an implementation.

```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<Integer, String> students = new HashMap<>();

        System.out.println(students);
    }
}
```

### Output

```text
{}
```

---

## Practice

Create:

```java
Map<Integer, String> students = new HashMap<>();
```

Use:

```text
101 → Mahesh
102 → Rahul
103 → Priya
```

---

# 3. Add Key-Value Pairs

Use `put()`.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Priya");

System.out.println(students);
```

The exact iteration order of a `HashMap` is not guaranteed.

Conceptually:

```text
101 → Mahesh
102 → Rahul
103 → Priya
```

---

## Practice Task

Create a map of:

```text
Student ID → Student Name
```

Add five students.

---

# 4. Retrieve Values

Use:

```java
get(key)
```

Example:

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");

System.out.println(students.get(101));
```

### Output

```text
Mahesh
```

---

## Missing Key

```java
System.out.println(students.get(999));
```

Output:

```text
null
```

---

## Practice

Create:

```text
101 → Java
102 → Spring
103 → SQL
```

Retrieve the value for:

```text
102
```

Expected:

```text
Spring
```

---

# 5. Update Values

Calling `put()` with an existing key replaces its value.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");

students.put(101, "Mahesh Lute");

System.out.println(students.get(101));
```

### Output

```text
Mahesh Lute
```

---

## Practice

Create:

```text
101 → Java
102 → Python
103 → C++
```

Update:

```text
102 → Spring Boot
```

Expected:

```text
101 → Java
102 → Spring Boot
103 → C++
```

---

# 6. Duplicate Keys

A map cannot contain duplicate keys.

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "A");
map.put(1, "B");

System.out.println(map);
```

The final mapping for key `1` is:

```text
1 → B
```

The second value replaced the first.

---

## Practice

Predict:

```java
map.put(10, "Java");
map.put(20, "Spring");
map.put(10, "Python");
```

What is the value associated with key `10`?

### Answer

```text
Python
```

---

# 7. Duplicate Values

Duplicate values are allowed.

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");
map.put(102, "Java");
map.put(103, "Spring");

System.out.println(map);
```

This is valid.

Conceptually:

```text
101 → Java
102 → Java
103 → Spring
```

---

# 8. remove()

Remove an entry using its key.

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");
map.put(102, "Spring");
map.put(103, "SQL");

map.remove(102);

System.out.println(map);
```

The mapping:

```text
102 → Spring
```

is removed.

---

## Conditional remove

```java
map.remove(103, "SQL");
```

This removes the mapping only if both the key and value match.

---

## Practice

Given:

```text
101 → Java
102 → Spring
103 → SQL
104 → Docker
```

Remove:

```text
103
```

Then remove:

```text
104 → Docker
```

using the key-value version of `remove()`.

---

# 9. containsKey()

Check whether a key exists.

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");
map.put(102, "Spring");

System.out.println(map.containsKey(101));
System.out.println(map.containsKey(999));
```

### Output

```text
true
false
```

---

## Practice

Create a student map and check whether these IDs exist:

```text
101
105
110
```

---

# 10. containsValue()

Check whether a value exists.

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");
map.put(102, "Spring");

System.out.println(map.containsValue("Java"));
System.out.println(map.containsValue("Python"));
```

### Output

```text
true
false
```

---

# 11. size() and isEmpty()

## size()

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");
map.put(102, "Spring");
map.put(103, "SQL");

System.out.println(map.size());
```

Output:

```text
3
```

---

## isEmpty()

```java
System.out.println(map.isEmpty());
```

Output:

```text
false
```

After:

```java
map.clear();
```

```java
System.out.println(map.isEmpty());
```

Output:

```text
true
```

---

# 12. clear()

Removes all mappings.

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");
map.put(102, "Spring");

map.clear();

System.out.println(map);
```

### Output

```text
{}
```

---

# 13. getOrDefault()

Useful when a key may not exist.

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Mahesh");

System.out.println(
    map.getOrDefault(101, "Unknown")
);

System.out.println(
    map.getOrDefault(999, "Unknown")
);
```

### Output

```text
Mahesh
Unknown
```

---

## Practice

Create:

```text
101 → Java
102 → Spring
```

Retrieve:

```text
101
999
```

Use `"Not Found"` as the default value.

---

# 14. putIfAbsent()

Adds a value only when the key is not already mapped.

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");

map.putIfAbsent(101, "Python");
map.putIfAbsent(102, "Spring");

System.out.println(map);
```

The result conceptually contains:

```text
101 → Java
102 → Spring
```

The existing value for `101` is not replaced.

---

## Practice

Try:

```java
map.put(1, "A");
map.putIfAbsent(1, "B");
map.putIfAbsent(2, "C");
```

Predict the final map.

---

# 15. replace()

Replace the value for an existing key.

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");

map.replace(101, "Spring");

System.out.println(map.get(101));
```

Output:

```text
Spring
```

---

## Conditional replace

```java
map.replace(101, "Spring", "Spring Boot");
```

This works only if the current value is `"Spring"`.

---

# 16. replaceAll()

Apply a transformation to every value.

```java
Map<String, Integer> marks = new HashMap<>();

marks.put("Java", 80);
marks.put("DBMS", 70);
marks.put("DSA", 90);

marks.replaceAll(
    (subject, mark) -> mark + 5
);

System.out.println(marks);
```

Conceptually:

```text
Java → 85
DBMS → 75
DSA → 95
```

---

## Practice

Create:

```text
Maths → 60
Java  → 75
DBMS  → 80
```

Add `10` marks to every value using `replaceAll()`.

---

# 17. keySet()

`keySet()` returns all keys.

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");
map.put(102, "Spring");
map.put(103, "SQL");

for (Integer key : map.keySet()) {
    System.out.println(key);
}
```

---

## Practice

Create a map:

```text
101 → Mahesh
102 → Rahul
103 → Priya
```

Print only the student IDs.

---

# 18. values()

`values()` returns all values.

```java
for (String value : map.values()) {
    System.out.println(value);
}
```

This is useful when you only need the values.

---

## Practice

Create:

```text
101 → Java
102 → Spring
103 → SQL
```

Print only:

```text
Java
Spring
SQL
```

---

# 19. entrySet()

`entrySet()` returns the mappings as `Map.Entry` objects.

```java
for (Map.Entry<Integer, String> entry :
        map.entrySet()) {

    System.out.println(entry);
}
```

Conceptually:

```text
101=Java
102=Spring
103=SQL
```

---

# 20. Map.Entry

`Map.Entry<K, V>` represents one key-value pair.

Example:

```java
for (Map.Entry<Integer, String> entry :
        map.entrySet()) {

    System.out.println(
        "Key: " + entry.getKey()
    );

    System.out.println(
        "Value: " + entry.getValue()
    );
}
```

---

## Practice

For every entry, print:

```text
Student ID: 101
Student Name: Mahesh
```

---

# 21. Iterating Over a Map

## Method 1 — keySet()

```java
for (Integer key : map.keySet()) {
    System.out.println(
        key + " → " + map.get(key)
    );
}
```

---

## Method 2 — entrySet()

```java
for (Map.Entry<Integer, String> entry :
        map.entrySet()) {

    System.out.println(
        entry.getKey() + " → " + entry.getValue()
    );
}
```

When you need both key and value, `entrySet()` is generally the cleaner choice.

---

## Method 3 — forEach()

```java
map.forEach(
    (key, value) ->
        System.out.println(key + " → " + value)
);
```

---

# 22. forEach()

Example:

```java
Map<String, Integer> marks = new HashMap<>();

marks.put("Java", 90);
marks.put("DBMS", 85);
marks.put("DSA", 95);

marks.forEach(
    (subject, mark) ->
        System.out.println(
            subject + " → " + mark
        )
);
```

---

## Practice

Print each student's ID and name using `forEach()`.

---

# 23. Null Practice

Different `Map` implementations have different rules.

For example, `HashMap` allows:

```java
Map<Integer, String> map = new HashMap<>();

map.put(null, "Unknown");
map.put(101, null);
map.put(102, null);
```

This is valid for `HashMap`.

---

## Practice

Create a `HashMap` containing:

```text
null → Unknown
101 → Java
102 → null
```

Then:

1. Check `containsKey(null)`.
2. Check `containsValue(null)`.
3. Retrieve the value for `null`.

---

# 24. Frequency Counting

One of the most important uses of `Map` in DSA is frequency counting.

Suppose:

```text
[1, 2, 2, 3, 1, 1, 4]
```

Expected:

```text
1 → 3
2 → 2
3 → 1
4 → 1
```

---

## Using getOrDefault()

```java
int[] numbers = {1, 2, 2, 3, 1, 1, 4};

Map<Integer, Integer> frequency =
        new HashMap<>();

for (int number : numbers) {

    frequency.put(
        number,
        frequency.getOrDefault(number, 0) + 1
    );
}

System.out.println(frequency);
```

---

## Practice

Find frequencies of:

```text
[10, 20, 10, 30, 20, 10, 40, 30]
```

Expected:

```text
10 → 3
20 → 2
30 → 2
40 → 1
```

---

# 25. Character Frequency

Given:

```text
"programming"
```

Count every character.

Expected conceptually:

```text
p → 1
r → 2
o → 1
g → 2
a → 1
m → 2
i → 1
n → 1
```

---

## Practice

Implement:

```java
Map<Character, Integer>
```

to count character frequencies.

---

## Hint

```java
for (char ch : str.toCharArray()) {

    frequency.put(
        ch,
        frequency.getOrDefault(ch, 0) + 1
    );
}
```

---

# 26. Word Frequency

Given:

```text
"java spring java spring boot java"
```

Count each word.

Expected:

```text
java   → 3
spring → 2
boot   → 1
```

---

## Practice

Write a program that:

1. Splits a sentence into words.
2. Counts each word.
3. Stores the result in a `Map<String, Integer>`.
4. Prints the frequency.

---

# 27. Find Maximum Frequency

Given:

```text
[1, 2, 2, 3, 1, 2, 4, 2]
```

Frequency:

```text
1 → 2
2 → 4
3 → 1
4 → 1
```

The most frequent element is:

```text
2
```

---

## Practice

Write a program to find the element with the highest frequency.

### Hint

1. Build the frequency map.
2. Iterate over `entrySet()`.
3. Track the maximum frequency.

---

# 28. Find Duplicate Elements

Given:

```text
[10, 20, 10, 30, 20, 40, 50]
```

Duplicate values:

```text
10
20
```

---

## Practice

Use a `Map<Integer, Integer>` to find all elements whose frequency is greater than `1`.

---

# 29. First Non-Repeating Character

Given:

```text
"swiss"
```

Frequencies:

```text
s → 3
w → 1
i → 1
```

The first non-repeating character is:

```text
w
```

---

## Practice

Write a program to find the first character whose frequency is `1`.

### Recommended Approach

Use:

```text
LinkedHashMap
```

when preserving character insertion order is useful.

---

# 30. Grouping Data

A map can group multiple values under one key.

Example:

```text
Department → Employees
```

Conceptually:

```text
IT   → [Mahesh, Rahul]
HR   → [Priya, Sneha]
Sales → [Amit]
```

This can be represented as:

```java
Map<String, List<String>> employees;
```

---

## Practice

Create:

```text
Programming → [Java, Python, C++]
Database    → [MySQL, PostgreSQL]
DevOps      → [Docker, Kubernetes]
```

Use:

```java
Map<String, List<String>>
```

---

# 31. Map Conversion Practice

## List to Map

Given:

```java
List<String> names =
        Arrays.asList(
            "Mahesh",
            "Rahul",
            "Priya"
        );
```

Create a map:

```text
index → name
```

Expected:

```text
0 → Mahesh
1 → Rahul
2 → Priya
```

---

## Map to List of Keys

```java
List<Integer> keys =
        new ArrayList<>(map.keySet());
```

---

## Map to List of Values

```java
List<String> values =
        new ArrayList<>(map.values());
```

---

# 32. Nested Maps

A map can contain another map as its value.

Example:

```java
Map<Integer, Map<String, Object>> students =
        new HashMap<>();
```

Conceptually:

```text
101
 ├── name  → Mahesh
 ├── age   → 22
 └── marks → 85
```

---

## Practice

Create a nested structure representing:

```text
Student ID
    ↓
name
age
course
marks
```

---

# 33. Basic Coding Problems

## Problem 1 — Student Lookup

Create:

```text
101 → Mahesh
102 → Rahul
103 → Priya
```

Ask the user for a student ID and print the student's name.

---

## Problem 2 — Count Numbers

Given:

```text
[1, 2, 3, 2, 1, 4, 2]
```

Print the frequency of every number.

---

## Problem 3 — Check Duplicate

Given:

```text
[10, 20, 30, 10]
```

Determine whether the array contains duplicates.

---

## Problem 4 — Character Frequency

Input:

```text
"hello"
```

Expected:

```text
h → 1
e → 1
l → 2
o → 1
```

---

## Problem 5 — Word Frequency

Input:

```text
"java is simple java is powerful"
```

Count each word.

---

## Problem 6 — Find Maximum Frequency

Input:

```text
[5, 1, 5, 2, 5, 2, 3]
```

Expected:

```text
5
```

---

# 34. Intermediate Problems

## Problem 1 — Two Sum Using Map

Given:

```text
[2, 7, 11, 15]
```

and:

```text
target = 9
```

Find the two numbers whose sum equals the target.

Expected:

```text
2 + 7 = 9
```

### Hint

Use a map to store:

```text
value → index
```

---

## Problem 2 — First Non-Repeating Character

Input:

```text
"leetcode"
```

Find the first character that occurs only once.

Expected:

```text
l
```

---

## Problem 3 — Anagram Check

Determine whether:

```text
"listen"
```

and:

```text
"silent"
```

are anagrams.

### Hint

Use character frequencies.

---

## Problem 4 — Group Anagrams

Given:

```text
["eat", "tea", "tan", "ate", "nat", "bat"]
```

Group the anagrams.

Expected groups conceptually:

```text
[eat, tea, ate]
[tan, nat]
[bat]
```

---

## Problem 5 — Top Frequency

Given:

```text
[1, 1, 1, 2, 2, 3]
```

Find the most frequent number.

Expected:

```text
1
```

---

## Problem 6 — Unique Frequency

Determine whether every value in an array has a unique frequency.

Example:

```text
[1, 1, 2, 2, 2, 3]
```

Frequencies:

```text
1 → 2
2 → 3
3 → 1
```

All frequencies are unique.

---

# 35. Challenge Problems

## Challenge 1 — Longest Consecutive Sequence

Given:

```text
[100, 4, 200, 1, 3, 2]
```

Find the length of the longest consecutive sequence.

Expected:

```text
4
```

Sequence:

```text
1, 2, 3, 4
```

Try solving using a suitable map/set-based approach.

---

## Challenge 2 — Subarray Sum Equals K

Given:

```text
[1, 1, 1]
```

and:

```text
k = 2
```

Find the number of subarrays whose sum equals `2`.

Expected:

```text
2
```

### Important Idea

Use prefix sums with a frequency map.

---

## Challenge 3 — Longest Subarray with Sum K

Given:

```text
[10, 5, 2, 7, 1, 9]
```

and:

```text
k = 15
```

Find the longest subarray whose sum equals `15`.

---

## Challenge 4 — Count Pairs with Given Sum

Given:

```text
[1, 5, 7, -1, 5]
```

and:

```text
target = 6
```

Count the pairs whose sum is `6`.

---

## Challenge 5 — Isomorphic Strings

Determine whether two strings follow the same character mapping pattern.

Example:

```text
egg
add
```

Expected:

```text
true
```

---

## Challenge 6 — LRU Cache Concept

Study how a combination of:

```text
HashMap
+
LinkedHashMap
```

can be used to build cache-related structures.

Focus on understanding the data structure design rather than memorizing implementation code.

---

# 36. Scenario-Based Practice

## Scenario 1 — Student Database

Requirement:

```text
Student ID → Student Object
```

Questions:

- Which map structure is suitable for direct lookup?
- How would you retrieve a student?
- How would you check whether an ID exists?

Expected concepts:

```text
HashMap
get()
containsKey()
```

---

## Scenario 2 — Word Counter

Requirement:

> Count how many times each word appears in a document.

Use:

```java
Map<String, Integer>
```

Important method:

```java
getOrDefault()
```

---

## Scenario 3 — Character Counter

Requirement:

> Count the frequency of every character in a string.

Use:

```java
Map<Character, Integer>
```

---

## Scenario 4 — Preserve Insertion Order

Requirement:

> Store API responses by ID while preserving insertion order.

Consider:

```text
LinkedHashMap
```

---

## Scenario 5 — Sorted Keys

Requirement:

> Store employee IDs and always iterate through them in ascending order.

Consider:

```text
TreeMap
```

---

## Scenario 6 — Concurrent Access

Requirement:

> Multiple threads frequently read and update a shared map.

Consider:

```text
ConcurrentHashMap
```

depending on the exact concurrency requirements.

---

## Scenario 7 — Configuration

Requirement:

```text
property name → property value
```

Example:

```text
server.port → 8080
database.url → localhost
```

A map is a natural representation.

---

# 37. Implementation Comparison

## HashMap

Use when:

```text
Unique keys
+
No ordering requirement
+
Fast average basic operations
```

---

## LinkedHashMap

Use when:

```text
Unique keys
+
Predictable iteration order
```

---

## TreeMap

Use when:

```text
Unique keys
+
Sorted key order
+
Navigation/range operations
```

---

## Hashtable

Use mainly when working with legacy APIs that specifically require it.

---

## ConcurrentHashMap

Use when:

```text
Multiple threads
+
Concurrent map operations
```

are part of the design.

---

# 38. Practice Checklist

## Basic Map Operations

- [ ] Create a `Map`.
- [ ] Add mappings using `put()`.
- [ ] Retrieve values using `get()`.
- [ ] Update existing values.
- [ ] Remove mappings.
- [ ] Check keys.
- [ ] Check values.
- [ ] Check size.
- [ ] Check empty state.
- [ ] Clear the map.

---

## Modern Map Methods

- [ ] Practice `getOrDefault()`.
- [ ] Practice `putIfAbsent()`.
- [ ] Practice `replace()`.
- [ ] Practice conditional `replace()`.
- [ ] Practice `replaceAll()`.

---

## Views

- [ ] Practice `keySet()`.
- [ ] Practice `values()`.
- [ ] Practice `entrySet()`.
- [ ] Understand `Map.Entry`.

---

## Iteration

- [ ] Iterate using `keySet()`.
- [ ] Iterate using `entrySet()`.
- [ ] Use `forEach()`.
- [ ] Understand when `entrySet()` is preferable.

---

## DSA Practice

- [ ] Frequency counting.
- [ ] Character frequency.
- [ ] Word frequency.
- [ ] Duplicate detection.
- [ ] First non-repeating character.
- [ ] Two Sum.
- [ ] Anagram checking.
- [ ] Grouping anagrams.
- [ ] Prefix sum + frequency map.
- [ ] Pair counting.

---

## Implementation Selection

- [ ] Understand `HashMap`.
- [ ] Understand `LinkedHashMap`.
- [ ] Understand `TreeMap`.
- [ ] Understand `Hashtable`.
- [ ] Understand `ConcurrentHashMap`.

---

# 39. Final Goal

You should be comfortable writing code like:

```java
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, Integer> frequency =
                new HashMap<>();

        String[] words = {
            "java",
            "spring",
            "java",
            "docker",
            "spring",
            "java"
        };

        for (String word : words) {

            frequency.put(
                word,
                frequency.getOrDefault(word, 0) + 1
            );
        }

        frequency.forEach(
            (word, count) ->
                System.out.println(
                    word + " → " + count
                )
        );
    }
}
```

Conceptually, the result is:

```text
java   → 3
spring → 2
docker → 1
```

You should also understand the core mental model:

```text
Map
│
├── Key → Value
│
├── Keys are unique
│
├── Values can repeat
│
├── get(key)
├── put(key, value)
├── remove(key)
│
├── keySet()
├── values()
└── entrySet()
```

---

# 40. Progress

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
│   └── INTERVIEW.md   [ ]
│
├── 17-HashMap
├── 18-LinkedHashMap
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

**`16-Map/PRACTICE.md` is complete. Next: `16-Map/INTERVIEW.md`.**
