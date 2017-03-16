package com.example.yasmin.educationalaugmentedreality;

/**
 * Created by Yasmin on 16/03/2017.
 */
public class Point {
    public double longitude = 0f;
    public double latitude = 0f;
    public String description;
    public float x, y = 0;

    //Change this to getters/setters later
    public Point(double lat, double lon, String desc){
        this.latitude = lat;
        this.longitude = lon;
        this.description = desc;
    }
}
