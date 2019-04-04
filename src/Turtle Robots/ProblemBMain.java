import java.util.ArrayList;
import java.util.Scanner;
 
public class ProblemBMain {

	public static void main(String[] args) {
		    
			Solver solver=readFromSysInAndCreateInitialDataForSolver();
			System.out.println(solver.solve());
	}
	
	/** Reads input data and returns new Solver for problem instance */
	public static Solver readFromSysInAndCreateInitialDataForSolver() {
	 	
		Scanner scanner = new Scanner(System.in);
		String[] stringLineElement=new String[0];
		
		int currentY=7;
		boolean boardCreated=false;
		
		ArrayList<String> commands=new ArrayList<>();
		String[][] board=new String[8][8];
		int xDiamond=0;
		int yDiamond=0;
		
		while(scanner.hasNextLine()) {
			 
			String rawLine = scanner.nextLine();
			
			if(rawLine.equals("EXIT")) {
				scanner.close();
				break;
			}
			stringLineElement=rawLine.split(" ");
			
  			if(stringLineElement.length==1) {
  				
  				if(!boardCreated) {
   					for(int i =0;i<8;i++ ) {
   						
						String boardCell =String.valueOf(stringLineElement[0].charAt(i));
						
						if(boardCell.equals("D")) {
							xDiamond=i;
							yDiamond=currentY;
						}
						board[i][currentY]=boardCell;
  					}
  				   if(currentY==0) {
  					   boardCreated=true;
  					   //System.out.println("We have created new board!");
  					   continue;
  				   }
  				   currentY--;
  				}
  				else {
   					/** Adding each command into list */
  					for(int commandTemp=0;commandTemp<stringLineElement[0].length();commandTemp++) {
  						String command=String.valueOf(stringLineElement[0].charAt(commandTemp));
  						commands.add(command);
  					}
  					/*for(String finalCommand : commands) {
  						System.out.println("Command: "+finalCommand);
  					}*/
  					 break;
  				}
  			 }
		 }
		 
		Solver solver=new Solver(board,commands,xDiamond,yDiamond);
		
		return solver;
}
}
