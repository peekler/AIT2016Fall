package hu.ait.mapfragmentdemo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng hungary = new LatLng(47, 19);
        mMap.addMarker(new MarkerOptions().position(hungary).title("Marker in Hungary"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hungary));

        mMap.setTrafficEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        for (int i = 0; i < 20; i++) {
            MarkerOptions markerOptions = new MarkerOptions().
                    position(new LatLng(0, i * 10)).
                    title("Hello Android team!").
                    icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
            Marker marker = mMap.addMarker(markerOptions);
            marker.setDraggable(true);
        }


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(0, 0))
                .zoom(5)
                .bearing(90)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }
}
