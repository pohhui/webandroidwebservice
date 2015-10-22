package json;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * A class that manages connections to the database. It also has a utility
 * method that close connections, statements and resultsets
 */
public class ConnectionManager {
    /**
     * Gets a connection to the database
     *
     * @return the connection
     * @throws SQLException if an error occurs when connecting
     */
    public static Connection getConnection() throws SQLException {
        // grab environment variable
        String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        
        ApplicationContext appContext;

        if (host != null) {
            appContext = new ClassPathXmlApplicationContext("openshift.xml");
        } else {
            appContext = new ClassPathXmlApplicationContext("local.xml");
        }
        
        DataSource dataSource = (DataSource) appContext.getBean("dataSource");
 
        return dataSource.getConnection();
    }

    /**
     * close the given connection, statement and resultset
     *
     * @param conn the connection object to be closed
     * @param stmt the statement object to be closed
     * @param rs the resultset object to be closed
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.WARNING,
                    "Unable to close ResultSet", ex);
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.WARNING,
                    "Unable to close Statement", ex);
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.WARNING,
                    "Unable to close Connection", ex);
        }
    }
}
