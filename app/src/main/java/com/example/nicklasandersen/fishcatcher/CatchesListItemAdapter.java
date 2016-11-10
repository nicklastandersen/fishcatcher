package com.example.nicklasandersen.fishcatcher;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CatchesListItemAdapter extends ArrayAdapter<Catches> {

    private final int resource;

    public CatchesListItemAdapter(Context context, int resource, List<Catches> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Catches catches = getItem(position);
        String angler_name = catches.getAngler_name();
        String datetime = catches.getDatetime();
        String fishing_method = catches.getFishing_method();
        String breed = catches.getBreed();
        String length = catches.getLength();
        String weight = catches.getWeight();
        String weather = catches.getWeather();
        String locations = catches.getLocation();
        double latitude = catches.getLatitude();
        double longitude = catches.getLongitude();

        LinearLayout catchesView;
        if (convertView == null) {
            catchesView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, catchesView, true);
        } else {
            catchesView = (LinearLayout) convertView;
        }
        TextView anglerNameView = (TextView) catchesView.findViewById(R.id.show_catch_angler_name);
        TextView datetimeView = (TextView) catchesView.findViewById(R.id.show_catch_datetime);
        TextView breedView = (TextView) catchesView.findViewById(R.id.show_catch_breed);
        anglerNameView.setText(angler_name);
        datetimeView.setText(datetime);
        breedView.setText(breed);
        return catchesView;
    }
}
