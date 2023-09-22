package org.alex.Repository.jdbc.mapper;

import org.alex.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    public User mapRow(ResultSet resultSet, int n) throws SQLException {
        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String salt = resultSet.getString("salt");
        String userType = resultSet.getString("user_type");
        return User.builder().
                id(id)
                .email(email)
                .password(password)
                .salt(salt)
                .userType(userType)
                .build();
    }
}
