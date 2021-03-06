package com.app.smartmuseum.smartmuseum;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import com.google.zxing.Result;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class HomeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    ViewPager viewPager;
    private static int flag;

    private static final String TAG = "Home Activity";
    private static final String URLSTRING = "https://smartmuseum.000webhostapp.com/wp-content/plugins/extensionModel/service.php?id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Fullscreen schermo
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimertask(), 2000, 4000);

        //set margine nameApp
        CollapsingToolbarLayout tl = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        tl.setExpandedTitleMarginBottom(100);

        //libreria QRCode
        mScannerView = new ZXingScannerView(this);
        flag = 1; // flag per tornare dietro alle activity precedenti


        //gestione del bottone per scansione QRCODE
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.scanQr);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setContentView(mScannerView);
                mScannerView.setResultHandler((ZXingScannerView.ResultHandler) view.getContext());
                mScannerView.startCamera();
                flag = -1;
                Log.d(TAG, "Faccio partire la fotocamera");

            }
        });
    }

    /**
     * Gestisce lo scorrimento dell slide in Home Activity
     */
    public class MyTimertask extends TimerTask {

        @Override
        /**
         * Scorrimento slide nella HomeActivity
         */
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    /**
     * Gestisce il risultato della scansione del QRCode
     */
    public void handleResult(Result result) {
        try {
            // Creo l'oggetto URL che rappresenta l'indirizzo della pagina da richiamare
            URL url = new URL(URLSTRING + result.getText().toString());

            // creo l'oggetto HttpURLConnection paragonabile all'apertura di una finestra del browser
            HttpURLConnection client = (HttpURLConnection) url.openConnection();

            // Recupero le informazioni inviate dal server
            InputStream risposta = new BufferedInputStream(client.getInputStream());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                String dati= mostroDati(risposta);
                JSONArray jsonArray= new JSONArray(dati);
                JSONArray jsonArrayreperto = (JSONArray) jsonArray.get(0);
                if(jsonArrayreperto.length()!=0) {

                    Intent intent = new Intent(getApplication(), WorkActivity.class);
                    intent.putExtra("TEST", dati);
                    startActivity(intent);
                }
                else{
                    Log.d(TAG, "sono in else");
                    Toast toast = Toast.makeText(getBaseContext(), R.string.dialog, Toast.LENGTH_SHORT);
                    toast.show();
                    mScannerView.setResultHandler((ZXingScannerView.ResultHandler) this);
                    mScannerView.startCamera();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplication(),InfoActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //riortno alla homeActivity dalla scansione qrcode
        super.onBackPressed();
        if (flag != 1) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }

    }

    /**
     * Formatta i dati dall' input stream
     * @param in
     * @return
     */
    private static String mostroDati(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
