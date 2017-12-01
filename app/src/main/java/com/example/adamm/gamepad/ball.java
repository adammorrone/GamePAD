package com.example.adamm.gamepad;

import static java.sql.Types.NULL;

/**
 * Created by Kofi on 12/1/2017.
 * not needed
 * future proofing (visual dev)
 */

public class ball {
    private double weight;
    private String size;
    private String color;


    public ball(double w){
        setWeight(w);
    }

    public void setWeight(double w){weight = w;}
    public double getWeight(){return weight;}
    public String getColor(){
        return color;
    }

}
