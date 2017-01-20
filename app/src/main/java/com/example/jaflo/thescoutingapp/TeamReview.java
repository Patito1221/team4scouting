package com.example.jaflo.thescoutingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TeamReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_review);

        Bundle TeamReviewSelection = getIntent().getExtras();
        String teamNumberVariable = TeamReviewSelection.getString("team number");


        TextView teamNumberTeamNameTitle = (TextView) findViewById(R.id.TeamNumberTeamNameTitle);
        TextView reviewMatchesTitle = (TextView) findViewById(R.id.ReviewMatchesTitle);
        Button match1 = (Button) findViewById(R.id.Match1Btn);
        Button match2 = (Button) findViewById(R.id.Match2Btn);
        Button match3 = (Button) findViewById(R.id.Match3Btn);
        Button match4 = (Button) findViewById(R.id.Match4Btn);
        Button match5 = (Button) findViewById(R.id.Match5Btn);
        Button match6 = (Button) findViewById(R.id.Match6Btn);
        Button match7 = (Button) findViewById(R.id.Match7Btn);
        Button match8 = (Button) findViewById(R.id.Match8Btn);
        TextView runningTotalsTitle = (TextView) findViewById(R.id.RunningTotalsTitle);
        TextView highGoalCyclesTitleC = (TextView) findViewById(R.id.HighGoalCyclesTitleC);
        TextView HGCCounter = (TextView) findViewById(R.id.HGCCounter);
        TextView lowGoalCyclesTileC = (TextView) findViewById(R.id.LowGoalCyclesTitleC);
        TextView LGCCounter = (TextView) findViewById(R.id.LGCCounter);
        TextView gearsPlacedTitleC = (TextView) findViewById(R.id.GearsPlacedTitleC);
        TextView GCounter = (TextView) findViewById(R.id.GCounter);
        TextView climbsTitleC = (TextView) findViewById(R.id.ClimbsTitleC);
        TextView CCounter = (TextView) findViewById(R.id.CCounter);
        TextView averagesTitle = (TextView) findViewById(R.id.AveragesTitle);
        TextView highGoalCyclesTitleA = (TextView) findViewById(R.id.HighGoalCyclesTitleA);
        TextView HGCAverage = (TextView) findViewById(R.id.HGCAverage);
        TextView lowGoalCyclesTitleA = (TextView) findViewById(R.id.LowGoalCyclesTitleA);
        TextView LGCAverage = (TextView) findViewById(R.id.LGCAverage);
        TextView gearsPlacedTitleA = (TextView) findViewById(R.id.GearsPlacedTitleA);
        TextView GAverage = (TextView) findViewById(R.id.Gaverage);
        TextView climbsTitleA = (TextView) findViewById(R.id.ClimbsTitleA);
        TextView driveBaseTitleR = (TextView) findViewById(R.id.DriveBaseTitleR);
        TextView driveBaseR = (TextView) findViewById(R.id.DriveBaseR);
        TextView numberWheelsTitleR = (TextView) findViewById(R.id.NumberWheelsTitleR);
        TextView numberWheelsR = (TextView) findViewById(R.id.NumberWheelsR);
        CheckBox gearsCheckBoxR = (CheckBox) findViewById(R.id.GearsCheckBoxR);
        CheckBox highGoalCheckBoxR = (CheckBox) findViewById(R.id.HighGoalCheckBoxR);
        CheckBox lowGoalCheckBoxR = (CheckBox) findViewById(R.id.LowGoalCheckBoxR);
        TextView ballsHeldTitleR = (TextView) findViewById(R.id.BallsHeldTitleR);
        TextView ballsHeldR = (TextView) findViewById(R.id.BallsHeldR);
        CheckBox ropeAbility = (CheckBox) findViewById(R.id.RopeAbilityR);
        CheckBox scoutsR = (CheckBox) findViewById(R.id.ScoutsR);

        teamNumberTeamNameTitle.setText(teamNumberVariable);
    }
}
