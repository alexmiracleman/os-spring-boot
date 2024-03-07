package org.alex.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.alex.repository.ItemRepository;
import org.alex.repository.jdbc.mapper.ItemRowMapper;
import org.alex.entity.Item;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class JdbcItemDao implements ItemRepository {

    private static final ItemRowMapper ITEM_ROW_MAPPER = new ItemRowMapper();

    private static final String FIND_ALL_SQL = """
            SELECT id, name, price, department, time_date FROM inventory ORDER BY id;
            """;

    private static final String ADD_SQL = """
            INSERT INTO inventory (name, price, department, time_date) VALUES (:name, :price, :department, :time_date);
             """;

    private static final String DELETE_SQL = """
            DELETE FROM inventory WHERE name = :name;
             """;

    private static final String UPDATE_SQL = """
            UPDATE inventory SET price = :price, time_date = :time_date WHERE name = :name;
             """;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Item> findAll() {
        return namedParameterJdbcTemplate.query(FIND_ALL_SQL, ITEM_ROW_MAPPER);
    }

    @Override
    public void add(Item item) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("name", item.getName());
        parameters.addValue("price", item.getPrice());
        parameters.addValue("department", item.getItemDepartmentType().getId());
        parameters.addValue("time_date", Timestamp.valueOf(item.getCreationModificationDate()));

        namedParameterJdbcTemplate.update(ADD_SQL, parameters);
    }

    @Override
    public void delete(Item item) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("name", item.getName());

        namedParameterJdbcTemplate.update(DELETE_SQL, parameters);
    }

    @Override
    public void update(Item item) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        parameters.addValue("name", item.getName());
        parameters.addValue("price", item.getPrice());
        parameters.addValue("time_date", Timestamp.valueOf(item.getCreationModificationDate()));

        namedParameterJdbcTemplate.update(UPDATE_SQL, parameters);
    }

    //test only
    @Override
    public void deleteAll() {
        namedParameterJdbcTemplate.getJdbcTemplate().update("DELETE FROM inventory");
    }
}
