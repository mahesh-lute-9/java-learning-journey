# TreeMap — Interview Questions & Answers

> SDE interview preparation for Java `TreeMap`, covering sorted key ordering, `NavigableMap`, red-black trees, navigation methods, range queries, `Comparable`, `Comparator`, complexity, and practical use cases.

---

## Table of Contents

- [1. What is TreeMap?](#1-what-is-treemap)
- [2. Why is TreeMap Used?](#2-why-is-treemap-used)
- [3. What is the Hierarchy of TreeMap?](#3-what-is-the-hierarchy-of-treemap)
- [4. Is TreeMap a Collection?](#4-is-treemap-a-collection)
- [5. Does TreeMap Maintain Sorted Order?](#5-does-treemap-maintain-sorted-order)
- [6. What Determines TreeMap Ordering?](#6-what-determines-treemap-ordering)
- [7. What is Natural Ordering?](#7-what-is-natural-ordering)
- [8. What is a Comparator in TreeMap?](#8-what-is-a-comparator-in-treemap)
- [9. Comparable vs Comparator in TreeMap](#9-comparable-vs-comparator-in-treemap)
- [10. What Data Structure Does TreeMap Use?](#10-what-data-structure-does-treemap-use)
- [11. What is a Red-Black Tree?](#11-what-is-a-red-black-tree)
- [12. Why Does TreeMap Use a Red-Black Tree?](#12-why-does-treemap-use-a-red-black-tree)
- [13. What is the Time Complexity of TreeMap?](#13-what-is-the-time-complexity-of-treemap)
- [14. How Does get() Work Internally?](#14-how-does-get-work-internally)
- [15. How Does put() Work Internally?](#15-how-does-put-work-internally)
- [16. How Does remove() Work Internally?](#16-how-does-remove-work-internally)
- [17. Does TreeMap Allow Duplicate Keys?](#17-does-treemap-allow-duplicate-keys)
- [18. Does TreeMap Allow Duplicate Values?](#18-does-treemap-allow-duplicate-values)
- [19. Does TreeMap Allow null Keys?](#19-does-treemap-allow-null-keys)
- [20. Does TreeMap Allow null Values?](#20-does-treemap-allow-null-values)
- [21. Is TreeMap Thread-Safe?](#21-is-treemap-thread-safe)
- [22. What is NavigableMap?](#22-what-is-navigablemap)
- [23. What is lowerKey()?](#23-what-is-lowerkey)
- [24. What is floorKey()?](#24-what-is-floorkey)
- [25. What is ceilingKey()?](#25-what-is-ceilingkey)
- [26. What is higherKey()?](#26-what-is-higherkey)
- [27. lowerKey() vs floorKey()](#27-lowerkey-vs-floorkey)
- [28. ceilingKey() vs higherKey()](#28-ceilingkey-vs-higherkey)
- [29. firstKey() vs firstEntry()](#29-firstkey-vs-firstentry)
- [30. What are pollFirstEntry() and pollLastEntry()?](#30-what-are-pollfirstentry-and-polllastentry)
- [31. What is subMap()?](#31-what-is-submap)
- [32. What is headMap()?](#32-what-is-headmap)
- [33. What is tailMap()?](#33-what-is-tailmap)
- [34. What is descendingMap()?](#34-what-is-descendingmap)
- [35. Does subMap() Return a Copy?](#35-does-submap-return-a-copy)
- [36. What Happens If Comparator Returns 0?](#36-what-happens-if-comparator-returns-0)
- [37. Can TreeMap Keys Be Custom Objects?](#37-can-treemap-keys-be-custom-objects)
- [38. What Happens If Keys Are Not Comparable?](#38-what-happens-if-keys-are-not-comparable)
- [39. Is TreeMap Ordering Consistent with equals()?](#39-is-treemap-ordering-consistent-with-equals)
- [40. TreeMap vs HashMap](#40-treemap-vs-hashmap)
- [41. TreeMap vs LinkedHashMap](#41-treemap-vs-linkedhashmap)
- [42. TreeMap vs Hashtable](#42-treemap-vs-hashtable)
- [43. TreeMap vs ConcurrentHashMap](#43-treemap-vs-concurrenthashmap)
- [44. TreeMap vs TreeSet](#44-treemap-vs-treeset)
- [45. Why Not Use HashMap and Sort Later?](#45-why-not-use-hashmap-and-sort-later)
- [46. Common TreeMap Use Cases](#46-common-treemap-use-cases)
- [47. Interview Scenario: Find Nearest Value](#47-interview-scenario-find-nearest-value)
- [48. Interview Scenario: Range Query](#48-interview-scenario-range-query)
- [49. Interview Scenario: Timestamp Lookup](#49-interview-scenario-timestamp-lookup)
- [50. Common Mistakes](#50-common-mistakes)
- [51. Rapid-Fire Revision](#51-rapid-fire-revision)

---

# 1. What is TreeMap?

### Answer

`TreeMap` is a Java `Map` implementation that keeps its keys sorted according to:

- Natural ordering, or
- A supplied `Comparator`

It implements:

```java
NavigableMap<K, V>
```

Example:

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

---

# 2. Why is TreeMap Used?

### Answer

TreeMap is useful when you need:

```text
Sorted keys
+
Efficient key navigation
+
Range queries
```

Typical requirements include:

- Smallest key
- Largest key
- Greatest key less than a value
- Smallest key greater than a value
- Keys inside a range
- Reverse traversal

These operations are much more natural with TreeMap than with HashMap.

---

# 3. What is the Hierarchy of TreeMap?

### Answer

The important hierarchy is:

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

---

# 4. Is TreeMap a Collection?

### Answer

`TreeMap` belongs to the Java Collections Framework, but `Map` does not extend `Collection`.

So TreeMap is:

```text
A Map implementation
```

not a `Collection` implementation.

---

# 5. Does TreeMap Maintain Sorted Order?

### Answer

Yes.

TreeMap always maintains its keys according to its ordering.

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

This is different from `HashMap`, which has no guaranteed iteration order.

---

# 6. What Determines TreeMap Ordering?

### Answer

TreeMap uses either:

```text
1. A Comparator supplied to the TreeMap
OR
2. The natural ordering of the keys
```

Conceptually:

```text
Comparator provided?
       |
   +---+---+
   |       |
  Yes      No
   |       |
Comparator Comparable
```

---

# 7. What is Natural Ordering?

### Answer

Natural ordering is the ordering defined by the key type itself through `Comparable`.

For example:

```java
Integer
String
Long
```

already have natural ordering.

Example:

```java
TreeMap<Integer, String> map =
        new TreeMap<>();
```

The keys are ordered numerically.

```text
10
20
30
40
```

---

# 8. What is a Comparator in TreeMap?

### Answer

A `Comparator` defines custom key ordering externally.

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

You can also define custom rules:

```java
TreeMap<String, Integer> map =
        new TreeMap<>(
            Comparator.comparingInt(String::length)
        );
```

---

# 9. Comparable vs Comparator in TreeMap

### Comparable

The class defines its natural ordering.

```java
class Student implements Comparable<Student> {

    int id;

    @Override
    public int compareTo(Student other) {
        return Integer.compare(id, other.id);
    }
}
```

Then:

```java
TreeMap<Student, String> map =
        new TreeMap<>();
```

### Comparator

Ordering is supplied externally.

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
→ Object defines its natural order

Comparator
→ External object defines the order
```

---

# 10. What Data Structure Does TreeMap Use?

### Answer

TreeMap is based on a:

```text
Red-Black Tree
```

A red-black tree is a self-balancing binary search tree.

This gives logarithmic height and therefore logarithmic basic operations.

---

# 11. What is a Red-Black Tree?

### Answer

A red-black tree is a self-balancing binary search tree where nodes have a color:

```text
RED
BLACK
```

The balancing rules prevent the tree from becoming excessively skewed.

Conceptually:

```text
            30
           /  \
         20    40
        / \    / \
      10  25  35  50
```

The exact colors and rotations depend on the operations being performed.

The important property is:

```text
Height = O(log n)
```

---

# 12. Why Does TreeMap Use a Red-Black Tree?

### Answer

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

Search then approaches:

```text
O(n)
```

A red-black tree keeps the height logarithmic:

```text
O(log n)
```

Therefore TreeMap offers predictable logarithmic performance for its key-based operations.

---

# 13. What is the Time Complexity of TreeMap?

### Answer

Typical complexities are:

| Operation | Complexity |
|---|---:|
| `put()` | O(log n) |
| `get()` | O(log n) |
| `remove()` | O(log n) |
| `containsKey()` | O(log n) |
| `containsValue()` | O(n) |
| `lowerKey()` | O(log n) |
| `floorKey()` | O(log n) |
| `ceilingKey()` | O(log n) |
| `higherKey()` | O(log n) |
| Iteration | O(n) |

The key advantage is not constant-time lookup, but maintaining sorted order while supporting navigation and range operations.

---

# 14. How Does get() Work Internally?

Suppose:

```java
map.get(35);
```

TreeMap searches the binary search tree.

Conceptually:

```text
             50
            /
          30
            \
             40
            /
          35
```

At each node:

```text
target < current
    → go left

target > current
    → go right

target == current
    → found
```

Since the tree is balanced:

```text
O(log n)
```

---

# 15. How Does put() Work Internally?

When executing:

```java
map.put(key, value);
```

TreeMap roughly:

```text
1. Compares the key with existing nodes
2. Finds the correct position
3. Inserts the new entry
4. Rebalances the red-black tree
```

Conceptually:

```text
Compare
   ↓
Find location
   ↓
Insert
   ↓
Rebalance
```

---

# 16. How Does remove() Work Internally?

When executing:

```java
map.remove(key);
```

TreeMap:

```text
1. Searches for the key
2. Removes the corresponding node
3. Repairs the tree
4. Rebalances when required
```

The balanced-tree property is maintained after deletion.

---

# 17. Does TreeMap Allow Duplicate Keys?

### Answer

No.

Example:

```java
map.put(10, "Java");
map.put(10, "Python");
```

Final:

```text
10 -> Python
```

The second insertion replaces the value associated with the existing key.

---

# 18. Does TreeMap Allow Duplicate Values?

### Answer

Yes.

```java
map.put(10, "Java");
map.put(20, "Java");
```

This is valid.

```text
10 -> Java
20 -> Java
```

---

# 19. Does TreeMap Allow null Keys?

### Answer

With natural ordering, `null` keys are generally not supported because TreeMap must compare keys and `null` cannot participate in the natural ordering.

Example:

```java
TreeMap<Integer, String> map =
        new TreeMap<>();

map.put(null, "Value");
```

This results in a `NullPointerException`.

A custom comparator can explicitly define how `null` should be ordered:

```java
Comparator<Integer> comparator =
        Comparator.nullsFirst(Integer::compareTo);

TreeMap<Integer, String> map =
        new TreeMap<>(comparator);
```

So the precise rule is:

```text
Natural ordering
→ null key not supported

Custom comparator
→ can define null handling
```

---

# 20. Does TreeMap Allow null Values?

### Answer

Yes.

Example:

```java
map.put(10, null);
```

The key is ordered normally; the value can be `null`.

---

# 21. Is TreeMap Thread-Safe?

### Answer

No.

TreeMap is not thread-safe.

For concurrent applications, use an appropriate concurrency mechanism or a collection designed for concurrent access.

Note:

```text
ConcurrentHashMap
```

does not provide the sorted-map behavior of TreeMap.

---

# 22. What is NavigableMap?

### Answer

`NavigableMap` is an interface that extends `SortedMap` and provides methods for navigating around keys.

Important operations include:

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

pollFirstEntry()
pollLastEntry()

subMap()
headMap()
tailMap()
descendingMap()
```

TreeMap implements `NavigableMap`.

---

# 23. What is lowerKey()?

### Answer

Returns the greatest key strictly less than the given key.

If keys are:

```text
10 20 30 40
```

then:

```java
map.lowerKey(30);
```

returns:

```text
20
```

Rule:

```text
lowerKey(x)
→ greatest key < x
```

---

# 24. What is floorKey()?

### Answer

Returns the greatest key less than or equal to the given key.

For:

```text
10 20 30 40
```

```java
map.floorKey(30);
```

returns:

```text
30
```

While:

```java
map.floorKey(35);
```

returns:

```text
30
```

Rule:

```text
floorKey(x)
→ greatest key <= x
```

---

# 25. What is ceilingKey()?

### Answer

Returns the smallest key greater than or equal to the given key.

For:

```text
10 20 30 40
```

```java
map.ceilingKey(30);
```

returns:

```text
30
```

And:

```java
map.ceilingKey(35);
```

returns:

```text
40
```

Rule:

```text
ceilingKey(x)
→ smallest key >= x
```

---

# 26. What is higherKey()?

### Answer

Returns the smallest key strictly greater than the given key.

For:

```text
10 20 30 40
```

```java
map.higherKey(30);
```

returns:

```text
40
```

Rule:

```text
higherKey(x)
→ smallest key > x
```

---

# 27. lowerKey() vs floorKey()

Suppose:

```text
10 20 30 40
```

For:

```text
x = 30
```

### lowerKey()

```text
20
```

because it requires:

```text
key < 30
```

### floorKey()

```text
30
```

because it allows:

```text
key <= 30
```

### Memory Trick

```text
lower → <
floor  → <=
```

---

# 28. ceilingKey() vs higherKey()

For:

```text
10 20 30 40
```

and:

```text
x = 30
```

### ceilingKey()

```text
30
```

because:

```text
key >= 30
```

### higherKey()

```text
40
```

because:

```text
key > 30
```

### Memory Trick

```text
ceiling → >=
higher  → >
```

---

# 29. firstKey() vs firstEntry()

### `firstKey()`

Returns only the smallest key.

```java
map.firstKey();
```

Example:

```text
10
```

### `firstEntry()`

Returns the complete key-value mapping.

```java
map.firstEntry();
```

Example:

```text
10 -> A
```

Similarly:

```text
firstKey()   → key
firstEntry() → key + value
```

---

# 30. What are pollFirstEntry() and pollLastEntry()?

### Answer

These methods:

```text
Return
+
Remove
```

the first or last mapping.

Example:

```java
Map.Entry<Integer, String> first =
        map.pollFirstEntry();
```

removes the smallest entry according to the map's ordering.

And:

```java
Map.Entry<Integer, String> last =
        map.pollLastEntry();
```

removes the largest entry.

---

# 31. What is subMap()?

### Answer

`subMap()` provides a view of part of the sorted map.

Example:

```java
map.subMap(20, 50);
```

represents:

```text
20 <= key < 50
```

You can explicitly control inclusivity:

```java
map.subMap(
    20, true,
    50, true
);
```

which means:

```text
20 <= key <= 50
```

---

# 32. What is headMap()?

### Answer

`headMap()` returns a view containing keys before a specified boundary.

```java
map.headMap(40);
```

means:

```text
key < 40
```

Inclusive form:

```java
map.headMap(40, true);
```

means:

```text
key <= 40
```

---

# 33. What is tailMap()?

### Answer

`tailMap()` returns a view containing keys from a specified boundary onward.

```java
map.tailMap(30);
```

means:

```text
key >= 30
```

You can exclude the boundary:

```java
map.tailMap(30, false);
```

meaning:

```text
key > 30
```

---

# 34. What is descendingMap()?

### Answer

`descendingMap()` returns a reverse-order view of the map.

Example:

```java
NavigableMap<Integer, String> reverse =
        map.descendingMap();
```

Original:

```text
10
20
30
40
```

Reverse:

```text
40
30
20
10
```

---

# 35. Does subMap() Return a Copy?

### Answer

No.

`subMap()`, `headMap()`, and `tailMap()` return **views** backed by the original map.

This means modifications through the view are reflected in the original map, subject to the view's range restrictions.

Conceptually:

```text
Original TreeMap
      |
      +---- subMap view
```

They are not independent copies.

---

# 36. What Happens If Comparator Returns 0?

### Answer

This is one of the most important TreeMap interview questions.

Suppose:

```java
TreeMap<String, Integer> map =
        new TreeMap<>(
            Comparator.comparingInt(String::length)
        );
```

Now:

```text
"Java"  -> length 4
"Code"  -> length 4
```

The comparator returns:

```text
0
```

for these two keys.

TreeMap treats them as equivalent for map operations.

So:

```java
map.put("Java", 1);
map.put("Code", 2);
```

does not necessarily create two separate mappings.

The second mapping can replace the first.

### Important Rule

In a `TreeMap`:

```text
compare(k1, k2) == 0
```

means the keys are considered equivalent for the map's ordering.

---

# 37. Can TreeMap Keys Be Custom Objects?

### Answer

Yes.

The key class must provide a usable ordering through either:

```text
Comparable
```

or:

```text
Comparator
```

Example:

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

Using Comparator:

```java
TreeMap<Student, String> map =
        new TreeMap<>(
            Comparator.comparingInt(
                student -> student.id
            )
        );
```

---

# 38. What Happens If Keys Are Not Comparable?

### Answer

If no comparator is supplied, TreeMap relies on natural ordering.

If the keys are not mutually comparable, operations that need comparison can fail with:

```text
ClassCastException
```

Example:

```java
TreeMap<Object, String> map =
        new TreeMap<>();

map.put(new Object(), "A");
```

The map has no natural ordering for arbitrary `Object` instances.

### Rule

```text
No Comparator
+
No compatible natural ordering
=
ClassCastException
```

---

# 39. Is TreeMap Ordering Consistent with equals()?

### Answer

Ideally, yes.

However, TreeMap determines key equivalence using its ordering:

```java
compare(k1, k2) == 0
```

not directly using `equals()`.

Therefore it is possible to have:

```text
k1.equals(k2) == false
```

while:

```text
compare(k1, k2) == 0
```

In that case, TreeMap treats them as the same key for map operations.

### Best Practice

Design the ordering so that:

```text
compare(a, b) == 0
```

is consistent with:

```text
a.equals(b)
```

whenever practical.

---

# 40. TreeMap vs HashMap

| Feature | TreeMap | HashMap |
|---|---|---|
| Ordering | Sorted | No guaranteed order |
| Internal structure | Red-black tree | Hash table |
| Basic lookup | O(log n) | O(1) expected |
| Range queries | Yes | No |
| Floor/ceiling | Yes | No |
| Reverse sorted view | Yes | No equivalent |
| Null key | Not with natural ordering | Yes |
| Thread-safe | No | No |

### Mental Model

```text
HashMap
→ Fast average lookup

TreeMap
→ Sorted keys + navigation
```

---

# 41. TreeMap vs LinkedHashMap

| Feature | TreeMap | LinkedHashMap |
|---|---|---|
| Ordering | Sorted | Insertion/access |
| Basic lookup | O(log n) | O(1) expected |
| Range operations | Yes | No |
| Floor/ceiling | Yes | No |
| Null key | Not with natural ordering | Yes |
| Internal structure | Red-black tree | Hash table + linked structure |

### Mental Model

```text
LinkedHashMap
→ Keep a predictable traversal order

TreeMap
→ Keep keys sorted
```

---

# 42. TreeMap vs Hashtable

| Feature | TreeMap | Hashtable |
|---|---|---|
| Ordering | Sorted | No guaranteed sorted order |
| Lookup | O(log n) | O(1) expected |
| Thread-safe | No | Synchronized legacy class |
| Null key | Not with natural ordering | No |
| Null value | Yes | No |
| Range queries | Yes | No |

---

# 43. TreeMap vs ConcurrentHashMap

| Feature | TreeMap | ConcurrentHashMap |
|---|---|---|
| Primary purpose | Sorted map | Concurrent map |
| Basic lookup | O(log n) | O(1) expected |
| Sorted keys | Yes | No |
| Range operations | Yes | No sorted-map equivalent |
| Thread-safe | No | Yes, designed for concurrency |
| Null key | Not with natural ordering | No |
| Null values | Yes | No |

---

# 44. TreeMap vs TreeSet

Both are tree-based sorted collections.

| Feature | TreeMap | TreeSet |
|---|---|---|
| Stores | Key-value pairs | Unique elements |
| Interface | `NavigableMap` | `NavigableSet` |
| Sorted | Keys | Elements |
| Duplicate | Keys not allowed | Elements not allowed |
| Typical use | Mapping + navigation | Unique sorted elements |

A useful implementation-level mental model:

```text
TreeSet
   |
   v
TreeMap
   |
element -> dummy value
```

---

# 45. Why Not Use HashMap and Sort Later?

### Answer

Because sorting after every update can be inefficient when you need sorted access continuously.

Suppose data changes frequently.

With HashMap:

```text
Insert
   ↓
Sort everything
   ↓
Query
```

With TreeMap:

```text
Insert
   ↓
TreeMap maintains order
   ↓
Query sorted data
```

TreeMap is especially useful when you repeatedly need:

```text
minimum
maximum
floor
ceiling
range
predecessor
successor
```

---

# 46. Common TreeMap Use Cases

## 46.1 Range Queries

```java
map.subMap(start, end);
```

---

## 46.2 Floor / Ceiling Search

```java
map.floorKey(x);
map.ceilingKey(x);
```

---

## 46.3 Timestamp Data

```text
timestamp -> event
```

Useful for finding:

```text
previous event
next event
nearest event
```

---

## 46.4 Scheduling

```text
time -> task
```

The smallest timestamp can represent the next task to process.

---

## 46.5 Price Matching

```text
price -> product
```

Useful for finding:

```text
highest price <= budget
lowest price >= budget
```

---

## 46.6 Ordered Statistics

Useful when data must remain dynamically sorted while insertions and deletions occur.

---

# 47. Interview Scenario: Find Nearest Value

### Problem

Keys:

```text
10, 20, 30, 40, 50
```

Target:

```text
34
```

### Approach

```java
Integer lower = map.floorKey(34);
Integer higher = map.ceilingKey(34);
```

Results:

```text
lower  = 30
higher = 40
```

Then compare:

```text
|34 - 30| = 4
|40 - 34| = 6
```

Nearest:

```text
30
```

### Key Idea

TreeMap gives both neighboring candidates efficiently.

---

# 48. Interview Scenario: Range Query

### Problem

Store:

```text
100 -> A
200 -> B
300 -> C
400 -> D
500 -> E
```

Find entries from:

```text
200 through 400
```

Use:

```java
map.subMap(200, true, 400, true);
```

Result:

```text
200 -> B
300 -> C
400 -> D
```

### Complexity

Finding the range boundaries is logarithmic, followed by traversal proportional to the number of returned entries.

---

# 49. Interview Scenario: Timestamp Lookup

Suppose:

```text
1000 -> Login
1500 -> Dashboard
2000 -> Profile
2500 -> Logout
```

Query:

```text
1800
```

Previous event:

```java
map.floorEntry(1800L);
```

Result:

```text
1500 -> Dashboard
```

Next event:

```java
map.ceilingEntry(1800L);
```

Result:

```text
2000 -> Profile
```

This pattern is extremely useful for:

```text
Time-based lookup
Scheduling
Event processing
Nearest-value queries
```

---

# 50. Common Mistakes

## Mistake 1 — Thinking TreeMap Uses Hashing

It does not.

```text
HashMap
→ Hash table

TreeMap
→ Red-black tree
```

---

## Mistake 2 — Assuming TreeMap Is O(1)

Incorrect.

Basic key operations are typically:

```text
O(log n)
```

---

## Mistake 3 — Thinking TreeMap Preserves Insertion Order

It does not.

It maintains sorted key order.

---

## Mistake 4 — Thinking TreeMap Sorts Values

No.

TreeMap sorts keys.

---

## Mistake 5 — Ignoring Comparator Semantics

If:

```java
compare(a, b) == 0
```

TreeMap treats the keys as equivalent for map operations.

---

## Mistake 6 — Using an Inconsistent Comparator

A Comparator that considers distinct logical keys equivalent can cause mappings to replace each other.

---

## Mistake 7 — Using Non-Comparable Keys Without a Comparator

This can result in:

```text
ClassCastException
```

when TreeMap needs to compare the keys.

---

## Mistake 8 — Assuming subMap() Creates a Copy

It returns a view backed by the original map.

---

## Mistake 9 — Assuming TreeMap Is Thread-Safe

It is not.

---

## Mistake 10 — Forgetting the Difference Between Navigation Methods

Remember:

```text
lower   → <
floor   → <=
ceiling → >=
higher  → >
```

---

# 51. Rapid-Fire Revision

### Q1. What is TreeMap?

```text
A sorted Map implementation based on a red-black tree.
```

---

### Q2. What interface does TreeMap implement?

```text
NavigableMap
```

---

### Q3. Does TreeMap maintain sorted order?

```text
Yes
```

---

### Q4. What is the default ordering?

```text
Natural ordering
```

when no Comparator is supplied.

---

### Q5. Can you customize the ordering?

```text
Yes — using Comparator
```

---

### Q6. What is the internal data structure?

```text
Red-black tree
```

---

### Q7. What is the complexity of `get()`?

```text
O(log n)
```

---

### Q8. What is the complexity of `put()`?

```text
O(log n)
```

---

### Q9. Are duplicate keys allowed?

```text
No
```

---

### Q10. Are duplicate values allowed?

```text
Yes
```

---

### Q11. Does TreeMap allow null values?

```text
Yes
```

---

### Q12. Does TreeMap allow a null key?

```text
Not with natural ordering.
```

A custom comparator can define null handling.

---

### Q13. Is TreeMap thread-safe?

```text
No
```

---

### Q14. What is `lowerKey(x)`?

```text
Greatest key < x
```

---

### Q15. What is `floorKey(x)`?

```text
Greatest key <= x
```

---

### Q16. What is `ceilingKey(x)`?

```text
Smallest key >= x
```

---

### Q17. What is `higherKey(x)`?

```text
Smallest key > x
```

---

### Q18. How do you get the smallest key?

```java
map.firstKey();
```

---

### Q19. How do you get the largest key?

```java
map.lastKey();
```

---

### Q20. How do you get the smallest complete mapping?

```java
map.firstEntry();
```

---

### Q21. How do you remove the smallest mapping?

```java
map.pollFirstEntry();
```

---

### Q22. How do you get a range of entries?

```java
map.subMap(...);
```

---

### Q23. What does `headMap()` return?

```text
Entries before a boundary
```

---

### Q24. What does `tailMap()` return?

```text
Entries from a boundary onward
```

---

### Q25. How do you get reverse sorted order?

```java
map.descendingMap();
```

---

### Q26. What happens when Comparator returns 0?

```text
TreeMap treats the keys as equivalent for map operations.
```

---

### Q27. Can TreeMap use custom objects as keys?

```text
Yes, if they have a valid ordering.
```

---

### Q28. What happens if keys are not mutually comparable?

```text
ClassCastException
```

can occur when comparison is required.

---

### Q29. Does TreeMap use equals() to determine key equivalence?

Primarily the map's ordering mechanism determines equivalence:

```text
compare(k1, k2) == 0
```

---

### Q30. HashMap or TreeMap?

```text
Fast average lookup
    → HashMap

Sorted keys / navigation / ranges
    → TreeMap
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
        +-----------------+-----------------+
        |                 |                 |
      Search           Navigation        Ranges
        |                 |                 |
      get()          lower / floor      subMap()
      put()          ceiling / higher   headMap()
      remove()                           tailMap()
```

### Navigation Cheat Sheet

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

### Map Selection Cheat Sheet

```text
HashMap
→ Fast expected lookup
→ No guaranteed order

LinkedHashMap
→ Predictable insertion/access order
→ Fast expected lookup

TreeMap
→ Sorted keys
→ O(log n)
→ Navigation
→ Range queries
```

### Core TreeMap Formula

```text
Sorted Map
+
Red-Black Tree
+
NavigableMap
=
TreeMap
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
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

> **`19-TreeMap` is now complete: `NOTES.md` + `PRACTICE.md` + `INTERVIEW.md`.**
>
> **Next: `20-Hashtable/NOTES.md`**.
