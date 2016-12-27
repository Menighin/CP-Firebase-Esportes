package com.mj.cpfirebaseesportes.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.mj.cpfirebaseesportes.R;
import com.mj.cpfirebaseesportes.components.listeners.GPSListener;
import com.mj.cpfirebaseesportes.fragments.interfaces.FragmentCallback;

public class MapPinFragment extends BaseFragment implements OnMapReadyCallback {

    public static final String TAG = "MAP_PIN_FRAGMENT";

    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private GPSListener gpsListener;
    private LatLng eventPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_pin, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_pin_fragment);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        gpsListener = new GPSListener(context);
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(gpsListener.getLatitude(), gpsListener.getLongitude()), 15));
        map.setMyLocationEnabled(true);

        eventPosition = map.getCameraPosition().target;
        callbackActivity();

        map.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                eventPosition = map.getCameraPosition().target;
                callbackActivity();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        getChildFragmentManager().beginTransaction().remove(mapFragment).commit();
    }

    private void callbackActivity() {
        if (getActivity() instanceof FragmentCallback)
            ((FragmentCallback)getActivity()).fragmentCallback(TAG, eventPosition);
    }

}
