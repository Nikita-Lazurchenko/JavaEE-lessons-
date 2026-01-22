package org.example.utils;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public final class ConnectionManager {
    public static String URL = "db.url";
    public static String USER = "db.username";
    public static String PASSWORD = "db.password";
    private static BlockingQueue<Connection> pool;
    private static int poolSize = 10;

    static{
        initConnectionPool();
    }

    private static void initConnectionPool(){
        pool = new ArrayBlockingQueue(poolSize);

        for (int i = 0; i < poolSize; i++) {
           Connection connection = open();
           var proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(),
                   new Class[]{Connection.class},
                   (proxy,method,args) -> method.getName().equals("close") ?
                           pool.add((Connection)proxy) : method.invoke(connection,args));

           pool.add(proxyConnection);
        }

    }

    public static Connection get() {
        try {
            return (Connection) pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection open() {
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
