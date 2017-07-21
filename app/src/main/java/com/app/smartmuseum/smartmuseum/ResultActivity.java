package com.app.smartmuseum.smartmuseum;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.app.smartmuseum.smartmuseum.remoteDatabase.JsonHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class ResultActivity extends AppCompatActivity {
    private static final String TAG="ResultActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle intent= getIntent().getExtras();
        String text= intent.getString("TEST");

       // ZXingScannerView edit = (ZXingScannerView) findViewById(R.id.scanner_fragment);
      //  edit.setResultHandler();
        TextView edit=(TextView) findViewById(R.id.textView);
        edit.setText(text);
        try {
            JSONArray jsonArray= new JSONArray(text);
            Log.d(TAG,jsonArray.get(0).toString());//è un json array
            JSONArray jsonArray1= (JSONArray) jsonArray.get(0);
            List listreperto= toList(jsonArray1);
            Log.d(TAG, " lista--->"+listreperto);
            Log.d(TAG,"elemento lista--->"+listreperto.get(0));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }








    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
