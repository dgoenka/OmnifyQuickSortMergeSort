package com.divyanshgoenka.omnify.quicksortmergesort.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.divyanshgoenka.omnify.quicksortmergesort.R;

import java.util.List;

public class IntergerRecyclerViewAdapter extends RecyclerView.Adapter<IntergerRecyclerViewAdapter.ViewHolder> {

    private final Integer[] mValues;

    public IntergerRecyclerViewAdapter(Integer[] items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_interger, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Integer mItem = mValues[position];
        holder.mIdView.setText(mItem.toString());
    }

    @Override
    public int getItemCount() {
        return mValues.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;

        public ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.number);
        }
    }
}
