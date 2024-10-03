package com.example.prac02;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    private EmployeeViewModel employeeViewModel;
    private TextView employeeDataTextView;
    private EditText staffIdEditText, fullNameEditText, birthDateEditText, salaryEditText;
    private Button addEmployeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Elements
        employeeDataTextView = findViewById(R.id.employeeDataTextView);
        staffIdEditText = findViewById(R.id.staffIdEditText);
        fullNameEditText = findViewById(R.id.staffIdEditText);
        birthDateEditText = findViewById(R.id.staffIdEditText);
        salaryEditText = findViewById(R.id.salaryEditText);
        addEmployeeButton = findViewById(R.id.addEmployeeButton);

        // Initialize ViewModel
        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);

        // Observe changes in the employee list and update UI
        employeeViewModel.getEmployees().observe(this, employees -> {
            // Display the list of employees in the TextView
            StringBuilder employeeData = new StringBuilder();
            for (Employee employee : employees) {
                employeeData.append(employee.getStaffId()).append("-")
                        .append(employee.getFullName()).append("-")
                        .append(employee.getBirthDate()).append("-")
                        .append(employee.getSalary()).append("\n");
            }
            employeeDataTextView.setText(employeeData.toString());
        });

        // Add employee button click listener
        addEmployeeButton.setOnClickListener(v -> {
            String staffId = staffIdEditText.getText().toString();
            String fullName = fullNameEditText.getText().toString();
            String birthDate = birthDateEditText.getText().toString();
            long salary = Long.parseLong(salaryEditText.getText().toString());

            Employee newEmployee = new Employee(staffId, fullName, birthDate, salary);
            employeeViewModel.addEmployee(newEmployee);
        });
    }
}
