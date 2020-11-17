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

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    EditText etEmailAddress, etPassword, etConfirmPassword;
    ProgressBar pbSignup;
    private FirebaseAuth fbAuth;
    String emailAddress, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Objects.requireNonNull(getSupportActionBar()).hide();

        etEmailAddress = findViewById(R.id.etEmailAddress);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        pbSignup = findViewById(R.id.pbSignup);

        fbAuth = FirebaseAuth.getInstance();

        Button btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                emailAddress = etEmailAddress.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                confirmPassword = etConfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(emailAddress)) {
                    etEmailAddress.setError(getString(R.string.loginsignup_emailerror));
                    return;
                }

                if (password.length() < 6) {
                    etPassword.setError(getString(R.string.signup_passworderror));
                    return;
                }

                if (!TextUtils.equals(password, confirmPassword)) {
                    etPassword.setError(getString(R.string.signup_passwordconfirmerror));
                    etConfirmPassword.setError(getString(R.string.signup_passwordconfirmerror));
                    return;
                }

                pbSignup.setVisibility(View.VISIBLE);

                fbAuth.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, getString(R.string.signup_registrationsuccessmessage), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignupActivity.this, getString(R.string.signup_registrationfailuremessage), Toast.LENGTH_SHORT).show();
                        }
                        pbSignup.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

        Button btnProceedLogin = findViewById(R.id.btnProceedLogin);
        btnProceedLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}