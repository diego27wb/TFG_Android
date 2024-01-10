package com.example.prueba05.users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.prueba05.MainScreen;
import com.example.prueba05.R;
import com.example.prueba05.objects.User;
import com.google.android.material.textfield.TextInputEditText;

public class UserProfileEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);

        Button saveButton = findViewById(R.id.buttonSaveProfile);
        Button cancelButton = findViewById(R.id.buttonCancelProfile);

        EditText description = findViewById(R.id.descriptionTextField);
        EditText city = findViewById(R.id.cityTextField);

        User user = (User) getIntent().getSerializableExtra("user");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User userResponse = user;
                userResponse.setDescription(description.getText().toString());
                userResponse.setCity(city.getText().toString());
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedUser", userResponse);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                setResult(RESULT_CANCELED, resultIntent);
                finish();
            }
        });

    }
}