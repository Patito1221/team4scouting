package com.example.jaflo.thescoutingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class TeleOpMatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_op_match);

        Bundle AutonMatch = getIntent().getExtras();
        String teamNumberVariable = AutonMatch.getString("team number");
        Bundle AutonMatch2 = getIntent().getExtras();
        String matchNumberVariable = AutonMatch2.getString("match number");

        TextView TeamNameTeleOp = (TextView) findViewById(R.id.TeamNameTeleOp);
        TextView MatchNameTeleOp = (TextView) findViewById(R.id.MatchNumberTeleOp);

        TeamNameTeleOp.setText("Match " + teamNumberVariable); //Fix
        MatchNameTeleOp.setText(matchNumberVariable);


        Button submitBtnT = (Button) findViewById(R.id.SubmitBtnT);
            final Intent TeleMain = new Intent(this, MainActivity.class);
        TeleMain.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        submitBtnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TeleMain);
              // fix when ander here  ((TeleOpMatch.class) TeleOpMatch.class()).finish();
            }
        });

        registerCounterListeners((ImageButton) findViewById(R.id.DecreaseHighGoalCycle), R.id.VariableHGCycle, (ImageButton) findViewById(R.id.IncreaseHighGoalCycle));
        registerCounterListeners((ImageButton) findViewById(R.id.DecreaseLowGoalCycle), R.id.VariableLGCycle, (ImageButton) findViewById(R.id.IncreaseLowGoalCycle));
        registerCounterListeners((ImageButton) findViewById(R.id.DecreaseGearsBrought), R.id.VariableGearsPlaced, (ImageButton) findViewById(R.id.IncreaseGearsBrought));

    }

    public void registerCounterListeners(ImageButton decreaseBtn, final int txtViewID, ImageButton increaseBtn){
        decreaseBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int currentAmount = Integer.parseInt(getTextOfView(txtViewID));
                if(currentAmount != 0) {
                    setTextOfView(txtViewID, ("" + (currentAmount - 1)));
                }
             }
        });
        increaseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int currentAmount = Integer.parseInt(getTextOfView(txtViewID));
                setTextOfView(txtViewID, ("" + (currentAmount+1)));
            }
        });
    }


    public String getTextOfView(int id){
        return ((TextView) findViewById(id)).getText().toString();
    }

    public void setTextOfView(int id, String text){
        ((TextView) findViewById(id)).setText(text);
    }
}
