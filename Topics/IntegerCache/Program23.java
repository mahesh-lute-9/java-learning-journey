

class Demo{

	public static void main(String[] args){
	
		int x = 10;	//stack frame

		Integer y = 20;	//Heap-Integer Cache

	}
}


/* Bytecode:
 *
 * Compiled from "Program23.java"
class Demo {
  Demo();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
       8: astore_2
       9: return
}
 *
 * */
