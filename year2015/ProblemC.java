package year2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author eaporter
 * 
 */
public class ProblemC {

	/**
	 * Program to calculate the minimum cost of moving teams between locations,
	 * where each location must be visited once, there are a finite number of
	 * teams, each team can visit zero or more locations, and the locations are
	 * ordered such for any two locations, a team needs to be present at
	 * location i before location j where i < j. Teams can move from any i to
	 * any j as long as i < j.
	 * 
	 * My solution represents the cost of moving teams between locations with a
	 * directed graph in the form of an adjacency matrix. Because the locations
	 * are ordered, there will cannot be any loops in the solution, but having
	 * multiple teams requires that the paths of all teams be considered
	 * together to ensure that all nodes have been visited.
	 */
	public static void main(String[] args) {

		bruteForceSoln();
	}

	/**
	 * Simple brute force solution that checks every valid combination of teams
	 * visiting locations and finds the smallest one. Runs in decent time on
	 * inputs up to ~13 locations.
	 */
	public static void bruteForceSoln() {
		Scanner input = new Scanner(System.in);
		int numLocations = input.nextInt();
		int numTeams = input.nextInt();
		int[][] adjacencyMatrix = readInput(input, numLocations);
		int[] startingFromLocation = new int[numLocations];
		int cost = bruteForceCost(adjacencyMatrix, startingFromLocation,
				numTeams, numLocations);
		System.out.println(cost);
	}

	/**
	 * Recursive method to calculate the minimum cost of moving teams between
	 * locations.
	 * 
	 * @param adjacencyMatrix
	 *            Adjacency matrix representing costs of movement between
	 *            locations
	 * @param startingFromLocation
	 *            Array to track number of teams originating at a particular
	 *            location. Used to prevent more than numTeams starting at the
	 *            origin and more than 1 team starting anywhere else
	 * @param numTeams
	 *            Number of teams available
	 * @param numLocations
	 *            Number of locations left to visit
	 * @return Cost of visiting numLocations visited so far
	 */
	public static int bruteForceCost(int[][] adjacencyMatrix,
			int[] startingFromLocation, int numTeams, int numLocations) {
		int minCost = Integer.MAX_VALUE;
		if (numLocations <= 0) { // base case: no more locations to visit, start
									// building up cost from 0
			return 0;
		}
		for (int i = 0; i < numLocations; i++) { // check cost for each possible
													// origin from which one can
													// move to the current
													// location
			startingFromLocation[i]++; // increment location to start from
			if ((i == 0 && startingFromLocation[i] > numTeams)
					|| (i > 0 && startingFromLocation[i] > 1)) { // don't
																	// consider
																	// paths if
																	// constraints
																	// on where
																	// teams can
																	// start
																	// from are
																	// violated
				startingFromLocation[i]--;
				continue;
			}
			int cost = bruteForceCost(adjacencyMatrix, startingFromLocation,
					numTeams, numLocations - 1); // recursively check paths for
													// locations with smaller
													// indices
			if (cost < Integer.MAX_VALUE) {
				cost += adjacencyMatrix[i][numLocations - 1];
				if (cost < minCost) { // update minCost if new cost is better
					minCost = cost;
				}
			}
			startingFromLocation[i]--; // reset startingFromLocation to check
										// next location
		}
		return minCost;
	}

	/**
	 * Optimized solution in progress
	 */
	public static void optimizedSoln() {
		Scanner input = new Scanner(System.in);
		int numLocations = input.nextInt();
		int numTeams = input.nextInt();
		int[][] adjacencyMatrix = readInput(input, numLocations);
		int[] pathsFollowed = new int[numLocations];
		int[][] sortedIndices = new int[numLocations][];
		int[] indexPointer = new int[numLocations];
		for (int i = 0; i < numLocations; i++) {
			pathsFollowed[i] = 0;
		}
		for (int i = 0; i < numLocations; i++) {
			pathsFollowed = addIndex(adjacencyMatrix, pathsFollowed,
					sortedIndices, indexPointer, numLocations, numTeams, i + 1);
		}
	}

	public static int[] addIndex(int[][] adjacencyMatrix, int[] pathsFollowed,
			int[][] sortedIndices, int[] indexPointer, int numLocations,
			int numTeams, int index) {
		boolean hasConflict = false;
		sortedIndices[index] = new int[index];
		indexPointer[index] = 0;
		for (int i = 0; i <= index; i++) {
			int sortedIndex = 0;
			while (sortedIndex <= i
					&& adjacencyMatrix[index][i] > adjacencyMatrix[index][sortedIndex]) {
				sortedIndex++;
			}
			sortedIndices[index][sortedIndex] = i;
		}
		int sortedIndex = 0;
		do {
			if (sortedIndices[index][sortedIndex] == 0) {
				int[] conflicts = getConflicts(pathsFollowed,
						sortedIndices[index][sortedIndex], index, numTeams);
				if (conflicts != null) {

				}
			} else {
				int conflict = getConflict(pathsFollowed,
						sortedIndices[index][sortedIndex], index);
				if (conflict != -1) {
					int otherBest = adjacencyMatrix[conflict][sortedIndex];
					int otherNext = adjacencyMatrix[conflict][sortedIndex + 1];
				}
			}
		} while (hasConflict);

		return pathsFollowed;
	}

	public static int getConflict(int[] pathsFollowed, int index,
			int locationNum) {
		for (int i = 0; i < locationNum; i++) {
			if (pathsFollowed[i] == index) {
				return i;
			}
		}
		return -1;
	}

	public static int[] getConflicts(int[] pathsFollowed, int index,
			int locationNum, int numTeams) {
		int[] conflicts = new int[numTeams];
		int conflictIndex = 0;
		for (int i = 0; i < locationNum; i++) {
			if (pathsFollowed[i] == index) {
				conflicts[conflictIndex++] = i;
			}
		}
		if (conflictIndex >= numTeams) {
			return conflicts;
		} else {
			return null;
		}
	}

	public static int calcValue(int[][] adjacencyMatrix, int[] pathsFollowed) {
		int value = 0;
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix.length; j++) {
				value += adjacencyMatrix[pathsFollowed[i]][i];
			}
		}
		return value;
	}

	/**
	 * Method to read input and convert into data structure
	 * 
	 * @param input
	 *            Scanner from which the input is read
	 * @param numLocations
	 *            Number of locations to visit. Determines the amount of input
	 *            expected and size of matrix generated
	 * @return Adjacency matrix representing costs for visiting numLocations
	 *         locations
	 */
	public static int[][] readInput(Scanner input, int numLocations) {
		int[][] adjacencyMatrix = new int[numLocations][numLocations];
		for (int i = 0; i < numLocations; i++) {
			for (int j = 0; j < i; j++) {
				adjacencyMatrix[i][j] = Integer.MAX_VALUE;
			}
			for (int j = i; j < numLocations; j++) {
				adjacencyMatrix[i][j] = input.nextInt();
			}
		}
		return adjacencyMatrix;
	}

	/**
	 * Method to generate random input for testing running time of different
	 * approaches.
	 * 
	 * @param input
	 *            Scanner given as a parameter to keep method signature
	 *            consistent with readInput, to allow easier switching between
	 *            the methods for testing purposes
	 * @param numLocations
	 *            Number of locations to visit. Determines size of matrix
	 *            generated
	 * @return Adjacency matrix representing costs for visiting numLocations
	 *         locations
	 */
	public static int[][] generateInput(Scanner input, int numLocations) {
		int[][] adjacencyMatrix = new int[numLocations][numLocations];
		Random r = new Random();
		for (int i = 0; i < numLocations; i++) {
			for (int j = 0; j < i; j++) {
				adjacencyMatrix[i][j] = Integer.MAX_VALUE;
			}
			for (int j = i; j < numLocations; j++) {
				adjacencyMatrix[i][j] = r.nextInt(1000);
				System.out.print(adjacencyMatrix[i][j] + " ");
			}
			System.out.println();
		}
		return adjacencyMatrix;
	}

	public static int[][] computeDifference(int[][] adjacencyMatrix) {
		int locations = adjacencyMatrix.length;
		int[][] difference = new int[locations - 1][locations - 1];
		for (int i = 0; i < locations - 1; i++) {
			for (int j = i + 1; j < locations - 1; j++) {
				int costSeparate = adjacencyMatrix[i][i]
						+ adjacencyMatrix[i][j];
				int costTogether = adjacencyMatrix[i][i]
						+ adjacencyMatrix[i + 1][j];
				difference[i][j - 1] = costSeparate - costTogether;
			}
		}
		return difference;
	}

	public static boolean[][] choosePaths(int[][] adjacencyMatrix) {
		boolean[][] pathFollowed = new boolean[adjacencyMatrix.length][adjacencyMatrix.length];
		for (int i = 0; i < pathFollowed.length; i++) {
			pathFollowed[0][i] = true;
		}
		int[][] difference = computeDifference(adjacencyMatrix);
		int[] maxDifference = new int[difference.length];
		for (int i = 0; i < difference.length; i++) {
			maxDifference[i] = maxDifference(difference, i);
			if (difference[i][maxDifference[i]] > 0) {
				pathFollowed[i + 1][i + 1] = true;
			}
		}

		return pathFollowed;
	}

	public static int maxDifference(int[][] difference, int row) {
		int maxDifference = row;
		for (int i = row + 1; i < difference.length; i++) {
			if (difference[row][i] > difference[row][maxDifference]) {
				maxDifference = i;
			}
		}
		return maxDifference;
	}
}
