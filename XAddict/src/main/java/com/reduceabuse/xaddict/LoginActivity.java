package com.reduceabuse.xaddict;

// Reduce Abuse

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

    SharedPreferences sharedPreferences;
    EditText etEmailAddress, etPassword, etResetPassword;
    CheckBox checkBoxRemember;
    TextView tvForgotPassword;
    ProgressBar pbLogin;
    private FirebaseAuth fbAuth;
    String emailAddress, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        etEmailAddress = findViewById(R.id.etEmailAddress);
        etPassword = findViewById(R.id.etPassword);
        checkBoxRemember = findViewById(R.id.checkBoxRemember);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        getData();

        pbLogin = findViewById(R.id.pbLogin);

        fbAuth = FirebaseAuth.getInstance();

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                emailAddress = etEmailAddress.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(emailAddress)) {
                    etEmailAddress.setError(getString(R.string.loginsignup_emailerror));
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    etPassword.setError(getString(R.string.login_passworderror));
                    return;
                }

                if (checkBoxRemember.isChecked()) {
                    boolean boolIsChecked = checkBoxRemember.isChecked();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(getString(R.string.login_emailsharedpref), emailAddress);
                    editor.putString(getString(R.string.login_passwordsharedpref), password);
                    editor.putBoolean(getString(R.string.login_checkboxsharedpref), boolIsChecked);
                    editor.apply();
                } else {
                    sharedPreferences.edit().clear().apply();
                }

                pbLogin.setVisibility(View.VISIBLE);

                fbAuth.signInWithEmailAndPassword(emailAddress, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (fbAuth.getCurrentUser().isEmailVerified()) {
                                Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, getString(R.string.login_verifymessage), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, getString(R.string.login_loginerror), Toast.LENGTH_SHORT).show();
                        }
                        pbLogin.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

        Button btnLoginProceedSignup = findViewById(R.id.btnProceedSignup);
        btnLoginProceedSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                etResetPassword = new EditText(v.getContext());
                emailAddress = etEmailAddress.getText().toString().trim();
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

    public void getData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (sp.contains(getString(R.string.login_emailsharedpref))) {
            String emailAddress = sp.getString(getString(R.string.login_emailsharedpref), "");
            etEmailAddress.setText(emailAddress);
        }
        if (sp.contains(getString(R.string.login_passwordsharedpref))) {
            String password = sp.getString(getString(R.string.login_passwordsharedpref), "");
            etPassword.setText(password);
        }
        if (sp.contains(getString(R.string.login_checkboxsharedpref))) {
            boolean checkBox = sp.getBoolean(getString(R.string.login_checkboxsharedpref), false);
            checkBoxRemember.setChecked(checkBox);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.login_dialogbackmessage))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.login_dialogpositive), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        System.exit(0);
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