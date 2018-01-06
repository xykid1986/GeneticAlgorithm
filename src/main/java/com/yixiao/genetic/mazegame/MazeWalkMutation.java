package com.yixiao.genetic.mazegame;

import com.yixiao.genetic.Mutation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MazeWalkMutation implements Mutation<String> {

    private double mutationRate;

    @Override
    public String mutate(String walkStrategy) {
        char[] c = walkStrategy.toCharArray();
        for (int i = 0; i < walkStrategy.length(); i++) {
            if (Math.random() <= mutationRate) {
                c[i] = c[i] == '0' ? '1' : '0';
            }
        }
        return new String(c);
    }
}
