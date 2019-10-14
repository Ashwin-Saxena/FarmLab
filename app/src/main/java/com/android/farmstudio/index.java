package com.android.farmstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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


    public void market(View bg)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flipkart.com/competitive-book-agriculture/p/itmfyqdzyzgykry7?pid=RBKFYQDZGYKMXENG&lid=LSTRBKFYQDZGYKMXENGXDTXCA&marketplace=FLIPKART&srno=s_1_2&otracker=search&otracker1=search&fm=SEARCH&iid=9dffcc09-9ef9-421b-9d13-54c980dc97f0.RBKFYQDZGYKMXENG.SEARCH&ppt=sp&ppn=sp&ssid=4bqdiisxsw0000001571081551838&qH=f8ea9e07f542c0cd"));
        startActivity(browserIntent);



    }



}
