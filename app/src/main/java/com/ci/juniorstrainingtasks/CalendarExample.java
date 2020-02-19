package com.ci.juniorstrainingtasks;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CalendarExample extends AppCompatActivity implements View.OnClickListener {


    private ArrayList<String> arrFormatList;
    private CalendarView cvCalendar;
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_example);
        init();
    }

    private void init() {
        getList();
        cvCalendar = findViewById(R.id.cvCalendar);
        cvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int monthOfYear, int dayOfMonth) {
                showToast("Selected date is"+dayOfMonth+"/"+monthOfYear+"/"+year);
            }
        });
        findViewById(R.id.btnDatePicker).setOnClickListener(this);
        findViewById(R.id.btnTimePicker).setOnClickListener(this);
        findViewById(R.id.btnFormatDate).setOnClickListener(this);
    }

    private void getList() {
        arrFormatList = new ArrayList<String>();
        arrFormatList.add("hh:mm");
        arrFormatList.add("HH:mm");
        arrFormatList.add("HH:mm:ss");
        arrFormatList.add("a");
        arrFormatList.add("dd-MM-yyyy");
        arrFormatList.add("dd-MM-yyyy HH:mm");
        arrFormatList.add("yyyy-MM-dd");
        arrFormatList.add("dd MMM yyyy, hh:mm a");
        arrFormatList.add("yyyy-MM-dd HH:mm:ss");
        arrFormatList.add("EEE dd-MMM-yyyy");
        arrFormatList.add("EEE dd");
        arrFormatList.add("MMMM yyyy");
        arrFormatList.add("Z");
        arrFormatList.add("z");
        arrFormatList.add("zzzz");
        arrFormatList.add("MMM");
        arrFormatList.add("EEEE");
        arrFormatList.add("EEEE dd MMM, yyyy");
        arrFormatList.add("dd MMMM, yyyy");
        arrFormatList.add("hh:mm a");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnDatePicker:
                showDatePicker();
                break;

            case R.id.btnTimePicker:
                showTimePicker();
                break;

            case R.id.btnFormatDate:
                formatDate();
                break;
        }
    }

    private void formatDate() {
        int index = new Random().nextInt(arrFormatList.size());
        SimpleDateFormat formatter = new SimpleDateFormat(arrFormatList.get(index));
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        String strMessage = " Formatted Date --> "+formatter.format(date) + "\n Formatted Calendar Date --> "+formatter.format(calendar.getTime());
        showToast(strMessage);
    }

    private void showDatePicker() {
        if (datePicker != null && datePicker.isShowing()){
            datePicker.dismiss();
        }
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        datePicker = new DatePickerDialog(CalendarExample.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                showToast("Selected date is"+day+"/"+month+"/"+year);
            }
        },mYear, mMonth, mDay);
        datePicker.show();
    }

    private void showTimePicker() {
        if (timePicker!= null && timePicker.isShowing()){
            timePicker.dismiss();
        }
        Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                showToast("Seleced date is "+hourOfDay + ":" + minute);
            }
        }, mHour, mMinute, true);
        timePicker.setMessage("Pick time");
        timePicker.setCancelable(false);
        timePicker.setCanceledOnTouchOutside(false);
        timePicker.show();
    }

    private void showToast(String strMessage) {
        Toast.makeText(CalendarExample.this, strMessage, Toast.LENGTH_SHORT).show();
    }

}
