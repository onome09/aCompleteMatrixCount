package aCompleteMatrixCount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static aCompleteMatrixCount.Cases.*;

public class MatCounter {
	private int K;
	private int X;
	private int[][] matrix;
	private List<DiagonalRule> dRules;
	private List<VerticalRule> vRules;
	private List<HorizontalRule> hRules;
	private HashSet<String> RectangleIDs;
	private HashMap<String, int[]> positiveVerticalRuleIDs;
	private HashMap<String, int[]> positiveHorizontalRuleIDs;
	private HashMap<String, int[]> negativeVerticalRuleIDs;
	private HashMap<String, int[]> negativeHorizontalRuleIDs;
	private HashMap<String, int[]> positiveVerticalDiagonalRuleIDs;
	private HashMap<String, int[]> positiveHorizontalDiagonalRuleIDs;
	private HashMap<String, int[]> negativeVerticalDiagonalRuleIDs;
	private HashMap<String, int[]> negativeHorizontalDiagonalRuleIDs;

	private HashSet<Integer> pairs;
	PointChecker checker;
	private int no;
	private final int rows;
	private final int cols;
	
	public MatCounter(int[][] array, int k, int x) {
		vRules = new ArrayList<>();
		hRules = new ArrayList<>();
		dRules = new ArrayList<>();
		RectangleIDs = new HashSet<>();
		positiveVerticalRuleIDs = new HashMap<>();
		positiveHorizontalRuleIDs = new HashMap<>();
		negativeVerticalRuleIDs = new HashMap<>();
		negativeHorizontalRuleIDs = new HashMap<>();
		positiveVerticalDiagonalRuleIDs = new HashMap<>();
		positiveHorizontalDiagonalRuleIDs = new HashMap<>();
		negativeVerticalDiagonalRuleIDs = new HashMap<>();
		negativeHorizontalDiagonalRuleIDs = new HashMap<>();
		matrix = array;
		K = k;
		X = x;
		no = 0;
		checker = new PointChecker();
		rows = array.length;
		cols = array[0].length;
	}
	
	public int getNumberOfSubMatrices() {
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				analysePoint(i,j);
				
			}
		}
		return no;
	}
	
	private void analysePoint(int i, int j) {
		Num num = new Num(matrix[i][j],i,j);
		List<InfoPackage> infos = checker.checkNum(num);
		if (infos == null){
			return;
		}
		for (InfoPackage info: infos){
				analyseInfo(num,info);
		}
	}
	
	private void analyseInfo(Num num, InfoPackage info){
        switch (info.relDirection) {
        	case VERTICAL:  
        		analyseVertical(num,info.point);
                 break;
        	case HORIZONTAL:  
        		analyseHorizontal(num,info.point);
                 break;
        	case DIAGONAL:
        		analyseDiagonal(num,info.point);
                 break;
        	default: 
                 break;
        }
	}
	
	private void analyseVertical(Num num, Pos point) {
		
		VerticalRule VerticalRule1 = analyseNPVertical(num, point, false);
		VerticalRule VerticalRule2 = analyseNPVertical(num, point, true);
		if (VerticalRule1 != null){
			vRules.add(VerticalRule1 );
		}
		if (VerticalRule2 != null){
			vRules.add(VerticalRule2 );
		}
	}
	
	private void analyseHorizontal(Num num, Pos point) {
		
		HorizontalRule HorizontalRule1 = analyseNPHorizontal(num, point, false);
		HorizontalRule HorizontalRule2 = analyseNPHorizontal(num, point, true);
		if (HorizontalRule1 != null){
			hRules.add(HorizontalRule1 );
		}
		if (HorizontalRule2 != null){
			hRules.add(HorizontalRule2);
		}
	}
	
	private VerticalRule analyseNPVertical(Num num, Pos point, boolean bool) {
		Rectangle r = getMinVerticalRectangletoExtend(num,point,bool);
		if (r==null ){
			return null;
		}
		int a = r.get(UPLEFT_CORNER).col;
		int b = r.get(UPRIGHT_CORNER).col;
		resetPairs();
		no++;
		
		no -= checkVerticalHasAnElementOfVertical(r,bool);
		no -= checkVerticalHasAnElementOfDiagonal(r,bool);
		no -= checkHorizontalRuleVerticalRectDoubleExpansion(r,bool);
		
		VerticalRule v = new VerticalRule(r, bool);
		
		if (bool){
			no += (cols - a) - (b-a+1);
		}else {
			no += b-(b-a);
		}	


		return v;
	}
	
	private HorizontalRule analyseNPHorizontal(Num num, Pos point, boolean bool) {
		Rectangle r = getMinHorizontalRectangletoExtend(num,point,bool);
		if (r==null ){
			return null;
		}
		int c = r.get(UPLEFT_CORNER).row;
		int d = r.get(DOWNLEFT_CORNER).row;

		resetPairs();
		no++;
		
		no -= checkHorizontalHasAnElementOfHorizontal(r,bool);
		no -= checkHorizontalHasAnElementOfDiagonal(r,bool);
		no -= checkVerticalRuleHorizontalRectDoubleExpansion(r,bool);
		HorizontalRule v = new HorizontalRule(r,bool);
		if (bool){
			no += (rows - c) - (d-c+1);
		}else {
			no += d-(d-c);
		}

		return v;
	}
	

	private void analyseDiagonal(Num num, Pos point) {
		Rectangle r = getDiagonalRectangle(num,point);
		if (r==null ){
			return;
		}
		if (checkDiagonalinDiagonal(r) ||  checkDiagonalinHorizontal(r)  || checkDiagonalinVertical(r) ){
			return;
		}
		addDiagonalRuleToIds(r);
		DiagonalRule v = new DiagonalRule(r);
		no++;
		dRules.add(v);

		
	}
	
	private void addDiagonalRuleToIds(Rectangle r) {
		// TODO Auto-generated method stub
		String pVDiagonalID = IDMaker.makePositiveVerticalForDiagonalRuleID(r);
		String nVDiagonalID = IDMaker.makeNegativeVerticalForDiagonalRuleID(r);
		String pHDiagonalID = IDMaker.makePositiveHorizontalForDiagonalRuleID(r);
		String nHDiagonalID = IDMaker.makeNegativeHorizontalForDiagonalRuleID(r);
		int a = r.get(UPRIGHT_CORNER).col;
		if (!positiveVerticalDiagonalRuleIDs.containsKey(pVDiagonalID)){
			int[] newRuleArray = new int[cols];
			newRuleArray[a] = 1;
			positiveVerticalDiagonalRuleIDs.put(pVDiagonalID, newRuleArray);
		} else {
			positiveVerticalDiagonalRuleIDs.get(pVDiagonalID)[a] = 1;
		}
		a = r.get(UPLEFT_CORNER).col;
		if (!negativeVerticalDiagonalRuleIDs.containsKey(nVDiagonalID)){
			int[] newRuleArray = new int[cols];
			newRuleArray[a] = 1;
			negativeVerticalDiagonalRuleIDs.put(nVDiagonalID, newRuleArray);
		} else {
			negativeVerticalDiagonalRuleIDs.get(nVDiagonalID)[a] = 1;
		}
		a = r.get(DOWNLEFT_CORNER).row;
		if (!positiveHorizontalDiagonalRuleIDs.containsKey(pHDiagonalID)){
			int[] newRuleArray = new int[rows];
			newRuleArray[a] = 1;
			positiveHorizontalDiagonalRuleIDs.put(pHDiagonalID, newRuleArray);
		} else {
			positiveHorizontalDiagonalRuleIDs.get(pHDiagonalID)[a] = 1;
		}
		a = r.get(UPLEFT_CORNER).row;
		if (!negativeHorizontalDiagonalRuleIDs.containsKey(nHDiagonalID)){
			int[] newRuleArray = new int[rows];
			newRuleArray[a] = 1;
			negativeHorizontalDiagonalRuleIDs.put(nHDiagonalID, newRuleArray);
		} else {
			negativeHorizontalDiagonalRuleIDs.get(nHDiagonalID)[a] = 1;
		}
	}

	
	private int checkVerticalHasAnElementOfVertical(Rectangle r, boolean bool){
		String RuleId = IDMaker.makeVerticalRuleID(r);
		int res = 0;
		if (bool){
			int a = r.get(UPLEFT_CORNER).col;
			int b = r.get(UPRIGHT_CORNER).col;

			if (!positiveVerticalRuleIDs.containsKey(RuleId)){
				int[] newRuleArray = new int[cols];
				newRuleArray[a] = 1;
				positiveVerticalRuleIDs.put(RuleId, newRuleArray);
			} else {
				positiveVerticalRuleIDs.get(RuleId)[a] = 1;
			}
			if (!negativeVerticalRuleIDs.containsKey(RuleId)){
				return 0;
			} else {
				int[] aRuleArray = negativeVerticalRuleIDs.get(RuleId);
				res = 0;
				int x = 0;
				for (int i = b; i < cols; i++){
					x = aRuleArray[i];
					if (x > 0 && pairs.add(i)){
						res += x;	
					}
				}
			}
		} else {
			int a = r.get(UPRIGHT_CORNER).col;
			int b = r.get(UPLEFT_CORNER).col;
			if (!negativeVerticalRuleIDs.containsKey(RuleId)){
				int[] newRuleArray = new int[cols];
				newRuleArray[a] = 1;
				negativeVerticalRuleIDs.put(RuleId, newRuleArray);
			} else {
				negativeVerticalRuleIDs.get(RuleId)[a] = 1;
			}
			if (!positiveVerticalRuleIDs.containsKey(RuleId)){
				return 0;
			} else {
				int[] aRuleArray = positiveVerticalRuleIDs.get(RuleId);
				res = 0;
				int x = 0;
				for (int i = b; i >= 0; i--){
					
					x = aRuleArray[i];
					if (x > 0 && pairs.add(i)){
						res+= x;
					}
				}
			}
		}
		
		return res;
	}
	
	private int checkHorizontalHasAnElementOfHorizontal(Rectangle r, boolean bool){
		String RuleId = IDMaker.makeHorizontalRuleID(r);
		int res = 0;
		if (bool){
			int a = r.get(UPLEFT_CORNER).row;
			int b = r.get(DOWNRIGHT_CORNER).row;

			if (!positiveHorizontalRuleIDs.containsKey(RuleId)){
				int[] newRuleArray = new int[rows];
				newRuleArray[a] = 1;
				positiveHorizontalRuleIDs.put(RuleId, newRuleArray);
			} else {
				positiveHorizontalRuleIDs.get(RuleId)[a] = 1;
			}
			if (!negativeHorizontalRuleIDs.containsKey(RuleId)){
				return 0;
			} else {
				int[] aRuleArray = negativeHorizontalRuleIDs.get(RuleId);
				res = 0;
				int x = 0;
				for (int i = b; i < rows; i++){
					x = aRuleArray[i];
					if (x > 0 && pairs.add(i)){
						res+= x;	
						
					}
				}
			}
		} else {
			int a = r.get(DOWNRIGHT_CORNER).row;
			int b = r.get(UPLEFT_CORNER).row;
			if (!negativeHorizontalRuleIDs.containsKey(RuleId)){
				int[] newRuleArray = new int[rows];
				newRuleArray[a] = 1;
				negativeHorizontalRuleIDs.put(RuleId, newRuleArray);
			} else {
				negativeHorizontalRuleIDs.get(RuleId)[a] = 1;
			}
			if (!positiveHorizontalRuleIDs.containsKey(RuleId)){
				return 0;
			} else {
				int[] aRuleArray = positiveHorizontalRuleIDs.get(RuleId);
				res = 0;
				int x = 0;
				for (int i = b; i >= 0; i--){
					
					x = aRuleArray[i];
					if (x > 0 && pairs.add(i)){
						res+= x;	
						
					}
				}
			}
		}
		return res;
	}
	
	private int checkVerticalHasAnElementOfDiagonal(Rectangle r, boolean bool) {
		/*int result = 0;
		int ruleType = 0;
		if (bool){
			ruleType = POSITIVE_VERTICAL_RULE;
		} else{
			ruleType = NEGATIVE_VERTICAL_RULE;
		}
		for (DiagonalRule d: dRules){
			if (pairs.add(d.checkIfNewRectangleSetWillHaveAnElementThatIsThisRect(r, ruleType))){
				result++;
			}
		}*/
		int res = 0;
		if (bool){
			int b = r.get(DOWNRIGHT_CORNER).col;
			String pVDiagonalID = IDMaker.makePositiveVerticalForDiagonalRuleID(r);
			if (!positiveVerticalDiagonalRuleIDs.containsKey(pVDiagonalID)){
				return 0;
			} else {
				int[] aRuleArray = positiveVerticalDiagonalRuleIDs.get(pVDiagonalID);
				res = 0;
				int x = 0;
				for (int i = b; i < cols; i++){
					x = aRuleArray[i];
					if (x > 0 && pairs.add(i)){
						res+= x;	
						
					}
				}
			}
		} else {
			int b = r.get(UPLEFT_CORNER).col;
			String nVDiagonalID = IDMaker.makeNegativeVerticalForDiagonalRuleID(r);
			if (!negativeVerticalDiagonalRuleIDs.containsKey(nVDiagonalID)){
				return 0;
			} else {
				int[] aRuleArray = negativeVerticalDiagonalRuleIDs.get(nVDiagonalID);
				res = 0;
				int x = 0;
				for (int i = b; i >= 0 ; i--){
					x = aRuleArray[i];
					if (x > 0 && pairs.add(i)){
						res+= x;		
					}
				}
			}
		}
		return res;
	}
	
	private int checkHorizontalHasAnElementOfDiagonal(Rectangle r, boolean bool) {
		/*int result = 0;
		int ruleType = 0;
		if (bool){
			ruleType = POSITIVE_HORIZONTAL_RULE;
		} else{
			ruleType = NEGATIVE_HORIZONTAL_RULE;
		}
		for (DiagonalRule d: dRules){
			if (pairs.add(d.checkIfNewRectangleSetWillHaveAnElementThatIsThisRect(r, ruleType))){
				result++;
			}
		}*/
		int res = 0;
		if (bool){
			int b = r.get(DOWNRIGHT_CORNER).row;
			String pHDiagonalID = IDMaker.makePositiveHorizontalForDiagonalRuleID(r);
			if (!positiveHorizontalDiagonalRuleIDs.containsKey(pHDiagonalID)){
				return 0;
			} else {
				int[] aRuleArray = positiveHorizontalDiagonalRuleIDs.get(pHDiagonalID);
				res = 0;
				int x = 0;
				for (int i = b; i < rows; i++){
					x = aRuleArray[i];
					if (x > 0 && pairs.add(i)){
						res+= x;	
						
					}
				}
			}
		} else {
			int b = r.get(UPRIGHT_CORNER).row;
			String nHDiagonalID = IDMaker.makeNegativeHorizontalForDiagonalRuleID(r);
			if (!negativeHorizontalDiagonalRuleIDs.containsKey(nHDiagonalID)){
				return 0;
			} else {
				int[] aRuleArray = negativeHorizontalDiagonalRuleIDs.get(nHDiagonalID);
				res = 0;
				int x = 0;
				for (int i = b; i >=0 ; i--){
					x = aRuleArray[i];
					if (x > 0 && pairs.add(i)){
						res+= x;		
					}
				}
			}
		}
		return res;
	}

	private int checkVerticalRuleHorizontalRectDoubleExpansion(Rectangle r, boolean b){
		int result = 0;
		for (VerticalRule v: vRules){
			if (pairs.add(v.checkIfBothExpansionsIntersect(r, b))){
				result++;
			}
		}
		return result;
	}


	private int checkHorizontalRuleVerticalRectDoubleExpansion(Rectangle r, boolean b){
		int result = 0;
		for (HorizontalRule v: hRules){
			if (pairs.add(v.checkIfBothExpansionsIntersect(r, b))){
				result++;
			}
		}
		return result;
	}
	
private boolean checkDiagonalinVertical(Rectangle r) {
		for (VerticalRule v: vRules){
			if (v.checkIfDiagonalRectangleIsAnElementOfRule(r)){
				return true;
			}
		}
		return false;
	}
	
	private boolean checkDiagonalinHorizontal(Rectangle r) {
		for (HorizontalRule v: hRules){
			if (v.checkIfDiagonalRectangleIsAnElementOfRule(r)){
				return true;
			}
		}
		return false;
	}

	private boolean checkDiagonalinDiagonal(Rectangle r) {
		for (DiagonalRule v: dRules){
			if (v.checkIfNewRectangleIsTheSameAsThis(r)){
				return true;
			}
		}
		return false;
	}
	
	private Rectangle getMinVerticalRectangletoExtend(Num num, Pos point,boolean fromAbove) {
		int index = -1;
		int startCol = point.col;
		int lowerRow = point.row;
		int upperRow = num.getRow();
		index = checkXGreaterThanKVertical(fromAbove, startCol, lowerRow, upperRow);
		if (index >-1){
			return new Rectangle(
					Math.min(num.getRow(), point.row),
					Math.min(num.getCol(), index),
					Math.abs(num.getRow()-point.row),
					Math.abs(num.getCol()-index));
		}
		return null;
	}

	private Rectangle getMinHorizontalRectangletoExtend(Num num, Pos point, boolean fromAbove) {
		int index = -1;
		int startRow = point.row;
		int lowerCol = point.col;
		int upperCol = num.getCol();
		index = checkXGreaterThanKHorizontal(fromAbove, startRow, lowerCol, upperCol);
		if (index >-1){
			return new Rectangle(
					Math.min(num.getRow(), index),
					Math.min(num.getCol(), point.col),
					Math.abs(num.getRow()-index),
					Math.abs(num.getCol()-point.col));
		}
		return null;
	}
	
	private Rectangle getDiagonalRectangle(Num num, Pos point) {
		if (checkXGreaterThanK(num.getRow(), point.row, num.getCol(),point.col)){
			return new Rectangle(
					Math.min(num.getRow(), point.row),
					Math.min(num.getCol(), point.col),
					Math.abs(num.getRow()-point.row),
					Math.abs(num.getCol()-point.col));
		}
		return null;
	}
	private int checkXGreaterThanKHorizontal(boolean fromAbove, int startRow, int lowerCol, int upperCol){
		int res = 0;
		if (lowerCol > upperCol){
			int temp = lowerCol;
			lowerCol = upperCol;
			upperCol = temp;
		}
		if(fromAbove){
			for (int i = startRow; i < rows; i++){
				for (int j = lowerCol; j <= upperCol; j++){
					if (matrix[i][j] == X){
						res++;
					}
				}
				if (res >= K && i > startRow){
					return i;
				}
			}
		} else {
			for (int i = startRow; i >= 0; i--){
				for (int j = lowerCol; j <= upperCol; j++){
					if (matrix[i][j] == X){
						res++;
					}
				}
				if (res >= K && i < startRow){
					return i;
				}
			}
		}
		return -1;
	}
	
	private int checkXGreaterThanKVertical(boolean fromAbove, int startCol, int lowerRow, int upperRow){
		int res = 0;
		if (lowerRow > upperRow){
			int temp = lowerRow;
			lowerRow = upperRow;
			upperRow = temp;
		}
		if(fromAbove){
			for (int i = startCol; i < cols; i++){
				for (int j = lowerRow; j <= upperRow; j++){
					if (matrix[j][i] == X){
						res++;
					}
				}
				if (res >= K && i > startCol){
					return i;
				}
			}
		} else {
			for (int i = startCol; i >= 0; i--){
				for (int j = lowerRow; j <= upperRow; j++){
					if (matrix[j][i] == X){
						res++;
					}
				}
				if (res >= K && i < startCol){
					return i;
				}
			}
		}
		return -1;
	}
	
	private boolean checkXGreaterThanK(int rowStart, int rowEnd,int colStart,
			int colEnd) {
		if (rowStart > rowEnd){
			int temp = rowStart;
			rowStart = rowEnd;
			rowEnd = temp;
		}
		if (colStart > colEnd){
			int temp = colStart;
			colStart = colEnd;
			colEnd = temp;
		}
		int result = 0;
		for (int i = rowStart; i <= rowEnd; i++){
			for (int j = colStart; j <= colEnd; j++){
				if (matrix[i][j] == X){
					result++;
				}
			}
			if (result >= K){
				return true;
			}
			
		}
		return (result>=K);
	}	

	private void resetPairs(){
		pairs = new HashSet<>();
		pairs.add(-1);
	}
}
