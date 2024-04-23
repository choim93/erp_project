package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.models.Position;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PositionRowMapper implements RowMapper<Position> {
    @Override
    public Position mapRow(ResultSet rs, int rowNum) throws SQLException {
        Position position = new Position();
        position.setPositionId(rs.getLong("position_id"));
        position.setPositionName(rs.getString("position_name"));

        return position;
    }
}
