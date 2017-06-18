package com.example.agnes.fischlogger;

/**
 * Created by Agnes on 10.05.2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;

public class WelcomeFragment extends Fragment{

    public WelcomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_welcome, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.start_action_FishViewer:
                Intent fishViewerIntent = new Intent(getActivity(), FishViewerActivity.class);
                String test = "Test";
                fishViewerIntent.putExtra(Intent.EXTRA_TEXT, test);
                startActivity(fishViewerIntent);
                break;
            case R.id.start_action_newFish:
                Intent editFishIntent = new Intent(getActivity(), EditFishActivity.class);
                startActivity(editFishIntent);
                break;
            /*
            case R.id.start_action_newSite:
                Intent newSiteIntent = new Intent(getActivity(), NewSiteActivity.class);
                startActivity(newSiteIntent);
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_welcome, container, false);

    }

}
