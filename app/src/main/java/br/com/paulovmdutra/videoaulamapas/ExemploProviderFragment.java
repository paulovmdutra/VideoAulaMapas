package br.com.paulovmdutra.videoaulamapas;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ExemploProviderFragment extends SupportMapFragment implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener{

    private static final String TAG = "ExemploProviderFragment";

    private GoogleMap mMap;

    private LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {

            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();

            String provider = locationManager.getBestProvider(criteria, true);

            Toast.makeText(getContext(), "Provider: " + provider, Toast.LENGTH_SHORT).show();

            mMap = googleMap;

            mMap.setMyLocationEnabled(true);

        }catch (SecurityException ex)
        {
            Log.e(TAG, "Erro", ex );
        }

        mMap.setOnMapClickListener(this);

        mMap.getUiSettings().setZoomControlsEnabled(true);

        //Coordenadas para a cidade de Sidney
        LatLng sydney = new LatLng(-33.87365, 151.20689);

        MarkerOptions marker = new MarkerOptions();
        marker.position(sydney);
        marker.title("Marker in Sydney");

        mMap.addMarker(marker);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    @Override
    public void onMapClick(LatLng latLng) {

        Toast.makeText( getContext(), "Coordenadas: " + latLng.toString(),
                Toast.LENGTH_SHORT).show();


    }



}
