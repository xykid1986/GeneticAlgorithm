package com.yixiao.genetic.mazegame;

import com.yixiao.genetic.Hybridization;
import com.yixiao.genetic.Individual;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
public class MazeWalkHybridization implements Hybridization<String> {

    private static final Random rand = new Random();

    private double uniformRate;

    @Override
    public String generateHybrid(List<Individual<String>> population) {
        String s1 = population.get(rand.nextInt(population.size())).getObject();
        String s2 = population.get(rand.nextInt(population.size())).getObject();
        char[] c = new char[s1.length()];
        for (int i = 0; i < s1.length(); i++) {
            c[i] = Math.random() >= uniformRate ? s1.charAt(i) : s2.charAt(i);
        }
        return new String(c);
    }
}
