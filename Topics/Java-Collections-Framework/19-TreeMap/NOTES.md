# TreeMap — Java Collections Framework

> A practical and interview-ready guide to `TreeMap`, covering sorted key ordering, `NavigableMap`, red-black trees, range operations, navigation methods, custom comparators, and complexity.

---

## Table of Contents

- [1. What is TreeMap?](#1-what-is-treemap)
- [2. Why TreeMap is Important](#2-why-treemap-is-important)
- [3. TreeMap Hierarchy](#3-treemap-hierarchy)
- [4. Key Features](#4-key-features)
- [5. Creating a TreeMap](#5-creating-a-treemap)
- [6. Natural Ordering](#6-natural-ordering)
- [7. Custom Ordering with Comparator](#7-custom-ordering-with-comparator)
- [8. Basic Operations](#8-basic-operations)
- [9. put()](#9-put)
- [10. get()](#10-get)
- [11. remove()](#11-remove)
- [12. containsKey() and containsValue()](#12-containskey-and-containsvalue)
- [13. firstKey() and lastKey()](#13-firstkey-and-lastkey)
- [14. firstEntry() and lastEntry()](#14-firstentry-and-lastentry)
- [15. lowerKey() and lowerEntry()](#15-lowerkey-and-lowerentry)
- [16. floorKey() and floorEntry()](#16-floorkey-and-floorentry)
- [17. ceilingKey() and ceilingEntry()](#17-ceilingkey-and-ceilingentry)
- [18. higherKey() and higherEntry()](#18-higherkey-and-higherentry)
- [19. pollFirstEntry() and pollLastEntry()](#19-pollfirstentry-and-polllastentry)
- [20. NavigableMap](#20-navigablemap)
- [21. subMap()](#21-submap)
- [22. headMap()](#22-headmap)
- [23. tailMap()](#23-tailmap)
- [24. descendingMap()](#24-descendingmap)
- [25. descendingKeySet()](#25-descendingkeyset)
- [26. Iteration](#26-iteration)
- [27. Null Keys and Null Values](#27-null-keys-and-null-values)
- [28. Duplicate Keys and Values](#28-duplicate-keys-and-values)
- [29. How TreeMap Works Internally](#29-how-treemap-works-internally)
- [30. Red-Black Tree](#30-red-black-tree)
- [31. Why Red-Black Tree?](#31-why-red-black-tree)
- [32. Search in TreeMap](#32-search-in-treemap)
- [33. Insertion in TreeMap](#33-insertion-in-treemap)
- [34. Deletion in TreeMap](#34-deletion-in-treemap)
- [35. TreeMap Ordering](#35-treemap-ordering)
- [36. Comparator and Key Equality](#36-comparator-and-key-equality)
- [37. TreeMap with Custom Objects](#37-treemap-with-custom-objects)
- [38. Comparable vs Comparator](#38-comparable-vs-comparator)
- [39. TreeMap Complexity](#39-treemap-complexity)
- [40. TreeMap vs HashMap](#40-treemap-vs-hashmap)
- [41. TreeMap vs LinkedHashMap](#41-treemap-vs-linkedhashmap)
- [42. TreeMap vs Hashtable](#42-treemap-vs-hashtable)
- [43. TreeMap vs ConcurrentHashMap](#43-treemap-vs-concurrenthashmap)
- [44. TreeMap vs TreeSet](#44-treemap-vs-treeset)
- [45. Common Use Cases](#45-common-use-cases)
- [46. Common Mistakes](#46-common-mistakes)
- [47. Interview Quick Revision](#47-interview-quick-revision)
- [48. Progress](#48-progress)

---

# 1. What is TreeMap?

`TreeMap` is a Java `Map` implementation that stores key-value pairs in **sorted key order**.

It implements:

```java
NavigableMap<K, V>
```

and is based on a balanced tree structure.

Example:

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(30, "C");
map.put(10, "A");
map.put(20, "B");
```

Iteration produces:

```text
10 -> A
20 -> B
30 -> C
```

The keys are sorted according to:

- Their natural ordering, or
- A supplied `Comparator`

---

# 2. Why TreeMap is Important

TreeMap is useful when you need:

- Automatically sorted keys
- Fast search in sorted data
- Minimum/maximum key access
- Range queries
- Predecessor/successor operations
- Floor/ceiling operations
- Reverse-order traversal
- Interval-based processing

Example:

```java
TreeMap<Integer, String> students = new TreeMap<>();

students.put(103, "Amit");
students.put(101, "Mahesh");
students.put(102, "Rahul");
```

Iteration:

```text
101 -> Mahesh
102 -> Rahul
103 -> Amit
```

---

# 3. TreeMap Hierarchy

```text
Map
 |
SortedMap
 |
NavigableMap
 |
TreeMap
```

More precisely:

```text
Map<K,V>
   ↑
SortedMap<K,V>
   ↑
NavigableMap<K,V>
   ↑
TreeMap<K,V>
```

TreeMap is therefore more powerful than a basic `Map` implementation because it provides sorted and navigation operations.

---

# 4. Key Features

| Feature | TreeMap |
|---|---|
| Stores | Key-value pairs |
| Duplicate keys | No |
| Duplicate values | Yes |
| Key ordering | Sorted |
| Internal structure | Red-black tree |
| Basic operations | O(log n) |
| Implements | `NavigableMap` |
| Thread-safe | No |
| Null key | Generally not supported with natural ordering |
| Null values | Allowed |
| Range queries | Yes |
| Reverse traversal | Yes |

---

# 5. Creating a TreeMap

## 5.1 Default Constructor

```java
TreeMap<Integer, String> map = new TreeMap<>();
```

Keys use their natural ordering.

---

## 5.2 With Comparator

```java
TreeMap<Integer, String> map =
        new TreeMap<>(Comparator.reverseOrder());
```

Now keys are sorted in descending order.

Example:

```text
30
20
10
```

---

## 5.3 From Another Map

```java
Map<Integer, String> source = new HashMap<>();

source.put(3, "C");
source.put(1, "A");
source.put(2, "B");

TreeMap<Integer, String> map =
        new TreeMap<>(source);
```

The TreeMap sorts the keys according to its ordering.

---

# 6. Natural Ordering

If no comparator is supplied, TreeMap uses the natural ordering of keys.

For numbers:

```text
1 < 2 < 3 < 4
```

For strings:

```text
Apple
Banana
Java
Python
```

Example:

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(40, "D");
map.put(10, "A");
map.put(30, "C");
map.put(20, "B");
```

Iteration:

```text
10 -> A
20 -> B
30 -> C
40 -> D
```

Keys must be mutually comparable for natural ordering to work correctly.

---

# 7. Custom Ordering with Comparator

You can define your own ordering.

Example:

```java
TreeMap<Integer, String> map =
        new TreeMap<>(Comparator.reverseOrder());
```

Now:

```text
40
30
20
10
```

Another example:

```java
TreeMap<String, Integer> map =
        new TreeMap<>(
            (a, b) -> Integer.compare(
                b.length(),
                a.length()
            )
        );
```

This orders keys by descending string length.

---

# 8. Basic Operations

Common Map operations:

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

TreeMap additionally provides navigation methods such as:

```java
map.firstKey();
map.lastKey();
map.lowerKey(key);
map.floorKey(key);
map.ceilingKey(key);
map.higherKey(key);
```

and range methods such as:

```java
map.subMap(...);
map.headMap(...);
map.tailMap(...);
```

---

# 9. put()

Adds or updates a mapping.

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(30, "C");
map.put(10, "A");
map.put(20, "B");
```

Iteration:

```text
10 -> A
20 -> B
30 -> C
```

Adding an existing key updates its value:

```java
map.put(20, "Updated");
```

Final mapping for `20`:

```text
20 -> Updated
```

---

# 10. get()

Retrieves the value associated with a key.

```java
String value = map.get(20);
```

Example:

```java
System.out.println(map.get(20));
```

If the key does not exist:

```java
map.get(999);
```

returns:

```text
null
```

---

# 11. remove()

Removes a mapping by key.

```java
String removed = map.remove(20);
```

The return value is the removed value.

---

# 12. containsKey() and containsValue()

## containsKey()

Checks whether a key exists.

```java
map.containsKey(20);
```

Typical complexity:

```text
O(log n)
```

---

## containsValue()

Checks whether a value exists.

```java
map.containsValue("Java");
```

This generally requires traversal of the entries.

Typical complexity:

```text
O(n)
```

TreeMap is optimized for key-based operations, not value lookup.

---

# 13. firstKey() and lastKey()

## firstKey()

Returns the first key according to the map's ordering.

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(30, "C");
map.put(10, "A");
map.put(20, "B");

System.out.println(map.firstKey());
```

Output:

```text
10
```

---

## lastKey()

```java
System.out.println(map.lastKey());
```

Output:

```text
30
```

These operations are typically:

```text
O(log n)
```

or effectively constant-time in current implementations because the tree maintains direct references to boundary entries, but interview answers should generally focus on the documented logarithmic complexity of TreeMap's core operations.

---

# 14. firstEntry() and lastEntry()

Instead of only getting the key, you can get the complete mapping.

```java
Map.Entry<Integer, String> first =
        map.firstEntry();

Map.Entry<Integer, String> last =
        map.lastEntry();
```

Example:

```java
System.out.println(first.getKey());
System.out.println(first.getValue());
```

---

# 15. lowerKey() and lowerEntry()

Returns the greatest key **strictly less than** the given key.

Example:

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");
map.put(40, "D");
```

```java
map.lowerKey(30);
```

returns:

```text
20
```

Because:

```text
20 < 30
```

and there is no larger key below `30`.

Similarly:

```java
map.lowerEntry(30);
```

returns:

```text
20 -> B
```

---

# 16. floorKey() and floorEntry()

Returns the greatest key **less than or equal to** the given key.

```java
map.floorKey(30);
```

returns:

```text
30
```

If `30` does not exist:

```java
map.floorKey(35);
```

returns:

```text
30
```

because:

```text
30 <= 35
```

Similarly:

```java
map.floorEntry(35);
```

returns:

```text
30 -> C
```

---

# 17. ceilingKey() and ceilingEntry()

Returns the smallest key **greater than or equal to** the given key.

```java
map.ceilingKey(30);
```

returns:

```text
30
```

For:

```java
map.ceilingKey(35);
```

the result is:

```text
40
```

because:

```text
40 >= 35
```

Similarly:

```java
map.ceilingEntry(35);
```

returns:

```text
40 -> D
```

---

# 18. higherKey() and higherEntry()

Returns the smallest key **strictly greater than** the given key.

```java
map.higherKey(30);
```

returns:

```text
40
```

because:

```text
40 > 30
```

Similarly:

```java
map.higherEntry(30);
```

returns:

```text
40 -> D
```

---

# 19. pollFirstEntry() and pollLastEntry()

These methods return and remove an entry.

## pollFirstEntry()

```java
Map.Entry<Integer, String> first =
        map.pollFirstEntry();
```

Removes the first entry according to the map's ordering.

---

## pollLastEntry()

```java
Map.Entry<Integer, String> last =
        map.pollLastEntry();
```

Removes the last entry.

Example:

```text
Before:
10 -> A
20 -> B
30 -> C

pollFirstEntry()

After:
20 -> B
30 -> C
```

These methods are useful when processing data from either end of the sorted map.

---

# 20. NavigableMap

TreeMap implements:

```java
NavigableMap<K,V>
```

`NavigableMap` provides navigation around keys.

Important methods:

```text
lowerKey()
floorKey()
ceilingKey()
higherKey()

lowerEntry()
floorEntry()
ceilingEntry()
higherEntry()

firstKey()
lastKey()

firstEntry()
lastEntry()

pollFirstEntry()
pollLastEntry()
```

It also provides views such as:

```text
subMap()
headMap()
tailMap()
descendingMap()
```

This makes TreeMap especially useful for range and nearest-key problems.

---

# 21. subMap()

Returns a view of a portion of the map.

Example:

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");
map.put(40, "D");
map.put(50, "E");
```

Using:

```java
map.subMap(20, 50);
```

returns entries with keys in:

```text
20 <= key < 50
```

Conceptually:

```text
20 -> B
30 -> C
40 -> D
```

---

## Inclusive Boundaries

You can explicitly control inclusivity:

```java
map.subMap(20, true, 50, true);
```

Now:

```text
20 <= key <= 50
```

---

# 22. headMap()

Returns entries whose keys are before a specified key.

```java
map.headMap(40);
```

produces:

```text
10 -> A
20 -> B
30 -> C
```

The default upper bound is exclusive.

You can include the boundary:

```java
map.headMap(40, true);
```

Now `40` is included.

---

# 23. tailMap()

Returns entries whose keys are from a specified key onward.

```java
map.tailMap(30);
```

produces:

```text
30 -> C
40 -> D
50 -> E
```

The default lower bound is inclusive.

You can control this explicitly:

```java
map.tailMap(30, false);
```

Now:

```text
30
```

is excluded.

---

# 24. descendingMap()

Returns a view of the map in reverse key order.

```java
NavigableMap<Integer, String> descending =
        map.descendingMap();
```

If the original order is:

```text
10
20
30
40
```

the descending view is:

```text
40
30
20
10
```

---

# 25. descendingKeySet()

Returns the keys in reverse order.

```java
NavigableSet<Integer> keys =
        map.descendingKeySet();
```

Example:

```text
40
30
20
10
```

---

# 26. Iteration

TreeMap supports normal enhanced `for` iteration.

## Using entrySet()

```java
for (Map.Entry<Integer, String> entry :
        map.entrySet()) {

    System.out.println(
        entry.getKey() + " -> " + entry.getValue()
    );
}
```

Iteration is in sorted key order according to the map's comparator.

---

## Using keySet()

```java
for (Integer key : map.keySet()) {
    System.out.println(key);
}
```

---

## Using values()

```java
for (String value : map.values()) {
    System.out.println(value);
}
```

---

## Using forEach()

```java
map.forEach((key, value) -> {
    System.out.println(
        key + " -> " + value
    );
});
```

---

# 27. Null Keys and Null Values

TreeMap's handling differs from HashMap.

## Null Key

With natural ordering:

```java
TreeMap<Integer, String> map =
        new TreeMap<>();

map.put(null, "Value");
```

generally results in a `NullPointerException` because natural ordering requires comparing keys.

A custom comparator may choose to define a policy that permits `null`, but this depends on the comparator.

### Interview Rule

For normal TreeMap usage with natural ordering:

```text
null key -> not supported
```

---

## Null Values

Null values are allowed:

```java
map.put(10, null);
```

---

# 28. Duplicate Keys and Values

## Duplicate Keys

Not allowed.

```java
map.put(10, "Java");
map.put(10, "Python");
```

Final:

```text
10 -> Python
```

---

## Duplicate Values

Allowed.

```java
map.put(10, "Java");
map.put(20, "Java");
```

Valid.

---

# 29. How TreeMap Works Internally

TreeMap is implemented using a **red-black tree**, which is a self-balancing binary search tree.

Simplified:

```text
            30
           /  \
         20    40
        / \    / \
      10  25  35  50
```

The keys remain ordered according to the comparator.

For every node:

```text
Left subtree
    ↓
smaller keys

Right subtree
    ↓
larger keys
```

The tree is kept approximately balanced so its height remains logarithmic.

---

# 30. Red-Black Tree

A red-black tree is a self-balancing binary search tree.

Each node has a color:

```text
RED
BLACK
```

It follows balancing rules that prevent the tree from becoming too tall.

A simplified tree:

```text
             30(B)
            /     \
         20(R)    40(R)
         /  \      /  \
      10(B)25(B)35(B)50(B)
```

The exact colors depend on the operations performed.

The important property is:

```text
Tree height = O(log n)
```

This provides predictable logarithmic performance.

---

# 31. Why Red-Black Tree?

A normal binary search tree can become skewed:

```text
10
  \
   20
     \
      30
        \
         40
```

This behaves like a linked list.

Search can degrade toward:

```text
O(n)
```

A red-black tree keeps the structure balanced enough that height remains:

```text
O(log n)
```

Therefore TreeMap can maintain:

```text
put()    -> O(log n)
get()    -> O(log n)
remove() -> O(log n)
```

---

# 32. Search in TreeMap

Suppose we search:

```java
map.get(35);
```

The tree search conceptually works like:

```text
             50
            /
          30
            \
             40
            /
          35
```

Comparison process:

```text
35 < 50
    ↓
go left

35 > 30
    ↓
go right

35 < 40
    ↓
go left

35 found
```

Because the tree height is logarithmic:

```text
O(log n)
```

is expected.

---

# 33. Insertion in TreeMap

When inserting:

```java
map.put(35, "X");
```

TreeMap:

```text
1. Finds the correct location
2. Inserts the node
3. Rebalances the red-black tree
```

Conceptually:

```text
Find position
      ↓
Insert node
      ↓
Fix colors / rotations
      ↓
Balanced tree
```

This maintains logarithmic height.

---

# 34. Deletion in TreeMap

When removing:

```java
map.remove(30);
```

TreeMap:

```text
1. Finds the key
2. Removes the corresponding node
3. Repairs the tree structure
4. Rebalances if necessary
```

Red-black tree operations such as recoloring and rotations maintain the required balance properties.

---

# 35. TreeMap Ordering

TreeMap determines ordering using:

```text
Comparator
      OR
Comparable
```

Priority:

```text
Comparator provided?
       |
    +-- Yes --> use Comparator
    |
    +-- No  --> use natural ordering
```

For example:

```java
TreeMap<Integer, String> map =
        new TreeMap<>(Comparator.reverseOrder());
```

Ordering:

```text
50
40
30
20
10
```

---

# 36. Comparator and Key Equality

This is an important interview concept.

TreeMap uses its ordering mechanism to determine where keys belong.

If:

```java
compare(k1, k2) == 0
```

then the map treats those keys as equivalent for map operations, even if:

```java
k1.equals(k2)
```

is `false`.

Example:

```java
TreeMap<String, Integer> map =
        new TreeMap<>(Comparator.comparingInt(String::length));
```

Now:

```text
"Java"      -> length 4
"Python"    -> length 6
"Spring"    -> length 6
```

`"Python"` and `"Spring"` compare as `0` because both have length `6`.

They therefore conflict as keys under that ordering.

### Important

The ordering used by a sorted map should ideally be consistent with `equals()`.

---

# 37. TreeMap with Custom Objects

Suppose:

```java
class Student {

    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
```

You can use a Comparator:

```java
TreeMap<Student, String> map =
        new TreeMap<>(
            Comparator.comparingInt(
                student -> student.id
            )
        );
```

Now students are ordered by ID.

Example:

```java
map.put(
    new Student(103, "Amit"),
    "CS"
);

map.put(
    new Student(101, "Mahesh"),
    "CS"
);
```

Iteration follows student ID order.

---

# 38. Comparable vs Comparator

TreeMap needs a way to compare keys.

## Comparable

The key class defines its natural ordering.

```java
class Student implements Comparable<Student> {

    int id;

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }
}
```

Then:

```java
TreeMap<Student, String> map =
        new TreeMap<>();
```

---

## Comparator

Ordering is supplied externally:

```java
TreeMap<Student, String> map =
        new TreeMap<>(
            Comparator.comparingInt(
                student -> student.id
            )
        );
```

### Mental Model

```text
Comparable
→ Class defines natural order

Comparator
→ External object defines order
```

---

# 39. TreeMap Complexity

| Operation | Complexity |
|---|---:|
| `put()` | O(log n) |
| `get()` | O(log n) |
| `remove()` | O(log n) |
| `containsKey()` | O(log n) |
| `containsValue()` | O(n) |
| `firstKey()` | O(log n) general guarantee |
| `lastKey()` | O(log n) general guarantee |
| `lowerKey()` | O(log n) |
| `floorKey()` | O(log n) |
| `ceilingKey()` | O(log n) |
| `higherKey()` | O(log n) |
| Iteration | O(n) |

The `NavigableMap` operations benefit from the sorted tree structure.

---

# 40. TreeMap vs HashMap

| Feature | TreeMap | HashMap |
|---|---|---|
| Ordering | Sorted | No guaranteed order |
| Internal structure | Red-black tree | Hash table |
| Basic lookup | O(log n) | O(1) expected |
| Range queries | Yes | No |
| `firstKey()` / `lastKey()` | Yes | No |
| Floor/ceiling | Yes | No |
| Null key | Not with natural ordering | Yes |
| Thread-safe | No | No |

### Rule

Use:

```text
HashMap
→ Fast average key lookup

TreeMap
→ Sorted keys + navigation/ranges
```

---

# 41. TreeMap vs LinkedHashMap

| Feature | TreeMap | LinkedHashMap |
|---|---|---|
| Order | Sorted | Insertion/access |
| Basic lookup | O(log n) | O(1) expected |
| Range queries | Yes | No |
| Reverse traversal | Yes | Not as a sorted map |
| Internal structure | Red-black tree | Hash table + linked order |
| Null key | Not with natural ordering | Yes |
| Thread-safe | No | No |

Use `LinkedHashMap` when order should reflect insertion/access.

Use `TreeMap` when keys themselves must remain sorted.

---

# 42. TreeMap vs Hashtable

| Feature | TreeMap | Hashtable |
|---|---|---|
| Ordering | Sorted | No guaranteed sorted order |
| Basic lookup | O(log n) | O(1) expected |
| Thread-safe | No | Synchronized legacy class |
| Null key | Not with natural ordering | No |
| Null values | Yes | No |
| Range operations | Yes | No |

---

# 43. TreeMap vs ConcurrentHashMap

| Feature | TreeMap | ConcurrentHashMap |
|---|---|---|
| Main purpose | Sorted map | Concurrent map |
| Basic lookup | O(log n) | O(1) expected |
| Sorted keys | Yes | No |
| Range operations | Yes | No equivalent sorted-map behavior |
| Thread-safe | No | Designed for concurrency |
| Null key | Not with natural ordering | No |
| Null values | Yes | No |

---

# 44. TreeMap vs TreeSet

`TreeSet` and `TreeMap` are both based on tree-based ordering.

| Feature | TreeMap | TreeSet |
|---|---|---|
| Stores | Key-value pairs | Unique elements |
| Interface | `NavigableMap` | `NavigableSet` |
| Duplicate keys/elements | No | No |
| Ordering | Sorted keys | Sorted elements |
| Typical operation | Key-value mapping | Membership / sorted elements |

A useful relationship:

```text
TreeSet
   ↓
TreeMap
   ↓
Element -> Dummy Value
```

`TreeSet` is backed by a `TreeMap` in the standard implementation.

---

# 45. Common Use Cases

## 45.1 Sorted Data

```java
TreeMap<Integer, String> map =
        new TreeMap<>();
```

Useful when keys should always remain sorted.

---

## 45.2 Minimum / Maximum

```java
map.firstKey();
map.lastKey();
```

Useful for quickly identifying boundary keys.

---

## 45.3 Floor / Ceiling Problems

Find nearest values:

```java
map.floorKey(x);
map.ceilingKey(x);
```

Useful in DSA for predecessor/successor style problems.

---

## 45.4 Range Queries

```java
map.subMap(100, 200);
```

Useful when you need all entries inside a key range.

---

## 45.5 Scheduling

TreeMap can be useful when events need to be processed in sorted key order.

Example:

```text
timestamp -> event
```

Then:

```text
earliest timestamp
latest timestamp
next timestamp
```

can be navigated efficiently.

---

## 45.6 Leaderboards / Ranked Data

When keys represent sorted scores or rankings, TreeMap can provide sorted navigation.

---

## 45.7 Time-Based Data

For:

```text
timestamp -> value
```

you can use:

```java
map.floorEntry(timestamp);
map.ceilingEntry(timestamp);
```

to find nearby timestamps.

---

# 46. Common Mistakes

## Mistake 1 — Thinking TreeMap Is O(1)

Incorrect.

TreeMap is based on a balanced tree.

Typical basic operations:

```text
O(log n)
```

---

## Mistake 2 — Thinking TreeMap Preserves Insertion Order

It does not.

It sorts according to its ordering mechanism.

---

## Mistake 3 — Thinking TreeMap Uses Hashing

TreeMap is tree-based, not hash-table-based.

```text
HashMap  → hashing
TreeMap  → red-black tree
```

---

## Mistake 4 — Forgetting Comparator Behavior

If a Comparator is supplied, TreeMap uses that ordering.

```java
new TreeMap<>(comparator);
```

---

## Mistake 5 — Ignoring compare() Returning 0

If:

```java
comparator.compare(a, b) == 0
```

TreeMap treats the keys as equivalent for map operations.

This can cause an apparently different key to replace an existing mapping.

---

## Mistake 6 — Using Null Keys with Natural Ordering

Natural-order TreeMap normally cannot compare `null` with a non-null key.

This usually results in:

```text
NullPointerException
```

---

## Mistake 7 — Using TreeMap When Sorting Is Not Required

If you only need fast average key lookup and do not need ordering:

```text
HashMap
```

is often the simpler choice.

---

# 47. Interview Quick Revision

### What is TreeMap?

A `NavigableMap` implementation that maintains keys in sorted order using a red-black tree.

---

### What is the default ordering?

Natural ordering of the keys.

---

### Can you provide a custom ordering?

Yes, using a `Comparator`.

---

### What is the internal data structure?

```text
Red-black tree
```

---

### What is the complexity of get()?

```text
O(log n)
```

---

### What is the complexity of put()?

```text
O(log n)
```

---

### Does TreeMap allow duplicate keys?

```text
No
```

---

### Does TreeMap allow duplicate values?

```text
Yes
```

---

### Does TreeMap sort values?

```text
No
```

It sorts keys.

---

### Does TreeMap maintain insertion order?

```text
No
```

---

### Can TreeMap provide the smallest key?

```java
map.firstKey();
```

---

### Can TreeMap provide the largest key?

```java
map.lastKey();
```

---

### What is `lowerKey(k)`?

Greatest key strictly less than `k`.

```text
< k
```

---

### What is `floorKey(k)`?

Greatest key less than or equal to `k`.

```text
<= k
```

---

### What is `ceilingKey(k)`?

Smallest key greater than or equal to `k`.

```text
>= k
```

---

### What is `higherKey(k)`?

Smallest key strictly greater than `k`.

```text
> k
```

---

### What does `subMap()` do?

Returns a view of a portion of the sorted map.

---

### What does `headMap()` do?

Returns entries before a specified key.

---

### What does `tailMap()` do?

Returns entries from a specified key onward.

---

### What does `descendingMap()` do?

Returns a reverse-order view of the map.

---

### Is TreeMap thread-safe?

```text
No
```

---

### Does TreeMap allow null values?

```text
Yes
```

---

### Does TreeMap allow null keys?

With natural ordering:

```text
No
```

A custom comparator can define behavior for `null`, but normal natural ordering cannot compare `null`.

---

### HashMap or TreeMap?

```text
Fast average lookup
    → HashMap

Sorted keys / range queries
    → TreeMap
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

# Core Navigation Cheat Sheet

```text
                    TreeMap
                       |
        +--------------+--------------+
        |              |              |
      Before          Around         After
        |              |              |
   lowerKey()      floorKey()     higherKey()
                   ceilingKey()
```

More precisely:

```text
lowerKey(x)
    → greatest key < x

floorKey(x)
    → greatest key <= x

ceilingKey(x)
    → smallest key >= x

higherKey(x)
    → smallest key > x
```

---

# Core Range Operations

```text
subMap(from, to)
    → from <= key < to

headMap(to)
    → key < to

tailMap(from)
    → key >= from

descendingMap()
    → reverse-order view
```

---

# Final Interview Mental Model

```text
                    TreeMap
                       |
                NavigableMap
                       |
               Red-Black Tree
                       |
              Sorted Key Order
                       |
        +--------------+--------------+
        |              |              |
      Search          Range        Navigation
        |              |              |
     get()         subMap()       floorKey()
     put()         headMap()      ceilingKey()
     remove()      tailMap()      lowerKey()
                                  higherKey()
```

### Remember

```text
HashMap
→ Hashing
→ Expected O(1)
→ No guaranteed order

LinkedHashMap
→ Hashing + linked ordering
→ Expected O(1)
→ Predictable insertion/access order

TreeMap
→ Red-black tree
→ O(log n)
→ Sorted key order
→ Range + navigation operations
```

---

# 48. Progress

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
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
│
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

> **`19-TreeMap/NOTES.md` completed.**
>
> **Next: `19-TreeMap/PRACTICE.md`**.
