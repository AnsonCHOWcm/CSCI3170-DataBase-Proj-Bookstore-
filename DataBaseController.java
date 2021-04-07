import java.sql.*;
import java.util.ArrayList;

public class DataBaseController {
    static private String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db1";
    static private String dbUsername = "Group1";
    static private String dbPassword = "CSCI3170";

    static public Connection connectToSQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
            System.out.println("Connection Success\n\n");
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("[ERROR] Java MySQL DB Driver not found.");
            System.exit(0);
        } catch (SQLException e) {
            System.out.println(e);
            System.exit(0);
        }
        return null;
    }

    static public ResultSet runSQL(String sql, ArrayList<String> sqlParms) {
        PreparedStatement prestmt;
        ResultSet r;

        try {
            Connection con = connectToSQL();
            prestmt = con.prepareStatement(sql);
            int i = 1;
            for (String s : sqlParms) {
                prestmt.setString(i, s);
                i++;
            }
            r = prestmt.executeQuery();

            prestmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return r;
    }
}
