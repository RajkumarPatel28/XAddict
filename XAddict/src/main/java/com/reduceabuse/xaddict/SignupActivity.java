package com.reduceabuse.xaddict;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmailAddress, etPassword, etConfirmPassword;

    FirebaseAuth fbAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_signup);

       etEmailAddress = findViewById(R.id.etEmailAddress);
       etPassword = findViewById(R.id.etPassword);
       etConfirmPassword = findViewById(R.id.etConfirmPassword);

       fbAuth = FirebaseAuth.getInstance();

       Button btnSignup = findViewById(R.id.btnSignup);
       Button btnProceedLogin = findViewById(R.id.btnProceedLogin);

       btnSignup.setOnClickListener(this);
       btnProceedLogin.setOnClickListener(this);

/*       if(fbAuth.getCurrentUser() != null)
       {
           Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
           startActivity(intent);
       }*/
    }

    @Override
    public void onClick(View v) {

        String emailAddress, password, confirmPassword;

        emailAddress = etEmailAddress.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        confirmPassword = etConfirmPassword.getText().toString().trim();


        switch(v.getId())
        {
            case R.id.btnSignup:
                if (TextUtils.isEmpty(emailAddress)) {
                    etEmailAddress.setError("Enter a valid email address");
                    return;
                }

                if (password.length() < 6)
                {
                    etPassword.setError("Password must be 6 or more characters");
                    return;
                }

                if (!TextUtils.equals(password, confirmPassword))
                {
                    etPassword.setError("Password does not match");
                    etConfirmPassword.setError("Password does not match");
                    return;
                }

                fbAuth.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(SignupActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }

                        else
                        {
                            Toast.makeText(SignupActivity.this, "Registration Failed:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

            case R.id.btnProceedLogin:
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}