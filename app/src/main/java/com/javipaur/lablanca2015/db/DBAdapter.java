package com.javipaur.lablanca2015.db;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.provider.BaseColumns;


import com.javipaur.lablanca2015.ProgramaClass;
import com.javipaur.lablanca2015.db.Programa.ProgramaColumns;
import com.javipaur.lablanca2015.db.Programa.ProgramaTable;

import java.util.ArrayList;
/**
 * Created by Javipaur on 10/02/2015.
 *
 */
public class DBAdapter extends Service {

	private final IBinder mBinder = new LocalBinder();

	private DBHelper dbHelper;

	private SQLiteDatabase db;


    public class LocalBinder extends Binder {
		public DBAdapter getService() {
			return DBAdapter.this;
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	@Override
	public void onCreate() {
		dbHelper = new DBHelper(this);
		// try {
		// db = dbHelper.getWritableDatabase();
		// } catch (SQLiteException ex) {
		// db = dbHelper.getReadableDatabase();
		// }
		db = dbHelper.getDataBase();
	}

	@Override
	public void onDestroy() {
		db.close();
	}

    /**
     * Lee los actos de un dia de fiesta de programa
     * */

    public ArrayList<ProgramaClass> getDiaPrograma(String dia) {
        ArrayList<ProgramaClass> actosDia = new ArrayList<ProgramaClass>();

        String sql="SELECT * FROM programa where fecha = \""+dia+"\" ORDER BY hora ASC";
        Cursor result = db.rawQuery(sql,null);

       /* Cursor result = db.query(ProgramaTable.TABLE_NAME, ProgramaTable.COLS,
                "fecha=?", args, null, null, "fecha ASC , hora ASC ");*/

        if (result.moveToFirst())
            do {

                actosDia.add(new ProgramaClass(
                        result.getInt(result.getColumnIndex(BaseColumns._ID)),
                        result.getString(result.getColumnIndex(ProgramaColumns.FECHA)),
                        result.getString(result.getColumnIndex(ProgramaColumns.HORA)),
                        result.getString(result.getColumnIndex(ProgramaColumns.LUGAR)),
                        result.getString(result.getColumnIndex(ProgramaColumns.TIPO)),
                        result.getString(result.getColumnIndex(ProgramaColumns.ACTO)),
                        result.getString(result.getColumnIndex(ProgramaColumns.FAVORITO))));
            } while (result.moveToNext());
        return actosDia;
    }

    /**
     * Lee todos los dias de fiesta de programa
     * */

    public ArrayList<ProgramaClass> getAllDias() {
        ArrayList<ProgramaClass> clientes = new ArrayList<ProgramaClass>();

    // Expanded version:
    /*    Cursor cursor = db.query(
    /* FROM  "messages_database",
    /* SELECT  new String[]{ "*", "COUNT(address) AS count" },
    /* WHERE  null,
    /* WHERE args  null,
    /* GROUP BY "address",
    /* HAVING  null,
    /* ORDER BY "date DESC" //);
    */
        Cursor result = db.query(ProgramaTable.TABLE_NAME, ProgramaTable.COL,
                null, null, "fecha", null, "fecha ASC");

        if (result.moveToFirst())
            do {
                clientes.add(new ProgramaClass(result.getString(result.getColumnIndex(ProgramaColumns.FECHA))));
            } while (result.moveToNext());
        return clientes;
    }

    /**
     * Lee todos los actos infantiles (tipo=4) de programa
     * */

    public ArrayList<ProgramaClass> getAllInfantil() {
        ArrayList<ProgramaClass> actosInfantil = new ArrayList<ProgramaClass>();

        // Expanded version:
    /*    Cursor cursor = db.query(
    /* FROM  "messages_database",
    /* SELECT  new String[]{ "*", "COUNT(address) AS count" },
    /* WHERE  null,
    /* WHERE args  null,
    /* GROUP BY "address",
    /* HAVING  null,
    /* ORDER BY "date DESC" //);
*/
        String[]args=new String []{"4"};
        Cursor result = db.query(ProgramaTable.TABLE_NAME, ProgramaTable.COLS,
                "tipo=?", args, null, null, "fecha ASC , hora ASC ");

        if (result.moveToFirst())
            do {
                actosInfantil.add(new ProgramaClass(
                        result.getInt(result.getColumnIndex(BaseColumns._ID)),
                        result.getString(result.getColumnIndex(ProgramaColumns.FECHA)),
                        result.getString(result.getColumnIndex(ProgramaColumns.HORA)),
                        result.getString(result.getColumnIndex(ProgramaColumns.LUGAR)),
                        result.getString(result.getColumnIndex(ProgramaColumns.TIPO)),
                        result.getString(result.getColumnIndex(ProgramaColumns.ACTO)),
                        result.getString(result.getColumnIndex(ProgramaColumns.FAVORITO))));
            } while (result.moveToNext());
        return actosInfantil;
    }


    public ArrayList<ProgramaClass> getAllConciertos() {
        ArrayList<ProgramaClass> conciertos= new ArrayList<ProgramaClass>();

        // Expanded version:
    /*    Cursor cursor = db.query(
    /* FROM  "messages_database",
    /* SELECT  new String[]{ "*", "COUNT(address) AS count" },
    /* WHERE  null,
    /* WHERE args  null,
    /* GROUP BY "address",
    /* HAVING  null,
    /* ORDER BY "date DESC" //);
*/
        String[]args=new String []{"3"};
        Cursor result = db.query(ProgramaTable.TABLE_NAME, ProgramaTable.COLS,
                "tipo=?", args, null, null, "fecha ASC , hora ASC ");

        if (result.moveToFirst())
            do {
                conciertos.add(new ProgramaClass(
                        result.getInt(result.getColumnIndex(BaseColumns._ID)),
                        result.getString(result.getColumnIndex(ProgramaColumns.FECHA)),
                        result.getString(result.getColumnIndex(ProgramaColumns.HORA)),
                        result.getString(result.getColumnIndex(ProgramaColumns.LUGAR)),
                        result.getString(result.getColumnIndex(ProgramaColumns.TIPO)),
                        result.getString(result.getColumnIndex(ProgramaColumns.ACTO)),
                        result.getString(result.getColumnIndex(ProgramaColumns.FAVORITO))));
            } while (result.moveToNext());
        return conciertos;
    }

    /**
     * Lee todos los actos marcados como favoritos (favorito=1) de programa
     * */

    public ArrayList<ProgramaClass> getAllFavoritos() {
        ArrayList<ProgramaClass> actosFavoritos = new ArrayList<ProgramaClass>();

        // Expanded version:
    /*    Cursor cursor = db.query(
    /* FROM  "messages_database",
    /* SELECT  new String[]{ "*", "COUNT(address) AS count" },
    /* WHERE  null,
    /* WHERE args  null,
    /* GROUP BY "address",
    /* HAVING  null,
    /* ORDER BY "date DESC" //);
*/
        String[]args=new String []{"1"};
        Cursor result = db.query(ProgramaTable.TABLE_NAME, ProgramaTable.COLS,
                "favorito=?", args, null, null, "fecha ASC , hora ASC ");

        if (result.moveToFirst())
            do {
                actosFavoritos.add(new ProgramaClass(
                        result.getInt(result.getColumnIndex(BaseColumns._ID)),
                        result.getString(result.getColumnIndex(ProgramaColumns.FECHA)),
                        result.getString(result.getColumnIndex(ProgramaColumns.HORA)),
                        result.getString(result.getColumnIndex(ProgramaColumns.LUGAR)),
                        result.getString(result.getColumnIndex(ProgramaColumns.TIPO)),
                        result.getString(result.getColumnIndex(ProgramaColumns.ACTO)),
                        result.getString(result.getColumnIndex(ProgramaColumns.FAVORITO))));
            } while (result.moveToNext());
        return actosFavoritos;
    }


    /**
     * Actualiza favorito en programa (on=true, off=false)
     * */

    public boolean updateFavoritos(String _rowIndex, boolean favorito) {
        ContentValues newValues = new ContentValues();
        newValues.put(ProgramaColumns.FAVORITO, favorito);
        return db.update(ProgramaTable.TABLE_NAME, newValues, BaseColumns._ID
                + "=" +_rowIndex, null) > 0;
    }

}
