package nordcloudtest.model;

public class Station {
	
	private int x;
	private int y;
	private int r;
	
	public Station(int x, int y, int r) {
		this.x = x;
		this.y=y;
		this.r=r;
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

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	


}
