/*
 * ============================================================================
 * Program 21 : Student Validation
 * ============================================================================
 *
 * Program Statement:
 * Write a Java program to validate student details such as name, age, marks,
 * and attendance using custom exceptions.
 *
 * Objective:
 * - Apply Exception Handling to a student-management scenario.
 * - Validate multiple fields before creating a valid student record.
 * - Use custom exceptions for different validation failures.
 *
 * Concepts Covered:
 * - Custom Exceptions
 * - RuntimeException
 * - throw Keyword
 * - Multiple catch Blocks
 * - Input Validation
 * - Business Rule Validation
 * - Encapsulation
 * ============================================================================
 */

class InvalidStudentNameException extends RuntimeException {

    public InvalidStudentNameException(String message) {

        super(message);

    }

}

class InvalidStudentAgeException extends RuntimeException {

    public InvalidStudentAgeException(String message) {

        super(message);

    }

}

class InvalidMarksException extends RuntimeException {

    public InvalidMarksException(String message) {

        super(message);

    }

}

class InvalidAttendanceException extends RuntimeException {

    public InvalidAttendanceException(String message) {

        super(message);

    }

}

class Student {

    private final String name;
    private final int age;
    private final double marks;
    private final double attendance;

    public Student(
            String name,
            int age,
            double marks,
            double attendance
    ) {

        validateName(name);
        validateAge(age);
        validateMarks(marks);
        validateAttendance(attendance);

        this.name = name;
        this.age = age;
        this.marks = marks;
        this.attendance = attendance;

    }

    private void validateName(String name) {

        if (name == null || name.isBlank()) {

            throw new InvalidStudentNameException(
                    "Student name cannot be empty."
            );

        }

    }

    private void validateAge(int age) {

        if (age < 16 || age > 100) {

            throw new InvalidStudentAgeException(
                    "Student age must be between 16 and 100."
            );

        }

    }

    private void validateMarks(double marks) {

        if (marks < 0 || marks > 100) {

            throw new InvalidMarksException(
                    "Marks must be between 0 and 100."
            );

        }

    }

    private void validateAttendance(double attendance) {

        if (attendance < 0 || attendance > 100) {

            throw new InvalidAttendanceException(
                    "Attendance must be between 0 and 100."
            );

        }

    }

    public void displayStudent() {

        System.out.println("Student Name : " + name);
        System.out.println("Age          : " + age);
        System.out.println("Marks        : " + marks);
        System.out.println("Attendance   : " + attendance + "%");

    }

}

public class StudentValidation {

    public static void main(String[] args) {

        System.out.println("Program Started");

        try {

            Student student = new Student(
                    "Rahul",
                    20,
                    105.0,
                    82.5
            );

            student.displayStudent();

        }

        catch (InvalidStudentNameException e) {

            System.out.println(
                    "Name Validation Failed : " + e.getMessage()
            );

        }

        catch (InvalidStudentAgeException e) {

            System.out.println(
                    "Age Validation Failed : " + e.getMessage()
            );

        }

        catch (InvalidMarksException e) {

            System.out.println(
                    "Marks Validation Failed : " + e.getMessage()
            );

        }

        catch (InvalidAttendanceException e) {

            System.out.println(
                    "Attendance Validation Failed : " + e.getMessage()
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
 * Marks Validation Failed : Marks must be between 0 and 100.
 * Program Finished
 * ============================================================================
 */

/*
 * ============================================================================
 * Explanation:
 * ============================================================================
 *
 * 1. Four custom unchecked exceptions represent different validation
 *    failures:
 *
 *      InvalidStudentNameException
 *      InvalidStudentAgeException
 *      InvalidMarksException
 *      InvalidAttendanceException
 *
 * 2. Each custom exception extends RuntimeException.
 *
 * 3. The Student constructor receives the student's details.
 *
 * 4. Before assigning values to the object fields, the constructor validates
 *    every value.
 *
 * 5. validateName() ensures that the student's name is neither null nor
 *    blank.
 *
 * 6. validateAge() ensures that age is between 16 and 100.
 *
 * 7. validateMarks() ensures that marks are between 0 and 100.
 *
 * 8. validateAttendance() ensures that attendance is between 0 and 100.
 *
 * 9. The program attempts to create:
 *
 *      Student("Rahul", 20, 105.0, 82.5)
 *
 * 10. The name passes validation.
 *
 * 11. The age passes validation.
 *
 * 12. The marks value 105.0 fails validation because it exceeds 100.
 *
 * 13. InvalidMarksException is immediately thrown.
 *
 * 14. Constructor execution stops at that point.
 *
 * 15. validateAttendance() is never called.
 *
 * 16. The field assignments are also never reached, so a valid Student
 *     object is not successfully constructed.
 *
 * 17. Control transfers to the matching InvalidMarksException catch block.
 *
 * 18. The validation error is displayed and the program continues after the
 *     catch blocks.
 * ============================================================================
 */

/*
 * ============================================================================
 * Key Points:
 * ============================================================================
 *
 * ✓ Validate object data before storing it.
 *
 * ✓ A constructor can throw an exception when supplied data is invalid.
 *
 * ✓ If a constructor terminates by throwing an exception, object creation
 *   does not complete successfully.
 *
 * ✓ Separate custom exceptions make different validation failures easier to
 *   identify and handle.
 *
 * ✓ Validation methods keep the constructor cleaner and separate individual
 *   validation responsibilities.
 *
 * ✓ RuntimeException-based custom exceptions do not require throws
 *   declarations.
 *
 * ✓ Validation stops at the first exception that is thrown.
 *
 * ✓ Exception Handling reports the failure, while conditional logic detects
 *   which business rule was violated.
 * ============================================================================
 */
