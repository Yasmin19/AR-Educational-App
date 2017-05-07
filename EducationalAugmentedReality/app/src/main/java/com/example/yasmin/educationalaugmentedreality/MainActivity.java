package com.example.yasmin.educationalaugmentedreality;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.RelativeLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.frontScreen);
        rl.setBackgroundColor(Color.WHITE);

        Button b1 = (Button) findViewById(R.id.playButton);
        Button b2 = (Button) findViewById(R.id.teacherButton);
        Button b3 = (Button) findViewById (R.id.exitButton);

        b1.setTextSize(30);
        b2.setTextSize(20);
        b3.setTextSize(20);

    }

    public void onClick(View view){

        if (view.getId() == R.id.playButton) {
            if (Items.getItemsNumber() < 2){
                Toast toast = Toast.makeText(this, "You must add at least 2 objects before starting the game",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0, 0);
                toast.show();
            }
            else{
                Intent intent = new Intent(this, CrossWordActivity.class);
                startActivity(intent);
            }
        }
        else if (view.getId() == R.id.teacherButton) {
            Intent intent = new Intent(this, TeacherLoginActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.exitButton){
            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }

}
