package com.example.adamm.gamepad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.transform.Result;

/**
 * Created by adamm on 2/28/2018.
 */

public class ResultsActivity extends AppCompatActivity {

    public int index = -1;
    private PatientList masterList = MainActivity.masterList;
    public  TextView scoreText;
    public  TextView workText;
    public  TextView throwsText;
    public  TextView timeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        scoreText = findViewById(R.id.scoreView);
        workText = findViewById(R.id.workView);
        throwsText = findViewById(R.id.throwsView);
        timeText = findViewById(R.id.timeView);


        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String json = sharedPref.getString("stored_master_list", "");
        Gson gson = new Gson();

        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        index = (int)b.get("Patient");
        ScoreRecord scoreRecord = (ScoreRecord)b.get("Score");
        masterList.getPatient(index).addScore(scoreRecord);

        saveChanges();

        scoreText.setText(Double.toString(scoreRecord.getScore()));
        workText.setText(Double.toString(scoreRecord.getWork()));
        throwsText.setText(Double.toString(scoreRecord.getNumThrows()));
        timeText.setText(Double.toString(scoreRecord.getTime()));

        ArrayList<ScoreRecord> scores = masterList.getPatient(index).getScores();
        DataPoint[] data = new DataPoint[scores.size()];
        for(int i = 0; i < scores.size(); i++) {
            data[i] = new DataPoint(i, scores.get(i).getScore());
        }
    }

    public void goto_main(View view)
    {
        Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
        intent.putExtra("Patient", index);
        startActivity(intent);
    }

    public void goto_newGame(View view)
    {
        Intent intent = new Intent(ResultsActivity.this, NewGame.class);
        intent.putExtra("Patient", index);
        startActivity(intent);
    }

    public void saveChanges()
    {
        MainActivity.saveMasterList(this.getApplicationContext(), masterList);
        Intent intent = new Intent(ResultsActivity.this, PatientOverviewActivity.class);
        intent.putExtra("Patient", index);
        startActivity(intent);
    }
}
