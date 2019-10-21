import static org.junit.Assert.*;

import org.junit.Test;

public class for2ptsTest {
	
	@Test
	public void testFor2pts() {
	}

	@Test
	public void testMakeOutWord() {
		String s = for2pts.makeOutWord("<<>>", "Yay");
		assertEquals("<<Yay>>", s);
	}

	@Test
	public void testSeeColor() {
		String s = for2pts.seeColor("bluexxxx");
		assertEquals("blue", s);
	}

	@Test
	public void testExtraEnd() {
		String s = for2pts.extraEnd("He");
		assertEquals("HeHeHe", s);
	}

	@Test
	public void testHasBad() {
		boolean sol = for2pts.hasBad("xbadx");
		assertEquals(true, sol);
	}

	@Test
	public void testConCat() {
		String s = for2pts.conCat("", "abc");
		assertEquals("abc", s);
	}

	@Test
	public void testFrontAgain() {
		boolean sol = for2pts.frontAgain("edited");
		assertEquals(true, sol);
	}

	@Test
	public void testWithout2() {
		String s = for2pts.without2("Hi");
		assertEquals("", s);
	}

	@Test
	public void testComboString() {
		String s = for2pts.comboString("123", "AbcDef");
		assertEquals("123AbcDef123", s);
	}

	@Test
	public void testMinCat() {
		String s = for2pts.minCat("He", "Hello");
		assertEquals("Helo", s);
	}

	@Test
	public void testDeFront() {
		String s = for2pts.deFront("sbba");
		assertEquals("bba", s);
	}

	@Test
	public void testCommonEnd() {
		int[] A = {1,2,3};
		int[] B = {1,2};
		boolean sol = for2pts.commonEnd(A, B);
		assertEquals(true, sol);
	}

	@Test
	public void testReverse3() {
		int[] A = {7,0,0};
		int[] B = {0,0,7};
		int[] sol = for2pts.reverse3(A);
		for(int i = 0; i < A.length; i++) {
			assertEquals(B[i], sol[i]);
		}
	}

	@Test
	public void testMaxTriple() {
		fail("Not yet implemented");
	}

	@Test
	public void testHas23() {
		fail("Not yet implemented");
	}

	@Test
	public void testSwapEnds() {
		fail("Not yet implemented");
	}

	@Test
	public void testFront11() {
		fail("Not yet implemented");
	}

	@Test
	public void testSquirrelPlay() {
		fail("Not yet implemented");
	}

	@Test
	public void testCaughtSpeeding() {
		fail("Not yet implemented");
	}

	@Test
	public void testMaxMod5() {
		fail("Not yet implemented");
	}

	@Test
	public void testInOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testSpecialEleven() {
		fail("Not yet implemented");
	}

	@Test
	public void testAnswerCell() {
		fail("Not yet implemented");
	}

}
