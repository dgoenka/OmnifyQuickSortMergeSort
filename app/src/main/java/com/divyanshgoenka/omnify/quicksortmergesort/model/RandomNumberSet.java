package com.divyanshgoenka.omnify.quicksortmergesort.model;

import com.divyanshgoenka.omnify.quicksortmergesort.util.Constants;

import java.io.Serializable;
import java.util.Random;

import io.reactivex.Observable;

/**
 * Created by divyanshgoenka on 27/07/17.
 */

public class RandomNumberSet implements Serializable {
    private final Integer[] numbers;

    private RandomNumberSet(Integer[] numbers) {
        this.numbers = numbers;
    }

    public static class Generator {
        public static Observable<RandomNumberSet> generate(final int size) {
            return Observable.just(size).map(integer -> {
                Random random = new Random();
                Integer[] numbers = new Integer[integer];
                for (int i = 0; i < size; i++) {
                    numbers[i] = Constants.LOWER_BOUND_INCLUSIVE + random.nextInt(Constants.UPPER_BOUND_NON_INCLUSIVE);
                }
                RandomNumberSet randomNumberSet = new RandomNumberSet(numbers);
                return randomNumberSet;
            });
        }
    }

    public Integer[] getNumbers() {
        Integer[] numSet = new Integer[numbers.length];

        for (int i = 0; i < numbers.length; i++)
            numSet[i] = numbers[i];

        return numSet;
    }
}
