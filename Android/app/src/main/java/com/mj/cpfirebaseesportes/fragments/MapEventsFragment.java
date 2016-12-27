package com.mj.cpfirebaseesportes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.mj.cpfirebaseesportes.R;


public class MapEventsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;

    public MapEventsFragment() { }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
