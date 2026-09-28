# Hashtable — Java Collections Framework

> A practical and interview-ready guide to Java `Hashtable`, covering its legacy design, synchronization, hashing, null restrictions, performance, Enumeration, comparison with modern Map implementations, and real-world considerations.

---

## Table of Contents

- [1. What is Hashtable?](#1-what-is-hashtable)
- [2. Why Hashtable is Important](#2-why-hashtable-is-important)
- [3. Hashtable Hierarchy](#3-hashtable-hierarchy)
- [4. Key Features](#4-key-features)
- [5. Creating a Hashtable](#5-creating-a-hashtable)
- [6. Basic Operations](#6-basic-operations)
- [7. put()](#7-put)
- [8. get()](#8-get)
- [9. remove()](#9-remove)
- [10. containsKey()](#10-containskey)
- [11. containsValue()](#11-containsvalue)
- [12. size() and isEmpty()](#12-size-and-isempty)
- [13. clear()](#13-clear)
- [14. putIfAbsent()](#14-putifabsent)
- [15. replace()](#15-replace)
- [16. Iterating over Hashtable](#16-iterating-over-hashtable)
- [17. Enumeration](#17-enumeration)
- [18. Null Keys and Null Values](#18-null-keys-and-null-values)
- [19. Duplicate Keys and Values](#19-duplicate-keys-and-values)
- [20. Hashing](#20-hashing)
- [21. hashCode() and equals()](#21-hashcode-and-equals)
- [22. How Hashtable Works Internally](#22-how-hashtable-works-internally)
- [23. Buckets and Collisions](#23-buckets-and-collisions)
- [24. Collision Handling](#24-collision-handling)
- [25. Synchronization](#25-synchronization)
- [26. Why Hashtable is Considered Legacy](#26-why-hashtable-is-considered-legacy)
- [27. Hashtable vs HashMap](#27-hashtable-vs-hashmap)
- [28. Hashtable vs ConcurrentHashMap](#28-hashtable-vs-concurrenthashmap)
- [29. Hashtable vs LinkedHashMap](#29-hashtable-vs-linkedhashmap)
- [30. Hashtable vs TreeMap](#30-hashtable-vs-treemap)
- [31. Capacity and Load Factor](#31-capacity-and-load-factor)
- [32. Rehashing](#32-rehashing)
- [33. Constructors](#33-constructors)
- [34. Custom Objects as Keys](#34-custom-objects-as-keys)
- [35. Common Use Cases](#35-common-use-cases)
- [36. Common Mistakes](#36-common-mistakes)
- [37. Interview Quick Revision](#37-interview-quick-revision)
- [38. Progress](#38-progress)

---

# 1. What is Hashtable?

`Hashtable` is a legacy Java class that implements the `Map` interface and stores data as:

```text
key -> value
```

Example:

```java
Hashtable<Integer, String> table =
        new Hashtable<>();

table.put(101, "Mahesh");
table.put(102, "Rahul");
table.put(103, "Amit");
```

It is a hash-table-based map.

---

# 2. Why Hashtable is Important

Hashtable is important mainly because it helps understand:

- Legacy Java collections
- Synchronized Map implementations
- Hashing
- Collision handling
- Differences between `Hashtable` and `HashMap`
- Why `ConcurrentHashMap` is preferred for modern concurrent applications

Although Hashtable is rarely the first choice in modern Java applications, it is still relevant for interviews.

---

# 3. Hashtable Hierarchy

Hashtable extends `Dictionary` and implements `Map`.

```text
Object
  |
Dictionary<K,V>
  |
Hashtable<K,V>
       |
       +---- implements Map<K,V>
```

It is therefore part of the Java Collections Framework through its implementation of `Map`.

---

# 4. Key Features

| Feature | Hashtable |
|---|---|
| Stores | Key-value pairs |
| Duplicate keys | No |
| Duplicate values | Yes |
| Ordering | No guaranteed order |
| Null key | Not allowed |
| Null value | Not allowed |
| Thread-safe | Yes, synchronized methods |
| Internal structure | Hash table |
| Basic lookup | O(1) expected |
| Legacy class | Yes |
| Implements | `Map` |
| Allows Enumeration | Yes |

---

# 5. Creating a Hashtable

## Basic

```java
Hashtable<Integer, String> table =
        new Hashtable<>();
```

---

## With Initial Capacity

```java
Hashtable<Integer, String> table =
        new Hashtable<>(20);
```

---

## With Capacity and Load Factor

```java
Hashtable<Integer, String> table =
        new Hashtable<>(20, 0.75f);
```

---

## From Another Map

```java
Map<Integer, String> source =
        new HashMap<>();

source.put(1, "A");
source.put(2, "B");

Hashtable<Integer, String> table =
        new Hashtable<>(source);
```

---

# 6. Basic Operations

Common operations include:

```java
table.put(key, value);
table.get(key);
table.remove(key);

table.containsKey(key);
table.containsValue(value);

table.size();
table.isEmpty();
table.clear();
```

Modern `Map` methods are also available, including methods such as:

```java
table.putIfAbsent(key, value);
table.replace(key, value);
table.computeIfAbsent(key, function);
table.computeIfPresent(key, function);
table.merge(key, value, function);
```

---

# 7. put()

Adds a key-value pair.

```java
Hashtable<Integer, String> table =
        new Hashtable<>();

table.put(101, "Mahesh");
table.put(102, "Rahul");
table.put(103, "Amit");
```

Result conceptually:

```text
101 -> Mahesh
102 -> Rahul
103 -> Amit
```

If the key already exists:

```java
table.put(101, "Updated");
```

the existing value is replaced.

---

# 8. get()

Retrieves the value associated with a key.

```java
String name = table.get(101);
```

Example:

```java
System.out.println(table.get(101));
```

Output:

```text
Mahesh
```

If the key does not exist:

```java
table.get(999);
```

returns:

```text
null
```

---

# 9. remove()

Removes a mapping by key.

```java
String removed =
        table.remove(101);
```

The removed value is returned.

After removal:

```text
101 -> Mahesh
```

is no longer present.

---

# 10. containsKey()

Checks whether a key exists.

```java
boolean exists =
        table.containsKey(102);
```

Example:

```java
if (table.containsKey(102)) {
    System.out.println("Student exists");
}
```

---

# 11. containsValue()

Checks whether a value exists.

```java
boolean exists =
        table.containsValue("Rahul");
```

Unlike key lookup, searching for a value requires scanning entries.

Typical complexity:

```text
O(n)
```

---

# 12. size() and isEmpty()

## size()

```java
int count = table.size();
```

Returns the number of mappings.

---

## isEmpty()

```java
if (table.isEmpty()) {
    System.out.println("Empty");
}
```

Returns:

```text
true
```

when there are no mappings.

---

# 13. clear()

Removes all mappings.

```java
table.clear();
```

After:

```java
table.clear();
```

the table is empty.

---

# 14. putIfAbsent()

Adds a mapping only if the key does not already have a non-null value.

```java
table.putIfAbsent(101, "Mahesh");
```

Since Hashtable does not allow null values, this behaves straightforwardly as:

```text
key exists
    -> existing value remains

key absent
    -> new value inserted
```

Example:

```java
table.put(101, "Mahesh");

table.putIfAbsent(101, "Rahul");
```

Result:

```text
101 -> Mahesh
```

---

# 15. replace()

Replaces the value for an existing key.

```java
table.replace(101, "Updated");
```

Before:

```text
101 -> Mahesh
```

After:

```text
101 -> Updated
```

You can also use the conditional form:

```java
table.replace(
    101,
    "Mahesh",
    "Updated"
);
```

This replaces the value only when the old value matches.

---

# 16. Iterating over Hashtable

You can use modern `Map` iteration.

## entrySet()

```java
for (Map.Entry<Integer, String> entry :
        table.entrySet()) {

    System.out.println(
        entry.getKey() + " -> " +
        entry.getValue()
    );
}
```

---

## keySet()

```java
for (Integer key : table.keySet()) {
    System.out.println(key);
}
```

---

## values()

```java
for (String value : table.values()) {
    System.out.println(value);
}
```

---

## forEach()

```java
table.forEach((key, value) ->
    System.out.println(
        key + " -> " + value
    )
);
```

### Important

Hashtable does **not** guarantee sorted or insertion order.

Never rely on the order in which entries are returned.

---

# 17. Enumeration

One of the legacy features associated with Hashtable is `Enumeration`.

Example:

```java
Enumeration<Integer> keys =
        table.keys();

while (keys.hasMoreElements()) {

    Integer key =
            keys.nextElement();

    System.out.println(key);
}
```

For values:

```java
Enumeration<String> values =
        table.elements();

while (values.hasMoreElements()) {

    String value =
            values.nextElement();

    System.out.println(value);
}
```

---

## Enumeration vs Iterator

| Feature | Enumeration | Iterator |
|---|---|---|
| Legacy | Yes | Modern |
| Used with | Older collections | Modern collections |
| Remove support | No | Yes |
| Direction | Forward | Forward |
| Common today | Rare | Common |

`Enumeration` is mainly useful when working with legacy APIs.

---

# 18. Null Keys and Null Values

This is one of the most important differences between Hashtable and HashMap.

Hashtable does **not** allow:

```text
null key
```

or:

```text
null value
```

Example:

```java
table.put(null, "Java");
```

throws:

```text
NullPointerException
```

Similarly:

```java
table.put(101, null);
```

also throws:

```text
NullPointerException
```

### Remember

```text
Hashtable
→ No null key
→ No null value

HashMap
→ One null key allowed
→ Multiple null values allowed
```

---

# 19. Duplicate Keys and Values

## Duplicate Keys

Not allowed.

```java
table.put(101, "Java");
table.put(101, "Spring");
```

Final mapping:

```text
101 -> Spring
```

The new value replaces the old value.

---

## Duplicate Values

Allowed.

```java
table.put(101, "Java");
table.put(102, "Java");
```

Valid.

---

# 20. Hashing

Hashtable uses hashing to determine where an entry should be stored.

Conceptually:

```text
Key
 ↓
hashCode()
 ↓
Hash calculation
 ↓
Bucket/index
 ↓
Entry
```

Example:

```java
table.put("Java", "Programming Language");
```

The key's hash is used to determine an appropriate bucket.

---

# 21. hashCode() and equals()

Hash-based maps rely on:

```text
hashCode()
equals()
```

For two objects:

```java
a.equals(b)
```

to be true, they must have:

```java
a.hashCode() == b.hashCode()
```

But the reverse is not guaranteed.

Two different keys can have the same hash code.

That creates a:

```text
collision
```

---

# 22. How Hashtable Works Internally

Conceptually, Hashtable maintains an array of buckets.

```text
Bucket Array
+-----+-----+-----+-----+
|  0  |  1  |  2  | ... |
+-----+-----+-----+-----+
          |
          +---- Entry
```

When inserting:

```text
key
 ↓
hashCode()
 ↓
bucket index
 ↓
store entry
```

When retrieving:

```text
key
 ↓
hashCode()
 ↓
bucket index
 ↓
search bucket
 ↓
equals()
 ↓
value
```

---

# 23. Buckets and Collisions

A collision occurs when multiple keys map to the same bucket.

For example:

```text
Key A ──┐
        ├──> Bucket 5
Key B ──┘
```

The implementation must store both mappings in that bucket.

Historically, Hashtable's buckets use linked entry structures for collision handling.

---

# 24. Collision Handling

Conceptually:

```text
Bucket
  |
  v
Entry A
  |
  v
Entry B
  |
  v
Entry C
```

When searching:

```text
1. Find bucket using hash
2. Compare stored hash/key
3. Use equals() to identify the correct key
```

Good hash distribution reduces collisions.

---

# 25. Synchronization

Hashtable is synchronized.

Its legacy design synchronizes its individual methods.

Conceptually:

```java
public synchronized V get(Object key) {
    // ...
}
```

This means multiple threads cannot simultaneously execute synchronized Hashtable methods on the same Hashtable instance in the same way they could with an unsynchronized `HashMap`.

However, synchronization of individual operations does **not** automatically make every multi-step sequence atomic.

For example:

```java
if (!table.containsKey(key)) {
    table.put(key, value);
}
```

The entire check-then-act sequence is not automatically one atomic operation merely because the individual methods are synchronized.

---

# 26. Why Hashtable is Considered Legacy

Hashtable predates the modern Java Collections Framework design.

It has several historical characteristics:

- Extends the legacy `Dictionary` class
- Synchronized methods
- Does not allow null keys/values
- Provides legacy `Enumeration`
- Less flexible concurrency model than modern alternatives

Modern code generally prefers:

```text
HashMap
```

for non-concurrent use, and:

```text
ConcurrentHashMap
```

when concurrent access is required.

---

# 27. Hashtable vs HashMap

| Feature | Hashtable | HashMap |
|---|---|---|
| Thread-safe | Yes, synchronized methods | No |
| Null key | No | Yes, one |
| Null values | No | Yes |
| Ordering | No guarantee | No guarantee |
| Basic lookup | O(1) expected | O(1) expected |
| Legacy | Yes | No |
| Enumeration | Yes | No |
| Modern default choice | Usually no | Often yes for non-concurrent use |

### Mental Model

```text
Hashtable
→ Legacy + synchronized

HashMap
→ Modern general-purpose map
```

---

# 28. Hashtable vs ConcurrentHashMap

| Feature | Hashtable | ConcurrentHashMap |
|---|---|---|
| Thread-safe | Yes | Yes |
| Concurrency design | Coarse synchronization | Designed for concurrent access |
| Null key | No | No |
| Null values | No | No |
| Performance under concurrency | Generally less scalable | Generally more scalable |
| Modern concurrent choice | Usually no | Yes |
| Atomic compound operations | Limited | Rich concurrent APIs |

For modern concurrent applications:

```text
ConcurrentHashMap
```

is generally preferred over Hashtable.

---

# 29. Hashtable vs LinkedHashMap

| Feature | Hashtable | LinkedHashMap |
|---|---|---|
| Thread-safe | Yes | No |
| Ordering | No guaranteed order | Insertion/access order |
| Null key | No | Yes |
| Null values | No | Yes |
| Internal structure | Hash table | Hash table + linked ordering |
| Legacy | Yes | No |

Use `LinkedHashMap` when predictable traversal order matters.

---

# 30. Hashtable vs TreeMap

| Feature | Hashtable | TreeMap |
|---|---|---|
| Ordering | No guaranteed order | Sorted |
| Internal structure | Hash table | Red-black tree |
| Basic lookup | O(1) expected | O(log n) |
| Null key | No | Not with natural ordering |
| Null values | No | Yes |
| Thread-safe | Yes | No |
| Range operations | No | Yes |
| Navigation | No sorted navigation | Yes |

---

# 31. Capacity and Load Factor

Hashtable maintains:

```text
capacity
```

and:

```text
load factor
```

The load factor determines when the table should grow.

The default load factor is:

```text
0.75
```

For a table with capacity:

```text
11
```

the approximate threshold is:

```text
11 × 0.75
≈ 8
```

When the table becomes sufficiently full, it is resized.

### Important

The exact resizing behavior differs from `HashMap`, so do not assume Hashtable uses the same capacity growth rules.

---

# 32. Rehashing

When the table becomes too full, Hashtable expands its internal storage and redistributes existing entries.

Conceptually:

```text
Old Table
   ↓
Resize
   ↓
New Table
   ↓
Redistribute entries
```

This operation is more expensive than an individual average lookup.

However, resizing happens periodically rather than on every insertion.

---

# 33. Constructors

Common constructors include:

```java
Hashtable<>();
```

```java
Hashtable<>(int initialCapacity);
```

```java
Hashtable<>(
    int initialCapacity,
    float loadFactor
);
```

```java
Hashtable<>(Map<? extends K, ? extends V> t);
```

Example:

```java
Hashtable<Integer, String> table =
        new Hashtable<>(20, 0.75f);
```

---

# 34. Custom Objects as Keys

You can use custom objects as keys.

Example:

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

        Student other =
                (Student) obj;

        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
```

Then:

```java
Hashtable<Student, String> table =
        new Hashtable<>();
```

### Important

If `equals()` and `hashCode()` are inconsistent, hash-based lookup can behave incorrectly.

---

# 35. Common Use Cases

Because Hashtable is legacy, you generally encounter it in:

### 35.1 Legacy Java Applications

Older applications may already use Hashtable extensively.

---

### 35.2 Legacy APIs

Some old Java APIs expose Hashtable-related types.

---

### 35.3 Interview Questions

Hashtable is frequently discussed to test understanding of:

```text
HashMap
vs
Hashtable
vs
ConcurrentHashMap
```

---

### 35.4 Learning Hash-Based Collections

It provides a useful historical perspective on Java's collection evolution.

---

# 36. Common Mistakes

## Mistake 1 — Thinking Hashtable and HashMap Are Identical

They are both hash-based maps, but their synchronization and null behavior differ.

---

## Mistake 2 — Thinking Hashtable Allows null

It does not allow:

```text
null key
null value
```

---

## Mistake 3 — Thinking Synchronization Makes Everything Atomic

Individual synchronized operations do not automatically make compound operations atomic.

---

## Mistake 4 — Assuming Hashtable Preserves Order

It does not guarantee insertion or sorted order.

---

## Mistake 5 — Using Hashtable for Modern Concurrent Applications Automatically

For modern concurrent workloads, `ConcurrentHashMap` is generally the more appropriate API.

---

## Mistake 6 — Forgetting Hashtable is Legacy

Hashtable is still valid Java, but it is not normally the first choice for new code.

---

## Mistake 7 — Confusing Enumeration with Iterator

`Enumeration` is a legacy traversal mechanism.

Modern code generally uses:

```java
Iterator
```

or enhanced `for` loops.

---

# 37. Interview Quick Revision

### What is Hashtable?

A legacy synchronized hash-based implementation of `Map`.

---

### Does Hashtable allow duplicate keys?

```text
No
```

---

### Does Hashtable allow duplicate values?

```text
Yes
```

---

### Does Hashtable allow null keys?

```text
No
```

---

### Does Hashtable allow null values?

```text
No
```

---

### Is Hashtable thread-safe?

```text
Yes
```

Its methods are synchronized.

---

### Is Hashtable recommended for new concurrent code?

Generally:

```text
No
```

`ConcurrentHashMap` is usually the modern choice.

---

### What is the expected complexity of get()?

```text
O(1)
```

assuming a good hash distribution.

---

### What is the expected complexity of put()?

```text
O(1)
```

amortized/expected, excluding occasional resizing costs.

---

### Does Hashtable maintain insertion order?

```text
No
```

---

### Does Hashtable maintain sorted order?

```text
No
```

---

### What does Hashtable use internally?

A hash table with buckets and collision handling.

---

### What methods are important for hashing?

```text
hashCode()
equals()
```

---

### What happens during a collision?

Multiple entries can map to the same bucket and are distinguished using their keys.

---

### What is the default load factor?

```text
0.75
```

---

### Why is Hashtable considered legacy?

Because it predates the modern Collections Framework design and uses an older synchronization model and legacy APIs such as `Enumeration`.

---

### Hashtable vs HashMap?

```text
Hashtable
→ Synchronized
→ No null keys/values
→ Legacy

HashMap
→ Not synchronized
→ Allows null
→ Modern general-purpose map
```

---

### Hashtable vs ConcurrentHashMap?

```text
Hashtable
→ Legacy synchronization model

ConcurrentHashMap
→ Designed for modern concurrent access
```

---

### Hashtable vs TreeMap?

```text
Hashtable
→ Hash-based
→ Expected O(1)
→ No sorted order

TreeMap
→ Red-black tree
→ O(log n)
→ Sorted keys
```

---

# Final Mental Model

```text
                    Hashtable
                        |
                  Hash-based Map
                        |
             +----------+----------+
             |                     |
        Synchronized          No guaranteed order
             |
       +-----+-----+
       |           |
    No null     Legacy API
    key/value   Enumeration
```

### Hash-Based Map Comparison

```text
HashMap
│
├── Fast expected lookup
├── Not synchronized
├── Allows null key/value
└── Modern general-purpose map

Hashtable
│
├── Fast expected lookup
├── Synchronized methods
├── Does not allow null
└── Legacy

ConcurrentHashMap
│
├── Designed for concurrency
├── High concurrent scalability
├── Does not allow null
└── Modern concurrent map
```

### Interview Shortcut

```text
Need normal Map?
    → HashMap

Need sorted keys?
    → TreeMap

Need predictable insertion/access order?
    → LinkedHashMap

Need modern concurrent Map?
    → ConcurrentHashMap

Encountering old synchronized Map code?
    → Hashtable
```

---

# Progress

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
│   └── INTERVIEW.md   [x]
│
├── 20-Hashtable
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
│
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

> **`20-Hashtable/NOTES.md` completed.**
>
> **Next: `20-Hashtable/PRACTICE.md`**.
