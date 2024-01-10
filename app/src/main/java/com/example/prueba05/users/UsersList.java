package com.example.prueba05.users;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.prueba05.R;
import com.example.prueba05.objects.User;
import com.example.prueba05.users.UserAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class UsersList extends AppCompatActivity {

    private ArrayList<User> userList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        userList = new ArrayList<>();
        userList = (ArrayList<User>) getIntent().getSerializableExtra("users");
        userAdapter = new UserAdapter(this, userList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(userAdapter);

        orderList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("City of the user " + userList.get(position).getCity());
            }
        });
    }

    public void orderList(){
        Collections.sort(userList, Comparator.comparingInt(User::getPoints).reversed());
        int i = 0;
        for(User user: userList){
            if(i == 0) user.setBadge("#FFD700");
            else if (i == 1) user.setBadge("#A9A9A9");
            else if (i == 2) user.setBadge("#B87333");
            else user.setBadge(randomBlue());
            i += 1;
        }
    }

    public static String randomBlue() {
        Random random = new Random();

        int minBlue = 100;
        int maxBlue = 255;

        int red = 0;
        int green = 0;
        int blue = random.nextInt(maxBlue - minBlue + 1) + minBlue;

        String hexColor = String.format("#%02X%02X%02X", red, green, blue);

        return hexColor;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}