package com.example.anmolnagvanshi.incredible11;

public class Player {

    private String playerName;
    private String teamName;
    private String quality;
    private String playerImage;

    public Player(String playerName, String teamName, String quality, String playerImage) {
        this.playerName = playerName;
        this.teamName = teamName;
        this.quality = quality;
        this.playerImage = playerImage;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getQuality() {
        return quality;
    }

    public String getPlayerImage() {
        return playerImage;
    }
}
