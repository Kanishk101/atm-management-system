import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bank implements ActionListener {
    private JFrame mainFrame;
    private JTextField inputField;
    private JPasswordField pinField;
    private JLabel balanceLabel, conversionLabel, rateLabel;
    private JComboBox<String> currencyBox;
    private double balance = 1000.00;
    private boolean authenticated = false;
    private String userPin = "";
    
    private final double INR_TO_USD = 80.0, INR_TO_EUR = 90.0, INR_TO_GBP = 100.0;

    public Bank() {
        mainFrame = new JFrame("ATM - Secure Bank");
        mainFrame.setSize(500, 550);
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setBackground(new Color(0, 0, 128));

        Color panelColor = new Color(44, 62, 80);
        Color btnColor = new Color(41, 128, 185);
        Color textColor = Color.WHITE;
        Font font = new Font("Arial", Font.BOLD, 16);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(panelColor);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel pinLabel = new JLabel("Set/Enter PIN:");
        pinLabel.setForeground(textColor);
        pinLabel.setFont(font);
        pinField = new JPasswordField(10);
        JButton setPinButton = new JButton("Set PIN");
        JButton authenticateButton = new JButton("Authenticate");
        JButton exitButton = new JButton("Exit");
        styleButton(setPinButton, btnColor, textColor);
        styleButton(authenticateButton, btnColor, textColor);
        styleButton(exitButton, Color.RED, textColor);

        JLabel denominationLabel = new JLabel("Enter Amount:");
        denominationLabel.setForeground(textColor);
        denominationLabel.setFont(font);
        inputField = new JTextField(10);

        JLabel currencyLabel = new JLabel("Choose Currency:");
        currencyLabel.setForeground(textColor);
        currencyLabel.setFont(font);
        String[] currencies = {"INR", "USD", "EUR", "GBP"};
        currencyBox = new JComboBox<>(currencies);

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        styleButton(depositButton, new Color(39, 174, 96), textColor);
        styleButton(withdrawButton, new Color(192, 57, 43), textColor);

        balanceLabel = new JLabel("Set your PIN to begin.");
        balanceLabel.setForeground(textColor);
        balanceLabel.setFont(font);

        conversionLabel = new JLabel("Currency conversion will appear here.");
        conversionLabel.setForeground(textColor);
        conversionLabel.setFont(font);

        rateLabel = new JLabel("Conversion Rates: 1 USD = 80 INR | 1 EUR = 90 INR | 1 GBP = 100 INR");
        rateLabel.setForeground(textColor);
        rateLabel.setFont(font);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(pinLabel, gbc);
        gbc.gridx = 1; panel.add(pinField, gbc);
        gbc.gridx = 2; panel.add(setPinButton, gbc);
        gbc.gridx = 3; panel.add(authenticateButton, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panel.add(denominationLabel, gbc);
        gbc.gridx = 1; panel.add(inputField, gbc);
        gbc.gridx = 0; gbc.gridy = 2; panel.add(currencyLabel, gbc);
        gbc.gridx = 1; panel.add(currencyBox, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panel.add(depositButton, gbc);
        gbc.gridx = 1; panel.add(withdrawButton, gbc);
        gbc.gridx = 2; panel.add(exitButton, gbc);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3; panel.add(balanceLabel, gbc);
        gbc.gridy = 5; panel.add(conversionLabel, gbc);
        gbc.gridy = 6; panel.add(rateLabel, gbc);

        setPinButton.addActionListener(this);
        authenticateButton.addActionListener(this);
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        exitButton.addActionListener(e -> System.exit(0));

        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String enteredPin = new String(pinField.getPassword());

        if (action.equals("Set PIN")) {
            if (enteredPin.isEmpty()) {
                balanceLabel.setText("PIN cannot be empty.");
                return;
            }
            userPin = enteredPin;
            balanceLabel.setText("PIN set successfully. Now authenticate.");
        } else if (action.equals("Authenticate")) {
            if (enteredPin.equals(userPin)) {
                authenticated = true;
                balanceLabel.setText("Authentication successful! Balance: " + balance + " INR");
            } else {
                balanceLabel.setText("Incorrect PIN. Try again.");
            }
        } else if (authenticated) {
            try {
                double amount = Double.parseDouble(inputField.getText());
                if (amount <= 0) {
                    balanceLabel.setText("Enter a valid positive amount.");
                    return;
                }
                String selectedCurrency = (String) currencyBox.getSelectedItem();
                double convertedAmount = convertToINR(amount, selectedCurrency);
                if (action.equals("Deposit")) {
                    balance += convertedAmount;
                } else if (action.equals("Withdraw")) {
                    if (convertedAmount > balance) {
                        balanceLabel.setText("Insufficient balance.");
                        return;
                    }
                    balance -= convertedAmount;
                }
                conversionLabel.setText("Converted: " + String.format("%.2f", amount) + " " + selectedCurrency + " = " + String.format("%.2f", convertedAmount) + " INR");
                balanceLabel.setText("Balance: " + String.format("%.2f", balance) + " INR");
                inputField.setText("");
            } catch (NumberFormatException ex) {
                balanceLabel.setText("Please enter a valid amount!");
            }
        } else {
            balanceLabel.setText("Please authenticate first.");
        }
    }

    private double convertToINR(double amount, String currency) {
        switch (currency) {
            case "USD": return amount * INR_TO_USD;
            case "EUR": return amount * INR_TO_EUR;
            case "GBP": return amount * INR_TO_GBP;
            default: return amount;
        }
    }

    public static void main(String[] args) {
        new Bank();
    }
}
