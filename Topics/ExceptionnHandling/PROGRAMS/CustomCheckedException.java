/*
 * ============================================================================
 * Program 14 : Custom Checked Exception
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to create and use a custom checked exception for
 * validating a user's age.
 *
 * Objective:
 * - Learn how to create a custom checked exception.
 * - Understand how to throw a custom exception explicitly.
 * - Learn how checked custom exceptions are handled or declared.
 *
 * Concepts Covered:
 * - Custom Exception
 * - Checked Exception
 * - Exception Class
 * - throw Keyword
 * - throws Keyword
 * - super() Constructor
 * ============================================================================
 */

class InvalidAgeException extends Exception {

    public InvalidAgeException(String message) {

        super(message);

    }

}

public class CustomCheckedException {

    public static void validateAge(int age) throws InvalidAgeException {

        if (age < 18) {

            throw new InvalidAgeException(
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

        catch (InvalidAgeException e) {

            System.out.println(
                    "Custom Exception Caught : " + e.getMessage()
            );

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
 * Custom Exception Caught : Age must be 18 or above.
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. InvalidAgeException is a user-defined exception class.
 *
 * 2. It extends Exception:
 *
 *      class InvalidAgeException extends Exception
 *
 *    Therefore, InvalidAgeException is a checked exception.
 *
 * 3. Its constructor receives an exception message.
 *
 * 4. super(message) passes that message to the constructor of Exception.
 *
 * 5. validateAge() declares:
 *
 *      throws InvalidAgeException
 *
 *    because the method may throw a checked exception.
 *
 * 6. The age value 16 is passed to validateAge().
 *
 * 7. Since age < 18, a new InvalidAgeException object is created.
 *
 * 8. The throw keyword explicitly throws that object.
 *
 * 9. Control returns to the calling method while searching for a matching
 *    exception handler.
 *
 * 10. The catch block in main() handles InvalidAgeException.
 *
 * 11. getMessage() retrieves the message originally passed through
 *     super(message).
 *
 * 12. After the exception is handled, normal program execution continues.
 * ============================================================================
 */

/*
 * ============================================================================
 * Exception Flow:
 * ============================================================================
 *
 * main()
 *   |
 *   v
 * validateAge(16)
 *   |
 *   v
 * age < 18
 *   |
 *   v
 * new InvalidAgeException(...)
 *   |
 *   v
 * throw
 *   |
 *   v
 * catch (InvalidAgeException e)
 *   |
 *   v
 * Exception Handled
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ A custom exception is created by defining a new exception class.
 *
 * ✓ Extending Exception creates a checked custom exception.
 *
 * ✓ Checked custom exceptions must be handled using try-catch or declared
 *   using throws.
 *
 * ✓ throw is used to actually throw the custom exception object.
 *
 * ✓ throws declares that a method may throw the custom exception.
 *
 * ✓ super(message) passes the custom message to the parent Exception class.
 *
 * ✓ Custom exceptions are useful when built-in Java exceptions do not clearly
 *   represent an application's business rule or domain-specific failure.
 *
 * ✓ Meaningful names such as InvalidAgeException make application errors
 *   easier to understand.
 * ============================================================================
 */
