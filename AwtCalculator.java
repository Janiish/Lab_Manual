import java.awt.*;
import java.awt.event.*;

public class AwtCalculator extends Frame implements ActionListener {
    private TextField display;
    private String operator = "";
    private double num1 = 0;
    private boolean startNewNumber = true;

    public AwtCalculator() {
        setTitle("AWT Calculator");
        setSize(320, 400);
        setLayout(new BorderLayout(10, 10));

        display = new TextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 22));
        add(display, BorderLayout.NORTH);

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (startNewNumber) {
                display.setText(command);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.equals("C")) {
            display.setText("0");
            num1 = 0;
            operator = "";
            startNewNumber = true;
        } else if (command.equals("=")) {
            if (!operator.isEmpty()) {
                double num2 = Double.parseDouble(display.getText());
                double result = calculate(num1, num2, operator);
                display.setText(String.valueOf(result));
                operator = "";
                startNewNumber = true;
            }
        } else {
            num1 = Double.parseDouble(display.getText());
            operator = command;
            startNewNumber = true;
        }
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return b != 0 ? a / b : 0;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        new AwtCalculator();
    }
}
