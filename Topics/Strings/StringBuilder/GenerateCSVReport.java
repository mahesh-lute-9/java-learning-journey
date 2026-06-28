
/*
Build a CSV Report using StringBuilder
Problem Statement

Write a Java program to generate a student report in CSV format using StringBuilder.

Code
/*
------------------------------------------------------------
Program 11 : Build a CSV Report using StringBuilder
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐⭐ Advanced

Concepts Covered
✔ StringBuilder
✔ append()
✔ Loop
✔ CSV Generation

Expected Time : 15 Minutes
------------------------------------------------------------
*/

public class GenerateCSVReport {

    public static void main(String[] args) {

        String[][] students = {
                {"101", "Mahesh", "89"},
                {"102", "Rahul", "92"},
                {"103", "Priya", "95"},
                {"104", "Sneha", "88"}
        };

        StringBuilder csvReport = new StringBuilder();

        csvReport.append("ID,Name,Marks\n");

        for (String[] student : students) {

            csvReport.append(student[0]).append(",")
                     .append(student[1]).append(",")
                     .append(student[2]).append("\n");

        }

        System.out.println(csvReport);

    }

}

/*Output:
	ID,Name,Marks
	101,Mahesh,89
	102,Rahul,92
	103,Priya,95
	104,Sneha,88

	
Explanation:

	First, we create a two-dimensional array containing student details.

	String[][] students = {
    			{"101", "Mahesh", "89"},
    			{"102", "Rahul", "92"},
    			{"103", "Priya", "95"},
   			{"104", "Sneha", "88"}
			};

	Each row represents one student.

	Next, we create a StringBuilder.

	StringBuilder csvReport = new StringBuilder();

	We first add the CSV header.

	csvReport.append("ID,Name,Marks\n");

	Using a for-each loop, we process one student at a time.

	for (String[] student : students)

	Each student's details are appended to the report.

	csvReport.append(student[0]).append(",")
         	.append(student[1]).append(",")
         	.append(student[2]).append("\n");
	Commas (,) separate the columns.
	\n moves to the next row.

	Finally, the complete CSV report is printed.


Use Cases:
	-Exporting data to Excel.
	-Student management systems.
	-Employee reports.
	-Banking transaction reports.
	-Sales reports.
	-Inventory management systems.
*/
