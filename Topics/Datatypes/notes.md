# 🚀 Datatypes in Java

Datatypes specify:

* What type of value a variable can store.
* How much memory it occupies.
* What operations can be performed on it.

Example:

```java
int age = 21;

double salary = 75000.50;

char grade = 'A';

boolean isPlaced = true;
```

---

# 1. Classification of Datatypes

Java Datatypes are divided into:

```text
                Datatypes

               /         \

       Primitive      Non Primitive

```

---

## Primitive Datatypes

Primitive datatypes store actual values.

There are 8 primitive datatypes:

| Datatype | Size          | Range             |
| -------- | ------------- | ----------------- |
| byte     | 1 byte        | -128 to 127       |
| short    | 2 bytes       | -32,768 to 32,767 |
| int      | 4 bytes       | -2³¹ to 2³¹-1     |
| long     | 8 bytes       | -2⁶³ to 2⁶³-1     |
| float    | 4 bytes       | IEEE 754          |
| double   | 8 bytes       | IEEE 754          |
| char     | 2 bytes       | 0 to 65535        |
| boolean  | JVM dependent | true / false      |

---

## Non Primitive Datatypes

Non primitive types store references.

Examples:

```java
String

Array

Class

Interface

Object

Enum
```

Example:

```java
String name = "Mahesh";

int arr[] = {1,2,3};

Scanner sc = new Scanner(System.in);
```

---

# 2. byte

Smallest integer datatype.

```java
byte age = 25;
```

Size:

```text
1 byte = 8 bits
```

Range:

```text
-128 to 127
```

Memory:

```text
00011001

= 25
```

---

# 3. short

Used when range exceeds byte.

```java
short marks = 30000;
```

Size:

```text
2 bytes = 16 bits
```

Range:

```text
-32768 to 32767
```

---

# 4. int

Most commonly used integer datatype.

```java
int salary = 50000;
```

Size:

```text
4 bytes
```

Range:

```text
-2147483648

to

2147483647
```

---

# 5. long

For very large integers.

```java
long population = 8000000000L;
```

Notice:

```java
L
```

is required.

Without:

```java
long x = 8000000000;
```

Compile Time Error.

---

Size:

```text
8 bytes
```

---

# 6. float

Stores decimal numbers.

```java
float pi = 3.14f;
```

Notice:

```java
f
```

is mandatory.

Because:

```java
3.14
```

is considered:

```java
double
```

by default.

---

Size:

```text
4 bytes
```

---

# 7. double

Most commonly used floating datatype.

```java
double price = 9999.99;
```

Size:

```text
8 bytes
```

Higher precision than float.

---

# float vs double

| float             | double         |
| ----------------- | -------------- |
| 4 bytes           | 8 bytes        |
| Less precision    | More precision |
| Suffix f required | No suffix      |
| 32 bits           | 64 bits        |

---

# 8. char

Stores a single character.

```java
char grade = 'A';
```

Notice:

```text
Single Quotes
```

---

Size:

```text
2 bytes
```

---

Because Java uses:

```text
Unicode
```

instead of ASCII.

---

Example:

```java
char ch = 65;

System.out.println(ch);
```

Output:

```text
A
```

Because:

```text
65 -> Unicode -> A
```

---

# Unicode Example

```java
char c = '\u0041';

System.out.println(c);
```

Output:

```text
A
```

---

# char is actually unsigned

Range:

```text
0 to 65535
```

Equivalent:

```text
0 to 2^16 -1
```

---

# 9. boolean

Stores:

```java
true

false
```

Example:

```java
boolean isPlaced = true;

boolean isFailed = false;
```

---

Size:

There is no officially fixed size.

Internally JVM optimizes it.

---

# 10. Default Values

Instance Variables get default values.

| Datatype  | Default Value |
| --------- | ------------- |
| byte      | 0             |
| short     | 0             |
| int       | 0             |
| long      | 0L            |
| float     | 0.0f          |
| double    | 0.0           |
| char      | '\u0000'      |
| boolean   | false         |
| Reference | null          |

---

Example:

```java
class Student {

    int age;

    boolean placed;

    String name;

}
```

Output:

```text
age = 0

placed = false

name = null
```

---

# Local Variables

Local variables DO NOT get default values.

```java
public class Main {

    public static void main(String[] args){

        int x;

        System.out.println(x);

    }

}
```

Output:

```text
Compile Time Error

Variable x might not have been initialized
```

---

# Primitive vs Non Primitive

| Primitive           | Non Primitive          |
| ------------------- | ---------------------- |
| Stores value        | Stores reference       |
| Fixed size          | Dynamic size           |
| Faster              | Slightly slower        |
| Stack               | Heap + Stack Reference |
| Cannot call methods | Can call methods       |

---

Example:

Primitive:

```java
int x = 10;
```

Non Primitive:

```java
String s = "Mahesh";
```

---

# Memory Representation

Primitive:

```text
Stack

x

+------+

|  10  |

+------+
```

---

Reference Type:

```java
String s = "Java";
```

Memory:

```text
Stack

s

|

↓

Heap

+---------+

| "Java" |

+---------+
```

---

# Wrapper Classes

Every primitive has a wrapper class.

| Primitive | Wrapper   |
| --------- | --------- |
| byte      | Byte      |
| short     | Short     |
| int       | Integer   |
| long      | Long      |
| float     | Float     |
| double    | Double    |
| char      | Character |
| boolean   | Boolean   |

---

Example:

```java
Integer x = 100;

Double y = 99.99;

Boolean b = true;
```

---

# Autoboxing

Automatic conversion:

```text
Primitive

↓

Wrapper Object
```

Example:

```java
int x = 10;

Integer y = x;
```

Compiler converts:

```java
Integer y = Integer.valueOf(x);
```

---

# Unboxing

Automatic conversion:

```text
Wrapper

↓

Primitive
```

Example:

```java
Integer x = 100;

int y = x;
```

Compiler converts:

```java
int y = x.intValue();
```

---

# NullPointerException Trap

```java
Integer x = null;

int y = x;
```

Compiler:

```java
int y = x.intValue();
```

Equivalent:

```java
null.intValue();
```

Output:

```text
NullPointerException
```

---

# Numeric Promotion

Example:

```java
byte a = 10;

byte b = 20;

byte c = a + b;
```

Output:

```text
Compile Time Error
```

Because:

```text
byte + byte

becomes

int + int
```

Correct:

```java
int c = a + b;
```

---

# Integer Overflow

```java
int x = Integer.MAX_VALUE;

System.out.println(x);
```

Output:

```text
2147483647
```

Now:

```java
x++;
```

Output:

```text
-2147483648
```

This is called:

```text
Integer Overflow
```

---

# Integer Constants

```java
Integer.MAX_VALUE

Integer.MIN_VALUE

Long.MAX_VALUE

Long.MIN_VALUE

Double.MAX_VALUE

Float.MAX_VALUE
```

Example:

```java
System.out.println(Integer.MAX_VALUE);
```

Output:

```text
2147483647
```

---

# Type Promotion Hierarchy

```text
byte

↓

short

↓

int

↓

long

↓

float

↓

double
```

This is called:

```text
Widening Conversion
```

---

# ==================================================

# 🎯 SDE INTERVIEW QUESTIONS WITH ANSWERS

### Q1. How many datatypes are there in Java?

**Answer**

```text
8 Primitive

byte

short

int

long

float

double

char

boolean
```

---

### Q2. Why is char 2 bytes in Java?

**Answer**

Because Java uses:

```text
Unicode
```

which requires:

```text
16 bits = 2 bytes
```

---

### Q3. Why float requires suffix f?

Example:

```java
float x = 3.14;
```

Output:

```text
Compile Time Error
```

Because:

```text
3.14

is double by default.
```

Correct:

```java
float x = 3.14f;
```

---

### Q4. Difference between int and Integer?

| int          | Integer         |
| ------------ | --------------- |
| Primitive    | Wrapper Class   |
| Stores value | Stores object   |
| Faster       | Slightly slower |
| No methods   | Has methods     |
| Stack        | Heap            |

---

### Q5. Difference between Primitive and Reference Types?

**Answer**

Primitive:

```text
Stores actual values.
```

Reference:

```text
Stores object addresses.
```

---

### Q6. What is Autoboxing?

```java
int x = 10;

Integer y = x;
```

Compiler:

```java
Integer y = Integer.valueOf(x);
```

---

### Q7. What is Unboxing?

```java
Integer x = 100;

int y = x;
```

Compiler:

```java
x.intValue();
```

---

### Q8. What is Integer Overflow?

```java
int x = Integer.MAX_VALUE;

x++;
```

Output:

```text
-2147483648
```

---

### Q9. What is the output?

```java
char ch = 65;

System.out.println(ch);
```

Output:

```text
A
```

Because:

```text
65 -> Unicode -> A
```

---

### Q10. What happens here?

```java
Integer x = null;

int y = x;
```

Output:

```text
NullPointerException
```

Because:

```java
x.intValue()
```

is called internally.

---

# 🔥 SDE Takeaway

Remember these important points:

✅ Java has **8 Primitive Datatypes**

✅ `char` occupies **2 bytes** because of Unicode.

✅ `float` requires suffix `f`.

✅ Primitive types store values.

✅ Reference types store addresses.

✅ Wrapper classes support Autoboxing and Unboxing.

✅ Unboxing a null wrapper causes:

```text
NullPointerException
```

✅ Integer Overflow is a common interview question.

These concepts are frequently asked in **SDE interviews** because they test:

* JVM Memory
* Primitive vs Wrapper Classes
* Unicode
* Autoboxing / Unboxing
* Integer Overflow
* Stack vs Heap
