package com.example.adamm.gamepad;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity  {

    private EditText itemText;
    private EditText nameText;
    private EditText priceText;
    private Button newItemButton;
    private Button priceCheckButton;
    private shoppingList masterList;
    private shoppingList currentList;
    private TextView counterView;
    private TextView runningTotal;

    /** This application's preferences */
    private static SharedPreferences settings;

    /** This application's settings editor*/
    private static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        itemText = (EditText) findViewById(R.id.new_name);
        nameText = (EditText) findViewById(R.id.new_dob);
        priceText = (EditText) findViewById(R.id.new_gender);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String json = sharedPref.getString("stored_master_list", "");
        Gson gson = new Gson();

        if(json.equals(""))
            masterList = new shoppingList();
        else
            masterList = gson.fromJson(json, shoppingList.class);

        currentList = new shoppingList();
        //masterList = new shoppingList();
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





    public void goto_MainActivity(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
