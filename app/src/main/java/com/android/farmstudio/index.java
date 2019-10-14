package com.android.farmstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class index extends AppCompatActivity {
String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

    city=getIntent().getStringExtra("city");
    }


    public void weather(View vc)
    {
        Intent intent= new
                Intent(index.this,Weather.class);
        intent.putExtra("city",city);
        startActivity(intent);

    }
}
