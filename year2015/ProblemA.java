package year2015;

import java.util.Scanner;

public class ProblemA {

	/**
	 * Program to calculate the largest decline in stock prices over an interval of time,
	 * where the input (read from System.in) consists of 6 values:
	 * 		p, a, b, c, d: parameters for calculating the price at time k according to
	 * 		the formula price(k) = p * (sin(a * k + b) + cos(c * k + d) + 2).
	 * 		n: the number of times 1,2,...n at which to consider the price
	 * The largest decline is calculated by calculating the price at each time and tracking both
	 * the maximum price seen so far and the maximum decline seen so far, so that if the decline
	 * between the maximum and the current price is greater than the maximum decline thus far,
	 * the decline is updated, resulting in the final decline representing the maximum decline
	 * over the entire time period.
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		// Read the six inputs p, a, b, c, d, and n
		int p = s.nextInt();
		int a = s.nextInt();
		int b = s.nextInt();
		int c = s.nextInt();
		int d = s.nextInt();
		int n = s.nextInt();
		s.close();
		// Initial maximum and decline are 0.
		double max = 0.0;
		double decline = 0.0;
		// Loop through each time from 1 to n
		for (int i = 1; i <= n; i++) {
			double value = price(p, a, b, c, d, i) ;
			if (value > max) { // Update the maximum price
				max = value;
			}
			if (max - value > decline) { // Update the maximum decline
				decline = max - value;
			}
		}
		// Output the maximum decline in price
		System.out.println(decline);
	}
	
	/**
	 * Calculate the price at time k according to the formula
	 * price(k) = p * (sin(a * k + b) + cos(c * k + d) + 2).
	 * 
	 * @param p constant
	 * @param a constant
	 * @param b constant
	 * @param c constant
	 * @param d constant
	 * @param k time at which to calculate the price
	 * @return price
	 */
	public static double price(int p, int a, int b, int c, int d, int k) {
		return p * (Math.sin(a * k + b) + Math.cos(c * k + d) + 2);
	}

}
