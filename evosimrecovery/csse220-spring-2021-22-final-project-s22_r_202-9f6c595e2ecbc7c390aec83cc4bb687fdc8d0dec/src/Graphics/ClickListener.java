package Graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import mainApp.Container;

public class ClickListener implements MouseListener {

	private ChromosomeComponent alpha;
	private PopulationComponent beta;
	private int squareSize;
	private char flag = '?';
	private Container c;
	private JFrame frame;

	public ClickListener(ChromosomeComponent alpha) {
		this.alpha = alpha;
		this.squareSize = alpha.getSquareSize();
		flag = 'a';
	}

	public ClickListener(PopulationComponent beta, Container c2, JFrame frame) {
		this.beta = beta;
		this.squareSize = beta.getSquareSize();
		flag = 'b';
		this.c = c2;
		this.frame = frame;
	}

	public ClickListener(JFrame frame, int squaresize) {
		this.frame = frame;
		this.squareSize = squaresize;
		flag = 'c';
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (flag == 'a') {
			int xcord = e.getX() - 53;
			int ycord = e.getY() - 31;

			if (xcord <= squareSize * alpha.columns && ycord <= squareSize * alpha.columns) {
				int genome = xcord / this.squareSize + (ycord / this.squareSize) * alpha.columns;
				this.alpha.getChromosome().flipByte(genome);
			}
//		System.out.println("coordinates: (" + xcord+","+ycord+")");
			this.alpha.repaint();
			
		} else if (flag == 'b') {
			
			c.setRunState(false);
			
			int xcord = e.getX() - 42;
			int ycord = e.getY() - 30;

			if (xcord <= squareSize * beta.columns && ycord <= squareSize * beta.columns) {
				int generation = xcord / this.squareSize + (ycord / this.squareSize) * beta.columns;
//				System.out.println(c.getChromosome().toString());
				c.setChromosome(c.getGeneration().chromosomeList.get(generation));
				System.out.println(c.getChromosome());
				c.setViewerSelect('c');
				frame.dispose();
			}
			
//			System.out.println("coordinates: (" + xcord+","+ycord+")");
		} else if (flag == 'c') {
			c.setRunState(false);

			int xcord = e.getX() - 53;
			int ycord = e.getY() - 31;

			if (xcord <= squareSize * alpha.columns && ycord <= squareSize * alpha.columns) {
				int genome = xcord / this.squareSize + (ycord / this.squareSize) * alpha.columns;
				this.alpha.getChromosome().flipByte(genome);
			}
//			System.out.println("coordinates: (" + xcord+","+ycord+")");
			this.alpha.repaint();

		}
//		System.out.println(flag);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
