package com.app.smartmuseum.smartmuseum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.app.smartmuseum.smartmuseum.model.Multimedia;
import com.app.smartmuseum.smartmuseum.model.Reperto;
import com.app.smartmuseum.smartmuseum.model.TipoMultimedia;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import static com.app.smartmuseum.smartmuseum.R.id.toolbar1;

public class WorkActivity extends AppCompatActivity {
    private static final String TAG = "WorkActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen schermo
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_work);
        Toolbar toolbar = (Toolbar) findViewById(toolbar1);
        setSupportActionBar(toolbar);

        //conversione Json per istanziare l'oggetto reperto
        Bundle intent = getIntent().getExtras();
        String text = intent.getString("TEST");

        try {
            JSONArray jsonArray = new JSONArray(text);
            JSONArray jsonArrayreperto = (JSONArray) jsonArray.get(0);
            JSONArray jsonArraymultimedia = (JSONArray) jsonArray.get(1);

            Reperto reperto = new Reperto();
            final Multimedia multimedia = new Multimedia();
            final TipoMultimedia tipoMultimedia = new TipoMultimedia(Integer.parseInt(jsonArraymultimedia.getJSONObject(0).get("id_tipo").toString()));

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

            multimedia.setId(Integer.parseInt(jsonArraymultimedia.getJSONObject(0).get("id").toString()));
            multimedia.setUrl(jsonArraymultimedia.getJSONObject(0).get("url").toString());

            // setto il titolo dell'opera
            setTitle(reperto.getTitolo());

            //carico e setto l'immagine del reperto
            ImageView imagereperto = (ImageView) findViewById(R.id.sfo_reperto);
            BitmapFactory.Options bmOptions;
            bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;
            Bitmap bm = LoadImage(multimedia.getUrl(), bmOptions);
            imagereperto.setImageBitmap(bm);

            //gestione bottoni audio e video
            FloatingActionButton fabaudio = (FloatingActionButton) findViewById(R.id.audio);
            fabaudio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tipoMultimedia.getNome().equals("audio")) {
                        Uri uri = Uri.parse(multimedia.getUrl()); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    } else {
                        Snackbar.make(view, R.string.snackbar, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            });

            FloatingActionButton fabvideo = (FloatingActionButton) findViewById(R.id.video);
            fabvideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tipoMultimedia.getNome().equals("video")) {
                        Uri uri = Uri.parse(multimedia.getUrl()); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    } else {
                        Snackbar.make(view, R.string.snackbar, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            });

            TextView descriptiontext = (TextView) findViewById(R.id.description);
            descriptiontext.setText(reperto.getDescrizione());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CollapsingToolbarLayout tl = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout1);
        //tl.setExpandedTitleMarginBottom(700);
        tl.setExpandedTitleGravity(50);
    }

    private Bitmap LoadImage(String URL, BitmapFactory.Options options) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            in.close();
        } catch (IOException e1) {
        }
        return bitmap;
    }

    private InputStream OpenHttpConnection(String strURL) throws IOException {
        InputStream inputStream = null;
        URL url = new URL(strURL);
        URLConnection conn = url.openConnection();

        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpConn.getInputStream();
            }
        } catch (Exception ex) {
        }
        return inputStream;
    }
}
