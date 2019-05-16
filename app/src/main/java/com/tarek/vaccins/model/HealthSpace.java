package com.tarek.vaccins.model;

public class HealthSpace  {

    private String title ;
    private int image ;

    public HealthSpace(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
