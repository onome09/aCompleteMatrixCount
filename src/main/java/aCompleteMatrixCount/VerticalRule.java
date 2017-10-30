package aCompleteMatrixCount;


public class VerticalRule extends Rule{
	
	
	public VerticalRule(Rectangle r, boolean p){
		super(r,p);
	}
	
	
	


	public boolean checkIfDiagonalRectangleIsAnElementOfRule(Rectangle r) {
		// TODO Auto-generated method stub
		initialiseNewRect(r);
		if (positive){
			return(rowL == lowerRow && rowU == upperRow && colL == lowerCol && colU >= upperCol); 
		}else{
			return(rowL == lowerRow && rowU == upperRow && colL <= lowerCol && colU == upperCol); 
		}
	}
	
	public int checkIfBothExpansionsIntersect(Rectangle r, boolean b) {
		initialiseNewRect(r);
		if(positive && b){
			if (lowerRow == rowL && lowerCol == colL) {
				if (colU >= upperCol && upperRow >= rowU){
					return upperRow;
				} 
			}
		} else if (!positive && !b) {
			if (upperRow == rowU && upperCol == colU) {
				if (colL <= lowerCol && rowL >= lowerRow){
					return lowerRow;
				} 
			}
		} else if(!positive && b){
			if (lowerRow == rowL && upperCol == colU) {
				if (colL <= lowerCol && upperRow >= rowU){
					return upperRow;
				}
			}
		} else if (positive && !b) {
			if (upperRow == rowU && lowerCol == colL) {
				if (rowL >= lowerRow && colU >= upperCol){
					return lowerRow;
				} 
			}
		}
		return -1;
	}
	
	
}