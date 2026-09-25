# LinkedHashMap — Practice

> Hands-on practice for `LinkedHashMap`, covering insertion order, access order, iteration, grouping, ordered frequency counting, and LRU cache design.

---

## Table of Contents

- [1. Basic LinkedHashMap](#1-basic-linkedhashmap)
- [2. Preserve Insertion Order](#2-preserve-insertion-order)
- [3. Update an Existing Key](#3-update-an-existing-key)
- [4. Remove a Mapping](#4-remove-a-mapping)
- [5. Iterate Using entrySet()](#5-iterate-using-entryset)
- [6. Compare HashMap and LinkedHashMap](#6-compare-hashmap-and-linkedhashmap)
- [7. Practice keySet(), values(), entrySet()](#7-practice-keyset-values-entryset)
- [8. Use getOrDefault()](#8-use-getordefault)
- [9. Use putIfAbsent()](#9-use-putifabsent)
- [10. Ordered Frequency Counting](#10-ordered-frequency-counting)
- [11. Character Frequency with First-Seen Order](#11-character-frequency-with-first-seen-order)
- [12. Word Frequency with First-Seen Order](#12-word-frequency-with-first-seen-order)
- [13. Remove Duplicates While Preserving Order](#13-remove-duplicates-while-preserving-order)
- [14. Ordered Student Records](#14-ordered-student-records)
- [15. Group Data While Preserving Order](#15-group-data-while-preserving-order)
- [16. Access Order Experiment](#16-access-order-experiment)
- [17. Observe get() in Access-Order Mode](#17-observe-get-in-access-order-mode)
- [18. Update in Access-Order Mode](#18-update-in-access-order-mode)
- [19. Build a Fixed-Size Cache](#19-build-a-fixed-size-cache)
- [20. Build an LRU Cache](#20-build-an-lru-cache)
- [21. Test LRU Cache Behavior](#21-test-lru-cache-behavior)
- [22. Recent Items Tracker](#22-recent-items-tracker)
- [23. Most Recently Used Item](#23-most-recently-used-item)
- [24. Custom removeEldestEntry()](#24-custom-removeeldestentry)
- [25. Ordered Login Tracker](#25-ordered-login-tracker)
- [26. Ordered API Response Data](#26-ordered-api-response-data)
- [27. Mini Project](#27-mini-project)
- [28. Challenge Problems](#28-challenge-problems)
- [29. Practice Checklist](#29-practice-checklist)
- [30. Progress](#30-progress)

---

# 1. Basic LinkedHashMap

### Task

Create a:

```java
LinkedHashMap<Integer, String>
```

and store:

```text
101 -> Mahesh
102 -> Rahul
103 -> Amit
```

### Starter Code

```java
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {

        LinkedHashMap<Integer, String> students =
                new LinkedHashMap<>();

        // Add entries
    }
}
```

### Goal

Practice:

- Creating `LinkedHashMap`
- `put()`
- Generics
- Basic iteration

---

# 2. Preserve Insertion Order

### Task

Insert:

```text
3 -> C
1 -> Java
2 -> Python
4 -> Go
```

Then iterate through the map.

### Expected Iteration

```text
3 -> C
1 -> Java
2 -> Python
4 -> Go
```

### Question

Why are the entries not automatically sorted as:

```text
1 -> Java
2 -> Python
3 -> C
4 -> Go
```

---

# 3. Update an Existing Key

Given:

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>();

map.put(1, "Java");
map.put(2, "Python");
map.put(3, "C++");
```

### Task

Update:

```text
2 -> Python
```

to:

```text
2 -> Spring Boot
```

Then iterate.

### Observe

Does key `2` move to the end?

Test and explain the result for the default insertion-order mode.

---

# 4. Remove a Mapping

Given:

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>();

map.put(101, "Mahesh");
map.put(102, "Rahul");
map.put(103, "Amit");
```

### Task

1. Remove key `102`.
2. Print the removed value.
3. Print the remaining map.

### Bonus

Try removing a key that does not exist.

---

# 5. Iterate Using entrySet()

Given:

```java
LinkedHashMap<Integer, String> students =
        new LinkedHashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Amit");
```

Print:

```text
101 -> Mahesh
102 -> Rahul
103 -> Amit
```

Use:

```java
for (Map.Entry<Integer, String> entry :
        students.entrySet()) {

    // print key and value
}
```

### Goal

Become comfortable with:

```java
Map.Entry<K, V>
```

---

# 6. Compare HashMap and LinkedHashMap

Create both:

```java
HashMap<Integer, String> hashMap =
        new HashMap<>();

LinkedHashMap<Integer, String> linkedHashMap =
        new LinkedHashMap<>();
```

Insert the same data into both:

```text
4 -> D
1 -> A
3 -> C
2 -> B
```

### Task

Iterate through both maps.

### Questions

1. Does `HashMap` guarantee the iteration order?
2. Does `LinkedHashMap` preserve insertion order?
3. Which one should you choose when predictable iteration order matters?

---

# 7. Practice keySet(), values(), entrySet()

Create:

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>();
```

Add five entries.

### Task

Print:

### Only Keys

```java
for (Integer key : map.keySet()) {
    // ...
}
```

### Only Values

```java
for (String value : map.values()) {
    // ...
}
```

### Keys + Values

```java
for (Map.Entry<Integer, String> entry :
        map.entrySet()) {
    // ...
}
```

### Goal

Understand which view to use for each requirement.

---

# 8. Use getOrDefault()

Create:

```java
LinkedHashMap<String, Integer> marks =
        new LinkedHashMap<>();

marks.put("DSA", 95);
marks.put("DBMS", 90);
marks.put("Java", 98);
```

### Task

Print:

```text
DSA -> 95
Spring -> 0
```

using:

```java
getOrDefault()
```

---

# 9. Use putIfAbsent()

Given:

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>();

map.put(1, "Java");
```

Execute:

```java
map.putIfAbsent(1, "Python");
map.putIfAbsent(2, "C++");
```

### Task

Print the final map.

### Question

Why does:

```text
1 -> Java
```

remain unchanged?

---

# 10. Ordered Frequency Counting

### Problem

Given:

```text
Java Java Python C++ Java Python
```

Count the frequency of each word while preserving the order in which each distinct word first appeared.

### Expected

```text
Java   -> 3
Python -> 2
C++    -> 1
```

### Requirement

Use:

```java
LinkedHashMap<String, Integer>
```

### Hint

```java
map.put(
    word,
    map.getOrDefault(word, 0) + 1
);
```

---

# 11. Character Frequency with First-Seen Order

### Problem

Given:

```text
"programming"
```

Count every character.

The output should preserve the order in which distinct characters first appeared.

For example, conceptually:

```text
p -> 1
r -> 2
o -> 1
g -> 2
a -> 1
m -> 2
i -> 1
n -> 1
```

### Requirement

Use:

```java
LinkedHashMap<Character, Integer>
```

### Bonus

Solve the same problem using:

```java
HashMap
```

and explain what changes.

---

# 12. Word Frequency with First-Seen Order

### Problem

Given:

```text
"java spring java spring boot java"
```

Build:

```java
LinkedHashMap<String, Integer>
```

### Expected

```text
java   -> 3
spring -> 2
boot   -> 1
```

### Important

The distinct words should appear in first-seen order:

```text
java
spring
boot
```

---

# 13. Remove Duplicates While Preserving Order

### Problem

Given:

```java
int[] arr = {
    4, 2, 4, 1, 2, 5, 1, 3
};
```

Produce:

```text
4 2 1 5 3
```

### Requirement

Use a collection that preserves insertion order.

Try:

```java
LinkedHashSet<Integer>
```

### Challenge

Can you solve the same idea using:

```java
LinkedHashMap<Integer, Boolean>
```

---

# 14. Ordered Student Records

Create:

```java
class Student {

    int rollNo;
    String name;

    Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    @Override
    public String toString() {
        return rollNo + " - " + name;
    }
}
```

Create:

```java
LinkedHashMap<Integer, Student> students =
        new LinkedHashMap<>();
```

### Add

```text
101 -> Mahesh
102 -> Rahul
103 -> Amit
104 -> Sneha
```

### Task

Display students in insertion order.

### Bonus

Update one student's details and observe whether its position changes.

---

# 15. Group Data While Preserving Order

Create:

```java
LinkedHashMap<String, List<String>> groups =
        new LinkedHashMap<>();
```

Group technologies:

```text
Backend  -> Java, Spring Boot
Frontend -> HTML, CSS
Database -> PostgreSQL, MySQL
```

### Requirement

Use:

```java
computeIfAbsent()
```

Example pattern:

```java
groups.computeIfAbsent(
    "Backend",
    key -> new ArrayList<>()
).add("Java");
```

### Goal

Preserve the order in which the groups were first created.

---

# 16. Access Order Experiment

Create:

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>(16, 0.75f, true);
```

Add:

```text
1 -> A
2 -> B
3 -> C
```

Print the map.

Expected initial order:

```text
1 -> A
2 -> B
3 -> C
```

Now execute:

```java
map.get(1);
```

Print again.

### Observe

The accessed entry should move toward the end in access-order mode.

Expected:

```text
2 -> B
3 -> C
1 -> A
```

---

# 17. Observe get() in Access-Order Mode

Create:

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>(16, 0.75f, true);
```

Insert:

```text
1 -> A
2 -> B
3 -> C
4 -> D
```

Perform:

```java
map.get(2);
map.get(4);
map.get(2);
```

### Task

Predict the final order **before running the program**.

Then run it and verify your answer.

### Goal

Build intuition for access-order mode.

---

# 18. Update in Access-Order Mode

Create:

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>(16, 0.75f, true);
```

Insert:

```text
1 -> A
2 -> B
3 -> C
```

Then execute:

```java
map.put(2, "Updated");
```

### Task

Print the map.

### Goal

Observe the ordering effect of accessing/replacing an existing entry in access-order mode.

---

# 19. Build a Fixed-Size Cache

Create:

```java
LinkedHashMap<Integer, String> cache =
        new LinkedHashMap<>();
```

### Requirement

Maintain a maximum of `3` entries.

When inserting the fourth entry, remove the oldest one.

### Hint

Override:

```java
removeEldestEntry()
```

Example structure:

```java
LinkedHashMap<Integer, String> cache =
        new LinkedHashMap<>(16, 0.75f, false) {

            @Override
            protected boolean removeEldestEntry(
                    Map.Entry<Integer, String> eldest) {

                return size() > 3;
            }
        };
```

### Test

```text
put(1, A)
put(2, B)
put(3, C)
put(4, D)
```

Expected remaining keys:

```text
2
3
4
```

---

# 20. Build an LRU Cache

### Problem

Implement a fixed-capacity LRU cache using:

```java
LinkedHashMap
```

### Requirements

- `get(key)`
- `put(key, value)`
- Fixed capacity
- Recently accessed entries become most recently used
- Least recently used entry is removed automatically

### Skeleton

```java
class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public LRUCache(int capacity) {

        super(16, 0.75f, true);

        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(
            Map.Entry<K, V> eldest) {

        return size() > capacity;
    }
}
```

### Goal

Understand how:

```text
Hash-based lookup
+
Access order
+
Automatic eviction
```

can create a simple LRU cache.

---

# 21. Test LRU Cache Behavior

Use:

```java
LRUCache<Integer, String> cache =
        new LRUCache<>(3);
```

Execute:

```java
cache.put(1, "A");
cache.put(2, "B");
cache.put(3, "C");
```

Current order:

```text
1 -> A
2 -> B
3 -> C
```

Now:

```java
cache.get(1);
```

Order should become:

```text
2 -> B
3 -> C
1 -> A
```

Now:

```java
cache.put(4, "D");
```

The least recently used entry should be removed.

### Expected Remaining Entries

```text
3 -> C
1 -> A
4 -> D
```

---

# 22. Recent Items Tracker

### Problem

Create a small "recently viewed items" tracker.

Use:

```java
LinkedHashMap<Integer, String>
```

with access-order enabled.

Example:

```text
101 -> Laptop
102 -> Phone
103 -> Headphones
104 -> Keyboard
```

When a user views an item:

```java
map.get(id);
```

the item should move toward the most-recently-used end.

### Task

Implement:

```text
viewItem(id)
displayRecentItems()
```

---

# 23. Most Recently Used Item

Using an access-order `LinkedHashMap`, track the most recently accessed item.

### Example

Start:

```text
1 -> A
2 -> B
3 -> C
```

Access:

```text
2
1
```

Final order:

```text
3 -> C
2 -> B
1 -> A
```

### Task

Find and print:

```text
Most Recently Used -> 1
Least Recently Used  -> 3
```

### Hint

Use iteration order.

---

# 24. Custom removeEldestEntry()

Create a LinkedHashMap where the maximum size is:

```text
5
```

Override:

```java
removeEldestEntry()
```

to automatically remove the oldest entry whenever the size exceeds `5`.

### Test

Insert:

```text
1
2
3
4
5
6
7
```

### Expected Remaining Keys

```text
3
4
5
6
7
```

### Challenge

Change the maximum size to a constructor parameter.

---

# 25. Ordered Login Tracker

Create:

```java
LinkedHashMap<String, String> users =
        new LinkedHashMap<>();
```

Store:

```text
mahesh -> 09:10
rahul  -> 09:12
amit   -> 09:15
sneha  -> 09:20
```

### Task

Display users in the order they logged in.

### Bonus

Use access-order mode and simulate users returning to the application.

---

# 26. Ordered API Response Data

Suppose an application processes fields in a preferred order:

```text
id
name
email
phone
address
```

### Task

Store these fields using:

```java
LinkedHashMap<String, Object>
```

and display them in insertion order.

### Goal

Understand where predictable field traversal can be useful.

---

# 27. Mini Project

# Recent Activity Manager

Build a console-based recent activity manager using:

```java
LinkedHashMap<Integer, String>
```

with access-order enabled.

---

## Features

```text
1. Add Activity
2. Access Activity
3. Remove Activity
4. Show All Activities
5. Show Least Recently Used
6. Show Most Recently Used
7. Set Maximum Capacity
8. Exit
```

---

## Example

User activities:

```text
101 -> Opened GitHub
102 -> Solved Two Sum
103 -> Read Spring Boot Notes
```

User then accesses:

```text
101
```

Order becomes:

```text
102 -> Solved Two Sum
103 -> Read Spring Boot Notes
101 -> Opened GitHub
```

---

## Suggested Class

```java
class ActivityManager
        extends LinkedHashMap<Integer, String> {

    private final int capacity;

    ActivityManager(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(
            Map.Entry<Integer, String> eldest) {

        return size() > capacity;
    }
}
```

---

# 28. Challenge Problems

## Challenge 1 — First Unique Character Order

Given:

```text
"swiss"
```

Use `LinkedHashMap` to count frequencies while preserving character insertion order.

Then identify the first character whose frequency is `1`.

Expected:

```text
w
```

---

## Challenge 2 — Ordered Duplicate Removal

Given:

```java
int[] arr = {
    5, 1, 5, 2, 3, 1, 4, 2
};
```

Produce:

```text
5 1 2 3 4
```

using an order-preserving collection.

---

## Challenge 3 — Recent Search History

Build a search-history component:

```text
Java
Spring Boot
PostgreSQL
Java
DSA
```

Use access-order `LinkedHashMap`.

### Requirement

When `"Java"` is searched again, it should become the most recently used item.

---

## Challenge 4 — Cache Eviction

Build a cache with capacity `3`.

Perform:

```text
put(A)
put(B)
put(C)
get(A)
put(D)
get(C)
put(E)
```

### Task

Predict which entries remain before running the code.

Then verify your result.

---

## Challenge 5 — Ordered Grouping

Given:

```text
Backend: Java
Frontend: HTML
Backend: Spring Boot
Database: PostgreSQL
Frontend: CSS
```

Build:

```java
LinkedHashMap<String, List<String>>
```

Expected structure:

```text
Backend  -> [Java, Spring Boot]
Frontend -> [HTML, CSS]
Database -> [PostgreSQL]
```

The groups must remain in first-seen order:

```text
Backend
Frontend
Database
```

---

# 29. Practice Checklist

## Basic

- [ ] Create `LinkedHashMap`
- [ ] Add entries
- [ ] Retrieve values
- [ ] Update values
- [ ] Remove entries
- [ ] Check keys
- [ ] Check values
- [ ] Find size
- [ ] Clear the map

---

## Ordering

- [ ] Understand insertion order
- [ ] Understand access order
- [ ] Compare insertion vs access order
- [ ] Predict iteration order
- [ ] Test `get()` in access-order mode
- [ ] Test updates in access-order mode

---

## Iteration

- [ ] `keySet()`
- [ ] `values()`
- [ ] `entrySet()`
- [ ] `Map.Entry`
- [ ] `forEach()`

---

## Practical Problems

- [ ] Ordered frequency counting
- [ ] Character frequency
- [ ] Word frequency
- [ ] Remove duplicates while preserving order
- [ ] Ordered grouping
- [ ] Recent item tracking
- [ ] Login tracking
- [ ] Search history

---

## Advanced

- [ ] `removeEldestEntry()`
- [ ] Fixed-size cache
- [ ] LRU cache
- [ ] Least recently used item
- [ ] Most recently used item
- [ ] Access-order behavior
- [ ] Custom cache implementation

---

# 30. Progress

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
│   └── INTERVIEW.md   [ ]
│
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

> **`18-LinkedHashMap/PRACTICE.md` completed.**
>
> **Next: `18-LinkedHashMap/INTERVIEW.md`**.
