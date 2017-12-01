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

public class MainActivity extends AppCompatActivity {

    private EditText dobText;
    private EditText nameText;
    private EditText genderText;
    private PatientList masterList;
    private PatientList currentList;
    private PatientList testLIst;

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
        genderText = findViewById(R.id.new_gender);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String json = sharedPref.getString("stored_master_list", "");
        Gson gson = new Gson();

        if(json.equals(""))
            masterList = new PatientList();
        else
            masterList = gson.fromJson(json, PatientList.class);

        currentList = new PatientList();
        //masterList = new PatientList();
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

    public void createItem(View v)
    {
        String name = nameText.getText().toString();
        String dob = dobText.getText().toString();
        String gender = genderText.getText().toString();

        if(masterList.indexOf(name) == -1)
        {
            masterList.addItem(name, dob, gender);
            currentList.addItem(name, dob, gender);
        }
        else
            currentList.addItem(name, dob, gender);

        nameText.setText("");
        //Snackbar.make(v, name + " was added to your shopping list", Snackbar.LENGTH_LONG).show();
        Toast toast = Toast.makeText(this, name + " was added as a new Patient", Toast.LENGTH_SHORT);
        toast.show();
        hideSoftKeyboard(this);
    }

    public void goto_UserProfile()
    {
        Intent intent = new Intent(MainActivity.this, PatientOverviewActivity.class);
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
                nameText.setText(names[which]);
                goto_UserProfile();
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

}
