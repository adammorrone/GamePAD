package com.example.adamm.gamepad;

import java.util.ArrayList;

/**
 * Created by Adam on 3/31/2017.
 * Modified for as weighted list by Ernest.
 */

public class BallWeightList
{

    public ArrayList<Integer>listOfBalls;

    public void fillArray(){
        int i = 6;
        if(listOfBalls.size() == 0)
            while(i<=10) {
                listOfBalls.add(i);
                i = i + 2;
            }
        else{} //Do nothing
    }


    public BallWeightList()
    {
        listOfBalls = new ArrayList<Integer>();
    }

    public void addBall(int weight)
    {
        listOfBalls.add(weight);
    }

    public int getBallSize()
    {
        return listOfBalls.size();
    }

    public CharSequence[] getBallWeightList(){
        CharSequence[] balls = new CharSequence[listOfBalls.size()];

        for (int i = 0; i < listOfBalls.size(); i++){
            balls[i] = listOfBalls.get(i).toString();
        }
        return balls;
    }


    //return smaller price is one exists
    //return -1 if no lower price exists
    // returns -2 if item DNE
    /*
    public double priceCheck(int index, double price)
    {
        return listOfItems.get(index).priceCheck(price);
    }

    public void addPrice(int index, double price)
    {
        listOfItems.get(index).addPrice(price);
    }


    public CharSequence[] getNamesList()
    {
        CharSequence[] names = new CharSequence[listOfItems.size()];

        for(int i = 0; i < listOfItems.size(); i++)
        {
            names[i] = listOfItems.get(i).getName();
        }

        return names;
    }

    public int indexOf(String name)
    {
        for(int i = 0; i < listOfItems.size(); i++)
        {
            if(listOfItems.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }

    public ArrayList<patient> getListOfItems()
    {
        return listOfItems;
    }

    public double getPrice(String name)
    {
        int index = indexOf(name);
        if(index > -1)
            return listOfItems.get(index).getAvgCost();

        return 0;
    }
    */





}
