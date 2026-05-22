

class Demo{

	public static void main(String[] agrs){
	
		System.out.println("Hello World!");
	}
}


/* Output :
 *
 * 	Error: A JNI error has occurred, please check your installation and try again
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
 * Explanation :
 *	This is a Java version mismatch problem.
 *
 *	Your class was compiled with a newer JDK(class file version 55.0 = java 11), but you're
 *	trying to run it using an older JRE(which supports only upto 52.0 = java 8). The older runtime doesn't
 *	understand the newer bytecode format, so it throws UnsupportedClassVersionError.
 *
 *	In simple Terms: you build it with a modern engine and tried to run it on older machine.
 *	Compile and run with the same Java version is best case. If you have Your Compiler of smaller version
 *	and the JVM version major it is also fine. JVM can understand older version bytecode.
 *
 *
 * */
