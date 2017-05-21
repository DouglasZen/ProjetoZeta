package layout;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.app.tarsus.projetozetaapp.R;
import com.app.tarsus.projetozetaapp.adapter.RecyclerAdapterCard;

public class HashtagFragment extends Fragment {
    private View view;
    private Toolbar toolbar;
    private LinearLayout linear;
    private int height;
    private int width;
    private int posicaoInicial;
    private int telaInteira;
    private TransitionDrawable transFimPreto;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    String[] dataArray = new String[]{"India","Australia","USA","U.K","Japan","Russia","Germany","Pakistan","Bangladesh","Africa","Brazil","Rome","italy"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initialize(inflater, container);
        getTamanhoDisplay();
        setFragmentPosition();
        setFragmentAnimations();


        toolbar.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(final View v, MotionEvent event) {
                view.setBackgroundColor(Color.TRANSPARENT);
                switch (event.getAction()) {
                    // Animacao para toda a acao de touch no fragmento
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate().y(event.getRawY()).setDuration(0).start();
                        break;
                    // Animacao ao soltar o fragmento
                    case MotionEvent.ACTION_UP:
                        if(view.getY() < (posicaoInicial / 2)) {
                            view.animate().y(telaInteira).setDuration(500).start();
                            view.setBackground(transFimPreto);
                            transFimPreto.startTransition(500);
                            ((MainActivity) getActivity()).mapTransparency(true);
                            initCards();
                        }else {
                            view.animate().y(posicaoInicial).setDuration(500).start();
                            ((MainActivity) getActivity()).mapTransparency(false);
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

    private void initialize(LayoutInflater inflater, ViewGroup container){
        view = inflater.inflate(R.layout.fragment_hashtag, container, false);
        linear = (LinearLayout) view.findViewById(R.id.card_fragment);
        toolbar = (Toolbar) view.findViewById(R.id.toolbarFragmentCards);
    }

    private void getTamanhoDisplay(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    private void setFragmentPosition(){
        linear.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
        LinearLayout.LayoutParams relativeParams = new LinearLayout.LayoutParams(linear.getLayoutParams());

        posicaoInicial = relativeParams.height - ((relativeParams.height/100)*10);
        telaInteira = relativeParams.height - ((relativeParams.height/100)*100);
        view.setY(posicaoInicial);
    }

    private void setFragmentAnimations(){
        ColorDrawable[] colorFimPreto = {new ColorDrawable(Color.TRANSPARENT), new ColorDrawable(0xFF38B0DE)};
        transFimPreto = new TransitionDrawable(colorFimPreto);
    }

    private void initCards(){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerCard);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapterCard(dataArray, getActivity(), posicaoInicial, telaInteira, transFimPreto, view);
        recyclerView.setAdapter(adapter);
    }
}
