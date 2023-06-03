package com.example.calculator_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solutionTv, resultTv;
    MaterialButton buttonC, buttonBracketOpen, buttonBracketClose;
    MaterialButton buttonDivide, buttonPlus, buttonMinus, buttonEquals, buttonMultiply;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solutionTv = findViewById(R.id.solution_tv);
        resultTv = findViewById(R.id.result_tv);

        assignId(buttonC, R.id.button_c);
        assignId(buttonAC, R.id.button_ac);
        assignId(buttonBracketOpen, R.id.button_open_bracket);
        assignId(buttonBracketClose, R.id.button_close_bracket);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonPlus, R.id.button_plus);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonEquals, R.id.button_equals);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonDot, R.id.button_dot);
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String inputToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
        }
        if (buttonText.equals("C")){
            inputToCalculate = inputToCalculate.substring(0, inputToCalculate.length() - 1);
        }else{
            inputToCalculate = inputToCalculate + buttonText;
        }

        solutionTv.setText(inputToCalculate);

        String finalResult = getResult(inputToCalculate);

        if (!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String input){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,input,"Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")){
                finalResult.replace(".0", "");
            }
            return finalResult;
        }catch(Exception e){
            return "Err";
        }
    }
}