package com.javipaur.lablanca2015;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.javipaur.lablanca2015.db.DBAdapter;
import com.javipaur.lablanca2015.db.DBHelper;
import com.javipaur.lablanca2015.db.Programa.ProgramaColumns;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Javipaur on 21/02/2015.
 */
public class FavoritosActivity extends Activity {

    ListView lista;
    ArrayAdapter adaptador;
    private DBHelper BD;
    DBAdapter dbAdapter;
    Boolean mBound;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.favoritos);
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

    //iNICIALIZAMOS EL SERVICIO


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


    private void loadFavoritos() {
        ArrayList<ProgramaClass> favoritos = dbAdapter.getAllFavoritos();
        lista=(ListView)findViewById(R.id.listaf);

        adaptador=new FavoritosAdapter(this,favoritos,dbAdapter);

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
            loadFavoritos();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
