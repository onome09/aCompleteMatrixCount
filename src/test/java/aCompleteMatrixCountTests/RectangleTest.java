package aCompleteMatrixCountTests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import aCompleteMatrixCount.Pos;
import aCompleteMatrixCount.Rectangle;
import static aCompleteMatrixCount.Cases.*;

public class RectangleTest {

	@Test
	public void creationTest() {
		Rectangle r = new Rectangle(0,0,3,4);
		Pos p = r.get(UPLEFT_CORNER);
		assertEquals(p.col,0);
		assertEquals(p.row,0);
		p = r.get(UPRIGHT_CORNER);
		assertEquals(p.col,4);
		assertEquals(p.row,0);
		
		p = r.get(DOWNRIGHT_CORNER);
		assertEquals(p.col,4);
		assertEquals(p.row,3);
		p = r.get(DOWNLEFT_CORNER);
		assertEquals(p.col,0);
		assertEquals(p.row,3);
		
	}
	
	@Test
	public void equalityTest() {
		Rectangle r1 = new Rectangle(0,0,3,4);
		Rectangle r2 = new Rectangle(0,0,3,4);
		Rectangle r3 = new Rectangle(0,1,3,4);
		assertTrue(r1.equals(r2));
		assertFalse(r2.equals(r3));	
	}
	
	@Test
	public void gettingNumberTest() {
		int a = 3;
		int b = 4;
		int c = 2;
		Rectangle r = new Rectangle(c,c,a,b);
		Pos p = r.get(DOWNRIGHT_CORNER);
		assertEquals(p.col,b+c);
		assertEquals(p.row,a+c);
	}

}
