package Controller;


public class CalculadoraModel {

    private Double operand = null;
    private String operator = null;

    public void pushNumber(double number) {
        if (operand == null) {
            operand = number;
        } else {
           
        }
    }

    public void setPendingOperator(String op) {
        
        if (op.equals("x")) op = "*";
        this.operator = op;
    }

    public double computer() {
        if (operator == null || operand == null) {
            return operand == null ? 0.0 : operand;
        }
        
        throw new UnsupportedOperationException("Model expects compute(double second). Use computeWith(double).");
    }

    
    public double computeWith(double second) {
        if (operator == null || operand == null) {
            double res = (operand == null) ? second : operand;
            operand = res;
            operator = null;
            return res;
        }
        double a = operand;
        double b = second;
        double res;
        switch (operator) {
            case "+":
                res = a + b;
                break;
            case "-":
                res = a - b;
                break;
            case "*":
                res = a * b;
                break;
            case "/":
                if (b == 0) throw new ArithmeticException("Division by zero");
                res = a / b;
                break;
            default:
                res = b;
        }
        
        operand = res;
        operator = null;
        return res;
    }

    
    public double compute() {
        throw new UnsupportedOperationException("Use computeWith(double) instead");
    }

    public void clear() {
        operand = null;
        operator = null;
    }
} // fim da classe
