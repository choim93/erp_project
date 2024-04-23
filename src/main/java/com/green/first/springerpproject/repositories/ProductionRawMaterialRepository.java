package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.ProductionRawMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductionRawMaterialRepository {
    JdbcTemplate template;

    public ProductionRawMaterialRepository(JdbcTemplate template) {
        this.template = template;
    }

    private static final String SELECT_ALL_QUERY = "SELECT * FROM production_raw_materials";
    private static final String FIND_BY_ID = "SELECT * FROM production_raw_materials WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO production_raw_materials (production_id, raw_material_id, raw_material_quantity, storage_id) VALUES ( ?, ?, ?, ? )";

    public List<ProductionRawMaterial> findAll() {
        return template.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(ProductionRawMaterial.class));
    }

    public ProductionRawMaterial findById(long id) {
        return template.queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(ProductionRawMaterial.class), id);
    }

    public void addProductionRawMaterial(ProductionRawMaterial prm) {
        template.update(INSERT_QUERY, prm.getProductionId(), prm.getRawMaterialId(), prm.getRawMaterialQuantity(), prm.getStorageId());
    }
}
