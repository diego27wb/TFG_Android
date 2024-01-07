package com.example.prueba05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class UserProfileEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);

        Button saveButton = findViewById(R.id.buttonSaveProfile);
        Button cancelButton = findViewById(R.id.buttonCancelProfile);

        TextInputEditText description = findViewById(R.id.descriptionTextField);
        TextInputEditText city = findViewById(R.id.cityTextField);

        String description_final = description.getText().toString();
        String city_final = city.getText().toString();

        User user = (User) getIntent().getSerializableExtra("user");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileEdit.this, MainScreen.class);
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileEdit.this, MainScreen.class);
                startActivity(intent);
            }
        });

    }
}