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
            listOfDistances.add("3 Feet");
            listOfDistances.add("5 Feet");
            listOfDistances.add("8 Feet");
            listOfDistances.add("10 Feet");
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
