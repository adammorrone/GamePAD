package com.example.adamm.gamepad;

import java.util.ArrayList;

import android.app.AlertDialog;

import java.util.ArrayList;

/**
 * Created by Adam on 3/31/2017.
 */

public class shoppingList
{

    public ArrayList<patient> listOfItems;




    public shoppingList()
    {
        listOfItems = new ArrayList<patient>();
    }

    public void addItem(String name, String dob, String gender)
    {
        listOfItems.add(new patient(name, dob, gender));
    }

    public int getSize()
    {
        return listOfItems.size();
    }


    //return smaller price is one exists
    //return -1 if no lower price exists
    // returns -2 if item DNE
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





}
