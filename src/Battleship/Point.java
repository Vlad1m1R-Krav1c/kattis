package problem_c;

/** Helper class for X and Y coordinates of shots and deployment map cells */
public class Point {
	
	private int x=0;
	private int y=0;
 
	public Point(int x, int y) {
		this.setX(x);  
		this.setY(y);
	}

public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
}
