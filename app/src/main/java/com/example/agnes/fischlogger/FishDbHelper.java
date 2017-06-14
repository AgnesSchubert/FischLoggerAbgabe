package com.example.agnes.fischlogger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Timestamp;

/**
 * Created by Agnes on 12.06.2017.
 */

public class FishDbHelper extends SQLiteOpenHelper{

    private static final String LOG_TAG = FishDbHelper.class.getSimpleName();
    public static final String DB_NAME = "fish_list.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_FISH_LIST = "fish_list";

    public static final String COLUMN_ART = "art";
    public static final String COLUMN_LAENGE = "laenge";
    public static final String COLUMN_BPA = "bpa";
    public static final String COLUMN_SV = "sv";
    public static final String COLUMN_HAEMATOM = "haematom";
    public static final String COLUMN_HAEMATOM_STELLE = "haematom_stelle";
    public static final String COLUMN_SCHUERFUNG = "schuerfung";
    public static final String COLUMN_SCHUERFUNG_STELLE = "schuerfung_stelle";
    public static final String COLUMN_SCHUERFUNG_VERPILZT = "schuerfung_verpilzt";
    public static final String COLUMN_OW = "ow";
    public static final String COLUMN_OW_STELLE = "ow_stelle";
    public static final String COLUMN_OW_VERPILZT ="ow_verpilzt";
    public static final String COLUMN_TA = "ta";
    public static final String COLUMN_TA_STELLE = "ta_stelle";
    public static final String COLUMN_TOTALDURCHTRENNUNG = "totaldurchtrennung";
    public static final String COLUMN_VERPILZUNG = "verpilzung";
    public static final String COLUMN_BEMERKUNG ="bemerkung";
    public static final String COLUMN_DATUM = "datum";
    public static final String COLUMN_ID = "_id";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_FISH_LIST +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DATUM + " REAL, " +
                    COLUMN_ART + " TEXT NOT NULL, " +
                    COLUMN_LAENGE + " INTEGER NOT NULL, " +
                    COLUMN_BPA + " TEXT NOT NULL, " +
                    COLUMN_SV + " TEXT NOT NULL, " +
                    COLUMN_HAEMATOM + " TEXT NOT NULL, " +
                    COLUMN_HAEMATOM_STELLE + " TEXT NOT NULL, " +
                    COLUMN_SCHUERFUNG + " TEXT NOT NULL, " +
                    COLUMN_SCHUERFUNG_STELLE + " TEXT NOT NULL, " +
                    COLUMN_SCHUERFUNG_VERPILZT + " INTEGER, " +
                    COLUMN_OW + " INTEGER, " +
                    COLUMN_OW_STELLE + " TEXT NOT NULL, " +
                    COLUMN_OW_VERPILZT + " INTEGER, " +
                    COLUMN_TA + " INTEGER, " +
                    COLUMN_TA_STELLE + " TEXT NOT NULL, " +
                    COLUMN_TOTALDURCHTRENNUNG + " INTEGER, " +
                    COLUMN_VERPILZUNG + " INTEGER, " +
                    COLUMN_BEMERKUNG + " TEXT);";

    public FishDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE + " angelegt.");
            db.execSQL(SQL_CREATE);
        }
        catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}