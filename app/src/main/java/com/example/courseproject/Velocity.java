package com.example.courseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Velocity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText convertFrom;
    TextView resultDisplay;
    Spinner spinnerFrom;
    Spinner spinnerTo;
    String[] menuArray;

    String spinner1 = null;
    String spinner2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appToolbar2);
        setSupportActionBar(toolbar);

        convertFrom = (EditText) findViewById(R.id.entryView);
        resultDisplay = (TextView) findViewById(R.id.resultView);
        Button convertButton = (Button) findViewById(R.id.convertButton);
        Button saveButton = (Button) findViewById(R.id.saveButton);

        spinnerFrom = (Spinner) findViewById(R.id.spinnerDropdown);
        spinnerTo = (Spinner) findViewById(R.id.spinnerDropdown2);

        menuArray = getResources().getStringArray(R.array.velocity_array);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.velocity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
        spinnerFrom.setOnItemSelectedListener(this);
        spinnerTo.setOnItemSelectedListener(this);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int value = Integer.parseInt(convertFrom.getText().toString());
//                resultDisplay.setText(String.valueOf(value));
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save to firebase
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        spinnerFrom.setSelection(position);
//        spinnerTo.setSelection(position);
//
//        String values = "Spinner 1: " + spinnerFrom.getSelectedItem().toString() +
//                "\nSpinner 2: " + spinnerTo.getSelectedItem().toString();
//
//        resultDisplay.setText(values);



        if(parent.getId() == R.id.spinnerDropdown)
        {
            spinner1 =(menuArray[position]);
        }
        else if(parent.getId() == R.id.spinnerDropdown2)
        {
            spinner2 =(menuArray[position]);
        }

        String values = "Spinner 1: " + spinner1 + "\nSpinner2: " + spinner2;
        resultDisplay.setText(values);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //no method here
    }

}
