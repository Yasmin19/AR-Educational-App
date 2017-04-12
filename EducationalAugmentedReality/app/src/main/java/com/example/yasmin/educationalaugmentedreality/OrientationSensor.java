package com.example.yasmin.educationalaugmentedreality;

import android.content.Intent;

import org.sensingkit.sensingkitlib.SKException;
import android.app.Service;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

import org.sensingkit.sensingkitlib.SKSensorDataListener;
import org.sensingkit.sensingkitlib.SKSensorModuleType;
import org.sensingkit.sensingkitlib.SensingKitLib;
import org.sensingkit.sensingkitlib.SensingKitLibInterface;
import org.sensingkit.sensingkitlib.data.SKAccelerometerData;
import org.sensingkit.sensingkitlib.data.SKMagnetometerData;
import org.sensingkit.sensingkitlib.data.SKRotationData;
import org.sensingkit.sensingkitlib.data.SKSensorData;

import java.security.Provider;

/**
 * Created by Yasmin on 15/03/2017.
 */
public class OrientationSensor extends Service {

    int mAzimuth = 0; //degrees

    float[] gData = new float[3]; //accelerometer
    float[] mData = new float[3]; //magnetometer
    float[] Rot = new float[9];
    float[] I = new float[9];
    float[] orient = new float[9];

    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            final SensingKitLibInterface mSensingKitLib = SensingKitLib.getSensingKitLib(this);

            //register a sensor module
            mSensingKitLib.registerSensorModule(SKSensorModuleType.ACCELEROMETER);
            //Start continuous sensing
            mSensingKitLib.startContinuousSensingWithSensor(SKSensorModuleType.ACCELEROMETER);
            //subscribe to sensor data listener
            mSensingKitLib.subscribeSensorDataListener(SKSensorModuleType.ACCELEROMETER, new SKSensorDataListener() {

                @Override
                public void onDataReceived(final SKSensorModuleType moduleType, final SKSensorData sensorData) {

                    Log.d("ACCELEROMETER", sensorData.getDataInCSV());
                    SKAccelerometerData accelerometerData = (SKAccelerometerData) sensorData;
                    Log.d("ACCELEROMETER", accelerometerData.getX() + "");
                    gData[0] = accelerometerData.getX();
                    gData[1] = accelerometerData.getY();
                    gData[2] = accelerometerData.getZ();

                }
            });

            //register a sensor module
            mSensingKitLib.registerSensorModule(SKSensorModuleType.MAGNETOMETER);
            //Start continuous sensing
            mSensingKitLib.startContinuousSensingWithSensor(SKSensorModuleType.MAGNETOMETER);
            //subscribe to sensor data listener
            mSensingKitLib.subscribeSensorDataListener(SKSensorModuleType.MAGNETOMETER, new SKSensorDataListener() {

                @Override
                public void onDataReceived(final SKSensorModuleType moduleType, final SKSensorData sensorData) {

                    Log.d("MAGNETOMETER", sensorData.getDataInCSV());
                    SKMagnetometerData magnetometerData = (SKMagnetometerData) sensorData;
                    Log.d("MAGNETOMETER", magnetometerData.getX() + "");
                    mData[0] = magnetometerData.getX();
                    mData[1] = magnetometerData.getY();
                    mData[2] = magnetometerData.getZ();


                    if (mData != null && gData != null) {
                        //If successful, work out azimuth
                        if (SensorManager.getRotationMatrix(Rot, I, gData, mData)) {
                            mAzimuth = (int) (Math.toDegrees(SensorManager.getOrientation(Rot, orient)[0]) + 360) % 360;
                            Log.d("Azimuth", mAzimuth + " degrees");
                        }
                    }
                }
            });

       }catch(SKException e){}


        return flags;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
