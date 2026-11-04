package com.example.employeebooking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class time_selector extends AppCompatActivity {
    private String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_timeselctor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Find Dashboard button
        Button dashboardButton = findViewById(R.id.dashboard);

        //onClick event
        dashboardButton.setOnClickListener(v -> {
            Intent intent = new Intent(time_selector.this, employee_dashboard.class);
            startActivity(intent);
        });
        //Pass through booking type
        String bookingType = getIntent().getStringExtra("TYPE");

        //Find views
        CalendarView calendarView = findViewById(R.id.calendarView);
        TimePicker timePickerFrom = findViewById(R.id.timePickerFrom);
        TimePicker timePickerTo = findViewById(R.id.timePickerto);
        Button confirmButton = findViewById(R.id.button);

        //Set defalt date to today (fix for error where date doesn't show up)
        java.util.Calendar c = java.util.Calendar.getInstance();
        selectedDate = c.get(java.util.Calendar.DAY_OF_MONTH) + "/" + (c.get(java.util.Calendar.MONTH) + 1) + "/" + c.get(java.util.Calendar.YEAR);
        //Store calander date
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
        });
        //store "From" time
        confirmButton.setOnClickListener(v -> {
        int fromHour = timePickerFrom.getHour();
        int fromMinute = timePickerFrom.getMinute();
        String fromTime = String.format("%02d:%02d", fromHour, fromMinute);

        //Store "Until" time
        int toHour = timePickerTo.getHour();
        int toMinute = timePickerTo.getMinute();
        String toTime = String.format("%02d:%02d", toHour, toMinute);

        //Save as a numbered list (to allow multiple keep Ids unique)
        android.content.SharedPreferences prefs = getSharedPreferences("BookingPrefs", MODE_PRIVATE);
        int count = prefs.getInt("BOOKING_COUNT", 0);
        prefs.edit().putInt("BOOKING_COUNT", count + 1).apply();

        //Generate a random unguessable booking reference (4 billion combinations)
            String bookingRef = java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            //with random reference ID
            String newBooking = "Ref: #" + bookingRef + " | Type: " + bookingType + " | Date: " + selectedDate + " | From: " + fromTime + " Until: " + toTime + "\n\n";

        //Append to existing list and save
        String currentList = prefs.getString("SAVED_BOOKING", "");
        String updatedList = currentList + newBooking;

        //Save appended list back to shared preferences
            prefs.edit().putString("SAVED_BOOKING", updatedList).apply();

        //Put all together and log them
        Log.d("BOOKING", "Type: " + bookingType + " | Date: " + selectedDate + " | From: " + fromTime + " | Until: " + toTime);
        //show splash text on screen with confirmation
            Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "Booking requested for " + bookingType + " | Date: " + selectedDate + " | From: " + fromTime + " | Until: " + toTime, Snackbar.LENGTH_LONG);
            snackbar.show();
        });

    }
}