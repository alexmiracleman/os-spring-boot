package org.alex.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.alex.repository.UserRepository;
import org.alex.repository.jdbc.mapper.UserRowMapper;
import org.alex.entity.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcUserDao implements UserRepository {

    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();
    private static final String FIND_ALL_SQL = """
            SELECT id, email, password, salt, user_type FROM users;
            """;
    private static final String ADD_SQL = """
            INSERT INTO users (email, password, salt, user_type) VALUES (:email, :password, :salt, :user_type);
             """;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<User> findAll() {
        return namedParameterJdbcTemplate.query(FIND_ALL_SQL, USER_ROW_MAPPER);
    }

    @Override
    public void add(User user) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("email", user.getEmail());
        parameters.addValue("password", user.getPassword());
        parameters.addValue("salt", user.getSalt());
        parameters.addValue("user_type", user.getUserType());

        namedParameterJdbcTemplate.update(ADD_SQL, parameters);
    }

   //test only
   public void deleteAll() {
       namedParameterJdbcTemplate.getJdbcTemplate().update("DELETE FROM users");
   }
}
