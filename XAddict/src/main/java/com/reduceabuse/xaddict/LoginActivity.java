package com.reduceabuse.xaddict;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText etLoginEmailAddress, etLoginPassword, etResetPassword;
    TextView tvLoginForgotPassword;
    ProgressBar pbLogin;
    FirebaseAuth fbAuth;
    String emailAddress, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        etLoginEmailAddress = findViewById(R.id.etLoginEmailAddress);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        pbLogin = findViewById(R.id.pbLogin);

        fbAuth = FirebaseAuth.getInstance();

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                emailAddress = etLoginEmailAddress.getText().toString().trim();
                password = etLoginPassword.getText().toString().trim();

                if (TextUtils.isEmpty(emailAddress)) {
                    etLoginEmailAddress.setError(getString(R.string.login_emailerror));
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etLoginPassword.setError(getString(R.string.login_passworderror));
                    return;
                }

                pbLogin.setVisibility(View.VISIBLE);

                fbAuth.signInWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, getString(R.string.login_loginerror), Toast.LENGTH_SHORT).show();
                        }
                        pbLogin.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

        Button btnLoginProceedSignup = findViewById(R.id.btnLoginProceedSignup);
        btnLoginProceedSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        tvLoginForgotPassword = findViewById(R.id.tvLoginForgotPassword);
        tvLoginForgotPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                etResetPassword = new EditText(v.getContext());
                emailAddress = etLoginEmailAddress.getText().toString().trim();
                etResetPassword.setText(emailAddress);

                builder.setMessage(getString(R.string.login_dialogresetmessage))
                        .setView(etResetPassword)
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.login_dialogpositive), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                emailAddress = etResetPassword.getText().toString().trim();

                                if (TextUtils.isEmpty(emailAddress)) {
                                    Toast.makeText(LoginActivity.this, getString(R.string.login_dialogresetfailuremessage), Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                fbAuth.sendPasswordResetEmail(emailAddress).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(LoginActivity.this, getString(R.string.login_dialogresetsuccessmessage), Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginActivity.this, getString(R.string.login_dialogresetfailuremessage), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton(getString(R.string.login_dialognegative), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.setTitle(getString(R.string.login_dialogresettitle));
                dialog.setIcon(android.R.drawable.ic_dialog_email);
                dialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.login_dialogbackmessage))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.login_dialogpositive), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoginActivity.this.finish();
                    }
                })
                .setNegativeButton(getString(R.string.login_dialognegative), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setTitle(getString(R.string.login_dialogbacktitle));
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.show();
    }
}