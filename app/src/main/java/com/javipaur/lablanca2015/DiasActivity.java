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
import android.widget.TextView;
import android.widget.Toast;

import com.javipaur.lablanca2015.db.DBAdapter;
import com.javipaur.lablanca2015.db.DBHelper;
import com.javipaur.lablanca2015.db.Programa.ProgramaColumns;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Javipaur on 07/02/2015.
 */
public class DiasActivity extends Activity
{
    ListView lista;
    ArrayAdapter adaptador;
    private DBHelper BD;
    DBAdapter dbAdapter;
    Boolean mBound;
    ArrayList<ProgramaClass>dias;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dias);
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


    private void loadDias() {
        ArrayList<ProgramaClass> dias = dbAdapter.getAllDias();
        lista=(ListView)findViewById(R.id.listad);
        adaptador=new DiasAdapter(this,dias,dbAdapter);
        //lista.setOnItemClickListener(this);
        lista.setAdapter(adaptador);

        //Eventos

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProgramaActivity.class);
                String dia =
                        ((ProgramaClass)a.getItemAtPosition(position)).getFecha();
                intent.putExtra("dia", dia);
                //System.out.println("Día seleccionado: " + dia);
                startActivity(intent);
               }
        });
      }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Enlazamos con LocalService, casteamos el IBinder y obtenemos as�
            // la instancia del Servicio
            DBAdapter.LocalBinder binder = (DBAdapter.LocalBinder) service;
            dbAdapter = binder.getService();
            mBound = true;
            loadDias();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}