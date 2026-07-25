// Step 1: Import the required packages java.awt.* and java.awt.event.*
import java.awt.*;
import java.awt.event.*;

// Step 2: Create a class that extends Frame and implements the ActionListener interface
public class SimpleCalculator extends Frame implements ActionListener {

    // Declare components
    Label lblNum1, lblNum2, lblResult;
    TextField txtNum1, txtNum2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnClear;

    public SimpleCalculator() {
        // Frame Settings
        setTitle("Simple Calculator");
        setSize(400, 250);
        setLayout(null); // Absolute layout for custom positioning

        // Step 3: Add two text fields to accept input numbers (and their labels)
        lblNum1 = new Label("First Number:");
        lblNum1.setBounds(50, 50, 100, 20);
        add(lblNum1);

        txtNum1 = new TextField();
        txtNum1.setBounds(160, 50, 150, 20);
        add(txtNum1);

        lblNum2 = new Label("Second Number:");
        lblNum2.setBounds(50, 90, 100, 20);
        add(lblNum2);

        txtNum2 = new TextField();
        txtNum2.setBounds(160, 90, 150, 20);
        add(txtNum2);

        // Step 4: Add five buttons (Addition, Subtraction, Multiplication, Division, and Clear)
        btnAdd = new Button("Add");
        btnAdd.setBounds(40, 140, 50, 30);
        add(btnAdd);

        btnSub = new Button("Subtract");
        btnSub.setBounds(100, 140, 60, 30);
        add(btnSub);

        btnMul = new Button("Multiply");
        btnMul.setBounds(170, 140, 60, 30);
        add(btnMul);

        btnDiv = new Button("Divide");
        btnDiv.setBounds(240, 140, 50, 30);
        add(btnDiv);

        btnClear = new Button("Clear");
        btnClear.setBounds(300, 140, 50, 30);
        add(btnClear);

        // Label to display the result
        lblResult = new Label("Result: ");
        lblResult.setBounds(50, 190, 300, 20);
        add(lblResult);

        // Step 5: Register all buttons using the addActionListener() method
        btnAdd.addActionListener(this);
        btnSub.addActionListener(this);
        btnMul.addActionListener(this);
        btnDiv.addActionListener(this);
        btnClear.addActionListener(this);

        // Step 9: Add a WindowAdapter to close the application
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Step 6: Override the actionPerformed() method to perform operations
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle Clear Button
        if (e.getSource() == btnClear) {
            txtNum1.setText("");
            txtNum2.setText("");
            lblResult.setText("Result: ");
            return;
        }

        // Step 8: Handle invalid inputs and division by zero using exception handling
        try {
            double num1 = Double.parseDouble(txtNum1.getText());
            double num2 = Double.parseDouble(txtNum2.getText());
            double result = 0;

            if (e.getSource() == btnAdd) {
                result = num1 + num2;
                lblResult.setText("Result: " + result); // Step 7: Display result in a label
            } 
            else if (e.getSource() == btnSub) {
                result = num1 - num2;
                lblResult.setText("Result: " + result);
            } 
            else if (e.getSource() == btnMul) {
                result = num1 * num2;
                lblResult.setText("Result: " + result);
            } 
            else if (e.getSource() == btnDiv) {
                if (num2 == 0) {
                    lblResult.setText("Result: Cannot divide by zero");
                } else {
                    result = num1 / num2;
                    lblResult.setText("Result: " + result);
                }
            }
        } catch (NumberFormatException ex) {
            lblResult.setText("Result: Invalid input! Please enter numbers.");
        }
    }

    public static void main(String[] args) {
        // Step 10: Execute the program
        new SimpleCalculator();
    }
}
