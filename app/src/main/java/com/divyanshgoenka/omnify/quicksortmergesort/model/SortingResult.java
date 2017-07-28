package com.divyanshgoenka.omnify.quicksortmergesort.model;

import com.divyanshgoenka.omnify.quicksortmergesort.util.SortingUtil;

import java.io.Serializable;

/**
 * Created by divyanshgoenka on 27/07/17.
 */

public class SortingResult implements Serializable {
    private Integer[] numbers;
    private SortingUtil.SortingMechanism sortingMechanism;
    private long timeTaken;

    public Integer[] getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer[] numbers) {
        this.numbers = numbers;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public CharSequence getMethodNameAndTime() {
        return getSortingMechanism().toString() + " - " + getTimeTaken() + "ms";
    }

    public SortingUtil.SortingMechanism getSortingMechanism() {
        return sortingMechanism;
    }

    public void setSortingMechanism(SortingUtil.SortingMechanism sortingMechanism) {
        this.sortingMechanism = sortingMechanism;
    }
}
