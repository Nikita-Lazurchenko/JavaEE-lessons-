package org.example.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.entity.Gender;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.exception.DAOException;
import org.example.utils.ConnectionManager;
import org.example.utils.LocalDateFormater;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao implements DAO<User,Integer>{
    public static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_USER = """
            INSERT INTO users (name, birthday, email, password, role, gender)
            VALUES (?,?,?,?,?,?)
            """;

    private static final String GET_BY_EMAIL_AND_PASSWORD_SQL = """
            SELECT * FROM users WHERE email = ? and password = ?
            """;

    public Optional<User> findByEmailAndPassword(String email, String password) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(GET_BY_EMAIL_AND_PASSWORD_SQL))
        {
            statement.setString(1, email);
            statement.setString(2, password);

            var resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()) {
                 user =  User.builder()
                        .name(resultSet.getString("name"))
                        .birthday(LocalDate.from(resultSet.getTimestamp("birthday").toLocalDateTime()))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .role(Role.valueOf(resultSet.getString("role")))
                        .gender(Gender.valueOf(resultSet.getString("gender")))
                        .build();
            }

            return Optional.ofNullable(user);
        }catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        try(var connection = ConnectionManager.get();
            var statement = connection.prepareStatement(SAVE_USER, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, user.getName());
            statement.setTimestamp(2, Timestamp.valueOf(user.getBirthday().atStartOfDay()));
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().toString());
            statement.setString(6, user.getGender().toString());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }

            return user;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
