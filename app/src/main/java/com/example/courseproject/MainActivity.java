package com.example.courseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appToolbar);
        setSupportActionBar(toolbar);

        //might go in onOptionSelect
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.linearLayout, new SettingsPreferences())
//                .commit();

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
                //Intent or fragment leading to settings page here...
                return true;

            case R.id.retrieveData:
                //Intent or fragment
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
