package com.green.first.springerpproject.repositories;

import com.green.first.springerpproject.dtos.InventoryDto;
import com.green.first.springerpproject.models.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InventoryRepository {
    @Autowired
    JdbcTemplate jdbcTemp;

    //  모든 창고의 모든 품목 재고량 조회
    static final String SELECT_INVENTORY_TOTAL = """
            SELECT CASE WHEN @warehouse != s.storage_name -- 변수와 창고이름 값이 같으면 창고이름을 출력
                              THEN s.storage_name ELSE ''
                              END AS storage_name,                 
                              itm.item_name,
                              SUM(inv.item_quantity) AS sum_quantity,
                              @warehouse := s.storage_name AS just_comparison_field,
                              -- 변수는 세션마다 초기화 되기 떄문에 변수에 창고이름을 계속 넣어서 창고이름과 비교를 한다 
                              s.storage_id
            FROM inventory AS inv
            JOIN items AS itm ON inv.item_id = itm.Item_id
            JOIN storages AS s ON inv.storage_id = s.storage_id
            JOIN inventory_move_reasons AS reason ON inv.move_reason_id = reason.reason_id
            JOIN (SELECT @warehouse := '') AS var_init -- 변수 warehouse를 에 빈문자를 할당하여 생성 및 초기화,(이때 조인은 일반적 조인과는 다름)
            GROUP BY s.storage_name,itm.Item_id, itm.item_name,s.storage_id  WITH ROLLUP
            HAVING s.storage_name IS NOT NULL AND itm.item_name IS NOT NULL and storage_id is not null
            ORDER BY s.storage_name, itm.Item_id;                                            
            """;

    //  창고 이름별 재고 증감 조회
    static final String SELECT_INVENTORY_HISTORY_BY_STORAGE_ID = """
            SELECT s.storage_id,s.storage_name,itm.item_name,inv.item_quantity,reason.description
                FROM inventory as inv join items as itm on inv.item_id = itm.Item_id join storages as s on inv.storage_id = s.storage_id
                join inventory_move_reasons as reason on inv.move_reason_id = reason.reason_id
                WHERE s.storage_id = ?
                order by inv.inventory_id;
                """;

    static final String INSERT_INVENTORY = "insert into inventory (storage_id, item_id, item_quantity, move_reason_id) values ( ? ,? ,?, ? )";


    public List<InventoryDto> findInventoryTotal() {
        return jdbcTemp.query(SELECT_INVENTORY_TOTAL, new BeanPropertyRowMapper<>(InventoryDto.class));
    }

    public List<InventoryDto> findInventoryHistoryByStorageId(long storage_id) {
        return jdbcTemp.query(SELECT_INVENTORY_HISTORY_BY_STORAGE_ID, new BeanPropertyRowMapper<>(InventoryDto.class), storage_id);
    }

    public void insertInventory(Inventory inventory) {
        jdbcTemp.update(INSERT_INVENTORY, inventory.getStorageId(), inventory.getItemId(), inventory.getItemQuantity(), inventory.getMoveReasonId());
    }

    public void insertInventory(Inventory inventory, long reason_id) {
        jdbcTemp.update(INSERT_INVENTORY, inventory.getItemId(), inventory.getStorageId(), inventory.getItemQuantity(), inventory.getMoveReasonId());
    }

    public void insertInventory(long storageId, long itemId, int quantity, long reason_id) {
        jdbcTemp.update(INSERT_INVENTORY, storageId, itemId, quantity, reason_id);
    }
}