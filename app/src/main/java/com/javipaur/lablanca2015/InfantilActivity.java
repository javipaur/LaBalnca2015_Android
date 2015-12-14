package com.javipaur.lablanca2015;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.javipaur.lablanca2015.db.DBAdapter;
import com.javipaur.lablanca2015.db.DBHelper;

import java.util.ArrayList;

/**
 * Created by Javipaur on 21/02/2015.
 */
public class InfantilActivity extends Activity {

    ListView lista;
    ArrayAdapter adaptador;
    private DBHelper BD;
    DBAdapter dbAdapter;
    Boolean mBound;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.infantil);
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


    private void loadInfantil() {
        ArrayList<ProgramaClass> infantil = dbAdapter.getAllInfantil();
        lista=(ListView)findViewById(R.id.listai);
        adaptador=new InfantilAdapter(this,infantil,dbAdapter);
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
            loadInfantil();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
