package com.example.anmolnagvanshi.incredible11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFixture extends AppCompatActivity implements FixtureRecycleAdapter.ListItemClickListener{

    private static final int NUM_LIST_ITEMS = 100;
    private static final String WEB_REQUEST_URL =
            "https://d11api.000webhostapp.com/data.php";


    private FixtureRecycleAdapter fixtureAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fixture);

        recyclerView = findViewById(R.id.rv_fixtures);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // Give the RecyclerView a default layout manager.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fixtureAdapter = new FixtureRecycleAdapter(this, NUM_LIST_ITEMS, new ArrayList<Fixture>());
        recyclerView.setAdapter(fixtureAdapter);

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
