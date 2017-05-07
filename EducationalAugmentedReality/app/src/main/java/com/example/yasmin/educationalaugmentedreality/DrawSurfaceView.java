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
 *
 */
public class DrawSurfaceView extends View {

    Paint mPaint = new Paint();
    private Bitmap mImage;
    private int screenWidth, screenHeight = 0;
    private float x = 0;
    private int prevAz = 0;
    public int myAzimuth = 0;
    public boolean flag = false;
    Paint paint2;

    public DrawSurfaceView(Context c, Paint paint){
        super(c);
    }

    public DrawSurfaceView(Context context, AttributeSet set){
        super(context, set);

        paint2 = new Paint();
        paint2.setColor(Color.parseColor("#8a00e6"));
        paint2.setStyle(Paint.Style.FILL);
        paint2.setTextSize(80);

        int sel = Items.getImageNo(CrossWord.selectedWord);
        if (sel == 0) {
            mImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.dinosaur);
        }
        else if (sel == 1){
            mImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.dice);
        }
        else {
            mImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ball);
        }

        //Generate random number between 0 and 359 degrees
        Random random = new Random();
        myAzimuth = random.nextInt(360);

        myAzimuth = 120;

    }

    protected void onDraw(Canvas canvas){

        //Retrieve azimuth readings from orientation sensor Service
        int mAzimuth = OrientationSensor.mAzimuth;

        //Finding difference in azimuth and scaling value
        //Get rid of any extreme/outlier values and round
        if (Math.abs(mAzimuth-prevAz) >= 5) {
            x = (myAzimuth - (Math.round(mAzimuth/5)*5)) * 40;
        }
        else{
            x = (myAzimuth - prevAz) * 40;
        }

        canvas.drawBitmap(mImage, x, 300, mPaint);
        canvas.drawText(Items.getWordDesc(CrossWord.selectedWord), x + 300, 1000, paint2);

        prevAz = mAzimuth;

        invalidate(); //re-draw
    }

}
