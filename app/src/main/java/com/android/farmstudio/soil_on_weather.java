package com.android.farmstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

public class soil_on_weather extends AppCompatActivity {
String temp,mint,maxt,humid,speed;
String pte;
TextView ta;
double tempp,minte,maxte,spee,hu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soil_on_weather);

//        ta.setMovementMethod(new ScrollingMovementMethod());

        ta=findViewById(R.id.textView4);
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

        Toast.makeText(soil_on_weather.this,temp+"\n"+mint+'\n'+maxt+"\n"+speed+"\n"+humid+"\n"+hu,Toast.LENGTH_LONG).show();


        pte= ta.getText().toString();
//        ta.setText(pte+"तापमान आधारित , वर्षा आधारित\nTemperature Based ,Rainfall Based\n");
        ta.setText(pte+"\nतापमान आधारित , वर्षा आधारित संभव फसल की भविष्यवाणी\nTemperature Based ,Rainfall Based \n");

        if (tempp<5)
        {
           pte= ta.getText().toString();
           ta.setText(pte + " \nतापमान कम होने के कारण \nइस दौरान गेहूं की रोपाई लाभदायक नहीं है");
           ta.setText(pte + "\nTransplanting of wheat during this period is not beneficial because of reduced tempearture");

           //Toast.makeText(soil_on_weather.this,"इस दौरान गेहूं की रोपाई लाभदायक नहीं है",Toast.LENGTH_SHORT).show();
        }
        else if (tempp>5 && tempp<36 )
        {
            pte= ta.getText().toString();
            ta.setText(pte + "\nइस दौरान गेहूं की रोपाई बहुत लाभदायक है");
            ta.setText(pte + "\nTransplanting of wheat during this period is beneficial");


            // Toast.makeText(soil_on_weather.this,"इस दौरान गेहूं की रोपाई बहुत लाभदायक है",Toast.LENGTH_SHORT).show();
        }

        pte= ta.getText().toString();
        ta.setText(pte + "\n\n");

        if (tempp<10)
        {
            pte= ta.getText().toString();
            ta.setText(pte + "\n\nतापमान कम होने के कारण\nचावल की खेती संभव नहीं है");
            ta.setText(pte + "\nCultivation of rice is not possible ");


            //Toast.makeText(soil_on_weather.this,"इस दौरान गेहूं की रोपाई लाभदायक नहीं है",Toast.LENGTH_SHORT).show();
        }
        else if (tempp>10 && tempp<25 )
        {
            if (hu>80 && spee<0.9)
            {
                pte= ta.getText().toString();
                ta.setText(pte + "\nभारी वर्षा की संभावना , चावल की खेती संभव है");
                ta.setText(pte + "\nCultivation of rice is possible ");
            }
            else
            {
                pte= ta.getText().toString();
                ta.setText(pte + "\nवर्षा की कमी के कारण , चावल की खेती लाभदायक नहीं है");
                ta.setText(pte + "\nCultivation of rice is not possible ");
            }
            // Toast.makeText(soil_on_weather.this,"इस दौरान गेहूं की रोपाई बहुत लाभदायक है",Toast.LENGTH_SHORT).show();
        }
        else if (tempp>=25 && tempp<35 )
        {
            if (hu>80 && spee<0.9)
            {
                pte= ta.getText().toString();
                ta.setText(pte + "\nभारी वर्षा की संभावना , चावल की खेती लाभदायक है");
                ta.setText(pte + "\nCultivation of rice is profiltable ");
            }
            else
            {
                pte= ta.getText().toString();
                ta.setText(pte + "\nवर्षा की कमी के कारण , चावल की खेती लाभदायक नहीं है");
                ta.setText(pte + "\nCultivation of rice is not possible ");
            }
            // Toast.makeText(soil_on_weather.this,"इस दौरान गेहूं की रोपाई बहुत लाभदायक है",Toast.LENGTH_SHORT).show();
        }


//        pte= ta.getText().toString();




    }
}
