package com.example.prueba05;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context, List<User> users){
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_user_adapter, parent, false);
        }

        User user = getItem(position);

        /*
         Manejar todos los datos de dentro
         */
        TextView name = convertView.findViewById(R.id.usernameTextView);
        TextView points = convertView.findViewById(R.id.pointsTextView);
        TextView description = convertView.findViewById(R.id.descriptionTextView);

        name.setText(user.getName());
        points.setText(user.getPoints() + " Points");
        points.setTextColor(Color.parseColor(user.getBadge()));
        description.setText(user.getDescription());

        return convertView;
    }
}