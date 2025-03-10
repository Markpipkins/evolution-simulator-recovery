package mainApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Generation {
	private int numberOfGenerations;
	private int maxFitness;
	private int averageFitness;
	private int minFitness;
	public ArrayList<Chromosome> chromosomeList;
	private int mutability;
	private int size = 100;
	public double hammingdbl;

//	for (Chromosome chromosome : chromosomeList) {
//	System.out.print(" "+chromosome.getFitness()+ ",");
//	
//}

	public Generation(int size, Chromosome seedingGenome) {
		this.size = 0;
		this.chromosomeList = new ArrayList<Chromosome>();
		for (int i = 0; i < size; i++) {
			chromosomeList.add(new Chromosome(seedingGenome.getGenome()));
		}
		numberOfGenerations = 0;
	}

	public Generation(String generation, long seed) {
		this.chromosomeList = new ArrayList<Chromosome>();
		Random rnd = null;
		if (generation.equals("seeded")) {
			rnd = new Random(seed);
//			System.out.println("pong");
		} else if (generation.equals("random")) {
			rnd = new Random();
//			System.out.println("ping");
		}

		for (int i = 0; i < this.size; i++) {
			ArrayList<Integer> newGenome = new ArrayList<Integer>();
			for (int k = 0; k < 100; k++) {
				if (rnd.nextBoolean()) {
					newGenome.add(0);
				} else {
					newGenome.add(1);
				}
//				System.out.println(newGenome.get(k));
			}

			chromosomeList.add(new Chromosome(newGenome));
		}

		Collections.sort(chromosomeList);
//		System.out.println(chromosomeList);
		this.maxFitness = chromosomeList.get(0).getFitness();
		this.minFitness = chromosomeList.get(this.size - 1).getFitness();

	}

	public Generation(Generation oldGeneration) {
		this.numberOfGenerations = oldGeneration.numberOfGenerations + 1;
//		this.maxFitness = 0;
		this.chromosomeList = new ArrayList<Chromosome>();
		for (int i = 0; i < oldGeneration.chromosomeList.size(); i++) {
			this.chromosomeList.add(new Chromosome(oldGeneration.chromosomeList.get(i).getGenome()));
		}
	}

	public ArrayList<Chromosome> elitism(double d) {
		ArrayList<Chromosome> chosen = new ArrayList<Chromosome>();

		double elitismPct = ((d * this.chromosomeList.size()) / 100);
//		System.out.println(elitismPct);
		int eliteSize = (int) Math.ceil(elitismPct);

		Collections.sort(chromosomeList);

		for (int i = 0; i < eliteSize; i++) {

			Chromosome pull = this.chromosomeList.remove(0);
			chosen.add(pull);

		}
		this.size = chromosomeList.size();
		for (Chromosome chromosome : chosen) {
//		System.out.print(" "+chromosome.getFitness()+ ",");
		
	}
//		System.out.println();
		return chosen;
	}

	public void crossover() {
		ArrayList<Chromosome> newList = new ArrayList<Chromosome>();
		Random rnd = new Random();

		for (int i = 1; i < this.chromosomeList.size(); i += 2) {
			ArrayList<Integer> newA = new ArrayList<Integer>();
			ArrayList<Integer> newB = new ArrayList<Integer>();

			int swap = rnd.nextInt(this.chromosomeList.get(0).getGenome().size());

			for (int j = 0; j < this.chromosomeList.get(0).getGenome().size(); j++) {
				if (j < swap) {
					newA.add(this.chromosomeList.get(i).getGenome().get(j));
					newB.add(this.chromosomeList.get(i - 1).getGenome().get(j));
				} else {
					newA.add(this.chromosomeList.get(i - 1).getGenome().get(j));
					newB.add(this.chromosomeList.get(i).getGenome().get(j));
				}

			}
			newList.add(new Chromosome(newA));
			newList.add(new Chromosome(newB));
		}
		this.chromosomeList = newList;
	}

	public void evolve(double mutability, String fitnessMethod, String selectionMethod) {
		for (Chromosome i : this.chromosomeList) {
			i.mutation(mutability);
			i.calculateFitness(fitnessMethod);
		}

		Collections.sort(this.chromosomeList);
		selection(selectionMethod);
		this.hammingdbl = hammingDistance();
	}

	public void selection(String selectionMethod) {

		int length = this.size;

		switch (selectionMethod) {

		case ("Truncation"):
			Collections.sort(this.chromosomeList);
			for (int i = 0; i < length / 2; i++) {
				this.chromosomeList.set(this.size - (i + 1), new Chromosome(this.chromosomeList.get(i).getGenome()));
			}
//			System.out.print("Fitnesses after selection :");
//			System.out.println(this.chromosomeList.get(0).getFitness());

			break;

		case ("Roulette"):
			probabilitySelection("roulette", this.size);
			break;

		case ("Rank"):
			probabilitySelection("rank", this.size);
			break;
		}
	}

	public int getMaxFitness() {
		Collections.sort(this.chromosomeList);
		return this.chromosomeList.get(0).getFitness();
	}

	public int getAverageFitness() {
		int sum = 0;
		for (Chromosome chromosome : chromosomeList) {
			sum += chromosome.getFitness();
		}
		return sum/this.chromosomeList.size();
		}

	public int getMinFitness() {
		Collections.sort(this.chromosomeList);
		return this.chromosomeList.get(this.chromosomeList.size()-1).getFitness();
	}
	
//	public void setMaxFitness(int fitness) {
//		return this.maxFitness;
//	}
//
//	public void setAverageFitness() {
//		return this.averageFitness;
//	}
//
//	public void setMinFitness() {
//		return this.minFitness;
//	}

	private void probabilitySelection(String method, int length) {

		int sum = 0;
		// System.out.println(length);
		double[] probability = new double[length];
		probability[length - 1] = 1;
		// System.out.println(probability.length);
		ArrayList<Chromosome> newList = new ArrayList<Chromosome>();
		Collections.reverse(chromosomeList);

		switch (method) {

		case ("roulette"):
			// gets sum of all fitnessess
			for (Chromosome chromosome : chromosomeList) {
				sum += chromosome.getFitness();
			}

			// fills probability array with intervals of selection

			for (int i = 1; i < probability.length - 2; i++) {
				probability[i] = (chromosomeList.get(i).getFitness() / sum) + probability[i - 1];
			}

			break;

		case ("rank"):

			sum = (length * (length + 1)) / 2;

			for (int i = 1; i < probability.length - 1; i++) {
				probability[i] = ((double) i + 1) / (double) sum + probability[i - 1];
			}

			break;

		// rolls a random

		}
		Random rnd = new Random();

		for (int i = 0; i < probability.length; i++) {
			double check = rnd.nextDouble();
			for (int j = 1; j < probability.length; j++) {
				if (check < probability[j] && check > probability[j - 1]) {
					newList.add(chromosomeList.get(i));
				}
			}
		}

//		for (Chromosome chromosome : newList) {
//			System.out.print(" "+chromosome.getFitness()+ ",");
//			
//		}

		this.chromosomeList = newList;
	}

	public void calculateChromosomeFitness(String s) {

		for (Chromosome i : chromosomeList) {
			i.calculateFitness(s);
		}

	}

	public double hammingDistance() {
		double sum = 0;
		double size = this.chromosomeList.get(0).getGenome().size();
		for (int i = 0; i < size; i++) {
			int ones = 0;
			for (Chromosome c : this.chromosomeList) {
				ones += c.getGenome().get(i);
			}
			sum += (ones * (size - ones));
		}

		double pairs = size * (size - 1) / 2;
		double average = sum / pairs;
		return (average / size);
	}
}

