package Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;

import Errors.IncorrectMutablilityValue;
import mainApp.Chromosome;
import mainApp.Container;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ChromosomeViewer extends JFrame{

	private double mutability;
	private ChromosomeComponent alpha;
	private Container c;
	
	public ChromosomeViewer(Container c) {
		this.c = c;
	}

	private void DriverMain() {
		final String frameTitle = "Chromosome Viewer";
		int frameWidth = 650;
		int frameHeight = 700;
//		final int frameXLoc = 100;
//		final int frameYLoc = 200;

		JFrame frame = new JFrame();
		JPanel controlPanel = new JPanel();
		JButton save = new JButton("Save");
		JButton load = new JButton("Load");
		JButton mutate = new JButton("Mutate");
		JTextField mutationChance = new JTextField(5);
		JButton openPopulationButton = new JButton("Open Population");
		
		this.alpha = new ChromosomeComponent(c.getChromosome());

		controlPanel.add(openPopulationButton);
		// controlPanel.add(load);
		// controlPanel.add(save);
		controlPanel.add(mutate);
		controlPanel.add(mutationChance);
		
		frame.addWindowFocusListener( new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent e) {
				frame.repaint();
				
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		mutate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				try {
					mutability = Double.parseDouble(mutationChance.getText());					

					if (mutability < 0 || mutability > 100) {
						double holder = mutability;
						mutability = 0;
						throw new IncorrectMutablilityValue(holder);
						
					} else if(mutability > 0 && mutability < 1) {
						System.out.println("You're entry of " + mutability + " has been refactored to "
						+ mutability*100 + ".");
						mutability *= 100;
					} 
					

				} catch (NumberFormatException e) {
					System.err.println("Invalid Input");
				} catch (IncorrectMutablilityValue e) {
					e.printError();
				}
				
//				System.out.println(mutability);

				alpha.callMutation(mutability);
				frame.repaint();
			}

		});

		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Chromosome c = alpha.getChromosome();
				SaveLoadViewer g = new SaveLoadViewer("Save", c, alpha);
							
				
			}
		});
		
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Chromosome c = alpha.getChromosome();
				SaveLoadViewer g = new SaveLoadViewer("Load", c, alpha);
				
			}
		});
		
		openPopulationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				c.setViewerSelect('p');
				frame.dispose();
				
			}
		});
				
		MouseListener dl = new ClickListener(alpha);
		frame.addMouseListener(dl);
		
		
		frame.add(this.alpha);
		frame.add(controlPanel, BorderLayout.SOUTH);
		frame.repaint();

		frame.setTitle(frameTitle);
		frame.setSize(frameWidth, frameHeight);
//		frame.setLocation(frameXLoc, frameYLoc);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public static void Drivercall(ChromosomeViewer g) {
		g.DriverMain();
	}
	
	public ChromosomeComponent getChromosomeComponent() {
		return this.alpha;
	}

	public void callrepaint() {
		this.alpha.repaint();
	}

}
