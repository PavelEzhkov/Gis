package ru.gaz_is.test;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.*;

import org.mockito.Mock;
import ru.gaz_is.dbService.UserDAO;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestClass {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private UserDAO userDAO;

    @Before
    public void before() throws SQLException {
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("name")).thenReturn("Name1");
        when(resultSet.getString("surname")).thenReturn("Surname1");
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void loadTest() {
        String loadedUser = userDAO.load("Name1");
        Assert.assertEquals("Name='Name1', surname='Surname1'", loadedUser);
    }

    @Test
    public void updateTest() throws SQLException {
        userDAO.update("Name1", "Surname3");
        verify(preparedStatement).executeUpdate();
    }
}


