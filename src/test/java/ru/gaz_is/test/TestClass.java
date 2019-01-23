package ru.gaz_is.test;

import org.junit.*;
import ru.gaz_is.dbService.DBService;
import ru.gaz_is.dbService.DBServiceImpl;
import ru.gaz_is.test.helper.H2ConnectionHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestClass {

    @Before
    public void before() throws SQLException {
        Connection connection = H2ConnectionHelper.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("drop table Users");
            statement.executeUpdate("create table Users (name varchar(256), surname varchar(256))");
            statement.executeUpdate("insert into Users (name, surname) values ('Name1', 'Surname1')");
            statement.executeUpdate("insert into Users (name, surname) values ('Name2', 'Surname2')");
        }
        connection.close();
    }

    @Test
    public void testDB() throws Exception {
        Connection connection = H2ConnectionHelper.getConnection();
        try (DBService dbService = new DBServiceImpl(connection)) {
            dbService.changeSurname("Name1", "Surname3");
            String loadedUser = dbService.read("Name1");
            String user = "Name='Name1', surname='Surname3'";
            Assert.assertNotNull(loadedUser);
            Assert.assertEquals(loadedUser, user);
        }
        connection.close();
    }
}


