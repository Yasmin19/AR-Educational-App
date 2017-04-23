package com.example.yasmin.educationalaugmentedreality;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

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
    public static boolean startGame = false;

    ArrayList<Geofence> mGeofenceList; //List of geofences used
    ArrayList<String> mGeofenceNames; //List of geofence names
    ArrayList<LatLng> mGeofenceCoordinates; //List of geofence coordinates
    public GeofenceStore mGeofenceStore;

    private static final LatLng ITL = new LatLng(51.522838, -0.043184);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){

        if (view.getId() == R.id.playButton) {
            if (TeacherActivity.cont) {
                Intent intent = new Intent(this, CrossWordActivity.class);
                startActivity(intent);
            }
            else{
                Toast toast = Toast.makeText(this, "You must add objects before starting the game",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0, 0);
                toast.show();
            }
        }
        else if (view.getId() == R.id.map){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.cameraButton) {
            Intent intent = new Intent(this, CameraActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.teacherButton) {
            Intent intent = new Intent(this, TeacherActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.exitButton){
            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }

}
