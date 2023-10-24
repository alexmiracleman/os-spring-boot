package org.alex.repository.jdbc.mapper;

import org.alex.entity.Item;
import org.alex.entity.ItemDepartmentType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ItemRowMapper implements RowMapper<Item> {
    public Item mapRow(ResultSet resultSet, int n) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");
        String itemDepartmentTypeId = resultSet.getString("department");
        Timestamp timestamp = resultSet.getTimestamp("time_date");
        return Item.builder().
                id(id)
                .name(name)
                .price(price)
                .itemDepartmentType(ItemDepartmentType.getById(itemDepartmentTypeId))
                .creationModificationDate(timestamp.toLocalDateTime())
                .build();
    }
}
