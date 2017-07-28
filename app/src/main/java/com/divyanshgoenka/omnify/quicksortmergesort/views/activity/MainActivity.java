package com.divyanshgoenka.omnify.quicksortmergesort.views.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.divyanshgoenka.omnify.quicksortmergesort.R;
import com.divyanshgoenka.omnify.quicksortmergesort.views.fragments.UnsortedFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
            switchFragment(UnsortedFragment.newInstance());
        }
        getSupportFragmentManager().addOnBackStackChangedListener(this);

    }

    public void switchFragment(Fragment fragment){
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

    public void shouldDisplayHomeUp(){
        //Enable Up button only  if there are entries in the back stack
        boolean canback = getSupportFragmentManager().getBackStackEntryCount()>1;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
    }
}
