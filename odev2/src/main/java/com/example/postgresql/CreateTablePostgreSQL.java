package com.example.postgresql;

import com.example.dto.Person;
import java.util.List;
import org.jdbi.v3.core.Jdbi;

public class CreateTablePostgreSQL {
    public static void main(String[] args) {
        Jdbi jdbi = DatabaseConnectionPostgreSQL.getJdbi();

        jdbi.useHandle(handle -> {
            handle.execute("CREATE TABLE IF NOT EXISTS person (id SERIAL PRIMARY KEY, first_name VARCHAR(50), last_name VARCHAR(50))");
            handle.execute("INSERT INTO person (first_name, last_name) VALUES (?, ?)", "Sarp", "Sarpoglu");
            handle.execute("INSERT INTO person (first_name, last_name) VALUES (?, ?)", "Hasan", "Hasanoglu");
            handle.execute("INSERT INTO person (first_name, last_name) VALUES (?, ?)", "Halil", "Haliloglu");
            handle.execute("DELETE FROM person WHERE first_name = ?", "Halil");

            // READ
            List<Person> persons = handle.createQuery("SELECT first_name, last_name FROM person")
                .mapToBean(Person.class)
                .list();
            persons.forEach(person -> System.out.printf("firstName= %s, lastName= %s%n", person.getFirstName(), person.getLastName()));
        });

        System.out.println("Tablo olusturuldu ve icerisine data eklendi.");

        // Exit the application
        System.exit(0);
    }
}
