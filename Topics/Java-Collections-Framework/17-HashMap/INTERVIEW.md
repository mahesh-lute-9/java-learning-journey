# HashMap — Interview Questions & Answers

> SDE interview preparation for Java `HashMap`, covering fundamentals, internal implementation, hashing, collisions, resizing, `equals()`/`hashCode()`, performance, and practical DSA questions.

---

## Table of Contents

- [1. What is HashMap?](#1-what-is-hashmap)
- [2. Why is HashMap Used?](#2-why-is-hashmap-used)
- [3. How Does HashMap Store Data?](#3-how-does-hashmap-store-data)
- [4. Is HashMap a Collection?](#4-is-hashmap-a-collection)
- [5. Does HashMap Allow Duplicate Keys?](#5-does-hashmap-allow-duplicate-keys)
- [6. Does HashMap Allow Duplicate Values?](#6-does-hashmap-allow-duplicate-values)
- [7. Does HashMap Allow null?](#7-does-hashmap-allow-null)
- [8. Does HashMap Maintain Insertion Order?](#8-does-hashmap-maintain-insertion-order)
- [9. Is HashMap Thread-Safe?](#9-is-hashmap-thread-safe)
- [10. What is the Time Complexity of HashMap Operations?](#10-what-is-the-time-complexity-of-hashmap-operations)
- [11. How Does get() Work Internally?](#11-how-does-get-work-internally)
- [12. How Does put() Work Internally?](#12-how-does-put-work-internally)
- [13. What is Hashing?](#13-what-is-hashing)
- [14. What is hashCode()?](#14-what-is-hashcode)
- [15. What is equals()?](#15-what-is-equals)
- [16. Why Are equals() and hashCode() Important?](#16-why-are-equals-and-hashcode-important)
- [17. What is a Hash Collision?](#17-what-is-a-hash-collision)
- [18. How Does HashMap Handle Collisions?](#18-how-does-hashmap-handle-collisions)
- [19. What are Tree Bins?](#19-what-are-tree-bins)
- [20. What is the Default Capacity?](#20-what-is-the-default-capacity)
- [21. What is Load Factor?](#21-what-is-load-factor)
- [22. What is the Default Load Factor?](#22-what-is-the-default-load-factor)
- [23. What is Threshold?](#23-what-is-threshold)
- [24. What Happens During Resizing?](#24-what-happens-during-resizing)
- [25. Why is HashMap Capacity Usually a Power of Two?](#25-why-is-hashmap-capacity-usually-a-power-of-two)
- [26. What Changed in Java 8 HashMap?](#26-what-changed-in-java-8-hashmap)
- [27. What Happens When Two Keys Have the Same Hash?](#27-what-happens-when-two-keys-have-the-same-hash)
- [28. Can Different Objects Have the Same hashCode()?](#28-can-different-objects-have-the-same-hashcode)
- [29. Can Equal Objects Have Different hashCode()?](#29-can-equal-objects-have-different-hashcode)
- [30. Why Should HashMap Keys Be Immutable?](#30-why-should-hashmap-keys-be-immutable)
- [31. What Happens If equals() is Overridden but hashCode() Is Not?](#31-what-happens-if-equals-is-overridden-but-hashcode-is-not)
- [32. What is entrySet()?](#32-what-is-entryset)
- [33. keySet() vs entrySet()](#33-keyset-vs-entryset)
- [34. containsKey() vs containsValue()](#34-containskey-vs-containsvalue)
- [35. get() vs containsKey()](#35-get-vs-containskey)
- [36. put() Return Value](#36-put-return-value)
- [37. putIfAbsent() vs put()](#37-putifabsent-vs-put)
- [38. getOrDefault()](#38-getordefault)
- [39. computeIfAbsent()](#39-computeifabsent)
- [40. HashMap vs Hashtable](#40-hashmap-vs-hashtable)
- [41. HashMap vs LinkedHashMap](#41-hashmap-vs-linkedhashmap)
- [42. HashMap vs TreeMap](#42-hashmap-vs-treemap)
- [43. HashMap vs ConcurrentHashMap](#43-hashmap-vs-concurrenthashmap)
- [44. How Do You Count Frequencies Using HashMap?](#44-how-do-you-count-frequencies-using-hashmap)
- [45. How Would You Solve Two Sum Using HashMap?](#45-how-would-you-solve-two-sum-using-hashmap)
- [46. How Can HashMap Be Used for Grouping?](#46-how-can-hashmap-be-used-for-grouping)
- [47. Can a Custom Object Be Used as a HashMap Key?](#47-can-a-custom-object-be-used-as-a-hashmap-key)
- [48. What Happens If a Mutable Key Is Changed?](#48-what-happens-if-a-mutable-key-is-changed)
- [49. What is the Difference Between HashMap and HashSet Internally?](#49-what-is-the-difference-between-hashmap-and-hashset-internally)
- [50. How Would You Design a HashMap?](#50-how-would-you-design-a-hashmap)
- [51. Rapid-Fire Revision](#51-rapid-fire-revision)

---

# 1. What is HashMap?

### Answer

`HashMap` is a Java implementation of the `Map` interface that stores data as key-value pairs.

```java
HashMap<K, V>
```

Example:

```java
HashMap<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
```

It provides expected O(1) time for basic operations such as:

```text
put()
get()
remove()
containsKey()
```

assuming good hash distribution.

---

# 2. Why is HashMap Used?

### Answer

HashMap is used when we need efficient lookup based on a key.

Common use cases:

- Frequency counting
- Caching
- Fast lookup
- Duplicate detection
- Grouping
- Memoization
- ID-to-object mapping
- DSA problems
- Backend data processing

Example:

```java
HashMap<Integer, Student> students = new HashMap<>();

students.put(101, student);
```

Now a student can be retrieved by ID.

---

# 3. How Does HashMap Store Data?

### Answer

HashMap stores data as:

```text
Key -> Value
```

Internally, it maintains a table of buckets.

Conceptually:

```text
HashMap
   |
   v
Table
   |
   +-- Bucket 0
   +-- Bucket 1
   +-- Bucket 2
   +-- Bucket 3
          |
         Node
          |
         Node
```

A key's hash helps determine which bucket should contain the entry.

---

# 4. Is HashMap a Collection?

### Answer

`Map` is part of the Java Collections Framework, but `Map` does **not** extend the `Collection` interface.

The hierarchy is different:

```text
Collection
   |
   +-- List
   +-- Set
   +-- Queue
```

while:

```text
Map
   |
   +-- HashMap
   +-- LinkedHashMap
   +-- TreeMap
```

So:

> HashMap is a collection-framework class, but it is not a `Collection`.

---

# 5. Does HashMap Allow Duplicate Keys?

### Answer

No.

Keys must be unique.

```java
map.put(1, "Java");
map.put(1, "Python");
```

Final mapping:

```text
1 -> Python
```

The second `put()` replaces the previous value.

---

# 6. Does HashMap Allow Duplicate Values?

### Answer

Yes.

```java
map.put(1, "Java");
map.put(2, "Java");
```

Both mappings are valid.

```text
1 -> Java
2 -> Java
```

Only keys need to be unique.

---

# 7. Does HashMap Allow null?

### Answer

Yes.

A HashMap can contain:

- One `null` key
- Multiple `null` values

Example:

```java
map.put(null, "Unknown");
map.put("Java", null);
map.put("Python", null);
```

---

# 8. Does HashMap Maintain Insertion Order?

### Answer

No.

HashMap does not guarantee any particular iteration order.

Therefore, code should never depend on the order in which entries happen to be returned.

If predictable insertion order is required, consider:

```java
LinkedHashMap
```

---

# 9. Is HashMap Thread-Safe?

### Answer

No.

A normal `HashMap` is not thread-safe for concurrent modifications/access patterns.

For concurrent use cases, consider:

```java
ConcurrentHashMap
```

or appropriate synchronization depending on the application.

---

# 10. What is the Time Complexity of HashMap Operations?

### Answer

Typical expected complexity:

| Operation | Expected |
|---|---:|
| `put()` | O(1) |
| `get()` | O(1) |
| `remove()` | O(1) |
| `containsKey()` | O(1) |
| `containsValue()` | O(n) |
| `size()` | O(1) |
| Iteration | O(n) |

The O(1) values are expected/average-case, not a guarantee for every possible collision pattern.

Modern Java can use tree bins in heavily collided buckets.

---

# 11. How Does get() Work Internally?

Suppose:

```java
map.get(key);
```

Conceptually:

```text
key
 ↓
hashCode()
 ↓
hash spreading
 ↓
bucket index
 ↓
bucket
 ↓
compare candidate keys
 ↓
equals()
 ↓
return value
```

HashMap first uses the hash to narrow the search to a bucket.

If multiple entries are in that bucket, it compares candidate entries using their hash and key equality.

---

# 12. How Does put() Work Internally?

When executing:

```java
map.put(key, value);
```

the process is conceptually:

```text
1. Calculate hash
2. Determine bucket
3. Check bucket
4. If matching key exists:
       replace value
5. Otherwise:
       add new entry
6. Check whether resize is required
```

If the bucket becomes sufficiently collision-heavy, a tree bin may be used in modern Java implementations.

---

# 13. What is Hashing?

### Answer

Hashing is the process of converting a key into a hash value used to efficiently locate an entry.

Conceptually:

```text
Key
 ↓
hashCode()
 ↓
Hash
 ↓
Bucket Index
 ↓
Bucket
```

Hashing reduces the amount of searching required during lookup.

---

# 14. What is hashCode()?

### Answer

`hashCode()` returns an integer hash code representing an object.

Example:

```java
String key = "Java";

int hash = key.hashCode();
```

The important contract is:

```text
If a.equals(b) == true
then
a.hashCode() == b.hashCode()
```

However:

```text
Same hashCode()
```

does not necessarily mean:

```text
Objects are equal
```

because collisions are possible.

---

# 15. What is equals()?

### Answer

`equals()` determines whether two objects are logically equal.

Example:

```java
String a = new String("Java");
String b = new String("Java");

System.out.println(a.equals(b));
```

Output:

```text
true
```

HashMap uses equality to determine whether two keys represent the same mapping.

---

# 16. Why Are equals() and hashCode() Important?

### Answer

HashMap uses both to correctly locate keys.

The general process is:

```text
hashCode()
    ↓
Find bucket
    ↓
Compare hash
    ↓
equals()
    ↓
Identify key
```

If custom key classes implement these methods incorrectly, HashMap behavior may be incorrect.

---

# 17. What is a Hash Collision?

### Answer

A collision occurs when different keys map to the same bucket.

For example:

```text
Key A
  ↓
Hash
  ↓
Bucket 5

Key B
  ↓
Hash
  ↓
Bucket 5
```

The keys may still be different.

```text
A.equals(B) == false
```

HashMap must therefore store and distinguish both entries.

---

# 18. How Does HashMap Handle Collisions?

### Answer

HashMap stores multiple entries within the same bucket.

Historically, linked nodes were used.

Modern implementations can convert a heavily populated bucket into a red-black tree when appropriate.

Conceptually:

```text
Bucket
  |
Node
  |
Node
  |
Node
```

can become:

```text
       Node
      /    \
   Node    Node
   /  \
Node  Node
```

This helps improve performance under heavy collisions.

---

# 19. What are Tree Bins?

### Answer

A tree bin is a bucket represented using a red-black tree instead of only a linked node chain.

This was introduced in Java 8 HashMap implementations to improve collision-heavy behavior.

Common OpenJDK implementation thresholds include:

```text
TREEIFY_THRESHOLD     = 8
UNTREEIFY_THRESHOLD   = 6
MIN_TREEIFY_CAPACITY  = 64
```

These are implementation details, not guarantees of the public `HashMap` API.

---

# 20. What is the Default Capacity?

### Answer

For the standard no-argument constructor, the default initial capacity configuration is:

```text
16
```

Example:

```java
HashMap<Integer, String> map = new HashMap<>();
```

An important implementation detail is that the internal table can be allocated lazily rather than immediately allocating all buckets when the empty map is constructed.

---

# 21. What is Load Factor?

### Answer

Load factor determines how full the hash table can become before resizing.

Conceptually:

```text
Threshold = Capacity × Load Factor
```

Example:

```text
Capacity = 16
Load Factor = 0.75

Threshold ≈ 12
```

---

# 22. What is the Default Load Factor?

### Answer

The standard default load factor is:

```text
0.75
```

This provides a common balance between:

- Memory usage
- Number of collisions
- Lookup performance

---

# 23. What is Threshold?

### Answer

Threshold is approximately the number of mappings that can be stored before HashMap resizes.

Example:

```text
Capacity = 16
Load Factor = 0.75

Threshold ≈ 12
```

When the size exceeds the applicable threshold, HashMap resizes its table.

---

# 24. What Happens During Resizing?

### Answer

When HashMap grows beyond its threshold:

```text
Old table
   ↓
Larger table
   ↓
Entries redistributed
```

For example:

```text
16 buckets
    ↓
32 buckets
```

Modern HashMap implementations optimize the redistribution process rather than simply recalculating every entry from scratch.

---

# 25. Why is HashMap Capacity Usually a Power of Two?

### Answer

HashMap's internal table uses power-of-two capacities because this enables efficient bucket-index calculation and efficient redistribution during resizing.

Conceptually, the bucket selection can use:

```text
(hash) & (capacity - 1)
```

instead of a more expensive general modulo operation.

For example, if:

```text
capacity = 16
```

then:

```text
capacity - 1 = 15
```

and the index calculation can use bitwise operations.

This is an implementation detail of HashMap.

---

# 26. What Changed in Java 8 HashMap?

### Answer

One major change was improved collision handling.

Before Java 8, heavily collided buckets primarily used linked structures.

Java 8 introduced tree bins:

```text
Linked nodes
     ↓
Red-black tree
```

when appropriate.

This improves behavior for collision-heavy buckets.

---

# 27. What Happens When Two Keys Have the Same Hash?

### Answer

Hash collision handling takes place.

HashMap does not assume:

```text
same hash = same key
```

Instead, it further compares keys.

Conceptually:

```text
Same hash?
   |
   v
Compare keys using equals()
   |
   +-- true  → Same key
   |
   +-- false → Different keys / collision
```

---

# 28. Can Different Objects Have the Same hashCode()?

### Answer

Yes.

This is completely valid.

```text
A.hashCode() == B.hashCode()
```

does not imply:

```text
A.equals(B)
```

This is a hash collision.

A good hash function tries to minimize collisions but cannot guarantee that different objects always have different hash codes.

---

# 29. Can Equal Objects Have Different hashCode()?

### Answer

No.

If:

```java
a.equals(b)
```

is `true`, then:

```java
a.hashCode() == b.hashCode()
```

must also be true.

Violating this contract can cause hash-based collections to behave incorrectly.

---

# 30. Why Should HashMap Keys Be Immutable?

### Answer

Because fields used in `equals()` and `hashCode()` should not change while the object is being used as a key.

Example:

```java
Student student = new Student(101);

map.put(student, "Java");

student.id = 999;
```

If `id` affects `hashCode()`, the object may now correspond to a different bucket than the one used during insertion.

As a result:

```java
map.get(student)
```

may fail to find the mapping.

### Best Practice

Use immutable keys whenever possible.

Examples:

```text
String
Integer
Long
UUID
```

---

# 31. What Happens If equals() is Overridden but hashCode() Is Not?

### Answer

This violates the `equals()`/`hashCode()` contract.

Suppose two logically equal objects produce different hash codes.

HashMap may place them in different buckets.

Then a lookup may fail even though `equals()` says the keys are equal.

### Rule

Whenever `equals()` is overridden, `hashCode()` should be overridden consistently.

---

# 32. What is entrySet()?

### Answer

`entrySet()` returns a view containing the key-value mappings.

Example:

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {

    System.out.println(
        entry.getKey() + " -> " + entry.getValue()
    );
}
```

Each `Map.Entry` represents one mapping.

---

# 33. keySet() vs entrySet()

### Answer

If only keys are needed:

```java
map.keySet()
```

If only values are needed:

```java
map.values()
```

If both keys and values are needed:

```java
map.entrySet()
```

Example:

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(entry.getKey());
    System.out.println(entry.getValue());
}
```

`entrySet()` avoids unnecessarily performing another `get()` when both key and value are already available.

---

# 34. containsKey() vs containsValue()

### Answer

`containsKey()` checks keys:

```java
map.containsKey(101);
```

Expected complexity:

```text
O(1)
```

`containsValue()` searches values:

```java
map.containsValue("Mahesh");
```

Typical complexity:

```text
O(n)
```

HashMap is organized around keys, not values.

---

# 35. get() vs containsKey()

### Answer

Consider:

```java
map.get("Java");
```

It can return `null` for two different reasons:

```text
1. Key does not exist
2. Key exists and maps to null
```

To distinguish them:

```java
if (map.containsKey("Java")) {
    // key exists
}
```

This distinction is important when `null` is a valid stored value.

---

# 36. put() Return Value

### Answer

`put()` returns the previous value associated with the key.

```java
String oldValue = map.put(1, "Spring Boot");
```

If the key previously mapped to:

```text
Java
```

then:

```text
oldValue = Java
```

If no previous mapping existed, it returns `null`.

Again, a previous `null` value and no previous mapping can both produce `null`, so `containsKey()` may be needed to distinguish them.

---

# 37. putIfAbsent() vs put()

### Answer

`put()` always inserts/replaces the mapping.

```java
map.put(1, "Python");
```

`putIfAbsent()` only inserts when the key is considered absent.

```java
map.putIfAbsent(1, "Python");
```

If:

```text
1 -> Java
```

already exists, it remains:

```text
1 -> Java
```

A `null` mapping is treated as absent by the `Map` contract for `putIfAbsent()`.

---

# 38. getOrDefault()

### Answer

`getOrDefault()` returns:

- The mapped value if a mapping exists
- The supplied default if the key is absent

Example:

```java
int marks = map.getOrDefault("DSA", 0);
```

Important:

If the key exists with a `null` value, the result is `null`, not the supplied default.

---

# 39. computeIfAbsent()

### Answer

`computeIfAbsent()` is useful when a value should be created only when a key has no non-null mapping.

Example:

```java
HashMap<String, List<String>> groups = new HashMap<>();

groups.computeIfAbsent(
    "Backend",
    key -> new ArrayList<>()
).add("Java");
```

This is particularly useful for:

- Grouping
- Adjacency lists
- Categorization
- Building maps of collections

---

# 40. HashMap vs Hashtable

| Feature | HashMap | Hashtable |
|---|---|---|
| Thread-safe | No | Yes, synchronized legacy class |
| `null` key | Yes | No |
| `null` values | Yes | No |
| Modern general use | Yes | Usually not preferred |
| Concurrent alternative | `ConcurrentHashMap` | Legacy approach |

### Interview Answer

`Hashtable` is a legacy synchronized map. HashMap is generally preferred for non-concurrent use, while `ConcurrentHashMap` is designed for concurrent access.

---

# 41. HashMap vs LinkedHashMap

| Feature | HashMap | LinkedHashMap |
|---|---|---|
| Ordering | No guarantee | Predictable insertion/access order |
| Lookup | Expected O(1) | Expected O(1) |
| Extra ordering structure | No | Yes |
| Null key | Yes | Yes |
| Thread-safe | No | No |

Use `LinkedHashMap` when predictable iteration order is required.

---

# 42. HashMap vs TreeMap

| Feature | HashMap | TreeMap |
|---|---|---|
| Ordering | No guarantee | Sorted |
| Basic lookup | O(1) expected | O(log n) |
| Internal structure | Hash table | Red-black tree |
| Range operations | No | Yes |
| Interface | Map | NavigableMap |

Use `TreeMap` when sorted or range-based operations are required.

---

# 43. HashMap vs ConcurrentHashMap

| Feature | HashMap | ConcurrentHashMap |
|---|---|---|
| Thread-safe | No | Yes |
| `null` key | Allowed | Not allowed |
| `null` value | Allowed | Not allowed |
| Concurrent access | Not designed for it | Designed for it |
| Typical use | General-purpose map | Concurrent applications |

---

# 44. How Do You Count Frequencies Using HashMap?

### Answer

Use the current frequency as the old value.

```java
HashMap<Integer, Integer> frequency = new HashMap<>();

for (int x : arr) {
    frequency.put(
        x,
        frequency.getOrDefault(x, 0) + 1
    );
}
```

Modern alternative:

```java
for (int x : arr) {
    frequency.merge(x, 1, Integer::sum);
}
```

Expected complexity:

```text
Time: O(n) expected
Space: O(k)
```

where `k` is the number of distinct elements.

---

# 45. How Would You Solve Two Sum Using HashMap?

### Answer

For each number:

```text
complement = target - current
```

Check whether the complement has already been seen.

Example:

```java
int[] nums = {2, 7, 11, 15};
int target = 9;

HashMap<Integer, Integer> map = new HashMap<>();

for (int i = 0; i < nums.length; i++) {

    int complement = target - nums[i];

    if (map.containsKey(complement)) {
        System.out.println(
            map.get(complement) + ", " + i
        );
        break;
    }

    map.put(nums[i], i);
}
```

Expected complexity:

```text
Time: O(n) expected
Space: O(n)
```

---

# 46. How Can HashMap Be Used for Grouping?

### Answer

Use the group/category as the key and a collection as the value.

Example:

```java
HashMap<String, List<String>> groups = new HashMap<>();

groups.computeIfAbsent(
    "Backend",
    key -> new ArrayList<>()
).add("Java");
```

Result:

```text
Backend -> [Java]
```

Additional values can be added to the same list.

This pattern is common in:

- Grouping objects
- Graph adjacency lists
- Anagram grouping
- Categorization
- Data processing

---

# 47. Can a Custom Object Be Used as a HashMap Key?

### Answer

Yes.

Example:

```java
HashMap<Student, String> map = new HashMap<>();
```

But the class should correctly implement:

```java
equals()
hashCode()
```

Example:

```java
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
```

The fields used by these methods should preferably be immutable while the object is used as a key.

---

# 48. What Happens If a Mutable Key Is Changed?

### Answer

Suppose:

```java
Student s = new Student(101);

map.put(s, "Java");
```

If `101` participates in `hashCode()` and is changed:

```java
s.id = 999;
```

the object's hash may change.

HashMap still has the entry in the location determined when it was inserted.

A lookup using the changed key can therefore fail.

This is one reason immutable keys are strongly preferred.

---

# 49. What is the Difference Between HashMap and HashSet Internally?

### Answer

`HashSet` is implemented using a `HashMap`.

Conceptually:

```text
HashSet
   |
   v
HashMap
   |
   +-- element -> dummy value
```

For example, adding:

```java
set.add("Java");
```

is conceptually backed by a map entry where the element acts as the key and a shared dummy object acts as the value.

Therefore:

```text
HashMap
    -> key-value pairs

HashSet
    -> unique elements
```

---

# 50. How Would You Design a HashMap?

### Answer

A simplified HashMap design would contain:

```text
1. Array of buckets
2. Hash function
3. Entry/Node structure
4. Collision handling
5. Key equality checking
6. Size tracking
7. Load factor
8. Resize mechanism
```

Simplified entry:

```java
class Node<K, V> {

    int hash;
    K key;
    V value;
    Node<K, V> next;
}
```

Basic insertion:

```text
key
 ↓
hash
 ↓
bucket index
 ↓
search bucket
 ↓
same key?
 ├── Yes → update value
 └── No  → add node
```

When the table becomes too full:

```text
resize
  ↓
larger table
  ↓
redistribute entries
```

A production-quality implementation must also address tree bins, iteration behavior, concurrent access considerations, null handling, and many edge cases.

---

# 51. Rapid-Fire Revision

## Q1. What does HashMap store?

```text
Key-value pairs
```

---

## Q2. Are duplicate keys allowed?

```text
No
```

---

## Q3. Are duplicate values allowed?

```text
Yes
```

---

## Q4. Is null key allowed?

```text
Yes — one null key
```

---

## Q5. Are null values allowed?

```text
Yes — multiple
```

---

## Q6. Does HashMap maintain insertion order?

```text
No guaranteed order
```

---

## Q7. Is HashMap thread-safe?

```text
No
```

---

## Q8. Expected complexity of get()?

```text
O(1)
```

---

## Q9. Expected complexity of put()?

```text
O(1)
```

---

## Q10. What causes a collision?

```text
Different keys targeting the same bucket
```

---

## Q11. What methods are important for custom keys?

```text
equals()
hashCode()
```

---

## Q12. What is the default load factor?

```text
0.75
```

---

## Q13. What is the standard default capacity configuration?

```text
16
```

---

## Q14. What happens when the threshold is exceeded?

```text
HashMap resizes and redistributes entries.
```

---

## Q15. What did Java 8 introduce for collision-heavy buckets?

```text
Red-black tree bins
```

---

## Q16. Can two unequal objects have the same hash code?

```text
Yes
```

---

## Q17. Can equal objects have different hash codes?

```text
No
```

---

## Q18. Which should you use when both key and value are needed?

```text
entrySet()
```

---

## Q19. Which method checks whether a key exists?

```text
containsKey()
```

---

## Q20. Which method checks whether a value exists?

```text
containsValue()
```

---

## Q21. Why should HashMap keys preferably be immutable?

```text
To prevent changes to equals()/hashCode()-relevant state
from making entries difficult to locate.
```

---

## Q22. Which Map should you use for sorted keys?

```text
TreeMap
```

---

## Q23. Which Map should you use when predictable insertion order is required?

```text
LinkedHashMap
```

---

## Q24. Which Map is designed for concurrent access?

```text
ConcurrentHashMap
```

---

## Q25. What is the most important HashMap interview concept?

```text
hashCode()
    ↓
bucket
    ↓
collision handling
    ↓
equals()
```

---

# Final Interview Mental Model

```text
                    HashMap
                       |
              Key -> Value
                       |
                    hashCode()
                       |
                  Hash / Spread
                       |
                  Bucket Index
                       |
                 +-----+-----+
                 |           |
              Empty      Existing
                 |           |
               Insert      Compare
                             |
                         equals()
                             |
                    +--------+--------+
                    |                 |
                  Equal            Different
                    |                 |
               Update Value       Collision
                                      |
                              Linked Nodes /
                                Tree Bin
```

The core concepts to remember are:

```text
HashMap
├── Key-value storage
├── No guaranteed order
├── One null key
├── Multiple null values
├── Not thread-safe
├── Expected O(1) basic operations
├── hashCode() → bucket
├── equals() → key equality
├── Collision handling
├── Load factor
├── Threshold
├── Resizing
├── Tree bins
└── Immutable keys are preferred
```

---

## Progress

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
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

> **`17-HashMap` is now complete: `NOTES.md` + `PRACTICE.md` + `INTERVIEW.md`.**
>
> **Next: `18-LinkedHashMap/NOTES.md`**.
