import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Task1 extends JFrame{
    private int count=0;
    private int attempt=0;
    private final JLabel att_label;
    private final JLabel message;
    private final JTextField guessField;
    private final JButton guessButton;
    private final JButton tryagain;
    private final int[] random_no = {50, 25, 75, 100, 35};

    public Task1(){
        setTitle("Random Guessing Numbers Game");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));
        getContentPane().setBackground(Color.RED);


        att_label = new JLabel("1st Attempt 1 / 5", SwingConstants.CENTER);
        message = new JLabel("Enter your guess:", SwingConstants.CENTER);
        guessField = new JTextField();
        guessField.setBackground(Color.yellow);
        guessButton = new JButton("Guess");
        guessButton.setBackground(Color.CYAN);
        tryagain = new JButton("Play Again");
        tryagain.setBackground(Color.gray);
        tryagain.setEnabled(false);

        guessButton.addActionListener(new GuessButtonListener());
        tryagain.addActionListener(new tryagainButtonListener());

        add(att_label);
        add(message);
        add(guessField);
        add(guessButton);
        add(tryagain);

        setVisible(true);

    }

    private class GuessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                int randomNo = random_no[attempt];

                if (guess == randomNo) {
                    message.setText("Both are Equal, Your guess is correct");
                    count++;
                } else if (guess < randomNo) {
                    message.setText("Guess is small, Your guess is not correct");
                } else {
                    message.setText("Guess is big, Your guess is not correct");
                }

                attempt++;
                if (attempt < 5) {
                    att_label.setText("Attempt " + (attempt + 1) + " / 5");
                    guessField.setText("");
                } else {
                    att_label.setText("Game Over");
                    guessField.setEnabled(false);
                    guessButton.setEnabled(false);
                    tryagain.setEnabled(true);
                    message.setText("Your score is: " + count);
                }
            } catch (NumberFormatException ex) {
                message.setText("Please enter a valid number");
            }
        }
    }

    private class tryagainButtonListener implements ActionListener {
     
        public void actionPerformed(ActionEvent e) {
            count = 0;
            attempt = 0;
            att_label.setText("Attempt 1 of 5 1 / 5");
            message.setText("Enter your guess:");
            guessField.setText("");
            guessField.setEnabled(true);
            guessButton.setEnabled(true);
            tryagain.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Task1();
            }
        });
    }
}

