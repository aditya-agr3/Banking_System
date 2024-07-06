import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingSystemGUI extends JFrame {
    private BankingSystem bankingSystem;

    private JTextField accountNumberField;
    private JTextField accountHolderNameField;
    private JTextField amountField;
    private JTextArea outputArea;

    public BankingSystemGUI() {
        bankingSystem = new BankingSystem();

        setTitle("Banking System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(new JLabel("Account Number:"));
        accountNumberField = new JTextField();
        inputPanel.add(accountNumberField);

        inputPanel.add(new JLabel("Account Holder Name:"));
        accountHolderNameField = new JTextField();
        inputPanel.add(accountHolderNameField);

        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);


        JButton createAccountButton = createStyledButton("Create Account", Color.decode("#008080"));
        JButton depositButton = createStyledButton("Deposit", Color.decode("#00BFFF"));
        JButton withdrawButton = createStyledButton("Withdraw", Color.decode("#FF8C00"));
        JButton checkBalanceButton = createStyledButton("Check Balance", Color.decode("#FFD700"));
        JButton exitButton = createStyledButton("Exit", Color.decode("#DC143C"));


        inputPanel.add(createAccountButton);
        inputPanel.add(depositButton);
        inputPanel.add(withdrawButton);
        inputPanel.add(checkBalanceButton);
        inputPanel.add(exitButton);


        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));


        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });

        return button;
    }

    private void createAccount() {
        try {
            String accountNumber = accountNumberField.getText();
            String accountHolderName = accountHolderNameField.getText();
            double initialDeposit = Double.parseDouble(amountField.getText());
            bankingSystem.createAccount(accountNumber, accountHolderName, initialDeposit);
            outputArea.append("Account created successfully.\n");
        } catch (Exception e) {
            outputArea.append("Error creating account: " + e.getMessage() + "\n");
        }
    }

    private void deposit() {
        try {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(amountField.getText());
            bankingSystem.deposit(accountNumber, amount);
            outputArea.append("Deposited Rs." + amount + " to account " + accountNumber + ".\n");
        } catch (Exception e) {
            outputArea.append("Error depositing money: " + e.getMessage() + "\n");
        }
    }

    private void withdraw() {
        try {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(amountField.getText());
            bankingSystem.withdraw(accountNumber, amount);
            outputArea.append("Withdrew Rs." + amount + " from account " + accountNumber + ".\n");
        } catch (Exception e) {
            outputArea.append("Error withdrawing money: " + e.getMessage() + "\n");
        }
    }

    private void checkBalance() {
        try {
            String accountNumber = accountNumberField.getText();
            double balance = bankingSystem.checkBalance(accountNumber);
            outputArea.append("Balance for account " + accountNumber + ": Rs." + balance + "\n");
        } catch (Exception e) {
            outputArea.append("Error checking balance: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankingSystemGUI().setVisible(true));
    }
}
