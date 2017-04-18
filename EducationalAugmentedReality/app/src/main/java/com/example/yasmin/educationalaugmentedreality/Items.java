package com.example.yasmin.educationalaugmentedreality;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Yasmin on 16/04/2017.
 */
public class Items {

    String word;
    String desc;
    int image;
    LatLng location;
    static ArrayList<Items> itemsList = new ArrayList<Items>();

/*
    public Items(String w, String d, int i, LatLng loc){
        word = w;
        desc = d;
        image = i;
        location = loc;
        itemsList.add(new Items(word, desc, image, location));
    }
*/

    public Items(String w){
        word = w;
        //itemsList.add(new Items(word));
    }

    public String getWord(){
        return word;
    }

    public String getDesc(){
        return desc;
    }
    public LatLng getLocation(){
        return location;
    }

    public int imagePath(){
        return image;
    }

    public ArrayList<Items> getItem(){
        return itemsList;
    }

    public static int getItemsNumber(){
        return itemsList.size();
    }
}
