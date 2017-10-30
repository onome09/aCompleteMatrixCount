package aCompleteMatrixCountTests;

import static org.junit.Assert.*;

import org.junit.Test;

import aCompleteMatrixCount.Rectangle;
import aCompleteMatrixCount.Rule;

public class RuleTest {

	@Test
	public void creationTest() {
		int a = 1;
		int b = 2;
		int c = 5;
		int d = 4;
		boolean positive = true;
		Rectangle r = new Rectangle(a,b,c,d);
		Rule rule = new Rule(r, positive);
		assertEquals(rule.getPositive(),positive);
		assertTrue(rule.checkIfNewRectangleIsTheSameAsThis(r));
		r = new Rectangle(-a,b,-c,d);
		assertFalse(rule.checkIfNewRectangleIsTheSameAsThis(r));
	}

}
