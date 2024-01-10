package com.example.prueba05.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String password;
    private String name;
    private String email;
    private String description;
    private String city;
    private String badge;
    private int points;
    private ArrayList<PickUp> pickUps;
    //TODO private ArrayList<Event> events;
    //TODO private Image badge;

    public User(String username, String password, String name, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.pickUps = new ArrayList<>();
        this.description = "";
        this.city = "";
    }

    public void addPoints(SizeEnum size){
        int res = this.getPoints();
        res += size.getValue();
        this.setPoints(res);
    }

    public void editProfile(String description, String city){
        this.setDescription(!description.isEmpty() ? description : "");
        this.setCity(!city.isEmpty() ? city : "");

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    public ArrayList<PickUp> getPickUps() {
        return pickUps;
    }

    public void setPickUps(ArrayList<PickUp> pickUps) {
        this.pickUps = pickUps;
        for(PickUp pickUp: pickUps){
            this.addPoints(pickUp.getSize());
        }
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
}
