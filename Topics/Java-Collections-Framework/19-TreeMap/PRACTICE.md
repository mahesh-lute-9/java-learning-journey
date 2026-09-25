# TreeMap — Practice

> Hands-on practice for `TreeMap`, covering sorted maps, navigation methods, range queries, custom comparators, and practical DSA-style problems.

---

## Table of Contents

- [1. Basic TreeMap](#1-basic-treemap)
- [2. Observe Sorted Key Order](#2-observe-sorted-key-order)
- [3. Update Existing Keys](#3-update-existing-keys)
- [4. Remove a Mapping](#4-remove-a-mapping)
- [5. First and Last Keys](#5-first-and-last-keys)
- [6. First and Last Entries](#6-first-and-last-entries)
- [7. lowerKey()](#7-lowerkey)
- [8. floorKey()](#8-floorkey)
- [9. ceilingKey()](#9-ceilingkey)
- [10. higherKey()](#10-higherkey)
- [11. Navigation Methods Together](#11-navigation-methods-together)
- [12. Entry Navigation](#12-entry-navigation)
- [13. pollFirstEntry() and pollLastEntry()](#13-pollfirstentry-and-polllastentry)
- [14. subMap()](#14-submap)
- [15. headMap()](#15-headmap)
- [16. tailMap()](#16-tailmap)
- [17. descendingMap()](#17-descendingmap)
- [18. descendingKeySet()](#18-descendingkeyset)
- [19. Reverse Order Using Comparator](#19-reverse-order-using-comparator)
- [20. Sort Strings by Custom Rule](#20-sort-strings-by-custom-rule)
- [21. TreeMap with Custom Objects](#21-treemap-with-custom-objects)
- [22. Comparable with TreeMap](#22-comparable-with-treemap)
- [23. TreeMap with Comparator](#23-treemap-with-comparator)
- [24. Find Nearest Lower and Higher Values](#24-find-nearest-lower-and-higher-values)
- [25. Find Floor and Ceiling](#25-find-floor-and-ceiling)
- [26. Range Query](#26-range-query)
- [27. Count Values in a Range](#27-count-values-in-a-range)
- [28. Process Events by Timestamp](#28-process-events-by-timestamp)
- [29. Find the Closest Timestamp](#29-find-the-closest-timestamp)
- [30. Student Marks in Sorted Order](#30-student-marks-in-sorted-order)
- [31. Word Dictionary](#31-word-dictionary)
- [32. Ordered Frequency Map](#32-ordered-frequency-map)
- [33. Compare HashMap, LinkedHashMap, TreeMap](#33-compare-hashmap-linkedhashmap-treemap)
- [34. Null Key Experiment](#34-null-key-experiment)
- [35. Comparator Returning Zero](#35-comparator-returning-zero)
- [36. Mini Project](#36-mini-project)
- [37. Challenge Problems](#37-challenge-problems)
- [38. Practice Checklist](#38-practice-checklist)
- [39. Progress](#39-progress)

---

# 1. Basic TreeMap

### Task

Create:

```java
TreeMap<Integer, String> students =
        new TreeMap<>();
```

Store:

```text
103 -> Amit
101 -> Mahesh
102 -> Rahul
104 -> Sneha
```

### Goal

Print the map and observe the key ordering.

Expected order:

```text
101 -> Mahesh
102 -> Rahul
103 -> Amit
104 -> Sneha
```

---

# 2. Observe Sorted Key Order

Insert the following in this order:

```text
50
10
40
20
30
```

Use:

```java
TreeMap<Integer, String> map =
        new TreeMap<>();
```

### Task

Print:

```java
for (Integer key : map.keySet()) {
    System.out.println(key);
}
```

### Expected

```text
10
20
30
40
50
```

### Question

Why does the output differ from insertion order?

---

# 3. Update Existing Keys

Given:

```java
TreeMap<Integer, String> map =
        new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");
```

Now execute:

```java
map.put(20, "Updated");
```

### Task

Print the map.

### Observe

Does the key `20` move?

### Goal

Understand the difference between:

```text
Updating a value
```

and:

```text
Changing key order
```

---

# 4. Remove a Mapping

Given:

```java
TreeMap<Integer, String> map =
        new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");
```

### Task

1. Remove key `20`.
2. Store the returned value.
3. Print the removed value.
4. Print the remaining map.

### Bonus

Try:

```java
map.remove(999);
```

and observe the return value.

---

# 5. First and Last Keys

Create:

```java
TreeMap<Integer, String> map =
        new TreeMap<>();
```

Add:

```text
40 -> D
10 -> A
30 -> C
20 -> B
```

### Task

Print:

```java
map.firstKey();
map.lastKey();
```

### Expected

```text
First -> 10
Last  -> 40
```

---

# 6. First and Last Entries

Practice:

```java
map.firstEntry();
map.lastEntry();
```

### Task

Print:

```text
First Entry -> 10 -> A
Last Entry  -> 40 -> D
```

Use:

```java
Map.Entry<Integer, String> first =
        map.firstEntry();
```

and:

```java
Map.Entry<Integer, String> last =
        map.lastEntry();
```

---

# 7. lowerKey()

Given:

```java
TreeMap<Integer, String> map =
        new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");
map.put(40, "D");
```

Test:

```java
map.lowerKey(30);
```

### Expected

```text
20
```

Now test:

```java
map.lowerKey(25);
```

Expected:

```text
20
```

### Rule

```text
Greatest key < given key
```

---

# 8. floorKey()

Using the same map:

```java
map.floorKey(30);
```

Expected:

```text
30
```

Now:

```java
map.floorKey(25);
```

Expected:

```text
20
```

### Rule

```text
Greatest key <= given key
```

---

# 9. ceilingKey()

Using:

```text
10, 20, 30, 40
```

test:

```java
map.ceilingKey(30);
```

Expected:

```text
30
```

Then:

```java
map.ceilingKey(25);
```

Expected:

```text
30
```

### Rule

```text
Smallest key >= given key
```

---

# 10. higherKey()

Test:

```java
map.higherKey(30);
```

Expected:

```text
40
```

Then:

```java
map.higherKey(35);
```

Expected:

```text
40
```

### Rule

```text
Smallest key > given key
```

---

# 11. Navigation Methods Together

Given:

```text
10, 20, 30, 40, 50
```

For:

```text
x = 30
```

predict:

```java
map.lowerKey(x);
map.floorKey(x);
map.ceilingKey(x);
map.higherKey(x);
```

### Expected

```text
lower   -> 20
floor   -> 30
ceiling -> 30
higher  -> 40
```

Now repeat for:

```text
x = 35
```

### Expected

```text
lower   -> 30
floor   -> 30
ceiling -> 40
higher  -> 40
```

---

# 12. Entry Navigation

Practice the entry-based versions:

```java
map.lowerEntry(30);
map.floorEntry(30);
map.ceilingEntry(30);
map.higherEntry(30);
```

### Task

Print:

```text
lowerEntry
floorEntry
ceilingEntry
higherEntry
```

in the format:

```text
20 -> B
30 -> C
30 -> C
40 -> D
```

---

# 13. pollFirstEntry() and pollLastEntry()

Given:

```text
10 -> A
20 -> B
30 -> C
40 -> D
```

### Task

Execute:

```java
Map.Entry<Integer, String> first =
        map.pollFirstEntry();
```

Then:

```java
Map.Entry<Integer, String> last =
        map.pollLastEntry();
```

### Observe

What entries are removed?

### Expected Remaining

```text
20 -> B
30 -> C
```

---

# 14. subMap()

Create:

```java
TreeMap<Integer, String> map =
        new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");
map.put(40, "D");
map.put(50, "E");
```

Run:

```java
map.subMap(20, 50);
```

### Expected

```text
20 -> B
30 -> C
40 -> D
```

### Rule

Default range:

```text
fromKey <= key < toKey
```

---

# 15. headMap()

Using the same map:

```java
map.headMap(40);
```

### Expected

```text
10 -> A
20 -> B
30 -> C
```

Now try:

```java
map.headMap(40, true);
```

### Expected

```text
10 -> A
20 -> B
30 -> C
40 -> D
```

---

# 16. tailMap()

Test:

```java
map.tailMap(30);
```

### Expected

```text
30 -> C
40 -> D
50 -> E
```

Now:

```java
map.tailMap(30, false);
```

### Expected

```text
40 -> D
50 -> E
```

---

# 17. descendingMap()

Given:

```text
10, 20, 30, 40, 50
```

run:

```java
NavigableMap<Integer, String> descending =
        map.descendingMap();
```

### Task

Print the result.

### Expected

```text
50 -> E
40 -> D
30 -> C
20 -> B
10 -> A
```

### Important

`descendingMap()` returns a view.

---

# 18. descendingKeySet()

Practice:

```java
NavigableSet<Integer> keys =
        map.descendingKeySet();
```

### Expected

```text
50
40
30
20
10
```

### Goal

Understand reverse navigation without creating a manually sorted list.

---

# 19. Reverse Order Using Comparator

Create:

```java
TreeMap<Integer, String> map =
        new TreeMap<>(Comparator.reverseOrder());
```

Insert:

```text
10
20
30
40
50
```

### Expected Iteration

```text
50
40
30
20
10
```

### Goal

Practice custom ordering at map construction time.

---

# 20. Sort Strings by Custom Rule

Create:

```java
TreeMap<String, Integer> map =
        new TreeMap<>(
            Comparator.comparingInt(String::length)
        );
```

Insert:

```text
Java
Spring
Python
Go
```

### Task

Observe the ordering.

### Important Question

What happens when two different strings have the same length?

Try:

```text
Java
Code
```

Both have length `4`.

What happens when their comparator returns `0`?

---

# 21. TreeMap with Custom Objects

Create:

```java
class Student {

    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
```

Create:

```java
TreeMap<Integer, Student> students =
        new TreeMap<>();
```

### Add

```text
103 -> Amit
101 -> Mahesh
102 -> Rahul
```

### Expected

```text
101 - Mahesh
102 - Rahul
103 - Amit
```

---

# 22. Comparable with TreeMap

Create:

```java
class Student implements Comparable<Student> {

    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
```

Create:

```java
TreeMap<Student, String> map =
        new TreeMap<>();
```

### Task

Add students in random order.

### Goal

Observe natural ordering through `Comparable`.

---

# 23. TreeMap with Comparator

Use:

```java
TreeMap<Student, String> map =
        new TreeMap<>(
            Comparator.comparingInt(
                student -> student.id
            )
        );
```

### Task

Compare this with the `Comparable` implementation.

### Question

What is the major difference between:

```text
Student implements Comparable
```

and:

```text
TreeMap receives Comparator
```

---

# 24. Find Nearest Lower and Higher Values

### Problem

Given sorted keys:

```text
10, 20, 30, 40, 50
```

For each query:

```text
25
34
50
55
```

find:

```text
Nearest lower
Nearest higher
```

### Use

```java
map.floorKey(x);
map.ceilingKey(x);
```

### Example

For:

```text
x = 34
```

Expected:

```text
Lower  -> 30
Higher -> 40
```

---

# 25. Find Floor and Ceiling

### Problem

Create:

```java
TreeMap<Integer, String> prices =
        new TreeMap<>();
```

Store:

```text
100 -> Product A
200 -> Product B
300 -> Product C
500 -> Product D
```

For a budget:

```text
350
```

find:

```text
Largest price <= 350
Smallest price >= 350
```

### Expected

```text
Floor   -> 300
Ceiling -> 500
```

### Real-World Connection

This pattern is useful when matching a request to the nearest available value.

---

# 26. Range Query

### Problem

Store employee salaries:

```text
30000
40000
50000
60000
70000
80000
90000
```

Use:

```java
TreeMap<Integer, String>
```

### Task

Find all salaries in the range:

```text
40000 to 70000
```

Use:

```java
subMap()
```

### Expected Keys

```text
40000
50000
60000
70000
```

---

# 27. Count Values in a Range

Using the salary map:

### Task

Count how many salary entries lie in:

```text
45000 <= salary <= 80000
```

### Hint

Use an inclusive `subMap()`:

```java
map.subMap(
    45000, true,
    80000, true
);
```

Then use:

```java
size()
```

---

# 28. Process Events by Timestamp

Create:

```java
TreeMap<Long, String> events =
        new TreeMap<>();
```

Store:

```text
1000 -> Login
1050 -> View Dashboard
1100 -> Open Profile
1200 -> Logout
```

### Task

Display events in chronological order.

### Bonus

Find the event immediately before timestamp `1080`.

Use:

```java
floorEntry(1080L);
```

### Also Find

The first event at or after `1080`:

```java
ceilingEntry(1080L);
```

---

# 29. Find the Closest Timestamp

Given:

```text
1000
1100
1200
1300
```

Query:

```text
1140
```

### Task

Find:

```text
floor = 1100
ceiling = 1200
```

Then calculate which timestamp is closer.

### Expected

```text
1100
```

### Goal

Combine TreeMap navigation with simple distance calculation.

---

# 30. Student Marks in Sorted Order

Create:

```java
TreeMap<Integer, Double> marks =
        new TreeMap<>();
```

Use roll number as key.

Example:

```text
103 -> 78.5
101 -> 91.0
104 -> 85.5
102 -> 88.0
```

### Expected Iteration

```text
101 -> 91.0
102 -> 88.0
103 -> 78.5
104 -> 85.5
```

### Bonus

Use:

```java
firstKey()
lastKey()
```

to find the lowest and highest roll number.

---

# 31. Word Dictionary

Create:

```java
TreeMap<String, String> dictionary =
        new TreeMap<>();
```

Store:

```text
"java"   -> "Programming language"
"spring" -> "Java framework"
"sql"    -> "Query language"
"git"    -> "Version control"
```

### Task

1. Display all words in alphabetical order.
2. Search for a specific word.
3. Find the word immediately before a target.
4. Find the word immediately after a target.

Use:

```java
lowerKey()
higherKey()
```

---

# 32. Ordered Frequency Map

Given:

```text
5 2 5 3 2 1 5 3
```

Create:

```java
TreeMap<Integer, Integer> frequency =
        new TreeMap<>();
```

### Expected

```text
1 -> 1
2 -> 2
3 -> 2
5 -> 3
```

### Important

The frequencies are not sorted.

The **keys** are sorted.

---

# 33. Compare HashMap, LinkedHashMap, TreeMap

Create three maps:

```java
HashMap<Integer, String> hashMap =
        new HashMap<>();

LinkedHashMap<Integer, String> linkedHashMap =
        new LinkedHashMap<>();

TreeMap<Integer, String> treeMap =
        new TreeMap<>();
```

Insert:

```text
30 -> C
10 -> A
20 -> B
```

### Task

Print all three.

### Compare

| Map | Ordering |
|---|---|
| `HashMap` | No guaranteed order |
| `LinkedHashMap` | Insertion order |
| `TreeMap` | Sorted key order |

### Goal

Understand when each implementation should be selected.

---

# 34. Null Key Experiment

Create:

```java
TreeMap<Integer, String> map =
        new TreeMap<>();
```

Try:

```java
map.put(null, "Value");
```

### Task

Observe the result.

### Then Try

Create a custom comparator that explicitly handles `null`.

For example:

```java
Comparator<Integer> comparator =
        Comparator.nullsFirst(Integer::compareTo);
```

Then:

```java
TreeMap<Integer, String> map =
        new TreeMap<>(comparator);
```

### Goal

Understand why natural ordering and custom ordering can have different null behavior.

---

# 35. Comparator Returning Zero

Create:

```java
TreeMap<String, Integer> map =
        new TreeMap<>(
            Comparator.comparingInt(String::length)
        );
```

Insert:

```text
"Java" -> 1
"Code" -> 2
"Spring" -> 3
"Python" -> 4
```

### Task

Observe what happens to:

```text
Java
Code
Spring
Python
```

### Important

`Java` and `Code` both have length `4`.

`Spring` and `Python` both have length `6`.

Their comparator returns `0`, so TreeMap treats them as equivalent keys for map operations.

This may cause one mapping to replace another.

### Goal

Understand why comparator design is critical in sorted maps.

---

# 36. Mini Project

# Event Scheduler Using TreeMap

Build a console-based event scheduler using:

```java
TreeMap<Long, String>
```

where:

```text
Key   = timestamp
Value = event
```

---

## Features

```text
1. Add Event
2. Remove Event
3. Find Event by Timestamp
4. Show All Events
5. Show Next Event
6. Show Previous Event
7. Show Events in a Range
8. Show Earliest Event
9. Show Latest Event
10. Exit
```

---

## Suggested Methods

```java
void addEvent(long timestamp, String event)

void removeEvent(long timestamp)

String findEvent(long timestamp)

Map.Entry<Long, String> nextEvent(long timestamp)

Map.Entry<Long, String> previousEvent(long timestamp)

NavigableMap<Long, String> eventsBetween(
        long start,
        long end
)
```

---

## Useful TreeMap Methods

```text
firstEntry()
lastEntry()
floorEntry()
ceilingEntry()
lowerEntry()
higherEntry()
subMap()
pollFirstEntry()
pollLastEntry()
```

---

# 37. Challenge Problems

## Challenge 1 — Closest Number

Given:

```java
TreeMap<Integer, Boolean> numbers =
        new TreeMap<>();
```

Store:

```text
10, 20, 40, 50, 70
```

For:

```text
target = 45
```

find the closest number.

### Hint

Check:

```java
floorKey(45);
ceilingKey(45);
```

Then compare distances.

---

## Challenge 2 — Next Available Slot

Given available time slots:

```text
9
10
12
14
16
```

A user requests:

```text
11
```

Find the earliest available slot at or after `11`.

Use:

```java
ceilingKey(11);
```

Expected:

```text
12
```

---

## Challenge 3 — Previous Available Slot

Using:

```text
9
10
12
14
16
```

request:

```text
13
```

Find the latest available slot at or before `13`.

Use:

```java
floorKey(13);
```

Expected:

```text
12
```

---

## Challenge 4 — Range Report

Store sales:

```text
100 -> 5000
200 -> 7000
300 -> 3000
400 -> 9000
500 -> 6000
```

Find all sales between:

```text
200 and 400
```

including both boundaries.

### Requirement

Use:

```java
subMap()
```

---

## Challenge 5 — Dynamic Leaderboard

Store:

```text
score -> player
```

using:

```java
TreeMap<Integer, String>
```

### Task

Support:

```text
Add score
Find highest score
Find lowest score
Find player immediately below a score
Find player immediately above a score
```

### Bonus

Allow multiple players with the same score by making the value a collection.

---

## Challenge 6 — Timestamp Lookup

Given:

```text
1000 -> Event A
1500 -> Event B
2000 -> Event C
2500 -> Event D
```

For query:

```text
1800
```

return:

```text
Previous -> Event B
Next     -> Event C
```

Use:

```java
floorEntry()
ceilingEntry()
```

---

# 38. Practice Checklist

## Basics

- [ ] Create a `TreeMap`
- [ ] Insert mappings
- [ ] Update values
- [ ] Remove mappings
- [ ] Retrieve values
- [ ] Check keys
- [ ] Check values
- [ ] Iterate through entries

---

## Ordering

- [ ] Understand natural ordering
- [ ] Understand custom `Comparator`
- [ ] Reverse the ordering
- [ ] Use custom object keys
- [ ] Understand `Comparable`
- [ ] Understand `Comparator`
- [ ] Understand comparator returning `0`

---

## Navigation

- [ ] `firstKey()`
- [ ] `lastKey()`
- [ ] `firstEntry()`
- [ ] `lastEntry()`
- [ ] `lowerKey()`
- [ ] `floorKey()`
- [ ] `ceilingKey()`
- [ ] `higherKey()`
- [ ] `lowerEntry()`
- [ ] `floorEntry()`
- [ ] `ceilingEntry()`
- [ ] `higherEntry()`

---

## Range Operations

- [ ] `subMap()`
- [ ] `headMap()`
- [ ] `tailMap()`
- [ ] `descendingMap()`
- [ ] `descendingKeySet()`
- [ ] Inclusive/exclusive boundaries

---

## Practical Problems

- [ ] Nearest lower/higher value
- [ ] Floor/ceiling search
- [ ] Range query
- [ ] Range counting
- [ ] Timestamp lookup
- [ ] Event scheduling
- [ ] Available-slot lookup
- [ ] Dictionary navigation
- [ ] Sorted frequency map

---

## Advanced

- [ ] Red-black tree concept
- [ ] O(log n) operations
- [ ] Null-key behavior
- [ ] Custom comparator
- [ ] Custom object ordering
- [ ] Comparator consistency with equality
- [ ] TreeMap vs HashMap
- [ ] TreeMap vs LinkedHashMap

---

# 39. Progress

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
│   └── INTERVIEW.md   [x]
│
├── 18-LinkedHashMap
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [x]
│
├── 19-TreeMap
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [ ]
│
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

> **`19-TreeMap/PRACTICE.md` completed.**
>
> **Next: `19-TreeMap/INTERVIEW.md`**.
