import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {
        // -- Create Connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "dev_user", "password");

        // -- Create Statement (to execute sql queries)
        Statement stmnt = con.createStatement();

        System.out.println("************** Task-1 **************");
        //TASK-1. Update salaries of developers whose salaries are less than average salary with average salary

        String query1 = "UPDATE developers SET salary=(SELECT AVG(salary) FROM developers) " +
                "WHERE salary<(SELECT AVG(salary) FROM developers)";

//        int updatedRows = stmnt.executeUpdate(query1); //executeUpdate() will return number of updated records
//        System.out.println("updatedRows: "+updatedRows);

        System.out.println("************** Task-2 **************");
        //TASK-2. Add new developer to developers table

        String query2 = "INSERT INTO developers (name,salary,prog_lang) VALUES('Sara',4000,'Java')";
//        int numberOfInsertedRows =  stmnt.executeUpdate(query2);
//        System.out.println("numberOfInsertedRows: "+numberOfInsertedRows);

        System.out.println("************** Task-3 **************");
        //TASK-3. DELETE row which has id of 14
        String query3 = "DELETE from developers WHERE ID =14";
        int numOfDeletedRows = stmnt.executeUpdate(query3);
        System.out.println("numOfDeletedRows: "+ numOfDeletedRows);

        System.out.println("************** Task-4 **************");
        //TASK-4. DELETE rows from developers table if  prog_lang is "CSS"
        String query4 = "DELETE from developers WHERE prog_lang ILIKE 'css'";
        int numOfDeletedRows1 = stmnt.executeUpdate(query4);
        System.out.println("numOfDeletedRows1: "+ numOfDeletedRows1);

        stmnt.close();
        con.close();
    }
}
