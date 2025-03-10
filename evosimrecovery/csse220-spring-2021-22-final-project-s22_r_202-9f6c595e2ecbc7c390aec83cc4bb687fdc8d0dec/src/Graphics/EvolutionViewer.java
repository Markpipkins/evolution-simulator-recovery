package Graphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Errors.IncorrectMutablilityValue;
import mainApp.Chromosome;
import mainApp.Container;
import mainApp.Controller;
import mainApp.Generation;

public class EvolutionViewer {

	private Double Mutability;
	private EvolutionComponent evo;
	private Container c;
	private JButton StartButton;

	public EvolutionViewer(Container c) {
		this.c = c;
	}

	private void DriverMain() {

		EvolutionComponent evo = new EvolutionComponent(this.c);
		this.evo = evo;

//		evo.numberOfEvolutions = 100;
		final String frameTitle = "Evolution Viewer";
		int frameWidth = 1400;
		int frameHeight = 600;
//		final int frameXLoc = 100;
//		final int frameYLoc = 200;

		JFrame frame = new JFrame();
		JPanel controlPanel = new JPanel();
		JPanel upperControlPanel = new JPanel();

		JLabel MutationRateLabel = new JLabel("Mutation Rate %:");
		JTextField MutationRateBox = new JTextField("1", 3);

		JLabel SelectionLabel = new JLabel("Selection:");
		JComboBox<String> SelectionMenu = new JComboBox<String>();
		SelectionMenu.addItem("Truncation");
		SelectionMenu.addItem("Roulette");
		SelectionMenu.addItem("Rank");

		JLabel FitnessLabel = new JLabel("Fitness:");
		JComboBox<String> FitnessMenu = new JComboBox<String>();
		FitnessMenu.addItem("Simple");
		FitnessMenu.addItem("Smiley");
		FitnessMenu.addItem("Streak");

		JLabel CrossoverLabel = new JLabel("Crossover:");
		JCheckBox CrossoverCheckBox = new JCheckBox();
		JCheckBox ScalingCheckBox = new JCheckBox();
		JLabel ScalingLabel = new JLabel("Enable Static Graph");
		
		JLabel PopulationSizeLabel = new JLabel("Population Size:");
		JTextField PopulationSizeBox = new JTextField(Integer.toString(c.getGenerationSize()) , 3);
		JLabel NumberofEvolutionsLabel = new JLabel("Generations:");
		JTextField NumberofEvolutionsBox = new JTextField(Integer.toString(c.getNumberofEvolutions()), 3);
		JLabel GenomeLengthLabel = new JLabel("Genome Length:");
		JTextField GenomeLengthBox = new JTextField(Integer.toString(c.getGenomeLength()), 3);
		JLabel ElitismRateLabel = new JLabel("Elitism %:");
		JTextField ElitismRateBox = new JTextField(Double.toString(c.getElitismRate()), 3);
		this.StartButton = new JButton("Start");
		JButton RestartButton = new JButton("Restart");
		JButton OpenGeneration = new JButton("Open this Generation");
		JButton EvolutionMutate = new JButton("Mutate");
		
		
		upperControlPanel.add(ScalingCheckBox);
		upperControlPanel.add(ScalingLabel);
		controlPanel.add(MutationRateLabel);
		controlPanel.add(MutationRateBox);
		controlPanel.add(SelectionLabel);
		controlPanel.add(SelectionMenu);
		controlPanel.add(FitnessLabel);
		controlPanel.add(FitnessMenu);
		controlPanel.add(CrossoverLabel);
		controlPanel.add(CrossoverCheckBox);
		controlPanel.add(PopulationSizeLabel);
		controlPanel.add(PopulationSizeBox);
		controlPanel.add(NumberofEvolutionsLabel);
		controlPanel.add(NumberofEvolutionsBox);
		controlPanel.add(GenomeLengthLabel);
		controlPanel.add(GenomeLengthBox);
		controlPanel.add(ElitismRateLabel);
		controlPanel.add(ElitismRateBox);
		upperControlPanel.add(StartButton);
		upperControlPanel.add(RestartButton);
		upperControlPanel.add(OpenGeneration);
//		upperControlPanel.add(EvolutionMutate);
		
		
		EvolutionMutate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				grabData();
				c.setRunState(true);
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				c.setRunState(false);
				
			}
			
			public void grabData() {
				c.setSelectionMethod((String) SelectionMenu.getSelectedItem());
				setMutability(MutationRateBox);
				c.setFitnessMethod((String) FitnessMenu.getSelectedItem());
				c.setSetCrossover(CrossoverCheckBox.isSelected());
				c.setPopulationSize(Integer.parseInt(PopulationSizeBox.getText()));
				c.setNumberofEvolutions(Integer.parseInt(NumberofEvolutionsBox.getText()));
				evo.numberOfEvolutions = Integer.parseInt(NumberofEvolutionsBox.getText());
				c.setGenomeLength(Integer.parseInt(GenomeLengthBox.getText()));
				c.setElitismRate(Double.parseDouble(ElitismRateBox.getText()));
//				System.out.println("data grabbed");
			}
			
		});

		MutationRateBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setMutability(MutationRateBox);

			}
		});

		SelectionMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.setSelectionMethod((String) SelectionMenu.getSelectedItem());

			}
		});

		FitnessMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.setFitnessMethod((String) FitnessMenu.getSelectedItem());

			}
		});

		CrossoverCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.setSetCrossover(CrossoverCheckBox.isSelected());
			}
		});

		PopulationSizeBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.setPopulationSize(Integer.parseInt(PopulationSizeBox.getText()));

			}
		});

		NumberofEvolutionsBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.setNumberofEvolutions(Integer.parseInt(NumberofEvolutionsBox.getText()));
				evo.numberOfEvolutions = Integer.parseInt(NumberofEvolutionsBox.getText());

			}
		});

		GenomeLengthBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.setGenomeLength(Integer.parseInt(GenomeLengthBox.getText()));
				grabData();
			}
			
			public void grabData() {
				c.setSelectionMethod((String) SelectionMenu.getSelectedItem());
				setMutability(MutationRateBox);
				c.setFitnessMethod((String) FitnessMenu.getSelectedItem());
				c.setSetCrossover(CrossoverCheckBox.isSelected());
				c.setPopulationSize(Integer.parseInt(PopulationSizeBox.getText()));
				c.setNumberofEvolutions(Integer.parseInt(NumberofEvolutionsBox.getText()));
				evo.numberOfEvolutions = Integer.parseInt(NumberofEvolutionsBox.getText());
				c.setGenomeLength(Integer.parseInt(GenomeLengthBox.getText()));
				c.setElitismRate(Double.parseDouble(ElitismRateBox.getText()));
//				System.out.println("data grabbed");
			}
			
		});

		ElitismRateBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				c.setElitismRate(Double.parseDouble(ElitismRateBox.getText()));
//				System.out.println(c.getElitismRate());
			}
		});

		StartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String currentText = StartButton.getText();

				if (currentText.equals("Start")) {
					StartButton.setText("Pause");
//					System.out.println(currentText);
					c.setRunState(true);
					grabData();

				} else if (currentText.equals("Pause")) {
					StartButton.setText("Continue");
//					System.out.println(currentText);
					c.setRunState(false);

				} else if (currentText.equals("Continue")) {
					StartButton.setText("Pause");
//					System.out.println(currentText);
					c.setRunState(true);
					grabData();

				} else if (currentText.equals("Continue Evolution")) {
					StartButton.setText("Pause");
					evo.clearFitnesses(0);
//					System.out.println(currentText);
					c.setRunState(true);
					grabData();

				}

			}

			public void grabData() {
				c.setSelectionMethod((String) SelectionMenu.getSelectedItem());
				setMutability(MutationRateBox);
				c.setFitnessMethod((String) FitnessMenu.getSelectedItem());
				c.setSetCrossover(CrossoverCheckBox.isSelected());
				c.setPopulationSize(Integer.parseInt(PopulationSizeBox.getText()));
				c.setNumberofEvolutions(Integer.parseInt(NumberofEvolutionsBox.getText()));
				evo.numberOfEvolutions = Integer.parseInt(NumberofEvolutionsBox.getText());
				c.setGenomeLength(Integer.parseInt(GenomeLengthBox.getText()));
				c.setElitismRate(Double.parseDouble(ElitismRateBox.getText()));
//				System.out.println("data grabbed");
			}
		});

		RestartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				StartButton.setText("Start");
				evo.clearFitnesses(1);
//				System.out.println("Restart");
				c.setRunState(false);
				c.setNullset('?');
				grabData();
				callrepaint();
				Controller.iterations = 0;

			}

			public void grabData() {
				c.setSelectionMethod((String) SelectionMenu.getSelectedItem());
				setMutability(MutationRateBox);
				c.setFitnessMethod((String) FitnessMenu.getSelectedItem());
				c.setSetCrossover(CrossoverCheckBox.isSelected());
				c.setPopulationSize(Integer.parseInt(PopulationSizeBox.getText()));
				c.setNumberofEvolutions(Integer.parseInt(NumberofEvolutionsBox.getText()));
				evo.numberOfEvolutions = Integer.parseInt(NumberofEvolutionsBox.getText());
				c.setGenomeLength(Integer.parseInt(GenomeLengthBox.getText()));
				c.setElitismRate(Double.parseDouble(ElitismRateBox.getText()));
//				System.out.println("data grabbed");
			}

		});

		OpenGeneration.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				c.setRunState(false);
				
				if(c.getNullset() == '?') {
					c.setGeneration(new Generation(evo.getGenome().size(), new Chromosome(evo.getGenome())));
				} else {
					c.setGeneration(c.getHistory().get(c.getHistory().size() - 1));
				}
				
				grabData();
				c.setViewerSelect('p');
				frame.dispose();

			}
			
			public void grabData() {
				c.setSelectionMethod((String) SelectionMenu.getSelectedItem());
				setMutability(MutationRateBox);
				c.setFitnessMethod((String) FitnessMenu.getSelectedItem());
				c.setSetCrossover(CrossoverCheckBox.isSelected());
				c.setPopulationSize(Integer.parseInt(PopulationSizeBox.getText()));
				c.setNumberofEvolutions(Integer.parseInt(NumberofEvolutionsBox.getText()));
				evo.numberOfEvolutions = Integer.parseInt(NumberofEvolutionsBox.getText());
				c.setGenomeLength(Integer.parseInt(GenomeLengthBox.getText()));
				c.setElitismRate(Double.parseDouble(ElitismRateBox.getText()));
//				System.out.println("data grabbed");
			}
			
		});
		
		ScalingCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				
				evo.setScalable(ScalingCheckBox.isSelected());
				
			}
		});
		
		
//		MouseListener d3 = new ClickListener(frame);

		frame.add(evo);
		frame.add(controlPanel, BorderLayout.SOUTH);
		frame.add(upperControlPanel, BorderLayout.NORTH);
		frame.setTitle(frameTitle);
		frame.setSize(frameWidth, frameHeight);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

	} // end drivermain

	public JButton getStartButton() {
		return this.StartButton;
	}

	public static void Drivercall(EvolutionViewer e) {
		e.DriverMain();
	}

	public void callrepaint() {
		this.evo.repaint();
	}

	public void setMutability(double m) {
		this.Mutability = m;
	}

	public void setMutability(JTextField MB) {

		try {
			Mutability = Double.parseDouble(MB.getText());
//			System.out.println(Mutability);

			if (Mutability < 0 || Mutability > 100) {
				double holder = Mutability;
				Mutability = 0.0;
				throw new IncorrectMutablilityValue(holder);
			}

			if (Mutability >= 0 && Mutability < 1) {
				Mutability *= 100;
				System.out.println("The number you entered has been reformatted to " + Mutability + "%");
			}

		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			System.err.println("Invalid Input");
		} catch (IncorrectMutablilityValue e1) {
			e1.printError();
		}

		c.setMutability(Mutability);
		
//		System.out.println(Mutability);
	}

	public void setStartButtonText(String s) {
		this.StartButton.setText(s);
	}
	
} // end of class
