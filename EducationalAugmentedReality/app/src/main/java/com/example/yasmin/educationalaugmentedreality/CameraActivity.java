package com.example.yasmin.educationalaugmentedreality;

import android.content.Intent;
import android.content.res.Resources;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class CameraActivity extends AppCompatActivity {

    public Button start;
    public EditText batteryText;
    private Camera mCamera;
    private CameraPreview mPreview;
    private DrawSurfaceView mDrawView;

    ArrayList<Geofence> mGeofenceList; //List of geofences used
    ArrayList<String> mGeofenceNames; //List of geofence names
    ArrayList<LatLng> mGeofenceCoordinates; //List of geofence coordinates
    public GeofenceStore mGeofenceStore;

    private static final LatLng ITL = new LatLng(51.522838, -0.043184);

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

        mDrawView = (DrawSurfaceView) findViewById(R.id.drawSurfaceView);

        populateGeofences();

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
    public void populateGeofences() {

        //Empty list for storing geofences
        mGeofenceNames = new ArrayList<String>();
        mGeofenceCoordinates = new ArrayList<LatLng>();
        mGeofenceList = new ArrayList<Geofence>();


        mGeofenceNames.add("ITL");
        //mGeofenceNames.add("Varey House/The Curve");
        //mGeofenceNames.add("Village Shop/Beaumont Court");
        // mGeofenceNames.add("Santander Bank");
        // mGeofenceNames.add("Canalside");


        mGeofenceCoordinates.add(ITL);
        mGeofenceCoordinates.add(new LatLng(51.526143, -0.039552));


        for (int i = 0; i < mGeofenceNames.size();i++){
            mGeofenceList.add(new Geofence.Builder()
                    // Set the request ID of the geofence. This is a string to identify this
                    // geofence.
                    .setRequestId(mGeofenceNames.get(i))
                            //(latitude, longitude, radius_in_meters)
                    .setCircularRegion(mGeofenceCoordinates.get(i).latitude,mGeofenceCoordinates.get(i).longitude,30)
                            //expiration in milliseconds
                    .setExpirationDuration(300000000)
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
                    .build());
        }
        //Add geofences to GeofenceStore obect
        mGeofenceStore = new GeofenceStore(this, mGeofenceList); //Send over context and geofence list

    }
}
