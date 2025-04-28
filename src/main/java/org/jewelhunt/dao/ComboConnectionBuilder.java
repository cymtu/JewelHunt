package org.jewelhunt.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Пул соединений к базе данных
 * @author Барабанов А.В.
 * @version 1.0.1
 */
public class ComboConnectionBuilder
{
    private final ComboPooledDataSource dataSource;

    public ComboConnectionBuilder() {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass("org.sqlite.JDBC");
            dataSource.setJdbcUrl("jdbc:sqlite:jewelhunt.db");
            dataSource.setMaxPoolSize(3);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
