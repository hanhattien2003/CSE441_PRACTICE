package com.example.prac04;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.regex.Pattern;

public class AddStudent extends AppCompatActivity {

    EditText edtBirthDate;
    EditText edtStudentID, edtName, edtEmail, edtGPA;
    CheckBox checkBox, checkBox2, checkBox3;
    Spinner spin_address, spin_major, spin_year;
    Button btn_add1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        btn_add1 = findViewById(R.id.btn_add1);
        edtStudentID = findViewById(R.id.edt_addid);
        edtName = findViewById(R.id.edt_addname);
        edtBirthDate = findViewById(R.id.edt_adddate);
        edtEmail = findViewById(R.id.edt_addemail);
        edtGPA = findViewById(R.id.edt_gpa);
        btn_add1 = findViewById(R.id.btn_add1);

        // Danh sách nghề nghiệp
        spin_major = findViewById(R.id.spin_major);
        ArrayAdapter<CharSequence> adapterMajor = ArrayAdapter.createFromResource(this,
                R.array.majors, android.R.layout.simple_spinner_item);
        adapterMajor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_major.setAdapter(adapterMajor);

        // Danh sách 63 tỉnh thành
        spin_address = findViewById(R.id.spin_address);
        ArrayAdapter<CharSequence> adapterAddress = ArrayAdapter.createFromResource(this,
                R.array.cities, android.R.layout.simple_spinner_item);
        adapterAddress.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_address.setAdapter(adapterAddress);

        // Năm học
        spin_year = findViewById(R.id.spin_year);
        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(this,
                R.array.years, android.R.layout.simple_spinner_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_year.setAdapter(adapterYear);

        // Ngày sinh
        edtBirthDate.setOnClickListener(v -> showDatePickerDialog());

        btn_add1.setOnClickListener(view -> {
            if (validateInputs()) {
                // Lấy dữ liệu từ các Spinner
                String address = spin_address.getSelectedItem().toString();
                String major = spin_major.getSelectedItem().toString();
                String year = spin_year.getSelectedItem().toString();

                // Tạo một đối tượng Student mới
                item_student.Student newStudent = new item_student.Student();
                newStudent.setId(edtStudentID.getText().toString());
                newStudent.setFullName(edtName.getText().toString());
                newStudent.setBirthDate(edtBirthDate.getText().toString());
                newStudent.setEmail(edtEmail.getText().toString());
                newStudent.setGpa(Double.parseDouble(edtGPA.getText().toString()));
                newStudent.setAddress(address);
                newStudent.setMajor(major);
                newStudent.setYear(Integer.parseInt(year));

                // Gửi dữ liệu về MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("new_student", newStudent);
                setResult(RESULT_OK, resultIntent);
                finish();
                Toast.makeText(AddStudent.this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_add), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                AddStudent.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    edtBirthDate.setText(date);
                },
                year, month, day);

        datePickerDialog.show();
    }

    private boolean validateInputs() {
        String studentID = edtStudentID.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String birthDate = edtBirthDate.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String gpaStr = edtGPA.getText().toString().trim();

        // 1. Kiểm tra mã sinh viên
        if (TextUtils.isEmpty(studentID)) {
            edtStudentID.setError("Mã sinh viên không được để trống");
            return false;
        }

        // 2. Kiểm tra họ tên
        if (TextUtils.isEmpty(name)) {
            edtName.setError("Họ tên không được để trống");
            return false;
        } else if (!Pattern.matches("[a-zA-Z ]+", name)) {
            edtName.setError("Họ tên chỉ chứa chữ cái và khoảng trắng");
            return false;
        }

        // 3. Kiểm tra ngày sinh
        if (TextUtils.isEmpty(birthDate)) {
            edtBirthDate.setError("Ngày sinh không được để trống");
            return false;
        }

        // 4. Kiểm tra email
        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Email không được để trống");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Email không hợp lệ");
            return false;
        }

        // 5. Kiểm tra GPA
        if (TextUtils.isEmpty(gpaStr)) {
            edtGPA.setError("GPA không được để trống");
            return false;
        }
        try {
            double gpa = Double.parseDouble(gpaStr);
            if (gpa < 0.0 || gpa > 4.0) {
                edtGPA.setError("GPA phải nằm trong khoảng từ 0.0 đến 4.0");
                return false;
            }
        } catch (NumberFormatException e) {
            edtGPA.setError("GPA phải là số hợp lệ");
            return false;
        }

        return true;
    }
}
