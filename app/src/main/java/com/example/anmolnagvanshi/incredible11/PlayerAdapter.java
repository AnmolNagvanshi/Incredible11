package com.example.anmolnagvanshi.incredible11;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlayerAdapter extends ArrayAdapter<Player> {

    public PlayerAdapter(Context context, List<Player> players) {
        super(context, 0, players);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.player_list_item, parent, false);
        }

        // Find the fixture at the given position in the list of fixtures
        Player currentPlayer = getItem(position);

        TextView playerNameText = listItemView.findViewById(R.id.playerNameText);
        assert currentPlayer != null;
        String playerName = currentPlayer.getPlayerName();
        playerNameText.setText(playerName);

        TextView teamNameText = listItemView.findViewById(R.id.teamNameText);
        String teamName = currentPlayer.getTeamName();
        teamNameText.setText(teamName);

        TextView playerQuality = listItemView.findViewById(R.id.playerQuality);
        String quality = currentPlayer.getQuality();
        playerQuality.setText(quality);

        ImageView playerImageView = listItemView.findViewById(R.id.playerImageView);
        String image = currentPlayer.getPlayerImage();
        GlideApp.with(getContext()).load(image).into(playerImageView);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

}

