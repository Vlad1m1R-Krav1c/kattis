 
import java.util.Scanner;

 
public class ProblemAMain {

	public static void main(String[] args) {
		readFromSysInAndStartSolving();
	}
	
	/** Reads input data, creates new Solver and starts solving the problem */
	public static void readFromSysInAndStartSolving() {
		
		Scanner scanner = new Scanner(System.in);
		
		String[] stringLineElement=new String[0];
		
		while(scanner.hasNextLine()) {
			 
			String rawLine = scanner.nextLine();
			
			if(rawLine.equals("EXIT")) {
				scanner.close();
				break;
			}
			stringLineElement=rawLine.split(" ");
 			if(stringLineElement.length==2) {
 				Solver solver=new Solver((int)Integer.parseInt(stringLineElement[0]),(int)Integer.parseInt(stringLineElement[1]));
 				solver.solve();
 				break;
 			}
			}
		}
	}
