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
    public String gender;
    public String height;
    public ArrayList<ScoreRecord> scores;

    public Patient(String inName, String inDOB, String inGender, String inHeight)
    {
        scores = new ArrayList<>();
        name = inName;
        dob = inDOB;
        gender = inGender;
        height = inHeight;
    }

    public String getName()
    {
        return name;
    }
    public String getDOB()
    {
        return dob;
    }
    public String getGender()
    {
        return gender;
    }
    public String getHeight() { return height; }
    public ArrayList<ScoreRecord> getScores() { return scores; }
    public void setName(String newName)
    {
        name = newName;
    }
    public void setDOB(String newDOB)
    {
        dob = newDOB;
    }
    public void setGender(String newGender)
    {
        gender = newGender;
    }
    public void setHeight(String newHeight) { height = newHeight; }
    public void newScore(double score, String gameType)
    {
        ScoreRecord sr = new ScoreRecord(score, gameType, Calendar.getInstance().getTime());
        scores.add(sr);
    }
    public double getAllTimeScores()
    {
        double sum = 0;

        for(int i = 0; i < scores.size(); i++)
            sum += scores.get(i).getScore();

        return sum;
    }
    public double getAvgScores()
    {
        double sum = 0;

        for(int i = 0; i < scores.size(); i++)
            sum += scores.get(i).getScore();

        return sum/scores.size();
    }
}
