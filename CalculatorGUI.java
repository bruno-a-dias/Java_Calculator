import javax.swing.JOptionPane;

public class CalculatorGUI {

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            // Entrada do primeiro número
            String input1 = JOptionPane.showInputDialog(null, "Digite o primeiro número:");
            if (input1 == null) break; // Usuário cancelou
            double num1;
            try {
                num1 = Double.parseDouble(input1);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            double resultadoAtual = num1;
            boolean novaOperacao = true;

            while (novaOperacao) {
                // Entrada do segundo número
                String input2 = JOptionPane.showInputDialog(null, "Digite o próximo número:");
                if (input2 == null) break; // Usuário cancelou
                double num2;
                try {
                    num2 = Double.parseDouble(input2);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                // Botões das operações
                Object[] options = { "+", "-", "*", "/" };
                int opIndex = JOptionPane.showOptionDialog(
                    null,
                    "Escolha a operação:",
                    "Operação",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
                );

                if (opIndex == JOptionPane.CLOSED_OPTION) break; // Usuário cancelou
                char operator = options[opIndex].toString().charAt(0);

                Double resultado = calculate(resultadoAtual, num2, operator);

                if (resultado == null) {
                    JOptionPane.showMessageDialog(null, "Operação inválida ou divisão por zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                } else {
                    resultadoAtual = resultado;
                    JOptionPane.showMessageDialog(null, "Resultado atual: " + resultadoAtual, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                }

                // Perguntar se deseja continuar com o resultado atual, limpar ou sair
                Object[] nextOptions = { "Nova operação com resultado", "Limpar e começar de novo", "Sair" };
                int nextStep = JOptionPane.showOptionDialog(
                    null,
                    "O que deseja fazer agora?",
                    "Próxima ação",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    nextOptions,
                    nextOptions[0]
                );

                if (nextStep == 0) {
                    // Continua com o resultado atual
                    novaOperacao = true;
                } else if (nextStep == 1) {
                    // Limpa e começa de novo
                    novaOperacao = false;
                } else {
                    // Sair
                    novaOperacao = false;
                    continuar = false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Calculadora finalizada.", "Fim", JOptionPane.INFORMATION_MESSAGE);
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
