import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentRegistration extends JFrame implements ActionListener {
    private JTextField usnField;
    private JTextField nameField;
    private JComboBox<String> branchBox;
    private JRadioButton maleRadio, femaleRadio;
    private ButtonGroup genderGroup;
    private JCheckBox javaCheck, pythonCheck;
    private JButton submitBtn, clearBtn;
    private JTextArea displayArea;

    public StudentRegistration() {
        setTitle("Student Registration Form");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        
        JLabel headerLabel = new JLabel("STUDENT REGISTRATION FORM");
        headerLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel separator1 = new JLabel("---------------------------------------------------------");
        separator1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel separator2 = new JLabel("---------------------------------------------------------");
        separator2.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(separator1);
        topPanel.add(headerLabel);
        topPanel.add(separator2);
        
        add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("USN            :"));
        usnField = new JTextField();
        formPanel.add(usnField);

        formPanel.add(new JLabel("Name           :"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Branch         :"));
        String[] branches = {"Computer Science", "Information Science", "Electronics", "Mechanical", "Civil"};
        branchBox = new JComboBox<>(branches);
        formPanel.add(branchBox);

        formPanel.add(new JLabel("Gender         :"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        maleRadio = new JRadioButton("Male   ");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        formPanel.add(genderPanel);

        formPanel.add(new JLabel("Skills         :"));
        JPanel skillsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        javaCheck = new JCheckBox("Java   ");
        pythonCheck = new JCheckBox("Python");
        skillsPanel.add(javaCheck);
        skillsPanel.add(pythonCheck);
        formPanel.add(skillsPanel);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        submitBtn = new JButton("Submit");
        clearBtn = new JButton("Clear");
        submitBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        buttonPanel.add(submitBtn);
        buttonPanel.add(clearBtn);
        
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        
        JPanel detailsHeaderPanel = new JPanel();
        detailsHeaderPanel.setLayout(new BoxLayout(detailsHeaderPanel, BoxLayout.Y_AXIS));
        JLabel separator3 = new JLabel("---------------------------------------------------------");
        JLabel detailsLabel = new JLabel("Student Details");
        JLabel separator4 = new JLabel("---------------------------------------------------------");
        detailsHeaderPanel.add(separator3);
        detailsHeaderPanel.add(detailsLabel);
        detailsHeaderPanel.add(separator4);
        
        bottomPanel.add(detailsHeaderPanel, BorderLayout.NORTH);

        displayArea = new JTextArea(8, 40);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        bottomPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            String usn = usnField.getText().trim();
            String name = nameField.getText().trim();
            String branch = (String) branchBox.getSelectedItem();
            
            String gender = "";
            if (maleRadio.isSelected()) {
                gender = "Male";
            } else if (femaleRadio.isSelected()) {
                gender = "Female";
            }

            StringBuilder skills = new StringBuilder();
            if (javaCheck.isSelected()) skills.append("Java ");
            if (pythonCheck.isSelected()) skills.append("Python");

            String result = "USN    : " + usn + "\n" +
                            "Name   : " + name + "\n" +
                            "Branch : " + branch + "\n" +
                            "Gender : " + gender + "\n" +
                            "Skills : " + skills.toString().trim();
                            
            displayArea.setText(result);
            
        } else if (e.getSource() == clearBtn) {
            usnField.setText("");
            nameField.setText("");
            branchBox.setSelectedIndex(0);
            genderGroup.clearSelection();
            javaCheck.setSelected(false);
            pythonCheck.setSelected(false);
            displayArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentRegistration());
    }
}
