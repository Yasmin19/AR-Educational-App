package com.example.yasmin.educationalaugmentedreality;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * This class is of object Items and consists of the fields: word, desc, image, latlng coordinates
 * and a list of items
 */
public class Items {

    String word;
    String desc;
    int image;
    static LatLng location;
    static ArrayList<Items> itemsList = new ArrayList<Items>();
    static ArrayList<String> words = new ArrayList<String>();

    public Items(String w, String d, int i, LatLng loc){
        word = w;
        desc = d;
        image = i;
        location = loc;
    }

    public String getWord(){
        return word;
    }

    public String getDesc(){
        return desc;
    }

    public int getImage() { return image; }

    public LatLng getLocation(){
        return location;
    }

    public static LatLng getWordLocation(String w){

        for (Items i: itemsList){
            if (i.getWord().equals(w)){
                return i.getLocation();
            }
        }
        return location;
    }

    public static String getWordDesc(String w){
        for (Items i: itemsList){
            if (i.getWord().equals(w)){
                return i.getDesc();
            }
        }
        return "";

    }

    public static int getImageNo(String w){
        for (Items i: itemsList){
            if (i.getWord().equals(w)){
                return i.getImage();
            }
        }

        return 0;
    }

    public static ArrayList<Items> getItemList(){
        return itemsList;
    }

    public static ArrayList<String> getWordList(){
        for (int i=0; i<itemsList.size(); i++){
            words.add(itemsList.get(i).getWord());
            Log.d("CROSS", ""+itemsList.get(i).getWord());
        }
        return words;
    }

    public static int getItemsNumber(){
        return itemsList.size();
    }
}
