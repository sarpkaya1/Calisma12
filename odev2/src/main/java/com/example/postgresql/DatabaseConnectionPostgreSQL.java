package com.example.postgresql;

import org.jdbi.v3.core.Jdbi;

public class DatabaseConnectionPostgreSQL {
    private static final String URL = "jdbc:postgresql://localhost:5432/testdb";
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
