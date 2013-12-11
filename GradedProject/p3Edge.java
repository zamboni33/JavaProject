package GradedProject;

public class p3Edge
{
    private final Integer left;
    private final Integer right;

    public p3Edge(int aLeft, int aRight)
    {
        // Smaller node always on left
    	if(aLeft < aRight){
	    	left   = aLeft;
	        right = aRight;
        } else {
        	left = aRight;
        	right = aLeft;
        }
    }

    public int left()   { return left; }
    public int right() { return right; }
    
    
    public boolean equals( p3Edge aEdge) {
    	if(aEdge.left == this.left && aEdge.right == this.right ){
    		return(true);
    	}else {
    		return(false);
    	}
    }
    
//    public int hashCode() {
//    	return(this.left);
//    }
    
    public String toString() { return (left.toString() + "  " + right.toString() ); }
}