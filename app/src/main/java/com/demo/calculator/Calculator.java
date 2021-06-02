package com.demo.calculator;

public class Calculator {

    private String symbol;
    private StringBuilder firstNum;
    private StringBuilder secondNum;

    public Calculator(StringBuilder firstNum, String symbol, StringBuilder secondNum) {
        this.symbol = symbol;
        this.firstNum = firstNum;
        this.secondNum = secondNum;
    }

    public Double calculate() {
        Double result;
        switch (symbol) {
            case "+":
                result = Double.parseDouble(firstNum.toString()) + Double.parseDouble(secondNum.toString());
                break;
            case "-":
                result = Double.parseDouble(firstNum.toString()) - Double.parseDouble(secondNum.toString());
                break;
            case "/":
                result = Double.parseDouble(firstNum.toString()) / Double.parseDouble(secondNum.toString());
                break;
            default:
                result = Double.parseDouble(firstNum.toString()) * Double.parseDouble(secondNum.toString());
                break;
        }
        return result;
    }
}