package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.Item;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ItemRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setItemId(rs.getLong("item_id"));
        item.setRawMaterial(rs.getBoolean("is_raw_material"));
        item.setItemName(rs.getString("item_name"));
        item.setItemPrice(rs.getInt("item_price"));
        return item;
    }
}
