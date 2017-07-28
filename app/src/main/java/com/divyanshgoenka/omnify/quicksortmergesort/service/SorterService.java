package com.divyanshgoenka.omnify.quicksortmergesort.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.divyanshgoenka.omnify.quicksortmergesort.model.RandomNumberSet;
import com.divyanshgoenka.omnify.quicksortmergesort.model.SortingResult;
import com.divyanshgoenka.omnify.quicksortmergesort.model.SortingResults;
import com.divyanshgoenka.omnify.quicksortmergesort.observers.SortedNumberSetObserver;
import com.divyanshgoenka.omnify.quicksortmergesort.service.binder.SortBinder;
import com.divyanshgoenka.omnify.quicksortmergesort.interfaces.ServiceInterface;
import com.divyanshgoenka.omnify.quicksortmergesort.util.SortingUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SorterService extends Service implements ServiceInterface {

    public static final String TAG = "SorterService";
    SortBinder sortBinder = new SortBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sortBinder.registerServiceInterface(this);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sortBinder.unregisterServiceInterface(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return sortBinder;
    }

    @Override
    public void startSorting(final RandomNumberSet randomNumberSet, SortedNumberSetObserver sortedNumberSetObserver) {
        Observable.just(randomNumberSet).map(new Function<RandomNumberSet, SortingResults>() {
            @Override
            public SortingResults apply(RandomNumberSet randomNumberSet) throws Exception {
               SortingResults sortingResults = new SortingResults();
               sortingResults.setQuickSortResult(SortingUtil.sort(SortingUtil.SortingMechanism.QUICKSORT,randomNumberSet));
               sortingResults.setMergeSortResult(SortingUtil.sort(SortingUtil.SortingMechanism.MERGESORT,randomNumberSet));
               return sortingResults;
           }
       }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(sortedNumberSetObserver);
    }
}
