package layout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.app.tarsus.projetozetaapp.LoginActivity;
import com.app.tarsus.projetozetaapp.MainActivity;
import com.app.tarsus.projetozetaapp.R;
import com.facebook.login.LoginManager;


public class menu_fragment extends Fragment {
    public boolean abrir = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_menu_fragment, container, false);
        setRetainInstance(true);
        view.findViewById(R.id.btMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu(view);
            }
        });

        view.findViewById(R.id.btSair).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sair(v);
            }
        });
        return view;
    }

    public void menu(View view){
        ImageButton bmenu = (ImageButton) view.findViewById(R.id.btMenu);
        ImageButton bsair = (ImageButton) view.findViewById(R.id.btSair);
        AnimatorSet lista = new AnimatorSet();
        ObjectAnimator obj;

        if(!abrir){
            obj = ObjectAnimator.ofFloat(bmenu, "rotation", 0, 90);
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(bsair, "y",bsair.getY(), bmenu.getY() - (bmenu.getWidth() + 2));
            lista.playSequentially(obj);

            abrir = true;
        }else{
            obj = ObjectAnimator.ofFloat(bsair, "y",bsair.getY(), bmenu.getY() - 2);
            lista.playSequentially(obj);
            obj = ObjectAnimator.ofFloat(bmenu, "rotation", 0, 0);
            lista.playSequentially(obj);
            abrir = false;
        }
        lista.setDuration(300);
        lista.start();

    }

    public void sair(View view){
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
