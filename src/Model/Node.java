package Model;

public class Node {
	
	// 0 = start, 1 = finish, 2 = wall, 3 = empty, 4 = checked, 5 = finalpath
	private int cellType = 0;
	private int hops;
	private int x;
	private int y;
	private int lastX;
	private int lastY;
	private double dToEnd = 0;

	public Node(int type, int x, int y) {	//CONSTRUCTOR
		cellType = type;
		this.x = x;
		this.y = y;
		hops = -1;
	}
	
	public double getEuclidDist(int finishx,int finishy) {		//CALCULATES THE EUCLIDIAN DISTANCE TO THE FINISH NODE
		int xdif = Math.abs(x-finishx);
		int ydif = Math.abs(y-finishy);
		dToEnd = Math.sqrt((xdif*xdif)+(ydif*ydif));
		return dToEnd;
	}
	
	public int getX() {return x;}		//GET METHODS
	public int getY() {return y;}
	public int getLastX() {return lastX;}
	public int getLastY() {return lastY;}
	public int getType() {return cellType;}
	public int getHops() {return hops;}
	
	public void setType(int type) {cellType = type;}		//SET METHODS
	public void setLastNode(int x, int y) {lastX = x; lastY = y;}
	public void setHops(int hops) {this.hops = hops;}
}

