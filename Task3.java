import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task3 extends JFrame {
    private Account acc;
    private JLabel balanceLabel;
    private JTextField amountField;
    private JTextArea messageArea;

    public Task3(Account acc) {
        this.acc = acc;
        createView();
        setTitle("ATM Machine");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createView() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.PINK);
        getContentPane().add(panel);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        Font font = new Font("Arial", Font.BOLD, 16);

        balanceLabel = new JLabel("Balance: $" + acc.getBalance());
        balanceLabel.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(balanceLabel, constraints);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(amountLabel, constraints);

        amountField = new JTextField(10);
        amountField.setFont(font);
        amountField.setBackground(Color.LIGHT_GRAY);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(amountField, constraints);

        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(font);
        depositButton.setBackground(Color.RED);
        depositButton.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(depositButton, constraints);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(font);
        withdrawButton.setBackground(Color.RED);
        withdrawButton.setForeground(Color.WHITE);
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(withdrawButton, constraints);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setFont(font);
        checkBalanceButton.setBackground(Color.RED);
        checkBalanceButton.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(checkBalanceButton, constraints);
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        messageArea = new JTextArea(5, 20);
        messageArea.setFont(font);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(scrollPane, constraints);
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            acc.deposit(amount);
            messageArea.setText("Successfully deposited: $" + amount);
            updateBalance();
        } catch (NumberFormatException e) {
            messageArea.setText("Invalid amount entered.");
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (acc.withdraw(amount)) {
                messageArea.setText("Successfully withdrew: $" + amount);
            } else {
                messageArea.setText("Insufficient balance or invalid withdrawal amount.");
            }
            updateBalance();
        } catch (NumberFormatException e) {
            messageArea.setText("Invalid amount entered.");
        }
    }

    private void checkBalance() {
        messageArea.setText("Current balance: $" + acc.getBalance());
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: $" + acc.getBalance());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Account acc = new Account(1000); // Initial balance of $1000
                new Task3(acc).setVisible(true);
            }
        });
    }
}
