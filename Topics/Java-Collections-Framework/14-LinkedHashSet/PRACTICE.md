# LinkedHashSet — Practice

> Hands-on practice for understanding uniqueness, insertion order, duplicate removal, set operations, custom objects, and real-world use cases of `LinkedHashSet`.

---

## Table of Contents

- [1. Practice Goals](#1-practice-goals)
- [2. Create a LinkedHashSet](#2-create-a-linkedhashset)
- [3. Add Elements](#3-add-elements)
- [4. Duplicate Elements](#4-duplicate-elements)
- [5. Understanding add() Return Value](#5-understanding-add-return-value)
- [6. Insertion Order Practice](#6-insertion-order-practice)
- [7. Remove Elements](#7-remove-elements)
- [8. contains() Practice](#8-contains-practice)
- [9. size(), isEmpty(), and clear()](#9-size-isempty-and-clear)
- [10. Iteration Practice](#10-iteration-practice)
- [11. Null Practice](#11-null-practice)
- [12. Remove Duplicates While Preserving Order](#12-remove-duplicates-while-preserving-order)
- [13. Output Prediction](#13-output-prediction)
- [14. LinkedHashSet from a Collection](#14-linkedhashset-from-a-collection)
- [15. Custom Object Practice](#15-custom-object-practice)
- [16. HashSet vs LinkedHashSet Practice](#16-hashset-vs-linkedhashset-practice)
- [17. LinkedHashSet vs TreeSet Practice](#17-linkedhashset-vs-treeset-practice)
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

- Create a `LinkedHashSet`.
- Add and remove elements.
- Understand duplicate handling.
- Understand insertion order.
- Use `add()` return values.
- Use `contains()` for membership checks.
- Work with `null`.
- Iterate through a `LinkedHashSet`.
- Remove duplicates while preserving order.
- Create a `LinkedHashSet` from another collection.
- Understand `equals()` and `hashCode()` with custom objects.
- Compare `HashSet`, `LinkedHashSet`, and `TreeSet`.
- Solve practical duplicate-removal problems.

---

# 2. Create a LinkedHashSet

## Basic Example

```java
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<Integer> numbers = new LinkedHashSet<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println(numbers);
    }
}
```

Output:

```text
[10, 20, 30]
```

Unlike `HashSet`, `LinkedHashSet` maintains insertion order.

---

## Practice

Create:

```java
Set<String> languages = new LinkedHashSet<>();
```

Add:

```text
Java
Python
C++
JavaScript
```

Then print the set.

Expected:

```text
[Java, Python, C++, JavaScript]
```

---

# 3. Add Elements

Use:

```java
add()
```

to insert elements.

```java
Set<String> names = new LinkedHashSet<>();

names.add("Amit");
names.add("Rahul");
names.add("Priya");

System.out.println(names);
```

Output:

```text
[Amit, Rahul, Priya]
```

---

## Practice Task

Create a `LinkedHashSet<Integer>` and add:

```text
50
20
40
10
30
```

Predict the iteration order.

Answer:

```text
50
20
40
10
30
```

The values remain in the order they were first inserted.

---

# 4. Duplicate Elements

Duplicates are ignored.

```java
Set<Integer> numbers = new LinkedHashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

---

## Important

When a duplicate is added:

```java
numbers.add(20);
```

the existing `20` remains in its original position.

It does **not** move to the end.

---

## Example

```java
Set<String> set = new LinkedHashSet<>();

set.add("A");
set.add("B");
set.add("C");
set.add("B");

System.out.println(set);
```

Output:

```text
[A, B, C]
```

Not:

```text
[A, C, B]
```

---

# 5. Understanding add() Return Value

`add()` returns a boolean.

```java
Set<Integer> numbers = new LinkedHashSet<>();

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

### Meaning

```text
true
→ Element was added and the set changed.

false
→ Element was already present.
```

---

## Duplicate Detection

You can use this behavior to detect duplicates:

```java
Set<Integer> seen = new LinkedHashSet<>();

int[] numbers = {10, 20, 30, 20, 40, 10};

for (int number : numbers) {

    if (!seen.add(number)) {
        System.out.println("Duplicate: " + number);
    }
}
```

Output:

```text
Duplicate: 20
Duplicate: 10
```

---

# 6. Insertion Order Practice

This is the most important feature that distinguishes `LinkedHashSet` from `HashSet`.

Consider:

```java
Set<Integer> numbers = new LinkedHashSet<>();

numbers.add(40);
numbers.add(10);
numbers.add(30);
numbers.add(20);
```

Iteration order:

```text
40
10
30
20
```

---

## Practice Question

What is the output?

```java
Set<String> set = new LinkedHashSet<>();

set.add("Java");
set.add("Python");
set.add("Java");
set.add("C++");
set.add("Python");

System.out.println(set);
```

Answer:

```text
[Java, Python, C++]
```

Why?

- `Java` → first insertion
- `Python` → second insertion
- second `Java` → duplicate
- `C++` → third unique insertion
- second `Python` → duplicate

---

# 7. Remove Elements

Use:

```java
remove()
```

Example:

```java
Set<String> names = new LinkedHashSet<>();

names.add("Amit");
names.add("Rahul");
names.add("Priya");

names.remove("Rahul");

System.out.println(names);
```

Output:

```text
[Amit, Priya]
```

---

## remove() Return Value

```java
Set<Integer> numbers = new LinkedHashSet<>();

numbers.add(10);
numbers.add(20);

System.out.println(numbers.remove(10));
System.out.println(numbers.remove(10));
```

Output:

```text
true
false
```

---

## Practice

Given:

```java
Set<Integer> numbers = new LinkedHashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);
```

Remove:

```text
30
```

Expected:

```text
[10, 20, 40]
```

The remaining elements keep their relative order.

---

# 8. contains() Practice

Use:

```java
contains()
```

to check whether an element exists.

```java
Set<String> languages = new LinkedHashSet<>();

languages.add("Java");
languages.add("Python");
languages.add("C++");

System.out.println(languages.contains("Java"));
System.out.println(languages.contains("Ruby"));
```

Output:

```text
true
false
```

---

## Practical Example

```java
Set<String> usernames = new LinkedHashSet<>();

usernames.add("mahesh");
usernames.add("rahul");
usernames.add("amit");

String username = "rahul";

if (usernames.contains(username)) {
    System.out.println("Username already exists");
} else {
    System.out.println("Username is available");
}
```

---

# 9. size(), isEmpty(), and clear()

## size()

```java
Set<Integer> numbers = new LinkedHashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
numbers.add(30);

System.out.println(numbers.size());
```

Output:

```text
3
```

The duplicate `10` does not increase the size.

---

## isEmpty()

```java
Set<Integer> numbers = new LinkedHashSet<>();

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
Set<Integer> numbers = new LinkedHashSet<>();

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

# 10. Iteration Practice

## Enhanced for Loop

```java
Set<String> languages = new LinkedHashSet<>();

languages.add("Java");
languages.add("Python");
languages.add("C++");

for (String language : languages) {
    System.out.println(language);
}
```

Output:

```text
Java
Python
C++
```

---

## Iterator

```java
import java.util.Iterator;

Set<Integer> numbers = new LinkedHashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

Output:

```text
10
20
30
```

---

## Removing During Iteration

If you need to remove elements while iterating, use the iterator's `remove()` method.

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

# 11. Null Practice

`LinkedHashSet` allows one `null` element.

```java
Set<String> set = new LinkedHashSet<>();

set.add("Java");
set.add(null);
set.add("Python");
set.add(null);

System.out.println(set);
```

Output:

```text
[Java, null, Python]
```

Only one `null` is stored.

---

## Practice Question

What is the size?

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(null);
set.add(null);
set.add(10);
set.add(20);
set.add(null);

System.out.println(set.size());
```

Answer:

```text
3
```

The unique elements are:

```text
null
10
20
```

---

# 12. Remove Duplicates While Preserving Order

This is one of the best practical uses of `LinkedHashSet`.

Suppose:

```java
List<Integer> numbers =
    Arrays.asList(10, 20, 10, 30, 20, 40, 30);
```

We want:

```text
10
20
30
40
```

while preserving the first occurrence order.

Use:

```java
Set<Integer> uniqueNumbers =
    new LinkedHashSet<>(numbers);
```

Now:

```java
System.out.println(uniqueNumbers);
```

Output:

```text
[10, 20, 30, 40]
```

---

## Why not HashSet?

A `HashSet` also removes duplicates, but it does not guarantee the original insertion order.

If order matters:

```text
Use LinkedHashSet
```

---

## Practice Task

Given:

```java
List<String> names = Arrays.asList(
    "Amit",
    "Rahul",
    "Amit",
    "Priya",
    "Rahul",
    "Sneha"
);
```

Create a `LinkedHashSet` that contains:

```text
Amit
Rahul
Priya
Sneha
```

in that order.

---

# 13. Output Prediction

## Question 1

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(10);
set.add(20);
set.add(10);

System.out.println(set);
```

Answer:

```text
[10, 20]
```

---

## Question 2

```java
Set<String> set = new LinkedHashSet<>();

set.add("B");
set.add("A");
set.add("C");

System.out.println(set);
```

Answer:

```text
[B, A, C]
```

---

## Question 3

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(10);
set.add(20);
set.add(30);

set.remove(20);

System.out.println(set);
```

Answer:

```text
[10, 30]
```

---

## Question 4

```java
Set<Integer> set = new LinkedHashSet<>();

System.out.println(set.add(10));
System.out.println(set.add(10));
System.out.println(set.contains(10));
```

Answer:

```text
true
false
true
```

---

## Question 5

```java
Set<String> set = new LinkedHashSet<>();

set.add("A");
set.add("B");
set.add("C");
set.add("B");

System.out.println(set);
```

Answer:

```text
[A, B, C]
```

---

## Question 6

```java
Set<Integer> set = new LinkedHashSet<>();

set.add(1);
set.add(2);
set.add(3);

set.remove(2);

set.add(2);

System.out.println(set);
```

Answer:

```text
[1, 3, 2]
```

### Why?

The original `2` was removed.

When `2` is added again, it becomes a new insertion and goes to the end.

---

# 14. LinkedHashSet from a Collection

You can construct a `LinkedHashSet` from another collection.

```java
List<String> names = Arrays.asList(
    "Amit",
    "Rahul",
    "Amit",
    "Priya",
    "Rahul"
);

Set<String> uniqueNames =
    new LinkedHashSet<>(names);

System.out.println(uniqueNames);
```

Output:

```text
[Amit, Rahul, Priya]
```

This is a convenient way to:

```text
Remove duplicates
+
Preserve original order
```

---

# 15. Custom Object Practice

`LinkedHashSet` can store custom objects.

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

Create:

```java
Set<Student> students = new LinkedHashSet<>();
```

However, if two students with the same `id` and `name` should be considered equal, implement `equals()` and `hashCode()` consistently.

```java
import java.util.Objects;

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
Set<Student> students = new LinkedHashSet<>();

students.add(new Student(1, "Amit"));
students.add(new Student(2, "Rahul"));
students.add(new Student(1, "Amit"));
```

The third object is considered a duplicate of the first.

---

## Practice Task

Create:

```text
Employee
```

with:

```text
id
name
department
```

Then:

1. Create `equals()`.
2. Create `hashCode()`.
3. Store employees in a `LinkedHashSet`.
4. Add duplicate employees.
5. Verify that duplicates are ignored.
6. Verify that the first insertion order of unique employees is maintained.

---

# 16. HashSet vs LinkedHashSet Practice

## Example

```java
Set<Integer> hashSet = new HashSet<>();
Set<Integer> linkedHashSet = new LinkedHashSet<>();

hashSet.add(30);
hashSet.add(10);
hashSet.add(20);

linkedHashSet.add(30);
linkedHashSet.add(10);
linkedHashSet.add(20);
```

### HashSet

```text
Order is not guaranteed.
```

### LinkedHashSet

```text
[30, 10, 20]
```

---

## Practice Question

You need:

> Unique usernames + order in which users registered.

Choose:

```text
LinkedHashSet
```

You need:

> Unique usernames + order does not matter.

Choose:

```text
HashSet
```

---

# 17. LinkedHashSet vs TreeSet Practice

Suppose:

```java
Set<Integer> linked =
    new LinkedHashSet<>();

Set<Integer> tree =
    new TreeSet<>();
```

Add:

```text
30
10
20
```

### LinkedHashSet

```text
[30, 10, 20]
```

### TreeSet

```text
[10, 20, 30]
```

---

## Key Difference

```text
LinkedHashSet
→ Preserves insertion order

TreeSet
→ Maintains sorted order
```

---

## Practice Question

Choose the correct collection:

| Requirement | Collection |
|---|---|
| Unique elements only | `HashSet` |
| Unique + insertion order | `LinkedHashSet` |
| Unique + sorted order | `TreeSet` |

---

# 18. Basic Coding Problems

## Problem 1 — Remove Duplicates

Given:

```java
int[] numbers = {
    10, 20, 10, 30, 20, 40
};
```

Remove duplicates using `LinkedHashSet`.

Expected:

```text
[10, 20, 30, 40]
```

---

## Problem 2 — Remove Duplicate Words

Given:

```text
Java is powerful Java is popular
```

Store the words in a `LinkedHashSet`.

Expected unique words, preserving first occurrence order:

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

Store characters in a:

```java
LinkedHashSet<Character>
```

The set should contain each distinct character once, in the order of its first appearance.

---

## Problem 4 — Check Duplicate

Given:

```java
int[] numbers = {
    5, 10, 15, 10, 20
};
```

Use `LinkedHashSet` to determine whether a duplicate exists.

Expected:

```text
true
```

---

## Problem 5 — Preserve First Occurrence

Given:

```java
int[] numbers = {
    5, 3, 5, 2, 3, 1
};
```

Expected:

```text
[5, 3, 2, 1]
```

---

# 19. Intermediate Problems

## Problem 1 — First Repeating Element

Given:

```java
int[] numbers = {
    5, 3, 4, 3, 5, 6
};
```

Find the first repeating element.

Expected:

```text
3
```

### Hint

Use:

```java
Set<Integer> seen = new LinkedHashSet<>();
```

and check the return value of:

```java
seen.add(number)
```

---

## Problem 2 — Preserve Unique Usernames

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

Create a `LinkedHashSet`.

Expected:

```text
[alice, bob, charlie, david]
```

---

## Problem 3 — Unique Search History

Suppose a user visits:

```text
Google
YouTube
GitHub
Google
StackOverflow
YouTube
```

Store the visited websites in a `LinkedHashSet`.

Expected:

```text
[Google, YouTube, GitHub, StackOverflow]
```

---

## Problem 4 — Unique Product Categories

Given:

```text
Laptop
Phone
Laptop
Tablet
Phone
Monitor
```

Store unique categories while preserving their first appearance.

Expected:

```text
[Laptop, Phone, Tablet, Monitor]
```

---

## Problem 5 — Common Elements

Given:

```java
Set<Integer> first =
    new LinkedHashSet<>(
        Arrays.asList(1, 2, 3, 4, 5)
    );

Set<Integer> second =
    new LinkedHashSet<>(
        Arrays.asList(3, 4, 5, 6, 7)
    );
```

Find the common elements.

Expected:

```text
3
4
5
```

---

# 20. Challenge Problems

## Challenge 1 — Remove Duplicates Without Changing Order

Given:

```java
List<Integer> numbers = Arrays.asList(
    4, 2, 4, 1, 2, 5, 1, 3
);
```

Produce:

```text
[4, 2, 1, 5, 3]
```

Use `LinkedHashSet`.

---

## Challenge 2 — First Unique Character

Given:

```text
swiss
```

Find the first character that occurs only once.

Expected:

```text
w
```

### Hint

A `LinkedHashSet` can help preserve character order, but you also need frequency information.

This is a useful exercise in recognizing that one collection may not be enough for every problem.

---

## Challenge 3 — Preserve First Appearance of Words

Given:

```text
java spring java boot spring java
```

Return:

```text
java spring boot
```

Use:

```java
LinkedHashSet<String>
```

---

## Challenge 4 — Remove Duplicate Objects

Create:

```java
class Product {

    int id;
    String name;
}
```

Store products in a `LinkedHashSet`.

Requirements:

- Same `id` and `name` should be considered duplicates.
- First insertion order should be preserved.
- Duplicate products should not be stored.

Implement:

```text
equals()
hashCode()
```

correctly.

---

## Challenge 5 — Ordered Unique Events

Given:

```text
LOGIN
SEARCH
VIEW
SEARCH
LOGOUT
LOGIN
```

Store the events in a `LinkedHashSet`.

Expected:

```text
[LOGIN, SEARCH, VIEW, LOGOUT]
```

Explain why `HashSet` is not the best choice if the output order must remain predictable.

---

# 21. Scenario-Based Practice

## Scenario 1 — Registration System

You need:

- Unique usernames.
- Registration order preserved.

Choose:

```java
Set<String> usernames =
    new LinkedHashSet<>();
```

---

## Scenario 2 — Duplicate Removal

You receive a list of IDs:

```text
101
102
101
103
102
104
```

You need unique IDs in their first-seen order.

Choose:

```text
LinkedHashSet
```

Expected:

```text
101
102
103
104
```

---

## Scenario 3 — Sorted Unique Values

You need:

- Unique values.
- Sorted ascending order.

Choose:

```text
TreeSet
```

not `LinkedHashSet`.

---

## Scenario 4 — Fast Membership Only

You need:

- Unique values.
- Fast average-case membership.
- No ordering requirement.

Choose:

```text
HashSet
```

rather than paying the additional ordering overhead of `LinkedHashSet`.

---

## Scenario 5 — Search History

A search application stores a user's unique search terms.

Requirements:

- Duplicate searches should be removed.
- First appearance order should be preserved.

A `LinkedHashSet<String>` is a good fit.

Example:

```java
Set<String> searches =
    new LinkedHashSet<>();

searches.add("Java");
searches.add("Spring Boot");
searches.add("Java");
searches.add("SQL");

System.out.println(searches);
```

Output:

```text
[Java, Spring Boot, SQL]
```

---

# 22. Practice Checklist

## Fundamentals

- [ ] Create a `LinkedHashSet`.
- [ ] Add elements.
- [ ] Remove elements.
- [ ] Check membership.
- [ ] Get the size.
- [ ] Check whether it is empty.
- [ ] Clear the set.
- [ ] Iterate through the set.

---

## Uniqueness

- [ ] Add duplicate integers.
- [ ] Add duplicate strings.
- [ ] Understand that duplicates are ignored.
- [ ] Use `add()` to detect duplicates.
- [ ] Understand duplicate insertion does not move an element.

---

## Ordering

- [ ] Understand insertion order.
- [ ] Verify iteration order.
- [ ] Remove an element and add it again.
- [ ] Observe that re-added elements go to the end.
- [ ] Understand that insertion order is not sorted order.

---

## Null

- [ ] Add `null`.
- [ ] Add `null` multiple times.
- [ ] Check `contains(null)`.
- [ ] Verify that only one `null` exists.

---

## Collections

- [ ] Create a `LinkedHashSet` from a `List`.
- [ ] Remove duplicates while preserving order.
- [ ] Compare `HashSet` and `LinkedHashSet`.
- [ ] Compare `LinkedHashSet` and `TreeSet`.

---

## Custom Objects

- [ ] Store custom objects.
- [ ] Override `equals()`.
- [ ] Override `hashCode()`.
- [ ] Test duplicate objects.
- [ ] Understand mutable-object risks.

---

## Problem Solving

- [ ] Remove duplicates from an array.
- [ ] Remove duplicate words.
- [ ] Find duplicate elements.
- [ ] Preserve first occurrence.
- [ ] Find common elements.
- [ ] Build unique search history.
- [ ] Remove duplicate objects.

---

# 23. Final Goal

After completing these exercises, you should be able to answer:

> **When should I use LinkedHashSet?**

A strong answer:

> Use `LinkedHashSet` when I need unique elements, want to preserve insertion order, and still want hash-based average-case O(1) operations such as `add()`, `remove()`, and `contains()`.

Remember:

```text
HashSet
→ Unique elements

LinkedHashSet
→ Unique elements
→ Insertion order

TreeSet
→ Unique elements
→ Sorted order
```

The key idea is:

> `LinkedHashSet` is useful when both **uniqueness** and **predictable insertion order** matter.

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
│   └── INTERVIEW.md   [x]
│
├── 14-LinkedHashSet
│   ├── NOTES.md       [x]
│   ├── PRACTICE.md    [x]
│   └── INTERVIEW.md   [ ]
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

**`14-LinkedHashSet/PRACTICE.md` is complete. Next: `14-LinkedHashSet/INTERVIEW.md`.**
