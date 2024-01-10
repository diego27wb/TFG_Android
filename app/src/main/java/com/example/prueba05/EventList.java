package com.example.prueba05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EventList extends AppCompatActivity {

    private ArrayList<Event> eventList;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        eventList = new ArrayList<>();
        eventAdapter = new EventAdapter(this, eventList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(eventAdapter);
        Button addEventButton = findViewById(R.id.addEventButton);

        //TEST WITH EVENTS
        generateEvents();
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

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void generateEvents(){
        Event event = new Event("22-01-2024", "Madrid", "El Retiro", "15:00", "Let's go to pick the trash from the Retiro!", 1);
        Event event1 = new Event("22-01-2024", "New York", "Central Park", "15:00", "Let's clean up Central Park!", 4);
        Event event2 = new Event("23-01-2024", "London", "Hyde Park", "14:30", "Hyde Park Cleanup Day!", 6);
        Event event3 = new Event("25-01-2024", "Tokyo", "Ueno Park", "16:00", "Join us in cleaning up Ueno Park.", 2);
        Event event4 = new Event("28-01-2024", "Sydney", "Bondi Beach", "17:30", "Community beach cleanup event.", 10);
        Event event5 = new Event("02-02-2024", "Paris", "Bois de Vincennes", "18:00", "Let's make Bois de Vincennes clean again!", 12);
        Event event6 = new Event("05-02-2024", "Rio de Janeiro", "Copacabana Beach", "11:00", "Join us for a beach cleanup in Copacabana.", 13);
        Event event7 = new Event("08-02-2024", "Cape Town", "Table Mountain National Park", "12:30", "Cleaning up nature at Table Mountain.", 5);
        Event event8 = new Event("12-02-2024", "Hong Kong", "Victoria Park", "14:00", "Community cleanup in Victoria Park.", 8);
        Event event9 = new Event("15-02-2024", "Berlin", "Tiergarten Park", "16:30", "Help us keep Tiergarten Park clean.", 1);
        Event event10 = new Event("18-02-2024", "Mumbai", "Marine Drive", "19:00", "Night cleanup at Marine Drive.", 9);

        eventList.add(event);
        eventList.add(event1);
        eventList.add(event2);
        eventList.add(event3);
        eventList.add(event4);
        eventList.add(event5);
        eventList.add(event6);
        eventList.add(event7);
        eventList.add(event8);
        eventList.add(event9);
        eventList.add(event10);
    }

    public void orderList(){
        Collections.sort(eventList, Comparator.comparing(Event::getDate));
    }
}