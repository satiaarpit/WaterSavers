package com.example.asatia.watersavers.SurveyPackage;


import android.app.Activity;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asatia.watersavers.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    SurveyActivity activity;
    MapView mapView;
    GoogleMap map;
    LatLng cord;
    Location location;
    public MapFragment() {
        // Required empty public constructor
    }

    public void onAttach(Activity a)    {
        super.onAttach(a);
        activity=(SurveyActivity)a;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_map, container, false);
        activity.changeToolbarText("Map Me");
        mapView=(MapView)rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        map=mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        ArrayList<MapListItem> item=activity.getMapItem();
        for(int i=0;i<item.size();i++)  {
            cord =new LatLng(item.get(i).getLat(),item.get(i).getLon());
            Marker marker=map.addMarker(new MarkerOptions().position(cord).title(item.get(i).getUser()));
            marker.setSnippet("Score : " + item.get(i).getScore());
            if(item.get(i).isUser())    {
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(cord,16));
            }
            else    {
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }
            marker.showInfoWindow();
        }
        try {
            MapsInitializer.initialize(activity.getApplicationContext());
        } catch (Exception e)   {
            Log.e("In maps",e.toString());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
            map.animateCamera(cameraUpdate);
        }
        return rootView;
    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


}
