package com.example.prac02;

public class Employee {
    private String staffId;
    private String fullName;
    private String birthDate;
    private long salary;

    public Employee(String staffId, String fullName, String birthDate, long salary) {
        this.staffId = staffId;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    // Getters and setters
    public String getStaffId() { return staffId; }
    public String getFullName() { return fullName; }
    public String getBirthDate() { return birthDate; }
    public long getSalary() { return salary; }
}
