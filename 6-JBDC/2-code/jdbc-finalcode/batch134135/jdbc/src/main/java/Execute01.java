import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.step -- Register Driver
        Class.forName("org.postgresql.Driver");

        //2. step  -- Create Connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "dev_user", "password");

        //test if connection established
        //System.out.println("Connection created successfully");

        //3.step -- Create Statement (to execute sql queries)
        Statement stmnt = con.createStatement();

        //4.step

        //TASK:1. create a table named "employee" with column names
        // of : "employee_id", "employee_name", "salary"

        boolean sql1 = stmnt.execute("CREATE TABLE employee(employee_id INT, employee_name VARCHAR(50), salary REAL)");

        /*
            execute() method return boolean value.
            --when it is used in DDL (CREATE/DROP/UPDATE table) it returns false
            --when it is used in DQL (SELECT ..) it returns true/false
         */
        //System.out.println("sql1:"+sql1);

        //TASK 2: add Varchar (20) column name "city" to employee table
        String query2 = "ALTER TABLE employee ADD COLUMN city VARCHAR(20)";
        stmnt.execute(query2);

        //TASK 3: Delete employee table from SCHEMA
        String query3 =  "DROP TABLE employee";
        stmnt.execute(query3);

        //5 step. close connection
        con.close();
        stmnt.close();

    }
}
