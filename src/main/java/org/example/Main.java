package org.example;
import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QLSV";
            String username = "22521474.qlsv";
            String password = "tovinhtien";
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                System.out.println("Connected to SQL Server");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }