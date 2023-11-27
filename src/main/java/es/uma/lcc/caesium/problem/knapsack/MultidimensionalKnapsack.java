
package es.uma.lcc.caesium.problem.knapsack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

/**
 * 0-1 Multidimensional Knapsack Problem
 * @author ccottap
 * @version 1.0
 */
public class MultidimensionalKnapsack {
	/**
	 * number of objects
	 */
	private int numObjects;			
	/**
	 * number of objects
	 */
	private int numKnapsacks;
	/**
	 * optimal value (0 if unknown)
	 */
	private int optimal;
	/**
	 * weight of each object
	 */
	private int[][] weights;	
	/**
	 * value of each object
	 */
	private int[] values;		
	/**
	 * capacity of the knapsacks
	 */
	private int[] W;			
	/**
	 * class-level random generator;
	 */
	private static Random r =  new Random(1);
	
	/**
	 * Sets the seed for the RNG
	 * @param seed the seed for the RNG
	 */
	public static void setSeed (long seed) {
		r.setSeed(seed);
	}
	
	/**
	 * Creates an empty knapsack problem instance (no objects, no knapsack).
	 * 
	 */
	public MultidimensionalKnapsack() {
		setSize(0, 0);
	}
	
	/**
	 * Creates a knapsack problem instance of a certain size. 
	 * Randomizes the knapsack capacity and the values and weights
	 * of the objects.
	 * 
	 * @param n is the number of objects
	 * @param m is the number of knapsacks
	 */
	public MultidimensionalKnapsack(int n, int m) {
		setSize(n, m);
		randomize();
	}
	
	/**
	 * Maximum value of an object when initializing at random
	 */
	private static final int MAX_VAL = 100;
	/**
	 * Maximum weight of an object when initializing at random
	 */
	private static final int MAX_WEIGHT = 100;
	/**
	 * Randomizes the values and weights of the objects in the knapsack
	 */
	private void randomize() {
		for (int j=0; j<numKnapsacks; j++) 
			W[j] = 0;
		
		for (int i=0; i<numObjects; i++) {
			values[i] = r.nextInt(MAX_VAL);
			for (int j=0; j<numKnapsacks; j++) {
				weights[j][i] = r.nextInt(MAX_WEIGHT);
				W[j] += weights[j][i];
			}
		}
		
		for (int j=0; j<numKnapsacks; j++) 
			W[j]/=2;
		
		optimal = 0;
		
	}

	/**
	 * Reads a knapsack problem instance from a file
	 * @param filename the name of the file
	 * @throws FileNotFoundException if the file is not found
	 */
	public MultidimensionalKnapsack(String filename) throws FileNotFoundException
	{
		Scanner inputFile = new Scanner (new File(filename));

		numObjects = inputFile.nextInt();
		numKnapsacks = inputFile.nextInt();
		setSize(numObjects, numKnapsacks);
		optimal = inputFile.nextInt();
		for (int i=0; i<numObjects; i++) {
			values[i] = inputFile.nextInt();
		}
		for (int j=0; j<numKnapsacks; j++) {
			for (int i=0; i<numObjects; i++)
				weights[j][i] = inputFile.nextInt();
		}
		for (int j=0; j<numKnapsacks; j++) {
			W[j] = inputFile.nextInt();
		}
		inputFile.close();
	}
	
	
	/**
	 * Writes a knapsack problem instance to file
	 * @param filename the name of the file
	 * @throws IOException if the file cannot be opened for writing
	 */
	public void writeToFile(String filename) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter(filename));
     	
     	out.println(numObjects + "\t" + numKnapsacks + "\t" + optimal);
     	for (int i=0; i<numObjects; i++) {
     		out.print(values[i] + "\t" );
     	}
     	out.println();
     	for (int j=0; j<numKnapsacks; j++) {
			for (int i=0; i<numObjects; i++)
				out.print(weights[j][i] + "\t");
			out.println();
		}
     	for (int j=0; j<numKnapsacks; j++) {
     		out.print(W[j] + "\t");
     	}
     	out.println();
     	out.close();
	}
	
	
	/**
	 * Returns the number of objects {@link MultidimensionalKnapsack#numObjects}
	 * @return the number of objects
	 */
	public int getNumObjects() {
		return numObjects;
	}
	
	/**
	 * Returns the number of knapsacks {@link MultidimensionalKnapsack#numKnapsacks}
	 * @return the number of knapsacks
	 */
	public int getNumKnapsacks() {
		return numKnapsacks;
	}

	/**
	 * Sets the number of objects {@link MultidimensionalKnapsack#numObjects} and the number
	 * of knapsacks {@link MultidimensionalKnapsack#numKnapsacks} (and allocates memory accordingly
	 * for {@link MultidimensionalKnapsack#values} and {@link MultidimensionalKnapsack#weights})
	 * @param n the number of objects 
	 * @param m the number of knapsacks
	 */
	public void setSize(int n, int m) {
		this.numObjects = n;
		this.numKnapsacks = m;
		weights = new int[m][n];
		values = new int[n];
		W = new int[m];
	}

	/**
	 * Returns the weight of an object in a knapsack
	 * @param i the index of the object
	 * @param j the index of the knapsack
	 * @return the weight of the object
	 */
	public int getWeight(int i, int j) {
		return weights[j][i];
	}

	/**
	 * Sets the weight of an object in a knapsack
	 * @param i the index of the object
	 * @param j the index of the knapsack
	 * @param w the weight to set
	 */
	public void setWeight(int i, int j, int w) {
		weights[j][i] = w;
	}

	/**
	 * Gets the value of an object
	 * @param i the index of the object
	 * @return the value of an object
	 */
	public int getValue(int i) {
		return values[i];
	}

	/**
	 * Sets the value of an object
	 * @param i the index of an object
	 * @param v the value to set
	 */
	public void setValue(int i, int v) {
		values[i] = v;
	}

	/**
	 * Gets the capacity {@link MultidimensionalKnapsack#W} of a knapsack 
	 * @param j the index of the knapsack
	 * @return the capacity of the knapsack
	 */
	public int getW(int j) {
		return W[j];
	}

	/**
	 * Sets the capacity {@link MultidimensionalKnapsack#W} of a knapsack
	 * @param j the index of the knapsack
	 * @param w the capacity of the knapsack
	 */
	public void setW(int j, int w) {
		W[j] = w;
	}
	
	
	/**
	 * Returns the total value of a solution represented as a collection of 
	 * integers
	 * @param sol the solution to be evaluated
	 * @return the total value of objects in the solution
	 */
	public int solutionValue (Collection<Integer> sol)
	{
		int val = 0;
		for (int i: sol) {
			val += values[i];
		}
		return val;
	}

	/**
	 * Returns the total weight of a solution -represented as a collection of 
	 * integers- in a knapsack
	 * @param sol the solution to be evaluated
	 * @param j the index of the knapsack
	 * @return the total weight of objects in the solution
	 */
	public int solutionWeight (Collection<Integer> sol, int j)
	{
		int wei = 0;
		for (int i: sol) {
			wei += weights[j][i];
		}
		return wei;
	}
	 
	/**
	 * Indicates whether a solution is feasible (i.e., its weight is no more than
	 * all knapsack capacities).
	 * @param sol the solution
	 * @return whether the solution is feasible or not
	 */
	public boolean isFeasible (Collection<Integer> sol) {
		for (int i=0; i<numKnapsacks; i++)
			if (solutionWeight(sol, i) > W[i])
				return false;
		return true;
	}
	
	
	/**
	 * Returns a printable string representation of the knapsack
	 * @return a string representing the knapsack
	 */
	public String toString()
	{
		String cad;
		
		cad = "Optimal:\n\t" + optimal + "\nValues: \n";
		for (int i=0; i<numObjects; i++)
			cad += "\t" + values[i];
		cad += "\nWeights:\n";
		for (int j=0; j<numKnapsacks; j++) {
			for (int i=0; i<numObjects; i++)
				cad += "\t" + weights[j][i];	
			cad += "\n";
		}
		cad += "Capacities:\n";
		for (int j=0; j<numKnapsacks; j++) 
			cad += W[j] + "\t";

		
		return cad;
	}
	
	
	public static void main(String[] args) throws IOException {
		MultidimensionalKnapsack mkp = new MultidimensionalKnapsack("sento1.dat");
		System.out.println(mkp);
		System.out.println(mkp.getNumObjects() + " " + mkp.getNumKnapsacks());
		System.out.println(mkp.getW(4) + " " + mkp.getValue(3) + " " + mkp.getWeight(5, 3));
		System.out.println(mkp.getW(4) + " " + mkp.getValue(3) + " " + mkp.getWeight(5, 3));
		mkp.writeToFile("sento1.mkp");
		MultidimensionalKnapsack mkp2 = new MultidimensionalKnapsack("sento1.mkp");
		System.out.println(mkp2);
		System.out.println(mkp2.getNumObjects() + " " + mkp2.getNumKnapsacks());
		System.out.println(mkp2.getW(4) + " " + mkp2.getValue(3) + " " + mkp2.getWeight(5, 3));
	}
	
}
