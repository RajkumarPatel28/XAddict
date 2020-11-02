package com.reduce_abuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_signup);


       Button btnSignup = findViewById(R.id.btnSignup);
       Button btnProceedLogin = findViewById(R.id.btnProceedLogin);

       btnSignup.setOnClickListener(this);
       btnProceedLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch(v.getId())
        {
            case R.id.btnSignup:
            case R.id.btnProceedLogin:
                intent = new Intent(SignupActivity.this, LoginActivity.class);
                break;
        }

        startActivity(intent);
    }
}