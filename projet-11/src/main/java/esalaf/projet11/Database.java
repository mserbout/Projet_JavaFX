package esalaf.projet11;

import java.sql.*;

public class Database {
    public static Connection connectDb() {
        try {
                //Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:/projet-11","root","");
                return  conn;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
