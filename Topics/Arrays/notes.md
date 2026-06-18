# Arrays in Java – Complete Detailed Guide

An **array** in Java is a container object that stores a **fixed number of values of the same data type**.

Think of it as a row of boxes:

```text
Index:   0    1    2    3    4
Array:  [10] [20] [30] [40] [50]
```

Each element is accessed using its **index**, and indexing starts from **0**.

---

# 1. Why Arrays?

Suppose you want to store marks of 100 students.

Without array:

```java
int m1 = 80;
int m2 = 75;
int m3 = 90;
...
int m100 = 88;
```

This becomes difficult.

Using array:

```java
int[] marks = new int[100];
```

Now all marks are stored together.

---

# 2. Declaration of Array

### Syntax

```java
datatype[] arrayName;
```

or

```java
datatype arrayName[];
```

Example:

```java
int[] arr;
String[] names;
double[] salary;
```

---

# 3. Array Creation

```java
int[] arr = new int[5];
```

Memory:

```text
Index : 0  1  2  3  4
Value : 0  0  0  0  0
```

Default values:

| Type    | Default  |
| ------- | -------- |
| int     | 0        |
| double  | 0.0      |
| boolean | false    |
| char    | '\u0000' |
| String  | null     |
| Object  | null     |

Example:

```java
String[] names = new String[3];

System.out.println(names[0]);
```

Output:

```text
null
```

---

# 4. Initialization

## Method 1

```java
int[] arr = new int[5];

arr[0] = 10;
arr[1] = 20;
arr[2] = 30;
arr[3] = 40;
arr[4] = 50;
```

---

## Method 2

Direct initialization

```java
int[] arr = {10,20,30,40,50};
```

---

## Method 3

```java
int[] arr = new int[]{10,20,30,40,50};
```

---

# 5. Accessing Elements

```java
int[] arr = {10,20,30};

System.out.println(arr[0]);
System.out.println(arr[1]);
System.out.println(arr[2]);
```

Output:

```text
10
20
30
```

---

# 6. Updating Elements

```java
int[] arr = {10,20,30};

arr[1] = 100;

System.out.println(arr[1]);
```

Output:

```text
100
```

---

# 7. Array Length

```java
int[] arr = {1,2,3,4,5};

System.out.println(arr.length);
```

Output:

```text
5
```

Notice:

```java
arr.length
```

Not

```java
arr.length()
```

because `length` is a property.

---

# 8. Traversing an Array

## Using for loop

```java
int[] arr = {10,20,30,40};

for(int i=0;i<arr.length;i++)
{
    System.out.println(arr[i]);
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

# 9. Enhanced for loop (For-each)

```java
int[] arr = {10,20,30,40};

for(int x : arr)
{
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

## Can we modify using for-each?

```java
for(int x : arr)
{
    x = x + 10;
}
```

Array remains unchanged.

Because:

```java
x is a copy of array element.
```

---

# 10. Array Memory Representation

```java
int[] arr = {10,20,30};
```

Actually:

```text
arr
 |
 ↓
 ------------------
|10|20|30|
 ------------------
```

`arr` is a reference variable.

The actual array object is created in heap memory.

---

# 11. Array Index Out Of Bounds Exception

```java
int[] arr = {10,20,30};

System.out.println(arr[5]);
```

Output:

```text
Exception in thread "main"
java.lang.ArrayIndexOutOfBoundsException
```

Because:

Valid indexes:

```text
0
1
2
```

Not:

```text
3
4
5
```

---

# 12. Taking Input in Array

Using Scanner:

```java
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[5];

        for(int i=0;i<arr.length;i++)
        {
            arr[i] = sc.nextInt();
        }

        for(int x : arr)
        {
            System.out.print(x+" ");
        }
    }
}
```

Input:

```text
10 20 30 40 50
```

Output:

```text
10 20 30 40 50
```

---

# 13. Finding Sum of Array

```java
int[] arr = {10,20,30,40};

int sum = 0;

for(int x : arr)
{
    sum += x;
}

System.out.println(sum);
```

Output:

```text
100
```

---

# 14. Finding Maximum Element

```java
int[] arr = {5,8,1,20,3};

int max = arr[0];

for(int i=1;i<arr.length;i++)
{
    if(arr[i] > max)
    {
        max = arr[i];
    }
}

System.out.println(max);
```

Output:

```text
20
```

---

# 15. Finding Minimum Element

```java
int[] arr = {5,8,1,20,3};

int min = arr[0];

for(int i=1;i<arr.length;i++)
{
    if(arr[i] < min)
    {
        min = arr[i];
    }
}

System.out.println(min);
```

Output:

```text
1
```

---

# 16. Reverse an Array

```java
int[] arr = {1,2,3,4,5};

int start = 0;
int end = arr.length - 1;

while(start < end)
{
    int temp = arr[start];
    arr[start] = arr[end];
    arr[end] = temp;

    start++;
    end--;
}

for(int x : arr)
{
    System.out.print(x+" ");
}
```

Output:

```text
5 4 3 2 1
```

---

# 17. Copying Arrays

### Manual Copy

```java
int[] a = {1,2,3};

int[] b = new int[a.length];

for(int i=0;i<a.length;i++)
{
    b[i] = a[i];
}
```

---

### Using `clone()`

```java
int[] a = {1,2,3};

int[] b = a.clone();
```

---

### Using `Arrays.copyOf()`

```java
import java.util.Arrays;

int[] b = Arrays.copyOf(a, a.length);
```

---

# 18. Sorting Array

```java
import java.util.Arrays;

int[] arr = {5,2,8,1,9};

Arrays.sort(arr);

System.out.println(Arrays.toString(arr));
```

Output:

```text
[1, 2, 5, 8, 9]
```

---

# 19. Binary Search

Array must be sorted.

```java
import java.util.Arrays;

int[] arr = {10,20,30,40,50};

int index = Arrays.binarySearch(arr,30);

System.out.println(index);
```

Output:

```text
2
```

---

# 20. Arrays of Objects

```java
String[] names =
{
    "Mahesh",
    "Rahul",
    "Amit"
};

for(String s : names)
{
    System.out.println(s);
}
```

Output:

```text
Mahesh
Rahul
Amit
```

---

# 21. Multidimensional Arrays

### 2D Array

```java
int[][] matrix =
{
    {1,2,3},
    {4,5,6},
    {7,8,9}
};
```

Output:

```text
1 2 3
4 5 6
7 8 9
```

---

# 22. Jagged Arrays

Rows can have different sizes.

```java
int[][] arr = new int[3][];

arr[0] = new int[2];
arr[1] = new int[4];
arr[2] = new int[3];
```

Memory:

```text
[ _ _ ]

[ _ _ _ _ ]

[ _ _ _ ]
```

Different row lengths are allowed.

---

# 23. Important Interview Questions

### Q1 Can array size change after creation?

❌ No

```java
int[] arr = new int[5];
```

Size is fixed.

---

### Q2 Can array store different data types?

❌ No

```java
int[] arr = {1,2,"Hello"};
```

Compile-time error.

---

### Q3 Are arrays objects in Java?

✅ Yes.

```java
int[] arr = new int[5];

System.out.println(arr.getClass());
```

Arrays are objects stored in heap memory.

---

### Q4 Can array have negative index?

❌ No.

```java
arr[-1];
```

Throws:

```text
ArrayIndexOutOfBoundsException
```

---

### Q5 Difference between Array and String

| Array               | String                   |
| ------------------- | ------------------------ |
| Mutable             | Immutable                |
| Stores same type    | Stores characters        |
| Fixed size          | Fixed size               |
| Can modify elements | Cannot modify characters |

---

# Frequently Asked Interview Programs

1. Reverse an array
2. Find second largest element
3. Find duplicates
4. Rotate array left/right
5. Move zeros to end
6. Merge two sorted arrays
7. Find missing number
8. Kadane's Algorithm
9. Binary Search
10. Two Sum problem

These array problems are among the most frequently asked in SDE interviews and are excellent practice if you're preparing for top product-based companies.


OTHER THAN THIS IMPORTANT TOPICS/QUESTIONS MANY OF MISSED: HERE THEY ARE

## 1. Arrays are Objects in Java ⭐⭐⭐

Many beginners miss this.

```java
int[] arr = new int[5];

System.out.println(arr instanceof Object); // true
System.out.println(arr.getClass());
```

Output:

```text
true
class [I
```

This means:

* Arrays are created in Heap memory.
* Array variables store references.
* Arrays inherit methods from `Object`.

---

## 2. Array Declaration Confusion ⭐⭐

All these are valid:

```java
int[] a;
int a[];
```

But:

```java
int []a;
```

Also valid.

**Industry Standard:**

```java
int[] a;
```

---

## 3. Anonymous Arrays ⭐⭐⭐

You can create arrays without assigning them to a variable.

```java
sum(new int[]{10,20,30});
```

Example:

```java
static void sum(int[] arr)
{
    int s = 0;

    for(int x : arr)
        s += x;

    System.out.println(s);
}

public static void main(String[] args)
{
    sum(new int[]{1,2,3,4});
}
```

Output:

```text
10
```

---

## 4. Command Line Arguments are Arrays ⭐⭐⭐

This is extremely important.

```java
public static void main(String[] args)
```

Here:

```java
String[] args
```

is an array of Strings.

Example:

```java
public class Main
{
    public static void main(String[] args)
    {
        System.out.println(args[0]);
    }
}
```

Run:

```bash
java Main Mahesh
```

Output:

```text
Mahesh
```

---

## 5. Array Reference Copy vs Deep Copy ⭐⭐⭐⭐

This is one of the most asked interview questions.

```java
int[] a = {1,2,3};

int[] b = a;

b[0] = 100;

System.out.println(a[0]);
```

Output:

```text
100
```

Because:

```text
a ----\
       --> [100,2,3]
b ----/
```

Both point to the same array.

---

### Deep Copy

```java
int[] b = a.clone();
```

Now:

```text
a --> [1,2,3]

b --> [1,2,3]
```

Separate arrays.

---

## 6. Passing Arrays to Methods ⭐⭐⭐

Arrays are passed by value of reference.

```java
static void change(int[] arr)
{
    arr[0] = 999;
}

public static void main(String[] args)
{
    int[] a = {1,2,3};

    change(a);

    System.out.println(a[0]);
}
```

Output:

```text
999
```

The original array changes.

---

## 7. Returning Arrays from Methods ⭐⭐⭐

```java
static int[] create()
{
    return new int[]{10,20,30};
}
```

Usage:

```java
int[] arr = create();
```

Very common in utility methods.

---

## 8. Variable Length Arguments (Varargs) ⭐⭐⭐⭐

Internally, varargs use arrays.

```java
static int sum(int... nums)
{
    int s = 0;

    for(int x : nums)
        s += x;

    return s;
}
```

Call:

```java
sum(1,2,3);

sum(1,2,3,4,5);
```

Internally:

```java
sum(new int[]{1,2,3});
```

---

## 9. `Arrays` Utility Class ⭐⭐⭐⭐⭐

Very important for interviews.

```java
import java.util.Arrays;
```

### Sort

```java
Arrays.sort(arr);
```

---

### Binary Search

```java
Arrays.binarySearch(arr,20);
```

---

### Fill

```java
Arrays.fill(arr,100);
```

Output:

```text
[100,100,100,100]
```

---

### Equals

```java
Arrays.equals(a,b);
```

Checks contents.

---

### Convert to String

```java
System.out.println(Arrays.toString(arr));
```

Output:

```text
[1, 2, 3]
```

---

## 10. `System.arraycopy()` ⭐⭐⭐⭐

Fast array copying.

```java
int[] a = {1,2,3,4};

int[] b = new int[4];

System.arraycopy(a,0,b,0,4);
```

Now:

```text
b = [1,2,3,4]
```

---

## 11. Multidimensional Arrays are Arrays of Arrays ⭐⭐⭐⭐

```java
int[][] arr =
{
    {1,2},
    {3,4,5},
    {6}
};
```

This is valid.

Because:

```text
arr

 |
 +--> [1,2]

 |
 +--> [3,4,5]

 |
 +--> [6]
```

Rows can have different lengths.

This is called:

### Jagged Array

---

## 12. Arrays Store Primitive and Reference Types ⭐⭐⭐

Primitive:

```java
int[] nums = {1,2,3};
```

Reference:

```java
String[] names =
{
    "Mahesh",
    "Rahul"
};
```

Custom Objects:

```java
Student[] students = new Student[100];
```

---

## 13. Covariant Arrays ⭐⭐⭐⭐

Tricky interview question.

```java
Object[] arr = new String[3];
```

This compiles.

But:

```java
arr[0] = "Hello"; // OK

arr[1] = 100; // Runtime Error
```

Throws:

```text
ArrayStoreException
```

---

## 14. Array Coversion to Stream ⭐⭐⭐⭐

Java 8 feature.

```java
int[] arr = {1,2,3,4};

Arrays.stream(arr)
      .forEach(System.out::println);
```

Output:

```text
1
2
3
4
```

---

## 15. Important Interview Algorithms on Arrays ⭐⭐⭐⭐⭐

If your goal is **Big Product Based Companies**, these topics are essential:

1. Reverse Array
2. Rotate Array
3. Prefix Sum
4. Sliding Window
5. Kadane's Algorithm
6. Two Sum
7. Dutch National Flag Algorithm
8. Merge Intervals
9. Next Permutation
10. Majority Element
11. Product of Array Except Self
12. Trapping Rain Water
13. Maximum Subarray
14. Binary Search on Arrays
15. Merge Sorted Arrays
16. Find Missing Number
17. Move Zeros to End
18. Spiral Matrix
19. 2D Matrix Search
20. Maximum Product Subarray

---

## Array Topics Checklist for SDE Interviews

✅ Array Basics
✅ Declaration & Initialization
✅ Traversal (`for`, enhanced `for`)
✅ Array Memory Model
✅ Passing Arrays to Methods
✅ Returning Arrays
✅ Anonymous Arrays
✅ Varargs (`int...`)
✅ `Arrays` Utility Class
✅ `System.arraycopy()`
✅ Clone vs Reference Copy
✅ Multidimensional Arrays
✅ Jagged Arrays
✅ Arrays of Objects
✅ Covariant Arrays
✅ Array Algorithms (Prefix Sum, Sliding Window, Kadane, Two Sum, etc.)
