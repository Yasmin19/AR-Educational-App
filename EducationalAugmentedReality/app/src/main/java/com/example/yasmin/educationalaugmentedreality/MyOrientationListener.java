package com.example.yasmin.educationalaugmentedreality;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;


/**
 * Created by Yasmin on 16/03/2016.
 */
public class MyOrientationListener extends Service implements SensorEventListener{

    private SensorManager mSensorManager;
    private Sensor accelerometer;
    private Sensor magnetometer;
    public static double azimuthAve=-1;
    static float[] linear_acceleration = new float[3]; //acceleration minus gravitational force
    float[] gravity = new float[3];

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);


        return START_STICKY;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
        // You must implement this callback in your code.
    }

    public static double azimuth1=-1;
    double azimuth3=-1;
    double azimuth2=-1;
    float azimuth;
    float mGravity[];
    float mGeomagnetic[];
    float orientation[] = new float[3];
    float Rot[] = new float[9];
    float I[] = new float[9];
    boolean success;

    float pitch_angle;
    float roll_angle;


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            // Applying filter to isolate the force of gravity
            // alpha calculated as t/t(t+dT)

            final float alpha = 0.8f;

            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

            linear_acceleration[0] = event.values[0] - gravity[0];
            linear_acceleration[1] = event.values[1] - gravity[1];
            linear_acceleration[2] = event.values[2] - gravity[2];



        }
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mGeomagnetic = event.values;
        }
        if (mGravity != null && mGeomagnetic != null) {
            success = SensorManager.getRotationMatrix(Rot, I, mGravity, mGeomagnetic);
            if (success) {
                SensorManager.getOrientation(Rot, orientation);
                azimuth = orientation[0];
                azimuth3=azimuth2;
                azimuth2=azimuth1;
                azimuth1 = Math.toDegrees(azimuth);
                azimuthAve = azimuth1;

                if (azimuth1 < 0) {
                    azimuth1+=360;
                    azimuthAve+=360;
                }

                //azimuthAve = (azimuth1 + azimuth2 + azimuth3) / 3;

            }

        }
    }

    public static double getAzimuthAve()
    {
        return azimuthAve;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
