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

/**
 * Created by adamm on 2/12/2018.
 */

public class MoreInfo extends AppCompatActivity {


    private TextView patientNameText2;
    private PatientList masterList = MainActivity.masterList;
    private TextView patientInfoText2;
    private int index = -1;

    /** This application's preferences */
    private static SharedPreferences settings;

    /** This application's settings editor*/
    private static SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        patientNameText2 = findViewById(R.id.patientNameBox2);
        patientInfoText2 = findViewById(R.id.patientInfoBox2);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String json = sharedPref.getString("stored_master_list", "");
        Gson gson = new Gson();

        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        index = (int)b.get("Patient");

        GraphView graph = findViewById(R.id.graph2);
        ArrayList<ScoreRecord> scores = masterList.getPatient(index).getScores();
        DataPoint[] data = new DataPoint[scores.size()];
        for(int i = 0; i < scores.size(); i++)
        {
            data[i] = new DataPoint(i, scores.get(i).getScore());
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series2);
        graph.addSeries(series);

        if (b != null) {
            String name = masterList.getPatient(index).getName();
            patientNameText2.setTextSize(25);
            patientNameText2.setText(name);

            String info = masterList.getPatient(index).getDOB() + "\t\t\t" + masterList.getPatient(index).getGender();
            patientInfoText2.setTextSize(15);
            patientInfoText2.setText(info);

        }
    }

    public void openRecords(View view)
    {
        ArrayList<ScoreRecord> scores = masterList.getPatient(index).getScores();
        final CharSequence[] names = new CharSequence[scores.size()];

        for(int i = 0; i < scores.size(); i++)
        {
            names[i] = scores.get(i).toString();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Score Records for " + masterList.getPatient(index).getName());
        builder.setItems(names, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                //goto_UserProfile(names[which].toString());
            }
        });
        builder.show();
    }

    public void goBack(View view)
    {
        finish();
    }

}
