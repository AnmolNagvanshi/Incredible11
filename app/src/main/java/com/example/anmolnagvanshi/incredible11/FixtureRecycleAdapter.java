package com.example.anmolnagvanshi.incredible11;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FixtureRecycleAdapter extends
        RecyclerView.Adapter<FixtureRecycleAdapter.FixtureViewHolder> {

    private static final String TAG = FixtureRecycleAdapter.class.getName();
    private final ListItemClickListener listItemClickListener;
    private static int viewHolderCount;
    private int numberItems;
    private List<Fixture> fixtures;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    class FixtureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardView;
        TextView homeTeam;
        TextView awayTeam;
        ImageView homeTeamImage;
        ImageView awayTeamImage;

        public FixtureViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            homeTeam = itemView.findViewById(R.id.hTeamName);
            awayTeam = itemView.findViewById(R.id.aTeamName);
            homeTeamImage = itemView.findViewById(R.id.hTeamView);
            awayTeamImage = itemView.findViewById(R.id.aTeamView);
            // COMPLETED (7) Call setOnClickListener on the View passed into the constructor
            // (use 'this' as the OnClickListener)
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            listItemClickListener.onListItemClick(clickedPosition);

        }
    }

    public FixtureRecycleAdapter(ListItemClickListener onClickListener, int numberItems, List<Fixture> fixtures) {
        listItemClickListener = onClickListener;
        this.numberItems = numberItems;
        this.fixtures = fixtures;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public FixtureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.fixture_list_item_card;
        // boolean shouldAttachToParentImmediately = false;

        View view = LayoutInflater.from(context).inflate(layoutIdForListItem, viewGroup, false);
        FixtureViewHolder viewHolder = new FixtureViewHolder(view);

        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FixtureViewHolder fixtureViewHolder, int i) {
        Fixture currentFixture = fixtures.get(i);
        fixtureViewHolder.homeTeam.setText(currentFixture.getTeam1());
        fixtureViewHolder.awayTeam.setText(currentFixture.getTeam2());
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

}
