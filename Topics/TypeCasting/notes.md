# 🚀 Type Casting in Java

Type Casting is the process of converting one datatype into another datatype.

Example:

```java
int x = 10;

double y = x;

System.out.println(y);
```

Output:

```text
10.0
```

Here:

```text
int → double
```

This conversion is called:

```text
Type Casting
```

---

# 1. Types of Type Casting

Java supports two types:

```text
                Type Casting

               /             \

      Implicit Casting    Explicit Casting

      (Widening)           (Narrowing)
```

---

# 2. Implicit Type Casting (Widening)

Automatic conversion from smaller datatype to larger datatype.

Example:

```java
int x = 100;

double y = x;

System.out.println(y);
```

Output:

```text
100.0
```

No explicit cast required.

---

## Widening Hierarchy

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

Also:

```text
char

↓

int

↓

long

↓

float

↓

double
```

---

# Example 1

```java
byte a = 10;

int b = a;

System.out.println(b);
```

Output:

```text
10
```

---

# Example 2

```java
int x = 500;

long y = x;

System.out.println(y);
```

Output:

```text
500
```

---

# Example 3

```java
float x = 10.5f;

double y = x;

System.out.println(y);
```

Output:

```text
10.5
```

---

# Why Widening is Safe?

Because:

```text
Smaller Datatype

↓

Larger Datatype
```

No information is lost.

Example:

```java
int x = 100;

double y = x;
```

100 remains 100.

---

# 3. Explicit Type Casting (Narrowing)

Converting larger datatype into smaller datatype.

Example:

```java
double x = 99.99;

int y = (int)x;

System.out.println(y);
```

Output:

```text
99
```

Fractional part is lost.

---

# Why Explicit Cast is Required?

Because:

```text
double

↓

int
```

may cause:

* Data Loss
* Precision Loss
* Overflow

Hence Java forces:

```java
(int)
```

---

# Example

```java
long salary = 100000;

int s = (int)salary;
```

Valid.

---

# Example

```java
double pi = 3.14;

int x = (int)pi;

System.out.println(x);
```

Output:

```text
3
```

---

# Data Loss Example

```java
int x = 130;

byte y = (byte)x;

System.out.println(y);
```

Output:

```text
-126
```

Why?

Because:

```text
byte range

-128 to 127
```

130 exceeds byte range.

Overflow occurs.

---

# Memory Representation

```text
130

Binary:

10000010


byte stores:

10000010


Result:

-126
```

---

# char to int

```java
char ch = 'A';

int x = ch;

System.out.println(x);
```

Output:

```text
65
```

Because:

```text
Unicode

'A' = 65
```

---

# int to char

```java
int x = 65;

char ch = (char)x;

System.out.println(ch);
```

Output:

```text
A
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

# ASCII / Unicode Table

| Character | Unicode |
| --------- | ------- |
| A         | 65      |
| B         | 66      |
| C         | 67      |
| a         | 97      |
| b         | 98      |
| 0         | 48      |
| 1         | 49      |

---

# boolean Cannot Be Cast

Invalid:

```java
boolean a = true;

int x = (int)a;
```

Output:

```text
Compile Time Error
```

Because:

```text
boolean

is NOT numeric datatype.
```

---

# byte + byte Problem

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

↓

int + int
```

Result becomes:

```text
int
```

Correct:

```java
int c = a + b;
```

---

# char + char

```java
char a = 'A';

char b = 'B';

System.out.println(a+b);
```

Output:

```text
131
```

Because:

```text
65 + 66

=

131
```

char gets promoted to int.

---

# Numeric Promotion Rules

If expression contains:

```text
byte

short

char
```

They are first converted to:

```text
int
```

Example:

```java
byte a = 10;

short b = 20;

int c = a + b;
```

Output:

```text
30
```

---

# Overflow Example

```java
byte x = 127;

x++;
```

Output:

```text
-128
```

Because:

```text
byte range:

-128 to 127
```

---

# Another Overflow Example

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

This is:

```text
Integer Overflow
```

---

# Type Promotion in Expressions

Example:

```java
int a = 10;

double b = 20.5;

double c = a + b;
```

Output:

```text
30.5
```

Because:

```text
int

↓

double


double + double
```

---

# Another Example

```java
char c = 'A';

int x = 10;

System.out.println(c+x);
```

Output:

```text
75
```

Because:

```text
'A'

↓

65


65 + 10

=

75
```

---

# Primitive Casting Summary

| From    | To     | Automatic   |
| ------- | ------ | ----------- |
| byte    | short  | Yes         |
| short   | int    | Yes         |
| int     | long   | Yes         |
| long    | float  | Yes         |
| float   | double | Yes         |
| double  | int    | No          |
| int     | byte   | No          |
| int     | char   | No          |
| boolean | int    | Not Allowed |

---

# Memory Representation

```text
int x = 100;


Stack

+-------+

| 100   |

+-------+



double y = x;


Stack

+---------+

| 100.0   |

+---------+
```

---

# JVM Internal Conversion

Example:

```java
Integer a = 100;

int b = a;
```

Compiler converts:

```java
int b = a.intValue();
```

This is:

```text
Unboxing
```

---

# ====================================================

# 🎯 SDE INTERVIEW QUESTIONS WITH ANSWERS

### Q1. What is Type Casting?

**Answer**

Converting one datatype into another datatype.

Example:

```java
int x = 10;

double y = x;
```

---

### Q2. What are the types of Type Casting?

**Answer**

1. Widening Casting

```text
int → long
```

Automatic.

2. Narrowing Casting

```text
double → int
```

Explicit.

---

### Q3. Difference between Widening and Narrowing?

| Widening      | Narrowing          |
| ------------- | ------------------ |
| Automatic     | Explicit           |
| No Data Loss  | Possible Data Loss |
| Small → Large | Large → Small      |
| Safe          | Unsafe             |

---

### Q4. Output?

```java
double x = 5.8;

int y = (int)x;

System.out.println(y);
```

**Answer**

```text
5
```

Fractional part is discarded.

---

### Q5. Output?

```java
char ch = 'A';

int x = ch;

System.out.println(x);
```

**Answer**

```text
65
```

Because:

```text
'A' → Unicode 65
```

---

### Q6. Output?

```java
int x = 65;

char ch = (char)x;

System.out.println(ch);
```

**Answer**

```text
A
```

---

### Q7. Why byte + byte returns int?

```java
byte a = 10;

byte b = 20;

byte c = a+b;
```

**Answer**

Because:

```text
byte

↓

Promoted to int


int + int

↓

int
```

Hence:

```text
Compile Time Error
```

---

### Q8. Can boolean be type cast?

**Answer**

No.

Example:

```java
boolean b = true;

int x = (int)b;
```

Output:

```text
Compile Time Error
```

---

### Q9. What is Integer Overflow?

```java
int x = Integer.MAX_VALUE;

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

### Q10. Output?

```java
char a = 'A';

char b = 'B';

System.out.println(a+b);
```

**Answer**

```text
131
```

Because:

```text
65 + 66

=

131
```

---

# 🔥 SDE Takeaway

Remember these important rules:

✅ Widening → Automatic

✅ Narrowing → Explicit

✅ `char` stores Unicode values

✅ `byte + byte` returns int

✅ `boolean` cannot be cast

✅ `double → int` loses decimal part

✅ Overflow is a favorite interview topic

These concepts are frequently asked in SDE interviews because they test:

* JVM Numeric Promotion
* Primitive Datatypes
* Unicode
* Overflow
* Expression Evaluation
* Widening vs Narrowing
