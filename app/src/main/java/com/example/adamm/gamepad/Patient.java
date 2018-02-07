package com.example.adamm.gamepad;


import java.util.ArrayList;

/**
 * Created by Adam on 3/31/2017.
 */

public class Patient
{
    public String name;
    public String dob;
    public String gender;
    public String height;
    public ArrayList<Double> scores;

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

    public void setName(String newName)
    {
        name = newName;
    }
    public void setDOB(String newName)
    {
        name = newName;
    }
    public void setGender(String newName)
    {
        name = newName;
    }
    public void setHeight(String newHeight) { height = newHeight; }





    //if there exists a smaller value in the price history, return smallest value
    //else returns -1
    public double priceCheck(double price)
    {
        double smallest = -1;
        for(int i = 0; i < scores.size() - 1; i++)
        {
            double tempPrice = scores.get(i);
            if(tempPrice < price && (tempPrice < smallest || smallest == -1))
                smallest = tempPrice;
        }

        return smallest;
    }

    public void addPrice(double price)
    {
        scores.add(price);
    }



    public double getAvgCost()
    {
        double sum = 0;

        for(int i = 0; i < scores.size(); i++)
            sum += scores.get(i);

        return sum;
    }
}
