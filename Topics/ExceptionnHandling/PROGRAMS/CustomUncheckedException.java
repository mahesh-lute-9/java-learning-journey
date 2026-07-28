/*
 * ============================================================================
 * Program 15 : Custom Unchecked Exception
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to create and use a custom unchecked exception for
 * validating an employee's salary.
 *
 * Objective:
 * - Learn how to create a custom unchecked exception.
 * - Understand the role of RuntimeException.
 * - Observe that unchecked exceptions do not require throws declaration.
 *
 * Concepts Covered:
 * - Custom Exception
 * - Unchecked Exception
 * - RuntimeException
 * - throw Keyword
 * - super() Constructor
 * ============================================================================
 */

class InvalidSalaryException extends RuntimeException {

    public InvalidSalaryException(String message) {

        super(message);

    }

}

public class CustomUncheckedException {

    public static void validateSalary(double salary) {

        if (salary < 0) {

            throw new InvalidSalaryException(
                    "Salary cannot be negative."
            );

        }

        System.out.println("Salary is valid.");

    }

    public static void main(String[] args) {

        System.out.println("Program Started");

        double salary = -5000.0;

        try {

            validateSalary(salary);

        }

        catch (InvalidSalaryException e) {

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
 * Custom Exception Caught : Salary cannot be negative.
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. InvalidSalaryException is a user-defined exception class.
 *
 * 2. It extends RuntimeException:
 *
 *      class InvalidSalaryException extends RuntimeException
 *
 *    Therefore, InvalidSalaryException is an unchecked exception.
 *
 * 3. The constructor receives an exception message.
 *
 * 4. super(message) passes the message to RuntimeException.
 *
 * 5. validateSalary() receives the salary value.
 *
 * 6. Unlike a checked custom exception, validateSalary() is not required
 *    to declare:
 *
 *      throws InvalidSalaryException
 *
 * 7. The salary value -5000.0 is passed to validateSalary().
 *
 * 8. Since salary < 0, a new InvalidSalaryException object is created.
 *
 * 9. The throw keyword explicitly throws the exception.
 *
 * 10. Normal execution of validateSalary() stops immediately.
 *
 * 11. Control transfers to the matching catch block in main().
 *
 * 12. getMessage() retrieves the message passed through super(message).
 *
 * 13. After the exception is handled, the program continues normally.
 * ============================================================================
 */

/*
 * ============================================================================
 * Checked vs Unchecked Custom Exception:
 * ============================================================================
 *
 * Checked Custom Exception:
 *
 *      class MyException extends Exception
 *
 *      - Compiler checks it.
 *      - Must be handled or declared.
 *
 *
 * Unchecked Custom Exception:
 *
 *      class MyException extends RuntimeException
 *
 *      - Compiler does not require handling or declaration.
 *      - Handling with try-catch is optional.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ Extending RuntimeException creates an unchecked custom exception.
 *
 * ✓ Unchecked exceptions do not need to be declared using throws.
 *
 * ✓ They can still be handled using try-catch when recovery is meaningful.
 *
 * ✓ throw is used to explicitly throw the custom exception object.
 *
 * ✓ super(message) passes the error message to RuntimeException.
 *
 * ✓ Custom unchecked exceptions are commonly useful for invalid arguments,
 *   invalid application state, and business-rule violations.
 *
 * ✓ Whether a custom exception should be checked or unchecked depends on
 *   whether callers are reasonably expected to recover from the condition.
 * ============================================================================
 */
