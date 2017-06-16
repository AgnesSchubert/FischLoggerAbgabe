package com.example.agnes.fischlogger;

/**
 * Created by Agnes on 10.05.2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import android.util.Log;

import android.text.TextUtils;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.content.DialogInterface;
import android.content.Intent;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.Date;

public class EditFishFragment extends Fragment {

    public static final String LOG_TAG = EditFishFragment.class.getSimpleName();
    private FishDataSource dataSource;
    int l = 12;
    private String [] choices = new String[l];

    private boolean[] haematom_checked = new boolean[l];
    private boolean[] schuerfung_checked = new boolean[l];
    private boolean[] ow_checked = new boolean[l];
    private boolean[] ta_checked = new boolean[l];

    //private String [] choices = null;
    //private boolean [] checkedPositions = null;

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
        final View rootView = inflater.inflate(R.layout.fragment_editfish, container, false);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new FishDataSource(getContext());

        choices[0] = getString(R.string.koerper_flosse_bauch);
        choices[1] = getString(R.string.koerper_flosse_ruecken);
        choices[2] = getString(R.string.koerper_flosse_after);
        choices[3] = getString(R.string.koerper_flosse_schwanz);
        choices[4] = getString(R.string.koerper_flosse_fett);
        choices[5] = getString(R.string.koerper_teil_kopf);
        choices[6] = getString(R.string.koerper_teil_maul);
        choices[7] = getString(R.string.koerper_teil_ruecken);
        choices[8] = getString(R.string.koerper_teil_bauch);
        choices[9] = getString(R.string.koerper_teil_seitelinks);
        choices[10] = getString(R.string.koerper_teil_seiterechts);
        choices[11] = getString(R.string.koerper_teil_seitebeide);

        Button btn_editOK = (Button) rootView.findViewById(R.id.btn_editOK);
        Button btn_editNext = (Button) rootView.findViewById(R.id.btn_editNext);
        Button btn_editAbort = (Button) rootView.findViewById(R.id.btn_editAbort);
        final Button btn_haematom_wo = (Button) rootView.findViewById(R.id.btn_heamatom_wo);
        final Button btn_schuerfung_wo = (Button) rootView.findViewById(R.id.btn_schuerfung_wo);
        final Button btn_ow_wo = (Button) rootView.findViewById(R.id.btn_ow_wo);
        final Button btn_ta_wo = (Button) rootView.findViewById(R.id.btn_ta_wo);
        Intent empfangenerIntent = getActivity().getIntent();
        if (empfangenerIntent != null && empfangenerIntent.hasExtra(Intent.EXTRA_TEXT)) {
            //String fischInfo = empfangenerIntent.getStringExtra(Intent.EXTRA_TEXT);
        }

        //OnClick-Listener für die Körperstellen-Buttons erstellen -> Alert-Dialoge anzeigen + auswerten


        btn_haematom_wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AlertDialog erstellen
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final boolean[] oldCheckedPositions = haematom_checked.clone();

                // initiale Markierungen: aus boolean-Feld übernehmen
                builder.setMultiChoiceItems(choices, haematom_checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        haematom_checked[which] = isChecked;    // Markierte Elemente updaten
                    }
                });

                // nicht cancelable
                builder.setCancelable(false);
                builder.setTitle(R.string.schaden_koerperstelle_betroffen);

                // ClickListener für OK-Button
                builder.setPositiveButton(R.string.antwort_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });

                //ClickListener für Abbrechen-Button
                builder.setNeutralButton(R.string.antwort_abbrechen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // boolean-Feld zurücksetzen
                        haematom_checked = oldCheckedPositions.clone();
                    }
                });

                //AlertDialog anzeigen
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btn_ow_wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final boolean[] oldCheckedPositions = ow_checked.clone();
                builder.setMultiChoiceItems(choices, ow_checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        ow_checked[which] = isChecked;
                    }
                });
                builder.setCancelable(false);
                builder.setTitle(R.string.schaden_koerperstelle_betroffen);
                builder.setPositiveButton(R.string.antwort_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                builder.setNeutralButton(R.string.antwort_abbrechen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ow_checked = oldCheckedPositions.clone();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btn_schuerfung_wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final boolean[] oldCheckedPositions = schuerfung_checked.clone();
                builder.setMultiChoiceItems(choices, schuerfung_checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        schuerfung_checked[which] = isChecked;
                    }
                });
                builder.setCancelable(false);
                builder.setTitle(R.string.schaden_koerperstelle_betroffen);
                builder.setPositiveButton(R.string.antwort_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                builder.setNeutralButton(R.string.antwort_abbrechen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        schuerfung_checked = oldCheckedPositions.clone();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btn_ta_wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final boolean[] oldCheckedPositions = ta_checked.clone();
                builder.setMultiChoiceItems(choices, ta_checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        ta_checked[which] = isChecked;
                    }
                });
                builder.setCancelable(false);
                builder.setTitle(R.string.schaden_koerperstelle_betroffen);
                builder.setPositiveButton(R.string.antwort_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                builder.setNeutralButton(R.string.antwort_abbrechen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ta_checked = oldCheckedPositions.clone();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btn_editOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean complete = testComplete(rootView);
                if(!complete){return;}
                getData(rootView,dataSource);

                //wechseln zum FishViewer
                Intent fishViewerIntent = new Intent(getActivity(), FishViewerActivity.class);
                String test = "Test";
                fishViewerIntent.putExtra(Intent.EXTRA_TEXT, test);
                startActivity(fishViewerIntent);
            }
        });

        btn_editNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean complete = testComplete(rootView);
                if(!complete){return;}
                getData(rootView,dataSource);

                //Eingabemaske leeren --> evtl. extra Methode dafür? Beim Abbrechen brauchen wir es aber doch nicht...
                final AutoCompleteTextView autoComplete_Fisch_art = (AutoCompleteTextView) rootView.findViewById(R.id.autoComplete_fisch_art);
                final EditText edit_fisch_laenge = (EditText) rootView.findViewById(R.id.edit_fisch_laenge);
                final RadioGroup rbtnGroup_bpa = (RadioGroup) rootView.findViewById(R.id.rbtnGroup_bpa);
                final RadioGroup rbtnGroup_sv = (RadioGroup) rootView.findViewById(R.id.rbtnGroup_sv);
                final CheckBox cb_haematom = (CheckBox) rootView.findViewById(R.id.cb_haematom);
                final CheckBox cb_schuerfung = (CheckBox) rootView.findViewById(R.id.cb_schuerfung);
                final CheckBox cb_schuerfung_verpilzt = (CheckBox) rootView.findViewById(R.id.cb_schuerfung_verpilzt);
                final CheckBox cb_ow = (CheckBox) rootView.findViewById(R.id.cb_ow);
                final CheckBox cb_ow_verpilzt = (CheckBox) rootView.findViewById(R.id.cb_ow_verpilzt);
                final CheckBox cb_ta = (CheckBox) rootView.findViewById(R.id.cb_ta);
                final CheckBox cb_td = (CheckBox) rootView.findViewById(R.id.cb_td);
                final CheckBox cb_verpilzung = (CheckBox) rootView.findViewById(R.id.cb_verpilzung);
                final EditText edit_bemerkung = (EditText) rootView.findViewById(R.id.edit_bemerkung);

                Arrays.fill(haematom_checked, false);
                Arrays.fill(schuerfung_checked, false);
                Arrays.fill(ow_checked, false);
                Arrays.fill(ta_checked, false);

                autoComplete_Fisch_art.setText(null);
                edit_fisch_laenge.setText(null);
                rbtnGroup_bpa.clearCheck();
                rbtnGroup_sv.clearCheck();
                cb_haematom.setChecked(false);
                cb_schuerfung.setChecked(false);
                cb_schuerfung_verpilzt.setChecked(false);
                cb_ow.setChecked(false);
                cb_ow_verpilzt.setChecked(false);
                cb_ta.setChecked(false);
                cb_td.setChecked(false);
                cb_verpilzung.setChecked(false);
                edit_bemerkung.setText(null);
            }
        });

        return rootView;
    }

    boolean testComplete(View rootView){

        //testen, ob alle nötigen Eingaben vorhanden sind
        final AutoCompleteTextView autoComplete_Fisch_art = (AutoCompleteTextView) rootView.findViewById(R.id.autoComplete_fisch_art);
        final EditText edit_fisch_laenge = (EditText) rootView.findViewById(R.id.edit_fisch_laenge);

        boolean ret = true;
        if(TextUtils.isEmpty(autoComplete_Fisch_art.getText())) {
            autoComplete_Fisch_art.setError(getString(R.string.error_art));
            ret = false;
        }
        if(TextUtils.isEmpty(edit_fisch_laenge.getText())) {
            edit_fisch_laenge.setError(getString(R.string.error_laenge));
            ret = false;
        }
        return ret;
    }

    void getData(View rootView, FishDataSource dataSource){

        final AutoCompleteTextView autoComplete_Fisch_art = (AutoCompleteTextView) rootView.findViewById(R.id.autoComplete_fisch_art);
        final EditText edit_fisch_laenge = (EditText) rootView.findViewById(R.id.edit_fisch_laenge);
        final RadioButton rbtn_bpa_einstg = (RadioButton) rootView.findViewById(R.id.rbtn_bpa_einstg);
        final RadioButton rbtn_bpa_beidstg = (RadioButton) rootView.findViewById(R.id.rbtn_bpa_beidstg);
        final RadioButton rbtn_sv_einstg = (RadioButton) rootView.findViewById(R.id.rbtn_sv_einstg);
        final RadioButton rbtn_sv_beidstg = (RadioButton) rootView.findViewById(R.id.rbtn_sv_beidstg);
        final CheckBox cb_haematom = (CheckBox) rootView.findViewById(R.id.cb_haematom);
        final CheckBox cb_schuerfung = (CheckBox) rootView.findViewById(R.id.cb_schuerfung);
        final CheckBox cb_schuerfung_verpilzt = (CheckBox) rootView.findViewById(R.id.cb_schuerfung_verpilzt);
        final CheckBox cb_ow = (CheckBox) rootView.findViewById(R.id.cb_ow);
        final CheckBox cb_ow_verpilzt = (CheckBox) rootView.findViewById(R.id.cb_ow_verpilzt);
        final CheckBox cb_ta = (CheckBox) rootView.findViewById(R.id.cb_ta);
        final CheckBox cb_td = (CheckBox) rootView.findViewById(R.id.cb_td);
        final CheckBox cb_verpilzung = (CheckBox) rootView.findViewById(R.id.cb_verpilzung);
        final EditText edit_bemerkung = (EditText) rootView.findViewById(R.id.edit_bemerkung);

        String haematom_wo = getStellen(haematom_checked, choices);
        String schuerfung_wo = getStellen(schuerfung_checked, choices);
        String ow_wo = getStellen(ow_checked, choices);
        String ta_wo = getStellen(ta_checked, choices);

        //Eingaben auslesen und abspeichern
        String art = autoComplete_Fisch_art.getText().toString();
        //double laenge = Double.valueOf(edit_fisch_laenge.getText().toString()).doubleValue();
        double laenge = Double.parseDouble(edit_fisch_laenge.getText().toString());
        Seite bpa = Seite.NONE;
        if(rbtn_bpa_einstg.isChecked()){bpa = Seite.ONE;}
        if(rbtn_bpa_beidstg.isChecked()){bpa = Seite.BOTH;}
        Seite sv = Seite.NONE;
        if(rbtn_sv_einstg.isChecked()){bpa = Seite.ONE;}
        if(rbtn_sv_beidstg.isChecked()){bpa = Seite.BOTH;}
        boolean haematom = cb_haematom.isChecked();
        boolean schuerfung = cb_schuerfung.isChecked();
        boolean schuerfung_verpilzt = cb_schuerfung_verpilzt.isChecked();
        boolean ow = cb_ow.isChecked();
        boolean ow_verpilzt = cb_ow_verpilzt.isChecked();
        boolean ta = cb_ta.isChecked();
        boolean td = cb_td.isChecked();
        boolean verpilzung = cb_verpilzung.isChecked();
        String bemerkung = edit_bemerkung.getText().toString();

        double datum = (new Date()).getTime();

        dataSource.createFish(art,laenge,bpa,sv,haematom,haematom_wo,schuerfung,schuerfung_wo,schuerfung_verpilzt,ow,ow_wo,ow_verpilzt,ta,ta_wo,td,verpilzung,bemerkung,datum);
    }


    String getStellen(boolean[] checkedPositions, String [] choices){
        String stellen = "";
        boolean first = true;
        for (int i=0; i<l; i++) {
            if (checkedPositions[i]) {
                if (first) {stellen = choices[i]; first = false;}
                else {stellen += ", " + choices[i];}
            }
        }
        return stellen;
    }

    boolean [] getCheckedPositions(String stellen, String [] choices){
    boolean [] checkedPositions = new boolean[l];
    for (int i=0; i<l; i++) {
        // teste auf Wort an Stelle > 0, Stelle 0 oder falls nur ein Wort enthalten ist
        if (stellen.contains(choices[i]+",") || stellen.contains(" "+choices[i]) || stellen.equals(choices[i])) {
            checkedPositions[i] = true;
        } else {
            checkedPositions[i] = false;
        }
    }
    return checkedPositions;
    }

    @Override
    public void onPause() {
        super.onPause();

        try{dataSource.close();}
        catch (java.lang.Exception e){}
    }
}
