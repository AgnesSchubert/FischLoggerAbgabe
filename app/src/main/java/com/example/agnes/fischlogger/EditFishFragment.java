package com.example.agnes.fischlogger;

/**
 * Created by Agnes on 10.05.2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import android.text.TextUtils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.DialogInterface;
import android.content.Intent;

import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.Date;

public class EditFishFragment extends Fragment {

    private boolean newFish = true;

    private FishLogger app;
    private FishDataSource dataSource;

    private String [] choices;

    private boolean[] haematom_checked;
    private boolean[] schuerfung_checked;
    private boolean[] ow_checked;
    private boolean[] ta_checked;

    public EditFishFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_editfish, container, false);

        final Intent receivedIntent = getActivity().getIntent();

        // Fischarten für das Autocomplete-Textfeld
        String[] arten = getResources().getStringArray(R.array.fischarten);

        // Körperstellen für die Auswahl-AlertDialoge
        choices = getResources().getStringArray(R.array.koerperstellen);

        // Körperstellen-Arrays initialisieren
        haematom_checked = new boolean[choices.length];
        schuerfung_checked = new boolean[choices.length];
        ow_checked = new boolean[choices.length];
        ta_checked = new boolean[choices.length];

        // Formularelemente
        Button btn_editOK = (Button) rootView.findViewById(R.id.btn_editOK);
        Button btn_editNext = (Button) rootView.findViewById(R.id.btn_editNext);
        Button btn_editAbort = (Button) rootView.findViewById(R.id.btn_editAbort);
        final Button btn_haematom_wo = (Button) rootView.findViewById(R.id.btn_haematom_wo);
        final Button btn_schuerfung_wo = (Button) rootView.findViewById(R.id.btn_schuerfung_wo);
        final Button btn_ow_wo = (Button) rootView.findViewById(R.id.btn_ow_wo);
        final Button btn_ta_wo = (Button) rootView.findViewById(R.id.btn_ta_wo);
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

        // Autocomplete-Vorschläge für die Fischart
        ArrayAdapter<String> artenAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                arten);
        final AutoCompleteTextView autoComplete_Fisch_art = (AutoCompleteTextView) rootView.findViewById(R.id.autoComplete_fisch_art);
        autoComplete_Fisch_art.setAdapter(artenAdapter);

        // Bedienelemente ein-/ausschalten
        btn_haematom_wo.setEnabled(false);
        btn_schuerfung_wo.setEnabled(false);
        btn_ow_wo.setEnabled(false);
        btn_ta_wo.setEnabled(false);
        cb_ow_verpilzt.setEnabled(false);
        cb_schuerfung_verpilzt.setEnabled(false);

        // ggf. Daten übernehmen
        if (receivedIntent != null && receivedIntent.getExtras() != null) {
            newFish = false;
            Bundle fishData = receivedIntent.getExtras();

            //Eingabemaske anpassen
            autoComplete_Fisch_art.setText(fishData.getString("art"));
            edit_fisch_laenge.setText((String.valueOf(fishData.getDouble("laenge"))));
            rbtn_bpa_einstg.setChecked(fishData.getBoolean("bpa_eins"));
            rbtn_bpa_beidstg.setChecked(fishData.getBoolean("bpa_beids"));
            rbtn_sv_einstg.setChecked(fishData.getBoolean("sv_eins"));
            rbtn_sv_beidstg.setChecked(fishData.getBoolean("sv_beids"));
            cb_haematom.setChecked(fishData.getBoolean("haematom"));
            if (fishData.getBoolean("haematom")){
                btn_haematom_wo.setEnabled(true);
                haematom_checked = getCheckedPositions(fishData.getString("haematom_stelle"),choices);
            }
            cb_schuerfung.setChecked(fishData.getBoolean("schuerfung"));
            if (fishData.getBoolean("schuerfung")){
                btn_schuerfung_wo.setEnabled(true);
                cb_schuerfung_verpilzt.setEnabled(true);
                schuerfung_checked = getCheckedPositions(fishData.getString("schuerfung_stelle"),choices);
                cb_schuerfung_verpilzt.setChecked(fishData.getBoolean("schuerfung_verpilzt"));
            }
            cb_ow.setChecked(fishData.getBoolean("ow"));
            if (fishData.getBoolean("ow")){
                btn_ow_wo.setEnabled(true);
                cb_schuerfung_verpilzt.setEnabled(true);
                ow_checked = getCheckedPositions(fishData.getString("ow_stelle"),choices);
                cb_ow_verpilzt.setChecked(fishData.getBoolean("ow_verpilzt"));
            }
            cb_ta.setChecked(fishData.getBoolean("ta"));
            if (fishData.getBoolean("ta")){
                btn_ta_wo.setEnabled(true);
                ta_checked = getCheckedPositions(fishData.getString("ta_stelle"),choices);
            }
            cb_td.setChecked(fishData.getBoolean("td"));
            cb_verpilzung.setChecked(fishData.getBoolean("verpilzung"));
            edit_bemerkung.setText(fishData.getString("bemerkung"));
        }

        // OnCheckedChange-Listener für die Boolean-CheckBoxes für Hämatome, Schürfungen, offene Wunden, Teilamputationen
        // aktiviert bzw. deaktiviert die zugehörigen Formularelemente
        cb_haematom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton b, boolean checked) {
                Arrays.fill(haematom_checked, false);
                btn_haematom_wo.setEnabled(checked);
            }
        });
        cb_schuerfung.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton b, boolean checked) {
                Arrays.fill(schuerfung_checked, false);
                cb_schuerfung_verpilzt.setChecked(false);
                btn_schuerfung_wo.setEnabled(checked);
                cb_schuerfung_verpilzt.setEnabled(checked);
            }
        });
        cb_ow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton b, boolean checked) {
                Arrays.fill(ow_checked, false);
                cb_ow_verpilzt.setChecked(false);
                btn_ow_wo.setEnabled(checked);
                cb_ow_verpilzt.setEnabled(checked);
            }
        });
        cb_ta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton b, boolean checked) {
                Arrays.fill(ta_checked, false);
                btn_ta_wo.setEnabled(checked);
            }
        });

        // OnClick-Listener für die Körperstellen-Buttons erstellen -> Alert-Dialoge anzeigen + auswerten
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

        btn_editAbort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wechseln zum FishViewer
                Intent fishViewerIntent = new Intent(getActivity(), FishViewerActivity.class);
                startActivity(fishViewerIntent);
            }
        });

        btn_editOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean complete = testComplete(rootView);
                if(!complete){return;}
                if (!newFish){
                    Bundle b = receivedIntent.getExtras(); //Nulltest weiter oben
                    updateData(rootView,dataSource,b.getLong("id"));
                }
                else {writeData(rootView,dataSource);}

                //wechseln zum FishViewer
                Intent fishViewerIntent = new Intent(getActivity(), FishViewerActivity.class);
                startActivity(fishViewerIntent);
            }
        });

        btn_editNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean complete = testComplete(rootView);
                if(!complete){return;}
                if (!newFish){
                    Bundle b = receivedIntent.getExtras(); //Nulltest weiter oben
                    updateData(rootView,dataSource,b.getLong("id"));
                }
                else {writeData(rootView,dataSource);}

                newFish = true;

                //Eingabemaske leeren
                Arrays.fill(haematom_checked, false);
                Arrays.fill(schuerfung_checked, false);
                Arrays.fill(ow_checked, false);
                Arrays.fill(ta_checked, false);

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

                autoComplete_Fisch_art.setText(null);
                autoComplete_Fisch_art.requestFocus();
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

    Fish getFish(View rootView){
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
        if(rbtn_sv_einstg.isChecked()){sv = Seite.ONE;}
        if(rbtn_sv_beidstg.isChecked()){sv = Seite.BOTH;}
        boolean haematom = cb_haematom.isChecked();
        boolean schuerfung = cb_schuerfung.isChecked();
        boolean schuerfung_verpilzt = cb_schuerfung_verpilzt.isChecked();
        boolean ow = cb_ow.isChecked();
        boolean ow_verpilzt = cb_ow_verpilzt.isChecked();
        boolean ta = cb_ta.isChecked();
        boolean td = cb_td.isChecked();
        boolean verpilzung = cb_verpilzung.isChecked();
        String bemerkung = edit_bemerkung.getText().toString();

        long datum = (new Date()).getTime();

        return new Fish(art,laenge,bpa,sv,haematom,haematom_wo,schuerfung,schuerfung_wo,schuerfung_verpilzt,ow,ow_wo,ow_verpilzt,ta,ta_wo,td,verpilzung,bemerkung,datum,null);
    }

    void writeData(View rootView, FishDataSource dataSource){
        Fish f = getFish(rootView);
        dataSource.createFish(f);
    }

    void updateData(View rootView, FishDataSource dataSource, long id){
        Fish f = getFish(rootView);
        dataSource.updateFish(id, f);
    }


    String getStellen(boolean[] checkedPositions, String [] choices){
        String stellen = "";
        boolean first = true;
        for (int i=0; i<choices.length; i++) {
            if (checkedPositions[i]) {
                if (first) {stellen = choices[i]; first = false;}
                else {stellen += ", " + choices[i];}
            }
        }
        return stellen;
    }

    boolean [] getCheckedPositions(String stellen, String [] choices){
    boolean [] checkedPositions = new boolean[choices.length];
    for (int i=0; i<choices.length; i++) {
        // teste auf Wort an Stelle > 0, Stelle 0 oder falls nur ein Wort enthalten ist
        checkedPositions[i] = stellen.contains(choices[i] + ",")
                                || stellen.contains(" " + choices[i])
                                || stellen.equals(choices[i]);
    }
    return checkedPositions;
    }
}
