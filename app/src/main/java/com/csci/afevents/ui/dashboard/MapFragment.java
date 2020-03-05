package com.csci.afevents.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.csci.afevents.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.Map;

public class MapFragment extends Fragment {

    private MapViewModel mapViewModel;
    private MapView osm;
    private MapController mc;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mapViewModel =
                ViewModelProviders.of(this).get(MapViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        Context ctx = getActivity();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        osm = root.findViewById(R.id.map);
        osm.setTileSource(TileSourceFactory.MAPNIK);
        osm.setMultiTouchControls(true);
        mc = (MapController) osm.getController();
        /**
         * Default point set to AFH  coordinates
         */
        GeoPoint center = new GeoPoint(44.648766,-63.575237);
        addMarker(center,"AFH","Alliance Francais Halifax");
        setFocus(center);

        return root;
    }
    public void onResume() {
        super.onResume();
        osm.onResume();
    }
    public void onPause(){
        super.onPause();
        osm.onPause();
    }

    /**
     * Method to set the focus on a selected point
     * @param point
     */
    public void setFocus(GeoPoint point){
        mc.setCenter(point);
        mc.setZoom(17);
    }

    /**
     * Method to add new point to the map
     * @param point
     */
    public void addMarker(GeoPoint point, String location_title, String location_desc){
        Marker dot = new Marker(osm);
        dot.setPosition(point);
        dot.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        osm.getOverlays().clear();
        osm.getOverlays().add(dot);
    }
}