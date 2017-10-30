package aCompleteMatrixCountTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import aCompleteMatrixCount.InfoPackage;
import aCompleteMatrixCount.Num;
import aCompleteMatrixCount.PointChecker;
import static aCompleteMatrixCount.Cases.*;


public class PointCheckerTest {

	@Test
	public void addingNumberThatHasNotBeenAddedYet() {
		PointChecker checker = new PointChecker();
		int value = 5;
		int a = 0;
		int b = 0;
		Num num1 = new Num(value, a, b);
		List<InfoPackage> l = checker.checkNum(num1);
		assertEquals(l,null);
	}
	
	@Test
	public void addingNumberThatHasBeenAddedTest() {
		PointChecker checker = new PointChecker();
		int value = 5;
		int a = 0;
		int b = 0;
		int c = 4;
		Num num1 = new Num(value, a, b);
		List<InfoPackage> l = checker.checkNum(num1);
		assertEquals(l,null);
		l = checker.checkNum(num1);
		assertEquals(l.get(0).relDirection,SAME);
		Num num2 = new Num(value, a + c, b);
		l = checker.checkNum(num2);
		assertEquals(l.get(0).relDirection,VERTICAL);
		assertEquals(l.get(1).relDirection,VERTICAL);

	}

}
