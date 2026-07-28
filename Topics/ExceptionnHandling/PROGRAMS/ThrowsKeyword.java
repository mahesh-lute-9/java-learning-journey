/*
 * ============================================================================
 * Program 11 : Throws Keyword
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to demonstrate the use of the throws keyword by
 * declaring a checked exception in a method and handling it in the calling
 * method.
 *
 * Objective:
 * - Understand the purpose of the throws keyword.
 * - Learn how a method declares a checked exception.
 * - Understand how exception-handling responsibility can be passed to the
 *   calling method.
 *
 * Concepts Covered:
 * - throws Keyword
 * - Checked Exception
 * - IOException
 * - Exception Declaration
 * - Exception Handling Responsibility
 * ============================================================================
 */

import java.io.IOException;

public class ThrowsKeyword {

    public static void readData() throws IOException {

        System.out.println("Inside readData()");

        throw new IOException("Unable to read data.");

    }

    public static void main(String[] args) {

        System.out.println("Program Started");

        try {

            readData();

        }

        catch (IOException e) {

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
 * Inside readData()
 * Exception Caught : Unable to read data.
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. The program starts execution from the main() method.
 * 2. main() calls the readData() method inside a try block.
 * 3. readData() declares IOException using the throws keyword.
 * 4. This declaration tells the compiler and caller that readData() may throw
 *    an IOException.
 * 5. Inside readData(), an IOException object is explicitly thrown.
 * 6. readData() does not handle the exception itself.
 * 7. The exception is passed back to the calling method.
 * 8. The catch block in main() handles the IOException.
 * 9. The exception message is printed using getMessage().
 * 10. The program continues normally after the exception is handled.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ throws is written in the method declaration.
 * ✓ It declares that a method may throw one or more exceptions.
 * ✓ It is mainly important when working with checked exceptions.
 * ✓ A method declaring a checked exception does not necessarily handle it.
 * ✓ The calling method must handle the checked exception or declare it again.
 * ✓ Multiple exceptions can be declared:
 *
 *      void process() throws IOException, SQLException
 *
 * ✓ throw and throws are different:
 *
 *      throw  -> Actually throws an exception object.
 *      throws -> Declares possible exceptions in a method signature.
 * ============================================================================
 */
