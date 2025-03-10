package Graphics;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;

import org.junit.runners.model.FrameworkField;

import mainApp.Chromosome;

public class ChromosomeComponent extends JComponent {

	private static Color bit1 = new Color(173, 216, 254);
	private static Color bit0 = Color.BLACK;
	private int frameWidth;
	private int lines;
	public int columns;
	private int squareSize;

	private Chromosome chromosome;
	private ArrayList<Integer> genome;
	
	
	/*
	 * 
	 * This is Mark's comment
	 * 
	 */
	

	public ChromosomeComponent(Chromosome chromosome) {
		this.setChromosome(chromosome);
//		this.frameWidth = width;
		repaint();
		resizeSquare();
	}
	
	public Chromosome DefaultChromosome() {
		File newload = new File("files/DefaultChromosome.txt");
		
		FileReader reader = null;
		try {
			reader = new FileReader(newload);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner s = new Scanner(reader);

		String[] splitString = s.nextLine().split("");
		ArrayList<Integer> newGenome = new ArrayList<Integer>();
		for (int i = 0; i < splitString.length; i++) {
			newGenome.add(Integer.parseInt(splitString[i]));
//			System.out.print(newGenome[i]);
  		}
		return new Chromosome(newGenome);
	}

	public ChromosomeComponent(int width) {
		this.frameWidth = width;
		this.chromosome = DefaultChromosome();
		this.genome = chromosome.getGenome();
		resizeSquare();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
//		System.out.println(squareSize);
		//int edge = (this.frameWidth-(this.squareSize * this.columns))*2/5;
		int edge = 45;
		
		this.genome = chromosome.getGenome();
//		System.out.println(genome.toString());
		
		int count = 0;
		for (int i = 0; i <= lines; i++) {
			for (int j = 0; j < columns; j++) {
				if(count == genome.size()) {
					break;
				}
				if (genome.get(count) == 1) {
					g2d.setColor(bit1);
				} else {
					g2d.setColor(bit0);
				}
				g2d.fillRect(edge + j * this.squareSize, i * this.squareSize, this.squareSize, this.squareSize);

				g2d.setColor(Color.RED);
				g2d.drawRect(edge + j * this.squareSize, i * this.squareSize, this.squareSize, this.squareSize);
				count +=1;
			}

		}

	}

	private void resizeSquare() {
		double number = genome.size();
		this.columns = (int) Math.ceil(Math.sqrt(number));
		this.lines = (int) Math.ceil(number / (float) columns);
		
		this.squareSize = 550/columns;
	}

	public Chromosome getChromosome() {
		return chromosome;
	}

	public void setChromosome(Chromosome chromosome) {
		this.chromosome = chromosome;
		this.genome = this.chromosome.getGenome();
	}

	public void callMutation(double mutability) {
		chromosome.mutation(mutability);
		resizeSquare();
		repaint();
		setChromosome(chromosome);
	}
	
	public void callResizeSquare() {
		resizeSquare();
	}

	public int getSquareSize() {
		return this.squareSize;
	}
	
}
