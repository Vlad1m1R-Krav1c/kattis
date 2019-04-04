/** Turtle class holds info for current position and direction of a Turtle */
public class Turtle {
	
	private int x=0;
	private int y=0;
 	private Direction direction;
	
	public Direction getDirectionEnum() {
		return direction;
	}
	public void setDirectionEnum(Direction directionEnum) {
		this.direction = directionEnum;
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
