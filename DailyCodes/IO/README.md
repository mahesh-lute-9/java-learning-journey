
Java I/O (Input/Output) is much more than just Scanner and System.out.println(). It's an entire ecosystem for handling:


    1.  Console input/output
    2.  File reading/writing
    3.  Buffered I/O
    4.  Byte streams
    5.  Character streams
    6.  Object serialization
    7.  Random access files
    8.  Modern NIO (New I/O)
    9.  Memory-mapped files
    10. Channels and Buffers


    1. What is I/O in Java?

        I/O means moving data:

        User --> Program      (Input)
        Program --> Screen    (Output)

        File --> Program      (Input)
        Program --> File      (Output)

        Network --> Program   (Input)
        Program --> Network   (Output)

        Java abstracts all these as Streams.

-------------------------------------------------------------------------------------------------
    2. Stream Concept

        A stream is simply:

        A sequence of data flowing from source to destination.

    Example:

        File ---------> Java Program
            Input Stream

        Java Program ---------> File
            Output Stream

        Java divides streams into:

                  Streams
                 /      \
        Byte Streams   Character Streams

-------------------------------------------------------------------------------------------------
    3. Byte Streams

    Used for:

        Images
        PDFs
        Videos
        Audio
        Binary files

    Base classes:

        InputStream
        OutputStream

    Hierarchy:

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

    FileInputStream Example

    Suppose:

        abc.txt

            Hello

    Code:

        FileInputStream fis = new FileInputStream("abc.txt");

        int ch;

        while((ch = fis.read()) != -1){
            System.out.print((char)ch);
        }

        fis.close();

    Output:

        Hello

    Important: Why int?
        
        int ch = fis.read();

    Because:

        read()

    returns:

        0 to 255 -> byte data

        -1 -> EOF (End Of File)

    That's why:

        while((ch = fis.read()) != -1)

        is extremely common.

-------------------------------------------------------------------------------------------------
    
    4. OutputStream

    Writing bytes:

        FileOutputStream fos = new FileOutputStream("abc.txt");

        String s = "Hello Java";

        fos.write(s.getBytes());

        fos.close();

    File:

        Hello Java

-------------------------------------------------------------------------------------------------

    5. Character Streams

    Byte streams are not good for text.

    For text:

        Reader
        Writer

    Hierarchy:

        Reader
            |
            +-- FileReader
            +-- BufferedReader

        Writer
            |
            +-- FileWriter
            +-- BufferedWriter
            +-- PrintWriter

    FileReader Example
        
        FileReader fr = new FileReader("abc.txt");

        int ch;

        while((ch=fr.read())!=-1){

            System.out.print((char)ch);
        }

        fr.close();

    Output:

        Hello Java
        
    FileWriter Example
     
       FileWriter fw = new FileWriter("abc.txt");

        fw.write("Welcome");

        fw.close();

    File:
        
        Welcome
-------------------------------------------------------------------------------------------------

    6. Buffered Streams

        This is where interviews start asking deeper questions.

        Without buffering:

            read()
            read()
            read()
            read()

    Every call:

        Java -> OS -> Disk

        which is slow.

    Buffered streams:

        BufferedInputStream

        BufferedOutputStream

        BufferedReader

        BufferedWriter

        They keep data in memory:

        Disk --> Buffer --> Program

        Fewer disk accesses.

        Much faster.

    BufferedReader Example:

        BufferedReader br = new BufferedReader(new FileReader("abc.txt"));

        String line;

        while((line=br.readLine())!=null){

            System.out.println(line);
        }

        br.close();

    Suppose file:

        Java
        Python
        C++

    Output:

        Java
        Python
        C++

-------------------------------------------------------------------------------------------------
    
    7. Why BufferedReader was preferred over Scanner

    People often ask:

        Scanner sc = new Scanner(System.in);

        or

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
    Scanner Pros:

        nextInt()
        nextDouble()
        next()

        Easy parsing.

    Cons:

        Slower
        Uses regex internally
        
    BufferedReader Pros:

        Very fast
        Competitive programming favorite

    Cons:

        Need manual conversion:

        int x = Integer.parseInt(br.readLine());

-------------------------------------------------------------------------------------------------

    8. System.in

        This is interesting.

        System.in is actually:

        InputStream

    That's why:

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Here:

        System.in

            ↓

        InputStreamReader

    (Byte -> Character conversion)

            ↓

        BufferedReader

    This is called Stream Chaining.

-------------------------------------------------------------------------------------------------

    9. Stream Chaining

    One stream wrapping another.

    Example:

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("abc.txt")));

    Meaning:

        FileInputStream

            ↓

        InputStreamReader

            ↓

        BufferedReader

    Each layer adds functionality.

--------------------------------------------------------------------------------------------------

    10. Serialization

    One of Java's most important I/O features.

    Serialization means:

        Convert an object into bytes so it can be stored or transferred.

    Example:

        class Student implements Serializable{

            int id;
            String name;
        }

    Writing object:

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"));

        oos.writeObject(student);

    Reading object:

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.dat"));

        Student s = (Student)ois.readObject();

    transient Keyword

    Suppose:

        class User implements Serializable{

            String username;

            transient String password;
        }

    During serialization:

        username -> saved

        password -> ignored

    Useful for:

            Passwords
            OTPs
            Security tokens
--------------------------------------------------------------------------------------------------

    11. PrintWriter

        A very convenient output class.

    Instead of:

        fw.write("Hello");

    You can do:

        PrintWriter pw = new PrintWriter("abc.txt");

        pw.println("Hello");

        pw.println(100);

        pw.println(true);

        pw.close();

    Output:

        Hello
        100
        true

-------------------------------------------------------------------------------------------------
    12. RandomAccessFile

        This is extremely interesting.

    You can:

        Read anywhere
        Write anywhere
        Jump to positions

    Example:

        RandomAccessFile raf = new RandomAccessFile("abc.txt","rw");

        raf.seek(10);

        raf.writeBytes("JAVA");

    Moves cursor to:

        position = 10

        and writes there.

------------------------------------------------------------------------------------------------

    13. NIO (New I/O)

        Modern Java uses:

            java.nio

    Important classes:

        Path
        Files
        Channel
        Buffer

    Creating a Path:

        Path p = Path.of("abc.txt");

    Reading file:

        String s = Files.readString(p);

        System.out.println(s);

    Writing:

        Files.writeString(p,"Hello World");

        Simple and elegant.


-----------------------------------------------------------------------------------------------
    
    14. Files class (Very Important)
            
            Files.exists(path)

            Files.copy()

            Files.move()

            Files.delete()

            Files.readAllLines()

            Files.readString()

            Files.writeString()

    Example:

        Path p = Path.of("abc.txt");

            if(Files.exists(p)){

                System.out.println("Found");
            }

-------------------------------------------------------------------------------------------------

    15. Byte vs Character Streams

        Byte Stream	Character Stream
        InputStream	Reader
        OutputStream	Writer
        Binary Data	Text Data
        Images	Text Files
        PDF	CSV
        Video	JSON

------------------------------------------------------------------------------------------------
    
    16. Complete Hierarchy to Remember
    
                      Object
    
                        |

                    InputStream
            /            |           \

FileInputStream BufferedInputStream ObjectInputStream

                            

                        OutputStream
                /             |          \
        
    FileOutputStream    BufferedOutputStream    ObjectOutputStream


                Reader

         1. FileReader
         2. BufferedReader


               Writer

          1. FileWriter
          2. BufferedWriter
          3. PrintWriter

-------------------------------------------------------------------------------------------------
    
    17. The Modern Recommendation

        In modern Java (Java 8+ and especially Java 11+):

            Task	            Recommended API
        Console Input	        Scanner / BufferedReader
        Read Text File	        Files.readString()
        Write Text File	        Files.writeString()
        Read Large File	        BufferedReader
        Binary File	            FileInputStream
        Object Storage	        ObjectOutputStream
        File Operations 	    Files API
        High Performance I/O	NIO Channels + Buffers
        

    Mental Model for Java I/O

    Remember this progression:

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
            Object Streams (Serialization)
                ↓
            Random Access File
                ↓
            NIO (Path, Files)
                ↓
            Channels + Buffers


----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    INTERVIEW QUESTIONS ON INPUT OUTPUT IN JAVA WITH ANSWERS---


    Here are some **top Java I/O interview questions** frequently asked in product-based companies and large organizations. I've included
    concise but interview-ready answers.

# 1. What is I/O in Java?

**Answer:**

I/O stands for **Input and Output**.

It is the mechanism through which Java programs:

* Read data from keyboard, files, network, etc.
* Write data to console, files, network, etc.

Java provides the `java.io` package for traditional I/O and `java.nio` for modern I/O operations.

---

# 2. What is a Stream in Java?

**Answer:**

A stream is a sequence of data flowing between:

```text
Source ------> Destination
```

Examples:

* File → Program
* Keyboard → Program
* Program → File

Streams are categorized into:

1. Byte Streams
2. Character Streams

---

# 3. Difference between Byte Stream and Character Stream?

| Byte Stream    | Character Stream   |
| -------------- | ------------------ |
| Handles bytes  | Handles characters |
| Binary data    | Text data          |
| `InputStream`  | `Reader`           |
| `OutputStream` | `Writer`           |
| Images, PDFs   | Text files         |

---

# 4. Why does `read()` return int instead of byte?

**Answer:**

Because:

```java
int ch = fis.read();
```

`read()` returns:

```text
0 to 255 -> valid byte

-1 -> End of File (EOF)
```

A `byte` cannot represent `-1` and all byte values simultaneously.

Therefore Java uses `int`.

---

# 5. Difference between Scanner and BufferedReader?

**Answer:**

| Scanner                    | BufferedReader    |
| -------------------------- | ----------------- |
| Slower                     | Faster            |
| Parses primitives directly | Reads only String |
| Uses regex internally      | No regex overhead |
| Easy syntax                | Manual conversion |

Example:

```java
int x = sc.nextInt();
```

vs

```java
int x =
Integer.parseInt(br.readLine());
```

---

# 6. Why is BufferedReader faster than Scanner?

**Answer:**

Two reasons:

### Scanner

* Uses regex
* Tokenizes input
* Additional parsing overhead

### BufferedReader

* Reads large chunks into memory
* Simply returns strings

Hence:

```text
BufferedReader > Scanner
```

in terms of speed.

---

# 7. Explain Stream Chaining.

**Answer:**

Wrapping one stream inside another.

Example:

```java
BufferedReader br =
new BufferedReader(

new InputStreamReader(

System.in

));

```

Hierarchy:

```text
System.in

↓

InputStreamReader

↓

BufferedReader
```

Each wrapper adds functionality.

---

# 8. What is InputStreamReader?

**Answer:**

It converts:

```text
Byte Stream

↓

Character Stream
```

Example:

```java
new InputStreamReader(System.in)
```

Because:

```java
System.in
```

is an InputStream.

---

# 9. Difference between FileInputStream and FileReader?

| FileInputStream     | FileReader       |
| ------------------- | ---------------- |
| Reads bytes         | Reads characters |
| Binary files        | Text files       |
| Images              | txt files        |
| Extends InputStream | Extends Reader   |

---

# 10. Difference between FileWriter and BufferedWriter?

**FileWriter**

Writes directly:

```java
fw.write("Hello");
```

---

**BufferedWriter**

Stores data in buffer:

```java
bw.write("Hello");
```

and writes in bulk.

Faster.

---

# 11. What is Buffering?

**Answer:**

Buffering means:

> Temporarily storing data in memory before reading or writing.

Without buffering:

```text
Program -> Disk

Program -> Disk

Program -> Disk
```

Many expensive operations.

With buffering:

```text
Disk

↓

Buffer

↓

Program
```

Less disk access.

Better performance.

---

# 12. What is Serialization?

**Answer:**

Serialization means:

> Converting an object into a byte stream.

Used for:

* Saving objects to files
* Sending objects over network
* Caching

---

Example:

```java
class Student
implements Serializable{

int id;

String name;

}
```

---

# 13. What is Deserialization?

**Answer:**

Converting byte stream back into object.

```java
ObjectInputStream ois;

Student s =

(Student)ois.readObject();
```

---

# 14. What is Serializable Interface?

**Answer:**

```java
java.io.Serializable
```

It is a:

```text
Marker Interface
```

Meaning:

* No methods
* Only tells JVM that object can be serialized.

---

# 15. What is a Marker Interface?

**Answer:**

An interface with:

```java
0 methods
```

Examples:

```java
Serializable

Cloneable

Remote
```

Used to provide metadata to JVM.

---

# 16. What is transient Keyword?

**Answer:**

A `transient` field is:

> Ignored during serialization.

Example:

```java
class User
implements Serializable{

String username;

transient String password;

}
```

After serialization:

```text
username -> saved

password -> NOT saved
```

---

# 17. What is serialVersionUID?

**Answer:**

Unique version identifier of a Serializable class.

Example:

```java
private static final long

serialVersionUID = 1L;
```

Used to verify:

```text
Serialized object

and

Current class

are compatible
```

---

# 18. What happens if serialVersionUID changes?

**Answer:**

JVM throws:

```text
InvalidClassException
```

because:

```text
Old object version

!=

Current class version
```

---

# 19. Difference between PrintWriter and BufferedWriter?

### BufferedWriter

Only writes text:

```java
bw.write("Hello");
```

---

### PrintWriter

Can print:

```java
pw.println("Hello");

pw.println(10);

pw.println(true);
```

Convenient formatting.

---

# 20. What is RandomAccessFile?

**Answer:**

A file class that allows:

* Read anywhere
* Write anywhere
* Move file pointer

Example:

```java
RandomAccessFile raf

= new RandomAccessFile(
"abc.txt","rw");

raf.seek(100);
```

Moves pointer to:

```text
Byte position = 100
```

---

# 21. Difference between Files and File?

### File

Represents path.

```java
File f = new File("abc.txt");
```

---

### Files

Utility class.

```java
Files.readString()

Files.copy()

Files.move()

Files.delete()
```

---

# 22. What is NIO?

**Answer:**

NIO means:

```text
New Input Output
```

Introduced for:

* Faster I/O
* Non-blocking I/O
* Channels
* Buffers

Package:

```java
java.nio
```

---

# 23. Difference between IO and NIO?

| IO           | NIO           |
| ------------ | ------------- |
| Stream based | Buffer based  |
| Blocking     | Non-blocking  |
| Sequential   | Random access |
| Slower       | Faster        |
| Old API      | Modern API    |

---

# 24. What is a Channel in NIO?

**Answer:**

A Channel is like:

```text
Two-way connection
```

Can:

```text
Read

Write
```

Examples:

```java
FileChannel

SocketChannel

DatagramChannel
```

---

# 25. What is try-with-resources?

One of the most asked interview questions.

Before Java 7:

```java
FileInputStream fis
= new FileInputStream("a.txt");

try{

}

finally{

fis.close();

}
```

---

After Java 7:

```java
try(FileInputStream fis

= new FileInputStream("a.txt")){

// use file

}
```

JVM automatically closes:

```text
fis.close()
```

---

# 26. Which is preferred today: Scanner, BufferedReader, or Files?

**Interview Answer:**

* Console input → Scanner
* Competitive programming → BufferedReader
* Small text file → `Files.readString()`
* Large file → BufferedReader
* Binary file → FileInputStream
* Modern applications → NIO (`Files`, `Path`, `Channels`)

---

# 27. Why should streams be closed?

**Answer:**

If streams are not closed:

* Memory leaks
* File locks remain
* OS resources are wasted

Use:

```java
try-with-resources
```

because it closes resources automatically.

---

## FAANG/Big Tech Favorite Questions

The questions most commonly asked at large companies are:

1. Why does `read()` return int?
2. Scanner vs BufferedReader?
3. FileInputStream vs FileReader?
4. What is Stream Chaining?
5. Serialization vs Deserialization?
6. Why is Serializable a Marker Interface?
7. transient keyword?
8. serialVersionUID?
9. IO vs NIO?
10. What is try-with-resources?
11. Buffered streams internally how do they improve performance?
12. Files API vs File class?

