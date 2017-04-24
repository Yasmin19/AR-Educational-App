package com.example.yasmin.educationalaugmentedreality;

import android.content.Intent;
import android.content.res.Resources;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class CameraActivity extends AppCompatActivity {

    public Button start;
    private Camera mCamera;
    private CameraPreview mPreview;
    private DrawSurfaceView mDrawView;
    GridView word;
    GridView availableLetters;
    public static char[] letters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        //Create instance of Camera
        mCamera = getCameraInstance();
        //Create our Preview view and set it as the content of our activity
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);


        letters = new char[12];

        for(int i=0; i<CrossWord.selectedWord.length(); i++){
            letters[i] = CrossWord.selectedWord.charAt(i);
        }

        for (int i=0; i<letters.length; i++){
            if (letters[i] == ' '){
                Random r = new Random();
                char c = (char) (r.nextInt(26) + 'a');
                letters[i] = c;
            }
        }
        Collections.shuffle(Arrays.asList(letters));

        availableLetters = (GridView) findViewById(R.id.availableletters);
        availableLetters.setAdapter(new AvailableLetters(this));

        startService(new Intent(getApplicationContext(), OrientationSensor.class));
    }

    /** A safe way to get an instance of the Camera object. */

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    public static int getScreenWidth(){
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(){
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onStop(){
        super.onStop();
    }
}
