package ru.gaz_is.dbService;


import java.sql.Connection;


public class DBServiceImpl implements DBService {
    private final Connection connection;
    private UserDAO dao;


    public DBServiceImpl(Connection connection) {
        this.connection = connection;
        dao = new UserDAO(connection);
    }


    @Override
    public String read(String name) {
        return dao.load(name);
    }

    @Override
    public void changeSurname(String name, String surname) {
        dao.update(name, surname);
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
