/*
 * ============================================================================
 * Program 10 : Throw Keyword
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to explicitly throw an exception using the throw
 * keyword when an invalid value is provided.
 *
 * Objective:
 * - Understand the purpose of the throw keyword.
 * - Learn how to explicitly create and throw an exception.
 * - Understand how throw can be used for validation.
 *
 * Concepts Covered:
 * - throw Keyword
 * - Explicit Exception Throwing
 * - IllegalArgumentException
 * - Input Validation
 * ============================================================================
 */

public class ThrowKeyword {

    public static void validateAge(int age) {

        if (age < 18) {

            throw new IllegalArgumentException(
                    "Age must be 18 or above."
            );

        }

        System.out.println("Age is valid.");

    }

    public static void main(String[] args) {

        System.out.println("Program Started");

        int age = 16;

        try {

            validateAge(age);

        }

        catch (IllegalArgumentException e) {

            System.out.println("Exception Caught : " + e.getMessage());

        }

        System.out.println("Program Finished");

    }

}

/*
 * ============================================================================
 * Sample Output:
 * ============================================================================
 *
 * Program Started
 * Exception Caught : Age must be 18 or above.
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. The program starts execution from the main() method.
 * 2. The age variable is initialized with the value 16.
 * 3. The validateAge() method is called.
 * 4. Inside validateAge(), the condition age < 18 becomes true.
 * 5. The programmer explicitly creates an IllegalArgumentException object.
 * 6. The throw keyword throws that exception.
 * 7. Normal execution of validateAge() stops immediately.
 * 8. Control transfers to the matching catch block in main().
 * 9. The exception message is printed using getMessage().
 * 10. After handling the exception, the program continues normally.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ The throw keyword is used to explicitly throw an exception.
 * ✓ The programmer decides when the exception should be thrown.
 * ✓ throw is commonly used for input and business-rule validation.
 * ✓ Only an object whose type extends Throwable can be thrown.
 * ✓ Execution of the current block stops immediately after throw.
 * ✓ throw actually throws an exception, while throws declares that a method
 *   may throw an exception.
 * ============================================================================
 */
