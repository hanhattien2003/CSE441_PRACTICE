package com.example.prac02;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class EmployeeViewModel extends ViewModel {
    private MutableLiveData<List<Employee>> employeeList;

    // Initialize the employee list
    public LiveData<List<Employee>> getEmployees() {
        if (employeeList == null) {
            employeeList = new MutableLiveData<>(new ArrayList<>());
        }
        return employeeList;
    }

    // Method to add new employee to the list
    public void addEmployee(Employee employee) {
        List<Employee> currentList = employeeList.getValue();
        currentList.add(employee);
        employeeList.setValue(currentList); // Notify observers of the data change
    }
}
