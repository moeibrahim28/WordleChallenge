package org.example.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class User {
    private double gamesPlayed;
    private double gamesWon;
    private double winPercentage;
    private final String fileName="user.json";

    public User(){

    }

    public int getGamesPlayed() {
        return (int)gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return (int)gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public double getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(double winPercentage) {
        this.winPercentage = winPercentage;
    }

    @Override
    public String toString() {
        return "User{" +
                "gamesPlayed=" + gamesPlayed +
                ", gamesWon=" + gamesWon +
                ", winPercentage=" + winPercentage +
                '}';
    }
}
