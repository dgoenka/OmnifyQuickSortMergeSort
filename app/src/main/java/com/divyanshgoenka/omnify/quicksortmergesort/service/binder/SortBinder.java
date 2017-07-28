package com.divyanshgoenka.omnify.quicksortmergesort.service.binder;

import android.os.Binder;

import com.divyanshgoenka.omnify.quicksortmergesort.interfaces.FragmentInterface;
import com.divyanshgoenka.omnify.quicksortmergesort.interfaces.ServiceInterface;
import com.divyanshgoenka.omnify.quicksortmergesort.model.RandomNumberSet;
import com.divyanshgoenka.omnify.quicksortmergesort.observers.SortedNumberSetObserver;

/**
 * Created by divyanshgoenka on 27/07/17.
 */
public class SortBinder extends Binder {
    private FragmentInterface fragmentInterface;
    private ServiceInterface serviceInterface;

    public void registerFragmentInterface(FragmentInterface fragmentInterface) {
        this.fragmentInterface = fragmentInterface;
    }

    public void unregisterFragmentInterface(FragmentInterface fragmentInterface) {
        if(this.fragmentInterface==fragmentInterface)
            this.fragmentInterface = null;
    }

    public void registerServiceInterface(ServiceInterface serviceInterface){
        this.serviceInterface = serviceInterface;
    }

    public void unregisterServiceInterface(ServiceInterface serviceInterface){
        if(this.serviceInterface == serviceInterface)
            this.serviceInterface = null;
    }

    public void sort(RandomNumberSet randomNumberSet, SortedNumberSetObserver sortedNumberSetObserver) {
        serviceInterface.startSorting(randomNumberSet,sortedNumberSetObserver);
    }
}
