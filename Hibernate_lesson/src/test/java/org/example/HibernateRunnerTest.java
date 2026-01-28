package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Table;
import org.example.converter.BirthdayConverter;
import org.example.entity.Birthday;
import org.example.entity.User;
import org.junit.Test;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class HibernateRunnerTest {

    @Test
    public void testHibernateApi() throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        var user = User.builder()
                .username("email@mail.com")
                .firstName("Alexander")
                .lastName("Duggan")
                .birthday(LocalDate.of(1994,8,21))
                .age(21)
                .build();

        String sql = "insert into %s (%s) values (%s)";


        var tableName  = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
                .map(table -> table.schema() + "." + table.name())
                .orElse(user.getClass().getName());

        Field[] fields = user.getClass().getDeclaredFields();

        var columNames = Arrays.stream(fields).map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
                .map(Column::name)
                .orElse(field.getName()))
                .collect(Collectors.joining(", "));

        var columnValues = Arrays.stream(fields).map(field -> "?").collect(Collectors.joining(", "));

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres","787898");
        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columNames, columnValues));


        for (int i = 3; i < fields.length; i++) {
            fields[i].setAccessible(true);

            preparedStatement.setObject(i + 1 , fields[i].get(user));
        }

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }
}
