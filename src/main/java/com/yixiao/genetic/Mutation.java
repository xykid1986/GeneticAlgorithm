package com.yixiao.genetic;

/**
 * Mutation strategy for the genetic algorithm. Generate new individual based on an existing individual.
 * @param <T>
 */
public interface Mutation<T> {

    /**
     * Make some random changes to a individual.
     * @param individual
     * @return mutated individual
     */
    T mutate(T individual);
}
