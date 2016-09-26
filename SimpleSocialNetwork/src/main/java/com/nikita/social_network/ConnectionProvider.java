package com.nikita.social_network;

import com.jolbox.bonecp.BoneCPDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionProvider {
    private static final Logger log = LoggerFactory.getLogger(ConnectionProvider.class);

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dating";
    private static BoneCPDataSource datasource;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        datasource = new BoneCPDataSource();
        datasource.setJdbcUrl(JDBC_URL); // set the
        // JDBC url
        datasource.setMinConnectionsPerPartition(80);
        datasource.setMaxConnectionsPerPartition(500);
        datasource.setAcquireIncrement(30);

        datasource.setUsername("root");
        datasource.setPassword("mysql");
        datasource.setDefaultAutoCommit(false);
    }

    public static Connection getConnection() throws SQLException {
        Connection con = datasource.getConnection();
        con.setAutoCommit(false);
        return con;
    }

}
