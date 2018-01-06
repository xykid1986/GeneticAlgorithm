package com.yixiao.genetic.mazegame;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A 2D maze util class. maze[0][0] is the start, maze[size-1][size-1] is the exit.
 */
public class Maze {

    public static final int BLOCKER = 2;

    /**
     * Get a size x size maze with blockers.
     *
     * @param size       length of the edge of the square maze.
     * @param numBlocker number of blockers
     * @return
     */
    public static int[][] getMaze(int size, int numBlocker) {
        int[][] maze = new int[size][size];
        Random random = new Random();
        for (int i = 0; i < numBlocker; i++) {
            int x, y;
            do {
                x = random.nextInt(size);
                y = random.nextInt(size);
            } while (maze[x][y] > 0);
            maze[x][y] = BLOCKER;
        }
        return maze;
    }

    /**
     * Print out the maze.
     *
     * @param maze
     */
    public static void display(int[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Calculate the distance of current position to exit.
     *
     * @param x    current row index
     * @param y    current column index
     * @param maze the maze
     * @return the distance
     */
    public static double distanceToExit(int x, int y, int[][] maze) {
        int xExit = maze.length - 1;
        int yExit = maze[0].length - 1;
        return Math.sqrt(Math.pow(x - xExit, 2) + Math.pow(y - yExit, 2));
    }

    public static int[][] getSolvableMaze(int size, int numBlocker) {
        int[][] maze = new int[size][size];
        Map<Integer, Set<Integer>> path = new HashMap<>();
        getPathToExit(maze).forEach(step -> {
            Set<Integer> s = path.getOrDefault(step.get(0), new HashSet<>());
            s.add(step.get(1));
            path.put(step.get(0), s);
        });
        Random rand = new Random();

        int x, y;
        for (int i = 0; i < numBlocker; i++) {
            do {
                x = rand.nextInt(maze.length);
                y = rand.nextInt(maze[0].length);
            } while (maze[x][y] == BLOCKER || path.getOrDefault(x, new HashSet<>()).contains(y));
            maze[x][y] = BLOCKER;
        }
        return maze;
    }

    private static List<List<Integer>> getPathToExit(int[][] maze) {
        List<List<Integer>> path = new ArrayList<>();
        int width = maze.length - 1;
        int height = maze[0].length - 1;
        Random rand = new Random();
        int x = 0, y = 0;
        while (true) {
            path.add(Arrays.asList(x, y));
            if (x < width && y < height) {
                int next = rand.nextInt(2);
                if (next > 0) {
                    x += 1;
                } else {
                    y += 1;
                }
            } else if (x < width) {
                x += 1;
            } else if (y < height) {
                y += 1;
            } else {
                break;
            }
        }
        return path;
    }

    /**
     * Generate a list of maze walk strategies.
     * @param size
     * @param length
     * @return
     */
    public static List<String> getWalkStrategies(int size, int length) {
        Random random = new Random();
        return IntStream.range(0, size).boxed().map(index -> {
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<length;i++) {
                sb.append(random.nextInt(2));
            }
            return sb.toString();
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(getPathToExit(new int[5][5]));
        display(getSolvableMaze(10, 40));
    }
}
