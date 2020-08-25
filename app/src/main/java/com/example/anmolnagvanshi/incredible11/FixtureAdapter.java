package com.example.anmolnagvanshi.incredible11;

import android.content.Context;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * An {@link FixtureAdapter} knows how to create a list item layout for each fixture
 * in the data source (a list of {@link Fixture} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class FixtureAdapter extends ArrayAdapter<Fixture> {

    /**
     * Constructs a new {@link FixtureAdapter}.
     *
     * @param context  of the app
     * @param fixtures is the list of fixtures, which is the data source of the adapter
     */
    public FixtureAdapter(Context context, List<Fixture> fixtures) {
        super(context, 0, fixtures);
    }

    /**
     * Returns a list item view that displays information about the fixture at the given position
     * in the list of fixtures.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fixture_list_item_card, parent, false);
        }

        // Find the fixture at the given position in the list of fixtures
        Fixture currentFixture = getItem(position);

        TextView homeTeamName = listItemView.findViewById(R.id.hTeamName);
        assert currentFixture != null;
        String name1 = currentFixture.getTeam1();
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

}
