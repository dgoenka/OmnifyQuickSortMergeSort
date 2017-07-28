package com.divyanshgoenka.omnify.quicksortmergesort.views.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.divyanshgoenka.omnify.quicksortmergesort.R;
import com.divyanshgoenka.omnify.quicksortmergesort.interfaces.SortedNumbersInterface;
import com.divyanshgoenka.omnify.quicksortmergesort.model.RandomNumberSet;
import com.divyanshgoenka.omnify.quicksortmergesort.model.SortingResults;
import com.divyanshgoenka.omnify.quicksortmergesort.observers.RandomNumberSetObserver;
import com.divyanshgoenka.omnify.quicksortmergesort.observers.SortedNumberSetObserver;
import com.divyanshgoenka.omnify.quicksortmergesort.service.SorterService;
import com.divyanshgoenka.omnify.quicksortmergesort.service.binder.SortBinder;
import com.divyanshgoenka.omnify.quicksortmergesort.interfaces.FragmentInterface;
import com.divyanshgoenka.omnify.quicksortmergesort.util.Constants;
import com.divyanshgoenka.omnify.quicksortmergesort.views.adapters.IntergerRecyclerViewAdapter;
import com.divyanshgoenka.omnify.quicksortmergesort.interfaces.SortingInterface;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UnsortedFragment extends BaseFragment implements SortingInterface, SortedNumbersInterface, FragmentInterface {

    RecyclerView recyclerView;

    SortBinder sortBinder;

    RandomNumberSet randomNumberSet;

    RandomNumberSetObserver randomNumberSetObserver = new RandomNumberSetObserver(this);

    SortedNumberSetObserver sortedNumberSetObserver = new SortedNumberSetObserver(this);

    private ServiceConnection mSortServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            sortBinder.unregisterFragmentInterface(UnsortedFragment.this);
            sortBinder =null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            sortBinder = (SortBinder) service;
            sortBinder.registerFragmentInterface(UnsortedFragment.this);
            sortBinder.sort(randomNumberSet,sortedNumberSetObserver);

        }
    };

    public static UnsortedFragment newInstance() {
        UnsortedFragment fragment = new UnsortedFragment();
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.unsorted_fargment_menu,menu);
        getActivity().setTitle(R.string.unsorted_list);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sort:
                startSorting();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startSorting(){
        Intent intent = new Intent(getActivity(), SorterService.class);
        getActivity().startService(intent);
        getActivity().bindService(intent, mSortServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().unbindService(mSortServiceConnection);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_interger_list, container, false);
        setupView(recyclerView);
        return recyclerView;
    }

    public void setupView(View view){
        if (view instanceof RecyclerView) {
            setupRecyclerView((RecyclerView) view);
            populateList();
        }
    }

    public void setupRecyclerView(RecyclerView recyclerView){
        Context context = recyclerView.getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void populateList(){
        randomNumberSetObserver = new RandomNumberSetObserver(this);
        RandomNumberSet.generate(Constants.DEFAULT_SIZE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(randomNumberSetObserver);

    }


    @Override
    public void onListReady(RandomNumberSet randomNumberSet) {
        this.randomNumberSet =randomNumberSet;
        recyclerView.setAdapter(new IntergerRecyclerViewAdapter(randomNumberSet.getNumbers()));
    }

    @Override
    public void resultsAvailable(SortingResults sortingResults) {
        getMainActivity().switchFragment(SortedResultsFragment.newInstance(sortingResults));
    }


}
