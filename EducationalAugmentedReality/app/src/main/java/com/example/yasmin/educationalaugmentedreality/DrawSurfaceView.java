package com.example.yasmin.educationalaugmentedreality;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Yasmin on 15/03/2017.
 */
public class DrawSurfaceView extends View {

    Paint mPaint = new Paint();
    private Bitmap mDinosaur;
    private int screenWidth, screenHeight = 0;
    private float x, y = 0;
    private int prevAz = 0;
    int xVel, yVel = 0;
    float xDistance, yDistance, xPos, yPos = 0f;
    float[] history = new float[3];
    public int myAzimuth = 0;
    public boolean flag = false;
    Random r;
    char c;
    Paint paint2;

    public DrawSurfaceView(Context c, Paint paint){
        super(c);
    }

    public DrawSurfaceView(Context context, AttributeSet set){
        super(context, set);

        paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setTextSize(80);

        mDinosaur = BitmapFactory.decodeResource(context.getResources(),R.drawable.dinosaur);

        history[0] = 0;
        history[1] = 0;
        history[2] = 0;

        //Generate random number between 0 and 359 degrees
        Random random = new Random();
        myAzimuth = random.nextInt(360);

    }

    protected void onDraw(Canvas canvas){

        float frametime = 0.666f;

        //Retrieve azimuth readings from orientation sensor Service
        int mAzimuth = OrientationSensor.mAzimuth;
/*
        if (Math.abs(mAzimuth - prevAz) >= 5){
            mAzimuth = prevAz;
        }
*/
        /*****************************
         * USE AZIMUTH WITH A COMBINATION OF SPEED AND DISTANCE ECT TO GET A SMOOTHER TRANSITION!!!!!!!!!!!!!
         ****************************/

        //Calcuate new speed of rotation using suvat
        //Approximate frame speed is 0.666f
        Log.d("DISTANCE", "Start Azimuth: " + myAzimuth + ", Current Azimuth: " + mAzimuth);
        Log.d("DISTANCE", ""+ (Math.round((myAzimuth - mAzimuth)/10)*10));
        //xVel += (int)((Math.round((myAzimuth - mAzimuth)/10)*10) / (frametime*80));
        xVel += (Math.round((myAzimuth - mAzimuth)/10)*10)/ (frametime);
        yVel += (int)(OrientationSensor.linear_acceleration[1] * frametime);

        //Calculate distance that it has travelled
        xPos = (float) 0.05 * xVel * frametime;
        yDistance = (float) (0.5 * yVel) * frametime;

        //Inverse the distance readings so that object will move in the right direction
        //xPos -= xDistance;
        yPos -= yDistance;


        if (history[0] - OrientationSensor.linear_acceleration[0] > 2){
            Log.d("MOVEMENT", "LEFT AT [0] ---- 'x'");
        }

        if (history[1] - OrientationSensor.linear_acceleration[1] > 2){
            Log.d("MOVEMENT", "LEFT AT [1] ----- 'y'");
        }

        if (history[2] - OrientationSensor.linear_acceleration[2] > 2){
            Log.d("MOVEMENT", "LEFT AT [2]  ---- 'z'");
        }

        screenHeight = CameraActivity.getScreenHeight();
        screenWidth = CameraActivity.getScreenWidth();


        //Finding difference in azimuth and scaling value
        //Get rid of any extreme/outlier values and round
        if (Math.abs(mAzimuth-prevAz) <= 5) {
            x = (myAzimuth - (Math.round(mAzimuth/5)*5)) * 40;
        }
        else{
            x = (myAzimuth - prevAz) * 40;
        }

        if (history[1] - OrientationSensor.linear_acceleration[1] > 2){
            //y = (history[1] - OrientationSensor.linear_acceleration[1]);
        }

        if (history[1] - OrientationSensor.linear_acceleration[1] > 1 || history[1] - OrientationSensor.linear_acceleration[1] < -1){

        }

        canvas.drawBitmap(mDinosaur, x, 0, mPaint);
        canvas.drawText(Items.getWordDesc(CrossWord.selectedWord), x + 400, 800, paint2);
        Log.d("DRAW", "" + x);


        prevAz = mAzimuth;

        history[0] = OrientationSensor.linear_acceleration[0];
        history[1] = OrientationSensor.linear_acceleration[1];
        history[2] = OrientationSensor.linear_acceleration[2];

        /*
        //Resize according to distance
        Bitmap b = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
        profileImage.setImageBitmap(Bitmap.createScaledBitmap(b, 120, 120, false));
        */
        invalidate(); //re-draw
    }

}
