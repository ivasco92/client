package com.app.smartmuseum.smartmuseum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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
import android.widget.Toast;

import com.app.smartmuseum.smartmuseum.model.Reperto;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Locale;

import static com.app.smartmuseum.smartmuseum.R.id.toolbar1;

public class WorkActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private static final String TAG = "WorkActivity";
    private int MY_DATA_CHECK_CODE = 0;
    private TextToSpeech myTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //intent per la sintesi vocale
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);


        //Fullscreen schermo
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_work);

        Toolbar toolbar = (Toolbar) findViewById(toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //conversione Json per istanziare l'oggetto reperto
        Bundle intent = getIntent().getExtras();
        final String text = intent.getString("TEST");

        try {
            JSONArray jsonArray = new JSONArray(text);
            JSONArray jsonArrayreperto = (JSONArray) jsonArray.get(0);
            JSONArray jsonArraymultimedia = (JSONArray) jsonArray.get(1);

            final Reperto reperto = new Reperto();

            reperto.setId(Integer.parseInt(jsonArrayreperto.getJSONObject(0).get("id").toString()));
            reperto.setDimensioni(jsonArrayreperto.getJSONObject(0).get("dimesioni").toString());
            reperto.setValore(Float.valueOf(jsonArrayreperto.getJSONObject(0).get("valore").toString()));
            reperto.setTitolo(jsonArrayreperto.getJSONObject(0).get("titolo").toString());
            reperto.setTipo(jsonArrayreperto.getJSONObject(0).get("tipo").toString());
            reperto.setNome_autore(jsonArrayreperto.getJSONObject(0).get("nome_autore").toString());
            reperto.setPeso(Double.parseDouble(jsonArrayreperto.getJSONObject(0).get("peso").toString()));
            reperto.setLuogo_scoperta(jsonArrayreperto.getJSONObject(0).get("luogo_scoperta").toString());
            reperto.setData_scoperta(jsonArrayreperto.getJSONObject(0).get("data_scoperta").toString());
            reperto.setData_acquisizione(jsonArrayreperto.getJSONObject(0).get("data_acquisizione").toString());
            reperto.setBibliografia(jsonArrayreperto.getJSONObject(0).get("bibliografia").toString());
            reperto.setDescrizione(jsonArrayreperto.getJSONObject(0).get("descrizione").toString());
            reperto.setPubblicato(jsonArrayreperto.getJSONObject(0).get("pubblicato").toString());

            final HashMap<String, String> mapmedia = new HashMap<>();

            for (int i = 0; i < jsonArraymultimedia.length(); i++) {
                String id_tipo = jsonArraymultimedia.getJSONObject(i).get("id_tipo").toString();
                String url = jsonArraymultimedia.getJSONObject(i).get("url").toString();
                mapmedia.put(id_tipo, url);
            }

            // setto il titolo dell'opera
            setTitle(reperto.getTitolo());

            //setto la descrizione dell'opera
            TextView descriptiontext = (TextView) findViewById(R.id.description);
            descriptiontext.setText("Autore: "+reperto.getNome_autore()+"\n\n"+
                                    "Data Scoperta: "+reperto.getData_scoperta()+"\n\n"+
                                    "Luogo Scoperta: "+reperto.getLuogo_scoperta()+"\n\n"+
                                    "Dimensioni: "+reperto.getDimensioni()+"\n\n"+
                                    "Tipo: "+reperto.getTipo()+"\n\n"+
                                    "Peso: "+reperto.getPeso()+"\n\n"+
                                    "Descrizione: "+"\n"+reperto.getDescrizione()+"\n\n"+
                                    "Bibliografia: "+reperto.getBibliografia()+"\n\n");

            //carico e setto l'immagine del reperto
            ImageView imagereperto = (ImageView) findViewById(R.id.sfo_reperto);
            BitmapFactory.Options bmOptions;
            bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;
            Bitmap bm = LoadImage(mapmedia.get("3"), bmOptions);
            imagereperto.setImageBitmap(bm);

            //gestione bottoni audio e video
            FloatingActionButton fabaudio = (FloatingActionButton) findViewById(R.id.audio);
            FloatingActionButton fabvideo = (FloatingActionButton) findViewById(R.id.video);
            final boolean isidtype2=mapmedia.containsKey("2");
            final String url2= mapmedia.get("2");
            fabaudio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(myTTS.isSpeaking()){
                        myTTS.stop();
                    }else{
                    myTTS.speak(reperto.getDescrizione(),TextToSpeech.QUEUE_FLUSH,null);
                    }
                }
            });

            fabvideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isidtype2) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
                        startActivity(intent);
                    } else {
                        Snackbar.make(view, R.string.snackbar, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CollapsingToolbarLayout tl = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout1);
        tl.setExpandedTitleGravity(50);
    }

    /**
     * carica l'immagine in bitmap
     * @param URL
     * @param options
     * @return
     */
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

    @Override
    public void onInit(int i) {

            if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);
        else if (i == TextToSpeech.ERROR) {
            Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                myTTS = new TextToSpeech(this, this);
            }
            else {
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        myTTS.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myTTS.stop();
    }
}
