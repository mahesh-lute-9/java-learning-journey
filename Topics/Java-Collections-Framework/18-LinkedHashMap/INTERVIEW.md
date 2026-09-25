# LinkedHashMap — Interview Questions & Answers

> SDE interview preparation for Java `LinkedHashMap`, covering ordering, internal implementation, access order, LRU caching, complexity, and comparisons with other `Map` implementations.

---

## Table of Contents

- [1. What is LinkedHashMap?](#1-what-is-linkedhashmap)
- [2. Why is LinkedHashMap Used?](#2-why-is-linkedhashmap-used)
- [3. What is the Relationship Between HashMap and LinkedHashMap?](#3-what-is-the-relationship-between-hashmap-and-linkedhashmap)
- [4. Does LinkedHashMap Maintain Order?](#4-does-linkedhashmap-maintain-order)
- [5. What is the Default Ordering of LinkedHashMap?](#5-what-is-the-default-ordering-of-linkedhashmap)
- [6. What is Access Order?](#6-what-is-access-order)
- [7. How Do You Enable Access Order?](#7-how-do-you-enable-access-order)
- [8. Insertion Order vs Access Order](#8-insertion-order-vs-access-order)
- [9. Does Updating an Existing Key Change Its Position?](#9-does-updating-an-existing-key-change-its-position)
- [10. Does get() Change Order?](#10-does-get-change-order)
- [11. Is LinkedHashMap Sorted?](#11-is-linkedhashmap-sorted)
- [12. Is LinkedHashMap Thread-Safe?](#12-is-linkedhashmap-thread-safe)
- [13. Does LinkedHashMap Allow null?](#13-does-linkedhashmap-allow-null)
- [14. Are Duplicate Keys Allowed?](#14-are-duplicate-keys-allowed)
- [15. Are Duplicate Values Allowed?](#15-are-duplicate-values-allowed)
- [16. How Does LinkedHashMap Work Internally?](#16-how-does-linkedhashmap-work-internally)
- [17. Why Does LinkedHashMap Use a Doubly Linked List?](#17-why-does-linkedhashmap-use-a-doubly-linked-list)
- [18. How Does Hashing Work in LinkedHashMap?](#18-how-does-hashing-work-in-linkedhashmap)
- [19. How Does get() Work Internally?](#19-how-does-get-work-internally)
- [20. How Does put() Work Internally?](#20-how-does-put-work-internally)
- [21. How Does LinkedHashMap Maintain Insertion Order?](#21-how-does-linkedhashmap-maintain-insertion-order)
- [22. How Does LinkedHashMap Maintain Access Order?](#22-how-does-linkedhashmap-maintain-access-order)
- [23. What is removeEldestEntry()?](#23-what-is-removeeldestentry)
- [24. How is an LRU Cache Implemented?](#24-how-is-an-lru-cache-implemented)
- [25. What is LRU?](#25-what-is-lru)
- [26. Why is LinkedHashMap Useful for LRU Cache?](#26-why-is-linkedhashmap-useful-for-lru-cache)
- [27. What Happens When removeEldestEntry() Returns true?](#27-what-happens-when-removeeldestentry-returns-true)
- [28. What is the Time Complexity of LinkedHashMap?](#28-what-is-the-time-complexity-of-linkedhashmap)
- [29. Why Does LinkedHashMap Use More Memory Than HashMap?](#29-why-does-linkedhashmap-use-more-memory-than-hashmap)
- [30. LinkedHashMap vs HashMap](#30-linkedhashmap-vs-hashmap)
- [31. LinkedHashMap vs TreeMap](#31-linkedhashmap-vs-treemap)
- [32. LinkedHashMap vs Hashtable](#32-linkedhashmap-vs-hashtable)
- [33. LinkedHashMap vs ConcurrentHashMap](#33-linkedhashmap-vs-concurrenthashmap)
- [34. Can LinkedHashMap Be Used for Ordered Frequency Counting?](#34-can-linkedhashmap-be-used-for-ordered-frequency-counting)
- [35. Can LinkedHashMap Be Used for Removing Duplicates While Preserving Order?](#35-can-linkedhashmap-be-used-for-removing-duplicates-while-preserving-order)
- [36. Can a Custom Object Be Used as a Key?](#36-can-a-custom-object-be-used-as-a-key)
- [37. Why Should Keys Be Immutable?](#37-why-should-keys-be-immutable)
- [38. What is entrySet()?](#38-what-is-entryset)
- [39. keySet() vs entrySet()](#39-keyset-vs-entryset)
- [40. Does LinkedHashMap Guarantee the Order of all Operations?](#40-does-linkedhashmap-guarantee-the-order-of-all-operations)
- [41. What Happens When an Existing Entry Is Reinserted?](#41-what-happens-when-an-existing-entry-is-reinserted)
- [42. Does Iteration Cost More Than HashMap?](#42-does-iteration-cost-more-than-hashmap)
- [43. How Would You Design an LRU Cache Using LinkedHashMap?](#43-how-would-you-design-an-lru-cache-using-linkedhashmap)
- [44. What Are Common Mistakes with LinkedHashMap?](#44-what-are-common-mistakes-with-linkedhashmap)
- [45. Rapid-Fire Revision](#45-rapid-fire-revision)

---

# 1. What is LinkedHashMap?

### Answer

`LinkedHashMap` is a `Map` implementation that combines:

```text
Hash-based lookup
+
Predictable iteration order
```

It extends `HashMap` and maintains a linked ordering structure across its entries.

```java
LinkedHashMap<K, V>
```

Example:

```java id="wb6y2j"
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>();

map.put(1, "Java");
map.put(2, "Python");
map.put(3, "C++");
```

With the default configuration, iteration follows insertion order.

---

# 2. Why is LinkedHashMap Used?

### Answer

Use `LinkedHashMap` when you need efficient key-based access similar to `HashMap`, while also needing predictable iteration order.

Common use cases:

- Preserving insertion order
- Ordered output
- Recent-item tracking
- LRU cache implementations
- Ordered frequency maps
- Deterministic traversal

---

# 3. What is the Relationship Between HashMap and LinkedHashMap?

### Answer

`LinkedHashMap` extends `HashMap`.

```text id="fsh1s7"
Map
 |
AbstractMap
 |
HashMap
 |
LinkedHashMap
```

A useful mental model is:

```text id="5a4yj1"
HashMap
├── Hash table
├── Hashing
└── Collision handling

LinkedHashMap
├── Everything inherited from HashMap
└── Linked ordering structure
```

This linked structure is what gives LinkedHashMap its predictable traversal order.

---

# 4. Does LinkedHashMap Maintain Order?

### Answer

Yes.

Unlike `HashMap`, `LinkedHashMap` provides predictable iteration ordering.

The order can be:

```text
Insertion order
```

or:

```text
Access order
```

depending on how the map is constructed.

---

# 5. What is the Default Ordering of LinkedHashMap?

### Answer

The default ordering is:

```text id="ka5q7b"
Insertion order
```

Example:

```java id="f2o6xd"
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>();

map.put(3, "C");
map.put(1, "A");
map.put(2, "B");
```

Iteration:

```text id="j5z0e5"
3 -> C
1 -> A
2 -> B
```

It follows insertion order, not sorted key order.

---

# 6. What is Access Order?

### Answer

Access order means the linked ordering is based on recent access rather than initial insertion.

When an existing entry is accessed by an operation covered by the map's access-order semantics, that entry can move toward the end of the linked order.

This is useful for:

```text
LRU
Recent items
Cache management
```

---

# 7. How Do You Enable Access Order?

### Answer

Use the constructor:

```java id="vmsj99"
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>(16, 0.75f, true);
```

The third argument is:

```java id="7ck0it"
accessOrder
```

So:

```text
false -> insertion order
true  -> access order
```

---

# 8. Insertion Order vs Access Order

| Feature | Insertion Order | Access Order |
|---|---|---|
| Third argument | `false` | `true` |
| Main idea | Order entries were inserted | Order entries by access |
| `get()` effect | No reorder | Can move entry to end |
| Typical use | Ordered data | LRU-style cache |

Example:

```java id="o0fqy0"
new LinkedHashMap<>(16, 0.75f, false);
```

Insertion order.

```java id="2r4f9w"
new LinkedHashMap<>(16, 0.75f, true);
```

Access order.

---

# 9. Does Updating an Existing Key Change Its Position?

### Answer

It depends on the ordering mode.

### In insertion-order mode

```java id="9yd2eb"
map.put(1, "A");
map.put(2, "B");
map.put(1, "Updated");
```

The position of key `1` remains unchanged.

Order:

```text id="z2n4ex"
1
2
```

### In access-order mode

Updating an existing mapping through an operation that counts as an access can move the entry toward the end.

This distinction is important in LRU implementations.

---

# 10. Does get() Change Order?

### Answer

In insertion-order mode:

```text
No
```

In access-order mode:

```text
Yes, an accessed existing entry can move toward the end.
```

Example:

```java id="j0tzub"
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>(16, 0.75f, true);

map.put(1, "A");
map.put(2, "B");
map.put(3, "C");

map.get(1);
```

The order becomes conceptually:

```text id="qox3hz"
2 -> B
3 -> C
1 -> A
```

---

# 11. Is LinkedHashMap Sorted?

### Answer

No.

LinkedHashMap preserves its configured linked iteration order.

It does **not** automatically sort keys.

For sorted keys, use:

```java id="yb67a4"
TreeMap
```

Example:

```text id="2x2hy6"
Inserted:
3, 1, 2

LinkedHashMap:
3, 1, 2

TreeMap:
1, 2, 3
```

---

# 12. Is LinkedHashMap Thread-Safe?

### Answer

No.

`LinkedHashMap` is not synchronized by default.

If multiple threads access and modify it concurrently, appropriate synchronization or another concurrency-oriented design is needed.

---

# 13. Does LinkedHashMap Allow null?

### Answer

Yes.

It allows:

```text id="i8b3o8"
One null key
Multiple null values
```

Example:

```java id="6w8luo"
map.put(null, "Unknown");
map.put("Java", null);
map.put("Python", null);
```

---

# 14. Are Duplicate Keys Allowed?

### Answer

No.

Example:

```java id="3a7s0c"
map.put(1, "Java");
map.put(1, "Python");
```

The second call replaces the value.

Final mapping:

```text id="m3t3lf"
1 -> Python
```

---

# 15. Are Duplicate Values Allowed?

### Answer

Yes.

```java id="4s9pjd"
map.put(1, "Java");
map.put(2, "Java");
```

This is valid.

```text id="2w7m4u"
1 -> Java
2 -> Java
```

---

# 16. How Does LinkedHashMap Work Internally?

### Answer

LinkedHashMap uses:

```text
Hash table
+
Linked ordering structure
```

A simplified view:

```text id="x3y2g0"
                Hash Table
                    |
         +----------+----------+
         |          |          |
      Bucket 0   Bucket 1   Bucket 2
                    |
                   Entry
```

At the same time, entries are linked:

```text id="wr5g4d"
Entry A <-> Entry B <-> Entry C <-> Entry D
```

The hash table provides efficient key lookup.

The linked structure provides predictable iteration.

---

# 17. Why Does LinkedHashMap Use a Doubly Linked List?

### Answer

A doubly linked structure allows each entry to maintain links to both neighboring entries.

Conceptually:

```text id="svh1b2"
       before          after
Entry A <--------> Entry B
```

This supports efficient insertion/removal of entries from the linked ordering structure.

It also makes it possible to maintain:

```text
Head <-> Entry <-> Entry <-> Tail
```

for predictable traversal.

---

# 18. How Does Hashing Work in LinkedHashMap?

### Answer

The hashing side works like `HashMap`.

Conceptually:

```text id="m0j8j8"
Key
 ↓
hashCode()
 ↓
Hash processing
 ↓
Bucket index
 ↓
Entry
```

If multiple entries land in the same bucket, normal hash collision handling applies.

The linked ordering is a separate concern from bucket lookup.

---

# 19. How Does get() Work Internally?

Suppose:

```java id="f4u9by"
map.get(key);
```

Conceptually:

```text id="t2vyot"
key
 ↓
hashCode()
 ↓
bucket
 ↓
find matching entry
 ↓
return value
```

In access-order mode, a successful access to an existing entry can also update the linked ordering:

```text id="5w0tj6"
Find entry
   ↓
Return value
   ↓
Move entry toward tail
```

---

# 20. How Does put() Work Internally?

For:

```java id="ymh7in"
map.put(key, value);
```

conceptually:

```text id="0mka2k"
1. Calculate hash
2. Find bucket
3. Search for matching key
4. If key exists:
      update value
5. Otherwise:
      insert new entry
6. Maintain linked ordering
7. Resize if required
```

LinkedHashMap inherits the hash table behavior of HashMap while maintaining the linked ordering structure.

---

# 21. How Does LinkedHashMap Maintain Insertion Order?

### Answer

When entries are inserted, LinkedHashMap links them in insertion sequence.

Conceptually:

```text id="z9u6l3"
Insert A
    ↓
A

Insert B
    ↓
A <-> B

Insert C
    ↓
A <-> B <-> C
```

Iteration follows this linked structure.

---

# 22. How Does LinkedHashMap Maintain Access Order?

### Answer

With:

```java id="zx4pl7"
accessOrder = true
```

an accessed entry can be detached from its current position and moved toward the end of the linked order.

Example:

```text id="n6n51d"
Before:
A <-> B <-> C

Access B

After:
A <-> C <-> B
```

This makes the tail represent the more recently accessed side of the ordering.

That behavior is useful for LRU caches.

---

# 23. What is removeEldestEntry()?

### Answer

`removeEldestEntry()` is a protected method that can be overridden to automatically remove the eldest entry after an insertion.

Example:

```java id="8kgxln"
LinkedHashMap<Integer, String> cache =
        new LinkedHashMap<>(16, 0.75f, true) {

            @Override
            protected boolean removeEldestEntry(
                    Map.Entry<Integer, String> eldest) {

                return size() > 3;
            }
        };
```

This keeps the size at most `3` after insertions.

---

# 24. How is an LRU Cache Implemented?

### Answer

Use:

```text id="5f5mkc"
LinkedHashMap
+
accessOrder = true
+
removeEldestEntry()
```

Example:

```java id="h1jtge"
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

---

# 25. What is LRU?

### Answer

LRU stands for:

```text id="b6j0oi"
Least Recently Used
```

An LRU cache removes the item that has not been used for the longest time when the cache is full.

Example:

```text id="8x9b4y"
Least Recently Used
        |
        v
      Entry A
      Entry B
      Entry C
        |
        v
Most Recently Used
```

When a new item must be inserted, the least recently used item is evicted.

---

# 26. Why is LinkedHashMap Useful for LRU Cache?

### Answer

Because access-order mode naturally maintains entries based on recent access.

It provides:

```text
Fast key lookup
+
Recent-access ordering
+
Automatic eldest-entry hook
```

Therefore a simple LRU cache can be implemented without manually maintaining a separate linked list and HashMap.

---

# 27. What Happens When removeEldestEntry() Returns true?

### Answer

After an insertion, if:

```java id="i4i2fk"
removeEldestEntry(eldest)
```

returns `true`, the eldest mapping is removed.

For example:

```java id="kjk0tr"
return size() > capacity;
```

means:

```text
Insert
  ↓
Size exceeds capacity?
  ↓
Yes
  ↓
Remove eldest
```

---

# 28. What is the Time Complexity of LinkedHashMap?

### Answer

Typical expected complexity:

| Operation | Expected Complexity |
|---|---:|
| `put()` | O(1) |
| `get()` | O(1) |
| `remove()` | O(1) |
| `containsKey()` | O(1) |
| `containsValue()` | O(n) |
| `size()` | O(1) |
| Iteration | O(n) |

The basic hash-table operations have the same expected complexity characteristics as `HashMap`.

---

# 29. Why Does LinkedHashMap Use More Memory Than HashMap?

### Answer

Because each entry needs additional links for maintaining the linked ordering.

Conceptually:

```text id="l6v22p"
HashMap entry
├── hash
├── key
├── value
└── next

LinkedHashMap entry
├── hash
├── key
├── value
├── next
├── before
└── after
```

Therefore, the predictable-order feature comes with extra memory overhead.

---

# 30. LinkedHashMap vs HashMap

| Feature | HashMap | LinkedHashMap |
|---|---|---|
| Basic lookup | O(1) expected | O(1) expected |
| Iteration order | No guarantee | Predictable |
| Default order | None guaranteed | Insertion |
| Access order | No | Yes |
| Memory usage | Lower | Higher |
| Null key | Yes | Yes |
| Null values | Yes | Yes |
| Thread-safe | No | No |

### Interview Summary

```text id="8n4v5r"
HashMap
→ order does not matter

LinkedHashMap
→ predictable iteration order matters
```

---

# 31. LinkedHashMap vs TreeMap

| Feature | LinkedHashMap | TreeMap |
|---|---|---|
| Ordering | Insertion/access | Sorted by key |
| Basic lookup | O(1) expected | O(log n) |
| Main structure | Hash table + linked order | Red-black tree |
| Range operations | No | Yes |
| Null key | Allowed | Not with natural ordering |
| Main use | Predictable traversal | Sorted/range-based operations |

---

# 32. LinkedHashMap vs Hashtable

| Feature | LinkedHashMap | Hashtable |
|---|---|---|
| Thread-safe | No | Synchronized legacy class |
| Null key | Yes | No |
| Null values | Yes | No |
| Predictable insertion/access ordering | Yes | No equivalent LinkedHashMap-style ordering |
| Modern general use | Common | Legacy |

For concurrent applications, `ConcurrentHashMap` is generally a more modern choice than relying on `Hashtable`.

---

# 33. LinkedHashMap vs ConcurrentHashMap

| Feature | LinkedHashMap | ConcurrentHashMap |
|---|---|---|
| Thread-safe | No | Designed for concurrent access |
| Null key | Yes | No |
| Null values | Yes | No |
| Predictable LinkedHashMap order | Yes | No |
| LRU convenience | Yes | Requires a different design |

These classes solve different problems.

---

# 34. Can LinkedHashMap Be Used for Ordered Frequency Counting?

### Answer

Yes.

For example:

```java id="tqhsz2"
String[] words = {
    "Java",
    "Python",
    "Java",
    "C++",
    "Python"
};

LinkedHashMap<String, Integer> frequency =
        new LinkedHashMap<>();

for (String word : words) {

    frequency.put(
        word,
        frequency.getOrDefault(word, 0) + 1
    );
}
```

Result:

```text id="x8ku8b"
Java   -> 2
Python -> 2
C++    -> 1
```

The order of distinct keys follows their first insertion.

---

# 35. Can LinkedHashMap Be Used for Removing Duplicates While Preserving Order?

### Answer

Yes, although `LinkedHashSet` is often the simpler choice when only unique elements are needed.

For example:

```java id="kfb3mz"
LinkedHashMap<Integer, Boolean> map =
        new LinkedHashMap<>();

for (int x : arr) {
    map.putIfAbsent(x, true);
}
```

Then iterate over:

```java id="t5wr46"
map.keySet()
```

to obtain unique keys in insertion order.

---

# 36. Can a Custom Object Be Used as a Key?

### Answer

Yes.

Example:

```java id="aw9s4g"
LinkedHashMap<Student, String> map =
        new LinkedHashMap<>();
```

The same key rules that apply to `HashMap` apply here.

Custom key classes should correctly implement:

```java id="5f3o79"
equals()
hashCode()
```

and the state relevant to those methods should not be mutated while the object is being used as a key.

---

# 37. Why Should Keys Be Immutable?

### Answer

Because changing a field that participates in `hashCode()`/`equals()` after insertion can make the mapping difficult to locate.

Example:

```java id="vhzd55"
Student s = new Student(101);

map.put(s, "Java");

s.setId(999);
```

If `id` affects the hash code, lookup may fail:

```java id="h6os96"
map.get(s);
```

This is why immutable keys are strongly preferred.

Common examples:

```text id="mtk4d0"
String
Integer
Long
UUID
```

---

# 38. What is entrySet()?

### Answer

`entrySet()` provides a view of the map's key-value mappings.

Example:

```java id="3ip7la"
for (Map.Entry<Integer, String> entry :
        map.entrySet()) {

    System.out.println(
        entry.getKey() + " -> " + entry.getValue()
    );
}
```

This is useful when both key and value are required.

---

# 39. keySet() vs entrySet()

### Answer

Use:

```java id="4lnm6d"
keySet()
```

when only keys are needed.

Use:

```java id="xqcv96"
values()
```

when only values are needed.

Use:

```java id="uh4y74"
entrySet()
```

when both key and value are needed.

Example:

```java id="z6aov8"
for (Map.Entry<Integer, String> entry :
        map.entrySet()) {

    Integer id = entry.getKey();
    String name = entry.getValue();
}
```

---

# 40. Does LinkedHashMap Guarantee the Order of all Operations?

### Answer

No.

The important point is that LinkedHashMap guarantees a **defined iteration order** according to its configured ordering mode.

Do not assume that every Map operation changes ordering.

For interview purposes, remember:

```text id="8bymh8"
Insertion mode
→ insertion order

Access mode
→ accesses covered by access-order semantics can reorder entries
```

The exact access behavior should be understood from the Java API contract rather than assumed from the name of every method.

---

# 41. What Happens When an Existing Entry Is Reinserted?

### Answer

Consider insertion-order mode:

```java id="kjh5ol"
map.put(1, "A");
map.put(2, "B");
map.put(1, "Updated");
```

The existing key is updated, but it does not become a newly inserted entry.

Order remains:

```text id="oqi5lu"
1
2
```

In access-order mode, updating an existing entry through a relevant access operation can affect its position.

---

# 42. Does Iteration Cost More Than HashMap?

### Answer

LinkedHashMap iteration is efficient because it traverses the linked ordering structure.

A useful distinction is:

```text id="5t9m8j"
HashMap iteration
→ based on its table/buckets

LinkedHashMap iteration
→ follows its linked ordering
```

LinkedHashMap iteration is generally proportional to the number of entries.

---

# 43. How Would You Design an LRU Cache Using LinkedHashMap?

### Answer

Use:

```text id="zqqk5g"
1. Extend LinkedHashMap
2. Enable access order
3. Store maximum capacity
4. Override removeEldestEntry()
```

Example:

```java id="9r3r9n"
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

```java id="24i5l6"
LRUCache<Integer, String> cache =
        new LRUCache<>(3);

cache.put(1, "A");
cache.put(2, "B");
cache.put(3, "C");

cache.get(1);

cache.put(4, "D");
```

The least recently used mapping is evicted when the new insertion makes the size exceed the capacity.

---

# 44. What Are Common Mistakes with LinkedHashMap?

## Mistake 1 — Thinking It Sorts Keys

LinkedHashMap maintains its configured iteration order.

It does not sort keys.

---

## Mistake 2 — Forgetting Access Order

This:

```java id="2hnytv"
new LinkedHashMap<>();
```

does not enable access ordering.

Use:

```java id="o3u8c9"
new LinkedHashMap<>(16, 0.75f, true);
```

for access-order behavior.

---

## Mistake 3 — Assuming Updating a Key Always Moves It

In insertion-order mode:

```java id="4cbpyv"
map.put(existingKey, newValue);
```

does not make it a newly inserted entry.

---

## Mistake 4 — Assuming Thread Safety

LinkedHashMap is not thread-safe.

---

## Mistake 5 — Ignoring Memory Overhead

The additional linked structure requires extra memory compared with HashMap.

---

## Mistake 6 — Using TreeMap When Only Predictable Insertion Order Is Required

If you do not need sorted keys, `TreeMap` introduces a different ordering model and typically O(log n) basic operations.

---

# 45. Rapid-Fire Revision

### Q1. What is LinkedHashMap?

```text id="g4ogw8"
A HashMap-based Map implementation
with predictable iteration order.
```

---

### Q2. What is the default ordering?

```text id="tvl2vq"
Insertion order
```

---

### Q3. Can it maintain access order?

```text id="xjbq3i"
Yes
```

---

### Q4. How do you enable access order?

```java id="b0ghv1"
new LinkedHashMap<>(16, 0.75f, true);
```

---

### Q5. Does LinkedHashMap sort keys?

```text id="eqjp9s"
No
```

---

### Q6. Does it allow one null key?

```text id="r9c1tq"
Yes
```

---

### Q7. Does it allow multiple null values?

```text id="pf8cfl"
Yes
```

---

### Q8. Are duplicate keys allowed?

```text id="nm7u34"
No
```

---

### Q9. Are duplicate values allowed?

```text id="2d6x5f"
Yes
```

---

### Q10. Is LinkedHashMap thread-safe?

```text id="o6k34t"
No
```

---

### Q11. What is the expected complexity of get()?

```text id="1y1j1s"
O(1)
```

---

### Q12. What is the expected complexity of put()?

```text id="00wnj7"
O(1)
```

---

### Q13. Why does LinkedHashMap use more memory than HashMap?

```text id="gjk4e9"
It maintains additional links between entries.
```

---

### Q14. What method is commonly overridden for an LRU cache?

```text id="y04rqs"
removeEldestEntry()
```

---

### Q15. What does LRU mean?

```text id="m9jmfg"
Least Recently Used
```

---

### Q16. Why is LinkedHashMap useful for LRU?

```text id="7je5op"
Because access-order mode tracks recent access
and removeEldestEntry() can evict the oldest entry.
```

---

### Q17. Which Map should you use for sorted keys?

```text id="sdtwvt"
TreeMap
```

---

### Q18. Which Map should you use when order does not matter?

```text id="kfw44e"
HashMap
```

---

### Q19. Which Map should you use for predictable insertion order?

```text id="8l6tqw"
LinkedHashMap
```

---

### Q20. Which Map is designed for concurrent access?

```text id="zbsm2k"
ConcurrentHashMap
```

---

# Final Interview Mental Model

```text id="lwj2ob"
                  LinkedHashMap
                        |
              +---------+---------+
              |                   |
         Hash-based           Linked ordering
           storage              structure
              |                   |
          Fast lookup        Predictable order
              |                   |
        hashCode()             Insertion
              |                  OR
            bucket              Access
              |
       Collision handling
```

For LRU:

```text id="1ldhk5"
LinkedHashMap
      |
accessOrder = true
      |
recent access moves toward tail
      |
removeEldestEntry()
      |
capacity exceeded
      |
remove least-recently-used entry
```

### Core Interview Formula

```text id="5y8l16"
HashMap
    +
Linked Ordering
    =
LinkedHashMap
```

And:

```text id="1zpl0f"
LinkedHashMap
    +
accessOrder = true
    +
removeEldestEntry()
    =
Simple LRU Cache
```

---

## Progress

```text id="6jv9dt"
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
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

> **`18-LinkedHashMap` is now complete: `NOTES.md` + `PRACTICE.md` + `INTERVIEW.md`.**
>
> **Next: `19-TreeMap/NOTES.md`**.
