import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task2 extends JFrame {
    private JTextField marathiField, hindiField, sanskritField, socialScienceField, englishField, mathsField, scienceField;
    private JLabel resultLabel;

    public Task2() {
        setTitle("Student Grade Calculator");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 2));
        getContentPane().setBackground(Color.PINK);

        Font labelFont = new Font("Arial", Font.BOLD, 24);
        Font inputFont = new Font("Arial", Font.PLAIN, 24);
        Font buttonFont = new Font("Arial", Font.BOLD, 24);

        JLabel marathiLabel = new JLabel("Enter the Marks in Marathi:");
        marathiLabel.setFont(labelFont);
        marathiLabel.setOpaque(true);
        marathiLabel.setBackground(Color.YELLOW);
        add(marathiLabel);
        marathiField = new JTextField();
        marathiField.setFont(inputFont);
        add(marathiField);

        JLabel hindiLabel = new JLabel("Enter the Marks in Hindi:");
        hindiLabel.setFont(labelFont);
        hindiLabel.setOpaque(true);
        hindiLabel.setBackground(Color.YELLOW);
        add(hindiLabel);
        hindiField = new JTextField();
        hindiField.setFont(inputFont);
        add(hindiField);

        JLabel sanskritLabel = new JLabel("Enter the Marks in Sanskrit:");
        sanskritLabel.setFont(labelFont);
        sanskritLabel.setOpaque(true);
        sanskritLabel.setBackground(Color.YELLOW);
        add(sanskritLabel);
        sanskritField = new JTextField();
        sanskritField.setFont(inputFont);
        add(sanskritField);

        JLabel socialScienceLabel = new JLabel("Enter the Marks in Social Science:");
        socialScienceLabel.setFont(labelFont);
        socialScienceLabel.setOpaque(true);
        socialScienceLabel.setBackground(Color.YELLOW);
        add(socialScienceLabel);
        socialScienceField = new JTextField();
        socialScienceField.setFont(inputFont);
        add(socialScienceField);

        JLabel englishLabel = new JLabel("Enter the Marks in English:");
        englishLabel.setFont(labelFont);
        englishLabel.setOpaque(true);
        englishLabel.setBackground(Color.YELLOW);
        add(englishLabel);
        englishField = new JTextField();
        englishField.setFont(inputFont);
        add(englishField);

        JLabel mathsLabel = new JLabel("Enter the Marks in Maths:");
        mathsLabel.setFont(labelFont);
        mathsLabel.setOpaque(true);
        mathsLabel.setBackground(Color.YELLOW);
        add(mathsLabel);
        mathsField = new JTextField();
        mathsField.setFont(inputFont);
        add(mathsField);

        JLabel scienceLabel = new JLabel("Enter the Marks in Science:");
        scienceLabel.setFont(labelFont);
        scienceLabel.setOpaque(true);
        scienceLabel.setBackground(Color.YELLOW);
        add(scienceLabel);
        scienceField = new JTextField();
        scienceField.setFont(inputFont);
        add(scienceField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(buttonFont);
        calculateButton.setBackground(Color.RED);
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });
        add(calculateButton);

        resultLabel = new JLabel("");
        resultLabel.setFont(labelFont);
        add(resultLabel);

        setVisible(true);
    }

    private void calculateResult() {
        try {
            float marathi = Float.parseFloat(marathiField.getText());
            float hindi = Float.parseFloat(hindiField.getText());
            float sanskrit = Float.parseFloat(sanskritField.getText());
            float socialScience = Float.parseFloat(socialScienceField.getText());
            float english = Float.parseFloat(englishField.getText());
            float maths = Float.parseFloat(mathsField.getText());
            float science = Float.parseFloat(scienceField.getText());

            float total = marathi + hindi + sanskrit + socialScience + english + maths + science;
            float avg = total / 7;

            String grade;
            if (avg >= 90 && avg <= 100) {
                grade = "A1";
            } else if (avg >= 80 && avg < 90) {
                grade = "A2";
            } else if (avg >= 70 && avg < 80) {
                grade = "B1";
            } else if (avg >= 60 && avg < 70) {
                grade = "B2";
            } else if (avg >= 50 && avg < 60) {
                grade = "C1";
            } else if (avg >= 40 && avg < 50) {
                grade = "C2";
            } else if (avg >= 33 && avg < 40) {
                grade = "D";
            } else {
                grade = "Failed";
            }

            resultLabel.setText("<html>Total Marks: " + total + "<br>Average Marks: " + avg + "<br>Grade: " + grade + "</html>");
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        new Task2();
    }
}
