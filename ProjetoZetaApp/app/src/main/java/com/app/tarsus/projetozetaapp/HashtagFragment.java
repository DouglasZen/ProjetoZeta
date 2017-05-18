package com.app.tarsus.projetozetaapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class HashtagFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Iniciando fragment
        View view = inflater.inflate(R.layout.fragment_hashtag, container, false);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        LinearLayout linear = (LinearLayout) view.findViewById(R.id.card_fragment);
        linear.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        LinearLayout.LayoutParams relativeParams = new LinearLayout.LayoutParams(linear.getLayoutParams());

        final int valorInicial = relativeParams.height - ((relativeParams.height/100)*10);
        view.setY(valorInicial);
        final int telaInteira = relativeParams.height - ((relativeParams.height/100)*100);

        ColorDrawable[] colorFimPreto = {new ColorDrawable(Color.TRANSPARENT), new ColorDrawable(0xFF38B0DE)};
        final TransitionDrawable transFimPreto = new TransitionDrawable(colorFimPreto);

        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(final View v, MotionEvent event) {
                v.setBackgroundColor(Color.TRANSPARENT);
                switch (event.getAction()) {
                    // Animacao para toda a acao de touch no fragmento
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        v.animate().y(event.getRawY()).setDuration(0).start();
                        break;
                    // Animacao ao soltar o fragmento
                    case MotionEvent.ACTION_UP:
                        if(v.getY() < (valorInicial / 2)) {
                            v.animate().y(telaInteira).setDuration(500).start();
                            v.setBackground(transFimPreto);
                            transFimPreto.startTransition(500);
                            ((MainActivity) getActivity()).mapTransparency(true);
                            ((MainActivity) getActivity()).showToobar(true);
                        }else {
                            v.animate().y(valorInicial).setDuration(500).start();
                            ((MainActivity) getActivity()).mapTransparency(false);
                            ((MainActivity) getActivity()).showToobar(false);
                        }
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
