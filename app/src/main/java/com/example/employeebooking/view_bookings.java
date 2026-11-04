package com.example.employeebooking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

//Toast for confirmation
import android.widget.Toast;

//Cancellation button imports for arrays
import java.util.ArrayList;
import java.util.Arrays;

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
        // 1. Find ListView
        ListView listView = findViewById(R.id.listViewBookings);

        // 2. Read stored bookings from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("BookingPrefs", MODE_PRIVATE);
        String currentList = prefs.getString("SAVED_BOOKING", "");

        ArrayList<String> bookingList = new ArrayList<>();
        if (!currentList.trim().isEmpty()) {
            bookingList.addAll(Arrays.asList(currentList.split("\n\n")));
        }

        // 3. Set up List Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookingList);
        listView.setAdapter(adapter);

        // 4. Tap to cancel popup ("Are you sure you'd like to cancel?")
        listView.setOnItemClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(view_bookings.this)
                    .setTitle("Cancel Booking")
                    .setMessage("Are you sure you'd like to cancel?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        bookingList.remove(position);
                        adapter.notifyDataSetChanged();

                        StringBuilder sb = new StringBuilder();
                        for (String booking : bookingList) {
                            sb.append(booking).append("\n\n");
                        }
                        prefs.edit().putString("SAVED_BOOKING", sb.toString()).apply();

                        Toast.makeText(view_bookings.this, "Booking cancelled", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null) // "No" dismisses popup
                    .show();
        });

        // Dashboard Back Button
        Button dashboardButton = findViewById(R.id.dashboard);
        dashboardButton.setOnClickListener(v -> {
            Intent intent = new Intent(view_bookings.this, employee_dashboard.class);
            startActivity(intent);
        });
    }
}