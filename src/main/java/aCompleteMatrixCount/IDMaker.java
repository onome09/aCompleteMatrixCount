package aCompleteMatrixCount;


import static aCompleteMatrixCount.Cases.*;

public class IDMaker {



	
	public static String makeRectangleID(Rectangle r){
		String s = "";
		s += String.valueOf(r.get(UPLEFT_CORNER).row);
		s += "-";
		s += String.valueOf(r.get(DOWNLEFT_CORNER).row);
		s += "-";
		s += String.valueOf(r.get(UPLEFT_CORNER).col);
		s += "-";
		s += String.valueOf(r.get(UPRIGHT_CORNER).col);
		return s;
	}
	
	public static String makeVerticalRuleID(Rectangle r){
		String s = "";
		s += String.valueOf(r.get(UPLEFT_CORNER).row);
		s += "-";
		s += String.valueOf(r.get(DOWNLEFT_CORNER).row);
		return s;
	}
	
	public static String makeHorizontalRuleID(Rectangle r){
		String s = "";
		s += String.valueOf(r.get(UPLEFT_CORNER).col);
		s += "-";
		s += String.valueOf(r.get(DOWNRIGHT_CORNER).col);
		return s;
	}

	public static String makePositiveVerticalForDiagonalRuleID(Rectangle r){
		String s = "";
		s += String.valueOf(r.get(UPLEFT_CORNER).row);
		s += "-";
		s += String.valueOf(r.get(DOWNRIGHT_CORNER).row);
		s += "-";
		s += String.valueOf(r.get(UPLEFT_CORNER).col);
		return s;
	}
	public static String makeNegativeVerticalForDiagonalRuleID(Rectangle r){
		String s = "";
		s += String.valueOf(r.get(UPLEFT_CORNER).row);
		s += "-";
		s += String.valueOf(r.get(DOWNRIGHT_CORNER).row);
		s += "-";
		s += String.valueOf(r.get(UPRIGHT_CORNER).col);
		return s;
	}
	public static String makePositiveHorizontalForDiagonalRuleID(Rectangle r){
		String s = "";
		s += String.valueOf(r.get(UPLEFT_CORNER).col);
		s += "-";
		s += String.valueOf(r.get(DOWNRIGHT_CORNER).col);
		s += "-";
		s += String.valueOf(r.get(UPLEFT_CORNER).row);
		return s;
	}
	public static String makeNegativeHorizontalForDiagonalRuleID(Rectangle r){
		String s = "";
		s += String.valueOf(r.get(UPLEFT_CORNER).col);
		s += "-";
		s += String.valueOf(r.get(DOWNRIGHT_CORNER).col);
		s += "-";
		s += String.valueOf(r.get(DOWNRIGHT_CORNER).row);
		return s;
	}
	
	
}
