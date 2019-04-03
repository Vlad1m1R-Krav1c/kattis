package problem_c;

import java.util.ArrayList;

/** This class is used to represent and solve each battleship problem instance */
public class Solver {
	
	  private int numberOfTests=0;
	  private int width=0;
	  private int height=0;
	  private int numberOfShots=0;
	  private String[][] deploymentMapPlayer1;
	  private String[][] deploymentMapPlayer2;
	  private ArrayList<Point> shots=new ArrayList<>();
 
	  public Solver( int width, int height, ArrayList<Point> shots,String[][] deploymentMapPlayer1, String[][] deploymentMapPlayer2) {
		 
		this.setWidth(width); 
		this.setHeight(height);  
		this.setShots(shots); 
		this.setDeploymentMapPlayer1(deploymentMapPlayer1);  
		this.setDeploymentMapPlayer2(deploymentMapPlayer2);  
	}

  /** Simulate turn-based Battleship game and shooting between two players and determine the outcome. 
   * Possible outcomes are: "player one wins", "player two wins" and "draw" */
  public String solve() {
	  
	  /*
	  this.printInitialDeploymentMap(this.getDeploymentMapPlayer1());
	  System.out.println("");
	  this.printInitialDeploymentMap(this.getDeploymentMapPlayer2());*/
	  
	  boolean firstPlayerMove=true;
	  boolean firstPlayerDestroyedAllEnemyShips=false;
	  boolean secondPlayerDestroyedAllEnemyShips=false;
      Integer firstWinner=null;
      
      /** For special case when deployment map doesn't have any ships at start */
      /*********************************************************************************/
	  boolean deploymentMapPlayer1IsEmpty=this.isDeploymentMapEmpty(this.getDeploymentMapPlayer1());
	  boolean deploymentMapPlayer2IsEmpty=this.isDeploymentMapEmpty(this.getDeploymentMapPlayer2());
	  
	  /** If both maps are empty at start, then it is a draw */
	  if(deploymentMapPlayer1IsEmpty && deploymentMapPlayer2IsEmpty) {
		  return "draw";
	  }
	  /** If Player 1 map is empty, that means Player 2 destroyed all his ships. Player 1 is on last move(s). 
	   * He can't win, but he can make a draw */
	  if(deploymentMapPlayer1IsEmpty) {
		  secondPlayerDestroyedAllEnemyShips=true;
		  firstWinner=2;
	  }
	  /** If Player 2 map is empty, that means Player 1 destroyed all his ships. 
	   * Player 2 is on last move(s) and first shot is his shot!!!. He can't win, but he can make a draw */
	  if(deploymentMapPlayer2IsEmpty) {
 		  firstPlayerDestroyedAllEnemyShips=true;
		  firstWinner=1;
		  firstPlayerMove=false;
	  }
	  /*********************************************************************************/
	  
	  /** Going through all shots */
	  for(Point shot : this.getShots()) {
		  //System.out.println("Shot: "+shot.getX()+","+shot.getY());
		  
		  /* If game is already over, we don't need any more extra shots data */
		  if(firstPlayerDestroyedAllEnemyShips && secondPlayerDestroyedAllEnemyShips) {
			  return "draw";
		  }
		  /** First player move */
		  if(firstPlayerMove) {
 			 
			  /** Determining if shot/token is "#" or "_"    */
			  String token=this.getDeploymentMapPlayer2()[shot.getX()][shot.getY()];
			  
			  /** Player 1 hits a ship */
			  if(token.equals("#")) {	
					   
				  //System.out.println("Player 1 has hit a ship on: "+shot.getX()+","+shot.getY());
				  this.getDeploymentMapPlayer2()[shot.getX()][shot.getY()]="_";
				  boolean isLastShip=this.isDeploymentMapEmpty(this.getDeploymentMapPlayer2());
				  
				  if(isLastShip) {
					 // System.out.println("This was last ship. Player 1 destroyed all ships of Player 2!");
					  firstPlayerDestroyedAllEnemyShips=true;
					  firstPlayerMove=false;
					  
					  /** If Player 1 managed to destroy all enemy ships first */
					  if(firstWinner==null) {
						  firstWinner=1;
					  }
					  /* If player two destroyed all ships and this was last move of Player 1 */
					  if(secondPlayerDestroyedAllEnemyShips && (firstWinner==2)) {
						  return "draw";
					  }
				  }
				  else {
					  //System.out.println("Player 2 has more ship(s) left! Player 1 will strike again!");
					 }
			  	}
 				/** Player 1 missed */
				else if(token.equals("_")) {
					
					  //System.out.println("Player 1 has hit an ocean on: "+shot.getX()+","+shot.getY());
					  firstPlayerMove=false;
					  /* If player two destroyed all ships and this was last move of Player 1 */
					  if(secondPlayerDestroyedAllEnemyShips && (firstWinner==2)) {
						  return "player two wins";
					  }
				}
		  }
		  /** Second player move */
		  else {
			  	/** Determining if shot/token is "#" or "_"    */
			  	String token=this.getDeploymentMapPlayer1()[shot.getX()][shot.getY()];
			  	
			  	/** Player 2 hits a ship */
			  	if(token.equals("#")) {
			  		
				  // System.out.println("Player 2 has hit a ship on: "+shot.getX()+","+shot.getY());
				  this.getDeploymentMapPlayer1()[shot.getX()][shot.getY()]="_";
				  boolean isLastShip=this.isDeploymentMapEmpty(this.getDeploymentMapPlayer1());
				  
				  if(isLastShip) {
					 // System.out.println("This was last ship. Player 2 destroyed all ships of Player 1!");
					  secondPlayerDestroyedAllEnemyShips=true;
					  firstPlayerMove=false;  
					  
					  /** If Player 2 managed to destroy all enemy ships first */
					  if(firstWinner==null) {
						  firstWinner=2;
					  	}
					  /* If player 1 destroyed all ships and this was last move of player 2 */
					  if(firstPlayerDestroyedAllEnemyShips && (firstWinner==1)) {
						  return "draw";
					  	}
					  else if(!firstPlayerDestroyedAllEnemyShips) {
						  return "player two wins";
					  }
				  }
				  else {
					  // System.out.println("Player 1 has more ship(s) left! Player 2 will strike again!");
				  	}
			  	}
 			 else if(token.equals("_")) {
 				
				  //System.out.println("Player 2 has hit an ocean on: "+shot.getX()+","+shot.getY());
				  firstPlayerMove=true;
				  /* If player 1 destroyed all ships and this was last move of player 2 */
				  if(firstPlayerDestroyedAllEnemyShips && (firstWinner==1)) {
					  return "player one wins";
				  }
			  }
		  }
	  }
 	  if(secondPlayerDestroyedAllEnemyShips && !firstPlayerDestroyedAllEnemyShips) {
		  return "player two wins";
	  }
	  if(firstPlayerDestroyedAllEnemyShips && !secondPlayerDestroyedAllEnemyShips) {
		  return "player one wins";
	  }
	  if(!firstPlayerDestroyedAllEnemyShips && !secondPlayerDestroyedAllEnemyShips) {
		  return "draw";
	  }
	  if(firstPlayerDestroyedAllEnemyShips && secondPlayerDestroyedAllEnemyShips) {
		  return "draw";
	  }
	  return "";
  }
  /** Printing out initial Deployment Map data */
  public void printInitialDeploymentMap(String[][] deploymentMapPlayer) {
		
		for(int u=(this.getHeight()-1);u>=0;u--) {
			StringBuilder builder= new StringBuilder();
			  for(int i=0;i<this.getWidth();i++) {
				   builder.append(deploymentMapPlayer[i][u]);
			  }
			  System.out.println(builder.toString());
		  }
	}
  /** Determining if Deployment map is empty (all ships destroyed) */
  public boolean isDeploymentMapEmpty(String[][] deploymentMapPlayer) {
	  
	  int numberOfShips=0;
	  
	  for(int i=0;i<this.getWidth();i++) {
		  for(int u=0;u<this.getHeight();u++) {
			  if(deploymentMapPlayer[i][u].equals("#")) {
				  numberOfShips++;
			  }
		  }
	  }
	  //System.out.println("Number of ships: "+numberOfShips); 
	  if(numberOfShips==0) {
		  return true;
	  }
	  
	  return false;
  }
  public ArrayList<Point> getShots() {
		return shots;
	}


public void setShots(ArrayList<Point> shots) {
		this.shots = shots;
	}

public int getNumberOfTests() {
	return numberOfTests;
}

public void setNumberOfTests(int numberOfTests) {
	this.numberOfTests = numberOfTests;
}

public int getWidth() {
	return width;
}

public void setWidth(int width) {
	this.width = width;
}

public int getHeight() {
	return height;
}

public void setHeight(int height) {
	this.height = height;
}

public int getNumberOfShots() {
	return numberOfShots;
}

public void setNumberOfShots(int numberOfShots) {
	this.numberOfShots = numberOfShots;
}

public String[][] getDeploymentMapPlayer1() {
	return deploymentMapPlayer1;
}

public void setDeploymentMapPlayer1(String[][] deploymentMapPlayer1) {
	this.deploymentMapPlayer1 = deploymentMapPlayer1;
}

public String[][] getDeploymentMapPlayer2() {
	return deploymentMapPlayer2;
}

public void setDeploymentMapPlayer2(String[][] deploymentMapPlayer2) {
	this.deploymentMapPlayer2 = deploymentMapPlayer2;
}



}
