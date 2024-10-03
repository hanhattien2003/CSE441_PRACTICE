package com.example.prac03;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CountryDetailActivity extends AppCompatActivity {

    private ImageView countryFlagImageView;
    private TextView countryNameTextView, countryDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        countryFlagImageView = findViewById(R.id.countryFlagImageView);
        countryNameTextView = findViewById(R.id.countryNameTextView);
        countryDetailsTextView = findViewById(R.id.countryDetailsTextView);

        Country country = (Country) getIntent().getSerializableExtra("country");

        countryFlagImageView.setImageResource(country.getFlagResource());
        countryNameTextView.setText(country.getName());
        countryDetailsTextView.setText("Capital: " + country.getCapital() + "\n"
                + "Population: " + country.getPopulation() + "\n"
                + "Area: " + country.getArea() + " km²\n"
                + "Density: " + country.getDensity() + " people/km²\n"
                + "World Share: " + country.getWorldShare() + "%");
    }
}
