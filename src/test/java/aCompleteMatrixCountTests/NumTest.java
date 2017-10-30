package aCompleteMatrixCountTests;

import static org.junit.Assert.*;

import org.junit.Test;

import aCompleteMatrixCount.Num;
import static aCompleteMatrixCount.Cases.*;

public class NumTest {


	
	@Test
	public void canComparePositionstest() {
		Num a = new Num(1,1,5);
		Num b = new Num(2,3,5);
		Num c = new Num(3,1,8);
		Num d = new Num(4,4,11);
		Num e = new Num(5,6,2);
		int t = a.relTo(b);
		assertEquals(t,VERTICAL);
		t = b.relTo(e);
		assertEquals(t,DIAGONAL);
		t = c.relTo(a);
		assertEquals(t,HORIZONTAL);
		t = d.relTo(c);
		assertEquals(t,DIAGONAL);
		t = a.relTo(d);
		assertEquals(t,DIAGONAL);
		t = a.relTo(a);
		assertEquals(t,SAME);

	}

}
