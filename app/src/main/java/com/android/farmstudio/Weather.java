package com.android.farmstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Weather extends AppCompatActivity {


    String CITY="";
    String API = "8118ed6ee68db2debfaaa5a44c832918";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        CITY =getIntent().getStringExtra("city");


    }
}
