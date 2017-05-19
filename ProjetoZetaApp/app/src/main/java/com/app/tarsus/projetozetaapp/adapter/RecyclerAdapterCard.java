package com.app.tarsus.projetozetaapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.tarsus.projetozetaapp.R;

public class RecyclerAdapterCard extends RecyclerView.Adapter<RecyclerAdapterCard.ViewHolder> {
    private String[] dataSource;

    public RecyclerAdapterCard(String[] dataArgs){
        dataSource = dataArgs;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView.setText(dataSource[position]);
    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        protected TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView =  (TextView) itemView.findViewById(R.id.list_item);
        }
    }
}