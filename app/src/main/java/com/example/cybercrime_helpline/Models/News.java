package com.example.cybercrime_helpline.Models;

public class News {
    String id;
    String title;
    String image_Name;
    String description;

    public News(String title, String image_Name, String description) {
        this.title = title;
        this.image_Name = image_Name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", image_Name='" + image_Name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
