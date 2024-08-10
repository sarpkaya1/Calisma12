package com.example.mysql;

import org.jdbi.v3.core.Jdbi;

public class DatabaseConnectionMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "testuser";
    private static final String PASSWORD = "testpass";
    private static Jdbi jdbi;

    public static Jdbi getJdbi() {
        if (jdbi == null) {
            jdbi = Jdbi.create(URL, USER, PASSWORD);
        }
        return jdbi;
    }
}

