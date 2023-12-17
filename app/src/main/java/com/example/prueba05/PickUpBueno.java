package com.example.prueba05;

import com.google.android.gms.maps.model.LatLng;


import java.util.UUID;

public class PickUpBueno {
    private UUID id;
    private String photo;
    private LatLng position;
    private String description;
    private SizeEnum size;
    //private User user;

    /*
        public Pickup(String photo, LatLng position, String description, SizeEnum size, User user){
        this.id = UUID.randomUUID();
        this.photo = photo;
        this.position = position;
        this.description = description;
        this.size = size;
        this.user = user;
        this.user.getPickups().add(this);
        this.user.addPoints(size);
    }
     */
    public PickUpBueno(){

    }
    public PickUpBueno(String photo, LatLng position, String description, SizeEnum size){
        this.id = UUID.randomUUID();
        this.photo = photo;
        this.position = position;
        this.description = description;
        this.size = size;
    }

    public void showPickup(){
        System.out.println("\tLocation: "+getPosition()+"\n\tPhoto: "+this.getPhoto()+"\n\tDescription: "+this.getDescription()
                +"\n\tSize: "+this.getSize()+"\n\t");
    }

    public UUID getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SizeEnum getSize() {
        return size;
    }

    public void setSize(SizeEnum size) {
        this.size = size;
    }

}


