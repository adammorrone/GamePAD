package com.example.adamm.gamepad;

import static java.sql.Types.NULL;

/**
 * Created by Kofi on 12/1/2017.
 * not needed
 * future proofing (visual dev)
 */

public class Ball {
    private double weight;
    private String size;
    private String color;


    public Ball(double w){
        setWeight(w);
    }

    private void setWeight(double w){weight = w;}
    public double getWeight(){return weight;}
    public String getColor(){
        return color;
    }

}
