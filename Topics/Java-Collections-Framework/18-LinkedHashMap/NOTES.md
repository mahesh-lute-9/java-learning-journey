# LinkedHashMap — Java Collections Framework

> A practical and interview-ready guide to `LinkedHashMap`, covering insertion order, access order, internal structure, iteration, LRU-style caching, and comparison with `HashMap` and `TreeMap`.

---

## Table of Contents

- [1. What is LinkedHashMap?](#1-what-is-linkedhashmap)
- [2. Why LinkedHashMap is Important](#2-why-linkedhashmap-is-important)
- [3. LinkedHashMap Hierarchy](#3-linkedhashmap-hierarchy)
- [4. Key Features](#4-key-features)
- [5. Creating LinkedHashMap](#5-creating-linkedhashmap)
- [6. Basic Operations](#6-basic-operations)
- [7. put()](#7-put)
- [8. get()](#8-get)
- [9. remove()](#9-remove)
- [10. Iteration Order](#10-iteration-order)
- [11. Insertion Order](#11-insertion-order)
- [12. Access Order](#12-access-order)
- [13. Insertion Order vs Access Order](#13-insertion-order-vs-access-order)
- [14. LinkedHashMap Constructors](#14-linkedhashmap-constructors)
- [15. removeEldestEntry()](#15-removeeldestentry)
- [16. Building an LRU Cache](#16-building-an-lru-cache)
- [17. keySet(), values(), entrySet()](#17-keyset-values-entryset)
- [18. Iterating Through LinkedHashMap](#18-iterating-through-linkedhashmap)
- [19. forEach()](#19-foreach)
- [20. Null Keys and Values](#20-null-keys-and-values)
- [21. Duplicate Keys and Values](#21-duplicate-keys-and-values)
- [22. How LinkedHashMap Works Internally](#22-how-linkedhashmap-works-internally)
- [23. LinkedHashMap and HashMap Relationship](#23-linkedhashmap-and-hashmap-relationship)
- [24. Doubly Linked List](#24-doubly-linked-list)
- [25. Hashing + Linked List](#25-hashing--linked-list)
- [26. Complexity](#26-complexity)
- [27. Memory Overhead](#27-memory-overhead)
- [28. LinkedHashMap vs HashMap](#28-linkedhashmap-vs-hashmap)
- [29. LinkedHashMap vs TreeMap](#29-linkedhashmap-vs-treemap)
- [30. LinkedHashMap vs Hashtable](#30-linkedhashmap-vs-hashtable)
- [31. LinkedHashMap vs ConcurrentHashMap](#31-linkedhashmap-vs-concurrenthashmap)
- [32. Common Use Cases](#32-common-use-cases)
- [33. Common Mistakes](#33-common-mistakes)
- [34. Interview Quick Revision](#34-interview-quick-revision)
- [35. Progress](#35-progress)

---

# 1. What is LinkedHashMap?

`LinkedHashMap` is a `Map` implementation that combines:

- Hash-table-based lookup
- A linked structure for predictable iteration order

It extends `HashMap`.

```java
LinkedHashMap<K, V>
```

Example:

```java
LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

map.put(101, "Mahesh");
map.put(102, "Rahul");
map.put(103, "Amit");
```

When iterated, the entries appear in their defined linked order.

With the default constructor, that order is **insertion order**.

---

# 2. Why LinkedHashMap is Important

`LinkedHashMap` is useful when you need:

> HashMap-like key lookup + predictable iteration order.

Common use cases:

- Maintaining insertion order
- Ordered results
- Caching
- LRU cache implementations
- Preserving input order
- Configuration data
- Serialization-related workflows
- Recent-item tracking
- Deterministic iteration

Example:

```java
LinkedHashMap<String, Integer> marks = new LinkedHashMap<>();

marks.put("DSA", 95);
marks.put("DBMS", 90);
marks.put("Java", 98);
```

Iteration follows:

```text
DSA
DBMS
Java
```

---

# 3. LinkedHashMap Hierarchy

```text
Map
 |
AbstractMap
 |
HashMap
 |
LinkedHashMap
```

Conceptually:

```text
Map<K,V>
    ↑
AbstractMap<K,V>
    ↑
HashMap<K,V>
    ↑
LinkedHashMap<K,V>
```

`LinkedHashMap` inherits the hashing-based behavior of `HashMap` and adds a linked ordering structure.

---

# 4. Key Features

| Feature | LinkedHashMap |
|---|---|
| Stores | Key-value pairs |
| Duplicate keys | No |
| Duplicate values | Yes |
| `null` key | One allowed |
| `null` values | Multiple allowed |
| Default ordering | Insertion order |
| Optional ordering | Access order |
| Average basic lookup | O(1) expected |
| Thread-safe | No |
| Sorted automatically | No |
| Extends | `HashMap` |

---

# 5. Creating LinkedHashMap

## 5.1 Default

```java
LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
```

By default:

```text
Insertion Order
```

is maintained.

---

## 5.2 Initial Capacity

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>(32);
```

---

## 5.3 Capacity and Load Factor

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>(32, 0.75f);
```

---

## 5.4 Access Order

A special constructor allows access-order iteration:

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>(
            16,
            0.75f,
            true
        );
```

The third argument:

```java
true
```

means:

```text
accessOrder = true
```

---

## 5.5 Copying Another Map

```java
Map<Integer, String> source = new HashMap<>();

source.put(1, "Java");
source.put(2, "Python");

LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>(source);
```

The resulting linked map follows the iteration order of the source map at the time it is copied.

---

# 6. Basic Operations

The basic Map operations are familiar:

```java
map.put(key, value);
map.get(key);
map.remove(key);
map.containsKey(key);
map.containsValue(value);
map.size();
map.isEmpty();
map.clear();
```

Example:

```java
LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

map.put(1, "Java");
map.put(2, "Python");

System.out.println(map.get(1));

map.remove(2);
```

---

# 7. put()

Adds or updates a key-value mapping.

```java
LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

map.put(1, "Java");
map.put(2, "Python");
map.put(3, "C++");
```

Iteration:

```text
1 -> Java
2 -> Python
3 -> C++
```

If an existing key is updated:

```java
map.put(2, "Spring Boot");
```

the value changes.

With normal insertion-order mode, replacing the value for an existing key does **not** make it a newly inserted entry.

---

# 8. get()

Retrieves a value by key.

```java
String language = map.get(1);
```

Example:

```java
System.out.println(map.get(1));
```

Output:

```text
Java
```

In access-order mode, a successful access can also affect the iteration order.

This is important for LRU-style caching.

---

# 9. remove()

Removes a mapping by key.

```java
map.remove(2);
```

The removed entry is no longer part of the map.

`remove()` returns the previous value.

```java
String removed = map.remove(2);

System.out.println(removed);
```

---

# 10. Iteration Order

The key difference between `HashMap` and `LinkedHashMap` is predictable iteration order.

Example:

```java
LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

map.put(3, "C");
map.put(1, "A");
map.put(2, "B");
```

Iteration:

```text
3 -> C
1 -> A
2 -> B
```

The entries are not sorted by key.

They follow the linked ordering.

---

# 11. Insertion Order

The default constructor uses insertion-order iteration.

```java
LinkedHashMap<String, Integer> map =
        new LinkedHashMap<>();

map.put("Java", 1);
map.put("Python", 2);
map.put("C++", 3);
```

Iteration:

```text
Java
Python
C++
```

If you update an existing key:

```java
map.put("Python", 20);
```

the position of `"Python"` remains where it was.

Result:

```text
Java
Python
C++
```

---

# 12. Access Order

`LinkedHashMap` can also maintain entries according to access order.

Create it using:

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>(16, 0.75f, true);
```

The third parameter enables:

```text
accessOrder = true
```

Now accessing an entry can move it toward the end of the iteration order.

Example:

```java
map.put(1, "A");
map.put(2, "B");
map.put(3, "C");
```

Initial order:

```text
1 -> A
2 -> B
3 -> C
```

Now:

```java
map.get(1);
```

The access order becomes:

```text
2 -> B
3 -> C
1 -> A
```

This behavior is especially useful for LRU caches.

---

# 13. Insertion Order vs Access Order

| Behavior | Insertion Order | Access Order |
|---|---|---|
| Constructor flag | `false` | `true` |
| New entries | Added at end | Added at end |
| Existing key updated | Position normally unchanged | Can move to end |
| Successful `get()` | Position unchanged | Can move to end |
| Useful for | Ordered data | LRU-style caching |

Example:

```java
new LinkedHashMap<>(16, 0.75f, false);
```

means insertion order.

```java
new LinkedHashMap<>(16, 0.75f, true);
```

means access order.

---

# 14. LinkedHashMap Constructors

Common constructors include:

```java
LinkedHashMap()
```

```java
LinkedHashMap(int initialCapacity)
```

```java
LinkedHashMap(int initialCapacity, float loadFactor)
```

```java
LinkedHashMap(
    int initialCapacity,
    float loadFactor,
    boolean accessOrder
)
```

```java
LinkedHashMap(
    Map<? extends K, ? extends V> m
)
```

The most important constructor for interviews is:

```java
new LinkedHashMap<>(16, 0.75f, true);
```

because it enables access-order behavior.

---

# 15. removeEldestEntry()

`LinkedHashMap` provides:

```java
protected boolean removeEldestEntry(
    Map.Entry<K,V> eldest
)
```

It can be overridden to automatically remove the eldest entry after an insertion.

Example:

```java
LinkedHashMap<Integer, String> cache =
        new LinkedHashMap<>(16, 0.75f, true) {

            @Override
            protected boolean removeEldestEntry(
                    Map.Entry<Integer, String> eldest) {

                return size() > 3;
            }
        };
```

Now the map can maintain a maximum size of `3`.

---

# 16. Building an LRU Cache

LRU means:

> Least Recently Used

An LRU cache removes the entry that has been unused for the longest time.

`LinkedHashMap` is well suited for a simple LRU cache because it supports:

```text
Access order
+
removeEldestEntry()
```

Example:

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

Usage:

```java
LRUCache<Integer, String> cache =
        new LRUCache<>(3);

cache.put(1, "A");
cache.put(2, "B");
cache.put(3, "C");

cache.get(1);

cache.put(4, "D");
```

After accessing `1`, the least recently used entry is `2`.

Therefore:

```text
1 -> A
3 -> C
4 -> D
```

is the resulting set of entries.

---

# 17. keySet(), values(), entrySet()

Like `HashMap`, `LinkedHashMap` provides:

```java
map.keySet();
map.values();
map.entrySet();
```

Example:

```java
for (Integer key : map.keySet()) {
    System.out.println(key);
}
```

Values:

```java
for (String value : map.values()) {
    System.out.println(value);
}
```

Both key and value:

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(
        entry.getKey() + " -> " + entry.getValue()
    );
}
```

The iteration order of these views follows the map's defined iteration order.

---

# 18. Iterating Through LinkedHashMap

## keySet()

```java
for (Integer key : map.keySet()) {
    System.out.println(key);
}
```

---

## values()

```java
for (String value : map.values()) {
    System.out.println(value);
}
```

---

## entrySet()

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(
        entry.getKey() + " -> " + entry.getValue()
    );
}
```

---

## Iterator

```java
Iterator<Map.Entry<Integer, String>> iterator =
        map.entrySet().iterator();

while (iterator.hasNext()) {

    Map.Entry<Integer, String> entry =
            iterator.next();

    System.out.println(
        entry.getKey() + " -> " + entry.getValue()
    );
}
```

---

# 19. forEach()

You can use `forEach()` with a lambda:

```java
map.forEach((key, value) -> {
    System.out.println(key + " -> " + value);
});
```

The traversal follows the LinkedHashMap's iteration order.

---

# 20. Null Keys and Values

`LinkedHashMap` supports:

- One `null` key
- Multiple `null` values

Example:

```java
LinkedHashMap<String, String> map =
        new LinkedHashMap<>();

map.put(null, "Unknown");
map.put("Java", null);
map.put("Python", null);
```

Valid.

---

# 21. Duplicate Keys and Values

## Duplicate Keys

Not allowed.

```java
map.put(1, "Java");
map.put(1, "Python");
```

Final mapping:

```text
1 -> Python
```

---

## Duplicate Values

Allowed.

```java
map.put(1, "Java");
map.put(2, "Java");
```

Valid.

```text
1 -> Java
2 -> Java
```

---

# 22. How LinkedHashMap Works Internally

`LinkedHashMap` extends `HashMap`.

It uses the hash-table mechanism inherited from `HashMap` and additionally maintains a doubly linked list across entries.

Simplified:

```text
             Hash Table
                 |
       +---------+---------+
       |         |         |
    Bucket 0  Bucket 1  Bucket 2
                 |
                Node
```

The entries are also linked:

```text
HEAD
 |
 v
Node1 <-> Node2 <-> Node3 <-> Node4
                                      |
                                     TAIL
```

This linked structure maintains iteration order.

---

# 23. LinkedHashMap and HashMap Relationship

A useful mental model:

```text
HashMap
   |
   +-- Hash table
   |
   +-- Bucket management
   |
   +-- Hashing
   |
   +-- Collision handling

LinkedHashMap
   |
   +-- Everything from HashMap
   |
   +-- Doubly linked ordering
   |
   +-- Insertion/access order
```

Therefore:

> LinkedHashMap provides predictable iteration order at the cost of additional linked-list bookkeeping.

---

# 24. Doubly Linked List

A doubly linked structure allows entries to point in both directions.

Conceptually:

```text
null
  ^
  |
HEAD
  |
  v
+------+    +------+    +------+
| Node |<-->| Node |<-->| Node |
+------+    +------+    +------+
                                  |
                                  v
                                 TAIL
```

Each entry maintains links to neighboring entries.

This allows LinkedHashMap to efficiently maintain its iteration ordering.

---

# 25. Hashing + Linked List

LinkedHashMap combines two ideas:

```text
Hashing
+
Linked ordering
```

Hashing provides efficient lookup:

```text
key
 ↓
hash
 ↓
bucket
 ↓
entry
```

The linked structure provides predictable traversal:

```text
Entry A
   ↓
Entry B
   ↓
Entry C
   ↓
Entry D
```

This combination is the main reason to use LinkedHashMap instead of HashMap when iteration order matters.

---

# 26. Complexity

Expected complexity for basic operations is generally:

| Operation | Expected Complexity |
|---|---:|
| `put()` | O(1) |
| `get()` | O(1) |
| `remove()` | O(1) |
| `containsKey()` | O(1) |
| `containsValue()` | O(n) |
| `size()` | O(1) |
| Iteration | O(n) |
| `clear()` | O(n) |

Access-order operations may additionally update the linked ordering when entries are accessed.

The same general hashing/collision considerations that apply to `HashMap` also apply to `LinkedHashMap`.

---

# 27. Memory Overhead

Compared with `HashMap`, `LinkedHashMap` requires additional memory to maintain ordering links between entries.

Conceptually:

```text
HashMap entry:
    key
    value
    hash
    next

LinkedHashMap entry additionally maintains:
    before
    after
```

Therefore:

```text
HashMap
    ↓
Less ordering overhead

LinkedHashMap
    ↓
Extra links
    ↓
Predictable ordering
```

This is the trade-off for maintaining iteration order.

---

# 28. LinkedHashMap vs HashMap

| Feature | HashMap | LinkedHashMap |
|---|---|---|
| Basic lookup | O(1) expected | O(1) expected |
| Ordering | No guarantee | Predictable |
| Default iteration | No guaranteed order | Insertion order |
| Access-order mode | No | Yes |
| LRU implementation | Not directly suited | Well suited |
| Memory overhead | Lower | Higher |
| Null key | Yes | Yes |
| Null values | Yes | Yes |
| Thread-safe | No | No |

### Rule of Thumb

Use:

```text
HashMap
```

when order does not matter.

Use:

```text
LinkedHashMap
```

when predictable iteration order matters.

---

# 29. LinkedHashMap vs TreeMap

| Feature | LinkedHashMap | TreeMap |
|---|---|---|
| Ordering | Insertion/access order | Sorted key order |
| Basic lookup | O(1) expected | O(log n) |
| Internal structure | Hash table + linked ordering | Red-black tree |
| Range operations | No | Yes |
| Null key | Allowed | Not with natural ordering |
| Main purpose | Predictable traversal | Sorted/range-based traversal |

Example:

If you want:

```text
Insertion:
C, A, B
```

to remain:

```text
C, A, B
```

use `LinkedHashMap`.

If you want:

```text
A, B, C
```

automatically sorted by key, use `TreeMap`.

---

# 30. LinkedHashMap vs Hashtable

| Feature | LinkedHashMap | Hashtable |
|---|---|---|
| Ordering | Predictable | No guaranteed insertion-order behavior |
| Thread-safe | No | Synchronized legacy class |
| Null key | Yes | No |
| Null values | Yes | No |
| Modern general-purpose choice | Yes | Usually no |

For concurrent applications, consider `ConcurrentHashMap` rather than using `Hashtable` simply for synchronization.

---

# 31. LinkedHashMap vs ConcurrentHashMap

| Feature | LinkedHashMap | ConcurrentHashMap |
|---|---|---|
| Thread-safe | No | Designed for concurrent access |
| Null key | Yes | No |
| Null values | Yes | No |
| Predictable insertion/access order | Yes | Not the same ordering model |
| LRU-style implementation | Convenient | Requires different design |

Choose based on the actual requirement:

```text
Ordering required?
    ↓
LinkedHashMap

Concurrent access required?
    ↓
ConcurrentHashMap
```

These are not interchangeable.

---

# 32. Common Use Cases

## 32.1 Preserve Insertion Order

Useful when data should be displayed in the same order in which it was inserted.

```java
LinkedHashMap<String, Integer> data =
        new LinkedHashMap<>();
```

---

## 32.2 LRU Cache

Use:

```java
accessOrder = true
```

with:

```java
removeEldestEntry()
```

---

## 32.3 Ordered Frequency Results

Suppose you want to count elements while preserving the order in which distinct elements first appeared.

```java
LinkedHashMap<Integer, Integer> frequency =
        new LinkedHashMap<>();
```

---

## 32.4 Deterministic Iteration

Useful when reproducible traversal is desirable.

For example:

```text
Input
 ↓
Process
 ↓
LinkedHashMap
 ↓
Predictable output
```

---

## 32.5 Recent Items

Access-order mode can be useful for tracking recently accessed items.

Conceptually:

```text
Least recently used
        ↓
     Entry A
     Entry B
     Entry C
        ↓
Most recently used
```

---

# 33. Common Mistakes

## Mistake 1 — Thinking LinkedHashMap Sorts Keys

It does not.

```java
map.put(3, "C");
map.put(1, "A");
map.put(2, "B");
```

does not produce:

```text
1
2
3
```

It maintains its configured linked order.

For sorted keys, use:

```text
TreeMap
```

---

## Mistake 2 — Thinking Existing Keys Move in Insertion Order

In insertion-order mode:

```java
map.put(1, "A");
map.put(2, "B");
map.put(1, "Updated");
```

The position of key `1` does not move merely because its value was replaced.

---

## Mistake 3 — Confusing Access Order with Insertion Order

This:

```java
new LinkedHashMap<>(16, 0.75f, true);
```

enables access-order behavior.

This:

```java
new LinkedHashMap<>();
```

uses insertion-order behavior.

---

## Mistake 4 — Assuming Access Order Means Every Operation Is an Access

Access-order behavior has specific Map operations and implementation-defined details around which accesses affect order.

For common interview-level usage, remember:

```text
get()
```

can move an accessed existing entry to the end in access-order mode.

Do not assume every operation changes order.

---

## Mistake 5 — Forgetting Extra Memory Overhead

LinkedHashMap maintains additional links between entries.

So it generally consumes more memory than HashMap.

---

## Mistake 6 — Assuming Thread Safety

`LinkedHashMap` is not thread-safe.

If multiple threads access and modify it concurrently, appropriate synchronization or a different concurrent design is required.

---

# 34. Interview Quick Revision

### What is LinkedHashMap?

A `HashMap` subclass that maintains a predictable iteration order using a linked structure.

---

### What is the default ordering?

```text
Insertion order
```

---

### Can LinkedHashMap maintain access order?

Yes.

```java
new LinkedHashMap<>(16, 0.75f, true);
```

---

### What does the third constructor argument mean?

```java
accessOrder
```

`true` enables access-order behavior.

---

### Does LinkedHashMap sort keys?

No.

Use `TreeMap` for sorted keys.

---

### Does LinkedHashMap allow null?

Yes.

```text
One null key
Multiple null values
```

---

### Does LinkedHashMap allow duplicate keys?

No.

An existing key's value is replaced.

---

### Does LinkedHashMap allow duplicate values?

Yes.

---

### Is LinkedHashMap thread-safe?

No.

---

### What is the expected complexity of get()?

```text
O(1)
```

assuming normal hash distribution.

---

### Why is LinkedHashMap slower/larger than HashMap in some cases?

Because it maintains additional links to preserve ordering.

---

### How does LinkedHashMap maintain order?

It maintains a doubly linked ordering structure across entries.

---

### Can LinkedHashMap be used for an LRU cache?

Yes.

A common pattern is:

```java
new LinkedHashMap<>(16, 0.75f, true)
```

combined with:

```java
removeEldestEntry()
```

---

### What is `removeEldestEntry()`?

A protected hook that can be overridden to automatically remove the eldest entry after insertion.

---

### HashMap or LinkedHashMap?

```text
Order irrelevant
    → HashMap

Predictable iteration order
    → LinkedHashMap
```

---

### LinkedHashMap or TreeMap?

```text
Insertion/access order
    → LinkedHashMap

Sorted key order
    → TreeMap
```

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
│   └── INTERVIEW.md   [x]
│
├── 18-LinkedHashMap
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
│
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

> **`18-LinkedHashMap/NOTES.md` completed.**
>
> **Next: `18-LinkedHashMap/PRACTICE.md`**.
