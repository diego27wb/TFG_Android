package com.example.prueba05;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PickUpAdapter extends ArrayAdapter<PickUp> {
    public PickUpAdapter(Context context, List<PickUp> pickups){
        super(context, 0, pickups);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pickup, parent, false);
        }


        PickUp pickUp = getItem(position);

        TextView atributo1TextView = convertView.findViewById(R.id.titulo);
        TextView pais = convertView.findViewById(R.id.pais);

        // Asigna los valores de los atributos a las vistas
        atributo1TextView.setText(pickUp.getTitulo());

        Geocoder geocoder = new Geocoder(PickUpAdapter.this.getContext(), Locale.getDefault());
        try {
            List<Address> listAddress = geocoder.getFromLocation(pickUp.getPosition().getLatitude(),pickUp.getPosition().getLongitude(), 1);
            if (listAddress.size()>0){
                pais.setText(listAddress.get(0).getLocality().toString()+", "+listAddress.get(0).getCountryName().toString());
                //pais.setText(listAddress.get(0).getCountryName().toString());
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        return convertView;
    }
}
