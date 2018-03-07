package com.example.adamm.gamepad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;

import android.media.*;

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

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.high_score_cheer);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you

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
        int _score_ = (int)b.get("Score");
        int _throws_ = (int)b.get("Throws");
        int _weight_ = Integer.parseInt((String)b.get("Weight"));
        double _distance_ = Double.parseDouble((String)b.get("Distance"));
        int _time_ = (int)b.get("Time");
        int _height_ = masterList.getPatient(index).getHeight();


        ScoreRecord scoreRecord = new ScoreRecord(_score_, "Standard", Calendar.getInstance(), _distance_, _throws_, _time_, _weight_, _height_);

        masterList.getPatient(index).addScore(scoreRecord);

        scoreText.setText(Double.toString(scoreRecord.getScore()));
        workText.setText(Double.toString(scoreRecord.getWork_kcal()));
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
        Intent intent = new Intent(ResultsActivity.this,
                PatientOverviewActivity.class);
        intent.putExtra("Patient", index);
        startActivity(intent);
    }
}
