package com.example.adamm.gamepad;

import java.util.ArrayList;

/**
 * Modified for as weighted list by Ernest.
 */

public class DistanceList
{

    public ArrayList<Integer>listOfDistances;

    public void fillArray(){
        int i = 6;
        if(listOfDistances.size() == 0)
            while(i<=10) {
                listOfDistances.add(i);
                i = i + 2;
            }
        else{} //Do nothing
    }



    public DistanceList()
    {
        listOfDistances = new ArrayList<Integer>();
    }

    public void addDistance(int weight)
    {
        listOfDistances.add(weight);
    }

    public int getTimeSize()
    {
        return listOfDistances.size();
    }

    public CharSequence[] geetDistanceList(){
        CharSequence[] balls = new CharSequence[listOfDistances.size()];

        for (int i = 0; i < listOfDistances.size(); i++){
            balls[i] = listOfDistances.get(i).toString();
        }
        return balls;
    }

}
