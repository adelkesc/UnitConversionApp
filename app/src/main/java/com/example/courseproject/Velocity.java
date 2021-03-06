package com.example.courseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Velocity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText convertFrom;
    TextView resultDisplay;

    Spinner spinnerFrom;
    Spinner spinnerTo;
    private String spinner1 = null;
    private String spinner2 = null;

    private double value;
    private double result = 0;

    private DatabaseReference appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appToolbarCategory);
        setSupportActionBar(toolbar);

        appDatabase = FirebaseDatabase.getInstance().getReference();

        convertFrom = (EditText) findViewById(R.id.entryView);
        resultDisplay = (TextView) findViewById(R.id.resultView);
        Button convertButton = (Button) findViewById(R.id.convertButton);
        Button saveButton = (Button) findViewById(R.id.saveButton);

        spinnerFrom = (Spinner) findViewById(R.id.spinnerDropdown);
        spinnerTo = (Spinner) findViewById(R.id.spinnerDropdown2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.velocity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);
        spinnerFrom.setOnItemSelectedListener(this);
        spinnerTo.setOnItemSelectedListener(this);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = Double.parseDouble(convertFrom.getText().toString());
                convertUnits(spinner1, spinner2, value);
                showResult(result);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valueToSave = resultDisplay.getText().toString();
                appDatabase.child("Saved Conversions").setValue(valueToSave);

                resultDisplay.setText("");
                Toast.makeText(Velocity.this, "Result saved.", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.sub_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.homeIcon:
                Intent intent = new Intent(Velocity.this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinner1 = spinnerFrom.getSelectedItem().toString();
        spinner2 = spinnerTo.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void convertUnits(String spinner1, String spinner2, double value)
    {
        if(spinner1.contains("Miles Per Hour") && spinner2.contains("Kilometers Per Hour"))
        {
            result = value * 1.61;
        }
        else if(spinner1.contains("Kilometers Per Hour") && spinner2.contains("Miles Per Hour"))
        {
            result = value * 0.62;
        }
        else
        {
            String noConvert = "Not Applicable";
            Toast.makeText(this, noConvert, Toast.LENGTH_SHORT).show();

            result = 0;
        }
    }

    private void showResult(double result)
    {
        resultDisplay.setText(String.valueOf(result));
    }
}
