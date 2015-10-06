package year2015;

public class ProblemB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	


}

class Polygon {
	Line[] sides;
	int v;
	
	public Polygon(int[] x, int[] y, int v) {
		sides = new Line[x.length];
		for (int i = 0; i < sides.length; i++) {
			int j = i + 1 % sides.length;
			sides[i] = new Line(new Point(x[i], y[i]), new Point(x[j], y[j]));
		}
		this.v = v;
	}
	
	public void computeIntersection(Polygon other) {
		
	}
}

class Line {
	Point start;
	Point end;
	double m;
	double b;
	
	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
		m = (start.y - end.y) / (start.x - end.x);
		b = start.y - m * start.x;
	}
	
	public Point computeIntersection(Line other) {
		
		
		return null;
	}
}

class Point {
	double x;
	double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}