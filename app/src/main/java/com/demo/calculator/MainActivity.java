package com.demo.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        if (symbol.equals("+")) {
            Double result = Double.parseDouble(firstNum.toString()) + Double.parseDouble(secondNum.toString());
            textViewScreen.setText(result.toString());
            firstNum = new StringBuilder(result.toString());
            isSecondNum = false;
            isResultOnScreen = true;
        }
        if (symbol.equals("-")) {
            Double result = Double.parseDouble(firstNum.toString()) - Double.parseDouble(secondNum.toString());
            textViewScreen.setText(result.toString());
            firstNum = new StringBuilder(result.toString());
            isSecondNum = false;
            isResultOnScreen = true;
        }
        if (symbol.equals("/")) {
            Double result = Double.parseDouble(firstNum.toString()) / Double.parseDouble(secondNum.toString());
            textViewScreen.setText(result.toString());
            firstNum = new StringBuilder(result.toString());
            isSecondNum = false;
            isResultOnScreen = true;
        }
        if (symbol.equals("*")) {
            Double result = Double.parseDouble(firstNum.toString()) * Double.parseDouble(secondNum.toString());
            textViewScreen.setText(result.toString());
            firstNum = new StringBuilder(result.toString());
            isSecondNum = false;
            isResultOnScreen = true;
        }
    }

    public void clickOnReset(View view) {
        firstNum = new StringBuilder();
        secondNum = new StringBuilder();
        symbol = "unknown";
        isSecondNum = false;
        textViewScreen.setText("0");
        isResultOnScreen = false;
    }
}