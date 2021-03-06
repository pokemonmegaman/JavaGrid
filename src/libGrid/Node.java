package libGrid;
//Node.java
//A data node to be stored in a grid.
public class Node {
	private Grid storedParentGrid;
	private Object storedData;
	private int xpos, ypos;
	public static final int 
			NORTHWEST = 0,
			NORTH = 1,
			NORTHEAST = 2,
			WEST = 3,
			EAST = 4,
			SOUTHWEST = 5,
			SOUTH = 6,
			SOUTHEAST = 7;
	public Node (Grid parentGrid, Object data, int height, int width){
		storedParentGrid = parentGrid;
		storedData = data;
		this.xpos = height;
		this.ypos = width;
	}
	public Node (Grid parentGrid, int height, int width){
		storedParentGrid = parentGrid;
	}
	public Object getData(){
		return storedData;
	}
	public int getXpos() {
		return xpos;
	}
	public int getYpos() {
		return ypos;
	}
	public void setData(Object data){
		storedData = data;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((storedData == null) ? 0 : storedData.hashCode());
		result = prime
				* result
				+ ((storedParentGrid == null) ? 0 : storedParentGrid.hashCode());
		result = prime * result + xpos;
		result = prime * result + ypos;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (storedData == null) {
			if (other.storedData != null)
				return false;
		} else if (!storedData.equals(other.storedData))
			return false;
		if (storedParentGrid == null) {
			if (other.storedParentGrid != null)
				return false;
		} else if (!storedParentGrid.equals(other.storedParentGrid))
			return false;
		if (xpos != other.xpos)
			return false;
		if (ypos != other.ypos)
			return false;
		return true;
	}
	public Grid getParentGrid(){
		return storedParentGrid;
	}
	public Node getInDirection(int direction){
		int x;
		int y;
		switch (direction){
			case 0: y = -1; x = -1; break;
			case 1: y = 0; x = -1; break;
			case 2: y = 1; x = -1; break;
			case 3: y = -1; x = 0; break;
			case 4: y = 1; x = 0; break;
			case 5: y = -1; x = 1; break;
			case 6: y = 0; x = 1; break;
			case 7: y = 1; x = 1; break;
			default: return null;
		}
		try {
			return storedParentGrid.getNode(xpos+x, ypos+y);
		} catch (java.lang.ArrayIndexOutOfBoundsException ex){
			return null;
		}
	}
	public boolean equals(Node node){
		if (storedData == node.getData()){
			return true;
		}
		return false;
	}
	public String toString(){
		try {
			return this.storedData.toString();
		} catch (java.lang.NullPointerException ex){
			return " ";
		}
	}
	
	public Node[] getNeighbors(){
		Node[] neighbors = new Node[8];
		for (int x=0; x<8; x++){
			try {
				neighbors[x] = this.getInDirection(x);
			}	
			catch (ArrayIndexOutOfBoundsException ex){ //Needed in case there are no neighboring cells.
				neighbors[x] = null;
			}
		}
		return neighbors;  //Fun enough, you can get a neighbor from the returned array by using the directional constants as the array index to read.
	}
}
