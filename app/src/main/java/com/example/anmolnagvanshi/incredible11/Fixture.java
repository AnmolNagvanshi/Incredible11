package com.example.anmolnagvanshi.incredible11;



/**
 * An {@link Fixture} object contains information related to a single Fixture.
 */
public class Fixture {

    /**
     * Team1 of the fixture
     */
    private String mTeam1;

    /**
     * Team2 of the fixture
     */
    private String mTeam2;

    /**
     * Logo URL of Team1
     */
    private String mTeam1Image;

    /**
     * Logo URL of Team2
     */
      private String mTeam2Image;

    /**
     * Constructs a new {@link Fixture} object.
     *
     * @param team1         is the magnitude (size) of the earthquake
     * @param team2          is the location where the earthquake happened
     * @param team1Image is the time in milliseconds (from the Epoch) when the
     *                           earthquake happened
     * @param team2Image                is the website URL to find more details about the earthquake
     */

    public Fixture(String team1, String team2, String team1Image, String team2Image) {
        mTeam1 = team1;
        mTeam2 = team2;
        mTeam1Image = team1Image;
        mTeam2Image = team2Image;
    }

    public String getTeam1() {
        return mTeam1;
    }

    public String getTeam2() {
        return mTeam2;
    }

    public String getTeam1Image() {
        return mTeam1Image;
    }

    public String getTeam2Image() {
        return mTeam2Image;
    }
}