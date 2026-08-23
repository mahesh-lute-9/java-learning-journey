# Collection Interface - Practice

> Practice problems to strengthen your understanding of the `Collection` interface and its common operations.

---

## Table of Contents

- [Level 1 - Basic Operations](#level-1---basic-operations)
- [Level 2 - Searching and Bulk Operations](#level-2---searching-and-bulk-operations)
- [Level 3 - Removal Operations](#level-3---removal-operations)
- [Level 4 - Iteration](#level-4---iteration)
- [Level 5 - Interface and Polymorphism](#level-5---interface-and-polymorphism)
- [Level 6 - Code Analysis](#level-6---code-analysis)
- [Level 7 - Real-World Problems](#level-7---real-world-problems)
- [Challenge Problems](#challenge-problems)
- [Practice Checklist](#practice-checklist)

---

# Level 1 - Basic Operations

## 1. Create a Collection

Create a `Collection<Integer>` using `ArrayList`.

Add:

```text
10
20
30
40
50
```

Print the collection.

### Expected Output

```text
[10, 20, 30, 40, 50]
```

### Concepts

- `Collection`
- `ArrayList`
- `add()`

---

## 2. Find Collection Size

Given:

```java
Collection<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Python",
                        "C++",
                        "Dart"
                )
        );
```

Print the number of elements.

### Expected Output

```text
4
```

---

## 3. Check Whether Collection Is Empty

Create:

```java
Collection<Integer> numbers =
        new ArrayList<>();
```

Check whether it is empty.

### Expected Output

```text
true
```

Then add one element and check again.

### Expected Output

```text
false
```

---

## 4. Add Multiple Elements

Create:

```java
Collection<String> languages =
        new ArrayList<>();
```

Add:

```text
Java
Spring
Python
C++
```

Print the result.

---

## 5. Remove an Element

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );
```

Remove:

```text
30
```

### Expected Output

```text
[10, 20, 40]
```

---

# Level 2 - Searching and Bulk Operations

## 6. Check Whether an Element Exists

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40, 50)
        );
```

Check whether:

```text
30
```

exists.

### Expected Output

```text
true
```

---

## 7. Search for Missing Element

Using the same collection, check whether:

```text
100
```

exists.

### Expected Output

```text
false
```

---

## 8. Check Multiple Elements

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40, 50)
        );

Collection<Integer> required =
        List.of(20, 30, 40);
```

Use:

```java
containsAll()
```

to determine whether all required elements exist.

### Expected Output

```text
true
```

---

## 9. Check Multiple Missing Elements

Given:

```java
Collection<Integer> required =
        List.of(20, 30, 100);
```

Check whether all elements exist in:

```java
[10, 20, 30, 40, 50]
```

### Expected Output

```text
false
```

---

## 10. Combine Two Collections

Given:

```java
Collection<Integer> first =
        new ArrayList<>(
                List.of(10, 20, 30)
        );

Collection<Integer> second =
        List.of(40, 50, 60);
```

Use:

```java
addAll()
```

to combine them.

### Expected Output

```text
[10, 20, 30, 40, 50, 60]
```

---

# Level 3 - Removal Operations

## 11. Remove Multiple Elements

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 30, 40, 50
                )
        );

Collection<Integer> remove =
        List.of(20, 40);
```

Use:

```java
removeAll()
```

### Expected Output

```text
[10, 30, 50]
```

---

## 12. Retain Only Selected Elements

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 30, 40, 50
                )
        );

Collection<Integer> keep =
        List.of(20, 40);
```

Use:

```java
retainAll()
```

### Expected Output

```text
[20, 40]
```

---

## 13. Remove All Elements

Given:

```java
Collection<String> names =
        new ArrayList<>(
                List.of(
                        "Mahesh",
                        "Rahul",
                        "Amit"
                )
        );
```

Use:

```java
clear()
```

Then print:

```java
names
```

### Expected Output

```text
[]
```

---

## 14. Remove Odd Numbers

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 15, 20, 25, 30, 35
                )
        );
```

Use:

```java
removeIf()
```

to remove all odd numbers.

### Expected Output

```text
[10, 20, 30]
```

---

## 15. Remove Numbers Greater Than 50

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 25, 50, 60, 75, 90
                )
        );
```

Use `removeIf()`.

### Expected Output

```text
[10, 25, 50]
```

---

# Level 4 - Iteration

## 16. Print Using Enhanced For Loop

Given:

```java
Collection<String> languages =
        new ArrayList<>(
                List.of(
                        "Java",
                        "Python",
                        "C++"
                )
        );
```

Print every element using:

```java
for-each
```

---

## 17. Print Using Iterator

Use an `Iterator` instead of an enhanced `for` loop.

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );
```

### Expected Output

```text
10
20
30
40
```

---

## 18. Calculate Sum

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40, 50)
        );
```

Calculate the sum using iteration.

### Expected Output

```text
150
```

---

## 19. Find Maximum

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 90, 30, 50, 20)
        );
```

Find the maximum value without using:

```java
Collections.max()
```

### Expected Output

```text
90
```

---

## 20. Find Minimum

Using the same approach, find the minimum value.

### Expected Output

```text
10
```

---

# Level 5 - Interface and Polymorphism

## 21. Collection Reference With ArrayList

Create:

```java
Collection<Integer> numbers =
        new ArrayList<>();
```

Add five numbers.

Explain why the following is valid:

```java
Collection<Integer> numbers =
        new ArrayList<>();
```

but:

```java
Collection<Integer> numbers =
        new Collection<>();
```

is invalid.

---

## 22. Change the Implementation

Create a method:

```java
static void printCollection(
        Collection<Integer> numbers
) {
    // ...
}
```

Call it using:

```java
ArrayList
HashSet
LinkedList
```

Example:

```java
printCollection(
        new ArrayList<>(List.of(10, 20, 30))
);

printCollection(
        new HashSet<>(List.of(10, 20, 30))
);

printCollection(
        new LinkedList<>(List.of(10, 20, 30))
);
```

### Goal

Understand programming to an interface.

---

## 23. Generic Collection Method

Create:

```java
static void printAll(
        Collection<String> collection
) {
    // ...
}
```

Use it with:

```java
ArrayList<String>
```

and:

```java
LinkedList<String>
```

---

## 24. Collection as Method Parameter

Create:

```java
static int countElements(
        Collection<?> collection
) {
    // ...
}
```

The method should return the number of elements.

Test it with:

```java
ArrayList
HashSet
LinkedList
```

---

# Level 6 - Code Analysis

## 25. Predict the Output

```java
Collection<Integer> numbers =
        new ArrayList<>();

System.out.println(numbers.add(10));
System.out.println(numbers.add(20));
System.out.println(numbers.add(10));

System.out.println(numbers);
```

### Questions

1. What is the output?
2. Why can the duplicate `10` be added?
3. What would happen with a `HashSet`?

---

## 26. Predict the Output

```java
Collection<Integer> numbers =
        new HashSet<>();

System.out.println(numbers.add(10));
System.out.println(numbers.add(10));

System.out.println(numbers.size());
```

### Questions

1. What is printed by the first `add()`?
2. What is printed by the second `add()`?
3. What is the final size?

---

## 27. Analyze `removeAll()`

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

numbers.removeAll(
        List.of(20, 40)
);

System.out.println(numbers);
```

### Expected Output

```text
[10, 30]
```

Explain why.

---

## 28. Analyze `retainAll()`

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

numbers.retainAll(
        List.of(20, 40)
);

System.out.println(numbers);
```

### Expected Output

```text
[20, 40]
```

Explain the difference between this and `removeAll()`.

---

## 29. Analyze `removeIf()`

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(10, 15, 20, 25, 30)
        );

numbers.removeIf(
        number -> number % 5 == 0
);

System.out.println(numbers);
```

### Question

What will be printed?

> Think carefully about the condition before answering.

---

# Level 7 - Real-World Problems

## 30. Student Names

Create:

```java
Collection<String> students
```

Store:

```text
Mahesh
Rahul
Amit
Sneha
Priya
```

Perform:

1. Add a student.
2. Remove a student.
3. Search for a student.
4. Print total students.
5. Print all students.

---

## 31. Course Registration

Create:

```java
Collection<String> courses
```

Store:

```text
Java
DBMS
OS
Computer Networks
DSA
```

Perform:

1. Check whether `Java` exists.
2. Check whether `Python` exists.
3. Remove `OS`.
4. Add `Spring Boot`.
5. Print all courses.

---

## 32. Common Elements Between Collections

Given:

```java
Collection<Integer> first =
        new ArrayList<>(
                List.of(10, 20, 30, 40, 50)
        );

Collection<Integer> second =
        List.of(20, 40, 60, 80);
```

Find the common elements.

### Expected Result

```text
[20, 40]
```

### Hint

Use:

```java
retainAll()
```

---

## 33. Difference Between Collections

Given:

```java
Collection<Integer> first =
        new ArrayList<>(
                List.of(10, 20, 30, 40)
        );

Collection<Integer> second =
        List.of(20, 40);
```

Find elements that exist in `first` but not in `second`.

### Expected Result

```text
[10, 30]
```

### Hint

Use:

```java
removeAll()
```

---

## 34. Filter a Collection

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(
                        5, 10, 15, 20,
                        25, 30, 35, 40
                )
        );
```

Remove all numbers that are not divisible by `10`.

### Expected Result

```text
[10, 20, 30, 40]
```

---

## 35. Collection Statistics

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 30, 40, 50
                )
        );
```

Calculate:

- Number of elements
- Sum
- Minimum
- Maximum
- Average

Do not use:

```java
Collections.min()
Collections.max()
```

---

# Challenge Problems

## Challenge 1 - Generic Collection Analyzer

Create a generic method:

```java
static <T> void analyze(
        Collection<T> collection
) {
    // ...
}
```

The method should print:

```text
Size
Empty?
Elements
```

Example:

```java
analyze(
        List.of("Java", "Spring", "SQL")
);
```

Expected output:

```text
Size: 3
Empty: false
Elements:
Java
Spring
SQL
```

---

## Challenge 2 - Collection Intersection

Create a method:

```java
static <T> Collection<T> intersection(
        Collection<T> first,
        Collection<T> second
) {
    // ...
}
```

Example:

```java
Collection<Integer> first =
        List.of(1, 2, 3, 4, 5);

Collection<Integer> second =
        List.of(3, 4, 5, 6, 7);
```

Expected result:

```text
[3, 4, 5]
```

---

## Challenge 3 - Collection Difference

Create:

```java
static <T> Collection<T> difference(
        Collection<T> first,
        Collection<T> second
) {
    // ...
}
```

For:

```text
first  = [1, 2, 3, 4, 5]
second = [2, 4]
```

Expected result:

```text
[1, 3, 5]
```

---

## Challenge 4 - Remove Duplicates Without Using Set

Given:

```java
Collection<Integer> numbers =
        new ArrayList<>(
                List.of(
                        10, 20, 10,
                        30, 20, 40,
                        30
                )
        );
```

Create a solution that removes duplicates.

### Expected Result

```text
[10, 20, 30, 40]
```

### Restriction

Do not use:

```java
Set
```

---

## Challenge 5 - Generic Filter

Create:

```java
static <T> void filter(
        Collection<T> collection,
        Predicate<T> condition
) {
    // ...
}
```

Use it to filter:

```java
Collection<Integer>
```

and:

```java
Collection<String>
```

---

# Practice Checklist

## Level 1 - Basic Operations

- [ ] Create a `Collection`
- [ ] Add elements
- [ ] Remove an element
- [ ] Check size
- [ ] Check emptiness

## Level 2 - Searching and Bulk Operations

- [ ] `contains()`
- [ ] `containsAll()`
- [ ] `addAll()`

## Level 3 - Removal Operations

- [ ] `removeAll()`
- [ ] `retainAll()`
- [ ] `clear()`
- [ ] `removeIf()`

## Level 4 - Iteration

- [ ] Enhanced `for`
- [ ] `Iterator`
- [ ] Sum
- [ ] Minimum
- [ ] Maximum

## Level 5 - Interfaces

- [ ] Interface reference
- [ ] Polymorphism
- [ ] Programming to an interface
- [ ] Generic methods

## Level 6 - Code Analysis

- [ ] Predict `add()` behavior
- [ ] Understand duplicates
- [ ] Understand `removeAll()`
- [ ] Understand `retainAll()`
- [ ] Understand `removeIf()`

## Level 7 - Real-World Problems

- [ ] Student collection
- [ ] Course registration
- [ ] Intersection
- [ ] Difference
- [ ] Filtering
- [ ] Collection statistics

## Challenges

- [ ] Generic analyzer
- [ ] Generic intersection
- [ ] Generic difference
- [ ] Remove duplicates without `Set`
- [ ] Generic filtering

---

# Key Practice Goals

By the end of this practice set, you should be comfortable with:

```text
Collection
    |
    ├── add()
    ├── addAll()
    ├── remove()
    ├── removeAll()
    ├── retainAll()
    ├── removeIf()
    ├── contains()
    ├── containsAll()
    ├── size()
    ├── isEmpty()
    ├── clear()
    └── iterator()
```

You should also understand:

```text
Collection
    ↓
Common interface
    ↓
List / Set / Queue
    ↓
Different implementations
    ↓
Different behaviors + performance
```

---

# Practice Completion

```text
02-Collection-Interface/
├── NOTES.md       ✅
├── PRACTICE.md    ✅
└── INTERVIEW.md   ⏳
```

> Complete the interview questions before moving to `03-List`.
