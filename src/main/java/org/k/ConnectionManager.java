package org.k;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by Kateryna on 20.07.2015.
 */
public class ConnectionManager {
    final static Logger logger = Logger.getLogger(DataBaseService.class);

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/";

    public static Connection openConnection(String dataBaseName){
        Connection con = null;
        try {
            Class.forName(JDBC_DRIVER);

            con = DriverManager.getConnection(DB_URL + dataBaseName, "postgres", "P#0409sK");

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        return con;
    }
    public static void closeConnection(Connection con, PreparedStatement pst, ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }

        if (pst != null){
            try {
                pst.close();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }

        if (con != null){
            try {
                con.close();
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
            }
        }
    }
}
