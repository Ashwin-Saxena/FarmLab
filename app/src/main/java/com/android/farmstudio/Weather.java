package com.android.farmstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androdocs.httprequest.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Weather extends AppCompatActivity {

TextView t1;
    String CITY="Ghaziabad";
    String API = "8118ed6ee68db2debfaaa5a44c832918";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        t1=findViewById(R.id.textView5);
        CITY =getIntent().getStringExtra("city");
        t1.setText(CITY);


    }

    public void wwe(View dssdd)
    {


    }


}
