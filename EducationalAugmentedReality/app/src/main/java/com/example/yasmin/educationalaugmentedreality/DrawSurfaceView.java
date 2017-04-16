package com.example.yasmin.educationalaugmentedreality;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

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


    public DrawSurfaceView(Context c, Paint paint){
        super(c);
    }

    public DrawSurfaceView(Context context, AttributeSet set){
        super(context, set);

        mDinosaur = BitmapFactory.decodeResource(context.getResources(),R.drawable.dinosaur);

    }

    protected void onDraw(Canvas canvas){

        float imageCentreX = mDinosaur.getWidth()/2;
        float imageCentreY = mDinosaur.getHeight()/2;


        int mAzimuth = OrientationSensor.mAzimuth;

        //Finding difference in azimuth and scaling value
        x = (objectAzimuth - mAzimuth) * 50;
        screenHeight = CameraActivity.getScreenHeight();
        screenWidth = CameraActivity.getScreenWidth();


        //canvas.drawBitmap(mDinosaur, 0, 0, mPaint);
        //canvas.drawBitmap(mDinosaur, imageCentreX + OrientationSensor.gData[0], imageCentreY + OrientationSensor.gData[1], mPaint);
        canvas.drawBitmap(mDinosaur, x, y, mPaint);
        Log.d("DRAW", "" + x);

        invalidate(); //re-draw
    }


    public void calculateDistance(){

    }
}
