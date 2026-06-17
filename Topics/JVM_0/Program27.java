

class Demo{

	Demo(){
	
		System.out.println("In Constructor");
	}

	void main(){
	
		int x = 10;
		int y = 20;

		
		System.out.println("In main method");
		System.out.println(x+y);
	}
}



/* Output: This code is compiled an ran using JDK 25
 *
 * 	aspirmahesh@SayMyName:~/Java/DailyCodes/JVM_0$ sudo update-alternatives --config javac
[sudo] password for aspirmahesh:
There are 5 choices for the alternative javac (providing /usr/bin/javac).

  Selection    Path                                          Priority   Status
------------------------------------------------------------
  0            /usr/lib/jvm/java-25-openjdk-amd64/bin/javac   2511      auto mode
  1            /usr/lib/jvm/java-11-openjdk-amd64/bin/javac   1111      manual mode
  2            /usr/lib/jvm/java-17-openjdk-amd64/bin/javac   1711      manual mode
  3            /usr/lib/jvm/java-21-openjdk-amd64/bin/javac   2111      manual mode
  4            /usr/lib/jvm/java-25-openjdk-amd64/bin/javac   2511      manual mode
* 5            /usr/lib/jvm/java-8-openjdk-amd64/bin/javac    1081      manual mode

Press <enter> to keep the current choice[*], or type selection number: 0
update-alternatives: using /usr/lib/jvm/java-25-openjdk-amd64/bin/javac to provide /usr/bin/javac (javac) in auto mode
aspirmahesh@SayMyName:~/Java/DailyCodes/JVM_0$ sudo update-alternatives --config java
There are 5 choices for the alternative java (providing /usr/bin/java).

  Selection    Path                                            Priority   Status
------------------------------------------------------------
  0            /usr/lib/jvm/java-25-openjdk-amd64/bin/java      2511      auto mode
  1            /usr/lib/jvm/java-11-openjdk-amd64/bin/java      1111      manual mode
  2            /usr/lib/jvm/java-17-openjdk-amd64/bin/java      1711      manual mode
  3            /usr/lib/jvm/java-21-openjdk-amd64/bin/java      2111      manual mode
  4            /usr/lib/jvm/java-25-openjdk-amd64/bin/java      2511      manual mode
* 5            /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java   1081      manual mode

Press <enter> to keep the current choice[*], or type selection number: 0
update-alternatives: using /usr/lib/jvm/java-25-openjdk-amd64/bin/java to provide /usr/bin/java (java) in auto mode
aspirmahesh@SayMyName:~/Java/DailyCodes/JVM_0$ javac Program27.java
aspirmahesh@SayMyName:~/Java/DailyCodes/JVM_0$ java Demo
In Constructor
In main method
30
 *
 *
 * */
