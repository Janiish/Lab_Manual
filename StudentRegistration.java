import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Step 2: Create a class that extends JFrame and implements the ActionListener interface
public class StudentRegistration extends JFrame implements ActionListener {
    
    // Step 3: Declare components
    JLabel title, lblUsn, lblName, lblBranch, lblGender, lblSkills;
    JTextField txtUsn, txtName;
    JComboBox<String> cbBranch;
    JRadioButton rbMale, rbFemale;
    ButtonGroup bgGender;
    JCheckBox chkJava, chkPython;
    JButton btnSubmit, btnClear;
    JTextArea txtAreaDetails;

    public StudentRegistration() {
        // Frame Settings
        setTitle("Student Registration Form");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Using absolute positioning for custom layout

        // Title Label
        title = new JLabel("STUDENT REGISTRATION FORM");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(100, 10, 300, 30);
        add(title);

        // USN Label and TextField
        lblUsn = new JLabel("USN:");
        lblUsn.setBounds(50, 60, 100, 20);
        add(lblUsn);
        
        txtUsn = new JTextField();
        txtUsn.setBounds(150, 60, 200, 20);
        add(txtUsn);

        // Name Label and TextField
        lblName = new JLabel("Name:");
        lblName.setBounds(50, 100, 100, 20);
        add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(150, 100, 200, 20);
        add(txtName);

        // Branch Label and ComboBox
        lblBranch = new JLabel("Branch:");
        lblBranch.setBounds(50, 140, 100, 20);
        add(lblBranch);
        
        String[] branches = {"Computer Science", "Information Science", "Electronics", "Mechanical"};
        cbBranch = new JComboBox<>(branches);
        cbBranch.setBounds(150, 140, 200, 20);
        add(cbBranch);

        // Gender Label and Radio Buttons (Step 4: Group the radio buttons)
        lblGender = new JLabel("Gender:");
        lblGender.setBounds(50, 180, 100, 20);
        add(lblGender);
        
        rbMale = new JRadioButton("Male");
        rbMale.setBounds(150, 180, 80, 20);
        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(230, 180, 100, 20);
        
        bgGender = new ButtonGroup();
        bgGender.add(rbMale);
        bgGender.add(rbFemale);
        
        add(rbMale);
        add(rbFemale);

        // Skills Label and CheckBoxes
        lblSkills = new JLabel("Skills:");
        lblSkills.setBounds(50, 220, 100, 20);
        add(lblSkills);
        
        chkJava = new JCheckBox("Java");
        chkJava.setBounds(150, 220, 80, 20);
        chkPython = new JCheckBox("Python");
        chkPython.setBounds(230, 220, 100, 20);
        
        add(chkJava);
        add(chkPython);

        // Submit and Clear Buttons (Step 5: Register buttons)
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(100, 270, 100, 30);
        btnSubmit.addActionListener(this);
        add(btnSubmit);
        
        btnClear = new JButton("Clear");
        btnClear.setBounds(250, 270, 100, 30);
        btnClear.addActionListener(this);
        add(btnClear);

        // Text Area to display student details
        txtAreaDetails = new JTextArea();
        txtAreaDetails.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(txtAreaDetails);
        scrollPane.setBounds(50, 320, 380, 150);
        add(scrollPane);

        setVisible(true);
    }

    // Step 6: Read entered details when buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnSubmit) {
            String usn = txtUsn.getText().trim();
            String name = txtName.getText().trim();
            String branch = (String) cbBranch.getSelectedItem();
            
            // Step 7: Validate that mandatory fields are not empty
            if (usn.isEmpty() || name.isEmpty() || (!rbMale.isSelected() && !rbFemale.isSelected())) {
                JOptionPane.showMessageDialog(this, "Please fill all mandatory fields (USN, Name, and Gender).", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String gender = rbMale.isSelected() ? "Male" : "Female";
            
            StringBuilder skills = new StringBuilder();
            if (chkJava.isSelected()) skills.append("Java ");
            if (chkPython.isSelected()) skills.append("Python");
            if (skills.length() == 0) skills.append("None");

            // Step 8: Display the entered information in the text area
            txtAreaDetails.setText("--- Student Details ---\n");
            txtAreaDetails.append("USN: " + usn + "\n");
            txtAreaDetails.append("Name: " + name + "\n");
            txtAreaDetails.append("Branch: " + branch + "\n");
            txtAreaDetails.append("Gender: " + gender + "\n");
            txtAreaDetails.append("Skills: " + skills.toString() + "\n");
        } 
        // Step 9: Clear all fields when the Clear button is clicked
        else if (e.getSource() == btnClear) {
            txtUsn.setText("");
            txtName.setText("");
            cbBranch.setSelectedIndex(0);
            bgGender.clearSelection();
            chkJava.setSelected(false);
            chkPython.setSelected(false);
            txtAreaDetails.setText("");
        }
    }

    public static void main(String[] args) {
        // Execute the program
        new StudentRegistration();
    }
}
