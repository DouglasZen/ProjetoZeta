package com.app.tarsus.projetozetaapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.facebook.login.LoginManager;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient mGoogleApiClient;
    protected GoogleMap map;
    private FloatingSearchView mSearchView;
    private BottomSheetLayout bottomSheet;
    Toolbar toolbar;
    TextView titulo;

    private static final String[] testeLista = new String[]{"amora", "maçã", "laranja", "pera"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbarTitulo);
        titulo = (TextView) findViewById(R.id.labelTitulo);
        /*bottomSheet = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        bottomSheet.setPeekOnDismiss(true);*/

        //FragmentManager fm = getFragmentManager();

        carregaMapa();
        configuraShearchBar();



    }

    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void showToobar(boolean show){
        if(show) {
            toolbar.setVisibility(View.VISIBLE);
            titulo.setVisibility(View.VISIBLE);
        }else{
            toolbar.setVisibility(View.INVISIBLE);
            titulo.setVisibility(View.INVISIBLE);
        }
    }

    public void showCards(View view){
        //new TesteFragment().show(getSupportFragmentManager(), R.id.bottomsheet);
    }

    private void configuraShearchBar(){
        AutoCompleteTextView atv = (AutoCompleteTextView) findViewById(R.id.atResult);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, testeLista);
        atv.setAdapter(adapter);
    }


    private void carregaMapa(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }



    private void setMapLocation(Location l) {
        if(map != null && l != null){
            LatLng latLng = new LatLng(l.getLatitude(), l.getLongitude());
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            map.animateCamera(update);

            CircleOptions circle = new CircleOptions().center(latLng);
            circle.fillColor(Color.RED);
            circle.radius(25);
            map.clear();
            map.addCircle(circle);
        }
    }

    public void mapTransparency(boolean aplicar){
        if(aplicar)
            getSupportFragmentManager().findFragmentById(R.id.map).getView().animate().alpha(0).setDuration(500).start();
        else
            getSupportFragmentManager().findFragmentById(R.id.map).getView().animate().alpha(1).setDuration(500).start();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }catch (Exception e){
            Log.e("Erro localizacao", e.getMessage());
        }
        Location l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        setMapLocation(l);
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }
}
