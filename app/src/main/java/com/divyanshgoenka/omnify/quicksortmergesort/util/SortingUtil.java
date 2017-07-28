package com.divyanshgoenka.omnify.quicksortmergesort.util;

import com.divyanshgoenka.omnify.quicksortmergesort.model.RandomNumberSet;
import com.divyanshgoenka.omnify.quicksortmergesort.model.SortingResult;

/**
 * Created by divyanshgoenka on 27/07/17.
 */

public class SortingUtil {
    public static SortingResult sort(SortingMechanism sortingMechanism,RandomNumberSet randomNumberSet) {
        SortingResult sortingResult = new SortingResult();
        Integer[] numberSet = randomNumberSet.getNumbers();
        long timeNow = System.currentTimeMillis();
            switch (sortingMechanism){
                case MERGESORT: mergeSort(numberSet);break;
                case QUICKSORT:quickSort(numberSet);break;

            }
        sortingResult.setTimeTaken(System.currentTimeMillis() - timeNow);
        sortingResult.setNumbers(numberSet);
        sortingResult.setSortingMechanism(sortingMechanism);
        return sortingResult;
    }

    private static void quickSort(Integer[] numberSet) {
        new QuickSort().sort(numberSet);
    }

    private static void mergeSort(Integer[] numberSet) {
        new MergeSort().sort(numberSet);
    }

    public  enum SortingMechanism{
        QUICKSORT, MERGESORT
    }
}
