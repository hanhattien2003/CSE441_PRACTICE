package com.example.prac04;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final int ADD_STUDENT_REQUEST = 1;
    RecyclerView recyclerView;
    Button btn_add1;
    FloatingActionButton btn_add;
    List<item_student.Student> studentList;
    StudentAdapter studentAdapter;

    ActivityResultLauncher<Intent> addStudentLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    item_student.Student newStudent = (item_student.Student) data.getSerializableExtra("new_student");
                    if (newStudent != null) {
                        studentList.add(newStudent); // Thêm sinh viên mới vào danh sách
                        studentAdapter.notifyItemInserted(studentList.size() - 1); // Cập nhật RecyclerView
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddStudent.class);
                startActivity(intent);
            }
        });


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddStudent.class);
                addStudentLauncher.launch(intent); // Khởi chạy activity để thêm sinh viên
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        studentList = new ArrayList<>(); // Khởi tạo danh sách sinh viên
        studentAdapter = new StudentAdapter(studentList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentAdapter);

        recyclerView = findViewById(R.id.recyclerView);
        btn_add = findViewById(R.id.btn_add);

        // Khởi tạo danh sách sinh viên và Adapter
        studentList = loadStudentsFromJson();
        studentAdapter = new StudentAdapter(studentList, this);

        // Thiết lập RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentAdapter);





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_add), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private List<item_student.Student> loadStudentsFromJson(){
        List<item_student.Student> students = new ArrayList<>();
        try {
            // Mở tệp JSON trong thư mục assets
            InputStream inputStream = getAssets().open("manual_student_data.json");
            // Đọc dữ liệu từ InputStream
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();

            // Chuyển đổi JSON thành danh sách sinh viên
            JSONArray jsonArray = new JSONArray(jsonString.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                item_student.Student student = new item_student.Student();

                student.setId(jsonObject.getString("id"));

                // Lấy tên đầy đủ từ các trường con
                JSONObject fullNameObject = jsonObject.getJSONObject("full_name");
                student.setFirstName(fullNameObject.getString("first"));
                student.setMiddleName(fullNameObject.optString("midd", "")); // Sử dụng optString để tránh NullPointerException
                student.setLastName(fullNameObject.getString("last"));

                student.setGender(jsonObject.getString("gender"));
                student.setBirthDate(jsonObject.getString("birth_date"));
                student.setEmail(jsonObject.getString("email"));
                student.setAddress(jsonObject.getString("address"));
                student.setMajor(jsonObject.getString("major"));
                student.setGpa(jsonObject.getDouble("gpa"));
                student.setYear(jsonObject.getInt("year"));

                students.add(student);
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return students;
    }

}

