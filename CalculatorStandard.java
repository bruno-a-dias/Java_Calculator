import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class CalculatorStandard extends JFrame {
    private JTextField display;
    private JTextArea historyArea;
    private String currentInput = "";
    private double result = 0;
    private String operator = "";
    private boolean isNewInput = true;
    private LinkedList<String> history = new LinkedList<>();

    public CalculatorStandard() {
        setTitle("Calculadora Padrão");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 430);
        setLocationRelativeTo(null);
        setLayout(null);

        display = new JTextField("0");
        display.setBounds(10, 10, 315, 40);
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 22));
        add(display);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(historyArea);
        scroll.setBounds(10, 60, 315, 60);
        add(scroll);

        String[] buttonLabels = {
            "7", "8", "9", "/", "√",
            "4", "5", "6", "*", "^",
            "1", "2", "3", "-", "%",
            "0", ".", "C", "+", "="
        };

        int x = 10, y = 130;
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton btn = new JButton(buttonLabels[i]);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.setBounds(x, y, 60, 40);
            add(btn);

            btn.addActionListener(new ButtonClickListener());

            x += 65;
            if ((i + 1) % 5 == 0) {
                x = 10;
                y += 45;
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            if ("0123456789".contains(cmd)) {
                if (isNewInput) {
                    currentInput = cmd;
                    isNewInput = false;
                } else {
                    currentInput += cmd;
                }
                display.setText(currentInput);
            } else if (cmd.equals(".")) {
                if (!currentInput.contains(".")) {
                    currentInput += (currentInput.isEmpty() ? "0." : ".");
                    display.setText(currentInput);
                    isNewInput = false;
                }
            } else if ("+-*/^%".contains(cmd)) {
                processOperator(cmd);
            } else if (cmd.equals("√")) {
                processSqrt();
            } else if (cmd.equals("=")) {
                processEquals();
            } else if (cmd.equals("C")) {
                clearAll();
            }
        }
    }

    private void processOperator(String op) {
        if (!currentInput.isEmpty()) {
            if (!operator.isEmpty()) {
                processEquals();
            } else {
                result = Double.parseDouble(currentInput);
            }
            operator = op;
            isNewInput = true;
        }
    }

    private void processSqrt() {
        if (!currentInput.isEmpty()) {
            double value = Double.parseDouble(currentInput);
            if (value < 0) {
                display.setText("Erro");
                currentInput = "";
                isNewInput = true;
                return;
            }
            double res = Math.sqrt(value);
            addToHistory("√(" + value + ") = " + res);
            display.setText(String.valueOf(res));
            currentInput = String.valueOf(res);
            isNewInput = true;
            operator = "";
        }
    }

    private void processEquals() {
        if (operator.isEmpty() || currentInput.isEmpty()) return;

        double num2 = Double.parseDouble(currentInput);
        double res = 0;
        boolean error = false;
        String operationStr = "";

        switch (operator) {
            case "+": res = result + num2; operationStr = result + " + " + num2; break;
            case "-": res = result - num2; operationStr = result + " - " + num2; break;
            case "*": res = result * num2; operationStr = result + " * " + num2; break;
            case "/":
                if (num2 == 0) {
                    display.setText("Erro: Div/0");
                    error = true;
                } else {
                    res = result / num2;
                    operationStr = result + " / " + num2;
                }
                break;
            case "^": res = Math.pow(result, num2); operationStr = result + " ^ " + num2; break;
            case "%":
                if (num2 == 0) {
                    display.setText("Erro: Div/0");
                    error = true;
                } else {
                    res = result % num2;
                    operationStr = result + " % " + num2;
                }
                break;
        }

        if (!error) {
            addToHistory(operationStr + " = " + res);
            display.setText(String.valueOf(res));
            currentInput = String.valueOf(res);
            result = res;
        }
        operator = "";
        isNewInput = true;
    }

    private void clearAll() {
        currentInput = "";
        result = 0;
        operator = "";
        display.setText("0");
        isNewInput = true;
    }

    private void addToHistory(String entry) {
        history.addFirst(entry);
        if (history.size() > 5) {
            history.removeLast();
        }
        StringBuilder sb = new StringBuilder();
        for (String s : history) {
            sb.append(s).append("\n");
        }
        historyArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorStandard().setVisible(true);
        });
    }
}
