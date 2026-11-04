package com.example.employeebooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class employee_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_employee_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Find Make a booking button
        Button makeBookingbutton = findViewById(R.id.makebooking);

        //onClick event
        makeBookingbutton.setOnClickListener(v -> {
            Intent intent = new Intent(employee_dashboard.this, booking_type.class);
            startActivity(intent);
        });
        //Find view bookings button
        Button viewBookingsbutton = findViewById(R.id.viewbookings);

        //onClick event
        viewBookingsbutton.setOnClickListener(v -> {
            Intent intent = new Intent(employee_dashboard.this, view_bookings.class);
            startActivity(intent);
        });
        //Find view bookings button
        Button signoutbutton = findViewById(R.id.signout);

        //onClick event
        signoutbutton.setOnClickListener(v -> {
            Intent intent = new Intent(employee_dashboard.this, MainActivity.class);
            startActivity(intent);
        });
    }
}