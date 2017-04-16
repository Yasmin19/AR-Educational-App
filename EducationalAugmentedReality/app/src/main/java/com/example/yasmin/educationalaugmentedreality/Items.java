package com.example.yasmin.educationalaugmentedreality;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Yasmin on 16/04/2017.
 */
public class Items {


    ArrayList<Items> itemsList = new ArrayList<Items>();

    public Items(String w, String d, String i, LatLng loc){
        itemsList.add(new Items())
    }

    public String getWord(){

    }

    public String getDesc(){

    }
    public LatLng getLocation(){

    }

    public String imagePath(){

    }

    public Items[] getItem(){

    }

    public int getItemsNumber(){
        return itemsList.size();
    }
}
