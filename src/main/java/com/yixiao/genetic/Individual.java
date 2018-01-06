package com.yixiao.genetic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The individual in the genetic algorithm.
 * @param <T>
 */
@Getter
@Setter
@AllArgsConstructor
public class Individual<T> {

    private T object;
    private double score;

}
