package com.example.adamm.gamepad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;

/**
 * Created by adamm on 2/2/2018.
 */

public class EditPatientInfo  extends AppCompatActivity  {

    private TextView currentNameText;
    private PatientList masterList = MainActivity.masterList;
    private TextView currentDOBText;
    private TextView currentGenderText;
    private EditText newDOBText;
    private EditText newNameText;
    private EditText newGenderText;
    private int index = -1;



    /** This application's preferences */
    private static SharedPreferences settings;

    /** This application's settings editor*/
    private static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);
        currentNameText = findViewById(R.id.name_editView);
        currentDOBText = findViewById(R.id.dob_editView);
        currentGenderText = findViewById(R.id.gender_editView);
        newNameText = findViewById(R.id.edited_name);
        newDOBText = findViewById(R.id.edited_dob);
        newGenderText = findViewById(R.id.edited_gender);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //masterList = new PatientList();

        resetCurrentValues();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        saveMasterList();
    }

    public void editName(View view)
    {
        String name = newNameText.getText().toString();
        masterList.setName(index, name);
        resetCurrentValues();
    }

    public void editDOB(View view)
    {
        String dob = newDOBText.getText().toString();
        masterList.setDOB(index, dob);
        resetCurrentValues();
    }

    public void progressivelyEditGender(View view)
    {
        String gender = newGenderText.getText().toString();
        masterList.setGender(index, gender);
        resetCurrentValues();
    }

    public void resetCurrentValues() {
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        index = (int) b.get("Patient");

        if (b != null) {
            String name = masterList.getName(index);
            currentNameText.setTextSize(15);
            currentNameText.setText(name);

            String dob = masterList.getDOB(index);
            currentDOBText.setTextSize(15);
            currentDOBText.setText(dob);

            String gender = masterList.getGender(index);
            currentGenderText.setTextSize(15);
            currentGenderText.setText(gender);
        }
        saveMasterList();
    }

    public void saveMasterList() {
        Gson gson = new Gson();
        String json = gson.toJson(masterList);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("stored_master_list", json);
        editor.commit();
    }

}