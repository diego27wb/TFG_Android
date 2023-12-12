package com.example.prueba05;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.SupportMapFragment;

public class PickUpNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickup_new);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogo("Add PickUp");
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
                .setView(customView);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
