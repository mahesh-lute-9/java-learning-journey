

class Demo{


}


/* Bytecode :
 * 	Compiled from "Program21.java"
class Demo {
  Demo();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return
}


 *
 * Explanation :
 * 	The line : 
 * 		Demo();
 *
 * 	means the compiler generated a constructor for the class. Since you did not write any constructor, Java provides one automatically.
 * 	This constructor is called the default constructor.
 * 	When a Demo object is created, it first calls the constructor od it's parent class, which is Object.
 *
 * 	Even though you did not write extends Object, it is happening automatically.
 *
 * *
