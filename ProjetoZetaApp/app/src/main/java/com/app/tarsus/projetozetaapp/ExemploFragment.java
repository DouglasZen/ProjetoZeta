package com.app.tarsus.projetozetaapp;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by Leandro on 12/05/2017.
 */

public class ExemploFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exemplo_fragment, container, false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        LinearLayout linear = (LinearLayout) view.findViewById(R.id.card_fragment);
        linear.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        LinearLayout.LayoutParams relativeParams = new LinearLayout.LayoutParams(linear.getLayoutParams());

        final int valorInicial = relativeParams.height - ((relativeParams.height/100)*10);
        view.setY(valorInicial);
        //relativeParams.setMargins(20, 0, 20, (relativeParams.height * -1) + ((relativeParams.height/100)*10));

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            float dX = 0, dY = 0;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //dX = v.getX() - event.getRawX();
                    dY = v.getY() - event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    v.animate().y(event.getRawY()).setDuration(0).start();
                    break;
                case MotionEvent.ACTION_UP:
                    if(event.getRawY() < height / 2)
                        v.animate().y(valorInicial).setDuration(500).start();
                    else
                        v.animate().y(height).setDuration(500).start();
                    break;
                default:
                    return false;
            }
            return true;
            }
        });

        return view;
    }
}
