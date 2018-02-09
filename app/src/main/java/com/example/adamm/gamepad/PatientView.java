package com.example.adamm.gamepad;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
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


    private BluetoothAdapter myBluetooth = null;
    //private Set pairedDevices;
//    Set<BluetoothDevice> pairedDevices;

    private TextView timerView;
/*
    ArrayAdapter<String> listAdapter;
    ArrayList<String> paired;
    Button btnPaired;
    ListView devicelist;
    IntentFilter filter;
    BroadcastReceiver receiver;
*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientview);

        Button next = findViewById(R.id.button_next2);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        Intent info = getIntent();
        Bundle checkInfo = info.getExtras();

        if(checkInfo == null){
            Toast toast = Toast.makeText(this, "No Timer Set! Game cannot start", Toast.LENGTH_LONG);
            toast.show();


        }
        else{
            startTimer(checkInfo.get("time").toString());
        }

        // initialize
        //init();
        timerView = findViewById(R.id.textView2); }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}

/*
        // bluetooth implementation


        BluetoothAdapter.getDefaultAdapter();
        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        if(myBluetooth == null){
            //There is no bluetooth connection (show message on toast)
            Toast.makeText(getApplicationContext(), "No Bluetooth Device Available", Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            if(!myBluetooth.isEnabled()){}
            else{
                // Ast User to turn on bluetooth
                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOn, 1);
            }
        }

        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pairedDeviceList(); // will be called (method)
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Toast.makeText(getApplicationContext(), "Please enable bluetooth", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void pairedDeviceList(){
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice bluth : pairedDevices) {
                paired.add(bluth.getName()); // Get device's name and address
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"No Paired Devices.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        devicelist.setAdapter(adapter);
        devicelist.setOnItemClickListener(myListClickListener); // Called when device on list is clicked (method)
    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // Get the device MAC address, last 17 characters in the view
            String info = ((TextView) view).getText().toString();
            String address = info.substring(info.length() - 17);
            // Make intent to start next activity
            Intent take = new Intent(PatientView.this, bluetoothReceiver.class);
            // Change activity
            //take.putExtra(EXTRA_ADDRESS, address); // Received by bluetoothReceiver (class) Activity
        }
    };

    private void init(){
        // Auto generated stubs
        paired = new ArrayList<>();
        filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action)){
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    listAdapter.add(device.getName()+"\n"+device.getAddress());
                }
                else if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)){

                }
                else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){

                }
                else if(BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)){
                    if(myBluetooth.getState() == myBluetooth.STATE_OFF){
                        Toast.makeText(getApplicationContext(), "Please enable bluetooth", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        };

        registerReceiver(receiver, filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        registerReceiver(receiver, filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(receiver, filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
*/

