
//5. Read Multiple Lines Untill "exit"


import java.io.*;

class Demo{
	
	public static void main(String[] args)throws IOException{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line;

		while(true){
			
			line = br.readLine();

			if(line.equals("exit")){
				
				break;
			}

			System.out.println("You types: " + line);
		}
	}
}
