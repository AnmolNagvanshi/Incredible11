package com.example.anmolnagvanshi.incredible11;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;






/**
 * An {@link FixtureAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link Fixture} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class FixtureAdapter extends ArrayAdapter<Fixture> {






    /**
     * Constructs a new {@link FixtureAdapter}.
     *
     * @param context of the app
     * @param fixtures is the list of earthquakes, which is the data source of the adapter
     */
    public FixtureAdapter(Context context, List<Fixture> fixtures) {
        super(context, 0, fixtures);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fixture_list_item_card, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        Fixture currentFixture = getItem(position);

        // Find the TextView with view ID magnitude
        TextView homeTeamName = listItemView.findViewById(R.id.hTeamName);
        // Format the magnitude to show 1 decimal place
        String name1 = currentFixture.getTeam1();
        // Display the magnitude of the current earthquake in that TextView
        homeTeamName.setText(name1);

        TextView awayTeamName = listItemView.findViewById(R.id.aTeamName);
        String name2 = currentFixture.getTeam2();
        awayTeamName.setText(name2);

        ImageView homeTeamView = listItemView.findViewById(R.id.hTeamView);
        String image1 = currentFixture.getTeam1Image();
        GlideApp.with(getContext()).load(image1).into(homeTeamView);

        ImageView awayTeamView = listItemView.findViewById(R.id.aTeamView);
        String image2 = currentFixture.getTeam2Image();
        GlideApp.with(getContext()).load(image2).into(awayTeamView);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }




    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
