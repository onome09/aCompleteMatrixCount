package aCompleteMatrixCount;
import static aCompleteMatrixCount.Cases.*;


public class Rectangle {
	
	private Pos[] positions = new Pos[4];
	public Rectangle(int row, int col, int height, int width){
		positions[0] = new Pos(row,col);
		positions[1] = new Pos(row+height,col);
		positions[2] = new Pos(row,col + width);
		positions[3] = new Pos(row+height,col+width);
	}
	
	public Pos get(int i){
		return positions[i];
	}

	public boolean equals(Rectangle r){
		for (int i = 0; i < 4; i++){
			if (positions[i].col != r.get(i).col || positions[i].row != r.get(i).row) {
				return false;
			}
		}
		return true;
	}
	public void print() {
		System.out.println("UPLEFT_CORNER = (" + positions[UPLEFT_CORNER].row+" , "+ positions[UPLEFT_CORNER].col+")");
		System.out.println("UPRIGHT_CORNER = (" + positions[UPRIGHT_CORNER].row+" , "+ positions[UPRIGHT_CORNER].col+")");
		System.out.println("DOWNLEFT_CORNER = (" + positions[DOWNLEFT_CORNER].row+" , "+ positions[DOWNLEFT_CORNER].col+")");
		System.out.println("DOWNRIGHT_CORNER = (" + positions[DOWNRIGHT_CORNER].row+" , "+ positions[DOWNRIGHT_CORNER].col+")");

		// TODO Auto-generated method stub
		
	}
}