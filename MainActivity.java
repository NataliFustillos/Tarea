package com.natalifustillos.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonDiv, buttonMulti, buttonResta, buttonSuma, buttonIgual, buttonDecimal, buttonC, buttonCE;
    private EditText display;
    private double currentValue = 0;
    private String operation = "";

    /**
     *Restaurar el estado de una actividad después de que esta se haya destruido y vuelto a crear
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button0 = findViewById(R.id.button0);
        buttonDecimal = findViewById(R.id.buttonDecimal);
        buttonSuma = findViewById(R.id.buttonSuma);
        buttonIgual = findViewById(R.id.buttonIgual);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonMulti = findViewById(R.id.buttonMulti);
        buttonResta = findViewById(R.id.buttonResta);
        buttonC = findViewById(R.id.buttonC);
        buttonCE = findViewById(R.id.buttonCE);
        display = findViewById(R.id.display);

        View.OnClickListener numberClickListener = new View.OnClickListener() {

            /**
             *Determinar cuál botón se ha hecho clic y realizar una acción específica
             * @param view
             */

            @Override
            public void onClick(View view) {
                Button clickedButton = (Button) view;
                String number = clickedButton.getText().toString();
                addToDisplay(number);
            }
        };

        button1.setOnClickListener(numberClickListener);
        button2.setOnClickListener(numberClickListener);
        button3.setOnClickListener(numberClickListener);
        button4.setOnClickListener(numberClickListener);
        button5.setOnClickListener(numberClickListener);
        button6.setOnClickListener(numberClickListener);
        button7.setOnClickListener(numberClickListener);
        button8.setOnClickListener(numberClickListener);
        button9.setOnClickListener(numberClickListener);
        button0.setOnClickListener(numberClickListener);
        buttonDecimal.setOnClickListener(numberClickListener);
        buttonSuma.setOnClickListener(view -> setOperation("+"));
        buttonIgual.setOnClickListener(view -> caculate());
        buttonResta.setOnClickListener(view -> setOperation("-"));
        buttonDiv.setOnClickListener(view -> setOperation("/"));
        buttonMulti.setOnClickListener(view -> setOperation("*"));
        buttonCE.setOnClickListener(view -> deleteAll());
        buttonC.setOnClickListener(view -> delete());
    }

    /**
     *Guardar el número que se ha presionado en un botón de la calculadora.
     * @param number
     */

    private void addToDisplay(String number) {
        String currentVal = this.display.getText().toString();

        if (currentVal.equals("0") && !number.equals(".")) {
            this.display.setText(number);
        }
        else if (currentVal.contains(".") && number.equals(".")) {
        }
        else {
            this.display.setText(currentVal + number);
        }
    }

    /**
     * Guardar el operador
     * @param op
     */
    private void setOperation (String op) {
        this.currentValue = Double.parseDouble(this.display.getText().toString());
        this.operation = op;
        this.display.setText("0");
    }

    /**
     * Calcular el resultado de acuerdo a la operación escogida en la calculadora
     */
    private void caculate() {
        double secondValue = Double.parseDouble(this.display.getText().toString());
        double result = 0;

        switch (this.operation) {
            case "+":
                result = this.currentValue + secondValue;
                break;
            case "-":
                result = this.currentValue - secondValue;
                break;
            case "/":
                if (secondValue == 0) {
                    this.display.setText("ERROR");
                    return;
                } else {
                    result = this.currentValue / secondValue;
                }
                break;
            case "*":
                result = this.currentValue * secondValue;
                break;
        }

        this.display.setText(String.valueOf(result));
        this.currentValue = result;
    }

    /**
     * Eliminar todos los valores ingresados al presionar el botón CE
     */
    private void deleteAll() {
        this.currentValue = 0;
        this.operation = "";
        this.display.setText("0");
    }

    /**
     * Eliminar el ultimo valor ingresado al presionar el botón C
     */
    private void delete() {
        String currentVal = this.display.getText().toString();

        if (currentVal.length() > 0) {
            currentVal = currentVal.substring(0, currentVal.length() - 1);
            this.display.setText(currentVal);
        }
    }
}