package com.example.cybercrime_helpline.Models;

public class Rules {
    private String title, description, fine, image_Name;

    public Rules(String title, String description, String fine, String image_Name) {
        this.title = title;
        this.description = description;
        this.fine = fine;
        this.image_Name = image_Name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public String getImage_Name() {
        return image_Name;
    }

    public void setImage_Name(String image_Name) {
        this.image_Name = image_Name;
    }

    @Override
    public String toString() {
        return "Rules{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", fine='" + fine + '\'' +
                ", image_Name='" + image_Name + '\'' +
                '}';
    }
}
