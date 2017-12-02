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
            }
        });

        ballList.fillArray();
        timeList.fillArray();
        distanceList.fillArray();

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
/*
    public void openShoppingList(View v)
    {
        final CharSequence[] names = currentList.getNamesList();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Items in your Current Shopping List");
        builder.setItems(names, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                nameText.setText(names[which]);
            }
        });
        builder.show();
    }

    public void startNewList(View view)
    {
        currentList = new BallWeightList();

        hideSoftKeyboard(this);
        Snackbar.make(view, "You are now editting a new shopping list", Snackbar.LENGTH_LONG).show();
        runningTotal.setText("0.00");
    }


    public void hideSoftKeyboard(Activity activity)
    {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

*/

}

/*
    public void createItem(View v)
    {
        String name = itemText.getText().toString();
        if(masterList.indexOf(name) == -1)
        {
            masterList.addItem(name);
            currentList.addItem(name);
        }
        else
            currentList.addItem(name);

        counterView.setText(masterList.getSize() + "");
        itemText.setText("");
        //Snackbar.make(v, name + " was added to your shopping list", Snackbar.LENGTH_LONG).show();
        Toast toast = Toast.makeText(this, name + " was added to your shopping list", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void createItem()
    {
        String name = itemText.getText().toString();
        if(masterList.indexOf(name) == -1)
        {
            masterList.addItem(name);
            currentList.addItem(name);
        }
        else
            currentList.addItem(name);

        counterView.setText(masterList.getSize() + "");
        itemText.setText("");
        //Snackbar.make(view, name + " was added to your shopping list", Snackbar.LENGTH_LONG).show();
        Toast toast = Toast.makeText(this, name + " was added to your shopping list", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void addToCart(View v)
    {
        final double price = Double.parseDouble(priceText.getText().toString());
        final String name = nameText.getText().toString();
        int masterIndex = masterList.indexOf(name);


        if(masterIndex == -1)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Item Not Found");
            builder.setMessage("Would you like to add this item to your shopping list?");
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    //adds new item
                    itemText.setText(nameText.getText());
                    createItem();
                    priceCommit(name, price, 0);

                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    // User cancelled the dialog
                }
            });

            builder.show();
        }

        else if(masterList.priceCheck(masterIndex, price) > -1) {
            new AlertDialog.Builder(this)
                    .setTitle("TOO MUCH")
                    .setMessage("You once paid: " + masterList.priceCheck(masterIndex, price))
                    .show();
        }

        else
        {
            priceCommit(name, price, masterIndex);
        }
    }

    public void priceCommit(String name, double price, int index)
    {
        masterList.addPrice(index, price);

        double total = Double.parseDouble(runningTotal.getText().toString()) + price;
        runningTotal.setText(total + "");

        nameText.setText("");
        priceText.setText("");
        Toast toast = Toast.makeText(this, name + " was purchased for $" + price, Toast.LENGTH_SHORT);
        toast.show();
    }
*/