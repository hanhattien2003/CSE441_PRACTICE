package com.example.prac04;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailActivity extends AppCompatActivity {

    private ImageView imgGender;
    private TextView tvStudentName, tvStudentId, tvStudentEmail, tvStudentBirthdate, tvStudentAddress, tvStudentMajor, tvStudentGpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        // Ánh xạ các view từ layout
        imgGender = findViewById(R.id.img_gender);
        tvStudentName = findViewById(R.id.tv_student_name);
        tvStudentId = findViewById(R.id.tv_student_id);
        tvStudentEmail = findViewById(R.id.tv_student_email);
        tvStudentBirthdate = findViewById(R.id.tv_student_birthdate);
        tvStudentAddress = findViewById(R.id.tv_student_address);
        tvStudentMajor = findViewById(R.id.tv_student_major);
        tvStudentGpa = findViewById(R.id.tv_student_gpa);

        // Lấy đối tượng Student từ Intent
        item_student.Student student = (item_student.Student) getIntent().getSerializableExtra("student");

        // Gán dữ liệu cho các view
        if (student != null) {
            String setFullName = student.firstName + " " + student.middleName + " " + student.lastName;
            tvStudentName.setText(setFullName);
            tvStudentId.setText("Mã SV: " + student.id);
            tvStudentEmail.setText("Email: " + student.email);
            tvStudentBirthdate.setText("Ngày sinh: " + student.birthDate);
            tvStudentAddress.setText("Địa chỉ: " + student.address);
            tvStudentMajor.setText("Chuyên ngành: " + student.major);
            tvStudentGpa.setText("GPA: " + student.gpa);

            // Hiển thị icon giới tính
            if (student.gender.equals("Nu")) {
                imgGender.setImageResource(R.drawable.female);
            } else {
                imgGender.setImageResource(R.drawable.male);
            }
        }
    }
}
