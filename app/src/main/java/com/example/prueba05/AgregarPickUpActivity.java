package com.example.prueba05;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class AgregarPickUpActivity extends AppCompatActivity implements OnMapReadyCallback {
    private FusedLocationProviderClient fusedLocationClient;
    private boolean locationPermissionGranted;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private Location lastKnownLocation;
    private SerializableLatLng pickUpLocation;
    private SizeEnum size;
    private ImageView imageView;
    private GoogleMap myMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pickup);

        final EditText titulo = findViewById(R.id.titulo);
        final EditText description = findViewById(R.id.comments);
        final Spinner spinner = findViewById(R.id.spinner);
        CardView photoButton = findViewById(R.id.btnCamera);
        imageView = findViewById(R.id.imageView);

        /**
         * Localizacion
         */
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLocationPermission();
        getDeviceLocation();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(AgregarPickUpActivity.this);
        /**
         * Foto
         */
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        /**
         * Spinner
         */
        String[] inlineOptions = {"XS", "S", "M", "L", "XL", "XXL"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                inlineOptions
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String option = parentView.getItemAtPosition(position).toString();
                size = SizeEnum.valueOf(option);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Use a default size
                size = SizeEnum.valueOf("XS");
            }
        });

        /**
         * Guardar
         */
        Button guardarButton = findViewById(R.id.guardarButton);
        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO añadir la imagen aqui también
                if (TextUtils.isEmpty(titulo.getText()) || size == null){
                    incompleteFieldsError();
                }
                else {
                    // Obtiene los valores ingresados por el usuario
                    String titulo_pickup = titulo.getText().toString();
                    String comments = description.getText().toString();

                    // Crea un nuevo PickUp con los valores ingresados
                    PickUp pickUp = new PickUp(titulo_pickup, "photo.png", pickUpLocation, comments, size);

                    // Retorna el nuevo PickUp a la actividad principal
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("nuevoPickUp", pickUp);
                    setResult(RESULT_OK, resultIntent);

                    // Cierra la actividad de agregar PickUp
                    finish();
                }
            }
        });
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }


    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        myMap = googleMap;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        if (requestCode
                == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationPermissionGranted = true;
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            lastKnownLocation = task.getResult();
                            if (lastKnownLocation != null) {
                                LatLng location = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                                SerializableLatLng pickUpLocation1 = new SerializableLatLng(location.latitude, location.longitude);
                                //pickUpLocation.setLongitude(location.longitude);
                                setPickUpLocation(pickUpLocation1);
                                //localizationView.setText("Localization: " + pickUpLocation.toString());
                            }
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void setPickUpLocation(SerializableLatLng location){
        this.pickUpLocation = location;
        LatLng location1 = new LatLng(pickUpLocation.getLatitude(), pickUpLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions()
                .position(location1)
                .title("Your location");

        Marker marker = myMap.addMarker(markerOptions);
        marker.setTag(0);
        myMap.moveCamera(CameraUpdateFactory.newLatLng(location1));
    }
}
