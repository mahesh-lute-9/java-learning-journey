

/*Program 07 : Parse CSV Data using StringTokenizer

## Problem Statement

Write a Java program to parse a CSV (Comma-Separated Values) record using `StringTokenizer` and display the extracted fields.
/*
------------------------------------------------------------
Program 07 : Parse CSV Data using StringTokenizer

Language    : Java
Difficulty  : ⭐⭐⭐ Intermediate

Concepts Covered
✔ StringTokenizer
✔ CSV Parsing
✔ Custom Delimiter
✔ nextToken()

Expected Time : 20 Minutes
------------------------------------------------------------
*/

import java.util.StringTokenizer;

public class ParseCSVData {

    public static void main(String[] args) {

        String csvRecord = "101,Rahul,Computer Science,85000,Pune";

        StringTokenizer tokenizer =
                new StringTokenizer(csvRecord, ",");

        String employeeId = tokenizer.nextToken();
        String employeeName = tokenizer.nextToken();
        String department = tokenizer.nextToken();
        String salary = tokenizer.nextToken();
        String city = tokenizer.nextToken();

        System.out.println("Employee Details");
        System.out.println("----------------------------");

        System.out.println("Employee ID : " + employeeId);
        System.out.println("Name        : " + employeeName);
        System.out.println("Department  : " + department);
        System.out.println("Salary      : " + salary);
        System.out.println("City        : " + city);

    }

}

/*
------------------------------------------------------------
Output:

	Employee Details
 ----------------------------

	Employee ID : 101

	Name        : Rahul

	Department  : Computer Science

	Salary      : 85000

	City        : Pune

------------------------------------------------------------
Memory Diagram:

		CSV Record

+------------------------------------------------------+
| 101,Rahul,Computer Science,85000,Pune               |
+------------------------------------------------------+

		Delimiter

		     	,

                    │
                    ▼

            StringTokenizer

+------+--------+-------------------+-------+------+
| 101  | Rahul  | Computer Science  | 85000 | Pune |
+------+--------+-------------------+-------+------+

------------------------------------------------------------
Explanation:

	A CSV record stores multiple values separated by commas.

First,

	we create a String containing the CSV data.

	String csvRecord = "101,Rahul,Computer Science,85000,Pune";

	A StringTokenizer object is created using a comma as the delimiter.

	Each call to nextToken() returns the next value from the CSV record.

	The values are stored in individual variables such as,

	employeeId

	employeeName

	department

	salary

	and

	city.

Finally,

	all extracted values are displayed in a well-formatted manner.

------------------------------------------------------------
Interview Notes:

	CSV stands for Comma-Separated Values.

	It is one of the most widely used file formats for exchanging structured data.

	Although StringTokenizer can parse simple CSV records,

	it cannot correctly handle complex CSV files containing quoted fields such as

	101,"Rahul Sharma",Pune

	For such cases,

	dedicated CSV libraries should be used.

------------------------------------------------------------
Important Points:

	✔ CSV values are separated by commas.

	✔ StringTokenizer is suitable for simple CSV parsing.

	✔ nextToken() retrieves values sequentially.

	✔ Complex CSV files require specialized libraries.

------------------------------------------------------------
Common Mistakes:

❌ Assuming StringTokenizer supports quoted
CSV values.

❌ Calling nextToken() more times than the
available fields.

❌ Forgetting to specify the comma delimiter.

------------------------------------------------------------
Follow-up Interview Questions

	1. What is a CSV file?

	2. Why is StringTokenizer suitable only for simple CSV parsing?

	3. Which delimiter is used in CSV files?

	4. What happens if a CSV record contains quoted commas?

	5. Which Java libraries are commonly used for advanced CSV parsing?

------------------------------------------------------------
Real World Use Cases:

	✔ Employee Records

	✔ Student Records

	✔ Sales Reports

	✔ Data Migration

	✔ Import and Export Utilities

	✔ Reporting Applications

------------------------------------------------------------
Practice Questions

1.

Parse the following CSV record.

102,Priya,Information Technology,92000,Mumbai

------------------------------------------------------------

2.

Read a CSV record from the user and display
each field separately.

------------------------------------------------------------

3.

Count the total number of fields in a CSV
record.

------------------------------------------------------------

4.

Modify the program to parse student details
instead of employee details.

------------------------------------------------------------
*/
