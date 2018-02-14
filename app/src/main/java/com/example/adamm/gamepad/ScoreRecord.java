package com.example.adamm.gamepad;

import java.util.Date;

/**
 * Created by adamm on 2/14/2018.
 */

public class ScoreRecord {
    public double score;
    public String gameType;
    public Date date;

    public ScoreRecord(double inScore, String inGameType, Date inDate)
    {
        score = inScore;
        date = inDate;
        gameType = inGameType;
    }

    public double getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }

    public String getGameType() {
        return gameType;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    @Override
    public String toString() {
        return "ScoreRecord{" +
                "score=" + score +
                ", gameType='" + gameType + '\'' +
                ", date=" + date +
                '}';
    }
}
