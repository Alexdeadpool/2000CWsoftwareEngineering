package com.example.employeebooking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class booking_type extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_booking_type);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Find meeting button
        Button meetingButton = findViewById(R.id.meeting);

        //onClick event
        meetingButton.setOnClickListener(v -> {
            Log.d("TYPE", "Meeting");
            Intent intent = new Intent(booking_type.this, time_selector.class);
            intent.putExtra("TYPE", "Meeting");
            startActivity(intent);
        });
        //Find Seminar button
        Button seminarButton = findViewById(R.id.seminar);

        //onClick event
        seminarButton.setOnClickListener(v -> {
            Log.d("TYPE", "Seminar");
            Intent intent = new Intent(booking_type.this, time_selector.class);
            intent.putExtra("TYPE", "Seminar");
            startActivity(intent);
        });
        //Find Dashboard button
        Button dashboardButton = findViewById(R.id.dashboard);

        //onClick event
        dashboardButton.setOnClickListener(v -> {
            Intent intent = new Intent(booking_type.this, employee_dashboard.class);
            startActivity(intent);
        });
    }
}