package aCompleteMatrixCount;

public class HorizontalRule extends Rule{
	
	
	public HorizontalRule(Rectangle r, boolean b){
		super(r,b);
	}
	


	public boolean checkIfDiagonalRectangleIsAnElementOfRule(Rectangle r) {
		initialiseNewRect(r);
		if (positive){
			return(colL == lowerCol && colU == upperCol && rowL == lowerRow && rowU >= upperRow); 
		}else{
			return(colL == lowerCol && colU == upperCol && rowL <= lowerRow && rowU == upperRow); 
		}
		
	}
	
	
	public int checkIfBothExpansionsIntersect(Rectangle r, boolean b) {
		initialiseNewRect(r);
		if(positive && b){
			if (lowerRow == rowL && lowerCol == colL) {
				 if (rowU >= upperRow && colU <= upperCol){
					 return upperCol;
				 }
			}
		} else if (!positive && !b) {
			if (upperRow == rowU && upperCol == colU) {
				if (colL >= lowerCol && rowL <= lowerRow){
					 return lowerCol;
				 }
			}
		} else if(!positive && b){
			if (lowerCol == colL && upperRow == rowU) {
				if (rowL <= lowerRow && colU <= upperCol){
					 return upperCol;
				 }
			}
		} else if (positive && !b) {
			if (upperCol == colU && lowerRow == rowL) {
				if (rowU >= upperRow && lowerCol <= colL){
					 return lowerCol;
				 }
			}
		}
		return -1;
	}
}