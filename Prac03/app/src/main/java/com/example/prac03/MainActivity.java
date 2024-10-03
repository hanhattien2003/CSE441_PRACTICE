package com.example.prac03;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCrepublic class MainActivity extends AppCompatActivity {
            private RecyclerView recyclerView;
            private CountryAdapter countryAdapter;
            private List<Country> countryList;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                // Tạo danh sách các quốc gia
                countryList = new ArrayList<>();
                countryList.add(new Country("India", "New Delhi", 1428600000, 2973190, 481, 17.76, R.drawable.india_flag));
                countryList.add(new Country("China", "Beijing", 1411778724, 9596961, 153, 18.47, R.drawable.china_flag));
                // Thêm các quốc gia khác...

                countryAdapter = new CountryAdapter(countryList, country -> {
                    Intent intent = new Intent(MainActivity.this, CountryDetailActivity.class);
                    intent.putExtra("country", country);
                    startActivity(intent);
                });

                recyclerView.setAdapter(countryAdapter);
            }
        }
