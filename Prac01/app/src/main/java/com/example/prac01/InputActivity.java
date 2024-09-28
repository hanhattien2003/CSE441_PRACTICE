package com.example.prac01;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prac01.R;

public class InputActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText gpaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        nameEditText = findViewById(R.id.nameEditText);
        gpaEditText = findViewById(R.id.gpaEditText);

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(view -> {
            String fullName = nameEditText.getText().toString();
            String gpa = gpaEditText.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("fullName", fullName);
            resultIntent.putExtra("gpa", gpa);
            setResult(RESULT_OK, resultIntent);
            finish(); // Close the activity and return to MainActivity
        });
    }
}
