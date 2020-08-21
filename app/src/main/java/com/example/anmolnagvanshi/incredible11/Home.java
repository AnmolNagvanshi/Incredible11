package com.example.anmolnagvanshi.incredible11;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private static final String LOG_TAG = Home.class.getName();

    public static final String EXTRA_MESSAGE = "com.example.myfanapp.MESSAGE";

    /** URL for earthquake data from the USGS dataset */
    private static final String WEB_REQUEST_URL =
            "https://d11api.000webhostapp.com/data.php";

    /** Adapter for the list of fixtures */
    private FixtureAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // Find a reference to the {@link ListView} in the layout
        ListView fixtureListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new FixtureAdapter(this, new ArrayList<Fixture>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        fixtureListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        fixtureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Fixture currentFixture = mAdapter.getItem(position);

                Intent intent = new Intent(Home.this, PLayerSelection.class);
                String homeTeam = currentFixture.getTeam1();
                String awayTeam = currentFixture.getTeam2();
                String teams = homeTeam + "." + awayTeam;
                intent.putExtra(EXTRA_MESSAGE, teams);
                startActivity(intent);

            }
        });


        // Start the AsyncTask to fetch the earthquake data
        FixtureAsyncTask task = new FixtureAsyncTask();
        task.execute(WEB_REQUEST_URL);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the list of earthquakes in the response.
     *
     * AsyncTask has three generic parameters: the input type, a type used for progress updates, and
     * an output type. Our task will take a String URL, and return an Earthquake. We won't do
     * progress updates, so the second generic is just Void.
     *
     * We'll only override two of the methods of AsyncTask: doInBackground() and onPostExecute().
     * The doInBackground() method runs on a background thread, so it can run long-running code
     * (like network activity), without interfering with the responsiveness of the app.
     * Then onPostExecute() is passed the result of doInBackground() method, but runs on the
     * UI thread, so it can use the produced data to update the UI.
     */
    private class FixtureAsyncTask extends AsyncTask<String, Void, List<Fixture>> {

        /**
         * This method runs on a background thread and performs the network request.
         * We should not update the UI from a background thread, so we return a list of
         * {@link Fixture}s as the result.
         */
        @Override
        protected List<Fixture> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Fixture> result = QueryUtils.fetchFixtureData(urls[0]);
            return result;
        }


        @Override
        protected void onPostExecute(List<Fixture> data) {
            // Clear the adapter of previous fixture data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
