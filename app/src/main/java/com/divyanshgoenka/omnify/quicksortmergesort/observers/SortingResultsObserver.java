package com.divyanshgoenka.omnify.quicksortmergesort.observers;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.divyanshgoenka.omnify.quicksortmergesort.model.SortingResults;
import com.divyanshgoenka.omnify.quicksortmergesort.util.Constants;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by divyanshgoenka on 28/07/17.
 */
public class SortingResultsObserver implements Observer<SortingResults> {

    Messenger messenger;

    public SortingResultsObserver(Message msg) {
        this.messenger = msg.replyTo;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(SortingResults sortingResults) {
        Message message = Message.obtain(null, Constants.MSG_RESULTS_AVAILABLE, 0, 0);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.SORTING_RESULTS, sortingResults);
        message.setData(bundle);
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
