package com.example.adamm.gamepad;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;


public class PatientView extends Activity {
    /** Called when the activity is first created. */
    // checking to make sure push is
    Handler bluetoothIn;
    //TextView txtString, txtStringLength, txtArduino;
    TextView sensorView1, sensorView2, sensorView3, sensorView4,
            sensorView5, sensorView6, sensorView7, sensorView8,
            sensorView9, sensorView10, sensorView11, sensorView12,
            sensorView13, sensorView14, sensorView15, sensorView16;
    ImageView imageSensor1, imageSensor2, imageSensor3, imageSensor4;
    final int handlerState = 0;             //used to identify handler message
    private BluetoothAdapter btAdapter;
    //private Set<BluetoothDevice> pairedDevices;
    private BluetoothSocket btSocket;
    private StringBuilder recDataString = new StringBuilder();

    private ConnectedThread mConnectedThread;   //Private thread for bluetooth connection

    // SPP UUID service
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private static String address;
    private static String time;
    private static String distance;
    private static String weight;


    private TextView timerView;
    ArrayList<String> paired;
    ListView pairedDeviceList;

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientview);

        Intent info = getIntent();
        Bundle checkInfo = info.getExtras();

        if (checkInfo == null) {
            Toast toast = Toast.makeText(this, "No Timer Set! Game cannot start", Toast.LENGTH_LONG);
            toast.show();
        }
        else {
            time = checkInfo.get("time").toString();
            address = checkInfo.get("address").toString();

        } // Do something
        Toast toast = Toast.makeText(getBaseContext(), "Address: " + address + "  Time: " + time, Toast.LENGTH_LONG);
        toast.show();
        //Init
        timerView = findViewById(R.id.textView2);
        pairedDeviceList = findViewById(R.id.listView);
        //Button bt = findViewById(R.id.btList);
        paired = new ArrayList<>();

        sensorView3 = findViewById(R.id.editText1);
        sensorView4 = findViewById(R.id.editText2);
        sensorView5 = findViewById(R.id.editText3);
        sensorView2 = findViewById(R.id.editText4);
        sensorView8 = findViewById(R.id.editText12);
        sensorView7 = findViewById(R.id.editText10);
        sensorView6 = findViewById(R.id.editText13);
        sensorView1 = findViewById(R.id.editText15);
        sensorView9 = findViewById(R.id.editText6);
        sensorView10 = findViewById(R.id.editText8);
        sensorView11 = findViewById(R.id.editText16);
        sensorView16 = findViewById(R.id.editText7);
        sensorView14 = findViewById(R.id.editText9);
        sensorView13 = findViewById(R.id.editText11);
        sensorView12 = findViewById(R.id.editText5);
        sensorView15 = findViewById(R.id.editText14);

        imageSensor1 = findViewById(R.id.imageView1);
        imageSensor2 = findViewById(R.id.imageView2);
        imageSensor3 = findViewById(R.id.imageView3);
        imageSensor4 = findViewById(R.id.imageView4);

        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {										//if message is what we want
                    String readMessage = (String) msg.obj;                                                                // msg.arg1 = bytes from connect thread
                    recDataString.append(readMessage);      								//keep appending to string until ~
                    int endOfLineIndex = recDataString.indexOf("~");                    // determine the end-of-line
                    if (endOfLineIndex > 0) {                                           // make sure there data before ~
                        String dataInPrint = recDataString.substring(0, endOfLineIndex);    // extract string
                        //txtString.setText("Data Received = " + dataInPrint);
                        int dataLength = dataInPrint.length();							//get length of data received
                        //txtStringLength.setText("String Length = " + String.valueOf(dataLength));

                        if (recDataString.charAt(0) == '#')								//if it starts with # we know it is what we are looking for
                        {
                            String sensor1 = recDataString.substring(1,2);             //get sensor value from string between indices 1-5
                            String sensor2 = recDataString.substring(2,3);            //same again...
                            String sensor3 = recDataString.substring(3,4);
                            String sensor4 = recDataString.substring(4,5);
                            String sensor5 = recDataString.substring(5,6);
                            String sensor6 = recDataString.substring(6,7);
                            String sensor7 = recDataString.substring(7,8);             //get sensor value from string between indices 1-5
                            String sensor8 = recDataString.substring(8,9);            //same again...
                            String sensor9 = recDataString.substring(9,10);
                            String sensor10 = recDataString.substring(10,11);
                            String sensor11 = recDataString.substring(11,12);
                            String sensor12 = recDataString.substring(12,13);
                            String sensor13 = recDataString.substring(13,14);
                            String sensor14 = recDataString.substring(14,15);
                            String sensor15 = recDataString.substring(15,16);
                            String sensor16 = recDataString.substring(16,17);

                            //sensorView0.setText(" Sensor 0 Voltage = " + sensor0 + "V");	//update the textviews with sensor values
                            sensorView1.setText(sensor1);
                            sensorView2.setText(sensor2);
                            sensorView3.setText(sensor3);
                            sensorView4.setText(sensor4);
                            sensorView5.setText(sensor5);
                            sensorView6.setText(sensor6);
                            sensorView7.setText(sensor7);
                            sensorView8.setText(sensor8);
                            sensorView9.setText(sensor9);
                            sensorView10.setText(sensor10);
                            sensorView11.setText(sensor11);
                            sensorView12.setText(sensor12);
                            sensorView13.setText(sensor13);
                            sensorView14.setText(sensor14);
                            sensorView15.setText(sensor15);
                            sensorView16.setText(sensor16);

                            if(sensor2.equals("1") || sensor1.equals("1") || sensor16.equals("1") || sensor15.equals("1")){        // if 2 1 16 15 == 1
                                imageSensor1.setVisibility(View.VISIBLE);
                            }
                            else {
                                imageSensor1.setVisibility(View.GONE);
                            }
                            if(sensor5.equals("1") || sensor6.equals("1") || sensor11.equals("1") || sensor12.equals("1")){
                                imageSensor2.setVisibility(View.VISIBLE);

                            }
                            else{
                                imageSensor2.setVisibility(View.GONE);
                            }
                            if(sensor4.equals("1") || sensor7.equals("1") || sensor10.equals("1") || sensor13.equals("1")){
                                imageSensor3.setVisibility(View.VISIBLE);
                            }
                            else{
                                imageSensor3.setVisibility(View.GONE);
                            }
                            if(sensor3.equals("1") || sensor8.equals("1") || sensor9.equals("1") || sensor14.equals("1")){
                                imageSensor4.setVisibility(View.VISIBLE);

                            }
                            else{
                                imageSensor4.setVisibility(View.GONE);
                            }

                        }
                        recDataString.delete(0, recDataString.length()); 	//clear all string data
                        // strIncom =" ";
                        dataInPrint = " ";
                    }
                }
            }
        };


        btAdapter = BluetoothAdapter.getDefaultAdapter();

        Button next = findViewById(R.id.button_next2);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });


    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return  device.createRfcommSocketToServiceRecord(myUUID);
        //creates secure outgoing connecetion with BT device using UUID
    }

    @Override
    public void onResume() {
        super.onResume();
        //Get MAC address from DeviceListActivity via intent
        //Intent intent = getIntent();

        //Get the MAC address from the DeviceListActivty via EXTRA
        //Bundle checkInfo;
        //checkInfo = intent.getExtras();
        //address = checkInfo.get("address").toString();
        // Old way => address = intent.getStringExtra(NewGame.EXTRA_DEVICE_ADDRESS);

        //create device and set the MAC address
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
        }
        // Establish the Bluetooth socket connection.
        try
        {
            btSocket.connect();
        } catch (IOException e) {
            try
            {
                btSocket.close();
            } catch (IOException e2)
            {
                //insert code to deal with this
            }
        }
        mConnectedThread = new ConnectedThread(btSocket);
        mConnectedThread.start();

        //I send a character when resuming.beginning transmission to check device is connected
        //If it is not an exception will be thrown in the write method and finish() will be called
        mConnectedThread.write("x");
    }

    public void startGame(View v){
        startTimer(time);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        try
        {
            //Don't leave Bluetooth sockets open when leaving activity
            btSocket.close();
        } catch (IOException e2) {
            //insert code to deal with this
        }
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

    //create new class for connect thread
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        //creation of the connect thread
        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }


        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);        	//read bytes from input buffer
                    String readMessage = new String(buffer, 0, bytes);
                    // Send the obtained bytes to the UI Activity via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
        //write method
        public void write(String input) {
            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(getBaseContext(), "Connection Failure", Toast.LENGTH_LONG).show();
                finish();

            }
        }
    }
}


/*
            if(sensor1 == "0"){
                imageSensor1.setVisibility(View.GONE);
            }
            else {
                imageSensor1.setVisibility(View.VISIBLE);
            }
            if(sensor2 == "0"){
                imageSensor2.setVisibility(View.GONE);

            }
            else{
                imageSensor2.setVisibility(View.VISIBLE);
            }
            if(sensor3 == "0"){
                imageSensor3.setVisibility(View.GONE);
            }
            else {
                imageSensor3.setVisibility(View.VISIBLE);
            }
            if(sensor4 == "0"){
                imageSensor4.setVisibility(View.GONE);

            }
            else{
                imageSensor4.setVisibility(View.VISIBLE);
            }
 */
