package com.android.farmstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Weather extends AppCompatActivity {

TextView t1;
    String CITY="Ghaziabad";
    String API = "8118ed6ee68db2debfaaa5a44c832918";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        t1=findViewById(R.id.textView4);
        t1.setText(CITY);
        CITY =getIntent().getStringExtra("city");


    }
}
