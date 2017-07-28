package com.divyanshgoenka.omnify.quicksortmergesort.model;

import java.io.Serializable;

/**
 * Created by divyanshgoenka on 27/07/17.
 */

public class SortingResults implements Serializable {
    private SortingResult quickSortResult;
    private SortingResult mergeSortResult;

    public SortingResult getQuickSortResult() {
        return quickSortResult;
    }

    public void setQuickSortResult(SortingResult quickSortResult) {
        this.quickSortResult = quickSortResult;
    }

    public SortingResult getMergeSortResult() {
        return mergeSortResult;
    }

    public void setMergeSortResult(SortingResult mergeSortResult) {
        this.mergeSortResult = mergeSortResult;
    }
}
