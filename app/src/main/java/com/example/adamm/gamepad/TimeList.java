package com.example.adamm.gamepad;

import java.util.ArrayList;

/**
 * Modified for as weighted list by Ernest.
 */

public class TimeList
{
    public ArrayList<String>listOfTimes;

    public void fillArray(){
        if(listOfTimes.size() == 0){
            listOfTimes.add("30 Seconds");
            listOfTimes.add("1 Minute");
            listOfTimes.add("1.5 Minutes");
            listOfTimes.add("2 Minutes");
            listOfTimes.add("2.5 Minutes");
            listOfTimes.add("3 Minutes");
        }
        else{} //Do nothing
    }


    public TimeList()
    {
        listOfTimes = new ArrayList<String>();
    }

    public void addItem(String time)
    {
        listOfTimes.add(time);
    }

    public int getSize() { return listOfTimes.size(); }

    public CharSequence[] getTimeList(){
        CharSequence[] times = new CharSequence[listOfTimes.size()];

        for (int i = 0; i < listOfTimes.size(); i++){
            times[i] = listOfTimes.get(i);
        }
        return times;
    }

    //return smaller price is one exists
    //return -1 if no lower price exists
    // returns -2 if item DNE
    /*
    public double priceCheck(int index, double price)
    {
        return listOfPatients.get(index).priceCheck(price);
    }

    public void addPrice(int index, double price)
    {
        listOfPatients.get(index).addPrice(price);
    }


    public CharSequence[] getNamesList()
    {
        CharSequence[] names = new CharSequence[listOfPatients.size()];

        for(int i = 0; i < listOfPatients.size(); i++)
        {
            names[i] = listOfPatients.get(i).getName();
        }

        return names;
    }

    public int indexOf(String name)
    {
        for(int i = 0; i < listOfPatients.size(); i++)
        {
            if(listOfPatients.get(i).getName().equals(name))
                return i;
        }
        return -1;
    }

    public ArrayList<patient> getListOfPatients()
    {
        return listOfPatients;
    }

    public double getPrice(String name)
    {
        int index = indexOf(name);
        if(index > -1)
            return listOfPatients.get(index).getAvgCost();

        return 0;
    }
    */





}
