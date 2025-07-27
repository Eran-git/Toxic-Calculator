import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML private Label display;

    private long numOne;
    private long numTwo;
    private String operator = "";
    private boolean start = true;
    private long result = 0;

    @FXML
    private void handleNumbers(ActionEvent e) {
        if(start){
            display.setText("");
            start = false;

        }

        String value = ((Button)e.getSource()).getText();
        display.setText(display.getText() + value);

    }

    @FXML
    private void handleOperators(ActionEvent e) {
        String newOperator = ((Button) e.getSource()).getText();

        if (start) {
            start = false;
            display.setText(result + " " + newOperator + " ");
        } else {
            display.setText(display.getText() + " " + newOperator + " ");
        }
        operator = newOperator;
    }

    @FXML
    private void handleEqual(ActionEvent  e){
        String[] tokens = display.getText().split(" ");

        if (tokens.length < 3) {
            display.setText("Syntax Error");
            return;
        }

        try {
            numOne = Long.parseLong(tokens[0]);
            operator = tokens[1];
            numTwo = Long.parseLong(tokens[2]);
        } catch (NumberFormatException ex) {
            display.setText("Invalid Number");
            return;
        }

        switch (operator) {
            case "+":
                result = numOne + numTwo;
                break;
            case "-":
                result = numOne - numTwo;
                break;
            case "*":
                result = numOne * numTwo;
                break;
            case "/":
                if (numTwo == 0) {
                    display.setText("Error: divide by 0");
                    operator = "";
                    start = true;
                    return;
                }
                result = numOne / numTwo;
                break;
            default:
                display.setText("Unknown Operator");
                return;
        }

        if(numOne == 1 && numTwo == 1 && operator.equals("+")){
            display.setText("HAHAHA BOBO " + result);
        }else display.setText(String.valueOf(result));

        numOne = result;
        numTwo = 0;
        operator = "";
        start = true;
    }

    @FXML
    private void handleClear(MouseEvent event) {
        if (event.getClickCount() == 2) {
            display.setText("");
            numOne = 0;
            numTwo = 0;
            operator = "";
        } else if (event.getClickCount() == 1) {
            String current = display.getText();
            if (!current.isEmpty()) {
                display.setText(current.substring(0, current.length()-1));
            }
        }
    }

}
