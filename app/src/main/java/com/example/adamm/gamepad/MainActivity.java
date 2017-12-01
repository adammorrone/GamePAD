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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.design.widget.Snackbar;
import android.widget.Toast;
import com.google.gson.Gson;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);
        itemText = findViewById(R.id.topTB);
        nameText = findViewById(R.id.nameTB);
        priceText = findViewById(R.id.priceTB);
        counterView = findViewById(R.id.tally);
        runningTotal = findViewById(R.id.runningTotal);
        Button button = findViewById(R.id.next_button);

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

    public void openMasterList(View v)
    {
        final CharSequence[] names = masterList.getNamesList();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Items in your Master List");
        builder.setItems(names, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                itemText.setText(names[which]);
                createItem();
            }
        });
        builder.show();
    }

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
        currentList = new shoppingList();

        hideSoftKeyboard(this);
        Snackbar.make(view, "You are now editting a new shopping list", Snackbar.LENGTH_LONG).show();
        runningTotal.setText("0.00");
    }

    public void beginShopping(View view)
    {
        hideSoftKeyboard(this);
        double priceEst = getPriceEstimate();
        Snackbar.make(view, "You shopping trip is estimated to cost: $" + priceEst, Snackbar.LENGTH_LONG).show();
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

    public double getPriceEstimate()
    {
        double sum = 0;

        ArrayList<patient> list = currentList.getListOfItems();

        for(int i = 0; i < list.size(); i++)
            sum += masterList.getPrice(list.get(i).getName());

        return sum/list.size();
    }

    public void nextScreen(View view)
    {
        Intent intent = new Intent(MainActivity.this, UserProfile.class);
        startActivity(intent);
    }

}
