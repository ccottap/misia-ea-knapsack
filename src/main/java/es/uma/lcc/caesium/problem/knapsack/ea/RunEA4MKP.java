package es.uma.lcc.caesium.problem.knapsack.ea;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Locale;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import es.uma.lcc.caesium.ea.base.EvolutionaryAlgorithm;
import es.uma.lcc.caesium.ea.config.EAConfiguration;
import es.uma.lcc.caesium.ea.statistics.EntropyDiversity;
import es.uma.lcc.caesium.problem.knapsack.MultidimensionalKnapsack;

/**
 * Class for testing the evolutionary algorithm for the multidimensional knapsack
 * @author ccottap
 * @version 1.0
 */
public class RunEA4MKP {

	/**
	 * Main method
	 * @param args command-line arguments
	 * @throws FileNotFoundException if configuration file cannot be read 
	 * @throws JsonException if the configuration file is not correctly formatted
	 */
	public static void main(String[] args) throws FileNotFoundException, JsonException {
		if (args.length < 2) {
			System.out.println("Parameters: <algorithm-configuration> <problem-data>");
			System.exit(1);
		}
		
		FileReader reader = new FileReader(args[0] + ".json");
		EAConfiguration conf = new EAConfiguration((JsonObject) Jsoner.deserialize(reader));
		
		int numruns = conf.getNumRuns();
		System.out.println(conf);
		EvolutionaryAlgorithm myEA = new EvolutionaryAlgorithm(conf);
		
		MultidimensionalKnapsack mkp = new MultidimensionalKnapsack(args[1] + ".dat");
		System.out.println(mkp);
		if (args[0].contains("decoder"))
			myEA.setObjectiveFunction(new MKPDecoderObjectiveFunction(mkp));
		else
			myEA.setObjectiveFunction(new MultidimensionalKnapsackObjectiveFunction(mkp));
		
		myEA.getStatistics().setDiversityMeasure(new EntropyDiversity());

		for (int i=0; i<numruns; i++) {
			myEA.run();
			System.out.println ("Run " + i + ": " + 
								String.format(Locale.US, "%.2f", myEA.getStatistics().getTime(i)) + "s\t" +
								myEA.getStatistics().getBest(i).getFitness());
		}
		PrintWriter file = new PrintWriter(args[0] + "-stats-" + args[1] + ".json");
		file.print(myEA.getStatistics().toJSON().toJson());
		file.close();
	}
	
	
	
	

}
