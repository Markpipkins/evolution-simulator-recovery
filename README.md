# Evolution-Simulator-Recovery

Upload of past program to a public repository so I can share with others


# Evolutionary Algorithm Visualization

This project implements an interactive evolutionary algorithm inspired by the accompanying research paper. It provides three windows— **Evolution Viewer** ,  **Population Viewer** , and  **Chromosome Viewer** —to visualize and control the evolutionary process in real time. Below is an overview of each window’s functionality, along with descriptions of the various settings and how to interact with the program.

---

## Overview

When you run the program, an **Evolution Viewer** window appears by default. From there, you can navigate to the **Population Viewer** and further into the **Chromosome Viewer** to inspect and manipulate individual chromosomes.

* **Evolution Viewer** : Displays a graph of fitness over time (tracking best, average, and worst fitness) and shows the highest-fitness chromosome in real time.
* **Population Viewer** : Arranges 100 chromosomes in a 10×10 grid, updating each chromosome’s progress in real time.
* **Chromosome Viewer** : Presents the chosen chromosome’s 10×10 grid of cells. Users can click on cells to manually modify them, which directly affects subsequent evolution.

---

## 1. Evolution Viewer

The **Evolution Viewer** is the primary interface where you control the overall simulation.

### 1.1 Graph Panel (Left Side)

* **Fitness Graph** : Shows the best, average, and worst fitness values for each generation.

### 1.2 Chromosome Display (Right Side)

* Shows the highest-fitness chromosome as it evolves. Each cell in this 10×10 grid represents either a blue (1) or black (0) cell.

### 1.3 Bottom Controls

1. **Mutation Rate (Text Box)**

   Enter a value from 0 to 100 to set the probability of gene mutation.
2. **Selection (Drop-Down List)**

   Choose the selection method for the evolutionary algorithm:  **Roulette** ,  **Rank** , or  **Truncation**.
3. **Fitness (Drop-Down List)**

   Select one of three fitness metrics:

   * **Simple** : Aims for all cells to be blue (1).
   * **Smiley** : Aims to create a smiley face pattern with certain cells remaining black (0).
   * **Streak** : Aims to find the longest continuous sequence of blue cells horizontally and vertically (usually converges more slowly than Simple).
4. **Crossover (Checkbox)**

   Enable or disable crossover during reproduction.
5. **Generations (Text Box)**

   Sets the total number of generations to run during the simulation.
6. **Elitism (Text Box)**

   Sets the percentage of top-performing individuals that carry over to the next generation unchanged.

### 1.4 Top Controls

1. **Enable Static Graph (Checkbox)**

   * **Unchecked (default)** : Dynamically adjusts the graph’s size as data grows.
   * **Checked** : Keeps the graph size fixed.
2. **Start / Pause / Continue (Button)**

   * **Start** : Begins the simulation, then switches to  **Pause** .
   * **Pause** : Temporarily halts the simulation.
   * **Continue** : Resumes the simulation from where it left off (unless perfect fitness is reached).
3. **Restart (Button)** : Resets all simulation data and the fitness graph to run a fresh simulation.
4. **Open this Generation (Button)** : Opens the **Population Viewer** for the current generation.

---

## 2. Population Viewer

The **Population Viewer** displays 100 chromosomes in a 10×10 grid. Each chromosome has 100 cells. This viewer allows you to observe the entire population evolving in real time.  **Click on any chromosome** to open the  **Chromosome Viewer** , which automatically pauses the simulation if it is running.

### 2.1 Navigation Buttons (Bottom)

1. **Open Evolution Viewer (Button)** : Returns to the  **Evolution Viewer** .
2. **Start / Pause / Continue (Button)** : Functions identically to the corresponding button in the  **Evolution Viewer** , allowing you to run or pause the simulation here.

---

## 3. Chromosome Viewer

Upon selecting a specific chromosome in the  **Population Viewer** , the **Chromosome Viewer** displays its 10×10 grid of cells.

### 3.1 Editing a Chromosome

* Each cell toggles between **blue (1)** and **black (0)** with a click.
* Changes take effect immediately. For instance, switching all cells to blue (1) under the “Simple” fitness goal instantly achieves 100% fitness, causing the simulation to stop once you continue.

### 3.2 Bottom Controls

1. **Open Population (Button)** : Returns to the  **Population Viewer** .
2. **Mutate (Button)** : Applies a mutation to this individual chromosome using the rate in the **Mutation Level** text box.
3. **Mutation Level (Text Box)** : Enter the desired mutation rate for this specific chromosome.

---

## 4. Running the Simulation

1. **Launch the Program** : The **Evolution Viewer** opens by default.
2. **Configure Settings** : Set the mutation rate, selection method, fitness metric, crossover, number of generations, and elitism.
3. **Start the Simulation** : Click **Start** to begin. Monitor changes in the fitness graph and the top chromosome’s progress.
4. **Monitor Population** : Use **Open this Generation** to access the **Population Viewer** and watch all chromosomes evolve in real time.
5. **Inspect Chromosomes** : Select a chromosome in the **Population Viewer** to open the  **Chromosome Viewer** , where you can make edits.
6. **Pause & Continue** : Manage the simulation’s pace to observe or adjust chromosomes as needed.

---

## 5. Acknowledgments

* **Research Paper** : This project was heavily inspired by the ideas and methodologies presented in the uploaded paper.
* **Contributors** : Special thanks to my old classmates for helping in development and testing.  Their Githubs are [rhit-chenna](https://github.com/rhit-chenna) and [roycerd](https://github.com/roycerd)

---

**Enjoy exploring the mechanics of evolutionary algorithms with this visualization tool!**

.

.
