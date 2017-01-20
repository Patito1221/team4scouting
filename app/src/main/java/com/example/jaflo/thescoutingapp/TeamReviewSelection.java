package com.example.jaflo.thescoutingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TeamReviewSelection extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_review_selection);//Still RIP

        TextView teams = (TextView) findViewById(R.id.TeamsTitle);
        final Spinner teamsRS = (Spinner) findViewById(R.id.TeamDropDownRS);
        Button submit = (Button) findViewById(R.id.SubmitBtnRS);
        final Intent startTeamReview = new Intent(this, TeamReview.class);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String TeamNumberVariable = teamsRS.getSelectedItem().toString();
                startTeamReview.putExtra("team number", TeamNumberVariable);
                startActivity(startTeamReview);
            }
        });


    }
}
