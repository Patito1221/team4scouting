package com.example.jaflo.thescoutingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CheckBox;

import java.util.ArrayList;

public class AutonMatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton_match);
        Bundle MatchTeamSelection = getIntent().getExtras();
        final String MatchNumberVariable = MatchTeamSelection.getString("Match Number");
        Bundle NameTeamSelection = getIntent().getExtras();
        final String TeamNameVariable = NameTeamSelection.getString("Team Name");

        TextView autonTitle = (TextView) findViewById(R.id.AutonTitle);
        CheckBox reachesBaselineCheckBox = (CheckBox) findViewById(R.id.ReachesBaselineCheckBox);
        TextView fuel = (TextView) findViewById(R.id.FuelTitleA);
        CheckBox shootsaLotHighCheckBox = (CheckBox) findViewById(R.id.ShootsaLotHighCheckBox);
        CheckBox shootsaLittleHighCheckBox = (CheckBox) findViewById(R.id.ShootsaLittleHighCheckBox);
        CheckBox shootsaLotLowCheckBox = (CheckBox) findViewById(R.id.ShootsaLotLowCheckBox);
        CheckBox shootsaLittleLowCheckBox = (CheckBox) findViewById(R.id.ShootsaLittleLowCheckBox);
        TextView gearsPlacedTitle = (TextView) findViewById(R.id.GearsPlacedTitleA);
        CheckBox places1GearCheckBox = (CheckBox) findViewById(R.id.Places1GearCheckBox);
        CheckBox places2GearsCheckBox = (CheckBox) findViewById(R.id.Places2GearsCheckBox);
        Button submitBtnA = (Button) findViewById(R.id.SubmitBtnA);
        TextView MatchNumberAuton = (TextView) findViewById(R.id.MatchNumberAuton);
        TextView TeamNameAuton = (TextView) findViewById(R.id.TeamNameAuton);

        MatchNumberAuton.setText("Match " + MatchNumberVariable);
        TeamNameAuton.setText(TeamNameVariable);






        //MatchNumberAuton.setTypeface(roboto);


        final Intent startTeleOpMatch = new Intent(this, TeleOpMatch.class);
        submitBtnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTeleOpMatch.putExtra("team number", MatchNumberVariable);
                startTeleOpMatch.putExtra("match number", TeamNameVariable);
                startActivity(startTeleOpMatch);
            }
        });

    }
}