package mainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Timer;

import Graphics.ChromosomeViewer;
import Graphics.EvolutionViewer;
import Graphics.PopulationViewer;

public class Controller {

	private ChromosomeViewer g;
	private EvolutionViewer e;
	private Container c;
	public static int iterations = 0;
	public PopulationViewer p;
	private char viewercharacter;

	// public ArrayList<Generation> history = new ArrayList<Generation>();
	public Generation generation;
	public Chromosome chromosome;

	public Controller() {

		startup('?');

		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tick();
			}
		};

		Timer timer = new Timer(50, timerListener);
		timer.start();

	}

	private void startup(char a) {

		switch (a) {

		case ('?'):
			this.c = new Container();
//			evolution();
			this.e = new EvolutionViewer(c);
			EvolutionViewer.Drivercall(e);
			c.setViewerSelect('e');
			this.viewercharacter = 'e';

			break;

		case ('e'):
			this.e = new EvolutionViewer(this.c);
			EvolutionViewer.Drivercall(e);

			break;

		case ('p'):
			this.p = new PopulationViewer(this.c);
			PopulationViewer.Drivercall(p);

			break;

		case ('c'):
			this.g = new ChromosomeViewer(this.c);
			ChromosomeViewer.Drivercall(g);

			break;

//		Generation test = new Generation("random",0);
// 		evolutionLoop(100, 2, "simple", "trunkation");
//
//		EvolutionViewer.Drivercall(e);

		}
	}

	public void Tick() {
		if (this.viewercharacter != c.getViewerSelect()) {
			startup(c.getViewerSelect());
			this.viewercharacter = c.getViewerSelect();
		}

		if (c.isRunState()) {
			c.setNullset('1');
			evolution();

			switch (c.getViewerSelect()) {

			case ('e'):
				e.callrepaint();
				break;

			case ('p'):
				p.callrepaint();
				break;

			case ('c'):
				g.callrepaint();
				break;

			}
		}

	}

	/*
	 * 
	 * public void evolutionLoop(int numberOfIterations, int mutability, String
	 * fitnessMethod, String selectionMethod) { history = new
	 * ArrayList<Generation>(); history.add(new Generation("seeded", 1231238995));
	 * for (int i = 1; i < numberOfIterations; i++) { history.add(new
	 * Generation(history.get(i - 1))); history.get(i).evolve(mutability,
	 * fitnessMethod, selectionMethod); } }
	 */

	public void evolution() {

		if (iterations >= c.getNumberofEvolutions()) {
			iterations = 0;
			c.setRunState(false);
			this.e.setStartButtonText("Continue Evolution");

		} else {

			Generation children;
			if (c.getHistory().isEmpty()) {
				children = new Generation("seeded", 918273645);

			} else {
				children = new Generation(c.getHistory().get(c.getHistory().size() - 1));

//			System.out.println(children.chromosomeList);
//			System.out.println(c.getHistory().get(c.getHistory().size() - 1));

				ArrayList<Chromosome> elite = children.elitism(c.getElitismRate());

				if (c.isSetCrossover()) {
					children.crossover();
				}

//				System.out.println();
//				System.out.println();
//				System.out.println();

				children.evolve(c.getMutability(), c.getFitnessMethod(), c.getSelectionMethod());

//				for (Chromosome chromosome : children.chromosomeList) {
////					System.out.print(" " + chromosome.getFitness() + ",");
//
//				}
//				System.out.println();
//				
//				for (Chromosome chromosome : elite) {
////					System.out.print(" " + chromosome.getFitness() + ",");
//
//					}

//				System.out.println();
				children.chromosomeList.addAll(0, elite);

				Collections.sort(children.chromosomeList);

//				for (Chromosome chromosome : children.chromosomeList) {
//				System.out.print(" " + chromosome.getFitness() + ",");
//
//				}
////				System.out.println();
//				System.out.println("Esize: " + elite.size());
//				System.out.println("Csize: " + children.chromosomeList.size());

			}

			ArrayList<Generation> history = c.getHistory();
			history.add(children);
			c.setHistory(history);
			children.calculateChromosomeFitness(c.getFitnessMethod());

			ArrayList<Integer> g = c.getMaxFitnesses();
			g.add(children.getMaxFitness());
			c.setMaxFitnesses(g);

			ArrayList<Integer> g2 = c.getMinFitnesses();
			g2.add(children.getMinFitness());
			c.setMinFitnesses(g2);

			ArrayList<Integer> g3 = c.getAverageFitnesses();
			g3.add(children.getAverageFitness());
			c.setAverageFitnesses(g3);

//	        System.out.println(g);
//	        System.out.println(g2);

			iterations++;
		}
		
		if (c.getMaxFitnesses().get(c.getMaxFitnesses().size() - 1) >= c.getFinalFitness()) {

			System.out.println("Final fitness reached");
			iterations = 0;
			c.setRunState(false);
			this.e.setStartButtonText("Continue Evolution");
			return;

		}
	} // end evolution

	public ChromosomeViewer getG() {
		return this.g;
	}

	public void setG(ChromosomeViewer g) {
		this.g = g;
	}
	
	public void callEvolution() {
		this.evolution();
	}

}
