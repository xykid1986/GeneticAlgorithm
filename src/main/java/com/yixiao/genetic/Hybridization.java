package com.yixiao.genetic;

import java.util.List;

/**
 * Strategy for hybridization, generating new individuals based on the existing ones in the population.
 * @param <T>
 */
public interface Hybridization<T> {

    /**
     * Generate hybrid from the individuals of a population
     * @param population
     * @return
     */
    T generateHybrid(List<Individual<T>> population);
}
