# 🚀 Java I/O (Input / Output)

Java I/O is much more than just `Scanner` and `System.out.println()`. It is a complete framework for handling:

* Console Input/Output
* File Reading/Writing
* Buffered I/O
* Byte Streams
* Character Streams
* Serialization
* Random Access Files
* NIO (New I/O)
* Channels and Buffers

---

# 1. What is I/O in Java?

I/O means moving data from one place to another.

```text
User ------> Program      (Input)

Program ---> Screen       (Output)


File ------> Program      (Input)

Program ---> File         (Output)


Network ---> Program      (Input)

Program ---> Network      (Output)
```

Java abstracts all these operations using **Streams**.

---

# 2. Stream Concept

A Stream is:

> A sequence of data flowing from source to destination.

Example:

```text
File ------------> Java Program

      Input Stream


Java Program ------------> File

      Output Stream
```

Java divides streams into:

```text
             Streams

            /       \

     Byte Streams    Character Streams
```

---

# 3. Byte Streams

Byte streams are used for:

* Images
* PDFs
* Audio Files
* Videos
* Binary Files

Base Classes:

```java
InputStream

OutputStream
```

Hierarchy:

```text
InputStream

   |

   +-- FileInputStream

   +-- BufferedInputStream

   +-- ObjectInputStream



OutputStream

   |

   +-- FileOutputStream

   +-- BufferedOutputStream

   +-- ObjectOutputStream
```

---

## FileInputStream Example

Suppose:

```text
abc.txt

Hello
```

Code:

```java
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        FileInputStream fis =

            new FileInputStream("abc.txt");

        int ch;

        while((ch = fis.read()) != -1){

            System.out.print((char)ch);

        }

        fis.close();

    }

}
```

Output:

```text
Hello
```

---

### Why does read() return int?

```java
int ch = fis.read();
```

Because:

```text
0 to 255  -> Valid Byte

-1        -> End Of File
```

Hence:

```java
while((ch = fis.read()) != -1)
```

is extremely common.

---

# 4. FileOutputStream

Writing bytes:

```java
FileOutputStream fos =

new FileOutputStream("abc.txt");

String s = "Hello Java";

fos.write(s.getBytes());

fos.close();
```

File Content:

```text
Hello Java
```

---

# 5. Character Streams

Byte streams are not ideal for text files.

For text:

```java
Reader

Writer
```

Hierarchy:

```text
Reader

  |

  +-- FileReader

  +-- BufferedReader



Writer

  |

  +-- FileWriter

  +-- BufferedWriter

  +-- PrintWriter
```

---

## FileReader Example

```java
FileReader fr =

new FileReader("abc.txt");

int ch;

while((ch = fr.read()) != -1){

    System.out.print((char)ch);

}

fr.close();
```

Output:

```text
Hello Java
```

---

## FileWriter Example

```java
FileWriter fw =

new FileWriter("abc.txt");

fw.write("Welcome");

fw.close();
```

File:

```text
Welcome
```

---

# 6. Buffered Streams

Without buffering:

```text
read()

read()

read()

read()
```

Every call:

```text
Java -> OS -> Disk
```

This is slow.

---

Buffered Streams:

```java
BufferedInputStream

BufferedOutputStream

BufferedReader

BufferedWriter
```

They use:

```text
Disk

↓

Buffer

↓

Program
```

Less disk access.

More performance.

---

## BufferedReader Example

```java
BufferedReader br =

new BufferedReader(

new FileReader("abc.txt")

);

String line;

while((line = br.readLine()) != null){

    System.out.println(line);

}

br.close();
```

Suppose File:

```text
Java

Python

C++
```

Output:

```text
Java

Python

C++
```

---

# 7. Scanner vs BufferedReader

| Scanner            | BufferedReader          |
| ------------------ | ----------------------- |
| Slower             | Faster                  |
| Uses Regex         | No Regex                |
| nextInt()          | readLine()              |
| Easy Parsing       | Manual Conversion       |
| Good for Beginners | Competitive Programming |

Scanner:

```java
Scanner sc =

new Scanner(System.in);

int x = sc.nextInt();
```

BufferedReader:

```java
BufferedReader br =

new BufferedReader(

new InputStreamReader(System.in)

);

int x =

Integer.parseInt(br.readLine());
```

---

# 8. System.in

This is an important interview question.

```java
System.in
```

is actually:

```java
InputStream
```

That's why:

```java
BufferedReader br =

new BufferedReader(

new InputStreamReader(

System.in

)

);
```

---

# 9. Stream Chaining

Wrapping one stream inside another.

Example:

```java
BufferedReader br =

new BufferedReader(

new InputStreamReader(

new FileInputStream("abc.txt")

)

);
```

Hierarchy:

```text
FileInputStream

      ↓

InputStreamReader

      ↓

BufferedReader
```

Each layer adds functionality.

---

# 10. Serialization

Serialization means:

> Converting an object into bytes.

Example:

```java
class Student

implements Serializable{

    int id;

    String name;

}
```

---

Writing Object

```java
ObjectOutputStream oos =

new ObjectOutputStream(

new FileOutputStream("student.dat")

);

oos.writeObject(student);
```

---

Reading Object

```java
ObjectInputStream ois =

new ObjectInputStream(

new FileInputStream("student.dat")

);

Student s =

(Student)ois.readObject();
```

---

# transient Keyword

```java
class User

implements Serializable{

    String username;

    transient String password;

}
```

During serialization:

```text
username -> Saved

password -> Ignored
```

Useful for:

* Passwords
* OTP
* Security Tokens

---

# 11. PrintWriter

Instead of:

```java
fw.write("Hello");
```

Use:

```java
PrintWriter pw =

new PrintWriter("abc.txt");

pw.println("Hello");

pw.println(100);

pw.println(true);

pw.close();
```

Output:

```text
Hello

100

true
```

---

# 12. RandomAccessFile

Allows:

* Read Anywhere
* Write Anywhere
* Jump To Any Position

Example:

```java
RandomAccessFile raf =

new RandomAccessFile(

"abc.txt",

"rw"

);

raf.seek(10);

raf.writeBytes("JAVA");
```

Moves pointer to:

```text
Position = 10
```

and writes there.

---

# 13. NIO (New I/O)

Modern Java uses:

```java
java.nio
```

Important Classes:

```text
Path

Files

Channel

Buffer
```

---

Create Path

```java
Path p =

Path.of("abc.txt");
```

---

Read File

```java
String s =

Files.readString(p);

System.out.println(s);
```

---

Write File

```java
Files.writeString(

p,

"Hello World"

);
```

Simple and elegant.

---

# 14. Files Class

Most Used Methods:

```java
Files.exists(path);

Files.copy();

Files.move();

Files.delete();

Files.readAllLines();

Files.readString();

Files.writeString();
```

Example:

```java
Path p =

Path.of("abc.txt");

if(Files.exists(p)){

    System.out.println("Found");

}
```

---

# 15. Byte Streams vs Character Streams

| Byte Streams | Character Streams |
| ------------ | ----------------- |
| InputStream  | Reader            |
| OutputStream | Writer            |
| Binary Data  | Text Data         |
| Images       | Text Files        |
| PDF          | CSV               |
| Videos       | JSON              |

---

# 16. Complete Hierarchy

```text
                   Object


                      |


                InputStream

         /             |             \

FileInputStream  BufferedInputStream  ObjectInputStream



                OutputStream

         /             |              \

FileOutputStream BufferedOutputStream ObjectOutputStream



                  Reader

              FileReader

           BufferedReader



                  Writer

             FileWriter

          BufferedWriter

            PrintWriter
```

---

# 17. Modern Recommendation

| Task             | Recommended API          |
| ---------------- | ------------------------ |
| Console Input    | Scanner / BufferedReader |
| Read Text File   | Files.readString()       |
| Write Text File  | Files.writeString()      |
| Large File       | BufferedReader           |
| Binary File      | FileInputStream          |
| Object Storage   | ObjectOutputStream       |
| File Operations  | Files API                |
| High Performance | NIO + Channels           |

---

# Mental Model for Java I/O

```text
Console I/O

      ↓

Streams

      ↓

Byte Streams

      ↓

Character Streams

      ↓

Buffered Streams

      ↓

Object Streams

      ↓

RandomAccessFile

      ↓

NIO

      ↓

Channels + Buffers
```

---

# ============================================================

# 🎯 SDE INTERVIEW QUESTIONS WITH ANSWERS

### Q1. What is I/O in Java?

**Answer:**

I/O stands for Input and Output.

It allows Java programs to:

* Read data
* Write data

Sources:

* Keyboard
* Files
* Network
* Memory

---

### Q2. What is a Stream?

**Answer:**

A Stream is a sequence of data flowing from source to destination.

```text
Source -----> Destination
```

Examples:

```text
File -> Program

Program -> File
```

---

### Q3. Difference Between Byte Stream and Character Stream?

| Byte Stream  | Character Stream |
| ------------ | ---------------- |
| Binary Data  | Text Data        |
| InputStream  | Reader           |
| OutputStream | Writer           |
| Images       | Text Files       |

---

### Q4. Why does read() return int instead of byte?

**Answer**

Because:

```text
0 to 255 -> Valid Byte

-1 -> EOF
```

Byte cannot represent all these values together.

Hence Java returns:

```java
int
```

---

### Q5. Scanner vs BufferedReader?

**Answer**

| Scanner   | BufferedReader |
| --------- | -------------- |
| Slower    | Faster         |
| Regex     | No Regex       |
| nextInt() | readLine()     |
| Easy      | Fast           |

---

### Q6. Why BufferedReader is Faster?

**Answer**

Because:

* Reads large chunks
* Uses internal buffer
* No regex parsing

Hence:

```text
BufferedReader > Scanner
```

---

### Q7. Explain Stream Chaining.

**Answer**

```java
BufferedReader br =

new BufferedReader(

new InputStreamReader(

System.in

));
```

Flow:

```text
System.in

↓

InputStreamReader

↓

BufferedReader
```

---

### Q8. What is Serialization?

**Answer**

Serialization means:

```text
Object -> Byte Stream
```

Used for:

* Saving Objects
* Network Transfer
* Caching

---

### Q9. What is Deserialization?

**Answer**

```text
Byte Stream -> Object
```

Example:

```java
Student s =

(Student)ois.readObject();
```

---

### Q10. What is transient Keyword?

**Answer**

Fields marked:

```java
transient
```

are ignored during serialization.

Example:

```java
transient String password;
```

---

### Q11. What is serialVersionUID?

**Answer**

Unique version id of Serializable class.

```java
private static final long

serialVersionUID = 1L;
```

Used for version checking.

---

### Q12. IO vs NIO?

| IO           | NIO           |
| ------------ | ------------- |
| Stream Based | Buffer Based  |
| Blocking     | Non Blocking  |
| Sequential   | Random Access |
| Slower       | Faster        |
| Old API      | Modern API    |

---

### Q13. What is try-with-resources?

**Answer**

```java
try(

FileInputStream fis =

new FileInputStream("a.txt")

){

}
```

JVM automatically calls:

```java
fis.close();
```

No need for finally block.

---

### Q14. Why should streams be closed?

**Answer**

If not closed:

* Memory leaks
* File locks remain
* OS resources wasted

Preferred:

```java
try-with-resources
```

---

## 🔥 Most Asked FAANG / Product Based Questions

1. Why does read() return int?
2. Scanner vs BufferedReader?
3. FileInputStream vs FileReader?
4. Explain Stream Chaining.
5. Serialization vs Deserialization?
6. Why Serializable is Marker Interface?
7. transient keyword?
8. serialVersionUID?
9. IO vs NIO?
10. What is try-with-resources?
11. Buffered Streams Internals?
12. Files API vs File Class?
