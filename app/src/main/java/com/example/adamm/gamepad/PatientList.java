package com.example.adamm.gamepad;

import java.util.ArrayList;

/**
 * Created by Adam on 3/31/2017.
 * Modified for as weighted list by Ernest.
 */

public class PatientList
{

    public ArrayList<Patient> listOfPatients;

    public PatientList()
    {
        listOfPatients = new ArrayList<Patient>();
    }

    public void addPatient(String name, String dob, String gender, double height, double weight)
    {
        listOfPatients.add(new Patient(name, dob, gender, height, weight));
    }

    public int getSize()
    {
        return listOfPatients.size();
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

    public ArrayList<Patient> getListOfPatients()
    {
        return listOfPatients;
    }

    public Patient getPatient(int index)
    {
        return listOfPatients.get(index);
    }

}
