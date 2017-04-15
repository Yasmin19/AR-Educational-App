package com.example.yasmin.educationalaugmentedreality;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.model.LatLng;

import org.sensingkit.sensingkitlib.SKException;
import org.sensingkit.sensingkitlib.SKSensorDataListener;
import org.sensingkit.sensingkitlib.SKSensorModuleType;
import org.sensingkit.sensingkitlib.SensingKitLib;
import org.sensingkit.sensingkitlib.SensingKitLibInterface;
import org.sensingkit.sensingkitlib.data.SKSensorData;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public Button start;
    public EditText batteryText;

    ArrayList<Geofence> mGeofenceList; //List of geofences used
    ArrayList<String> mGeofenceNames; //List of geofence names
    ArrayList<LatLng> mGeofenceCoordinates; //List of geofence coordinates
    public GeofenceStore mGeofenceStore;

    private static final LatLng ITL = new LatLng(51.522838, -0.043184);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateGeofences();

        //startService(new Intent(getApplicationContext(), OrientationSensor.class));

    }

    public void onClick(View view){

        if (view.getId() == R.id.next) {
            Intent intent = new Intent(this, CrossWordActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.map){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.cameraButton) {
            Intent intent = new Intent(this, CameraActivity.class);
            startActivity(intent);
        }

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
