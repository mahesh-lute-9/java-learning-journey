# 🚀 IntegerCache in Java

IntegerCache is an **internal optimization mechanism** used by Java to cache `Integer` objects for a specific range of values.

This means:

```java
Integer a = 100;
Integer b = 100;

System.out.println(a == b);
```

Output:

```text
true
```

Because Java does **not create two different Integer objects** here.

It reuses the same cached object.

---

# 1. Why IntegerCache Exists?

Suppose Java creates objects for every Integer:

```java
Integer a = 1;
Integer b = 1;
Integer c = 1;
Integer d = 1;
```

Millions of such objects may be created.

Problems:

* More Heap Memory Usage
* More Garbage Collection
* Slower Performance
* Increased Object Creation Cost

Therefore:

> Java caches commonly used Integer objects.

This optimization is called:

```text
IntegerCache
```

---

# 2. Cached Range

By default:

```text
-128 to 127
```

This range is cached.

Example:

```java
Integer a = 127;
Integer b = 127;

System.out.println(a == b);
```

Output:

```text
true
```

Because:

```text
a and b point to the same cached object.
```

---

But:

```java
Integer a = 128;
Integer b = 128;

System.out.println(a == b);
```

Output:

```text
false
```

Because:

```text
128 is outside cache range.
```

---

# Example

```java
public class Main {

    public static void main(String[] args) {

        Integer a = 100;

        Integer b = 100;

        Integer c = 200;

        Integer d = 200;

        System.out.println(a == b);

        System.out.println(c == d);

    }

}
```

Output:

```text
true

false
```

---

# 3. Why does this happen?

Because:

```java
Integer a = 100;
```

Compiler converts it to:

```java
Integer a = Integer.valueOf(100);
```

NOT:

```java
Integer a = new Integer(100);
```

And:

```java
Integer.valueOf()
```

checks the cache first.

---

# Internal Implementation

Simplified source:

```java
public static Integer valueOf(int i) {

    if(i >= -128 && i <= 127)

        return IntegerCache.cache[i + 128];

    return new Integer(i);

}
```

If value is inside cache:

```text
Return Existing Object
```

Else:

```text
Create New Object
```

---

# 4. What is IntegerCache?

Inside Integer class:

```java
private static class IntegerCache {

    static final Integer cache[];

    static {

        cache = new Integer[256];

        int j = -128;

        for(int k = 0 ; k < cache.length ; k++){

            cache[k] = new Integer(j++);

        }

    }

}
```

---

Cache Layout

```text
cache[0]   -> -128

cache[1]   -> -127

...

cache[128] -> 0

...

cache[255] -> 127
```

---

# 5. valueOf() vs new Integer()

## valueOf()

```java
Integer a = Integer.valueOf(100);

Integer b = Integer.valueOf(100);

System.out.println(a == b);
```

Output:

```text
true
```

Because:

```text
IntegerCache is used.
```

---

## new Integer()

```java
Integer a = new Integer(100);

Integer b = new Integer(100);

System.out.println(a == b);
```

Output:

```text
false
```

Because:

```text
Every call creates a new object.
```

---

# Memory Representation

```text
Integer a = 100;

Integer b = 100;



       IntegerCache

             |

        +-----------+

        |   100     |

        +-----------+

          ↑      ↑

          |      |

          a      b
```

Same object.

---

```text
Integer a = new Integer(100);

Integer b = new Integer(100);



      +-------+        +-------+

      | 100   |        | 100   |

      +-------+        +-------+

         ↑                 ↑

         a                 b
```

Different objects.

---

# 6. Autoboxing uses IntegerCache

When you write:

```java
Integer x = 50;
```

Compiler converts:

```java
Integer x = Integer.valueOf(50);
```

This is called:

```text
Autoboxing
```

Primitive:

```java
int x = 50;
```

Wrapper:

```java
Integer y = x;
```

Internally:

```java
Integer y = Integer.valueOf(x);
```

---

# 7. Famous Interview Question

Predict:

```java
Integer a = 127;

Integer b = 127;

System.out.println(a == b);
```

Output:

```text
true
```

---

Predict:

```java
Integer a = 128;

Integer b = 128;

System.out.println(a == b);
```

Output:

```text
false
```

Reason:

```text
127 -> Cached

128 -> Not Cached
```

---

# 8. == vs equals()

This is one of the most asked interview topics.

```java
Integer a = 200;

Integer b = 200;

System.out.println(a == b);

System.out.println(a.equals(b));
```

Output:

```text
false

true
```

Because:

```text
==

Compares Memory Addresses


equals()

Compares Actual Values
```

---

Example:

```java
Integer a = 500;

Integer b = 500;


a == b
```

Output:

```text
false
```

---

```java
a.equals(b)
```

Output:

```text
true
```

---

# 9. Tricky Interview Question

Predict:

```java
Integer a = 100;

Integer b = 100;

Integer c = 200;

Integer d = 200;


System.out.println(a == b);

System.out.println(c == d);

System.out.println(a.equals(b));

System.out.println(c.equals(d));
```

Output:

```text
true

false

true

true
```

---

# 10. Integer vs int

```java
Integer a = 100;

int b = 100;

System.out.println(a == b);
```

Output:

```text
true
```

Why?

Compiler performs:

```text
Unboxing
```

Converts:

```java
a.intValue() == b
```

which becomes:

```java
100 == 100
```

---

# 11. NullPointerException Trap

```java
Integer a = null;

int b = 5;

System.out.println(a == b);
```

Output:

```text
NullPointerException
```

Because compiler converts:

```java
a.intValue() == b
```

Equivalent:

```java
null.intValue()
```

which throws:

```text
NullPointerException
```

---

# 12. Other Wrapper Class Caches

| Wrapper   | Cache Range  |
| --------- | ------------ |
| Byte      | Entire Range |
| Short     | -128 to 127  |
| Integer   | -128 to 127  |
| Long      | -128 to 127  |
| Character | 0 to 127     |
| Boolean   | true & false |

---

# 13. Can Integer Cache Size be Changed?

Yes.

JVM Option:

```bash
-XX:AutoBoxCacheMax=1000
```

Then:

```java
Integer a = 500;

Integer b = 500;

System.out.println(a == b);
```

Output may become:

```text
true
```

because:

```text
Cache Range = -128 to 1000
```

---

# 14. IntegerCache and JVM Memory

```text
                   Heap Memory


        +-------------------------+

        |     IntegerCache        |

        |                         |

        | -128                    |

        | -127                    |

        |  ...                    |

        |  0                      |

        |  ...                    |

        | 127                     |

        +-------------------------+



      Integer a = 100

      Integer b = 100


            ↓

      Both point to

      Same Cached Object
```

---

# 15. Why JVM Designers Introduced IntegerCache?

Reasons:

1. Reduce Heap Usage
2. Reduce Object Creation
3. Reduce Garbage Collection
4. Improve Performance
5. Optimize Frequently Used Integers

---

# ==================================================

# 🎯 SDE INTERVIEW QUESTIONS WITH ANSWERS

### Q1. What is IntegerCache?

**Answer:**

IntegerCache is an internal cache used by Java to store Integer objects from:

```text
-128 to 127
```

to avoid unnecessary object creation.

---

### Q2. What is the output?

```java
Integer a = 127;

Integer b = 127;

System.out.println(a == b);
```

**Answer**

```text
true
```

Because:

```text
127 is cached.
```

---

### Q3. What is the output?

```java
Integer a = 128;

Integer b = 128;

System.out.println(a == b);
```

**Answer**

```text
false
```

Because:

```text
128 is outside cache range.
```

---

### Q4. What is the difference between:

```java
Integer.valueOf(100)
```

and

```java
new Integer(100)
```

**Answer**

```text
valueOf() -> Uses Cache

new Integer() -> Creates New Object
```

---

### Q5. What is Autoboxing?

**Answer**

```java
Integer x = 10;
```

Compiler converts:

```java
Integer x = Integer.valueOf(10);
```

Automatically converting:

```text
int -> Integer
```

is called:

```text
Autoboxing
```

---

### Q6. What is Unboxing?

**Answer**

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

### Q7. == vs equals() ?

**Answer**

```text
==

Compares References


equals()

Compares Values
```

Example:

```java
Integer a = 500;

Integer b = 500;


a == b          -> false

a.equals(b)     -> true
```

---

### Q8. What happens here?

```java
Integer a = null;

int b = a;
```

**Answer**

```text
NullPointerException
```

Because:

```java
a.intValue()
```

is called internally.

---

### Q9. Can Integer Cache Size be Changed?

**Answer**

Yes.

JVM Argument:

```bash
-XX:AutoBoxCacheMax=1000
```

---

### Q10. Why was IntegerCache introduced?

**Answer**

To:

* Save Heap Memory
* Reduce Object Creation
* Reduce Garbage Collection
* Improve Performance

---

# 🔥 SDE Takeaway

Remember these 5 rules:

✅ `Integer x = 10` → uses `Integer.valueOf()`

✅ Cache range is `-128 to 127`

✅ `==` compares references.

✅ `.equals()` compares values.

✅ Autoboxing & Unboxing can cause tricky bugs and `NullPointerException`.

These questions are frequently asked in SDE interviews because they test:

* JVM Internals
* Wrapper Classes
* Heap Memory
* IntegerCache
* Autoboxing & Unboxing
* `==` vs `.equals()`
* Hidden NullPointerException Scenarios
