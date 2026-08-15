import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

    // ---- Fields (components) that need to be reached from more than one method ----
    JLabel lblName, lblGender, lblDepartment, lblAddress, lblSkills;
    JTextField txtName;
    JTextArea txtAddress;
    JRadioButton male, female;
    ButtonGroup genderGroup;
    JCheckBox java, python;
    JComboBox<String> department;
    JButton submit, clear, exit;

    public Main() {
        setTitle("Student Registration Form");
        setSize(500, 500);

        // BorderLayout lets us put the form fields in the middle
        // and a separate button bar along the bottom, instead of
        // squeezing everything into one grid.
        setLayout(new BorderLayout(10, 10));

        // ---------------- Form panel (labels + inputs) ----------------
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        lblName = new JLabel("Student Name:");
        txtName = new JTextField();

        lblGender = new JLabel("Gender:");
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        JPanel genderPanel = new JPanel();
        genderPanel.add(male);
        genderPanel.add(female);

        lblDepartment = new JLabel("Department:");
        String[] dept = {
                "Computer Science",
                "Information Technology",
                "Software Engineering"
        };
        department = new JComboBox<>(dept);

        lblAddress = new JLabel("Address:");
        txtAddress = new JTextArea(3, 20);

        lblSkills = new JLabel("Skills:");
        java = new JCheckBox("Java");
        python = new JCheckBox("Python");
        JPanel skillPanel = new JPanel();
        skillPanel.add(java);
        skillPanel.add(python);

        formPanel.add(lblName);
        formPanel.add(txtName);
        formPanel.add(lblGender);
        formPanel.add(genderPanel);
        formPanel.add(lblDepartment);
        formPanel.add(department);
        formPanel.add(lblAddress);
        formPanel.add(new JScrollPane(txtAddress));
        formPanel.add(lblSkills);
        formPanel.add(skillPanel);

        // ---------------- Button panel ----------------
        submit = new JButton("Submit");
        clear = new JButton("Clear");
        exit = new JButton("Exit");
        submit.addActionListener(this);
        clear.addActionListener(this);
        exit.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(submit);
        buttonPanel.add(clear);
        buttonPanel.add(exit);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Close behavior must be set BEFORE the window is shown.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center the window on screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String gender = "";
            if (male.isSelected()) gender = "Male";
            else if (female.isSelected()) gender = "Female";

            String skills = "";
            if (java.isSelected()) skills += "Java ";
            if (python.isSelected()) skills += "Python";

            String message =
                    "Name: " + txtName.getText() +
                    "\nGender: " + gender +
                    "\nDepartment: " + department.getSelectedItem() +
                    "\nAddress: " + txtAddress.getText() +
                    "\nSkills: " + skills;

            JOptionPane.showMessageDialog(this, message);
        }
        else if (e.getSource() == clear) {
            txtName.setText("");
            txtAddress.setText("");
            genderGroup.clearSelection();
            java.setSelected(false);
            python.setSelected(false);
            department.setSelectedIndex(0);
        }
        else if (e.getSource() == exit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
