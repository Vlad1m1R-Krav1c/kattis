import java.util.ArrayList;

public class Solver {

	private String[][] board=new String[8][8];
	private ArrayList<String> commands=new ArrayList<>();
	private int xDiamond=0;
	private  int yDiamond=0;
	
	
	/** Solves the Turtle problem. Returns "Diamond!" or "Bug!". */
	public String solve() {
		
		 Turtle turtle=new Turtle();
	     turtle.setDirectionEnum(Direction.R);
 	     this.getBoard()[0][0]=".";
	    
		/*System.out.println("Initial board: ");
		for(int i=7;i>=0;i--) {
			String line=i+" ";
			for(int u=0;u<=7;u++) {
				line=line+this.getBoard()[u][i];
			}
			System.out.println(line);
		}*/
	     
		/** Trying to execute each command */ 
		for(String command : this.getCommands()) {
			
			/**************************************************************************/
			if(command.equals("F")) {
				if(!this.command_F(turtle)) {
					return "Bug!";
					}
				}
			/**************************************************************************/
			else if(command.equals("R")) {
				//System.out.println("Change turtle direction 90 degrees to right.");
				//System.out.println("Old turtle direction: "+turtle.getDirectionEnum());
				turtle.setDirectionEnum(this.command_R(turtle)); 
				//System.out.println("New turtle direction: "+turtle.getDirectionEnum());
				}
			/**************************************************************************/
			else if(command.equals("L")) {
				//System.out.println("Change turtle direction 90 degrees to left.");
				//System.out.println("Old turtle direction: "+turtle.getDirectionEnum());
				turtle.setDirectionEnum(this.command_L(turtle));
				//System.out.println("New turtle direction: "+turtle.getDirectionEnum());
				}
			/**************************************************************************/
			else if(command.equals("X")) {
				 if(!this.command_X(turtle)) {
					return "Bug!";
				 }
			 }
			/**************************************************************************/
		 }
 		if(turtle.getX()==this.getxDiamond() && turtle.getY()==this.getyDiamond()) {
			return "Diamond!";
		}
		 
		return "Bug!";
	} 
	/** Trying to move turtle to next spot. Returns false if next spot is Ice or Rock castle or if it is out of board dimensions */
	public boolean command_F(Turtle turtle) {
		
		/** Turtle facing to Right */
		if(turtle.getDirectionEnum().name()=="R") {
			  
			if((turtle.getX()+1)==this.getBoard().length) {
				//System.out.println("Turtle can't move to right, cos it is out of dimension limit!");
				return false;
		    }
			else {
					if(this.getBoard()[turtle.getX()+1][turtle.getY()].equals("C") || this.getBoard()[turtle.getX()+1][turtle.getY()].equals("I") ) {
						//System.out.println("Turtle can't move to castle.");
						return false;
					}
					else {
						//System.out.println("Moving turtle from: "+turtle.getX()+","+turtle.getY()+" to: "+(turtle.getX()+1)+","+turtle.getY());
						turtle.setX(turtle.getX()+1);
					}
			  }
		}
		/** Turtle facing to Left */
		if(turtle.getDirectionEnum().name()=="L") {
			
			if((turtle.getX()-1)<0) {
				//System.out.println("Turtle can't move to left, cos it is out of dimension limit!");
				return false;
				 }
			else {
				
				if(this.getBoard()[turtle.getX()-1][turtle.getY()].equals("C") || this.getBoard()[turtle.getX()-1][turtle.getY()].equals("I") ) {
					//System.out.println("Turtle can't move to castle.");
					return false;
				}
				else {
					//System.out.println("Moving turtle from: "+turtle.getX()+","+turtle.getY()+" to: "+(turtle.getX()-1)+","+turtle.getY());
					turtle.setX(turtle.getX()-1);
				}
			 }
		}
		/** Turtle facing Up */
		if(turtle.getDirectionEnum().name()=="U") {
			
			if((turtle.getY()+1)==this.getBoard().length) {
				//System.out.println("Turtle can't move up, cos it is out of dimension limit!");
				return false;
				 }
			 else {
				
				if(this.getBoard()[turtle.getX()][turtle.getY()+1].equals("C") || this.getBoard()[turtle.getX()][turtle.getY()+1].equals("I") ) {
					//System.out.println("Turtle can't move to castle.");
					return false;
					 }
				else {
					//System.out.println("Moving turtle from: "+turtle.getX()+","+turtle.getY()+" to: "+(turtle.getX())+","+(turtle.getY()+1));
					turtle.setY(turtle.getY()+1);
				}
			 }
		}
		/** Turtle facing to Down */
		if(turtle.getDirectionEnum().name()=="D") {

			if((turtle.getY()-1)<0) {
				//System.out.println("Turtle can't move down, cos it is out of dimension limit!");
				return false;
			}
			 else {
				
				if(this.getBoard()[turtle.getX()][turtle.getY()-1].equals("C") || this.getBoard()[turtle.getX()][turtle.getY()-1].equals("I") ) {
					//System.out.println("Turtle can't move to castle.");
					return false;
					 }
				else {
					//System.out.println("Moving turtle from: "+turtle.getX()+","+turtle.getY()+" to: "+(turtle.getX())+","+(turtle.getY()-1));
					turtle.setY(turtle.getY()-1);
				}
		    }
		}
		
	return true;
	}
	
	/** Checks if turtle Laser attack is valid. It is valid only if it shoots an Ice Castle, in which case it is transformed into empty spot */
	public boolean command_X(Turtle turtle) {
		
		/** Turtle facing to Right */
		if(turtle.getDirectionEnum().name()=="R") {
			
			if((turtle.getX()+1)==this.getBoard().length) {
				 //System.out.println("Turtle can't shoot to right, cos it is out of dimension limit!");
				return false;
			 }
			else {
				
				if(this.getBoard()[turtle.getX()+1][turtle.getY()].equals("C") || this.getBoard()[turtle.getX()+1][turtle.getY()].equals(".")) {
					// System.out.println("Turtle can't shoot at "+this.getBoard()[turtle.getX()+1][turtle.getY()]);
					return false;
					 }
				else if (this.getBoard()[turtle.getX()+1][turtle.getY()].equals("I")) {
					// System.out.println("Turtle shoot an Ice Castle, which is now transformed to empty slot!");
					this.getBoard()[turtle.getX()+1][turtle.getY()]=".";
 				}
			 }
		}
		/** Turtle facing to Left */
		if(turtle.getDirectionEnum().name()=="L") {
			
			if((turtle.getX()-1)<0) {
				 //System.out.println("Turtle can't shoot to left, cos it is out of dimension limit!");
				return false;
				 }
			else {
				
				if(this.getBoard()[turtle.getX()-1][turtle.getY()].equals("C") || this.getBoard()[turtle.getX()-1][turtle.getY()].equals(".")) {
					// System.out.println("Turtle can't shoot at "+this.getBoard()[turtle.getX()-1][turtle.getY()]);
					return false;
				 }
				else if (this.getBoard()[turtle.getX()-1][turtle.getY()].equals("I")) {
				    //System.out.println("Turtle shoot an Ice Castle, which is now transformed to empty slot!");
					this.getBoard()[turtle.getX()-1][turtle.getY()]=".";
 				}
			 }
		}
		/** Turtle facing to Up */
		if(turtle.getDirectionEnum().name()=="U") {
			
			if((turtle.getY()+1)==this.getBoard().length) {
			 //System.out.println("Turtle can't shoot up, cos it is out of dimension limit!");
				return false;
			 }
			
			else {
				
				if(this.getBoard()[turtle.getX()][turtle.getY()+1].equals("C") || this.getBoard()[turtle.getX()][turtle.getY()+1].equals(".") ) {
					// System.out.println("Turtle can't shoot at "+this.getBoard()[turtle.getX()][turtle.getY()+1]);
					return false;
				 }
				 else if (this.getBoard()[turtle.getX()][turtle.getY()+1].equals("I")) {
					// System.out.println("Turtle shoot an Ice Castle, which is now transformed to empty slot!");
					this.getBoard()[turtle.getX()][turtle.getY()+1]=".";
 				}
			 }
		}
		/** Turtle facing to Down */
		if(turtle.getDirectionEnum().name()=="D") {
		 
			if((turtle.getY()-1)<0) {
				//System.out.println("Turtle can't shoot down, cos it is out of dimension limit!");
				return false;
				}
			else {
				
				if(this.getBoard()[turtle.getX()][turtle.getY()-1].equals("C") || this.getBoard()[turtle.getX()][turtle.getY()-1].equals(".")) {
					//System.out.println("Turtle can't shoot at "+this.getBoard()[turtle.getX()][turtle.getY()-1]);
					return false;
				}
				 
				else if (this.getBoard()[turtle.getX()][turtle.getY()-1].equals("I")) {
					//System.out.println("Turtle shoot an Ice Castle, which is now transformed to empty slot!");
					this.getBoard()[turtle.getX()][turtle.getY()-1]=".";
 				}
			}
		}
		return true;
	}
	/** Rotates turtle 90 degrees to left and returns new direction*/
	public Direction command_L(Turtle turtle) {
		 
		if(turtle.getDirectionEnum().name()=="R") {
			return Direction.U;
 		}
		if(turtle.getDirectionEnum().name()=="U") {
			return Direction.L;
 		}
		if(turtle.getDirectionEnum().name()=="L") {
			return Direction.D;
 		}
		if(turtle.getDirectionEnum().name()=="D") {
			return Direction.R;
 		}
		return Direction.R;
	}
	
	/** Rotates turtle 90 degrees to right and returns new direction*/
 	public Direction command_R(Turtle turtle) {
		 
		if(turtle.getDirectionEnum().name()=="R") {
			return Direction.D;
 		}
		if(turtle.getDirectionEnum().name()=="D") {
			return Direction.L;
 		}
		if(turtle.getDirectionEnum().name()=="L") {
			return Direction.U;
 		}
		if(turtle.getDirectionEnum().name()=="U") {
			return Direction.R;
 		}
 		return Direction.R;
	}
	
	
	public Solver(String[][] board, ArrayList<String> commands,int xDiamond, int yDiamond) {
		 
		this.setBoard(board); 
		this.setCommands(commands); 
		this.setxDiamond(xDiamond);
		this.setyDiamond(yDiamond);
	}
	 

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}

	public ArrayList<String> getCommands() {
		return commands;
	}

	public void setCommands(ArrayList<String> commands) {
		this.commands = commands;
	}
	public int getxDiamond() {
		return xDiamond;
	}
	public void setxDiamond(int xDiamond) {
		this.xDiamond = xDiamond;
	}
	public int getyDiamond() {
		return yDiamond;
	}
	public void setyDiamond(int yDiamond) {
		this.yDiamond = yDiamond;
	}
	
}
