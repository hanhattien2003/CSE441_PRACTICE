package com.example.prac04;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.io.Serializable;
public class item_student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_student);


    }
        static class Student implements Serializable {


            public Student(String id, String firstName, String lastName, String middleName, String gender, String birthDate, String email, String address, String major, double gpa, int year) {
                this.id = id;
                this.firstName = firstName;
                this.lastName = lastName;
                this.middleName = middleName;
                this.gender = gender;
                this.birthDate = birthDate;
                this.email = email;
                this.address = address;
                this.major = major;
                this.gpa = gpa;
                this.year = year;
            }



            public Student() {
                
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public String getMiddleName() {
                return middleName;
            }

            public void setMiddleName(String middleName) {
                this.middleName = middleName;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getBirthDate() {
                return birthDate;
            }

            public void setBirthDate(String birthDate) {
                this.birthDate = birthDate;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getMajor() {
                return major;
            }

            public void setMajor(String major) {
                this.major = major;
            }

            public double getGpa() {
                return gpa;
            }

            public void setGpa(double gpa) {
                this.gpa = gpa;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
            String id;
            String lastName;
            String firstName;
            String middleName;
            String gender;
            String birthDate;
            String email;
            String address;
            String major;
            double gpa;
            int year;

            public void setFullName(String fullName) {
            }
            public String getFullName() {
                return firstName + " " + (middleName != null ? middleName : "") + " " + lastName;
            }

            // Constructor, Getters và Setters
        }
}
