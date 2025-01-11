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

public class JuniorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juniors);

        // Dummy data for juniors
        List<Student> juniors = new ArrayList<>();
        juniors.add(new Student("Adi", "Your branch", "A", "xxxxxxxxxxx"));
        juniors.add(new Student("Jimmy", "Your branch", "B", "xxxxxxxxxxx"));
        juniors.add(new Student("Jack", "Your branch", "B", "xxxxxxxxxxx"));
        juniors.add(new Student("Mohan", "Your branch", "C", "xxxxxxxxxxx"));
        juniors.add(new Student("Suman", "Your branch", "A", "xxxxxxxxxxx"));
        juniors.add(new Student("Sohan", "Your branch", "B", "xxxxxxxxxxx"));
        juniors.add(new Student("Rakesh", "Your branch", "A", "xxxxxxxxxxx"));
        juniors.add(new Student("Rakesh", "Your branch", "A", "xxxxxxxxxxx"));
        juniors.add(new Student("Rakesh", "Your branch", "A", "xxxxxxxxxxx"));
        juniors.add(new Student("Rakesh", "Your branch", "A", "xxxxxxxxxxx"));
        juniors.add(new Student("Rakesh", "Your branch", "A", "xxxxxxxxxxx"));
        juniors.add(new Student("Rakesh", "Your branch", "A", "xxxxxxxxxxx"));


        // Get the container layout for juniors list
        LinearLayout juniorsListContainer = findViewById(R.id.juniors_list_container);

        // Dynamically add each junior's details and button to the container
        for (Student junior : juniors) {
            LinearLayout studentLayout = new LinearLayout(this);
            studentLayout.setOrientation(LinearLayout.HORIZONTAL);


            // TextView for student details
            TextView studentView = new TextView(this);
            studentView.setText(String.format(
                    "Name: %s\nBranch: %s\nSec: %s\nContact: %s",
                    junior.getName(), junior.getBranch(), junior.getSection(), junior.getContact()
            ));
            studentView.setPadding(10, 10, 10, 10);
            studentView.setTextSize(16);

            // Button for "Request Connection"
            Button requestButton = new Button(this);
            requestButton.setText("Request Connection");

            requestButton.setOnClickListener(v -> {
                Toast.makeText(this, "Connection request sent to " + junior.getName(), Toast.LENGTH_SHORT).show();
            });

            studentLayout.addView(studentView);
            studentLayout.addView(requestButton);

            // Add the student layout to the container
            juniorsListContainer.addView(studentLayout);

            // Add spacing between entries
            TextView spacer = new TextView(this);
            spacer.setHeight(16);
            juniorsListContainer.addView(spacer);
        }

        // Handle "Other Colleges" button click
        Button otherCollegesButton = findViewById(R.id.button9);
        otherCollegesButton.setOnClickListener(view -> {
            Intent intent = new Intent(JuniorsActivity.this, JrOtherCollsActivity.class);
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