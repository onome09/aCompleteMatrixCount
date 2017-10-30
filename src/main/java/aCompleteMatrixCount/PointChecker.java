package aCompleteMatrixCount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


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