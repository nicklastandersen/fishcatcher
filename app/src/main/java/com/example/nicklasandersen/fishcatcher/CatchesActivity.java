package com.example.nicklasandersen.fishcatcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CatchesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_catcher);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ReadTask task = new ReadTask();
        task.execute("http://api.evang.dk/v1/catches");
    }

    private class ReadTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence charSequence) {
            super.onPostExecute(charSequence);
            //TextView messageTextView = (TextView) findViewById(R.id.main_message_textview);
            //messageTextView.setText(charSequence);
            final List<Catches> catchess = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(charSequence.toString());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    String angler_name = obj.getString("angler_name");
                    String datetime = obj.getString("datetime");
                    String fishing_method = obj.getString("fishing_method");
                    String breed = obj.getString("breed");
                    String length = obj.getString("length");
                    String weight = obj.getString("weight");
                    String weather = obj.getString("weather");
                    String location = obj.getString("location");
                    double latitude = obj.getDouble("latitude");
                    double longitude = obj.getDouble("longitude");
                    int id = obj.getInt("Id");
                    Catches catches = new Catches(id, angler_name, datetime, fishing_method, breed, length, weight, weather, location, latitude, longitude);
                    catchess.add(catches);
                }

                ListView listView = (ListView) findViewById(R.id.main_listView_catches);
                ArrayAdapter<Catches> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, catchess);
                //CatchesListItemAdapter adapter = new CatchesListItemAdapter(getBaseContext(), R.layout.booklist_item, books);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getBaseContext(), CatchesActivity.class);
                        intent.putExtra("Catches", catchess.get((int) id));
                        startActivity(intent);
                    }
                });
            } catch (JSONException ex) {
                //messageTextView.setText(ex.getMessage());
                Log.e("BOOKS", ex.getMessage());
            }
        }
    }
}
