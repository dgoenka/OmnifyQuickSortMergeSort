package com.divyanshgoenka.omnify.quicksortmergesort.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.divyanshgoenka.omnify.quicksortmergesort.views.activity.MainActivity;

/**
 * Created by divyanshgoenka on 28/07/17.
 */


public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().invalidateOptionsMenu();
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ((MainActivity) getActivity()).popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    public MainActivity getMainActivity() {
        if (getActivity() instanceof MainActivity)
            return ((MainActivity) getActivity());
        return null;
    }
}
