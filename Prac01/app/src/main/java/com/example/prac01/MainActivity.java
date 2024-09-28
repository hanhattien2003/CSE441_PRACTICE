package com.example.prac01;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prac01.R;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);


        findViewById(R.id.startNewActivityButton).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, com.example.prac01.InputActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String fullName = data.getStringExtra("fullName");
            String gpa = data.getStringExtra("gpa");
            resultTextView.setText("Họ và tên: " + fullName + "\nĐiểm GPA: " + gpa);
        }
    }
}
