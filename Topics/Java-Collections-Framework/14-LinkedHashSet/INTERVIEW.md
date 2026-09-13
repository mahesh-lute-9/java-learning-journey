# LinkedHashSet — Interview Questions

> Interview-focused questions covering `LinkedHashSet`, insertion order, hashing, `equals()`, `hashCode()`, internal working, performance, and practical use cases.

---

## Table of Contents

- [1. What is LinkedHashSet?](#1-what-is-linkedhashset)
- [2. What are the main characteristics of LinkedHashSet?](#2-what-are-the-main-characteristics-of-linkedhashset)
- [3. What is the main difference between HashSet and LinkedHashSet?](#3-what-is-the-main-difference-between-hashset-and-linkedhashset)
- [4. Does LinkedHashSet allow duplicates?](#4-does-linkedhashset-allow-duplicates)
- [5. Does LinkedHashSet maintain insertion order?](#5-does-linkedhashset-maintain-insertion-order)
- [6. What happens when a duplicate is added?](#6-what-happens-when-a-duplicate-is-added)
- [7. Does adding a duplicate move the element to the end?](#7-does-adding-a-duplicate-move-the-element-to-the-end)
- [8. What happens if an element is removed and added again?](#8-what-happens-if-an-element-is-removed-and-added-again)
- [9. Can LinkedHashSet store null?](#9-can-linkedhashset-store-null)
- [10. Is LinkedHashSet thread-safe?](#10-is-linkedhashset-thread-safe)
- [11. What is the time complexity of LinkedHashSet?](#11-what-is-the-time-complexity-of-linkedhashset)
- [12. How does LinkedHashSet work internally?](#12-how-does-linkedhashset-work-internally)
- [13. What is the relationship between LinkedHashSet and LinkedHashMap?](#13-what-is-the-relationship-between-linkedhashset-and-linkedhashmap)
- [14. Why does LinkedHashSet maintain insertion order?](#14-why-does-linkedhashset-maintain-insertion-order)
- [15. What is the difference between insertion order and sorted order?](#15-what-is-the-difference-between-insertion-order-and-sorted-order)
- [16. Does LinkedHashSet sort elements?](#16-does-linkedhashset-sort-elements)
- [17. Does LinkedHashSet provide index-based access?](#17-does-linkedhashset-provide-index-based-access)
- [18. Why doesn't LinkedHashSet have get(index)?](#18-why-doesnt-linkedhashset-have-getindex)
- [19. Why are equals() and hashCode() important?](#19-why-are-equals-and-hashcode-important)
- [20. What happens if equals() is overridden but hashCode() is not?](#20-what-happens-if-equals-is-overridden-but-hashcode-is-not)
- [21. Can LinkedHashSet store custom objects?](#21-can-linkedhashset-store-custom-objects)
- [22. What happens if a mutable object is changed after insertion?](#22-what-happens-if-a-mutable-object-is-changed-after-insertion)
- [23. What is initial capacity?](#23-what-is-initial-capacity)
- [24. What is load factor?](#24-what-is-load-factor)
- [25. Does initial capacity represent maximum size?](#25-does-initial-capacity-represent-maximum-size)
- [26. What does add() return?](#26-what-does-add-return)
- [27. What does remove() return?](#27-what-does-remove-return)
- [28. How can LinkedHashSet be used to remove duplicates while preserving order?](#28-how-can-linkedhashset-be-used-to-remove-duplicates-while-preserving-order)
- [29. HashSet vs LinkedHashSet](#29-hashset-vs-linkedhashset)
- [30. LinkedHashSet vs TreeSet](#30-linkedhashset-vs-treeset)
- [31. LinkedHashSet vs ArrayList](#31-linkedhashset-vs-arraylist)
- [32. LinkedHashSet vs LinkedHashMap](#32-linkedhashset-vs-linkedhashmap)
- [33. When should you use LinkedHashSet?](#33-when-should-you-use-linkedhashset)
- [34. When should you not use LinkedHashSet?](#34-when-should-you-not-use-linkedhashset)
- [35. Can LinkedHashSet be made thread-safe?](#35-can-linkedhashset-be-made-thread-safe)
- [36. Practical Interview Problems](#36-practical-interview-problems)
- [37. Output-Based Questions](#37-output-based-questions)
- [38. Rapid-Fire Questions](#38-rapid-fire-questions)
- [39. Interview Checklist](#39-interview-checklist)
- [40. Final Interview Summary](#40-final-interview-summary)

---

# 1. What is LinkedHashSet?

`LinkedHashSet` is a class in the `java.util` package that implements the `Set` interface.

It stores:

- Unique elements.
- Insertion order.

Example:

```java
Set<String> names = new LinkedHashSet<>();

names.add("Amit");
names.add("Rahul");
names.add("Priya");
```

Iteration occurs in:

```text
Amit
Rahul
Priya
```

order.

---

# 2. What are the main characteristics of LinkedHashSet?

| Feature | LinkedHashSet |
|---|---|
| Implements | `Set` |
| Duplicates | Not allowed |
| Insertion order | Preserved |
| Sorted order | No |
| `null` | One allowed |
| Index access | No |
| Thread-safe | No |
| Hash-based | Yes |
| Typical `add()` | O(1) average |
| Typical `remove()` | O(1) average |
| Typical `contains()` | O(1) average |

---

# 3. What is the main difference between HashSet and LinkedHashSet?

The primary difference is ordering.

### HashSet

```java
Set<Integer> set = new HashSet<>();

set.add(30);
set.add(10);
set.add(20);
```

The iteration order is **not guaranteed**.

### LinkedHashSet

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(30);
set.add(10);
set.add(20);
```

The iteration order is:

```text
30
10
20
```

because insertion order is maintained.

### Interview Answer

> `HashSet` provides uniqueness without guaranteeing iteration order, while `LinkedHashSet` provides uniqueness and maintains insertion order.

---

# 4. Does LinkedHashSet allow duplicates?

No.

```java
Set<Integer> numbers = new LinkedHashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
```

The set contains:

```text
10
20
```

only once.

---

# 5. Does LinkedHashSet maintain insertion order?

Yes.

This is its main advantage over `HashSet`.

Example:

```java
Set<String> set = new LinkedHashSet<>();

set.add("C");
set.add("A");
set.add("B");
```

Iteration order:

```text
C
A
B
```

---

# 6. What happens when a duplicate is added?

The duplicate is ignored.

```java
Set<String> set = new LinkedHashSet<>();

set.add("Java");
set.add("Python");
set.add("Java");
```

Result:

```text
[Java, Python]
```

The second `"Java"` does not change the set.

---

# 7. Does adding a duplicate move the element to the end?

No.

Example:

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(10);
set.add(20);
set.add(30);
set.add(20);

System.out.println(set);
```

Output:

```text
[10, 20, 30]
```

It does not become:

```text
[10, 30, 20]
```

The existing element remains in its original insertion position.

---

# 8. What happens if an element is removed and added again?

If an element is removed and later added again, it becomes a new insertion.

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(10);
set.add(20);
set.add(30);

set.remove(20);
set.add(20);

System.out.println(set);
```

Output:

```text
[10, 30, 20]
```

This is different from adding an already-present duplicate.

---

# 9. Can LinkedHashSet store null?

Yes.

It can contain at most one `null`.

```java
Set<String> set = new LinkedHashSet<>();

set.add(null);
set.add(null);
```

Only one `null` is stored.

Example:

```java
Set<String> set = new LinkedHashSet<>();

set.add("Java");
set.add(null);
set.add("Python");

System.out.println(set);
```

Output:

```text
[Java, null, Python]
```

---

# 10. Is LinkedHashSet thread-safe?

No.

`LinkedHashSet` is not thread-safe.

If multiple threads access and modify the same set concurrently, you need an appropriate synchronization or concurrent collection strategy.

---

# 11. What is the time complexity of LinkedHashSet?

Typical average-case complexity:

| Operation | Average Complexity |
|---|---:|
| `add()` | O(1) |
| `remove()` | O(1) |
| `contains()` | O(1) |
| `size()` | O(1) |
| `isEmpty()` | O(1) |
| `clear()` | O(n) |

These are typical average-case expectations, not absolute guarantees.

The linked structure used to preserve order adds some memory and maintenance overhead compared with `HashSet`.

---

# 12. How does LinkedHashSet work internally?

`LinkedHashSet` combines hash-based storage with a linked structure for maintaining insertion order.

Conceptually:

```text
LinkedHashSet
      │
      ├── Hash table
      │      ↓
      │   Fast lookup
      │
      └── Linked structure
             ↓
        Insertion order
```

The standard implementation is built around `LinkedHashMap`.

---

# 13. What is the relationship between LinkedHashSet and LinkedHashMap?

The standard Java implementation of `LinkedHashSet` uses a `LinkedHashMap` internally.

Conceptually:

```text
LinkedHashSet
      ↓
LinkedHashMap
      ↓
Hash table + linked ordering
```

The elements of the set behave like keys, while an internal shared dummy value is used.

The implementation detail is useful for understanding how the collection works, but application code normally interacts with the `Set` API.

---

# 14. Why does LinkedHashSet maintain insertion order?

Unlike a plain `HashSet`, it maintains additional linked ordering information for its entries.

Conceptually:

```text
First
  ↓
Second
  ↓
Third
  ↓
Fourth
```

This linked structure allows iteration to follow insertion order.

---

# 15. What is the difference between insertion order and sorted order?

### Insertion order

The elements appear in the order they were first inserted.

```text
30
10
20
```

If inserted as:

```text
30 → 10 → 20
```

### Sorted order

The elements are arranged according to their natural ordering or a comparator.

```text
10
20
30
```

`LinkedHashSet` provides insertion order.

`TreeSet` provides sorted order.

---

# 16. Does LinkedHashSet sort elements?

No.

Example:

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(50);
set.add(10);
set.add(30);
```

Iteration order:

```text
50
10
30
```

It does not automatically become:

```text
10
30
50
```

For sorted unique elements, use `TreeSet`.

---

# 17. Does LinkedHashSet provide index-based access?

No.

This is invalid:

```java
set.get(0);
```

`LinkedHashSet` does not provide list-style index access.

---

# 18. Why doesn't LinkedHashSet have get(index)?

Because `LinkedHashSet` implements the `Set` abstraction.

A set is primarily concerned with:

```text
Uniqueness
Membership
```

not:

```text
Position
Index
```

If you need:

```java
list.get(0);
```

you should generally use a `List`.

---

# 19. Why are equals() and hashCode() important?

`LinkedHashSet` is hash-based.

For custom objects, it uses hashing and equality to determine whether an equivalent element already exists.

Suppose:

```java
Student s1 = new Student(1, "Amit");
Student s2 = new Student(1, "Amit");
```

If these should represent the same logical student, their class needs consistent:

```java
equals()
hashCode()
```

implementations.

---

# 20. What happens if equals() is overridden but hashCode() is not?

This can cause incorrect behavior in hash-based collections.

If:

```java
a.equals(b)
```

is `true`, then:

```java
a.hashCode() == b.hashCode()
```

must also be true.

If this contract is broken, logically equal objects may not be located in the same hash area, and the set may fail to recognize them as duplicates.

### Rule

Override `equals()` and `hashCode()` consistently.

---

# 21. Can LinkedHashSet store custom objects?

Yes.

Example:

```java
Set<Student> students = new LinkedHashSet<>();
```

For logical equality, implement `equals()` and `hashCode()` correctly.

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

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Student)) {
            return false;
        }

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

---

# 22. What happens if a mutable object is changed after insertion?

This can cause lookup problems if the changed fields participate in `equals()` or `hashCode()`.

Example:

```java
Set<Student> students = new LinkedHashSet<>();

Student student = new Student(10, "Amit");

students.add(student);

student.id = 20;
```

Now:

```java
students.contains(student);
```

may not behave as expected.

### Why?

The object's hash-related state changed after it was inserted.

### Best Practice

Avoid changing fields used by:

```text
equals()
hashCode()
```

while the object is stored in the set.

---

# 23. What is initial capacity?

Initial capacity is the initial sizing parameter for the underlying hash table.

Example:

```java
Set<Integer> numbers =
    new LinkedHashSet<>(32);
```

This does not mean:

```text
Maximum size = 32
```

The collection can grow beyond the initial capacity.

---

# 24. What is load factor?

Load factor controls how full the hash table can become before resizing occurs.

The commonly used default load factor is:

```text
0.75
```

Conceptually:

```text
threshold = capacity × load factor
```

When the threshold is reached, resizing may occur.

---

# 25. Does initial capacity represent maximum size?

No.

For example:

```java
Set<Integer> set =
    new LinkedHashSet<>(10);
```

does not restrict the collection to ten elements.

It can grow as elements are added.

---

# 26. What does add() return?

`add()` returns a `boolean`.

```java
Set<Integer> set = new LinkedHashSet<>();

System.out.println(set.add(10));
System.out.println(set.add(10));
```

Output:

```text
true
false
```

Meaning:

```text
true
→ Set changed.

false
→ Element was already present.
```

---

# 27. What does remove() return?

`remove()` also returns a `boolean`.

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(10);

System.out.println(set.remove(10));
System.out.println(set.remove(10));
```

Output:

```text
true
false
```

---

# 28. How can LinkedHashSet be used to remove duplicates while preserving order?

This is one of the most common practical uses.

Given:

```java
List<Integer> numbers =
    Arrays.asList(
        10, 20, 10, 30, 20, 40
    );
```

Use:

```java
Set<Integer> unique =
    new LinkedHashSet<>(numbers);
```

Result:

```text
[10, 20, 30, 40]
```

The first occurrence of every unique element is preserved.

### Interview Answer

> `LinkedHashSet` is useful when duplicates must be removed but the original insertion order must be preserved.

---

# 29. HashSet vs LinkedHashSet

| Feature | HashSet | LinkedHashSet |
|---|---|---|
| Unique elements | Yes | Yes |
| Insertion order | Not guaranteed | Preserved |
| Hash-based | Yes | Yes |
| Typical `contains()` | O(1) average | O(1) average |
| Memory overhead | Lower | Higher |
| Use when | Order doesn't matter | Order matters |

### Example

Use `HashSet`:

```text
Unique employee IDs
```

when order is irrelevant.

Use `LinkedHashSet`:

```text
Unique search history
```

when first-seen order matters.

---

# 30. LinkedHashSet vs TreeSet

| Feature | LinkedHashSet | TreeSet |
|---|---|---|
| Unique elements | Yes | Yes |
| Insertion order | Preserved | No |
| Sorted order | No | Yes |
| Typical operations | O(1) average | O(log n) |
| Hash-based | Yes | No |
| Comparison-based | No | Yes |

### Example

If you insert:

```text
30
10
20
```

`LinkedHashSet`:

```text
30
10
20
```

`TreeSet`:

```text
10
20
30
```

---

# 31. LinkedHashSet vs ArrayList

| Feature | LinkedHashSet | ArrayList |
|---|---|---|
| Duplicates | No | Yes |
| Insertion order | Yes | Yes |
| Index access | No | Yes |
| Typical `contains()` | O(1) average | O(n) |
| Primary use | Unique ordered data | Ordered sequence |

### Choose LinkedHashSet

When you need:

```text
Unique + insertion order
```

### Choose ArrayList

When you need:

```text
Duplicates + index access
```

---

# 32. LinkedHashSet vs LinkedHashMap

`LinkedHashSet` stores unique elements:

```java
Set<String> names =
    new LinkedHashSet<>();
```

`LinkedHashMap` stores key-value pairs:

```java
Map<Integer, String> students =
    new LinkedHashMap<>();
```

Both maintain insertion order, but their abstractions are different.

```text
LinkedHashSet
→ Element

LinkedHashMap
→ Key → Value
```

---

# 33. When should you use LinkedHashSet?

Use it when all or most of these requirements are true:

- Duplicates should not be stored.
- Insertion order matters.
- Fast average-case membership is useful.
- Index-based access is not required.
- Sorted order is not required.

### Example

Unique search history:

```java
Set<String> searches =
    new LinkedHashSet<>();
```

---

# 34. When should you not use LinkedHashSet?

Do not use it when:

### You need duplicates

Use:

```text
List
```

### You need index access

Use:

```text
List
```

### You need sorted unique elements

Use:

```text
TreeSet
```

### You do not care about ordering

A plain:

```text
HashSet
```

may be a better choice.

### You need key-value storage

Use:

```text
Map
```

---

# 35. Can LinkedHashSet be made thread-safe?

Yes, using a synchronized wrapper:

```java
Set<Integer> set =
    Collections.synchronizedSet(
        new LinkedHashSet<>()
    );
```

However, synchronization requirements can extend beyond individual method calls when performing compound operations or iteration.

For highly concurrent workloads, choose a collection designed for the required concurrency model.

---

# 36. Practical Interview Problems

## Problem 1 — Remove Duplicates While Preserving Order

Given:

```java
int[] numbers = {
    5, 3, 5, 2, 3, 1
};
```

Produce:

```text
[5, 3, 2, 1]
```

### Solution Idea

```java
Set<Integer> unique =
    new LinkedHashSet<>();

for (int number : numbers) {
    unique.add(number);
}
```

---

## Problem 2 — Unique Words

Given:

```text
Java is powerful Java is popular
```

Store the words in a `LinkedHashSet`.

Expected:

```text
Java
is
powerful
popular
```

---

## Problem 3 — Unique Characters

Given:

```text
programming
```

Store the characters in:

```java
LinkedHashSet<Character>
```

The first occurrence of each character should determine its position.

---

## Problem 4 — Detect Duplicate

Given:

```java
int[] numbers = {
    10, 20, 30, 20, 40
};
```

Return `true` if any duplicate exists.

### Hint

Use:

```java
Set<Integer> seen =
    new LinkedHashSet<>();
```

and:

```java
if (!seen.add(number)) {
    return true;
}
```

---

## Problem 5 — First Repeating Element

Given:

```java
int[] numbers = {
    5, 3, 4, 3, 5, 6
};
```

Find the first element encountered for the second time.

Expected:

```text
3
```

---

## Problem 6 — Unique Usernames

Given:

```java
String[] usernames = {
    "alice",
    "bob",
    "alice",
    "charlie",
    "bob",
    "david"
};
```

Expected:

```text
[alice, bob, charlie, david]
```

---

## Problem 7 — Search History

Given:

```text
Java
Spring
Java
SQL
Spring
Docker
```

Return:

```text
[Java, Spring, SQL, Docker]
```

A `LinkedHashSet` is an appropriate choice.

---

# 37. Output-Based Questions

## Question 1

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(30);
set.add(10);
set.add(20);

System.out.println(set);
```

### Answer

```text
[30, 10, 20]
```

---

## Question 2

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(10);
set.add(20);
set.add(10);

System.out.println(set);
```

### Answer

```text
[10, 20]
```

---

## Question 3

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(10);
set.add(20);
set.add(30);

set.remove(20);
set.add(20);

System.out.println(set);
```

### Answer

```text
[10, 30, 20]
```

### Why?

`20` was removed and then inserted again, so it receives a new insertion position.

---

## Question 4

```java
Set<String> set = new LinkedHashSet<>();

System.out.println(set.add("Java"));
System.out.println(set.add("Java"));
System.out.println(set.contains("Java"));
```

### Answer

```text
true
false
true
```

---

## Question 5

```java
Set<String> set = new LinkedHashSet<>();

set.add(null);
set.add(null);
set.add("Java");

System.out.println(set.size());
```

### Answer

```text
2
```

The two unique elements are:

```text
null
Java
```

---

## Question 6

```java
List<Integer> numbers =
    Arrays.asList(4, 2, 4, 1, 2, 5);

Set<Integer> unique =
    new LinkedHashSet<>(numbers);

System.out.println(unique);
```

### Answer

```text
[4, 2, 1, 5]
```

---

# 38. Rapid-Fire Questions

### Q1. Which package contains LinkedHashSet?

```text
java.util
```

### Q2. Which interface does it implement?

```text
Set
```

### Q3. Does it allow duplicates?

```text
No
```

### Q4. Does it preserve insertion order?

```text
Yes
```

### Q5. Does it sort elements?

```text
No
```

### Q6. How many null elements can it contain?

```text
One
```

### Q7. Does it support index access?

```text
No
```

### Q8. Is it thread-safe?

```text
No
```

### Q9. Typical average complexity of `contains()`?

```text
O(1)
```

### Q10. Is O(1) an absolute guarantee?

```text
No
```

### Q11. What does LinkedHashSet use internally?

```text
LinkedHashMap-based implementation
```

### Q12. Why is a linked structure used?

```text
To maintain insertion order
```

### Q13. What does `add()` return?

```text
true if the set changed, false if the element already existed
```

### Q14. What does `remove()` return?

```text
true if an element was removed, otherwise false
```

### Q15. Which collection gives unique elements without guaranteed order?

```text
HashSet
```

### Q16. Which collection gives unique elements in sorted order?

```text
TreeSet
```

### Q17. Which collection gives unique elements in insertion order?

```text
LinkedHashSet
```

### Q18. What methods are important for custom objects?

```text
equals()
hashCode()
```

### Q19. What happens when an existing element is added again?

```text
It is ignored and add() returns false.
```

### Q20. What happens if an element is removed and added again?

```text
It is inserted again at the end of the insertion order.
```

---

# 39. Interview Checklist

Before considering `LinkedHashSet` interview-ready, make sure you can explain:

## Fundamentals

- [ ] What `LinkedHashSet` is.
- [ ] Which interface it implements.
- [ ] Why duplicates are not allowed.
- [ ] Why insertion order is preserved.
- [ ] Why sorted order is not provided.
- [ ] Why index access is unavailable.
- [ ] Why it is not thread-safe.
- [ ] How `null` is handled.

## Internal Working

- [ ] Hash-based storage.
- [ ] Linked ordering structure.
- [ ] Relationship with `LinkedHashMap`.
- [ ] Hashing.
- [ ] `hashCode()`.
- [ ] `equals()`.
- [ ] Hash collisions.
- [ ] Load factor.
- [ ] Initial capacity.

## Ordering

- [ ] Insertion order.
- [ ] Duplicate insertion behavior.
- [ ] Remove-and-reinsert behavior.
- [ ] Difference between insertion and sorted order.

## Performance

- [ ] Average `add()` → O(1).
- [ ] Average `remove()` → O(1).
- [ ] Average `contains()` → O(1).
- [ ] `size()` → O(1).
- [ ] `clear()` → O(n).
- [ ] O(1) is average-case, not an absolute guarantee.
- [ ] Additional memory overhead compared with `HashSet`.

## Object Equality

- [ ] `equals()`/`hashCode()` contract.
- [ ] Custom objects.
- [ ] Mutable object problem.
- [ ] Preference for stable equality/hash fields.

## Comparisons

- [ ] HashSet vs LinkedHashSet.
- [ ] LinkedHashSet vs TreeSet.
- [ ] LinkedHashSet vs ArrayList.
- [ ] LinkedHashSet vs LinkedHashMap.

## Coding

- [ ] Remove duplicates while preserving order.
- [ ] Detect duplicates.
- [ ] Find first repeating element.
- [ ] Remove duplicate words.
- [ ] Create unique search history.
- [ ] Store custom objects correctly.

---

# 40. Final Interview Summary

If an interviewer asks:

> **What is LinkedHashSet?**

A strong answer is:

> `LinkedHashSet` is a `Set` implementation that stores unique elements and maintains their insertion order. It is hash-based, allows one `null`, does not provide index-based access, and is not thread-safe. Its typical average-case time complexity for `add()`, `remove()`, and `contains()` is O(1).

---

## If Asked: HashSet vs LinkedHashSet

Answer:

> Both store unique elements and provide hash-based average-case operations. The key difference is that `LinkedHashSet` maintains insertion order, while `HashSet` does not guarantee iteration order. `LinkedHashSet` therefore requires additional memory and bookkeeping.

---

## If Asked: LinkedHashSet vs TreeSet

Answer:

> `LinkedHashSet` maintains insertion order, while `TreeSet` maintains sorted order. `LinkedHashSet` typically provides O(1) average-case basic operations, whereas `TreeSet` typically provides O(log n) operations.

---

## If Asked: Why Use LinkedHashSet?

Answer:

> I would use `LinkedHashSet` when I need unique elements and also need to preserve the order in which those elements were first inserted.

---

## If Asked: What Happens When a Duplicate Is Added?

Answer:

> The existing element remains unchanged, the set size does not increase, and `add()` returns `false`. The duplicate does not move the existing element to the end.

---

## If Asked: What Happens When an Element Is Removed and Added Again?

Answer:

> Once removed, adding it again creates a new insertion, so it appears at the end of the insertion order during iteration.

---

## One-Minute Revision

```text
LinkedHashSet
│
├── java.util
├── Implements Set
│
├── Unique elements
├── Preserves insertion order
├── One null allowed
├── No index access
├── Not thread-safe
│
├── Hash-based
├── Linked ordering structure
├── LinkedHashMap-based implementation
│
├── add()      → O(1) average
├── remove()   → O(1) average
├── contains() → O(1) average
│
├── Duplicate add
│      └── ignored
│
├── Remove + add again
│      └── moves to end of insertion order
│
├── Custom objects
│      └── equals() + hashCode()
│
└── Best use:
       Unique elements
       +
       Insertion order
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
├── 16-Map
├── 17-HashMap
├── 18-LinkedHashMap
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

**`14-LinkedHashSet` is now complete. Next topic: `15-TreeSet`.**
