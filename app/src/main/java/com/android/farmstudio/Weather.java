package com.android.farmstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

    ProgressDialog pr;
    double dtma,dtmi;
    String temp , tempMin,tempMax,humidity,windSpeed,weatherDescription;
    String CITY="Ghaziabad,in";
    String API = "8118ed6ee68db2debfaaa5a44c832918";
    TextView  statusTxt, tempTxt, temp_minTxt, temp_maxTxt, windTxt, humidityTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        CITY =getIntent().getStringExtra("city")+",in";

        Toast.makeText(Weather.this,"Your City : " +CITY,Toast.LENGTH_SHORT).show();
        statusTxt = findViewById(R.id.status);
        tempTxt = findViewById(R.id.temp);
        temp_minTxt = findViewById(R.id.temp_min);
        temp_maxTxt = findViewById(R.id.temp_max);
        windTxt = findViewById(R.id.wind);
        humidityTxt = findViewById(R.id.humidity);

        new weatherTask().execute();


    }


    class weatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pr=ProgressDialog.show(Weather.this,"","Detecting Wait",true);

            findViewById(R.id.errorText).setVisibility(View.GONE);
        }

        protected String doInBackground(String... args) {
            String response = HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&units=metric&appid=" + API);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {


            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject main = jsonObj.getJSONObject("main");
                JSONObject sys = jsonObj.getJSONObject("sys");
                JSONObject wind = jsonObj.getJSONObject("wind");
                JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);

                 temp = main.getString("temp") ;
                 tempMin =  main.getString("temp_min") ;
                 double tmi=Double.parseDouble(tempMin.substring(0,2));
                 tempMax =  main.getString("temp_max") ;
                double tma=Double.parseDouble(tempMax.substring(0,2));
                 humidity = main.getString("humidity");
                 windSpeed = wind.getString("speed");
                 weatherDescription = weather.getString("description");

                 dtmi=tmi-3.4;
                 dtma=tmi+1.3;
                statusTxt.setText("Descript :"+weatherDescription.toUpperCase());
                tempTxt.setText("Temp : \n"+temp+ "°C" );
                temp_minTxt.setText("Min Temp: " +dtmi+ "°C");
                temp_maxTxt.setText("Max Temp: " +dtma+ "°C");
                windTxt.setText("Wind Speed :"+windSpeed);
                humidityTxt.setText("Humidity : "+humidity +"%");


                pr.dismiss();



            } catch (JSONException e) {
                findViewById(R.id.errorText).setVisibility(View.VISIBLE);
            }


        }
    }

    public void send_details(View ddf) {
        Intent intent = new Intent(Weather.this, soil_on_weather.class);
        intent.putExtra("temp", temp);
        intent.putExtra("min",""+dtmi);
        intent.putExtra("max",""+dtma);
        intent.putExtra("speed",windSpeed);
        intent.putExtra("humid",humidity);
        startActivity(intent);
    }

}
