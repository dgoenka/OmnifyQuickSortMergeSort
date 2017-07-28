package com.divyanshgoenka.omnify.quicksortmergesort.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;

import com.divyanshgoenka.omnify.quicksortmergesort.model.RandomNumberSet;
import com.divyanshgoenka.omnify.quicksortmergesort.model.SortingResults;
import com.divyanshgoenka.omnify.quicksortmergesort.observers.SortingResultsObserver;
import com.divyanshgoenka.omnify.quicksortmergesort.util.Constants;
import com.divyanshgoenka.omnify.quicksortmergesort.util.SortingUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SorterService extends Service {

    public static final String TAG = "SorterService";
    Messenger messenger = new Messenger(new SortingHandler());

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    public void startSorting(final RandomNumberSet randomNumberSet, Observer<SortingResults> observer) {
        Observable.just(randomNumberSet).map(new Function<RandomNumberSet, SortingResults>() {
            @Override
            public SortingResults apply(RandomNumberSet randomNumberSet) throws Exception {
                SortingResults sortingResults = new SortingResults();
                sortingResults.setQuickSortResult(SortingUtil.sort(SortingUtil.SortingMechanism.QUICKSORT, randomNumberSet));
                sortingResults.setMergeSortResult(SortingUtil.sort(SortingUtil.SortingMechanism.MERGESORT, randomNumberSet));
                return sortingResults;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnComplete(() -> {
        }).subscribe(observer);
    }

    class SortingHandler extends Handler {
        @Override
        public void handleMessage(final Message msg) {
            switch (msg.what) {
                case Constants.MSG_SORT:
                    Bundle bundle = msg.getData();
                    RandomNumberSet randomNumberSet = (RandomNumberSet) bundle.getSerializable(Constants.RANDOM_NUMBER_SET);
                    startSorting(randomNumberSet, new SortingResultsObserver(msg));
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }
}
