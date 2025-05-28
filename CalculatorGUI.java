import javax.swing.JOptionPane;

public class CalculatorGUI {

    public static void main(String[] args) {
        // Entrada dos números
        String input1 = JOptionPane.showInputDialog(null, "Digite o primeiro número:");
        if (input1 == null) return; // Usuário cancelou
        String input2 = JOptionPane.showInputDialog(null, "Digite o segundo número:");
        if (input2 == null) return;

        double num1, num2;
        try {
            num1 = Double.parseDouble(input1);
            num2 = Double.parseDouble(input2);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, digite números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Entrada da operação
        String op = JOptionPane.showInputDialog(null, "Digite a operação (+, -, *, /):");
        if (op == null || op.length() == 0) return;
        char operator = op.charAt(0);

        Double result = calculate(num1, num2, operator);

        // Exibição do resultado
        if (result == null) {
            JOptionPane.showMessageDialog(null, "Operação inválida ou divisão por zero.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, String.format("%.2f %c %.2f = %.2f", num1, operator, num2, result), "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static Double calculate(double num1, double num2, char operator) {
        switch (operator) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/': 
                if (num2 == 0) return null;
                return num1 / num2;
            default: return null;
        }
    }
}
