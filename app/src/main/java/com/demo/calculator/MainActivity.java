package com.demo.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private StringBuilder firstNum;
    private StringBuilder secondNum;
    private String symbol;
    private boolean isSecondNum = false;
    private boolean isResultOnScreen = false;
    private TextView textViewScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(sp.getInt("Theme", R.style.Theme_Calculator));
        setContentView(R.layout.activity_main);

        textViewScreen = findViewById(R.id.textViewScreen);
        if (savedInstanceState == null) {
            firstNum = new StringBuilder();
            secondNum = new StringBuilder();
        } else {
            firstNum = new StringBuilder(savedInstanceState.getString("firstNum"));
            secondNum = new StringBuilder(savedInstanceState.getString("secondNum"));
            symbol = savedInstanceState.getString("symbol");
            isSecondNum = savedInstanceState.getBoolean("isSecondNum");
            isResultOnScreen = savedInstanceState.getBoolean("isResultOnScreen");
            textViewScreen.setText(savedInstanceState.getString("resultOnScreenNow"));
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("firstNum", firstNum.toString());
        outState.putString("secondNum", secondNum.toString());
        outState.putString("symbol", symbol);
        outState.putBoolean("isSecondNum", isSecondNum);
        outState.putBoolean("isResultOnScreen", isResultOnScreen);
        outState.putString("resultOnScreenNow", textViewScreen.getText().toString());
    }

    public void clickOnNum(View view) {
        Button button = (Button) view;
        if (isSecondNum) {
            secondNum.append(button.getText());
            textViewScreen.setText(secondNum);
        } else {
            if (isResultOnScreen) {
                firstNum = new StringBuilder();
                isResultOnScreen = false;
            }
            firstNum.append(button.getText());
            textViewScreen.setText(firstNum);
        }
    }

    public void clickOnPoint(View view) {
        if (isSecondNum && !secondNum.toString().isEmpty() && !secondNum.toString().contains(".")) {
            secondNum.append(".");
        }
        if (!isSecondNum && !firstNum.toString().isEmpty() && !firstNum.toString().contains(".")) {
            firstNum.append(".");
        }
    }

    public void clickOnSymbol(View view) {
        if (!firstNum.toString().isEmpty()) {
            secondNum = new StringBuilder();
            isSecondNum = true;
            Button button = (Button) view;
            symbol = button.getText().toString();
        }
    }

    public void clickOnEquals(View view) {
        if (!firstNum.toString().isEmpty() && symbol != null && !secondNum.toString().isEmpty()) {
            Double result = new Calculator(firstNum, symbol, secondNum).calculate();
            textViewScreen.setText(result.toString());
            firstNum = new StringBuilder(result.toString());
            isSecondNum = false;
            isResultOnScreen = true;
        }
    }

    public void clickOnReset(View view) {
        firstNum = new StringBuilder();
        secondNum = new StringBuilder();
        symbol = null;
        isSecondNum = false;
        textViewScreen.setText("0");
        isResultOnScreen = false;
    }

    public void clickOnTheme(View view) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getInt("Theme", R.style.Theme_Calculator) == R.style.Theme_Calculator) {
            sharedPreferences.edit().putInt("Theme", R.style.Theme_Calculator2).apply();
        } else {
            sharedPreferences.edit().putInt("Theme", R.style.Theme_Calculator).apply();
        }
        recreate();
    }
}