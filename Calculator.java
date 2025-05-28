import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro número: ");
        double num1 = scanner.nextDouble();

        System.out.print("Digite o segundo número: ");
        double num2 = scanner.nextDouble();

        System.out.print("Digite a operação (+, -, *, /): ");
        char operator = scanner.next().charAt(0);

        scanner.close();

        Double result = calculate(num1, num2, operator);

        if (result == null) {
            System.out.println("Operação inválida ou divisão por zero.");
        } else {
            System.out.printf("%.2f %c %.2f = %.2f%n", num1, operator, num2, result);
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
