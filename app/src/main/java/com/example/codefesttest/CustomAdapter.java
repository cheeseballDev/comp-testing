package com.example.codefesttest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<Products> localDataSet;

    public CustomAdapter(List<Products>dataSet) {
        this.localDataSet = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView productTitle;

        public ViewHolder(View view) {
            super(view);

            productTitle = view.findViewById(R.id.product_title);

            // Define click listener for the ViewHolder's View
            view.setOnClickListener(
                    v -> {
                        System.out.println("Clicked.");
                    }
            );
        }

        public void bind(Products product) {
            productTitle.setText(product.getProdName());
        }
        public TextView getProductTitle() {
            return productTitle;
        }

        // add more product information methods here
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.bind(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet != null ? localDataSet.size() : 0;
    }
}