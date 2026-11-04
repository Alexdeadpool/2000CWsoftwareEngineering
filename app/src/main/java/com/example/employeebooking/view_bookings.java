package com.example.employeebooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class view_bookings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_view_bookings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Find the saved booking
        TextView txtSavedBooking = findViewById(R.id.savedBooking);

        //Read booking from shared preferenced
        SharedPreferences prefs = getSharedPreferences("BookingPrefs", MODE_PRIVATE);
        String savedBooking = prefs.getString("SAVED_BOOKING", "No current bookings.");

        //Display the text
        txtSavedBooking.setText(savedBooking);

        // Dashboard Back Button
        Button dashboardButton = findViewById(R.id.dashboard);
        dashboardButton.setOnClickListener(v -> {
            Intent intent = new Intent(view_bookings.this, employee_dashboard.class);
            startActivity(intent);
        });
    }
}