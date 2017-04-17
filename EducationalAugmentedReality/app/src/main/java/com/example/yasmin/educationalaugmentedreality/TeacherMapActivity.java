package com.example.yasmin.educationalaugmentedreality;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class TeacherMapActivity extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    private Marker mMarker;
    private LatLng objLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
/*
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        Location myLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        // Location wasn't found, check the next most accurate place for the current location
        if (myLocation == null) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            // Finds a provider that matches the criteria
            String provider = lm.getBestProvider(criteria, true);
            // Use the provider to get the last known location

            myLocation = lm.getLastKnownLocation(provider);
            LatLng loc = new LatLng(myLocation.getLatitude(), myLocation.getLatitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 17));
        }
*/

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point) {

                if (mMarker != null) {
                    mMarker.remove();
                }

                objLoc = point;
                mMarker = mMap.addMarker(new MarkerOptions()
                        .position(point)
                        .title("Selected location")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker)));


            }
        });
    }
    public void onClick(View view) {

        if (view.getId() == R.id.okButton) {
            Intent intent = new Intent();
            intent.putExtra("location", objLoc);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

}
