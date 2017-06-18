package com.example.agnes.fischlogger;

/**
 * Created by Agnes on 10.05.2017.
 */

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class FishViewerFragment extends Fragment {

    public static final String LOG_TAG = FishViewerFragment.class.getSimpleName();
    private FishDataSource dataSource;
    private ArrayAdapter<Fish> fishListAdapter;

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

        if (id == R.id.fishViewer_action_newFish) {
            Intent editFishIntent = new Intent(getActivity(), EditFishActivity.class);
            startActivity(editFishIntent);
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

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new FishDataSource(getContext());

        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.listview_fische) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_fishviewer_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.fishviewer_context_delete) {
            final ListView fishViewerListView = (ListView) getActivity().findViewById(R.id.listview_fische);
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            //long selID = menuInfo.id;
            int selPos = menuInfo.position;
            Fish f = fishListAdapter.getItem(selPos);
            dataSource.deleteFish(f.getId());
            showAllListEntries(fishViewerListView);
        }else if (id == R.id.fishviewer_context_edit) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            //long selID = menuInfo.id;
            int selPos = menuInfo.position;
            Intent editFishIntent = new Intent(getActivity(), EditFishActivity.class);
            editFishIntent.putExtras(getBundle(selPos));
            startActivity(editFishIntent);
        }else{
            return false;
        }
        return true;
    }

    private void showAllListEntries (ListView Liste) {
        List<Fish> fishList = dataSource.getAllFishes();

        fishListAdapter = new ArrayAdapter<> (
                getActivity(),
                android.R.layout.simple_list_item_1,
                fishList);

        Liste.setAdapter(fishListAdapter);
    }

    Bundle getBundle(int selPos){
        Bundle b = new Bundle();

        Fish f = fishListAdapter.getItem(selPos);
        b.putString("art",f.getArt());
        b.putDouble("laenge",f.getLaenge());
        b.putBoolean("bpa_eins",f.getBpa()==Seite.ONE);
        b.putBoolean("bpa_beids",f.getBpa()==Seite.BOTH);
        b.putBoolean("sv_eins",f.getSv()==Seite.ONE);
        b.putBoolean("sv_beids",f.getSv()==Seite.BOTH);
        b.putBoolean("haematom",f.getHaematom());
        b.putString("haematom_stelle",f.getHaematomStelle());
        b.putBoolean("schuerfung",f.getSchuerfung());
        b.putString("schuerfung_stelle",f.getSchuerfungStelle());
        b.putBoolean("schuerfung_verpilzt",f.getSchuerfungVerpilzt());
        b.putBoolean("ow",f.getOw());
        b.putString("ow_stelle",f.getOwStelle());
        b.putBoolean("ow_verpilzt",f.getOwVerpilzt());
        b.putBoolean("ta",f.getTa());
        b.putString("ta_stelle",f.getTaStelle());
        b.putBoolean("td",f.getTotaldurchtrennung());
        b.putBoolean("verpilzung",f.getVerpilzung());
        b.putString("bemerkung",f.getBemerkung());
        b.putLong("id",f.getId());

        return b;
    }
}
