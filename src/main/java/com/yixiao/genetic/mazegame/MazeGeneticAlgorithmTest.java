package com.yixiao.genetic.mazegame;

import com.yixiao.genetic.*;

import java.util.List;

public class MazeGeneticAlgorithmTest {

    public static void main(String[] args) {
        int[][] maze = Maze.getSolvableMaze(20, 250);
        Mutation<String> mutation = new MazeWalkMutation(0.1);
        Hybridization<String> hybrid = new MazeWalkHybridization(0.7);
        ShortcutStrategy<String> shortcut = new MazeWalkDistanceShortcutStrategy(maze);
        Evaluator<String> evaluator = new MazeWalkDistanceEvaluator(maze);

        List<String> population = Maze.getWalkStrategies(100, 60);
        GeneticAlgorithm<String> ga = new GeneticAlgorithm<>(evaluator, hybrid, mutation, shortcut);

        int numCycles = 1000;
        double eliteFraction = 0.1;

        List<String> survivors = ga.naturalSelection(population, numCycles, eliteFraction);
        Maze.display(maze);
        System.out.println(survivors);
    }
}
