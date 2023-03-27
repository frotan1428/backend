import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {

        // -- Create Connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "dev_user", "password");

        //test if connection established
        //System.out.println("Connection created successfully");

        // -- Create Statement (to execute sql queries)
        Statement stmnt = con.createStatement();

        //TASK-1. GET/SELECT  "country_name" from countries table with ID between 5 and 10
        System.out.println("*********** TASK 1 ***********");
        String query1 = "SELECT country_name FROM countries WHERE id BETWEEN 5 AND 10";
        boolean rs = stmnt.execute(query1);
        System.out.println("rs: "+rs);

        //to display query result we use executeQuery()
        ResultSet rs1 = stmnt.executeQuery(query1);
//        rs1.next();
//        System.out.println(rs1.getString("country_name"));
        while (rs1.next()){
            //get by column
            System.out.println(rs1.getString("country_name"));
            //get by column index
            //System.out.println(rs1.getString(1));
        }

        System.out.println("*********** TASK 2 ***********");
        //TASK - 2: Get "phone_code" and "country_name" from countries table,
        // whose phone code is greater than 600

        String query2 = "SELECT phone_code, country_name FROM countries WHERE phone_code>600";
        ResultSet rs2 = stmnt.executeQuery(query2);

        while (rs2.next()){
            System.out.println(rs2.getInt("phone_code")+"--"
                    +rs2.getString("country_name"));
        }

        System.out.println("*********** TASK 3 ***********");
        //TASK-3. Get all information about the developers whose salary is lowest
        String query3 = "SELECT * FROM developers WHERE salary = (SELECT min(salary) FROM developers) ";
        ResultSet rs3 = stmnt.executeQuery(query3);
        while (rs3.next()){
            System.out.println(rs3.getInt("id") +" "+rs3.getString("name")+" -- "
            +rs3.getDouble("salary")+ " -- " + rs3.getString("prog_lang"));
        }
        ////TASK - 4 : Display students' name and grade whose grades are
        // higher than average passing grade of departments.


        System.out.println("*********** TASK 4 ***********");
        String query4 = "SELECT name, grade FROM students WHERE grade>(SELECT AVG(pass_grade) FROM departments)";
        ResultSet rs4= stmnt.executeQuery(query4);
        while (rs4.next()){
            System.out.println(rs4.getString("name")+"--"+rs4.getInt("grade"));
        }

        //ResultSet has other methods such as first(), last(), next()
        //There is no method to iterate backward
        stmnt.close();
        con.close();

    }
}
