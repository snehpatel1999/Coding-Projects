
/**
 * TODO Put here a description of what this class does.
 *
 * @author sneh.
 *         Created Oct 9, 2019.
 */
public class Test {
	
	public static int min(int x, int y) {
		return 0;
	}
	
	
}

public class ForYouTest extends TestCase{
	assertEquals(1, Test.min(1, 2));
	assertEquals(2, Test.min(2, 3));
	assertEquals(3, Test.min(3, 4));
	assertEquals(4, Test.min(4, 5));
}