# HashSet — Practice

> Hands-on practice for understanding `HashSet`, uniqueness, hashing, `equals()`, `hashCode()`, custom objects, and common real-world problems.

---

## Table of Contents

- [1. Practice Goals](#1-practice-goals)
- [2. Create a HashSet](#2-create-a-hashset)
- [3. Add Elements](#3-add-elements)
- [4. Duplicate Elements](#4-duplicate-elements)
- [5. Understanding add() Return Value](#5-understanding-add-return-value)
- [6. Remove Elements](#6-remove-elements)
- [7. contains()](#7-contains)
- [8. size(), isEmpty(), and clear()](#8-size-isempty-and-clear)
- [9. Iterating Over a HashSet](#9-iterating-over-a-hashset)
- [10. Null Practice](#10-null-practice)
- [11. Output Prediction](#11-output-prediction)
- [12. equals() and hashCode() Practice](#12-equals-and-hashcode-practice)
- [13. Custom Object Practice](#13-custom-object-practice)
- [14. Mutable Object Trap](#14-mutable-object-trap)
- [15. HashSet vs ArrayList](#15-hashset-vs-arraylist)
- [16. HashSet vs LinkedHashSet](#16-hashset-vs-linkedhashset)
- [17. HashSet vs TreeSet](#17-hashset-vs-treeset)
- [18. Basic Coding Problems](#18-basic-coding-problems)
- [19. Intermediate Problems](#19-intermediate-problems)
- [20. Challenge Problems](#20-challenge-problems)
- [21. Scenario-Based Practice](#21-scenario-based-practice)
- [22. Practice Checklist](#22-practice-checklist)
- [23. Final Goal](#23-final-goal)
- [24. Progress](#24-progress)

---

# 1. Practice Goals

By completing this practice file, you should be able to:

- Create and initialize a `HashSet`.
- Add elements.
- Understand duplicate handling.
- Understand the return value of `add()`.
- Remove elements.
- Check whether an element exists.
- Iterate through a `HashSet`.
- Work with `null`.
- Understand why iteration order should not be relied upon.
- Use `equals()` and `hashCode()` correctly.
- Store custom objects in a `HashSet`.
- Understand the mutable-object problem.
- Solve duplicate-removal problems.
- Find common and unique elements.
- Solve frequency-independent membership problems efficiently.

---

# 2. Create a HashSet

## Basic Example

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Set<Integer> numbers = new HashSet<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println(numbers);
    }
}
```

### Important

Prefer programming to the interface:

```java
Set<Integer> numbers = new HashSet<>();
```

instead of:

```java
HashSet<Integer> numbers = new HashSet<>();
```

The first approach gives you more flexibility to change the implementation later.

---

# 3. Add Elements

```java
Set<String> names = new HashSet<>();

names.add("Amit");
names.add("Rahul");
names.add("Sneha");
names.add("Priya");

System.out.println(names);
```

### Practice

Create a `HashSet<Integer>` and add:

```text
10
20
30
40
50
```

Then print the set.

### Question

Does the output have to be:

```text
[10, 20, 30, 40, 50]
```

### Answer

No.

`HashSet` does **not guarantee insertion order**.

The important property is:

> The elements are unique, not ordered.

---

# 4. Duplicate Elements

One of the most important properties of `HashSet` is that it does not allow duplicate elements.

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

The set contains only:

```text
10
20
30
```

The duplicate values are ignored.

---

## Practice

What happens here?

```java
Set<String> languages = new HashSet<>();

languages.add("Java");
languages.add("Python");
languages.add("Java");
languages.add("C++");
languages.add("Python");

System.out.println(languages);
```

Expected concept:

```text
Java
Python
C++
```

but **do not depend on the exact printed order**.

---

# 5. Understanding add() Return Value

The `add()` method returns a `boolean`.

```java
Set<Integer> numbers = new HashSet<>();

System.out.println(numbers.add(10));
System.out.println(numbers.add(20));
System.out.println(numbers.add(10));
```

Output:

```text
true
true
false
```

### Why?

```java
numbers.add(10);
```

The set changed, so:

```text
true
```

Adding `10` again does not change the set:

```text
false
```

---

## Practice

Predict the output:

```java
Set<String> set = new HashSet<>();

System.out.println(set.add("Java"));
System.out.println(set.add("Java"));
System.out.println(set.add("Python"));
System.out.println(set.add("Python"));
```

### Answer

```text
true
false
true
false
```

---

## Useful Pattern

You can use `add()` to detect duplicates:

```java
if (!set.add(value)) {
    System.out.println("Duplicate found: " + value);
}
```

Example:

```java
Set<Integer> numbers = new HashSet<>();

int[] arr = {10, 20, 30, 20, 40, 10};

for (int number : arr) {

    if (!numbers.add(number)) {
        System.out.println("Duplicate: " + number);
    }
}
```

Possible output:

```text
Duplicate: 20
Duplicate: 10
```

---

# 6. Remove Elements

Use:

```java
remove()
```

Example:

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

numbers.remove(20);

System.out.println(numbers);
```

`20` is removed.

---

## remove() Return Value

`remove()` also returns a boolean.

```java
System.out.println(numbers.remove(10));
System.out.println(numbers.remove(100));
```

Output:

```text
true
false
```

### Meaning

| Situation | Return value |
|---|---:|
| Element existed and was removed | `true` |
| Element did not exist | `false` |

---

# 7. contains()

Use `contains()` to check whether an element exists.

```java
Set<String> names = new HashSet<>();

names.add("Amit");
names.add("Rahul");
names.add("Priya");

System.out.println(names.contains("Rahul"));
System.out.println(names.contains("Rohit"));
```

Output:

```text
true
false
```

---

## Practical Example

```java
Set<String> registeredUsers = new HashSet<>();

registeredUsers.add("alice");
registeredUsers.add("bob");
registeredUsers.add("charlie");

String username = "bob";

if (registeredUsers.contains(username)) {
    System.out.println("Username already exists");
} else {
    System.out.println("Username is available");
}
```

This is a common real-world use case for a `Set`.

---

# 8. size(), isEmpty(), and clear()

## size()

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers.size());
```

Output:

```text
3
```

Duplicates do not increase the size.

```java
numbers.add(10);

System.out.println(numbers.size());
```

Still:

```text
3
```

---

## isEmpty()

```java
Set<Integer> numbers = new HashSet<>();

System.out.println(numbers.isEmpty());

numbers.add(10);

System.out.println(numbers.isEmpty());
```

Output:

```text
true
false
```

---

## clear()

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

numbers.clear();

System.out.println(numbers);
System.out.println(numbers.isEmpty());
```

Output:

```text
[]
true
```

---

# 9. Iterating Over a HashSet

## Enhanced for Loop

```java
Set<String> names = new HashSet<>();

names.add("Amit");
names.add("Rahul");
names.add("Priya");

for (String name : names) {
    System.out.println(name);
}
```

Remember:

> The iteration order is not guaranteed.

Do not write logic that depends on which element appears first.

---

## Iterator

```java
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

---

## Removing During Iteration

Do not directly modify the set while iterating with a for-each loop.

Avoid:

```java
for (Integer number : numbers) {
    if (number == 20) {
        numbers.remove(number);
    }
}
```

Instead, use an `Iterator` when you need to safely remove during iteration:

```java
Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {

    Integer number = iterator.next();

    if (number == 20) {
        iterator.remove();
    }
}
```

---

# 10. Null Practice

`HashSet` allows one `null` element.

```java
Set<String> names = new HashSet<>();

names.add("Amit");
names.add(null);
names.add("Rahul");
names.add(null);

System.out.println(names);
```

Only one `null` is stored.

Conceptually:

```text
null
Amit
Rahul
```

The exact iteration order is not guaranteed.

---

## Practice

Predict:

```java
Set<Integer> set = new HashSet<>();

set.add(null);
set.add(null);
set.add(10);

System.out.println(set.size());
```

### Answer

```text
2
```

There are only two unique elements:

```text
null
10
```

---

# 11. Output Prediction

## Question 1

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);
set.add(10);
set.add(30);

System.out.println(set.size());
```

### Answer

```text
3
```

---

## Question 2

```java
Set<String> set = new HashSet<>();

System.out.println(set.add("A"));
System.out.println(set.add("B"));
System.out.println(set.add("A"));
```

### Answer

```text
true
true
false
```

---

## Question 3

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);

System.out.println(set.contains(10));
System.out.println(set.contains(50));
```

### Answer

```text
true
false
```

---

## Question 4

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);
set.remove(10);

System.out.println(set.size());
```

### Answer

```text
1
```

---

## Question 5

What is wrong with this assumption?

```java
Set<Integer> set = new HashSet<>();

set.add(10);
set.add(20);
set.add(30);

System.out.println(set.iterator().next());
```

### Answer

You cannot assume that the first element will be `10`.

`HashSet` does not guarantee insertion order.

---

# 12. equals() and hashCode() Practice

This is one of the most important concepts when working with `HashSet`.

Consider:

```java
String a = new String("Java");
String b = new String("Java");

System.out.println(a.equals(b));
System.out.println(a.hashCode() == b.hashCode());
```

Output:

```text
true
true
```

Because equal objects must have the same hash code.

---

## Important Contract

If:

```java
a.equals(b)
```

is `true`, then:

```java
a.hashCode() == b.hashCode()
```

must also be `true`.

But:

```java
a.hashCode() == b.hashCode()
```

being `true` does **not** necessarily mean:

```java
a.equals(b)
```

is `true`.

---

## Practice

Why does this set contain only one `"Java"`?

```java
Set<String> set = new HashSet<>();

set.add(new String("Java"));
set.add(new String("Java"));

System.out.println(set.size());
```

Answer:

```text
1
```

Because `String` correctly implements `equals()` and `hashCode()` based on its content.

---

# 13. Custom Object Practice

Consider:

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

Now:

```java
Set<Student> students = new HashSet<>();

students.add(new Student(1, "Amit"));
students.add(new Student(1, "Amit"));

System.out.println(students.size());
```

Without overriding `equals()` and `hashCode()`, these two objects are normally treated as different objects.

So the size will be:

```text
2
```

---

## Correct Implementation

```java
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

Now:

```java
Set<Student> students = new HashSet<>();

students.add(new Student(1, "Amit"));
students.add(new Student(1, "Amit"));

System.out.println(students.size());
```

Output:

```text
1
```

---

## Practice Task

Create a class:

```java
Employee
```

with:

```text
id
name
department
```

Store multiple `Employee` objects in a `HashSet`.

Then:

1. Override `equals()`.
2. Override `hashCode()`.
3. Decide which fields determine employee equality.
4. Add duplicate employees.
5. Verify that duplicates are not stored.

---

# 14. Mutable Object Trap

This is an important interview and practical concept.

Suppose your `hashCode()` depends on a field:

```java
class Student {

    int id;

    Student(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Student)) {
            return false;
        }

        Student other = (Student) obj;

        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
```

Now:

```java
Set<Student> students = new HashSet<>();

Student student = new Student(10);

students.add(student);

student.id = 20;

System.out.println(students.contains(student));
```

Do not assume this will behave as expected.

The object's hash-related state changed after insertion.

The object was placed according to its old hash value, while later lookup may use the new hash value.

### Practical Rule

Avoid mutating fields that participate in:

```java
equals()
hashCode()
```

while the object is stored in a `HashSet`.

---

# 15. HashSet vs ArrayList

Consider:

```java
List<Integer> list = new ArrayList<>();
Set<Integer> set = new HashSet<>();
```

Add:

```text
10
20
10
30
```

The `ArrayList` contains:

```text
10
20
10
30
```

The `HashSet` contains:

```text
10
20
30
```

---

## Practice Comparison

| Feature | ArrayList | HashSet |
|---|---|---|
| Duplicates | Allowed | Not allowed |
| Index access | Yes | No |
| `contains()` | O(n) average | O(1) average |
| Insertion order | Preserved | Not guaranteed |
| Null | Multiple allowed | At most one |
| Primary purpose | Ordered collection | Unique elements |

---

## Practice Question

You receive:

```text
100000 usernames
```

and need to repeatedly check:

> "Does this username already exist?"

Which is generally more appropriate?

```text
HashSet
```

because membership checks are typically O(1) average-case.

---

# 16. HashSet vs LinkedHashSet

Consider:

```java
Set<Integer> hashSet = new HashSet<>();
Set<Integer> linkedHashSet = new LinkedHashSet<>();
```

Add:

```text
30
10
20
```

`HashSet`:

```text
Order is not guaranteed
```

`LinkedHashSet`:

```text
30
10
20
```

because `LinkedHashSet` maintains insertion order.

---

## Practice Question

You need:

> Unique values + insertion order.

Which should you choose?

```text
LinkedHashSet
```

---

# 17. HashSet vs TreeSet

Consider:

```java
Set<Integer> hashSet = new HashSet<>();
Set<Integer> treeSet = new TreeSet<>();
```

Add:

```text
30
10
20
```

`HashSet`:

```text
Order not guaranteed
```

`TreeSet`:

```text
10
20
30
```

because `TreeSet` maintains sorted order.

---

## Comparison

| Feature | HashSet | TreeSet |
|---|---|---|
| Unique elements | Yes | Yes |
| Ordering | None guaranteed | Sorted |
| Typical basic operations | O(1) average | O(log n) |
| Uses hashing | Yes | No |
| Uses comparison | No | Yes |
| Best for | Fast membership | Sorted unique data |

---

# 18. Basic Coding Problems

## Problem 1 — Remove Duplicates

Given:

```java
int[] arr = {1, 2, 2, 3, 4, 4, 5};
```

Remove duplicates using a `HashSet`.

Expected unique values:

```text
1
2
3
4
5
```

### Hint

```java
Set<Integer> set = new HashSet<>();

for (int number : arr) {
    set.add(number);
}
```

---

## Problem 2 — Count Unique Elements

Given:

```java
int[] arr = {10, 20, 10, 30, 20, 40};
```

Find the number of unique elements.

Expected answer:

```text
4
```

---

## Problem 3 — Check Duplicate

Given:

```java
int[] arr = {10, 20, 30, 20, 40};
```

Return `true` if the array contains duplicates.

Expected:

```text
true
```

---

## Problem 4 — No Duplicates

Given:

```java
int[] arr = {10, 20, 30, 40};
```

Check whether every element is unique.

Expected:

```text
true
```

---

## Problem 5 — Common Elements

Given:

```java
int[] a = {1, 2, 3, 4, 5};
int[] b = {4, 5, 6, 7};
```

Find common elements.

Expected:

```text
4
5
```

### Hint

Put the first array into a `HashSet`.

Then check each element of the second array using:

```java
contains()
```

---

## Problem 6 — Union

Given:

```text
A = {1, 2, 3}
B = {3, 4, 5}
```

Find the union.

Expected:

```text
{1, 2, 3, 4, 5}
```

### Hint

```java
Set<Integer> union = new HashSet<>(A);
union.addAll(B);
```

---

## Problem 7 — Difference

Given:

```text
A = {1, 2, 3, 4}
B = {3, 4, 5}
```

Find:

```text
A - B
```

Expected:

```text
{1, 2}
```

### Hint

```java
Set<Integer> difference = new HashSet<>(A);
difference.removeAll(B);
```

---

# 19. Intermediate Problems

## Problem 1 — First Repeating Element

Given:

```java
int[] arr = {5, 3, 4, 3, 5, 6};
```

Find the first element that repeats.

Expected:

```text
3
```

### Hint

Maintain a set of already-seen elements.

```java
Set<Integer> seen = new HashSet<>();

for (int number : arr) {

    if (!seen.add(number)) {
        System.out.println(number);
        break;
    }
}
```

---

## Problem 2 — Find Duplicate Values

Given:

```java
int[] arr = {1, 2, 3, 2, 4, 1, 5, 3};
```

Print duplicate values.

Expected:

```text
2
1
3
```

---

## Problem 3 — Unique Characters

Given:

```text
programming
```

Find the unique characters.

Example concept:

```text
p
r
o
g
a
m
i
n
```

Use:

```java
Set<Character>
```

---

## Problem 4 — Check Anagrams

Given:

```text
listen
silent
```

Determine whether the two strings contain the same unique characters.

### Important

A plain `HashSet` is not enough to correctly check anagrams in general because anagrams depend on character frequencies too.

This is a good exercise in understanding when a `Set` is appropriate and when a frequency map is needed.

---

## Problem 5 — Find Missing Number

Given:

```java
int[] arr = {1, 2, 3, 5, 6};
```

Find the missing number from:

```text
1 to 6
```

Expected:

```text
4
```

Try solving it using a `HashSet`.

---

# 20. Challenge Problems

## Challenge 1 — Longest Consecutive Sequence

Given:

```java
int[] nums = {100, 4, 200, 1, 3, 2};
```

Find the length of the longest consecutive sequence.

Expected:

```text
4
```

Because:

```text
1, 2, 3, 4
```

---

### Key Idea

Put all numbers into a `HashSet`.

Then:

```java
if (!set.contains(number - 1))
```

the current number may be the beginning of a sequence.

This allows an efficient solution.

---

## Challenge 2 — Intersection Without Duplicates

Given:

```java
int[] a = {1, 2, 2, 3, 4};
int[] b = {2, 2, 4, 5};
```

Find the intersection without duplicate results.

Expected:

```text
2
4
```

---

## Challenge 3 — Symmetric Difference

Given:

```text
A = {1, 2, 3}
B = {3, 4, 5}
```

Find elements that belong to exactly one set.

Expected:

```text
1
2
4
5
```

---

## Challenge 4 — Detect Duplicate Objects

Create:

```java
class Product {

    int id;
    String name;
}
```

Store products in a `HashSet`.

Two products with the same:

```text
id
name
```

should be considered duplicates.

Implement:

```java
equals()
hashCode()
```

correctly.

---

## Challenge 5 — Find Repeated Words

Given:

```text
"java is powerful and java is popular"
```

Find repeated words.

Expected:

```text
java
is
```

### Hint

Use a:

```java
HashSet<String>
```

for previously seen words.

---

# 21. Scenario-Based Practice

## Scenario 1 — Registered Usernames

You are building a registration system.

Requirements:

- Usernames must be unique.
- Fast existence checks are needed.
- Order does not matter.

Which collection would you choose?

```text
HashSet<String>
```

---

## Scenario 2 — Unique Product IDs

You receive product IDs from multiple sources:

```text
101
102
103
101
104
102
```

You need only unique IDs.

Use:

```java
Set<Integer> productIds = new HashSet<>();
```

---

## Scenario 3 — Preserve User Entry Order

You need:

- Unique values.
- Original insertion order.

Do not choose plain `HashSet`.

Prefer:

```java
LinkedHashSet
```

---

## Scenario 4 — Unique Sorted Values

You need:

- Unique values.
- Automatically sorted values.

Prefer:

```java
TreeSet
```

---

## Scenario 5 — Custom Objects

You have:

```java
Set<Employee> employees = new HashSet<>();
```

You notice that two employees with the same ID are being stored separately.

What should you investigate?

Check:

```text
equals()
hashCode()
```

---

# 22. Practice Checklist

## Fundamentals

- [ ] Create a `HashSet`.
- [ ] Add elements.
- [ ] Remove elements.
- [ ] Check membership.
- [ ] Get the size.
- [ ] Check whether it is empty.
- [ ] Clear the set.
- [ ] Iterate over the set.

---

## Duplicate Handling

- [ ] Add duplicate integers.
- [ ] Add duplicate strings.
- [ ] Understand why duplicates are ignored.
- [ ] Use `add()` return value to detect duplicates.
- [ ] Remove duplicates from an array.
- [ ] Find duplicate values.

---

## Null

- [ ] Add `null`.
- [ ] Add `null` twice.
- [ ] Check `contains(null)`.
- [ ] Understand that only one `null` is stored.

---

## Hashing

- [ ] Understand `hashCode()`.
- [ ] Understand `equals()`.
- [ ] Understand the `equals()`/`hashCode()` contract.
- [ ] Create a custom class with proper implementations.
- [ ] Test duplicate custom objects.
- [ ] Understand the mutable-object trap.

---

## Problem Solving

- [ ] Remove duplicates.
- [ ] Check whether an array contains duplicates.
- [ ] Find common elements.
- [ ] Find union.
- [ ] Find difference.
- [ ] Find first repeating element.
- [ ] Find repeated words.
- [ ] Find unique characters.
- [ ] Solve longest consecutive sequence.
- [ ] Compare `HashSet`, `LinkedHashSet`, and `TreeSet`.

---

# 23. Final Goal

After completing these exercises, you should be comfortable answering:

> **When should I use a `HashSet`?**

A good answer:

> Use `HashSet` when you need a collection of unique elements and fast average-case membership, insertion, and removal, and you do not need a guaranteed iteration order.

You should also understand that:

```text
HashSet
    ↓
Unique elements
    ↓
Hashing
    ↓
hashCode()
    ↓
equals()
    ↓
Fast average-case lookup
```

The most important practical idea is:

> `HashSet` is not just about removing duplicates. It is about efficient membership and uniqueness.

---

# 24. Progress

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
│   └── INTERVIEW.md   [ ]
│
├── 14-LinkedHashSet
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

**`13-HashSet/PRACTICE.md` is complete. Next: `13-HashSet/INTERVIEW.md`.**
