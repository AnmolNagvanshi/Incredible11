package com.example.anmolnagvanshi.incredible11;

/**
 * An {@link Fixture} object contains information related to a single Fixture.
 */
public class Fixture {

    /**
     * Team1 of the fixture
     */
    private String team1;

    /**
     * Team2 of the fixture
     */
    private String team2;

    /**
     * Logo URL of Team1
     */
    private String team1Image;

    /**
     * Logo URL of Team2
     */
      private String team2Image;

    public Fixture(String team1, String team2, String team1Image, String team2Image) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Image = team1Image;
        this.team2Image = team2Image;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTeam1Image() {
        return team1Image;
    }

    public String getTeam2Image() {
        return team2Image;
    }
}