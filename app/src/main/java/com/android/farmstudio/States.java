package com.android.farmstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class States extends AppCompatActivity {
Button btn;
String state="",city="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states);

        btn=findViewById(R.id.button);


    }
    public void jandk(View view)
    {
        state="Jammu and Kashmir";
      Log.i("MSG",state);

        iindex();

    }

    public  void rajasthan(View c)
        {
            state="Rajasthan";
            iindex();
        }

        public void madhya(View ds)
        {
            state="Madhya Pradesh";
            iindex();

        }


    public void up(View ds)
    {
        state="Uttar Pradesh";
        iindex();

    }



    public  void iindex()
{
    Intent intent = new Intent(States.this,Cities.class);
    intent.putExtra("State",state);
    startActivity(intent);
    finishAffinity();
}

}
