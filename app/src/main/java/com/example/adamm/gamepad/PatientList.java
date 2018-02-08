package com.example.adamm.gamepad;

import java.util.ArrayList;

/**
 * Created by Adam on 3/31/2017.
 * Modified for as weighted list by Ernest.
 */

public class PatientList
{

    public ArrayList<Patient> listOfItems;




    public PatientList()
    {
        listOfItems = new ArrayList<Patient>();
    }

    public void addItem(String name, String dob, String gender, String height)
    {
        listOfItems.add(new Patient(name, dob, gender, height));
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

    public ArrayList<Patient> getListOfItems()
    {
        return listOfItems;
    }

    public String getDOB(String name)
    {
        int index = indexOf(name);
        if(index > -1)
            return listOfItems.get(index).getDOB();

        return null;
    }

    public String getName(String name)
    {
        int index = indexOf(name);
        if(index > -1)
            return listOfItems.get(index).getName();

        return null;
    }

    public String getGender(String name)
    {
        int index = indexOf(name);
        if(index > -1)
            return listOfItems.get(index).getGender();

        return null;
    }

    public String getDOB(int index)
    {
        if(index > -1)
            return listOfItems.get(index).getDOB();

        return null;
    }

    public String getName(int index)
    {
        if(index > -1)
            return listOfItems.get(index).getName();

        return null;
    }

    public String getGender(int index)
    {
        if(index > -1)
            return listOfItems.get(index).getGender();

        return null;
    }

    public void setDOB(int index, String dob)
    {
        if(index > -1)
            listOfItems.get(index).setDOB(dob);
    }

    public void setName(int index, String name)
    {
        if(index > -1)
            listOfItems.get(index).setName(name);

    }

    public void setGender(int index, String gender)
    {
        if(index > -1)
            listOfItems.get(index).setGender(gender);

    }





}
