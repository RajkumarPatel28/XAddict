package com.reduceabuse.xaddict;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ScheduleActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Objects.requireNonNull(getSupportActionBar()).hide();

        EditText opencalendar = (EditText) findViewById(R.id.etOpenCalendar);


        opencalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //open time picker
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");

            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        //define time into calendar
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        //get picked date into string
        String strDateString  = DateFormat.getDateInstance().format(calendar.getTime());
        Date currentTime = Calendar.getInstance().getTime();

        DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        String strCurrentDate = dateFormat.format(currentTime);


        if(strCurrentDate.equals(strDateString) ){
            DisplayToast("You are to late for todays appointments");
        }else{
            EditText editText = (EditText) findViewById(R.id.etOpenCalendar);
            editText.setText(strDateString);
        }

        //show the time picked

    }

    private void DisplayToast(String msg)
    {
        Toast.makeText(getBaseContext(), msg,
                Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ScheduleActivity.this, HomepageActivity.class);
        startActivity(intent);

    }
}