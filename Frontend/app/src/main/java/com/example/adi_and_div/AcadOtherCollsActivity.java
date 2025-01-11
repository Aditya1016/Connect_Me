package com.example.adi_and_div;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;


public class AcadOtherCollsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acad_other_colls);

        // List of colleges
        String[] colleges = {
                "IIT Bombay", "IIT Delhi", "IIT Madras", "IIT Kanpur", "IIT Kharagpur",
                "IIT Roorkee", "IIT Guwahati","IIT Hyderabad","IIT Mandi","IIT Dhanbad","NIT Trichy", "NIT Surathkal",
                "NIT Warangal", "NIT Rourkela", "NIT Calicut", "IIT BHU",
                "NIT Kurukshetra", "NIT Durgapur", "IIT Bhubaneswar"
        };

        // Show searchable spinner when clicking the button
        Button selectCollegeButton = findViewById(R.id.button17);
        selectCollegeButton.setOnClickListener(view -> {
            CustomSearchableSpinner spinner = new CustomSearchableSpinner(
                    this,
                    colleges,
                    "Select a College"
            );
            spinner.showSearchableSpinner();
        });



        // Handle Syllabus button click
        Button syllabusButton = findViewById(R.id.button15);
        syllabusButton.setOnClickListener(view -> Toast.makeText(this, "Syllabus Clicked", Toast.LENGTH_SHORT).show());

        // Handle PYQs button click
        Button pyqsButton = findViewById(R.id.button16);
        pyqsButton.setOnClickListener(view -> Toast.makeText(this, "PYQs Clicked", Toast.LENGTH_SHORT).show());
    }
}