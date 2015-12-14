package com.javipaur.lablanca2015.db.Programa;

public class ProgramaTable implements ProgramaColumns {

    public final static String TABLE_NAME = "programa";

    public final static String[] COLS = {_ID,
            FECHA, HORA,
            LUGAR, TIPO, ACTO, FAVORITO};

    public final static String[] COL = {FECHA};

    /*public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FECHA + " TEXT , " + HORA + " TEXT, " + LUGAR+ " TEXT, " +ACTO+ " TEXT, " +FAVORITO
            + " TEXT );";*/

}
