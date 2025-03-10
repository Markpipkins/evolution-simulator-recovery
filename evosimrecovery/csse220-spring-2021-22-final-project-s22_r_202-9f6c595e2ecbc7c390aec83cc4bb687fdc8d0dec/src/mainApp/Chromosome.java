package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class Chromosome implements Comparable<Chromosome> {
	private ArrayList<Integer> genome;
	private int fitness;

	public Chromosome(ArrayList<Integer> oldGenome) {
		this.genome = new ArrayList<Integer>();
		for (int i = 0; i < oldGenome.size(); i++) {
			this.genome.add(oldGenome.get(i));
		}
		calculateFitness("Simple");
	}
	
	public Chromosome(String s) {
		ArrayList<Integer> genome = new ArrayList<>();
		
		for (int i = 0; i < 100; i++) {
			genome.add(0);
		}
		this.genome = genome;
		
	}

	/**
	 * Mutates this Chromosomes genome
	 * 
	 * @param mutability
	 */
	public void mutation(double mutability) {
		
//		double mutabilitypct = mutability;
		
		mutability = ((mutability/100) * this.genome.size())/ (this.genome.size());
		
		ArrayList<Integer> mutatedGenome = new ArrayList<Integer>();
		
		for (int i = 0; i < genome.size(); i++) {
			if (Math.random() <= mutability) {
				if (genome.get(i) == 1) {
					mutatedGenome.add(0);
				} else {
					mutatedGenome.add(1);
				} // Close mutation if
			} else {
				mutatedGenome.add(genome.get(i));
			} // Close mutation check
		} // End loop
		this.genome = mutatedGenome;
	}// End mutation

	/**
	 * Calculates the fitness of this Chromosome using entered fitness method
	 * 
	 * @param calculationMethod
	 */
	public void calculateFitness(String calculationMethod) {

		int sum = 0;
		int count = 0;
		int streakCount = 0;
		for (int i : genome) {

			switch (calculationMethod) {

			case ("Simple"):
				sum += i;
				break;

			case ("Smiley"):
				int[] smile = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
										  1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
										  1, 1, 0, 0, 1, 1, 0, 0, 1, 1,
										  1, 1, 0, 0, 1, 1, 0, 0, 1, 1,
										  1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
										  1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
										  1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
										  1, 0, 1, 1, 1, 1, 1, 1, 0, 1,
										  1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 
										  1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
				if(i == smile[count]) {
					sum +=1;
				}
				break;
				
			case ("Streak"):
//				sum = 1;
				if(i == 1) {
					streakCount += 1;
				} else {
					streakCount = 0;
				}
				if (streakCount > sum) {
					sum = streakCount;
				}
				break;
			}
			this.fitness = sum;
			count++;
		}
	}

	/**
	 * @return This Chromosome's genome
	 */
	public ArrayList<Integer> getGenome() {
		return this.genome;
	}

	public int getFitness() {
		return this.fitness;
	}

	/**
	 * @return This Chromosome's genome as a string of 1s and 0s
	 */
	@Override
	public String toString() {
		String s = "";

		for (Integer i : this.genome) {
			s += Integer.toString(i);
		}

		return s;
	}

	public void setGenome(ArrayList<Integer> newGenome) {
		this.genome = newGenome;
	}

	public void flipByte(int location) {
		if (this.genome.get(location) == 1) {
			this.genome.set(location, 0);
		} else {
			this.genome.set(location, 1);
		}
//		System.out.println(this);
	}

	@Override
	public int compareTo(Chromosome o) {
		return o.fitness - this.fitness;
	}

}
