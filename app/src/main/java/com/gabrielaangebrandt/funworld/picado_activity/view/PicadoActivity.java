package com.gabrielaangebrandt.funworld.picado_activity.view;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.base.SharedPrefs;
import com.gabrielaangebrandt.funworld.models.data_model.Player;
import com.gabrielaangebrandt.funworld.picado_activity.PicadoContract;
import com.gabrielaangebrandt.funworld.picado_activity.presenter.PicadoPresenterImpl;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Gabriela on 23.7.2017..
 */

public class PicadoActivity extends AppCompatActivity implements OnMapReadyCallback, PicadoContract.PicadoView{
    GoogleMap googleMap;
    MapFragment mapFragment;
    Marker marker;
    @BindView(R.id.tv_time)
    TextView time;
    @BindView(R.id.tv_cName) TextView countryName;
    String name;
    PicadoContract.PicadoPresenter presenter;
    private GoogleMap.OnMapClickListener mCustomOnMapClickListener;
    private String timeFormat;
    private LatLngBounds EUROPE = new LatLngBounds(
            new LatLng(38.849975202462815, -16.69921875), new LatLng(49.11846681463632, 87.36328125));

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
                if (marker != null) {
                    marker.remove();
                }
                marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude,
                        latLng.longitude)));
                presenter.checkIfCoordinatesAreCorrect(String.valueOf(latLng.latitude+latLng.longitude), name, getTimeInLong());
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
        uiSettings.setZoomGesturesEnabled(true);
        this.googleMap.setOnMapClickListener(this.mCustomOnMapClickListener);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
      /*  googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(55.70, 13.19)));*/
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(55.70, 13.19)).zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    @Override
    public long sendStartTime() {
        return System.currentTimeMillis();
    }

    @Override
    public void getTime(String timeFormat){
        this.timeFormat = timeFormat;
        time.setText(timeFormat);
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
            if(user.getHsPicado() < score){
                user.setHsPicado(score);
            }
        }
        Player realmUser = realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Your score is:  " + score + "\n" +
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

    public long getTimeInLong(){
        SimpleDateFormat sdf = new SimpleDateFormat("ss");
        Date dateObj= null;
        try {
            dateObj = sdf.parse(timeFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long newScore = dateObj.getTime();
        return Math.abs(newScore);

    }
}
