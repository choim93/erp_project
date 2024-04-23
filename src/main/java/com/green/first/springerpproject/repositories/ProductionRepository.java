package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.Production;
import com.green.first.springerpproject.dtos.ProductionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProductionRepository {
    @Autowired
    JdbcTemplate jdbcTemp;

    private static final String SELECT_ALL_PRODUCTION = """
            SELECT p.production_id,p.production_date,i.item_name,p.production_quantity,s.storage_name
              FROM production as p
              join items as i on i.Item_id = p.item_id
              join storages as s on s.storage_id = p.storage_id 
              ORDER BY p.production_date DESC  , p.production_id desc 
              """;
    private static final String INSERT_PRODUCTION = """
            insert into production ( production_date,item_id,production_quantity,storage_id) values(?,?,?,?)""";
    private static final String INSERT_PRODUCTION_RAW_MATERIAL = """
            insert into production_raw_materials ( production_id,raw_material_id,raw_material_quantity,storage_id) values(?,?,?,?);
            """;

    public List<ProductionDto> findAllProduction() {
        return jdbcTemp.query(SELECT_ALL_PRODUCTION, new BeanPropertyRowMapper<>(ProductionDto.class));
    }

    public void insertProduction(long item_id, int item_quantity, long item_storage_id) {
        jdbcTemp.update(INSERT_PRODUCTION, item_id, item_quantity, item_storage_id);
    }

    public long insertProductionAndGetProductionId(LocalDate production_date, long product_id, int productionQuantity, long storageId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemp.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCTION, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, production_date.toString());
                ps.setLong(2, product_id);
                ps.setInt(3, productionQuantity);
                ps.setLong(4, storageId);
                return ps;
            }
        }, keyHolder);

        // keyHolder.getKey()를 사용하여 생성된 기본 키 값을 가져옴
        return keyHolder.getKey().longValue();
    }
}