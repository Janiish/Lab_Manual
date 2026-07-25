import java.awt.*;
import java.awt.event.*;

public class SimpleAwtCalculator extends Frame implements ActionListener {
    private TextField display;
    private String operator = "";
    private double num1 = 0;
    private boolean startNewNumber = true;

    public SimpleAwtCalculator() {
        setTitle("AWT Calculator");
        setSize(300, 400);
        setLayout(new BorderLayout());

        display = new TextField("0");
        display.setEditable(false);
        display.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            Button btn = new Button(text);
            btn.setFont(new Font("SansSerif", Font.BOLD, 18));
            btn.addActionListener(this);
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

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
        new SimpleAwtCalculator();
    }
}
