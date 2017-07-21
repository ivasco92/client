package com.app.smartmuseum.smartmuseum;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;


import com.google.zxing.Result;
import com.google.zxing.common.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class HomeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    ViewPager viewPager;
    private static int flag;
    private static final String TAG="Home Activity";

    //connessione server
    HttpURLConnection httpurlconnection;
    final StringBuilder urlBuilder = new StringBuilder("https://smartmuseum.000webhostapp.com/wp-content/plugins/extensionModel/service.php");
    private static final String CONTENT_TYPE ="content_type";
    private OutputStream out; //Stream di output per la comunicazione con il server
    private InputStream in ; //Stream di input per la comunicazione con il server

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
        CollapsingToolbarLayout tl= (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        tl.setExpandedTitleMarginBottom(100);
       // toolbar.setLogo(R.drawable.ic_launcher);



        //libreria QRCode
        mScannerView = new ZXingScannerView(this);
        flag=1; // flag per tornare dietro alle activity precedenti


        //gestione del bottone per scansione QRCODE
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.scanQr);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // mScannerView = new ZXingScannerView(getApplicationContext());
                setContentView(mScannerView);
                mScannerView.setResultHandler((ZXingScannerView.ResultHandler) view.getContext());
                mScannerView.startCamera();
                flag = -1;
                Log.d(TAG, "Faccio partire la fotocamera");

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    public class MyTimertask extends TimerTask{

        //Scorrimento slide nella HomeActivity
        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else{
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


        //risultato aperto mella ResultActivity
        @Override
        public void handleResult(Result result) {

                InetAddress host;
                try {
                    final URL requesturl=new URL(urlBuilder.toString());
                    httpurlconnection=(HttpURLConnection) requesturl.openConnection();
                    httpurlconnection.setRequestMethod("GET");
                    httpurlconnection.setDoInput(true);
                    httpurlconnection.setDoOutput(true);


                    /*host = InetAddress.getByName(requesturl.getHost());   //identificazione dell'IP del client
                    Socket socket = new Socket(host, 8080);
                    out = socket.getOutputStream();
                    in = socket.getInputStream();
                    BufferedReader inR = new BufferedReader(new InputStreamReader(in));
                    PrintWriter outW = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)), true); //stream in output (invia i dati al server)

                    outW.println(result.getText().toString());*/
                     out=httpurlconnection.getOutputStream();
                    in= httpurlconnection.getInputStream();

                    //invio
                    PrintWriter outW= new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)), true); //stream in output (invia i dati al server)
               //     outW.write(Integer.valueOf(result.getText().toString()));
                    final String urlreperto="https://smartmuseum.000webhostapp.com/wp-content/plugins/extensionModel/service.php?id=";
                    outW.println(urlreperto+result.getText().toString());
                    Log.d(TAG, "inviato il qrcode--->" + urlreperto+result.getText().toString());

                    //ricevo
                    BufferedReader r = new BufferedReader(new InputStreamReader(in));
                    String x = "";
                    x = r.readLine();
                    String total = "";

                    while(x!= null){
                        total += x;
                        x = r.readLine();
                    }

                    Log.d(TAG, "Ecco la readline--->" + total);

                    Intent intent = new Intent(getApplication(), ResultActivity.class);
                    intent.putExtra("TEST", total);
                    startActivity(intent);
               /* Log.d(TAG,"istanzio l url");
                final URL requesturl=new URL(urlBuilder.toString());
                httpurlconnection=(HttpURLConnection) requesturl.openConnection();
                httpurlconnection.setRequestMethod("GET");
                httpurlconnection.setDoInput(true);
                httpurlconnection.setDoOutput(true);

                //mando al server
                outputStream=httpurlconnection.getOutputStream();
                outputStream.write(result.getText().getBytes("UTF-8"));

                Log.d(TAG, "invio il qrcode");
                httpurlconnection.addRequestProperty(CONTENT_TYPE, "application/json");
                final int httpResponseCode= httpurlconnection.getResponseCode();
                Log.d(TAG, "response--------->"+ httpResponseCode);
                outputStream.close();*/

                /*ricevo dal server
                if(httpResponseCode>= httpurlconnection.HTTP_OK && httpResponseCode< httpurlconnection.HTTP_MULT_CHOICE){
                    inputStream= httpurlconnection.getInputStream();
                    Log.d(TAG, "ho appena ricevuto dal server non so cosa");*/
                    //  final String asString=sun.misc.IOUtils

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
               /* else{
                    inputStream= httpurlconnection.getErrorStream();
                }

                final String httpResponseMessage= httpurlconnection.getResponseMessage();
                Log.d(TAG, "risposta dal server--->"+httpResponseMessage.toString());
                Log.d(TAG, "risultato dal server--->"+ inputStream.toString());


            }*/
            /*
            Log.v("handleResult", result.getText());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Scan result");
            builder.setMessage(result.getText());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();*/

                //mScannerView.resumeCameraPreview(this);

        }
    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (flag != 1) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}
