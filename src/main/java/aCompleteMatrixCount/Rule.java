package aCompleteMatrixCount;


import static aCompleteMatrixCount.Cases.*;

public class Rule {
	protected final int lowerRow;
	protected final int upperRow;
	protected final int lowerCol;
	protected final int upperCol; 
	protected int rowL;
	protected int rowU;
	protected int colL;
	protected int colU;
	protected boolean positive;
	protected final Rectangle rect;
	
	
	public Rule(Rectangle r,boolean p){
		rect = r;
		lowerCol = r.get(UPLEFT_CORNER).col;
		upperCol = r.get(UPRIGHT_CORNER).col;
		lowerRow = r.get(UPLEFT_CORNER).row;
		upperRow = r.get(DOWNRIGHT_CORNER).row;
		positive = p;
	}
	
	protected Rectangle getRectangle(){
		return rect;
	}
	protected void initialiseNewRect(Rectangle r){
		rowL = r.get(UPLEFT_CORNER).row;
		rowU = r.get(DOWNLEFT_CORNER).row;
		colL = r.get(UPLEFT_CORNER).col;
		colU = r.get(UPRIGHT_CORNER).col;
	}
	
	public boolean checkIfNewRectangleIsTheSameAsThis(Rectangle r){
		initialiseNewRect(r);
		if(rowL == lowerRow && rowU == upperRow 
				&&colL == lowerCol&& colU == upperCol){
			return true;
		}
		return false;
	}
	
	public boolean getPositive(){
		return positive;
	}
	
	
	
}