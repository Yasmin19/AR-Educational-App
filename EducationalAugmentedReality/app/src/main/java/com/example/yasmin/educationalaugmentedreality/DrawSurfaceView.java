package com.example.yasmin.educationalaugmentedreality;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Yasmin on 15/03/2017.
 */
public class DrawSurfaceView extends View {

    Point me = new Point(-33.870932d, 151.204727d, "Me");
    Paint mPaint = new Paint();
    private Bitmap mDinosaur;

    public static ArrayList<Point> props = new ArrayList<Point>();
    static {
        props.add(new Point(90d, 110.8000, "North Pole"));
        props.add(new Point(-90d, -110.8000, "South Pole"));
        props.add(new Point(-33.870932d, 151.8000, "East"));
        props.add(new Point(-33.870932d, 150.8000, "West"));
    }

    public DrawSurfaceView(Context c, Paint paint){
        super(c);
    }

    public DrawSurfaceView(Context context, AttributeSet set){
        super(context, set);

        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(50);
        mPaint.setStrokeWidth(20);
        mPaint.setAntiAlias(true);

        mDinosaur = BitmapFactory.decodeResource(context.getResources(),R.drawable.dinosaur);

    }

    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(mDinosaur, 0, 0, mPaint);
    }
}
