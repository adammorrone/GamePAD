package com.example.adamm.gamepad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import org.w3c.dom.Text;

/**
 * Created by adamm on 2/2/2018.
 */

public class EditPatientInfo  extends AppCompatActivity  {

    private TextView patientNameText;
    private PatientList masterList = MainActivity.masterList;
    private TextView patientDOBText;
    private TextView patientGenderText;



    /** This application's preferences */
    private static SharedPreferences settings;

    /** This application's settings editor*/
    private static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);
        patientNameText = findViewById(R.id.name_editView);
        patientDOBText = findViewById(R.id.dob_editView);
        patientGenderText = findViewById(R.id.gender_editView);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //masterList = new PatientList();

        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        int index = (int)b.get("Patient");

        if (b != null) {
            String name = masterList.getName(index);
            patientNameText.setTextSize(15);
            patientNameText.setText(name);

            String dob = masterList.getDOB(index);
            patientDOBText.setTextSize(15);
            patientDOBText.setText(dob);

            String gender = masterList.getGender(index);
            patientDOBText.setTextSize(15);
            patientDOBText.setText(gender);

        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Gson gson = new Gson();
        String json = gson.toJson(masterList);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("stored_master_list", json);
        editor.commit();
    }



}

