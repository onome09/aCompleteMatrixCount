package aCompleteMatrixCountTests;

import static org.junit.Assert.*;

import org.junit.Test;

import aCompleteMatrixCount.Pos;

public class PosTest {

	@Test
	public void test() {
		int a = 3;
		int b = 4;
		Pos p = new Pos(a,b);
		assertEquals(p.row,a);
		assertEquals(p.col,b);
	}

}
