package com.example.prueba05.events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.prueba05.R;
import com.example.prueba05.objects.Event;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(Context context, List<Event> events){
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_event_adapter, parent, false);
        }

        Event event = getItem(position);

        TextView city = convertView.findViewById(R.id.cityTextView);
        TextView date = convertView.findViewById(R.id.dateTextView);
        TextView people = convertView.findViewById(R.id.peopleTextView);

        city.setText(event.getCity());
        date.setText(event.getDate());
        people.setText(String.valueOf(event.getPeople()) + " People Assisting");

        return convertView;
    }
}