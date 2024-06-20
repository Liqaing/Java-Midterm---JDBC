import java.sql.*;
import java.sql.ResultSet;

public class CreateConnection {

    public static ResultSet rs;
    public static Statement st;
    public static Connection conn;
    public static PreparedStatement ps;

    public static void main(String[] args){

        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb", "root", "rick2908");
            st = conn.createStatement();

            rs = st.executeQuery("SELECT * FROM Product");
            System.out.println(rs);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                double pricePerUnit = rs.getDouble("PricePerUnit");
                int isActiveForSale = rs.getBoolean("IsActiveForSale") ? 1 : 0;

                System.out.println("ID: " + id + ", Name: " + name + ", Price Per Unit: " + pricePerUnit + ", Is Active For Sale: " + isActiveForSale);
            }
        }
        catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
        }

    }

}
