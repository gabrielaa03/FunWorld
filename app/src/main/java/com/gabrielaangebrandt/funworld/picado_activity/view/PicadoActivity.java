package com.gabrielaangebrandt.funworld.picado_activity.view;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.base.Converter;
import com.gabrielaangebrandt.funworld.base.SharedPrefs;
import com.gabrielaangebrandt.funworld.models.data_model.Player;
import com.gabrielaangebrandt.funworld.picado_activity.PicadoContract;
import com.gabrielaangebrandt.funworld.picado_activity.presenter.PicadoPresenterImpl;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Gabriela on 23.7.2017..
 */

public class PicadoActivity extends AppCompatActivity implements OnMapReadyCallback, PicadoContract.PicadoView{
    GoogleMap googleMap;
    MapFragment mapFragment;
    Marker marker, marker2;
    @BindView(R.id.tv_time)
    TextView time;
    @BindView(R.id.tv_Name) TextView countryName;
    private String name;
    PicadoContract.PicadoPresenter presenter;
    private GoogleMap.OnMapClickListener mCustomOnMapClickListener;
    private String timeFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picado_layout);
        ButterKnife.bind(this);
        presenter = new PicadoPresenterImpl(this);
        this.mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.f_google_map);
        this.mapFragment.getMapAsync(this);
        this.mCustomOnMapClickListener = new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                marker = googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(new LatLng(latLng.latitude,
                        latLng.longitude)));
                String[] trueValues =  presenter.showRealCoordinatesOfCity(name);
                marker2 = googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).position(new LatLng(Double.parseDouble(trueValues[0]),
                        Double.parseDouble(trueValues[1]))));
                presenter.checkIfCoordinatesAreCorrect(String.valueOf(latLng.latitude + ","+ latLng.longitude), name, Converter.getTimeInLong(timeFormat));
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        UiSettings uiSettings = this.googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));
            if (!success) {
                Log.e("error", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("error", "Can't find style. Error: ", e);
        }

        LatLngBounds EUROPE = new LatLngBounds(
                new LatLng(35.88905007936091, -5.625), new LatLng(69.90011762668541,20.56640625));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(EUROPE.getCenter(), 3));
        this.googleMap.setOnMapClickListener(this.mCustomOnMapClickListener);
    }

    @Override
    public long sendStartTime() {
        return System.currentTimeMillis();
    }

    @Override
    public void getTime(final String timeFormat){
        if (marker != null || marker2 != null) {
            marker.remove();
            marker2.remove();
        }

        final android.os.Handler handler1 = new android.os.Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                time.setText(timeFormat);

            }
        }, 500);
        this.timeFormat = timeFormat;

    }

    @Override
    public void sendCityName(String name) {
        this.name = name;
        countryName.setText(name);
    }

    @Override
    public void showScore(double score) {
        presenter.onStop();
        Realm realm = Realm.getDefaultInstance();
        String username = SharedPrefs.getDefaults("username", this);
        String password = SharedPrefs.getDefaults("password", this);
        realm.beginTransaction();
        Player user = realm.where(Player.class).equalTo("username", username).equalTo("password", password).findFirst();
        if(user != null){
            if(user.getHsPicado() > (int) score){
                user.setHsPicado((int) score);
            }
        }
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Your score is:  " + (int) score + "\n" +
                "Your best score is : " + user.getHsPicado())
                .setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("Replay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter.onStart();
                    }
                }).show();
    }
}
