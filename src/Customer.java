import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Customer {
    public static ResultSet rs;
    public static Statement st;
    public static Connection conn;
    public static PreparedStatement ps;

    private static JTextField idField;
    private static JTextField lastNameField;
    private static JTextField firstNameField;
    private static JTextField phoneField;

    public static void main(String[] args){

        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb", "root", "rick2908");
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("SELECT * FROM Customer");
            System.out.println(rs);
        }
        catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }

        JFrame frame = new JFrame("Customer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(5, 2));

        frame.add(new JLabel("ID:"));
        idField = new JTextField(10);
        idField.setEditable(false);
        frame.add(idField);

        frame.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(10);
        lastNameField.setEditable(false);
        frame.add(lastNameField);

        frame.add(new JLabel("First Name:"));
        firstNameField = new JTextField(10);
        firstNameField.setEditable(false);
        frame.add(firstNameField);

        frame.add(new JLabel("Phone:"));
        phoneField = new JTextField(10);
        phoneField.setEditable(false);
        frame.add(phoneField);

        JButton prevButton = new JButton("Previous");
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (rs.previous()) {
                        int id = rs.getInt("customer_id");
                        String lastName = rs.getString("customer_last_name");
                        String firstName = rs.getString("customer_first_name");
                        String phone = rs.getString("customer_phone");

                        idField.setText(String.valueOf(id));
                        lastNameField.setText(lastName);
                        firstNameField.setText(firstName);
                        phoneField.setText(phone);
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        frame.add(prevButton);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (rs.next()) {
                        int id = rs.getInt("customer_id");
                        String lastName = rs.getString("customer_last_name");
                        String firstName = rs.getString("customer_first_name");
                        String phone = rs.getString("customer_phone");

                        idField.setText(String.valueOf(id));
                        lastNameField.setText(lastName);
                        firstNameField.setText(firstName);
                        phoneField.setText(phone);
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        try {
            int id = rs.getInt("customer_id");
            String lastName = rs.getString("customer_last_name");
            String firstName = rs.getString("customer_first_name");
            String phone = rs.getString("customer_phone");

            idField.setText(String.valueOf(id));
            lastNameField.setText(lastName);
            firstNameField.setText(firstName);
            phoneField.setText(phone);
        }

        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        frame.add(nextButton);
        frame.setVisible(true);

    }
}
