package com.javipaur.lablanca2015;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        final ImageButton programa = (ImageButton) findViewById(R.id.programa);
        //Implementamos el evento “click” del botón
        programa.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              //Creamos el Intent
              Intent intent =
                      new Intent(MainActivity.this, DiasActivity.class);
              //Iniciamos la nueva actividad
              startActivity(intent);
          }
        });

        final ImageButton dev = (ImageButton) findViewById(R.id.dev);
        //Implementamos el evento “click” del botón
        dev.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             //Creamos el Intent
             Intent intent =
                    new Intent(MainActivity.this, DesarrolladorActivity.class);
             //Iniciamos la nueva actividad
             startActivity(intent);
          }
        });

        final ImageButton favoritos = (ImageButton) findViewById(R.id.favoritos);
        //Implementamos el evento “click” del botón
        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //Creamos el Intent
             Intent intent =
                     new Intent(MainActivity.this, FavoritosActivity.class);
             //Iniciamos la nueva actividad
             startActivity(intent);
            }
        });

        final ImageButton conciertos = (ImageButton) findViewById(R.id.conciertos);
        //Implementamos el evento “click” del botón
        conciertos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Creamos el Intent
            Intent intent =
                    new Intent(MainActivity.this, ConciertosActivity.class);
            //Iniciamos la nueva actividad
            startActivity(intent);
            }
        });


        final ImageButton infantil = (ImageButton) findViewById(R.id.infantil);
        //Implementamos el evento “click” del botón
        infantil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //Creamos el Intent
             Intent intent =
                    new Intent(MainActivity.this, InfantilActivity.class);
             //Iniciamos la nueva actividad
             startActivity(intent);
            }
        });

        final ImageButton toros = (ImageButton) findViewById(R.id.toros);
        //Implementamos el evento “click” del botón
        toros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //Creamos el Intent
             Intent intent =
                   new Intent(MainActivity.this, TorosActivity.class);
             //Iniciamos la nueva actividad
             startActivity(intent);
            }
        });

        final ImageButton info = (ImageButton) findViewById(R.id.info);
        //Implementamos el evento “click” del botón
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(MainActivity.this, informacion.class);
                //Iniciamos la nueva actividad
                startActivity(intent);
            }
        });
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
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
