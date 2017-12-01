package com.example.adamm.gamepad;


import java.util.ArrayList;

/**
 * Created by Adam on 3/31/2017.
 */

public class patient
{
    public String name;
    public String dob;
    public String gender;
    public ArrayList<Double> scores;

    public patient(String inName, String inDOB, String inGender)
    {
        scores = new ArrayList<>();
        name = inName;
        dob = inDOB;
        gender = inGender;
    }


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

    public String getName()
    {
        return name;
    }



    public double getAvgCost()
    {
        double sum = 0;

        for(int i = 0; i < scores.size(); i++)
            sum += scores.get(i);

        return sum;
    }






}
