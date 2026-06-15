
//Labeled continue(Advanced)


class Demo{

	public static void main(String[] args){
outer:
		
		for(int i = 1; i <= 3; i++){
		
			for(int j = 1; j <= 3; j++){
		
				if(j == 2)
					continue outer;

				System.out.println(i + " " + j);
			}
		}
	}
}


/* Output:
 * 	1 1
	2 1
	3 1
 *
 * 	When j == 2, control jumps directly to the next iteration of the outer loops.
 *
 *	* Where we can use break and continue keyword
 *
 * 	Control Statement     break     continue
 * 	for                   yes       yes
 * 	while		      yes	yes
 * 	do-while 	      yes	yes
 * 	switch		      yes 	no
 * 	if-else 	      no 	no
 * */
