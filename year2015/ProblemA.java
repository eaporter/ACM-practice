package year2015;

import java.util.Scanner;

public class ProblemA {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int p = s.nextInt();
		int a = s.nextInt();
		int b = s.nextInt();
		int c = s.nextInt();
		int d = s.nextInt();
		int n = s.nextInt();
		s.close();
		double max = 0.0;
		double diff = 0.0;
		for (int i = 1; i <= n; i++) {
			double value = price(p, a, b, c, d, i) ;
			if (value > max) {
				max = value;
			}
			if (max - value > diff) {
				diff = max - value;
			}
		}
		System.out.println(diff);
	}
	
	public static double price(int p, int a, int b, int c, int d, int k) {
		return p * (Math.sin(a * k + b) + Math.cos(c * k + d) + 2);
	}

}
