package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.util.Arrays;

public class PositiveIntGenerator extends Generator<Integer> {

    public PositiveIntGenerator() {
        super(Arrays.asList(Integer.class, int.class));
    }

    @Override
    public Integer generate(SourceOfRandomness random, GenerationStatus status) {
        int value = random.nextInt();
        return Math.abs(value);
    }

}