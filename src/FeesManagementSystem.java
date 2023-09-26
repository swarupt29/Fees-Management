import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class FeesManagementSystem {
    private JFrame frame;
    private Connection connection;
    private DefaultTableModel tableModel;
    private JTable dataTable;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FeesManagementSystem window = new FeesManagementSystem();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FeesManagementSystem() {
        initialize();
        connectToDatabase();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1013, 627); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 222, 173));
        panel.setBounds(10, 10, 979, 58);
        frame.getContentPane().add(panel);

        JLabel lblNewLabel = new JLabel("Fees Management System");
        lblNewLabel.setForeground(new Color(0, 0, 0));
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 40));

        JButton viewDataBtn = new JButton("View Data");
        viewDataBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        viewDataBtn.setBounds(86, 100, 180, 33);
        frame.getContentPane().add(viewDataBtn);

        viewDataBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewData();
            }
        });

        JButton addEntryBtn = new JButton("Add Entry");
        addEntryBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        addEntryBtn.setBounds(300, 100, 180, 33);
        frame.getContentPane().add(addEntryBtn);

        addEntryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddEntryDialog();
            }
        });

        JButton updateEntryBtn = new JButton("Update Entry");
        updateEntryBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        updateEntryBtn.setBounds(513, 100, 180, 33);
        frame.getContentPane().add(updateEntryBtn);

        updateEntryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showUpdateEntryDialog();
            }
        });

        JButton deleteEntryBtn = new JButton("Delete Entry");
        deleteEntryBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        deleteEntryBtn.setBounds(729, 100, 180, 33);
        frame.getContentPane().add(deleteEntryBtn);

        deleteEntryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDeleteEntryDialog();
            }
        });

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Class");
        tableModel.addColumn("Contact");
        tableModel.addColumn("Amount");

        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBounds(50, 150, 900, 400);
        frame.getContentPane().add(scrollPane);
    }

    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Fees", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewData() {
        tableModel.setRowCount(0);

        try {
            String sql = "SELECT * FROM FeeData";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String studentClass = resultSet.getString("Class");
                String contact = resultSet.getString("Contact");
                double amount = resultSet.getDouble("Amount");

                tableModel.addRow(new Object[]{name, studentClass, contact, amount});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            showMessage("Error viewing data.");
        }
    }

    private void showAddEntryDialog() {
        JTextField nameField = new JTextField();
        JTextField classField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField amountField = new JTextField();

        Object[] message = {
            "Name:", nameField,
            "Class:", classField,
            "Contact:", contactField,
            "Amount:", amountField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Add New Entry", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String studentClass = classField.getText();
            String contact = contactField.getText();
            String amountStr = amountField.getText();

            try {
                double amount = Double.parseDouble(amountStr);
                String sql = "INSERT INTO FeeData (Name, Class, Contact, Amount) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, studentClass);
                preparedStatement.setString(3, contact);
                preparedStatement.setDouble(4, amount);
                preparedStatement.executeUpdate();

                showMessage("Fee added successfully.");
            } catch (NumberFormatException | SQLException ex) {
                ex.printStackTrace();
                showMessage("Error adding fee.");
            }
        }
    }

    private void showUpdateEntryDialog() {
        int selectedRow = dataTable.getSelectedRow();

        if (selectedRow >= 0) {
            JTextField nameField = new JTextField();
            JTextField classField = new JTextField();
            JTextField contactField = new JTextField();
            JTextField amountField = new JTextField();

            String name = tableModel.getValueAt(selectedRow, 0).toString();
            String studentClass = tableModel.getValueAt(selectedRow, 1).toString();
            String contact = tableModel.getValueAt(selectedRow, 2).toString();
            String amountStr = tableModel.getValueAt(selectedRow, 3).toString();

            nameField.setText(name);
            classField.setText(studentClass);
            contactField.setText(contact);
            amountField.setText(amountStr);

            Object[] message = {
                "Name:", nameField,
                "Class:", classField,
                "Contact:", contactField,
                "Amount:", amountField
            };

            int option = JOptionPane.showConfirmDialog(frame, message, "Update Entry", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                String updatedName = nameField.getText();
                String updatedClass = classField.getText();
                String updatedContact = contactField.getText();
                String updatedAmountStr = amountField.getText();

                try {
                    double updatedAmount = Double.parseDouble(updatedAmountStr);
                    String sql = "UPDATE FeeData SET Name=?, Class=?, Contact=?, Amount=? WHERE Name=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, updatedName);
                    preparedStatement.setString(2, updatedClass);
                    preparedStatement.setString(3, updatedContact);
                    preparedStatement.setDouble(4, updatedAmount);
                    preparedStatement.setString(5, name);
                    preparedStatement.executeUpdate();

                    showMessage("Fee updated successfully.");
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    showMessage("Error updating fee.");
                }
            }
        } else {
            showMessage("Please select a row to update.");
        }
    }

    private void showDeleteEntryDialog() {
        int selectedRow = dataTable.getSelectedRow();

        if (selectedRow >= 0) {
            String name = tableModel.getValueAt(selectedRow, 0).toString();
            int option = JOptionPane.showConfirmDialog(frame, "Delete the selected entry?", "Delete Entry", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                try {
                    String sql = "DELETE FROM FeeData WHERE Name=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, name);
                    preparedStatement.executeUpdate();

                    showMessage("Fee deleted successfully.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    showMessage("Error deleting fee.");
                }
            }
        } else {
            showMessage("Please select a row to delete.");
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
