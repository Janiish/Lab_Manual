import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentJDBC {
    
    // Database URL and Credentials
    static final String DB_URL = "jdbc:mysql://localhost:3306/college";
    static final String DB_USER = "root";       // Replace with your MySQL username
    static final String DB_PASSWORD = "password"; // Replace with your MySQL password

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Step 1: Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish a connection with the database
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Step 3: Insert two student records into the database
            String insertQuery = "INSERT INTO student (rollno, name, department, marks) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertQuery);

            // Inserting first record (Rahul)
            pstmt.setInt(1, 101);
            pstmt.setString(2, "Rahul");
            pstmt.setString(3, "CSE");
            pstmt.setInt(4, 85); // Initial marks before the update step
            pstmt.executeUpdate();

            // Inserting second record (Sneha)
            pstmt.setInt(1, 102);
            pstmt.setString(2, "Sneha");
            pstmt.setString(3, "ISE");
            pstmt.setInt(4, 91);
            pstmt.executeUpdate();
            
            System.out.println("Records Inserted Successfully.");

            // Step 4: Update the marks of a student using the Roll Number
            String updateQuery = "UPDATE student SET marks = ? WHERE rollno = ?";
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setInt(1, 95);  // Updating Rahul's marks to 95 as per sample output
            pstmt.setInt(2, 101); 
            pstmt.executeUpdate();
            
            System.out.println("Record Updated Successfully.");

            // Step 5: Search for a student using the Roll Number
            String searchQuery = "SELECT * FROM student WHERE rollno = ?";
            pstmt = conn.prepareStatement(searchQuery);
            pstmt.setInt(1, 101);
            rs = pstmt.executeQuery();

            System.out.println("\nStudent Details");
            if (rs.next()) {
                System.out.println("Roll No: " + rs.getInt("rollno"));
                System.out.println("Name : " + rs.getString("name"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("Marks : " + rs.getInt("marks"));
            }
            rs.close(); // Close search ResultSet

            // Step 6: Display all student records
            String displayQuery = "SELECT * FROM student";
            pstmt = conn.prepareStatement(displayQuery);
            rs = pstmt.executeQuery();

            System.out.println("\nStudent Records");
            System.out.println("Roll\tName\tDepartment\tMarks");
            while (rs.next()) {
                System.out.print(rs.getInt("rollno") + "\t");
                System.out.print(rs.getString("name") + "\t");
                System.out.print(rs.getString("department") + "\t\t");
                System.out.println(rs.getInt("marks"));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Ensure the connector JAR is in your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 7: Close the database connection
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
