package com.android.farmstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Cities extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
String state="";
String city="";
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        state=getIntent().getStringExtra("State");

        spinner=findViewById(R.id.spinner);
        switch (state)
        {
            case "Uttar Pradesh":   ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.up, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(this);

                break;

            case "Rajasthan":  ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                    R.array.rajasthan, android.R.layout.simple_spinner_item);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter1);
                spinner.setOnItemSelectedListener(this);

                break;

            case  "Jammu and Kashmir":
                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                        R.array.jk, android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter2);
                spinner.setOnItemSelectedListener(this);

                break;

            case  "Madhya Pradesh":
                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                        R.array.up, android.R.layout.simple_spinner_item);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter3);
                spinner.setOnItemSelectedListener(this);
                break;
                default:ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                        R.array.up, android.R.layout.simple_spinner_item);
                    adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter4);
                    spinner.setOnItemSelectedListener(this);


        }



    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String name=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(Cities.this,name,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
