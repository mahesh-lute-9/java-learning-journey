
/*Program 08 : Parse Log File Entries using StringTokenizer

## Problem Statement

Write a Java program to parse a server log entry using `StringTokenizer` and extract the date, time, log level, module, and message.

/*
------------------------------------------------------------
Program 08 : Parse Log File Entries using StringTokenizer

Language    : Java
Difficulty  : ⭐⭐⭐⭐ Advanced

Concepts Covered
✔ StringTokenizer
✔ Log File Parsing
✔ Custom Delimiter
✔ nextToken()

Expected Time : 20 Minutes
------------------------------------------------------------
*/

import java.util.StringTokenizer;

public class ParseLogFile {

    public static void main(String[] args) {

        String logEntry =
                "2026-07-03|10:30:15|INFO|Authentication|User Login Successful";

        StringTokenizer tokenizer =
                new StringTokenizer(logEntry, "|");

        String date = tokenizer.nextToken();
        String time = tokenizer.nextToken();
        String logLevel = tokenizer.nextToken();
        String module = tokenizer.nextToken();
        String message = tokenizer.nextToken();

        System.out.println("Server Log Details");
        System.out.println("------------------------------");

        System.out.println("Date      : " + date);
        System.out.println("Time      : " + time);
        System.out.println("Log Level : " + logLevel);
        System.out.println("Module    : " + module);
        System.out.println("Message   : " + message);

    }

}

/*
------------------------------------------------------------
Output

	Server Log Details
------------------------------

Date      : 2026-07-03
Time      : 10:30:15
Log Level : INFO
Module    : Authentication
Message   : User Login Successful

------------------------------------------------------------
Memory Diagram:

			Log Entry

+--------------------------------------------------------------------------+
|2026-07-03|10:30:15|INFO|Authentication|User Login Successful             |
+--------------------------------------------------------------------------+

			Delimiter

			   |

                     	   │	
                     	   ▼

          	    StringTokenizer

+------------+----------+------+----------------+-------------------------+
|2026-07-03  |10:30:15  |INFO  |Authentication  |User Login Successful    |
+------------+----------+------+----------------+-------------------------+

------------------------------------------------------------
Explanation:

	A server log stores different pieces of information separated by a delimiter.

	In this example,

	the delimiter is |

	A StringTokenizer object is created using the pipe character as the delimiter.

	Each call to nextToken() extracts one field.

	The extracted values are stored in

	date

	time

	logLevel

	module

	and

	message

Finally,

	the information is displayed in a structured format.

------------------------------------------------------------
Interview Notes:

	Log parsing is one of the most common backend tasks.

	Application logs usually contain

	✔ Timestamp

	✔ Log Level

	✔ Module Name

	✔ Message

	While StringTokenizer works well for simple logs,

	modern applications often use JSON logs, XML logs, or dedicated logging frameworks.

------------------------------------------------------------
Important Points:

	✔ Any character can be used as a delimiter.

	✔ StringTokenizer is useful for structured text.

	✔ Tokens are returned sequentially.

	✔ It is suitable for simple parsing tasks.

------------------------------------------------------------
Common Mistakes

❌ Assuming every log file uses the same
delimiter.

❌ Calling nextToken() without checking the
number of available tokens.

❌ Using StringTokenizer for complex log
formats.

------------------------------------------------------------
Follow-up Interview Questions

	1. Why are delimiters used in log files?

	2. Can StringTokenizer parse JSON logs?

	3. What happens if a field is missing?

	4. Which delimiter is used in this example?

	5. Which classes can replace StringTokenizer for advanced parsing?

------------------------------------------------------------
Real World Use Cases

	✔ Server Log Analysis

	✔ Application Monitoring

	✔ Security Auditing

	✔ DevOps Automation

	✔ Backend Development

	✔ Error Tracking Systems

------------------------------------------------------------
Practice Questions

1.

Parse the following log entry.

2026-07-03|14:45:10|ERROR|Database|Connection Failed

------------------------------------------------------------

2.

Modify the program to parse five different
log entries stored in an array.

------------------------------------------------------------

3.

Count the number of fields in each log entry.

------------------------------------------------------------

4.

Replace

|

with

;

and observe the output.

------------------------------------------------------------
*/
