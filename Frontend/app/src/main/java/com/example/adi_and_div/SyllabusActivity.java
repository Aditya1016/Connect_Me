package com.example.adi_and_div;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SyllabusActivity extends AppCompatActivity {

    private HashMap<String, List<Subject>> syllabusData;
    private LinearLayout syllabusContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        // Initialize syllabus container
        syllabusContainer = findViewById(R.id.syllabus_container);

        // Initialize syllabus data
        initializeSyllabusData();

        // Set up branch spinner
        Spinner branchSpinner = findViewById(R.id.branch_spinner);
        ArrayAdapter<String> branchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"CSE", "ECE", "MECH", "EEE"});
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(branchAdapter);

        // Set up semester spinner
        Spinner semesterSpinner = findViewById(R.id.semester_spinner);
        ArrayAdapter<String> semesterAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(semesterAdapter);

        // Listener for selection
        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSyllabus(branchSpinner.getSelectedItem().toString(), semesterSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSyllabus(branchSpinner.getSelectedItem().toString(), semesterSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void initializeSyllabusData() {
        syllabusData = new HashMap<>();

        // Example syllabus for CSE
        List<Subject> cseSem1 = new ArrayList<>();
        cseSem1.add(new Subject("Mathematics",
                List.of("Linear Algebra", "Calculus", "Probability", "Differentiation", "Integration")));
        cseSem1.add(new Subject("Physics",
                List.of("Kinematics", "Thermodynamics", "Waves", "Optics", "Quantum Mechanics")));
        cseSem1.add(new Subject("Programming",
                List.of("Variables", "Loops", "Arrays", "Functions", "OOP Concepts")));
        syllabusData.put("CSE-1", cseSem1);

        List<Subject> cseSem2 = new ArrayList<>();
        cseSem2.add(new Subject("Data Structures",
                List.of("Arrays", "Linked Lists", "Stacks", "Queues", "Trees")));
        cseSem2.add(new Subject("Discrete Mathematics",
                List.of("Set Theory", "Graph Theory", "Combinatorics", "Logic", "Number Theory")));
        cseSem2.add(new Subject("Digital Logic",
                List.of("Boolean Algebra", "Logic Gates", "K-Maps", "Flip Flops", "Sequential Circuits")));
        syllabusData.put("CSE-2", cseSem2);

        // Example syllabus for ECE
        List<Subject> eceSem1 = new ArrayList<>();
        eceSem1.add(new Subject("Circuits",
                List.of("Ohm's Law", "Kirchhoff's Law", "AC/DC Circuits", "Resistors", "Capacitors")));
        eceSem1.add(new Subject("Mathematics",
                List.of("Linear Algebra", "Calculus", "Probability", "Differentiation", "Integration")));
        syllabusData.put("ECE-1", eceSem1);

        List<Subject> eceSem2 = new ArrayList<>();
        eceSem2.add(new Subject("Signals and Systems",
                List.of("Fourier Series", "Laplace Transform", "Convolution", "Sampling Theorem", "Filters")));
        eceSem2.add(new Subject("Electronic Devices",
                List.of("Diodes", "Transistors", "MOSFETs", "Amplifiers", "Oscillators")));
        eceSem2.add(new Subject("Digital Logic Design",
                List.of("Boolean Algebra", "Logic Gates", "Flip Flops", "Registers", "Counters")));
        syllabusData.put("ECE-2", eceSem2);

        // Example syllabus for Mechanical Engineering (ME)
        List<Subject> mechSem1 = new ArrayList<>();
        mechSem1.add(new Subject("Engineering Mechanics",
                List.of("Statics", "Dynamics", "Friction", "Kinetics", "Work and Energy")));
        mechSem1.add(new Subject("Mathematics",
                List.of("Linear Algebra", "Calculus", "Differential Equations", "Vector Calculus")));
        mechSem1.add(new Subject("Thermodynamics",
                List.of("Laws of Thermodynamics", "Entropy", "Heat Transfer", "Fluid Mechanics")));
        syllabusData.put("MECH-1", mechSem1);

        List<Subject> mechSem2 = new ArrayList<>();
        mechSem2.add(new Subject("Strength of Materials",
                List.of("Stress and Strain", "Bending Moment", "Shear Force", "Torsion", "Buckling")));
        mechSem2.add(new Subject("Kinematics of Machines",
                List.of("Velocity Analysis", "Acceleration Analysis", "Gear Trains", "Cams", "Flywheels")));
        mechSem2.add(new Subject("Manufacturing Processes",
                List.of("Casting", "Welding", "Machining", "Forging", "Powder Metallurgy")));
        syllabusData.put("MECH-2", mechSem2);

        // Example syllabus for Electrical Engineering (EE)
        List<Subject> eeeSem1 = new ArrayList<>();
        eeeSem1.add(new Subject("Basic Electrical Engineering",
                List.of("DC Circuits", "AC Circuits", "Transformers", "Motors", "Generators")));
        eeeSem1.add(new Subject("Mathematics",
                List.of("Linear Algebra", "Calculus", "Differential Equations", "Laplace Transforms")));
        eeeSem1.add(new Subject("Physics",
                List.of("Electromagnetics", "Wave Optics", "Modern Physics", "Quantum Mechanics")));
        syllabusData.put("EEE-1", eeeSem1);

        List<Subject> eeeSem2 = new ArrayList<>();
        eeeSem2.add(new Subject("Circuit Analysis",
                List.of("Thevenin's Theorem", "Norton's Theorem", "Mesh Analysis", "Nodal Analysis", "Transient Analysis")));
        eeeSem2.add(new Subject("Electrical Machines",
                List.of("DC Machines", "Synchronous Machines", "Induction Motors", "Transformers", "Special Machines")));
        eeeSem2.add(new Subject("Control Systems",
                List.of("Open Loop Systems", "Closed Loop Systems", "PID Controllers", "Bode Plot", "State Space Analysis")));
        syllabusData.put("EEE-2", eeeSem2);
    }


    private void updateSyllabus(String branch, String semester) {
        syllabusContainer.removeAllViews();
        String key = branch.toUpperCase() + "-" + semester;
        List<Subject> subjects = syllabusData.get(key);

        if (subjects != null) {
            for (Subject subject : subjects) {
                TextView subjectTitle = new TextView(this);
                subjectTitle.setText(subject.getName());
                subjectTitle.setTextSize(18);
                subjectTitle.setPadding(10, 10, 10, 10);
                syllabusContainer.addView(subjectTitle);

                for (String module : subject.getModules()) {
                    TextView moduleText = new TextView(this);
                    moduleText.setText(" - " + module);
                    moduleText.setPadding(20, 5, 10, 5);
                    syllabusContainer.addView(moduleText);
                }
            }
        } else {
            TextView noDataText = new TextView(this);
            noDataText.setText("No syllabus data available for the selected branch and semester.");
            noDataText.setTextSize(16);
            syllabusContainer.addView(noDataText);
        }
    }

    // Helper class for Subject
    private static class Subject {
        private final String name;
        private final List<String> modules;

        public Subject(String name, List<String> modules) {
            this.name = name;
            this.modules = modules;
        }

        public String getName() {
            return name;
        }

        public List<String> getModules() {
            return modules;
        }
    }
}
