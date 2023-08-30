package com.example.passengeractivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    boolean isPermissionGranted;
    MapView mapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapView = findViewById(R.id.mapView);

        // Always call mapView.onCreate
        mapView.onCreate(savedInstanceState);

        checkMyPermission();
        if (isPermissionGranted) {
            mapView.getMapAsync(this);
        }
    }


    private void checkMyPermission() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Toast.makeText(MapsActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                        isPermissionGranted = true;
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), "");
                        intent.setData(uri);
                        startActivity(intent);
                    }


                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Initialize and configure the Google Map here
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // Enable the "My Location" button on the map
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);

        // Create LatLng objects for your markers (Cebu City, Colon, IT Park)
        LatLng cebuCityLatLng = new LatLng(10.3032, 123.8913);
        LatLng colonLatLng = new LatLng(10.2965, 123.898631);
        LatLng ITparklatlng = new LatLng(10.3280, 123.9063);
        LatLng ParkmallLatLng = new LatLng(10.3248, 123.9348);

        // Add markers for your locations
        googleMap.addMarker(new MarkerOptions()
                .position(cebuCityLatLng)
                .title("Southwestern University")
                .snippet("Southwestern University Stall (#0001)"));

        googleMap.addMarker(new MarkerOptions()
                .position(colonLatLng)
                .title("Colon")
                .snippet("Colon Stall (#0002)"));

        googleMap.addMarker(new MarkerOptions()
                .position(ITparklatlng)
                .title("IT Park")
                .snippet("IT Park Stall (#0003)"));

        googleMap.addMarker(new MarkerOptions()
                .position(ParkmallLatLng)
                .title("Park Mall")
                .snippet("Southwestern University Stall (#0001)"));

        // Move the camera to focus on Cebu City and set an initial zoom level
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cebuCityLatLng, 12));
    }


    @Override
    protected void onStart() {
        super.onStart();
        try {
            // Initialize the map here
            // ...
        } catch (NullPointerException e) {
            // Handle the null pointer exception here or log it for debugging
            Log.e("MapsActivity", "Error initializing map: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mapView != null) {
            mapView.onResume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
