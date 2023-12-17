package com.example.prueba05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

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

        // Asigna los valores de los atributos a las vistas
        atributo1TextView.setText(pickUp.getTitulo());

        return convertView;
    }
}
