package com.divyanshgoenka.omnify.quicksortmergesort.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Function;

/**
 * Created by divyanshgoenka on 27/07/17.
 */

public class RandomNumberSet implements Serializable {
    private final Integer[] numbers;

    private RandomNumberSet(int size) {
        numbers = new Integer[size];
    }

    public static Observable<RandomNumberSet> generate(final int size){
         return Observable.just(size).map(new Function<Integer, RandomNumberSet>() {
             @Override
             public RandomNumberSet apply(Integer integer) throws Exception {
                 RandomNumberSet randomNumberSet = new RandomNumberSet(size);
                 Random random = new Random();
                 for(int i=0;i<size;i++){
                     randomNumberSet.numbers[i] = 10 + random.nextInt(90);
                 }
                 return randomNumberSet;
             }
         });
    }

    public Integer[] getNumbers() {
        Integer[] numSet = new Integer[numbers.length];

        for(int i=0;i<numbers.length;i++)
            numSet[i] = numbers[i];

        return numSet;
    }
}
