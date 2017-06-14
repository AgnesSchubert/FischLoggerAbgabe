package com.example.agnes.fischlogger;

/**
 * Created by Agnes on 17.05.2017.
 */

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class FishViewerActivity extends ActionBarActivity {

    /*public static final String LOG_TAG = FishViewerActivity.class.getSimpleName();
    private FishDataSource dataSource;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishviewer);
        /*java.util.Date datum = new java.util.Date();
        Fish testFisch = new Fish("Bachforelle", 12.5, Seite.NONE, Seite.ONE,false,"",true,"Bauch",false,false,"",false,true,"Schwanzflosse",false,false,"Dies ist ein Test.",datum.getTime(),1);
        Log.d(LOG_TAG, "Inhalt der Testmemo: " + testFisch.toString());

        dataSource = new FishDataSource(this);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Fish fish = dataSource.createFish("Bachforelle", 12.5, Seite.BOTH, Seite.ONE,false,"",true,"Bauch",false,false,"",false,true,"Schwanzflosse",false,false,"Dies ist ein Test.",datum.getTime());
        Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
        Log.d(LOG_TAG, "ID: " + fish.getId() + ", Inhalt: " + fish.toString());

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries();


        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();*/
    }

    /*private void showAllListEntries () {
        List<Fish> fishList = dataSource.getAllFishes();

        ArrayAdapter<Fish> fishArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                fishList);

        ListView fishListView = (ListView) findViewById(R.id.listview_fische);
        fishListView.setAdapter(fishArrayAdapter);
    }*/
}
