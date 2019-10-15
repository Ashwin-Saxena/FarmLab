package com.android.farmstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class soil_on_weather extends AppCompatActivity {
String temp,mint,maxt,humid,speed;
double tempp,minte,maxte,spee,hu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_on_weather);


        temp=getIntent().getStringExtra("temp");
        mint=getIntent().getStringExtra("min");
        maxt=getIntent().getStringExtra("max");
        speed=getIntent().getStringExtra("speed");
        humid=getIntent().getStringExtra("humid" +
                "");

        tempp=Double.parseDouble(temp);
        minte=Double.parseDouble(mint);
        maxte=Double.parseDouble(maxt);
        spee=Double.parseDouble(speed);
        hu=Double.parseDouble(humid);

        Toast.makeText(soil_on_weather.this,temp+"\n"+mint+'\n'+maxt+"\n"+speed+"\n"+humid+"\n"+hu+122,Toast.LENGTH_LONG).show();


        if (tempp<5)
        {
            Toast.makeText(soil_on_weather.this,"इस दौरान गेहूं की रोपाई लाभदायक नहीं है",Toast.LENGTH_SHORT).show();
        }
        else if (tempp>5 && tempp<36 )
        {
            Toast.makeText(soil_on_weather.this,"इस दौरान गेहूं की रोपाई बहुत लाभदायक है",Toast.LENGTH_SHORT).show();
        }


    }
}
