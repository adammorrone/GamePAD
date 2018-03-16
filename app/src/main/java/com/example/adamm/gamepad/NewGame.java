package com.example.adamm.gamepad;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Set;

public class NewGame extends Activity {

    private BallWeightList ballList;
    private DistanceList distanceList;
    private TimeList timeList;
    private TextView disView;
    private TextView balView;
    private TextView timView;
    private String address;
    public int index = -1;
    public PatientList masterList = MainActivity.masterList;

    //Bluetooth
    private BluetoothAdapter btAdapter;
    private Set<BluetoothDevice> pairedDevices;
    ArrayList<String> paired;
    ListView pairedDeviceList;

    /** This application's preferences */
    private static SharedPreferences settings;

    /** This application's settings editor*/
    private static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);

        disView = findViewById(R.id.distance_textView);
        balView = findViewById(R.id.weight_textView);
        timView = findViewById(R.id.time_textView);


        //Bluetooth Init
        pairedDeviceList = findViewById(R.id.listView);
        Button bt = findViewById(R.id.btList);
        paired = new ArrayList<>();

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String bjson = sharedPref.getString("stored_ball_list", "");
        Gson bgson = new Gson();

        String djson = sharedPref.getString("stored_distance_list", "");
        Gson dgson = new Gson();

        String tjson = sharedPref.getString("stored_time_list", "");
        Gson tgson = new Gson();

        Intent info = getIntent();
        Bundle checkInfo = info.getExtras();
        index = (int)checkInfo.get("Patient");

        if(bjson.equals(""))
            ballList = new BallWeightList();
        else
            ballList = bgson.fromJson(bjson, BallWeightList.class);


        if(djson.equals(""))
            timeList = new TimeList();
        else
            ballList = dgson.fromJson(bjson, BallWeightList.class);

        if(tjson.equals(""))
            distanceList = new DistanceList();
        else
            ballList = tgson.fromJson(bjson, BallWeightList.class);

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
        intent.putExtra("address", address);
        intent.putExtra("Patient", index);

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

    // Bluetooth Stuff
    public void on(View v){
        if (!btAdapter.isEnabled()){
            Intent turnOn =  new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, 0);
            Toast.makeText(getApplicationContext(), "Turned On", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Already On", Toast.LENGTH_LONG).show();
        }
    }

    public void off(View v){
        btAdapter.disable();
        Toast.makeText(getApplicationContext(), "Turned off", Toast.LENGTH_LONG).show();
    }

    public void visible(View v){
        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(getVisible, 0);
    }

    public void list(View v){
        //Toast.makeText(getApplicationContext(), "Getting Bluetooth Devices", Toast.LENGTH_LONG).show();
        pairedDeviceList.setVisibility(View.VISIBLE);
        pairedDevices = btAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        for(BluetoothDevice bt: pairedDevices)
            list.add(bt.getName() + "\n" + bt.getAddress()); // Get the device's name and address
        Toast.makeText(getApplicationContext(), "Paired Devices", Toast.LENGTH_LONG).show();


        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);

        pairedDeviceList.setAdapter(adapter);
        pairedDeviceList.setOnItemClickListener(myListClickListener); //called when an item from the list is clicked
    }

    public void openDeviceList(View v){
        //on(v);
        //visible(v);
        //list();
        CharSequence[] times = new CharSequence[paired.size()];
        for (int i = 0; i < paired.size(); i++){
            times[i] = paired.get(i);
        }
        final CharSequence[] time = times;
        AlertDialog.Builder tbuilder = new AlertDialog.Builder(this);
        tbuilder.setTitle("Select a paired device");
        tbuilder.setItems(time, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) {
                //timView.setText(time[which]);
            }
        });
        tbuilder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            Toast.makeText(getApplicationContext(),info, Toast.LENGTH_LONG).show();;
            address = info.substring(info.length() - 17);
            // Make an intent to start next activity.
            //Intent i = new Intent(NewGame.this, PatientView.class);
            //Change the activity.
            //i.putExtra(EX)//this will be received at bluetoothReceiver (class) Activity
            //i.putExtra("address", address);
            //startActivity(i);

            // Disappear
            pairedDeviceList.setVisibility(View.GONE);
        }
    };



}