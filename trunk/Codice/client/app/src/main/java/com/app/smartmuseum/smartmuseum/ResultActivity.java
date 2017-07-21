package com.app.smartmuseum.smartmuseum;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

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
    }
}
