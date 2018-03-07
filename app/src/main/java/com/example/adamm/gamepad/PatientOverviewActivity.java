package com.example.adamm.gamepad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.util.ArrayList;
import android.media.*;


public class PatientOverviewActivity extends AppCompatActivity  {

    private TextView patientNameText;
    private PatientList masterList = MainActivity.masterList;
    private TextView patientInfoText;
    private int index = -1;




    /** This application's preferences */
    private static SharedPreferences settings;

    /** This application's settings editor*/
    private static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.high_score_cheer);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        patientNameText = findViewById(R.id.patientNameBox);
        patientInfoText = findViewById(R.id.patientInfoBox);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String json = sharedPref.getString("stored_master_list", "");
        Gson gson = new Gson();

        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        index = (int)b.get("Patient");

        GraphView graph = findViewById(R.id.graph);
        ArrayList<ScoreRecord> scores = masterList.getPatient(index).getScores();
        DataPoint[] data = new DataPoint[scores.size()];
        for(int i = 0; i < scores.size(); i++)
        {
            data[i] = new DataPoint(i, scores.get(i).getScore());
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);
        graph.addSeries(series);

        if (b != null) {
            String name = masterList.getPatient(index).getName();
            patientNameText.setTextSize(25);
            patientNameText.setText(name);

            String info = masterList.getPatient(index).getDOB() + "\t\t\t" + masterList.getPatient(index).getHeight();
            patientInfoText.setTextSize(15);
            patientInfoText.setText(info + " ins tall");
        }
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


    public void goto_newGame(View view)
    {
        Intent intent = new Intent(this, NewGame.class);
        intent.putExtra("Patient", index);
        startActivity(intent);
    }

    public void goto_editPatient(View view)
    {
        Intent intent = new Intent(PatientOverviewActivity.this, EditPatientInfo.class);
        intent.putExtra("Patient", index);
        startActivity(intent);
    }

    public void goto_moreInfo(View view)
    {
        Intent intent = new Intent(PatientOverviewActivity.this, MoreInfo.class);
        intent.putExtra("Patient", index);
        startActivity(intent);
    }

    public void saveChanges()
    {
        MainActivity.saveMasterList(this.getApplicationContext(), masterList);
    }

}
