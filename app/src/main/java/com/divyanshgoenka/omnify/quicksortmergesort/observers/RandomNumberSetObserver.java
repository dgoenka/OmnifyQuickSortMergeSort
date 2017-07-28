package com.divyanshgoenka.omnify.quicksortmergesort.observers;

import com.divyanshgoenka.omnify.quicksortmergesort.interfaces.SortingInterface;
import com.divyanshgoenka.omnify.quicksortmergesort.model.RandomNumberSet;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by divyanshgoenka on 27/07/17.
 */
public class RandomNumberSetObserver implements Observer<RandomNumberSet> {

    private final SortingInterface sortingInterface;

    public RandomNumberSetObserver(SortingInterface sortingInterface) {
        this.sortingInterface = sortingInterface;
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(RandomNumberSet randomNumberSet) {
        sortingInterface.onListReady(randomNumberSet);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
