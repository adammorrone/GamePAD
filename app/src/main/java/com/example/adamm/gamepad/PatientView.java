package com.example.adamm.gamepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Scanner;

public class PatientView extends Activity {
    /** Called when the activity is first created. */
    // checking to make sure push is

    private TextView timerView;

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

        timerView = findViewById(R.id.textView2);
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
}


