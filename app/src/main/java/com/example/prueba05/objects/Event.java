package com.example.prueba05.objects;

import java.io.Serializable;

public class Event implements Serializable {
    private String date;
    private String city;
    private String exact_place;
    private String hour;
    private String comments;
    private String isPassed;
    private int people;
    public Event(String date, String city, String exact_place, String hour, String comments, int people){
        this.date = date;
        this.city = city;
        this.exact_place = exact_place;
        this.hour = hour;
        this.comments = comments;
        this.people = people;
        this.isPassed = "#000000";
    }

    public void addPeople(){
        this.people += 1;
    }
    public void removePeople(){
        if (this.people > 0){
            this.people -= 1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Event otherEvent = (Event) obj;
        return  this.date.equals(otherEvent.date) &&
                this.city.equals(otherEvent.city) &&
                this.exact_place.equals(otherEvent.exact_place) &&
                this.hour.equals(otherEvent.hour) &&
                this.comments.equals(otherEvent.comments);
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsPassed() {
        return isPassed;
    }

    public void setIsPassed(String isPassed) {
        this.isPassed = isPassed;
    }

    public String getExact_place() {
        return exact_place;
    }

    public void setExact_place(String exact_place) {
        this.exact_place = exact_place;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }
}
