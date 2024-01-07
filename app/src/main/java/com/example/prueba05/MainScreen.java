package com.example.prueba05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends AppCompatActivity {

    private ArrayList<PickUp> pickUpList;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Manage the pickups
        pickUpList = new ArrayList<>();

        //TEST PICKUPS
        SerializableLatLng location = new SerializableLatLng(48.856666666667, 2.3522222222222);
        SerializableLatLng location2 = new SerializableLatLng(39.56939, 2.65024);
        SerializableLatLng location3 = new SerializableLatLng(41.149472222222, -8.6107777777778);

        pickUpList.add(new PickUp("Basura", "image.png", location, "Buena descripion", SizeEnum.L));
        pickUpList.add(new PickUp("Plastico", "image.png", location2, "Buena descripion", SizeEnum.XL));
        pickUpList.add(new PickUp("Bolsas", "image.png", location3, "Buena descripion", SizeEnum.XXL));

        //TEST USER
        currentUser = new User("diego27wb", "pass", "Diego Wiederkehr Bruno", "diego@gmail.com");
        currentUser.setPickUps(pickUpList);
        currentUser.editProfile("I am a really ecological and nature-friendly person. I love this app!", "Tenerife, Spain");

        CardView mapCard = findViewById(R.id.mapCard);
        CardView addPickupCard = findViewById(R.id.addPickupCard);
        CardView eventsCard = findViewById(R.id.eventsCard);
        CardView rankingCard = findViewById(R.id.rankingCard);
        CardView profileCard = findViewById(R.id.profileCard);
        CardView helpCard = findViewById(R.id.helpCard);

        mapCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, MainActivity.class);
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
                Intent intent = new Intent(MainScreen.this, AgregarPickUpActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        eventsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your action for the "Events" CardView here
            }
        });

        rankingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, UsersList.class);
                startActivity(intent);
            }
        });

        profileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, UserProfile.class);
                intent.putExtra("user", currentUser);
                startActivity(intent);
            }
        });

        helpCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your action for the "Help" CardView here
            }
        });
    }

    // Function to handle when a new pickup is creates
    //TODO With a database it is not needed to do this
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                // Obtiene el nuevo PickUp creado y lo agrega a la lista
                PickUp nuevoPickUp = (PickUp) data.getSerializableExtra("nuevoPickUp");
                pickUpList.add(nuevoPickUp);
                currentUser.getPickUps().add(nuevoPickUp);
            }
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
