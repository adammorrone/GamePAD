package com.example.adamm.gamepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import android.preference.*;

public class MainActivity extends AppCompatActivity {

    private EditText dobText;
    private EditText nameText;
    private EditText heightText;
    private EditText weightText;
    public static PatientList masterList;
    private PatientList currentList;

    /** This application's preferences */
    private static SharedPreferences settings;

    /** This application's settings editor*/
    private static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = findViewById(R.id.new_name);
        dobText = findViewById(R.id.new_dob);
        heightText = findViewById(R.id.new_height);
        weightText = findViewById(R.id.new_weight);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        String json = sharedPref.getString("stored_master_list", "");
        Gson gson = new Gson();

        if(json.equals(""))
            masterList = new PatientList();
        else
            masterList = gson.fromJson(json, PatientList.class);

        currentList = new PatientList();
        //masterList = new PatientList();
        //hideSoftKeyboard(this);
    }

        @Override
        public void onPause()
        {
        super.onPause();
        saveMasterList(this.getApplicationContext(), masterList);
    }

    public void createNewPatient(View v)
    {
        String name = nameText.getText().toString();
        String dob = dobText.getText().toString();
        String height = heightText.getText().toString();
        String weight = weightText.getText().toString();

        if(masterList.indexOf(name) == -1)
        {
            masterList.addPatient(name, dob, Integer.parseInt(height), Double.parseDouble(weight));
            goto_UserProfile(name);

            Toast toast = Toast.makeText(this, name + " was added as a new Patient", Toast.LENGTH_SHORT);
            toast.show();
            hideSoftKeyboard(this);
        }
        else
        {
            Toast toast = Toast.makeText(this, name + " is already a patient, here is the existing profile.", Toast.LENGTH_SHORT);
            toast.show();
            hideSoftKeyboard(this);
            goto_UserProfile(name);
        }
    }

    public void goto_UserProfile(String name)
    {
        int index = masterList.indexOf(name);
        Intent intent = new Intent(MainActivity.this, PatientOverviewActivity.class);
        intent.putExtra("Patient", index);
        startActivity(intent);
    }

    public void openMasterList(View v)
    {
        final CharSequence[] names = masterList.getNamesList();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select A Patient");
        builder.setItems(names, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                goto_UserProfile(names[which].toString());
            }
        });
        builder.show();
    }

    public void hideSoftKeyboard(Activity activity)
    {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void saveMasterList(Context context, PatientList master_list) {
        Gson gson = new Gson();
        String json = gson.toJson(master_list);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("stored_master_list", json);
        editor.commit();
    }
}