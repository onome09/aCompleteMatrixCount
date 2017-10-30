package leggo;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;





public class submit {
	
	public final static int SAME = 0;
	public final static int VERTICAL = 1;
	public final static int HORIZONTAL = 2;
	public final static int DIAGONAL = 3;

	public final static int DOWNLEFT_CORNER = 1;
	public final static int UPLEFT_CORNER = 0;
	public final static int DOWNRIGHT_CORNER = 3;
	public final static int UPRIGHT_CORNER = 2;
	
	public final static int POSITIVE_HORIZONTAL_RULE = 0;
	public final static int POSITIVE_VERTICAL_RULE = 1;
	public final static int NEGATIVE_HORIZONTAL_RULE = 2;
	public final static int NEGATIVE_VERTICAL_RULE = 3;
	public final static int DIAGONAL_RULE = 4;
	
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
	
	public class HorizontalRule extends Rule{
		
		
		public HorizontalRule(Rectangle r, boolean b){
			super(r,b);
		}
		
		public boolean checkIfHorizontalRectangleIsAnElementOfRule(Rectangle r, boolean b){
			initialiseNewRect(r);
			
			if (colL == lowerCol && colU == upperCol){
				
				if (positive && b){
					return (rowL == lowerRow && rowU >= upperRow);
				} else if (!positive && !b){
					return (rowU == upperRow && rowL <= lowerRow);
				}
			}
			return false;
		}
		
		public Integer checkIfHorizontalRectangleWillBeAnElementOfHorizontalRule(Rectangle r, boolean p){
			initialiseNewRect(r);
			if (colL == lowerCol && colU == upperCol){
				if (!positive && p){
					if (upperRow >= rowU && lowerRow >= rowL){
						return upperRow;
					}
				} 
				if (!p && positive){
					if (upperRow <= rowU && lowerRow <= rowL){
						return lowerRow;
					}
				}
			}
			return -1;
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
	
	public class VerticalRule extends Rule{
		
		
		public VerticalRule(Rectangle r, boolean p){
			super(r,p);
		}
		
		public boolean checkIfVerticalRectangleIsAnElementOfRule(Rectangle r, boolean b){		
			initialiseNewRect(r);
			if (rowL == lowerRow && rowU == upperRow){	
				if (positive && b){
					return (colL == lowerCol && colU >= upperCol);
				} else if (!positive && !b){
					return (colU == upperCol && colL <= lowerCol);
				}
			}
			return false;
		}
		
		public int checkIfVerticalRectangleWillBeAnElementOfVerticalRule(Rectangle r, boolean p){
			initialiseNewRect(r);
			if (rowL == lowerRow && rowU == upperRow){
				if (!positive && p){
					if (upperCol >= colU && lowerCol >= colL){
						return upperCol;
					}
				} 
				if (!p && positive){
					if (upperCol <= colU && lowerCol <= colL){
						return lowerCol;
					}
				}
			}
			return -1;
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
	
	public class InfoPackage {
		
		public final int relDirection;
		public final Pos point;
		
		public InfoPackage(int r, Pos p){
			relDirection = r;
			point = p;
		}
	}
	
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
	
	public class PointChecker {
		
		private HashSet<Integer> values;
		private HashMap<Integer, List<Pos>> points;
		
		public PointChecker(){
			values = new HashSet<>();
			points = new HashMap<>();
		}
		
		public List<InfoPackage> checkNum(Num num){
			int v = num.getNumber();
			if (values.add(v)){
				List<Pos> l = new ArrayList<>();
				l.add(new Pos(num.getRow(),num.getCol()));
				points.put(v,l);
				return null;
			} else {
				List<Pos> l = points.get(v);
				List<InfoPackage> li = new ArrayList<>();
				for (Pos p: l){
					int t = num.relTo(new Num(0,p.row,p.col));
					li.add(new InfoPackage(t, p));
				}
				l.add(new Pos(num.getRow(),num.getCol()));
				return li;
				
			}
		}
	}
	
	public class Pos {
		public final int row;
		public final int col;
		
		public Pos(int i, int j){
			row = i;
			col = j;
		}
	}
	
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
	
	public class MatCounter {
		private int K;
		private int X;
		private int[][] matrix;
		private List<DiagonalRule> dRules;
		private List<VerticalRule> vRules;
		private List<HorizontalRule> hRules;
		private HashSet<Integer> pairs;
		PointChecker checker;
		private int no;
		private final int rows;
		private final int cols;
		private boolean dummy;
		
		public MatCounter(int[][] array, int k, int x) {
			// TODO Auto-generated constructor stub
			vRules = new ArrayList<>();
			hRules = new ArrayList<>();
			dRules = new ArrayList<>();
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
					//System.out.println(cols*i+j);
					analysePoint(i,j);
					
				}
			}
			return no;
		}
		
		private void analysePoint(int i, int j) {
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
					HorizontalRule HorizontalRule1 = analyseNPHorizontal(num, point, false);
					HorizontalRule HorizontalRule2 = analyseNPHorizontal(num, point, true);
					if (HorizontalRule1 != null){
						hRules.add(HorizontalRule1 );
					}
					if (HorizontalRule2 != null){
						hRules.add(HorizontalRule2 );
					}
		}
		
		private VerticalRule analyseNPVertical(Num num, Pos point, boolean bool) {
			// TODO Auto-generated method stub
			Rectangle r = getMinVerticalRectangletoExtend(num,point,bool);
			if (r==null ){
				return null;
			}
			dummy = false;
			int a = r.get(UPLEFT_CORNER).col;
			int b = r.get(UPRIGHT_CORNER).col;
			if (bool){
				resetPairs(b);
			}else {
				resetPairs(a);
			}
			if (checkVerticalInVertical(r,bool)){
				return null;
			}
			if (!dummy){
				no++;
			}
				
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
			// TODO Auto-generated method stub

			Rectangle r = getMinHorizontalRectangletoExtend(num,point,bool);
			if (r==null ){
				return null;
			}
			int c = r.get(UPLEFT_CORNER).row;
			int d = r.get(DOWNLEFT_CORNER).row;
			if (bool){
				resetPairs(d);
			}else {
				resetPairs(c);
			}
			dummy = false;
			if (checkHorizontalInHorizontal(r,bool)){
				return null;
			}
			if (!dummy){
				no++;
			}
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
			// TODO Auto-generated method stub
			Rectangle r = getDiagonalRectangle(num,point);
			if (r==null ){
				return;
			}
			if (checkDiagonalinDiagonal(r) ||  checkDiagonalinHorizontal(r)  || checkDiagonalinVertical(r) ){
				return;
			}
			DiagonalRule v = new DiagonalRule(r);
			no++;
			dRules.add(v);
		}
		
		private int checkVerticalHasAnElementOfVertical(Rectangle r, boolean p){
			int b = 0;
			for (VerticalRule v: vRules){
				int u = v.checkIfVerticalRectangleWillBeAnElementOfVerticalRule(r,p);
				if (pairs.add(u)){
					b++;
				}
			}
			return b;
		}
		
		private int checkHorizontalHasAnElementOfHorizontal(Rectangle r, boolean p){
			int b = 0;
			for (HorizontalRule v: hRules){
				if (pairs.add(v.checkIfHorizontalRectangleWillBeAnElementOfHorizontalRule(r,p))){
					b++;
				}
			}
			return b;
		}
		
		private boolean checkVerticalInVertical(Rectangle r, boolean b) {
			
			for (VerticalRule v: vRules){
				if (b != v.getPositive()){
					if(r.equals(v.getRectangle())){
						dummy = true;
						if (b){
							pairs.add(r.get(DOWNRIGHT_CORNER).col);
						} else {
							pairs.add(r.get(UPLEFT_CORNER).col);
						}
					}
				}
				if (v.checkIfVerticalRectangleIsAnElementOfRule(r,b)){
					return true;
				}
			}
			return false;
		}
		
		private boolean checkHorizontalInHorizontal(Rectangle r, boolean b) {
			for (HorizontalRule v: hRules){
				if (b != v.getPositive()){
					if(r.equals(v.getRectangle())){
						dummy = true;
						if (b){
							pairs.add(r.get(DOWNRIGHT_CORNER).row);
						} else {
							pairs.add(r.get(UPRIGHT_CORNER).row);
						}
					}
				}
				if (v.checkIfHorizontalRectangleIsAnElementOfRule(r,b)){
					return true;
				}
			}
			return false;
		}
		
		private int checkVerticalHasAnElementOfDiagonal(Rectangle r, boolean b) {
			int result = 0;
			int ruleType = 0;
			if (b){
				ruleType = POSITIVE_VERTICAL_RULE;
			} else{
				ruleType = NEGATIVE_VERTICAL_RULE;
			}
			for (DiagonalRule d: dRules){
				if (pairs.add(d.checkIfNewRectangleSetWillHaveAnElementThatIsThisRect(r, ruleType))){
					result++;
				}
			}
			return result;
		}
		
		private int checkHorizontalHasAnElementOfDiagonal(Rectangle r, boolean b) {
			int result = 0;
			int ruleType = 0;
			if (b){
				ruleType = POSITIVE_HORIZONTAL_RULE;
			} else{
				ruleType = NEGATIVE_HORIZONTAL_RULE;
			}
			for (DiagonalRule d: dRules){
				if (pairs.add(d.checkIfNewRectangleSetWillHaveAnElementThatIsThisRect(r, ruleType))){
					result++;
				}
			}
			return result;
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
			// TODO Auto-generated method stub
			int index = -1;
			int x = 0;
			int y = point.col;
			if (fromAbove){
				x = point.col + 1;
				y = cols;
			}	
			int c = cols+1;
			for (int j = x; j < y; j++){	
				if (checkXGreaterThanK(num.getRow(), point.row, num.getCol(),j)){
					
					if(Math.abs(j - point.col) < c){
						index = j;
						c = Math.abs(j - point.col);
					}
				}	
			}
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
			// TODO Auto-generated method stub
			int index = -1;
			int x = 0;
			int y = point.row;
			if (fromAbove){
				x = point.row + 1;
				y = rows;
			}
			int c = rows +1;
			for (int i = x; i < y; i++){
				
				if (checkXGreaterThanK(num.getRow(), i, num.getCol(),point.col)){
					if(Math.abs(i - point.row) < c){
						index = i;
						c = Math.abs(i - point.row);
					}
				}
			}
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
			// TODO Auto-generated method stub
			if (checkXGreaterThanK(num.getRow(), point.row, num.getCol(),point.col)){
				return new Rectangle(
						Math.min(num.getRow(), point.row),
						Math.min(num.getCol(), point.col),
						Math.abs(num.getRow()-point.row),
						Math.abs(num.getCol()-point.col));
			}
			return null;
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
				
			}
			return (result>=K);
		}	

		private void resetPairs(int index){
			pairs = new HashSet<>();
			pairs.add(-1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		//
	    submit sub = new submit();
		int K,X,N,M;
		int[][]array;
		MatCounter m;
		long start = System.currentTimeMillis();
        /*Scanner scanner = new Scanner(new FileReader("data.txt"));
        
        N = scanner.nextInt();
        M = scanner.nextInt();
        K = scanner.nextInt();
        X = scanner.nextInt();
        array = new int[N][M];
        for(int i = 0; i < N; i++){
        	for(int j = 0; j < M; j++){
        		array[i][j] = scanner.nextInt();
        	}
        }
        scanner.close();
        System.out.println("ms");
        m = sub.new MatCounter(array,K,X); 
        
        System.out.println(m.getNumberOfSubMatrices());
        long end = System.currentTimeMillis();

	    System.out.println((end - start) + " ms");*/
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 5;
        array = new int[][]{
				{1, 2, 1, 3, 4, 2, 1, 5},
				{4, 5, 2, 5, 5, 3, 2, 2},
				{5, 5, 5, 3, 4, 4, 5, 3},
				{3, 5, 5, 3, 3, 5, 5, 2},
				{2, 3, 1, 2, 3, 5, 1, 1},
				{1, 4, 3, 1, 5, 3, 3, 4},
				{2, 1, 2, 5, 1, 2, 1, 5},
				{4, 1, 1, 1, 5, 4, 4, 3},
				{1, 1, 5, 5, 5, 1, 5, 1},
				{2, 2, 2, 2, 3, 5, 3, 4},
        };
        m = sub.new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 552");
        System.out.println("------------------------------------------------------------------");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 1;
        array = new int[][]{
				{3, 5, 2, 3, 5, 1, 1, 5},
				{4, 3, 3, 2, 1, 2, 3, 1},
				{3, 5, 3, 1, 2, 1, 2, 4},
				{4, 5, 1, 5, 5, 4, 4, 4},
				{5, 5, 5, 4, 3, 1, 1, 5},
				{2, 2, 4, 2, 4, 2, 4, 4},
				{4, 3, 4, 2, 4, 3, 1, 1},
				{2, 5, 5, 4, 5, 4, 3, 1},
				{3, 3, 3, 2, 4, 2, 3, 5},
				{2, 3, 4, 4, 5, 5, 3, 3}
        };
        m = sub.new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 240");
        System.out.println("------------------------------------------------------------------");		
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 3;
        array = new int[][]{
				{2, 5, 2, 1, 1, 4, 2, 3, 1},
				{2, 5, 2, 2, 4, 3, 5, 4, 4},
				{5, 2, 5, 3, 1, 5, 4, 4, 3},
				{5, 1, 3, 3, 4, 2, 4, 5, 5},
				{1, 2, 3, 3, 4, 5, 2, 4, 5},
				{2, 2, 4, 1, 3, 5, 1, 5, 5},
				{5, 2, 4, 3, 1, 2, 2, 2, 1},
				{4, 2, 4, 2, 3, 5, 2, 5, 1},
				{1, 3, 2, 1, 5, 3, 2, 1, 5},
				{1, 1, 1, 5, 5, 1, 4, 5, 3}
        };
        m = sub.new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 461");
        System.out.println("------------------------------------------------------------------");	
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 5;
        array = new int[][]{
				{2, 4, 2, 2, 3, 1, 5, 4},
			    {3, 3, 4, 5, 5, 1, 5, 3},
			    {1, 2, 2, 5, 3, 3, 2, 3},
		        {5, 2, 5, 2, 5, 4, 2, 1},
		        {1, 2, 3, 1, 1, 3, 1, 4},
		        {5, 5, 4, 2, 1, 1, 2, 1},
		        {3, 5, 5, 5, 2, 4, 3, 4},
		        {2, 1, 1, 5, 4, 4, 2, 3},
		        {3, 1, 1, 1, 2, 1, 3, 2},
		        {2, 1, 5, 1, 5, 5, 1, 1}
        };
        m = sub.new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 380");
        System.out.println("------------------------------------------------------------------");	
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 2;
        array = new int[][]{
				{2, 2, 5, 1, 3, 2, 5, 5},
				{2, 3, 4, 3, 3, 5, 3, 2},
				{4, 3, 4, 3, 4, 5, 3, 2},
				{5, 3, 1, 4, 3, 5, 5, 2},
				{2, 3, 4, 4, 3, 4, 2, 1},
				{2, 3, 1, 4, 1, 5, 2, 4},
				{5, 1, 2, 4, 5, 2, 1, 4},
				{3, 4, 2, 1, 4, 3, 3, 5},
				{4, 5, 2, 1, 2, 1, 5, 2},
				{2, 5, 3, 4, 4, 5, 1, 1}
        };
        m = sub.new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 268");
        System.out.println("------------------------------------------------------------------");	
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 1;
        array = new int[][]{
				{4, 3 ,4 ,2 ,5 ,3, 2, 4},
				{3, 5, 4, 1, 4, 5, 2, 5},
				{5, 1, 4, 2, 2, 3, 3, 5},
				{5, 4, 3, 5, 4, 1, 5, 3},
				{3, 3, 2, 3, 5, 2, 5, 2},
				{3, 2, 3, 1, 5, 2, 3, 2},
				{4, 3, 4, 3, 3, 1, 3, 4},
				{3, 2, 2, 5, 3, 1, 4, 4},
				{4, 2, 3, 4, 2, 3, 4, 4},
				{1, 3, 4, 4, 1, 5, 5, 5}
        };
        m = sub.new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 105");
        System.out.println("------------------------------------------------------------------");	
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 2;
        array = new int[][]{
				{2, 5, 1, 4, 4, 5, 5, 2},
				{2, 3, 2, 1, 2, 5, 3, 4},
				{5, 1, 4, 4, 1, 3, 3, 5},
				{3, 1, 4, 2, 4, 2, 3, 5},
				{2, 3, 2, 5, 5, 2, 4, 1},
				{5, 4, 3, 4, 2, 5, 1, 2},
				{4, 5, 5, 3, 4, 5, 4, 3},
				{4, 3, 3, 5, 1, 5, 4, 4},
				{1, 4, 4, 4, 5, 1, 3, 5},
				{5, 5, 2, 5, 4, 1, 5, 4}
        };
        m = sub.new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 273");
        System.out.println("------------------------------------------------------------------");	
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 4;
        array = new int[][]{
				{1, 5, 4, 1, 3, 1, 4, 1},
				{4, 2, 1, 5, 5, 3, 1, 5},
				{1, 4, 5, 1, 2, 5, 4, 3},
				{1, 1, 2, 3, 5, 3, 2, 2},
				{1, 2, 1, 5, 5, 5, 5, 3},
				{5, 4, 1, 3, 3, 4, 3, 1},
				{4, 5, 2, 4, 3, 4, 3, 2},
				{1, 3, 2, 1, 2, 4, 3, 3},
				{5, 4, 2, 1, 3, 1, 3, 3},
				{3, 5, 2, 1, 3, 4, 1, 1}
        };
        m = sub.new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 218");
        System.out.println("------------------------------------------------------------------");	
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		K = 5;
        X = 5;
        array = new int[][]{
				{5, 2, 1, 1, 4, 4, 1, 3, 5},
				{5, 2, 3, 5, 3, 1, 1, 2, 4},
				{2, 4, 4, 2, 2, 1, 4, 2, 5},
				{5, 4, 2, 2, 5, 3, 4, 5, 4},
				{2, 4, 4, 2, 5, 5, 2, 5, 2},
				{3, 3, 2, 1, 2, 5, 4, 1, 5},
				{4, 4, 5, 2, 5, 5, 4, 4, 1},
				{2, 4, 2, 4, 4, 1, 4, 3, 2},
				{2, 4, 4, 2, 5, 2, 5, 5, 4},
				{4, 5, 5, 4, 3, 2, 3, 2, 1}
        };
        m = sub.new MatCounter(array,K,X);        
        System.out.println("Answer is " + m.getNumberOfSubMatrices() + " The answer should be 583");
        System.out.println("------------------------------------------------------------------");	
        long end = System.currentTimeMillis();

	    System.out.println((end - start) + " ms");
	}
}
