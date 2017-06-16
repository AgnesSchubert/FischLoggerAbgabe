package com.example.agnes.fischlogger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agnes on 12.06.2017.
 */

public class FishDataSource {
    public static final String LOG_TAG = FishDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private FishDbHelper dbHelper;

    private String[] columns = {
            FishDbHelper.COLUMN_ID,
            FishDbHelper.COLUMN_DATUM,
            FishDbHelper.COLUMN_ART,
            FishDbHelper.COLUMN_LAENGE,
            FishDbHelper.COLUMN_BPA,
            FishDbHelper.COLUMN_SV,
            FishDbHelper.COLUMN_HAEMATOM,
            FishDbHelper.COLUMN_HAEMATOM_STELLE,
            FishDbHelper.COLUMN_SCHUERFUNG,
            FishDbHelper.COLUMN_SCHUERFUNG_STELLE,
            FishDbHelper.COLUMN_SCHUERFUNG_VERPILZT,
            FishDbHelper.COLUMN_OW,
            FishDbHelper.COLUMN_OW_STELLE,
            FishDbHelper.COLUMN_OW_VERPILZT,
            FishDbHelper.COLUMN_TA,
            FishDbHelper.COLUMN_TA_STELLE,
            FishDbHelper.COLUMN_TOTALDURCHTRENNUNG,
            FishDbHelper.COLUMN_VERPILZUNG,
            FishDbHelper.COLUMN_BEMERKUNG
    };

    public FishDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new FishDbHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

public Fish createFish (String art,
                        double laenge,
                        Seite bpa,
                        Seite sv,
                        boolean haematom,
                        String heamatom_stelle,
                        boolean schuerfung,
                        String schuerfung_stelle,
                        boolean schuerfung_verpilzt,
                        boolean ow,
                        String ow_stelle,
                        boolean ow_verpilzt,
                        boolean ta,
                        String ta_stelle,
                        boolean totaldurchtrennung,
                        boolean verpilzung,
                        String bemerkung,
                        double datum) {
    this.open();
    ContentValues values = new ContentValues();
    values.put(FishDbHelper.COLUMN_ART, art);
    values.put(FishDbHelper.COLUMN_LAENGE, laenge);
    values.put(FishDbHelper.COLUMN_BPA, bpa.getName());
    values.put(FishDbHelper.COLUMN_SV, sv.getName());
    values.put(FishDbHelper.COLUMN_HAEMATOM, haematom);
    values.put(FishDbHelper.COLUMN_HAEMATOM_STELLE, heamatom_stelle);
    values.put(FishDbHelper.COLUMN_SCHUERFUNG, schuerfung);
    values.put(FishDbHelper.COLUMN_SCHUERFUNG_STELLE, schuerfung_stelle);
    values.put(FishDbHelper.COLUMN_SCHUERFUNG_VERPILZT, schuerfung_verpilzt);
    values.put(FishDbHelper.COLUMN_OW, ow);
    values.put(FishDbHelper.COLUMN_OW_STELLE, ow_stelle);
    values.put(FishDbHelper.COLUMN_OW_VERPILZT, ow_verpilzt);
    values.put(FishDbHelper.COLUMN_TA, ta);
    values.put(FishDbHelper.COLUMN_TA_STELLE, ta_stelle);
    values.put(FishDbHelper.COLUMN_TOTALDURCHTRENNUNG, totaldurchtrennung);
    values.put(FishDbHelper.COLUMN_VERPILZUNG, verpilzung);
    values.put(FishDbHelper.COLUMN_BEMERKUNG, bemerkung);
    values.put(FishDbHelper.COLUMN_DATUM, datum);

    long insertId = database.insert(FishDbHelper.TABLE_FISH_LIST, null, values);

    //brauche ich das überhaupt?
    Cursor cursor = database.query(FishDbHelper.TABLE_FISH_LIST,
            columns, FishDbHelper.COLUMN_ID + "=" + insertId,
            null, null, null, null);

    cursor.moveToFirst();
    Fish fish = cursorToFish(cursor);
    cursor.close();
    this.close();

    return fish;
    }

    private Fish cursorToFish(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(FishDbHelper.COLUMN_ID);
        int idArt = cursor.getColumnIndex(FishDbHelper.COLUMN_ART);
        int idLaenge = cursor.getColumnIndex(FishDbHelper.COLUMN_LAENGE);
        int idBpa = cursor.getColumnIndex(FishDbHelper.COLUMN_BPA);
        int idSv = cursor.getColumnIndex(FishDbHelper.COLUMN_SV);
        int idHaematom = cursor.getColumnIndex(FishDbHelper.COLUMN_HAEMATOM);
        int idHaematomStelle = cursor.getColumnIndex(FishDbHelper.COLUMN_HAEMATOM_STELLE);
        int idSchuerfung = cursor.getColumnIndex(FishDbHelper.COLUMN_SCHUERFUNG);
        int idSchuerfungStelle = cursor.getColumnIndex(FishDbHelper.COLUMN_SCHUERFUNG_STELLE);
        int idSchuerfungVerpilzt = cursor.getColumnIndex(FishDbHelper.COLUMN_SCHUERFUNG_VERPILZT);
        int idOw = cursor.getColumnIndex(FishDbHelper.COLUMN_OW);
        int idOwStelle = cursor.getColumnIndex(FishDbHelper.COLUMN_OW_STELLE);
        int idOwVerpilzt = cursor.getColumnIndex(FishDbHelper.COLUMN_OW_VERPILZT);
        int idTa = cursor.getColumnIndex(FishDbHelper.COLUMN_TA);
        int idTaStelle = cursor.getColumnIndex(FishDbHelper.COLUMN_TA_STELLE);
        int idTotaldurchtrennung = cursor.getColumnIndex(FishDbHelper.COLUMN_TOTALDURCHTRENNUNG);
        int idVerpilzung = cursor.getColumnIndex(FishDbHelper.COLUMN_VERPILZUNG);
        int idBemerkung = cursor.getColumnIndex(FishDbHelper.COLUMN_BEMERKUNG);
        int idDatum = cursor.getColumnIndex(FishDbHelper.COLUMN_DATUM);

        String art = cursor.getString(idArt);
        double laenge = cursor.getDouble(idLaenge);
        Seite bpa = Seite.toSeite(cursor.getString(idBpa));
        Seite sv = Seite.toSeite(cursor.getString(idSv));
        boolean haematom = (cursor.getInt(idHaematom)==1);
        String heamatom_stelle = cursor.getString(idHaematomStelle);
        boolean schuerfung = (cursor.getInt(idSchuerfung)==1);
        String schuerfung_stelle = cursor.getString(idSchuerfungStelle);
        boolean schuerfung_verpilzt = (cursor.getInt(idSchuerfungVerpilzt)==1);
        boolean ow = (cursor.getInt(idOw)==1);
        String ow_stelle = cursor.getString(idOwStelle);
        boolean ow_verpilzt = (cursor.getInt(idOwVerpilzt)==1);
        boolean ta = (cursor.getInt(idTa)==1);
        String ta_stelle = cursor.getString(idTaStelle);
        boolean totaldurchtrennung = (cursor.getInt(idTotaldurchtrennung)==1);
        boolean verpilzung = (cursor.getInt(idVerpilzung)==1);
        String bemerkung = cursor.getString(idBemerkung);
        double datum = cursor.getDouble(idDatum);
        long id = cursor.getLong(idIndex);

        Fish fish = new Fish(art,
                laenge,
                bpa,
                sv,
                haematom,
                heamatom_stelle,
                schuerfung,
                schuerfung_stelle,
                schuerfung_verpilzt,
                ow,
                ow_stelle,
                ow_verpilzt,
                ta,
                ta_stelle,
                totaldurchtrennung,
                verpilzung,
                bemerkung,
                datum,
                id);

        return fish;
    }

    public List<Fish> getAllFishes() {
        List<Fish> fishList = new ArrayList<>();

        Cursor cursor = database.query(FishDbHelper.TABLE_FISH_LIST,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Fish fish;

        while(!cursor.isAfterLast()) {
            fish = cursorToFish(cursor);
            fishList.add(fish);
            Log.d(LOG_TAG, "ID: " + fish.getId() + ", Inhalt: " + fish.toString());
            cursor.moveToNext();
        }

        cursor.close();

        return fishList;
    }

}