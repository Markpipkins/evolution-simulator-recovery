package Graphics;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Errors.IncorrectMutablilityValue;
import mainApp.Chromosome;
import mainApp.Controller;
import mainApp.MainApp;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveLoadViewer {

	private String type;
	private Chromosome chromosome;
	private ChromosomeComponent alpha;

	private void DriverMain() {
		final String frameTitle = "Chromosome Viewer";
		final int frameWidth = 350;
		final int frameHeight = 200;

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		JTextField filename = new JTextField(30);
		JButton button = new JButton(this.type);
		JButton cancel = new JButton("Cancel");

		panel.add(filename, BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
		panel.add(cancel, BorderLayout.SOUTH);

		frame.add(panel, BorderLayout.CENTER);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (button.getText().equals("Save")) {

					File newsave = new File("files/"+filename.getText());
					try {
						if (newsave.exists()) {
							throw new FileAlreadyExistsException("files/"+filename.getText());
						}
						frame.dispose();

					} catch (FileAlreadyExistsException e1) {
						System.out.println("files/"+filename.getText() + " already exists!");
					}

					try {
						PrintWriter printer = new PrintWriter(newsave);
						printer.print(chromosome.toString());
						printer.close();

					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						System.out.println(newsave.getName() + " does not exist...");
					}

				} else {

					File newload = new File("files/"+filename.getText());

					try {

						FileReader reader = new FileReader(newload);
						Scanner s = new Scanner(reader);

						String[] splitString = s.nextLine().split("");
						ArrayList<Integer> newGenome = new ArrayList<Integer>();
						for (int i = 0; i < splitString.length; i++) {
							newGenome.add(Integer.parseInt(splitString[i]));
//							System.out.print(newGenome[i]);
						}
//						System.out.println(newGenome);
						chromosome.setGenome(newGenome);
						
						s.close();
						frame.dispose();
						ChromosomeComponent c = getAlpha();
						
						c.setChromosome(chromosome);
						
						c.callResizeSquare();

					} catch (FileNotFoundException e3) {
						System.out.println("File does not exist...");
					} catch (NumberFormatException e4) {
						System.out.println("This file has invalid characters in it and cannot be loaded.");
					}

				}

			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		frame.setTitle(frameTitle);
		frame.setSize(frameWidth, frameHeight);
		frame.setLocationRelativeTo(frame);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);

	}

	SaveLoadViewer(String type, Chromosome chromosome, ChromosomeComponent c) {
		this.type = type;
		this.chromosome = chromosome;
		this.setAlpha(c);
		DriverMain();
	}

	public ChromosomeComponent getAlpha() {
		return alpha;
	}

	public void setAlpha(ChromosomeComponent alpha) {
		this.alpha = alpha;
	}

}
