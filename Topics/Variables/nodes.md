# Variables in Java

## Overview

A variable is a named memory location used to store data that can be modified during program execution.

```java
int age = 21;
String name = "Mahesh";
```

* `age` → Variable Name
* `21` → Value
* `int` → Data Type

---

## Types of Variables

Java provides three main types of variables:

### 1. Local Variable

Declared inside a method, constructor, or block.

```java
void display() {
    int x = 10;
}
```

### Characteristics

* Stored in Stack Memory
* Scope limited to the block
* No default value
* Must be initialized before use

---

### 2. Instance Variable

Declared inside a class but outside methods.

```java
class Student {
    int age;
}
```

### Characteristics

* Stored inside objects in Heap Memory
* Every object gets its own copy
* Gets default values from JVM

---

### 3. Static Variable

Declared using the `static` keyword.

```java
class Student {

    static String college = "ABC";

}
```

### Characteristics

* Belongs to the class
* Shared among all objects
* Created when class is loaded
* Stored in Method Area / Metaspace

---

## Default Values

| Data Type | Default Value |
| --------- | ------------- |
| byte      | 0             |
| short     | 0             |
| int       | 0             |
| long      | 0L            |
| float     | 0.0f          |
| double    | 0.0           |
| boolean   | false         |
| char      | '\u0000'      |
| Object    | null          |

> Local variables do NOT receive default values.

---

## Variable Scope

### Local Variable Scope

```java
void test(){

    int x = 10;

}
```

Accessible only inside the method.

---

### Instance Variable Scope

```java
class Student{

    int age;

}
```

Accessible through object:

```java
Student s = new Student();

s.age;
```

---

### Static Variable Scope

```java
class Student{

    static int count;

}
```

Access using:

```java
Student.count;
```

---

## Variable Lifetime

| Variable Type | Lifetime                  |
| ------------- | ------------------------- |
| Local         | Until method finishes     |
| Instance      | Until object is destroyed |
| Static        | Until JVM shuts down      |

---

## Variable Shadowing

When a local variable hides an instance variable.

```java
class Student {

    int age = 21;

    void show(){

        int age = 30;

        System.out.println(age);

        System.out.println(this.age);

    }

}
```

Output:

```text
30
21
```

Explanation:

* `age` → Local variable
* `this.age` → Instance variable

---

## Static vs Instance Variables

| Feature    | Static             | Instance        |
| ---------- | ------------------ | --------------- |
| Belongs To | Class              | Object          |
| Shared     | Yes                | No              |
| Memory     | Method Area        | Heap            |
| Access     | ClassName.variable | object.variable |
| Created    | Class Loading      | Object Creation |

---

## Primitive vs Reference Variables

### Primitive Variable

```java
int x = 10;
```

Stores actual value.

---

### Reference Variable

```java
Student s = new Student();
```

Stores reference to object.

---

## Multiple References

```java
Student s1 = new Student();

Student s2 = s1;

s2.age = 50;
```

Both references point to the same object.

```text
s1 ----+

       |----> Student Object

s2 ----+
```

---

## Java is Pass By Value

Java is always Pass By Value.

### Primitive Example

```java
void change(int x){

    x = 100;

}
```

Original value remains unchanged.

---

### Object Example

```java
void change(Student s){

    s.age = 100;

}
```

Object state changes because copied reference points to the same object.

---

## final Variable

```java
final int x = 10;
```

Cannot be reassigned.

```java
x = 20;
```

Compilation Error.

---

## Blank final Variable

```java
final int id;

id = 101;
```

Allowed exactly once.

---

## final Reference Variable

```java
final Student s = new Student();
```

Allowed:

```java
s.age = 50;
```

Not Allowed:

```java
s = new Student();
```

---

## Important Rule

```text
final primitive

Value cannot change


final reference

Reference cannot change

Object may change
```

---

## Memory Allocation

| Variable          | Memory Location         |
| ----------------- | ----------------------- |
| Local Variable    | Stack                   |
| Instance Variable | Heap                    |
| Static Variable   | Method Area / Metaspace |
| Object            | Heap                    |

---

## Frequently Asked Interview Questions

### Why local variables don't have default values?

Because local variables are stored in Stack Memory and JVM does not initialize them automatically.

---

### Can static methods access instance variables directly?

No.

Because instance variables belong to objects while static methods belong to the class.

---

### Is Java Pass By Reference?

No.

Java is always Pass By Value.

For objects, Java passes a copy of the reference.

---

### Can final object change?

Yes.

Object state may change.

Only the reference cannot change.

---

## Key Takeaways

* Local variables → Stack → No default value
* Instance variables → Heap → Default values available
* Static variables → Shared among objects
* Java is always Pass By Value
* `this` refers to current object
* `final` reference ≠ Immutable object
* Variable shadowing can be resolved using `this`

---

## Status

✅ Variables Topic Completed

Coverage:

* Local Variables
* Instance Variables
* Static Variables
* final Variables
* Scope
* Lifetime
* Shadowing
* Default Values
* Reference Variables
* Pass By Value
* final References
* Memory Allocation
* Interview Questions
