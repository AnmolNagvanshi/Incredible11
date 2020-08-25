package com.example.anmolnagvanshi.incredible11;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PLayerSelection extends AppCompatActivity {

    private static final String LOG_TAG = PLayerSelection.class.getName();

    private static final String PLAYER_REQUEST = "https://d11api.000webhostapp.com/tdetails.php/";

    private PlayerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selection);
        Objects.requireNonNull(getSupportActionBar()).hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        Intent intent = getIntent();
        String message = intent.getStringExtra(Home.EXTRA_MESSAGE);
        String PLAYER_REQUEST_URL = PLAYER_REQUEST + message;

        final ListView playerListView = (ListView) findViewById(R.id.playerList);

        adapter = new PlayerAdapter(this, new ArrayList<Player>());

        playerListView.setAdapter(adapter);

//        Get the Intent that started this activity and extract the string

//        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               Player currentPlayer = mAdapter.getItem(position);
//
//            }
//        });

        PlayerAsyncTask task = new PlayerAsyncTask();
        task.execute(PLAYER_REQUEST_URL);
    }

    private class PlayerAsyncTask extends AsyncTask<String, Void, List<Player>> {

        @Override
        protected List<Player> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Player> result = QueryUtils.fetchPlayerData(urls[0]);
            return result;

        }

        @Override
        protected void onPostExecute(List<Player> data) {

            adapter.clear();

            if (data != null && !data.isEmpty()) {
                adapter.addAll(data);
            }
        }
    }
}
