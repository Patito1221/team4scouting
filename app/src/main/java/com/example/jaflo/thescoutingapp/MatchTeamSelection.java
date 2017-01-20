package com.example.jaflo.thescoutingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;

public class MatchTeamSelection extends AppCompatActivity {

    Button submitBtnMTS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_team_selection);

        final Intent startTeleOpMatch = new Intent(this, TeleOpMatch.class);

        TextView teamsTitleMTS = (TextView) findViewById(R.id.TeamsTitle);
        final Spinner teamDropDownMTS = (Spinner) findViewById(R.id.TeamDropDownMTS);
        TextView matchTitle = (TextView) findViewById(R.id.MatchTitle);
        final Spinner matchNumberDropDown = (Spinner) findViewById(R.id.MatchNumberDropDown);
        Button submitBtnMTS = (Button) findViewById(R.id.SubmitBtnMTS);
        final String teamselection;
        final TextView TeamSelectionError = (TextView) findViewById(R.id.TeamErrorText);

        TeamSelectionError.setTextColor(Color.parseColor("#ffffff"));

        Teams.initValues();
        ArrayAdapter teamAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Teams.teams.keySet().toArray());
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamDropDownMTS.setAdapter(teamAdapter);

        Object selected = teamDropDownMTS.getSelectedItem();
        Integer[] array = Teams.teams.get(selected);
        final ArrayAdapter<Integer> matchAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, array);
        matchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        matchNumberDropDown.setAdapter(matchAdapter);

        final Context context = this;
        teamDropDownMTS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                matchAdapter.notifyDataSetInvalidated();
                Object selected = teamDropDownMTS.getSelectedItem();
                Integer[] array = Teams.teams.get(selected);
                final ArrayAdapter<Integer> matchAdapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_dropdown_item, array);
                matchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                matchNumberDropDown.setAdapter(matchAdapter);
                matchAdapter.notifyDataSetInvalidated();
                return true;
            }
        });


//        ArrayList teamlist = new ArrayList(); //Array with all teams.
//        teamlist.add("Select Team...");
//        teamlist.add("Team 4 ELEMENT - 4");
//        teamlist.add("399");
//        teamlist.add("696");
//        teamlist.add("254");
//
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, teamlist);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        teamDropDownMTS.setAdapter(adapter);
//
//        ArrayList matchlistteam4 = new ArrayList(); //Array for TESTING team 4.
//        matchlistteam4.add("Select Match...");
//        matchlistteam4.add("2");
//        matchlistteam4.add("4");
//        matchlistteam4.add("5");
//        matchlistteam4.add("8");
//
//        final ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, matchlistteam4);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        matchNumberDropDown.setAdapter(adapter1);
//
//
//        ArrayList matchlistteam399 = new ArrayList(); //Array for TESTING team 399.
//        matchlistteam399.add("Select Match...");
//        matchlistteam399.add("3");
//        matchlistteam399.add("6");
//        matchlistteam399.add("7");
//        matchlistteam399.add("10");
//        final ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, matchlistteam399);
//       adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//
//
//
//        matchNumberDropDown.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                String teamselection = teamDropDownMTS.getSelectedItem().toString();
//                if (teamselection.equals("Team 4 ELEMENT - 4")){
//
//                    matchNumberDropDown.setAdapter(adapter2);
//                    System.out.println("the good shi");
//                    return false;
//                }
//                else if (teamselection.equals("696")){
//                    matchNumberDropDown.setAdapter(adapter1);
//                    System.out.println("the good shi");
//                    return false;
//                }
//                else {
//                    System.out.println("the bad shi");
//                    return false;
//                }
//
//            }
//        });
        final Intent startAutonMatch = new Intent(this, AutonMatch.class);
        submitBtnMTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teamselection = teamDropDownMTS.getSelectedItem().toString();
                String matchselection = matchNumberDropDown.getSelectedItem().toString();
                if (teamselection.equals("Select Team...") || matchselection.equals("Select Match...")){
                    TeamSelectionError.setError("");
                    TeamSelectionError.setTextColor(Color.RED);//just to highlight that this is an error
                    TeamSelectionError.setText("Invalid Selection");//changes the selected item text to this
                }
                else {
                    String MatchNumberVariable = matchNumberDropDown.getSelectedItem().toString();
                    TeamSelectionError.setError(null);
                    TeamSelectionError.setTextColor(Color.WHITE);
                    TeamSelectionError.setText("");
                    startAutonMatch.putExtra("Match Number", MatchNumberVariable);
                    String TeamNameVariable = teamDropDownMTS.getSelectedItem().toString();
                    startAutonMatch.putExtra("Team Name", TeamNameVariable);
                    startActivity(startAutonMatch);
                    System.out.println("yeet");
                }

            }
        });


    }}

