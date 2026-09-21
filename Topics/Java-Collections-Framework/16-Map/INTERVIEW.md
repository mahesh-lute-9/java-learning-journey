# Map — Interview Questions

> Interview-focused questions and answers covering Java `Map`, key-value pairs, important methods, implementations, iteration, internal concepts, and common DSA use cases.

---

## 📚 Table of Contents

1. [What is Map?](#1-what-is-map)
2. [Is Map an Interface or Class?](#2-is-map-an-interface-or-class)
3. [Does Map Extend Collection?](#3-does-map-extend-collection)
4. [What Does a Map Store?](#4-what-does-a-map-store)
5. [Can a Map Have Duplicate Keys?](#5-can-a-map-have-duplicate-keys)
6. [Can a Map Have Duplicate Values?](#6-can-a-map-have-duplicate-values)
7. [What Happens When put() Uses an Existing Key?](#7-what-happens-when-put-uses-an-existing-key)
8. [What Does get() Return for a Missing Key?](#8-what-does-get-return-for-a-missing-key)
9. [Important Map Methods](#9-important-map-methods)
10. [get() vs getOrDefault()](#10-get-vs-getordefault)
11. [put() vs putIfAbsent()](#11-put-vs-putifabsent)
12. [remove() vs clear()](#12-remove-vs-clear)
13. [replace()](#13-replace)
14. [containsKey() vs containsValue()](#14-containskey-vs-containsvalue)
15. [keySet(), values(), and entrySet()](#15-keyset-values-and-entryset)
16. [What is Map.Entry?](#16-what-is-mapentry)
17. [How Do You Iterate Over a Map?](#17-how-do-you-iterate-over-a-map)
18. [Why is entrySet() Often Preferred?](#18-why-is-entryset-often-preferred)
19. [Can Map Store null?](#19-can-map-store-null)
20. [What is HashMap?](#20-what-is-hashmap)
21. [What is LinkedHashMap?](#21-what-is-linkedhashmap)
22. [What is TreeMap?](#22-what-is-treemap)
23. [What is Hashtable?](#23-what-is-hashtable)
24. [What is ConcurrentHashMap?](#24-what-is-concurrenthashmap)
25. [HashMap vs LinkedHashMap](#25-hashmap-vs-linkedhashmap)
26. [HashMap vs TreeMap](#26-hashmap-vs-treemap)
27. [HashMap vs Hashtable](#27-hashmap-vs-hashtable)
28. [HashMap vs ConcurrentHashMap](#28-hashmap-vs-concurrenthashmap)
29. [TreeMap vs TreeSet](#29-treemap-vs-treeset)
30. [Map vs Set](#30-map-vs-set)
31. [Map vs List](#31-map-vs-list)
32. [Time Complexity of Common Map Operations](#32-time-complexity-of-common-map-operations)
33. [How is Map Used in DSA?](#33-how-is-map-used-in-dsa)
34. [Frequency Counting](#34-frequency-counting)
35. [Two Sum Using Map](#35-two-sum-using-map)
36. [First Non-Repeating Character](#36-first-non-repeating-character)
37. [Anagram Checking](#37-anagram-checking)
38. [Grouping with Map](#38-grouping-with-map)
39. [Common Output Questions](#39-common-output-questions)
40. [Scenario-Based Questions](#40-scenario-based-questions)
41. [Quick Interview Revision](#41-quick-interview-revision)
42. [Interview Checklist](#42-interview-checklist)
43. [Progress](#43-progress)

---

# 1. What is Map?

`Map` is an interface in Java that represents a collection of **key-value mappings**.

Each key maps to at most one value.

Example:

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Priya");
```

Conceptually:

```text
101 → Mahesh
102 → Rahul
103 → Priya
```

---

# 2. Is Map an Interface or Class?

`Map` is an **interface**.

You cannot directly instantiate it:

```java
Map<Integer, String> map = new Map<>();
```

Instead, use an implementation:

```java
Map<Integer, String> map = new HashMap<>();
```

Other implementations include:

```java
LinkedHashMap
TreeMap
Hashtable
ConcurrentHashMap
```

---

# 3. Does Map Extend Collection?

### No.

`Map` does **not** extend the `Collection` interface.

They are separate interfaces.

```text
Collection
├── List
├── Set
└── Queue

Map
├── HashMap
├── LinkedHashMap
├── TreeMap
└── ...
```

This is a common interview question.

---

# 4. What Does a Map Store?

A `Map` stores:

```text
Key → Value
```

Example:

```text
Student ID → Student Name
```

```text
101 → Mahesh
102 → Rahul
103 → Priya
```

Keys are used to locate values.

---

# 5. Can a Map Have Duplicate Keys?

### No.

Keys must be unique.

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");
map.put(101, "Spring");
```

The second operation replaces the first value.

Final mapping:

```text
101 → Spring
```

---

# 6. Can a Map Have Duplicate Values?

### Yes.

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");
map.put(102, "Java");
map.put(103, "Spring");
```

This is completely valid.

The keys are different even though two values are the same.

---

# 7. What Happens When put() Uses an Existing Key?

The existing value is replaced.

```java
map.put(101, "Java");
map.put(101, "Spring");
```

The result is:

```text
101 → Spring
```

The key is not duplicated.

---

# 8. What Does get() Return for a Missing Key?

Normally:

```java
map.get(missingKey)
```

returns:

```text
null
```

Example:

```java
Map<Integer, String> map = new HashMap<>();

System.out.println(map.get(999));
```

Output:

```text
null
```

If you need a fallback value, use:

```java
map.getOrDefault(999, "Unknown");
```

---

# 9. Important Map Methods

| Method | Purpose |
|---|---|
| `put()` | Add or update mapping |
| `get()` | Retrieve value |
| `remove()` | Remove mapping |
| `containsKey()` | Check key |
| `containsValue()` | Check value |
| `size()` | Number of mappings |
| `isEmpty()` | Check empty |
| `clear()` | Remove everything |
| `getOrDefault()` | Get value or fallback |
| `putIfAbsent()` | Add only if absent |
| `replace()` | Replace existing value |
| `keySet()` | Get keys |
| `values()` | Get values |
| `entrySet()` | Get mappings |

---

# 10. get() vs getOrDefault()

## get()

```java
map.get(101);
```

If the key does not exist:

```text
null
```

## getOrDefault()

```java
map.getOrDefault(101, "Unknown");
```

If the key does not exist:

```text
Unknown
```

### Interview Answer

Use `getOrDefault()` when you want a fallback value instead of handling a missing mapping separately.

---

# 11. put() vs putIfAbsent()

## put()

Always inserts or replaces the value associated with the key.

```java
map.put(101, "Java");
map.put(101, "Spring");
```

Result:

```text
101 → Spring
```

---

## putIfAbsent()

Adds the mapping only if the key is not already associated with a value.

```java
map.put(101, "Java");
map.putIfAbsent(101, "Spring");
```

Result:

```text
101 → Java
```

### Interview Rule

```text
put()
→ add or replace

putIfAbsent()
→ add only when absent
```

---

# 12. remove() vs clear()

## remove()

Removes a specific mapping.

```java
map.remove(101);
```

## clear()

Removes all mappings.

```java
map.clear();
```

---

# 13. replace()

`replace()` updates an existing mapping.

```java
map.replace(101, "Spring");
```

It does not add a new mapping if the key is absent.

---

## Conditional Replace

```java
map.replace(
    101,
    "Java",
    "Spring"
);
```

The replacement occurs only if the current value is `"Java"`.

---

# 14. containsKey() vs containsValue()

### containsKey()

Checks whether a key exists.

```java
map.containsKey(101);
```

### containsValue()

Checks whether a value exists.

```java
map.containsValue("Java");
```

### Interview Tip

If your goal is to determine whether a mapping exists for a particular identifier, `containsKey()` is usually the relevant operation.

---

# 15. keySet(), values(), and entrySet()

## keySet()

Returns all keys.

```java
map.keySet();
```

Type:

```java
Set<K>
```

---

## values()

Returns all values.

```java
map.values();
```

Type:

```java
Collection<V>
```

---

## entrySet()

Returns all key-value mappings.

```java
map.entrySet();
```

Type:

```java
Set<Map.Entry<K, V>>
```

---

# 16. What is Map.Entry?

`Map.Entry<K, V>` represents one mapping inside a map.

Example:

```java
for (Map.Entry<Integer, String> entry :
        map.entrySet()) {

    System.out.println(entry.getKey());
    System.out.println(entry.getValue());
}
```

Important methods:

```java
entry.getKey();
entry.getValue();
entry.setValue(...);
```

---

# 17. How Do You Iterate Over a Map?

There are several ways.

## Using keySet()

```java
for (Integer key : map.keySet()) {
    System.out.println(
        key + " → " + map.get(key)
    );
}
```

---

## Using entrySet()

```java
for (Map.Entry<Integer, String> entry :
        map.entrySet()) {

    System.out.println(
        entry.getKey() + " → " + entry.getValue()
    );
}
```

---

## Using forEach()

```java
map.forEach(
    (key, value) ->
        System.out.println(
            key + " → " + value
        )
);
```

---

# 18. Why is entrySet() Often Preferred?

Suppose you need both key and value.

With `keySet()`:

```java
for (Integer key : map.keySet()) {
    String value = map.get(key);
}
```

With `entrySet()`:

```java
for (Map.Entry<Integer, String> entry :
        map.entrySet()) {

    Integer key = entry.getKey();
    String value = entry.getValue();
}
```

`entrySet()` directly gives you both parts of each mapping and is generally the natural choice when both key and value are needed.

---

# 19. Can Map Store null?

There is no single answer for all `Map` implementations.

It depends on the implementation.

For example:

### HashMap

Allows:

```text
one null key
multiple null values
```

### TreeMap

Natural ordering does not generally support a `null` key.

### Hashtable

Does not allow:

```text
null key
null value
```

### ConcurrentHashMap

Does not allow:

```text
null key
null value
```

Always consider the specific implementation.

---

# 20. What is HashMap?

`HashMap` is a widely used `Map` implementation.

Characteristics:

- No guaranteed iteration order.
- Allows one `null` key.
- Allows multiple `null` values.
- Not synchronized.
- Average basic operations are typically `O(1)`.

Example:

```java
Map<Integer, String> map =
        new HashMap<>();
```

---

# 21. What is LinkedHashMap?

`LinkedHashMap` is a `HashMap`-based map implementation that maintains a predictable iteration order.

By default, it maintains insertion order.

```java
Map<Integer, String> map =
        new LinkedHashMap<>();
```

Example insertion:

```text
101
102
103
```

Iteration normally follows:

```text
101
102
103
```

It can also be configured for access-order.

---

# 22. What is TreeMap?

`TreeMap` is a `NavigableMap` implementation that maintains mappings according to sorted key order.

```java
Map<Integer, String> map =
        new TreeMap<>();
```

Example:

```java
map.put(30, "C");
map.put(10, "A");
map.put(20, "B");
```

Keys are maintained as:

```text
10
20
30
```

Basic operations are typically:

```text
O(log n)
```

---

# 23. What is Hashtable?

`Hashtable` is a legacy synchronized `Map` implementation.

Important properties:

- Synchronized.
- Does not allow `null` keys.
- Does not allow `null` values.
- Older API.

It is generally not the first choice for new concurrent code.

---

# 24. What is ConcurrentHashMap?

`ConcurrentHashMap` is designed for concurrent access.

It allows multiple threads to operate on the map with concurrency-oriented semantics.

Important:

```text
ConcurrentHashMap does not allow null keys or null values.
```

Use it when the application's requirements involve concurrent map access.

---

# 25. HashMap vs LinkedHashMap

| Feature | HashMap | LinkedHashMap |
|---|---|---|
| Key-value storage | Yes | Yes |
| Duplicate keys | No | No |
| Guaranteed iteration order | No | Yes |
| Default ordering | None | Insertion order |
| `null` key | One | One |
| Typical basic operations | `O(1)` average | `O(1)` average |
| Access-order mode | No | Yes |

---

# 26. HashMap vs TreeMap

| Feature | HashMap | TreeMap |
|---|---|---|
| Ordering | No guaranteed order | Sorted by keys |
| Basic operation | `O(1)` average | `O(log n)` |
| Navigation | No | Yes |
| Range operations | No | Yes |
| `null` key | One | Not with natural ordering |
| Data structure | Hash table/tree bins internally | Red-black tree |

### Interview Rule

Use:

```text
HashMap
```

when you mainly need key-based lookup without sorted-key requirements.

Use:

```text
TreeMap
```

when sorted keys or navigation/range operations are required.

---

# 27. HashMap vs Hashtable

| Feature | HashMap | Hashtable |
|---|---|---|
| Thread-safe by default | No | Yes |
| `null` key | One | No |
| `null` values | Yes | No |
| Modern API | Yes | Legacy |
| Typical choice for new code | Yes | Usually no |

---

# 28. HashMap vs ConcurrentHashMap

| Feature | HashMap | ConcurrentHashMap |
|---|---|---|
| Concurrent use | Not thread-safe | Designed for concurrent access |
| `null` key | One | No |
| `null` values | Yes | No |
| Typical single-thread use | Yes | Sometimes |
| Concurrent applications | Requires external design/synchronization | Designed for this use case |

---

# 29. TreeMap vs TreeSet

Both are sorted tree-based collections, but they solve different problems.

| TreeMap | TreeSet |
|---|---|
| Stores key-value pairs | Stores individual elements |
| Keys are sorted | Elements are sorted |
| Implements `NavigableMap` | Implements `NavigableSet` |
| `put(key, value)` | `add(element)` |
| `get(key)` | No `get()` |

Conceptually:

```text
TreeMap
Key → Value
```

```text
TreeSet
Value
Value
Value
```

---

# 30. Map vs Set

## Map

Stores:

```text
Key → Value
```

## Set

Stores:

```text
Unique elements
```

Example:

```java
Set<Integer> ids;
```

versus:

```java
Map<Integer, String> students;
```

---

# 31. Map vs List

## List

```text
Index → Element
```

Example:

```java
List<String> names;
names.get(0);
```

## Map

```text
Key → Value
```

Example:

```java
Map<Integer, String> students;
students.get(101);
```

---

# 32. Time Complexity of Common Map Operations

Complexity depends on the implementation.

## HashMap

Average:

```text
put()       → O(1)
get()       → O(1)
remove()    → O(1)
containsKey → O(1)
```

Worst-case behavior can differ because of collisions and treeification details.

---

## TreeMap

Typically:

```text
put()       → O(log n)
get()       → O(log n)
remove()    → O(log n)
containsKey → O(log n)
```

---

## LinkedHashMap

Average:

```text
put()       → O(1)
get()       → O(1)
remove()    → O(1)
```

with additional linked-order maintenance.

---

# 33. How is Map Used in DSA?

`Map` is extremely important in DSA.

Common applications:

```text
Frequency counting
Duplicate detection
Hashing
Two Sum
Anagram checking
Prefix sums
Grouping
Caching
Lookup tables
```

---

# 34. Frequency Counting

Given:

```text
[1, 2, 2, 3, 1, 1]
```

Use:

```java
Map<Integer, Integer> frequency =
        new HashMap<>();
```

Then:

```java
for (int number : numbers) {

    frequency.put(
        number,
        frequency.getOrDefault(number, 0) + 1
    );
}
```

Result:

```text
1 → 3
2 → 2
3 → 1
```

---

# 35. Two Sum Using Map

Given:

```text
[2, 7, 11, 15]
```

Target:

```text
9
```

We need:

```text
2 + 7 = 9
```

A map can store previously seen values.

Concept:

```text
value → index
```

For every number:

```text
complement = target - number
```

Check whether the complement already exists.

This gives an average `O(n)` solution for the standard Two Sum problem.

---

# 36. First Non-Repeating Character

Given:

```text
"swiss"
```

Count frequencies:

```text
s → 3
w → 1
i → 1
```

Then scan the string from left to right.

The first character with frequency `1` is:

```text
w
```

A `LinkedHashMap` can be useful when you want predictable insertion-order iteration, though another approach is to use a frequency map and then scan the original string.

---

# 37. Anagram Checking

Two strings are anagrams if they contain the same characters with the same frequencies.

Example:

```text
listen
silent
```

Approach:

1. Count characters in the first string.
2. Decrease counts using the second string.
3. Verify all counts become zero.

Use:

```java
Map<Character, Integer>
```

---

# 38. Grouping with Map

A map can group multiple values under a common key.

Example:

```text
Department → Employees
```

```java
Map<String, List<String>> employees =
        new HashMap<>();
```

Conceptually:

```text
IT
 ├── Mahesh
 └── Rahul

HR
 └── Priya
```

This pattern appears frequently in backend applications.

---

# 39. Common Output Questions

## Question 1

What is the final value?

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "A");
map.put(2, "B");
map.put(1, "C");

System.out.println(map.get(1));
```

### Answer

```text
C
```

---

## Question 2

What is the size?

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "A");
map.put(2, "B");
map.put(1, "C");

System.out.println(map.size());
```

### Answer

```text
2
```

The key `1` was updated rather than duplicated.

---

## Question 3

What does this return?

```java
map.get(999);
```

### Answer

```text
null
```

assuming `999` is not mapped and no unusual implementation behavior is involved.

---

## Question 4

What is the result?

```java
Map<Integer, String> map = new HashMap<>();

map.put(1, "Java");
map.put(2, "Java");

System.out.println(map.size());
```

### Answer

```text
2
```

Duplicate values are allowed.

---

# 40. Scenario-Based Questions

## Scenario 1

You need:

```text
Student ID → Student
```

and mostly need fast average lookup.

### Suitable choice

```text
HashMap
```

---

## Scenario 2

You need:

```text
Student ID → Student
```

and want iteration in insertion order.

### Suitable choice

```text
LinkedHashMap
```

---

## Scenario 3

You need employee IDs to always appear in ascending order.

### Suitable choice

```text
TreeMap
```

---

## Scenario 4

You need to count how many times each number appears.

### Suitable choice

```text
HashMap<Integer, Integer>
```

---

## Scenario 5

Multiple threads need to update a shared map.

### Suitable choice

Consider:

```text
ConcurrentHashMap
```

based on the concurrency requirements.

---

## Scenario 6

You need a legacy synchronized map that does not allow `null`.

### Answer

```text
Hashtable
```

---

# 41. Quick Interview Revision

```text
Map
│
├── Key → Value
├── Keys are unique
├── Values may repeat
├── Does not extend Collection
│
├── HashMap
│   ├── No guaranteed order
│   ├── Average O(1) basic operations
│   └── Allows one null key
│
├── LinkedHashMap
│   └── Predictable iteration order
│
├── TreeMap
│   ├── Sorted keys
│   └── O(log n) basic operations
│
├── Hashtable
│   ├── Legacy
│   ├── Synchronized
│   └── No null keys/values
│
└── ConcurrentHashMap
    ├── Concurrent access
    └── No null keys/values
```

---

# 42. Interview Checklist

## Map Fundamentals

- [ ] What is `Map`?
- [ ] Is `Map` an interface?
- [ ] Does `Map` extend `Collection`?
- [ ] What is a key-value pair?
- [ ] Can keys be duplicated?
- [ ] Can values be duplicated?
- [ ] What happens when the same key is inserted again?

---

## Methods

- [ ] `put()`
- [ ] `get()`
- [ ] `remove()`
- [ ] `containsKey()`
- [ ] `containsValue()`
- [ ] `size()`
- [ ] `isEmpty()`
- [ ] `clear()`
- [ ] `getOrDefault()`
- [ ] `putIfAbsent()`
- [ ] `replace()`
- [ ] `keySet()`
- [ ] `values()`
- [ ] `entrySet()`

---

## Iteration

- [ ] Iterate using `keySet()`.
- [ ] Iterate using `entrySet()`.
- [ ] Use `Map.Entry`.
- [ ] Use `forEach()`.
- [ ] Know why `entrySet()` is useful.

---

## Implementations

- [ ] `HashMap`
- [ ] `LinkedHashMap`
- [ ] `TreeMap`
- [ ] `Hashtable`
- [ ] `ConcurrentHashMap`

---

## DSA

- [ ] Frequency counting.
- [ ] Duplicate detection.
- [ ] Two Sum.
- [ ] Character frequency.
- [ ] Word frequency.
- [ ] Anagram checking.
- [ ] First non-repeating character.
- [ ] Grouping.
- [ ] Prefix sum + map.

---

# 43. Progress

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
├── 18-LinkedHashMap
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

**`16-Map` is now complete: `NOTES.md` + `PRACTICE.md` + `INTERVIEW.md`.**

**Next: `17-HashMap/NOTES.md`.**
