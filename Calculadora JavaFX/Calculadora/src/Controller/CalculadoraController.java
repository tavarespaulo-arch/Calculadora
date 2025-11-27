package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CalculadoraController {

    @FXML
    private TextField display;

   
    private CalculadoraModel model = new CalculadoraModel();

    private boolean novoNumero = true;

    @FXML
    private void onNumberAction(ActionEvent e) {
        String value = ((javafx.scene.control.Button)e.getSource()).getText();
        if (novoNumero) {
            display.setText(value);
            novoNumero = false;
        } else {
            display.setText(display.getText() + value);
        }
    }

    /*
     * Método para adicionar o . no visor da calculadora.
     */
    @FXML
    private void onDotAction(ActionEvent e) {
        if (novoNumero) {
            display.setText("0.");
            novoNumero = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    @FXML
    private void onOperatorAction(ActionEvent e) {
        String op = ((javafx.scene.control.Button)e.getSource()).getText();
        try {
            double current = Double.parseDouble(display.getText());
            model.pushNumber(current);
            model.setPendingOperator(op);
            novoNumero = true;
        } catch (NumberFormatException ex) {
            display.setText("Erro");
            novoNumero = true;
        }
    }

    @FXML
    private void onEqualsAction(ActionEvent e) {
        try {
            double current = Double.parseDouble(display.getText());
            double result = model.computeWith(current);
            display.setText(trimDouble(result));
            novoNumero = true;
        } catch (ArithmeticException ex) {
            display.setText("Erro");
            novoNumero = true;
        } catch (NumberFormatException ex) {
            display.setText("Erro");
            novoNumero = true;
        }
    }

    @FXML
    private void onClearAction(ActionEvent e) {
        model.clear();
        display.setText("0");
        novoNumero = true;
    }

    private String trimDouble(double v) {
        if (v == (long) v)
            return String.format("%d", (long) v);
        else
            return String.valueOf(v);
    }
}
