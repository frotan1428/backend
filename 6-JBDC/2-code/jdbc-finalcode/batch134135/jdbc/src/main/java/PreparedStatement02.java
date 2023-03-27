import java.sql.*;

public class PreparedStatement02 {
    public static void main(String[] args) throws SQLException {
        // -- Create Connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "dev_user", "password");

        // -- Create Statement (to execute sql queries)
        Statement stmnt = con.createStatement();

        System.out.println("************ TASK-1 **************");
        //TASK-1. Using preparedstatement, delete students who are from Mathematics department, from students table.
        String query1 = "DELETE FROM students WHERE department ILIKE ?";
        PreparedStatement prs1 = con.prepareStatement(query1);
        prs1.setString(1, "mathematics");
        int updated =  prs1.executeUpdate();
        System.out.println("updated rows: "+updated);

        System.out.println("************ TASK-2 **************");
        //TASK-2. Insert software engineering department using prepared statement into departments table.
        // (id = 5006, pass_grade=475, campus='South')

        String query2 = "INSERT INTO departments VALUES(?,?, ?, ?)";
        PreparedStatement prs2 = con.prepareStatement(query2);
        prs2.setInt(1, 5006);
        prs2.setString(2, "Soft.Eng.");
        prs2.setInt(3, 475);
        prs2.setString(4, "South");

        int numOfUpdatedRows = prs2.executeUpdate();
        System.out.println("numOfUpdatedRows: "+numOfUpdatedRows);
        prs2.close();
        stmnt.close();
        con.close();

    }
}

