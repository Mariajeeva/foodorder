import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calc2 {
    private JFrame frame;
    private JTextField inputField;
    private String operator;
    private double firstNum;

    public calc2() {
        frame = new JFrame("Simple Calculator");
        inputField = new JTextField(20);
        operator = "";
        firstNum = 0;

        // Setting up the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Input field
        frame.add(inputField, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));

        // Buttons
        String[] buttonLabels = { "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+" };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Set frame size and visibility
        frame.setSize(300, 400);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                inputField.setText(inputField.getText() + command);
            } else if (command.equals("C")) {
                inputField.setText("");
                operator = "";
                firstNum = 0;
            } else if (command.equals("=")) {
                double secondNum = Double.parseDouble(inputField.getText());
                switch (operator) {
                    case "+":
                        inputField.setText(String.valueOf(firstNum + secondNum));
                        break;
                    case "-":
                        inputField.setText(String.valueOf(firstNum - secondNum));
                        break;
                    case "*":
                        inputField.setText(String.valueOf(firstNum * secondNum));
                        break;
                    case "/":
                        if (secondNum != 0) {
                            inputField.setText(String.valueOf(firstNum / secondNum));
                        } else {
                            inputField.setText("Error");
                        }
                        break;
                }
                operator = "";
            } else {
                if (!operator.isEmpty()) {
                    return; // Prevent multiple operators
                }
                firstNum = Double.parseDouble(inputField.getText());
                operator = command;
                inputField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new calc2(); // Directly instantiate the calculator
    }
}