package com.app.smartmuseum.smartmuseum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AlertDialog;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
    }

    public void onClick(View v){

    mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
}
    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.v("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //mScannerView.resumeCameraPreview(this);
    }
}
