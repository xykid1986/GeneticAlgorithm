package com.yixiao.genetic.mazegame;

import com.yixiao.genetic.Individual;
import com.yixiao.genetic.ShortcutStrategy;

/**
 * If the score equals max distance, the best strategy is found.
 */
public class MazeWalkDistanceShortcutStrategy implements ShortcutStrategy<String> {

    private double maxDistance;
    private static double delta = 0.00000000000001;

    public MazeWalkDistanceShortcutStrategy(int[][] maze) {
        maxDistance = Maze.distanceToExit(0, 0, maze);
    }

    @Override
    public boolean isFit(Individual<String> individual) {
        return Math.abs(individual.getScore() - maxDistance) < delta;
    }
}
