package com.example.adamm.gamepad;

import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class NewGame extends Activity {

    //private EditText itemText;
    //private EditText nameText;
    //private EditText priceText;
    //private Button newItemButton;
    //private Button priceCheckButton;
    //private Button next;
    //private shoppingList masterList;
    private BallWeightList currentList;
    //private TextView counterView;
    //private TextView runningTotal;
    private BallWeightList ballList;
    private DistanceList distanceList;
    private TimeList timeList;

    //private Button start;
    private TextView disView;
    private TextView balView;
    private TextView timView;

    /** This application's preferences */
    private static SharedPreferences settings;

    /** This application's settings editor*/
    private static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        //itemText = (EditText) findViewById(R.id.topTB);
        //nameText = (EditText) findViewById(R.id.nameTB);
        //priceText = (EditText) findViewById(R.id.priceTB);
        //counterView = (TextView) findViewById(R.id.tally);
        //runningTotal = (TextView) findViewById(R.id.runningTotal);

        //Button distance;
        //Button time;

        //distance = findViewById(R.id.distanceButton);
        //time = findViewById(R.id.timeButton);
        //start = findViewById(R.id.beginButton);
        disView = findViewById(R.id.distance_textView);
        balView = findViewById(R.id.weight_textView);
        timView = findViewById(R.id.time_textView);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String bjson = sharedPref.getString("stored_ball_list", "");
        Gson bgson = new Gson();

        String djson = sharedPref.getString("stored_distance_list", "");
        Gson dgson = new Gson();

        String tjson = sharedPref.getString("stored_time_list", "");
        Gson tgson = new Gson();

        if(bjson.equals(""))
            ballList = new BallWeightList();
        else
            ballList = bgson.fromJson(bjson, BallWeightList.class);

        //currentList = new BallWeightList();

        if(djson.equals(""))
            timeList = new TimeList();
        else
            ballList = dgson.fromJson(bjson, BallWeightList.class);

        //currentList = new BallWeightList();

        if(tjson.equals(""))
            distanceList = new DistanceList();
        else
            ballList = tgson.fromJson(bjson, BallWeightList.class);

        //currentList = new BallWeightList();
        //masterList = new shoppingList();

        Button next = findViewById(R.id.beginButton);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent myIntent = new Intent (view.getContext(), PatientView.class);
                startActivityForResult(myIntent, 0);
                newGameInfo();
            }
        });

        ballList.fillArray();
        timeList.fillArray();
        distanceList.fillArray();

    }

    public void newGameInfo(){
        Intent intent = new Intent(NewGame.this, PatientView.class);
        intent.putExtra("distance", disView.getText());
        intent.putExtra("ball", balView.getText());
        intent.putExtra("time", timView.getText());
        startActivity(intent);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Gson gson = new Gson();
        String json = gson.toJson(ballList);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("stored_master_list", json);
        editor.apply();
    }

    public void openDistanceList(View v)
    {
        final CharSequence[] distance = distanceList.getDistanceList();
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);
        dbuilder.setTitle("Select Distance");
        dbuilder.setItems(distance, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                disView.setText(distance[which]);
            }
        });
        dbuilder.show();
    }

    public void openBallList(View v)
    {
        final CharSequence[] weights = ballList.getBallWeightList();
        AlertDialog.Builder bbuilder = new AlertDialog.Builder(this);
        bbuilder.setTitle("Select Ball Weight");
        bbuilder.setItems(weights, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                balView.setText(weights[which]);
            }
        });
        bbuilder.show();
    }

    public void openTimeList(View v)
    {
        final CharSequence[] time = timeList.getTimeList();
        AlertDialog.Builder tbuilder = new AlertDialog.Builder(this);
        tbuilder.setTitle("Select a Timer");
        tbuilder.setItems(time, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) {
                timView.setText(time[which]);
            }
        });
        tbuilder.show();
    }

}