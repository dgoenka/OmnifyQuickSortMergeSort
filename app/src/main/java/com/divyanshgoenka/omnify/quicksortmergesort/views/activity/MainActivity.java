package com.divyanshgoenka.omnify.quicksortmergesort.views.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.divyanshgoenka.omnify.quicksortmergesort.R;
import com.divyanshgoenka.omnify.quicksortmergesort.interfaces.SortedNumbersInterface;
import com.divyanshgoenka.omnify.quicksortmergesort.model.RandomNumberSet;
import com.divyanshgoenka.omnify.quicksortmergesort.service.SorterService;
import com.divyanshgoenka.omnify.quicksortmergesort.util.Constants;
import com.divyanshgoenka.omnify.quicksortmergesort.views.fragments.UnsortedFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    Messenger messenger;

    SortedNumbersInterface sortedNumbersInterface;

    private ServiceConnection mSortServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }
    };

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.container);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mSortServiceConnection);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, SorterService.class);
        startService(intent);
        bindService(intent, mSortServiceConnection, Context.BIND_AUTO_CREATE);

        if (savedInstanceState == null) {
            switchFragment(UnsortedFragment.newInstance());
        }

        getSupportFragmentManager().addOnBackStackChangedListener(this);


    }

    public void registerSortedNumberInterface(SortedNumbersInterface sortedNumbersInterface) {
        this.sortedNumbersInterface = sortedNumbersInterface;
    }

    public void sendSortMessage(RandomNumberSet randomNumberSet) {
        Message msg = Message.obtain(null, Constants.MSG_SORT, 0, 0);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.RANDOM_NUMBER_SET, randomNumberSet);
        msg.setData(bundle);
        msg.replyTo = new Messenger(sortedNumbersInterface.getHandler());
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();
    }


    @Override
    public void onBackPressed() {
        popBackStack();
    }

    public void popBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStackImmediate();
        }
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
        invalidateOptionsMenu();
    }

    public void shouldDisplayHomeUp() {
        //Enable Up button only  if there are entries in the back stack
        boolean canback = getSupportFragmentManager().getBackStackEntryCount() > 1;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
    }

    public void unregisterSortedNumbersInterface() {
       sortedNumbersInterface = null;
    }


}
