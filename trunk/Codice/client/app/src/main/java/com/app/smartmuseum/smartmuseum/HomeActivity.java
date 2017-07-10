package com.app.smartmuseum.smartmuseum;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.zxing.Result;

import java.util.Timer;
import java.util.TimerTask;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class HomeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    ViewPager viewPager;
    private static int flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimertask(), 2000, 4000);



        CollapsingToolbarLayout tl= (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        tl.setExpandedTitleMarginBottom(40);
       // toolbar.setLogo(R.drawable.ic_launcher);




        mScannerView = new ZXingScannerView(this);
        flag=1;


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.scanQr);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               // mScannerView = new ZXingScannerView(getApplicationContext());
                setContentView(mScannerView);
                mScannerView.setResultHandler((ZXingScannerView.ResultHandler) view.getContext());
                mScannerView.startCamera();
                flag = -1;

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public class MyTimertask extends TimerTask{

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

        @Override
        public void handleResult(Result result) {
            /*
            Log.v("handleResult", result.getText());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Scan result");
            builder.setMessage(result.getText());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            //mScannerView.resumeCameraPreview(this);
            */
            Intent intent = new Intent(getApplication(), ResultActivity.class);
            intent.putExtra("TEST", result.getText());
            startActivity(intent);
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
