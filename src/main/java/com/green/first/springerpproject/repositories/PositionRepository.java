package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionRepository {

    @Autowired
    JdbcTemplate template;

    private final static String SELECT_POSITIONS_BY_ID_QUERY = "SELECT * FROM positions WHERE position_id = ?";
    private final static String SELECT_ALL_POSITIONS_QUERY = "SELECT * FROM positions";
    private final static String INSERT_POSITIONS_QUERY = "INSERT INTO positions(position_name) VALUES (?)";
    private final static String UPDATE_POSITION_BY_ID_QUERY = "UPDATE positions SET position_name = ? WHERE position_id = ?";
    private final static String DELETE_POSITION_BY_ID_QUERY = "DELETE FROM positions WHERE position_id = ?";

    public List<Position> findAllPosition() {
        return template.query(SELECT_ALL_POSITIONS_QUERY, new PositionRowMapper());
    }

    public Position findPositionById(long id) {
        return template.queryForObject(SELECT_POSITIONS_BY_ID_QUERY, new BeanPropertyRowMapper<>(Position.class), id);
    }

    public void insertPosition(String position_name) {
        template.update(INSERT_POSITIONS_QUERY, position_name);
    }

    public void updatePositionById(String position_name, long id) {
        template.update(UPDATE_POSITION_BY_ID_QUERY, position_name, id);
    }

    public void deletePositionById(long id) {
        template.update(DELETE_POSITION_BY_ID_QUERY, id);
    }
}
