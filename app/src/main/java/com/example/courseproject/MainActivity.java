package com.example.courseproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appToolbar);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.distanceButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Distance.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.volumeButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Volume.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.massButton);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Mass.class);
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.velocityButton);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Velocity.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.settingsButton:
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                return true;

            case R.id.retrieveData:
                Intent intent2 = new Intent(MainActivity.this, ConversionRetrieval.class);
                startActivity(intent2);
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void setTheme() {
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
        boolean appDark = shared.getBoolean("color_choice_dark", false);
        if(appDark)
        {
            setTheme(R.style.AppTheme);
            Toast.makeText(this, "Dark mode", Toast.LENGTH_SHORT).show();
        }
        else {
            setTheme(R.style.AppThemeLight);
            Toast.makeText(this, "Light mode", Toast.LENGTH_SHORT).show();
        }
    }


}
