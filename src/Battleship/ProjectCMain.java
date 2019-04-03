package problem_c;

 
import java.util.ArrayList;
import java.util.Scanner;

public class ProjectCMain {

	public static void main(String[] args) {
		 
		ArrayList<Solver> listOfSolvers=readFromSysInAndCreateInitialData();
	 
		for(Solver solver : listOfSolvers) {
 			
 			String solution=solver.solve();
 			System.out.println(solution);
 		}
		 
	}
	/** Reads input data via Scanner and Returns list of Solver instance(s) with initial data */
	public static ArrayList<Solver> readFromSysInAndCreateInitialData() {
		
		Scanner scanner = new Scanner(System.in);
		
		String[] stringLineElement=new String[0];
		int numberOfTests=0;
	    int currentTestNumber=0;
	    
		/** Solver instance data */
		String[][] deploymentMapPlayer1=null;
	    String[][] deploymentMapPlayer2=null;
	    ArrayList<Point> shots=new ArrayList<>();
	    ArrayList<Solver> listOfSolvers=new ArrayList<>();
	    int width=0;
		int height=0;
		int numberOfShots=0;
		
		/** Timer Data */
		long startTimeInitial=0;
		boolean timer=true;
		
		/** Parser data */
		boolean testInfo=true;
		int currentY=0;
		boolean player1MapCreated=false;
		boolean player2MapCreated=false;
		boolean skipYDecrement=false;
		
		while(scanner.hasNextLine()) {
			
			if(timer) {
				startTimeInitial=System.currentTimeMillis(); 
				timer=false;
			}
			
			String rawLine = scanner.nextLine();
			
			if(rawLine.equals("EXIT")) {
				scanner.close();
				break;
			}
			stringLineElement=rawLine.split(" ");
			
			/** Case 1: Happens once for number of test cases and each time for deployment map line representation */
 			if(stringLineElement.length==1) {
 				
 				if(!testInfo) {
 					
 					for(int i =0;i<width;i++ ) {
 						String deploymentData =String.valueOf(stringLineElement[0].charAt(i));
 						
 						if(!player1MapCreated) {
 							 
 							deploymentMapPlayer1[i][currentY]=deploymentData;
 							/* If we finished making Map for player 1 */
 							if(currentY==0 && (i==(width-1))) {
 								player1MapCreated=true;
 								currentY=(height-1);
 								skipYDecrement=true;
 							}
 						 }
 						 else {
 							skipYDecrement=false;
 							deploymentMapPlayer2[i][currentY]=deploymentData;
 						}
					}	
 					if(skipYDecrement&&currentY==(height-1)) {
 						continue;
 					}
 					currentY--;
				}
 				else {
 					 numberOfTests=Integer.parseInt(stringLineElement[0]);
 					 //System.out.println("Number of test cases: "+stringLineElement[0]);
 					 testInfo=false;
	 			}		
			  }
			/** Case 2: Happens each time for new Problem instance, but once. */
 			else if (stringLineElement.length==3) {
 				
					  width =Integer.parseInt(stringLineElement[0]);
					  height =Integer.parseInt(stringLineElement[1]);
					  numberOfShots =Integer.parseInt(stringLineElement[2]);
					  //System.out.println("Problem number: "+currentTestNumber+". Width: "+width+". Height: "+height+". Number of shots: "+numberOfShots);
					  //System.out.println("");
					  currentTestNumber++;
					  currentY=(height-1);
					  /** Re-setting new Solver instance data */
				  	  deploymentMapPlayer1=new String[width][height];
				  	  deploymentMapPlayer2=new String[width][height];
				  	  shots=new ArrayList<>();
				 }
 			/** Case 3: Happens each time for new line with Shot data */
			else if(stringLineElement.length==2) {
				
				  	 Point point=new Point(Integer.parseInt(stringLineElement[0]),Integer.parseInt(stringLineElement[1]));
				  	 shots.add(point);
				  	 
				  	 /** This means that we have all data for new Solver instance from input.*/
					 /** We must reset some initial data and add Solver to solverList */
				  	 if(shots.size()==numberOfShots) {
				  		 
				  		 // System.out.println("");
				  		 Solver solver=new Solver (width, height, shots, deploymentMapPlayer1, deploymentMapPlayer2);
				  		 
				  		 /* We add new Solver instance and restart initial data now  */
				  		 listOfSolvers.add(solver);
				  		 
				  		 /** Time data  */
				  		 startTimeInitial=0;
						 timer=true;
						 /** Parser data */
						 player1MapCreated=false;
						 player2MapCreated=false;
						 skipYDecrement=false;
						 currentY=height-1;
				  	 }
			  }
		}
		scanner.close();
		return listOfSolvers;
	}

}
