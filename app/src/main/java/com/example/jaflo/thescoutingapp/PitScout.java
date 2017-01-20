package com.example.jaflo.thescoutingapp;

        import android.content.Intent;
        import android.graphics.Typeface;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;

        import org.w3c.dom.Text;

        import static com.example.jaflo.thescoutingapp.R.array.Drivebases;

public class PitScout extends AppCompatActivity {

    Typeface roboto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scout);

        Bundle PitScoutSelection = getIntent().getExtras();
        String pitScoutSelection = PitScoutSelection.getString("pit selection");

        Button submitBtnPS = (Button) findViewById(R.id.SubmitPS);
        TextView PitScoutTitle = (TextView) findViewById(R.id.PitScoutTitle);
        TextView DriveBaseTitle = (TextView) findViewById(R.id.DrivebaseTitle);
        Spinner DriveBases = (Spinner) findViewById(R.id.DrivebasesDropDown);
        TextView NumberWheels = (TextView) findViewById(R.id.NumberofWheelsTitle);
        EditText NumberWheelsField = (EditText) findViewById(R.id.NumberofWheelsField);
        CheckBox Gears = (CheckBox) findViewById(R.id.GearsCheckBox);
        CheckBox HighGoal = (CheckBox) findViewById(R.id.HighGoalAbilityCheckBox);
        CheckBox LowGoal = (CheckBox) findViewById(R.id.LowGoalAbilityCheckBox);
        TextView BallsHeld = (TextView) findViewById(R.id.AmountofBallsHeldTitle);
        EditText BallsHeldField = (EditText)  findViewById(R.id.AmountofBallsField);
        CheckBox RopeAbilityPS = (CheckBox) findViewById(R.id.ClimbsRopeCheckBoxPS);
        CheckBox Scouts = (CheckBox) findViewById(R.id.ScoutsCheckBox);
        TextView TeamName = (TextView) findViewById(R.id.PitSelectionTeamName);

        Typeface roboto = Typeface.createFromAsset(getAssets(), "Roboto-Black.ttf");

        submitBtnPS.setTypeface(roboto);
        PitScoutTitle.setTypeface(roboto);
        DriveBaseTitle.setTypeface(roboto);
        NumberWheels.setTypeface(roboto);
        NumberWheelsField.setTypeface(roboto);
        Gears.setTypeface(roboto);
        HighGoal.setTypeface(roboto);
        LowGoal.setTypeface(roboto);
        BallsHeld.setTypeface(roboto);
        BallsHeldField.setTypeface(roboto);
        RopeAbilityPS.setTypeface(roboto);
        Scouts.setTypeface(roboto);
        TeamName.setTypeface(roboto); //Sets font for all Objects

        TeamName.setText(pitScoutSelection);


        final Intent PitMain = new Intent(this, MainActivity.class);
        PitMain.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        submitBtnPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PitMain);
            }
        });
    }
}
