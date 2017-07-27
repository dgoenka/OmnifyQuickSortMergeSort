package com.divyanshgoenka.omnify.quicksortmergesort.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.io.FileDescriptor;

public class SorterService extends Service {

    IBinder iBinder = new SortBinder();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
    class SortBinder extends Binder {
        SorterService getService() {
            return SorterService.this;
        }
    }
}
