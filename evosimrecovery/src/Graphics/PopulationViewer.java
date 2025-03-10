package Graphics;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mainApp.Container;

public class PopulationViewer {
	private PopulationComponent p;
	private Container c;
	private String buttonlabel;
	
	public PopulationViewer(Container c) {
		this.c = c;
		if(c.getNullset() == '?') {
			buttonlabel = "Start";
		} else {
			buttonlabel = "Continue";
		}
	}

	private void DriverMain() {
		final String frameTitle = "Population Viewer";
		int frameWidth = 800;
		int frameHeight = 800;
		
		JFrame frame = new JFrame();
		JButton openEvolutionButton = new JButton("Open Evolution Viewer");
		JButton continueButton = new JButton(buttonlabel);
		JPanel controlPanel = new JPanel();
		JLabel hamminglabel = new JLabel();
		
		p = new PopulationComponent(frameWidth, c);
		
		MouseListener d2 = new ClickListener(p, c, frame);
		
		frame.addMouseListener(d2);
				
//		controlPanel.setLayout(new BorderLayout());
		controlPanel.add(openEvolutionButton, BorderLayout.SOUTH);
		controlPanel.add(continueButton, BorderLayout.SOUTH);
//		controlPanel.add(p, BorderLayout.CENTER);
		
		hamminglabel.setText("Hamming Distance: " + Double.toString(c.getGeneration().hammingdbl));
		frame.add(hamminglabel, BorderLayout.NORTH);
		
//		
		frame.add(p);
		frame.add(controlPanel, BorderLayout.SOUTH);
		frame.setTitle(frameTitle);
		frame.setSize(frameWidth, frameHeight);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		
		continueButton.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					String currentText = continueButton.getText();

					if (currentText.equals("Start")) {
						continueButton.setText("Pause");
						c.setRunState(true);
//						grabData();

					} else if (currentText.equals("Pause")) {
						continueButton.setText("Continue");
						c.setRunState(false);

					} else if (currentText.equals("Continue")) {
						continueButton.setText("Pause");
						c.setRunState(true);
//						grabData();

					} 
//					else if (currentText.equals("Continue Evolution")) {
//						continueButton.setText("Pause");
//						evo.clearFitnesses(0);
//						System.out.println(currentText);
//						c.setRunState(true);
//						grabData();
//
//					}
//
//				}
//
//				public void grabData() {
//					c.setSelectionMethod((String) SelectionMenu.getSelectedItem());
//					setMutability(MutationRateBox);
//					c.setFitnessMethod((String) FitnessMenu.getSelectedItem());
//					c.setSetCrossover(CrossoverCheckBox.isSelected());
//					c.setGenerationSize(Integer.parseInt(GenerationSizeBox.getText()));
//					c.setNumberofEvolutions(Integer.parseInt(NumberofEvolutionsBox.getText()));
//					evo.numberOfEvolutions = Integer.parseInt(NumberofEvolutionsBox.getText());
//					c.setGenomeLength(Integer.parseInt(GenomeLengthBox.getText()));
//					c.setElitismRate(Integer.parseInt(ElitismRateBox.getText()));
//					System.out.println("data grabbed");
				}
			});
		
		openEvolutionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				c.setViewerSelect('e');
				frame.dispose();
				
			}
		});
		
	}
	public static void Drivercall(PopulationViewer e) {
		e.DriverMain();
	}

	public void callrepaint() {
		this.p.callrepaint();
	}
}
