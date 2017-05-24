package com.example.agnes.fischlogger;

/**
 * Created by Agnes on 10.05.2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import android.content.DialogInterface;
import android.content.Intent;

public class EditFishFragment extends Fragment {

    public EditFishFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_editfish, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.editFish_action_einstellungen) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_editfish, container, false);

        Intent empfangenerIntent = getActivity().getIntent();
        if (empfangenerIntent != null && empfangenerIntent.hasExtra(Intent.EXTRA_TEXT)) {
            //String fischInfo = empfangenerIntent.getStringExtra(Intent.EXTRA_TEXT);
        }

        final String[] stelle = new String[]{
                getString(R.string.koerper_flosse_bauch),
                getString(R.string.koerper_flosse_ruecken),
                getString(R.string.koerper_flosse_after),
                getString(R.string.koerper_flosse_schwanz),
                getString(R.string.koerper_flosse_fett),
                getString(R.string.koerper_teil_kopf),
                getString(R.string.koerper_teil_maul),
                getString(R.string.koerper_teil_ruecken),
                getString(R.string.koerper_teil_bauch),
                getString(R.string.koerper_teil_seitelinks),
                getString(R.string.koerper_teil_seiterechts),
                getString(R.string.koerper_teil_seitebeide)
        };

        //OnClick-Listener für die Körperstellen-Buttons erstellen -> Alert-Dialoge anzeigen + auswerten

        Button btn_haematom_wo = (Button) rootView.findViewById(R.id.btn_heamatom_wo);
        btn_haematom_wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AlertDialog erstellen
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                // initiale Markierungen: aus Datenbank übernehmen
                final boolean[] checkedPositions = new boolean[]{
                        false,false,false,false,false,false,false,false,false,false,false,false,false
                };

                builder.setMultiChoiceItems(stelle, checkedPositions, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedPositions[which] = isChecked;    // Markierte Elemente updaten
                    }
                });

                // nicht cancelable
                builder.setCancelable(false);
                builder.setTitle(R.string.schaden_koerperstelle_betroffen);

                // ClickListener für OK-Button
                builder.setPositiveButton(R.string.antwort_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //irgendwas
                    }
                });

                //ClickListener für Abbrechen-Button
                builder.setNeutralButton(R.string.antwort_abbrechen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // irgendwas
                    }
                });

                //AlertDialog anzeigen
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button btn_ow_wo = (Button) rootView.findViewById(R.id.btn_ow_wo);
        btn_ow_wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final boolean[] checkedPositions = new boolean[]{
                        false,false,false,false,false,false,false,false,false,false,false,false,false
                };
                builder.setMultiChoiceItems(stelle, checkedPositions, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedPositions[which] = isChecked;
                    }
                });
                builder.setCancelable(false);
                builder.setTitle(R.string.schaden_koerperstelle_betroffen);
                builder.setPositiveButton(R.string.antwort_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //irgendwas
                    }
                });
                builder.setNeutralButton(R.string.antwort_abbrechen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // irgendwas
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button btn_schuerfung_wo = (Button) rootView.findViewById(R.id.btn_schuerfung_wo);
        btn_schuerfung_wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final boolean[] checkedPositions = new boolean[]{
                        false,false,false,false,false,false,false,false,false,false,false,false,false
                };
                builder.setMultiChoiceItems(stelle, checkedPositions, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedPositions[which] = isChecked;
                    }
                });
                builder.setCancelable(false);
                builder.setTitle(R.string.schaden_koerperstelle_betroffen);
                builder.setPositiveButton(R.string.antwort_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //irgendwas
                    }
                });
                builder.setNeutralButton(R.string.antwort_abbrechen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // irgendwas
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button btn_ta_wo = (Button) rootView.findViewById(R.id.btn_ta_wo);
        btn_ta_wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final boolean[] checkedPositions = new boolean[]{
                        false,false,false,false,false,false,false,false,false,false,false,false,false
                };
                builder.setMultiChoiceItems(stelle, checkedPositions, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkedPositions[which] = isChecked;
                    }
                });
                builder.setCancelable(false);
                builder.setTitle(R.string.schaden_koerperstelle_betroffen);
                builder.setPositiveButton(R.string.antwort_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //irgendwas
                    }
                });
                builder.setNeutralButton(R.string.antwort_abbrechen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // irgendwas
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return rootView;
    }
}
