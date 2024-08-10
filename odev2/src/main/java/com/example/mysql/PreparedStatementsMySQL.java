package com.example.mysql;

import com.example.dto.Person;
import org.jdbi.v3.core.Jdbi;
import java.util.List;

public class PreparedStatementsMySQL {
    public static void main(String[] args) {
        Jdbi jdbi = DatabaseConnectionMySQL.getJdbi();

        jdbi.useHandle(handle -> {
            // DELETE All Data
            handle.execute("DELETE FROM person");

            // CREATE
            handle.createUpdate("INSERT INTO person (first_name, last_name) VALUES (:first_name, :last_name)")
                .bind("first_name", "Osman")
                .bind("last_name", "Osmanoglu")
                .execute();

            // READ
            List<Person> persons = handle.createQuery("SELECT first_name, last_name FROM person")
                .mapToBean(Person.class)
                .list();
            persons.forEach(person -> System.out.printf("Update Oncesi: firstName= %s, lastName= %s%n", person.getFirstName(), person.getLastName()));

            // UPDATE
            handle.createUpdate("UPDATE person SET last_name = :last_name WHERE first_name = :first_name")
                .bind("last_name", "Hakanoglu")
                .bind("first_name", "Osman")
                .execute();

            // Güncellenmiş verileri tekrar oku
            persons = handle.createQuery("SELECT first_name, last_name FROM person")
                .mapToBean(Person.class)
                .list();
            persons.forEach(person -> System.out.printf("Update Sonrasi: firstName= %s, lastName= %s%n", person.getFirstName(), person.getLastName()));
        });

        System.out.println("PreparedStatement operations completed.");

        // Exit the application
        System.exit(0);
    }
}
