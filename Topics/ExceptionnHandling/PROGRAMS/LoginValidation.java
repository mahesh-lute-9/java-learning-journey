/*
 * ============================================================================
 * Program 18 : Login Validation
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to simulate a login system where custom exceptions are
 * thrown when the username or password is invalid.
 *
 * Objective:
 * - Apply Exception Handling to a real-world login scenario.
 * - Validate username and password separately.
 * - Use custom exceptions to represent authentication failures clearly.
 *
 * Concepts Covered:
 * - Custom Exceptions
 * - RuntimeException
 * - throw Keyword
 * - try-catch
 * - Multiple catch Blocks
 * - Input Validation
 * - Authentication Logic
 * ============================================================================
 */

class InvalidUsernameException extends RuntimeException {

    public InvalidUsernameException(String message) {

        super(message);

    }

}

class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {

        super(message);

    }

}

class LoginService {

    private final String validUsername = "admin";
    private final String validPassword = "Java@123";

    public void login(String username, String password) {

        if (username == null || username.isBlank()) {

            throw new InvalidUsernameException(
                    "Username cannot be empty."
            );

        }

        if (password == null || password.isBlank()) {

            throw new InvalidPasswordException(
                    "Password cannot be empty."
            );

        }

        if (!validUsername.equals(username)) {

            throw new InvalidUsernameException(
                    "Invalid username."
            );

        }

        if (!validPassword.equals(password)) {

            throw new InvalidPasswordException(
                    "Invalid password."
            );

        }

        System.out.println("Login Successful");

    }

}

public class LoginValidation {

    public static void main(String[] args) {

        System.out.println("Program Started");

        LoginService loginService = new LoginService();

        String username = "admin";
        String password = "wrongPassword";

        try {

            loginService.login(username, password);

        }

        catch (InvalidUsernameException e) {

            System.out.println(
                    "Login Failed : " + e.getMessage()
            );

        }

        catch (InvalidPasswordException e) {

            System.out.println(
                    "Login Failed : " + e.getMessage()
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
 * Login Failed : Invalid password.
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. Two custom unchecked exceptions are created:
 *
 *      InvalidUsernameException
 *      InvalidPasswordException
 *
 * 2. Both extend RuntimeException, so the compiler does not require them to
 *    be declared using throws.
 *
 * 3. LoginService contains the valid username and password.
 *
 * 4. The login() method first checks whether the username is null or blank.
 *
 * 5. It then checks whether the password is null or blank.
 *
 * 6. validUsername.equals(username) is used instead of:
 *
 *      username.equals(validUsername)
 *
 *    This avoids NullPointerException if username is null.
 *
 * 7. If the username does not match, InvalidUsernameException is thrown.
 *
 * 8. If the password does not match, InvalidPasswordException is thrown.
 *
 * 9. In this example, the username "admin" is correct.
 *
 * 10. The password "wrongPassword" is incorrect.
 *
 * 11. Therefore, InvalidPasswordException is thrown.
 *
 * 12. The matching catch block handles the exception and displays the
 *     failure message.
 *
 * 13. Since the exception is handled, the program continues normally.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ Custom exceptions can represent different authentication failures.
 *
 * ✓ Validation should be performed before processing login credentials.
 *
 * ✓ Different exceptions can be handled using separate catch blocks.
 *
 * ✓ RuntimeException-based custom exceptions do not require a throws
 *   declaration.
 *
 * ✓ Calling equals() on a known non-null value helps avoid an accidental
 *   NullPointerException.
 *
 * ✓ isBlank() detects empty strings and strings containing only whitespace.
 *
 * ✓ Exception Handling communicates authentication failure while normal
 *   conditional logic determines which validation rule failed.
 *
 * NOTE:
 * This program stores credentials directly in code only for learning purposes.
 * Real applications should never store plain-text passwords this way.
 * Authentication systems normally use secure password hashing and persistent
 * user storage.
 * ============================================================================
 */
