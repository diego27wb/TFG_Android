package com.example.prueba05;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class PickUpList extends AppCompatActivity {
    private List<PickUp> pickUpList;
    private PickUpAdapter pickUpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickup_list);

        // Inicializa la lista y el adaptador
        pickUpList = new ArrayList<>();
        pickUpAdapter = new PickUpAdapter(this, pickUpList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(pickUpAdapter);

        //AÑADO PICKUPS DE PRUEBA
        SerializableLatLng location = new SerializableLatLng(40.41831, -3.70275);

        pickUpList.add(new PickUp("Basura", "image.png", location, "Buena descripion", SizeEnum.L));
        pickUpList.add(new PickUp("Basura", "image.png", location, "Buena descripion", SizeEnum.L));
        pickUpList.add(new PickUp("Basura", "image.png", location, "Buena descripion", SizeEnum.L));

        // Configura el evento de clic en la lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Abre la nueva actividad para mostrar los detalles del PickUp seleccionado
                Intent intent = new Intent(PickUpList.this, DetallePickUpActivity.class);
                intent.putExtra("pickUp", pickUpList.get(position));
                startActivity(intent);
            }
        });

        // Configura el botón para agregar un nuevo PickUp
        Button agregarButton = findViewById(R.id.agregarButton);
        agregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad para agregar un nuevo PickUp
                Intent intent = new Intent(PickUpList.this, AgregarPickUpActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    // Método para manejar el resultado de la actividad de agregar PickUp
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                // Obtiene el nuevo PickUp creado y lo agrega a la lista
                PickUp nuevoPickUp = (PickUp) data.getSerializableExtra("nuevoPickUp");
                pickUpList.add(nuevoPickUp);
                pickUpAdapter.notifyDataSetChanged();
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
