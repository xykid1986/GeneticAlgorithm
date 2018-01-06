package com.yixiao.genetic;

/**
 * Shortcut strategy for the genetic algorithm. End the evolution cycles if a known best individual is found.
 * @param <T>
 */
public interface ShortcutStrategy<T> {

    /**
     * Check if the individual is a fit and need no more optimization.
     * @param individual
     * @return
     */
    boolean isFit(Individual<T> individual);
}
