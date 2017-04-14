package com.example.yasmin.educationalaugmentedreality;

import android.annotation.TargetApi;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.sensingkit.sensingkitlib.SKException;
import org.sensingkit.sensingkitlib.SKSensorDataListener;
import org.sensingkit.sensingkitlib.SKSensorModuleType;
import org.sensingkit.sensingkitlib.SensingKitLib;
import org.sensingkit.sensingkitlib.SensingKitLibInterface;
import org.sensingkit.sensingkitlib.data.SKSensorData;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } catch(SecurityException e) {}

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney and move the camera

        mMap = googleMap;
        //mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);


        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));

        //********SET TO FOCUS ON CURRENT LOCATION TO START OFF WITH*******
    }

    @Override
    public void onLocationChanged(Location loc) {

        float acc = loc.getAccuracy();
        Log.d("Location", "I've moved!!!");
        LatLng current = new LatLng(loc.getLatitude(), loc.getLongitude());
        loc.getLatitude();
        loc.getLongitude();

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(current, 17));
        mMap.addMarker(new MarkerOptions()
                .position(current)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.hiker)));
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
