package com.divyanshgoenka.omnify.quicksortmergesort.interfaces;

import com.divyanshgoenka.omnify.quicksortmergesort.model.RandomNumberSet;
import com.divyanshgoenka.omnify.quicksortmergesort.observers.SortedNumberSetObserver;

/**
 * Created by divyanshgoenka on 27/07/17.
 */

public interface ServiceInterface {
    void startSorting(RandomNumberSet randomNumberSet, SortedNumberSetObserver sortedNumberSetObserver);
}
