package com.example.yasmin.educationalaugmentedreality;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.sensingkit.sensingkitlib.SKException;
import org.sensingkit.sensingkitlib.SKSensorDataListener;
import org.sensingkit.sensingkitlib.SKSensorModuleType;
import org.sensingkit.sensingkitlib.SensingKitLib;
import org.sensingkit.sensingkitlib.SensingKitLibInterface;
import org.sensingkit.sensingkitlib.data.SKSensorData;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker mMarker;
    private Marker objMarker;
    private Context mContext;
    private LatLng objectLoc;
    Location currentLocation;
    Location objectLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        startService(new Intent(getApplicationContext(), OrientationSensor.class));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setOnMarkerClickListener(this);

        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }

        //Add marker for object
        objectLoc =Items.getWordLocation(CrossWord.selectedWord);
        objMarker = mMap.addMarker(new MarkerOptions()
                .position(objectLoc)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.treasure)));

        objectLocation = new Location("Object");
        objectLocation.setLatitude(objectLoc.latitude);
        objectLocation.setLongitude(objectLoc.longitude);

        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {

            public void onLocationChanged(Location loc) {
                float acc = loc.getAccuracy();
                LatLng current = new LatLng(loc.getLatitude(), loc.getLongitude());
                loc.getLatitude();
                loc.getLongitude();

                currentLocation = loc;

                //Delete marker if it already exists
                if (mMarker != null) {
                    mMarker.remove();
                }
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(current)
                        .zoom(19)
                        .tilt(60)
                        .bearing(0)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                mMarker = mMap.addMarker(new MarkerOptions()
                        .position(current)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.hiker)));

            }

            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            public void onProviderEnabled(String s) {
            }

            public void onProviderDisabled(String s) {
            }

        });

    }

    public boolean onMarkerClick(final Marker marker) {
        //When object marker is clicked on,
        // start child activity of the camera view if object within 10m
        if (marker.equals(objMarker)) {
           // if (currentLocation.distanceTo(objectLocation) <= 15){

                //Log.d("DISTANCE", "" + currentLocation.distanceTo(objectLocation));
                Intent intent = new Intent(this, CameraActivity.class);
                startActivityForResult(intent, 1);
            /*}
            else
                return false;*/
        }

        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            Intent intent = new Intent();
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

}
