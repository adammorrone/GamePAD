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
    public double throwingDistance;
    public int numThrows;
    public int time;
    public double work_kcal;
    public int massOfBall;
    public double power_watts;
    public double height;

    public ScoreRecord(double score_points, String gameType_string, Calendar date_cal,
                       double distance_ft, int throws_number, int time_seconds, int weight_lbs,
                       double height_inches)
    {
        score = score_points;
        date = date_cal;
        gameType = gameType_string;
        throwingDistance = distance_ft;
        numThrows = throws_number;
        time = time_seconds;
        massOfBall = weight_lbs;
        height = height_inches;
        calculateWork();
    }

    public ScoreRecord()
    {
        score = 0;
        date = null;
        gameType = "null";
        throwingDistance = 0;
        numThrows = 0;
        time = 0;
        massOfBall = 0;
        calculateWork();
    }

    public void calculateWork()
    {
        double g = 9.81;
        double d = throwingDistance * 0.305 * numThrows + 0.25 * score;
        double h = height * 0.025;
        double m = massOfBall * 0.454;

        work_kcal = m*g*h * numThrows + (m*d*d)/h;
        power_watts = work_kcal / (numThrows * Math.sqrt((2*h)/g));
        work_kcal = work_kcal * 0.00024;
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


        return  format.format(date.getTime()) +
                "\nGame = " + gameType +
                "\nScore = " + score + " points" +
                "\nWork = " + Double.toString(work_kcal).substring(0, 6) + " kcal" +
                "\n          ≈ " + Double.toString(Math.ceil(work_kcal / 0.35)).substring(0, 1)
                + " push ups" + //based on 0.35kcal per pushup
                "\nAverage Power = " + Double.toString(power_watts).substring(0, 6) + " Watts" +
                "\nBall Weight = " + massOfBall + " lbs" +
                "\nThrows = " + numThrows + " throws" +
                "\nThrowing Height = " + height + " inches" +
                "\nThrowing Distance = " + throwingDistance + " feet" +
                "\nTime = " + time + " seconds" +
                "\n";

    }

    public String toCSV()
    {
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");


        return  format.format(date.getTime()) +
                "\t" + gameType +
                "\t" + score +
                "\t" + Double.toString(work_kcal).substring(0, 6) +
                "\t" + Double.toString(Math.ceil(work_kcal / 0.35)).substring(0, 1) +
                "\t" + Double.toString(power_watts).substring(0, 6) +
                "\t" + massOfBall +
                "\t" + numThrows +
                "\t" + height +
                "\t" + throwingDistance +
                "\t" + time +
                "\n";

    }

    public double getThrowingDistance() {
        return throwingDistance;
    }

    public void setThrowingDistance(double throwingDistance) {
        this.throwingDistance = throwingDistance;
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

    public double getWork_kcal() {
        return work_kcal;
    }

    public void setWork_kcal(double work_kcal) {
        this.work_kcal = work_kcal;
    }


}
