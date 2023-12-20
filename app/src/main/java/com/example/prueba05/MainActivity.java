package com.example.prueba05;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MainActivity.this);
    }

    private void agregarCentrosDeReciclaje() {
        HashMap<LatLng, String> centros = new HashMap<>();
        centros.put(new LatLng(52.5200, 13.4050),"Berlin");
        centros.put(new LatLng(41.89193, 12.51133),"Rome");
        centros.put(new LatLng(51.50853, -0.12574),"London");
        centros.put(new LatLng(50.08804, 14.42076),"Prague");
        centros.put(new LatLng(47.36667, 8.55),"Zurich");

        for (LatLng location : centros.keySet()) {
            String nombreCentro = centros.get(location);
            agregarMarcadorCentroReciclaje(nombreCentro, location);
        }
    }

    private void agregarMarcadorCentroReciclaje(String texto, LatLng location){
        float colorHue = 200;
        BitmapDescriptor blueMarkerIcon = BitmapDescriptorFactory.defaultMarker(colorHue);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(location)
                .title(texto)
                .icon(blueMarkerIcon);

        Marker marker = myMap.addMarker(markerOptions);
        marker.setTag(0);
    }

    private void agregarMarcadorConTexto(String texto, LatLng location, String comment, SizeEnum size) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(location)
                .title(texto)
                .snippet(comment + "\n\nSize: " + size);

        Marker marker = myMap.addMarker(markerOptions);
        marker.setTag(0);
        myMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }


    @Override
    public boolean onMarkerClick(final Marker marker){
        dialogoAdicional(marker);
        return true;
    }

    private void dialogoAdicional(final Marker clickedMarker){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String snippet = clickedMarker.getSnippet();
        builder.setMessage(snippet)
                .setTitle(clickedMarker.getTitle())
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do-nothing
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setIcon(R.drawable.pickup_icon);
        myMap.moveCamera(CameraUpdateFactory.newLatLng(clickedMarker.getPosition()));
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        myMap = googleMap;

        myMap.setOnMarkerClickListener(this);

        /**
         * Agregar centros de reciclaje
         */
        agregarCentrosDeReciclaje();

        if (getIntent().hasExtra("pickUps") && getIntent().hasExtra("pickUpsTitles") &&
                getIntent().hasExtra("pickUpsComments") && getIntent().hasExtra("pickUpsSizes")){
            ArrayList<LatLng> pickUpLocations = getIntent().getParcelableArrayListExtra("pickUps");
            ArrayList<String> pickUpTitles = getIntent().getStringArrayListExtra("pickUpsTitles");
            ArrayList<String> pickUpComments = getIntent().getStringArrayListExtra("pickUpsComments");
            ArrayList<SizeEnum> pickUpSizes = (ArrayList<SizeEnum>) getIntent().getSerializableExtra("pickUpsSizes");

            if (pickUpLocations != null && pickUpTitles != null && pickUpComments != null && pickUpSizes != null){
                int i = 0;
                for (LatLng location : pickUpLocations){
                    agregarMarcadorConTexto(pickUpTitles.get(i), location, pickUpComments.get(i), pickUpSizes.get(i));
                    i++;
                }
            }else{
                showToast("HOLAAAAAAA");
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
