

class Demo{

	public static void main(String[] args){
	
		int x = 10;
		int y = 20;

		System.out.println("In main Method");
		System.out.println(x+y);
	}
}


/* Output :
 *
 * 	aspirmahesh@SayMyName:~/Java/DailyCodes/JVM_0$ vim Program25.java
aspirmahesh@SayMyName:~/Java/DailyCodes/JVM_0$ sudo update-alternatives --config javac
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

Press <enter> to keep the current choice[*], or type selection number: 1
update-alternatives: using /usr/lib/jvm/java-11-openjdk-amd64/bin/javac to provide /usr/bin/javac (javac) in manual mode
aspirmahesh@SayMyName:~/Java/DailyCodes/JVM_0$ javac Program25.java
aspirmahesh@SayMyName:~/Java/DailyCodes/JVM_0$ java Demo
Error: A JNI error has occurred, please check your installation and try again
Exception in thread "main" java.lang.UnsupportedClassVersionError: Demo has been compiled by a more recent version of the Java Runtime (class file version 55.0), this version of the Java Runtime only recognizes class file versions up to 52.0
        at java.lang.ClassLoader.defineClass1(Native Method)
        at java.lang.ClassLoader.defineClass(ClassLoader.java:756)
        at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
        at java.net.URLClassLoader.defineClass(URLClassLoader.java:473)
        at java.net.URLClassLoader.access$100(URLClassLoader.java:74)
        at java.net.URLClassLoader$1.run(URLClassLoader.java:369)
        at java.net.URLClassLoader$1.run(URLClassLoader.java:363)
        at java.security.AccessController.doPrivileged(Native Method)
        at java.net.URLClassLoader.findClass(URLClassLoader.java:362)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:418)
        at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:352)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:351)
        at sun.launcher.LauncherHelper.checkAndLoadMain(LauncherHelper.java:621)
 *
 *
 **/
