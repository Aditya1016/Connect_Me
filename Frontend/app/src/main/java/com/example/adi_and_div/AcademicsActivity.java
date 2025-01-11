package com.example.adi_and_div;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AcademicsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academics);

        Spinner branchSpinner = findViewById(R.id.spinner_branch);
        ArrayAdapter<CharSequence> branchAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.branch_array,
                android.R.layout.simple_spinner_item
        );
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(branchAdapter);

        // Spinner for Semester
        Spinner semesterSpinner = findViewById(R.id.spinner_semester);
        ArrayAdapter<CharSequence> semesterAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.semester_array,
                android.R.layout.simple_spinner_item
        );
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(semesterAdapter);

        // Set listeners for Branch Spinner
        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String branch = parent.getItemAtPosition(position).toString();
                Toast.makeText(AcademicsActivity.this, "Selected Branch: " + branch, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Set listeners for Semester Spinner
        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String semester = parent.getItemAtPosition(position).toString();
                Toast.makeText(AcademicsActivity.this, "Selected Semester: " + semester, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Handle Syllabus button click
        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcademicsActivity.this, SyllabusActivity.class);
                startActivity(intent);

            }
        });

        // Handle PYQs button click
        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcademicsActivity.this, PYQsActivity.class);
                startActivity(intent);


            }
        });

        // Handle Lectures button click
        findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcademicsActivity.this, LecturesActivity.class);
                startActivity(intent);

            }
        });

        // Handle Other Colleges button click
        findViewById(R.id.button14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcademicsActivity.this, AcadOtherCollsActivity.class);
                startActivity(intent);

            }
        });
    }
}