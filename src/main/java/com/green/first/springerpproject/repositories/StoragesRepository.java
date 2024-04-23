package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoragesRepository {
    @Autowired
    JdbcTemplate jdbcTemp;

    static final String SELECT_STORAGES_ALL = "select * from storages";
    static final String SELECT_STORAGE_BYID = "SELECT * FROM storages WHERE storage_id = ?";
    static final String INSERT_STORAGE = "insert into storages ( storage_name, storage_address )values (?, ?)";
    static final String UPDATE_STORAGE_BYID = "update storages set storage_name = ? ," +
            " storage_address = ? where storage_id = ?";
    static final String DELETE_STORAGE_BYID = "delete from storages where storage_id = ?";

    public List<Storage> findAllStorages() {
        return jdbcTemp.query(SELECT_STORAGES_ALL, new BeanPropertyRowMapper<>(Storage.class));
    }

    public Storage findStorageById(long id) {
        return jdbcTemp.queryForObject(SELECT_STORAGE_BYID, new BeanPropertyRowMapper<>(Storage.class), id);
    }

    public void insertStorage(Storage storage) {
        jdbcTemp.update(INSERT_STORAGE, storage.getStorageName(), storage.getStorageAddress());
    }

    public void updateStorageById(Storage storage) {
        jdbcTemp.update(UPDATE_STORAGE_BYID, storage.getStorageName(), storage.getStorageAddress(), storage.getStorageId());
    }

    public void deleteStorageById(long id) {
        jdbcTemp.update(DELETE_STORAGE_BYID, id);
    }

}
