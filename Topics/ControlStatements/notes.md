# 🚀 Control Statements in Java

Control Statements are used to control the flow of execution of a Java program.

Normally:

```text
Statement 1

↓

Statement 2

↓

Statement 3
```

But using control statements, we can:

* Execute statements conditionally.
* Repeat statements multiple times.
* Jump from one part of program to another.

---

# Types of Control Statements

Java provides three categories:

```text
                Control Statements

        --------------------------------

        Selection Statements

        Iteration Statements

        Jump Statements
```

---

## 1. Selection Statements

Selection statements decide which block of code should execute.

Java provides:

```text
if

if-else

if-else-if ladder

Nested if

switch
```

---

# 2. if Statement

Syntax:

```java
if(condition){

    // statements

}
```

Example:

```java
int age = 20;

if(age >= 18){

    System.out.println("Eligible to Vote");

}
```

Output:

```text
Eligible to Vote
```

---

# Flow Diagram

```text
          Condition

         /       \

      true      false

       |           |

 Execute      Skip Block
```

---

# 3. if-else Statement

Syntax:

```java
if(condition){

    // code

}

else{

    // code

}
```

Example:

```java
int age = 16;

if(age >= 18){

    System.out.println("Adult");

}

else{

    System.out.println("Minor");

}
```

Output:

```text
Minor
```

---

# 4. if-else-if Ladder

Used for multiple conditions.

Example:

```java
int marks = 82;

if(marks >= 90){

    System.out.println("Grade A");

}

else if(marks >= 75){

    System.out.println("Grade B");

}

else if(marks >= 60){

    System.out.println("Grade C");

}

else{

    System.out.println("Fail");

}
```

Output:

```text
Grade B
```

---

# Flow

```text
Condition1

   |

True -> Execute

False

   |

Condition2

   |

True -> Execute

False

   |

Condition3

   |

Else Block
```

---

# 5. Nested if

if inside another if.

Example:

```java
int age = 22;

boolean citizen = true;

if(age >= 18){

    if(citizen){

        System.out.println("Eligible");

    }

}
```

Output:

```text
Eligible
```

---

# 6. switch Statement

Alternative to multiple if-else.

Syntax:

```java
switch(expression){

case value:

    statements;

    break;

default:

}
```

---

Example

```java
int day = 3;

switch(day){

case 1:

    System.out.println("Monday");

    break;

case 2:

    System.out.println("Tuesday");

    break;

case 3:

    System.out.println("Wednesday");

    break;

default:

    System.out.println("Invalid");

}
```

Output:

```text
Wednesday
```

---

# Fall Through

If break is missing:

```java
int x = 2;

switch(x){

case 1:

    System.out.println("One");

case 2:

    System.out.println("Two");

case 3:

    System.out.println("Three");

}
```

Output:

```text
Two

Three
```

This is called:

```text
Fall Through
```

---

# Datatypes Supported by switch

```text
byte

short

char

int

String

enum
```

Not Allowed:

```text
float

double

long

boolean
```

---

# switch with String

```java
String day = "MON";

switch(day){

case "MON":

    System.out.println("Monday");

    break;

}
```

Output:

```text
Monday
```

---

# Enhanced Switch (Java 14+)

Traditional:

```java
switch(day){

case 1:

    System.out.println("Mon");

    break;

}
```

Modern:

```java
switch(day){

case 1 -> System.out.println("Mon");

case 2 -> System.out.println("Tue");

default -> System.out.println("Invalid");

}
```

---

# Switch Expression

```java
String result =

switch(day){

case 1 -> "Monday";

case 2 -> "Tuesday";

default -> "Invalid";

};

System.out.println(result);
```

---

# 7. Loops in Java

Loops repeat statements.

Java provides:

```text
while

do while

for

enhanced for
```

---

# while Loop

Syntax:

```java
while(condition){

    statements;

}
```

Example:

```java
int i=1;

while(i<=5){

    System.out.println(i);

    i++;

}
```

Output:

```text
1

2

3

4

5
```

---

# Flow

```text
Condition

   |

true

   |

Execute

   |

Back to Condition


false

↓

Exit
```

---

# Infinite while Loop

```java
while(true){

}
```

Runs forever.

---

# do-while Loop

Executes at least once.

Syntax:

```java
do{

    statements;

}

while(condition);
```

---

Example

```java
int i=1;

do{

    System.out.println(i);

    i++;

}

while(i<=5);
```

Output:

```text
1

2

3

4

5
```

---

# Difference

```java
while

Checks first.

Executes later.
```

```java
do while

Executes first.

Checks later.
```

---

Example

```java
int x=100;

while(x<10){

    System.out.println(x);

}
```

Output:

```text
Nothing
```

---

```java
int x=100;

do{

    System.out.println(x);

}

while(x<10);
```

Output:

```text
100
```

---

# for Loop

Syntax:

```java
for(initialization;

condition;

increment){

}
```

---

Example

```java
for(int i=1;i<=5;i++){

    System.out.println(i);

}
```

Output:

```text
1

2

3

4

5
```

---

# Infinite for Loop

```java
for(;;){

}
```

Equivalent:

```java
while(true){

}
```

---

# Enhanced for Loop

Also called:

```text
For Each Loop
```

Example:

```java
int arr[] = {10,20,30,40};

for(int x : arr){

    System.out.println(x);

}
```

Output:

```text
10

20

30

40
```

---

# Memory Representation

```text
arr

↓

+----+----+----+----+

|10  |20  |30  |40  |

+----+----+----+----+


x receives:

10

20

30

40
```

---

# 8. break Statement

Used to terminate loop immediately.

Example:

```java
for(int i=1;i<=10;i++){

    if(i==5)

        break;

    System.out.println(i);

}
```

Output:

```text
1

2

3

4
```

---

# break in switch

```java
switch(day){

case 1:

    System.out.println("Mon");

    break;

}
```

Stops further execution.

---

# 9. continue Statement

Skips current iteration.

Example:

```java
for(int i=1;i<=5;i++){

    if(i==3)

        continue;

    System.out.println(i);

}
```

Output:

```text
1

2

4

5
```

---

# break vs continue

| break                    | continue        |
| ------------------------ | --------------- |
| Terminates loop          | Skips iteration |
| Exits loop               | Continues loop  |
| Used in loops and switch | Only loops      |

---

# 10. Labelled break

Java supports labels.

Example:

```java
outer:

for(int i=1;i<=3;i++){

    for(int j=1;j<=3;j++){

        if(j==2)

            break outer;

        System.out.println(i+" "+j);

    }

}
```

Output:

```text
1 1
```

Entire outer loop terminates.

---

# Labelled continue

```java
outer:

for(int i=1;i<=3;i++){

    for(int j=1;j<=3;j++){

        if(j==2)

            continue outer;

        System.out.println(i+" "+j);

    }

}
```

Output:

```text
1 1

2 1

3 1
```

---

# 11. return Statement

Used to exit method.

Example:

```java
public static int add(){

    return 10;

}
```

---

Another Example

```java
public static void test(){

    System.out.println("A");

    return;

}
```

Immediately exits method.

---

# Control Statement Hierarchy

```text
                  Control Statements


       ----------------------------------------


Selection      Iteration        Jump


if             while            break

if-else        do while         continue

switch         for              return

               enhanced for
```

---

# ==================================================

# 🎯 SDE INTERVIEW QUESTIONS WITH ANSWERS

### Q1. Difference between while and do-while?

| while               | do while               |
| ------------------- | ---------------------- |
| Checks first        | Executes first         |
| May execute 0 times | Executes at least once |

---

### Q2. What is Fall Through in switch?

**Answer**

When:

```java
break;
```

is missing,

Execution continues into next case.

This is:

```text
Fall Through
```

---

### Q3. Which datatypes are supported in switch?

**Answer**

Supported:

```text
byte

short

char

int

String

enum
```

Not Supported:

```text
float

double

long

boolean
```

---

### Q4. Difference between break and continue?

| break      | continue       |
| ---------- | -------------- |
| Exit loop  | Skip iteration |
| Stops loop | Continues loop |

---

### Q5. Output?

```java
for(int i=1;i<=5;i++){

if(i==3)

continue;

System.out.println(i);

}
```

Output:

```text
1

2

4

5
```

---

### Q6. Output?

```java
for(int i=1;i<=5;i++){

if(i==3)

break;

System.out.println(i);

}
```

Output:

```text
1

2
```

---

### Q7. What is Enhanced for loop?

**Answer**

Used to iterate arrays and collections.

Example:

```java
for(int x : arr){

}
```

---

### Q8. Can switch use String?

**Answer**

Yes.

Supported from:

```text
Java 7
```

---

### Q9. Can while loop be infinite?

**Answer**

Yes.

```java
while(true){

}
```

---

### Q10. Difference between if-else and switch?

| if-else             | switch         |
| ------------------- | -------------- |
| Multiple conditions | Equality based |
| Slower              | Faster         |
| Complex expressions | Fixed values   |
| Flexible            | Cleaner syntax |

---

# 🔥 SDE Takeaway

Remember:

✅ if → Single condition

✅ if-else-if → Multiple conditions

✅ switch → Equality comparisons

✅ while → Check first

✅ do-while → Execute first

✅ for → Known iterations

✅ break → Exit loop

✅ continue → Skip iteration

✅ Enhanced for → Arrays & Collections

These concepts are frequently asked in **SDE interviews** because they test:

* Flow Control
* Loop Internals
* Switch Optimization
* Labelled break
* Enhanced Switch
* Edge Cases in Loops
