package databaseConfig;

import java.util.HashMap;
import java.util.Map;

public class JPAConfig {
    
    private String db;

    public JPAConfig(String db) {
        this.db = db;
    }

    public Map Config(){
        
        // JDBC driver
        final String JDBC_DRIVER="com.mysql.jdbc.Driver";

        //local settings (default)
        String server = "localhost";
        String port = "3306";
        String db_user = "gerrygj";
        String db_password = "pa55word";
        
        //remote settings
        if(System.getenv("OPENSHIFT_MYSQL_DB_HOST") != null){
            server = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
            db_user = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
            db_password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
        }
            
        String db_url="jdbc:mysql://" + server + ":" + port + "/" + this.db;

        Map<String, String> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.driver", JDBC_DRIVER);
        properties.put("javax.persistence.jdbc.url", db_url);
        properties.put("javax.persistence.jdbc.user", db_user);
        properties.put("javax.persistence.jdbc.password", db_password);
        
        return properties;
    }
    
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
}