package com.divyanshgoenka.omnify.quicksortmergesort.views.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.divyanshgoenka.omnify.quicksortmergesort.R;
import com.divyanshgoenka.omnify.quicksortmergesort.model.SortingResult;
import com.divyanshgoenka.omnify.quicksortmergesort.model.SortingResults;
import com.divyanshgoenka.omnify.quicksortmergesort.util.Constants;
import com.divyanshgoenka.omnify.quicksortmergesort.views.adapters.IntergerRecyclerViewAdapter;


public class SortedResultsFragment extends BaseFragment {
    private static final String SORTING_RESULTS = "SORTING_RESULTS";

    SortingResults mSortingResults;

    public static SortedResultsFragment newInstance(SortingResults sortedResults) {
        SortedResultsFragment fragment = new SortedResultsFragment();
        Bundle args = new Bundle();
        args.putSerializable(SORTING_RESULTS, sortedResults);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSortingResults = (SortingResults) getArguments().getSerializable(SORTING_RESULTS);
        }else if(savedInstanceState!=null){
            mSortingResults = (SortingResults) savedInstanceState.getSerializable(SORTING_RESULTS);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(Constants.SORTING_RESULTS,mSortingResults);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().setTitle(R.string.sorted_results);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sorted_results, container, false);
        View mQuickSortLayout = view.findViewById(R.id.quick_sort_layout);
        View mMergeSortLayout = view.findViewById(R.id.merge_sort_layout);
        setView(mQuickSortLayout, mSortingResults.getQuickSortResult());
        setView(mMergeSortLayout, mSortingResults.getMergeSortResult());
        getActivity().invalidateOptionsMenu();
        return view;
    }

    private void setView(View sortLayout, SortingResult sortResult) {
        ((TextView) sortLayout.findViewById(R.id.method_name_time_taken)).setText(sortResult.getMethodNameAndTime());
        RecyclerView recyclerView = (RecyclerView) sortLayout.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new IntergerRecyclerViewAdapter(sortResult.getNumbers()));
    }


}
