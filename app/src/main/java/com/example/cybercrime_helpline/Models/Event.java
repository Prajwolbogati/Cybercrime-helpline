package com.example.cybercrime_helpline.Models;

import androidx.annotation.NonNull;

public class Event {
    String id, fullname, event_type, image_Name, description, area;

    public Event(String fullname, String event_type, String image_Name, String description, String area) {
        this.fullname = fullname;
        this.event_type = event_type;
        this.image_Name = image_Name;
        this.description = description;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getImage_Name() {
        return image_Name;
    }

    public void setImage_Name(String image_Name) {
        this.image_Name = image_Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @NonNull
    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", fullname='" + fullname + '\'' +
                ", event_type='" + event_type + '\'' +
                ", image_Name='" + image_Name + '\'' +
                ", description='" + description + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
    }

