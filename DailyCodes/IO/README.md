
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


    
    4. OutputStream

    Writing bytes:

        FileOutputStream fos = new FileOutputStream("abc.txt");

        String s = "Hello Java";

        fos.write(s.getBytes());

        fos.close();

    File:

        Hello Java


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



    15. Byte vs Character Streams

        Byte Stream	Character Stream
        InputStream	Reader
        OutputStream	Writer
        Binary Data	Text Data
        Images	Text Files
        PDF	CSV
        Video	JSON


    
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
