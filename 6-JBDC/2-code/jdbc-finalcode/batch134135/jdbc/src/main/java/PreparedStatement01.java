import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        // -- Create Connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "dev_user", "password");

        // -- Create Statement (to execute sql queries)
        Statement stmnt = con.createStatement();

        System.out.println("************** TASK-1 ***************");
        //TASK-1. Update pass_grade to 475 of Mathematics department (use PreparedStatement)
        //String query1= "UPDATE departments SET pass_grade=475 WHERE department ILIKE 'mathematics'";

        String query1= "UPDATE departments SET pass_grade=? WHERE department ILIKE ?";
        PreparedStatement prs1= con.prepareStatement(query1);
        prs1.setInt(1, 475);
        prs1.setString(2, "mathematics");

        int numOfUpdatedRows = prs1.executeUpdate();
        System.out.println("numOfUpdatedRows: "+numOfUpdatedRows);

        System.out.println("************** TASK-2 ***************");
        //TASK-2. Update pass_grade to 455 of Literature department (use PreparedStatement)

        prs1.setInt(1, 455);
        prs1.setString(2, "literature");
        int numOfUpdatedRows1=  prs1.executeUpdate();
        System.out.println("numOfUpdatedRows1: "+numOfUpdatedRows1);

        prs1.close();
        stmnt.close();
        con.close();



    }
}
