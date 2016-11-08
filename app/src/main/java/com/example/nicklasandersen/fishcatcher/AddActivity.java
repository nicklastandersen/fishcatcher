package com.example.nicklasandersen.fishcatcher;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void addCatches(View view) {
        String angler_name = ((EditText) findViewById(R.id.add_catch_angler_name)).getText().toString();
        String datetime = ((EditText) findViewById(R.id.add_catch_datetime)).getText().toString();
        String fishing_method = ((EditText) findViewById(R.id.add_catch_fishing_method)).getText().toString();
        String breed = ((EditText) findViewById(R.id.add_catch_breed)).getText().toString();
        String length = ((EditText) findViewById(R.id.add_catch_length)).getText().toString();
        String weight = ((EditText) findViewById(R.id.add_catch_weight)).getText().toString();
        String weather = ((EditText) findViewById(R.id.add_catch_weather)).getText().toString();
        String location = ((EditText) findViewById(R.id.add_catch_location)).getText().toString();

        TextView messageView = (TextView) findViewById(R.id.add_catches_message_textview);
        try {
            JSONObject jsonObject = new JSONObject("catches");
            jsonObject.put("angler_name", angler_name);
            jsonObject.put("datetime", datetime);
            jsonObject.put("fishing_method", fishing_method);
            jsonObject.put("breed", breed);
            jsonObject.put("length", length);
            jsonObject.put("weight", weight);
            jsonObject.put("weather", weather);
            jsonObject.put("location", location);
            jsonObject.put("latitude", 4.4);
            jsonObject.put("longitude", 5.5);
            String jsonDocument = jsonObject.toString();
            messageView.setText(jsonDocument);
            PostCatchesTask task = new PostCatchesTask();
            task.execute("http://api.evang.dk/v1/catches", jsonDocument);
        } catch (JSONException ex) {
            messageView.setText(ex.getMessage());
        }
        finish();
    }

    private class PostCatchesTask extends AsyncTask<String, Void, CharSequence> {

        @Override
        protected CharSequence doInBackground(String... params) {
            String urlString = params[0];
            String jsonDocumet = params[1];
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
                osw.write(jsonDocumet);
                osw.flush();
                osw.close();
                int responseCode = connection.getResponseCode();
                if (responseCode / 100 != 2) {
                    String responseMessage = connection.getResponseMessage();
                    throw new IOException("HTTP response code: " + responseCode + " " + responseMessage);
                }
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = reader.readLine();
                return line;
            } catch (MalformedURLException ex) {
                cancel(true);
                String message = ex.getMessage() + " " + urlString;
                Log.e("CATCHES", message);
                return message;
            } catch (IOException ex) {
                cancel(true);
                Log.e("CATCHES", ex.getMessage());
                return ex.getMessage();
            }
        }

        @Override
        protected void onPostExecute(CharSequence charSequence) {
            super.onPostExecute(charSequence);
            TextView messageView = (TextView) findViewById(R.id.add_catches_message_textview);
            messageView.setText(charSequence);
        }

        @Override
        protected void onCancelled(CharSequence charSequence) {
            super.onCancelled(charSequence);
            TextView messageView = (TextView) findViewById(R.id.add_catches_message_textview);
            messageView.setText(charSequence);
        }
    }
}
