# TreeSet — Practice

> Hands-on practice for understanding `TreeSet`, sorted sets, navigation methods, custom ordering, `Comparable`, and `Comparator`.

---

## 📚 Table of Contents

1. [Practice Goals](#1-practice-goals)
2. [Create a TreeSet](#2-create-a-treeset)
3. [Add Elements](#3-add-elements)
4. [Duplicate Elements](#4-duplicate-elements)
5. [Sorted Order](#5-sorted-order)
6. [Natural Ordering](#6-natural-ordering)
7. [Comparator Ordering](#7-comparator-ordering)
8. [add() Return Value](#8-add-return-value)
9. [remove()](#9-remove)
10. [contains()](#10-contains)
11. [first() and last()](#11-first-and-last)
12. [lower(), floor(), higher(), ceiling()](#12-lower-floor-higher-ceiling)
13. [Iteration and Descending Order](#13-iteration-and-descending-order)
14. [pollFirst() and pollLast()](#14-pollfirst-and-polllast)
15. [Null Practice](#15-null-practice)
16. [TreeSet from a Collection](#16-treeset-from-a-collection)
17. [Range Views](#17-range-views)
18. [Custom Object Practice](#18-custom-object-practice)
19. [Comparable Practice](#19-comparable-practice)
20. [Comparator Practice](#20-comparator-practice)
21. [TreeSet vs HashSet](#21-treeset-vs-hashset)
22. [TreeSet vs LinkedHashSet](#22-treeset-vs-linkedhashset)
23. [TreeSet vs ArrayList](#23-treeset-vs-arraylist)
24. [Basic Coding Problems](#24-basic-coding-problems)
25. [Intermediate Problems](#25-intermediate-problems)
26. [Challenge Problems](#26-challenge-problems)
27. [Scenario-Based Practice](#27-scenario-based-practice)
28. [Practice Checklist](#28-practice-checklist)
29. [Final Goal](#29-final-goal)
30. [Progress](#30-progress)

---

# 1. Practice Goals

By completing this practice file, you should be able to:

- Create and initialize a `TreeSet`.
- Add and remove elements.
- Understand duplicate handling.
- Understand natural ordering.
- Use a custom `Comparator`.
- Use navigation methods.
- Find the smallest and largest elements.
- Iterate in ascending and descending order.
- Create range views.
- Understand `Comparable` with `TreeSet`.
- Understand `Comparator` with `TreeSet`.
- Work with custom objects.
- Solve common `TreeSet` problems.
- Choose between `TreeSet`, `HashSet`, `LinkedHashSet`, and `ArrayList`.

---

# 2. Create a TreeSet

## Basic Example

```java
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        TreeSet<Integer> numbers = new TreeSet<>();

        System.out.println(numbers);
    }
}
```

### Output

```text
[]
```

---

## Practice

Create:

```java
TreeSet<String> names = new TreeSet<>();
```

Add at least five names and print the set.

---

# 3. Add Elements

```java
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        TreeSet<Integer> numbers = new TreeSet<>();

        numbers.add(50);
        numbers.add(10);
        numbers.add(40);
        numbers.add(20);
        numbers.add(30);

        System.out.println(numbers);
    }
}
```

### Output

```text
[10, 20, 30, 40, 50]
```

### Observe

The elements were added in this order:

```text
50, 10, 40, 20, 30
```

But the `TreeSet` maintains them in sorted order:

```text
10, 20, 30, 40, 50
```

---

## Practice Task

Create a `TreeSet<Integer>` and add:

```text
75, 10, 90, 25, 40, 5, 60
```

Print the result.

### Expected Output

```text
[5, 10, 25, 40, 60, 75, 90]
```

---

# 4. Duplicate Elements

`TreeSet` does not allow duplicate elements according to its ordering.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

### Output

```text
[10, 20, 30]
```

---

## Practice

Predict the output:

```java
TreeSet<String> names = new TreeSet<>();

names.add("Java");
names.add("Spring");
names.add("Java");
names.add("Hibernate");
names.add("Spring");

System.out.println(names);
```

### Answer

```text
[Hibernate, Java, Spring]
```

---

# 5. Sorted Order

`TreeSet` maintains elements according to their ordering.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(100);
numbers.add(20);
numbers.add(75);
numbers.add(10);
numbers.add(50);

System.out.println(numbers);
```

### Output

```text
[10, 20, 50, 75, 100]
```

---

## Practice

Add these values:

```text
45, 12, 78, 3, 99, 34, 56
```

Verify that the output is:

```text
[3, 12, 34, 45, 56, 78, 99]
```

---

# 6. Natural Ordering

When no `Comparator` is provided, `TreeSet` uses the natural ordering of its elements.

## Integers

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(40);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

### Output

```text
[10, 20, 30, 40]
```

---

## Strings

```java
TreeSet<String> names = new TreeSet<>();

names.add("Charlie");
names.add("Alice");
names.add("Bob");
names.add("David");

System.out.println(names);
```

### Output

```text
[Alice, Bob, Charlie, David]
```

---

## Practice

Create a `TreeSet<String>` containing:

```text
Spring
Java
Docker
Hibernate
Maven
Git
```

Print the natural ordering.

---

# 7. Comparator Ordering

You can provide a `Comparator` to define custom ordering.

## Descending Integer Order

```java
import java.util.Comparator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        TreeSet<Integer> numbers =
                new TreeSet<>(Comparator.reverseOrder());

        numbers.add(10);
        numbers.add(50);
        numbers.add(20);
        numbers.add(40);
        numbers.add(30);

        System.out.println(numbers);
    }
}
```

### Output

```text
[50, 40, 30, 20, 10]
```

---

## Practice

Create a `TreeSet<Integer>` that stores numbers in descending order.

Add:

```text
15, 5, 40, 25, 10
```

### Expected Output

```text
[40, 25, 15, 10, 5]
```

---

# 8. add() Return Value

`add()` returns:

- `true` if the element was added.
- `false` if the element was already considered present according to the set's ordering.

```java
TreeSet<Integer> numbers = new TreeSet<>();

System.out.println(numbers.add(10));
System.out.println(numbers.add(20));
System.out.println(numbers.add(10));
```

### Output

```text
true
true
false
```

---

## Practice

Predict the output:

```java
TreeSet<String> set = new TreeSet<>();

System.out.println(set.add("Java"));
System.out.println(set.add("Spring"));
System.out.println(set.add("Java"));
System.out.println(set);
```

### Answer

```text
true
true
false
[Java, Spring]
```

---

# 9. remove()

Use `remove()` to delete an element.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers.remove(20));
System.out.println(numbers);
```

### Output

```text
true
[10, 30]
```

If the element does not exist:

```java
System.out.println(numbers.remove(100));
```

Output:

```text
false
```

---

## Practice

Given:

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);
```

Perform:

1. Remove `20`.
2. Remove `100`.
3. Print the final set.

---

# 10. contains()

Use `contains()` to check whether an element exists.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers.contains(20));
System.out.println(numbers.contains(50));
```

### Output

```text
true
false
```

---

## Practice

Create a `TreeSet<String>` containing:

```text
Java
Spring
Docker
Git
```

Check whether:

```text
Spring
Python
Git
```

are present.

---

# 11. first() and last()

## first()

Returns the smallest element according to the set's ordering.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(40);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers.first());
```

### Output

```text
10
```

---

## last()

Returns the largest element according to the set's ordering.

```java
System.out.println(numbers.last());
```

### Output

```text
40
```

---

## Practice

Given:

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(75);
numbers.add(25);
numbers.add(100);
numbers.add(50);
numbers.add(10);
```

Find:

```text
Smallest element:
Largest element:
```

### Expected

```text
Smallest element: 10
Largest element: 100
```

---

# 12. lower(), floor(), higher(), ceiling()

These methods are part of `NavigableSet`.

Consider:

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);
numbers.add(50);
```

---

## lower()

Returns the greatest element strictly less than the given element.

```java
System.out.println(numbers.lower(30));
```

Output:

```text
20
```

---

## floor()

Returns the greatest element less than or equal to the given element.

```java
System.out.println(numbers.floor(30));
```

Output:

```text
30
```

---

## higher()

Returns the smallest element strictly greater than the given element.

```java
System.out.println(numbers.higher(30));
```

Output:

```text
40
```

---

## ceiling()

Returns the smallest element greater than or equal to the given element.

```java
System.out.println(numbers.ceiling(30));
```

Output:

```text
30
```

---

## Easy Way to Remember

| Method | Meaning |
|---|---|
| `lower(x)` | `< x` |
| `floor(x)` | `<= x` |
| `higher(x)` | `> x` |
| `ceiling(x)` | `>= x` |

---

## Practice

Given:

```text
[10, 20, 30, 40, 50]
```

Find:

```text
lower(35)
floor(35)
higher(35)
ceiling(35)
```

### Expected Output

```text
lower(35): 30
floor(35): 30
higher(35): 40
ceiling(35): 40
```

---

## Practice with an Existing Value

Find:

```text
lower(30)
floor(30)
higher(30)
ceiling(30)
```

### Expected

```text
lower(30): 20
floor(30): 30
higher(30): 40
ceiling(30): 30
```

---

# 13. Iteration and Descending Order

## Enhanced for Loop

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(40);
numbers.add(10);
numbers.add(30);
numbers.add(20);

for (Integer number : numbers) {
    System.out.println(number);
}
```

### Output

```text
10
20
30
40
```

---

## descendingSet()

```java
System.out.println(numbers.descendingSet());
```

### Output

```text
[40, 30, 20, 10]
```

---

## descendingIterator()

```java
var iterator = numbers.descendingIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

### Output

```text
40
30
20
10
```

---

## Practice

Create:

```text
[10, 20, 30, 40, 50]
```

Print:

1. Ascending order.
2. Descending order.

---

# 14. pollFirst() and pollLast()

These methods retrieve and remove elements.

## pollFirst()

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

System.out.println(numbers.pollFirst());
System.out.println(numbers);
```

### Output

```text
10
[20, 30]
```

---

## pollLast()

```java
System.out.println(numbers.pollLast());
System.out.println(numbers);
```

### Output

```text
30
[20]
```

---

## Practice

Given:

```text
[10, 20, 30, 40, 50]
```

Execute:

```java
pollFirst();
pollLast();
```

Print the removed values and remaining set.

### Expected Remaining Set

```text
[20, 30, 40]
```

---

# 15. Null Practice

With natural ordering, `TreeSet` generally does not allow `null` because it needs to compare elements.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(null);
```

This results in a:

```text
NullPointerException
```

---

## Important

Do not assume that `TreeSet` supports `null` like `HashSet`.

A comparator can be explicitly designed to handle null values:

```java
TreeSet<Integer> numbers =
        new TreeSet<>(Comparator.nullsFirst(Comparator.naturalOrder()));

numbers.add(20);
numbers.add(null);
numbers.add(10);

System.out.println(numbers);
```

### Output

```text
[null, 10, 20]
```

---

# 16. TreeSet from a Collection

You can create a `TreeSet` from another collection.

```java
import java.util.ArrayList;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(50);
        numbers.add(10);
        numbers.add(30);
        numbers.add(10);
        numbers.add(20);

        TreeSet<Integer> set = new TreeSet<>(numbers);

        System.out.println(set);
    }
}
```

### Output

```text
[10, 20, 30, 50]
```

### Observe

The conversion:

- Removes duplicates.
- Maintains sorted order.

---

## Practice

Create an `ArrayList<Integer>` containing:

```text
40, 10, 30, 20, 10, 40, 50
```

Convert it into a `TreeSet`.

Expected:

```text
[10, 20, 30, 40, 50]
```

---

# 17. Range Views

`TreeSet` provides range operations through `NavigableSet`.

---

## headSet()

Returns elements before a specified element.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);
numbers.add(50);

System.out.println(numbers.headSet(30));
```

### Output

```text
[10, 20]
```

The endpoint is exclusive by default.

---

## tailSet()

Returns elements from a specified element onward.

```java
System.out.println(numbers.tailSet(30));
```

### Output

```text
[30, 40, 50]
```

The endpoint is inclusive by default.

---

## subSet()

Returns elements between two endpoints.

```java
System.out.println(numbers.subSet(20, 50));
```

### Output

```text
[20, 30, 40]
```

The lower endpoint is inclusive and the upper endpoint is exclusive.

---

## Inclusive / Exclusive Control

```java
System.out.println(
        numbers.subSet(20, true, 50, true)
);
```

### Output

```text
[20, 30, 40, 50]
```

---

## headSet() with Inclusive Endpoint

```java
System.out.println(numbers.headSet(30, true));
```

### Output

```text
[10, 20, 30]
```

---

## tailSet() with Exclusive Endpoint

```java
System.out.println(numbers.tailSet(30, false));
```

### Output

```text
[40, 50]
```

---

## Practice

Given:

```text
[10, 20, 30, 40, 50, 60]
```

Find:

```text
headSet(40)
tailSet(40)
subSet(20, 50)
subSet(20, true, 50, true)
```

### Expected

```text
[10, 20, 30]

[40, 50, 60]

[20, 30, 40]

[20, 30, 40, 50]
```

---

# 18. Custom Object Practice

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

A `TreeSet<Student>` needs an ordering.

You cannot simply do:

```java
TreeSet<Student> students = new TreeSet<>();
```

unless `Student` has a natural ordering or the set receives a suitable comparator.

---

## Practice Task

Create a `Student` class with:

```text
id
name
marks
```

Then create a `TreeSet<Student>` sorted by:

```text
marks
```

---

# 19. Comparable Practice

`Comparable` defines the natural ordering of a class.

## Example

```java
import java.util.TreeSet;

class Student implements Comparable<Student> {

    int id;
    String name;
    int marks;

    Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.marks, other.marks);
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + marks;
    }
}

public class Main {

    public static void main(String[] args) {

        TreeSet<Student> students = new TreeSet<>();

        students.add(new Student(1, "Alice", 85));
        students.add(new Student(2, "Bob", 70));
        students.add(new Student(3, "Charlie", 95));

        System.out.println(students);
    }
}
```

### Output

```text
[2 - Bob - 70, 1 - Alice - 85, 3 - Charlie - 95]
```

---

## Practice Task

Modify the example so students are sorted by:

```text
id
```

instead of marks.

---

# 20. Comparator Practice

Instead of modifying the class's natural ordering, use a `Comparator`.

```java
TreeSet<Student> students =
        new TreeSet<>(
                Comparator.comparingInt(student -> student.marks)
        );
```

Now the `TreeSet` orders students by marks.

---

## Descending Marks

```java
TreeSet<Student> students =
        new TreeSet<>(
                Comparator.comparingInt(
                        (Student student) -> student.marks
                ).reversed()
        );
```

---

## Sort by Name

```java
TreeSet<Student> students =
        new TreeSet<>(
                Comparator.comparing(student -> student.name)
        );
```

---

## Practice Tasks

Create different `TreeSet<Student>` objects:

### Task 1

Sort by:

```text
id ascending
```

### Task 2

Sort by:

```text
marks ascending
```

### Task 3

Sort by:

```text
marks descending
```

### Task 4

Sort by:

```text
name alphabetically
```

### Task 5

Sort by:

```text
marks ascending
then name alphabetically
```

Hint:

```java
Comparator.comparingInt((Student s) -> s.marks)
          .thenComparing(s -> s.name);
```

---

# 21. TreeSet vs HashSet

Practice identifying the correct collection.

| Requirement | Better Choice |
|---|---|
| Need unique elements | `HashSet` / `TreeSet` |
| Need sorted elements | `TreeSet` |
| Need fastest average basic set operations | `HashSet` |
| Need navigation methods | `TreeSet` |
| Need range queries | `TreeSet` |
| Need insertion order | `LinkedHashSet` |

---

## Practice Question

Which collection would you choose?

### Question 1

Store unique employee IDs without caring about order.

**Answer:**

```text
HashSet
```

### Question 2

Store unique employee IDs in ascending order.

**Answer:**

```text
TreeSet
```

### Question 3

Store unique employee IDs in insertion order.

**Answer:**

```text
LinkedHashSet
```

---

# 22. TreeSet vs LinkedHashSet

Consider:

```java
TreeSet<Integer> treeSet = new TreeSet<>();

treeSet.add(30);
treeSet.add(10);
treeSet.add(20);

System.out.println(treeSet);
```

Output:

```text
[10, 20, 30]
```

Now:

```java
LinkedHashSet<Integer> linkedSet = new LinkedHashSet<>();

linkedSet.add(30);
linkedSet.add(10);
linkedSet.add(20);

System.out.println(linkedSet);
```

Output:

```text
[30, 10, 20]
```

---

## Practice Question

If the requirement is:

> "Remove duplicates but preserve the order in which users entered the data."

Which should you use?

```text
LinkedHashSet
```

If the requirement is:

> "Remove duplicates and keep the values sorted."

Use:

```text
TreeSet
```

---

# 23. TreeSet vs ArrayList

Consider the requirements:

| Requirement | Collection |
|---|---|
| Allow duplicates | `ArrayList` |
| Index-based access | `ArrayList` |
| Unique elements | `TreeSet` |
| Automatically maintain sorted order | `TreeSet` |
| `get(index)` | `ArrayList` |
| Navigation methods | `TreeSet` |
| Range views | `TreeSet` |

---

## Important

`TreeSet` does **not** provide:

```java
get(index)
```

This is invalid:

```java
numbers.get(0);
```

If you need index-based access, use a `List`.

---

# 24. Basic Coding Problems

## Problem 1 — Remove Duplicates and Sort

Given:

```text
[50, 20, 10, 50, 30, 20, 40]
```

Use `TreeSet` to produce:

```text
[10, 20, 30, 40, 50]
```

---

## Problem 2 — Find Minimum

Given:

```text
[45, 10, 78, 23, 5, 90]
```

Use `TreeSet` to find the minimum.

Expected:

```text
5
```

---

## Problem 3 — Find Maximum

Find the maximum from:

```text
[45, 10, 78, 23, 5, 90]
```

Expected:

```text
90
```

---

## Problem 4 — Check Element

Create a `TreeSet<Integer>` and check whether:

```text
75
```

exists.

---

## Problem 5 — Remove Element

Create:

```text
[10, 20, 30, 40, 50]
```

Remove:

```text
30
```

Expected:

```text
[10, 20, 40, 50]
```

---

## Problem 6 — Print in Descending Order

Create:

```text
[10, 20, 30, 40, 50]
```

Print:

```text
50 40 30 20 10
```

---

## Problem 7 — Find Nearest Lower Value

Given:

```text
[10, 20, 30, 40, 50]
```

Find the greatest value less than:

```text
35
```

Expected:

```text
30
```

Use:

```java
lower()
```

---

## Problem 8 — Find Nearest Greater Value

Given:

```text
[10, 20, 30, 40, 50]
```

Find the smallest value greater than:

```text
35
```

Expected:

```text
40
```

Use:

```java
higher()
```

---

# 25. Intermediate Problems

## Problem 1 — Floor and Ceiling

Given:

```text
[10, 20, 30, 40, 50]
```

For:

```text
35
```

find:

```text
floor
ceiling
```

Expected:

```text
floor: 30
ceiling: 40
```

---

## Problem 2 — Find Values in a Range

Given:

```text
[10, 20, 30, 40, 50, 60, 70]
```

Print all values between:

```text
25 and 65
```

Expected:

```text
[30, 40, 50, 60]
```

---

## Problem 3 — Inclusive Range

Print values from:

```text
20 to 60
```

including both endpoints.

Expected:

```text
[20, 30, 40, 50, 60]
```

---

## Problem 4 — Remove Minimum and Maximum

Given:

```text
[10, 20, 30, 40, 50]
```

Remove both:

```text
minimum
maximum
```

Expected:

```text
[20, 30, 40]
```

Use:

```java
pollFirst();
pollLast();
```

---

## Problem 5 — Find Closest Lower and Higher

Given:

```text
[10, 20, 30, 40, 50]
```

For:

```text
33
```

find:

```text
lower value
higher value
```

Expected:

```text
lower: 30
higher: 40
```

---

# 26. Challenge Problems

## Challenge 1 — Third Smallest Unique Number

Given:

```text
[50, 10, 20, 10, 40, 30, 20, 60]
```

Find the third smallest unique number.

Expected:

```text
30
```

### Hint

Use a `TreeSet` and iterate through it.

---

## Challenge 2 — Remove Values Below a Limit

Given:

```text
[5, 10, 15, 20, 25, 30]
```

Remove all values below:

```text
20
```

Expected:

```text
[20, 25, 30]
```

Try solving this using a range view.

---

## Challenge 3 — Find Nearest Value

Given:

```text
[10, 20, 30, 40, 50]
```

Find the value closest to:

```text
34
```

Expected:

```text
30
```

### Hint

Compare:

```java
floor()
ceiling()
```

---

## Challenge 4 — Student Ranking

Create a `Student` class:

```text
id
name
marks
```

Store students in a `TreeSet` sorted by:

```text
marks descending
```

Example:

```text
1 - Alice - 95
2 - Bob - 85
3 - Charlie - 75
```

Expected ordering:

```text
Alice
Bob
Charlie
```

---

## Challenge 5 — Employee Salary Ranking

Create an `Employee` class:

```text
id
name
salary
```

Store employees in a `TreeSet` sorted by:

```text
salary descending
```

If two employees have the same salary, sort them by:

```text
name
```

---

# 27. Scenario-Based Practice

## Scenario 1 — Sorted Unique Scores

You are developing a leaderboard.

Requirements:

- Scores must be unique.
- Scores should always be sorted.
- Need the highest score.
- Need the lowest score.
- Need to find the nearest score below a target.

### Question

Which collection should you use?

**Answer:**

```text
TreeSet<Integer>
```

---

## Scenario 2 — Unique Product Prices

A shopping application needs to store unique product prices.

Requirements:

- No duplicate prices.
- Prices must remain sorted.
- Find the next price greater than a given price.

### Answer

```text
TreeSet
```

Useful method:

```java
higher()
```

---

## Scenario 3 — Exam Marks

Store unique exam marks in ascending order.

Requirements:

- Remove duplicates.
- Find minimum.
- Find maximum.
- Find marks immediately below a given mark.

### Answer

```java
TreeSet<Integer>
```

---

## Scenario 4 — User Names

You need:

- Unique usernames.
- Alphabetical order.
- Ability to find the first and last username.

### Answer

```java
TreeSet<String>
```

---

## Scenario 5 — Preserve Input Order

You need:

- Unique usernames.
- Preserve insertion order.
- No sorting required.

### Answer

```text
LinkedHashSet
```

Not:

```text
TreeSet
```

---

# 28. Practice Checklist

## Basic Operations

- [ ] Create a `TreeSet`.
- [ ] Add elements.
- [ ] Remove elements.
- [ ] Check elements using `contains()`.
- [ ] Check size using `size()`.
- [ ] Clear the set.
- [ ] Check whether the set is empty.

---

## Ordering

- [ ] Understand natural ordering.
- [ ] Practice integer ordering.
- [ ] Practice string ordering.
- [ ] Use `Comparator.reverseOrder()`.
- [ ] Create a custom comparator.
- [ ] Understand ascending order.
- [ ] Understand descending order.

---

## Navigation

- [ ] Practice `first()`.
- [ ] Practice `last()`.
- [ ] Practice `lower()`.
- [ ] Practice `floor()`.
- [ ] Practice `higher()`.
- [ ] Practice `ceiling()`.
- [ ] Practice `pollFirst()`.
- [ ] Practice `pollLast()`.

---

## Range Operations

- [ ] Practice `headSet()`.
- [ ] Practice `tailSet()`.
- [ ] Practice `subSet()`.
- [ ] Practice inclusive ranges.
- [ ] Practice exclusive ranges.
- [ ] Understand that range methods return views.

---

## Iteration

- [ ] Use enhanced `for` loop.
- [ ] Use `iterator()`.
- [ ] Use `descendingIterator()`.
- [ ] Use `descendingSet()`.

---

## Custom Objects

- [ ] Create a custom class.
- [ ] Implement `Comparable`.
- [ ] Override `compareTo()`.
- [ ] Create a `TreeSet` using natural ordering.
- [ ] Create a `TreeSet` using `Comparator`.
- [ ] Sort by multiple fields.
- [ ] Understand comparison-based uniqueness.

---

# 29. Final Goal

After completing this practice, you should be comfortable writing code like:

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(50);
numbers.add(10);
numbers.add(30);
numbers.add(20);
numbers.add(40);

System.out.println(numbers);
System.out.println(numbers.first());
System.out.println(numbers.last());
System.out.println(numbers.lower(25));
System.out.println(numbers.floor(25));
System.out.println(numbers.higher(25));
System.out.println(numbers.ceiling(25));
System.out.println(numbers.descendingSet());
```

Expected output:

```text
[10, 20, 30, 40, 50]
10
50
20
20
30
30
[50, 40, 30, 20, 10]
```

You should also understand when to choose:

```text
HashSet
LinkedHashSet
TreeSet
ArrayList
```

based on the application's requirements.

---

# 30. Progress

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
│   └── INTERVIEW.md   [ ]
│
├── 16-Map
├── 17-HashMap
├── 18-LinkedHashMap
├── 19-TreeMap
├── 20-Hashtable
├── 21-ConcurrentHashMap
├── 22-Comparable
└── 23-Comparator
```

**`15-TreeSet/PRACTICE.md` is complete. Next: `15-TreeSet/INTERVIEW.md`.**
