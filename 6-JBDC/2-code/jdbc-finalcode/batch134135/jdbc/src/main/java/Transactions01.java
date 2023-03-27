import java.sql.*;

public class Transactions01 {
    public static void main(String[] args) throws Exception {
        // -- Create Connection
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                "dev_user", "password");

        // -- Create Statement (to execute sql queries)
        Statement stmnt = con.createStatement();

        //TASK-1. Transfer amount of 1000 from account_nu:1234 to account_nu:5678

        con.setAutoCommit(false); //by setting setAutoCommit() to false we are telling
        // that we will commit manually
        Savepoint sp = null;
        try {
            //insert into
            //
            sp = con.setSavepoint();
            String query = "UPDATE accounts SET amount = amount + ? WHERE account_nu = ?";
            PreparedStatement prs = con.prepareStatement(query);
            prs.setDouble(1, -1000);
            prs.setInt(2, 1234);
            prs.executeUpdate();
            //suppose we have some system error
            //i am forcing the code to stop
            if (false) {
                throw new Exception(); //the code lines after exception will not run
            }
            prs.setDouble(1, 1000);
            prs.setInt(2, 5678);
            prs.executeUpdate();
            con.commit(); //saves/persists changes on db
            prs.close();
            con.close();
        }catch (Exception e){
            con.rollback(sp); //cancels all previous activies.. returns to savePoint
        }

    }
}
