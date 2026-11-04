package com.example.employeebooking;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;

//For login attempts
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    private final String valid_email = "test@booking.com";
    private final String valid_password = "password123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find views
        EditText emailInput = findViewById(R.id.editTextTextEmailAddress);
        EditText passwordInput = findViewById(R.id.editTextTextPassword);

        //Find login button
        Button loginButton = findViewById(R.id.login);

        // onClick event with validation
        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (email.equals(valid_email) && password.equals(valid_password)) {
                Intent intent = new Intent(MainActivity.this, employee_dashboard.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Invalid Email or Password!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}