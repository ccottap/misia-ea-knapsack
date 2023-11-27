package es.uma.lcc.caesium.problem.knapsack.ea;

import java.util.HashSet;
import java.util.Set;

import es.uma.lcc.caesium.ea.base.Genotype;
import es.uma.lcc.caesium.ea.base.Individual;
import es.uma.lcc.caesium.ea.fitness.DiscreteObjectiveFunction;
import es.uma.lcc.caesium.ea.fitness.OptimizationSense;
import es.uma.lcc.caesium.problem.knapsack.MultidimensionalKnapsack;


/**
 * Objective function for the Multidimensional Knapsack Problem. Solutions are represented as 
 * a list of the same length as number of objects, each position being 0 or 1 depending on whether 
 * the object is included or not
 * @author ccottap
 * @version 1.0
 */
public class MultidimensionalKnapsackObjectiveFunction extends DiscreteObjectiveFunction {
	/**
	 * the problem instance
	 */
	private MultidimensionalKnapsack mkp;


	
	/**
	 * Basic constructor of the objective function
	 * @param mkp the problem instance
	 */
	public MultidimensionalKnapsackObjectiveFunction(MultidimensionalKnapsack mkp) {
		super(mkp.getNumObjects(), 2);
		this.mkp = mkp;
	}

	@Override
	public OptimizationSense getOptimizationSense() {
		return OptimizationSense.MAXIMIZATION;
	}
	
	/**
	 * Returns the knapsack problem instance being solved
	 * @return the knapsack problem instance being solved
	 */
	public MultidimensionalKnapsack getProblemData () {
		return mkp;
	}
	
	
	@Override
	protected double _evaluate(Individual ind) {
		int n = mkp.getNumObjects();
		
		Set<Integer> sol = new HashSet<Integer>();
		Genotype g = ind.getGenome();
		for (int i=0; i<n; i++)
			if ((int)g.getGene(i) > 0)
				sol.add(i);
		
		double penalty = 0;
		
		// TODO completar esta función
		//
		// Comprobar la factibilidad de la solución y calcular
		// en su caso el valor de la penalización que debe
		// considerarse.
		
		if (penalty > 0)
			return -penalty;
		else
			return mkp.solutionValue(sol);
	}



}
