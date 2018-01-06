package com.yixiao.genetic;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The genetic algorithm.
 * @param <T>
 */
@Slf4j
@AllArgsConstructor
public class GeneticAlgorithm<T> {

    private final Evaluator<T> evaluator;
    private final Hybridization<T> hybridization;
    private final Mutation<T> mutation;
    private final ShortcutStrategy shortcutStrategy;

    /**
     * Use natural selection method to evolve the population.
     * @param initialPopulation initial random population
     * @param numCycles number of evolution cycles
     * @param eliteFraction the rate of population that survive
     * @return the best fit survivors
     */
    public List<T> naturalSelection(final List<T> initialPopulation, final int numCycles, final double eliteFraction) {
        List<T> population = initialPopulation;
        int count = 0;
        while (count++ < numCycles) {
            log.debug("Starting cycle " + count);
            List<Individual<T>> elites = population.stream().map(id -> new Individual<>(id, evaluator.evaluate(id)))
                    .sorted(ScoreComparator.getComparator())
                    .collect(Collectors.toList())
                    .subList(0, (int) (initialPopulation.size() * eliteFraction));
            if (elites.isEmpty()) {
                log.info("All individuals fail to survive. Start over.");
                population = initialPopulation;
                continue;
            }
            log.debug("Best score in this cycle: " + elites.get(0).getScore());

            List<T> fitters = elites.stream().filter(elite -> shortcutStrategy.isFit(elite))
                    .map(Individual::getObject).collect(Collectors.toList());
            if(!fitters.isEmpty()) {
                log.debug("Found " + fitters.size() + " shortcut fitters! End evolution.");
                return fitters;
            }

            population = elites.stream().map(Individual::getObject).collect(Collectors.toList());
            if (count != numCycles) {
                List<T> offspring = IntStream.range(0, (int) (initialPopulation.size() * (1 - eliteFraction))).boxed()
                        .map(id -> hybridization.generateHybrid(elites))
                        .map(id -> mutation.mutate(id)).collect(Collectors.toList());
                population.addAll(offspring);
            }
        }
        return population;
    }

    // Individual with higher score are in the front.
    private static class ScoreComparator implements Comparator<Individual> {

        static Comparator<Individual> instance = new ScoreComparator();

        static Comparator<Individual> getComparator() {
            return instance;
        }

        @Override
        public int compare(Individual o1, Individual o2) {
            if (o1.getScore() > o2.getScore()) {
                return -1;
            } else if (o1.getScore() < o2.getScore()) {
                return 1;
            }
            return 0;
        }
    }

}
