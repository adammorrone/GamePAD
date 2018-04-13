package com.example.adamm.gamepad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class EditPatientInfo  extends AppCompatActivity  {

    private TextView currentNameText;
    private PatientList masterList = MainActivity.masterList;
    private TextView currentDOBText;
    private TextView currentWeightText;
    private TextView currentHeightText;
    private EditText newDOBText;
    private EditText newNameText;
    private EditText newHeightText;
    private EditText newWeightText;
    private int index = -1;
    public String address;



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
        currentHeightText = findViewById(R.id.height_editView);
        currentWeightText = findViewById(R.id.weight_editView);
        newNameText = findViewById(R.id.edited_name);
        newDOBText = findViewById(R.id.edited_dob);
        newHeightText = findViewById(R.id.edited_height);
        newWeightText = findViewById(R.id.edited_weight);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //masterList = new PatientList();

        resetCurrentValues();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    public void editName(View view)
    {
        String name = newNameText.getText().toString();
        masterList.getPatient(index).setName(name);
        resetCurrentValues();
    }

    public void editDOB(View view)
    {
        String dob = newDOBText.getText().toString();
        masterList.getPatient(index).setDOB(dob);
        resetCurrentValues();
    }

    public void editHeight(View view)
    {
        try {
            int height = Integer.parseInt(newHeightText.getText().toString());
            masterList.getPatient(index).setHeight(height);
            resetCurrentValues();
        }catch(Exception ex) {

            Toast.makeText(getApplicationContext(), "Something went wrong. Ensure that all fields are filled out and that Hieght and Weight are only whole numbers",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void editWeight(View view)
    {
        try {
            double weight = Double.parseDouble(newWeightText.getText().toString());
            masterList.getPatient(index).setWeight(weight);
            resetCurrentValues();
        }catch(Exception ex) {

            Toast.makeText(getApplicationContext(), "Something went wrong. Ensure that all fields are filled out and that Hieght and Weight are only whole numbers",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void resetCurrentValues() {
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        index = (int) b.get("Patient");
        address = "NULL";

        if (b != null) {
            String name = masterList.getPatient(index).getName();
            currentNameText.setTextSize(15);
            currentNameText.setText(name);

            String dob = masterList.getPatient(index).getDOB();
            currentDOBText.setTextSize(15);
            currentDOBText.setText(dob);

            int height = masterList.getPatient(index).getHeight();
            currentHeightText.setTextSize(15);
            currentHeightText.setText("" + height);

            double weight = masterList.getPatient(index).getWeight_pounds();
            currentWeightText.setTextSize(15);
            currentWeightText.setText("" + weight);

        }
    }

    public void saveChanges(View view)
    {
        MainActivity.saveMasterList(this.getApplicationContext(), masterList);
        Intent intent = new Intent(EditPatientInfo.this, PatientOverviewActivity.class);
        intent.putExtra("Patient", index);
        intent.putExtra("Address", address);
        startActivity(intent);
    }
}