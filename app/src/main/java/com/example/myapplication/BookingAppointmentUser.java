package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.CalendarView;

import java.util.Calendar;


public class BookingAppointmentUser extends AppCompatActivity {
    //represents start and end time of a particular day
    int d,m,y;
    int startHour;
    int startMin;
    int endHour;
    int endMin;

    String newDate;

    //current day
    String currentDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment_user);




    }

    //takes information from the selected day of the week and initialize startHour, endHour etc....
    public void parseTimes(){

    }

    public void getDayOfWeek(){
        CalendarView calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                switch (dayOfWeek ) {
                    case Calendar.SUNDAY:
                        currentDay= "sun";
                    case Calendar.MONDAY:
                        currentDay= "mon";
                    case Calendar.TUESDAY:
                        currentDay= "sun";
                    case Calendar.WEDNESDAY:
                        currentDay= "tues";
                    case Calendar.THURSDAY:
                        currentDay= "thurs";
                    case Calendar.FRIDAY:
                        currentDay= "fri";
                    case Calendar.SATURDAY:
                        currentDay= "sat";
                }
                m = month + 1;
                d = dayOfMonth;
                y = year;

                // output to log cat **not sure how to format year to two places here**
                newDate = y+"-"+m+"-"+d;

                System.out.println(newDate);
                return;

            }
        });
    }


}
