package com.example.agnes.fischlogger;

/**
 * Created by Agnes on 10.05.2017.
 */

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.Date;

public class EditFishFragment extends Fragment {

    private boolean newFish = true;

    public static final String LOG_TAG = EditFishFragment.class.getSimpleName();
    private FishDataSource dataSource;

    private String [] arten = new String[74];

    int l = 12;
    private String [] choices = new String[l];

    private boolean[] haematom_checked = new boolean[l];
    private boolean[] schuerfung_checked = new boolean[l];
    private boolean[] ow_checked = new boolean[l];
    private boolean[] ta_checked = new boolean[l];

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

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new FishDataSource(getContext());

        arten[0] = getString(R.string.art_europa_Meeresneunauge);
        arten[1] = getString(R.string.art_europa_Flussneunauge);
        arten[2] = getString(R.string.art_europa_Bachneunauge);
        arten[3] = getString(R.string.art_europa_Stoer);
        arten[4] = getString(R.string.art_europa_Sterlet);
        arten[5] = getString(R.string.art_europa_Hausen);
        arten[6] = getString(R.string.art_europa_Aal);
        arten[7] = getString(R.string.art_europa_Finte);
        arten[8] = getString(R.string.art_europa_Bachforelle);
        arten[9] = getString(R.string.art_europa_Meerforelle);
        arten[10] = getString(R.string.art_europa_LachsAtlantischer);
        arten[11] = getString(R.string.art_europa_Huchen);
        arten[12] = getString(R.string.art_europa_Regenbogenforelle);
        arten[13] = getString(R.string.art_europa_Ketalachs);
        arten[14] = getString(R.string.art_europa_Buckellachs);
        arten[15] = getString(R.string.art_europa_Bachsaibling);
        arten[16] = getString(R.string.art_europa_Seesaibling);
        arten[17] = getString(R.string.art_europa_Renke);
        arten[18] = getString(R.string.art_europa_Aesche);
        arten[19] = getString(R.string.art_europa_Stint);
        arten[20] = getString(R.string.art_europa_Hecht);
        arten[21] = getString(R.string.art_europa_Hundsfisch);
        arten[22] = getString(R.string.art_europa_Ploetze);
        arten[23] = getString(R.string.art_europa_Frauennerfling);
        arten[24] = getString(R.string.art_europa_Perlfisch);
        arten[25] = getString(R.string.art_europa_Moderlieschen);
        arten[26] = getString(R.string.art_europa_Hasel);
        arten[27] = getString(R.string.art_europa_Doebel);
        arten[28] = getString(R.string.art_europa_Stroemer);
        arten[29] = getString(R.string.art_europa_Orfe);
        arten[30] = getString(R.string.art_europa_Elritze);
        arten[31] = getString(R.string.art_europa_Rotfeder);
        arten[32] = getString(R.string.art_europa_Rapfen);
        arten[33] = getString(R.string.art_europa_Nase);
        arten[34] = getString(R.string.art_europa_Mairenke);
        arten[35] = getString(R.string.art_europa_Ukelei);
        arten[36] = getString(R.string.art_europa_Schneider);
        arten[37] = getString(R.string.art_europa_Guester);
        arten[38] = getString(R.string.art_europa_Blei);
        arten[39] = getString(R.string.art_europa_Zobel);
        arten[40] = getString(R.string.art_europa_Zope);
        arten[41] = getString(R.string.art_europa_Zaehrte);
        arten[42] = getString(R.string.art_europa_Ziege);
        arten[43] = getString(R.string.art_europa_Graskarpfen);
        arten[44] = getString(R.string.art_europa_Gruendling);
        arten[45] = getString(R.string.art_europa_Steingressling);
        arten[46] = getString(R.string.art_europa_Barbe);
        arten[47] = getString(R.string.art_europa_Schleie);
        arten[48] = getString(R.string.art_europa_Karausche);
        arten[49] = getString(R.string.art_europa_Giebel);
        arten[50] = getString(R.string.art_europa_Karpfen);
        arten[51] = getString(R.string.art_europa_Bitterling);
        arten[52] = getString(R.string.art_europa_Schmerle);
        arten[53] = getString(R.string.art_europa_Schlammpeitzger);
        arten[54] = getString(R.string.art_europa_Steinbeisser);
        arten[55] = getString(R.string.art_europa_Wels);
        arten[56] = getString(R.string.art_europa_ZwergwelsBrauner);
        arten[57] = getString(R.string.art_europa_Quappe);
        arten[58] = getString(R.string.art_europa_MittelmeerKaerpfling);
        arten[59] = getString(R.string.art_europa_KoboldKaerpfling);
        arten[60] = getString(R.string.art_europa_AehrenfischKleiner);
        arten[61] = getString(R.string.art_europa_StichlingDreistachliger);
        arten[62] = getString(R.string.art_europa_StichlingNeunstachliger);
        arten[63] = getString(R.string.art_europa_Flussbarsch);
        arten[64] = getString(R.string.art_europa_Kaulbarsch);
        arten[65] = getString(R.string.art_europa_Schraetzer);
        arten[66] = getString(R.string.art_europa_Zander);
        arten[67] = getString(R.string.art_europa_Zingel);
        arten[68] = getString(R.string.art_europa_Forellenbarsch);
        arten[69] = getString(R.string.art_europa_Sonnenbarsch);
        arten[70] = getString(R.string.art_europa_SuesswasserSchleimfisch);
        arten[71] = getString(R.string.art_europa_Marmorgrundel);
        arten[72] = getString(R.string.art_europa_Groppe);
        arten[73] = getString(R.string.art_europa_Flunder);

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
        final Intent receivedIntent = getActivity().getIntent();

        // Autocomplete-Vorschläge für die Fischart
        ArrayAdapter<String> artenAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, arten);
        final AutoCompleteTextView autoComplete_Fisch_art = (AutoCompleteTextView) rootView.findViewById(R.id.autoComplete_fisch_art);
        autoComplete_Fisch_art.setAdapter(artenAdapter);

        // ggf. Daten übernehmen
        if (receivedIntent != null && receivedIntent.getExtras() != null) {
            newFish = false;
            Bundle fishData = receivedIntent.getExtras();
            haematom_checked = getCheckedPositions(fishData.getString("haematom_stelle"),choices);
            schuerfung_checked = getCheckedPositions(fishData.getString("schuerfung_stelle"),choices);
            ow_checked = getCheckedPositions(fishData.getString("ow_stelle"),choices);
            ta_checked = getCheckedPositions(fishData.getString("ta_stelle"),choices);

            //Eingabemaske anpassen
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

            autoComplete_Fisch_art.setText(fishData.getString("art"));
            edit_fisch_laenge.setText((String.valueOf(fishData.getDouble("laenge"))));
            rbtn_bpa_einstg.setChecked(fishData.getBoolean("bpa_eins"));
            rbtn_bpa_beidstg.setChecked(fishData.getBoolean("bpa_beids"));
            rbtn_sv_einstg.setChecked(fishData.getBoolean("sv_eins"));
            rbtn_sv_beidstg.setChecked(fishData.getBoolean("sv_beids"));
            cb_haematom.setChecked(fishData.getBoolean("haematom"));
            cb_schuerfung.setChecked(fishData.getBoolean("schuerfung"));
            cb_schuerfung_verpilzt.setChecked(fishData.getBoolean("schuerfung_verpilzt"));
            cb_ow.setChecked(fishData.getBoolean("ow"));
            cb_ow_verpilzt.setChecked(fishData.getBoolean("ow_verpilzt"));
            cb_ta.setChecked(fishData.getBoolean("ta"));
            cb_td.setChecked(fishData.getBoolean("td"));
            cb_verpilzung.setChecked(fishData.getBoolean("verpilzung"));
            edit_bemerkung.setText(fishData.getString("bemerkung"));
        }

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
                    Bundle b = receivedIntent.getExtras();
                    updateData(rootView,dataSource,b.getInt("pos")+1); //warum ist die +1 hier nötig???
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
                    Bundle b = receivedIntent.getExtras();
                    updateData(rootView,dataSource,b.getInt("pos")+1);
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
                final EditText edit_bemerkung = (EditText) rootView.findViewById(R.id.edit_bemerkung);

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
                edit_bemerkung.setText(null);

                /*
                // neues Fragment (ohne putExtras) erstellen
                // man müsste das alte hier irgendwie zerstören...
                receivedIntent.removeExtra("");
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.beginTransaction().remove(actualFragment).commit();
                Intent editFishIntent = new Intent(getActivity(), EditFishActivity.class);
                startActivity(editFishIntent);
                */
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

    void updateData(View rootView, FishDataSource dataSource, int pos){
        Fish f = getFish(rootView);
        dataSource.updateFish(pos, f);
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
