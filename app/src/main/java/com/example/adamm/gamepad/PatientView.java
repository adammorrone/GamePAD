package com.example.adamm.gamepad;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class PatientView extends Activity {
    /** Called when the activity is first created. */
    // checking to make sure push is


    private BluetoothAdapter btAdapter = null;
    private Set<BluetoothDevice> pairedDevices;

    private TextView timerView;
    ArrayAdapter<String> deviceListArray;
    ArrayList<String> paired;
    Button btnPaired;
    ListView pairedDeviceList;
    IntentFilter filter;
    BroadcastReceiver receiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientview);

        //Init
        timerView = findViewById(R.id.textView2);
        //pairedDeviceList = findViewById(R.id.pairedList);
        Button bt = findViewById(R.id.btList);
        paired = new ArrayList<>();

        //deviceListArray = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        //pairedDeviceList.setAdapter(deviceListArray);
        //pairedDeviceList.setOnItemClickListener(deviceListClickListener);

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        int lovelybones = 7;


        // Dummy mac
        paired.add("F2-B0-F6-32-9A-40");
        paired.add("B7-2E-E3-82-D9-4A");
        paired.add("34-A7-B8-3C-C9-E0");


        Button next = findViewById(R.id.button_next2);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Intent info = getIntent();
        Bundle checkInfo = info.getExtras();

        if (checkInfo == null) {
            Toast toast = Toast.makeText(this, "No Timer Set! Game cannot start", Toast.LENGTH_LONG);
            toast.show();


        } else {
            startTimer(checkInfo.get("time").toString());
        }
    }

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
        pairedDevices = btAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        for(BluetoothDevice bt: pairedDevices)
            paired.add(bt.getName());
        Toast.makeText(getApplicationContext(), "Paired Devices", Toast.LENGTH_LONG).show();


        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);

        pairedDeviceList.setAdapter(adapter);
    }

    public void startTimer (String time){
        Scanner in = new Scanner(time).useDelimiter("[^0-9]+");
        final int timer = in.nextInt();
        new CountDownTimer((timer * 60000), 1000) {

            public void onTick(long millisUntilFinished) {
                timerView.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerView.setText("done!");
            }
        }.start();

    }

    //paired = list;

    public void openDeviceList(View v){
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
}