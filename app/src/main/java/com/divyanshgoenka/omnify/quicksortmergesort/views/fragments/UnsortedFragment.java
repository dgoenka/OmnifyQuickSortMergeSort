package com.divyanshgoenka.omnify.quicksortmergesort.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.divyanshgoenka.omnify.quicksortmergesort.interfaces.SortingInterface;
import com.divyanshgoenka.omnify.quicksortmergesort.model.RandomNumberSet;
import com.divyanshgoenka.omnify.quicksortmergesort.model.SortingResults;
import com.divyanshgoenka.omnify.quicksortmergesort.observers.RandomNumberSetObserver;
import com.divyanshgoenka.omnify.quicksortmergesort.util.Constants;
import com.divyanshgoenka.omnify.quicksortmergesort.views.adapters.IntergerRecyclerViewAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UnsortedFragment extends BaseFragment implements SortingInterface, SortedNumbersInterface {

    RecyclerView recyclerView;

    RandomNumberSet randomNumberSet;

    RandomNumberSetObserver randomNumberSetObserver = new RandomNumberSetObserver(this);

    Handler resultsMessageHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case Constants.MSG_RESULTS_AVAILABLE:
                    Bundle bundle = message.getData();
                    SortingResults sortingResults = (SortingResults) bundle.getSerializable(Constants.SORTING_RESULTS);
                    resultsAvailable(sortingResults);
            }
            return false;
        }
    });

    public static UnsortedFragment newInstance() {
        UnsortedFragment fragment = new UnsortedFragment();
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.unsorted_fargment_menu, menu);
        getActivity().setTitle(R.string.unsorted_list);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort:
                startSorting();
                break;

            case R.id.regenerate:
                populateList();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startSorting() {
        getMainActivity().sendSortMessage(randomNumberSet);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getMainActivity().registerSortedNumberInterface(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getMainActivity().unregisterSortedNumbersInterface();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_interger_list, container, false);
        setupView(recyclerView);
        return recyclerView;
    }

    public void setupView(View view) {
        if (view instanceof RecyclerView) {
            setupRecyclerView((RecyclerView) view);
            populateList();
        }
    }

    public void setupRecyclerView(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void populateList() {
        randomNumberSetObserver = new RandomNumberSetObserver(this);
        RandomNumberSet.Generator.generate(Constants.DEFAULT_SIZE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(randomNumberSetObserver);

    }


    @Override
    public void onListReady(RandomNumberSet randomNumberSet) {
        this.randomNumberSet = randomNumberSet;
        recyclerView.setAdapter(new IntergerRecyclerViewAdapter(randomNumberSet.getNumbers()));
    }

    @Override
    public void resultsAvailable(SortingResults sortingResults) {
        getMainActivity().switchFragment(SortedResultsFragment.newInstance(sortingResults));
    }


    @Override
    public Handler getHandler() {
        return resultsMessageHandler;
    }


}
