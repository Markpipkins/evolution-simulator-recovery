package mainApp;

import java.util.ArrayList;

public class Container {
	private ArrayList<Generation> history = new ArrayList<Generation>();
	private Generation generation;
	private Chromosome chromosome;
	
	private String selectionMethod = "Truncation";
	private String FitnessMethod = "Simple";
	private double mutability = 1;
	private boolean setCrossover = false;
	private int generationSize = 100;
	private int numberofEvolutions = 100;
	private int genomeLength = 100;
	private double elitismRate = 0.0;
	private int finalFitness = 100;
	private boolean runState = false;
	private char viewerSelect = '?';
	private char nullset = '?';
	
	private ArrayList<Integer> maxFitnesses = new ArrayList<Integer>();
	private ArrayList<Integer> averageFitnesses = new ArrayList<Integer>();
	private ArrayList<Integer> minFitnesses = new ArrayList<Integer>();
	private ArrayList<Integer> hammingDistance = new ArrayList<Integer>();
	
	public String getSelectionMethod() {
		return selectionMethod;
	}
	public void setSelectionMethod(String selectionMethod) {
		this.selectionMethod = selectionMethod;
	}
	public double getMutability() {
		return mutability;
	}
	public void setMutability(double mutability) {
		this.mutability = mutability;
	}
	public boolean isSetCrossover() {
		return setCrossover;
	}
	public void setSetCrossover(boolean setCrossover) {
		this.setCrossover = setCrossover;
	}
	public int getGenerationSize() {
		return generationSize;
	}
	public void setPopulationSize(int generationSize) {
		this.generationSize = generationSize;
	}
	public int getNumberofEvolutions() {
		return numberofEvolutions;
	}
	public void setNumberofEvolutions(int numberofEvolutions) {
		this.numberofEvolutions = numberofEvolutions;
	}
	public int getGenomeLength() {
		return genomeLength;
	}
	public void setGenomeLength(int genomeLength) {
		this.genomeLength = genomeLength;
	}
	public double getElitismRate() {
		return elitismRate;
	}
	public void setElitismRate(double d) {
		this.elitismRate = d;
	}
	public boolean isRunState() {
		return runState;
	}
	public void setRunState(boolean runState) {
		this.runState = runState;
	}
	public ArrayList<Generation> getHistory() {
		return history;
	}
	public void setHistory(ArrayList<Generation> history) {
		this.history = history;
	}
	public Generation getGeneration() {
		return generation;
	}
	public void setGeneration(Generation generation) {
		this.generation = generation;
	}
	public Chromosome getChromosome() {
		return chromosome;
	}
	public void setChromosome(Chromosome chromosome) {
		this.chromosome = chromosome;
	}
	public ArrayList<Integer> getMaxFitnesses() {
		return maxFitnesses;
	}
	public void setMaxFitnesses(ArrayList<Integer> maxFitnesses) {
		this.maxFitnesses = maxFitnesses;
	}
	public ArrayList<Integer> getAverageFitnesses() {
		return averageFitnesses;
	}
	public void setAverageFitnesses(ArrayList<Integer> averageFitnesses) {
		this.averageFitnesses = averageFitnesses;
	}
	public ArrayList<Integer> getMinFitnesses() {
		return minFitnesses;
	}
	public void setMinFitnesses(ArrayList<Integer> minFitnesses) {
		this.minFitnesses = minFitnesses;
	}
	public void setFitnessMethod(String selectedItem) {
		this.FitnessMethod = selectedItem;
	}
	public String getFitnessMethod() {
		return FitnessMethod;
	}
	public char getViewerSelect() {
		return viewerSelect;
	}
	public void setViewerSelect(char viewerSelect) {
		this.viewerSelect = viewerSelect;
	}
	public ArrayList<Integer> getHammingDistance() {
		return hammingDistance;
	}
	public void setHammingDistance(ArrayList<Integer> hammingDistance) {
		this.hammingDistance = hammingDistance;
	}
	public int getFinalFitness() {
		return finalFitness;
	}
	public void setFinalFitness(int finalFitness) {
		this.finalFitness = finalFitness;
	}
	public char getNullset() {
		return nullset;
	}
	public void setNullset(char nullset) {
		this.nullset = nullset;
	}

}
