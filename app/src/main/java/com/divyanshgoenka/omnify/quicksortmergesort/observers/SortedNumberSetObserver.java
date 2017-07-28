package com.divyanshgoenka.omnify.quicksortmergesort.observers;

import android.util.Log;

import com.divyanshgoenka.omnify.quicksortmergesort.interfaces.SortedNumbersInterface;
import com.divyanshgoenka.omnify.quicksortmergesort.model.SortingResults;
import com.divyanshgoenka.omnify.quicksortmergesort.views.fragments.UnsortedFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by divyanshgoenka on 27/07/17.
 */

public class SortedNumberSetObserver implements Observer<SortingResults> {
    public static final String TAG = "SortedNumberSetObserver";

    private final SortedNumbersInterface sortedNumbersInterface;

    public SortedNumberSetObserver(SortedNumbersInterface sortedNumbersInterface) {
        this.sortedNumbersInterface = sortedNumbersInterface;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(SortingResults sortingResults) {
        Log.e(TAG,"in onNext, sortingResults is "+sortingResults);
        sortedNumbersInterface.resultsAvailable(sortingResults);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
