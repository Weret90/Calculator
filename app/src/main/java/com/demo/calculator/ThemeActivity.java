package com.demo.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class ThemeActivity extends AppCompatActivity {

    private Button buttonChooseTheme;
    private RadioButton radioButtonOrangeTheme;
    private RadioButton radioButtonPurpleTheme;
    private RadioButton radioButtonRedTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        radioButtonOrangeTheme = findViewById(R.id.orangeTheme);
        radioButtonPurpleTheme = findViewById(R.id.purpleTheme);
        radioButtonRedTheme = findViewById(R.id.redTheme);
        buttonChooseTheme = findViewById(R.id.buttonChooseTheme);

        buttonChooseTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                if (radioButtonOrangeTheme.isChecked()) {
                    sharedPreferences.edit().putInt("Theme", R.style.Theme_Calculator).apply();
                }
                if (radioButtonPurpleTheme.isChecked()) {
                    sharedPreferences.edit().putInt("Theme", R.style.Theme_Calculator2).apply();
                }
                if (radioButtonRedTheme.isChecked()) {
                    sharedPreferences.edit().putInt("Theme", R.style.Theme_Calculator3).apply();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}