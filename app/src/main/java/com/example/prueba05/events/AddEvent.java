package com.example.prueba05.events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import com.example.prueba05.R;
import com.example.prueba05.objects.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEvent extends AppCompatActivity {
    String date = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        EditText city = findViewById(R.id.textView21);
        EditText exactPlace = findViewById(R.id.textView23);
        EditText hour = findViewById(R.id.textView25);
        EditText comments = findViewById(R.id.textView27);
        CalendarView calendar = findViewById(R.id.calendarView);
        Button saveButton = findViewById(R.id.addEventButton);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(i, i1, i2);

                // Format the selected date as a string
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                date = sdf.format(selectedCalendar.getTime());
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(city.getText()) || TextUtils.isEmpty(exactPlace.getText()) ||
                        TextUtils.isEmpty(hour.getText()) || TextUtils.isEmpty(comments.getText())){
                    incompleteFieldsError();
                } else{
                    String city_input = city.getText().toString();
                    String exactPlace_input = exactPlace.getText().toString();
                    String hour_input = hour.getText().toString();
                    String comments_input = comments.getText().toString();

                    Event event = new Event(date, city_input, exactPlace_input, hour_input,
                            comments_input, 1);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newEvent", event);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }

    private void incompleteFieldsError(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Incomplete fields");
        builder.setMessage("Some fields are mandatory to fill.\nPlease, complete them.");
        builder.setIcon(R.drawable.ic_alert_icon);
        builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do-nothing
            }
        });
        builder.show();
    }
}