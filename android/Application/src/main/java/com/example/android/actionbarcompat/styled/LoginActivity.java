package com.example.android.actionbarcompat.styled;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class LoginActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hides the bulky title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.fragment_login);


        Intent intent = getIntent();


        Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // navigate to main activity
                finish(); // ends this activity

            }
        });

        Button passwordButton = (Button) findViewById(R.id.forgotPasswordButton);
        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // navigate to main activity
                IoteAPI.registerNewIoteTag("hi","hello");

            }
        });


    }
}