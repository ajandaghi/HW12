package org.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connect {

    private static Connect INSTANCE;
    private Connection connection;



    private Connect() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            this.connection = connection;
        } catch (SQLException e){
            e.printStackTrace();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public static Connect getInstance()  {
        if(INSTANCE == null) {
            INSTANCE = new Connect();
        }

        return INSTANCE;
    }

    public Connection getConnect() {
        return connection;
    }

    public void setConnect(Connection connection) {
        this.connection = connection;
    }
    // getters and setters
}
