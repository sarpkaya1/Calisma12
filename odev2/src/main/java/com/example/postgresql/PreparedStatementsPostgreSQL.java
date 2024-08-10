package com.example.postgresql;

import com.example.dto.Person;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class PreparedStatementsPostgreSQL {
    public static void main(String[] args) {
        Jdbi jdbi = DatabaseConnectionPostgreSQL.getJdbi();

        jdbi.useHandle(handle -> {
            // Tum datayi sil, temizle
            handle.execute("DELETE FROM person");

            // Bir veri olustur
            handle.createUpdate("INSERT INTO person (first_name, last_name) VALUES (:first_name, :last_name)")
                .bind("first_name", "Osman")
                .bind("last_name", "Osmanoglu")
                .execute();

            // Bu veriyi oku
            List<Person> persons = handle.createQuery("SELECT first_name, last_name FROM person")
                .mapToBean(Person.class)
                .list();
            persons.forEach(person -> System.out.printf("Update Oncesi: " + "firstName= %s, lastName= %s%n", person.getFirstName(), person.getLastName()));

            // Bu veriyi guncelle
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
