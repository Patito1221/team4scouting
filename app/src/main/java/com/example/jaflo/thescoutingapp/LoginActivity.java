package com.example.jaflo.thescoutingapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener {

    Button login_btn;
    EditText userName;//Fix these XML's and maybe the errors will go away, at least that's what I think
    EditText password;
    TextView logintext;
    TextView UsernameText;
    TextView PasswordText;
    Typeface roboto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        roboto = Typeface.createFromAsset(getAssets(), "Roboto-Black.ttf");
        userName = (EditText) findViewById(R.id.Username);//I see it now xD
        password = (EditText) findViewById(R.id.Password);//wuta pain, kk lemme google
        login_btn = (Button) findViewById(R.id.LoginBtn);
        logintext = (TextView) findViewById(R.id.LoginText);
        UsernameText = (TextView) findViewById(R.id.UsernameText);
        PasswordText = (TextView) findViewById(R.id.PasswordText);

        userName.setTypeface(roboto);
        password.setTypeface(roboto);
        login_btn.setTypeface(roboto);
        logintext.setTypeface(roboto);
        PasswordText.setTypeface(roboto);
        UsernameText.setTypeface(roboto);


        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent LoginMain = new Intent(this, MainActivity.class);

        if (view.getId() == login_btn.getId()) {
            // check login info
           String user = userName.getText().toString();
            String pass = password.getText().toString();
            if (user.equals("developer") && pass.equals("10902"))
                startActivity(LoginMain);
            else if (user.equals("MrB") && pass.equals("22"))
                startActivity(LoginMain);
            else if (user.equals("AliA") && pass.equals("22"))
                startActivity(LoginMain);
            else if (user.equals("OwenK") && pass.equals("22"))
                startActivity(LoginMain);
            else if (user.equals("JoseF") && pass.equals("22"))
                startActivity(LoginMain);
            else if (user.equals("TrumanF") && pass.equals("22"))
                startActivity(LoginMain);
            else if (user.equals("BrittC") && pass.equals("4"))
                startActivity(LoginMain);
            else if (user.equals("GuestLogin") && pass.equals("4"))
                startActivity(LoginMain);
            else if (user.equals("") && pass.equals("")) // DELETE THIS AFTER CODING IS DONE PLEASE DON'T FORGET OR APP IS PROGRAM
                startActivity(LoginMain);
            else password.setError("Invalid Username or Password");
        }
    }
}