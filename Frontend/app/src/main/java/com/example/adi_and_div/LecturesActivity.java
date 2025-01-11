package com.example.adi_and_div;

import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LecturesActivity extends AppCompatActivity {

    private Spinner branchSpinner, semesterSpinner, examTypeSpinner;
    private RecyclerView linksRecyclerView;

    private LinksAdapter linksAdapter;
    private Map<String, List<String>> lecturesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);

        branchSpinner = findViewById(R.id.spinner_branch);
        semesterSpinner = findViewById(R.id.spinner_semester);
        examTypeSpinner = findViewById(R.id.spinner_exam_type);
        linksRecyclerView = findViewById(R.id.linksRecyclerView);

        // Initialize dummy data
        initializeData();

        // Initialize RecyclerView
        linksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        linksAdapter = new LinksAdapter(this, Arrays.asList("No links available"));
        linksRecyclerView.setAdapter(linksAdapter);

        // Set up spinners
        setupSpinners();

        // Update links when spinner values change
        setupSpinnerListeners();
    }

    private void initializeData() {
        lecturesData = new HashMap<>();
        lecturesData.put("CSE_1_Midsem", Arrays.asList("https://youtube.com/lecture1", "https://youtube.com/lecture2"));
        lecturesData.put("CSE_1_Endsem", Arrays.asList("https://youtube.com/lecture3", "https://youtube.com/lecture4"));
        lecturesData.put("ECE_1_Midsem", Arrays.asList("https://youtube.com/lecture5", "https://youtube.com/lecture6"));
        lecturesData.put("ECE_2_Endsem", Arrays.asList("https://youtube.com/lecture7", "https://youtube.com/lecture8"));
        lecturesData.put("CSE_2_Midsem", Arrays.asList("https://youtube.com/lecture9", "https://youtube.com/lecture10"));
        lecturesData.put("MECH_1_Midsem", Arrays.asList("https://youtube.com/lecture11", "https://youtube.com/lecture12"));
    }

    private void setupSpinners() {
        String[] branches = {"CSE", "ECE", "MECH"};
        String[] semesters = {"1", "2", "3", "4", "5"};
        String[] examTypes = {"Midsem", "Endsem"};

        ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branches);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(branchAdapter);

        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, semesters);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(semesterAdapter);

        ArrayAdapter<String> examTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, examTypes);
        examTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        examTypeSpinner.setAdapter(examTypeAdapter);
    }

    private void setupSpinnerListeners() {
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                updateLinks();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };

        branchSpinner.setOnItemSelectedListener(listener);
        semesterSpinner.setOnItemSelectedListener(listener);
        examTypeSpinner.setOnItemSelectedListener(listener);
    }

    private void updateLinks() {
        String branch = (String) branchSpinner.getSelectedItem();
        String semester = (String) semesterSpinner.getSelectedItem();
        String examType = (String) examTypeSpinner.getSelectedItem();

        String key = branch + "_" + semester + "_" + examType;
        Log.d("LecturesActivity", "Key: " + key);

        List<String> links = lecturesData.getOrDefault(key, Arrays.asList("No links available"));
        linksAdapter = new LinksAdapter(this, links);
        linksRecyclerView.setAdapter(linksAdapter);
    }
}
