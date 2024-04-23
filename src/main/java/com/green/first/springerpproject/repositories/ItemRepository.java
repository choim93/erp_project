package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    @Autowired
    JdbcTemplate template;
    static final String SELECT_ITEM_BY_ID_QUERY = "SELECT * FROM items WHERE item_id = ?";
    static final String SELECT_ITEM_QUERY = "SELECT * FROM items";
    static final String SELECT_ONLY_RAW_MATERIAL_QUERY = "SELECT * FROM items WHERE is_raw_material = 1";
    static final String SELECT_ONLY_PRODUCT_QUERY = "SELECT * FROM items WHERE is_raw_material = 0";

    static final String INSERT_ITEM_QUERY = "INSERT INTO items (is_raw_material, item_price, item_name) VALUES (?, ?, ?)";
    static final String UPDATE_ITEM_BY_ID_QUERY = "UPDATE items SET is_raw_material = ?, item_price = ?, item_name = ? , effective_date = CURRENT_DATE WHERE item_id = ?";
    static final String DELETE_ITEM_BY_ID_QUERY = "DELETE FROM items WHERE Item_id = ?";    // 대문자임 대문자임 대문자임 대문자임 대문자임 대문자임 대문자임 대문자임


    public Item findItemById(long id) {
        return template.queryForObject(SELECT_ITEM_BY_ID_QUERY, new BeanPropertyRowMapper<>(Item.class), id);
    }

    public List<Item> findAllItem() {
        return template.query(SELECT_ITEM_QUERY, new ItemRowMapper());
    }

    public List<Item> findOnlyRawMaterial() {
        return template.query(SELECT_ONLY_RAW_MATERIAL_QUERY, new BeanPropertyRowMapper<>(Item.class));
    }

    public List<Item> findOnlyProduct() {
        return template.query(SELECT_ONLY_PRODUCT_QUERY, new BeanPropertyRowMapper<>(Item.class));
    }

    public void insertItem(boolean isRawMaterial, int itemPrice, String itemName) {
        template.update(INSERT_ITEM_QUERY, isRawMaterial, itemPrice, itemName);
    }

    public void updateItemById(boolean isRawMaterial, int itemPrice, String itemName, long id) {
        template.update(UPDATE_ITEM_BY_ID_QUERY, isRawMaterial, itemPrice, itemName, id);
    }

    public void deleteItemById(long id) {
        template.update(DELETE_ITEM_BY_ID_QUERY, id);
    }

}
