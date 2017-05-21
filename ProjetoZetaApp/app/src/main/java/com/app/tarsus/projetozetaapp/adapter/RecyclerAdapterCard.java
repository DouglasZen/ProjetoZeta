package com.app.tarsus.projetozetaapp.adapter;

import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.tarsus.projetozetaapp.R;

import layout.MainActivity;


public class RecyclerAdapterCard extends RecyclerView.Adapter<RecyclerAdapterCard.ViewHolder> {
    private String[] dataSource;
    private FragmentActivity activity;
    private int posicaoInicial;
    private int telaInteira;
    private TransitionDrawable transFimPreto;
    private View fragment;

    public RecyclerAdapterCard(String[] dataSource, FragmentActivity activity, int posicaoInicial, int telaInteira, TransitionDrawable transFimPreto, View view){
        this.dataSource = dataSource;
        this.activity = activity;
        this.posicaoInicial = posicaoInicial;
        this.telaInteira = telaInteira;
        this.transFimPreto = transFimPreto;
        this.fragment = view;
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
        if(position == 0) {
            holder.itemView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(final View v, MotionEvent event) {
                    Log.i("teste", event.getY() + "     " + event.getRawY());
                    fragment.setBackgroundColor(Color.TRANSPARENT);
                    switch (event.getAction()) {
                        // Animacao para toda a acao de touch no fragmento
                        case MotionEvent.ACTION_DOWN:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            fragment.animate().y(event.getY()).setDuration(0).start();
                            break;
                        // Animacao ao soltar o fragmento
                        case MotionEvent.ACTION_POINTER_DOWN:
                            fragment.animate().y(posicaoInicial).setDuration(500).start();
                            ((MainActivity) activity).mapTransparency(false);
                            break;
                        default:
                            return false;
                    }
                    return true;
                }
            });
        }
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