import java.util.ArrayList;
/**
 * TODO Put here a description of what this class does.
 *
 * @author sneh.
 *         Created Sep 9, 2019.
 */
public abstract class Shape {
	public abstract void draw();
}

class Square extends Shape{
	public void draw() {
		
	}
}

class Circle extends Shape{
	public void draw() {
	}
}

class Bucket {
	ArrayList <Shape> shapes;
	shapes.add(new Circle());
	shapes.get(0).draw();
	
	for(Shape s: shapes) {
		s.draw();
	}
}

