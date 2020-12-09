package com.reduceabuse.xaddict;

// Reduce Abuse

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ScheduleActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText etDate;
    Spinner spTime;
    String date, time, userID;
    String[] scheduleTimes;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Objects.requireNonNull(getSupportActionBar()).hide();

        database = FirebaseDatabase.getInstance();
        userID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        scheduleTimes = getResources().getStringArray(R.array.schedule_times);

        loadBooking();

        etDate = findViewById(R.id.etDate);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment fragment = new DatePickerFragment();
                fragment.show(getSupportFragmentManager(), null);
            }
        });

        Button btnBook = findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(date)) {
                    etDate.setError("");
                    return;

                } else {
                    etDate.setError(null);
                }

                saveBooking();

                Toast.makeText(ScheduleActivity.this, getString(R.string.schedule_schedulemessage), Toast.LENGTH_SHORT).show();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(getString(R.string.schedule_schedulenotificationid), getString(R.string.schedule_schedulenotificationid), NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager manager = getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(ScheduleActivity.this, getString(R.string.schedule_schedulenotificationid));
                builder.setContentTitle(getString(R.string.app_name));
                builder.setContentText(getString(R.string.schedule_schedulemessage));
                builder.setSmallIcon(R.drawable.ic_message);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(ScheduleActivity.this);
                managerCompat.notify(1, builder.build());
            }
        });

        spTime = findViewById(R.id.spTime);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ScheduleActivity.this, android.R.layout.browser_link_context_header, getResources().getStringArray(R.array.schedule_times));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTime.setAdapter(adapter);
    }

    public void loadBooking() {
        ref = database.getReference(getString(R.string.schedule_title)).child(userID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(getString(R.string.schedule_datehint)).exists() && snapshot.child(getString(R.string.schedule_time)).exists()) {
                    date = snapshot.child(getString(R.string.schedule_datehint)).getValue().toString();
                    time = snapshot.child(getString(R.string.schedule_time)).getValue().toString();

                    etDate.setText(date);

                    spTime.setSelection(Arrays.asList(scheduleTimes).indexOf(time));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void saveBooking() {
        ref = database.getReference(getString(R.string.schedule_title)).child(userID);
        time = spTime.getSelectedItem().toString();

        ref.child(getString(R.string.schedule_datehint)).setValue(date);
        ref.child(getString(R.string.schedule_time)).setValue(time);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        date = DateFormat.getDateInstance().format(calendar.getTime());

        DateFormat dateFormat = DateFormat.getDateInstance();
        Date currentDate = Calendar.getInstance().getTime();
        String strCurrentDate = dateFormat.format(currentDate);

        if (strCurrentDate.equals(date)) {
            Toast.makeText(ScheduleActivity.this, getString(R.string.schedule_bookmessage), Toast.LENGTH_SHORT).show();
            date = null;
            etDate.setText(null);

        } else {
            etDate = findViewById(R.id.etDate);
            etDate.setText(date);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ScheduleActivity.this, HomepageActivity.class);
        startActivity(intent);
    }
}