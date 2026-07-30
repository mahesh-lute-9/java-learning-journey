Java Exception Handling — Cheatsheet

«[!TIP]
Use this file for quick revision. For detailed explanations, examples, and interview preparation, refer to the files inside the "NOTES/" directory.»

---

1. Exception Hierarchy

Object
  │
  └── Throwable
        │
        ├── Error
        │     ├── StackOverflowError
        │     └── OutOfMemoryError
        │
        └── Exception
              │
              ├── IOException
              │     └── FileNotFoundException
              │
              ├── SQLException
              │
              └── RuntimeException
                    ├── ArithmeticException
                    ├── NullPointerException
                    ├── IllegalArgumentException
                    │     └── NumberFormatException
                    └── IndexOutOfBoundsException
                          ├── ArrayIndexOutOfBoundsException
                          └── StringIndexOutOfBoundsException

Remember

Throwable
├── Error       → Serious system/JVM problems
└── Exception   → Conditions applications may handle

---

2. Basic try-catch

try {

    // Risky code

} catch (ExceptionType e) {

    // Handle exception

}

Example:

try {

    int result = 10 / 0;

} catch (ArithmeticException e) {

    System.out.println(e.getMessage());

}

Rule

When an exception occurs inside "try":

Exception Occurs
       ↓
Remaining try statements skipped
       ↓
Matching catch searched
       ↓
catch executes
       ↓
Program continues

---

3. Multiple catch Blocks

try {

    // Risky code

} catch (ArithmeticException e) {

    // Handle arithmetic problem

} catch (NullPointerException e) {

    // Handle null problem

} catch (Exception e) {

    // General handler

}

«[!IMPORTANT]
Always place specific exceptions before general exceptions.»

Correct

catch (ArithmeticException e) {

}

catch (Exception e) {

}

Incorrect

catch (Exception e) {

}

catch (ArithmeticException e) {

}

The second "catch" is unreachable.

---

4. finally

try {

    // Risky code

} catch (Exception e) {

    // Handle exception

} finally {

    // Cleanup

}

Execution

try
 ↓
catch (if required)
 ↓
finally
 ↓
continue

"finally" normally executes whether an exception occurs or not.

Typical use:

- Cleanup operations
- Releasing resources
- Closing connections

«[!WARNING]
Avoid "return" statements inside "finally". They can override another return value or suppress a pending exception.»

---

5. throw

Used to explicitly throw an exception object.

throw new IllegalArgumentException(
        "Age cannot be negative."
);

Example:

if (age < 0) {

    throw new IllegalArgumentException(
            "Age cannot be negative."
    );

}

Remember

throw = actually throw an exception

---

6. throws

Used in a method declaration to specify exceptions that may be propagated.

void readFile() throws IOException {

}

Multiple exceptions:

void process()
        throws IOException, SQLException {

}

Remember

throws = declare possible exception types

---

7. throw vs throws

"throw"| "throws"
Throws an exception| Declares possible exceptions
Used inside method/block| Used in method signature
Works with an exception object| Works with exception types
One object per statement| Can declare multiple types

void process() throws IOException {

    throw new IOException("Unable to process.");

}

---

8. Checked Exceptions

Checked exceptions are enforced by the compiler.

They must be:

Handled
   OR
Declared

Handle

try {

    // Code that may throw checked exception

} catch (IOException e) {

}

Declare

void readData() throws IOException {

}

Common examples:

IOException
FileNotFoundException
SQLException
ClassNotFoundException
InterruptedException

---

9. Unchecked Exceptions

Unchecked exceptions are subclasses of:

RuntimeException

The compiler does not require them to be handled or declared.

Common examples:

Exception| Typical Cause
"ArithmeticException"| Integer division by zero
"NullPointerException"| Dereferencing "null"
"ArrayIndexOutOfBoundsException"| Invalid array index
"NumberFormatException"| Invalid numeric conversion
"IllegalArgumentException"| Invalid method argument
"ClassCastException"| Invalid type cast

---

10. Checked vs Unchecked

Checked| Unchecked
Compiler enforces handling/declaration| Compiler does not enforce it
Usually extends "Exception" directly| Extends "RuntimeException"
Must handle or declare| Handling optional
Example: "IOException"| Example: "ArithmeticException"

«[!NOTE]
Both checked and unchecked exceptions occur at runtime.
"Checked" refers to compiler enforcement.»

---

11. Custom Checked Exception

class InvalidAgeException extends Exception {

    public InvalidAgeException(String message) {

        super(message);

    }

}

Usage:

void validateAge(int age)
        throws InvalidAgeException {

    if (age < 18) {

        throw new InvalidAgeException(
                "Age must be 18 or above."
        );

    }

}

---

12. Custom Unchecked Exception

class InvalidAgeException
        extends RuntimeException {

    public InvalidAgeException(String message) {

        super(message);

    }

}

Usage:

if (age < 18) {

    throw new InvalidAgeException(
            "Age must be 18 or above."
    );

}

No "throws" declaration is required by the compiler.

---

13. Exception Propagation

methodOne()
    ↓
methodTwo()
    ↓
methodThree()
    ↓
Exception

If "methodThree()" does not handle the exception:

methodThree()
     ↑
methodTwo()
     ↑
methodOne()
     ↑
main()
     ↑
JVM

The exception moves up the call stack until a compatible handler is found.

If nobody handles it, the JVM's default exception handling terminates the thread and prints exception information.

---

14. Try-with-Resources

Introduced in Java 7.

try (BufferedReader reader =
        new BufferedReader(
                new FileReader("data.txt")
        )) {

    // Use resource

} catch (IOException e) {

    System.out.println(e.getMessage());

}

Requirement

The resource must implement:

AutoCloseable

or an interface derived from it, such as "Closeable".

Benefits

Automatic close()
Less boilerplate
Safer cleanup
Reduced resource leaks

---

15. Multiple Resources

try (
    FileReader reader = new FileReader("input.txt");
    BufferedReader buffer = new BufferedReader(reader)
) {

    // Use resources

}

Resources are closed in reverse declaration order.

BufferedReader
      ↓
FileReader

---

16. Common Exception Methods

Assume:

catch (Exception e) {

}

"getMessage()"

e.getMessage();

Returns the detail message.

---

"toString()"

e.toString();

Returns the exception class name plus its detail message when available.

---

"printStackTrace()"

e.printStackTrace();

Prints the stack trace, which helps locate where the exception originated and how execution reached that point.

---

17. Exception Matching

Suppose Java throws:

ArithmeticException

Hierarchy:

ArithmeticException
        ↑
RuntimeException
        ↑
Exception
        ↑
Throwable

Therefore, compatible handlers can include:

catch (ArithmeticException e)

catch (RuntimeException e)

catch (Exception e)

But Java executes only the first compatible catch block.

---

18. Custom Exception Decision

Need application-specific exception?
              │
              ▼
             Yes
              │
              ▼
Should callers be forced by the compiler
to handle or declare it?
        │               │
       Yes              No
        │               │
        ▼               ▼
   Exception       RuntimeException
        │               │
        ▼               ▼
    Checked          Unchecked

---

19. Common Mistakes

Empty catch

catch (Exception e) {

}

Avoid silently swallowing exceptions.

---

Catching everything

catch (Exception e) {

}

Prefer a more specific type when you can handle it meaningfully.

---

Generic throw

throw new Exception("Error");

Prefer an exception that describes the actual failure.

---

Large try block

try {

    // Huge amount of unrelated code

}

Keep "try" focused on operations whose failures you intend to handle.

---

Manual resource handling when unnecessary

Prefer:

try (Resource resource = ...) {

}

when the resource supports "AutoCloseable".

---

20. Best Practices

- Catch specific exceptions.
- Provide meaningful exception messages.
- Never silently ignore exceptions.
- Keep "try" blocks focused.
- Use Try-with-Resources for "AutoCloseable" resources.
- Use custom exceptions for meaningful domain-specific failures.
- Validate before modifying important object state.
- Avoid catching "Throwable" in normal application code.
- Avoid returning from "finally".
- Handle exceptions at a level where meaningful recovery or reporting is possible.

---

21. Quick Syntax Revision

try-catch

try {

} catch (Exception e) {

}

try-catch-finally

try {

} catch (Exception e) {

} finally {

}

throw

throw new IllegalArgumentException("Invalid value");

throws

void method() throws IOException {

}

Custom Checked Exception

class MyException extends Exception {

}

Custom Unchecked Exception

class MyException extends RuntimeException {

}

Try-with-Resources

try (Resource resource = ...) {

}

---

22. One-Line Revision

Concept| Remember
"Throwable"| Root of Java's throwable hierarchy
"Error"| Serious JVM/system-level problem
"Exception"| Conditions applications may handle
"RuntimeException"| Base class for many unchecked exceptions
"try"| Contains risky code
"catch"| Handles compatible exceptions
"finally"| Cleanup/finalization logic
"throw"| Explicitly throws an object
"throws"| Declares possible exception types
Checked| Must handle or declare
Unchecked| Compiler does not require handling
Custom Exception| Application/domain-specific exception
Propagation| Exception moves up call stack
"AutoCloseable"| Enables try-with-resources
"getMessage()"| Gets detail message
"printStackTrace()"| Prints stack trace

---

⚡ 30-Second Revision

Throwable
├── Error
└── Exception
     └── RuntimeException

try      → risky code
catch    → handle exception
finally  → cleanup
throw    → throw exception
throws   → declare exception

Exception
   ↓
Checked
   ↓
Handle OR Declare

RuntimeException
   ↓
Unchecked
   ↓
Compiler does not force handling

Custom Checked   → extends Exception
Custom Unchecked → extends RuntimeException

Resource Management
        ↓
Try-with-Resources
        ↓
AutoCloseable
        ↓
Automatic close()

«[!IMPORTANT]
Don't memorize Exception Handling as isolated syntax. Understand the flow:

Exception occurs → normal flow breaks → handler is searched → exception is handled or propagated → cleanup runs where applicable → execution either continues or terminates.»
