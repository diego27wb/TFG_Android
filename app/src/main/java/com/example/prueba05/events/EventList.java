package com.example.prueba05.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.prueba05.R;
import com.example.prueba05.objects.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class EventList extends AppCompatActivity {

    private ArrayList<Event> eventList;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        eventList = new ArrayList<>();
        eventList = (ArrayList<Event>) getIntent().getSerializableExtra("events");
        eventAdapter = new EventAdapter(this, eventList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(eventAdapter);
        Button addEventButton = findViewById(R.id.addEventButton);

        orderList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(EventList.this, EventDetails.class);
                intent.putExtra("selectedEvent", eventList.get(i));
                startActivityForResult(intent, 1);
            }
        });

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventList.this, AddEvent.class);
                startActivityForResult(intent, 2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Event updatedEvent = (Event) data.getSerializableExtra("updatedEvent");

            int position = -1;
            for (int i = 0; i < eventList.size(); i++) {
                if (eventList.get(i).equals(updatedEvent)) {
                    position = i;
                    break;
                }
            }
            if (position != -1) {
                eventList.set(position, updatedEvent);
                eventAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == 2 && resultCode == RESULT_OK){
            Event newEvent = (Event) data.getSerializableExtra("newEvent");
            eventList.add(newEvent);
            eventAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("events", this.eventList);
        setResult(RESULT_OK, resultIntent);
        finish();
        super.onBackPressed();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void orderList(){
        Collections.sort(eventList, new Comparator<Event>() {
            @Override
            public int compare(Event event, Event t1) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

                try{
                    Date date1 = sdf.parse(event.getDate());
                    Date date2 = sdf.parse(t1.getDate());

                    return date1.compareTo(date2);
                } catch (ParseException e){
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        Collections.reverse(eventList);

        dateIsPassed();
    }

    public void dateIsPassed(){
        Date actualDate = new Date();
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

         try {
             for (Event event : eventList) {
                 Date eventDate = sdf.parse(event.getDate());
                 if (eventDate.before(actualDate)){
                     event.setIsPassed("#FF0000");
                 }
             }
         } catch(ParseException e){
             e.printStackTrace();
         }
    }
}