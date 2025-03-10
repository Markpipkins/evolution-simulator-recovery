
package Graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;

import mainApp.Container;
import mainApp.Generation;

public class EvolutionComponent extends JComponent {
	private ArrayList<Generation> generations = new ArrayList<Generation>();

	private Graphics2D graphics;
	public int numberOfEvolutions;
	private Container c;
	private ArrayList<Integer> genome = new ArrayList<>();
	private boolean scaleGraph = false;
	private int graphWidth = 600, graphHeight = 300, TICK_WIDTH = 7;
	private int size;
	private int columns;
	private int lines;
	private int squareSize;
	private boolean flag = true;
	private Color bit1 = new Color(172, 216, 254);
	private Color bit0 = Color.BLACK;

	public EvolutionComponent(Container c) {
		this.c = c;
		if (c.getNullset() == '?') {
			for (int i = 0; i < 100; i++) {
				this.genome.add(0);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		this.graphics = g2d;
		g2d.translate(100, 100);
		g2d.drawRect(0, 0, graphWidth, graphHeight);
		g2d.translate(0, graphHeight);
		int fitnessCounter = 0;
		for (int i = 0; i >= -graphHeight; i -= graphHeight / 10) {
			g2d.drawLine(-TICK_WIDTH, i, TICK_WIDTH, i);
			g2d.drawString(Integer.toString(fitnessCounter), -4 * TICK_WIDTH, i);
			fitnessCounter += 10;
		}

		g2d.drawString("Fitness", -12 * TICK_WIDTH, -graphHeight / 2);

		drawXAxis(g2d);

		if (!c.getMaxFitnesses().isEmpty()) {
			callPlotPoints();
			this.genome = c.getHistory().get(c.getHistory().size() - 1).chromosomeList.get(0).getGenome();
		}

//		this.genome = c.getHistory().get(c.getHistory().size() - 1).chromosomeList.get(0).getGenome();
		resizeSquare();
		g2d.translate(650, -350);
		evolutionChromosomePaint(g2d);
		g2d.translate(-650, 350);

	}

	private void plotPoints(ArrayList<Integer> fitnesses, Graphics2D g2d) {

		this.numberOfEvolutions = c.getNumberofEvolutions();
		ArrayList<Point> points = new ArrayList<Point>();
		this.size = fitnesses.size();
		int xspacing = graphWidth;
		if (!scaleGraph) {
			if (size > 0) {
				xspacing /= size;
			}
		} else {
			xspacing /= this.numberOfEvolutions;
		}
		int yspacing = graphHeight / 100;

		for (int i = 0; i < size; i++) {
			Point point = new Point(i * xspacing, -(fitnesses.get(i) * yspacing));
			points.add(point);
//			System.out.println(point);

		}
		for (int i = 0; i < points.size() - 1; i++) {
			g2d.drawLine(points.get(i).x, points.get(i).y, points.get(i + 1).x, points.get(i + 1).y);
		}
	}

	public void drawXAxis(Graphics2D g2d) {
		double generationCounter = 0;
		int counter = 0;
		if (!scaleGraph) {
			for (int i = 0; i <= graphWidth; i += graphWidth / 10) {
				g2d.drawLine(i, -TICK_WIDTH, i, TICK_WIDTH);
				g2d.drawString(Integer.toString((int) Math.round(generationCounter)), i - TICK_WIDTH, 3 * TICK_WIDTH);
				generationCounter += size / 10.0;
//		        		System.out.println("size: " + this.size);
				counter++;
			}
			g2d.drawString("Generation", graphWidth / 2 - 30, 6 * TICK_WIDTH);
		} else {
			for (int i = 0; i <= graphWidth; i += graphWidth / 10) {
				g2d.drawLine(i, -TICK_WIDTH, i, TICK_WIDTH);
				g2d.drawString(Integer.toString((int) Math.round(generationCounter)), i - TICK_WIDTH, 3 * TICK_WIDTH);
				generationCounter += c.getNumberofEvolutions() / 10.0;
				counter++;
			}
			g2d.drawString("Generation", graphWidth / 2 - 30, 6 * TICK_WIDTH);
		}
	}

	private void callPlotPoints() {
		this.graphics.setStroke(new BasicStroke(3));
		this.graphics.setColor(Color.BLUE);
		plotPoints(c.getMaxFitnesses(), this.graphics);
		this.graphics.setColor(Color.RED);
		plotPoints(c.getMinFitnesses(), this.graphics);
		this.graphics.setColor(Color.YELLOW);
		plotPoints(c.getAverageFitnesses(), this.graphics);
	}

	public void clearFitnesses(int i) {
		c.getMaxFitnesses().clear();
		c.getAverageFitnesses().clear();
		c.getMinFitnesses().clear();

		if (i == 1) {
			c.getHistory().clear();
		}
	}

	public void evolutionChromosomePaint(Graphics2D g2d) {

		int edge = 45;

		int count = 0;
		for (int i = 0; i <= lines; i++) {
			for (int j = 0; j < columns; j++) {
				if (count == genome.size()) {
					break;
				}
				if (genome.get(count) == 1) {
					g2d.setColor(bit1);
				} else {
					g2d.setColor(bit0);
				}
				g2d.fillRect(edge + j * this.squareSize, i * this.squareSize, this.squareSize, this.squareSize);

				g2d.setColor(Color.RED);
				g2d.setStroke(new BasicStroke(1));
				g2d.drawRect(edge + j * this.squareSize, i * this.squareSize, this.squareSize, this.squareSize);
				count += 1;
			}

		}

	}

	private void resizeSquare() {
		double number = genome.size();
		this.columns = (int) Math.ceil(Math.sqrt(number));
		this.lines = (int) Math.ceil(number / (float) columns);

		this.squareSize = 400 / columns;
	}

	public ArrayList<Integer> getGenome() {
		return this.genome;
	}
	
	public void setScalable(boolean b) {
		this.scaleGraph = b;
	}
	
	public boolean getScalable() {
		return this.scaleGraph;
	}
}