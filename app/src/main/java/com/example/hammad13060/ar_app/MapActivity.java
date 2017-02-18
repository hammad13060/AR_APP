package com.example.hammad13060.ar_app;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import com.google.android.gms.common.images.Size;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.maps.MapFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;

public class MapActivity extends AppCompatActivity {
    private static final String TAG = "MapActivity";
    private MapHandler mapHandler;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initMapHandler();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initMapHandler();
        getMapFragment();
    }

    @Override
    protected void onStop() {

        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initMapHandler() {
        if (mapHandler == null) {
            mapHandler = new MapHandler(this, 17);
        }
    }

    private void getMapFragment() {
        if (mapFragment == null && mapHandler != null) {
            mapFragment = (MapFragment) getFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(mapHandler);
        }
    }

    @Subscribe
    public void onLocationUpdate(LocationEvent e) {
        if (mapHandler != null) {
            mapHandler.UpdateLocation(e.getLatitude(), e.getLongitude());
        }
    }
}
