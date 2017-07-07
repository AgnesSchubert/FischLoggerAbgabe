package com.example.agnes.fischlogger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agnes on 12.06.2017.
 */

public class FishDataSource {

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
        dbHelper = new FishDbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Fish createFish(Fish f) {
        this.open();
        ContentValues values = new ContentValues();
        values.put(FishDbHelper.COLUMN_ART, f.getArt());
        values.put(FishDbHelper.COLUMN_LAENGE, f.getLaenge());
        values.put(FishDbHelper.COLUMN_BPA, f.getBpa().getName());
        values.put(FishDbHelper.COLUMN_SV, f.getSv().getName());
        values.put(FishDbHelper.COLUMN_HAEMATOM, f.getHaematom());
        values.put(FishDbHelper.COLUMN_HAEMATOM_STELLE, f.getHaematomStelle());
        values.put(FishDbHelper.COLUMN_SCHUERFUNG, f.getSchuerfung());
        values.put(FishDbHelper.COLUMN_SCHUERFUNG_STELLE, f.getSchuerfungStelle());
        values.put(FishDbHelper.COLUMN_SCHUERFUNG_VERPILZT, f.getSchuerfungVerpilzt());
        values.put(FishDbHelper.COLUMN_OW, f.getOw());
        values.put(FishDbHelper.COLUMN_OW_STELLE, f.getOwStelle());
        values.put(FishDbHelper.COLUMN_OW_VERPILZT, f.getOwVerpilzt());
        values.put(FishDbHelper.COLUMN_TA, f.getTa());
        values.put(FishDbHelper.COLUMN_TA_STELLE, f.getTaStelle());
        values.put(FishDbHelper.COLUMN_TOTALDURCHTRENNUNG, f.getTotaldurchtrennung());
        values.put(FishDbHelper.COLUMN_VERPILZUNG, f.getVerpilzung());
        values.put(FishDbHelper.COLUMN_BEMERKUNG, f.getBemerkung());
        values.put(FishDbHelper.COLUMN_DATUM, f.getDatum());

        long insertID = database.insert(FishDbHelper.TABLE_FISH_LIST, null, values);
        f.setId(insertID);
        return f;
    }

    public void updateFish(long id, Fish f) {
        this.open();
        ContentValues values = new ContentValues();
        values.put(FishDbHelper.COLUMN_ART, f.getArt());
        values.put(FishDbHelper.COLUMN_LAENGE, f.getLaenge());
        values.put(FishDbHelper.COLUMN_BPA, f.getBpa().getName());
        values.put(FishDbHelper.COLUMN_SV, f.getSv().getName());
        values.put(FishDbHelper.COLUMN_HAEMATOM, f.getHaematom());
        values.put(FishDbHelper.COLUMN_HAEMATOM_STELLE, f.getHaematomStelle());
        values.put(FishDbHelper.COLUMN_SCHUERFUNG, f.getSchuerfung());
        values.put(FishDbHelper.COLUMN_SCHUERFUNG_STELLE, f.getSchuerfungStelle());
        values.put(FishDbHelper.COLUMN_SCHUERFUNG_VERPILZT, f.getSchuerfungVerpilzt());
        values.put(FishDbHelper.COLUMN_OW, f.getOw());
        values.put(FishDbHelper.COLUMN_OW_STELLE, f.getOwStelle());
        values.put(FishDbHelper.COLUMN_OW_VERPILZT, f.getOwVerpilzt());
        values.put(FishDbHelper.COLUMN_TA, f.getTa());
        values.put(FishDbHelper.COLUMN_TA_STELLE, f.getTaStelle());
        values.put(FishDbHelper.COLUMN_TOTALDURCHTRENNUNG, f.getTotaldurchtrennung());
        values.put(FishDbHelper.COLUMN_VERPILZUNG, f.getVerpilzung());
        values.put(FishDbHelper.COLUMN_BEMERKUNG, f.getBemerkung());

        this.database.update(FishDbHelper.TABLE_FISH_LIST, values, "_id=" + id, null);
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
        boolean haematom = (cursor.getInt(idHaematom) == 1);
        String heamatom_stelle = cursor.getString(idHaematomStelle);
        boolean schuerfung = (cursor.getInt(idSchuerfung) == 1);
        String schuerfung_stelle = cursor.getString(idSchuerfungStelle);
        boolean schuerfung_verpilzt = (cursor.getInt(idSchuerfungVerpilzt) == 1);
        boolean ow = (cursor.getInt(idOw) == 1);
        String ow_stelle = cursor.getString(idOwStelle);
        boolean ow_verpilzt = (cursor.getInt(idOwVerpilzt) == 1);
        boolean ta = (cursor.getInt(idTa) == 1);
        String ta_stelle = cursor.getString(idTaStelle);
        boolean totaldurchtrennung = (cursor.getInt(idTotaldurchtrennung) == 1);
        boolean verpilzung = (cursor.getInt(idVerpilzung) == 1);
        String bemerkung = cursor.getString(idBemerkung);
        long datum = cursor.getLong(idDatum);
        long id = cursor.getLong(idIndex);

        return new Fish(art,
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
    }

    public List<Fish> getAllFishes() {
        List<Fish> fishList = new ArrayList<>();

        Cursor cursor = database.query(FishDbHelper.TABLE_FISH_LIST,
                columns, null, null, null, null, null);

        cursor.moveToFirst();
        Fish fish;

        while (!cursor.isAfterLast()) {
            fish = cursorToFish(cursor);
            fishList.add(fish);
            cursor.moveToNext();
        }

        cursor.close();

        return fishList;
    }

    public void deleteFish(long id) {
        this.database.delete(FishDbHelper.TABLE_FISH_LIST,"_id=" + id, null);
    }
}