package com.android.farmstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class States extends AppCompatActivity {
Button btn;
String state="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states);

        btn=findViewById(R.id.button);



    }
    public void jandk(View view)
    {
        state="Jammu and Kashmir";
        //Log.i("MSG",state);


    }


}
