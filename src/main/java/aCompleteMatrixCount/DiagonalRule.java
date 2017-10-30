package aCompleteMatrixCount;

import static aCompleteMatrixCount.Cases.*;

public class DiagonalRule extends Rule{
	
	
	public DiagonalRule(Rectangle r){
		super(r,false);
	}
	
	public DiagonalRule(Rectangle r, boolean b){
		super(r,b);
	}
		
	public int checkIfNewRectangleSetWillHaveAnElementThatIsThisRect(Rectangle r, int ruleType){
		initialiseNewRect(r);
		switch (ruleType) {
			case POSITIVE_VERTICAL_RULE:
				if (rowL == lowerRow && rowU == upperRow && colL == lowerCol && colU <= upperCol){
					return upperCol;
				}
				break;
			case NEGATIVE_VERTICAL_RULE:
				if(rowL == lowerRow && rowU == upperRow && colU == upperCol && colL >= lowerCol){
						return lowerCol;
				}
				break;
			case POSITIVE_HORIZONTAL_RULE:
				if (colL == lowerCol && colU == upperCol && rowL == lowerRow && rowU <= upperRow){
					return upperRow;
				}
				break;
			case NEGATIVE_HORIZONTAL_RULE:
				if (colL == lowerCol && colU == upperCol && rowU == upperRow && rowL >= lowerRow){
					return lowerRow;
				}
				break;
			default:
				return -1;
		}
		
		return -1;
	}
	
	
}