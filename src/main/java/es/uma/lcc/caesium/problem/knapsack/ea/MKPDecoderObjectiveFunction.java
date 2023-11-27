package es.uma.lcc.caesium.problem.knapsack.ea;

import java.util.HashSet;
import java.util.Set;

import es.uma.lcc.caesium.ea.base.Genotype;
import es.uma.lcc.caesium.ea.base.Individual;
import es.uma.lcc.caesium.ea.fitness.OptimizationSense;
import es.uma.lcc.caesium.ea.fitness.PermutationalObjectiveFunction;
import es.uma.lcc.caesium.problem.knapsack.MultidimensionalKnapsack;


/**
 * Objective function for the Multidimensional Knapsack Problem. Solutions are represented as 
 * a permutation of the objects, indicating the order in which they are considered for inclusion
 * (if doing so does not violate any constraint)
 * @author ccottap
 * @version 1.0
 */
public class MKPDecoderObjectiveFunction extends PermutationalObjectiveFunction {
	/**
	 * the problem instance
	 */
	private MultidimensionalKnapsack mkp;
	
	
	/**
	 * Basic constructor of the objective function
	 * @param mkp the problem instance
	 */
	public MKPDecoderObjectiveFunction(MultidimensionalKnapsack mkp) {
		super(mkp.getNumObjects());
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
	
	
	/**
	 * Decodes an MKP solution encoded in the genotype
	 * @param g the genotype of an individual
	 * @return the set of objects encoded in the genotype
	 */
	private Set<Integer> decode (Genotype g) {
		Set<Integer> sol = new HashSet<Integer>();

		// TODO completar esta función.
		//
		// El genotipo contiene una permutación de los objetos, a partir
		// de la cual hay que generar una solución válida, introduciendo
		// los objetos cuya inclusión no suponga violar ninguna restricción
		//
		
		
		return sol;
	}
	
	@Override
	protected double _evaluate(Individual ind) {		
		return mkp.solutionValue(decode(ind.getGenome()));
	}



}
