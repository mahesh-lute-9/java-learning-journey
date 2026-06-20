

IntegerCache in Java — Detailed Explanation with interview questions

IntegerCache is an internal optimization mechanism used by Java to cache Integer objects for a specific range of values.

This means:

Integer a = 100;
Integer b = 100;

System.out.println(a == b);

Output:

true

Because Java does not create two different objects here.

It reuses the same cached object.

1. Why IntegerCache Exists?

Suppose Java creates objects for every Integer:

Integer a = 1;
Integer b = 1;
Integer c = 1;
Integer d = 1;

Millions of such objects would be created.

Problems:

More heap memory usage
More garbage collection
Slower performance

Therefore:

Java caches commonly used integers.

2. Cached Range

By default:

-128 to 127

This range is cached.

Meaning:

Integer a = 127;
Integer b = 127;

a == b // true

But:

Integer a = 128;
Integer b = 128;

a == b // false

Because 128 is outside the cache.

Example
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

Output:

true
false
3. Why does this happen?

Because:

Integer a = 100;

Compiler converts:

Integer a = Integer.valueOf(100);

NOT:

new Integer(100)

And:

Integer.valueOf()

checks cache first.

Internal Implementation

Simplified source:

public static Integer valueOf(int i) {

    if(i >= -128 && i <= 127)
        return IntegerCache.cache[i + 128];

    return new Integer(i);
}

If inside cache:

Return existing object.

Else:

Create new object.

4. What is IntegerCache?

Inside Integer class:

private static class IntegerCache {

    static final Integer cache[];

    static {

        cache = new Integer[256];

        int j = -128;

        for(int k=0;k<cache.length;k++)
            cache[k] = new Integer(j++);
    }
}

Cache contains:

cache[0] -> -128
cache[1] -> -127
...
cache[128] -> 0
...
cache[255] -> 127
5. Difference Between valueOf() and new Integer()
valueOf()
Integer a = Integer.valueOf(100);
Integer b = Integer.valueOf(100);

a == b

Output:

true

Because cache is used.

new Integer()
Integer a = new Integer(100);
Integer b = new Integer(100);

System.out.println(a == b);

Output:

false

Because:

Every call creates a new object.

6. Autoboxing uses IntegerCache

When you write:

Integer x = 50;

Compiler converts:

Integer x = Integer.valueOf(50);

This is called:

Autoboxing

Primitive:

int x = 50;

Wrapper:

Integer y = x;

Internally:

Integer y = Integer.valueOf(x);
7. Why this interview question is famous?

Because of this:

Integer a = 127;
Integer b = 127;

System.out.println(a == b);

Answer:

true

But:

Integer a = 128;
Integer b = 128;

System.out.println(a == b);

Answer:

false
8. == vs equals()

Most important interview topic.

Integer a = 200;
Integer b = 200;

System.out.println(a == b);
System.out.println(a.equals(b));

Output:

false
true

Because:

==

Compares:

Memory addresses
equals()

Compares:

Actual values
Example
Integer a = 500;
Integer b = 500;

a == b        // false

a.equals(b)   // true
9. Tricky Interview Question

Predict:

Integer a = 100;
Integer b = 100;

Integer c = 200;
Integer d = 200;

System.out.println(a == b);
System.out.println(c == d);
System.out.println(a.equals(b));
System.out.println(c.equals(d));

Output:

true
false
true
true
10. Another Tricky One
Integer a = 100;
int b = 100;

System.out.println(a == b);

Output:

true

Why?

Because:

a.intValue() == b

Compiler performs:

Unboxing
Integer -> int
11. NullPointerException Trap
Integer a = null;
int b = 5;

System.out.println(a == b);

Output:

NullPointerException

Because:

Compiler converts:

a.intValue() == b

Equivalent:

null.intValue()

which throws:

NullPointerException
12. Does Long also have cache?

Yes.

Wrapper classes with cache:

Wrapper	Cache Range
Byte	Entire range
Short	-128 to 127
Integer	-128 to 127
Long	-128 to 127
Character	0 to 127
Boolean	true & false
13. Can Integer Cache Size be changed?

Yes.

JVM option:

-XX:AutoBoxCacheMax=1000

Then:

Integer a = 500;
Integer b = 500;

a == b

May become:

true

because cache extends to 1000.

SDE Level Interview Questions
Q1

What is the output?

Integer a = 127;
Integer b = 127;

System.out.println(a == b);

Answer

true
Q2
Integer a = 128;
Integer b = 128;

System.out.println(a == b);

Answer:

false
Q3
Integer a = 100;
Integer b = new Integer(100);

System.out.println(a == b);

Answer:

false

Because:

a -> cached object

b -> newly created object
Q4
Integer a = 100;

Integer b = 100;

System.out.println(a.equals(b));

Answer:

true
Q5 (Very Popular)
Integer a = null;

int b = a;

What happens?

Answer:

NullPointerException

because:

b = a.intValue();
SDE Takeaway

Remember these 5 rules:

Integer x = 10 → uses Integer.valueOf()
Cache range is -128 to 127
== compares references for objects.
.equals() compares values.
Autoboxing + Unboxing can cause tricky bugs and NullPointerException.

These questions are frequently asked in SDE interviews because they test:

JVM internals
Wrapper classes
Memory optimization
Autoboxing/Unboxing
== vs equals()
Hidden NullPointerException scenarios

A strong SDE candidate is expected not only to know the outputs, but also to explain how Integer.valueOf() uses IntegerCache internally and why the JVM designers introduced this optimization.
