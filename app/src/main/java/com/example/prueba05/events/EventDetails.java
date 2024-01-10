package com.example.prueba05.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba05.R;
import com.example.prueba05.objects.Event;

public class EventDetails extends AppCompatActivity {
    private Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        event = (Event) getIntent().getSerializableExtra("selectedEvent");

        Button saveButton = findViewById(R.id.addButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        TextView date = findViewById(R.id.textView19);
        TextView city = findViewById(R.id.textView20);
        TextView city_value = findViewById(R.id.textView21);
        TextView exact_place = findViewById(R.id.textView22);
        TextView exact_place_value = findViewById(R.id.textView23);
        TextView hour = findViewById(R.id.textView24);
        TextView hour_value = findViewById(R.id.textView25);
        TextView comments = findViewById(R.id.textView26);
        TextView comments_value = findViewById(R.id.textView27);
        TextView people_assisting = findViewById(R.id.textView28);
        TextView people_assisting_value = findViewById(R.id.textView29);

        date.setText(event.getDate());
        city_value.setText(event.getCity());
        exact_place_value.setText(event.getExact_place());
        hour_value.setText(event.getHour());
        comments_value.setText(event.getComments());
        people_assisting_value.setText(String.valueOf(event.getPeople()));



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event.addPeople();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedEvent", event);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                event.removePeople();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedEvent", event);
                setResult(RESULT_OK, resultIntent);

                finish();
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}