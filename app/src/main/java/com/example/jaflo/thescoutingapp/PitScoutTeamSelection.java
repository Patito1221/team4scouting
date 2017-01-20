package com.example.jaflo.thescoutingapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PitScoutTeamSelection extends AppCompatActivity {

    Button submitBtnPSS;
    Typeface roboto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scout_team_selection);

        TextView teams = (TextView) findViewById(R.id.TeamsTitle);
        final Spinner teamDropDownPSS = (Spinner) findViewById(R.id.TeamDropDownPSS);
        Button submitBtnPSS = (Button) findViewById(R.id.SubmitBtnPSS);


        Typeface roboto = Typeface.createFromAsset(getAssets(), "Roboto-Black.ttf");

        submitBtnPSS.setTypeface(roboto);

        final Intent startPitScout = new Intent(this, PitScout.class);
        submitBtnPSS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String pitScoutSelection = teamDropDownPSS.getSelectedItem().toString();
                startPitScout.putExtra("pit selection", pitScoutSelection);
                startActivity(startPitScout);
            }
        });

    }
}
