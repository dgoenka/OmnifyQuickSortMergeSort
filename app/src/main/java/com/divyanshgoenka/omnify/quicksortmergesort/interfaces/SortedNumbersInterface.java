package com.divyanshgoenka.omnify.quicksortmergesort.interfaces;

import android.os.Handler;

import com.divyanshgoenka.omnify.quicksortmergesort.model.SortingResults;

/**
 * Created by divyanshgoenka on 27/07/17.
 */

public interface SortedNumbersInterface {

    void resultsAvailable(SortingResults sortingResults);

    Handler getHandler();
}
