package com.example.courseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class velocity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText convertFrom;
    TextView resultDisplay;
    int spinner1;
    int spinner2;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocity);

        convertFrom = (EditText) findViewById(R.id.entryView);
        resultDisplay = (TextView) findViewById(R.id.resultView);
        Button button = (Button) findViewById(R.id.convertButton);

        Spinner spinnerFrom = (Spinner) findViewById(R.id.spinnerDropdown);
        Spinner spinnerTo = (Spinner) findViewById(R.id.spinnerDropdown2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.velocity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
        spinnerFrom.setOnItemSelectedListener(this);
        spinnerTo.setOnItemSelectedListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int value = Integer.parseInt(convertFrom.getText().toString());
                convertValue();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getId() == R.id.spinnerDropdown)
        {
            spinner1 = Integer.parseInt(parent.getItemAtPosition(position).toString());
        }
        else if(parent.getId() == R.id.spinnerDropdown2)
        {
            spinner2 = Integer.parseInt(parent.getItemAtPosition(position).toString());
        }

        convertValue();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //no method here
    }

    public void convertValue() {
        int value = Integer.parseInt(convertFrom.getText().toString());

        if(spinner1 == 0 && spinner2 == 1)
        {
            result = value * 3;
        }
        else if(spinner1 == 1 && spinner2 == 0)
        {
            result = value * 2;
        }
    }
}
