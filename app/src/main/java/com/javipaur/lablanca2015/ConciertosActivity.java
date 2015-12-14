package com.javipaur.lablanca2015;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.javipaur.lablanca2015.db.DBAdapter;
import com.javipaur.lablanca2015.db.DBHelper;

import java.util.ArrayList;

/**
 * Created by 2fprogmm08 on 2/3/15.
 */
public class ConciertosActivity extends FragmentActivity {
    ListView lista;
    ArrayAdapter adaptador;
    private DBHelper BD;
    DBAdapter dbAdapter;
    Boolean mBound;


    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.conciertos);
        setUpMapIfNeeded();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //INICIALIZAMOS EL SERVICIO

    @Override
    protected void onStart() {
        super.onStart();
        // Enlazamos con el Servicio
        Intent intent = new Intent(this, DBAdapter.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Nos desenlazamos del servicio
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }


    private void loadConciertos() {
        ArrayList<ProgramaClass> conciertos = dbAdapter.getAllConciertos();
        lista=(ListView)findViewById(R.id.listac);
        adaptador=new ConciertoAdapter(this,conciertos,dbAdapter);
        lista.setAdapter(adaptador);
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Enlazamos con LocalService, casteamos el IBinder y obtenemos as�
            // la instancia del Servicio
            DBAdapter.LocalBinder binder = (DBAdapter.LocalBinder) service;
            dbAdapter = binder.getService();
            mBound = true;
            loadConciertos();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        LatLng vitoria = new LatLng(42.846231, -2.671589);
        LatLng plazaMachete = new LatLng(42.847319, -2.672064);
        LatLng plazaEspaña = new LatLng(42.846479, -2.672427);
        LatLng plazaLosFueros = new LatLng(42.845617, -2.670049);
        mMap.addMarker(new MarkerOptions().position(plazaMachete).title("Plaza Machete"));
        mMap.addMarker(new MarkerOptions().position(plazaLosFueros).title("Plaza Los Fueros"));
        mMap.addMarker(new MarkerOptions().position(plazaEspaña).title("Plaza España"));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        CameraPosition camPos = new CameraPosition.Builder()

                .target(vitoria)   //Centramos el mapa en vitoria centro
                .zoom(17)         //Establecemos el zoom en 19
                .bearing(45)      //Establecemos la orientación con el noreste arriba
                .tilt(70)         //Bajamos el punto de vista de la cámara 70 grados
                .build();

        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);

        mMap.animateCamera(camUpd3);
    }

}
