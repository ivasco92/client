package com.app.smartmuseum.smartmuseum;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;


import com.app.smartmuseum.smartmuseum.model.Multimedia;
import com.app.smartmuseum.smartmuseum.model.Reperto;
import com.app.smartmuseum.smartmuseum.model.TipoMultimedia;

import org.json.JSONArray;
import org.json.JSONException;

import static com.app.smartmuseum.smartmuseum.R.id.center;

import static com.app.smartmuseum.smartmuseum.R.id.toolbar1;

public class WorkActivity extends AppCompatActivity {
    private static final String TAG="WorkActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen schermo
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_work);
        Toolbar toolbar = (Toolbar) findViewById(toolbar1);
        setSupportActionBar(toolbar);

        //xoversione Json per istanziare l'oggetto reperto
        Bundle intent= getIntent().getExtras();
        String text= intent.getString("TEST");


        try {
            JSONArray jsonArray= new JSONArray(text);
            // Log.d(TAG,jsonArray.get(0).toString());//è un json array;
            JSONArray jsonArrayreperto= (JSONArray) jsonArray.get(0);
            JSONArray jsonArraymultimedia= (JSONArray) jsonArray.get(1);
            Log.d(TAG, "il json--->"+jsonArray.toString());
            // Log.d(TAG, "il primo array, reperto--->"+ jsonArrayreperto.toString());
            // Log.d(TAG, "secondo array, multimedia--->"+jsonArraymultimedia.toString());
            // Log.d(TAG, "elemento array multimedia--->"+ jsonArraymultimedia.getJSONObject(0).get("url").toString());
            Reperto reperto= new Reperto();
            Multimedia multimedia= new Multimedia();
            TipoMultimedia tipoMultimedia= new TipoMultimedia(Integer.parseInt(jsonArraymultimedia.getJSONObject(0).get("id_tipo").toString()));

            reperto.setId(Integer.parseInt(jsonArrayreperto.getJSONObject(0).get("id").toString()));
            reperto.setDimensioni(jsonArrayreperto.getJSONObject(0).get("dimesioni").toString());
            reperto.setValore(Float.valueOf(jsonArrayreperto.getJSONObject(0).get("valore").toString()));
            reperto.setTitolo(jsonArrayreperto.getJSONObject(0).get("titolo").toString());
            reperto.setTipo(jsonArrayreperto.getJSONObject(0).get("tipo").toString());
            reperto.setNome_autore(jsonArrayreperto.getJSONObject(0).get("nome_autore").toString());
            reperto.setPeso(Integer.parseInt(jsonArrayreperto.getJSONObject(0).get("peso").toString()));
            reperto.setLuogo_scoperta(jsonArrayreperto.getJSONObject(0).get("luogo_scoperta").toString());
            reperto.setData_scoperta(jsonArrayreperto.getJSONObject(0).get("data_scoperta").toString());
            reperto.setData_acquisizione(jsonArrayreperto.getJSONObject(0).get("data_acquisizione").toString());
            reperto.setBibliografia(jsonArrayreperto.getJSONObject(0).get("bibliografia").toString());
            reperto.setDescrizione(jsonArrayreperto.getJSONObject(0).get("descrizione").toString());
            reperto.setPubblicato(jsonArrayreperto.getJSONObject(0).get("pubblicato").toString());

            Log.d(TAG, "reperto--->"+reperto.toString());

            multimedia.setId(Integer.parseInt(jsonArraymultimedia.getJSONObject(0).get("id").toString()));
            multimedia.setUrl(jsonArraymultimedia.getJSONObject(0).get("url").toString());
            Log.d(TAG, "multimedia---->"+multimedia.toString());
            Log.d(TAG, "tipo multimedia--->"+tipoMultimedia.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }




        // modifica del titolo
        setTitle("ProvaTitoloRepertoTestoLungo");

        CollapsingToolbarLayout tl= (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout1);
        //tl.setExpandedTitleMarginBottom(700);
        tl.setExpandedTitleGravity(50);


        //gestione bottoni audio e video
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.audio);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.video);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



}
