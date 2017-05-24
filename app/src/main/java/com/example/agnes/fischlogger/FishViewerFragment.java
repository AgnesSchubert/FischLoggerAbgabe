package com.example.agnes.fischlogger;

/**
 * Created by Agnes on 10.05.2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FishViewerFragment extends Fragment {

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
        String [] fischlisteArray = {
                "Bachforelle 23",
                "Flussbarsch 17",
                "Bachforelle 9",
                "Groppe 11",
                "Gründling 13",
                "Bachforelle 45",
                "Plötze 12",
                "Blaubandbärbling 7",
                "Blaubandbärbling 6"
        };
        List <String> fischListe = new ArrayList<>(Arrays.asList(fischlisteArray));
        ArrayAdapter <String> fischlisteAdapter =
                new ArrayAdapter<>(
                        getActivity(), // Die aktuelle Umgebung (FishViewerActivity)
                        R.layout.list_item_fisch, // XML-Layout Datei
                        R.id.list_item_fisch_textview, // TextViews
                        fischListe); // ArrayList (Beispiel-Fisch-Daten)
        View rootView = inflater.inflate(R.layout.fragment_fishviewer, container, false);

        final ListView fishViewerListView = (ListView) rootView.findViewById(R.id.listview_fische);
        registerForContextMenu(fishViewerListView);
        fishViewerListView.setAdapter(fischlisteAdapter);

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
}
