package com.example.movieuitemplate.models;

public class Cast {

    String name;
    int ImageLink;


    public Cast(String name, int imageLink) {
        this.name = name;
        ImageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageLink() {
        return ImageLink;
    }

    public void setImageLink(int imageLink) {
        ImageLink = imageLink;
    }
}
