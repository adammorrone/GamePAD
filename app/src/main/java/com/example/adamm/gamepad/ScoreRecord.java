package com.example.adamm.gamepad;

import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Created by adamm on 2/14/2018.
 */

public class ScoreRecord {
    public double score;
    public String gameType;
    public Calendar date;

    public ScoreRecord(double inScore, String inGameType, Calendar inDate)
    {
        score = inScore;
        date = inDate;
        gameType = inGameType;
    }

    public double getScore() {
        return score;
    }

    public Calendar getDate() {
        return date;
    }

    public String getGameType() {
        return gameType;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    @Override
    public String toString()
    {
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");

        return  format.format(date.getTime()) + "\nScore = " + score +
                "\nGame = " + gameType + "\n";
    }
}
