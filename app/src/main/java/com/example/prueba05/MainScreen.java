package com.example.prueba05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.prueba05.events.EventList;
import com.example.prueba05.objects.Event;
import com.example.prueba05.objects.PickUp;
import com.example.prueba05.objects.SerializableLatLng;
import com.example.prueba05.objects.SizeEnum;
import com.example.prueba05.objects.User;
import com.example.prueba05.pickups.AddPickUp;
import com.example.prueba05.pickups.MapPickUps;
import com.example.prueba05.users.UserProfile;
import com.example.prueba05.users.UsersList;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private ArrayList<PickUp> pickUpList;
    private ArrayList<User> users;
    private ArrayList<Event> events;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Manage the pickups, users and events
        pickUpList = new ArrayList<>();
        users = new ArrayList<>();
        events = new ArrayList<>();

        // Initialize data
        dataInit();

        CardView mapCard = findViewById(R.id.mapCard);
        CardView addPickupCard = findViewById(R.id.addPickupCard);
        CardView eventsCard = findViewById(R.id.eventsCard);
        CardView rankingCard = findViewById(R.id.rankingCard);
        CardView profileCard = findViewById(R.id.profileCard);
        CardView helpCard = findViewById(R.id.helpCard);

        mapCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, MapPickUps.class);
                ArrayList<LatLng> pickUpLocations = new ArrayList<>();
                ArrayList<String> pickUpTitles = new ArrayList<>();
                ArrayList<String> pickUpComments = new ArrayList<>();
                ArrayList<SizeEnum> pickUpSize = new ArrayList<>();

                for (PickUp pickUp : pickUpList){
                    pickUpLocations.add(new LatLng(pickUp.getPosition().getLatitude(), pickUp.getPosition().getLongitude()));
                    pickUpTitles.add(pickUp.getTitulo());
                    pickUpComments.add(pickUp.getDescription());
                    pickUpSize.add(pickUp.getSize());
                }
                intent.putParcelableArrayListExtra("pickUps", pickUpLocations);
                intent.putStringArrayListExtra("pickUpsTitles", pickUpTitles);
                intent.putStringArrayListExtra("pickUpsComments", pickUpComments);
                intent.putExtra("pickUpsSizes", pickUpSize);
                startActivity(intent);
            }
        });

        addPickupCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, AddPickUp.class);
                startActivityForResult(intent, 1);
            }
        });

        eventsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, EventList.class);
                intent.putExtra("events", events);
                startActivityForResult(intent, 2);
            }
        });

        rankingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, UsersList.class);
                intent.putExtra("users", users);
                startActivity(intent);
            }
        });

        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, UserProfile.class);
                intent.putExtra("user", currentUser);
                startActivityForResult(intent, 3);
            }
        });

        helpCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, Information.class);
                startActivity(intent);
            }
        });
    }

    // Function to handle when the info is updated
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                // Obtain the new pickUp and add it to the list
                PickUp nuevoPickUp = (PickUp) data.getSerializableExtra("nuevoPickUp");
                pickUpList.add(nuevoPickUp);
                currentUser.getPickUps().add(nuevoPickUp);
                currentUser.addPoints(nuevoPickUp.getSize());
            }
        } else if (requestCode == 2 && resultCode == RESULT_OK){
            if (data != null) {
                // Obtain the event list updated with the new ones and the people assisting
                ArrayList<Event> eventsUpdated = (ArrayList<Event>) data.getSerializableExtra("events");
                events = eventsUpdated;
            }
        } else if (requestCode == 3 && resultCode == RESULT_OK){
            if (data != null){
                //Obtain the updated info of the current user
                User updatedUser = (User) data.getSerializableExtra("user");
                currentUser = updatedUser;
                for (int i = 0; i < users.size(); i++){
                    if(users.get(i).getEmail().equals(updatedUser.getEmail())){
                        users.set(i, updatedUser);
                        break;
                    }
                }
            }
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void dataInit(){
        //PickUps
        SerializableLatLng location = new SerializableLatLng(48.856666666667, 2.3522222222222);
        SerializableLatLng location2 = new SerializableLatLng(39.56939, 2.65024);
        SerializableLatLng location3 = new SerializableLatLng(41.149472222222, -8.6107777777778);

        pickUpList.add(new PickUp("Garbage", "image.png", location, "A lot of garbage found here!", SizeEnum.L));
        pickUpList.add(new PickUp("Plastic", "image.png", location2, "It is really sad how people waste so much plastic...", SizeEnum.XL));
        pickUpList.add(new PickUp("Bags", "image.png", location3, "Is it so necessary to use this much plastic bags?", SizeEnum.XXL));

        //Current User
        currentUser = new User("diego27wb", "pass", "Diego Wiederkehr Bruno", "diego@gmail.com");
        currentUser.setPickUps(pickUpList);
        currentUser.editProfile("I am a really ecological and nature-friendly person. I love this app!", "Tenerife, Spain");

        //Events
        Event event = new Event("02-01-2024", "Madrid", "El Retiro", "15:00", "Let's go to pick the trash from the Retiro!", 1);
        Event event1 = new Event("05-01-2024", "New York", "Central Park", "15:00", "Let's clean up Central Park!", 4);
        Event event2 = new Event("07-01-2024", "London", "Hyde Park", "14:30", "Hyde Park Cleanup Day!", 6);
        Event event3 = new Event("11-01-2024", "Tokyo", "Ueno Park", "16:00", "Join us in cleaning up Ueno Park.", 2);
        Event event4 = new Event("28-01-2024", "Sydney", "Bondi Beach", "17:30", "Community beach cleanup event.", 10);
        Event event5 = new Event("02-02-2024", "Paris", "Bois de Vincennes", "18:00", "Let's make Bois de Vincennes clean again!", 12);
        Event event6 = new Event("05-02-2024", "Rio de Janeiro", "Copacabana Beach", "11:00", "Join us for a beach cleanup in Copacabana.", 13);
        Event event7 = new Event("08-02-2024", "Cape Town", "Table Mountain National Park", "12:30", "Cleaning up nature at Table Mountain.", 5);
        Event event8 = new Event("12-02-2024", "Hong Kong", "Victoria Park", "14:00", "Community cleanup in Victoria Park.", 8);
        Event event9 = new Event("15-02-2024", "Berlin", "Tiergarten Park", "16:30", "Help us keep Tiergarten Park clean.", 1);
        Event event10 = new Event("18-02-2024", "Mumbai", "Marine Drive", "19:00", "Night cleanup at Marine Drive.", 9);

        events.add(event);
        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);
        events.add(event6);
        events.add(event7);
        events.add(event8);
        events.add(event9);
        events.add(event10);

        //Users
        User user1 = new User("jane_doe", "password123", "Jane Doe", "jane@gmail.com");
        user1.setPoints(100);
        user1.editProfile("I am passionate about protecting the environment and promoting sustainable living. Let's make the world a greener place!", "New York, USA");
        users.add(user1);
        User user2 = new User("alex_green", "greenworld", "Alex Green", "alex@gmail.com");
        user2.editProfile("Nature lover and advocate for eco-friendly practices. Join me in making a positive impact on the planet!", "London, UK");
        user2.setPoints(80);
        users.add(user2);
        User user3 = new User("nature_enthusiast", "green123", "Nature Enthusiast", "nature@gmail.com");
        user3.editProfile("Exploring the beauty of nature and dedicated to preserving it. Together, we can make a difference!", "Vancouver, Canada");
        user3.setPoints(70);
        users.add(user3);
        User user4 = new User("eco_warrior", "savetheearth", "Eco Warrior", "eco@gmail.com");
        user4.editProfile("On a mission to save the Earth! Advocate for sustainable living and environmental conservation.", "Sydney, Australia");
        user4.setPoints(60);
        users.add(user4);
        User user5 = new User("green_guru", "ecofriendly1", "Green Guru", "guru@gmail.com");
        user5.editProfile("Sharing eco-friendly tips and ideas for a sustainable lifestyle. Let's inspire positive change!", "Tokyo, Japan");
        user5.setPoints(50);
        users.add(user5);
        User user6 = new User("earth_caretaker", "earthlove", "Earth Caretaker", "caretaker@gmail.com");
        user6.editProfile("Dedicated to taking care of our beautiful planet. Join me in fostering a greener and healthier world!", "Rio de Janeiro, Brazil");
        user6.setPoints(40);
        users.add(user6);
        User user7 = new User("sustainable_soul", "soulfulgreen", "Sustainable Soul", "soul@gmail.com");
        user7.editProfile("Living a soulful and sustainable life. Advocate for eco-conscious choices and positive environmental impact.", "Paris, France");
        user7.setPoints(30);
        users.add(user7);
        User user8 = new User("green_explorer", "exploreandprotect", "Green Explorer", "explorer@gmail.com");
        user8.editProfile("Exploring the wonders of nature and committed to protecting our environment. Together, we can make a difference!", "Cape Town, South Africa");
        user8.setPoints(20);
        users.add(user8);
        User user9 = new User("eco_champion", "champion123", "Eco Champion", "champion@gmail.com");
        user9.editProfile("Championing eco-friendly initiatives and promoting sustainable practices. Join me on the journey to a greener future!", "Stockholm, Sweden");
        user9.setPoints(10);
        users.add(user9);
        User user10 = new User("green_activist", "activist456", "Green Activist", "activist@gmail.com");
        user10.editProfile("Passionate environmental activist striving for positive change. Let's work together towards a sustainable and eco-friendly world!", "Berlin, Germany");
        user10.setPoints(5);
        users.add(user10);
        users.add(currentUser);
    }
}
