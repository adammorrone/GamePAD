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
    public double totalDistance;
    public int numThrows;
    public int time;
    public int work;

    public ScoreRecord(double inScore, String inGameType, Calendar inDate, double inDistance, int inThrows, int time_seconds)
    {
        score = inScore;
        date = inDate;
        gameType = inGameType;
        totalDistance = inDistance;
        numThrows = inThrows;
        time = time_seconds;
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

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getNumThrows() {
        return numThrows;
    }

    public void setNumThrows(int numThrows) {
        this.numThrows = numThrows;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getWork() {
        return work;
    }

    public void setWork(int work) {
        this.work = work;
    }
}
