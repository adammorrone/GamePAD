package com.example.adamm.gamepad;

import java.util.ArrayList;

/**
 * Created by Adam on 3/31/2017.
 * Modified for as weighted list by Ernest.
 */

public class BallWeightList
{

    public ArrayList<String>listOfBalls;

    public void fillArray(){
        int i = 6;
        if(listOfBalls.size() == 0)
            while(i<=10) {
                listOfBalls.add(i + " Ibs");
                i = i + 2;
            }
        else{} //Do nothing
    }


    public BallWeightList()
    {
        listOfBalls = new ArrayList<String>();
    }

    public void addBall(int weight)
    {
        listOfBalls.add(weight + " Ibs");
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

}
