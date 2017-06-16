package com.example.agnes.fischlogger;

/**
 * Created by Agnes on 10.05.2017.
 */

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.text.TextUtils;

import android.view.ContextMenu;
import android.view.inputmethod.InputMethodManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class FishViewerFragment extends Fragment {

    public static final String LOG_TAG = FishViewerFragment.class.getSimpleName();
    private FishDataSource dataSource;

    public FishViewerFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fishviewer, menu);
    }

    public void onResume() {
        super.onResume();
        final ListView fishViewerListView = (ListView) getActivity().findViewById(R.id.listview_fische);

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries(fishViewerListView);
    }

    public void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.fishViewer_action_einstellungen) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_fishviewer, container, false);
        final ListView fishViewerListView = (ListView) rootView.findViewById(R.id.listview_fische);
        registerForContextMenu(fishViewerListView);

        //java.util.Date datum = new java.util.Date();
        //Fish testFisch = new Fish("Bachforelle", 12.5, Seite.NONE, Seite.ONE,false,"",true,"Bauch",false,false,"",false,true,"Schwanzflosse",false,false,"Dies ist ein Test.",datum.getTime(),1);
        //Log.d(LOG_TAG, "Inhalt der Testmemo: " + testFisch.toString());
        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new FishDataSource(getContext());

        /*Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Fish fish = dataSource.createFish("Bachforelle", 12.5, Seite.BOTH, Seite.ONE,false,"",true,"Bauch",false,false,"",false,true,"Schwanzflosse",false,false,"Dies ist ein Test.",datum.getTime());
        Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
        Log.d(LOG_TAG, "ID: " + fish.getId() + ", Inhalt: " + fish.toString());

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries(fishViewerListView);

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();*/

        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.listview_fische) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_deletefish, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.fishviewer_context_delete) {
            Toast.makeText(getContext(), "TEST: löschen", Toast.LENGTH_LONG).show();
        }else if (id == R.id.fishviewer_context_edit) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            long selID = menuInfo.id;
            int selPos = menuInfo.position;
            //String fischInfo = (String) adapterView.getItemAtPosition(position);
            String fischInfo = "TEST: neuer Intent";
            Intent editFishIntent = new Intent(getActivity(), EditFishActivity.class);
            editFishIntent.putExtra(Intent.EXTRA_TEXT, fischInfo);
            startActivity(editFishIntent);
        }else if (id == R.id.fishviewer_context_new){
            Toast.makeText(getContext(),"TEST: neu",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;

    }

    private void showAllListEntries (ListView Liste) {
        List<Fish> fishList = dataSource.getAllFishes();

        ArrayAdapter<Fish> fishListAdapter = new ArrayAdapter<> (
                getActivity(),
                android.R.layout.simple_list_item_multiple_choice,
                fishList);

        Liste.setAdapter(fishListAdapter);
    }
}
