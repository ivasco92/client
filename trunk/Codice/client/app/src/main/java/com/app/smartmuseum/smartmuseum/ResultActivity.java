package com.app.smartmuseum.smartmuseum;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.app.smartmuseum.smartmuseum.model.Reperto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ResultActivity extends AppCompatActivity {
    private static final String TAG="ResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle intent= getIntent().getExtras();
        String text= intent.getString("TEST");


        try {
            JSONArray jsonArray= new JSONArray(text);
            Log.d(TAG,jsonArray.get(0).toString());//è un json array;
            JSONArray jsonArray0= (JSONArray) jsonArray.get(0);
            Reperto reperto= new Reperto();
            
            reperto.setId(Integer.parseInt(jsonArray0.getJSONObject(0).get("id").toString()));
            reperto.setDimensioni(jsonArray0.getJSONObject(0).get("dimesioni").toString());
            reperto.setValore(Float.valueOf(jsonArray0.getJSONObject(0).get("valore").toString()));
            reperto.setTitolo(jsonArray0.getJSONObject(0).get("titolo").toString());
            reperto.setTipo(jsonArray0.getJSONObject(0).get("tipo").toString());
            reperto.setNome_autore(jsonArray0.getJSONObject(0).get("nome_autore").toString());
            reperto.setPeso(Integer.parseInt(jsonArray0.getJSONObject(0).get("peso").toString()));
            reperto.setLuogo_scoperta(jsonArray0.getJSONObject(0).get("luogo_scoperta").toString());
            reperto.setData_scoperta(jsonArray0.getJSONObject(0).get("data_scoperta").toString());
            reperto.setData_acquisizione(jsonArray0.getJSONObject(0).get("data_acquisizione").toString());
            reperto.setBibliografia(jsonArray0.getJSONObject(0).get("bibliografia").toString());
            reperto.setDescrizione(jsonArray0.getJSONObject(0).get("descrizione").toString());
            reperto.setPubblicato(jsonArray0.getJSONObject(0).get("pubblicato").toString());

            Log.d(TAG, "reperto--->"+reperto.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
