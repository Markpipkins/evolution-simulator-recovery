package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import mainApp.Chromosome;
import mainApp.Container;
import mainApp.Generation;

public class PopulationComponent extends JComponent {
	private static Color bit1 = new Color(173, 216, 254);
	private static Color bit0 = Color.BLACK;
	private int frameWidth;
	private int rows;
	public int columns;
	private int squareSize;
	private int byteSize;
	private Container c;
	private Generation generation;
	private ArrayList<Integer> genome;

	public PopulationComponent(int width, Container c) {
		this.c = c;
		
		if (c.getNullset() == '?') {
			this.generation = c.getGeneration();
		} else {
			this.generation = c.getHistory().get(c.getHistory().size() - 1);
		}
		
		this.frameWidth = width;
		this.columns = (int) Math.ceil(Math.sqrt(generation.chromosomeList.size()));
		this.rows = (int) Math.ceil(generation.chromosomeList.size() / (float) columns);
		resizeSquare();
		
	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				g2d.translate(squareSize * j, i * squareSize);
//				g2d.drawRect(j * this.squareSize, i * this.squareSize, this.squareSize, this.squareSize);
				paintChromosome(c.getGeneration().chromosomeList.get(columns * i + j), g2d);
				g2d.translate(squareSize * -j, -i * squareSize);
			}
		}

		if(c.getNullset() != '?') {
			c.setGeneration(c.getHistory().get(c.getHistory().size() - 1));
		}		
	}

	public void paintChromosome(Chromosome chromosome, Graphics2D g2d) {
		int edge = 35;

		int count = 0;
		for (int i = 0; i <= rows; i++) {

			for (int j = 0; j < columns; j++) {
				if (count == generation.chromosomeList.size()) {
					break;
				}
				if (chromosome.getGenome().get(count) == 1) {
					g2d.setColor(bit1);
				} else {
					g2d.setColor(bit0);
				}
				g2d.fillRect(edge + j * this.byteSize, i * this.byteSize, this.byteSize, this.byteSize);

//				g2d.setColor(Color.RED);
//				g2d.drawRect(edge + j * this.byteSize, i * this.byteSize, this.byteSize, this.byteSize);
				count += 1;
			}

		}
	}

	private void resizeSquare() {
		this.columns = (int) Math.ceil(Math.sqrt(generation.chromosomeList.size()));
		this.rows = (int) Math.ceil(generation.chromosomeList.size() / (float) columns);

		this.squareSize = 720 / columns;
		this.byteSize = this.squareSize / this.rows;
	}

	public int getSquareSize() {
		return this.squareSize;
	}

	public Generation getgeneration() {
		return this.generation;
	}
	
	public void callrepaint() {
		this.repaint();
	}
}
