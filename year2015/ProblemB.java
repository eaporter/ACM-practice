package year2015;

import java.util.Scanner;

/**
 * @author eaporter
 */
public class ProblemB {

	/**
	 * Program to calculate the time at which two polygons moving in the xy plane have maximum
	 * overlap.
	 */
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		Polygon firstPolygon = readInput(input);
		Polygon secondPolygon = readInput(input);
		
		// TODO minimize distance between polygon centers
		
		// TODO calculate overlap at minimized distance
		
		// TODO write output

	}
	
	public static Polygon readInput(Scanner input) {
		int n = input.nextInt();
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = input.nextInt();
			y[i] = input.nextInt();
		}
		int v_x = input.nextInt();
		int v_y = input.nextInt();
		return new Polygon(x, y, v_x, v_y);
	}

}

/*
 * Class representing a polygon with an arbitrary number of sides
 */
class Polygon {
	Line[] sides;	// Sides of the polygon
	int v_x;		// Velocity in the x direction
	int v_y;		// Velocity in the y direction
	
	/**
	 * x and y must be the same length. The first point in x and y is assumed to also be the
	 * last point.
	 * @param x		x coordinates of the points in the polygon
	 * @param y		y coordinates of the points in the polygon
	 * @param v_x	Velocity of the polygon in the x direction
	 * @param v_y	Velocity of the polygon in the y direction
	 */
	public Polygon(int[] x, int[] y, int v_x, int v_y) {
		sides = new Line[x.length];
		// Create lines from each point.
		for (int i = 0; i < sides.length; i++) {
			int j = i + 1 % sides.length;
			sides[i] = new Line(new Point(x[i], y[i]), new Point(x[j], y[j]));
		}
		this.v_x = v_x;
		this.v_y = v_y;
	}
	
	/**
	 * Method to compute the area of overlap between two polygons
	 * @param other	polygon with which to compute the overlap
	 * @return		overlapping area
	 */
	public double computeOverlap(Polygon other) {
		
		// TODO
		
		return 0.0;
	}
	
	/**
	 * Find the center of the polygon
	 * @return
	 */
	public Point findCenter() {
		
		// TODO
		
		return null;
	}
}

/*
 * Class to represent a line, with start and end points
 */
class Line {
	Point start; // Initial point on the line
	Point end; // Final point on the line
	double m; // Slope of the line
	double b; // y-intercept of the line
	
	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
		m = (start.y - end.y) / (start.x - end.x);
		b = start.y - m * start.x;
	}
	
	/**
	 * Method to compute the point at which this line intersects with another line
	 * @param other line with which this line may or may not intersect
	 * @return		point at which the two lines intersect, if there is one
	 * 				null otherwise
	 */
	public Point computeIntersection(Line other) {
		
		// TODO
		
		return null;
	}
}

/*
 * Class to represent a point in an xy coordinate plane
 */
class Point {
	double x;
	double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}