# 🚀 Operators in Java

Operators are special symbols that perform operations on variables and values.

Example:

```java
int a = 10;

int b = 20;

int c = a + b;
```

Here:

```text
+

is an Operator.
```

---

# 1. Types of Operators in Java

Java provides:

```text
                     Operators

      ------------------------------------------------

      Arithmetic Operators

      Unary Operators

      Relational Operators

      Logical Operators

      Bitwise Operators

      Shift Operators

      Assignment Operators

      Ternary Operator

      instanceof Operator
```

---

# 2. Arithmetic Operators

Used for mathematical calculations.

| Operator | Meaning        |
| -------- | -------------- |
| +        | Addition       |
| -        | Subtraction    |
| *        | Multiplication |
| /        | Division       |
| %        | Modulus        |

---

Example

```java
int a = 20;

int b = 10;

System.out.println(a+b);

System.out.println(a-b);

System.out.println(a*b);

System.out.println(a/b);

System.out.println(a%b);
```

Output:

```text
30

10

200

2

0
```

---

# Integer Division

```java
System.out.println(10/3);
```

Output:

```text
3
```

Because:

```text
10 and 3 are int

Result is int

Decimal part is discarded.
```

---

Example

```java
System.out.println(10.0/3);
```

Output:

```text
3.3333333333333335
```

---

# Modulus Operator %

Returns remainder.

```java
System.out.println(10%3);
```

Output:

```text
1
```

---

# 3. Unary Operators

Unary operators work on a single operand.

```text
+

-

++

--
```

---

## Increment Operator

```java
int x = 10;

x++;

System.out.println(x);
```

Output:

```text
11
```

---

## Pre Increment

```java
int x = 10;

System.out.println(++x);
```

Output:

```text
11
```

---

## Post Increment

```java
int x = 10;

System.out.println(x++);
```

Output:

```text
10
```

After statement:

```text
x = 11
```

---

# Pre vs Post

```java
int x = 10;

int y = ++x;
```

Output:

```text
x = 11

y = 11
```

---

```java
int x = 10;

int y = x++;
```

Output:

```text
x = 11

y = 10
```

---

# Decrement Operator

```java
int x = 10;

x--;

System.out.println(x);
```

Output:

```text
9
```

---

# 4. Relational Operators

Used for comparison.

| Operator | Meaning            |
| -------- | ------------------ |
| ==       | Equal              |
| !=       | Not Equal          |
| >        | Greater Than       |
| <        | Less Than          |
| >=       | Greater Than Equal |
| <=       | Less Than Equal    |

---

Example

```java
int a = 10;

int b = 20;

System.out.println(a>b);

System.out.println(a<b);

System.out.println(a==b);
```

Output:

```text
false

true

false
```

---

# 5. Logical Operators

Used with boolean values.

| Operator | Meaning |   |    |
| -------- | ------- | - | -- |
| &&       | AND     |   |    |
|          |         |   | OR |
| !        | NOT     |   |    |

---

Example

```java
boolean a = true;

boolean b = false;

System.out.println(a && b);

System.out.println(a || b);

System.out.println(!a);
```

Output:

```text
false

true

false
```

---

# Short Circuit AND

```java
int x = 10;

if(x>20 && ++x>10){

}
```

First condition:

```text
x>20

false
```

Second condition:

```text
++x>10
```

is NOT evaluated.

Hence:

```text
x remains 10
```

---

# Short Circuit OR

```java
int x = 10;

if(x<20 || ++x>10){

}
```

First condition:

```text
true
```

Second condition is skipped.

---

# 6. Bitwise Operators

Operate on bits.

| Operator | Meaning    |    |
| -------- | ---------- | -- |
| &        | AND        |    |
|          |            | OR |
| ^        | XOR        |    |
| ~        | Complement |    |

---

Example

```java
int a = 5;

int b = 3;

System.out.println(a & b);
```

Binary:

```text
5 = 101

3 = 011

AND

001
```

Output:

```text
1
```

---

# Bitwise OR

```java
5 | 3
```

```text
101

011

111
```

Output:

```text
7
```

---

# XOR

```java
5 ^ 3
```

```text
101

011

110
```

Output:

```text
6
```

---

# Complement ~

```java
System.out.println(~5);
```

Output:

```text
-6
```

Because:

```text
~n = -(n+1)
```

---

# 7. Shift Operators

Three shift operators:

```text
<<

>>

>>>
```

---

## Left Shift <<

```java
System.out.println(5<<1);
```

Binary:

```text
5 = 00000101

Shift left 1

00001010
```

Output:

```text
10
```

Equivalent:

```text
5 * 2^1
```

---

## Right Shift >>

```java
System.out.println(20>>2);
```

Output:

```text
5
```

Equivalent:

```text
20 / 2²
```

---

## Unsigned Right Shift >>>

```java
System.out.println(-10>>>1);
```

Output:

Large positive integer.

Fills leftmost bits with:

```text
0
```

---

# Difference Between >> and >>>

| >>             | >>>            |
| -------------- | -------------- |
| Signed Shift   | Unsigned Shift |
| Preserves Sign | Ignores Sign   |
| Fills sign bit | Fills 0        |

---

# 8. Assignment Operators

```java
=

+=

-=

*=

/=

%=
```

---

Example

```java
int x = 10;

x += 5;
```

Equivalent:

```java
x = x + 5;
```

Output:

```text
15
```

---

# Another Example

```java
int x = 10;

x *= 2;
```

Equivalent:

```java
x = x * 2;
```

Output:

```text
20
```

---

# 9. Ternary Operator

Syntax:

```java
condition ? value1 : value2;
```

Example

```java
int a = 10;

int b = 20;

int max = a>b ? a : b;
```

Output:

```text
20
```

---

# Nested Ternary

```java
int a=10;

int b=20;

int c=30;


int max =

a>b ?

(a>c?a:c)

:

(b>c?b:c);
```

Output:

```text
30
```

---

# 10. instanceof Operator

Checks object type.

```java
String s = "Java";

System.out.println(s instanceof String);
```

Output:

```text
true
```

---

Example

```java
Integer x = 10;

System.out.println(x instanceof Integer);
```

Output:

```text
true
```

---

# Operator Precedence

Highest to Lowest:

```text
()

++

--

!

* / %

+ -

<< >>

< <= > >=

== !=

&

^

|

&&

||

?:

=
```

---

Example

```java
int x = 5 + 2 * 3;
```

Output:

```text
11
```

Because:

```text
2*3

↓

6


5+6

↓

11
```

---

# Overflow Example

```java
int x = Integer.MAX_VALUE;

x++;

System.out.println(x);
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

# Memory Representation

```text
int a = 10;

int b = 20;

int c = a+b;



Stack


a -> 10

b -> 20

c -> 30
```

---

# ====================================================

# 🎯 SDE INTERVIEW QUESTIONS WITH ANSWERS

### Q1. Difference between & and && ?

**Answer**

```text
&

Always evaluates both operands.



&&

Short circuits.

Second operand executes only if required.
```

---

### Q2. Difference between | and || ?

**Answer**

```text
|

Evaluates both sides.



||

Short circuits.
```

---

### Q3. Output?

```java
System.out.println(10/3);
```

**Answer**

```text
3
```

Because:

```text
int / int

returns

int
```

---

### Q4. Output?

```java
int x = 10;

System.out.println(x++);
```

Output:

```text
10
```

After statement:

```text
x=11
```

---

### Q5. Output?

```java
int x=10;

System.out.println(++x);
```

Output:

```text
11
```

---

### Q6. Difference between >> and >>> ?

| >>             | >>>            |
| -------------- | -------------- |
| Signed Shift   | Unsigned Shift |
| Preserves sign | Fills zero     |

---

### Q7. Output?

```java
System.out.println(~5);
```

Output:

```text
-6
```

Formula:

```text
~n

=

-(n+1)
```

---

### Q8. Output?

```java
System.out.println(5<<1);
```

Output:

```text
10
```

Because:

```text
5 × 2¹
```

---

### Q9. What is Short Circuit Evaluation?

**Answer**

In:

```java
false && something
```

Second condition is not executed.

In:

```java
true || something
```

Second condition is skipped.

---

### Q10. Output?

```java
int a=10;

int b=20;

int max =

a>b ? a : b;

System.out.println(max);
```

Output:

```text
20
```

---

# 🔥 SDE Takeaway

Remember these rules:

✅ `==` → Equality

✅ `&&` → Short Circuit AND

✅ `||` → Short Circuit OR

✅ `~n = -(n+1)`

✅ `<<` → Multiply by powers of 2

✅ `>>` → Divide by powers of 2

✅ `>>>` → Unsigned Right Shift

✅ `?:` → Ternary Operator

These concepts are frequently asked in SDE interviews because they test:

* Expression Evaluation
* Bit Manipulation
* Operator Precedence
* Short Circuit Evaluation
* JVM Numeric Operations
