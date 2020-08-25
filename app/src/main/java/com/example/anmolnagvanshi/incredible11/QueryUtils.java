package com.example.anmolnagvanshi.incredible11;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving fixture data.
 */
public final class QueryUtils {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the api and return a list of {@link Fixture} objects.
     */
    public static List<Fixture> fetchFixtureData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Fixture}s
        List<Fixture> fixtures = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Fixture}s
        return fixtures;
    }

    public static List<Player> fetchPlayerData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);

        }

        List<Player> players = extractPlayerFromJson(jsonResponse);
        return players;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            //urlConnection.setReadTimeout(10000 /* milliseconds */);
            //urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the fixture JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link Fixture} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<Fixture> extractFeatureFromJson(String fixtureJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(fixtureJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding fixtures to
        List<Fixture> fixtures = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string

            JSONArray fixtureArray = new JSONArray(fixtureJSON);

            // For each fixture in the earthquakeArray, create an {@link Fixture} object
            for (int i = 0; i < fixtureArray.length(); i++) {

                // Get a single fixture at position i within the list of fixtures
                JSONObject currentFixture = fixtureArray.getJSONObject(i);

                String team1 = currentFixture.getString("team1");
                String team2 = currentFixture.getString("team2");

                String team1Image = currentFixture.getString("team1url");
                String team2Image = currentFixture.getString("team2url");

                // Create a new {@link Fixture} object from the JSON response.
                Fixture fixture = new Fixture(team1, team2, team1Image, team2Image);

                // Add the new {@link Fixture} to the list of fixtures.
                fixtures.add(fixture);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the fixture JSON results", e);
        }

        // Return the list of fixtures
        return fixtures;
    }


    private static List<Player> extractPlayerFromJson(String playerJson) {
        if (TextUtils.isEmpty(playerJson)) {
            return null;
        }

        List<Player> players = new ArrayList<>();

        try {
            JSONArray playerArray = new JSONArray(playerJson);

            for (int i = 0; i < playerArray.length(); i++) {

                JSONObject currentPlayer = playerArray.getJSONObject(i);

                String playerName = currentPlayer.getString("pname");
                String playerTeamName = currentPlayer.getString("pteam");
                String playerQuality = currentPlayer.getString("quality");
                String playerImageUrl = currentPlayer.getString("purl");

                Player player = new Player(playerName, playerTeamName, playerQuality, playerImageUrl);
                players.add(player);
            }


        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the fixture JSON results", e);
            e.printStackTrace();
        }

        return players;
    }

}
