
public class Solver {
 
	private int r;
	private int c;
 	
	public Solver(int r, int c) {
	    this.setR(r); 
		this.setC(c);  
	}
	
	public void solve() {
		//System.out.println("Pizza radius: "+this.getR()+", Crust: "+this.getC());
		double percentage=(double)( (this.getR()-this.getC()) * (this.getR()-this.getC()) ) /(double) (this.getR()*this.getR());
		System.out.println(percentage*100.0);
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
}
