package aCompleteMatrixCount;
import static aCompleteMatrixCount.Cases.*;

public class Num {
	private final int number;
	private final int row;
	private final int col;

	public Num(int num, int x, int y){
		number = num;
		row = x;
		col = y;
	}
	
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}
	
	public int getCol() {
		// TODO Auto-generated method stub
		return col;
	}

	public int relTo(Num e) {
		int x = e.getRow();
		int y = e.getCol();
		if (row == x && col == y){
			return SAME;
		}
		if (row == x){
			return HORIZONTAL;
		}
		
		if (col == y){
			return VERTICAL;
		}
		
		return DIAGONAL;
		
	}


	public int getNumber() {
		// TODO Auto-generated method stub
		return number;
	}
	
	
}