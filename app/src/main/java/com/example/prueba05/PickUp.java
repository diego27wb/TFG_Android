package com.example.prueba05;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.UUID;

public class PickUp implements Serializable {
    private UUID id;
    private String titulo;
    private String photo;
    private SerializableLatLng position;
    private String description;
    private SizeEnum size;

    public PickUp(String titulo, String photo, SerializableLatLng position, String description, SizeEnum size){
        this.id = UUID.randomUUID();
        this.titulo = titulo;
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
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public SerializableLatLng getPosition() {
        return position;
    }

    public void setPosition(SerializableLatLng position) {
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
