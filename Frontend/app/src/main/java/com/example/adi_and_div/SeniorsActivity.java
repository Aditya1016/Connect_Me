package com.example.adi_and_div;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SeniorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniors);

        // Dummy data for seniors
        List<Student> seniors = new ArrayList<>();
        seniors.add(new Student("Adi", "Your branch", "A", "xxxxxxxxxxx"));
        seniors.add(new Student("Jimmy", "Your branch", "B", "xxxxxxxxxxx"));
        seniors.add(new Student("Jack", "Your branch", "B", "xxxxxxxxxxx"));
        seniors.add(new Student("Mohan", "Your branch", "C", "xxxxxxxxxxx"));
        seniors.add(new Student("Suman", "Your branch", "A", "xxxxxxxxxxx"));
        seniors.add(new Student("Sohan", "Your branch", "B", "xxxxxxxxxxx"));
        seniors.add(new Student("Sohan", "Your branch", "B", "xxxxxxxxxxx"));
        seniors.add(new Student("Sohan", "Your branch", "B", "xxxxxxxxxxx"));
        seniors.add(new Student("Sohan", "Your branch", "B", "xxxxxxxxxxx"));
        seniors.add(new Student("Sohan", "Your branch", "B", "xxxxxxxxxxx"));
        seniors.add(new Student("Sohan", "Your branch", "B", "xxxxxxxxxxx"));
        seniors.add(new Student("Sohan", "Your branch", "B", "xxxxxxxxxxx"));
        seniors.add(new Student("Sohan", "Your branch", "B", "xxxxxxxxxxx"));

        // Get the container layout for seniors list
        LinearLayout seniorsListContainer = findViewById(R.id.Seniors_list_container);

        // Dynamically add each senior's details to the container
        for (Student senior : seniors) {
            LinearLayout entryLayout = new LinearLayout(this);
            entryLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView studentView = new TextView(this);
            studentView.setText(String.format(
                    "Name: %s\nBranch: %s\nSec: %s\nContact: %s",
                    senior.getName(), senior.getBranch(), senior.getSection(), senior.getContact()
            ));
            studentView.setPadding(10, 10, 10, 10);
            studentView.setTextSize(16);

            // "Request Connection" button
            Button requestButton = new Button(this);
            requestButton.setText("Request Connection");
            requestButton.setOnClickListener(view -> {
                Toast.makeText(SeniorsActivity.this,
                        "Connection request sent to " + senior.getName(),
                        Toast.LENGTH_SHORT).show();
            });

            entryLayout.addView(studentView);
            entryLayout.addView(requestButton);

            seniorsListContainer.addView(entryLayout);

            // Add spacing between entries
            TextView spacer = new TextView(this);
            spacer.setHeight(16);
            seniorsListContainer.addView(spacer);
        }

        // Handle "Other Colleges" button click
        Button otherCollegesButton = findViewById(R.id.button10);
        otherCollegesButton.setOnClickListener(view -> {
            Intent intent = new Intent(SeniorsActivity.this, SrOtherCollsActivity.class);
            startActivity(intent);
        });
    }

    // Helper class for student details
    public static class Student {
        private final String name;
        private final String branch;
        private final String section;
        private final String contact;

        public Student(String name, String branch, String section, String contact) {
            this.name = name;
            this.branch = branch;
            this.section = section;
            this.contact = contact;
        }

        public String getName() {
            return name;
        }

        public String getBranch() {
            return branch;
        }

        public String getSection() {
            return section;
        }

        public String getContact() {
            return contact;
        }
    }
}