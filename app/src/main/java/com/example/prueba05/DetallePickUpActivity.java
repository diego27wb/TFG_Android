package com.example.prueba05;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DetallePickUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pickup);

        // Obtiene el PickUp seleccionado de la intent
        PickUp pickUp = (PickUp) getIntent().getSerializableExtra("pickUp");

        // Muestra los detalles del PickUp en la interfaz
        TextView titulo = findViewById(R.id.tituloDetalle);
        TextView photo = findViewById(R.id.photoDetalle);
        TextView location = findViewById(R.id.locationDetalle);
        TextView comments = findViewById(R.id.commentsDetalle);
        TextView size = findViewById(R.id.sizeDetalle);

        titulo.setText(pickUp.getTitulo());
        photo.setText(pickUp.getPhoto());
        //location.setText(pickUp.getPosition().toString());
        comments.setText(pickUp.getDescription());
        size.setText(pickUp.getSize().toString());

        Geocoder geocoder = new Geocoder(DetallePickUpActivity.this, Locale.getDefault());
        try {
            List<Address> listAddress = geocoder.getFromLocation(pickUp.getPosition().getLatitude(),pickUp.getPosition().getLongitude(), 1);
            //List<Address> listAddress = geocoder.getFromLocation(1.38,103.27, 1);
            if (listAddress.size()>0){
                location.setText(listAddress.get(0).getCountryName().toString());
                showToast(listAddress.get(0).getCountryCode().toString());
            }
        } catch(IOException e){
            showToast("ERROR");
            e.printStackTrace();
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

