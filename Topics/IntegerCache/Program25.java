
//IntegerCache size change

class Demo{

	public static void main(String[] args){
	
		Integer x = 128;

		Integer y = 128;

		System.out.println(x == y);
	}
}

/* Output:
 * 	true
 *
 * 	java -XX:AutoBoxCacheMax=200 Demo (JVM option)
 *
 * 	in our code, both variables x and y are declared as Integer objects and assigned the value 128. Normally, without any JVM tuning, this
 * 	value lies outside the default Integer Cache range(-128 to 127). Because of that, Java creates two separate objects, and x == y evalueate to false.
 *
 * 	However, in this case, we're runnig the program with JVM oprion
 *
 * 	java -XX:AutoBoxCacheMax=200 : This option extends the upper limit of the Integer Cache the default 127 to 200. So now the cache range becomes
 * 	-128 to 200
 *
 * 	java -Djava.lang.Integer.IntegerCache.high=500 (System Property) changes system wise limit of IntegerCache
 *	
 *	This tells Java: "Extend the cache up to 500" So now the cache range becomes -128 to 500.
 *
 *
 *
 * */
