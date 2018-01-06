package com.yixiao.genetic.mazegame;

import com.yixiao.genetic.Evaluator;

/**
 * Evaluator for a walking strategy in the maze. '1' goes down, '0' goes left.
 */
public class MazeWalkDistanceEvaluator implements Evaluator<String> {

    private int[][] maze;
    private double largestDistance;

    public MazeWalkDistanceEvaluator(int[][] maze) {
        this.maze = maze;
        largestDistance = Maze.distanceToExit(0, 0, maze);
    }

    /**
     * Evaluate the walking strategy for the maze.
     *
     * @param strategy a string of '1's and '0's
     * @return Return the score of the walking strategy. It's largest distance to exit minus farthest distance it can go.
     */
    @Override
    public double evaluate(String strategy) {
        int x = 0, y = 0;
        for (int i = 0; i < strategy.length(); i++) {
            int xNext = x, yNext = y;
            char move = strategy.charAt(i);
            if (move == '1') {
                xNext = x + 1;
            } else if (move == '0') {
                yNext = y + 1;
            }
            if (shouldStop(xNext, yNext)) {
                return largestDistance - Maze.distanceToExit(x, y, maze);
            }
            x = xNext;
            y = yNext;
        }
        throw new IllegalStateException("Nonstop evaluation " + strategy);
    }

    // Out of boundary or on a blocker
    private boolean shouldStop(int x, int y) {
        return x < 0 || x >= maze.length || y < 0 || y >= maze[0].length || maze[x][y] > 0;
    }
}
