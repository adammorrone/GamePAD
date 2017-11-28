package com.example.adamm.gamepad;


import java.util.ArrayList;

/**
 * Created by Adam on 3/31/2017.
 */

public class listItem
{
    public String name;
    public ArrayList<Double> prices;

    public listItem(String inName)
    {
        prices = new ArrayList<>();
        name = inName;
    }

    //if there exists a smaller value in the price history, return smallest value
    //else returns -1
    public double priceCheck(double price)
    {
        double smallest = -1;
        for(int i = 0; i < prices.size() - 1; i++)
        {
            double tempPrice = prices.get(i);
            if(tempPrice < price && (tempPrice < smallest || smallest == -1))
                smallest = tempPrice;
        }

        return smallest;
    }

    public void addPrice(double price)
    {
        prices.add(price);
    }

    public String getName()
    {
        return name;
    }



    public double getAvgCost()
    {
        double sum = 0;

        for(int i = 0; i < prices.size(); i++)
            sum += prices.get(i);

        return sum;
    }






}
