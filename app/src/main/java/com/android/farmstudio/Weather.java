package com.android.farmstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
    String CITY="Ghaziabad,in";
    String API = "8118ed6ee68db2debfaaa5a44c832918";
    TextView addressTxt, updated_atTxt, statusTxt, tempTxt, temp_minTxt, temp_maxTxt, sunriseTxt,
            sunsetTxt, windTxt, pressureTxt, humidityTxt;
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

                String temp = main.getString("temp") + "°C";
                String tempMin = "Min Temp: " + main.getString("temp_min") + "°C";
                String tempMax = "Max Temp: " + main.getString("temp_max") + "°C";
                String humidity = main.getString("humidity");
                String windSpeed = wind.getString("speed");
                String weatherDescription = weather.getString("description");

                statusTxt.setText("Descript :"+weatherDescription.toUpperCase());
                tempTxt.setText("Temp : \n"+temp );
                temp_minTxt.setText(tempMin);
                temp_maxTxt.setText(tempMax);
                windTxt.setText("Wind Speed :"+windSpeed);
                humidityTxt.setText("Humidity : "+humidity +"%");

                pr.dismiss();


            } catch (JSONException e) {
                findViewById(R.id.errorText).setVisibility(View.VISIBLE);
            }

        }
    }

}
