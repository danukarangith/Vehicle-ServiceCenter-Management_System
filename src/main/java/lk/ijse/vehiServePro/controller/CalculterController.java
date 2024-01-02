package lk.ijse.vehiServePro.controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

    public class CalculterController {

        @FXML
        private Label display;

        private double num1, num2;
        private String operator = "";

        @FXML
        private void handleDigit(ActionEvent event) {
            String digit = ((Button) event.getSource()).getText();
            appendToDisplay(digit);
        }

        @FXML
        private void handleOperator(ActionEvent event) {
            String newOperator = ((Button) event.getSource()).getText();
            if (!operator.isEmpty()) {
                calculate();
            }
            num1 = Double.parseDouble(display.getText());
            operator = newOperator;
            display.setText("");
        }

        @FXML
        private void handleEquals(ActionEvent event) {
            calculate();
            operator = "";
        }

        @FXML
        private void handleClear(ActionEvent event) {
            num1 = 0;
            num2 = 0;
            operator = "";
            display.setText("");
        }

        private void appendToDisplay(String text) {
            String currentText = display.getText();
            if (currentText.equals("0")) {
                display.setText(text);
            } else {
                display.setText(currentText + text);
            }
        }

        private void calculate() {
            if (operator.isEmpty()) {
                return;
            }
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+":
                    display.setText(Double.toString(num1 + num2));
                    break;
                case "-":
                    display.setText(Double.toString(num1 - num2));
                    break;
                case "*":
                    display.setText(Double.toString(num1 * num2));
                    break;
                case "/":
                    if (num2 == 0) {
                        display.setText("Error");
                    } else {
                        display.setText(Double.toString(num1 / num2));
                    }
                    break;
                default:
                    break;
            }
        }
    }


