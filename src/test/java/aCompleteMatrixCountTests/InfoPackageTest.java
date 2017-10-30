package aCompleteMatrixCountTests;

import static org.junit.Assert.*;

import org.junit.Test;

import aCompleteMatrixCount.InfoPackage;
import aCompleteMatrixCount.Pos;
import static aCompleteMatrixCount.Cases.*;

public class InfoPackageTest {

	@Test
	public void valueTest() {
		int a = 3;
		int b = 4;
		int rel = DIAGONAL;
		InfoPackage info = new InfoPackage(rel,new Pos(a,b));
		assertEquals(rel, info.relDirection);
		assertEquals(a,info.point.row);
		assertEquals(b,info.point.col);
	}

}
