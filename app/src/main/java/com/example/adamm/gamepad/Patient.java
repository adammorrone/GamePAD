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
    public double height_inches;
    public double weight_pounds;

    public ArrayList<ScoreRecord> scores;

    public Patient(String inName, String inDOB, String inGender, double inHeight_inches, double inWeight_pounds)
    {
        scores = new ArrayList<>();
        name = inName;
        dob = inDOB;
        gender = inGender;
        height_inches = inHeight_inches;
        weight_pounds = inWeight_pounds;

        newScore(12, "Standard");
        newScore(1, "Standard");
        newScore(8, "Standard");
        newScore(11, "Standard");
        newScore(8, "Standard");
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
    public double getHeight() { return height_inches; }
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
    public void setHeight(int newHeight) { height_inches = newHeight; }

    public double getHeight_inches() {
        return height_inches;
    }

    public void setHeight_inches(double height_inches) {
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
        ScoreRecord sr = new ScoreRecord(score, gameType, Calendar.getInstance(), 0, 0, 0);
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
