package com.example.adamm.gamepad;


import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Adam on 3/31/2017.
 */

public class Patient
{
    public String name;
    public String dob;
    public int height_inches;
    public double weight_pounds;
    public String weight_setting;
    public String distance_setting;
    public String time_setting;

    public ArrayList<ScoreRecord> scores;

    public Patient(String inName, String inDOB, int inHeight, double inWeight_pounds)
    {
        scores = new ArrayList<>();
        name = inName;
        dob = inDOB;
        height_inches = inHeight;
        weight_pounds = inWeight_pounds;
        weight_setting = "-1";
        distance_setting = "-1";
        time_setting = "-1";
    }

    public String getName()
    {
        return name;
    }
    public String getDOB()
    {
        return dob;
    }
    public int getHeight() { return height_inches; }
    public ArrayList<ScoreRecord> getScores() { return scores; }
    public void setName(String newName)
    {
        name = newName;
    }
    public void setDOB(String newDOB)
    {
        dob = newDOB;
    }
    public void setHeight(int newHeight) { height_inches = newHeight; }

    public void setWeight(double newWeight) { weight_pounds = newWeight; }



    public double getHeight_inches() {
        return height_inches;
    }

    public void setHeight_inches(int height_inches) {
        this.height_inches = height_inches;
    }

    public double getWeight_pounds() {
        return weight_pounds;
    }

    public void setWeight_pounds(double weight_pounds) {
        this.weight_pounds = weight_pounds;
    }

    public void addScore(ScoreRecord sr)
    {
        scores.add(sr);
    }

    public void newScore(double score, String gameType)
    {
        ScoreRecord sr = new ScoreRecord(score, gameType, Calendar.getInstance(),
                10, 10, 60, 5, 50);
        scores.add(sr);
    }
    public double getHighScore()
    {
        double high = 0;
        double temp = 0;

        for(int i = 0; i < scores.size(); i++)
        {
            temp = scores.get(i).getScore();
            if(temp > high)
                high = temp;
        }
        return high;
    }

    public double getAvgScores()
    {
        double sum = 0;

        for(int i = 0; i < scores.size(); i++)
            sum += scores.get(i).getScore();

        return sum/scores.size();
    }

    public String getWeight_setting() {
        return weight_setting;
    }

    public void setWeight_setting(String weight_setting) {
        this.weight_setting = weight_setting;
    }

    public String getDistance_setting() {
        return distance_setting;
    }

    public void setDistance_setting(String distance_setting) {
        this.distance_setting = distance_setting;
    }

    public String getTime_setting() {
        return time_setting;
    }

    public void setTime_setting(String time_setting) {
        this.time_setting = time_setting;
    }
}
