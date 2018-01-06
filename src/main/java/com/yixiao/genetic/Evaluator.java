package com.yixiao.genetic;

/**
 * Fit score evaluator interface.
 * @param <T>
 */
public interface Evaluator<T> {

    /**
     * Get the score of the object.
     * @param object the individual to be evaluated.
     * @return evaluated score, the higher the better.
     */
    double evaluate(T object);
}
