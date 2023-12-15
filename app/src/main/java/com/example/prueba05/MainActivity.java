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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MainActivity.this);

        if (getIntent().hasExtra("currentLocation")){

            LatLng currentLocation = getIntent().getParcelableExtra("currentLocation");
            showToast(currentLocation.toString());
            myMap.addMarker(new MarkerOptions().position(currentLocation).title("New pickup")).setTag(0);
            myMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

        }

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mostrarDialogo("Agregar sitio");
                Intent intent = new Intent(MainActivity.this, PickUpNew.class);
                startActivity(intent);
            }
        });

    }
    private void mostrarDialogo(String mensaje) {
        // Inflar el diseño personalizado
        View customView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);

        // Obtener una referencia al EditText en el diseño personalizado
        final EditText editText = customView.findViewById(R.id.editText);

        // Configurar el cuadro de diálogo con el diseño personalizado
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje)
                .setTitle("Añadir sitio")
                .setView(customView)
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Obtener el texto ingresado en el EditText
                        String textoIngresado = editText.getText().toString();

                        // Hacer algo con la cadena ingresada (por ejemplo, agregar un marcador con ese texto)
                        agregarMarcadorConTexto(textoIngresado);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void agregarMarcadorConTexto(String texto) {
        // Aquí puedes hacer lo que quieras con la cadena ingresada
        // Por ejemplo, agregar un marcador con ese texto
        LatLng random = new LatLng(40, -3);
        myMap.addMarker(new MarkerOptions().position(random).title(texto).draggable(true)).setTag(0);
        myMap.moveCamera(CameraUpdateFactory.newLatLng(random));
    }


    private void addMap() {
        LatLng random = new LatLng(40, -3);
        myMap.addMarker(new MarkerOptions().position(random).title("Random").draggable(true)).setTag(0);
        myMap.moveCamera(CameraUpdateFactory.newLatLng(random));
    }

    @Override
    public boolean onMarkerClick(final Marker marker){
        dialogoAdicional(marker);
        return true;
    }

    private void dialogoAdicional(final Marker clickedMarker){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("")
                .setTitle(clickedMarker.getTitle())
                .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Elimina el marcador que se hizo clic
                        if (clickedMarker != null) {
                            clickedMarker.remove();
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        myMap = googleMap;

        LatLng lalaguna = new LatLng(28.4853, -16.32014);
        myMap.addMarker(new MarkerOptions().position(lalaguna).title("La Laguna"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(lalaguna));

        myMap.setOnMarkerClickListener(this);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
