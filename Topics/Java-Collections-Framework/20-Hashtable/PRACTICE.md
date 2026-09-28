# Hashtable — Practice

> Hands-on exercises to understand `Hashtable` through implementation, experiments, and comparisons.

---

## 📚 Table of Contents

- [1. Basic Hashtable](#1-basic-hashtable)
- [2. Insert Key-Value Pairs](#2-insert-key-value-pairs)
- [3. Retrieve Values](#3-retrieve-values)
- [4. Update Existing Key](#4-update-existing-key)
- [5. Remove Mapping](#5-remove-mapping)
- [6. containsKey()](#6-containskey)
- [7. containsValue()](#7-containsvalue)
- [8. size() and isEmpty()](#8-size-and-isempty)
- [9. clear()](#9-clear)
- [10. putIfAbsent()](#10-putifabsent)
- [11. replace()](#11-replace)
- [12. Iteration with entrySet()](#12-iteration-with-entryset)
- [13. keySet() and values()](#13-keyset-and-values)
- [14. Enumeration](#14-enumeration)
- [15. Null Key Experiment](#15-null-key-experiment)
- [16. Null Value Experiment](#16-null-value-experiment)
- [17. Duplicate Keys](#17-duplicate-keys)
- [18. Duplicate Values](#18-duplicate-values)
- [19. Hashing Experiment](#19-hashing-experiment)
- [20. Custom Object Keys](#20-custom-object-keys)
- [21. Hashtable vs HashMap](#21-hashtable-vs-hashmap)
- [22. Hashtable vs ConcurrentHashMap](#22-hashtable-vs-concurrenthashmap)
- [23. Frequency Counter](#23-frequency-counter)
- [24. Mini Project](#24-mini-project)
- [25. Challenge Problems](#25-challenge-problems)
- [26. Practice Checklist](#26-practice-checklist)
- [27. Progress](#27-progress)

---

# 1. Basic Hashtable

Create a `Hashtable` that stores student roll numbers and names.

### Task

Store:

```text
101 → Mahesh
102 → Rahul
103 → Priya
```

### Example

```java
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {

        Hashtable<Integer, String> students = new Hashtable<>();

        students.put(101, "Mahesh");
        students.put(102, "Rahul");
        students.put(103, "Priya");

        System.out.println(students);
    }
}
```

> Do not rely on the printed order of a `Hashtable`.

---

# 2. Insert Key-Value Pairs

Practice the `put()` method.

### Task

Create:

```java
Hashtable<String, Integer> marks
```

Insert marks for:

- Java
- DSA
- DBMS
- Operating Systems

Then print the map.

### Questions

1. What happens if you insert the same key twice?
2. Can two keys have the same value?
3. Can a `Hashtable` contain a `null` key?

---

# 3. Retrieve Values

Practice:

```java
get()
```

### Task

Create a `Hashtable<Integer, String>` containing employee IDs and names.

Retrieve the name associated with ID `102`.

### Example

```java
String name = employees.get(102);

System.out.println(name);
```

### Experiment

Try:

```java
System.out.println(employees.get(999));
```

Observe the result when the key does not exist.

---

# 4. Update Existing Key

Use `put()` to update an existing mapping.

### Task

Create:

```text
101 → Java
102 → Python
103 → SQL
```

Change:

```text
102 → Spring Boot
```

### Example

```java
courses.put(102, "Spring Boot");
```

### Verify

```java
System.out.println(courses.get(102));
```

Expected:

```text
Spring Boot
```

---

# 5. Remove Mapping

Practice:

```java
remove()
```

### Task

Create a `Hashtable` containing five products.

Remove one product using its key.

### Example

```java
products.remove(103);
```

### Questions

- What does `remove()` return?
- What happens if the key does not exist?

---

# 6. containsKey()

Practice checking whether a key exists.

### Example

```java
if (students.containsKey(101)) {
    System.out.println("Student exists");
}
```

### Task

Create a student directory and check:

- existing key
- non-existing key

### Challenge

Write a program that prints:

```text
Student Found
```

or

```text
Student Not Found
```

depending on the key.

---

# 7. containsValue()

Practice:

```java
containsValue()
```

### Task

Create:

```text
101 → Java
102 → Python
103 → SQL
```

Check whether `"Java"` exists.

```java
if (courses.containsValue("Java")) {
    System.out.println("Course found");
}
```

### Interview Note

`containsValue()` generally requires scanning the values, so it is typically **O(n)**.

---

# 8. size() and isEmpty()

Practice:

```java
size()
isEmpty()
```

### Example

```java
System.out.println(students.size());
System.out.println(students.isEmpty());
```

### Task

1. Create an empty `Hashtable`.
2. Check `isEmpty()`.
3. Add three entries.
4. Check `size()`.
5. Remove all entries.
6. Check `isEmpty()` again.

---

# 9. clear()

Practice removing all mappings.

### Example

```java
students.clear();
```

### Task

Create a `Hashtable` with five entries.

Print:

```text
Before clear: 5
After clear: 0
```

Use:

```java
size()
```

to verify the result.

---

# 10. putIfAbsent()

Practice:

```java
putIfAbsent()
```

### Example

```java
students.put(101, "Mahesh");

students.putIfAbsent(101, "Rahul");
```

### Question

What should the value of key `101` be?

Expected:

```text
Mahesh
```

Because the key already exists.

### Experiment

Try:

```java
students.putIfAbsent(102, "Rahul");
```

What happens?

---

# 11. replace()

Practice:

```java
replace()
```

### Example

```java
students.replace(101, "Mahesh Updated");
```

### Conditional Replace

Try:

```java
students.replace(101, "Mahesh", "Mahesh Updated");
```

The replacement happens only if the existing value matches `"Mahesh"`.

### Task

Experiment with:

```java
replace(key, newValue)
replace(key, oldValue, newValue)
```

Record the return values.

---

# 12. Iteration with entrySet()

Practice the recommended modern way to iterate through mappings.

### Example

```java
for (Map.Entry<Integer, String> entry : students.entrySet()) {

    System.out.println(
        entry.getKey() + " → " + entry.getValue()
    );
}
```

Remember to import:

```java
import java.util.Map;
```

### Task

Create a `Hashtable` containing:

```text
101 → Mahesh
102 → Rahul
103 → Priya
104 → Ankit
```

Print every key-value pair.

---

# 13. keySet() and values()

Practice:

```java
keySet()
values()
```

### Keys

```java
for (Integer key : students.keySet()) {
    System.out.println(key);
}
```

### Values

```java
for (String value : students.values()) {
    System.out.println(value);
}
```

### Task

Print:

```text
All Keys
All Values
```

separately.

---

# 14. Enumeration

`Hashtable` is one of the legacy collection classes that supports `Enumeration`.

### Keys

```java
Enumeration<Integer> keys = students.keys();

while (keys.hasMoreElements()) {
    System.out.println(keys.nextElement());
}
```

### Values

```java
Enumeration<String> values = students.elements();

while (values.hasMoreElements()) {
    System.out.println(values.nextElement());
}
```

### Task

Perform the same iteration using:

1. `Enumeration`
2. `entrySet()`

### Think

Which style would you normally prefer in modern Java code?

---

# 15. Null Key Experiment

Try:

```java
Hashtable<Integer, String> map = new Hashtable<>();

map.put(null, "Mahesh");
```

### Observe

This throws:

```text
NullPointerException
```

### Question

Why?

> `Hashtable` does not permit `null` keys.

---

# 16. Null Value Experiment

Try:

```java
map.put(101, null);
```

### Observe

This also throws:

```text
NullPointerException
```

### Remember

| Operation | Hashtable |
|---|---|
| `null` key | ❌ |
| `null` value | ❌ |

Compare this behavior with `HashMap`.

---

# 17. Duplicate Keys

Create:

```java
Hashtable<Integer, String> map = new Hashtable<>();

map.put(101, "Java");
map.put(101, "Spring Boot");
```

Print the result.

### Question

How many mappings exist?

Expected:

```text
1
```

The second `put()` replaces the value associated with the existing key.

---

# 18. Duplicate Values

Try:

```java
map.put(101, "Java");
map.put(102, "Java");
```

### Question

Is this allowed?

Yes.

Multiple keys can map to the same value.

Example:

```text
101 → Java
102 → Java
```

---

# 19. Hashing Experiment

Create a `Hashtable` and insert several keys.

```java
Hashtable<Integer, String> map = new Hashtable<>();

for (int i = 1; i <= 20; i++) {
    map.put(i, "Value-" + i);
}

System.out.println(map);
```

### Investigate

Think about:

- How is a key converted into a hash?
- How is the bucket selected?
- What happens when two keys collide?
- When does resizing happen?
- Why does a good `hashCode()` matter?

### Goal

Understand that a `Hashtable` is fundamentally a **hash-table-based Map**.

---

# 20. Custom Object Keys

Create a custom `Student` class.

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

        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
```

Use it as a key:

```java
Hashtable<Student, String> students = new Hashtable<>();

Student s1 = new Student(101, "Mahesh");

students.put(s1, "Computer Science");
```

Now create another object:

```java
Student s2 = new Student(101, "Mahesh");
```

Test:

```java
System.out.println(students.get(s2));
```

### Goal

Understand why `equals()` and `hashCode()` must follow a consistent contract.

---

# 21. Hashtable vs HashMap

Create equivalent examples using:

```java
Hashtable<Integer, String>
HashMap<Integer, String>
```

Test:

### 1. Null key

```java
map.put(null, "Test");
```

### 2. Null value

```java
map.put(101, null);
```

### 3. Ordering

Insert several entries and observe iteration order.

### 4. Synchronization

Research how their thread-safety models differ.

### Comparison

| Feature | Hashtable | HashMap |
|---|---|---|
| Legacy | Yes | No |
| Synchronized methods | Yes | No |
| Null key | ❌ | One |
| Null values | ❌ | Multiple |
| Ordering | Not guaranteed | Not guaranteed |
| Typical modern use | Rare | Common |

---

# 22. Hashtable vs ConcurrentHashMap

Create examples using:

```java
Hashtable<Integer, String>
ConcurrentHashMap<Integer, String>
```

### Investigate

Compare:

- Thread safety
- Locking/concurrency model
- Null handling
- Performance under concurrency
- Modern usage
- Compound operations

### Important

Do not assume that:

```java
map.get(key);
map.put(key, value);
```

is automatically one atomic operation merely because individual methods are synchronized.

For compound logic, use appropriate atomic methods such as:

```java
putIfAbsent()
compute()
computeIfAbsent()
merge()
```

where applicable.

---

# 23. Frequency Counter

Use a `Hashtable` to count character frequencies.

### Input

```text
banana
```

### Expected Result

Conceptually:

```text
b → 1
a → 3
n → 2
```

### Starter Code

```java
import java.util.Hashtable;

public class FrequencyCounter {

    public static void main(String[] args) {

        String input = "banana";

        Hashtable<Character, Integer> frequency =
                new Hashtable<>();

        for (char ch : input.toCharArray()) {

            frequency.put(
                ch,
                frequency.getOrDefault(ch, 0) + 1
            );
        }

        System.out.println(frequency);
    }
}
```

### Practice

Modify the program to count:

- words
- digits
- vowels
- duplicate characters

---

# 24. Mini Project

## Student Directory

Build a console-based student directory using:

```java
Hashtable<Integer, Student>
```

### Student

```java
class Student {

    private int id;
    private String name;
    private String course;

    public Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + course;
    }
}
```

### Required Operations

Implement:

```text
1. Add Student
2. Find Student
3. Update Student
4. Remove Student
5. Display All Students
6. Search by Course
7. Count Students
8. Clear Directory
```

### Suggested Structure

```text
StudentDirectory
│
├── addStudent()
├── findStudent()
├── updateStudent()
├── removeStudent()
├── displayStudents()
├── searchByCourse()
└── clearDirectory()
```

### Goal

Use the `Hashtable` API instead of manually implementing a hash table.

---

# 25. Challenge Problems

## Challenge 1 — First Non-Repeating Character

Given:

```text
swiss
```

Use a `Hashtable` to determine the first non-repeating character.

Expected:

```text
w
```

---

## Challenge 2 — Duplicate Detection

Given:

```text
[10, 20, 30, 20, 40, 10]
```

Use a `Hashtable` to identify duplicate values.

Expected duplicates:

```text
20
10
```

---

## Challenge 3 — Word Frequency

Input:

```text
java spring java backend spring java
```

Generate frequencies:

```text
java → 3
spring → 2
backend → 1
```

---

## Challenge 4 — Character Frequency

Given:

```text
programming
```

Count the frequency of every character.

---

## Challenge 5 — Student Lookup

Create:

```java
Hashtable<Integer, Student>
```

Implement:

```java
findStudentById()
```

The method should return the student if found.

---

## Challenge 6 — Compare Map Implementations

Write a program that demonstrates the difference between:

```text
Hashtable
HashMap
ConcurrentHashMap
```

Test:

- null keys
- null values
- basic insertion
- retrieval
- removal
- iteration

Document your observations.

---

## Challenge 7 — Choose the Right Map

For each scenario, decide whether you would use:

```text
HashMap
Hashtable
ConcurrentHashMap
TreeMap
LinkedHashMap
```

### Scenarios

1. Normal key-value storage
2. Need sorted keys
3. Need insertion order
4. Multiple threads modifying the map
5. Maintaining legacy code using `Hashtable`

Write the reason for each choice.

---

# 26. Practice Checklist

### Basic API

- [ ] Create a `Hashtable`
- [ ] Add entries using `put()`
- [ ] Retrieve using `get()`
- [ ] Update using `put()`
- [ ] Remove using `remove()`
- [ ] Check keys using `containsKey()`
- [ ] Check values using `containsValue()`
- [ ] Use `size()`
- [ ] Use `isEmpty()`
- [ ] Use `clear()`

### Modern Map Operations

- [ ] Practice `putIfAbsent()`
- [ ] Practice `replace()`
- [ ] Practice `replace(key, oldValue, newValue)`
- [ ] Practice `getOrDefault()`
- [ ] Practice `computeIfAbsent()`
- [ ] Practice `merge()`

### Iteration

- [ ] Iterate with `entrySet()`
- [ ] Iterate with `keySet()`
- [ ] Iterate with `values()`
- [ ] Practice legacy `Enumeration`

### Concepts

- [ ] Understand hashing
- [ ] Understand buckets
- [ ] Understand collisions
- [ ] Understand resizing
- [ ] Understand `equals()` / `hashCode()`
- [ ] Understand why null keys are not allowed
- [ ] Understand why null values are not allowed
- [ ] Understand synchronization
- [ ] Understand compound-operation limitations
- [ ] Understand why `Hashtable` is considered legacy

### Comparisons

- [ ] Hashtable vs HashMap
- [ ] Hashtable vs ConcurrentHashMap
- [ ] Understand when each Map implementation is appropriate

### Problem Solving

- [ ] Frequency counter
- [ ] Duplicate detection
- [ ] First non-repeating character
- [ ] Student directory
- [ ] Map implementation comparison

---

# 27. Progress

```text
Hashtable Practice
│
├── Basic Operations
│   ├── put()              [x]
│   ├── get()              [x]
│   ├── remove()           [x]
│   ├── containsKey()      [x]
│   ├── containsValue()    [x]
│   ├── size()             [x]
│   ├── isEmpty()          [x]
│   └── clear()            [x]
│
├── Map Operations
│   ├── putIfAbsent()      [x]
│   ├── replace()          [x]
│   └── getOrDefault()     [x]
│
├── Iteration
│   ├── entrySet()         [x]
│   ├── keySet()           [x]
│   ├── values()           [x]
│   └── Enumeration        [x]
│
├── Concepts
│   ├── Hashing            [x]
│   ├── Collisions         [x]
│   ├── Null restrictions   [x]
│   ├── equals/hashCode     [x]
│   └── Synchronization     [x]
│
└── Problem Solving
    ├── Frequency Counter [x]
    ├── Duplicate Detection [x]
    ├── Custom Object Keys [x]
    └── Mini Project       [x]
```

---

## 🎯 Final Goal

After completing this practice file, you should be able to:

> **Use `Hashtable`, understand its hashing and synchronization behavior, work with its API, recognize its legacy limitations, and confidently compare it with modern `Map` implementations.**

### Remember

```text
Hashtable
   ↓
Legacy Map implementation
   ↓
Synchronized methods
   ↓
No null keys / values
   ↓
Hash-based lookup
   ↓
No guaranteed ordering
   ↓
Usually prefer HashMap or ConcurrentHashMap in new code
```
