package ru.gaz_is.dbService;

public interface DBService extends AutoCloseable {
    String read(String name);

    void changeSurname(String name, String surname);

}
