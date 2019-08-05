package com.example.retrofitmvvmv2;

public class Hero {

    private String name;

    private String imageurl;

    public Hero(String name,String imageurl) {
        this.name = name;
        this.imageurl = imageurl;
    }

    String getName() {
        return name;
    }

    String getImageurl() {
        return imageurl;
    }

}
