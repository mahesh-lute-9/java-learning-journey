

/*
------------------------------------------------------------
Program 14 : Wrapper Performance (Boxing & Unboxing Overhead)

Language    : Java
Difficulty  : ⭐⭐⭐⭐ Advanced

Concepts Covered

	✔ Autoboxing Overhead
	✔ Unboxing Overhead
	✔ Primitive Performance
	✔ Wrapper Performance
	✔ Best Practices

Expected Time : 30 Minutes
------------------------------------------------------------

Problem Statement

	Write a Java program to compare the performance of
	primitive data types and Wrapper Classes while
	performing a large number of addition operations.

	Measure the execution time for both approaches.

------------------------------------------------------------
*/

public class WrapperPerformanceDemo {

    public static void main(String[] args) {

        final int ITERATIONS = 10_000_000;

        // Primitive Performance

        long primitiveStartTime = System.nanoTime();

        int primitiveSum = 0;

        for (int employeeAge = 1; employeeAge <= ITERATIONS; employeeAge++) {

            primitiveSum += employeeAge;

        }

        long primitiveEndTime = System.nanoTime();

        // Wrapper Performance

        long wrapperStartTime = System.nanoTime();

        Integer wrapperSum = 0;

        for (int employeeAge = 1; employeeAge <= ITERATIONS; employeeAge++) {

            wrapperSum += employeeAge;

        }

        long wrapperEndTime = System.nanoTime();

        System.out.println("Primitive Sum : " + primitiveSum);

        System.out.println("Wrapper Sum   : " + wrapperSum);

        System.out.println();

        System.out.println("Primitive Time : "
                + (primitiveEndTime - primitiveStartTime)
                + " ns");

        System.out.println("Wrapper Time   : "
                + (wrapperEndTime - wrapperStartTime)
                + " ns");

    }

}

/*
------------------------------------------------------------
Sample Output

	Primitive Sum : -2004260032

	Wrapper Sum   : -2004260032

	Primitive Time : 10576431 ns

	Wrapper Time   : 29851644 ns

	(Note: Execution time varies depending on the
	hardware, JVM, and operating system.)

------------------------------------------------------------
Explanation:

	Primitive data types store values directly.

	During addition,

	primitive values are processed directly by the CPU.

------------------------------------------------------------

	Wrapper Classes store objects.

	Consider

	wrapperSum += employeeAge;

	The compiler converts it approximately into

	wrapperSum =
        	Integer.valueOf(wrapperSum.intValue() + employeeAge);

	Each iteration involves

	1.

	Unboxing

	↓

	wrapperSum.intValue()

-------------------------

	2.

	Addition

	↓

	primitive arithmetic

-------------------------

	3.

	Autoboxing

	↓

	Integer.valueOf(...)

Because these additional operations occur in every
iteration, Wrapper Classes are generally slower than
primitive data types for intensive numerical
computations.

------------------------------------------------------------
Internal Working:

	Primitive

	primitiveSum += employeeAge;

	↓

	Addition

	↓

	Result

------------------------------------------------------------

Wrapper:

	wrapperSum += employeeAge;

	↓

	Unboxing

	↓

	Addition

	↓

	Autoboxing

	↓

	Result

------------------------------------------------------------
Important Points

	✔ Primitive data types are generally faster.

	✔ Wrapper Classes introduce Boxing and Unboxing
	operations.

	✔ Wrapper Classes create additional objects or reuse cached objects depending on the value.

	✔ Performance differences become noticeable in large
	loops.

	✔ Modern JVMs perform many optimizations, but primitive
	types are still preferred for heavy calculations.

------------------------------------------------------------
Common Mistakes

❌ Using Wrapper Classes inside performance-critical
loops without necessity.

------------------------------------------------------------

❌ Assuming Wrapper Classes perform exactly like
primitive types.

------------------------------------------------------------

❌ Measuring execution time using

System.currentTimeMillis()

instead of

System.nanoTime().

------------------------------------------------------------

❌ Drawing conclusions from a single execution.

Run the program multiple times because JVM
optimizations (such as JIT compilation) affect timing.

------------------------------------------------------------
Best Practices

✔ Prefer primitive types for mathematical calculations.

------------------------------------------------------------

✔ Use Wrapper Classes only when object behavior is
required.

------------------------------------------------------------

✔ Avoid unnecessary Boxing and Unboxing inside loops.

------------------------------------------------------------

✔ Benchmark performance using proper tools (such as
JMH) for production-quality measurements.

------------------------------------------------------------
Follow-up Interview Questions

1.

Why are Wrapper Classes slower than primitive data
types?

------------------------------------------------------------

2.

What happens internally during

wrapperSum += employeeAge;

------------------------------------------------------------

3.

Why should

System.nanoTime()

be preferred over

System.currentTimeMillis()

for benchmarking?

------------------------------------------------------------

4.

When should primitive data types be preferred?

------------------------------------------------------------

5.

How does the JVM optimize Boxing and Unboxing?

------------------------------------------------------------
Real World Use Cases

	✔ Financial Calculations

	✔ Scientific Computing

	✔ Data Analytics

	✔ Machine Learning

	✔ Competitive Programming

	✔ Performance-Critical Applications

------------------------------------------------------------
Practice Questions

1.

Repeat this program using

Double

and

double.

------------------------------------------------------------

2.

Increase

ITERATIONS

to

50,000,000

and compare the execution time.

------------------------------------------------------------

3.

Measure performance using

Long

and

long.

------------------------------------------------------------

4.

Research Java Microbenchmark Harness (JMH) and
benchmark the same program.

------------------------------------------------------------

Quick Revision

Primitive

↓

Direct Value

↓

Fast

-------------------------

Wrapper

↓

Object

↓

Autoboxing

↓

Unboxing

↓

Slower

-------------------------

Loops

↓

Prefer Primitive Types

-------------------------

Benchmark

↓

System.nanoTime()

------------------------------------------------------------
*/
