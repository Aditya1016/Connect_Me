package com.example.adi_and_div;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class JrOtherCollsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jr_other_colls);

        // List of colleges
        String[] colleges = {
                "IIT Bombay", "IIT Delhi", "IIT Madras", "IIT Kanpur", "IIT Kharagpur",
                "IIT Roorkee", "IIT Guwahati","IIT Hyderabad","IIT Mandi","IIT Dhanbad","NIT Trichy", "NIT Surathkal",
                "NIT Warangal", "NIT Rourkela", "NIT Calicut", "IIT BHU",
                "NIT Kurukshetra", "NIT Durgapur", "IIT Bhubaneswar"
        };

        // Show searchable spinner when clicking the button
        Button selectCollegeButton = findViewById(R.id.button18);
        selectCollegeButton.setOnClickListener(view -> {
            CustomSearchableSpinner spinner = new CustomSearchableSpinner(
                    this,
                    colleges,
                    "Select a College"
            );
            spinner.showSearchableSpinner();
        });
    }
}