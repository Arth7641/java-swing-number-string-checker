
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberChecker extends JFrame {

    private JTextField inputField;
    private JTextArea outputArea;   // corrected from JTextField

    public NumberChecker() {
        setTitle("Number Property Checker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Font commonFont = new Font("SansSerif", Font.PLAIN, 14);

        JLabel inputLabel = new JLabel("Enter a number.");
        inputLabel.setFont(commonFont);
        inputField = new JTextField(12);
        inputField.setFont(commonFont);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 2, 10);
        add(inputPanel, gbc);

        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints btnConstraints = new GridBagConstraints();
        btnConstraints.anchor = GridBagConstraints.WEST;
        btnConstraints.fill = GridBagConstraints.NONE;
        btnConstraints.insets = new Insets(3, 10, 3, 5);

        String[] buttonLabels = { "Prime", "Palindrome", "Perfect", "Armstrong", "Buzz", "Harshad" };

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton btn = new JButton(buttonLabels[i]);
            btn.setFont(commonFont);
            btn.setPreferredSize(new Dimension(100, 30));
            btnConstraints.gridx = 0;
            btnConstraints.gridy = i;
            btnConstraints.insets = new Insets(3, 10 + i * 20, 3, 5);
            buttonsPanel.add(btn, btnConstraints);
            btn.addActionListener(new ButtonActionListener(buttonLabels[i]));
        }

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(2, 10, 2, 10);
        add(buttonsPanel, gbc);

        outputArea = new JTextArea(6, 28);
        outputArea.setFont(commonFont);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBorder(BorderFactory.createTitledBorder("Result"));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel outputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        outputPanel.add(scrollPane);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(2, 10, 5, 10);
        add(outputPanel, gbc);

        setVisible(true);
    }

    private class ButtonActionListener implements ActionListener {
        private String operation;

        public ButtonActionListener(String operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String inputText = inputField.getText().trim();
            if (inputText.isEmpty()) {
                outputArea.setText("Please enter a number.");
                return;
            }
            try {
                long number = Long.parseLong(inputText);
                String result = "";
                switch (operation) {
                    case "Prime":
                        result = isPrime(number) ? "Prime number" : "Not a prime number";
                        break;
                    case "Palindrome":
                        result = isPalindrome(number) ? "Palindrome number" : "Not a palindrome number";
                        break;
                    case "Perfect":
                        result = isPerfect(number) ? "Perfect number" : "Not a perfect number";
                        break;
                    case "Armstrong":
                        result = isArmstrong(number) ? "Armstrong number" : "Not an Armstrong number";
                        break;
                    case "Buzz":
                        result = isBuzz(number) ? "Buzz number" : "Not a buzz number";
                        break;
                    case "Harshad":
                        result = isHarshad(number) ? "Harshad number" : "Not a Harshad number";
                        break;
                    default:
                        result = "Unknown operation";
                }
                outputArea.setText("Number: " + number + "\n\n" + operation + " Check:\n" + result);
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid input! Please enter a valid integer.");
            }
        }

        private boolean isPrime(long n) {
            if (n <= 1) return false;
            if (n == 2) return true;
            if (n % 2 == 0) return false;
            for (long i = 3; i < n; i += 2) {
                if (n % i == 0) return false;
            }
            return true;
        }

        private boolean isPalindrome(long n) {
            String str = Long.toString(n);
            String rev = new StringBuilder(str).reverse().toString();
            return str.equals(rev);
        }

        private boolean isPerfect(long n) {
            if (n <= 1) return false;
            long sum = 1;
            for (long i = 2; i < n; i++) {
                if (n % i == 0) {
                    sum += i;
                    if (i != n / i) {
                        sum += n / i;
                    }
                }
            }
            return sum == n;
        }

        private boolean isArmstrong(long n) {
            String str = Long.toString(n);
            int len = str.length();
            long sum = 0;
            long temp = n;
            while (temp > 0) {
                long digit = temp % 10;
                sum += Math.pow(digit, len);
                temp /= 10;
            }
            return sum == n;
        }

        private boolean isBuzz(long n) {
            return (n % 7 == 0) || (n % 10 == 7);
        }

        private boolean isHarshad(long n) {
            if (n <= 0) return false;
            long sum = 0;
            long temp = n;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            return n % sum == 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberChecker());
    }
}