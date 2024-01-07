package com.example.prueba05;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class UserProfile extends AppCompatActivity {
    private User currentUser;
    private TextView name;
    private TextView description;
    private TextView location;
    private TextView points;
    private TextView pickups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.textView8);
        description = findViewById(R.id.textView10);
        location = findViewById(R.id.textView12);
        points = findViewById(R.id.textView15);
        pickups = findViewById(R.id.textView17);
        Button btnEditProfile = findViewById(R.id.buttonEditProfile);
        Button btnDeleteProfile = findViewById(R.id.buttonDeleteProfile);

        Intent intent = getIntent();
        if (intent != null) {
            User user = (User) intent.getSerializableExtra("user");
            if (user != null) {
                this.currentUser = user;
                name.setText(currentUser.getName());
                description.setText(currentUser.getDescription());
                points.setText(String.valueOf(currentUser.getPoints()));
                pickups.setText(String.valueOf(currentUser.getPickUps().size()));
                location.setText(currentUser.getCity().toString());
            }
        }

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(UserProfile.this, UserProfileEdit.class);
                intent1.putExtra("user", currentUser);
                startActivity(intent1);
            }
        });

        btnDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(UserProfile.this, MainScreen.class);
                startActivity(intent2);
            }
        });


    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Método para manejar el resultado de la actividad de agregar PickUp
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                // Obtiene el User editado
                User editedUser = (User) data.getSerializableExtra("userEdited");
                currentUser = editedUser;
                description.setText(editedUser.getDescription());
                location.setText(editedUser.getCity().toString());
            }
        }
    }
}