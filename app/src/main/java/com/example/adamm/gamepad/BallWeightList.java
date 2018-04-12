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
        if(listOfBalls.size() == 0){
            listOfBalls.add("2 Ibs");
            listOfBalls.add("2.2 Ibs");
            listOfBalls.add("3 Ibs");
            listOfBalls.add("4 Ibs");
            listOfBalls.add("4.4 Ibs");
            listOfBalls.add("5 Ibs");
            listOfBalls.add("5.5 Ibs");
            listOfBalls.add("6 Ibs");
            listOfBalls.add("6.6 Ibs");
            listOfBalls.add("8 Ibs");
            listOfBalls.add("8.8 Ibs");
            listOfBalls.add("10 Ibs");
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
