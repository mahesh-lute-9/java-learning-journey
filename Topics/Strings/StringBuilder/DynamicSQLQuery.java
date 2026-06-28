/*
Build a Dynamic SQL Query using StringBuilder
Problem Statement

Write a Java program to build a dynamic SQL query using StringBuilder.

Code
/*
------------------------------------------------------------
Program 12 : Build a Dynamic SQL Query using StringBuilder
Author      : Mahesh Lute
Language    : Java
Difficulty  : ⭐⭐⭐⭐ Advanced

Concepts Covered
✔ StringBuilder
✔ append()
✔ Dynamic Query Building
✔ Conditional Statements

Expected Time : 15 Minutes
------------------------------------------------------------
*/

public class DynamicSQLQuery {

    public static void main(String[] args) {

        boolean filterByDepartment = true;
        boolean filterBySalary = true;

        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM employees WHERE 1=1");

        if (filterByDepartment) {
            query.append(" AND department = 'IT'");
        }

        if (filterBySalary) {
            query.append(" AND salary >= 50000");
        }

        query.append(" ORDER BY employee_name");

        System.out.println("Generated SQL Query:");
        System.out.println(query);

    }

}

/*Output:
	Generated SQL Query:

	SELECT * FROM employees WHERE 1=1
	AND department = 'IT'
	AND salary >= 50000
	ORDER BY employee_name

Explanation:

	First, we create two boolean variables.

	boolean filterByDepartment = true;
	boolean filterBySalary = true;

	These variables decide whether a particular condition should be added to the SQL query.

	Next, we create an empty StringBuilder.

	StringBuilder query = new StringBuilder();

	We start building the SQL query.

	query.append("SELECT * FROM employees WHERE 1=1");

	The condition 1=1 is always true.

	It is commonly used so that additional conditions can be appended easily using AND without worrying about whether it is the first condition.

	If the department filter is enabled,

	query.append(" AND department = 'IT'");

	is added.

	Similarly, if the salary filter is enabled,

	query.append(" AND salary >= 50000");

	is appended.

	Finally, the query is sorted by employee name.

	query.append(" ORDER BY employee_name");

	The complete SQL query is then printed.


Use Cases:
	-Employee Management Systems.
	-Banking applications.
	-E-commerce product search.
	-Student Management Systems.
	-Dynamic search filters.
	-Report generation.
⚠️ 
Important Note:

This example demonstrates how to use StringBuilder to build a query.

In real-world Java applications, you should use PreparedStatement instead of concatenating user input into SQL queries.

Example:

	String sql = "SELECT * FROM employees WHERE department = ?";

	PreparedStatement preparedStatement =
        	connection.prepareStatement(sql);

	preparedStatement.setString(1, "IT");


PreparedStatement helps:

-Prevent SQL Injection.
-Improve security.
-Improve performance by allowing query reuse.
*/
