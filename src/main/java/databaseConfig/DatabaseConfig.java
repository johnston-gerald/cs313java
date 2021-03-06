package databaseConfig;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConfig {
    // JDBC driver
    private final String JDBC_DRIVER="com.mysql.jdbc.Driver";

    private String db;

    public DatabaseConfig(String db) {
        this.db = db;
    }
    
    public Connection Config(){

        //local settings (default)
        String server = "localhost";
        String port = "3306";
        String db_user = "gerrygj";
        String db_password = "pa55word";
        Connection conn = null;

        //remote settings
        if(System.getenv("OPENSHIFT_MYSQL_DB_HOST") != null){
            server = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
            db_user = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
            db_password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
        }

        //database URL
        String db_url="jdbc:mysql://" + server + ":" + port + "/" + this.db;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(db_url,db_user,db_password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return conn;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
}
