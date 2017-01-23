package com.example.yasmin.educationalaugmentedreality;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import org.sensingkit.sensingkitlib.SKException;
import org.sensingkit.sensingkitlib.SKSensorDataListener;
import org.sensingkit.sensingkitlib.SKSensorModuleType;
import org.sensingkit.sensingkitlib.SensingKitLib;
import org.sensingkit.sensingkitlib.SensingKitLibInterface;
import org.sensingkit.sensingkitlib.data.SKSensorData;


public class MainActivity extends AppCompatActivity {

    public Button start;
    public EditText batteryText;
    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        //Create instance of Camera
        mCamera = getCameraInstance();

      try{
            testSensor();
        }catch(SKException E){}

        //Create our Preview view and set it as the content of our activity
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void testSensor() throws SKException {
        final SensingKitLibInterface mSensingKitLib = SensingKitLib.getSensingKitLib(this);
        mSensingKitLib.registerSensorModule(SKSensorModuleType.BATTERY);

        start = (Button) findViewById(R.id.start);
        batteryText = (EditText) findViewById(R.id.batteryText);
        start.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                Button start = (Button) arg0;


               try {
                   mSensingKitLib.startContinuousSensingWithSensor(SKSensorModuleType.BATTERY);
                   mSensingKitLib.subscribeSensorDataListener(SKSensorModuleType.BATTERY, new SKSensorDataListener() {
                       @Override
                       public void onDataReceived(final SKSensorModuleType moduleType, final SKSensorData sensorData) {
                           System.out.println(sensorData.getDataInCSV());  // Print data in CSV format
                           batteryText.setText(sensorData.getDataInCSV());
                       }
                   });
               }
               catch(SKException e){}

                //batteryText.setText("Hi, this part works");
            }
        });

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
}
