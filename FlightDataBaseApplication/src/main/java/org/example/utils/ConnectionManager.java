package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    public static String URL = "db.url";
    public static String USER = "db.username";
    public static String PASSWORD = "db.password";

    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL),
                    PropertiesUtil.get(USER),
                    PropertiesUtil.get(PASSWORD));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionManager() {
    }
}
