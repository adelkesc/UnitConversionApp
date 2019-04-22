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
import android.widget.Toast;

public class Velocity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText convertFrom;
    TextView resultDisplay;
    Spinner spinnerFrom;
    Spinner spinnerTo;
    String[] menuArray;

    String spinner1 = null;
    String spinner2 = null;
    int value;

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
                value = Integer.parseInt(convertFrom.getText().toString());
                showResult(value);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save to firebase
                resultDisplay.setText("");
                Toast.makeText(Velocity.this, "Result saved.", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getId() == R.id.spinnerDropdown)
        {
            spinner1 =(menuArray[position]);
        }
        else if(parent.getId() == R.id.spinnerDropdown2)
        {
            spinner2 =(menuArray[position]);
        }

        convertUnits(spinner1, spinner2);
//        showResult(result);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //no method here
    }

    public void convertUnits(String spinner1, String spinner2)
    {
//        Toast.makeText(this, spinner1, Toast.LENGTH_SHORT).show();

        if(spinner1.contains("Double") && spinner2.contains("Triple"))
        {
            String success = "This works.";
            Toast.makeText(this, success, Toast.LENGTH_SHORT).show();
        }
        else if(spinner1.contains("Triple") && spinner2.contains("Double"))
        {
            String success = "This works.";
            Toast.makeText(this, success, Toast.LENGTH_SHORT).show();
        }
        else
        {
            String fail = "This doesn't work.";
            Toast.makeText(this, fail, Toast.LENGTH_SHORT).show();
        }
    }

    public void showResult(int value)
    {
        resultDisplay.setText(String.valueOf(value));
    }
}
