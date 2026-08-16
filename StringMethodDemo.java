import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StringMethodDemo extends JFrame {

    private JTextField textField1, textField2;
    private JTextArea resultArea;

    public StringMethodDemo() {
        setTitle("String Method Tester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Font commonFont = new Font("SansSerif", Font.PLAIN, 14);

        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JLabel label1 = new JLabel("String 1");
        label1.setFont(commonFont);
        textField1 = new JTextField(15);
        textField1.setFont(commonFont);

        JLabel label2 = new JLabel("String 2");
        label2.setFont(commonFont);
        textField2 = new JTextField(15);
        textField2.setFont(commonFont);

        inputPanel.add(label1);
        inputPanel.add(textField1);
        inputPanel.add(label2);
        inputPanel.add(textField2);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 15, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] buttonLabels = { "equals()", "equalsIgnoreCase()", "startsWith()", 
                                  "endsWith()", "contains()", "indexOf()" };

        for (String label : buttonLabels) {
            JButton btn = new JButton(label);
            btn.setFont(commonFont);
            btn.addActionListener(new MethodActionListener(label));
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Result"));
        resultArea = new JTextArea(8, 30);
        resultArea.setFont(commonFont);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        add(resultPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class MethodActionListener implements ActionListener {
        private String methodName;

        public MethodActionListener(String methodName) {
            this.methodName = methodName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String s1 = textField1.getText();
            String s2 = textField2.getText();

            if (s1.isEmpty() && s2.isEmpty()) {
                resultArea.setText("Please enter at least one string.");
                return;
            }

            StringBuilder result = new StringBuilder();
            result.append("String 1: \"").append(s1).append("\"\n");
            result.append("String 2: \"").append(s2).append("\"\n\n");
            result.append("Method: ").append(methodName).append("\n");

            switch (methodName) {
                case "equals()":
                    result.append("s1.equals(s2) = ").append(s1.equals(s2));
                    break;
                case "equalsIgnoreCase()":
                    result.append("s1.equalsIgnoreCase(s2) = ").append(s1.equalsIgnoreCase(s2));
                    break;
                case "startsWith()":
                    result.append("s1.startsWith(s2) = ").append(s1.startsWith(s2));
                    break;
                case "endsWith()":
                    result.append("s1.endsWith(s2) = ").append(s1.endsWith(s2));
                    break;
                case "contains()":
                    result.append("s1.contains(s2) = ").append(s1.contains(s2));
                    break;
                case "indexOf()":
                    result.append("s1.indexOf(s2) = ").append(s1.indexOf(s2));
                    break;
                default:
                    result.append("Unknown method");
            }
            resultArea.setText(result.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StringMethodDemo());
    }
}