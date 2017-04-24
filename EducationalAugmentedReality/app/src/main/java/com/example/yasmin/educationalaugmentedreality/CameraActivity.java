package com.example.yasmin.educationalaugmentedreality;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

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
    GridView selectedLetters;
    public static char[] letters;
    static String selectedChar = " ";
    int count = 0;
    boolean correct = true;
    TextView textView2;

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

        startService(new Intent(getApplicationContext(), OrientationSensor.class));

        letters = new char[12];

        for(int i=0; i<CrossWord.selectedWord.length(); i++){
            letters[i] = CrossWord.selectedWord.charAt(i);
        }

        for (int i=CrossWord.selectedWord.length(); i<letters.length; i++){
            Random r = new Random();
            char c = (char) (r.nextInt(26) + 'A');
            letters[i] = c;
        }

        Collections.shuffle(Arrays.asList(letters));

        availableLetters = (GridView) findViewById(R.id.availableletters);
        availableLetters.setAdapter(new AvailableLetters(this));

        textView2 = (TextView) findViewById(R.id.selectedletters);
        textView2.setTextSize(43);
        textView2.setTextColor(Color.parseColor("#ffff9000"));
        textView2.setTypeface(CrossWordActivity.font);


        availableLetters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                TextView textView = (TextView) availableLetters.getChildAt(position);
                selectedChar = textView.getText().toString();
                textView.setText(" ");

                if (!selectedChar.equals(" ")) {
                    String curr = textView2.getText().toString();
                    textView2.setText(curr + selectedChar);
                    count++;
                }

                if (count == CrossWord.selectedWord.length()){
                    for (int i=0; i<CrossWord.selectedWord.length(); i++){
                        if (CrossWord.selectedWord.charAt(i) != textView2.getText().toString().charAt(i)){
                            correct = false;
                            Vibrator v = (Vibrator) CameraActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                            v.vibrate(500);

                            textView2.setText(" ");

                        }
                    }
                }
            }
        });
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
