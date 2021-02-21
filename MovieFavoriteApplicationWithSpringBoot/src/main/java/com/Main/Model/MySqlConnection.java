package com.Main.Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {

    private static Connection instanceConnection;

    public static synchronized Connection getInstanceConnection(){
        if(instanceConnection == null){
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                instanceConnection = DriverManager
                        .getConnection("jdbc:sqlserver://localhost:1433;databaseName=movie_db;user=sa;password=123123;");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return instanceConnection;
    }
}
