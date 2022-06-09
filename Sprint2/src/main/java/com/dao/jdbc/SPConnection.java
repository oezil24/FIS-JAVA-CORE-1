package com.dao.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SPConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/sprint2";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123456";

    private static HikariConfig config = new HikariConfig("/datasource.properties");
    private static HikariDataSource ds;

    public static ComboPooledDataSource getDataSource()
            throws PropertyVetoException
    {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(URL);
        cpds.setUser(USER_NAME);
        cpds.setPassword(PASSWORD);

        // Optional Settings
        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(100);

        return cpds;

    }
static{
        ds = new HikariDataSource(config);
}
    }

