# HashMap — Java Collections Framework

> A practical and interview-ready guide to `HashMap`, including hashing, collisions, resizing, `equals()`/`hashCode()`, modern Java internals, and common DSA use cases.

---

## Table of Contents

- [1. What is HashMap?](#1-what-is-hashmap)
- [2. Why HashMap is Important](#2-why-hashmap-is-important)
- [3. HashMap Hierarchy](#3-hashmap-hierarchy)
- [4. Key Features](#4-key-features)
- [5. Creating a HashMap](#5-creating-a-hashmap)
- [6. Basic Operations](#6-basic-operations)
- [7. put()](#7-put)
- [8. get()](#8-get)
- [9. Updating an Existing Key](#9-updating-an-existing-key)
- [10. remove()](#10-remove)
- [11. containsKey() and containsValue()](#11-containskey-and-containsvalue)
- [12. size(), isEmpty(), clear()](#12-size-isempty-clear)
- [13. getOrDefault()](#13-getordefault)
- [14. putIfAbsent()](#14-putifabsent)
- [15. replace() and replaceAll()](#15-replace-and-replaceall)
- [16. computeIfAbsent()](#16-computeifabsent)
- [17. computeIfPresent()](#17-computeifpresent)
- [18. compute()](#18-compute)
- [19. merge()](#19-merge)
- [20. keySet(), values(), entrySet()](#20-keyset-values-entryset)
- [21. Map.Entry](#21-mapentry)
- [22. Iterating Through HashMap](#22-iterating-through-hashmap)
- [23. Null Keys and Null Values](#23-null-keys-and-null-values)
- [24. Duplicate Keys and Values](#24-duplicate-keys-and-values)
- [25. Hashing](#25-hashing)
- [26. hashCode()](#26-hashcode)
- [27. equals()](#27-equals)
- [28. How HashMap Works Internally](#28-how-hashmap-works-internally)
- [29. Buckets](#29-buckets)
- [30. Hash Collisions](#30-hash-collisions)
- [31. Collision Handling](#31-collision-handling)
- [32. Tree Bins](#32-tree-bins)
- [33. Capacity](#33-capacity)
- [34. Load Factor](#34-load-factor)
- [35. Threshold](#35-threshold)
- [36. Resizing](#36-resizing)
- [37. Default Capacity and Load Factor](#37-default-capacity-and-load-factor)
- [38. HashMap Complexity](#38-hashmap-complexity)
- [39. Java 8+ Improvements](#39-java-8-improvements)
- [40. HashMap with Custom Objects](#40-hashmap-with-custom-objects)
- [41. equals() and hashCode() Contract](#41-equals-and-hashcode-contract)
- [42. Mutable Keys](#42-mutable-keys)
- [43. Common DSA Use Cases](#43-common-dsa-use-cases)
- [44. HashMap vs Other Maps](#44-hashmap-vs-other-maps)
- [45. Common Mistakes](#45-common-mistakes)
- [46. Interview Quick Revision](#46-interview-quick-revision)
- [47. Progress](#47-progress)

---

# 1. What is HashMap?

`HashMap` is a class in Java that stores data as **key-value pairs**.

```java
HashMap<K, V>
```

Example:

```java
HashMap<Integer, String> students = new HashMap<>();

students.put(101, "Mahesh");
students.put(102, "Rahul");
students.put(103, "Amit");
```

Conceptually:

```text
Key     Value
101  -> Mahesh
102  -> Rahul
103  -> Amit
```

A key is used to efficiently locate its associated value.

---

# 2. Why HashMap is Important

`HashMap` is one of the most frequently used collections in Java.

It is useful when you need:

- Fast key-based lookup
- Key-value relationships
- Frequency counting
- Grouping
- Caching
- Memoization
- Duplicate detection
- Fast membership checks
- Mapping IDs to objects
- Graph representations
- DSA problem solving

Example:

```java
HashMap<String, Integer> marks = new HashMap<>();

marks.put("Math", 85);
marks.put("DBMS", 90);
marks.put("DSA", 95);

System.out.println(marks.get("DSA"));
```

Output:

```text
95
```

---

# 3. HashMap Hierarchy

```text
Iterable
   |
Collection
   |
   +----------------+
                    |
                  Map
                    |
              AbstractMap
                    |
                 HashMap
```

More precisely:

```text
Map<K,V>
   ↑
AbstractMap<K,V>
   ↑
HashMap<K,V>
```

`HashMap` implements the `Map` interface.

```java
public class HashMap<K,V>
    extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable
```

---

# 4. Key Features

| Feature | HashMap |
|---|---|
| Stores | Key-value pairs |
| Duplicate keys | Not allowed |
| Duplicate values | Allowed |
| `null` key | One allowed |
| `null` values | Multiple allowed |
| Ordering | No guaranteed order |
| Thread-safe | No |
| Average lookup | O(1) expected |
| Average insertion | O(1) expected |
| Allows generics | Yes |
| Uses hashing | Yes |

Example:

```java
HashMap<Integer, String> map = new HashMap<>();

map.put(1, "Java");
map.put(2, "Python");
map.put(3, "C++");
```

---

# 5. Creating a HashMap

## 5.1 Using Default Constructor

```java
HashMap<Integer, String> map = new HashMap<>();
```

---

## 5.2 With Initial Capacity

```java
HashMap<Integer, String> map = new HashMap<>(32);
```

---

## 5.3 With Capacity and Load Factor

```java
HashMap<Integer, String> map =
        new HashMap<>(32, 0.75f);
```

---

## 5.4 From Another Map

```java
Map<Integer, String> oldMap = new HashMap<>();

oldMap.put(1, "Java");
oldMap.put(2, "Python");

HashMap<Integer, String> newMap =
        new HashMap<>(oldMap);
```

---

# 6. Basic Operations

```java
HashMap<Integer, String> map = new HashMap<>();

map.put(1, "Java");
map.put(2, "Python");

String value = map.get(1);

map.remove(2);

boolean exists = map.containsKey(1);

int size = map.size();
```

Common methods:

| Method | Purpose |
|---|---|
| `put()` | Add/update mapping |
| `get()` | Get value |
| `remove()` | Remove mapping |
| `containsKey()` | Check key |
| `containsValue()` | Check value |
| `size()` | Number of mappings |
| `isEmpty()` | Check whether empty |
| `clear()` | Remove everything |
| `getOrDefault()` | Get value or default |
| `putIfAbsent()` | Add only if absent |
| `replace()` | Replace value |
| `compute()` | Compute value |
| `merge()` | Merge values |

---

# 7. put()

Adds a key-value mapping.

```java
HashMap<Integer, String> map = new HashMap<>();

map.put(1, "Java");
map.put(2, "Python");
```

Now:

```text
1 -> Java
2 -> Python
```

### Return Value

`put()` returns the previous value associated with the key.

```java
String old = map.put(1, "C++");

System.out.println(old);
```

Output:

```text
Java
```

If the key did not previously exist:

```java
String old = map.put(3, "Go");

System.out.println(old);
```

Output:

```text
null
```

> Note: `null` alone cannot tell you whether the key was absent or previously mapped to `null`. Use `containsKey()` when that distinction matters.

---

# 8. get()

Retrieves a value using its key.

```java
HashMap<Integer, String> map = new HashMap<>();

map.put(101, "Mahesh");

System.out.println(map.get(101));
```

Output:

```text
Mahesh
```

If the key does not exist:

```java
System.out.println(map.get(999));
```

Output:

```text
null
```

Important:

```java
map.get(key)
```

does not throw an exception simply because the key is missing.

---

# 9. Updating an Existing Key

Adding an existing key replaces its value.

```java
HashMap<Integer, String> map = new HashMap<>();

map.put(1, "Java");
map.put(1, "Spring Boot");
```

Result:

```text
1 -> Spring Boot
```

The key remains the same.

Only its value changes.

---

# 10. remove()

Removes a mapping using its key.

```java
HashMap<Integer, String> map = new HashMap<>();

map.put(1, "Java");
map.put(2, "Python");

map.remove(1);
```

Remaining:

```text
2 -> Python
```

`remove()` returns the removed value.

```java
String removed = map.remove(2);

System.out.println(removed);
```

Output:

```text
Python
```

---

# 11. containsKey() and containsValue()

## containsKey()

Checks whether a key exists.

```java
if (map.containsKey(101)) {
    System.out.println("Student exists");
}
```

---

## containsValue()

Checks whether a value exists.

```java
if (map.containsValue("Mahesh")) {
    System.out.println("Found");
}
```

### Important Difference

```java
containsKey()
```

is generally much more efficient than:

```java
containsValue()
```

because HashMap is organized around keys.

Typical expected complexity:

```text
containsKey()    -> O(1) expected
containsValue()  -> O(n)
```

---

# 12. size(), isEmpty(), clear()

## size()

```java
System.out.println(map.size());
```

Returns the number of key-value mappings.

---

## isEmpty()

```java
if (map.isEmpty()) {
    System.out.println("Map is empty");
}
```

---

## clear()

Removes all mappings.

```java
map.clear();
```

After:

```java
map.isEmpty()
```

returns:

```text
true
```

---

# 13. getOrDefault()

Returns the mapped value if the key exists; otherwise returns the supplied default.

```java
HashMap<String, Integer> marks = new HashMap<>();

marks.put("DSA", 95);

System.out.println(marks.getOrDefault("DSA", 0));
System.out.println(marks.getOrDefault("DBMS", 0));
```

Output:

```text
95
0
```

### Important

If the key exists and its value is `null`, `getOrDefault()` returns `null`, not the default.

```java
map.put("Java", null);

System.out.println(
    map.getOrDefault("Java", "Unknown")
);
```

Output:

```text
null
```

---

# 14. putIfAbsent()

Adds a mapping only when the key is absent according to the `Map` contract.

```java
HashMap<Integer, String> map = new HashMap<>();

map.putIfAbsent(1, "Java");
map.putIfAbsent(1, "Python");
```

Result:

```text
1 -> Java
```

The second call does not replace the existing non-null value.

### Null Detail

For the default `Map` behavior, a key mapped to `null` is treated as absent by `putIfAbsent()`.

```java
map.put(1, null);

map.putIfAbsent(1, "Java");
```

Now:

```text
1 -> Java
```

---

# 15. replace() and replaceAll()

## replace()

```java
map.replace(1, "Spring Boot");
```

Only replaces the value if the key exists.

---

## Conditional replace

```java
map.replace(1, "Java", "Spring Boot");
```

This replaces:

```text
Java -> Spring Boot
```

only if the current value is `"Java"`.

---

## replaceAll()

Updates every mapping using a function.

```java
HashMap<String, Integer> marks = new HashMap<>();

marks.put("DSA", 80);
marks.put("DBMS", 70);

marks.replaceAll((subject, mark) -> mark + 5);
```

Result:

```text
DSA  -> 85
DBMS -> 75
```

---

# 16. computeIfAbsent()

Computes a value only when the key is absent or mapped to `null`.

```java
HashMap<String, Integer> map = new HashMap<>();

map.computeIfAbsent("Java", key -> key.length());
```

Result:

```text
Java -> 4
```

A common use case is grouping.

```java
HashMap<String, List<String>> groups = new HashMap<>();

groups.computeIfAbsent("Backend", key -> new ArrayList<>())
      .add("Java");
```

This avoids manually checking:

```java
if (!groups.containsKey("Backend")) {
    groups.put("Backend", new ArrayList<>());
}
```

---

# 17. computeIfPresent()

Computes a new value only when the key is present and mapped to a non-null value.

```java
HashMap<String, Integer> map = new HashMap<>();

map.put("Java", 10);

map.computeIfPresent(
    "Java",
    (key, value) -> value + 5
);
```

Result:

```text
Java -> 15
```

---

# 18. compute()

`compute()` recalculates the value for a key whether or not a mapping currently exists.

```java
map.compute(
    "Java",
    (key, value) -> value == null ? 1 : value + 1
);
```

This can be useful when the update logic depends on both the key and current value.

---

# 19. merge()

`merge()` is especially useful for frequency counting.

```java
HashMap<String, Integer> frequency = new HashMap<>();

frequency.merge("Java", 1, Integer::sum);
frequency.merge("Java", 1, Integer::sum);
frequency.merge("Python", 1, Integer::sum);
```

Result:

```text
Java   -> 2
Python -> 1
```

Equivalent common pattern:

```java
map.put(
    key,
    map.getOrDefault(key, 0) + 1
);
```

---

# 20. keySet(), values(), entrySet()

HashMap provides three important views.

## keySet()

Returns all keys.

```java
for (Integer key : map.keySet()) {
    System.out.println(key);
}
```

---

## values()

Returns all values.

```java
for (String value : map.values()) {
    System.out.println(value);
}
```

Values can contain duplicates.

---

## entrySet()

Returns key-value mappings.

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(
        entry.getKey() + " -> " + entry.getValue()
    );
}
```

### Which Should You Use?

If you need both key and value:

```java
entrySet()
```

is generally preferred.

Avoid unnecessarily doing:

```java
for (Integer key : map.keySet()) {
    System.out.println(map.get(key));
}
```

when you can directly use:

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(entry.getValue());
}
```

---

# 21. Map.Entry

`Map.Entry<K,V>` represents a single key-value mapping.

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {

    Integer key = entry.getKey();
    String value = entry.getValue();

    System.out.println(key + " -> " + value);
}
```

Important methods:

```java
entry.getKey();
entry.getValue();
entry.setValue(newValue);
```

---

# 22. Iterating Through HashMap

## Method 1 — keySet()

```java
for (Integer key : map.keySet()) {
    System.out.println(key);
}
```

---

## Method 2 — values()

```java
for (String value : map.values()) {
    System.out.println(value);
}
```

---

## Method 3 — entrySet()

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(
        entry.getKey() + " -> " + entry.getValue()
    );
}
```

---

## Method 4 — forEach()

```java
map.forEach((key, value) -> {
    System.out.println(key + " -> " + value);
});
```

### Important

HashMap does **not** guarantee iteration order.

Do not write code that depends on the order in which entries happen to appear.

---

# 23. Null Keys and Null Values

HashMap allows:

- One `null` key
- Multiple `null` values

Example:

```java
HashMap<String, String> map = new HashMap<>();

map.put(null, "Unknown");
map.put("Java", null);
map.put("Python", null);
```

Valid.

Conceptually:

```text
null   -> Unknown
Java   -> null
Python -> null
```

You cannot have multiple distinct `null` keys because keys must be unique.

```java
map.put(null, "A");
map.put(null, "B");
```

Result:

```text
null -> B
```

---

# 24. Duplicate Keys and Values

## Duplicate Keys

Not allowed.

```java
map.put(1, "Java");
map.put(1, "Python");
```

Final:

```text
1 -> Python
```

The value is replaced.

---

## Duplicate Values

Allowed.

```java
map.put(1, "Java");
map.put(2, "Java");
```

Valid:

```text
1 -> Java
2 -> Java
```

The uniqueness requirement applies to keys, not values.

---

# 25. Hashing

Hashing is the process of converting a key into a hash value that helps determine where the key-value pair should be stored.

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

For example:

```java
String key = "Java";

int hash = key.hashCode();
```

Hashing allows HashMap to avoid searching every entry for every lookup.

---

# 26. hashCode()

Every Java object inherits `hashCode()` from `Object`, although many classes override it.

```java
String s = "Java";

System.out.println(s.hashCode());
```

`hashCode()` returns an `int`.

Important rules:

### Rule 1

If two objects are equal according to `equals()`, they **must** have the same hash code.

```text
a.equals(b) == true
        ↓
a.hashCode() == b.hashCode()
```

### Rule 2

Different objects may have the same hash code.

```text
a.hashCode() == b.hashCode()
```

does **not** necessarily mean:

```text
a.equals(b)
```

This is called a collision.

---

# 27. equals()

`equals()` determines whether two objects should be considered equal.

HashMap uses both:

```text
hashCode()
+
equals()
```

to identify keys.

Conceptually:

```text
Same hash?
    |
    +-- No --> Different bucket
    |
    +-- Yes
          |
          v
      equals()?
          |
       +--+--+
       |     |
      Yes    No
       |     |
    Same     Collision
    key
```

This is why custom key classes must correctly implement both methods.

---

# 28. How HashMap Works Internally

A simplified view:

```text
                 HashMap
                    |
                  Table
                    |
      +-------------+-------------+
      |             |             |
   Bucket 0      Bucket 1      Bucket 2
      |             |             |
    Node          Node          Node
                                  |
                                Node
```

When you execute:

```java
map.put(key, value);
```

HashMap roughly performs:

```text
1. Calculate hash from key
2. Determine bucket
3. Check existing entries in that bucket
4. Compare hash values
5. If necessary, compare keys using equals()
6. Insert or update the mapping
```

For:

```java
map.get(key);
```

the process is conceptually:

```text
key
 ↓
hash
 ↓
bucket
 ↓
find matching key
 ↓
return value
```

---

# 29. Buckets

Internally, HashMap maintains a table of buckets.

A simplified representation:

```text
table
 |
 +-- bucket[0]
 +-- bucket[1]
 +-- bucket[2]
 +-- bucket[3]
 +-- ...
```

Each bucket can contain one or more entries.

A bucket may contain multiple entries because different keys can map to the same bucket.

---

# 30. Hash Collisions

A collision occurs when different keys end up targeting the same bucket.

Example:

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

Even though:

```text
A != B
```

they may still belong to the same bucket.

Collisions are normal in hash-based data structures.

A good hashing strategy attempts to distribute keys evenly across buckets.

---

# 31. Collision Handling

HashMap must handle multiple entries in the same bucket.

Historically, collisions were handled using linked structures.

Conceptually:

```text
Bucket 5
   |
   v
Node A
   |
   v
Node B
   |
   v
Node C
```

When searching, HashMap checks candidate entries until it finds the matching key.

It compares:

```text
hash
+
equals()
```

to identify the correct key.

---

# 32. Tree Bins

Modern Java HashMap implementations can convert a heavily populated bucket into a **red-black tree** when certain implementation thresholds are reached.

Simplified:

```text
Before:

Bucket
  |
Node
  |
Node
  |
Node
  |
Node
```

Potentially becomes:

```text
        Node
       /    \
    Node    Node
    /  \      \
 Node  Node   Node
```

This improves lookup behavior in collision-heavy buckets.

Common OpenJDK implementation constants include:

```text
TREEIFY_THRESHOLD      = 8
UNTREEIFY_THRESHOLD    = 6
MIN_TREEIFY_CAPACITY   = 64
```

These are implementation details, not API guarantees.

### Important Interview Point

Java 8 introduced tree bins to improve HashMap's behavior when many entries collide into the same bucket.

---

# 33. Capacity

Capacity represents the number of buckets available in the hash table.

Example:

```java
HashMap<Integer, String> map =
        new HashMap<>(32);
```

The requested initial capacity is used when constructing the map, with HashMap's internal capacity management rules.

In normal HashMap operation, the internal table capacity is maintained as a power of two.

Example capacities:

```text
16
32
64
128
256
...
```

---

# 34. Load Factor

Load factor determines how full the hash table is allowed to become before resizing.

The default load factor is:

```text
0.75
```

Conceptually:

```text
Threshold = Capacity × Load Factor
```

For example:

```text
Capacity = 16
Load Factor = 0.75

Threshold ≈ 16 × 0.75
           = 12
```

When the number of mappings exceeds the threshold, HashMap may resize.

---

# 35. Threshold

The threshold determines approximately when HashMap should resize.

Example:

```text
Capacity = 16
Load Factor = 0.75

Threshold = 12
```

When the map grows beyond the applicable threshold:

```text
Resize
  ↓
Increase capacity
  ↓
Redistribute entries
```

---

# 36. Resizing

When a HashMap becomes sufficiently full, it increases its table capacity.

Simplified example:

```text
Before:

Capacity = 16
Threshold ≈ 12

        ↓ resize

After:

Capacity = 32
Threshold ≈ 24
```

Entries are redistributed across the new table.

Modern HashMap implementations optimize this redistribution by using the relationship between the old and new capacity rather than simply recomputing everything from scratch.

### Why Resize?

Without resizing, too many entries would accumulate in buckets, increasing collision costs.

---

# 37. Default Capacity and Load Factor

For the standard no-argument constructor:

```java
HashMap<Integer, String> map = new HashMap<>();
```

the standard default configuration uses:

```text
Initial capacity setting: 16
Load factor: 0.75
```

A useful distinction:

> The table may be allocated lazily; constructing an empty HashMap does not necessarily mean a 16-element internal table is immediately allocated.

---

# 38. HashMap Complexity

| Operation | Expected Complexity |
|---|---:|
| `put()` | O(1) |
| `get()` | O(1) |
| `remove()` | O(1) |
| `containsKey()` | O(1) |
| `containsValue()` | O(n) |
| `size()` | O(1) |
| `isEmpty()` | O(1) |
| `clear()` | O(n) |
| Iteration | O(n) |

The O(1) values are **expected/average-case**, assuming good hash distribution.

In collision-heavy situations, performance can degrade.

Modern Java's tree bins improve worst-case behavior for sufficiently large collision-heavy buckets.

---

# 39. Java 8+ Improvements

Java 8 made an important improvement to HashMap collision handling.

### Before

Collision-heavy buckets were primarily handled using linked structures.

### Java 8+

A sufficiently collision-heavy bucket can be transformed into a red-black tree.

```text
Linked structure
       ↓
Red-black tree
```

This helps avoid extremely poor lookup behavior when many keys collide.

---

# 40. HashMap with Custom Objects

You can use your own class as a HashMap key.

Example:

```java
class Student {

    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Student))
            return false;

        Student other = (Student) obj;

        return id == other.id &&
               Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
```

Usage:

```java
HashMap<Student, String> map = new HashMap<>();

Student s1 = new Student(101, "Mahesh");

map.put(s1, "Computer Science");
```

For custom keys, correct `equals()` and `hashCode()` implementation is essential.

---

# 41. equals() and hashCode() Contract

The fundamental contract is:

```text
If a.equals(b) == true
        ↓
a.hashCode() == b.hashCode()
```

But:

```text
a.hashCode() == b.hashCode()
```

does NOT guarantee:

```text
a.equals(b) == true
```

because collisions are possible.

### Example

```text
Object A
hash = 100

Object B
hash = 100
```

They may still be different keys:

```java
A.equals(B) == false
```

HashMap uses `equals()` to distinguish such keys.

---

# 42. Mutable Keys

Using mutable objects as HashMap keys can cause serious problems.

Example:

```java
class Student {
    int id;
    String name;
}
```

Suppose `id` and `name` are used in:

```java
hashCode()
equals()
```

and then:

```java
Student student = new Student(101, "Mahesh");

map.put(student, "Java");
```

Later:

```java
student.name = "Rahul";
```

If the changed field affects `hashCode()`, the object may no longer be found in the bucket where it was originally stored.

```java
map.get(student);
```

may unexpectedly return:

```text
null
```

### Best Practice

Prefer immutable keys.

Common safe keys include:

```text
String
Integer
Long
UUID
```

and properly designed immutable custom objects.

---

# 43. Common DSA Use Cases

HashMap is extremely common in DSA.

## 43.1 Frequency Counting

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

---

## 43.2 Duplicate Detection

```java
HashMap<Integer, Boolean> seen = new HashMap<>();

for (int x : arr) {

    if (seen.containsKey(x)) {
        System.out.println("Duplicate");
    }

    seen.put(x, true);
}
```

A `HashSet` is often simpler when you only need membership.

---

## 43.3 Two Sum

Typical idea:

```text
For every number x:

target = targetSum - x

Check whether target exists in HashMap.
```

Example pattern:

```java
HashMap<Integer, Integer> map = new HashMap<>();

for (int i = 0; i < nums.length; i++) {

    int complement = target - nums[i];

    if (map.containsKey(complement)) {
        // answer found
    }

    map.put(nums[i], i);
}
```

Expected time:

```text
O(n)
```

---

## 43.4 Prefix Sum

HashMap can store prefix sums and their frequencies or indices.

Common applications include:

- Subarray sum
- Count of subarrays
- Zero-sum subarray
- Longest subarray problems

---

## 43.5 Memoization

Store already-computed results.

```java
HashMap<Integer, Integer> memo = new HashMap<>();
```

Conceptually:

```text
Input
 ↓
Already calculated?
 ├── Yes → Return stored result
 └── No  → Calculate → Store
```

---

## 43.6 Grouping

```java
HashMap<String, List<String>> groups = new HashMap<>();

groups.computeIfAbsent(
    "Backend",
    key -> new ArrayList<>()
).add("Java");
```

---

# 44. HashMap vs Other Maps

## HashMap vs LinkedHashMap

| Feature | HashMap | LinkedHashMap |
|---|---|---|
| Ordering | No guarantee | Maintains insertion/access order depending on configuration |
| Performance | Usually slightly simpler/faster | Slight overhead for ordering |
| Null key | Yes | Yes |
| Thread-safe | No | No |
| Use case | General lookup | Lookup + predictable order |

---

## HashMap vs TreeMap

| Feature | HashMap | TreeMap |
|---|---|---|
| Ordering | No guarantee | Sorted |
| Structure | Hash table | Red-black tree |
| Average basic lookup | O(1) expected | O(log n) |
| `null` key | Allowed | Not allowed with natural ordering |
| Range operations | No | Yes |
| Interface | Map | NavigableMap |

Use `TreeMap` when sorted/range-based operations matter.

---

## HashMap vs Hashtable

| Feature | HashMap | Hashtable |
|---|---|---|
| Thread-safe | No | Synchronized |
| `null` key | Yes | No |
| `null` value | Yes | No |
| Modern choice | Usually preferred for general use | Legacy |

For concurrent applications, `ConcurrentHashMap` is generally a more modern option than `Hashtable`.

---

## HashMap vs ConcurrentHashMap

| Feature | HashMap | ConcurrentHashMap |
|---|---|---|
| Thread-safe | No | Designed for concurrent access |
| `null` key | Yes | No |
| `null` value | Yes | No |
| Concurrent operations | No | Yes |
| Typical use | Single-threaded/general use | Concurrent applications |

---

# 45. Common Mistakes

## Mistake 1 — Assuming Order

Wrong assumption:

```java
HashMap preserves insertion order.
```

It does not guarantee this.

Use:

```java
LinkedHashMap
```

when predictable insertion/access order is required.

---

## Mistake 2 — Using Mutable Keys

Changing fields involved in `equals()`/`hashCode()` after insertion can make a key difficult to retrieve.

---

## Mistake 3 — Overriding equals() Without hashCode()

Bad:

```java
@Override
public boolean equals(Object obj) {
    ...
}
```

without a matching:

```java
@Override
public int hashCode() {
    ...
}
```

This breaks the contract required by hash-based collections.

---

## Mistake 4 — Assuming Same Hash Means Same Object

This is incorrect:

```text
same hashCode() ≠ necessarily equal objects
```

Collisions are possible.

---

## Mistake 5 — Confusing Missing Key with Null Value

These can both produce:

```java
map.get(key) == null
```

Cases:

```text
Key does not exist
        OR
Key exists and maps to null
```

Use:

```java
map.containsKey(key)
```

to distinguish them.

---

## Mistake 6 — Using keySet() + get() Unnecessarily

Instead of:

```java
for (Integer key : map.keySet()) {
    System.out.println(map.get(key));
}
```

prefer:

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(entry.getValue());
}
```

when both key and value are required.

---

# 46. Interview Quick Revision

### What is HashMap?

A hash-table-based `Map` implementation that stores key-value pairs and provides expected O(1) basic operations.

---

### Does HashMap allow duplicate keys?

No.

Adding an existing key replaces its value.

---

### Does HashMap allow duplicate values?

Yes.

---

### Does HashMap allow null?

Yes.

```text
One null key
Multiple null values
```

---

### Is HashMap synchronized?

No.

It is not thread-safe by default.

---

### Does HashMap maintain insertion order?

No.

There is no guaranteed iteration order.

---

### What is the default load factor?

```text
0.75
```

---

### What is the typical initial capacity?

```text
16
```

for the standard default configuration.

---

### What happens when the map exceeds its threshold?

The table is resized and entries are redistributed.

---

### What is a collision?

When different keys end up targeting the same bucket.

---

### How are collisions handled?

Modern Java HashMap buckets can use linked nodes and, when appropriate, red-black tree bins.

---

### Why are equals() and hashCode() important?

HashMap uses them to correctly locate and distinguish keys.

---

### Can two different objects have the same hash code?

Yes.

That is a collision.

---

### If two objects are equal, must their hash codes be equal?

Yes.

---

### Which is better for both key and value iteration?

Usually:

```java
entrySet()
```

---

### What is the average complexity of get()?

```text
O(1) expected
```

---

### What is the complexity of containsValue()?

```text
O(n)
```

---

### Why should keys ideally be immutable?

Because changing fields used by `equals()`/`hashCode()` after insertion can make the mapping difficult to find.

---

# 47. Progress

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
│   ├── PRACTICE.md    [ ]
│   └── INTERVIEW.md   [ ]
│
├── 18-LinkedHashMap
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

> **`17-HashMap/NOTES.md` completed.**
>
> **Next: `17-HashMap/PRACTICE.md`**.
