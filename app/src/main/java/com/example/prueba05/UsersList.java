package com.example.prueba05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
        userAdapter = new UserAdapter(this, userList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(userAdapter);

        //TEST WITH USERS
        generateUsers();
        orderList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("User selected " + userList.get(position).getName());
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

    public void generateUsers(){
        User user1 = new User("jane_doe", "password123", "Jane Doe", "jane@gmail.com");
        user1.setPoints(600);
        user1.editProfile("I am passionate about protecting the environment and promoting sustainable living. Let's make the world a greener place!", "New York, USA");
        userList.add(user1);
        User user2 = new User("alex_green", "greenworld", "Alex Green", "alex@gmail.com");
        user2.editProfile("Nature lover and advocate for eco-friendly practices. Join me in making a positive impact on the planet!", "London, UK");
        user2.setPoints(500);
        userList.add(user2);
        User user3 = new User("nature_enthusiast", "green123", "Nature Enthusiast", "nature@gmail.com");
        user3.editProfile("Exploring the beauty of nature and dedicated to preserving it. Together, we can make a difference!", "Vancouver, Canada");
        user3.setPoints(400);
        userList.add(user3);
        User user4 = new User("eco_warrior", "savetheearth", "Eco Warrior", "eco@gmail.com");
        user4.editProfile("On a mission to save the Earth! Advocate for sustainable living and environmental conservation.", "Sydney, Australia");
        user4.setPoints(300);
        userList.add(user4);
        User user5 = new User("green_guru", "ecofriendly1", "Green Guru", "guru@gmail.com");
        user5.editProfile("Sharing eco-friendly tips and ideas for a sustainable lifestyle. Let's inspire positive change!", "Tokyo, Japan");
        user5.setPoints(200);
        userList.add(user5);
        User user6 = new User("earth_caretaker", "earthlove", "Earth Caretaker", "caretaker@gmail.com");
        user6.editProfile("Dedicated to taking care of our beautiful planet. Join me in fostering a greener and healthier world!", "Rio de Janeiro, Brazil");
        user6.setPoints(100);
        userList.add(user6);
        User user7 = new User("sustainable_soul", "soulfulgreen", "Sustainable Soul", "soul@gmail.com");
        user7.editProfile("Living a soulful and sustainable life. Advocate for eco-conscious choices and positive environmental impact.", "Paris, France");
        user7.setPoints(50);
        userList.add(user7);
        User user8 = new User("green_explorer", "exploreandprotect", "Green Explorer", "explorer@gmail.com");
        user8.editProfile("Exploring the wonders of nature and committed to protecting our environment. Together, we can make a difference!", "Cape Town, South Africa");
        user8.setPoints(40);
        userList.add(user8);
        User user9 = new User("eco_champion", "champion123", "Eco Champion", "champion@gmail.com");
        user9.editProfile("Championing eco-friendly initiatives and promoting sustainable practices. Join me on the journey to a greener future!", "Stockholm, Sweden");
        user9.setPoints(30);
        userList.add(user9);
        User user10 = new User("green_activist", "activist456", "Green Activist", "activist@gmail.com");
        user10.editProfile("Passionate environmental activist striving for positive change. Let's work together towards a sustainable and eco-friendly world!", "Berlin, Germany");
        user10.setPoints(10);
        userList.add(user10);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}