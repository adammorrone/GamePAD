package com.example.adamm.gamepad;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import android.content.ClipboardManager;
import android.widget.Toast;

/**
 * Created by adamm on 2/12/2018.
 */

public class MoreInfo extends AppCompatActivity {


    private TextView patientNameText2;
    private PatientList masterList = MainActivity.masterList;
    private TextView patientInfoText2;
    private GraphView graph;
    private Switch switch1;
    private RadioButton rb_score;
    private RadioButton rb_work;
    private RadioButton rb_power;
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
        graph = findViewById(R.id.graph2);
        switch1 = findViewById(R.id.switch1);
        rb_score = findViewById(R.id.radioButton);
        rb_work = findViewById(R.id.radioButton2);
        rb_power = findViewById(R.id.radioButton3);
        rb_score.setChecked(true);


        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String json = sharedPref.getString("stored_master_list", "");
        Gson gson = new Gson();

        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        index = (int)b.get("Patient");

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
            patientNameText2.setTextSize(25);
            patientNameText2.setText(name);

            String info = masterList.getPatient(index).getDOB() + "\t\t\t" + masterList.getPatient(index).getHeight();
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

    public void toggleSorting(View view)
    {
        if(switch1.isChecked())
        {
            if(rb_score.isChecked())
                graph_scores_by_day();
            else if(rb_work.isChecked())
                graph_work_by_day();
            else
                graph_power_by_day();
        }
        else
        {
            if(rb_score.isChecked())
                graph_score_by_game();
            else if(rb_work.isChecked())
                graph_work_by_game();
            else
                graph_power_by_game();
        }
    }

    public void graph_scores_by_day()
    {
        ArrayList<ScoreRecord> scores = masterList.getPatient(index).getScores();
        ArrayList<DataPoint> data = new ArrayList<>();
        double temp_score = scores.get(0).getScore();
        for(int i = 1; i < scores.size(); i++)
        {
            if(scores.get(i).getDate().compareTo(scores.get(i - 1).getDate()) == 0)
                temp_score += scores.get(i).getScore();
            else
            {
                data.add(new DataPoint(scores.get(i).getDate().get(Calendar.DAY_OF_YEAR), temp_score));
                temp_score = scores.get(i).getScore();
            }
        }
        int day = scores.get(scores.size() - 1).getDate().get(Calendar.DAY_OF_YEAR);
        data.add(new DataPoint(day, temp_score));

        DataPoint[] data_arr = new DataPoint[data.size()];
        for(int i = 0; i < data.size(); i++)
            data_arr[i] = data.get(i);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data_arr);
        graph.removeAllSeries();
        graph.addSeries(series);
    }
    public void graph_work_by_day()
    {
        ArrayList<ScoreRecord> scores = masterList.getPatient(index).getScores();
        ArrayList<DataPoint> data = new ArrayList<>();
        double temp_score = scores.get(0).getScore();
        for(int i = 1; i < scores.size(); i++)
        {
            if(scores.get(i).getDate().compareTo(scores.get(i - 1).getDate()) == 0)
                temp_score += scores.get(i).getWork_kcal();
            else
            {
                data.add(new DataPoint(scores.get(i).getDate().get(Calendar.DAY_OF_YEAR), temp_score));
                temp_score = scores.get(i).getWork_kcal();
            }
        }
        int day = scores.get(scores.size() - 1).getDate().get(Calendar.DAY_OF_YEAR);
        data.add(new DataPoint(day, temp_score));

        DataPoint[] data_arr = new DataPoint[data.size()];
        for(int i = 0; i < data.size(); i++)
            data_arr[i] = data.get(i);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data_arr);
        graph.removeAllSeries();
        graph.addSeries(series);
    }

    public void graph_power_by_day()
    {
        ArrayList<ScoreRecord> scores = masterList.getPatient(index).getScores();
        ArrayList<DataPoint> data = new ArrayList<>();
        double temp_score = scores.get(0).getScore();
        int count = 0;
        for(int i = 1; i < scores.size(); i++)
        {
            count++;

            if(scores.get(i).getDate().compareTo(scores.get(i - 1).getDate()) == 0)
                temp_score += scores.get(i).getPower_watts();

            else
            {
                data.add(new DataPoint(scores.get(i).getDate().get(Calendar.DAY_OF_YEAR), temp_score/count));
                temp_score = scores.get(i).getScore();
                count = 0;
            }
        }
        int day = scores.get(scores.size() - 1).getDate().get(Calendar.DAY_OF_YEAR);
        data.add(new DataPoint(day, temp_score));

        DataPoint[] data_arr = new DataPoint[data.size()];
        for(int i = 0; i < data.size(); i++)
            data_arr[i] = data.get(i);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data_arr);
        graph.removeAllSeries();
        graph.addSeries(series);
    }


    public void graph_score_by_game()
    {
        ArrayList<ScoreRecord> scores = masterList.getPatient(index).getScores();
        DataPoint[] data = new DataPoint[scores.size()];
        for(int i = 0; i < scores.size(); i++)
        {
            data[i] = new DataPoint(i, scores.get(i).getScore());
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);
        graph.removeAllSeries();
        graph.addSeries(series);
    }

    public void graph_work_by_game()
    {
        ArrayList<ScoreRecord> scores = masterList.getPatient(index).getScores();
        DataPoint[] data = new DataPoint[scores.size()];
        for(int i = 0; i < scores.size(); i++)
        {
            data[i] = new DataPoint(i, scores.get(i).getWork_kcal());
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);
        graph.removeAllSeries();
        graph.addSeries(series);
    }

    public void graph_power_by_game()
    {
        ArrayList<ScoreRecord> scores = masterList.getPatient(index).getScores();
        DataPoint[] data = new DataPoint[scores.size()];
        for(int i = 0; i < scores.size(); i++)
        {
            data[i] = new DataPoint(i, scores.get(i).getPower_watts());
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);
        graph.removeAllSeries();
        graph.addSeries(series);
    }


    public void copyRecords(View view)
    {
        ArrayList<ScoreRecord> records = masterList.getPatient(index).getScores();
        String recordsTest = "GamePAD records for " + masterList.getPatient(index).getName() + ":\n\n";

        for(int i = 0; i < records.size(); i++)
        {
            recordsTest += records.get(i).toString() + "\n";
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Patient Records", recordsTest);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Patient records have been coped to the clipboard", Toast.LENGTH_LONG).show();
    }

    public void copyRecordsAsCSV(View view)
    {
        ArrayList<ScoreRecord> records = masterList.getPatient(index).getScores();

        String recordsTest = "Date" +
                "\tGame" +
                "\tScore" +
                "\tWork" +
                "\tPush Ups" +
                "\tAverage Power" +
                "\tBall Weight" +
                "\tThrows" +
                "\tThrowing Height" +
                "\tThrowing Distance" +
                "\tTime" +
                "\n" +
                "Date\t\t" +
                "\tGameType" +
                "\tPoints" +
                "\tkcal" +
                "\tPush Ups" +
                "\tWatts" +
                "\tlbs" +
                "\tThrows" +
                "\tinches" +
                "\tfeet" +
                "\tseconds" +
                "\n";

        for(int i = 0; i < records.size(); i++)
        {
            recordsTest += records.get(i).toCSV();
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Patient Records", recordsTest);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(), "Patient records have been coped to the clipboard", Toast.LENGTH_LONG).show();
    }


}
