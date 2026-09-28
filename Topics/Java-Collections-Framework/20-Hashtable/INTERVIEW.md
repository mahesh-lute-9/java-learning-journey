# Hashtable — Interview Questions & Answers

> Interview-focused questions covering `Hashtable` fundamentals, internals, limitations, synchronization, and comparisons with modern `Map` implementations.

---

## 📚 Table of Contents

- [1. What is Hashtable?](#1-what-is-hashtable)
- [2. Which interfaces does Hashtable implement?](#2-which-interfaces-does-hashtable-implement)
- [3. Is Hashtable a Collection?](#3-is-hashtable-a-collection)
- [4. Why is Hashtable considered legacy?](#4-why-is-hashtable-considered-legacy)
- [5. Is Hashtable synchronized?](#5-is-hashtable-synchronized)
- [6. Does Hashtable allow null keys?](#6-does-hashtable-allow-null-keys)
- [7. Does Hashtable allow null values?](#7-does-hashtable-allow-null-values)
- [8. Does Hashtable maintain insertion order?](#8-does-hashtable-maintain-insertion-order)
- [9. How does Hashtable work internally?](#9-how-does-hashtable-work-internally)
- [10. What is the time complexity of Hashtable?](#10-what-is-the-time-complexity-of-hashtable)
- [11. What happens during a hash collision?](#11-what-happens-during-a-hash-collision)
- [12. Why are equals() and hashCode() important?](#12-why-are-equals-and-hashcode-important)
- [13. Can duplicate keys exist?](#13-can-duplicate-keys-exist)
- [14. Can duplicate values exist?](#14-can-duplicate-values-exist)
- [15. What happens when the same key is inserted twice?](#15-what-happens-when-the-same-key-is-inserted-twice)
- [16. What does put() return?](#16-what-does-put-return)
- [17. What does get() return when a key is absent?](#17-what-does-get-return-when-a-key-is-absent)
- [18. What is Enumeration in Hashtable?](#18-what-is-enumeration-in-hashtable)
- [19. Hashtable vs HashMap](#19-hashtable-vs-hashmap)
- [20. Hashtable vs ConcurrentHashMap](#20-hashtable-vs-concurrenthashmap)
- [21. Is Hashtable completely thread-safe?](#21-is-hashtable-completely-thread-safe)
- [22. Can Hashtable be used for compound operations safely?](#22-can-hashtable-be-used-for-compound-operations-safely)
- [23. What is the default load factor?](#23-what-is-the-default-load-factor)
- [24. What causes Hashtable resizing?](#24-what-causes-hashtable-resizing)
- [25. Can custom objects be Hashtable keys?](#25-can-custom-objects-be-hashtable-keys)
- [26. What happens if a key is mutable?](#26-what-happens-if-a-key-is-mutable)
- [27. Why might Hashtable be slower than HashMap?](#27-why-might-hashtable-be-slower-than-hashmap)
- [28. What should be used instead of Hashtable?](#28-what-should-be-used-instead-of-hashtable)
- [29. Rapid-Fire Questions](#29-rapid-fire-questions)
- [30. Interview Coding Questions](#30-interview-coding-questions)
- [31. Interview Checklist](#31-interview-checklist)
- [32. Progress](#32-progress)

---

# 1. What is Hashtable?

### Answer

`Hashtable` is a legacy, hash-based implementation of the `Map` interface.

It stores data as:

```text
Key → Value
```

Example:

```java
Hashtable<Integer, String> students = new Hashtable<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
```

It is synchronized and does not permit `null` keys or `null` values.

---

# 2. Which interfaces does Hashtable implement?

Conceptually, its declaration is:

```java
public class Hashtable<K,V>
    extends Dictionary<K,V>
    implements Map<K,V>, Cloneable, Serializable
```

Important points:

- Extends legacy `Dictionary`
- Implements `Map`
- Implements `Cloneable`
- Implements `Serializable`

---

# 3. Is Hashtable a Collection?

### Answer

No.

`Hashtable` implements `Map`, while the `Collection` hierarchy contains interfaces such as:

```text
Collection
├── List
├── Set
└── Queue
```

`Map` is a separate hierarchy:

```text
Map
├── HashMap
├── LinkedHashMap
├── TreeMap
├── Hashtable
└── ConcurrentHashMap
```

---

# 4. Why is Hashtable considered legacy?

### Answer

`Hashtable` was introduced before the modern Java Collections Framework.

It has several legacy characteristics:

- Extends `Dictionary`
- Uses synchronized methods
- Does not allow `null`
- Provides legacy `Enumeration`
- Has generally less flexible concurrency behavior than modern alternatives

For new code, developers usually prefer:

```text
HashMap
```

for ordinary non-concurrent use, or:

```text
ConcurrentHashMap
```

for concurrent use.

---

# 5. Is Hashtable synchronized?

### Answer

Yes.

Its legacy API methods are synchronized, which provides mutual exclusion for individual method calls.

Example:

```java
Hashtable<Integer, String> map =
        new Hashtable<>();

map.put(1, "Java");
```

The synchronization comes with overhead compared with an unsynchronized `HashMap`.

### Important

Synchronized individual methods do **not** automatically make every multi-step sequence atomic.

---

# 6. Does Hashtable allow null keys?

### Answer

No.

This throws `NullPointerException`:

```java
Hashtable<Integer, String> map =
        new Hashtable<>();

map.put(null, "Java");
```

### Remember

```text
Hashtable
❌ null key
```

---

# 7. Does Hashtable allow null values?

### Answer

No.

This also throws `NullPointerException`:

```java
map.put(101, null);
```

### Remember

```text
Hashtable
❌ null key
❌ null value
```

---

# 8. Does Hashtable maintain insertion order?

### Answer

No.

`Hashtable` does not guarantee insertion order.

For example:

```java
map.put(1, "A");
map.put(2, "B");
map.put(3, "C");
```

You should not write code that depends on the iteration order.

If insertion order matters, consider:

```java
LinkedHashMap
```

---

# 9. How does Hashtable work internally?

At a high level:

```text
Key
 ↓
hashCode()
 ↓
Hash calculation
 ↓
Bucket/index
 ↓
Stored entry
```

When retrieving:

```text
Key
 ↓
hashCode()
 ↓
Locate bucket
 ↓
Compare keys
 ↓
Return value
```

The implementation uses an internal hash table and handles collisions within buckets.

---

# 10. What is the time complexity of Hashtable?

With good hash distribution, typical complexity is:

| Operation | Average |
|---|---:|
| `put()` | O(1) |
| `get()` | O(1) |
| `remove()` | O(1) |
| `containsKey()` | O(1) |
| `containsValue()` | O(n) |

Resizing can temporarily require additional work.

### Interview Answer

> Hashtable provides expected O(1) basic key-based operations under good hashing, while operations such as `containsValue()` require scanning values.

---

# 11. What happens during a hash collision?

A collision occurs when multiple keys map to the same bucket.

Conceptually:

```text
Key A ──┐
        ├──> Bucket X
Key B ──┘
```

The implementation must then distinguish between the entries using key equality.

This is why both:

```java
hashCode()
equals()
```

matter.

---

# 12. Why are equals() and hashCode() important?

For hash-based collections:

```text
hashCode()
    ↓
Find candidate bucket
    ↓
equals()
    ↓
Determine key equality
```

The contract requires:

> If two objects are equal according to `equals()`, they must have the same `hashCode()`.

Example:

```java
@Override
public boolean equals(Object obj) {
    // equality logic
}

@Override
public int hashCode() {
    // hash logic
}
```

If the contract is broken, lookups can behave unexpectedly.

---

# 13. Can duplicate keys exist?

### Answer

No.

A `Map` cannot contain duplicate keys.

Example:

```java
map.put(101, "Java");
map.put(101, "Spring Boot");
```

There is still only one key:

```text
101 → Spring Boot
```

---

# 14. Can duplicate values exist?

### Answer

Yes.

Example:

```java
map.put(101, "Java");
map.put(102, "Java");
```

This is valid:

```text
101 → Java
102 → Java
```

---

# 15. What happens when the same key is inserted twice?

The new value replaces the old value.

```java
map.put(101, "Java");
map.put(101, "Spring Boot");
```

Final mapping:

```text
101 → Spring Boot
```

The first value is replaced.

---

# 16. What does put() return?

`put()` returns the previous value associated with the key.

Example:

```java
String oldValue = map.put(101, "Spring Boot");
```

If the key already contained:

```text
101 → Java
```

then:

```text
oldValue = "Java"
```

If there was no previous mapping, `put()` returns `null`.

However, with `Hashtable`, a `null` return does not represent an existing mapping to `null`, because null values are prohibited.

---

# 17. What does get() return when a key is absent?

It returns:

```java
null
```

Example:

```java
System.out.println(map.get(999));
```

Output:

```text
null
```

Since `Hashtable` does not allow null values, `null` unambiguously indicates that no mapping was found.

---

# 18. What is Enumeration in Hashtable?

`Enumeration` is a legacy interface used to traverse elements.

Example:

```java
Enumeration<Integer> keys = map.keys();

while (keys.hasMoreElements()) {
    System.out.println(keys.nextElement());
}
```

`Hashtable` also provides:

```java
elements()
```

for values.

Modern code generally prefers:

```java
entrySet()
keySet()
values()
```

with the enhanced `for` loop or streams.

---

# 19. Hashtable vs HashMap

This is one of the most common interview questions.

| Feature | Hashtable | HashMap |
|---|---|---|
| Legacy | Yes | No |
| Implements Map | Yes | Yes |
| Synchronized methods | Yes | No |
| Null key | ❌ | One |
| Null values | ❌ | Multiple |
| Ordering | Not guaranteed | Not guaranteed |
| Typical modern use | Rare | Common |
| Concurrency | Coarse-grained synchronization | Not thread-safe by itself |

### Interview Summary

> Hashtable is a legacy synchronized Map, while HashMap is the general-purpose unsynchronized Map normally preferred for non-concurrent code.

---

# 20. Hashtable vs ConcurrentHashMap

| Feature | Hashtable | ConcurrentHashMap |
|---|---|---|
| Legacy | Yes | No |
| Thread-safe | Yes | Yes |
| Concurrency design | Legacy synchronization | Designed for concurrent access |
| Null keys | ❌ | ❌ |
| Null values | ❌ | ❌ |
| Modern concurrent choice | Usually no | Usually yes |

### Interview Answer

> For modern concurrent applications, `ConcurrentHashMap` is generally preferred because it is designed specifically for concurrent access and provides better scalability than the coarse-grained synchronization model of `Hashtable`.

---

# 21. Is Hashtable completely thread-safe?

### Answer

It is thread-safe for individual synchronized method calls, but this does not mean arbitrary sequences of operations are automatically atomic.

For example:

```java
if (!map.containsKey(key)) {
    map.put(key, value);
}
```

Another thread could modify the map between:

```java
containsKey()
```

and:

```java
put()
```

So the compound operation is not automatically atomic merely because both methods are synchronized.

---

# 22. Can Hashtable be used for compound operations safely?

### Answer

Not automatically.

Instead of:

```java
if (!map.containsKey(key)) {
    map.put(key, value);
}
```

prefer an appropriate atomic Map operation when available:

```java
map.putIfAbsent(key, value);
```

For concurrent applications, `ConcurrentHashMap` is generally the more appropriate modern choice.

---

# 23. What is the default load factor?

The default load factor of `Hashtable` is:

```text
0.75
```

The load factor controls when the table should resize.

Conceptually:

```text
threshold ≈ capacity × load factor
```

When the number of entries crosses the threshold, the table grows and entries are redistributed.

---

# 24. What causes Hashtable resizing?

When the number of entries exceeds its threshold:

```text
size > threshold
```

the internal table is resized.

This requires redistributing existing entries into the new table.

Therefore, although basic operations are expected to be O(1), resizing can cause an operation to take more time temporarily.

---

# 25. Can custom objects be Hashtable keys?

### Answer

Yes.

Example:

```java
Hashtable<Student, String> students =
        new Hashtable<>();
```

The key class should correctly implement:

```java
equals()
hashCode()
```

Example:

```java
class Student {

    private final int id;

    Student(int id) {
        this.id = id;
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

---

# 26. What happens if a key is mutable?

Mutable keys can cause serious problems.

Suppose the key's fields used by:

```java
hashCode()
equals()
```

change after insertion.

The object may effectively become associated with a different bucket.

Example conceptually:

```text
Insert key
   ↓
hashCode = 100
   ↓
Bucket 4
```

Then the key changes:

```text
hashCode = 200
```

A later lookup may search a different bucket.

### Best Practice

Prefer immutable objects as map keys.

Common examples:

```java
String
Integer
Long
```

---

# 27. Why might Hashtable be slower than HashMap?

Because `Hashtable` synchronizes its legacy methods.

This introduces synchronization overhead even when multiple threads are not involved.

For ordinary single-threaded or externally synchronized use:

```java
HashMap
```

is generally preferred.

For concurrent access:

```java
ConcurrentHashMap
```

is generally preferred.

---

# 28. What should be used instead of Hashtable?

It depends on the requirement.

### Normal Map

Use:

```java
HashMap
```

### Need insertion order

Use:

```java
LinkedHashMap
```

### Need sorted keys

Use:

```java
TreeMap
```

### Need concurrent access

Use:

```java
ConcurrentHashMap
```

### Interview Principle

> Choose the Map implementation based on the required ordering, sorting, concurrency, and performance characteristics rather than automatically using `Hashtable`.

---

# 29. Rapid-Fire Questions

### Q1. Is Hashtable ordered?

**Answer:** No.

### Q2. Does Hashtable allow null keys?

**Answer:** No.

### Q3. Does Hashtable allow null values?

**Answer:** No.

### Q4. Is Hashtable synchronized?

**Answer:** Its legacy methods are synchronized.

### Q5. Is Hashtable a Map?

**Answer:** Yes.

### Q6. Can Hashtable contain duplicate keys?

**Answer:** No.

### Q7. Can Hashtable contain duplicate values?

**Answer:** Yes.

### Q8. What is its average `get()` complexity?

**Answer:** Expected O(1) with good hashing.

### Q9. What is `containsValue()` complexity?

**Answer:** Typically O(n).

### Q10. Does Hashtable guarantee insertion order?

**Answer:** No.

### Q11. What is the default load factor?

**Answer:** 0.75.

### Q12. What is the modern alternative to Hashtable for normal use?

**Answer:** `HashMap`.

### Q13. What is the modern alternative for concurrent access?

**Answer:** `ConcurrentHashMap`.

### Q14. Does Hashtable support Enumeration?

**Answer:** Yes.

### Q15. Can custom objects be keys?

**Answer:** Yes, provided their equality and hashing contract is correctly implemented.

---

# 30. Interview Coding Questions

## Problem 1 — Frequency Counter

Write a program using `Hashtable` to count the frequency of every character in:

```text
programming
```

### Expected Concept

```java
frequency.put(
    ch,
    frequency.getOrDefault(ch, 0) + 1
);
```

---

## Problem 2 — First Non-Repeating Character

Given:

```text
swiss
```

Find the first character that occurs only once.

Expected:

```text
w
```

### Approach

1. Count frequencies.
2. Traverse the original string.
3. Return the first character with frequency `1`.

---

## Problem 3 — Student Lookup

Create:

```java
Hashtable<Integer, Student>
```

Implement:

```java
Student findStudent(int id)
```

The method should return the student associated with the ID.

---

## Problem 4 — Duplicate Detection

Given:

```text
[10, 20, 10, 30, 20, 40]
```

Use a hash-based structure to identify duplicates.

Expected:

```text
10
20
```

---

## Problem 5 — Compare Map Implementations

Write a Java program that demonstrates:

```text
Hashtable
HashMap
ConcurrentHashMap
```

Test:

- null key
- null value
- insertion
- retrieval
- removal
- iteration

Then document the observed differences.

---

# 31. Interview Checklist

Before considering `Hashtable` interview-ready, make sure you can explain:

### Fundamentals

- [ ] What is Hashtable?
- [ ] Why is it a Map?
- [ ] Why is it considered legacy?
- [ ] Why does it extend `Dictionary`?
- [ ] Is it synchronized?

### Null Handling

- [ ] Why are null keys prohibited?
- [ ] Why are null values prohibited?
- [ ] How does this differ from HashMap?

### Internals

- [ ] How does hashing work?
- [ ] What is a bucket?
- [ ] What is a collision?
- [ ] Why are `equals()` and `hashCode()` important?
- [ ] What causes resizing?
- [ ] What is the default load factor?

### Complexity

- [ ] Expected O(1) `put()`
- [ ] Expected O(1) `get()`
- [ ] Expected O(1) `remove()`
- [ ] O(n) `containsValue()`

### Concurrency

- [ ] What does synchronized mean here?
- [ ] Why aren't compound operations automatically atomic?
- [ ] Why is ConcurrentHashMap generally preferred for modern concurrent applications?

### Comparisons

- [ ] Hashtable vs HashMap
- [ ] Hashtable vs ConcurrentHashMap
- [ ] Hashtable vs LinkedHashMap
- [ ] Hashtable vs TreeMap

### Practical

- [ ] Frequency counter
- [ ] Duplicate detection
- [ ] Custom object keys
- [ ] Student directory

---

# 32. Progress

```text
Hashtable Interview Preparation
│
├── Fundamentals
│   ├── Definition              [x]
│   ├── Map relationship        [x]
│   ├── Legacy status           [x]
│   └── Synchronization        [x]
│
├── Null Handling
│   ├── Null keys               [x]
│   └── Null values             [x]
│
├── Internals
│   ├── Hashing                 [x]
│   ├── Buckets                 [x]
│   ├── Collisions              [x]
│   ├── equals/hashCode         [x]
│   └── Resizing                [x]
│
├── Complexity
│   ├── put()                   [x]
│   ├── get()                   [x]
│   ├── remove()                [x]
│   └── containsValue()         [x]
│
├── Concurrency
│   ├── Synchronized methods    [x]
│   ├── Compound operations     [x]
│   └── ConcurrentHashMap       [x]
│
├── Comparisons
│   ├── HashMap                 [x]
│   ├── ConcurrentHashMap       [x]
│   ├── LinkedHashMap           [x]
│   └── TreeMap                 [x]
│
└── Coding
    ├── Frequency Counter      [x]
    ├── Duplicate Detection    [x]
    ├── Custom Keys             [x]
    └── Student Lookup          [x]
```

---

## 🎯 Interview One-Liner

> **Hashtable is a legacy, synchronized, hash-based implementation of `Map` that does not allow null keys or values and provides expected O(1) basic operations; modern applications generally prefer `HashMap` or `ConcurrentHashMap` depending on concurrency requirements.**

---

## 🧠 Remember This

```text
Hashtable
     │
     ├── Legacy
     ├── Implements Map
     ├── Synchronized methods
     ├── No null key
     ├── No null value
     ├── No guaranteed order
     ├── Hash-based
     ├── Expected O(1) basic operations
     └── Usually replaced by
           │
           ├── HashMap
           └── ConcurrentHashMap
```
