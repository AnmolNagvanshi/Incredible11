package com.example.anmolnagvanshi.incredible11;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class PLayerSelection extends AppCompatActivity {

    private static final String LOG_TAG = PLayerSelection.class.getName();

    private static final String PLAYER_REQUEST = "https://d11api.000webhostapp.com/tdetails.php/";

    private PlayerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selection);
        getSupportActionBar().hide();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        Intent intent = getIntent();
        String message = intent.getStringExtra(Home.EXTRA_MESSAGE);
        String PLAYER_REQUEST_URL = PLAYER_REQUEST + message;

        final ListView playerListView = (ListView) findViewById(R.id.playerList);

        mAdapter = new PlayerAdapter(this, new ArrayList<Player>());

        playerListView.setAdapter(mAdapter);

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

            mAdapter.clear();

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
