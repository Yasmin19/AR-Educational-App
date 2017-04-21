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
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Yasmin on 15/03/2017.
 */
public class DrawSurfaceView extends View {

    Paint mPaint = new Paint();
    private Bitmap mDinosaur;
    private int screenWidth, screenHeight = 0;
    private float x, y = 0;
    private int objectAzimuth = 140;
    private int prevAz = 0;
    int xVel, yVel = 0;
    float xDistance, yDistance, xPos, yPos = 0f;
    float[] history = new float[3];
    public int myAzimuth = 0;
    public boolean flag = false;


    public DrawSurfaceView(Context c, Paint paint){
        super(c);
    }

    public DrawSurfaceView(Context context, AttributeSet set){
        super(context, set);

        mDinosaur = BitmapFactory.decodeResource(context.getResources(),R.drawable.dinosaur);

        history[0] = 0;
        history[1] = 0;
        history[2] = 0;


        /***CHANGE SIZE OF IMAGE!!!!!!*******

        Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        final int dstWidth = 50;
        final int dstHeight = 50;
        mBitmap = Bitmap.createScaledBitmap(ball, dstWidth, dstHeight, true);
        mWood = BitmapFactory.decodeResource(getResources(), R.drawable.wood);
        ************************************/

    }

    protected void onDraw(Canvas canvas){

        float frametime = 0.666f;

        //Get initial Azimuth bearing, get a reading until reading is more than 0 degrees
        if (!flag){
            myAzimuth = OrientationSensor.mAzimuth;
            if (myAzimuth > 0) {
                flag = true;
            }
        }
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


/*
        //Calcuate new speed of rotation using suvat
        //Approximate frame speed is 0.666f
        xVel +=(int)(OrientationSensor.linear_acceleration[1] * 0.666f);
        yVel += (int)(OrientationSensor.linear_acceleration[1] * 0.666f);

        //Calucate distance that it has travelled
        xDistance = (float) 0.5 * (xVel + OrientationSensor.linear_acceleration[1]) * 0.666f;
        yDistance = (float) 0.5 * (yVel + OrientationSensor.linear_acceleration[1]) * 0.666f;

        //Inverse the distance readings so that object will move in the right direction
        xPos -= xDistance;
        yPos -= yDistance;
        ***************/

        //Finding difference in azimuth and scaling value
        //Get rid of any extreme/outlier values and round
        if (Math.abs(mAzimuth-prevAz) <= 5) {
            x = (myAzimuth - (Math.round(mAzimuth/5)*5))* 50;
        }
        else{
            x = ((myAzimuth - prevAz) * 5) * 50;
        }

        /*
        //Finding difference in azimuth and scaling value
        //Get rid of any extreme/outlier values and round
        if (Math.abs(mAzimuth-prevAz) <= 5) {
            x = (objectAzimuth - (Math.round(mAzimuth/5)*5)) * 50;
        }
        else{
            x = (objectAzimuth - prevAz) * 50;
        }
*/
        screenHeight = CameraActivity.getScreenHeight();
        screenWidth = CameraActivity.getScreenWidth();

        canvas.drawBitmap(mDinosaur, x, 0, mPaint);
        Log.d("DRAW", "" + x);


        prevAz = mAzimuth;

        history[0] = OrientationSensor.linear_acceleration[0];
        history[1] = OrientationSensor.linear_acceleration[1];
        history[2] = OrientationSensor.linear_acceleration[2];


        invalidate(); //re-draw
    }


    public void calculateDistance(){

    }
}
