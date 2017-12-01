package com.example.adamm.gamepad;

import java.util.ArrayList;

/**
 * Modified for as weighted list by Ernest.
 */

public class TimeList
{
    public ArrayList<Integer>listOfTimes;

    public void fillArray(){
        int i = 6;
        if(listOfTimes.size() == 0)
            while(i<=10) {
                listOfTimes .add(i);
                i = i + 2;
            }
        else{} //Do nothing
    }


    public TimeList()
    {
        listOfTimes = new ArrayList<Integer>();
    }

    public void addItem(int weight)
    {
        listOfTimes.add(weight);
    }

    public int getSize() { return listOfTimes.size(); }

    public CharSequence[] getBallWeightList(){
        CharSequence[] balls = new CharSequence[listOfTimes.size()];

        for (int i = 0; i < listOfTimes.size(); i++){
            balls[i] = listOfTimes.get(i).toString();
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
