# Map — Java Collections Framework

> `Map` is one of the most important parts of the Java Collections Framework. It stores data as **key-value pairs** and is heavily used in real-world applications, DSA, databases, caching, configuration, and backend development.

---

## 📚 Table of Contents

1. [What is Map?](#1-what-is-map)
2. [Map Hierarchy](#2-map-hierarchy)
3. [Key-Value Pair](#3-key-value-pair)
4. [Important Features](#4-important-features)
5. [Creating a Map](#5-creating-a-map)
6. [Basic Operations](#6-basic-operations)
7. [put()](#7-put)
8. [get()](#8-get)
9. [put() with Existing Key](#9-put-with-existing-key)
10. [remove()](#10-remove)
11. [containsKey()](#11-containskey)
12. [containsValue()](#12-containsvalue)
13. [size()](#13-size)
14. [isEmpty()](#14-isempty)
15. [clear()](#15-clear)
16. [getOrDefault()](#16-getordefault)
17. [putIfAbsent()](#17-putifabsent)
18. [replace()](#18-replace)
19. [replaceAll()](#19-replaceall)
20. [Key Set](#20-key-set)
21. [Values Collection](#21-values-collection)
22. [Entry Set](#22-entry-set)
23. [Iterating Over a Map](#23-iterating-over-a-map)
24. [Map.Entry](#24-mapentry)
25. [forEach()](#25-foreach)
26. [Null Keys and Values](#26-null-keys-and-values)
27. [Duplicate Keys](#27-duplicate-keys)
28. [Duplicate Values](#28-duplicate-values)
29. [Map vs Collection](#29-map-vs-collection)
30. [Map vs List](#30-map-vs-list)
31. [Map vs Set](#31-map-vs-set)
32. [Common Map Implementations](#32-common-map-implementations)
33. [HashMap](#33-hashmap)
34. [LinkedHashMap](#34-linkedhashmap)
35. [TreeMap](#35-treemap)
36. [Hashtable](#36-hashtable)
37. [ConcurrentHashMap](#37-concurrenthashmap)
38. [Map Methods Quick Reference](#38-map-methods-quick-reference)
39. [Common Use Cases](#39-common-use-cases)
40. [Important Interview Points](#40-important-interview-points)
41. [Quick Revision](#41-quick-revision)
42. [Practice Direction](#42-practice-direction)
43. [Progress](#43-progress)

---

# 1. What is Map?

`Map` is an interface in Java that represents a collection of **key-value mappings**.

Each mapping contains:

```text
Key → Value
```

Example:

```text
101 → Mahesh
102 → Rahul
103 → Priya
```

A map is useful when you want to retrieve a value using a key.

Example:

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Priya");

System.out.println(students.get(101));
```

Output:

```text
Mahesh
```

---

# 2. Map Hierarchy

`Map` is separate from the `Collection` interface hierarchy.

```text
                  Map
                   │
       ┌───────────┼──────────────┐
       │           │              │
    HashMap    TreeMap      LinkedHashMap
       │
       └── Hashtable
       
ConcurrentHashMap
```

Important:

```text
Map
```

does **not** extend:

```text
Collection
```

Instead, `Map` is its own top-level interface in the Java Collections Framework.

---

# 3. Key-Value Pair

A map stores data using:

```text
Key → Value
```

Example:

```java
Map<String, Integer> marks = new HashMap<>();

marks.put("Maths", 90);
marks.put("Java", 85);
marks.put("DBMS", 88);
```

Conceptually:

```text
Maths → 90
Java  → 85
DBMS  → 88
```

You use the key to retrieve the value:

```java
marks.get("Java");
```

Output:

```text
85
```

---

# 4. Important Features

| Feature | Map |
|---|---|
| Stores | Key-value pairs |
| Duplicate keys | Not allowed |
| Duplicate values | Allowed |
| Access by | Key |
| Index-based access | No |
| `get(key)` | Yes |
| `containsKey()` | Yes |
| `containsValue()` | Yes |
| `keySet()` | Yes |
| `values()` | Yes |
| `entrySet()` | Yes |

The exact ordering and `null` behavior depend on the implementation.

---

# 5. Creating a Map

Since `Map` is an interface, you cannot directly do:

```java
Map<Integer, String> map = new Map<>();
```

Instead, use an implementation:

```java
Map<Integer, String> map = new HashMap<>();
```

This is preferred because the variable uses the interface type while the object uses a concrete implementation.

Other implementations include:

```java
Map<Integer, String> map = new LinkedHashMap<>();
```

```java
Map<Integer, String> map = new TreeMap<>();
```

---

# 6. Basic Operations

Consider:

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Priya");
```

Common operations:

```java
students.put(104, "Amit");

students.get(101);

students.remove(102);

students.containsKey(103);

students.containsValue("Priya");

students.size();

students.isEmpty();

students.clear();
```

---

# 7. put()

`put()` adds a key-value pair.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Priya");

System.out.println(students);
```

Conceptually:

```text
101 → Mahesh
102 → Rahul
103 → Priya
```

The exact printed order depends on the map implementation.

---

# 8. get()

`get(key)` returns the value associated with the key.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");

System.out.println(students.get(101));
```

Output:

```text
Mahesh
```

If the key does not exist:

```java
System.out.println(students.get(999));
```

Typically:

```text
null
```

---

# 9. put() with Existing Key

A map cannot contain duplicate keys.

If you insert the same key again, its value is replaced.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(101, "Rahul");

System.out.println(students.get(101));
```

Output:

```text
Rahul
```

The key remains:

```text
101
```

but its value changes:

```text
Mahesh → Rahul
```

---

# 10. remove()

`remove(key)` removes the mapping associated with the key.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");

students.remove(101);

System.out.println(students);
```

The mapping:

```text
101 → Mahesh
```

is removed.

---

## Remove Only if Key-Value Matches

```java
students.remove(102, "Rahul");
```

This removes the entry only when both the key and value match.

---

# 11. containsKey()

Checks whether a key exists.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");

System.out.println(students.containsKey(101));
System.out.println(students.containsKey(999));
```

Output:

```text
true
false
```

This is commonly used before retrieving or updating data.

---

# 12. containsValue()

Checks whether a value exists.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");

System.out.println(students.containsValue("Mahesh"));
```

Output:

```text
true
```

---

# 13. size()

Returns the number of key-value mappings.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Priya");

System.out.println(students.size());
```

Output:

```text
3
```

---

# 14. isEmpty()

Checks whether the map contains no mappings.

```java
Map<Integer, String> students = new HashMap<>();

System.out.println(students.isEmpty());

students.put(101, "Mahesh");

System.out.println(students.isEmpty());
```

Output:

```text
true
false
```

---

# 15. clear()

Removes all mappings.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");

students.clear();

System.out.println(students);
```

Output:

```text
{}
```

---

# 16. getOrDefault()

`getOrDefault()` returns the value associated with a key.

If the key does not exist, it returns the supplied default value.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");

System.out.println(
    students.getOrDefault(101, "Unknown")
);

System.out.println(
    students.getOrDefault(999, "Unknown")
);
```

Output:

```text
Mahesh
Unknown
```

---

# 17. putIfAbsent()

Adds a mapping only if the key is not already associated with a value.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");

students.putIfAbsent(101, "Rahul");
students.putIfAbsent(102, "Priya");

System.out.println(students);
```

The value for `101` remains:

```text
Mahesh
```

because the key already exists.

---

# 18. replace()

`replace()` changes the value associated with an existing key.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");

students.replace(101, "Rahul");

System.out.println(students.get(101));
```

Output:

```text
Rahul
```

---

## Conditional replace

```java
students.replace(101, "Rahul", "Amit");
```

The replacement happens only when the current value is `"Rahul"`.

---

# 19. replaceAll()

`replaceAll()` applies a function to every mapping.

Example:

```java
Map<String, Integer> marks = new HashMap<>();

marks.put("Java", 80);
marks.put("DBMS", 70);
marks.put("DSA", 90);

marks.replaceAll(
    (subject, mark) -> mark + 5
);
```

Result:

```text
Java → 85
DBMS → 75
DSA  → 95
```

---

# 20. Key Set

`keySet()` returns a `Set` containing all keys.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Priya");

System.out.println(students.keySet());
```

Conceptually:

```text
[101, 102, 103]
```

The exact iteration order depends on the implementation.

---

# 21. Values Collection

`values()` returns a `Collection` containing all values.

```java
System.out.println(students.values());
```

Conceptually:

```text
[Mahesh, Rahul, Priya]
```

Unlike keys, values do not have to be unique.

Example:

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Mahesh");
students.put(103, "Rahul");
```

Here `"Mahesh"` appears as a value more than once.

---

# 22. Entry Set

`entrySet()` returns a set of key-value mappings.

```java
System.out.println(students.entrySet());
```

Conceptually:

```text
[101=Mahesh, 102=Rahul, 103=Priya]
```

`entrySet()` is commonly used when iterating through both keys and values.

---

# 23. Iterating Over a Map

## Using keySet()

```java
for (Integer key : students.keySet()) {
    System.out.println(
        key + " → " + students.get(key)
    );
}
```

This works, but when both key and value are needed, `entrySet()` is generally preferable.

---

## Using entrySet()

```java
for (Map.Entry<Integer, String> entry :
        students.entrySet()) {

    System.out.println(
        entry.getKey() + " → " + entry.getValue()
    );
}
```

---

# 24. Map.Entry

`Map.Entry<K, V>` represents one key-value mapping.

Important methods:

```java
entry.getKey();
```

Returns the key.

```java
entry.getValue();
```

Returns the value.

```java
entry.setValue(newValue);
```

Changes the value of the entry where supported by the map's entry view.

---

## Example

```java
for (Map.Entry<Integer, String> entry :
        students.entrySet()) {

    Integer id = entry.getKey();
    String name = entry.getValue();

    System.out.println(id + " → " + name);
}
```

---

# 25. forEach()

Modern Java allows convenient map iteration:

```java
students.forEach(
    (id, name) ->
        System.out.println(id + " → " + name)
);
```

This is concise and useful for simple processing.

---

# 26. Null Keys and Values

`Map` itself does not define one universal `null` policy.

It depends on the implementation.

For example, `HashMap` permits:

```text
one null key
multiple null values
```

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(null, "Unknown");
map.put(101, null);
```

Other implementations have different rules.

For example:

```text
TreeMap
```

has different behavior for `null` keys under natural ordering.

Therefore, always check the specific implementation's contract.

---

# 27. Duplicate Keys

A `Map` does not allow duplicate keys.

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");
map.put(101, "Spring");
```

The final mapping is:

```text
101 → Spring
```

The second `put()` replaces the previous value.

---

# 28. Duplicate Values

Duplicate values are allowed.

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Java");
map.put(102, "Java");
map.put(103, "Spring");
```

This is valid.

Conceptually:

```text
101 → Java
102 → Java
103 → Spring
```

Keys must be unique; values do not have to be.

---

# 29. Map vs Collection

A `Collection` generally represents a group of individual elements.

Example:

```text
10
20
30
```

A `Map` represents relationships between keys and values.

Example:

```text
101 → Mahesh
102 → Rahul
103 → Priya
```

Important:

```text
Map ≠ Collection
```

`Map` is a separate interface.

---

# 30. Map vs List

| Feature | Map | List |
|---|---|---|
| Data model | Key-value | Ordered elements |
| Duplicate keys/elements | Keys: No | Elements: Yes |
| Index | No | Yes |
| Access | By key | By index |
| `get()` | `get(key)` | `get(index)` |
| Example | `HashMap` | `ArrayList` |

---

# 31. Map vs Set

| Feature | Map | Set |
|---|---|---|
| Stores | Key-value pairs | Individual elements |
| Duplicate keys/elements | Keys: No | No |
| Access | By key | By element |
| Values | Yes | No |
| Example | `HashMap` | `HashSet` |

---

# 32. Common Map Implementations

The most important implementations are:

```text
HashMap
LinkedHashMap
TreeMap
Hashtable
ConcurrentHashMap
```

They differ mainly in:

- Ordering.
- Thread-safety.
- Performance characteristics.
- `null` support.
- Concurrency behavior.

---

# 33. HashMap

`HashMap` is one of the most commonly used `Map` implementations.

Characteristics:

- No guaranteed iteration order.
- Allows one `null` key.
- Allows multiple `null` values.
- Not synchronized.
- Average basic operations are typically `O(1)`.

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Mahesh");
map.put(102, "Rahul");
```

---

# 34. LinkedHashMap

`LinkedHashMap` maintains a predictable iteration order.

By default, that order is insertion order.

```java
Map<Integer, String> map =
        new LinkedHashMap<>();

map.put(101, "Mahesh");
map.put(102, "Rahul");
map.put(103, "Priya");
```

Iteration follows:

```text
101
102
103
```

It can also be configured for access-order, which is useful in some cache implementations.

---

# 35. TreeMap

`TreeMap` stores mappings according to sorted key order.

```java
Map<Integer, String> map =
        new TreeMap<>();

map.put(30, "C");
map.put(10, "A");
map.put(20, "B");
```

The keys are maintained in sorted order:

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

# 36. Hashtable

`Hashtable` is an older synchronized map implementation.

Important characteristics:

- Synchronized.
- Does not allow `null` keys.
- Does not allow `null` values.
- Legacy class.

For modern concurrent applications, `ConcurrentHashMap` is often considered instead when its semantics fit the requirement.

---

# 37. ConcurrentHashMap

`ConcurrentHashMap` is designed for concurrent access.

It supports concurrent operations without synchronizing the entire map for ordinary operations.

Important:

```text
ConcurrentHashMap does not allow null keys or null values.
```

Use it when multiple threads need to work with a shared map and concurrent semantics are required.

---

# 38. Map Methods Quick Reference

| Method | Purpose |
|---|---|
| `put(k, v)` | Add/update mapping |
| `get(k)` | Get value |
| `remove(k)` | Remove mapping |
| `containsKey(k)` | Check key |
| `containsValue(v)` | Check value |
| `size()` | Number of mappings |
| `isEmpty()` | Check whether empty |
| `clear()` | Remove all mappings |
| `getOrDefault()` | Get value or fallback |
| `putIfAbsent()` | Add only if absent |
| `replace()` | Replace existing value |
| `replaceAll()` | Update all values |
| `keySet()` | Get keys |
| `values()` | Get values |
| `entrySet()` | Get entries |
| `forEach()` | Iterate mappings |

---

# 39. Common Use Cases

## 1. Student Lookup

```text
Student ID → Student Name
```

Example:

```java
Map<Integer, String> students;
```

---

## 2. Frequency Counting

```text
Word → Frequency
```

Example:

```text
java → 5
spring → 3
docker → 2
```

---

## 3. Caching

```text
Key → Cached Data
```

---

## 4. Configuration

```text
Property → Value
```

Example:

```text
server.port → 8080
```

---

## 5. Database-Like Lookup

```text
User ID → User Object
```

---

## 6. Graph Representation

An adjacency list can be represented using a map:

```text
Vertex → Neighbors
```

---

## 7. Grouping

For example:

```text
Department → Employees
```

---

# 40. Important Interview Points

### Point 1

`Map` stores:

```text
Key → Value
```

### Point 2

Keys must be unique.

### Point 3

Values can be duplicated.

### Point 4

`Map` does not extend `Collection`.

### Point 5

The exact ordering depends on the implementation.

### Point 6

`HashMap` is generally used when ordering is not required.

### Point 7

`LinkedHashMap` maintains predictable iteration order.

### Point 8

`TreeMap` maintains sorted key order.

### Point 9

`ConcurrentHashMap` is designed for concurrent access.

### Point 10

`Hashtable` is a legacy synchronized implementation.

---

# 41. Quick Revision

```text
Map
│
├── Stores key-value pairs
├── Keys are unique
├── Values can repeat
├── Map is separate from Collection
│
├── HashMap
│   └── No guaranteed order
│
├── LinkedHashMap
│   └── Predictable iteration order
│
├── TreeMap
│   └── Sorted keys
│
├── Hashtable
│   └── Legacy synchronized map
│
└── ConcurrentHashMap
    └── Concurrent access
```

---

# 42. Practice Direction

Before moving to the next topic, practice:

### Basic

- [ ] Create a `HashMap`.
- [ ] Add key-value pairs.
- [ ] Retrieve values.
- [ ] Update values.
- [ ] Remove entries.
- [ ] Check keys.
- [ ] Check values.
- [ ] Check size.
- [ ] Clear the map.

### Intermediate

- [ ] Iterate using `keySet()`.
- [ ] Iterate using `entrySet()`.
- [ ] Use `Map.Entry`.
- [ ] Use `getOrDefault()`.
- [ ] Use `putIfAbsent()`.
- [ ] Use `replace()`.
- [ ] Use `replaceAll()`.
- [ ] Count frequencies using a map.

### Comparison

Understand when to choose:

```text
HashMap
LinkedHashMap
TreeMap
Hashtable
ConcurrentHashMap
```

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
│   ├── PRACTICE.md    [ ]
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

**`16-Map/NOTES.md` is complete. Next: `16-Map/PRACTICE.md`.**
