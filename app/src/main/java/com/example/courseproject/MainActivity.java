package com.example.courseproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    private static final String GET_REQUEST = "https://api.weather.gov/zones/forecast/FLZ155/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        GetWeatherLocationAsync task = new GetWeatherLocationAsync();
        task.execute(GET_REQUEST);

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

        Button button2 = (Button) findViewById(R.id.temperatureButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Temperature.class);
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
            //Toast.makeText(this, "Dark mode", Toast.LENGTH_SHORT).show();
        }
        else {
            setTheme(R.style.AppThemeLight);
            //Toast.makeText(this, "Light mode", Toast.LENGTH_SHORT).show();
        }
    }


    private String sendHttpRequest(URL url) throws IOException
    {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else{
                Toast.makeText(this, "NOT WORKING", Toast.LENGTH_SHORT).show();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(urlConnection != null)
            {
                urlConnection.disconnect();
            }
            if(inputStream != null)
            {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException
    {
        StringBuilder output = new StringBuilder();
        if(inputStream != null)
        {
            InputStreamReader isr = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();

            while(line != null)
            {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private class GetWeatherLocationAsync extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            String jsonResponse = null;

            try
            {
                url = new URL(strings[0]);
                jsonResponse = sendHttpRequest(url);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String string) {
            JSONObject root = null;
            try
            {
                root = new JSONObject(string);
                JSONObject properties = root.getJSONObject("properties");
                String id = properties.getString("id");
                String county = properties.getString("name");
                String state = properties.getString("state");
                String location = "Location ID: " + id + "\nCounty: " + county + "\nState: " + state;
                Toast.makeText(MainActivity.this, location, Toast.LENGTH_LONG).show();
            }
            catch (JSONException je)
            {
                je.printStackTrace();
            }
        }
    }
}
