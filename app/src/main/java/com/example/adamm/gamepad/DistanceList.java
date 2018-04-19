package com.example.adamm.gamepad;

import java.util.ArrayList;

/**
 * Modified for as weighted list by Ernest.
 */

public class DistanceList
{

    public ArrayList<String>listOfDistances;

    public void fillArray(){
        if(listOfDistances.size() == 0){
            listOfDistances.add("2 Feet");
            listOfDistances.add("2.5 Feet");
            listOfDistances.add("3 Feet");
            listOfDistances.add("3.5 Feet");
            listOfDistances.add("4 Feet");
            listOfDistances.add("4.5 Feet");
            listOfDistances.add("5 Feet");
            listOfDistances.add("5.5 Feet");
            listOfDistances.add("6 Feet");
            listOfDistances.add("6.5 Feet");
            listOfDistances.add("7 Feet");
            listOfDistances.add("7.5 Feet");
            listOfDistances.add("8 Feet");
            listOfDistances.add("8.5 Feet");
            listOfDistances.add("9 Feet");
            listOfDistances.add("9.5 Feet");
            listOfDistances.add("10 Feet");
            listOfDistances.add("10.5 Feet");
            listOfDistances.add("11 Feet");
        }
        else{} //Do nothing
    }



    public DistanceList()
    {
        listOfDistances = new ArrayList<String>();
    }

    public void addDistance(String distance)
    {
        listOfDistances.add(distance);
    }

    public int getSize()
    {
        return listOfDistances.size();
    }

    public CharSequence[] getDistanceList(){
        CharSequence[] distances = new CharSequence[listOfDistances.size()];

        for (int i = 0; i < listOfDistances.size(); i++){
            distances[i] = listOfDistances.get(i).toString();
        }
        return distances;
    }

}
