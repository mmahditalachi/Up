package com.android.up;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Address extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static LatLng location_selected;
    private Button btn_selected;
    private MarkerOptions marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        MarkerSelected();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        LatLng location = new LatLng(35.792513, 51.461179);
        float zoom = 16.0f;
        mMap.addMarker(new MarkerOptions().position(location).title("Marker in my location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,zoom));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                marker = new MarkerOptions();

                marker.position(latLng);

                googleMap.clear();

                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                googleMap.addMarker(marker);

                location_selected = marker.getPosition();
            }
        });
    }
    public void MarkerSelected()
    {
        btn_selected = findViewById(R.id.btn_selected_ok);
        btn_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(location_selected != null) {
                    GoToSignUp();
                }
                else
                    Alert();
            }
        });
    }

    public void Alert()
    {
        AlertDialog alertDialog1 = new AlertDialog.Builder(
                Address.this).create();
        alertDialog1.setTitle("Error");
        alertDialog1.setMessage("Select your location");
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getApplicationContext(),
//                                    "You clicked on OK", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog1.show();
    }

    public void GoToSignUp()
    {
        finish();
    }
}
