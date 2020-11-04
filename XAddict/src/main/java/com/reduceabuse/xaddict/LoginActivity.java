package com.reduceabuse.xaddict;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etLoginEmailAddress, etLoginPassword;
    FirebaseAuth fbAuth;
    ProgressBar pblogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmailAddress = findViewById(R.id.etLoginEmailAddress);
        etLoginPassword = findViewById(R.id.etLoginPassword);

        fbAuth = FirebaseAuth.getInstance();
        pblogin = findViewById(R.id.pblogin);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnLoginProceedSignup = findViewById(R.id.btnLoginProceedSignup);
        Button btnLoginForgotPassword = findViewById(R.id.btnLoginForgotPassword);

        btnLogin.setOnClickListener(this);
        btnLoginProceedSignup.setOnClickListener(this);
        btnLoginForgotPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String emailAddress, password;

        emailAddress = etLoginEmailAddress.getText().toString().trim();
        password = etLoginPassword.getText().toString().trim();

        switch(v.getId()) {
            case R.id.btnLogin:
                if (TextUtils.isEmpty(emailAddress)) {
                    etLoginEmailAddress.setError("Please enter an email address");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etLoginPassword.setError("Please enter a password");
                    return;
                }

                if (password.length() < 6) {
                    etLoginPassword.setError("Password must be 6 or more characters");
                    return;
                }

                pblogin.setVisibility(View.VISIBLE);

                fbAuth.signInWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Error to login!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pblogin.setVisibility(View.GONE);
                        }
                    }
                });
                break;

            case R.id.btnLoginProceedSignup:
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                break;
        }
    }
}